package com.imagepicker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ViewProps;
import com.theartofdev.edmodo.cropper.CropImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

public class ImagePickerModule extends ReactContextBaseJavaModule {
    static final int REQUEST_LAUNCH_IMAGE_CAPTURE = 13001;
    static final int REQUEST_LAUNCH_IMAGE_LIBRARY = 13002;
    static final int REQUEST_LAUNCH_VIDEO_CAPTURE = 13004;
    static final int REQUEST_LAUNCH_VIDEO_LIBRARY = 13003;
    private Boolean allowsEditing;
    EventForwarderFragment fragment;
    private Callback mCallback;
    private Uri mCameraCaptureURI;
    private final ReactApplicationContext mReactContext;
    private int maxHeight = 0;
    private int maxWidth = 0;
    private Boolean noData = Boolean.valueOf(false);
    private Boolean pickVideo = Boolean.valueOf(false);
    private int quality = 100;
    WritableMap response;
    private int rotation = 0;
    private Boolean tmpImage;
    private int videoDurationLimit = 0;
    private int videoQuality = 1;

    public ImagePickerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mReactContext = reactContext;
    }

    public String getName() {
        return "ImagePickerManager";
    }

    @ReactMethod
    public void showImagePicker(final ReadableMap options, final Callback callback) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            this.response = Arguments.createMap();
            this.response.putString("error", "can't find current Activity");
            callback.invoke(this.response);
            return;
        }
        List<String> titles = new ArrayList<>();
        final List<String> actions = new ArrayList<>();
        if (options.hasKey("takePhotoButtonTitle") && options.getString("takePhotoButtonTitle") != null && !options.getString("takePhotoButtonTitle").isEmpty() && isCameraAvailable()) {
            titles.add(options.getString("takePhotoButtonTitle"));
            actions.add(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO);
        }
        if (options.hasKey("chooseFromLibraryButtonTitle") && options.getString("chooseFromLibraryButtonTitle") != null && !options.getString("chooseFromLibraryButtonTitle").isEmpty()) {
            titles.add(options.getString("chooseFromLibraryButtonTitle"));
            actions.add("library");
        }
        if (options.hasKey("customButtons")) {
            ReadableArray customButtons = options.getArray("customButtons");
            for (int i = 0; i < customButtons.size(); i++) {
                ReadableMap button = customButtons.getMap(i);
                int currentIndex = titles.size();
                titles.add(currentIndex, button.getString("title"));
                actions.add(currentIndex, button.getString("name"));
            }
        }
        if (options.hasKey("cancelButtonTitle") && options.getString("cancelButtonTitle") != null && !options.getString("cancelButtonTitle").isEmpty()) {
            titles.add(options.getString("cancelButtonTitle"));
            actions.add(BaseAnalytics.CANCEL);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(currentActivity, 17367057, titles);
        Builder builder = new Builder(currentActivity);
        if (options.hasKey("title") && options.getString("title") != null && !options.getString("title").isEmpty()) {
            builder.setTitle(options.getString("title"));
        }
        builder.setAdapter(adapter, new OnClickListener() {
            public void onClick(DialogInterface dialog, int index) {
                String action = (String) actions.get(index);
                ImagePickerModule.this.response = Arguments.createMap();
                char c = 65535;
                switch (action.hashCode()) {
                    case -1367724422:
                        if (action.equals(BaseAnalytics.CANCEL)) {
                            c = 2;
                            break;
                        }
                        break;
                    case 106642994:
                        if (action.equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO)) {
                            c = 0;
                            break;
                        }
                        break;
                    case 166208699:
                        if (action.equals("library")) {
                            c = 1;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        ImagePickerModule.this.launchCamera(options, callback);
                        return;
                    case 1:
                        ImagePickerModule.this.launchImageLibrary(options, callback);
                        return;
                    case 2:
                        ImagePickerModule.this.response.putBoolean("didCancel", true);
                        callback.invoke(ImagePickerModule.this.response);
                        return;
                    default:
                        ImagePickerModule.this.response.putString("customButton", action);
                        callback.invoke(ImagePickerModule.this.response);
                        return;
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                ImagePickerModule.this.response = Arguments.createMap();
                dialog.dismiss();
                ImagePickerModule.this.response.putBoolean("didCancel", true);
                callback.invoke(ImagePickerModule.this.response);
            }
        });
        dialog.show();
    }

    @ReactMethod
    public void launchCamera(ReadableMap options, Callback callback) {
        int requestCode;
        Intent cameraIntent;
        this.response = Arguments.createMap();
        if (!isCameraAvailable()) {
            this.response.putString("error", "Camera not available");
            callback.invoke(this.response);
            return;
        }
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            this.response.putString("error", "can't find current Activity");
            callback.invoke(this.response);
            return;
        }
        parseOptions(options);
        if (this.pickVideo.booleanValue()) {
            requestCode = 13004;
            cameraIntent = new Intent("android.media.action.VIDEO_CAPTURE");
            cameraIntent.putExtra("android.intent.extra.videoQuality", this.videoQuality);
            if (this.videoDurationLimit > 0) {
                cameraIntent.putExtra("android.intent.extra.durationLimit", this.videoDurationLimit);
            }
        } else {
            requestCode = REQUEST_LAUNCH_IMAGE_CAPTURE;
            cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
            File imageFile = createNewFile();
            this.mCameraCaptureURI = Uri.fromFile(imageFile);
            Uri providerUri = FileProvider.getUriForFile(currentActivity, currentActivity.getPackageName() + ".provider", imageFile);
            Context context = getReactApplicationContext();
            for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(cameraIntent, 65536)) {
                context.grantUriPermission(resolveInfo.activityInfo.packageName, providerUri, 3);
            }
            cameraIntent.putExtra("output", providerUri);
        }
        if (cameraIntent.resolveActivity(this.mReactContext.getPackageManager()) == null) {
            this.response.putString("error", "Cannot launch camera");
            callback.invoke(this.response);
            return;
        }
        this.mCallback = callback;
        this.fragment = EventForwarderFragment.startActivity(currentActivity, cameraIntent, requestCode, this);
    }

    @ReactMethod
    public void launchImageLibrary(ReadableMap options, Callback callback) {
        int requestCode;
        Intent libraryIntent;
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            this.response = Arguments.createMap();
            this.response.putString("error", "can't find current Activity");
            callback.invoke(this.response);
            return;
        }
        parseOptions(options);
        if (this.pickVideo.booleanValue()) {
            requestCode = REQUEST_LAUNCH_VIDEO_LIBRARY;
            libraryIntent = new Intent("android.intent.action.PICK");
            libraryIntent.setType("video/*");
        } else {
            requestCode = REQUEST_LAUNCH_IMAGE_LIBRARY;
            libraryIntent = new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI);
        }
        if (libraryIntent.resolveActivity(this.mReactContext.getPackageManager()) == null) {
            this.response = Arguments.createMap();
            this.response.putString("error", "Cannot launch photo library");
            callback.invoke(this.response);
            return;
        }
        this.mCallback = callback;
        this.fragment = EventForwarderFragment.startActivity(currentActivity, libraryIntent, requestCode, this);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.mCallback == null) {
            return;
        }
        if (this.mCameraCaptureURI != null || requestCode != REQUEST_LAUNCH_IMAGE_CAPTURE) {
            if (requestCode == REQUEST_LAUNCH_IMAGE_CAPTURE || requestCode == REQUEST_LAUNCH_IMAGE_LIBRARY || requestCode == REQUEST_LAUNCH_VIDEO_LIBRARY || requestCode == 13004 || requestCode == 203) {
                this.response = Arguments.createMap();
                if (resultCode != -1) {
                    this.response.putString("error", "canceled");
                } else if (requestCode == 203) {
                    generateImageResponse(CropImage.getActivityResult(data).getUri(), this.response);
                } else if (requestCode == REQUEST_LAUNCH_VIDEO_LIBRARY || requestCode == 13004) {
                    this.response.putString("uri", data.getData().toString());
                    this.response.putString("path", getRealPathFromURI(data.getData()));
                    if (requestCode == 13004) {
                        fileScan(this.response.getString("path"));
                    }
                } else {
                    Uri uri = null;
                    if (requestCode == REQUEST_LAUNCH_IMAGE_CAPTURE) {
                        uri = this.mCameraCaptureURI;
                        fileScan(uri.getPath());
                    } else if (requestCode == REQUEST_LAUNCH_IMAGE_LIBRARY) {
                        uri = data.getData();
                    }
                    if (this.allowsEditing.booleanValue()) {
                        createNewFile().deleteOnExit();
                        CropImage.activity(uri).setAllowFlipping(false).start((Context) this.mReactContext, (Fragment) this.fragment);
                        return;
                    }
                    generateImageResponse(uri, this.response);
                }
                finishCallback();
            }
        }
    }

    private void finishCallback() {
        this.mCallback.invoke(this.response);
        this.mCallback = null;
        this.fragment.removeFragment();
        this.fragment = null;
    }

    private void generateImageResponse(Uri uri, WritableMap response2) {
        String realPath = getRealPathFromURI(uri);
        boolean isUrl = false;
        if (realPath != null) {
            try {
                URL url = new URL(realPath);
                isUrl = true;
            } catch (MalformedURLException e) {
            }
        }
        if (realPath == null || isUrl) {
            try {
                File file = createFileFromURI(uri);
                realPath = file.getAbsolutePath();
                uri = Uri.fromFile(file);
            } catch (Exception e2) {
                response2.putString("error", "Could not read photo");
                response2.putString("uri", uri.toString());
                return;
            }
        }
        int currentRotation = 0;
        try {
            ExifInterface exif = new ExifInterface(realPath);
            float[] latlng = new float[2];
            exif.getLatLong(latlng);
            float latitude = latlng[0];
            float longitude = latlng[1];
            if (!(latitude == 0.0f && longitude == 0.0f)) {
                response2.putDouble("latitude", (double) latitude);
                response2.putDouble("longitude", (double) longitude);
            }
            String timestamp = exif.getAttribute("DateTime");
            SimpleDateFormat exifDatetimeFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
            DateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                response2.putString(ErfExperimentsModel.TIMESTAMP, isoFormat.format(exifDatetimeFormat.parse(timestamp)) + "Z");
            } catch (Exception e3) {
            }
            boolean isVertical = true;
            switch (exif.getAttributeInt("Orientation", 1)) {
                case 3:
                    currentRotation = 180;
                    break;
                case 6:
                    isVertical = false;
                    currentRotation = 90;
                    break;
                case 8:
                    isVertical = false;
                    currentRotation = MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS;
                    break;
            }
            response2.putBoolean("isVertical", isVertical);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(realPath, options);
            int initialWidth = options.outWidth;
            int initialHeight = options.outHeight;
            if (((initialWidth >= this.maxWidth || this.maxWidth <= 0) && this.maxWidth != 0) || !(((initialHeight < this.maxHeight && this.maxHeight > 0) || this.maxHeight == 0) && this.quality == 100 && (this.rotation == 0 || currentRotation == this.rotation))) {
                File resized = getResizedImage(realPath, initialWidth, initialHeight);
                if (resized == null) {
                    response2.putString("error", "Can't resize the image");
                } else {
                    realPath = resized.getAbsolutePath();
                    uri = Uri.fromFile(resized);
                    BitmapFactory.decodeFile(realPath, options);
                    response2.putInt("width", options.outWidth);
                    response2.putInt("height", options.outHeight);
                }
            } else {
                response2.putInt("width", initialWidth);
                response2.putInt("height", initialHeight);
            }
            response2.putString("uri", uri.toString());
            response2.putString("path", realPath);
            if (!this.noData.booleanValue()) {
                response2.putString("data", getBase64StringFromFile(realPath));
            }
            putExtraFileInfo(realPath, response2);
        } catch (IOException e4) {
            e4.printStackTrace();
            response2.putString("error", e4.getMessage());
        }
    }

    public void onPermisionDenied(Boolean neverAskAgain) {
        this.response = Arguments.createMap();
        this.response.putString("error", neverAskAgain.booleanValue() ? "denied" : "canceled");
        finishCallback();
    }

    private static long parseTimestamp(String dateTimeString, String subSecs) {
        if (dateTimeString == null) {
            return -1;
        }
        SimpleDateFormat sFormatter = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.getDefault());
        sFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date datetime = sFormatter.parse(dateTimeString, new ParsePosition(0));
            if (datetime == null) {
                return -1;
            }
            long msecs = datetime.getTime();
            if (subSecs == null) {
                return msecs;
            }
            try {
                long sub = Long.valueOf(subSecs).longValue();
                while (sub > 1000) {
                    sub /= 10;
                }
                return msecs + sub;
            } catch (NumberFormatException e) {
                return msecs;
            }
        } catch (IllegalArgumentException e2) {
            return -1;
        }
    }

    private boolean permissionsCheck(Activity activity) {
        int writePermission = ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
        int cameraPermission = ActivityCompat.checkSelfPermission(activity, "android.permission.CAMERA");
        if (writePermission == 0 && cameraPermission == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(activity, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"}, 1);
        return false;
    }

    private boolean isCameraAvailable() {
        return this.mReactContext.getPackageManager().hasSystemFeature("android.hardware.camera") || this.mReactContext.getPackageManager().hasSystemFeature("android.hardware.camera.any");
    }

    private String getRealPathFromURI(Uri uri) {
        Cursor cursor = this.mReactContext.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
        if (cursor == null) {
            return uri.getPath();
        }
        cursor.moveToFirst();
        String result = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
        cursor.close();
        return result;
    }

    private File createFileFromURI(Uri uri) throws Exception {
        File file = new File(this.mReactContext.getExternalCacheDir(), "photo-" + uri.getLastPathSegment());
        InputStream input = this.mReactContext.getContentResolver().openInputStream(uri);
        OutputStream output = new FileOutputStream(file);
        try {
            byte[] buffer = new byte[4096];
            while (true) {
                int read = input.read(buffer);
                if (read != -1) {
                    output.write(buffer, 0, read);
                } else {
                    output.flush();
                    return file;
                }
            }
        } finally {
            output.close();
            input.close();
        }
    }

    private String getBase64StringFromFile(String absoluteFilePath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(absoluteFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[8192];
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while (true) {
            try {
                int bytesRead = inputStream.read(buffer);
                if (bytesRead == -1) {
                    break;
                }
                output.write(buffer, 0, bytesRead);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return Base64.encodeToString(output.toByteArray(), 2);
    }

    private File getResizedImage(String realPath, int initialWidth, int initialHeight) {
        double ratio;
        Options options = new Options();
        options.inScaled = false;
        Bitmap photo = BitmapFactory.decodeFile(realPath, options);
        if (photo == null) {
            return null;
        }
        if (this.maxWidth == 0) {
            this.maxWidth = initialWidth;
        }
        if (this.maxHeight == 0) {
            this.maxHeight = initialHeight;
        }
        double widthRatio = ((double) this.maxWidth) / ((double) initialWidth);
        double heightRatio = ((double) this.maxHeight) / ((double) initialHeight);
        if (widthRatio < heightRatio) {
            ratio = widthRatio;
        } else {
            ratio = heightRatio;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) this.rotation);
        matrix.postScale((float) ratio, (float) ratio);
        try {
            int orientation = new ExifInterface(realPath).getAttributeInt("Orientation", 0);
            if (orientation == 6) {
                matrix.postRotate(90.0f);
            } else if (orientation == 3) {
                matrix.postRotate(180.0f);
            } else if (orientation == 8) {
                matrix.postRotate(270.0f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap scaledphoto = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, true);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        scaledphoto.compress(CompressFormat.JPEG, this.quality, bytes);
        File f = createNewFile();
        try {
            try {
                new FileOutputStream(f).write(bytes.toByteArray());
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        }
        if (photo == null) {
            return f;
        }
        scaledphoto.recycle();
        photo.recycle();
        return f;
    }

    private File createNewFile() {
        File path;
        String filename = "image-" + UUID.randomUUID().toString() + ".jpg";
        if (this.tmpImage.booleanValue()) {
            path = this.mReactContext.getExternalCacheDir();
        } else {
            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        }
        File f = new File(path, filename);
        try {
            path.mkdirs();
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    private void putExtraFileInfo(String path, WritableMap response2) {
        try {
            File f = new File(path);
            response2.putDouble("fileSize", (double) f.length());
            response2.putString("fileName", f.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String extension = MimeTypeMap.getFileExtensionFromUrl(path);
        if (extension != null) {
            response2.putString("type", MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension));
        }
    }

    private void parseOptions(ReadableMap options) {
        this.noData = Boolean.valueOf(false);
        if (options.hasKey("noData")) {
            this.noData = Boolean.valueOf(options.getBoolean("noData"));
        }
        this.maxWidth = 0;
        if (options.hasKey(ViewProps.MAX_WIDTH)) {
            this.maxWidth = options.getInt(ViewProps.MAX_WIDTH);
        }
        this.maxHeight = 0;
        if (options.hasKey(ViewProps.MAX_HEIGHT)) {
            this.maxHeight = options.getInt(ViewProps.MAX_HEIGHT);
        }
        this.quality = 100;
        if (options.hasKey("quality")) {
            this.quality = (int) (options.getDouble("quality") * 100.0d);
        }
        this.tmpImage = Boolean.valueOf(true);
        if (options.hasKey("storageOptions")) {
            this.tmpImage = Boolean.valueOf(false);
        }
        this.rotation = 0;
        if (options.hasKey("rotation")) {
            this.rotation = options.getInt("rotation");
        }
        this.pickVideo = Boolean.valueOf(false);
        if (options.hasKey("mediaType") && options.getString("mediaType").equals(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO)) {
            this.pickVideo = Boolean.valueOf(true);
        }
        this.videoQuality = 1;
        if (options.hasKey("videoQuality") && options.getString("videoQuality").equals("low")) {
            this.videoQuality = 0;
        }
        this.videoDurationLimit = 0;
        if (options.hasKey("durationLimit")) {
            this.videoDurationLimit = options.getInt("durationLimit");
        }
        this.allowsEditing = Boolean.valueOf(false);
        if (options.hasKey("allowsEditing")) {
            this.allowsEditing = Boolean.valueOf(options.getBoolean("allowsEditing"));
        }
    }

    public void fileScan(String path) {
        MediaScannerConnection.scanFile(this.mReactContext, new String[]{path}, null, new OnScanCompletedListener() {
            public void onScanCompleted(String path, Uri uri) {
                Log.i("TAG", "Finished scanning " + path);
            }
        });
    }

    public void onNewIntent(Intent intent) {
    }
}
