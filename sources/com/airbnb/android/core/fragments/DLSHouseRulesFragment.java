package com.airbnb.android.core.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.adapters.HouseRulesEpoxyController;
import com.airbnb.android.core.adapters.HouseRulesEpoxyController.Listener;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.P3ListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.HouseRulesAndExpectationsUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import p032rx.Observer;

public class DLSHouseRulesFragment extends AirDialogFragment implements Listener {
    private static final String ARG_FOR_BOOKING = "for_booking";
    private static final String ARG_FOR_LONG_TERM_STAY = "for_long_term_stay";
    private static final String ARG_HAS_PAST_BOOKINGS = "has_past_bookings";
    private static final String ARG_LISTING = "listing";
    @State
    boolean forBooking;
    @State
    boolean hasPastBookings;
    private HouseRulesEpoxyController houseRulesEpoxyController;
    final RequestListener<ListingResponse> houseRulesResponseRequestListener = new C0699RL().onResponse(DLSHouseRulesFragment$$Lambda$1.lambdaFactory$(this)).onError(DLSHouseRulesFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    boolean isForLongTermStay;
    @State
    Listing listing;
    @BindView
    PrimaryButton primaryButton;
    @BindView
    AirRecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static DLSHouseRulesFragment newInstance(Listing listing2, AirDate checkin, AirDate checkout) {
        return (DLSHouseRulesFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new DLSHouseRulesFragment()).putParcelable("listing", listing2)).putBoolean(ARG_FOR_LONG_TERM_STAY, HouseRulesAndExpectationsUtils.shouldShowLongTermHouseRules(checkin, checkout))).build();
    }

    public static DLSHouseRulesFragment instanceForBooking(Reservation reservation) {
        return (DLSHouseRulesFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new DLSHouseRulesFragment()).putParcelable("listing", reservation.getListing())).putBoolean(ARG_FOR_BOOKING, true)).putBoolean(ARG_HAS_PAST_BOOKINGS, reservation.getGuest().hasPastBookings())).putBoolean(ARG_FOR_LONG_TERM_STAY, HouseRulesAndExpectationsUtils.shouldShowLongTermHouseRules(reservation.getCheckinDate(), reservation.getCheckoutDate()))).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0716R.layout.fragment_dls_house_rules, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        if (getAirActivity() instanceof TransparentActionBarActivity) {
            ((TransparentActionBarActivity) getAirActivity()).getAirToolbar().setVisibility(8);
        }
        if (savedInstanceState == null) {
            this.listing = (Listing) getArguments().getParcelable("listing");
            this.forBooking = getArguments().getBoolean(ARG_FOR_BOOKING);
            this.hasPastBookings = getArguments().getBoolean(ARG_HAS_PAST_BOOKINGS, true);
            this.isForLongTermStay = getArguments().getBoolean(ARG_FOR_LONG_TERM_STAY, false);
        }
        Check.notNull(this.listing);
        populateLayout(savedInstanceState);
        return view;
    }

    private void populateLayout(Bundle savedInstanceState) {
        this.houseRulesEpoxyController = new HouseRulesEpoxyController(getContext(), null, this.listing, this.forBooking, this.hasPastBookings, this.isForLongTermStay, this, savedInstanceState);
        this.recyclerView.setEpoxyControllerAndBuildModels(this.houseRulesEpoxyController);
        this.recyclerView.setHasFixedSize(true);
        ViewUtils.setVisibleIf((View) this.primaryButton, this.forBooking);
    }

    public void fetchTranslation() {
        P3ListingRequest.forTranslatedHouseRules(this.listing.getId()).withListener((Observer) this.houseRulesResponseRequestListener).execute(this.requestManager);
    }

    public void onTranslationToggle(boolean toShowTranslation) {
        AirbnbEventLogger.track(this.forBooking ? "p4" : "p3", Strap.make().mo11639kv("page", ListingRequestConstants.JSON_HOUSE_RULES_KEY).mo11639kv(BaseAnalytics.OPERATION, "click").mo11639kv(BaseAnalytics.TARGET, toShowTranslation ? "translate" : "see_original_language"));
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.houseRulesEpoxyController.onSaveInstanceState(outState);
    }

    @OnClick
    public void confirmReadingHouseRules() {
        getActivity().setResult(-1);
        getActivity().finish();
    }

    static /* synthetic */ void lambda$new$2(DLSHouseRulesFragment dLSHouseRulesFragment, AirRequestNetworkException e) {
        if (dLSHouseRulesFragment.getView() != null) {
            new SnackbarWrapper().view(dLSHouseRulesFragment.getView()).action(C0716R.string.retry, DLSHouseRulesFragment$$Lambda$3.lambdaFactory$(dLSHouseRulesFragment)).title(dLSHouseRulesFragment.getView().getResources().getString(C0716R.string.p3_translation_error), true).duration(0).buildAndShow();
        }
    }

    public boolean isLeafDialog() {
        return true;
    }
}
