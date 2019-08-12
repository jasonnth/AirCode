package com.airbnb.android.explore.controllers;

import com.airbnb.android.core.responses.ExploreTabResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ExploreDataRepositoryImpl$$Lambda$8 implements Action1 {
    private final ExploreDataRepositoryImpl arg$1;

    private ExploreDataRepositoryImpl$$Lambda$8(ExploreDataRepositoryImpl exploreDataRepositoryImpl) {
        this.arg$1 = exploreDataRepositoryImpl;
    }

    public static Action1 lambdaFactory$(ExploreDataRepositoryImpl exploreDataRepositoryImpl) {
        return new ExploreDataRepositoryImpl$$Lambda$8(exploreDataRepositoryImpl);
    }

    public void call(Object obj) {
        this.arg$1.callback.onMetadataLoaded((ExploreTabResponse) obj);
    }
}
