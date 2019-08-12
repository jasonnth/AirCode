package com.airbnb.android.listing.adapters;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.PricingJitneyLogger;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineFormattedIntegerInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.ListingGraph;
import com.airbnb.android.listing.enums.ListingDisplayMode;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
import com.airbnb.p027n2.utils.IntegerNumberFormatHelper;
import icepick.State;
import java.util.Currency;

public class BasePriceAdapter extends AirEpoxyAdapter implements InputAdapter {
    @State
    Integer basePrice;
    @State
    String currencyCode;
    private InlineInputRowEpoxyModel_ currencyInputRow;
    private final OnCurrencyRowClickListener currencyRowClickListener;
    LoggingContextFactory loggingContextFactory;
    private final ListingDisplayMode mode;
    private final InlineFormattedIntegerInputRowEpoxyModel_ priceInput;
    private final PricingJitneyLogger pricingJitneyLogger;
    private final ValidSettingsListener validSettingsListener;

    public interface OnCurrencyRowClickListener {
        void onCurrencyRowClicked(String str);
    }

    public interface ValidSettingsListener {
        void settingsAreValid(boolean z);
    }

    public BasePriceAdapter(ListingDisplayMode mode2, Context context, Listing listing, ValidSettingsListener validSettingsListener2, Bundle savedInstanceState) {
        this(mode2, context, listing, validSettingsListener2, null, savedInstanceState);
    }

    public BasePriceAdapter(ListingDisplayMode mode2, Context context, Listing listing, ValidSettingsListener validSettingsListener2, OnCurrencyRowClickListener currencyRowClickListener2, Bundle savedInstanceState) {
        this.validSettingsListener = validSettingsListener2;
        this.mode = mode2;
        this.currencyRowClickListener = currencyRowClickListener2;
        enableDiffing();
        ((ListingGraph) CoreApplication.instance(context).component()).inject(this);
        this.pricingJitneyLogger = new PricingJitneyLogger(this.loggingContextFactory, mode2 == ListingDisplayMode.LYS ? C2585PricingSettingsPageType.ListYourSpace : C2585PricingSettingsPageType.ManageListing, C2586PricingSettingsSectionType.PricingSettings, listing.getId());
        if (savedInstanceState == null) {
            this.basePrice = SanitizeUtils.nullIfZero(mode2 == ListingDisplayMode.LYS ? listing.getListingPrice() : listing.getListingPriceNative());
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        DocumentMarqueeEpoxyModel_ header = new DocumentMarqueeEpoxyModel_().titleRes(C7213R.string.listing_setting_base_price).captionRes(C7213R.string.listing_setting_base_price_explanation);
        Currency currency = Currency.getInstance(ListingTextUtils.getListingCurrency(listing));
        int tipAmount = listing.getAutoPricingDaily();
        this.priceInput = InlineFormattedIntegerInputRowEpoxyModel_.forCurrency(currency).titleRes(C7213R.string.listing_setting_price_per_night).amountChangedListener(BasePriceAdapter$$Lambda$1.lambdaFactory$(this)).inputAmount(this.basePrice).tipClickListener(BasePriceAdapter$$Lambda$2.lambdaFactory$(this, currency, listing, tipAmount)).m4872id((CharSequence) "price_input_row");
        if (tipAmount > 0) {
            this.priceInput.tip(context.getString(ListingTextUtils.getTipRes(), new Object[]{CurrencyUtils.formatAmount((double) tipAmount, currency)})).tipAmount(Integer.valueOf(tipAmount));
        }
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{header, this.priceInput});
        storeBasePrice(this.basePrice);
        handleModeSpecificRows(listing, savedInstanceState);
    }

    private void handleModeSpecificRows(Listing listing, Bundle savedInstanceState) {
        switch (this.mode) {
            case LYS:
                addCurrencyRow(listing, savedInstanceState);
                return;
            default:
                return;
        }
    }

    private void addCurrencyRow(Listing listing, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.currencyCode = ListingTextUtils.getListingCurrency(listing);
        }
        this.currencyInputRow = new InlineInputRowEpoxyModel_().titleRes(C7213R.string.ml_currency).input(this.currencyCode).m4884id((CharSequence) "currency_row");
        this.currencyInputRow.clickListener(BasePriceAdapter$$Lambda$3.lambdaFactory$(this));
        addModel(this.currencyInputRow);
    }

    public void updateCurrencyCode(Context context, Listing listing, String currencyCode2) {
        if (!this.currencyInputRow.input().equals(currencyCode2)) {
            Currency currency = Currency.getInstance(currencyCode2);
            int suggestedPrice = listing.getAutoPricingDaily();
            this.currencyCode = currencyCode2;
            this.currencyInputRow.input(currencyCode2);
            this.priceInput.numberFormat(IntegerNumberFormatHelper.forCurrency(currency));
            if (suggestedPrice > 0) {
                this.priceInput.tip(context.getString(ListingTextUtils.getTipRes(), new Object[]{CurrencyUtils.formatAmount((double) suggestedPrice, currency)})).tipAmount(Integer.valueOf(suggestedPrice)).tipClickListener(BasePriceAdapter$$Lambda$4.lambdaFactory$(this, currency, listing, suggestedPrice));
            }
            this.basePrice = null;
            this.priceInput.hint(currency.getSymbol()).inputAmount(this.basePrice);
            notifyModelsChanged();
            checkValidity();
        }
    }

    public void setInputEnabled(boolean enabled) {
        this.priceInput.enabled(enabled);
        this.currencyInputRow.enabled(enabled);
        notifyModelChanged(this.priceInput);
    }

    /* access modifiers changed from: private */
    public void storeBasePrice(Integer amount) {
        this.basePrice = amount;
        checkValidity();
    }

    private void checkValidity() {
        this.validSettingsListener.settingsAreValid(this.basePrice != null && SanitizeUtils.zeroIfNull(this.basePrice) > 0);
    }

    public int getPrice() {
        return SanitizeUtils.zeroIfNull(this.basePrice);
    }

    public String getCurrentCurrencyCode() {
        return this.currencyCode;
    }
}
