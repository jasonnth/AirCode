package com.jumio.commons.camera;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import com.airbnb.android.airmapview.AirMapInterface;
import com.jumio.commons.camera.CameraManager.PreviewProperties;
import com.jumio.commons.camera.CameraManager.Size;

public class CameraUtils {
    private static IYuvConversion yuvConversion;

    public interface IYuvConversion {
        int yuvCutRotateScale2rgb(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, byte[] bArr2, int i7, int i8, int i9, StringBuilder sb);
    }

    public static void setYuvConversion(IYuvConversion yuvConversion2) {
        yuvConversion = yuvConversion2;
    }

    public static int getImageRotation(PreviewProperties previewProperties) {
        int imageRotation = previewProperties.orientation / 90;
        if (!previewProperties.frontFacing) {
            return imageRotation;
        }
        if (imageRotation == 1 || imageRotation == 3) {
            return (imageRotation + 2) % 4;
        }
        return imageRotation;
    }

    public static Bitmap yuv2bitmap(byte[] yuv, boolean isPortrait, PreviewProperties properties) {
        return yuv2bitmap(yuv, isPortrait, properties, false);
    }

    public static Bitmap yuv2bitmap(byte[] yuv, boolean isPortrait, PreviewProperties properties, boolean cropToSurface) {
        Size outSize = new Size(-1, -1);
        byte[] rgb = yuv2rgb(yuv, isPortrait, properties, cropToSurface, outSize);
        if (rgb != null) {
            return rgb2bitmap(rgb, outSize.width, outSize.height);
        }
        return null;
    }

    public static Bitmap yuv2bitmap(byte[] yuv, boolean isPortrait, PreviewProperties properties, boolean cropToSurface, int maxSize) {
        Size outSize = new Size(-1, -1);
        byte[] rgb = yuv2rgb(yuv, isPortrait, properties, cropToSurface, outSize, maxSize);
        if (rgb != null) {
            return rgb2bitmap(rgb, outSize.width, outSize.height);
        }
        return null;
    }

    public static Bitmap yuv2bitmap(byte[] yuv, boolean isPortrait, PreviewProperties properties, float ratio) {
        Size outSize = new Size(-1, -1);
        byte[] rgb = yuv2rgb(yuv, isPortrait, properties, ratio, outSize);
        if (rgb != null) {
            return rgb2bitmap(rgb, outSize.width, outSize.height);
        }
        return null;
    }

    public static Bitmap yuv2bitmap(byte[] yuv, boolean isPortrait, PreviewProperties properties, int holeWidth, int holeHeight, int holeLeft, int holeTop, int outWidth, int outHeight) {
        byte[] rgb = yuv2rgb(yuv, isPortrait, properties, holeWidth, holeHeight, holeLeft, holeTop, outWidth, outHeight);
        if (rgb != null) {
            return rgb2bitmap(rgb, outWidth, outHeight);
        }
        return null;
    }

    public static byte[] yuv2rgb(byte[] yuv, boolean isPortrait, PreviewProperties properties) {
        return yuv2rgb(yuv, isPortrait, properties, false, (Size) null);
    }

    public static byte[] yuv2rgb(byte[] yuv, boolean isPortrait, PreviewProperties previewProperties, boolean cropToSurface, Size outSize) {
        return yuv2rgb(yuv, isPortrait, previewProperties, cropToSurface, outSize, -1);
    }

    public static byte[] yuv2rgb(byte[] yuv, boolean isPortrait, PreviewProperties previewProperties, boolean cropToSurface, Size outSize, int maxSize) {
        int holeTop;
        int holeHeight;
        int holeWidth;
        int holeLeft;
        int outWidth;
        int outHeight;
        if (cropToSurface) {
            if (isPortrait) {
                holeTop = (int) ((((float) (previewProperties.scaledPreview.width - previewProperties.surface.width)) / 2.0f) * (((float) previewProperties.preview.width) / ((float) previewProperties.scaledPreview.width)));
                holeHeight = previewProperties.preview.width - (holeTop * 2);
                holeLeft = (int) ((((float) (previewProperties.scaledPreview.height - previewProperties.surface.height)) / 2.0f) * (((float) previewProperties.preview.height) / ((float) previewProperties.scaledPreview.height)));
                holeWidth = previewProperties.preview.height - (holeLeft * 2);
                outWidth = holeHeight;
                outHeight = holeWidth;
            } else {
                holeTop = (int) ((((float) (previewProperties.scaledPreview.height - previewProperties.surface.height)) / 2.0f) * (((float) previewProperties.preview.height) / ((float) previewProperties.scaledPreview.height)));
                holeHeight = previewProperties.preview.height - (holeTop * 2);
                holeLeft = (int) ((((float) (previewProperties.scaledPreview.width - previewProperties.surface.width)) / 2.0f) * (((float) previewProperties.preview.width) / ((float) previewProperties.scaledPreview.width)));
                holeWidth = previewProperties.preview.width - (holeLeft * 2);
                outWidth = holeWidth;
                outHeight = holeHeight;
            }
        } else if (isPortrait) {
            holeTop = 0;
            holeHeight = previewProperties.preview.width;
            holeWidth = previewProperties.preview.height;
            holeLeft = 0;
            outWidth = holeHeight;
            outHeight = holeWidth;
        } else {
            holeTop = 0;
            holeHeight = previewProperties.preview.height;
            holeWidth = previewProperties.preview.width;
            holeLeft = 0;
            outWidth = holeWidth;
            outHeight = holeHeight;
        }
        if (maxSize != -1 && (outWidth > maxSize || outHeight > maxSize)) {
            if (outWidth > outHeight) {
                float ratio = ((float) outHeight) / ((float) outWidth);
                outWidth = maxSize;
                outHeight = (int) (((float) maxSize) * ratio);
            } else {
                float ratio2 = ((float) outWidth) / ((float) outHeight);
                outHeight = maxSize;
                outWidth = (int) (((float) maxSize) * ratio2);
            }
        }
        if (outSize != null) {
            outSize.width = outWidth;
            outSize.height = outHeight;
        }
        return yuv2rgb(yuv, isPortrait, previewProperties, holeWidth, holeHeight, holeLeft, holeTop, outWidth, outHeight);
    }

    public static byte[] yuv2rgb(byte[] yuv, boolean isPortrait, PreviewProperties previewProperties, float ratio, Size outSize) {
        return yuv2rgb(yuv, isPortrait, previewProperties, ratio, outSize, (StringBuilder) null);
    }

    public static byte[] yuv2rgb(byte[] yuv, boolean isPortrait, PreviewProperties previewProperties, float ratio, Size outSize, StringBuilder extendedLog) {
        int holeLeft;
        int holeWidth;
        int holeHeight;
        int holeTop;
        int i;
        int i2;
        float surfaceRatio = ((float) previewProperties.surface.height) / ((float) previewProperties.surface.width);
        if (isPortrait) {
            if (surfaceRatio >= ratio) {
                holeTop = (int) ((((float) (previewProperties.scaledPreview.width - previewProperties.surface.width)) / 2.0f) * (((float) previewProperties.preview.width) / ((float) previewProperties.scaledPreview.width)));
                holeHeight = previewProperties.preview.width - (holeTop * 2);
                holeWidth = (int) (((float) holeHeight) * ratio);
                holeLeft = (previewProperties.preview.height - holeWidth) / 2;
            } else {
                holeLeft = (int) ((((float) (previewProperties.scaledPreview.height - previewProperties.surface.height)) / 2.0f) * (((float) previewProperties.preview.height) / ((float) previewProperties.scaledPreview.height)));
                holeWidth = previewProperties.preview.height - (holeLeft * 2);
                holeHeight = (int) (((float) holeWidth) / ratio);
                holeTop = (previewProperties.preview.width - holeHeight) / 2;
            }
        } else if (surfaceRatio <= ratio) {
            holeTop = (int) ((((float) (previewProperties.scaledPreview.height - previewProperties.surface.height)) / 2.0f) * (((float) previewProperties.preview.height) / ((float) previewProperties.scaledPreview.height)));
            holeHeight = previewProperties.preview.height - (holeTop * 2);
            holeWidth = (int) (((float) holeHeight) / ratio);
            holeLeft = (previewProperties.preview.width - holeWidth) / 2;
        } else {
            holeLeft = (int) ((((float) (previewProperties.scaledPreview.width - previewProperties.surface.width)) / 2.0f) * (((float) previewProperties.preview.width) / ((float) previewProperties.scaledPreview.width)));
            holeWidth = previewProperties.preview.width - (holeLeft * 2);
            holeHeight = (int) (((float) holeWidth) * ratio);
            holeTop = (previewProperties.preview.height - holeHeight) / 2;
        }
        if (outSize.width == -1) {
            if (isPortrait) {
                i2 = holeHeight;
            } else {
                i2 = holeWidth;
            }
            outSize.width = i2;
        }
        if (outSize.height == -1) {
            if (isPortrait) {
                i = holeWidth;
            } else {
                i = holeHeight;
            }
            outSize.height = i;
        }
        return yuv2rgb(yuv, isPortrait, previewProperties, holeWidth, holeHeight, holeLeft, holeTop, outSize.width, outSize.height);
    }

    public static byte[] yuv2rgb(byte[] yuv, boolean isPortrait, PreviewProperties properties, int holeWidth, int holeHeight, int holeLeft, int holeTop, int outWidth, int outHeight) {
        return yuv2rgb(yuv, isPortrait, properties, holeWidth, holeHeight, holeLeft, holeTop, outWidth, outHeight, null);
    }

    public static byte[] yuv2rgb(byte[] yuv, boolean isPortrait, PreviewProperties properties, int holeWidth, int holeHeight, int holeLeft, int holeTop, int outWidth, int outHeight, StringBuilder extendedLog) {
        int yuvWidth;
        int yuvHeight;
        if (isPortrait) {
            yuvWidth = properties.preview.height;
            yuvHeight = properties.preview.width;
        } else {
            yuvWidth = properties.preview.width;
            yuvHeight = properties.preview.height;
        }
        int imageRotation = properties.orientation / 90;
        if (properties.frontFacing && (imageRotation == 1 || imageRotation == 3)) {
            imageRotation = (imageRotation + 2) % 4;
        }
        byte[] out = new byte[(outWidth * outHeight * 3)];
        int status = -1;
        if (yuvConversion != null) {
            status = yuvConversion.yuvCutRotateScale2rgb(yuv, yuvWidth, yuvHeight, holeLeft, holeTop, holeWidth, holeHeight, out, outWidth, outHeight, imageRotation, extendedLog);
        }
        if (status != 0) {
            return null;
        }
        return out;
    }

    public static Bitmap rgb2bitmap(byte[] rgb, int width, int height) {
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

    public static Bitmap rgb2bitmap(byte[] rgb, int width, int height, int cornerRadius) {
        Bitmap bitmap = rgb2bitmap(rgb, width, height);
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float roundPx = (float) cornerRadius;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x001a A[SYNTHETIC, Splitter:B:12:0x001a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void saveBitmap(android.graphics.Bitmap r4, java.io.File r5, android.graphics.Bitmap.CompressFormat r6, int r7) throws java.io.IOException {
        /*
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x0017 }
            r2.<init>(r5)     // Catch:{ all -> 0x0017 }
            r4.compress(r6, r7, r2)     // Catch:{ all -> 0x0023 }
            r2.flush()     // Catch:{ all -> 0x0023 }
            if (r2 == 0) goto L_0x0011
            r2.close()     // Catch:{ IOException -> 0x0012 }
        L_0x0011:
            return
        L_0x0012:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0011
        L_0x0017:
            r3 = move-exception
        L_0x0018:
            if (r1 == 0) goto L_0x001d
            r1.close()     // Catch:{ IOException -> 0x001e }
        L_0x001d:
            throw r3
        L_0x001e:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x001d
        L_0x0023:
            r3 = move-exception
            r1 = r2
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.commons.camera.CameraUtils.saveBitmap(android.graphics.Bitmap, java.io.File, android.graphics.Bitmap$CompressFormat, int):void");
    }
}
