package com.airbnb.android.contentframework.fragments;

final /* synthetic */ class StoryDetailViewFragment$2$$Lambda$1 implements Runnable {
    private final C57292 arg$1;

    private StoryDetailViewFragment$2$$Lambda$1(C57292 r1) {
        this.arg$1 = r1;
    }

    public static Runnable lambdaFactory$(C57292 r1) {
        return new StoryDetailViewFragment$2$$Lambda$1(r1);
    }

    public void run() {
        StoryDetailViewFragment.this.readPercentageHelper.handlePercentage(((double) StoryDetailViewFragment.this.layoutManager.findLastVisibleItemPosition()) / ((double) StoryDetailViewFragment.this.articleAdapter.getItemCount()), StoryDetailViewFragment.this.getElapsedTimeSinceImpression());
    }
}
