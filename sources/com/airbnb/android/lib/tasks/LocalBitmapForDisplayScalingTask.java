package com.airbnb.android.lib.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.util.Log;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.utils.ImageUtil;
import java.io.IOException;

public class LocalBitmapForDisplayScalingTask extends AsyncTask<Object, Void, Bitmap> {
    public static final int MAX_IMAGE_SIZE = 512;
    private static final String TAG = LocalBitmapForDisplayScalingTask.class.getSimpleName();
    ImageUtils mImageUtils;

    public LocalBitmapForDisplayScalingTask() {
        ((AirbnbGraph) AirbnbApplication.instance().component()).inject(this);
    }

    /* access modifiers changed from: protected */
    public Bitmap doInBackground(Object... params) {
        Bitmap scaledBitmap;
        if (params.length < 1) {
            return null;
        }
        String src = params[0];
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(src, options);
        options.inSampleSize = ImageUtil.getInSampleSize(options.outWidth, options.outHeight, 512, 512, true);
        if (isCancelled()) {
            return null;
        }
        options.inJustDecodeBounds = false;
        try {
            scaledBitmap = BitmapFactory.decodeFile(src, options);
        } catch (OutOfMemoryError e) {
            Log.d(TAG, "Not enough memory to create scaled bitmap");
            options.inSampleSize *= 2;
            try {
                scaledBitmap = BitmapFactory.decodeFile(src, options);
            } catch (OutOfMemoryError e2) {
                return null;
            }
        }
        if (isCancelled()) {
            if (scaledBitmap != null && !scaledBitmap.isRecycled()) {
                scaledBitmap.recycle();
            }
            return null;
        }
        try {
            return ImageUtils.rotateIfNeeded(scaledBitmap, ImageUtil.getExifOrientation(src));
        } catch (IOException e3) {
            return scaledBitmap;
        }
    }
}
