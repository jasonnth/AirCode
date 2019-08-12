package com.airbnb.android.contentframework.fragments;

import com.airbnb.android.contentframework.responses.StoryCreationSearchPlaceResponse;
import java.util.ArrayList;
import p032rx.functions.Action1;

final /* synthetic */ class StoryCreationComposerFragment$$Lambda$2 implements Action1 {
    private final StoryCreationComposerFragment arg$1;

    private StoryCreationComposerFragment$$Lambda$2(StoryCreationComposerFragment storyCreationComposerFragment) {
        this.arg$1 = storyCreationComposerFragment;
    }

    public static Action1 lambdaFactory$(StoryCreationComposerFragment storyCreationComposerFragment) {
        return new StoryCreationComposerFragment$$Lambda$2(storyCreationComposerFragment);
    }

    public void call(Object obj) {
        this.arg$1.suggestedPlaceTags = new ArrayList<>(((StoryCreationSearchPlaceResponse) obj).placeTagList);
    }
}
