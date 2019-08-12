package com.airbnb.android.contentframework.controller;

import com.airbnb.android.contentframework.data.StoryPublishArguments;
import p032rx.functions.Action0;

final /* synthetic */ class StoryPublishController$$Lambda$10 implements Action0 {
    private final StoryPublishController arg$1;
    private final long arg$2;
    private final StoryPublishArguments arg$3;

    private StoryPublishController$$Lambda$10(StoryPublishController storyPublishController, long j, StoryPublishArguments storyPublishArguments) {
        this.arg$1 = storyPublishController;
        this.arg$2 = j;
        this.arg$3 = storyPublishArguments;
    }

    public static Action0 lambdaFactory$(StoryPublishController storyPublishController, long j, StoryPublishArguments storyPublishArguments) {
        return new StoryPublishController$$Lambda$10(storyPublishController, j, storyPublishArguments);
    }

    public void call() {
        this.arg$1.editStory(this.arg$2, this.arg$3);
    }
}
