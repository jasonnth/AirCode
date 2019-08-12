package com.airbnb.android.photopicker;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import java.io.IOException;

public class ImageUtils {
    private static final String TAG = ImageUtils.class.getSimpleName();

    public static int getInSampleSize(int srcWidth, int srcHeight, int maxWidth, int maxHeight) {
        if (srcWidth <= maxWidth && srcHeight <= maxHeight) {
            return 1;
        }
        int maxRatio = Math.max((int) Math.ceil((double) (((float) srcWidth) / ((float) maxWidth))), (int) Math.ceil((double) (((float) srcHeight) / ((float) maxHeight))));
        for (int i = 0; i < 32; i++) {
            if ((maxRatio >> i) == 0) {
                return 1 << (i - 1);
            }
        }
        return 1;
    }

    public static int getExifOrientation(String filepath) {
        if (filepath == null) {
            return 0;
        }
        try {
            int orientation = new ExifInterface(filepath).getAttributeInt("Orientation", -1);
            if (orientation == -1) {
                return 0;
            }
            switch (orientation) {
                case 3:
                    return 180;
                case 6:
                    return 90;
                case 8:
                    return MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS;
                default:
                    return 0;
            }
        } catch (IOException e) {
            Log.w(TAG, "unable to get exif orientation", e);
            return 0;
        }
    }

    public static Bitmap rotateIfNeeded(Bitmap bitmap, int degrees) {
        if (degrees == 0 || bitmap == null) {
            return bitmap;
        }
        Matrix m = new Matrix();
        m.setRotate((float) degrees, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
        try {
            Bitmap b2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            if (b2 != bitmap) {
                bitmap.recycle();
            }
            return b2;
        } catch (OutOfMemoryError e) {
            return bitmap;
        }
    }
}
