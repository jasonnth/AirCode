package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ArticleImageEpoxyModel$$Lambda$1 implements OnClickListener {
    private final ArticleImageEpoxyModel arg$1;

    private ArticleImageEpoxyModel$$Lambda$1(ArticleImageEpoxyModel articleImageEpoxyModel) {
        this.arg$1 = articleImageEpoxyModel;
    }

    public static OnClickListener lambdaFactory$(ArticleImageEpoxyModel articleImageEpoxyModel) {
        return new ArticleImageEpoxyModel$$Lambda$1(articleImageEpoxyModel);
    }

    public void onClick(View view) {
        this.arg$1.callback.onImageClick(this.arg$1.details, view, this.arg$1.photoIdx);
    }
}
