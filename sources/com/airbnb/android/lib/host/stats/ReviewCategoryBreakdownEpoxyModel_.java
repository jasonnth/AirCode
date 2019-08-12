package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.HostRatingBreakdown;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ReviewCategoryBreakdownEpoxyModel_ extends ReviewCategoryBreakdownEpoxyModel implements GeneratedModel<ReviewCategoryBreakdownView> {
    private OnModelBoundListener<ReviewCategoryBreakdownEpoxyModel_, ReviewCategoryBreakdownView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ReviewCategoryBreakdownEpoxyModel_, ReviewCategoryBreakdownView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ReviewCategoryBreakdownView object, int position) {
    }

    public void handlePostBind(ReviewCategoryBreakdownView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ReviewCategoryBreakdownEpoxyModel_ onBind(OnModelBoundListener<ReviewCategoryBreakdownEpoxyModel_, ReviewCategoryBreakdownView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ReviewCategoryBreakdownView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ReviewCategoryBreakdownEpoxyModel_ onUnbind(OnModelUnboundListener<ReviewCategoryBreakdownEpoxyModel_, ReviewCategoryBreakdownView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ReviewCategoryBreakdownEpoxyModel_ ratingBreakdown(HostRatingBreakdown ratingBreakdown) {
        onMutation();
        this.ratingBreakdown = ratingBreakdown;
        return this;
    }

    public HostRatingBreakdown ratingBreakdown() {
        return this.ratingBreakdown;
    }

    public ReviewCategoryBreakdownEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ReviewCategoryBreakdownEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ReviewCategoryBreakdownEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ReviewCategoryBreakdownEpoxyModel_ m6047id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ReviewCategoryBreakdownEpoxyModel_ m6052id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ReviewCategoryBreakdownEpoxyModel_ m6048id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ReviewCategoryBreakdownEpoxyModel_ m6049id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ReviewCategoryBreakdownEpoxyModel_ m6051id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ReviewCategoryBreakdownEpoxyModel_ m6050id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ReviewCategoryBreakdownEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ReviewCategoryBreakdownEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ReviewCategoryBreakdownEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ReviewCategoryBreakdownEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ReviewCategoryBreakdownEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.view_holder_review_category_breakdown_view;
    }

    public ReviewCategoryBreakdownEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.ratingBreakdown = null;
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
        if (!(o instanceof ReviewCategoryBreakdownEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ReviewCategoryBreakdownEpoxyModel_ that = (ReviewCategoryBreakdownEpoxyModel_) o;
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
        if (this.ratingBreakdown != null) {
            if (!this.ratingBreakdown.equals(that.ratingBreakdown)) {
                return false;
            }
        } else if (that.ratingBreakdown != null) {
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
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i3 = 0;
        }
        int i5 = (hashCode + i3) * 31;
        if (this.ratingBreakdown != null) {
            i = this.ratingBreakdown.hashCode();
        } else {
            i = 0;
        }
        int i6 = (i5 + i) * 31;
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
        return "ReviewCategoryBreakdownEpoxyModel_{ratingBreakdown=" + this.ratingBreakdown + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
