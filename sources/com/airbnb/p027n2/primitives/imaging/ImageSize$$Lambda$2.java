package com.airbnb.p027n2.primitives.imaging;

import java.util.Comparator;

/* renamed from: com.airbnb.n2.primitives.imaging.ImageSize$$Lambda$2 */
final /* synthetic */ class ImageSize$$Lambda$2 implements Comparator {
    private static final ImageSize$$Lambda$2 instance = new ImageSize$$Lambda$2();

    private ImageSize$$Lambda$2() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ImageSize.lambda$static$1((ImageSize) obj, (ImageSize) obj2);
    }
}
