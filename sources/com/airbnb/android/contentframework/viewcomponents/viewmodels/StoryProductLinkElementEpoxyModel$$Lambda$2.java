package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StoryProductLinkElementEpoxyModel$$Lambda$2 implements OnClickListener {
    private final StoryProductLinkElementEpoxyModel arg$1;

    private StoryProductLinkElementEpoxyModel$$Lambda$2(StoryProductLinkElementEpoxyModel storyProductLinkElementEpoxyModel) {
        this.arg$1 = storyProductLinkElementEpoxyModel;
    }

    public static OnClickListener lambdaFactory$(StoryProductLinkElementEpoxyModel storyProductLinkElementEpoxyModel) {
        return new StoryProductLinkElementEpoxyModel$$Lambda$2(storyProductLinkElementEpoxyModel);
    }

    public void onClick(View view) {
        StoryProductLinkElementEpoxyModel.lambda$bind$1(this.arg$1, view);
    }
}