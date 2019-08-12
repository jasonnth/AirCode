package com.airbnb.android.contentframework.controller;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.android.contentframework.data.StoryPublishArguments;
import p032rx.functions.Action1;

final /* synthetic */ class StoryPublishController$$Lambda$11 implements Action1 {
    private final StoryPublishController arg$1;
    private final StoryPublishArguments arg$2;

    private StoryPublishController$$Lambda$11(StoryPublishController storyPublishController, StoryPublishArguments storyPublishArguments) {
        this.arg$1 = storyPublishController;
        this.arg$2 = storyPublishArguments;
    }

    public static Action1 lambdaFactory$(StoryPublishController storyPublishController, StoryPublishArguments storyPublishArguments) {
        return new StoryPublishController$$Lambda$11(storyPublishController, storyPublishArguments);
    }

    public void call(Object obj) {
        StoryPublishController.lambda$editStory$10(this.arg$1, this.arg$2, (AirResponse) obj);
    }
}
