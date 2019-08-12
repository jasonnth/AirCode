package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class StoryProductLinkElementEpoxyModel$$Lambda$1 implements OnClickListener {
    private final StoryProductLinkElementEpoxyModel arg$1;

    private StoryProductLinkElementEpoxyModel$$Lambda$1(StoryProductLinkElementEpoxyModel storyProductLinkElementEpoxyModel) {
        this.arg$1 = storyProductLinkElementEpoxyModel;
    }

    public static OnClickListener lambdaFactory$(StoryProductLinkElementEpoxyModel storyProductLinkElementEpoxyModel) {
        return new StoryProductLinkElementEpoxyModel$$Lambda$1(storyProductLinkElementEpoxyModel);
    }

    public void onClick(View view) {
        StoryProductLinkElementEpoxyModel.lambda$bind$0(this.arg$1, view);
    }
}
