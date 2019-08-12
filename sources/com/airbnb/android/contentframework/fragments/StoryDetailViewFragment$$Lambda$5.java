package com.airbnb.android.contentframework.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class StoryDetailViewFragment$$Lambda$5 implements Action1 {
    private final StoryDetailViewFragment arg$1;

    private StoryDetailViewFragment$$Lambda$5(StoryDetailViewFragment storyDetailViewFragment) {
        this.arg$1 = storyDetailViewFragment;
    }

    public static Action1 lambdaFactory$(StoryDetailViewFragment storyDetailViewFragment) {
        return new StoryDetailViewFragment$$Lambda$5(storyDetailViewFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleArticleLikeUnlikeSuccessResult();
    }
}
