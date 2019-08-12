package com.airbnb.android.lib.views;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import com.airbnb.android.lib.C0880R;

public class PriceGroupedCell_ViewBinding extends GroupedCell_ViewBinding {
    public PriceGroupedCell_ViewBinding(PriceGroupedCell target) {
        this(target, target);
    }

    public PriceGroupedCell_ViewBinding(PriceGroupedCell target, View source) {
        super(target, source);
        Context context = source.getContext();
        target.priceColorDefault = ContextCompat.getColor(context, C0880R.color.c_hof);
        target.priceColorNegative = ContextCompat.getColor(context, C0880R.color.c_rausch);
        target.priceColorPositive = ContextCompat.getColor(context, C0880R.color.c_green);
    }
}
