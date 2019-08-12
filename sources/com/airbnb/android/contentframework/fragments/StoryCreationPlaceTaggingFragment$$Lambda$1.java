package com.airbnb.android.contentframework.fragments;

final /* synthetic */ class StoryCreationPlaceTaggingFragment$$Lambda$1 implements Runnable {
    private final StoryCreationPlaceTaggingFragment arg$1;

    private StoryCreationPlaceTaggingFragment$$Lambda$1(StoryCreationPlaceTaggingFragment storyCreationPlaceTaggingFragment) {
        this.arg$1 = storyCreationPlaceTaggingFragment;
    }

    public static Runnable lambdaFactory$(StoryCreationPlaceTaggingFragment storyCreationPlaceTaggingFragment) {
        return new StoryCreationPlaceTaggingFragment$$Lambda$1(storyCreationPlaceTaggingFragment);
    }

    public void run() {
        this.arg$1.onSearch(this.arg$1.getSearchText());
    }
}
