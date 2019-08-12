package com.jumio.clientlib.impl.livenessAndTM;

public final class ImageOrientation {
    public static final ImageOrientation IMAGE_ORIENTATION_LANDSCAPE = new ImageOrientation("IMAGE_ORIENTATION_LANDSCAPE", jniLivenessAndTMJNI.IMAGE_ORIENTATION_LANDSCAPE_get());
    public static final ImageOrientation IMAGE_ORIENTATION_PORTRAIT = new ImageOrientation("IMAGE_ORIENTATION_PORTRAIT");
    public static final ImageOrientation IMAGE_ORIENTATION_REVERSE_LANDSCAPE = new ImageOrientation("IMAGE_ORIENTATION_REVERSE_LANDSCAPE");
    public static final ImageOrientation IMAGE_ORIENTATION_REVERSE_PORTRAIT = new ImageOrientation("IMAGE_ORIENTATION_REVERSE_PORTRAIT");

    /* renamed from: a */
    private static ImageOrientation[] f3177a = {IMAGE_ORIENTATION_LANDSCAPE, IMAGE_ORIENTATION_PORTRAIT, IMAGE_ORIENTATION_REVERSE_LANDSCAPE, IMAGE_ORIENTATION_REVERSE_PORTRAIT};

    /* renamed from: b */
    private static int f3178b = 0;

    /* renamed from: c */
    private final int f3179c;

    /* renamed from: d */
    private final String f3180d;

    public final int swigValue() {
        return this.f3179c;
    }

    public String toString() {
        return this.f3180d;
    }

    public static ImageOrientation swigToEnum(int i) {
        if (i < f3177a.length && i >= 0 && f3177a[i].f3179c == i) {
            return f3177a[i];
        }
        for (int i2 = 0; i2 < f3177a.length; i2++) {
            if (f3177a[i2].f3179c == i) {
                return f3177a[i2];
            }
        }
        throw new IllegalArgumentException("No enum " + ImageOrientation.class + " with value " + i);
    }

    private ImageOrientation(String str) {
        this.f3180d = str;
        int i = f3178b;
        f3178b = i + 1;
        this.f3179c = i;
    }

    private ImageOrientation(String str, int i) {
        this.f3180d = str;
        this.f3179c = i;
        f3178b = i + 1;
    }
}
