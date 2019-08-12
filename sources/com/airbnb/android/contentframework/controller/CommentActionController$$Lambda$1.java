package com.airbnb.android.contentframework.controller;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.airbnb.android.core.models.ArticleComment;

final /* synthetic */ class CommentActionController$$Lambda$1 implements OnClickListener {
    private final CommentActionController arg$1;
    private final boolean arg$2;
    private final ArticleComment arg$3;

    private CommentActionController$$Lambda$1(CommentActionController commentActionController, boolean z, ArticleComment articleComment) {
        this.arg$1 = commentActionController;
        this.arg$2 = z;
        this.arg$3 = articleComment;
    }

    public static OnClickListener lambdaFactory$(CommentActionController commentActionController, boolean z, ArticleComment articleComment) {
        return new CommentActionController$$Lambda$1(commentActionController, z, articleComment);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        CommentActionController.lambda$onShowMenuForComment$0(this.arg$1, this.arg$2, this.arg$3, dialogInterface, i);
    }
}
