package com.airbnb.p027n2.primitives.imaging;

import java.util.Comparator;

/* renamed from: com.airbnb.n2.primitives.imaging.ImageSize$$Lambda$4 */
final /* synthetic */ class ImageSize$$Lambda$4 implements Comparator {
    private static final ImageSize$$Lambda$4 instance = new ImageSize$$Lambda$4();

    private ImageSize$$Lambda$4() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ImageSize.lambda$static$3((ImageSize) obj, (ImageSize) obj2);
    }
}
