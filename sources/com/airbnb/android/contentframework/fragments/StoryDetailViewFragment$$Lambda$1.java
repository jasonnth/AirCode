package com.airbnb.android.contentframework.fragments;

import com.airbnb.android.contentframework.responses.DeleteArticleResponse;
import p032rx.functions.Action1;

final /* synthetic */ class StoryDetailViewFragment$$Lambda$1 implements Action1 {
    private final StoryDetailViewFragment arg$1;

    private StoryDetailViewFragment$$Lambda$1(StoryDetailViewFragment storyDetailViewFragment) {
        this.arg$1 = storyDetailViewFragment;
    }

    public static Action1 lambdaFactory$(StoryDetailViewFragment storyDetailViewFragment) {
        return new StoryDetailViewFragment$$Lambda$1(storyDetailViewFragment);
    }

    public void call(Object obj) {
        StoryDetailViewFragment.lambda$new$0(this.arg$1, (DeleteArticleResponse) obj);
    }
}
