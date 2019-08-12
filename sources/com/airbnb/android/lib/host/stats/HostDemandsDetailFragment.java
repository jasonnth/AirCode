package com.airbnb.android.lib.host.stats;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.host.stats.HostStatsJitneyLogger;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingDemandDetails;
import com.airbnb.android.core.models.SimilarListing;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import p032rx.Observer;
import p032rx.functions.Action1;

public class HostDemandsDetailFragment extends AirFragment {
    private static final int SIMILAR_LISTINGS_FETCH_COUNT = 5;
    @State
    ListingDemandDetails aggregateDemandDetails;
    public final NonResubscribableRequestListener<AirBatchResponse> batchResponseRequestListener = new C0699RL().onError(HostDemandsDetailFragment$$Lambda$3.lambdaFactory$(this)).onResponse(new Action1<AirBatchResponse>() {
        public void call(AirBatchResponse airBatchResponse) {
            if (HostDemandsDetailFragment.this.aggregateDemandDetails != null) {
                HostDemandsDetailFragment.this.detailsAdapter.setDemandDetails(HostDemandsDetailFragment.this.aggregateDemandDetails);
            }
            HostDemandsDetailFragment.this.detailsAdapter.setSimilarListings(HostDemandsDetailFragment.this.similarListingsMap);
        }
    }).buildWithoutResubscription();
    final RequestListener<ListingDemandDetailsResponse> demandDetailsListener = new C0699RL().onResponse(HostDemandsDetailFragment$$Lambda$2.lambdaFactory$(this)).build();
    /* access modifiers changed from: private */
    public HostDemandDetailsAdapter detailsAdapter;
    private HostStatsInterface hostStatsInterface;
    @State
    ArrayList<ListingDemandDetails> listingDemandDetails;
    private final HostListingSelectorCallback listingSelectorCallback = new HostListingSelectorCallback() {
        public void onListingSelected(Listing listing) {
            HostDemandsDetailFragment.this.loadDataForListing(listing, false);
        }

        public void onAllListingsSelected() {
            HostDemandsDetailFragment.this.selectedListing = null;
            HostDemandsDetailFragment.this.toolbar.setTitle(C0880R.string.host_stats_page_title_all_listings);
            HostDemandsDetailFragment.this.detailsAdapter.setDemandDetails(HostDemandsDetailFragment.this.aggregateDemandDetails);
            HostDemandsDetailFragment.this.detailsAdapter.setSimilarListings(HostDemandsDetailFragment.this.similarListingsMap);
        }

        public boolean isListingSelectable(Listing listing) {
            return listing.isListed();
        }
    };
    @BindView
    RecyclerView recyclerView;
    @State
    Listing selectedListing;
    final RequestListener<HostListingsSimilarListingsResponse> similarListingsListener = new C0699RL().onResponse(HostDemandsDetailFragment$$Lambda$1.lambdaFactory$(this)).build();
    /* access modifiers changed from: private */
    public final HashMap<Listing, List<SimilarListing>> similarListingsMap = new HashMap<>();
    HostStatsJitneyLogger statsJitneyLogger;
    @BindView
    AirToolbar toolbar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_demand_details, container, false);
        ButterKnife.bind(this, view);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(1);
        this.toolbar.setTheme(3);
        setupListingSelector();
        this.detailsAdapter = new HostDemandDetailsAdapter(getContext());
        this.recyclerView.setAdapter(this.detailsAdapter);
        if (this.selectedListing != null) {
            loadDataForListing(this.selectedListing, true);
        } else if (this.aggregateDemandDetails == null || this.similarListingsMap == null) {
            loadDataFromNetwork();
        } else {
            this.toolbar.setTitle(C0880R.string.host_stats_page_title_all_listings);
            this.detailsAdapter.setDemandDetails(this.aggregateDemandDetails);
            this.detailsAdapter.setSimilarListings(this.similarListingsMap);
        }
        return view;
    }

    private void loadDataFromNetwork() {
        if (this.hostStatsInterface.userHasOnlyOneListedListing()) {
            this.toolbar.setTitle((CharSequence) this.hostStatsInterface.getFirstListedListing().getName());
        } else {
            this.toolbar.setTitle(C0880R.string.host_stats_page_title_all_listings);
        }
        new AirBatchRequest(Arrays.asList(new BaseRequestV2[]{HostListingsSimilarListingsRequest.forUser(this.mAccountManager.getCurrentUser(), 0, 5).withListener((Observer) this.similarListingsListener), ListingDemandDetailsRequest.forUser(this.mAccountManager.getCurrentUser()).withListener((Observer) this.demandDetailsListener)}), this.batchResponseRequestListener).execute(this.requestManager);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Check.state(context instanceof HostStatsInterface, "activity must implement HostStatsInterface");
        this.hostStatsInterface = (HostStatsInterface) context;
    }

    public void onDetach() {
        this.hostStatsInterface = null;
        super.onDetach();
    }

    /* access modifiers changed from: private */
    public void loadDataForListing(Listing listing, boolean forceReload) {
        if (forceReload || this.selectedListing == null || this.selectedListing.getId() != listing.getId()) {
            this.selectedListing = listing;
            this.toolbar.setTitle((CharSequence) listing.getName());
            Entry<Listing, List<SimilarListing>> entry = (Entry) FluentIterable.from((Iterable<E>) this.similarListingsMap.entrySet()).firstMatch(HostDemandsDetailFragment$$Lambda$4.lambdaFactory$(listing)).orNull();
            if (entry != null) {
                HashMap<Listing, List<SimilarListing>> filteredMap = new HashMap<>();
                filteredMap.put(entry.getKey(), entry.getValue());
                this.detailsAdapter.setSimilarListings(filteredMap);
            } else {
                HostListingsSimilarListingsRequest.forListing(listing).withListener((Observer) this.similarListingsListener).execute(this.requestManager);
                this.detailsAdapter.setSimilarListingsLoading();
            }
            ListingDemandDetails selectedListingDemandDetails = (ListingDemandDetails) FluentIterable.from((Iterable<E>) this.listingDemandDetails).filter(HostDemandsDetailFragment$$Lambda$5.lambdaFactory$(listing)).first().orNull();
            Check.notNull(selectedListingDemandDetails, "demand details for listing ID not found: " + listing.getId() + " is it listed: " + listing.hasAvailability());
            this.detailsAdapter.setDemandDetails(selectedListingDemandDetails);
        }
    }

    static /* synthetic */ boolean lambda$loadDataForListing$0(Listing listing, Entry input) {
        return input != null && ((Listing) input.getKey()).getId() == listing.getId();
    }

    static /* synthetic */ boolean lambda$loadDataForListing$1(Listing listing, ListingDemandDetails input) {
        return input != null && input.getId() == listing.getId();
    }

    private void setupListingSelector() {
        if (this.hostStatsInterface.shouldEnableListingSelector()) {
            this.toolbar.setOnTitleClickListener(HostDemandsDetailFragment$$Lambda$6.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$setupListingSelector$2(HostDemandsDetailFragment hostDemandsDetailFragment, View v) {
        if (!hostDemandsDetailFragment.requestManager.hasRequests(hostDemandsDetailFragment.batchResponseRequestListener)) {
            hostDemandsDetailFragment.statsJitneyLogger.logListingPickerOpenEvent(HostStatsJitneyLogger.PAGE_LISTING_VIEW_DETAILS);
            hostDemandsDetailFragment.hostStatsInterface.showListingSelector(hostDemandsDetailFragment.listingSelectorCallback, true);
        }
    }

    static /* synthetic */ void lambda$new$4(HostDemandsDetailFragment hostDemandsDetailFragment, ListingDemandDetailsResponse response) {
        hostDemandsDetailFragment.aggregateDemandDetails = response.aggregateDemandDetails;
        hostDemandsDetailFragment.listingDemandDetails = new ArrayList<>(response.listingDemandDetails);
    }

    static /* synthetic */ void lambda$new$5(HostDemandsDetailFragment hostDemandsDetailFragment, AirRequestNetworkException e) {
        Activity activity = hostDemandsDetailFragment.getActivity();
        if (activity != null) {
            NetworkUtil.toastNetworkError((Context) activity, (NetworkException) e);
            BugsnagWrapper.notify((Throwable) new RuntimeException("unable to load demand details", e));
            activity.finish();
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.HostStatsViewsDetail;
    }

    public Strap getNavigationTrackingParams() {
        String valueOf;
        Strap navigationTrackingParams = super.getNavigationTrackingParams();
        String str = "listing_id";
        if (this.selectedListing == null) {
            valueOf = "all";
        } else {
            valueOf = String.valueOf(this.selectedListing.getId());
        }
        return navigationTrackingParams.mo11639kv(str, valueOf);
    }
}
