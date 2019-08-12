package com.airbnb.android.contentframework.controller;

import com.airbnb.android.contentframework.data.StoryCreationImage;
import p032rx.functions.Func1;

final /* synthetic */ class StoryPublishController$$Lambda$3 implements Func1 {
    private static final StoryPublishController$$Lambda$3 instance = new StoryPublishController$$Lambda$3();

    private StoryPublishController$$Lambda$3() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return StoryPublishController.lambda$processAndUploadPhotos$2((StoryCreationImage) obj);
    }
}
