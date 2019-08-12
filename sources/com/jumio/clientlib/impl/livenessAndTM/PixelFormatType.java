package com.jumio.clientlib.impl.livenessAndTM;

public final class PixelFormatType {
    public static final PixelFormatType PIXEL_FORMAT_BGRA_8 = new PixelFormatType("PIXEL_FORMAT_BGRA_8");
    public static final PixelFormatType PIXEL_FORMAT_BGR_8 = new PixelFormatType("PIXEL_FORMAT_BGR_8");
    public static final PixelFormatType PIXEL_FORMAT_GRAY = new PixelFormatType("PIXEL_FORMAT_GRAY");
    public static final PixelFormatType PIXEL_FORMAT_RGBA_8 = new PixelFormatType("PIXEL_FORMAT_RGBA_8");
    public static final PixelFormatType PIXEL_FORMAT_RGB_8 = new PixelFormatType("PIXEL_FORMAT_RGB_8");
    public static final PixelFormatType PIXEL_FORMAT_YUV420_NV21 = new PixelFormatType("PIXEL_FORMAT_YUV420_NV21");

    /* renamed from: a */
    private static PixelFormatType[] f3188a = {PIXEL_FORMAT_RGB_8, PIXEL_FORMAT_RGBA_8, PIXEL_FORMAT_BGR_8, PIXEL_FORMAT_BGRA_8, PIXEL_FORMAT_YUV420_NV21, PIXEL_FORMAT_GRAY};

    /* renamed from: b */
    private static int f3189b = 0;

    /* renamed from: c */
    private final int f3190c;

    /* renamed from: d */
    private final String f3191d;

    public final int swigValue() {
        return this.f3190c;
    }

    public String toString() {
        return this.f3191d;
    }

    public static PixelFormatType swigToEnum(int i) {
        if (i < f3188a.length && i >= 0 && f3188a[i].f3190c == i) {
            return f3188a[i];
        }
        for (int i2 = 0; i2 < f3188a.length; i2++) {
            if (f3188a[i2].f3190c == i) {
                return f3188a[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + PixelFormatType.class + " with value " + i);
    }

    private PixelFormatType(String str) {
        this.f3191d = str;
        int i = f3189b;
        f3189b = i + 1;
        this.f3190c = i;
    }
}
