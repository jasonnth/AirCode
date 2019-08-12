package com.airbnb.android.contentframework.controller;

import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics.PublishErrorType;
import com.airbnb.android.contentframework.data.StoryPublishArguments;
import p032rx.functions.Action1;

final /* synthetic */ class StoryPublishController$$Lambda$5 implements Action1 {
    private final StoryPublishController arg$1;
    private final long arg$2;
    private final StoryPublishArguments arg$3;

    private StoryPublishController$$Lambda$5(StoryPublishController storyPublishController, long j, StoryPublishArguments storyPublishArguments) {
        this.arg$1 = storyPublishController;
        this.arg$2 = j;
        this.arg$3 = storyPublishArguments;
    }

    public static Action1 lambdaFactory$(StoryPublishController storyPublishController, long j, StoryPublishArguments storyPublishArguments) {
        return new StoryPublishController$$Lambda$5(storyPublishController, j, storyPublishArguments);
    }

    public void call(Object obj) {
        this.arg$1.recoverStoryInComposer(this.arg$2, this.arg$3, this.arg$1.context.getString(C5709R.string.story_creation_composer_photo_error), PublishErrorType.ProcessPhotoError);
    }
}