package com.airbnb.android.contentframework.fragments;

import com.airbnb.android.core.responses.GuestReservationsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class StoryCreationPickTripFragment$$Lambda$1 implements Action1 {
    private final StoryCreationPickTripFragment arg$1;

    private StoryCreationPickTripFragment$$Lambda$1(StoryCreationPickTripFragment storyCreationPickTripFragment) {
        this.arg$1 = storyCreationPickTripFragment;
    }

    public static Action1 lambdaFactory$(StoryCreationPickTripFragment storyCreationPickTripFragment) {
        return new StoryCreationPickTripFragment$$Lambda$1(storyCreationPickTripFragment);
    }

    public void call(Object obj) {
        StoryCreationPickTripFragment.lambda$new$0(this.arg$1, (GuestReservationsResponse) obj);
    }
}
