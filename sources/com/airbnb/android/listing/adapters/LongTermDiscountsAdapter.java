package com.airbnb.android.listing.adapters;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.analytics.PricingJitneyLogger;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingLongTermDiscountValues;
import com.airbnb.android.core.utils.PercentageUtils;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineFormattedIntegerInputRowEpoxyModel_;
import com.airbnb.android.itinerary.TripEventModel;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.enums.ListingDisplayMode;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Objects;
import icepick.State;

public class LongTermDiscountsAdapter extends AirEpoxyAdapter implements InputAdapter {
    private final int averageMonthlyPrice;
    private final int averageWeeklyPrice;
    private final Context context;
    private final String currencyCode;
    private final Listener listener;
    private final InlineFormattedIntegerInputRowEpoxyModel_ monthlyDiscountInputRow;
    @State
    boolean monthlyDiscountShowError;
    @State
    Integer monthlyDiscountValue;
    private final InlineFormattedIntegerInputRowEpoxyModel_ weeklyDiscountInputRow;
    @State
    boolean weeklyDiscountShowError;
    @State
    Integer weeklyDiscountValue;

    public interface Listener {
        void showLengthOfStayDiscountLearnMore();

        void validStateUpdated(boolean z);
    }

    public LongTermDiscountsAdapter(Context context2, ListingDisplayMode mode, Listing listing, ListingLongTermDiscountValues averagePrices, Listener listener2, PricingJitneyLogger pricingJitneyLogger, Bundle savedInstanceState) {
        super(true);
        enableDiffing();
        this.listener = listener2;
        this.context = context2;
        this.currencyCode = ListingTextUtils.getListingCurrency(listing);
        if (averagePrices == null) {
            this.averageWeeklyPrice = 0;
            this.averageMonthlyPrice = 0;
        } else {
            this.averageWeeklyPrice = averagePrices.getWeeklyDiscountValueNative();
            this.averageMonthlyPrice = averagePrices.getMonthlyDiscountValueNative();
        }
        if (savedInstanceState == null) {
            this.weeklyDiscountValue = Integer.valueOf(listing.getWeeklyPriceFactor().getDiscountPercentage());
            this.monthlyDiscountValue = Integer.valueOf(listing.getMonthlyPriceFactor().getDiscountPercentage());
            this.weeklyDiscountShowError = false;
            this.monthlyDiscountShowError = false;
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        DocumentMarqueeEpoxyModel_ header = createHeader(mode);
        this.weeklyDiscountInputRow = InlineFormattedIntegerInputRowEpoxyModel_.forIntegerPercentage().m4872id((CharSequence) "weekly_row").titleRes(C7213R.string.manage_listing_long_term_discounts_weekly_discount_title).amountChangedListener(LongTermDiscountsAdapter$$Lambda$1.lambdaFactory$(this)).inputAmount(this.weeklyDiscountValue).tipClickListener(LongTermDiscountsAdapter$$Lambda$2.lambdaFactory$(pricingJitneyLogger, listing)).showError(this.weeklyDiscountShowError);
        setTip(this.weeklyDiscountInputRow, listing.getAutoWeeklyFactor());
        setWeeklyPricingSubtitle();
        this.monthlyDiscountInputRow = InlineFormattedIntegerInputRowEpoxyModel_.forIntegerPercentage().m4872id((CharSequence) "monthly_row").titleRes(C7213R.string.manage_listing_long_term_discounts_monthly_discount_title).amountChangedListener(LongTermDiscountsAdapter$$Lambda$3.lambdaFactory$(this)).tipClickListener(LongTermDiscountsAdapter$$Lambda$4.lambdaFactory$(pricingJitneyLogger, listing)).showError(this.monthlyDiscountShowError).inputAmount(this.monthlyDiscountValue);
        setTip(this.monthlyDiscountInputRow, listing.getAutoMonthlyFactor());
        setMonthlyPricingSubtitle();
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{header, this.weeklyDiscountInputRow, this.monthlyDiscountInputRow});
    }

    private DocumentMarqueeEpoxyModel_ createHeader(ListingDisplayMode mode) {
        DocumentMarqueeEpoxyModel_ header = new DocumentMarqueeEpoxyModel_().m4536id((CharSequence) TripEventModel.HEADER);
        if (mode == ListingDisplayMode.LYS) {
            header.titleRes(C7213R.string.manage_listing_long_term_discounts_title);
        } else if (FeatureToggles.showHostPricingRules()) {
            header.titleRes(C7213R.string.manage_listing_length_of_stay_discounts_title).captionRes(C7213R.string.manage_listing_length_of_stay_discounts_intro).linkRes(C7213R.string.learn_more_info_text).linkClickListener(LongTermDiscountsAdapter$$Lambda$5.lambdaFactory$(this));
        } else {
            header.titleRes(C7213R.string.manage_listing_long_term_discounts_title).captionRes(C7213R.string.manage_listing_long_term_discounts_intro);
        }
        return header;
    }

    public void onSaveInstanceState(Bundle outState) {
        this.weeklyDiscountShowError = this.weeklyDiscountInputRow.showError();
        this.monthlyDiscountShowError = this.monthlyDiscountInputRow.showError();
        super.onSaveInstanceState(outState);
    }

    public boolean hasChanged(Listing listing) {
        return !Objects.equal(this.weeklyDiscountValue, Integer.valueOf(listing.getWeeklyPriceFactor().getDiscountPercentage())) || !Objects.equal(this.monthlyDiscountValue, Integer.valueOf(listing.getMonthlyPriceFactor().getDiscountPercentage()));
    }

    private void setTip(InlineFormattedIntegerInputRowEpoxyModel_ model, double tipDecimal) {
        int tip = PercentageUtils.discountedDoubleToDiscountInt(tipDecimal);
        if (tip > 0 && tip < 100) {
            model.tip(this.context.getString(ListingTextUtils.getTipRes(), new Object[]{PercentageUtils.formatDiscountPercentage(tip)})).tipAmount(Integer.valueOf(tip));
        }
    }

    private void updateListener() {
        boolean validWeekly;
        boolean validMonthly;
        boolean validInput;
        if (this.weeklyDiscountValue == null || (this.weeklyDiscountValue.intValue() >= 0 && this.weeklyDiscountValue.intValue() < 100)) {
            validWeekly = true;
        } else {
            validWeekly = false;
        }
        if (this.monthlyDiscountValue == null || (this.monthlyDiscountValue.intValue() >= 0 && this.monthlyDiscountValue.intValue() < 100)) {
            validMonthly = true;
        } else {
            validMonthly = false;
        }
        if (!validWeekly || !validMonthly) {
            validInput = false;
        } else {
            validInput = true;
        }
        this.listener.validStateUpdated(validInput);
    }

    /* access modifiers changed from: private */
    public void setWeeklyDiscountValue() {
        this.weeklyDiscountValue = this.weeklyDiscountInputRow.inputAmount();
        setWeeklyPricingSubtitle();
        notifyModelChanged(this.weeklyDiscountInputRow);
        updateListener();
    }

    /* access modifiers changed from: private */
    public void setMonthlyDiscountValue() {
        this.monthlyDiscountValue = this.monthlyDiscountInputRow.inputAmount();
        setMonthlyPricingSubtitle();
        notifyModelChanged(this.monthlyDiscountInputRow);
        updateListener();
    }

    public double getWeeklyFactor() {
        return PercentageUtils.discountIntToDiscountedDouble(SanitizeUtils.zeroIfNull(this.weeklyDiscountValue));
    }

    public double getMonthlyFactor() {
        return PercentageUtils.discountIntToDiscountedDouble(SanitizeUtils.zeroIfNull(this.monthlyDiscountValue));
    }

    public Strap getDiscountsSettings() {
        return Strap.make().mo11635kv("weekly_price_factor", getWeeklyFactor()).mo11635kv("monthly_price_factor", getMonthlyFactor());
    }

    public void setInputEnabled(boolean enabled) {
        this.monthlyDiscountInputRow.enabled(enabled);
        this.weeklyDiscountInputRow.enabled(enabled);
        notifyModelsChanged();
    }

    public void markErrors(boolean markError) {
        this.weeklyDiscountInputRow.showError(markError);
        this.monthlyDiscountInputRow.showError(markError);
        notifyModelChanged(this.weeklyDiscountInputRow);
        notifyModelChanged(this.monthlyDiscountInputRow);
    }

    public boolean showWeeklyPriceHigherError() {
        return (this.weeklyDiscountValue == null || this.weeklyDiscountValue.intValue() == 0 || this.monthlyDiscountValue == null || this.monthlyDiscountValue.intValue() == 0 || this.monthlyDiscountValue.intValue() >= this.weeklyDiscountValue.intValue()) ? false : true;
    }

    private void setWeeklyPricingSubtitle() {
        setPricingSubtitle(this.weeklyDiscountInputRow, C7213R.string.manage_listing_long_term_discounts_weekly_discount_info, this.weeklyDiscountValue, this.averageWeeklyPrice);
    }

    private void setMonthlyPricingSubtitle() {
        setPricingSubtitle(this.monthlyDiscountInputRow, C7213R.string.manage_listing_long_term_discounts_monthly_discount_info, this.monthlyDiscountValue, this.averageMonthlyPrice);
    }

    private void setPricingSubtitle(InlineFormattedIntegerInputRowEpoxyModel_ model, int strRes, Integer discountPercentage, int price) {
        if (price == 0 || (discountPercentage != null && (discountPercentage.intValue() < 0 || discountPercentage.intValue() >= 100))) {
            model.subTitle("");
            return;
        }
        int usedPercentage = SanitizeUtils.zeroIfNull(discountPercentage);
        String percentString = PercentageUtils.formatDiscountPercentage(usedPercentage);
        String priceString = getDiscountedPriceString(usedPercentage, price);
        model.subTitle(this.context.getString(strRes, new Object[]{percentString, priceString}));
    }

    private String getDiscountedPriceString(int discountPercentage, int averagePrice) {
        return CurrencyUtils.formatCurrencyAmount((double) Math.round(((double) averagePrice) * PercentageUtils.discountIntToDiscountedDouble(discountPercentage)), this.currencyCode);
    }
}
