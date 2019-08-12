package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.P3ReviewsRow;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class P3ReviewsRowEpoxyModel_ extends P3ReviewsRowEpoxyModel implements GeneratedModel<P3ReviewsRow> {
    private OnModelBoundListener<P3ReviewsRowEpoxyModel_, P3ReviewsRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<P3ReviewsRowEpoxyModel_, P3ReviewsRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, P3ReviewsRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(P3ReviewsRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public P3ReviewsRowEpoxyModel_ onBind(OnModelBoundListener<P3ReviewsRowEpoxyModel_, P3ReviewsRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(P3ReviewsRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public P3ReviewsRowEpoxyModel_ onUnbind(OnModelUnboundListener<P3ReviewsRowEpoxyModel_, P3ReviewsRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public P3ReviewsRowEpoxyModel_ reviewModels(List<HomeReviewRowEpoxyModel_> reviewModels) {
        onMutation();
        this.reviewModels = reviewModels;
        return this;
    }

    public List<HomeReviewRowEpoxyModel_> reviewModels() {
        return this.reviewModels;
    }

    public P3ReviewsRowEpoxyModel_ reviewsCount(int reviewsCount) {
        onMutation();
        this.reviewsCount = reviewsCount;
        return this;
    }

    public int reviewsCount() {
        return this.reviewsCount;
    }

    public P3ReviewsRowEpoxyModel_ starRating(float starRating) {
        onMutation();
        this.starRating = starRating;
        return this;
    }

    public float starRating() {
        return this.starRating;
    }

    public P3ReviewsRowEpoxyModel_ loading(boolean loading) {
        onMutation();
        this.loading = loading;
        return this;
    }

    public boolean loading() {
        return this.loading;
    }

    public P3ReviewsRowEpoxyModel_ clickListener(OnModelClickListener<P3ReviewsRowEpoxyModel_, P3ReviewsRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public P3ReviewsRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public P3ReviewsRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public P3ReviewsRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public P3ReviewsRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public P3ReviewsRowEpoxyModel_ m5254id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public P3ReviewsRowEpoxyModel_ m5259id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public P3ReviewsRowEpoxyModel_ m5255id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public P3ReviewsRowEpoxyModel_ m5256id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public P3ReviewsRowEpoxyModel_ m5258id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public P3ReviewsRowEpoxyModel_ m5257id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public P3ReviewsRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public P3ReviewsRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public P3ReviewsRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public P3ReviewsRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public P3ReviewsRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_p3_reviews_row;
    }

    public P3ReviewsRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.reviewModels = null;
        this.reviewsCount = 0;
        this.starRating = 0.0f;
        this.loading = false;
        this.clickListener = null;
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
        if (!(o instanceof P3ReviewsRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        P3ReviewsRowEpoxyModel_ that = (P3ReviewsRowEpoxyModel_) o;
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
        if (this.reviewModels != null) {
            if (!this.reviewModels.equals(that.reviewModels)) {
                return false;
            }
        } else if (that.reviewModels != null) {
            return false;
        }
        if (this.reviewsCount != that.reviewsCount || Float.compare(that.starRating, this.starRating) != 0 || this.loading != that.loading) {
            return false;
        }
        if ((this.clickListener == null) != (that.clickListener == null)) {
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
        if (this.reviewModels != null) {
            i2 = this.reviewModels.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (((i8 + i2) * 31) + this.reviewsCount) * 31;
        if (this.starRating != 0.0f) {
            i3 = Float.floatToIntBits(this.starRating);
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.loading) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.clickListener == null) {
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
        return "P3ReviewsRowEpoxyModel_{reviewModels=" + this.reviewModels + ", reviewsCount=" + this.reviewsCount + ", starRating=" + this.starRating + ", loading=" + this.loading + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
