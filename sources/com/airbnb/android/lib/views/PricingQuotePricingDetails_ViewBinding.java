package com.airbnb.android.lib.views;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class PricingQuotePricingDetails_ViewBinding implements Unbinder {
    private PricingQuotePricingDetails target;

    public PricingQuotePricingDetails_ViewBinding(PricingQuotePricingDetails target2) {
        this(target2, target2);
    }

    public PricingQuotePricingDetails_ViewBinding(PricingQuotePricingDetails target2, View source) {
        this.target = target2;
        target2.mPriceNativeCell = (GroupedCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.subtotal_cell, "field 'mPriceNativeCell'", GroupedCell.class);
        target2.mCleaningFeeCell = (GroupedCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.cleaning_fee_cell, "field 'mCleaningFeeCell'", GroupedCell.class);
        target2.mServiceFeeCell = (GroupedCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.service_fee_cell, "field 'mServiceFeeCell'", GroupedCell.class);
        target2.mTaxesCell = (GroupedCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.taxes_cell, "field 'mTaxesCell'", GroupedCell.class);
        target2.mTotalPriceCell = (GroupedCell) Utils.findRequiredViewAsType(source, C0880R.C0882id.total_price_cell, "field 'mTotalPriceCell'", GroupedCell.class);
        target2.mPriceBreakdown = (ViewGroup) Utils.findRequiredViewAsType(source, C0880R.C0882id.price_breakdown, "field 'mPriceBreakdown'", ViewGroup.class);
    }

    public void unbind() {
        PricingQuotePricingDetails target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mPriceNativeCell = null;
        target2.mCleaningFeeCell = null;
        target2.mServiceFeeCell = null;
        target2.mTaxesCell = null;
        target2.mTotalPriceCell = null;
        target2.mPriceBreakdown = null;
    }
}
