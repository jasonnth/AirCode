package com.airbnb.android.contentframework.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StoryCreationComposerFragment$$Lambda$8 implements OnClickListener {
    private final StoryCreationComposerFragment arg$1;

    private StoryCreationComposerFragment$$Lambda$8(StoryCreationComposerFragment storyCreationComposerFragment) {
        this.arg$1 = storyCreationComposerFragment;
    }

    public static OnClickListener lambdaFactory$(StoryCreationComposerFragment storyCreationComposerFragment) {
        return new StoryCreationComposerFragment$$Lambda$8(storyCreationComposerFragment);
    }

    public void onClick(View view) {
        StoryCreationComposerFragment.lambda$setupViews$3(this.arg$1, view);
    }
}
