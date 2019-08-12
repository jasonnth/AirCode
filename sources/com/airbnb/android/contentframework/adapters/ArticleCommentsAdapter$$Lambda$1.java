package com.airbnb.android.contentframework.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.contentframework.CommentActionListener;

final /* synthetic */ class ArticleCommentsAdapter$$Lambda$1 implements OnClickListener {
    private final CommentActionListener arg$1;

    private ArticleCommentsAdapter$$Lambda$1(CommentActionListener commentActionListener) {
        this.arg$1 = commentActionListener;
    }

    public static OnClickListener lambdaFactory$(CommentActionListener commentActionListener) {
        return new ArticleCommentsAdapter$$Lambda$1(commentActionListener);
    }

    public void onClick(View view) {
        this.arg$1.onAddComment();
    }
}
