package com.airbnb.android.contentframework.fragments;

import com.airbnb.android.contentframework.responses.GetArticleCommentResponse;
import p032rx.functions.Action1;

final /* synthetic */ class StoryDetailViewFragment$$Lambda$11 implements Action1 {
    private final StoryDetailViewFragment arg$1;

    private StoryDetailViewFragment$$Lambda$11(StoryDetailViewFragment storyDetailViewFragment) {
        this.arg$1 = storyDetailViewFragment;
    }

    public static Action1 lambdaFactory$(StoryDetailViewFragment storyDetailViewFragment) {
        return new StoryDetailViewFragment$$Lambda$11(storyDetailViewFragment);
    }

    public void call(Object obj) {
        StoryDetailViewFragment.lambda$new$10(this.arg$1, (GetArticleCommentResponse) obj);
    }
}
