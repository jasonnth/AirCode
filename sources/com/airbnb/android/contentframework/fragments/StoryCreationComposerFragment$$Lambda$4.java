package com.airbnb.android.contentframework.fragments;

import com.airbnb.android.core.interfaces.OnHomeListener;

final /* synthetic */ class StoryCreationComposerFragment$$Lambda$4 implements OnHomeListener {
    private final StoryCreationComposerFragment arg$1;

    private StoryCreationComposerFragment$$Lambda$4(StoryCreationComposerFragment storyCreationComposerFragment) {
        this.arg$1 = storyCreationComposerFragment;
    }

    public static OnHomeListener lambdaFactory$(StoryCreationComposerFragment storyCreationComposerFragment) {
        return new StoryCreationComposerFragment$$Lambda$4(storyCreationComposerFragment);
    }

    public boolean onHomePressed() {
        return this.arg$1.confirmExit();
    }
}
