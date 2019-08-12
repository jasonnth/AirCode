package com.airbnb.android.contentframework.controller;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.android.contentframework.responses.StoryCreationImageUploadResponse;
import java.util.List;
import p032rx.functions.Action1;

final /* synthetic */ class StoryPublishController$$Lambda$8 implements Action1 {
    private final StoryPublishController arg$1;
    private final long arg$2;

    private StoryPublishController$$Lambda$8(StoryPublishController storyPublishController, long j) {
        this.arg$1 = storyPublishController;
        this.arg$2 = j;
    }

    public static Action1 lambdaFactory$(StoryPublishController storyPublishController, long j) {
        return new StoryPublishController$$Lambda$8(storyPublishController, j);
    }

    public void call(Object obj) {
        ((List) this.arg$1.articleToImageNames.get(Long.valueOf(this.arg$2))).add(((StoryCreationImageUploadResponse) ((AirResponse) obj).body()).photoResponse.getImageName());
    }
}
