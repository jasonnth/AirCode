package com.airbnb.android.core.utils;

import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;

public class PricingDisclaimerModelUtils {

    /* renamed from: ID */
    private static Long f8500ID = null;
    private static final String ID_KEY = "pricing disclaimer";

    public static long getModelId() {
        if (f8500ID == null) {
            f8500ID = Long.valueOf(buildPricingDisclaimerEpoxyModel().mo11715id());
        }
        return f8500ID.longValue();
    }

    public static SimpleTextRowEpoxyModel_ buildPricingDisclaimerEpoxyModel() {
        return new SimpleTextRowEpoxyModel_().small().showDivider(false).m5580id((CharSequence) ID_KEY);
    }

    public static EpoxyModel<?> updateTotalPricingDisclaimerEpoxyModel(EpoxyModel<?> viewModel, boolean hasDates) {
        return updateTotalPricingDisclaimerEpoxyModel(viewModel, hasDates, false);
    }

    public static EpoxyModel<?> updateTotalPricingDisclaimerEpoxyModel(EpoxyModel<?> viewModel, boolean hasDates, boolean priceIncludesTaxes) {
        SimpleTextRowEpoxyModel_ model = (SimpleTextRowEpoxyModel_) viewModel;
        if (priceIncludesTaxes) {
            return model.textRes(hasDates ? C0716R.string.with_dates_including_taxes_disclaimer : C0716R.string.no_dates_short_disclaimer);
        }
        return model.textRes(hasDates ? C0716R.string.with_dates_disclaimer : C0716R.string.no_dates_disclaimer);
    }

    public static EpoxyModel<?> updateServiceFeeDisclaimerEpoxyModel(EpoxyModel<?> viewModel, boolean hasDates) {
        return ((SimpleTextRowEpoxyModel_) viewModel).textRes(hasDates ? C0716R.string.search_pricing_disclaimer_de : C0716R.string.search_pricing_disclaimer_without_dates_de);
    }

    public static EpoxyModel<?> buildPricingDisclaimerModel(boolean hasDates, boolean priceIncludesTaxes) {
        return updateTotalPricingDisclaimerEpoxyModel(new SimpleTextRowEpoxyModel_().small().showDivider(false).m5580id((CharSequence) ID_KEY), hasDates, priceIncludesTaxes);
    }

    public static EpoxyModel<?> buildServiceFeeDisclaimerModel(boolean hasDates) {
        return updateServiceFeeDisclaimerEpoxyModel(new SimpleTextRowEpoxyModel_().small().showDivider(false).m5580id((CharSequence) ID_KEY), hasDates);
    }
}
