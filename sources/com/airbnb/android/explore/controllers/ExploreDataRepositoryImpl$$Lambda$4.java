package com.airbnb.android.explore.controllers;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ExploreDataRepositoryImpl$$Lambda$4 implements Action1 {
    private final ExploreDataRepositoryImpl arg$1;

    private ExploreDataRepositoryImpl$$Lambda$4(ExploreDataRepositoryImpl exploreDataRepositoryImpl) {
        this.arg$1 = exploreDataRepositoryImpl;
    }

    public static Action1 lambdaFactory$(ExploreDataRepositoryImpl exploreDataRepositoryImpl) {
        return new ExploreDataRepositoryImpl$$Lambda$4(exploreDataRepositoryImpl);
    }

    public void call(Object obj) {
        ExploreDataRepositoryImpl.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}