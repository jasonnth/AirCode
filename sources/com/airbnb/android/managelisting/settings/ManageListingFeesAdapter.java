package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineFormattedIntegerInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.views.OptionsMenuFactory;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Objects;
import icepick.State;
import java.util.Currency;

public class ManageListingFeesAdapter extends AirEpoxyAdapter {
    /* access modifiers changed from: 0000 */
    @State
    public Integer cleaningFee;
    private final InlineFormattedIntegerInputRowEpoxyModel_ cleaningFeeInput;
    private final Context context;
    private final InlineFormattedIntegerInputRowEpoxyModel_ feePerPersonInput;
    private final InlineInputRowEpoxyModel_ guestsIncludedRow;
    /* access modifiers changed from: 0000 */
    @State
    public Integer includedGuestPrice;
    @State
    int includedGuests;
    private final Listing listing;
    /* access modifiers changed from: 0000 */
    @State
    public Integer securityDeposit;
    private final InlineFormattedIntegerInputRowEpoxyModel_ securityDepositInput;
    /* access modifiers changed from: 0000 */
    @State
    public Integer weekendPrice;
    private final InlineFormattedIntegerInputRowEpoxyModel_ weekendPriceInput;

    ManageListingFeesAdapter(Context context2, Listing listing2, Bundle savedInstanceState) {
        super(true);
        enableDiffing();
        onRestoreInstanceState(savedInstanceState);
        this.context = context2;
        this.listing = listing2;
        if (savedInstanceState == null) {
            this.includedGuests = listing2.getGuestsIncluded();
            this.includedGuestPrice = SanitizeUtils.nullIfZero(listing2.getListingPriceForExtraPersonNative());
            this.cleaningFee = listing2.getListingCleaningFeeNative();
            this.weekendPrice = SanitizeUtils.nullIfZero(listing2.getListingWeekendPriceNative());
            this.securityDeposit = listing2.getListingSecurityDepositNative();
        }
        DocumentMarqueeEpoxyModel_ sectionHeader = new DocumentMarqueeEpoxyModel_().titleRes(C7368R.string.manage_listing_fees_title);
        Currency currency = Currency.getInstance(listing2.getListingNativeCurrency());
        String zeroHintText = CurrencyUtils.formatAmount(0.0d, currency);
        this.securityDepositInput = InlineFormattedIntegerInputRowEpoxyModel_.forCurrency(currency).titleRes(C7368R.string.manage_listing_fees_security_deposit_label).inputAmount(this.securityDeposit).hint(zeroHintText).amountChangedListener(ManageListingFeesAdapter$$Lambda$1.lambdaFactory$(this));
        this.cleaningFeeInput = InlineFormattedIntegerInputRowEpoxyModel_.forCurrency(currency).titleRes(C7368R.string.manage_listing_fees_cleaning_fee).inputAmount(this.cleaningFee).hint(zeroHintText).amountChangedListener(ManageListingFeesAdapter$$Lambda$2.lambdaFactory$(this));
        this.weekendPriceInput = InlineFormattedIntegerInputRowEpoxyModel_.forCurrency(currency).titleRes(C7368R.string.manage_listing_fees_weekend_price).subTitleRes(C7368R.string.manage_listing_fees_weekend_price_sublabel).inputAmount(this.weekendPrice).amountChangedListener(ManageListingFeesAdapter$$Lambda$3.lambdaFactory$(this));
        SectionHeaderEpoxyModel_ extraGuestsHeader = new SectionHeaderEpoxyModel_().titleRes(C7368R.string.manage_listing_fees_extra_guests_title);
        this.feePerPersonInput = InlineFormattedIntegerInputRowEpoxyModel_.forCurrency(currency).titleRes(C7368R.string.manage_listing_fees_extra_guests_price).inputAmount(this.includedGuestPrice).hint(zeroHintText).amountChangedListener(ManageListingFeesAdapter$$Lambda$4.lambdaFactory$(this));
        this.guestsIncludedRow = new InlineInputRowEpoxyModel_().titleRes(C7368R.string.manage_listing_fees_extra_guests_count).input(ListingTextUtils.createCountWithMaxPlus(context2, this.includedGuests, 16)).clickListener(ManageListingFeesAdapter$$Lambda$5.lambdaFactory$(this));
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{sectionHeader, this.securityDepositInput, this.cleaningFeeInput, this.weekendPriceInput, extraGuestsHeader, this.feePerPersonInput, this.guestsIncludedRow});
    }

    public void onSaveInstanceState(Bundle outState) {
        this.includedGuestPrice = this.feePerPersonInput.inputAmount();
        this.cleaningFee = this.cleaningFeeInput.inputAmount();
        this.weekendPrice = this.weekendPriceInput.inputAmount();
        this.securityDeposit = this.securityDepositInput.inputAmount();
        super.onSaveInstanceState(outState);
    }

    public void setEnabled(boolean enabled) {
        this.securityDepositInput.enabled(enabled);
        this.cleaningFeeInput.enabled(enabled);
        this.weekendPriceInput.enabled(enabled);
        this.feePerPersonInput.enabled(enabled);
        this.guestsIncludedRow.enabled(enabled);
        notifyModelsChanged();
    }

    public boolean hasChanged() {
        return !Objects.equal(this.securityDeposit, this.listing.getListingSecurityDepositNative()) || !Objects.equal(this.cleaningFee, this.listing.getListingCleaningFeeNative()) || !Objects.equal(this.weekendPrice, SanitizeUtils.nullIfZero(this.listing.getListingWeekendPriceNative())) || !Objects.equal(this.includedGuestPrice, SanitizeUtils.nullIfZero(this.listing.getListingPriceForExtraPersonNative())) || !Objects.equal(Integer.valueOf(this.includedGuests), Integer.valueOf(this.listing.getGuestsIncluded()));
    }

    public Strap getFeeSettings() {
        return Strap.make().mo11637kv(ListingRequestConstants.JSON_CLEANING_FEE_KEY, SanitizeUtils.zeroIfNull(this.cleaningFee)).mo11637kv(ListingRequestConstants.JSON_WEEKEND_PRICING_KEY, SanitizeUtils.zeroIfNull(this.weekendPrice)).mo11637kv(ListingRequestConstants.JSON_EXTRA_GUEST_PRICE_KEY, SanitizeUtils.zeroIfNull(this.includedGuestPrice)).mo11637kv(ListingRequestConstants.JSON_GUESTS_INCLUDED_KEY, this.includedGuests).mo11637kv(ListingRequestConstants.JSON_SECURITY_DEPOSIT_KEY, SanitizeUtils.zeroIfNull(this.securityDeposit));
    }

    /* access modifiers changed from: private */
    public void showGuestOptionsMenu() {
        OptionsMenuFactory.forIntRange(this.context, 1, 16).setTitleTransformer(ManageListingFeesAdapter$$Lambda$6.lambdaFactory$(this)).setListener(ManageListingFeesAdapter$$Lambda$7.lambdaFactory$(this)).buildAndShow();
    }

    static /* synthetic */ void lambda$showGuestOptionsMenu$6(ManageListingFeesAdapter manageListingFeesAdapter, Integer includedGuests2) {
        manageListingFeesAdapter.includedGuests = includedGuests2.intValue();
        manageListingFeesAdapter.guestsIncludedRow.input(ListingTextUtils.createGuestsCount(manageListingFeesAdapter.context, includedGuests2.intValue()));
        manageListingFeesAdapter.notifyModelChanged(manageListingFeesAdapter.guestsIncludedRow);
    }
}
