package com.airbnb.android.lib.host.stats;

import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Predicate;

final /* synthetic */ class HostReviewDetailAdapter$$Lambda$5 implements Predicate {
    private static final HostReviewDetailAdapter$$Lambda$5 instance = new HostReviewDetailAdapter$$Lambda$5();

    private HostReviewDetailAdapter$$Lambda$5() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return HostReviewDetailAdapter.lambda$onModelBound$3((EpoxyModel) obj);
    }
}
