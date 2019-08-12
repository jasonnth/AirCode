package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Article;

final /* synthetic */ class UserProfileAdapter$StoriesAdapterCNOnly$$Lambda$1 implements OnClickListener {
    private final StoriesAdapterCNOnly arg$1;
    private final Article arg$2;

    private UserProfileAdapter$StoriesAdapterCNOnly$$Lambda$1(StoriesAdapterCNOnly storiesAdapterCNOnly, Article article) {
        this.arg$1 = storiesAdapterCNOnly;
        this.arg$2 = article;
    }

    public static OnClickListener lambdaFactory$(StoriesAdapterCNOnly storiesAdapterCNOnly, Article article) {
        return new UserProfileAdapter$StoriesAdapterCNOnly$$Lambda$1(storiesAdapterCNOnly, article);
    }

    public void onClick(View view) {
        this.arg$1.clickListener.onStoryClicked(this.arg$2);
    }
}
