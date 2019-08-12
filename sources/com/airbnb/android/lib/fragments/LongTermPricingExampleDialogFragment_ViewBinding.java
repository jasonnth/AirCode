package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class LongTermPricingExampleDialogFragment_ViewBinding implements Unbinder {
    private LongTermPricingExampleDialogFragment target;

    public LongTermPricingExampleDialogFragment_ViewBinding(LongTermPricingExampleDialogFragment target2, View source) {
        this.target = target2;
        target2.priceWithoutDiscountLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.price_without_discount, "field 'priceWithoutDiscountLayout'", LinearLayout.class);
        target2.weeklyDiscountLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.weekly_discount, "field 'weeklyDiscountLayout'", LinearLayout.class);
        target2.priceBeforeFeesLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.price_before_fees, "field 'priceBeforeFeesLayout'", LinearLayout.class);
        target2.dailyPriceList = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.daily_price_list, "field 'dailyPriceList'", LinearLayout.class);
    }

    public void unbind() {
        LongTermPricingExampleDialogFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.priceWithoutDiscountLayout = null;
        target2.weeklyDiscountLayout = null;
        target2.priceBeforeFeesLayout = null;
        target2.dailyPriceList = null;
    }
}
