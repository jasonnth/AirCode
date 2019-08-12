package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class DocumentMarqueeEpoxyModel_ extends DocumentMarqueeEpoxyModel implements GeneratedModel<DocumentMarquee> {
    private OnModelBoundListener<DocumentMarqueeEpoxyModel_, DocumentMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<DocumentMarqueeEpoxyModel_, DocumentMarquee> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, DocumentMarquee object, int position) {
        if (this.linkClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.linkClickListener).bind(holder, object);
        }
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(DocumentMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public DocumentMarqueeEpoxyModel_ onBind(OnModelBoundListener<DocumentMarqueeEpoxyModel_, DocumentMarquee> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(DocumentMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public DocumentMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<DocumentMarqueeEpoxyModel_, DocumentMarquee> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public DocumentMarqueeEpoxyModel_ titleText(CharSequence titleText) {
        onMutation();
        this.titleText = titleText;
        super.titleText(titleText);
        return this;
    }

    public CharSequence titleText() {
        return this.titleText;
    }

    public DocumentMarqueeEpoxyModel_ captionText(CharSequence captionText) {
        onMutation();
        this.captionText = captionText;
        super.captionText(captionText);
        return this;
    }

    public CharSequence captionText() {
        return this.captionText;
    }

    public DocumentMarqueeEpoxyModel_ linkText(CharSequence linkText) {
        onMutation();
        this.linkText = linkText;
        return this;
    }

    public CharSequence linkText() {
        return this.linkText;
    }

    public DocumentMarqueeEpoxyModel_ userImageUrl(String userImageUrl) {
        onMutation();
        this.userImageUrl = userImageUrl;
        return this;
    }

    public String userImageUrl() {
        return this.userImageUrl;
    }

    public DocumentMarqueeEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public DocumentMarqueeEpoxyModel_ captionRes(int captionRes) {
        onMutation();
        this.captionRes = captionRes;
        return this;
    }

    public int captionRes() {
        return this.captionRes;
    }

    public DocumentMarqueeEpoxyModel_ linkRes(int linkRes) {
        onMutation();
        this.linkRes = linkRes;
        return this;
    }

    public int linkRes() {
        return this.linkRes;
    }

    public DocumentMarqueeEpoxyModel_ linkClickListener(OnModelClickListener<DocumentMarqueeEpoxyModel_, DocumentMarquee> linkClickListener) {
        onMutation();
        if (linkClickListener == null) {
            this.linkClickListener = null;
        } else {
            this.linkClickListener = new WrappedEpoxyModelClickListener(this, linkClickListener);
        }
        return this;
    }

    public DocumentMarqueeEpoxyModel_ linkClickListener(OnClickListener linkClickListener) {
        onMutation();
        this.linkClickListener = linkClickListener;
        return this;
    }

    public OnClickListener linkClickListener() {
        return this.linkClickListener;
    }

    public DocumentMarqueeEpoxyModel_ clickListener(OnModelClickListener<DocumentMarqueeEpoxyModel_, DocumentMarquee> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public DocumentMarqueeEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public DocumentMarqueeEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public DocumentMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public DocumentMarqueeEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public DocumentMarqueeEpoxyModel_ m4534id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public DocumentMarqueeEpoxyModel_ m4539id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public DocumentMarqueeEpoxyModel_ m4535id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public DocumentMarqueeEpoxyModel_ m4536id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public DocumentMarqueeEpoxyModel_ m4538id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public DocumentMarqueeEpoxyModel_ m4537id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public DocumentMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public DocumentMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public DocumentMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public DocumentMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public DocumentMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_document_marquee;
    }

    public DocumentMarqueeEpoxyModel_ withNoTopPaddingLayout() {
        layout(C0716R.layout.n2_view_holder_document_marquee_no_top_padding);
        return this;
    }

    public DocumentMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.titleText = null;
        this.captionText = null;
        this.linkText = null;
        this.userImageUrl = null;
        this.titleRes = 0;
        this.captionRes = 0;
        this.linkRes = 0;
        this.linkClickListener = null;
        this.clickListener = null;
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
        if (!(o instanceof DocumentMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        DocumentMarqueeEpoxyModel_ that = (DocumentMarqueeEpoxyModel_) o;
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
        if (this.linkText != null) {
            if (!this.linkText.equals(that.linkText)) {
                return false;
            }
        } else if (that.linkText != null) {
            return false;
        }
        if (this.userImageUrl != null) {
            if (!this.userImageUrl.equals(that.userImageUrl)) {
                return false;
            }
        } else if (that.userImageUrl != null) {
            return false;
        }
        if (this.titleRes != that.titleRes || this.captionRes != that.captionRes || this.linkRes != that.linkRes) {
            return false;
        }
        if ((this.linkClickListener == null) != (that.linkClickListener == null)) {
            return false;
        }
        if (this.clickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.clickListener == null)) {
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
        int i6;
        int i7;
        int i8 = 1;
        int i9 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i10 = (hashCode + i) * 31;
        if (this.titleText != null) {
            i2 = this.titleText.hashCode();
        } else {
            i2 = 0;
        }
        int i11 = (i10 + i2) * 31;
        if (this.captionText != null) {
            i3 = this.captionText.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (i11 + i3) * 31;
        if (this.linkText != null) {
            i4 = this.linkText.hashCode();
        } else {
            i4 = 0;
        }
        int i13 = (i12 + i4) * 31;
        if (this.userImageUrl != null) {
            i5 = this.userImageUrl.hashCode();
        } else {
            i5 = 0;
        }
        int i14 = (((((((i13 + i5) * 31) + this.titleRes) * 31) + this.captionRes) * 31) + this.linkRes) * 31;
        if (this.linkClickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.clickListener == null) {
            i8 = 0;
        }
        int i16 = (i15 + i8) * 31;
        if (this.showDivider != null) {
            i7 = this.showDivider.hashCode();
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (this.numCarouselItemsShown != null) {
            i9 = this.numCarouselItemsShown.hashCode();
        }
        return i17 + i9;
    }

    public String toString() {
        return "DocumentMarqueeEpoxyModel_{titleText=" + this.titleText + ", captionText=" + this.captionText + ", linkText=" + this.linkText + ", userImageUrl=" + this.userImageUrl + ", titleRes=" + this.titleRes + ", captionRes=" + this.captionRes + ", linkRes=" + this.linkRes + ", linkClickListener=" + this.linkClickListener + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
