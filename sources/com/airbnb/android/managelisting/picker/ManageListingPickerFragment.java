package com.airbnb.android.managelisting.picker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline.Event;
import com.airbnb.android.core.controllers.BottomBarController;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.CheckinIntents;
import com.airbnb.android.core.intents.FixItIntents;
import com.airbnb.android.core.intents.ListYourSpaceIntents;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.models.ListingPickerInfo;
import com.airbnb.android.core.requests.ListingPickerInfoRequest;
import com.airbnb.android.core.responses.ListingPickerInfoResponse;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.List;
import p032rx.Observer;

public class ManageListingPickerFragment extends AirFragment {
    private static final int ACTIVITY_REQUEST_CODE_DLS_ML = 101;
    private static final int ACTIVITY_REQUEST_CODE_REQUIRES_RELOAD = 100;
    private final Listener adapterListener = new Listener() {
        public void newListing() {
            ManageListingPickerFragment.this.startListYourSpace();
        }

        public void continueNewListing(long listingId) {
            ManageListingPickerFragment.this.startActivityWhichChangesListings(ListYourSpaceIntents.intentForInProgressListing(ManageListingPickerFragment.this.getContext(), listingId, "ManageListingPicker", "ListingRow"));
        }

        public void manageListing(long listingId) {
            ManageListingPickerFragment.this.startActivityForResult(ManageListingIntents.intentForExistingListing(ManageListingPickerFragment.this.getContext(), listingId), 101);
        }

        public void fixItReport(long reportId, String listingName) {
            ManageListingPickerFragment.this.startActivity(FixItIntents.intentForReportId(ManageListingPickerFragment.this.getContext(), reportId, listingName));
        }
    };
    @BindView
    View blockingOverlay;
    BottomBarController bottomBarController;
    private ManageListingPickerEpoxyController epoxyController;
    final RequestListener<ListingPickerInfoResponse> listingPickerInfoListener = new C0699RL().onResponse(ManageListingPickerFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingPickerFragment$$Lambda$2.lambdaFactory$(this)).onComplete(ManageListingPickerFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    AirRecyclerView recyclerView;
    SharedPrefsHelper sharedPrefsHelper;
    @BindView
    AirSwipeRefreshLayout swipeRefreshLayout;
    @BindView
    AirToolbar toolbar;

    public static AirFragment create() {
        return new ManageListingPickerFragment();
    }

    static /* synthetic */ void lambda$new$0(ManageListingPickerFragment manageListingPickerFragment, ListingPickerInfoResponse response) {
        manageListingPickerFragment.epoxyController.setListings(response.getListings());
        if (!manageListingPickerFragment.sharedPrefsHelper.hasSeenNuxFlowForFeature(AirbnbConstants.PREFS_CHECK_IN_GUIDE_NUX) && response.getListings().size() > 0 && FeatureToggles.showHostCheckinGuideTool()) {
            List<ListingPickerInfo> hasEverBeenAvailableListings = response.getHasEverBeenAvailableListings();
            manageListingPickerFragment.startActivity(CheckinIntents.intentForCheckInNux(manageListingPickerFragment.getContext(), hasEverBeenAvailableListings.size() == 1 ? Long.valueOf(((ListingPickerInfo) hasEverBeenAvailableListings.get(0)).getId()) : null));
        }
        PerformanceLoggerTimeline.logTimeToInteraction(Event.HOST_MANAGE_LISTING_PICKER);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ManageListingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7368R.layout.fragment_manage_listings, container, false);
        bindViews(view);
        setHasOptionsMenu(true);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(0);
        this.epoxyController = new ManageListingPickerEpoxyController(getContext(), this.adapterListener, savedInstanceState);
        this.recyclerView.setEpoxyControllerAndBuildModels(this.epoxyController);
        this.swipeRefreshLayout.setScrollableChild(this.recyclerView);
        this.swipeRefreshLayout.setOnRefreshListener(ManageListingPickerFragment$$Lambda$4.lambdaFactory$(this));
        if (savedInstanceState == null) {
            makeListingInfoRequest(false);
        }
        return view;
    }

    public void onStart() {
        super.onStart();
        this.bottomBarController.setShowBottomBar(true, true);
        showBlockingOverlayIfSuspended();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.epoxyController.onSaveInstanceState(outState);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7368R.C7370id.menu_create_listing) {
            return false;
        }
        startListYourSpace();
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean reloadListings = false;
        switch (requestCode) {
            case 100:
                reloadListings = true;
                break;
            case 101:
                if (resultCode == 0) {
                    reloadListings = false;
                    break;
                } else {
                    reloadListings = true;
                    break;
                }
        }
        if (reloadListings) {
            makeListingInfoRequest(true);
        }
    }

    private void showBlockingOverlayIfSuspended() {
        boolean isHostSuspended;
        boolean z = true;
        if (!FeatureToggles.showHostSuspensionDlsBanner() || !this.mAccountManager.getCurrentUser().isSuspended()) {
            isHostSuspended = false;
        } else {
            isHostSuspended = true;
        }
        ViewLibUtils.setVisibleIf(this.blockingOverlay, isHostSuspended);
        if (isHostSuspended) {
            z = false;
        }
        setMenuVisibility(z);
    }

    /* access modifiers changed from: private */
    public void makeListingInfoRequest(boolean skipCache) {
        this.epoxyController.clearListings();
        ListingPickerInfoRequest.forUserId(this.mAccountManager.getCurrentUserId()).withListener((Observer) this.listingPickerInfoListener).skipCache(skipCache).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void startListYourSpace() {
        startActivityWhichChangesListings(ListYourSpaceIntents.intentForNewListing(getContext(), "ManageListingPicker", "PlusSign"));
    }

    /* access modifiers changed from: private */
    public void startActivityWhichChangesListings(Intent intent) {
        startActivityForResult(intent, 100);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingPicker;
    }
}
