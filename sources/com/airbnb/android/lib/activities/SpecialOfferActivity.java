package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.controllers.CalendarViewCallbacks;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.datepicker.DatesFragment;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerController;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerControllerProvider;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerFragmentBuilder;
import com.airbnb.android.core.interfaces.UpdateRequestListener;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.SouthKoreanCancellationPolicyFragment;
import com.airbnb.android.lib.fragments.SouthKoreanCancellationPolicyFragment.Listener;
import com.airbnb.android.lib.fragments.SpecialOfferFragment;
import com.airbnb.android.lib.fragments.find.ListingSelectionFragment;
import icepick.State;

public class SpecialOfferActivity extends AirActivity implements CalendarViewCallbacks, GuestPickerControllerProvider, Listener, SpecialOfferFragment.Listener, ListingSelectionFragment.Listener {
    public static final String ARG_PROVIDER = "trip_provider";
    private static final String SK_CANCELLATION_TAG = SouthKoreanCancellationPolicyFragment.class.getSimpleName();
    CurrencyFormatter currencyHelper;
    @State
    AirDate endDate;
    private final GuestPickerController guestPickerController = new GuestPickerController() {
        public NavigationTag getNavigationAnalyticsTag() {
            return NavigationTag.SpecialOffer;
        }

        public void onGuestDetailsSaved(GuestDetails guestData, UpdateRequestListener listener) {
            SpecialOfferActivity.this.numGuests = guestData.totalGuestCount();
            SpecialOfferActivity.this.getSupportFragmentManager().popBackStack();
        }
    };
    @State
    int initialPrice;
    @State
    int maxGuests;
    @State
    int numGuests;
    @State
    TripInformationProvider provider;
    @State
    Listing selectedListing;
    @State
    AirDate startDate;

    public static Intent intentForProvider(Context context, TripInformationProvider provider2) {
        Check.notNull(provider2);
        return new Intent(context, SpecialOfferActivity.class).putExtra(ARG_PROVIDER, provider2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        ButterKnife.bind((Activity) this);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        if (savedInstanceState == null) {
            this.provider = (TripInformationProvider) getIntent().getParcelableExtra(ARG_PROVIDER);
            this.startDate = this.provider.getStartDate();
            this.endDate = this.provider.getEndDate();
            this.numGuests = this.provider.getGuestCount();
            this.maxGuests = this.provider.getListing().getPersonCapacity();
            this.initialPrice = this.provider.getHostSubtotalAmount();
            this.selectedListing = this.provider.getListing();
            if (this.provider.isKoreanStrictBooking()) {
                showFragment(SouthKoreanCancellationPolicyFragment.newInstance(C0880R.string.special_offer_reservation_south_korean_cancellation_policy, C0880R.string.preapprove_south_korean_cancellation_policy_host_agreement, this.provider), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, false, SK_CANCELLATION_TAG);
                return;
            }
            showSpecialOfferFragment(false);
        }
    }

    public void onAcceptCancellationAgreement() {
        showSpecialOfferFragment(true);
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public void goToChangeListingFlow() {
        if (this.provider.getPrimaryHost().getListingsCount() > 1) {
            showFragment(ListingSelectionFragment.newInstance(this.accountManager.getCurrentUser(), this.selectedListing), C0880R.C0882id.content_container, FragmentTransitionType.SlideFromBottom, true);
        }
    }

    public void goToChangeDatesFlow() {
        showModal(DatesFragment.forListing(this.selectedListing, this.startDate, this.endDate, NavigationTag.SpecialOffer), C0880R.C0882id.content_container, C0880R.C0882id.modal_container, true);
    }

    public void goToChangeNumGuestsFlow() {
        showModal(new GuestPickerFragmentBuilder(new GuestDetails().adultsCount(this.numGuests), NavigationTag.SpecialOffer.trackingName).forTotalNumberOfGuests(true).showMaxGuestsDescription(false).setMaxNumberOfGuests(this.maxGuests).build(), C0880R.C0882id.content_container, C0880R.C0882id.modal_container, true);
    }

    public void goToSuccessfulSubmit() {
        setResult(-1);
        finish();
    }

    public AirDate getStartDate() {
        return this.startDate;
    }

    public AirDate getEndDate() {
        return this.endDate;
    }

    public long getThreadId() {
        return this.provider.getThreadId();
    }

    public int getNumGuests() {
        return this.numGuests;
    }

    public int getInitialPrice() {
        return this.initialPrice;
    }

    public String getCurrency() {
        return this.provider.getHostSubtotalCurrency();
    }

    public String getFormattedPrice() {
        return this.provider.getHostSubtotalFormatted();
    }

    public Listing getListing() {
        return this.selectedListing;
    }

    public void onCalendarDatesApplied(AirDate start, AirDate end) {
        this.startDate = start;
        this.endDate = end;
        getSupportFragmentManager().popBackStack();
    }

    public void onStartDateClicked(AirDate start) {
    }

    public void onEndDateClicked(AirDate end) {
    }

    public GuestPickerController getGuestPickerController() {
        return this.guestPickerController;
    }

    public void listingChanged(Listing listing) {
        this.selectedListing = listing;
    }

    private void showSpecialOfferFragment(boolean hostAgreedSouthKoreanSpecialOffer) {
        showFragment(SpecialOfferFragment.newInstance(hostAgreedSouthKoreanSpecialOffer), C0880R.C0882id.content_container, FragmentTransitionType.SlideFromBottom, true);
    }
}
