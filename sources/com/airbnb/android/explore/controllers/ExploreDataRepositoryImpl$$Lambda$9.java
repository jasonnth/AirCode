package com.airbnb.android.explore.controllers;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ExploreDataRepositoryImpl$$Lambda$9 implements Action1 {
    private final ExploreDataRepositoryImpl arg$1;

    private ExploreDataRepositoryImpl$$Lambda$9(ExploreDataRepositoryImpl exploreDataRepositoryImpl) {
        this.arg$1 = exploreDataRepositoryImpl;
    }

    public static Action1 lambdaFactory$(ExploreDataRepositoryImpl exploreDataRepositoryImpl) {
        return new ExploreDataRepositoryImpl$$Lambda$9(exploreDataRepositoryImpl);
    }

    public void call(Object obj) {
        this.arg$1.callback.onMetadataFailed((AirRequestNetworkException) obj);
    }
}
