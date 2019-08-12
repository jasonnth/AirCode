package com.airbnb.android.lib.host.stats;

import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.host.stats.HostStatsJitneyLogger;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.host.stats.HostListingsAdapter.Callback;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import p032rx.functions.Action1;

public class HostListingSelectorFragment extends AirFragment {
    private static final String ARG_LISTINGS = "arg_listings";
    private static final String ARG_OVERALL_RATING = "arg_overall_rating";
    private static final String ARG_SHOW_ALL_LISTINGS_BUTTON = "arg_show_all_listings_btn";
    private static final String ARG_TOTAL_LISTING_VIEWS = "arg_listing_views";
    private static final int FETCH_COUNT = 10;
    /* access modifiers changed from: private */
    public HostListingsAdapter adapter;
    @State
    double averageOverallRating;
    private final Callback callback = new Callback() {
        public void loadMore() {
            HostListingSelectorFragment.this.fetchListings();
        }

        public void onListingClicked(Listing listing) {
            HostListingSelectorFragment.this.statsJitneyLogger.logListingPickerListingSelectedEvent(listing.getId());
            if (HostListingSelectorFragment.this.hostListingSelectorCallback.isListingSelectable(listing)) {
                HostListingSelectorFragment.this.hostListingSelectorCallback.onListingSelected(listing);
            }
        }

        public void onAllListingsClicked() {
            HostListingSelectorFragment.this.statsJitneyLogger.logListingPickerAllListingsClickedEvent();
            HostListingSelectorFragment.this.hostListingSelectorCallback.onAllListingsSelected();
        }
    };
    /* access modifiers changed from: private */
    public HostListingSelectorCallback hostListingSelectorCallback;
    @State
    boolean isFetchingListings;
    @State
    int listingViews;
    @State
    boolean moreListingsToLoad;
    @BindView
    RecyclerView recyclerView;
    final RequestListener<ListingResponse> requestListener = new C0699RL().onResponse(new Action1<ListingResponse>() {
        public void call(ListingResponse listingResponse) {
            List<Listing> newListings = listingResponse.getListings();
            HostListingSelectorFragment.this.moreListingsToLoad = newListings.size() >= 10;
            HostListingSelectorFragment.this.adapter.addListings(newListings, HostListingSelectorFragment.this.moreListingsToLoad);
            if (HostListingSelectorFragment.this.getActivity() instanceof HostStatsActivity) {
                ((HostStatsActivity) HostListingSelectorFragment.this.getActivity()).addListingsToCache(newListings);
            }
            HostListingSelectorFragment.this.isFetchingListings = false;
        }
    }).onError(HostListingSelectorFragment$$Lambda$1.lambdaFactory$(this)).build();
    @State
    boolean showAllListingsButton;
    HostStatsJitneyLogger statsJitneyLogger;
    @BindView
    AirToolbar toolbar;

    public static Bundle getBundle(ArrayList<Listing> listings, int listingViews2, double averageOverallRating2) {
        return ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putParcelableArrayList(ARG_LISTINGS, listings)).putInt(ARG_TOTAL_LISTING_VIEWS, listingViews2)).putDouble(ARG_OVERALL_RATING, averageOverallRating2)).toBundle();
    }

    public static HostListingSelectorFragment newInstance(boolean showAllListingsButton2, Bundle bundle) {
        return (HostListingSelectorFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new HostListingSelectorFragment()).putBoolean(ARG_SHOW_ALL_LISTINGS_BUTTON, showAllListingsButton2)).putAll(bundle)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.showAllListingsButton = getArguments().getBoolean(ARG_SHOW_ALL_LISTINGS_BUTTON, true);
            this.listingViews = getArguments().getInt(ARG_TOTAL_LISTING_VIEWS, 0);
            this.averageOverallRating = getArguments().getDouble(ARG_OVERALL_RATING, 0.0d);
            this.moreListingsToLoad = true;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.recyclerview_with_toolbar, container, false);
        ButterKnife.bind(this, view);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.toolbar.setTheme(3);
        this.toolbar.setNavigationOnClickListener(HostListingSelectorFragment$$Lambda$2.lambdaFactory$(this));
        this.adapter = new HostListingsAdapter(this.callback, savedInstanceState, this.showAllListingsButton, getContext(), this.listingViews, this.averageOverallRating);
        this.recyclerView.setAdapter(this.adapter);
        List<Listing> listings = (savedInstanceState != null ? savedInstanceState : getArguments()).getParcelableArrayList(ARG_LISTINGS);
        if (ListUtils.isEmpty((Collection<?>) listings)) {
            fetchListings();
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("no cached listings were provided"));
        } else {
            this.adapter.addListings(listings, this.moreListingsToLoad);
        }
        return view;
    }

    public void onAttach(Context context) {
        Check.argument(context instanceof HostListingSelectorCallback);
        super.onAttach(context);
        this.hostListingSelectorCallback = (HostListingSelectorCallback) context;
    }

    public void onDetach() {
        this.hostListingSelectorCallback = null;
        super.onDetach();
    }

    static /* synthetic */ void lambda$new$1(HostListingSelectorFragment hostListingSelectorFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(hostListingSelectorFragment.getView(), e);
        hostListingSelectorFragment.isFetchingListings = false;
    }

    /* access modifiers changed from: private */
    public void fetchListings() {
        if (!this.isFetchingListings) {
            this.isFetchingListings = true;
            ListingRequest.forHostStatsPicker(this.mAccountManager.getCurrentUserId(), this.adapter.getListings().size(), 10, this.requestListener).execute(this.requestManager);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(ARG_LISTINGS, this.adapter.getListings());
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.HostStatsListingPicker;
    }
}
