package com.jumio.commons.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import com.airbnb.android.airmapview.AirMapInterface;
import com.airbnb.android.core.models.MaxDaysNoticeSetting;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtil {
    public static Bitmap rgbToBitmap(byte[] rgb, int width, int height) {
        if (rgb == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        int[] row = new int[width];
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                row[i] = AirMapInterface.CIRCLE_BORDER_COLOR + ((rgb[((j * width) * 3) + (i * 3)] & 255) << 16) + ((rgb[(((j * width) * 3) + (i * 3)) + 1] & 255) << 8) + (rgb[(j * width * 3) + (i * 3) + 2] & 255);
            }
            bitmap.setPixels(row, 0, width, 0, j, width, 1);
        }
        return bitmap;
    }

    public static byte[] rgbToFormat(byte[] rgb, int width, int height, CompressFormat format, int quality) {
        Bitmap bitmap = rgbToBitmap(rgb, width, height);
        byte[] webP = bitmapToFormat(bitmap, format, quality);
        MutableBitmap.delete(bitmap);
        return webP;
    }

    public static byte[] bitmapToFormat(Bitmap bitmap, CompressFormat format, int quality) {
        byte[] out = null;
        try {
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
            bitmap.compress(format, quality, blob);
            out = blob.toByteArray();
            blob.close();
            return out;
        } catch (IOException e) {
            e.printStackTrace();
            return out;
        }
    }

    public static byte[] bitmap2rgb(Bitmap _cropped) {
        int w = _cropped.getWidth();
        int h = _cropped.getHeight();
        byte[] data = new byte[(w * h * 3)];
        int[] pixels = new int[(w * h)];
        _cropped.getPixels(pixels, 0, w, 0, 0, w, h);
        int byteIx = 0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int ix = (y * w) + x;
                data[byteIx] = (byte) ((pixels[ix] >> 16) & 255);
                data[byteIx + 1] = (byte) ((pixels[ix] >> 8) & 255);
                data[byteIx + 2] = (byte) (pixels[ix] & 255);
                byteIx += 3;
            }
        }
        return data;
    }

    public static int calculateInSampleSize(Options options, int reqWidth, int reqHeight) {
        if (options == null) {
            return 1;
        }
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height <= reqHeight && width <= reqWidth) {
            return 1;
        }
        int halfHeight = height / 2;
        int halfWidth = width / 2;
        while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
            inSampleSize *= 2;
        }
        return inSampleSize;
    }

    public static Bitmap loadResized(String file, int width, int height, int degrees) {
        Options factoryOptions = new Options();
        factoryOptions.inJustDecodeBounds = true;
        Bitmap decodeFile = BitmapFactory.decodeFile(file, factoryOptions);
        int heightRatio = (int) Math.ceil((double) (((float) factoryOptions.outHeight) / ((float) height)));
        int widthRatio = (int) Math.ceil((double) (((float) factoryOptions.outWidth) / ((float) width)));
        if (heightRatio > 1 || widthRatio > 1) {
            if (heightRatio > widthRatio) {
                factoryOptions.inSampleSize = heightRatio;
            } else {
                factoryOptions.inSampleSize = widthRatio;
            }
        }
        factoryOptions.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(file, factoryOptions);
        if (degrees != 0) {
            Bitmap source = bitmap;
            bitmap = rotate(source, degrees);
            if (bitmap != source) {
                source.recycle();
            }
        }
        return bitmap;
    }

    public static Bitmap loadResized(Context context, int resId, int width, int height) {
        Options factoryOptions = new Options();
        factoryOptions.inJustDecodeBounds = true;
        int heightRatio = (int) Math.ceil((double) (((float) factoryOptions.outHeight) / ((float) height)));
        int widthRatio = (int) Math.ceil((double) (((float) factoryOptions.outWidth) / ((float) width)));
        if (heightRatio > 1 || widthRatio > 1) {
            if (heightRatio > widthRatio) {
                factoryOptions.inSampleSize = heightRatio;
            } else {
                factoryOptions.inSampleSize = widthRatio;
            }
        }
        factoryOptions.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), resId, factoryOptions);
    }

    public static Bitmap rotate(Bitmap source, int degrees) {
        if (source == null) {
            return null;
        }
        Matrix lMatrix = new Matrix();
        lMatrix.postRotate((float) degrees);
        int width = 0;
        int height = 0;
        switch (degrees) {
            case 0:
            case 180:
                width = source.getWidth();
                height = source.getHeight();
                break;
            case 90:
            case MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS /*270*/:
                width = source.getHeight();
                height = source.getWidth();
                break;
        }
        return Bitmap.createBitmap(source, 0, 0, width, height, lMatrix, true);
    }
}
