package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.StarRatingSummary;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class StarRatingSummaryEpoxyModel_ extends StarRatingSummaryEpoxyModel implements GeneratedModel<StarRatingSummary> {
    private OnModelBoundListener<StarRatingSummaryEpoxyModel_, StarRatingSummary> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<StarRatingSummaryEpoxyModel_, StarRatingSummary> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, StarRatingSummary object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(StarRatingSummary object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public StarRatingSummaryEpoxyModel_ onBind(OnModelBoundListener<StarRatingSummaryEpoxyModel_, StarRatingSummary> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(StarRatingSummary object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public StarRatingSummaryEpoxyModel_ onUnbind(OnModelUnboundListener<StarRatingSummaryEpoxyModel_, StarRatingSummary> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public StarRatingSummaryEpoxyModel_ reviewsCount(int reviewsCount) {
        onMutation();
        this.reviewsCount = reviewsCount;
        return this;
    }

    public int reviewsCount() {
        return this.reviewsCount;
    }

    public StarRatingSummaryEpoxyModel_ title(String title) {
        onMutation();
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public StarRatingSummaryEpoxyModel_ starRating(float starRating) {
        onMutation();
        this.starRating = starRating;
        return this;
    }

    public float starRating() {
        return this.starRating;
    }

    public StarRatingSummaryEpoxyModel_ minNumReviewsToShowStars(Integer minNumReviewsToShowStars) {
        onMutation();
        this.minNumReviewsToShowStars = minNumReviewsToShowStars;
        return this;
    }

    public Integer minNumReviewsToShowStars() {
        return this.minNumReviewsToShowStars;
    }

    public StarRatingSummaryEpoxyModel_ clickListener(OnModelClickListener<StarRatingSummaryEpoxyModel_, StarRatingSummary> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public StarRatingSummaryEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public StarRatingSummaryEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public StarRatingSummaryEpoxyModel_ listing(Listing listing) {
        super.listing(listing);
        return this;
    }

    public StarRatingSummaryEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public StarRatingSummaryEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public StarRatingSummaryEpoxyModel_ m6263id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public StarRatingSummaryEpoxyModel_ m6268id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public StarRatingSummaryEpoxyModel_ m6264id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public StarRatingSummaryEpoxyModel_ m6265id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public StarRatingSummaryEpoxyModel_ m6267id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public StarRatingSummaryEpoxyModel_ m6266id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public StarRatingSummaryEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public StarRatingSummaryEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public StarRatingSummaryEpoxyModel_ show() {
        super.show();
        return this;
    }

    public StarRatingSummaryEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public StarRatingSummaryEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.n2_view_holder_star_rating_summary;
    }

    public StarRatingSummaryEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.reviewsCount = 0;
        this.title = null;
        this.starRating = 0.0f;
        this.minNumReviewsToShowStars = null;
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
        if (!(o instanceof StarRatingSummaryEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        StarRatingSummaryEpoxyModel_ that = (StarRatingSummaryEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.reviewsCount != that.reviewsCount) {
            return false;
        }
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (Float.compare(that.starRating, this.starRating) != 0) {
            return false;
        }
        if (this.minNumReviewsToShowStars != null) {
            if (!this.minNumReviewsToShowStars.equals(that.minNumReviewsToShowStars)) {
                return false;
            }
        } else if (that.minNumReviewsToShowStars != null) {
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
        int i6 = 1;
        int i7 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i8 = (((hashCode + i) * 31) + this.reviewsCount) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.starRating != 0.0f) {
            i3 = Float.floatToIntBits(this.starRating);
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.minNumReviewsToShowStars != null) {
            i4 = this.minNumReviewsToShowStars.hashCode();
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
        return "StarRatingSummaryEpoxyModel_{reviewsCount=" + this.reviewsCount + ", title=" + this.title + ", starRating=" + this.starRating + ", minNumReviewsToShowStars=" + this.minNumReviewsToShowStars + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
