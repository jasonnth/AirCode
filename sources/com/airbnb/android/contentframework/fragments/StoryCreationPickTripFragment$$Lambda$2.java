package com.airbnb.android.contentframework.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class StoryCreationPickTripFragment$$Lambda$2 implements Action1 {
    private final StoryCreationPickTripFragment arg$1;

    private StoryCreationPickTripFragment$$Lambda$2(StoryCreationPickTripFragment storyCreationPickTripFragment) {
        this.arg$1 = storyCreationPickTripFragment;
    }

    public static Action1 lambdaFactory$(StoryCreationPickTripFragment storyCreationPickTripFragment) {
        return new StoryCreationPickTripFragment$$Lambda$2(storyCreationPickTripFragment);
    }

    public void call(Object obj) {
        StoryCreationPickTripFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
