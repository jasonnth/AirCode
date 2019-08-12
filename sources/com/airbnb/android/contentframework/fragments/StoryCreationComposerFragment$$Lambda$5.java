package com.airbnb.android.contentframework.fragments;

final /* synthetic */ class StoryCreationComposerFragment$$Lambda$5 implements Runnable {
    private final StoryCreationComposerFragment arg$1;

    private StoryCreationComposerFragment$$Lambda$5(StoryCreationComposerFragment storyCreationComposerFragment) {
        this.arg$1 = storyCreationComposerFragment;
    }

    public static Runnable lambdaFactory$(StoryCreationComposerFragment storyCreationComposerFragment) {
        return new StoryCreationComposerFragment$$Lambda$5(storyCreationComposerFragment);
    }

    public void run() {
        this.arg$1.updateActionMenuState();
    }
}
