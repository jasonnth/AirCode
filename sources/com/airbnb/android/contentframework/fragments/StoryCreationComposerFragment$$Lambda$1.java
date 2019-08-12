package com.airbnb.android.contentframework.fragments;

import com.airbnb.android.utils.TextWatcherUtils.StringTextWatcher;

final /* synthetic */ class StoryCreationComposerFragment$$Lambda$1 implements StringTextWatcher {
    private final StoryCreationComposerFragment arg$1;

    private StoryCreationComposerFragment$$Lambda$1(StoryCreationComposerFragment storyCreationComposerFragment) {
        this.arg$1 = storyCreationComposerFragment;
    }

    public static StringTextWatcher lambdaFactory$(StoryCreationComposerFragment storyCreationComposerFragment) {
        return new StoryCreationComposerFragment$$Lambda$1(storyCreationComposerFragment);
    }

    public void textUpdated(String str) {
        this.arg$1.updateActionMenuState();
    }
}
