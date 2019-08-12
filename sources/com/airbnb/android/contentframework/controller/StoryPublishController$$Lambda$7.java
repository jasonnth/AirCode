package com.airbnb.android.contentframework.controller;

import com.airbnb.android.contentframework.requests.StoryCreationImageUploadRequest;
import p032rx.functions.Func1;

final /* synthetic */ class StoryPublishController$$Lambda$7 implements Func1 {
    private final StoryPublishController arg$1;
    private final long arg$2;

    private StoryPublishController$$Lambda$7(StoryPublishController storyPublishController, long j) {
        this.arg$1 = storyPublishController;
        this.arg$2 = j;
    }

    public static Func1 lambdaFactory$(StoryPublishController storyPublishController, long j) {
        return new StoryPublishController$$Lambda$7(storyPublishController, j);
    }

    public Object call(Object obj) {
        return this.arg$1.requestManager.adapt(new StoryCreationImageUploadRequest(this.arg$2, (String) obj));
    }
}
