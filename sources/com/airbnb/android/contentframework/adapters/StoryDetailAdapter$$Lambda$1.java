package com.airbnb.android.contentframework.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StoryDetailAdapter$$Lambda$1 implements OnClickListener {
    private final StoryDetailAdapter arg$1;

    private StoryDetailAdapter$$Lambda$1(StoryDetailAdapter storyDetailAdapter) {
        this.arg$1 = storyDetailAdapter;
    }

    public static OnClickListener lambdaFactory$(StoryDetailAdapter storyDetailAdapter) {
        return new StoryDetailAdapter$$Lambda$1(storyDetailAdapter);
    }

    public void onClick(View view) {
        this.arg$1.delegate.onAuthorRowClicked();
    }
}
