package com.facebook.react.modules.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ReactModule(name = "RKImageEditingManager")
public class ImageEditingManager extends ReactContextBaseJavaModule {
    private static final int COMPRESS_QUALITY = 90;
    @SuppressLint({"InlinedApi"})
    private static final String[] EXIF_ATTRIBUTES = {"FNumber", "DateTime", "DateTimeDigitized", "ExposureTime", "Flash", "FocalLength", "GPSAltitude", "GPSAltitudeRef", "GPSDateStamp", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSProcessingMethod", "GPSTimeStamp", "ImageLength", "ImageWidth", "ISOSpeedRatings", "Make", "Model", "Orientation", "SubSecTime", "SubSecTimeDigitized", "SubSecTimeOriginal", "WhiteBalance"};
    private static final List<String> LOCAL_URI_PREFIXES = Arrays.asList(new String[]{"file://", "content://"});
    private static final String TEMP_FILE_PREFIX = "ReactNative_cropped_image_";

    private static class CleanTask extends GuardedAsyncTask<Void, Void> {
        private final Context mContext;

        private CleanTask(ReactContext context) {
            super(context);
            this.mContext = context;
        }

        /* access modifiers changed from: protected */
        public void doInBackgroundGuarded(Void... params) {
            cleanDirectory(this.mContext.getCacheDir());
            File externalCacheDir = this.mContext.getExternalCacheDir();
            if (externalCacheDir != null) {
                cleanDirectory(externalCacheDir);
            }
        }

        private void cleanDirectory(File directory) {
            File[] toDelete = directory.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String filename) {
                    return filename.startsWith(ImageEditingManager.TEMP_FILE_PREFIX);
                }
            });
            if (toDelete != null) {
                for (File file : toDelete) {
                    file.delete();
                }
            }
        }
    }

    private static class CropTask extends GuardedAsyncTask<Void, Void> {
        final Context mContext;
        final Callback mError;
        final int mHeight;
        final Callback mSuccess;
        int mTargetHeight;
        int mTargetWidth;
        final String mUri;
        final int mWidth;

        /* renamed from: mX */
        final int f1373mX;

        /* renamed from: mY */
        final int f1374mY;

        private CropTask(ReactContext context, String uri, int x, int y, int width, int height, Callback success, Callback error) {
            super(context);
            this.mTargetWidth = 0;
            this.mTargetHeight = 0;
            if (x < 0 || y < 0 || width <= 0 || height <= 0) {
                throw new JSApplicationIllegalArgumentException(String.format("Invalid crop rectangle: [%d, %d, %d, %d]", new Object[]{Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(width), Integer.valueOf(height)}));
            }
            this.mContext = context;
            this.mUri = uri;
            this.f1373mX = x;
            this.f1374mY = y;
            this.mWidth = width;
            this.mHeight = height;
            this.mSuccess = success;
            this.mError = error;
        }

        public void setTargetSize(int width, int height) {
            if (width <= 0 || height <= 0) {
                throw new JSApplicationIllegalArgumentException(String.format("Invalid target size: [%d, %d]", new Object[]{Integer.valueOf(width), Integer.valueOf(height)}));
            }
            this.mTargetWidth = width;
            this.mTargetHeight = height;
        }

        private InputStream openBitmapInputStream() throws IOException {
            InputStream stream;
            if (ImageEditingManager.isLocalUri(this.mUri)) {
                stream = this.mContext.getContentResolver().openInputStream(Uri.parse(this.mUri));
            } else {
                stream = new URL(this.mUri).openConnection().getInputStream();
            }
            if (stream != null) {
                return stream;
            }
            throw new IOException("Cannot open bitmap: " + this.mUri);
        }

        /* access modifiers changed from: protected */
        public void doInBackgroundGuarded(Void... params) {
            boolean hasTargetSize;
            Bitmap cropped;
            try {
                Options outOptions = new Options();
                if (this.mTargetWidth <= 0 || this.mTargetHeight <= 0) {
                    hasTargetSize = false;
                } else {
                    hasTargetSize = true;
                }
                if (hasTargetSize) {
                    cropped = cropAndResize(this.mTargetWidth, this.mTargetHeight, outOptions);
                } else {
                    cropped = crop(outOptions);
                }
                String mimeType = outOptions.outMimeType;
                if (mimeType == null || mimeType.isEmpty()) {
                    throw new IOException("Could not determine MIME type");
                }
                File tempFile = ImageEditingManager.createTempFile(this.mContext, mimeType);
                ImageEditingManager.writeCompressedBitmapToFile(cropped, mimeType, tempFile);
                if (mimeType.equals("image/jpeg")) {
                    ImageEditingManager.copyExif(this.mContext, Uri.parse(this.mUri), tempFile);
                }
                this.mSuccess.invoke(Uri.fromFile(tempFile).toString());
            } catch (Exception e) {
                this.mError.invoke(e.getMessage());
            }
        }

        private Bitmap crop(Options outOptions) throws IOException {
            InputStream inputStream = openBitmapInputStream();
            try {
                Bitmap fullResolutionBitmap = BitmapFactory.decodeStream(inputStream, null, outOptions);
                if (fullResolutionBitmap != null) {
                    return Bitmap.createBitmap(fullResolutionBitmap, this.f1373mX, this.f1374mY, this.mWidth, this.mHeight);
                }
                throw new IOException("Cannot decode bitmap: " + this.mUri);
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }

        private Bitmap cropAndResize(int targetWidth, int targetHeight, Options outOptions) throws IOException {
            float newWidth;
            float newHeight;
            float newX;
            float newY;
            float scale;
            Assertions.assertNotNull(outOptions);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            InputStream inputStream = openBitmapInputStream();
            try {
                BitmapFactory.decodeStream(inputStream, null, options);
                float targetRatio = ((float) targetWidth) / ((float) targetHeight);
                if (((float) this.mWidth) / ((float) this.mHeight) > targetRatio) {
                    newWidth = ((float) this.mHeight) * targetRatio;
                    newHeight = (float) this.mHeight;
                    newX = ((float) this.f1373mX) + ((((float) this.mWidth) - newWidth) / 2.0f);
                    newY = (float) this.f1374mY;
                    scale = ((float) targetHeight) / ((float) this.mHeight);
                } else {
                    newWidth = (float) this.mWidth;
                    newHeight = ((float) this.mWidth) / targetRatio;
                    newX = (float) this.f1373mX;
                    newY = ((float) this.f1374mY) + ((((float) this.mHeight) - newHeight) / 2.0f);
                    scale = ((float) targetWidth) / ((float) this.mWidth);
                }
                outOptions.inSampleSize = ImageEditingManager.getDecodeSampleSize(this.mWidth, this.mHeight, targetWidth, targetHeight);
                options.inJustDecodeBounds = false;
                InputStream inputStream2 = openBitmapInputStream();
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream2, null, outOptions);
                    if (bitmap == null) {
                        throw new IOException("Cannot decode bitmap: " + this.mUri);
                    }
                    int cropX = (int) Math.floor((double) (newX / ((float) outOptions.inSampleSize)));
                    int cropY = (int) Math.floor((double) (newY / ((float) outOptions.inSampleSize)));
                    int cropWidth = (int) Math.floor((double) (newWidth / ((float) outOptions.inSampleSize)));
                    int cropHeight = (int) Math.floor((double) (newHeight / ((float) outOptions.inSampleSize)));
                    float cropScale = scale * ((float) outOptions.inSampleSize);
                    Matrix scaleMatrix = new Matrix();
                    scaleMatrix.setScale(cropScale, cropScale);
                    return Bitmap.createBitmap(bitmap, cropX, cropY, cropWidth, cropHeight, scaleMatrix, true);
                } finally {
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                }
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
    }

    public ImageEditingManager(ReactApplicationContext reactContext) {
        super(reactContext);
        new CleanTask(getReactApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public String getName() {
        return "RKImageEditingManager";
    }

    public Map<String, Object> getConstants() {
        return Collections.emptyMap();
    }

    public void onCatalystInstanceDestroy() {
        new CleanTask(getReactApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void cropImage(String uri, ReadableMap options, Callback success, Callback error) {
        ReadableMap offset = options.hasKey("offset") ? options.getMap("offset") : null;
        ReadableMap size = options.hasKey("size") ? options.getMap("size") : null;
        if (offset == null || size == null || !offset.hasKey("x") || !offset.hasKey("y") || !size.hasKey("width") || !size.hasKey("height")) {
            throw new JSApplicationIllegalArgumentException("Please specify offset and size");
        } else if (uri == null || uri.isEmpty()) {
            throw new JSApplicationIllegalArgumentException("Please specify a URI");
        } else {
            CropTask cropTask = new CropTask(getReactApplicationContext(), uri, (int) offset.getDouble("x"), (int) offset.getDouble("y"), (int) size.getDouble("width"), (int) size.getDouble("height"), success, error);
            if (options.hasKey("displaySize")) {
                ReadableMap targetSize = options.getMap("displaySize");
                cropTask.setTargetSize(targetSize.getInt("width"), targetSize.getInt("height"));
            }
            cropTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* access modifiers changed from: private */
    public static void copyExif(Context context, Uri oldImage, File newFile) throws IOException {
        String[] strArr;
        File oldFile = getFileFromUri(context, oldImage);
        if (oldFile == null) {
            FLog.m1847w(ReactConstants.TAG, "Couldn't get real path for uri: " + oldImage);
            return;
        }
        ExifInterface oldExif = new ExifInterface(oldFile.getAbsolutePath());
        ExifInterface newExif = new ExifInterface(newFile.getAbsolutePath());
        for (String attribute : EXIF_ATTRIBUTES) {
            String value = oldExif.getAttribute(attribute);
            if (value != null) {
                newExif.setAttribute(attribute, value);
            }
        }
        newExif.saveAttributes();
    }

    private static File getFileFromUri(Context context, Uri uri) {
        if (uri.getScheme().equals(UriUtil.LOCAL_FILE_SCHEME)) {
            return new File(uri.getPath());
        }
        if (!uri.getScheme().equals("content")) {
            return null;
        }
        Cursor cursor = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.moveToFirst()) {
                String path = cursor.getString(0);
                if (!TextUtils.isEmpty(path)) {
                    return new File(path);
                }
            }
            cursor.close();
            return null;
        } finally {
            cursor.close();
        }
    }

    /* access modifiers changed from: private */
    public static boolean isLocalUri(String uri) {
        for (String localPrefix : LOCAL_URI_PREFIXES) {
            if (uri.startsWith(localPrefix)) {
                return true;
            }
        }
        return false;
    }

    private static String getFileExtensionForType(String mimeType) {
        if ("image/png".equals(mimeType)) {
            return ".png";
        }
        if ("image/webp".equals(mimeType)) {
            return ".webp";
        }
        return ".jpg";
    }

    private static CompressFormat getCompressFormatForType(String type) {
        if ("image/png".equals(type)) {
            return CompressFormat.PNG;
        }
        if ("image/webp".equals(type)) {
            return CompressFormat.WEBP;
        }
        return CompressFormat.JPEG;
    }

    /* access modifiers changed from: private */
    public static void writeCompressedBitmapToFile(Bitmap cropped, String mimeType, File tempFile) throws IOException {
        OutputStream out = new FileOutputStream(tempFile);
        try {
            cropped.compress(getCompressFormatForType(mimeType), 90, out);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /* access modifiers changed from: private */
    public static File createTempFile(Context context, String mimeType) throws IOException {
        File externalCacheDir = context.getExternalCacheDir();
        File internalCacheDir = context.getCacheDir();
        if (externalCacheDir == null && internalCacheDir == null) {
            throw new IOException("No cache directory available");
        }
        File cacheDir = externalCacheDir == null ? internalCacheDir : internalCacheDir == null ? externalCacheDir : externalCacheDir.getFreeSpace() > internalCacheDir.getFreeSpace() ? externalCacheDir : internalCacheDir;
        return File.createTempFile(TEMP_FILE_PREFIX, getFileExtensionForType(mimeType), cacheDir);
    }

    /* access modifiers changed from: private */
    public static int getDecodeSampleSize(int width, int height, int targetWidth, int targetHeight) {
        int inSampleSize = 1;
        if (height > targetWidth || width > targetHeight) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while (halfWidth / inSampleSize >= targetWidth && halfHeight / inSampleSize >= targetHeight) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
