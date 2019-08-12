package com.airbnb.android.explore.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.models.SearchPriceHistogram;
import com.airbnb.android.core.models.SearchPriceMetaData;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.SearchPricingUtil;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar.OnRangeSeekBarChangeListener;
import com.airbnb.android.utils.PriceScaleUtil;
import com.airbnb.android.utils.PriceScaleUtil.ScaleType;
import com.airbnb.p027n2.primitives.AirTextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExplorePriceHistogramRow extends LinearLayout {
    @BindView
    AirTextView averagePriceTextView;
    protected CurrencyFormatter currencyHelper;
    private OnRangeSeekBarChangeListener<Integer> externalRangeSeekBarChangeListener;
    @BindView
    FrameLayout histogramContainer;
    private OnRangeSeekBarChangeListener<Integer> internalRangeSeekBarChangeListener;
    private ExploreInlineRangeSeekBar priceHistogramView;
    private final List<Integer> priceScale = new ArrayList();
    @BindView
    AirTextView priceTextView;

    public ExplorePriceHistogramRow(Context context) {
        super(context);
        init();
    }

    public ExplorePriceHistogramRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExplorePriceHistogramRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C0857R.layout.explore_price_histogram_row, this);
        ButterKnife.bind((View) this);
        setOrientation(1);
        this.currencyHelper = CoreApplication.instance(getContext()).component().currencyHelper();
        this.priceHistogramView = new ExploreInlineRangeSeekBar(getContext(), C0857R.C0858drawable.inline_explore_price_histogram_handle, C0857R.C0858drawable.inline_explore_price_histogram_handle_pressed);
        this.histogramContainer.addView(this.priceHistogramView);
        this.internalRangeSeekBarChangeListener = ExplorePriceHistogramRow$$Lambda$1.lambdaFactory$(this);
    }

    static /* synthetic */ void lambda$init$0(ExplorePriceHistogramRow explorePriceHistogramRow, ExploreBaseRangeSeekBar bar, Integer minValue, Integer maxValue, boolean dragging) {
        int minPrice = explorePriceHistogramRow.mapSeekBarValueToPrice(minValue.intValue());
        int maxPrice = explorePriceHistogramRow.mapSeekBarValueToPrice(maxValue.intValue());
        explorePriceHistogramRow.updatePriceRangeText(minPrice, maxPrice);
        if (explorePriceHistogramRow.externalRangeSeekBarChangeListener != null && !dragging) {
            explorePriceHistogramRow.externalRangeSeekBarChangeListener.onRangeSeekBarValuesChanged(bar, Integer.valueOf(minPrice), Integer.valueOf(maxPrice), dragging);
        }
    }

    public void setHistogramData(SearchPriceHistogram priceHistogram, SearchPriceMetaData priceMetaData, int minPrice, int maxPrice, boolean hasDates) {
        List<Integer> histogramData = priceHistogram == null ? Collections.emptyList() : priceHistogram.getHistogram();
        int minFilterPrice = priceMetaData == null ? 0 : priceMetaData.getMinFilterPrice();
        int maxFilterPrice = priceMetaData == null ? maxPrice * 2 : priceMetaData.getMaxFilterPrice();
        this.priceScale.clear();
        this.priceScale.addAll(PriceScaleUtil.getPriceScale(minFilterPrice, maxFilterPrice, ScaleType.Geometric));
        this.priceHistogramView.setAbsoluteMaxValue(Integer.valueOf(this.priceScale.size() - 1));
        this.priceHistogramView.setHistogram(histogramData);
        if (minPrice <= 0) {
            minPrice = minFilterPrice;
        }
        if (maxPrice <= 0) {
            maxPrice = maxFilterPrice;
        }
        this.priceHistogramView.setSelectedMinValue(Integer.valueOf(mapPriceToSeekBarValue(minPrice)));
        this.priceHistogramView.setSelectedMaxValue(Integer.valueOf(mapPriceToSeekBarValue(maxPrice)));
        this.priceHistogramView.setOnRangeSeekBarChangeListener(this.internalRangeSeekBarChangeListener);
        this.priceHistogramView.setNotifyWhileDragging(true);
        updatePriceRangeText(minPrice, maxPrice);
        if (priceHistogram != null) {
            setAveragePriceText(priceHistogram.getAveragePrice(), priceMetaData.isPriceMonthly(), hasDates);
        } else {
            this.averagePriceTextView.setText(null);
        }
    }

    public void setRangeChangeListener(OnRangeSeekBarChangeListener<Integer> listener) {
        this.externalRangeSeekBarChangeListener = listener;
    }

    private void setAveragePriceText(int averagePrice, boolean isPriceMonthly, boolean hasDates) {
        int averageTextRes;
        int monthlyOrNightlyTextRes = isPriceMonthly ? C0857R.string.price_histogram_average_text_monthly : C0857R.string.price_histogram_average_text_nightly;
        if (!SearchPricingUtil.isTotalPricingEnabled() || !hasDates) {
            averageTextRes = monthlyOrNightlyTextRes;
        } else {
            averageTextRes = C0857R.string.price_histogram_average_text_total;
        }
        this.averagePriceTextView.setText(getContext().getString(averageTextRes, new Object[]{this.currencyHelper.formatNativeCurrency((double) averagePrice, true)}));
    }

    private void updatePriceRangeText(int minPrice, int maxPrice) {
        String formattedMinPrice = this.currencyHelper.formatNativeCurrency((double) minPrice, true);
        String formattedMaxPrice = this.currencyHelper.formatNativeCurrency((double) maxPrice, true);
        if (maxPrice == ((Integer) this.priceScale.get(this.priceScale.size() - 1)).intValue()) {
            formattedMaxPrice = getContext().getString(C0857R.string.over_maximum_search_filter_price, new Object[]{formattedMaxPrice});
        }
        this.priceTextView.setText(getContext().getString(C0857R.string.separator_with_values, new Object[]{formattedMinPrice, formattedMaxPrice}));
    }

    private int mapPriceToSeekBarValue(int price) {
        for (int i = 0; i < this.priceScale.size(); i++) {
            if (((Integer) this.priceScale.get(i)).intValue() >= price) {
                return i;
            }
        }
        return this.priceScale.size() - 1;
    }

    private int mapSeekBarValueToPrice(int seekBarValue) {
        return ((Integer) this.priceScale.get(Math.min(seekBarValue, this.priceScale.size() - 1))).intValue();
    }
}
