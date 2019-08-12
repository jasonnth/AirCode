package com.airbnb.p027n2.primitives.imaging;

import com.google.common.base.Predicate;

/* renamed from: com.airbnb.n2.primitives.imaging.ImageSize$$Lambda$1 */
final /* synthetic */ class ImageSize$$Lambda$1 implements Predicate {
    private static final ImageSize$$Lambda$1 instance = new ImageSize$$Lambda$1();

    private ImageSize$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((ImageSize) obj).portrait;
    }
}
