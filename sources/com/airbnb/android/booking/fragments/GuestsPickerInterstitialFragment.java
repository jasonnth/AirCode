package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.interfaces.UpdateRequestListener;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.views.guestpicker.GuestsPickerView;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;

public class GuestsPickerInterstitialFragment extends BookingBaseFragment implements UpdateRequestListener {
    private static final String ARG_GUEST_DATA = "arg_guest_data";
    private static final String ARG_LISTING = "arg_listing";
    private static final String ARG_SHOW_BLOCK_IB_WARNING = "arg_show_block_ib_warning";
    private static final String ARG_SOURCE_TAG = "arg_source_tag";
    private static final int BOOKING_ADULT_COUNT_MIN = 1;
    public static final String TAG = GuestsPickerInterstitialFragment.class.getSimpleName();
    @BindView
    GuestsPickerView guestsPickerView;
    @BindView
    DocumentMarquee marquee;
    @BindView
    AirToolbar toolbar;

    public static class GuestsPickerInterstitialFragmentBuilder {
        private final GuestDetails guestDetails;
        private Listing listing;
        private boolean showBlockInstantBookWarning = false;
        private final String sourceTag;

        public GuestsPickerInterstitialFragmentBuilder(GuestDetails guestDetails2, String sourceTag2) {
            this.guestDetails = guestDetails2;
            this.sourceTag = sourceTag2;
        }

        public GuestsPickerInterstitialFragmentBuilder setListing(Listing listing2) {
            this.listing = listing2;
            return this;
        }

        public GuestsPickerInterstitialFragmentBuilder setShowBlockInstantBookWarning(boolean showBlockInstantBookWarning2) {
            this.showBlockInstantBookWarning = showBlockInstantBookWarning2;
            return this;
        }

        public GuestsPickerInterstitialFragment build() {
            return (GuestsPickerInterstitialFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new GuestsPickerInterstitialFragment()).putString(GuestsPickerInterstitialFragment.ARG_SOURCE_TAG, this.sourceTag)).putParcelable(GuestsPickerInterstitialFragment.ARG_GUEST_DATA, this.guestDetails)).putParcelable(GuestsPickerInterstitialFragment.ARG_LISTING, this.listing)).putBoolean(GuestsPickerInterstitialFragment.ARG_SHOW_BLOCK_IB_WARNING, this.showBlockInstantBookWarning)).build();
        }
    }

    public static GuestsPickerInterstitialFragment newInstance() {
        return new GuestsPickerInterstitialFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0704R.layout.fragment_guests_picker_interstitial, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.marquee.setCaption((CharSequence) this.guestsPickerView.getVerboseMaxGuestsDescription());
        if (savedInstanceState == null) {
            this.guestsPickerView.setGuestData((GuestDetails) getArguments().getParcelable(ARG_GUEST_DATA));
        }
        this.guestsPickerView.setShowBlockInstantBookWarning(getArguments().getBoolean(ARG_SHOW_BLOCK_IB_WARNING, false));
        Listing listing = (Listing) getArguments().getParcelable(ARG_LISTING);
        if (listing != null) {
            this.guestsPickerView.setMaxGuestsCount(listing.getPersonCapacity());
            if (listing.getGuestControls() != null) {
                this.guestsPickerView.setGuestControls(listing.getGuestControls());
            }
        }
        this.guestsPickerView.setMinNumberAdults(1);
        return view;
    }

    @OnClick
    public void onContinueClicked() {
        getBookingActivity().doneWithGuestPicking(this.guestsPickerView.getGuestData());
    }

    public void onPause() {
        this.guestsPickerView.dismissAllSnackBars();
        super.onPause();
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11637kv(FindTweenAnalytics.GUESTS, this.guestsPickerView.getNumberAdults()).mo11640kv(FindTweenAnalytics.PETS, this.guestsPickerView.hasPets()).mo11639kv(BaseAnalytics.FROM, getArguments().getString(ARG_SOURCE_TAG));
    }
}
