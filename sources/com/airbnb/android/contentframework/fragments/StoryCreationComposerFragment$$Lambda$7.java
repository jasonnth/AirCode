package com.airbnb.android.contentframework.fragments;

import java.util.List;
import p032rx.functions.Action1;

final /* synthetic */ class StoryCreationComposerFragment$$Lambda$7 implements Action1 {
    private final StoryCreationComposerFragment arg$1;

    private StoryCreationComposerFragment$$Lambda$7(StoryCreationComposerFragment storyCreationComposerFragment) {
        this.arg$1 = storyCreationComposerFragment;
    }

    public static Action1 lambdaFactory$(StoryCreationComposerFragment storyCreationComposerFragment) {
        return new StoryCreationComposerFragment$$Lambda$7(storyCreationComposerFragment);
    }

    public void call(Object obj) {
        this.arg$1.onSelectedPhotosProcessed((List) obj);
    }
}
