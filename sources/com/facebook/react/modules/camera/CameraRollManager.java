package com.facebook.react.modules.camera;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.facebook.common.logging.FLog;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import p005cn.jpush.android.data.DbHelper;

@ReactModule(name = "RKCameraRollManager")
public class CameraRollManager extends ReactContextBaseJavaModule {
    private static final String ERROR_UNABLE_TO_LOAD = "E_UNABLE_TO_LOAD";
    private static final String ERROR_UNABLE_TO_LOAD_PERMISSION = "E_UNABLE_TO_LOAD_PERMISSION";
    private static final String ERROR_UNABLE_TO_SAVE = "E_UNABLE_TO_SAVE";
    public static final boolean IS_JELLY_BEAN_OR_LATER = (VERSION.SDK_INT >= 16);
    /* access modifiers changed from: private */
    public static final String[] PROJECTION;
    private static final String SELECTION_BUCKET = "bucket_display_name = ?";
    private static final String SELECTION_DATE_TAKEN = "datetaken < ?";

    private static class GetPhotosTask extends GuardedAsyncTask<Void, Void> {
        private final String mAfter;
        private final Context mContext;
        private final int mFirst;
        private final String mGroupName;
        private final ReadableArray mMimeTypes;
        private final Promise mPromise;

        private GetPhotosTask(ReactContext context, int first, String after, String groupName, ReadableArray mimeTypes, Promise promise) {
            super(context);
            this.mContext = context;
            this.mFirst = first;
            this.mAfter = after;
            this.mGroupName = groupName;
            this.mMimeTypes = mimeTypes;
            this.mPromise = promise;
        }

        /* access modifiers changed from: protected */
        public void doInBackgroundGuarded(Void... params) {
            Cursor photos;
            StringBuilder selection = new StringBuilder("1");
            List<String> selectionArgs = new ArrayList<>();
            if (!TextUtils.isEmpty(this.mAfter)) {
                selection.append(" AND datetaken < ?");
                selectionArgs.add(this.mAfter);
            }
            if (!TextUtils.isEmpty(this.mGroupName)) {
                selection.append(" AND bucket_display_name = ?");
                selectionArgs.add(this.mGroupName);
            }
            if (this.mMimeTypes != null && this.mMimeTypes.size() > 0) {
                selection.append(" AND mime_type IN (");
                for (int i = 0; i < this.mMimeTypes.size(); i++) {
                    selection.append("?,");
                    selectionArgs.add(this.mMimeTypes.getString(i));
                }
                selection.replace(selection.length() - 1, selection.length(), ")");
            }
            WritableMap response = new WritableNativeMap();
            ContentResolver resolver = this.mContext.getContentResolver();
            try {
                photos = resolver.query(Media.EXTERNAL_CONTENT_URI, CameraRollManager.PROJECTION, selection.toString(), (String[]) selectionArgs.toArray(new String[selectionArgs.size()]), "datetaken DESC, date_modified DESC LIMIT " + (this.mFirst + 1));
                if (photos == null) {
                    this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_LOAD, "Could not get photos");
                    return;
                }
                CameraRollManager.putEdges(resolver, photos, response, this.mFirst);
                CameraRollManager.putPageInfo(photos, response, this.mFirst);
                photos.close();
                this.mPromise.resolve(response);
            } catch (SecurityException e) {
                this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_LOAD_PERMISSION, "Could not get photos: need READ_EXTERNAL_STORAGE permission", e);
            } catch (Throwable th) {
                photos.close();
                this.mPromise.resolve(response);
                throw th;
            }
        }
    }

    private enum MediaType {
        PHOTO,
        VIDEO
    }

    private static class SaveToCameraRoll extends GuardedAsyncTask<Void, Void> {
        private final Context mContext;
        /* access modifiers changed from: private */
        public final Promise mPromise;
        private final MediaType mType;
        private final Uri mUri;

        public SaveToCameraRoll(ReactContext context, Uri uri, MediaType type, Promise promise) {
            super(context);
            this.mContext = context;
            this.mUri = uri;
            this.mPromise = promise;
            this.mType = type;
        }

        /* access modifiers changed from: protected */
        public void doInBackgroundGuarded(Void... params) {
            File exportDir;
            String sourceName;
            String sourceExt;
            int n;
            File source = new File(this.mUri.getPath());
            FileChannel input = null;
            FileChannel output = null;
            try {
                if (this.mType == MediaType.PHOTO) {
                    exportDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                } else {
                    exportDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
                }
                exportDir.mkdirs();
                if (!exportDir.isDirectory()) {
                    this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_LOAD, "External media storage directory not available");
                    if (input != null && input.isOpen()) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            FLog.m1808e(ReactConstants.TAG, "Could not close input channel", (Throwable) e);
                        }
                    }
                    if (output != null && output.isOpen()) {
                        try {
                            output.close();
                        } catch (IOException e2) {
                            FLog.m1808e(ReactConstants.TAG, "Could not close output channel", (Throwable) e2);
                        }
                    }
                } else {
                    File dest = new File(exportDir, source.getName());
                    String fullSourceName = source.getName();
                    if (fullSourceName.indexOf(46) >= 0) {
                        sourceName = fullSourceName.substring(0, fullSourceName.lastIndexOf(46));
                        sourceExt = fullSourceName.substring(fullSourceName.lastIndexOf(46));
                        n = 0;
                    } else {
                        sourceName = fullSourceName;
                        sourceExt = "";
                        n = 0;
                    }
                    while (!dest.createNewFile()) {
                        int n2 = n + 1;
                        dest = new File(exportDir, sourceName + "_" + n + sourceExt);
                        n = n2;
                    }
                    FileChannel input2 = new FileInputStream(source).getChannel();
                    FileChannel output2 = new FileOutputStream(dest).getChannel();
                    output2.transferFrom(input2, 0, input2.size());
                    input2.close();
                    output2.close();
                    MediaScannerConnection.scanFile(this.mContext, new String[]{dest.getAbsolutePath()}, null, new OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            if (uri != null) {
                                SaveToCameraRoll.this.mPromise.resolve(uri.toString());
                            } else {
                                SaveToCameraRoll.this.mPromise.reject(CameraRollManager.ERROR_UNABLE_TO_SAVE, "Could not add image to gallery");
                            }
                        }
                    });
                    if (input2 != null && input2.isOpen()) {
                        try {
                            input2.close();
                        } catch (IOException e3) {
                            FLog.m1808e(ReactConstants.TAG, "Could not close input channel", (Throwable) e3);
                        }
                    }
                    if (output2 != null && output2.isOpen()) {
                        try {
                            output2.close();
                        } catch (IOException e4) {
                            FLog.m1808e(ReactConstants.TAG, "Could not close output channel", (Throwable) e4);
                        }
                    }
                }
            } catch (IOException e5) {
                this.mPromise.reject((Throwable) e5);
                if (input != null && input.isOpen()) {
                    try {
                        input.close();
                    } catch (IOException e6) {
                        FLog.m1808e(ReactConstants.TAG, "Could not close input channel", (Throwable) e6);
                    }
                }
                if (output != null && output.isOpen()) {
                    try {
                        output.close();
                    } catch (IOException e7) {
                        FLog.m1808e(ReactConstants.TAG, "Could not close output channel", (Throwable) e7);
                    }
                }
            } catch (Throwable th) {
                if (input != null && input.isOpen()) {
                    try {
                        input.close();
                    } catch (IOException e8) {
                        FLog.m1808e(ReactConstants.TAG, "Could not close input channel", (Throwable) e8);
                    }
                }
                if (output != null && output.isOpen()) {
                    try {
                        output.close();
                    } catch (IOException e9) {
                        FLog.m1808e(ReactConstants.TAG, "Could not close output channel", (Throwable) e9);
                    }
                }
                throw th;
            }
        }
    }

    static {
        if (IS_JELLY_BEAN_OR_LATER) {
            PROJECTION = new String[]{DbHelper.TABLE_ID, "mime_type", "bucket_display_name", "datetaken", "width", "height", "longitude", "latitude"};
        } else {
            PROJECTION = new String[]{DbHelper.TABLE_ID, "mime_type", "bucket_display_name", "datetaken", "longitude", "latitude"};
        }
    }

    public CameraRollManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "RKCameraRollManager";
    }

    @ReactMethod
    public void saveToCameraRoll(String uri, String type, Promise promise) {
        new SaveToCameraRoll(getReactApplicationContext(), Uri.parse(uri), type.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO) ? MediaType.VIDEO : MediaType.PHOTO, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void getPhotos(ReadableMap params, Promise promise) {
        String after;
        String groupName;
        ReadableArray mimeTypes;
        int first = params.getInt("first");
        if (params.hasKey("after")) {
            after = params.getString("after");
        } else {
            after = null;
        }
        if (params.hasKey("groupName")) {
            groupName = params.getString("groupName");
        } else {
            groupName = null;
        }
        if (params.hasKey("mimeTypes")) {
            mimeTypes = params.getArray("mimeTypes");
        } else {
            mimeTypes = null;
        }
        if (params.hasKey("groupTypes")) {
            throw new JSApplicationIllegalArgumentException("groupTypes is not supported on Android");
        }
        new GetPhotosTask(getReactApplicationContext(), first, after, groupName, mimeTypes, promise).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    /* access modifiers changed from: private */
    public static void putPageInfo(Cursor photos, WritableMap response, int limit) {
        WritableMap pageInfo = new WritableNativeMap();
        pageInfo.putBoolean("has_next_page", limit < photos.getCount());
        if (limit < photos.getCount()) {
            photos.moveToPosition(limit - 1);
            pageInfo.putString("end_cursor", photos.getString(photos.getColumnIndex("datetaken")));
        }
        response.putMap("page_info", pageInfo);
    }

    /* access modifiers changed from: private */
    public static void putEdges(ContentResolver resolver, Cursor photos, WritableMap response, int limit) {
        int widthIndex;
        int heightIndex;
        WritableArray edges = new WritableNativeArray();
        photos.moveToFirst();
        int idIndex = photos.getColumnIndex(DbHelper.TABLE_ID);
        int mimeTypeIndex = photos.getColumnIndex("mime_type");
        int groupNameIndex = photos.getColumnIndex("bucket_display_name");
        int dateTakenIndex = photos.getColumnIndex("datetaken");
        if (IS_JELLY_BEAN_OR_LATER) {
            widthIndex = photos.getColumnIndex("width");
        } else {
            widthIndex = -1;
        }
        if (IS_JELLY_BEAN_OR_LATER) {
            heightIndex = photos.getColumnIndex("height");
        } else {
            heightIndex = -1;
        }
        int longitudeIndex = photos.getColumnIndex("longitude");
        int latitudeIndex = photos.getColumnIndex("latitude");
        int i = 0;
        while (i < limit && !photos.isAfterLast()) {
            WritableMap edge = new WritableNativeMap();
            WritableMap node = new WritableNativeMap();
            if (putImageInfo(resolver, photos, node, idIndex, widthIndex, heightIndex)) {
                putBasicNodeInfo(photos, node, mimeTypeIndex, groupNameIndex, dateTakenIndex);
                putLocationInfo(photos, node, longitudeIndex, latitudeIndex);
                edge.putMap("node", node);
                edges.pushMap(edge);
            } else {
                i--;
            }
            photos.moveToNext();
            i++;
        }
        response.putArray("edges", edges);
    }

    private static void putBasicNodeInfo(Cursor photos, WritableMap node, int mimeTypeIndex, int groupNameIndex, int dateTakenIndex) {
        node.putString("type", photos.getString(mimeTypeIndex));
        node.putString("group_name", photos.getString(groupNameIndex));
        node.putDouble(ErfExperimentsModel.TIMESTAMP, ((double) photos.getLong(dateTakenIndex)) / 1000.0d);
    }

    private static boolean putImageInfo(ContentResolver resolver, Cursor photos, WritableMap node, int idIndex, int widthIndex, int heightIndex) {
        WritableMap image = new WritableNativeMap();
        Uri photoUri = Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, photos.getString(idIndex));
        image.putString("uri", photoUri.toString());
        float width = -1.0f;
        float height = -1.0f;
        if (IS_JELLY_BEAN_OR_LATER) {
            width = (float) photos.getInt(widthIndex);
            height = (float) photos.getInt(heightIndex);
        }
        if (width <= 0.0f || height <= 0.0f) {
            try {
                AssetFileDescriptor photoDescriptor = resolver.openAssetFileDescriptor(photoUri, "r");
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor(photoDescriptor.getFileDescriptor(), null, options);
                photoDescriptor.close();
                width = (float) options.outWidth;
                height = (float) options.outHeight;
            } catch (IOException e) {
                FLog.m1808e(ReactConstants.TAG, "Could not get width/height for " + photoUri.toString(), (Throwable) e);
                return false;
            }
        }
        image.putDouble("width", (double) width);
        image.putDouble("height", (double) height);
        node.putMap(ContentFrameworkAnalytics.IMAGE, image);
        return true;
    }

    private static void putLocationInfo(Cursor photos, WritableMap node, int longitudeIndex, int latitudeIndex) {
        double longitude = photos.getDouble(longitudeIndex);
        double latitude = photos.getDouble(latitudeIndex);
        if (longitude > 0.0d || latitude > 0.0d) {
            WritableMap location = new WritableNativeMap();
            location.putDouble("longitude", longitude);
            location.putDouble("latitude", latitude);
            node.putMap("location", location);
        }
    }
}
