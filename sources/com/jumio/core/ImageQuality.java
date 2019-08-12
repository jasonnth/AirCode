package com.jumio.core;

import com.jumio.clientlib.impl.imagequality.ImageQualityAcquisition;
import com.jumio.clientlib.impl.imagequality.PixelFormatType;
import com.jumio.commons.log.Log;
import com.jumio.ocr.impl.smartEngines.swig.ImageCheck;

public class ImageQuality {
    public static final float FOCUS_THRESHOLD = 0.12f;
    public static final int INTENSITY_THRESHOLD = 70;

    public static boolean isFlashNeeded(RawImageFrame item) {
        return isFlashNeeded(item.data, item.width, item.height, item.format == 0);
    }

    public static boolean isFlashNeeded(byte[] yuv, int width, int height) {
        return isFlashNeeded(yuv, width, height, 70);
    }

    public static boolean isFlashNeeded(byte[] yuv, int width, int height, int threshold) {
        return isFlashNeeded(yuv, width, height, threshold, false);
    }

    public static boolean isFlashNeeded(byte[] data, int width, int height, boolean isRgb) {
        return isFlashNeeded(data, width, height, 70, isRgb);
    }

    public static boolean isFlashNeeded(byte[] data, int width, int height, int threshold, boolean isRgb) {
        int strideMultiplier;
        int channels;
        if (isRgb) {
            strideMultiplier = 3;
        } else {
            strideMultiplier = 1;
        }
        if (isRgb) {
            channels = 3;
        } else {
            channels = 1;
        }
        Log.m1924v("ImageCheck", String.format("-- isFlashNeeded: len = %d, threshold=%d, w = %d, h = %d, stride = %d, ch = %d", new Object[]{Integer.valueOf(data.length), Integer.valueOf(70), Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(strideMultiplier * width), Integer.valueOf(channels)}));
        try {
            return ImageCheck.isFlashNeeded(data, threshold, width, height, strideMultiplier * width, channels);
        } catch (Exception e) {
            Log.m1930w("ImageCheck", "isFlashNeeded failed!", (Throwable) e);
            return false;
        }
    }

    public static boolean isRefocusNeeded(RawImageFrame item) {
        return calculateFocus(item.data, item.width, item.height, item.format == 0) < 0.12f;
    }

    public static float calculateFocus(byte[] yuv, int width, int height) {
        return calculateFocus(yuv, width, height, 0.12f);
    }

    public static float calculateFocus(byte[] yuv, int width, int height, float threshold) {
        return calculateFocus(yuv, width, height, threshold, false);
    }

    public static float calculateFocus(byte[] data, int width, int height, boolean isRgb) {
        return calculateFocus(data, width, height, 0.12f, isRgb);
    }

    public static float calculateFocus(byte[] data, int width, int height, float threshold, boolean isRgb) {
        int strideMultiplier;
        if (isRgb) {
            strideMultiplier = 3;
        } else {
            strideMultiplier = 1;
        }
        if (!isRgb) {
        }
        PixelFormatType pixelFormatType = isRgb ? PixelFormatType.PIXEL_FORMAT_RGB_8 : PixelFormatType.PIXEL_FORMAT_YUV420_NV21;
        float focusConfidence = -1.0f;
        try {
            Log.m1924v("ImageCheck", String.format("-- computeFocusConfidence: len = %d, threshold=%f, w = %d, h = %d, stride = %d, format = %s", new Object[]{Integer.valueOf(data.length), Float.valueOf(threshold), Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(strideMultiplier * width), pixelFormatType.toString()}));
            return ImageQualityAcquisition.Evaluate(data, ((long) strideMultiplier) * ((long) width), width, height, pixelFormatType);
        } catch (Exception ex) {
            Log.m1930w("ImageCheck", "computeFocusConfidence failed!", (Throwable) ex);
            return focusConfidence;
        }
    }
}
