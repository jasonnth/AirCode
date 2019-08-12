package com.airbnb.android.contentframework.controller;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.android.contentframework.data.StoryPublishArguments;
import com.airbnb.android.contentframework.responses.StoryCreationPublishResponse;
import p032rx.functions.Action1;

final /* synthetic */ class StoryPublishController$$Lambda$1 implements Action1 {
    private final StoryPublishController arg$1;
    private final StoryPublishArguments arg$2;

    private StoryPublishController$$Lambda$1(StoryPublishController storyPublishController, StoryPublishArguments storyPublishArguments) {
        this.arg$1 = storyPublishController;
        this.arg$2 = storyPublishArguments;
    }

    public static Action1 lambdaFactory$(StoryPublishController storyPublishController, StoryPublishArguments storyPublishArguments) {
        return new StoryPublishController$$Lambda$1(storyPublishController, storyPublishArguments);
    }

    public void call(Object obj) {
        this.arg$1.processAndUploadPhotos(((StoryCreationPublishResponse) ((AirResponse) obj).body()).article.getId(), this.arg$2);
    }
}
