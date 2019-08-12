package com.airbnb.android.explore.views;

import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar.OnRangeSeekBarChangeListener;

final /* synthetic */ class ExplorePriceHistogramRow$$Lambda$1 implements OnRangeSeekBarChangeListener {
    private final ExplorePriceHistogramRow arg$1;

    private ExplorePriceHistogramRow$$Lambda$1(ExplorePriceHistogramRow explorePriceHistogramRow) {
        this.arg$1 = explorePriceHistogramRow;
    }

    public static OnRangeSeekBarChangeListener lambdaFactory$(ExplorePriceHistogramRow explorePriceHistogramRow) {
        return new ExplorePriceHistogramRow$$Lambda$1(explorePriceHistogramRow);
    }

    public void onRangeSeekBarValuesChanged(ExploreBaseRangeSeekBar exploreBaseRangeSeekBar, Object obj, Object obj2, boolean z) {
        ExplorePriceHistogramRow.lambda$init$0(this.arg$1, exploreBaseRangeSeekBar, (Integer) obj, (Integer) obj2, z);
    }
}
