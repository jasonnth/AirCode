package com.airbnb.android.contentframework.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class StoryDetailViewFragment$$Lambda$12 implements Action1 {
    private final StoryDetailViewFragment arg$1;

    private StoryDetailViewFragment$$Lambda$12(StoryDetailViewFragment storyDetailViewFragment) {
        this.arg$1 = storyDetailViewFragment;
    }

    public static Action1 lambdaFactory$(StoryDetailViewFragment storyDetailViewFragment) {
        return new StoryDetailViewFragment$$Lambda$12(storyDetailViewFragment);
    }

    public void call(Object obj) {
        StoryDetailViewFragment.lambda$new$11(this.arg$1, (AirRequestNetworkException) obj);
    }
}
