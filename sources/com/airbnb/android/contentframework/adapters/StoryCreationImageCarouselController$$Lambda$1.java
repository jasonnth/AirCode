package com.airbnb.android.contentframework.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StoryCreationImageCarouselController$$Lambda$1 implements OnClickListener {
    private final StoryCreationImageCarouselController arg$1;

    private StoryCreationImageCarouselController$$Lambda$1(StoryCreationImageCarouselController storyCreationImageCarouselController) {
        this.arg$1 = storyCreationImageCarouselController;
    }

    public static OnClickListener lambdaFactory$(StoryCreationImageCarouselController storyCreationImageCarouselController) {
        return new StoryCreationImageCarouselController$$Lambda$1(storyCreationImageCarouselController);
    }

    public void onClick(View view) {
        this.arg$1.delegate.onAddPhotoClicked();
    }
}
