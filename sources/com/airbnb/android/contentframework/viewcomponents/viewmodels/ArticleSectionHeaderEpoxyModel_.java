package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import com.airbnb.android.contentframework.C5709R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.primitives.AirTextView;

public class ArticleSectionHeaderEpoxyModel_ extends ArticleSectionHeaderEpoxyModel implements GeneratedModel<AirTextView> {
    private OnModelBoundListener<ArticleSectionHeaderEpoxyModel_, AirTextView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ArticleSectionHeaderEpoxyModel_, AirTextView> onModelUnboundListener_epoxyGeneratedModel;

    public ArticleSectionHeaderEpoxyModel_(String text) {
        super(text);
    }

    public void handlePreBind(EpoxyViewHolder holder, AirTextView object, int position) {
    }

    public void handlePostBind(AirTextView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ArticleSectionHeaderEpoxyModel_ onBind(OnModelBoundListener<ArticleSectionHeaderEpoxyModel_, AirTextView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(AirTextView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ArticleSectionHeaderEpoxyModel_ onUnbind(OnModelUnboundListener<ArticleSectionHeaderEpoxyModel_, AirTextView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ArticleSectionHeaderEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ArticleSectionHeaderEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ArticleSectionHeaderEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ArticleSectionHeaderEpoxyModel_ m4104id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ArticleSectionHeaderEpoxyModel_ m4109id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ArticleSectionHeaderEpoxyModel_ m4105id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ArticleSectionHeaderEpoxyModel_ m4106id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ArticleSectionHeaderEpoxyModel_ m4108id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ArticleSectionHeaderEpoxyModel_ m4107id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ArticleSectionHeaderEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ArticleSectionHeaderEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ArticleSectionHeaderEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ArticleSectionHeaderEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ArticleSectionHeaderEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5709R.layout.view_holder_article_section_header;
    }

    public ArticleSectionHeaderEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
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
        if (!(o instanceof ArticleSectionHeaderEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ArticleSectionHeaderEpoxyModel_ that = (ArticleSectionHeaderEpoxyModel_) o;
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
        int i3 = 1;
        int i4 = 0;
        int hashCode = super.hashCode() * 31;
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i5 = (hashCode + i) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i3 = 0;
        }
        int i6 = (i5 + i3) * 31;
        if (this.showDivider != null) {
            i2 = this.showDivider.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.numCarouselItemsShown != null) {
            i4 = this.numCarouselItemsShown.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "ArticleSectionHeaderEpoxyModel_{showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
