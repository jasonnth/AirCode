package com.airbnb.android.explore.controllers;

import p032rx.functions.Action1;

final /* synthetic */ class ExploreDataRepositoryImpl$$Lambda$5 implements Action1 {
    private final ExploreDataRepositoryImpl arg$1;

    private ExploreDataRepositoryImpl$$Lambda$5(ExploreDataRepositoryImpl exploreDataRepositoryImpl) {
        this.arg$1 = exploreDataRepositoryImpl;
    }

    public static Action1 lambdaFactory$(ExploreDataRepositoryImpl exploreDataRepositoryImpl) {
        return new ExploreDataRepositoryImpl$$Lambda$5(exploreDataRepositoryImpl);
    }

    public void call(Object obj) {
        ExploreDataRepositoryImpl.lambda$new$4(this.arg$1, (Boolean) obj);
    }
}
