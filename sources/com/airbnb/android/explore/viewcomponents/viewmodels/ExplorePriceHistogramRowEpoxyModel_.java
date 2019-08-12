package com.airbnb.android.explore.viewcomponents.viewmodels;

import com.airbnb.android.core.models.SearchPriceHistogram;
import com.airbnb.android.core.models.SearchPriceMetaData;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar.OnRangeSeekBarChangeListener;
import com.airbnb.android.explore.views.ExplorePriceHistogramRow;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ExplorePriceHistogramRowEpoxyModel_ extends ExplorePriceHistogramRowEpoxyModel implements GeneratedModel<ExplorePriceHistogramRow> {
    private OnModelBoundListener<ExplorePriceHistogramRowEpoxyModel_, ExplorePriceHistogramRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ExplorePriceHistogramRowEpoxyModel_, ExplorePriceHistogramRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ExplorePriceHistogramRow object, int position) {
    }

    public void handlePostBind(ExplorePriceHistogramRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ExplorePriceHistogramRowEpoxyModel_ onBind(OnModelBoundListener<ExplorePriceHistogramRowEpoxyModel_, ExplorePriceHistogramRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ExplorePriceHistogramRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ExplorePriceHistogramRowEpoxyModel_ onUnbind(OnModelUnboundListener<ExplorePriceHistogramRowEpoxyModel_, ExplorePriceHistogramRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ExplorePriceHistogramRowEpoxyModel_ histogram(SearchPriceHistogram histogram) {
        onMutation();
        this.histogram = histogram;
        return this;
    }

    public SearchPriceHistogram histogram() {
        return this.histogram;
    }

    public ExplorePriceHistogramRowEpoxyModel_ metadata(SearchPriceMetaData metadata) {
        onMutation();
        this.metadata = metadata;
        return this;
    }

    public SearchPriceMetaData metadata() {
        return this.metadata;
    }

    public ExplorePriceHistogramRowEpoxyModel_ minPrice(int minPrice) {
        onMutation();
        this.minPrice = minPrice;
        return this;
    }

    public int minPrice() {
        return this.minPrice;
    }

    public ExplorePriceHistogramRowEpoxyModel_ maxPrice(int maxPrice) {
        onMutation();
        this.maxPrice = maxPrice;
        return this;
    }

    public int maxPrice() {
        return this.maxPrice;
    }

    public ExplorePriceHistogramRowEpoxyModel_ hasDates(boolean hasDates) {
        onMutation();
        this.hasDates = hasDates;
        return this;
    }

    public boolean hasDates() {
        return this.hasDates;
    }

    public ExplorePriceHistogramRowEpoxyModel_ rangeChangeListener(OnRangeSeekBarChangeListener<Integer> rangeChangeListener) {
        onMutation();
        this.rangeChangeListener = rangeChangeListener;
        return this;
    }

    public OnRangeSeekBarChangeListener<Integer> rangeChangeListener() {
        return this.rangeChangeListener;
    }

    public ExplorePriceHistogramRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ExplorePriceHistogramRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ExplorePriceHistogramRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ExplorePriceHistogramRowEpoxyModel_ m5867id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ExplorePriceHistogramRowEpoxyModel_ m5872id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ExplorePriceHistogramRowEpoxyModel_ m5868id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ExplorePriceHistogramRowEpoxyModel_ m5869id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ExplorePriceHistogramRowEpoxyModel_ m5871id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ExplorePriceHistogramRowEpoxyModel_ m5870id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ExplorePriceHistogramRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ExplorePriceHistogramRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ExplorePriceHistogramRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ExplorePriceHistogramRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ExplorePriceHistogramRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0857R.layout.view_holder_explore_inline_price_histogram;
    }

    public ExplorePriceHistogramRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.histogram = null;
        this.metadata = null;
        this.minPrice = 0;
        this.maxPrice = 0;
        this.hasDates = false;
        this.rangeChangeListener = null;
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
        if (!(o instanceof ExplorePriceHistogramRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ExplorePriceHistogramRowEpoxyModel_ that = (ExplorePriceHistogramRowEpoxyModel_) o;
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
        if (this.histogram != null) {
            if (!this.histogram.equals(that.histogram)) {
                return false;
            }
        } else if (that.histogram != null) {
            return false;
        }
        if (this.metadata != null) {
            if (!this.metadata.equals(that.metadata)) {
                return false;
            }
        } else if (that.metadata != null) {
            return false;
        }
        if (this.minPrice != that.minPrice || this.maxPrice != that.maxPrice || this.hasDates != that.hasDates) {
            return false;
        }
        if ((this.rangeChangeListener == null) != (that.rangeChangeListener == null)) {
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
        if (this.histogram != null) {
            i2 = this.histogram.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.metadata != null) {
            i3 = this.metadata.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (((((i9 + i3) * 31) + this.minPrice) * 31) + this.maxPrice) * 31;
        if (this.hasDates) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.rangeChangeListener == null) {
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
        return "ExplorePriceHistogramRowEpoxyModel_{histogram=" + this.histogram + ", metadata=" + this.metadata + ", minPrice=" + this.minPrice + ", maxPrice=" + this.maxPrice + ", hasDates=" + this.hasDates + ", rangeChangeListener=" + this.rangeChangeListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
