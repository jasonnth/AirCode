package com.airbnb.android.contentframework.fragments;

import com.airbnb.android.core.interfaces.OnBackListener;

final /* synthetic */ class StoryCreationComposerFragment$$Lambda$3 implements OnBackListener {
    private final StoryCreationComposerFragment arg$1;

    private StoryCreationComposerFragment$$Lambda$3(StoryCreationComposerFragment storyCreationComposerFragment) {
        this.arg$1 = storyCreationComposerFragment;
    }

    public static OnBackListener lambdaFactory$(StoryCreationComposerFragment storyCreationComposerFragment) {
        return new StoryCreationComposerFragment$$Lambda$3(storyCreationComposerFragment);
    }

    public boolean onBackPressed() {
        return this.arg$1.confirmExit();
    }
}
