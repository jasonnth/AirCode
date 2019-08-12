package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.CommentActionListener;
import com.airbnb.android.contentframework.views.ArticleCommentRow;
import com.airbnb.android.core.models.ArticleComment;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ArticleCommentRowEpoxyModel_ extends ArticleCommentRowEpoxyModel implements GeneratedModel<ArticleCommentRow> {
    private OnModelBoundListener<ArticleCommentRowEpoxyModel_, ArticleCommentRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ArticleCommentRowEpoxyModel_, ArticleCommentRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ArticleCommentRow object, int position) {
    }

    public void handlePostBind(ArticleCommentRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ArticleCommentRowEpoxyModel_ onBind(OnModelBoundListener<ArticleCommentRowEpoxyModel_, ArticleCommentRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ArticleCommentRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ArticleCommentRowEpoxyModel_ onUnbind(OnModelUnboundListener<ArticleCommentRowEpoxyModel_, ArticleCommentRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ArticleCommentRowEpoxyModel_ comment(ArticleComment comment) {
        onMutation();
        this.comment = comment;
        return this;
    }

    public ArticleComment comment() {
        return this.comment;
    }

    public ArticleCommentRowEpoxyModel_ commentActionListener(CommentActionListener commentActionListener) {
        onMutation();
        this.commentActionListener = commentActionListener;
        return this;
    }

    public CommentActionListener commentActionListener() {
        return this.commentActionListener;
    }

    public ArticleCommentRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ArticleCommentRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ArticleCommentRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ArticleCommentRowEpoxyModel_ m4080id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ArticleCommentRowEpoxyModel_ m4085id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ArticleCommentRowEpoxyModel_ m4081id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ArticleCommentRowEpoxyModel_ m4082id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ArticleCommentRowEpoxyModel_ m4084id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ArticleCommentRowEpoxyModel_ m4083id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ArticleCommentRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ArticleCommentRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ArticleCommentRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ArticleCommentRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ArticleCommentRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5709R.layout.view_holder_article_comment_row;
    }

    public ArticleCommentRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.comment = null;
        this.commentActionListener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArticleCommentRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ArticleCommentRowEpoxyModel_ that = (ArticleCommentRowEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.comment != null) {
            if (!this.comment.equals(that.comment)) {
                return false;
            }
        } else if (that.comment != null) {
            return false;
        }
        if (this.commentActionListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.commentActionListener == null)) {
            return false;
        }
        if (this.showDivider != null) {
            if (!this.showDivider.equals(that.showDivider)) {
                return false;
            }
        } else if (that.showDivider != null) {
            return false;
        }
        if (this.numCarouselItemsShown != null) {
            if (!this.numCarouselItemsShown.equals(that.numCarouselItemsShown)) {
                return false;
            }
        } else if (that.numCarouselItemsShown != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4 = 1;
        int i5 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i6 = (hashCode + i) * 31;
        if (this.comment != null) {
            i2 = this.comment.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.commentActionListener == null) {
            i4 = 0;
        }
        int i8 = (i7 + i4) * 31;
        if (this.showDivider != null) {
            i3 = this.showDivider.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.numCarouselItemsShown != null) {
            i5 = this.numCarouselItemsShown.hashCode();
        }
        return i9 + i5;
    }

    public String toString() {
        return "ArticleCommentRowEpoxyModel_{comment=" + this.comment + ", commentActionListener=" + this.commentActionListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
