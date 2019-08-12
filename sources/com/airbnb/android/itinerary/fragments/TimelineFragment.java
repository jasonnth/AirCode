package com.airbnb.android.itinerary.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.TimelineMetadata;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.adapters.ItineraryEpoxyController;
import com.airbnb.android.itinerary.controllers.ItineraryNavigationController;
import com.airbnb.android.itinerary.data.models.TimelineTrip;
import com.airbnb.android.itinerary.listeners.ItineraryDataChangedListener;
import com.airbnb.android.itinerary.utils.ItineraryUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.primitives.AirTextView;
import java.util.List;

public class TimelineFragment extends ItineraryBaseFragment implements ItineraryDataChangedListener {
    private static final String EXTRA_FIRST_LOAD = "extra_first_load";
    public static final String TAG = "TimelineFragment";
    @BindView
    LottieAnimationView animationImage;
    @BindView
    LinearLayout emptyState;
    private boolean isFirstLoad;
    private ItineraryEpoxyController itineraryEpoxyController;
    private Runnable loadingRunnable = TimelineFragment$$Lambda$1.lambdaFactory$(this);
    @BindView
    LoadingView loadingView;
    @BindView
    AirTextView pendingAction;
    @BindView
    LinearLayout pendingHeader;
    @BindView
    AirTextView pendingTitle;
    @BindView
    AirRecyclerView recyclerView;

    public static TimelineFragment instance(boolean isFirstLoad2) {
        return (TimelineFragment) ((FragmentBundleBuilder) FragmentBundler.make(new TimelineFragment()).putBoolean(EXTRA_FIRST_LOAD, isFirstLoad2)).build();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ItineraryTimeline;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loadStateForArguments();
        }
        View view = inflater.inflate(C5755R.layout.fragment_timeline, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.itineraryEpoxyController = new ItineraryEpoxyController(ItineraryNavigationController.FRAGMENT_TIMELINE_TAG, this.itineraryDataController, this.itineraryNavigationController, this.itineraryPerformanceAnalytics, this.itineraryJitneyLogger, true, true);
        ((LinearLayoutManager) this.recyclerView.getLayoutManager()).setReverseLayout(true);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setEpoxyController(this.itineraryEpoxyController);
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

    private void loadStateForArguments() {
        Bundle bundle = getArguments();
        if (bundle != null && !bundle.isEmpty()) {
            this.isFirstLoad = bundle.getBoolean(EXTRA_FIRST_LOAD);
            bundle.clear();
            this.itineraryJitneyLogger.trackItineraryImpressionOverviewEvent();
            this.itineraryPerformanceAnalytics.trackTimelineLoadStart();
        }
    }

    @OnClick
    public void onEmptyStateClicked() {
        this.itineraryJitneyLogger.trackClickStartExploringEvent();
        startActivity(HomeActivityIntents.intentForGuestHome(getContext()));
    }

    private void setupUI() {
        if (!ListUtils.isEmpty(this.itineraryDataController.getTimelineTrips())) {
            this.itineraryEpoxyController.setData(this.itineraryDataController.getUnbundledTimelineTrips(this.itineraryDataController.getTimelineTrips()), Boolean.valueOf(false));
        }
        this.itineraryDataController.fetchPendingTimelineTrips();
        this.itineraryDataController.fetchTimelineTrips(true);
        this.loadingView.postDelayed(this.loadingRunnable, 100);
    }

    private void setEmptyState(boolean visible) {
        int i = 8;
        this.emptyState.setVisibility(visible ? 0 : 8);
        AirRecyclerView airRecyclerView = this.recyclerView;
        if (!visible) {
            i = 0;
        }
        airRecyclerView.setVisibility(i);
        if (!visible) {
            this.animationImage.cancelAnimation();
        }
    }

    public void onTimelineContentUpdated(boolean hasNewContent) {
        boolean z;
        this.loadingView.removeCallbacks(this.loadingRunnable);
        this.loadingView.setVisibility(8);
        if (this.itineraryDataController.getTimelineTripCount() == 0) {
            z = true;
        } else {
            z = false;
        }
        setEmptyState(z);
        this.itineraryEpoxyController.setData(this.itineraryDataController.getUnbundledTimelineTrips(this.itineraryDataController.getTimelineTrips()), Boolean.valueOf(hasNewContent));
        if (this.isFirstLoad) {
            this.isFirstLoad = false;
            ((LinearLayoutManager) this.recyclerView.getLayoutManager()).scrollToPositionWithOffset(this.itineraryEpoxyController.getUpcomingItemPosition(), getResources().getDimensionPixelSize(C5755R.dimen.timeline_scroll_offset));
        }
    }

    public void onPendingContentUpdated() {
        List<TimelineTrip> pendingTimelineTrips = this.itineraryDataController.getPendingTimelineTripList();
        TimelineMetadata pendingMetadata = this.itineraryDataController.getPendingMetadata();
        String pendingText = ItineraryUtils.getPendingHeaderTitle(getContext(), pendingMetadata, pendingTimelineTrips);
        if (!TextUtils.isEmpty(pendingText)) {
            this.pendingHeader.setVisibility(0);
            this.pendingTitle.setText(pendingText);
            this.pendingAction.setText(ItineraryUtils.getPendingHeaderButtonText(getContext(), pendingMetadata, pendingTimelineTrips));
            this.pendingAction.setOnClickListener(TimelineFragment$$Lambda$2.lambdaFactory$(this, pendingTimelineTrips, pendingMetadata));
            return;
        }
        this.pendingHeader.setVisibility(8);
    }
}
