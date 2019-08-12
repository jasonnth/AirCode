package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.LongTermPricingExample;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.primitives.fonts.Font;

public class DiscountsExampleAdapter extends AirEpoxyAdapter {
    private final LoadingRowEpoxyModel_ loadingRow = new LoadingRowEpoxyModel_();

    public DiscountsExampleAdapter() {
        enableDiffing();
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new DocumentMarqueeEpoxyModel_().titleRes(C7213R.string.how_discounts_work), new SimpleTextRowEpoxyModel_().textRes(C7213R.string.how_discounts_work_body).showDivider(false), new MicroSectionHeaderEpoxyModel_().maxLines(2).showDivider(false).titleRes(C7213R.string.example_discounts_title), this.loadingRow});
    }

    public void setExampleData(LongTermPricingExample exampleData, String listingCurrency) {
        this.loadingRow.hide();
        String totalBeforeDiscountString = CurrencyUtils.getFormattedPrice((double) exampleData.getTotalBeforeDiscount(), listingCurrency);
        String totalAfterDiscountString = CurrencyUtils.getFormattedPrice((double) exampleData.getTotal(), listingCurrency);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new StandardRowEpoxyModel_().titleRes(C7213R.string.example_discounts_total_cost).infoText((CharSequence) totalBeforeDiscountString), new StandardRowEpoxyModel_().title(C7213R.string.example_discounts_after_discount).infoText((CharSequence) totalAfterDiscountString).font(Font.CircularBook), new MicroRowEpoxyModel_().textRes(C7213R.string.example_discounts_disclaimer)});
        notifyModelsChanged();
    }
}
