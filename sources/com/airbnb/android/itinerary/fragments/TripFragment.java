package com.airbnb.android.itinerary.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.adapters.ItineraryEpoxyController;
import com.airbnb.android.itinerary.animation.ItineraryItemAnimator;
import com.airbnb.android.itinerary.controllers.ItineraryNavigationController;
import com.airbnb.android.itinerary.data.models.TripEvent;
import com.airbnb.android.itinerary.listeners.ItineraryDataChangedListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LoadingView;
import com.google.common.collect.ImmutableList;
import icepick.State;
import java.util.ArrayList;
import java.util.Collections;

public class TripFragment extends ItineraryBaseFragment implements OnBackListener, ItineraryDataChangedListener {
    public static final String EXTRA_CONFIRMATION_CODE = "extra_confirmation_code";
    public static final String EXTRA_LOCATION = "extra_location";
    public static final String EXTRA_SHOW_NOW_INDICATOR = "extra_show_now_indicator";
    public static final String TAG = "TripFragment";
    @State
    String confirmationCode;
    private ItineraryEpoxyController itineraryEpoxyController;
    private final ItineraryItemAnimator itineraryItemAnimator = new ItineraryItemAnimator();
    private Runnable loadingRunnable = TripFragment$$Lambda$1.lambdaFactory$(this);
    @BindView
    LoadingView loadingView;
    @State
    String location;
    @BindView
    AirRecyclerView recyclerView;
    @State
    boolean showNowIndicator;
    @BindView
    AirToolbar toolbar;
    @State
    ArrayList<TripEvent> tripEvents;

    public static TripFragment instance(String confirmationCode2, String location2, boolean showNowIndicator2) {
        return (TripFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new TripFragment()).putString("extra_confirmation_code", confirmationCode2)).putString(EXTRA_LOCATION, location2)).putBoolean(EXTRA_SHOW_NOW_INDICATOR, showNowIndicator2)).build();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ItineraryReservationGroup;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadStateForArguments();
        }
        View view = inflater.inflate(C5755R.layout.fragment_trip, container, false);
        bindViews(view);
        setupToolbar();
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.itineraryEpoxyController = new ItineraryEpoxyController(ItineraryNavigationController.FRAGMENT_TRIP_TAG, this.itineraryDataController, this.itineraryNavigationController, this.itineraryPerformanceAnalytics, this.itineraryJitneyLogger, false, this.showNowIndicator);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setEpoxyController(this.itineraryEpoxyController);
        this.itineraryItemAnimator.setItineraryNavigationController(this.itineraryNavigationController);
        this.recyclerView.setItemAnimator(this.itineraryItemAnimator);
        if (FeatureToggles.showSuggestionsInNativeItinerary()) {
            this.itineraryDataController.fetchSuggestionsFromNetwork(this.confirmationCode);
        }
    }

    public void onResume() {
        super.onResume();
        setupUI();
    }

    public void onDestroyView() {
        if (this.loadingView != null) {
            this.loadingView.removeCallbacks(this.loadingRunnable);
        }
        super.onDestroyView();
    }

    private void setupToolbar() {
        setToolbar(this.toolbar);
        this.toolbar.setTitle((CharSequence) this.location);
        this.toolbar.setNavigationOnClickListener(TripFragment$$Lambda$2.lambdaFactory$(this));
    }

    private void loadStateForArguments() {
        Bundle bundle = getArguments();
        if (bundle != null && !bundle.isEmpty()) {
            this.confirmationCode = bundle.getString("extra_confirmation_code");
            this.location = bundle.getString(EXTRA_LOCATION);
            this.showNowIndicator = bundle.getBoolean(EXTRA_SHOW_NOW_INDICATOR);
            this.itineraryItemAnimator.setUseCustomAnimator(true);
            bundle.clear();
        }
    }

    private void setupUI() {
        this.itineraryDataController.fetchTripEvents(this.confirmationCode);
        this.loadingView.postDelayed(this.loadingRunnable, 100);
    }

    public void onTripContentUpdated() {
        this.loadingView.removeCallbacks(this.loadingRunnable);
        this.loadingView.setVisibility(8);
        if (ListUtils.isEmpty(this.itineraryDataController.getTripEventsList())) {
            getActivity().onBackPressed();
        } else {
            this.itineraryEpoxyController.setData(ImmutableList.copyOf(this.itineraryDataController.getTripEventsList()), Boolean.valueOf(true));
        }
    }

    public boolean onBackPressed() {
        ((ItineraryItemAnimator) this.recyclerView.getItemAnimator()).setUseCustomAnimator(true);
        this.itineraryEpoxyController.setData(Collections.emptyList(), Boolean.valueOf(true));
        return true;
    }
}
