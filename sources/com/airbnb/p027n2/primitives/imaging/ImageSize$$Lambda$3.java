package com.airbnb.p027n2.primitives.imaging;

import com.google.common.base.Predicate;

/* renamed from: com.airbnb.n2.primitives.imaging.ImageSize$$Lambda$3 */
final /* synthetic */ class ImageSize$$Lambda$3 implements Predicate {
    private static final ImageSize$$Lambda$3 instance = new ImageSize$$Lambda$3();

    private ImageSize$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ImageSize.lambda$static$2((ImageSize) obj);
    }
}
