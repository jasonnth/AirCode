package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.HostRatingDistributionStatistic;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.host.stats.views.ReviewStarBreakdownView;
import com.airbnb.android.lib.host.stats.views.ReviewStarBreakdownView.Callback;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class ReviewStarBreakdownEpoxyModel_ extends ReviewStarBreakdownEpoxyModel implements GeneratedModel<ReviewStarBreakdownView> {
    private OnModelBoundListener<ReviewStarBreakdownEpoxyModel_, ReviewStarBreakdownView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ReviewStarBreakdownEpoxyModel_, ReviewStarBreakdownView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ReviewStarBreakdownView object, int position) {
    }

    public void handlePostBind(ReviewStarBreakdownView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ReviewStarBreakdownEpoxyModel_ onBind(OnModelBoundListener<ReviewStarBreakdownEpoxyModel_, ReviewStarBreakdownView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ReviewStarBreakdownView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ReviewStarBreakdownEpoxyModel_ onUnbind(OnModelUnboundListener<ReviewStarBreakdownEpoxyModel_, ReviewStarBreakdownView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ReviewStarBreakdownEpoxyModel_ ratingDistributionStatistics(List<HostRatingDistributionStatistic> ratingDistributionStatistics) {
        onMutation();
        this.ratingDistributionStatistics = ratingDistributionStatistics;
        return this;
    }

    public List<HostRatingDistributionStatistic> ratingDistributionStatistics() {
        return this.ratingDistributionStatistics;
    }

    public ReviewStarBreakdownEpoxyModel_ callback(Callback callback) {
        onMutation();
        this.callback = callback;
        return this;
    }

    public Callback callback() {
        return this.callback;
    }

    public ReviewStarBreakdownEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ReviewStarBreakdownEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ReviewStarBreakdownEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ReviewStarBreakdownEpoxyModel_ m6059id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ReviewStarBreakdownEpoxyModel_ m6064id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ReviewStarBreakdownEpoxyModel_ m6060id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ReviewStarBreakdownEpoxyModel_ m6061id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ReviewStarBreakdownEpoxyModel_ m6063id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ReviewStarBreakdownEpoxyModel_ m6062id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ReviewStarBreakdownEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ReviewStarBreakdownEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ReviewStarBreakdownEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ReviewStarBreakdownEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ReviewStarBreakdownEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.view_holder_review_breakdown;
    }

    public ReviewStarBreakdownEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.ratingDistributionStatistics = null;
        this.callback = null;
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
        if (!(o instanceof ReviewStarBreakdownEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ReviewStarBreakdownEpoxyModel_ that = (ReviewStarBreakdownEpoxyModel_) o;
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
        if (this.ratingDistributionStatistics != null) {
            if (!this.ratingDistributionStatistics.equals(that.ratingDistributionStatistics)) {
                return false;
            }
        } else if (that.ratingDistributionStatistics != null) {
            return false;
        }
        if (this.callback == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.callback == null)) {
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
        if (this.ratingDistributionStatistics != null) {
            i2 = this.ratingDistributionStatistics.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.callback == null) {
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
        return "ReviewStarBreakdownEpoxyModel_{ratingDistributionStatistics=" + this.ratingDistributionStatistics + ", callback=" + this.callback + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
