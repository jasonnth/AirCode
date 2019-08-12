package com.airbnb.android.explore.controllers;

import com.airbnb.android.core.responses.ExploreTabResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ExploreDataRepositoryImpl$$Lambda$3 implements Action1 {
    private final ExploreDataRepositoryImpl arg$1;

    private ExploreDataRepositoryImpl$$Lambda$3(ExploreDataRepositoryImpl exploreDataRepositoryImpl) {
        this.arg$1 = exploreDataRepositoryImpl;
    }

    public static Action1 lambdaFactory$(ExploreDataRepositoryImpl exploreDataRepositoryImpl) {
        return new ExploreDataRepositoryImpl$$Lambda$3(exploreDataRepositoryImpl);
    }

    public void call(Object obj) {
        ExploreDataRepositoryImpl.lambda$new$2(this.arg$1, (ExploreTabResponse) obj);
    }
}
