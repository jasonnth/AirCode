package com.airbnb.android.contentframework.views;

import com.airbnb.android.contentframework.data.StoryCreationImage;

final /* synthetic */ class StoryCreationImageView$$Lambda$1 implements Runnable {
    private final StoryCreationImageView arg$1;
    private final StoryCreationImage arg$2;

    private StoryCreationImageView$$Lambda$1(StoryCreationImageView storyCreationImageView, StoryCreationImage storyCreationImage) {
        this.arg$1 = storyCreationImageView;
        this.arg$2 = storyCreationImage;
    }

    public static Runnable lambdaFactory$(StoryCreationImageView storyCreationImageView, StoryCreationImage storyCreationImage) {
        return new StoryCreationImageView$$Lambda$1(storyCreationImageView, storyCreationImage);
    }

    public void run() {
        this.arg$1.loadImageFromUri(this.arg$2.uri());
    }
}
