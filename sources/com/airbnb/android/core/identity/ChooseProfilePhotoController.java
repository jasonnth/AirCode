package com.airbnb.android.core.identity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.p000v4.app.Fragment;
import android.webkit.MimeTypeMap;
import com.airbnb.android.core.AirFragmentFacade;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.core.utils.AirPhotoPicker;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.PhotoCompressor;
import com.airbnb.android.core.utils.PhotoCompressor.PhotoCompressionCallback;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.android.utils.CropUtil;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.theartofdev.edmodo.cropper.CropImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;

public class ChooseProfilePhotoController {
    private static final String FACEBOOK_PROFILE_IMAGE_URL = "http://graph.facebook.com/{id}/picture?type=large";
    private static final String FACEBOOK_PROFILE_IMAGE_URL_ID = "{id}";
    private static final int RC_CHOOSE_PHOTO = 201;
    /* access modifiers changed from: private */
    public final Context context;
    private CallbackManager facebookCallbackManager;
    /* access modifiers changed from: private */
    public AirFragmentFacade fragment;
    /* access modifiers changed from: private */
    public ChooseProfilePhotoListener listener;
    private final PhotoCompressor photoCompressor;

    public interface ChooseProfilePhotoListener {
        void onProfilePhotoCompressFailed();

        void onProfilePhotoCompressStart();

        void onProfilePhotoReady(String str);
    }

    private class DownloadFacebookProfilePhoto extends AsyncTask<String, File, File> {
        private DownloadFacebookProfilePhoto() {
        }

        /* access modifiers changed from: protected */
        public File doInBackground(String... urlStr) {
            try {
                Response response = new OkHttpClient().newCall(new Builder().url(urlStr[0]).build()).execute();
                File outputFile = new File(ChooseProfilePhotoController.this.context.getCacheDir(), "uncropped." + MimeTypeMap.getSingleton().getExtensionFromMimeType(response.header(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE)));
                BufferedSink sink = Okio.buffer(Okio.sink(outputFile));
                sink.writeAll(response.body().source());
                sink.close();
                return outputFile;
            } catch (IOException e) {
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("IOException while downloading " + urlStr));
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(File source) {
            ChooseProfilePhotoController.this.beginCrop(Uri.fromFile(source));
        }
    }

    public ChooseProfilePhotoController(Context context2, PhotoCompressor photoCompressor2) {
        this.context = context2;
        this.photoCompressor = photoCompressor2;
    }

    public void init(AirFragmentFacade fragment2, ChooseProfilePhotoListener listener2) {
        this.fragment = fragment2;
        this.listener = listener2;
    }

    public void startPhotoPickerIntent(Integer source) {
        PhotoPicker.Builder builder = AirPhotoPicker.builder().targetOutputDimensions(2048, 2048);
        if (source != null) {
            builder = builder.setSource(source.intValue());
        }
        this.fragment.startActivityForResult(builder.create(this.context), RC_CHOOSE_PHOTO);
    }

    public void startFacebookLoginIntent() {
        FacebookSdk.sdkInitialize(this.fragment.getContext());
        LoginManager.getInstance().logInWithReadPermissions((Fragment) this.fragment, (Collection<String>) Arrays.asList(new String[]{"public_profile"}));
    }

    private void facebookOnActivityResult(int requestCode, int resultCode, Intent data) {
        this.facebookCallbackManager = Factory.create();
        LoginManager.getInstance().registerCallback(this.facebookCallbackManager, new FacebookCallback<LoginResult>() {
            public void onSuccess(LoginResult loginResult) {
                String urlStr = ChooseProfilePhotoController.FACEBOOK_PROFILE_IMAGE_URL.replace(ChooseProfilePhotoController.FACEBOOK_PROFILE_IMAGE_URL_ID, loginResult.getAccessToken().getUserId());
                new DownloadFacebookProfilePhoto().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{urlStr});
            }

            public void onCancel() {
            }

            public void onError(FacebookException error) {
                NetworkUtil.toastGenericNetworkError(ChooseProfilePhotoController.this.fragment.getActivity());
            }
        });
        this.facebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestCodeOffset.Login.toRequestCode()) {
            facebookOnActivityResult(requestCode, resultCode, data);
        } else if (resultCode == -1) {
            switch (requestCode) {
                case RC_CHOOSE_PHOTO /*201*/:
                    beginCrop(Uri.fromFile(new File(data.getStringExtra(PhotoPicker.EXTRA_PHOTO_PATH))));
                    return;
                case 203:
                    handleCropResult(data);
                    return;
                default:
                    return;
            }
        } else if (resultCode == 0 && requestCode == RC_CHOOSE_PHOTO) {
            AccountVerificationAnalytics.trackButtonClick(this.fragment.getNavigationTrackingTag(), "menu_cancel_button");
        }
    }

    public void destroy() {
        this.photoCompressor.cancelCompressionJobs();
        this.fragment = null;
        this.listener = null;
    }

    /* access modifiers changed from: private */
    public void beginCrop(Uri source) {
        CropUtil.square(source).start(this.context, (Fragment) this.fragment);
    }

    private void handleCropResult(Intent data) {
        this.listener.onProfilePhotoCompressStart();
        this.photoCompressor.compressPhoto(CropImage.getActivityResult(data).getUri(), new PhotoCompressionCallback() {
            public void onPhotoCompressed(String filePath) {
                ChooseProfilePhotoController.this.listener.onProfilePhotoReady(filePath);
            }

            public void onCompressionFailure() {
                ChooseProfilePhotoController.this.listener.onProfilePhotoCompressFailed();
            }
        });
    }
}
