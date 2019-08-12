package com.airbnb.android.contentframework.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.StoryCardFeedItemEpoxyModel_;
import com.airbnb.android.core.models.Article;

final /* synthetic */ class StoryFeedAdapter$$Lambda$1 implements OnClickListener {
    private final StoryFeedAdapter arg$1;
    private final Article arg$2;
    private final StoryCardFeedItemEpoxyModel_ arg$3;

    private StoryFeedAdapter$$Lambda$1(StoryFeedAdapter storyFeedAdapter, Article article, StoryCardFeedItemEpoxyModel_ storyCardFeedItemEpoxyModel_) {
        this.arg$1 = storyFeedAdapter;
        this.arg$2 = article;
        this.arg$3 = storyCardFeedItemEpoxyModel_;
    }

    public static OnClickListener lambdaFactory$(StoryFeedAdapter storyFeedAdapter, Article article, StoryCardFeedItemEpoxyModel_ storyCardFeedItemEpoxyModel_) {
        return new StoryFeedAdapter$$Lambda$1(storyFeedAdapter, article, storyCardFeedItemEpoxyModel_);
    }

    public void onClick(View view) {
        this.arg$1.listener.onStoryClicked(this.arg$2, this.arg$1.models.indexOf(this.arg$3));
    }
}
