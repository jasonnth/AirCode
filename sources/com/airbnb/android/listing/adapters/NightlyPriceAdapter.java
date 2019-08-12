package com.airbnb.android.listing.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.PricingJitneyLogger;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.android.core.models.DynamicPricingControl.DesiredHostingFrequency;
import com.airbnb.android.core.models.DynamicPricingControl.DesiredHostingFrequencyVersion;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingPersonaInput.ListingPersonaAnswer;
import com.airbnb.android.core.utils.RadioRowModelManager;
import com.airbnb.android.core.utils.RadioRowModelManager.Listener;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineFormattedIntegerInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToggleActionRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.ListingGraph;
import com.airbnb.android.listing.enums.ListingDisplayMode;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
import com.airbnb.p027n2.components.SwitchStyle;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.utils.IntegerNumberFormatHelper;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import icepick.State;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;

public class NightlyPriceAdapter extends AirEpoxyAdapter implements InputAdapter {
    private final LinkActionRowEpoxyModel_ aboutFrequencyRow;
    private final NightlyPriceActionListener actionListener;
    @State
    Integer baseInputValue;
    private final InlineFormattedIntegerInputRowEpoxyModel_ basePriceInput;
    private final MicroSectionHeaderEpoxyModel_ basePriceInputTitle;
    private final InlineInputRowEpoxyModel_ currencyInput = new InlineInputRowEpoxyModel_();
    @State
    DesiredHostingFrequency frequency;
    private final List<EpoxyModel<?>> hostingFrequencyModels = Lists.newArrayList();
    @State
    boolean isEditing;
    @State
    boolean isKnownFrequencyVersion;
    @State
    boolean isSmartPricingEnabled;
    private final long listingId;
    LoggingContextFactory loggingContextFactory;
    @State
    Integer maxInputValue;
    private final InlineFormattedIntegerInputRowEpoxyModel_ maxPriceInput;
    @State
    boolean maxPriceInputShowError;
    @State
    Integer minInputValue;
    private final InlineFormattedIntegerInputRowEpoxyModel_ minPriceInput;
    @State
    boolean minPriceInputShowError;
    private final List<EpoxyModel<?>> mlHideableModels = Lists.newArrayList();
    private final ListingDisplayMode mode;
    private final ListingPersonaAnswer occupancyAnswer;
    private final PricingJitneyLogger pricingJitneyLogger;
    private final RadioRowModelManager<DesiredHostingFrequency> radioRowManager = new RadioRowModelManager<>(new Listener<DesiredHostingFrequency>() {
        public void onValueSelected(DesiredHostingFrequency value) {
            NightlyPriceAdapter.this.frequency = value;
            NightlyPriceAdapter.this.initEditing();
        }

        public void onModelUpdated(ToggleActionRowEpoxyModel_ model) {
            NightlyPriceAdapter.this.notifyModelChanged(model);
        }
    });
    private final DynamicPricingControl settings;
    private final SwitchRowEpoxyModel_ smartPricingSwitch;

    public interface NightlyPriceActionListener {
        void hostingFrequencyInfo(DesiredHostingFrequencyVersion desiredHostingFrequencyVersion);

        void showCurrencyOptions(String str);

        void showUpdateAppSnackbar();

        void smartPricingTip();

        void startEditingValues();
    }

    public NightlyPriceAdapter(Context context, Listing listing, DynamicPricingControl settings2, boolean userEnabledSmartPricing, NightlyPriceActionListener actionListener2, ListingDisplayMode mode2, Bundle savedInstanceState) {
        super(true);
        enableDiffing();
        this.actionListener = actionListener2;
        this.mode = mode2;
        this.listingId = listing.getId();
        this.settings = settings2;
        this.occupancyAnswer = listing.getOccupancyPersonaAnswer();
        ((ListingGraph) CoreApplication.instance(context).component()).inject(this);
        this.pricingJitneyLogger = new PricingJitneyLogger(this.loggingContextFactory, mode2 == ListingDisplayMode.LYS ? C2585PricingSettingsPageType.ListYourSpace : C2585PricingSettingsPageType.ManageListing, C2586PricingSettingsSectionType.PricingSettings, this.listingId);
        boolean isSmartPricingAvailable = listing.isSmartPricingAvailable();
        Currency currency = Currency.getInstance(ListingTextUtils.getListingCurrency(listing));
        if (savedInstanceState == null) {
            this.minInputValue = SanitizeUtils.nullIfZero(settings2.getMinPrice());
            this.maxInputValue = SanitizeUtils.nullIfZero(settings2.getMaxPrice());
            this.baseInputValue = SanitizeUtils.nullIfZero(getListingPrice(listing));
            this.isSmartPricingEnabled = userEnabledSmartPricing && isSmartPricingAvailable;
            this.frequency = settings2.getDesiredHostingFrequency();
            this.minPriceInputShowError = false;
            this.maxPriceInputShowError = false;
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        DocumentMarqueeEpoxyModel_ header = new DocumentMarqueeEpoxyModel_().titleRes(mode2 == ListingDisplayMode.LYS ? C7213R.string.ml_demand_based_pricing : C7213R.string.nightly_price_title).captionRes((mode2 == ListingDisplayMode.LYS || !isSmartPricingAvailable) ? 0 : C7213R.string.ml_demand_based_pricing_explanation);
        this.smartPricingSwitch = new SwitchRowEpoxyModel_().titleRes(C7213R.string.smart_pricing_title).style(SwitchStyle.Filled).checked(this.isSmartPricingEnabled).checkedChangeListener(NightlyPriceAdapter$$Lambda$1.lambdaFactory$(this)).showDivider(true).show(mode2 != ListingDisplayMode.LYS && isSmartPricingAvailable);
        LinkActionRowEpoxyModel_ aboutSmartPricingRow = new LinkActionRowEpoxyModel_().textRes(C7213R.string.manage_listing_smart_pricing_more_info).clickListener(NightlyPriceAdapter$$Lambda$2.lambdaFactory$(actionListener2)).show(mode2 != ListingDisplayMode.LYS && isSmartPricingAvailable);
        SectionHeaderEpoxyModel_ priceRangeHeader = new SectionHeaderEpoxyModel_().titleRes(C7213R.string.lys_dls_smart_pricing_price_range_title).descriptionRes(C7213R.string.lys_dls_smart_pricing_price_range_text).show(mode2 == ListingDisplayMode.LYS);
        this.minPriceInput = InlineFormattedIntegerInputRowEpoxyModel_.forCurrency(currency).titleRes(C7213R.string.manage_listing_smart_pricing_min_nightly_price).inputAmount(this.minInputValue).amountChangedListener(NightlyPriceAdapter$$Lambda$3.lambdaFactory$(this)).showError(this.minPriceInputShowError).tipClickListener(NightlyPriceAdapter$$Lambda$4.lambdaFactory$(this, currency, settings2));
        setPriceInputTip(context, this.minPriceInput, listing, currency, settings2.getSuggestedMinPrice());
        this.maxPriceInput = InlineFormattedIntegerInputRowEpoxyModel_.forCurrency(currency).titleRes(C7213R.string.manage_listing_smart_pricing_max_nightly_price).inputAmount(this.maxInputValue).amountChangedListener(NightlyPriceAdapter$$Lambda$5.lambdaFactory$(this)).showError(this.maxPriceInputShowError).tipClickListener(NightlyPriceAdapter$$Lambda$6.lambdaFactory$(this, currency, settings2));
        setPriceInputTip(context, this.maxPriceInput, listing, currency, settings2.getSuggestedMaxPrice());
        SectionHeaderEpoxyModel_ defaultPriceHeader = new SectionHeaderEpoxyModel_().titleRes(C7213R.string.lys_dls_base_price_title).descriptionRes(C7213R.string.lys_dls_base_price_text).show(mode2 == ListingDisplayMode.LYS);
        this.basePriceInputTitle = new MicroSectionHeaderEpoxyModel_().titleRes(C7213R.string.listing_setting_base_price).showDivider(false);
        this.basePriceInput = InlineFormattedIntegerInputRowEpoxyModel_.forCurrency(currency).titleRes(C7213R.string.lys_price_per_night).amountChangedListener(NightlyPriceAdapter$$Lambda$7.lambdaFactory$(this)).inputAmount(this.baseInputValue).tipClickListener(NightlyPriceAdapter$$Lambda$8.lambdaFactory$(this, currency, listing));
        setPriceInputTip(context, this.basePriceInput, listing, currency, listing.getAutoPricingDaily());
        this.currencyInput.titleRes(C7213R.string.listing_currency_title).input(currency.getCurrencyCode()).show(mode2 == ListingDisplayMode.LYS);
        this.currencyInput.clickListener(NightlyPriceAdapter$$Lambda$9.lambdaFactory$(this, actionListener2));
        this.aboutFrequencyRow = new LinkActionRowEpoxyModel_().textRes(C7213R.string.why_it_is_important).clickListener(NightlyPriceAdapter$$Lambda$10.lambdaFactory$(actionListener2, settings2));
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{header, this.smartPricingSwitch, aboutSmartPricingRow, priceRangeHeader, this.minPriceInput, this.maxPriceInput, defaultPriceHeader, this.basePriceInputTitle, this.basePriceInput, this.currencyInput});
        addHostingFrequencyRows();
        addModel(this.aboutFrequencyRow);
        this.mlHideableModels.add(this.minPriceInput);
        this.mlHideableModels.add(this.maxPriceInput);
        this.mlHideableModels.addAll(this.radioRowManager.getModels());
        showOrHideModels();
    }

    static /* synthetic */ void lambda$new$0(NightlyPriceAdapter nightlyPriceAdapter, SwitchRowInterface v, boolean isChecked) {
        nightlyPriceAdapter.setSmartPricingEnabled(isChecked);
        nightlyPriceAdapter.initEditing();
    }

    static /* synthetic */ void lambda$new$2(NightlyPriceAdapter nightlyPriceAdapter, Integer inputAmount) {
        nightlyPriceAdapter.minInputValue = inputAmount;
        nightlyPriceAdapter.initEditing();
    }

    static /* synthetic */ void lambda$new$3(NightlyPriceAdapter nightlyPriceAdapter, Currency currency, DynamicPricingControl settings2, View v) {
        nightlyPriceAdapter.pricingJitneyLogger.adoptSmartPricingMinPriceTip(currency.getCurrencyCode(), (long) settings2.getMinPrice(), (long) settings2.getSuggestedMinPrice(), settings2.toSmartPricingSettingsContext());
        nightlyPriceAdapter.initEditing();
    }

    static /* synthetic */ void lambda$new$4(NightlyPriceAdapter nightlyPriceAdapter, Integer inputAmount) {
        nightlyPriceAdapter.maxInputValue = inputAmount;
        nightlyPriceAdapter.initEditing();
    }

    static /* synthetic */ void lambda$new$5(NightlyPriceAdapter nightlyPriceAdapter, Currency currency, DynamicPricingControl settings2, View v) {
        nightlyPriceAdapter.pricingJitneyLogger.adoptSmartPricingMaxPriceTip(currency.getCurrencyCode(), (long) settings2.getMaxPrice(), (long) settings2.getSuggestedMaxPrice(), settings2.toSmartPricingSettingsContext());
        nightlyPriceAdapter.initEditing();
    }

    static /* synthetic */ void lambda$new$6(NightlyPriceAdapter nightlyPriceAdapter, Integer inputAmount) {
        nightlyPriceAdapter.baseInputValue = inputAmount;
        nightlyPriceAdapter.initEditing();
    }

    static /* synthetic */ void lambda$new$7(NightlyPriceAdapter nightlyPriceAdapter, Currency currency, Listing listing, View v) {
        nightlyPriceAdapter.pricingJitneyLogger.adoptBasePrice(currency.getCurrencyCode(), (long) SanitizeUtils.zeroIfNull(Integer.valueOf(nightlyPriceAdapter.getListingPrice(listing))), (long) listing.getAutoPricingDaily());
        nightlyPriceAdapter.initEditing();
    }

    static /* synthetic */ void lambda$new$8(NightlyPriceAdapter nightlyPriceAdapter, NightlyPriceActionListener actionListener2, View v) {
        actionListener2.showCurrencyOptions((String) nightlyPriceAdapter.currencyInput.input());
        nightlyPriceAdapter.initEditing();
    }

    public void onSaveInstanceState(Bundle outState) {
        this.minPriceInputShowError = this.minPriceInput.showError();
        this.maxPriceInputShowError = this.maxPriceInput.showError();
        super.onSaveInstanceState(outState);
    }

    private void addHostingFrequencyRows() {
        boolean z = false;
        SectionHeaderEpoxyModel_ frequencyHeaderRow = new SectionHeaderEpoxyModel_().titleRes(C7213R.string.manage_listing_smart_pricing_hosting_frequency).descriptionRes(C7213R.string.manage_listing_smart_pricing_hosting_frequency_explanation).showDivider(false);
        List<DesiredHostingFrequency> hostingFrequencyOptions = DesiredHostingFrequency.valuesForFrequencyVersion(this.settings.getDesiredHostingFrequencyVersion());
        for (DesiredHostingFrequency opt : hostingFrequencyOptions) {
            ToggleActionRowEpoxyModel_ row = new ToggleActionRowEpoxyModel_().titleRes(opt.getTitleStringId());
            this.hostingFrequencyModels.add(row);
            this.radioRowManager.addRow(row, opt);
        }
        if (hostingFrequencyOptions.contains(this.frequency)) {
            this.radioRowManager.setSelectedValue(this.frequency);
        } else {
            this.frequency = null;
        }
        this.hostingFrequencyModels.add(frequencyHeaderRow);
        addModel(frequencyHeaderRow);
        addModels(this.radioRowManager.getModels());
        if (!hostingFrequencyOptions.isEmpty()) {
            z = true;
        }
        this.isKnownFrequencyVersion = z;
        if (!this.isKnownFrequencyVersion) {
            this.actionListener.showUpdateAppSnackbar();
        }
    }

    private void showOrHideModels() {
        boolean hideSPBecauseToggle;
        boolean hideFrequencyBecausePersona;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = false;
        if (this.mode == ListingDisplayMode.LYS || this.isSmartPricingEnabled) {
            hideSPBecauseToggle = false;
        } else {
            hideSPBecauseToggle = true;
        }
        if (this.mode != ListingDisplayMode.LYS || this.occupancyAnswer == ListingPersonaAnswer.HOST_UNSURE_OCCUPANCY_ANSWER) {
            hideFrequencyBecausePersona = false;
        } else {
            hideFrequencyBecausePersona = true;
        }
        List<EpoxyModel<?>> list = this.mlHideableModels;
        if (!hideSPBecauseToggle) {
            z = true;
        } else {
            z = false;
        }
        showModels((Iterable<EpoxyModel<?>>) list, z);
        List<EpoxyModel<?>> list2 = this.hostingFrequencyModels;
        if (hideSPBecauseToggle || !this.isKnownFrequencyVersion || hideFrequencyBecausePersona) {
            z2 = false;
        } else {
            z2 = true;
        }
        showModels((Iterable<EpoxyModel<?>>) list2, z2);
        LinkActionRowEpoxyModel_ linkActionRowEpoxyModel_ = this.aboutFrequencyRow;
        if (hideSPBecauseToggle || !this.isKnownFrequencyVersion || this.mode == ListingDisplayMode.LYS) {
            z3 = false;
        } else {
            z3 = true;
        }
        showModel(linkActionRowEpoxyModel_, z3);
        InlineFormattedIntegerInputRowEpoxyModel_ inlineFormattedIntegerInputRowEpoxyModel_ = this.basePriceInput;
        if (this.mode == ListingDisplayMode.LYS || hideSPBecauseToggle) {
            z4 = true;
        }
        showModel(inlineFormattedIntegerInputRowEpoxyModel_, z4);
        showModel(this.basePriceInputTitle, hideSPBecauseToggle);
        notifyModelsChanged();
    }

    public void setInputEnabled(boolean enabled) {
        this.smartPricingSwitch.enabled(enabled);
        this.radioRowManager.setRowsEnabled(enabled);
        this.minPriceInput.enabled(enabled);
        this.maxPriceInput.enabled(enabled);
        this.basePriceInput.enabled(enabled);
        notifyModelsChanged();
    }

    public DynamicPricingControl getNewPricingSettings() {
        DynamicPricingControl newSettings = new DynamicPricingControl();
        newSettings.setListingId(this.listingId);
        newSettings.setIsEnabled(this.isSmartPricingEnabled);
        if (this.isSmartPricingEnabled) {
            newSettings.setMinPrice(SanitizeUtils.zeroIfNull(this.minInputValue));
            newSettings.setMaxPrice(SanitizeUtils.zeroIfNull(this.maxInputValue));
            if (this.frequency != null) {
                newSettings.setDesiredHostingFrequency(this.frequency);
            }
        }
        return newSettings;
    }

    public String getCurrentCurrencyCode() {
        return this.currencyInput.input().toString();
    }

    public int getPrice() {
        return SanitizeUtils.zeroIfNull(this.baseInputValue);
    }

    public boolean isSmartPricingEnabled() {
        return this.isSmartPricingEnabled;
    }

    public boolean hasChanged(Listing listing, DynamicPricingControl settings2) {
        return !Objects.equal(Boolean.valueOf(this.isSmartPricingEnabled), Boolean.valueOf(settings2.isEnabled() && listing.isSmartPricingAvailable())) || !Objects.equal(this.minInputValue, SanitizeUtils.nullIfZero(settings2.getMinPrice())) || !Objects.equal(this.maxInputValue, SanitizeUtils.nullIfZero(settings2.getMaxPrice())) || !Objects.equal(this.baseInputValue, SanitizeUtils.nullIfZero(getListingPrice(listing))) || !Objects.equal(getCurrentCurrencyCode(), ListingTextUtils.getListingCurrency(listing)) || this.frequency != settings2.getDesiredHostingFrequency();
    }

    public void markErrors(boolean showErrors) {
        boolean hasPricingError = showErrors && SanitizeUtils.zeroIfNull(this.minInputValue) >= SanitizeUtils.zeroIfNull(this.maxInputValue);
        this.minPriceInput.showError(hasPricingError);
        this.maxPriceInput.showError(hasPricingError);
        notifyModelsChanged();
    }

    private void setSmartPricingEnabled(boolean isEnabled) {
        this.isSmartPricingEnabled = isEnabled;
        showOrHideModels();
    }

    private void setPriceInputTip(Context context, InlineFormattedIntegerInputRowEpoxyModel_ model, Listing listing, Currency currency, int tipAmount) {
        if (tipAmount > 0) {
            model.tip(context.getString(ListingTextUtils.getTipRes(), new Object[]{CurrencyUtils.formatAmount((double) tipAmount, currency)})).tipAmount(Integer.valueOf(tipAmount));
        }
    }

    private int getListingPrice(Listing listing) {
        return this.mode == ListingDisplayMode.ML ? listing.getListingPriceNative() : listing.getListingPrice();
    }

    public void updateCurrency(Context context, Listing listing, String currencyCode, DynamicPricingControl pricingControl) {
        if (!this.currencyInput.input().equals(currencyCode)) {
            Currency currency = Currency.getInstance(currencyCode);
            NumberFormat numberFormat = IntegerNumberFormatHelper.forCurrency(currency);
            this.currencyInput.input(currencyCode);
            this.minPriceInput.numberFormat(numberFormat).hint(currency.getSymbol()).tipClickListener(NightlyPriceAdapter$$Lambda$11.lambdaFactory$(this, currency));
            this.maxPriceInput.numberFormat(numberFormat).hint(currency.getSymbol()).tipClickListener(NightlyPriceAdapter$$Lambda$12.lambdaFactory$(this, currency));
            this.basePriceInput.numberFormat(numberFormat).hint(currency.getSymbol()).tipClickListener(NightlyPriceAdapter$$Lambda$13.lambdaFactory$(this, currency, listing));
            setPriceInputTip(context, this.basePriceInput, listing, currency, listing.getAutoPricingDaily());
            setPriceInputTip(context, this.minPriceInput, listing, currency, pricingControl.getSuggestedMinPrice());
            setPriceInputTip(context, this.maxPriceInput, listing, currency, pricingControl.getSuggestedMaxPrice());
            clearPriceValues();
            notifyModelsChanged();
        }
    }

    private void clearPriceValues() {
        this.minInputValue = null;
        this.maxInputValue = null;
        this.baseInputValue = null;
        this.minPriceInput.inputAmount(this.minInputValue);
        this.maxPriceInput.inputAmount(this.maxInputValue);
        this.basePriceInput.inputAmount(this.baseInputValue);
    }

    /* access modifiers changed from: private */
    public void initEditing() {
        if (!this.isEditing) {
            this.actionListener.startEditingValues();
            this.isEditing = true;
        }
    }

    public void setIsEditing(boolean isEditing2) {
        this.isEditing = isEditing2;
    }
}
