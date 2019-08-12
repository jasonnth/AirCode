package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.ArticleDocumentMarquee;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ArticleDocumentMarqueeEpoxyModel_ extends ArticleDocumentMarqueeEpoxyModel implements GeneratedModel<ArticleDocumentMarquee> {
    private OnModelBoundListener<ArticleDocumentMarqueeEpoxyModel_, ArticleDocumentMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ArticleDocumentMarqueeEpoxyModel_, ArticleDocumentMarquee> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ArticleDocumentMarquee object, int position) {
    }

    public void handlePostBind(ArticleDocumentMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ArticleDocumentMarqueeEpoxyModel_ onBind(OnModelBoundListener<ArticleDocumentMarqueeEpoxyModel_, ArticleDocumentMarquee> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ArticleDocumentMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ArticleDocumentMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<ArticleDocumentMarqueeEpoxyModel_, ArticleDocumentMarquee> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ArticleDocumentMarqueeEpoxyModel_ titleText(CharSequence titleText) {
        onMutation();
        this.titleText = titleText;
        super.titleText(titleText);
        return this;
    }

    public CharSequence titleText() {
        return this.titleText;
    }

    public ArticleDocumentMarqueeEpoxyModel_ captionText(CharSequence captionText) {
        onMutation();
        this.captionText = captionText;
        super.captionText(captionText);
        return this;
    }

    public CharSequence captionText() {
        return this.captionText;
    }

    public ArticleDocumentMarqueeEpoxyModel_ kickerText(CharSequence kickerText) {
        onMutation();
        this.kickerText = kickerText;
        super.kickerText(kickerText);
        return this;
    }

    public CharSequence kickerText() {
        return this.kickerText;
    }

    public ArticleDocumentMarqueeEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public ArticleDocumentMarqueeEpoxyModel_ captionRes(int captionRes) {
        onMutation();
        this.captionRes = captionRes;
        return this;
    }

    public int captionRes() {
        return this.captionRes;
    }

    public ArticleDocumentMarqueeEpoxyModel_ kickerRes(int kickerRes) {
        onMutation();
        this.kickerRes = kickerRes;
        return this;
    }

    public int kickerRes() {
        return this.kickerRes;
    }

    public ArticleDocumentMarqueeEpoxyModel_ withTopPadding(boolean withTopPadding) {
        onMutation();
        this.withTopPadding = withTopPadding;
        return this;
    }

    public boolean withTopPadding() {
        return this.withTopPadding;
    }

    public ArticleDocumentMarqueeEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ArticleDocumentMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ArticleDocumentMarqueeEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ArticleDocumentMarqueeEpoxyModel_ m4303id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ArticleDocumentMarqueeEpoxyModel_ m4308id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ArticleDocumentMarqueeEpoxyModel_ m4304id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ArticleDocumentMarqueeEpoxyModel_ m4305id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ArticleDocumentMarqueeEpoxyModel_ m4307id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ArticleDocumentMarqueeEpoxyModel_ m4306id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ArticleDocumentMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ArticleDocumentMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ArticleDocumentMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ArticleDocumentMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ArticleDocumentMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public ArticleDocumentMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.titleText = null;
        this.captionText = null;
        this.kickerText = null;
        this.titleRes = 0;
        this.captionRes = 0;
        this.kickerRes = 0;
        this.withTopPadding = false;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArticleDocumentMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ArticleDocumentMarqueeEpoxyModel_ that = (ArticleDocumentMarqueeEpoxyModel_) o;
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
        if (this.titleText != null) {
            if (!this.titleText.equals(that.titleText)) {
                return false;
            }
        } else if (that.titleText != null) {
            return false;
        }
        if (this.captionText != null) {
            if (!this.captionText.equals(that.captionText)) {
                return false;
            }
        } else if (that.captionText != null) {
            return false;
        }
        if (this.kickerText != null) {
            if (!this.kickerText.equals(that.kickerText)) {
                return false;
            }
        } else if (that.kickerText != null) {
            return false;
        }
        if (this.titleRes != that.titleRes || this.captionRes != that.captionRes || this.kickerRes != that.kickerRes || this.withTopPadding != that.withTopPadding) {
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
        int i4;
        int i5;
        int i6 = 1;
        int i7 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i8 = (hashCode + i) * 31;
        if (this.titleText != null) {
            i2 = this.titleText.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.captionText != null) {
            i3 = this.captionText.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.kickerText != null) {
            i4 = this.kickerText.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (((((((i10 + i4) * 31) + this.titleRes) * 31) + this.captionRes) * 31) + this.kickerRes) * 31;
        if (!this.withTopPadding) {
            i6 = 0;
        }
        int i12 = (i11 + i6) * 31;
        if (this.showDivider != null) {
            i5 = this.showDivider.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.numCarouselItemsShown != null) {
            i7 = this.numCarouselItemsShown.hashCode();
        }
        return i13 + i7;
    }

    public String toString() {
        return "ArticleDocumentMarqueeEpoxyModel_{titleText=" + this.titleText + ", captionText=" + this.captionText + ", kickerText=" + this.kickerText + ", titleRes=" + this.titleRes + ", captionRes=" + this.captionRes + ", kickerRes=" + this.kickerRes + ", withTopPadding=" + this.withTopPadding + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
