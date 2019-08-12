package com.facebook.share;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.internal.CollectionMapper;
import com.facebook.internal.CollectionMapper.OnErrorListener;
import com.facebook.internal.CollectionMapper.OnMapValueCompleteListener;
import com.facebook.internal.CollectionMapper.OnMapperCompleteListener;
import com.facebook.internal.CollectionMapper.ValueMapper;
import com.facebook.internal.Mutable;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.share.Sharer.Result;
import com.facebook.share.internal.ShareContentValidation;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.share.internal.VideoUploader;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideoContent;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants.PushService;

public final class ShareApi {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_GRAPH_NODE = "me";
    private static final String GRAPH_PATH_FORMAT = "%s/%s";
    private static final String PHOTOS_EDGE = "photos";
    private static final String TAG = "ShareApi";
    private String graphNode = DEFAULT_GRAPH_NODE;
    private String message;
    private final ShareContent shareContent;

    public static void share(ShareContent shareContent2, FacebookCallback<Result> callback) {
        new ShareApi(shareContent2).share(callback);
    }

    public ShareApi(ShareContent shareContent2) {
        this.shareContent = shareContent2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message2) {
        this.message = message2;
    }

    public String getGraphNode() {
        return this.graphNode;
    }

    public void setGraphNode(String graphNode2) {
        this.graphNode = graphNode2;
    }

    public ShareContent getShareContent() {
        return this.shareContent;
    }

    public boolean canShare() {
        if (getShareContent() == null) {
            return false;
        }
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null) {
            return false;
        }
        Set<String> permissions2 = accessToken.getPermissions();
        if (permissions2 == null || !permissions2.contains("publish_actions")) {
            Log.w(TAG, "The publish_actions permissions are missing, the share will fail unless this app was authorized to publish in another installation.");
        }
        return true;
    }

    public void share(FacebookCallback<Result> callback) {
        if (!canShare()) {
            ShareInternalUtility.invokeCallbackWithError(callback, "Insufficient permissions for sharing content via Api.");
            return;
        }
        ShareContent shareContent2 = getShareContent();
        try {
            ShareContentValidation.validateForApiShare(shareContent2);
            if (shareContent2 instanceof ShareLinkContent) {
                shareLinkContent((ShareLinkContent) shareContent2, callback);
            } else if (shareContent2 instanceof SharePhotoContent) {
                sharePhotoContent((SharePhotoContent) shareContent2, callback);
            } else if (shareContent2 instanceof ShareVideoContent) {
                shareVideoContent((ShareVideoContent) shareContent2, callback);
            } else if (shareContent2 instanceof ShareOpenGraphContent) {
                shareOpenGraphContent((ShareOpenGraphContent) shareContent2, callback);
            }
        } catch (FacebookException ex) {
            ShareInternalUtility.invokeCallbackWithException(callback, ex);
        }
    }

    /* access modifiers changed from: private */
    public String getGraphPath(String pathAfterGraphNode) {
        try {
            return String.format(Locale.ROOT, GRAPH_PATH_FORMAT, new Object[]{URLEncoder.encode(getGraphNode(), "UTF-8"), pathAfterGraphNode});
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    private void addCommonParameters(Bundle bundle, ShareContent shareContent2) {
        List<String> peopleIds = shareContent2.getPeopleIds();
        if (!Utility.isNullOrEmpty((Collection<T>) peopleIds)) {
            bundle.putString(PushService.PARAM_TAGS, TextUtils.join(", ", peopleIds));
        }
        if (!Utility.isNullOrEmpty(shareContent2.getPlaceId())) {
            bundle.putString("place", shareContent2.getPlaceId());
        }
        if (!Utility.isNullOrEmpty(shareContent2.getRef())) {
            bundle.putString("ref", shareContent2.getRef());
        }
    }

    private void shareOpenGraphContent(ShareOpenGraphContent openGraphContent, final FacebookCallback<Result> callback) {
        final Callback requestCallback = new Callback() {
            public void onCompleted(GraphResponse response) {
                JSONObject data = response.getJSONObject();
                ShareInternalUtility.invokeCallbackWithResults(callback, data == null ? null : data.optString("id"), response);
            }
        };
        final ShareOpenGraphAction action = openGraphContent.getAction();
        final Bundle parameters = action.getBundle();
        addCommonParameters(parameters, openGraphContent);
        if (!Utility.isNullOrEmpty(getMessage())) {
            parameters.putString("message", getMessage());
        }
        final FacebookCallback<Result> facebookCallback = callback;
        stageOpenGraphAction(parameters, new OnMapperCompleteListener() {
            public void onComplete() {
                try {
                    ShareApi.handleImagesOnAction(parameters);
                    new GraphRequest(AccessToken.getCurrentAccessToken(), ShareApi.this.getGraphPath(URLEncoder.encode(action.getActionType(), "UTF-8")), parameters, HttpMethod.POST, requestCallback).executeAsync();
                } catch (UnsupportedEncodingException ex) {
                    ShareInternalUtility.invokeCallbackWithException(facebookCallback, ex);
                }
            }

            public void onError(FacebookException exception) {
                ShareInternalUtility.invokeCallbackWithException(facebookCallback, exception);
            }
        });
    }

    /* access modifiers changed from: private */
    public static void handleImagesOnAction(Bundle parameters) {
        String imageStr = parameters.getString(ContentFrameworkAnalytics.IMAGE);
        if (imageStr != null) {
            try {
                JSONArray images = new JSONArray(imageStr);
                for (int i = 0; i < images.length(); i++) {
                    JSONObject jsonImage = images.optJSONObject(i);
                    if (jsonImage != null) {
                        putImageInBundleWithArrayFormat(parameters, i, jsonImage);
                    } else {
                        parameters.putString(String.format(Locale.ROOT, "image[%d][url]", new Object[]{Integer.valueOf(i)}), images.getString(i));
                    }
                }
                parameters.remove(ContentFrameworkAnalytics.IMAGE);
            } catch (JSONException e) {
                try {
                    putImageInBundleWithArrayFormat(parameters, 0, new JSONObject(imageStr));
                    parameters.remove(ContentFrameworkAnalytics.IMAGE);
                } catch (JSONException e2) {
                }
            }
        }
    }

    private static void putImageInBundleWithArrayFormat(Bundle parameters, int index, JSONObject image) throws JSONException {
        Iterator<String> keys = image.keys();
        while (keys.hasNext()) {
            String property = (String) keys.next();
            parameters.putString(String.format(Locale.ROOT, "image[%d][%s]", new Object[]{Integer.valueOf(index), property}), image.get(property).toString());
        }
    }

    private void sharePhotoContent(SharePhotoContent photoContent, FacebookCallback<Result> callback) {
        final Mutable<Integer> requestCount = new Mutable<>(Integer.valueOf(0));
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        ArrayList<GraphRequest> requests = new ArrayList<>();
        final ArrayList<JSONObject> results = new ArrayList<>();
        final ArrayList<GraphResponse> errorResponses = new ArrayList<>();
        final FacebookCallback<Result> facebookCallback = callback;
        C39003 r3 = new Callback() {
            public void onCompleted(GraphResponse response) {
                JSONObject result = response.getJSONObject();
                if (result != null) {
                    results.add(result);
                }
                if (response.getError() != null) {
                    errorResponses.add(response);
                }
                requestCount.value = Integer.valueOf(((Integer) requestCount.value).intValue() - 1);
                if (((Integer) requestCount.value).intValue() != 0) {
                    return;
                }
                if (!errorResponses.isEmpty()) {
                    ShareInternalUtility.invokeCallbackWithResults(facebookCallback, null, (GraphResponse) errorResponses.get(0));
                } else if (!results.isEmpty()) {
                    ShareInternalUtility.invokeCallbackWithResults(facebookCallback, ((JSONObject) results.get(0)).optString("id"), response);
                }
            }
        };
        try {
            for (SharePhoto photo : photoContent.getPhotos()) {
                try {
                    Bundle params = getSharePhotoCommonParameters(photo, photoContent);
                    Bitmap bitmap = photo.getBitmap();
                    Uri photoUri = photo.getImageUrl();
                    String caption = photo.getCaption();
                    if (caption == null) {
                        caption = getMessage();
                    }
                    if (bitmap != null) {
                        String graphPath = getGraphPath("photos");
                        requests.add(GraphRequest.newUploadPhotoRequest(accessToken, graphPath, bitmap, caption, params, (Callback) r3));
                    } else if (photoUri != null) {
                        AccessToken accessToken2 = accessToken;
                        ArrayList arrayList = requests;
                        arrayList.add(GraphRequest.newUploadPhotoRequest(accessToken2, getGraphPath("photos"), photoUri, caption, params, (Callback) r3));
                    }
                } catch (JSONException e) {
                    ShareInternalUtility.invokeCallbackWithException(callback, e);
                    return;
                }
            }
            requestCount.value = Integer.valueOf(((Integer) requestCount.value).intValue() + requests.size());
            Iterator it = requests.iterator();
            while (it.hasNext()) {
                ((GraphRequest) it.next()).executeAsync();
            }
        } catch (FileNotFoundException ex) {
            ShareInternalUtility.invokeCallbackWithException(callback, ex);
        }
    }

    private void shareLinkContent(ShareLinkContent linkContent, final FacebookCallback<Result> callback) {
        Callback requestCallback = new Callback() {
            public void onCompleted(GraphResponse response) {
                JSONObject data = response.getJSONObject();
                ShareInternalUtility.invokeCallbackWithResults(callback, data == null ? null : data.optString("id"), response);
            }
        };
        Bundle parameters = new Bundle();
        addCommonParameters(parameters, linkContent);
        parameters.putString("message", getMessage());
        parameters.putString("link", Utility.getUriString(linkContent.getContentUrl()));
        parameters.putString("picture", Utility.getUriString(linkContent.getImageUrl()));
        parameters.putString("name", linkContent.getContentTitle());
        parameters.putString("description", linkContent.getContentDescription());
        parameters.putString("ref", linkContent.getRef());
        new GraphRequest(AccessToken.getCurrentAccessToken(), getGraphPath("feed"), parameters, HttpMethod.POST, requestCallback).executeAsync();
    }

    private void shareVideoContent(ShareVideoContent videoContent, FacebookCallback<Result> callback) {
        try {
            VideoUploader.uploadAsync(videoContent, getGraphNode(), callback);
        } catch (FileNotFoundException ex) {
            ShareInternalUtility.invokeCallbackWithException(callback, ex);
        }
    }

    private Bundle getSharePhotoCommonParameters(SharePhoto photo, SharePhotoContent photoContent) throws JSONException {
        Bundle params = photo.getParameters();
        if (!params.containsKey("place") && !Utility.isNullOrEmpty(photoContent.getPlaceId())) {
            params.putString("place", photoContent.getPlaceId());
        }
        if (!params.containsKey(PushService.PARAM_TAGS) && !Utility.isNullOrEmpty((Collection<T>) photoContent.getPeopleIds())) {
            List<String> peopleIds = photoContent.getPeopleIds();
            if (!Utility.isNullOrEmpty((Collection<T>) peopleIds)) {
                JSONArray tags = new JSONArray();
                for (String id : peopleIds) {
                    JSONObject tag = new JSONObject();
                    tag.put("tag_uid", id);
                    tags.put(tag);
                }
                params.putString(PushService.PARAM_TAGS, tags.toString());
            }
        }
        if (!params.containsKey("ref") && !Utility.isNullOrEmpty(photoContent.getRef())) {
            params.putString("ref", photoContent.getRef());
        }
        return params;
    }

    /* access modifiers changed from: private */
    public void stageArrayList(final ArrayList arrayList, final OnMapValueCompleteListener onArrayListStagedListener) {
        final JSONArray stagedObject = new JSONArray();
        stageCollectionValues(new CollectionMapper.Collection<Integer>() {
            public Iterator<Integer> keyIterator() {
                final int size = arrayList.size();
                final Mutable<Integer> current = new Mutable<>(Integer.valueOf(0));
                return new Iterator<Integer>() {
                    public boolean hasNext() {
                        return ((Integer) current.value).intValue() < size;
                    }

                    public Integer next() {
                        Integer num = (Integer) current.value;
                        Mutable mutable = current;
                        mutable.value = Integer.valueOf(((Integer) mutable.value).intValue() + 1);
                        return num;
                    }

                    public void remove() {
                    }
                };
            }

            public Object get(Integer key) {
                return arrayList.get(key.intValue());
            }

            public void set(Integer key, Object value, OnErrorListener onErrorListener) {
                try {
                    stagedObject.put(key.intValue(), value);
                } catch (JSONException ex) {
                    String message = ex.getLocalizedMessage();
                    if (message == null) {
                        message = "Error staging object.";
                    }
                    onErrorListener.onError(new FacebookException(message));
                }
            }
        }, new OnMapperCompleteListener() {
            public void onComplete() {
                onArrayListStagedListener.onComplete(stagedObject);
            }

            public void onError(FacebookException exception) {
                onArrayListStagedListener.onError(exception);
            }
        });
    }

    private <T> void stageCollectionValues(CollectionMapper.Collection<T> collection, OnMapperCompleteListener onCollectionValuesStagedListener) {
        CollectionMapper.iterate(collection, new ValueMapper() {
            public void mapValue(Object value, OnMapValueCompleteListener onMapValueCompleteListener) {
                if (value instanceof ArrayList) {
                    ShareApi.this.stageArrayList((ArrayList) value, onMapValueCompleteListener);
                } else if (value instanceof ShareOpenGraphObject) {
                    ShareApi.this.stageOpenGraphObject((ShareOpenGraphObject) value, onMapValueCompleteListener);
                } else if (value instanceof SharePhoto) {
                    ShareApi.this.stagePhoto((SharePhoto) value, onMapValueCompleteListener);
                } else {
                    onMapValueCompleteListener.onComplete(value);
                }
            }
        }, onCollectionValuesStagedListener);
    }

    private void stageOpenGraphAction(final Bundle parameters, OnMapperCompleteListener onOpenGraphActionStagedListener) {
        stageCollectionValues(new CollectionMapper.Collection<String>() {
            public Iterator<String> keyIterator() {
                return parameters.keySet().iterator();
            }

            public Object get(String key) {
                return parameters.get(key);
            }

            public void set(String key, Object value, OnErrorListener onErrorListener) {
                if (!Utility.putJSONValueInBundle(parameters, key, value)) {
                    onErrorListener.onError(new FacebookException("Unexpected value: " + value.toString()));
                }
            }
        }, onOpenGraphActionStagedListener);
    }

    /* access modifiers changed from: private */
    public void stageOpenGraphObject(final ShareOpenGraphObject object, final OnMapValueCompleteListener onOpenGraphObjectStagedListener) {
        String type = object.getString("type");
        if (type == null) {
            type = object.getString("og:type");
        }
        if (type == null) {
            onOpenGraphObjectStagedListener.onError(new FacebookException("Open Graph objects must contain a type value."));
            return;
        }
        final JSONObject stagedObject = new JSONObject();
        CollectionMapper.Collection<String> collection = new CollectionMapper.Collection<String>() {
            public Iterator<String> keyIterator() {
                return object.keySet().iterator();
            }

            public Object get(String key) {
                return object.get(key);
            }

            public void set(String key, Object value, OnErrorListener onErrorListener) {
                try {
                    stagedObject.put(key, value);
                } catch (JSONException ex) {
                    String message = ex.getLocalizedMessage();
                    if (message == null) {
                        message = "Error staging object.";
                    }
                    onErrorListener.onError(new FacebookException(message));
                }
            }
        };
        final Callback requestCallback = new Callback() {
            public void onCompleted(GraphResponse response) {
                FacebookRequestError error = response.getError();
                if (error != null) {
                    String message = error.getErrorMessage();
                    if (message == null) {
                        message = "Error staging Open Graph object.";
                    }
                    onOpenGraphObjectStagedListener.onError(new FacebookGraphResponseException(response, message));
                    return;
                }
                JSONObject data = response.getJSONObject();
                if (data == null) {
                    onOpenGraphObjectStagedListener.onError(new FacebookGraphResponseException(response, "Error staging Open Graph object."));
                    return;
                }
                String stagedObjectId = data.optString("id");
                if (stagedObjectId == null) {
                    onOpenGraphObjectStagedListener.onError(new FacebookGraphResponseException(response, "Error staging Open Graph object."));
                } else {
                    onOpenGraphObjectStagedListener.onComplete(stagedObjectId);
                }
            }
        };
        final String ogType = type;
        final OnMapValueCompleteListener onMapValueCompleteListener = onOpenGraphObjectStagedListener;
        stageCollectionValues(collection, new OnMapperCompleteListener() {
            public void onComplete() {
                String objectString = stagedObject.toString();
                Bundle parameters = new Bundle();
                parameters.putString("object", objectString);
                try {
                    new GraphRequest(AccessToken.getCurrentAccessToken(), ShareApi.this.getGraphPath("objects/" + URLEncoder.encode(ogType, "UTF-8")), parameters, HttpMethod.POST, requestCallback).executeAsync();
                } catch (UnsupportedEncodingException ex) {
                    String message = ex.getLocalizedMessage();
                    if (message == null) {
                        message = "Error staging Open Graph object.";
                    }
                    onMapValueCompleteListener.onError(new FacebookException(message));
                }
            }

            public void onError(FacebookException exception) {
                onMapValueCompleteListener.onError(exception);
            }
        });
    }

    /* access modifiers changed from: private */
    public void stagePhoto(final SharePhoto photo, final OnMapValueCompleteListener onPhotoStagedListener) {
        Bitmap bitmap = photo.getBitmap();
        Uri imageUrl = photo.getImageUrl();
        if (bitmap == null && imageUrl == null) {
            onPhotoStagedListener.onError(new FacebookException("Photos must have an imageURL or bitmap."));
            return;
        }
        Callback requestCallback = new Callback() {
            public void onCompleted(GraphResponse response) {
                FacebookRequestError error = response.getError();
                if (error != null) {
                    String message = error.getErrorMessage();
                    if (message == null) {
                        message = "Error staging photo.";
                    }
                    onPhotoStagedListener.onError(new FacebookGraphResponseException(response, message));
                    return;
                }
                JSONObject data = response.getJSONObject();
                if (data == null) {
                    onPhotoStagedListener.onError(new FacebookException("Error staging photo."));
                    return;
                }
                String stagedImageUri = data.optString("uri");
                if (stagedImageUri == null) {
                    onPhotoStagedListener.onError(new FacebookException("Error staging photo."));
                    return;
                }
                JSONObject stagedObject = new JSONObject();
                try {
                    stagedObject.put("url", stagedImageUri);
                    stagedObject.put(NativeProtocol.IMAGE_USER_GENERATED_KEY, photo.getUserGenerated());
                    onPhotoStagedListener.onComplete(stagedObject);
                } catch (JSONException ex) {
                    String message2 = ex.getLocalizedMessage();
                    if (message2 == null) {
                        message2 = "Error staging photo.";
                    }
                    onPhotoStagedListener.onError(new FacebookException(message2));
                }
            }
        };
        if (bitmap != null) {
            ShareInternalUtility.newUploadStagingResourceWithImageRequest(AccessToken.getCurrentAccessToken(), bitmap, requestCallback).executeAsync();
            return;
        }
        try {
            ShareInternalUtility.newUploadStagingResourceWithImageRequest(AccessToken.getCurrentAccessToken(), imageUrl, requestCallback).executeAsync();
        } catch (FileNotFoundException ex) {
            String message2 = ex.getLocalizedMessage();
            if (message2 == null) {
                message2 = "Error staging photo.";
            }
            onPhotoStagedListener.onError(new FacebookException(message2));
        }
    }
}
