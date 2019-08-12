package com.airbnb.android.p011p3;

import com.airbnb.android.core.models.SimilarListing;
import com.google.common.base.Function;

/* renamed from: com.airbnb.android.p3.P3EpoxyController$$Lambda$23 */
final /* synthetic */ class P3EpoxyController$$Lambda$23 implements Function {
    private static final P3EpoxyController$$Lambda$23 instance = new P3EpoxyController$$Lambda$23();

    private P3EpoxyController$$Lambda$23() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((SimilarListing) obj).getListing().getId());
    }
}
