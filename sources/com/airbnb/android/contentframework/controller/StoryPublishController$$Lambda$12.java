package com.airbnb.android.contentframework.controller;

import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics.PublishErrorType;
import com.airbnb.android.contentframework.data.StoryPublishArguments;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class StoryPublishController$$Lambda$12 implements Action1 {
    private final StoryPublishController arg$1;
    private final long arg$2;
    private final StoryPublishArguments arg$3;

    private StoryPublishController$$Lambda$12(StoryPublishController storyPublishController, long j, StoryPublishArguments storyPublishArguments) {
        this.arg$1 = storyPublishController;
        this.arg$2 = j;
        this.arg$3 = storyPublishArguments;
    }

    public static Action1 lambdaFactory$(StoryPublishController storyPublishController, long j, StoryPublishArguments storyPublishArguments) {
        return new StoryPublishController$$Lambda$12(storyPublishController, j, storyPublishArguments);
    }

    public void call(Object obj) {
        this.arg$1.recoverStoryInComposer(this.arg$2, this.arg$3, NetworkUtil.getErrorMessage(this.arg$1.context, (NetworkException) ((Throwable) obj)), PublishErrorType.EditStoryError);
    }
}
