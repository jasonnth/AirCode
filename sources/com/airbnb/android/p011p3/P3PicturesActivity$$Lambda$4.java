package com.airbnb.android.p011p3;

import com.airbnb.android.core.models.Photo;
import com.google.common.base.Function;

/* renamed from: com.airbnb.android.p3.P3PicturesActivity$$Lambda$4 */
final /* synthetic */ class P3PicturesActivity$$Lambda$4 implements Function {
    private static final P3PicturesActivity$$Lambda$4 instance = new P3PicturesActivity$$Lambda$4();

    private P3PicturesActivity$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return P3PicturesActivity.lambda$setPhotos$1((Photo) obj);
    }
}
