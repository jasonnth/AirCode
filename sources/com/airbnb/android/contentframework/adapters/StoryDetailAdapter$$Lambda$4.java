package com.airbnb.android.contentframework.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StoryDetailAdapter$$Lambda$4 implements OnClickListener {
    private final StoryDetailAdapter arg$1;
    private final int arg$2;

    private StoryDetailAdapter$$Lambda$4(StoryDetailAdapter storyDetailAdapter, int i) {
        this.arg$1 = storyDetailAdapter;
        this.arg$2 = i;
    }

    public static OnClickListener lambdaFactory$(StoryDetailAdapter storyDetailAdapter, int i) {
        return new StoryDetailAdapter$$Lambda$4(storyDetailAdapter, i);
    }

    public void onClick(View view) {
        this.arg$1.commentActionController.onShowAllComments(this.arg$2);
    }
}
