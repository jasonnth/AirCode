package com.airbnb.android.contentframework.imagepicker;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StoryCreationImagePreviewActivity$$Lambda$1 implements OnClickListener {
    private final StoryCreationImagePreviewActivity arg$1;

    private StoryCreationImagePreviewActivity$$Lambda$1(StoryCreationImagePreviewActivity storyCreationImagePreviewActivity) {
        this.arg$1 = storyCreationImagePreviewActivity;
    }

    public static OnClickListener lambdaFactory$(StoryCreationImagePreviewActivity storyCreationImagePreviewActivity) {
        return new StoryCreationImagePreviewActivity$$Lambda$1(storyCreationImagePreviewActivity);
    }

    public void onClick(View view) {
        this.arg$1.supportFinishAfterTransition();
    }
}
