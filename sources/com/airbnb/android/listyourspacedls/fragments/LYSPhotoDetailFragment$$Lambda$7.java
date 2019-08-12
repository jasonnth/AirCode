package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.models.Photo;
import com.google.common.base.Predicate;

final /* synthetic */ class LYSPhotoDetailFragment$$Lambda$7 implements Predicate {
    private final long arg$1;

    private LYSPhotoDetailFragment$$Lambda$7(long j) {
        this.arg$1 = j;
    }

    public static Predicate lambdaFactory$(long j) {
        return new LYSPhotoDetailFragment$$Lambda$7(j);
    }

    public boolean apply(Object obj) {
        return LYSPhotoDetailFragment.lambda$getPhoto$4(this.arg$1, (Photo) obj);
    }
}
