package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.adapters.HouseRulesEpoxyController;
import com.airbnb.android.core.adapters.HouseRulesEpoxyController.Listener;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.P3ListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.HouseRulesAndExpectationsUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.P4FlowNavigationMethod.p172v1.C2466P4FlowNavigationMethod;
import com.airbnb.jitney.event.logging.P4FlowPage.p173v1.C2467P4FlowPage;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BookingNavigationView;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import p032rx.Observer;

public class BookingHouseRulesFragment extends BookingV2BaseFragment implements Listener {
    private static final String ARG_FOR_LONG_TERM_STAY = "for_long_term_stay";
    private static final String ARG_HAS_PAST_BOOKINGS = "has_past_bookings";
    private static final String ARG_LISTING = "listing";
    @State
    boolean hasPastBookings;
    @State
    User host;
    private HouseRulesEpoxyController houseRulesEpoxyController;
    final RequestListener<ListingResponse> houseRulesResponseRequestListener = new C0699RL().onResponse(BookingHouseRulesFragment$$Lambda$1.lambdaFactory$(this)).onError(BookingHouseRulesFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    boolean isForLongTermStay;
    @State
    Listing listing;
    @BindView
    BookingNavigationView navView;
    @BindView
    AirRecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static BookingHouseRulesFragment newInstance(Reservation reservation) {
        return (BookingHouseRulesFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new BookingHouseRulesFragment()).putParcelable("listing", reservation.getListing())).putBoolean(ARG_HAS_PAST_BOOKINGS, reservation.getGuest().hasPastBookings())).putBoolean(ARG_FOR_LONG_TERM_STAY, HouseRulesAndExpectationsUtils.shouldShowLongTermHouseRules(reservation.getCheckinDate(), reservation.getCheckoutDate()))).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_booking_house_rules, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        setUpNavButton(this.navView, C0704R.string.p4_agree);
        if (getAirActivity() instanceof TransparentActionBarActivity) {
            ((TransparentActionBarActivity) getAirActivity()).getAirToolbar().setVisibility(8);
        }
        if (savedInstanceState == null) {
            Bundle arguments = getArguments();
            Check.notNull(arguments);
            this.hasPastBookings = getArguments().getBoolean(ARG_HAS_PAST_BOOKINGS, true);
            this.isForLongTermStay = getArguments().getBoolean(ARG_FOR_LONG_TERM_STAY, false);
            this.listing = (Listing) arguments.getParcelable("listing");
            Check.notNull(this.listing);
        }
        populateLayout(savedInstanceState);
        return view;
    }

    private void populateLayout(Bundle savedInstanceState) {
        this.houseRulesEpoxyController = new HouseRulesEpoxyController(getContext(), getController().getP4Steps(), this.listing, true, this.hasPastBookings, this.isForLongTermStay, this, savedInstanceState);
        this.recyclerView.setEpoxyControllerAndBuildModels(this.houseRulesEpoxyController);
        this.recyclerView.setHasFixedSize(true);
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

    static /* synthetic */ void lambda$new$2(BookingHouseRulesFragment bookingHouseRulesFragment, AirRequestNetworkException e) {
        if (bookingHouseRulesFragment.getView() != null) {
            new SnackbarWrapper().view(bookingHouseRulesFragment.getView()).action(C0704R.string.retry, BookingHouseRulesFragment$$Lambda$3.lambdaFactory$(bookingHouseRulesFragment)).title(bookingHouseRulesFragment.getView().getResources().getString(C0704R.string.p3_translation_error), true).duration(0).buildAndShow();
        }
    }

    public void fetchTranslation() {
        P3ListingRequest.forTranslatedHouseRules(this.listing.getId()).withListener((Observer) this.houseRulesResponseRequestListener).execute(this.requestManager);
    }

    public void onTranslationToggle(boolean toShowTranslation) {
        AirbnbEventLogger.track("p4", Strap.make().mo11639kv("page", ListingRequestConstants.JSON_HOUSE_RULES_KEY).mo11639kv(BaseAnalytics.OPERATION, "click").mo11639kv(BaseAnalytics.TARGET, toShowTranslation ? "translate" : "see_original_language"));
    }

    public void onUpdateError(NetworkException e) {
    }

    public void onUpdated() {
    }

    public Strap getNavigationTrackingParams() {
        return BookingAnalytics.getP4NavigationTrackingParams(true);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.BookingHouseRules;
    }

    public C2467P4FlowPage getP4FlowPage() {
        return C2467P4FlowPage.BookingHouseRules;
    }

    /* access modifiers changed from: protected */
    public C2466P4FlowNavigationMethod getP4FlowNavigationMethod() {
        return C2466P4FlowNavigationMethod.AgreeButton;
    }
}
