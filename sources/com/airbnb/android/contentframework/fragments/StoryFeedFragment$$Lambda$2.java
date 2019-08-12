package com.airbnb.android.contentframework.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StoryFeedFragment$$Lambda$2 implements OnClickListener {
    private final StoryFeedFragment arg$1;

    private StoryFeedFragment$$Lambda$2(StoryFeedFragment storyFeedFragment) {
        this.arg$1 = storyFeedFragment;
    }

    public static OnClickListener lambdaFactory$(StoryFeedFragment storyFeedFragment) {
        return new StoryFeedFragment$$Lambda$2(storyFeedFragment);
    }

    public void onClick(View view) {
        StoryFeedFragment.lambda$setupComposerPill$1(this.arg$1, view);
    }
}
