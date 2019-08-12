package com.airbnb.android.contentframework.fragments;

final /* synthetic */ class StoryCreationComposerFragment$$Lambda$12 implements Runnable {
    private final StoryCreationComposerFragment arg$1;
    private final int arg$2;

    private StoryCreationComposerFragment$$Lambda$12(StoryCreationComposerFragment storyCreationComposerFragment, int i) {
        this.arg$1 = storyCreationComposerFragment;
        this.arg$2 = i;
    }

    public static Runnable lambdaFactory$(StoryCreationComposerFragment storyCreationComposerFragment, int i) {
        return new StoryCreationComposerFragment$$Lambda$12(storyCreationComposerFragment, i);
    }

    public void run() {
        StoryCreationComposerFragment.lambda$updateImageCarousel$7(this.arg$1, this.arg$2);
    }
}
