package com.airbnb.android.cohosting.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class RemoveCohostFragment$$Lambda$1 implements Action1 {
    private final RemoveCohostFragment arg$1;

    private RemoveCohostFragment$$Lambda$1(RemoveCohostFragment removeCohostFragment) {
        this.arg$1 = removeCohostFragment;
    }

    public static Action1 lambdaFactory$(RemoveCohostFragment removeCohostFragment) {
        return new RemoveCohostFragment$$Lambda$1(removeCohostFragment);
    }

    public void call(Object obj) {
        this.arg$1.finishRemoval();
    }
}
