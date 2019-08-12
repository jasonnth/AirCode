package com.airbnb.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import com.bumptech.glide.Glide;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public final class ImageUtil {
    private ImageUtil() {
    }

    public static int getExifOrientation(String filepath) throws IOException {
        if (filepath == null) {
            return 0;
        }
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
    }

    public static int getInSampleSize(int srcWidth, int srcHeight, int maxWidth, int maxHeight, boolean roundUp) {
        if (srcWidth <= maxWidth && srcHeight <= maxHeight) {
            return 1;
        }
        int maxRatio = Math.max((int) Math.ceil((double) (((float) srcWidth) / ((float) maxWidth))), (int) Math.ceil((double) (((float) srcHeight) / ((float) maxHeight))));
        for (int i = 0; i < 32; i++) {
            if ((maxRatio >> i) == 0) {
                int pow2 = 1 << (i - 1);
                if (!roundUp || maxRatio <= pow2) {
                    return pow2;
                }
                return pow2 << 1;
            }
        }
        return 1;
    }

    public static Bitmap decodeSampledBitmapFromResource(Context context, int resId, int reqWidth, int reqHeight) {
        try {
            return (Bitmap) Glide.with(context).load(Integer.valueOf(resId)).asBitmap().into(reqWidth, reqHeight).get();
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }
    }
}
