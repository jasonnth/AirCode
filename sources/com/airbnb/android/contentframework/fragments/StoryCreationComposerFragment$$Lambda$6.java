package com.airbnb.android.contentframework.fragments;

import android.content.Intent;
import com.airbnb.android.contentframework.imagepicker.StoryCreationImagePickerFragment;
import java.util.concurrent.Callable;

final /* synthetic */ class StoryCreationComposerFragment$$Lambda$6 implements Callable {
    private final StoryCreationComposerFragment arg$1;
    private final Intent arg$2;

    private StoryCreationComposerFragment$$Lambda$6(StoryCreationComposerFragment storyCreationComposerFragment, Intent intent) {
        this.arg$1 = storyCreationComposerFragment;
        this.arg$2 = intent;
    }

    public static Callable lambdaFactory$(StoryCreationComposerFragment storyCreationComposerFragment, Intent intent) {
        return new StoryCreationComposerFragment$$Lambda$6(storyCreationComposerFragment, intent);
    }

    public Object call() {
        return this.arg$1.processSelectedImages(this.arg$2.getParcelableArrayListExtra(StoryCreationImagePickerFragment.EXTRA_RESULT_PHOTO_URIS));
    }
}
