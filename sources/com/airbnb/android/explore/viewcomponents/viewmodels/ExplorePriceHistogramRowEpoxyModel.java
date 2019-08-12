package com.airbnb.android.explore.viewcomponents.viewmodels;

import com.airbnb.android.core.models.SearchPriceHistogram;
import com.airbnb.android.core.models.SearchPriceMetaData;
import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar.OnRangeSeekBarChangeListener;
import com.airbnb.android.explore.views.ExplorePriceHistogramRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ExplorePriceHistogramRowEpoxyModel extends AirEpoxyModel<ExplorePriceHistogramRow> {
    boolean hasDates;
    SearchPriceHistogram histogram;
    int maxPrice;
    SearchPriceMetaData metadata;
    int minPrice;
    OnRangeSeekBarChangeListener<Integer> rangeChangeListener;

    public void bind(ExplorePriceHistogramRow row) {
        super.bind(row);
        row.setHistogramData(this.histogram, this.metadata, this.minPrice, this.maxPrice, this.hasDates);
        row.setRangeChangeListener(this.rangeChangeListener);
    }
}
