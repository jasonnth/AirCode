package com.airbnb.android.contentframework.controller;

import com.airbnb.android.contentframework.data.StoryPublishArguments;
import java.util.List;
import p032rx.functions.Action0;

final /* synthetic */ class StoryPublishController$$Lambda$6 implements Action0 {
    private final StoryPublishController arg$1;
    private final long arg$2;
    private final StoryPublishArguments arg$3;
    private final List arg$4;

    private StoryPublishController$$Lambda$6(StoryPublishController storyPublishController, long j, StoryPublishArguments storyPublishArguments, List list) {
        this.arg$1 = storyPublishController;
        this.arg$2 = j;
        this.arg$3 = storyPublishArguments;
        this.arg$4 = list;
    }

    public static Action0 lambdaFactory$(StoryPublishController storyPublishController, long j, StoryPublishArguments storyPublishArguments, List list) {
        return new StoryPublishController$$Lambda$6(storyPublishController, j, storyPublishArguments, list);
    }

    public void call() {
        this.arg$1.uploadPhotos(this.arg$2, this.arg$3, this.arg$4);
    }
}
