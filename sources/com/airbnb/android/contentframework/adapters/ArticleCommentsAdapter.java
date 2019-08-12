package com.airbnb.android.contentframework.adapters;

import android.content.Context;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.CommentActionListener;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.ArticleCommentRowEpoxyModel;
import com.airbnb.android.contentframework.viewcomponents.viewmodels.ArticleCommentRowEpoxyModel_;
import com.airbnb.android.core.models.ArticleComment;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.ListSpacerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyViewHolder;
import java.util.List;

public final class ArticleCommentsAdapter extends AirEpoxyAdapter {
    private final CommentActionListener commentActionListener;
    private final ListSpacerEpoxyModel_ commentSectionStartPadding;
    private final StandardRowEpoxyModel_ commentsSummary;
    private final Context context;
    private final CommentsLoader loader;
    private final LoadingRowEpoxyModel loadingRow = new LoadingRowEpoxyModel_();
    private int totalComments;

    public interface CommentsLoader {
        void onLoadNextPage();
    }

    public ArticleCommentsAdapter(Context context2, CommentsLoader loader2, CommentActionListener commentActionListener2, int totalComments2) {
        this.context = context2;
        this.totalComments = totalComments2;
        this.loader = loader2;
        this.commentActionListener = commentActionListener2;
        this.models.add(new ToolbarSpacerEpoxyModel_());
        this.commentsSummary = new StandardRowEpoxyModel_().title((CharSequence) context2.getResources().getQuantityString(C5709R.plurals.comments, totalComments2, new Object[]{Integer.valueOf(totalComments2)})).actionText(C5709R.string.content_framework_add_a_comment).clickListener(ArticleCommentsAdapter$$Lambda$1.lambdaFactory$(commentActionListener2)).showDivider(true);
        this.models.add(this.commentsSummary);
        this.commentSectionStartPadding = new ListSpacerEpoxyModel_().spaceHeight(context2.getResources().getDimensionPixelSize(C5709R.dimen.n2_vertical_padding_medium));
        this.models.add(this.commentSectionStartPadding);
        this.models.add(this.loadingRow);
    }

    public void addComments(List<ArticleComment> comments, int totalComments2, boolean hasMore) {
        this.loadingRow.hide();
        if (totalComments2 != this.totalComments) {
            this.totalComments = totalComments2;
            refreshSummary();
        }
        for (ArticleComment comment : comments) {
            insertModelBefore(new ArticleCommentRowEpoxyModel_().comment(comment).commentActionListener(this.commentActionListener).m4080id(comment.getId()), this.loadingRow);
        }
        if (hasMore) {
            this.loadingRow.show();
        }
        notifyModelChanged(this.loadingRow);
    }

    public void notifyCommentChange(ArticleCommentRowEpoxyModel model) {
        notifyModelChanged(model);
    }

    public void addToFirstComment(ArticleComment comment) {
        this.totalComments++;
        refreshSummary();
        insertModelAfter(new ArticleCommentRowEpoxyModel_().comment(comment).commentActionListener(this.commentActionListener).m4080id(comment.getId()), this.commentSectionStartPadding);
    }

    public void deleteCommentWithId(long commentIdToBeDeleted) {
        for (EpoxyModel<?> model : this.models) {
            if ((model instanceof ArticleCommentRowEpoxyModel) && model.mo11715id() == commentIdToBeDeleted) {
                removeModel(model);
                this.totalComments--;
                refreshSummary();
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onModelBound(EpoxyViewHolder holder, EpoxyModel<?> model, int position) {
        if (model == this.loadingRow && this.loadingRow.isShown()) {
            this.loader.onLoadNextPage();
        }
    }

    private void refreshSummary() {
        this.commentsSummary.title((CharSequence) this.context.getResources().getQuantityString(C5709R.plurals.comments, this.totalComments, new Object[]{Integer.valueOf(this.totalComments)}));
        notifyModelChanged(this.commentsSummary);
    }
}
