package com.airbnb.android.contentframework.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Article;

final /* synthetic */ class StoryDetailAdapter$$Lambda$5 implements OnClickListener {
    private final StoryDetailAdapter arg$1;
    private final Article arg$2;

    private StoryDetailAdapter$$Lambda$5(StoryDetailAdapter storyDetailAdapter, Article article) {
        this.arg$1 = storyDetailAdapter;
        this.arg$2 = article;
    }

    public static OnClickListener lambdaFactory$(StoryDetailAdapter storyDetailAdapter, Article article) {
        return new StoryDetailAdapter$$Lambda$5(storyDetailAdapter, article);
    }

    public void onClick(View view) {
        this.arg$1.delegate.onClickRelatedArticle(this.arg$2);
    }
}
