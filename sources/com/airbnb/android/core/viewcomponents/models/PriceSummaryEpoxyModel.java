package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.p027n2.components.PriceSummary;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class PriceSummaryEpoxyModel extends AirEpoxyModel<PriceSummary> {
    CurrencyAmount currencyAmount;
    boolean hideCaption;

    public void bind(PriceSummary priceSummary) {
        super.bind(priceSummary);
        priceSummary.setPriceAndCurrency(this.currencyAmount.formattedForDisplay(), this.currencyAmount.getCurrency());
        if (!this.hideCaption) {
            priceSummary.setPriceBreakdownText(C0716R.string.p4_price_breakdown);
        } else {
            priceSummary.setPriceBreakdownText((CharSequence) "");
        }
        priceSummary.setNormal();
    }

    public int getDividerViewType() {
        return 0;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
