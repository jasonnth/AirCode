package com.airbnb.android.contentframework.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.StoryCreationPlaceTag;

final /* synthetic */ class StoryCreationPlaceSearchEpoxyController$$Lambda$1 implements OnClickListener {
    private final StoryCreationPlaceSearchEpoxyController arg$1;
    private final StoryCreationPlaceTag arg$2;

    private StoryCreationPlaceSearchEpoxyController$$Lambda$1(StoryCreationPlaceSearchEpoxyController storyCreationPlaceSearchEpoxyController, StoryCreationPlaceTag storyCreationPlaceTag) {
        this.arg$1 = storyCreationPlaceSearchEpoxyController;
        this.arg$2 = storyCreationPlaceTag;
    }

    public static OnClickListener lambdaFactory$(StoryCreationPlaceSearchEpoxyController storyCreationPlaceSearchEpoxyController, StoryCreationPlaceTag storyCreationPlaceTag) {
        return new StoryCreationPlaceSearchEpoxyController$$Lambda$1(storyCreationPlaceSearchEpoxyController, storyCreationPlaceTag);
    }

    public void onClick(View view) {
        this.arg$1.delegate.onPlaceClicked(this.arg$2);
    }
}
