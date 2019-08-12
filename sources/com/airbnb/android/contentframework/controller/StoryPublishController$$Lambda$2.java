package com.airbnb.android.contentframework.controller;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics.PublishErrorType;
import com.airbnb.android.contentframework.data.StoryPublishArguments;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class StoryPublishController$$Lambda$2 implements Action1 {
    private final StoryPublishController arg$1;
    private final StoryPublishArguments arg$2;

    private StoryPublishController$$Lambda$2(StoryPublishController storyPublishController, StoryPublishArguments storyPublishArguments) {
        this.arg$1 = storyPublishController;
        this.arg$2 = storyPublishArguments;
    }

    public static Action1 lambdaFactory$(StoryPublishController storyPublishController, StoryPublishArguments storyPublishArguments) {
        return new StoryPublishController$$Lambda$2(storyPublishController, storyPublishArguments);
    }

    public void call(Object obj) {
        this.arg$1.recoverStoryInComposer(this.arg$2, NetworkUtil.getErrorMessage(this.arg$1.context, (AirRequestNetworkException) ((Throwable) obj)), PublishErrorType.CreateStoryError);
    }
}
