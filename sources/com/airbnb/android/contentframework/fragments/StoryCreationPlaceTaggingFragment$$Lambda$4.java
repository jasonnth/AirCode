package com.airbnb.android.contentframework.fragments;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class StoryCreationPlaceTaggingFragment$$Lambda$4 implements OnEditorActionListener {
    private final StoryCreationPlaceTaggingFragment arg$1;

    private StoryCreationPlaceTaggingFragment$$Lambda$4(StoryCreationPlaceTaggingFragment storyCreationPlaceTaggingFragment) {
        this.arg$1 = storyCreationPlaceTaggingFragment;
    }

    public static OnEditorActionListener lambdaFactory$(StoryCreationPlaceTaggingFragment storyCreationPlaceTaggingFragment) {
        return new StoryCreationPlaceTaggingFragment$$Lambda$4(storyCreationPlaceTaggingFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return StoryCreationPlaceTaggingFragment.lambda$setupInputMarquee$3(this.arg$1, textView, i, keyEvent);
    }
}
