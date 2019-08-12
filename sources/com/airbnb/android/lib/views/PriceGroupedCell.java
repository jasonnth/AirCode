package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindColor;
import butterknife.ButterKnife;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;

public class PriceGroupedCell extends GroupedCell {
    public static final float EMPHASIZED_CONTENT_MULTIPLIER = 1.1f;
    CurrencyFormatter currencyFormatter;
    private float defaultContentTextSizePx;
    @BindColor
    int priceColorDefault;
    @BindColor
    int priceColorNegative;
    @BindColor
    int priceColorPositive;

    public PriceGroupedCell(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public PriceGroupedCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PriceGroupedCell(Context context) {
        super(context);
        init();
    }

    private void init() {
        ButterKnife.bind((View) this);
        this.defaultContentTextSizePx = this.mContent.getTextSize();
        if (!isInEditMode()) {
            ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        }
    }
}
