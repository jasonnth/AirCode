package com.airbnb.android.contentframework.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StoryDetailAdapter$$Lambda$6 implements OnClickListener {
    private final StoryDetailAdapter arg$1;

    private StoryDetailAdapter$$Lambda$6(StoryDetailAdapter storyDetailAdapter) {
        this.arg$1 = storyDetailAdapter;
    }

    public static OnClickListener lambdaFactory$(StoryDetailAdapter storyDetailAdapter) {
        return new StoryDetailAdapter$$Lambda$6(storyDetailAdapter);
    }

    public void onClick(View view) {
        StoryDetailAdapter.lambda$addRelatedArticles$5(this.arg$1, view);
    }
}
