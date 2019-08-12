package com.jumio.commons.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.jumio.commons.log.Log;
import com.jumio.commons.utils.DownloadTask.ProgressListener;

public class ImageProvider {
    private static final String COUNTRY_IMAGE_URL = "assets/nv/images/country/";
    private static final String IMAGE_EXTENSION = ".webp";
    private static ImageManager mImageManager;

    public interface FlagReceivedInterface {
        void onFlagReceived(Bitmap bitmap);
    }

    /* access modifiers changed from: private */
    public static synchronized ImageManager getImageManager() {
        ImageManager imageManager;
        synchronized (ImageProvider.class) {
            if (mImageManager == null) {
                mImageManager = new ImageManager();
            }
            imageManager = mImageManager;
        }
        return imageManager;
    }

    public static Bitmap getCountryFlagFromServer(String serverBaseUrl, String countryCode3, Options factoryOptions) {
        Bitmap bmp = null;
        String urlString = serverBaseUrl + COUNTRY_IMAGE_URL + "flag_" + countryCode3.toLowerCase() + IMAGE_EXTENSION;
        int id = urlString.hashCode();
        if (getImageManager().hasBitmap(id)) {
            bmp = getImageManager().getBitmap(id);
            Log.m1922v("Reading " + urlString + " from local cache");
        } else {
            try {
                long d = System.currentTimeMillis();
                byte[] data = new DownloadTask(urlString).getData();
                if (data != null) {
                    long d2 = System.currentTimeMillis() - d;
                    if (factoryOptions != null) {
                        bmp = BitmapFactory.decodeByteArray(data, 0, data.length, factoryOptions);
                    } else {
                        bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                    }
                    getImageManager().storeBitmap(id, bmp);
                }
            } catch (Exception e) {
                Log.m1916e(ImageProvider.class.getSimpleName(), (Throwable) e);
                return null;
            }
        }
        return bmp;
    }

    public static void getCountryFlagFromServerAsync(String serverBaseUrl, String countryCode3, final Options factoryOptions, final FlagReceivedInterface callback) {
        String urlString = serverBaseUrl + COUNTRY_IMAGE_URL + "flag_" + countryCode3.toLowerCase() + IMAGE_EXTENSION;
        final int id = urlString.hashCode();
        if (getImageManager().hasBitmap(id)) {
            callback.onFlagReceived(getImageManager().getBitmap(id));
            Log.m1922v("Reading " + urlString + " from local cache");
            return;
        }
        try {
            DownloadTask downloadTask = new DownloadTask(urlString);
            downloadTask.setListener(new ProgressListener() {
                public void onProgressUpdate(float percent) {
                }

                public void onProgressDone(byte[] data) {
                    if (data == null) {
                        callback.onFlagReceived(null);
                        return;
                    }
                    Bitmap flag = BitmapFactory.decodeByteArray(data, 0, data.length, factoryOptions);
                    ImageProvider.getImageManager().storeBitmap(id, flag);
                    callback.onFlagReceived(flag);
                }
            });
            downloadTask.execute(new Void[0]);
        } catch (Exception e) {
            Log.m1916e(ImageProvider.class.getSimpleName(), (Throwable) e);
        }
    }
}
