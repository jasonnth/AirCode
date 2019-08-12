package com.airbnb.android.contentframework.fragments;

import android.support.p000v4.widget.SwipeRefreshLayout.OnRefreshListener;

final /* synthetic */ class StoryFeedFragment$$Lambda$1 implements OnRefreshListener {
    private final StoryFeedFragment arg$1;

    private StoryFeedFragment$$Lambda$1(StoryFeedFragment storyFeedFragment) {
        this.arg$1 = storyFeedFragment;
    }

    public static OnRefreshListener lambdaFactory$(StoryFeedFragment storyFeedFragment) {
        return new StoryFeedFragment$$Lambda$1(storyFeedFragment);
    }

    public void onRefresh() {
        StoryFeedFragment.lambda$onCreateView$0(this.arg$1);
    }
}
