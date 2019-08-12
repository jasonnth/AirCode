package com.airbnb.android.contentframework.fragments;

import com.airbnb.android.contentframework.responses.StoryCreationSearchPlaceResponse;
import p032rx.functions.Action1;

final /* synthetic */ class StoryCreationPlaceTaggingFragment$$Lambda$2 implements Action1 {
    private final StoryCreationPlaceTaggingFragment arg$1;

    private StoryCreationPlaceTaggingFragment$$Lambda$2(StoryCreationPlaceTaggingFragment storyCreationPlaceTaggingFragment) {
        this.arg$1 = storyCreationPlaceTaggingFragment;
    }

    public static Action1 lambdaFactory$(StoryCreationPlaceTaggingFragment storyCreationPlaceTaggingFragment) {
        return new StoryCreationPlaceTaggingFragment$$Lambda$2(storyCreationPlaceTaggingFragment);
    }

    public void call(Object obj) {
        this.arg$1.placeSearchEpoxyController.setLoadingResults(((StoryCreationSearchPlaceResponse) obj).placeTagList);
    }
}
