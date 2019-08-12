package com.airbnb.android.contentframework.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class StoryCreationPlaceTaggingFragment$$Lambda$3 implements Action1 {
    private final StoryCreationPlaceTaggingFragment arg$1;

    private StoryCreationPlaceTaggingFragment$$Lambda$3(StoryCreationPlaceTaggingFragment storyCreationPlaceTaggingFragment) {
        this.arg$1 = storyCreationPlaceTaggingFragment;
    }

    public static Action1 lambdaFactory$(StoryCreationPlaceTaggingFragment storyCreationPlaceTaggingFragment) {
        return new StoryCreationPlaceTaggingFragment$$Lambda$3(storyCreationPlaceTaggingFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
