package com.airbnb.android.explore.controllers;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ExploreDataRepositoryImpl$$Lambda$7 implements Action1 {
    private static final ExploreDataRepositoryImpl$$Lambda$7 instance = new ExploreDataRepositoryImpl$$Lambda$7();

    private ExploreDataRepositoryImpl$$Lambda$7() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        ExploreDataRepositoryImpl.lambda$new$6((AirRequestNetworkException) obj);
    }
}
