package com.airbnb.android.lib.host.stats;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.host.stats.HostStatsJitneyLogger;
import com.airbnb.android.core.models.HostRatingBreakdown;
import com.airbnb.android.core.models.HostRatingDistributionStatistic;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRatingAggregation;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.ReviewsResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.host.stats.HostReviewDetailAdapter.Callback;
import com.airbnb.android.lib.host.stats.views.ReviewStarBreakdownView;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import p032rx.Observer;

public class HostReviewDetailsFragment extends AirFragment {
    private static final long ALL_LISTINGS_ID = -1;
    private static final String ARG_SELECTED_LISTING = "selected_listing";
    private static final String ARG_SIMILAR_HOST_AVERAGE_RATING = "similar_host_rating";
    private static final int REVIEWS_FETCH_COUNT = 10;
    private static final int REVIEW_LOAD_MORE_THRESHOLD = 3;
    /* access modifiers changed from: private */
    public static final String TAG = HostReviewDetailsFragment.class.getSimpleName();
    @State
    ArrayList<HostRatingDistributionStatistic> aggregateRatingStatistics;
    NonResubscribableRequestListener<AirBatchResponse> batchRequestListener;
    private String currentPaginationIdentifier;
    /* access modifiers changed from: private */
    public HostStatsInterface hostStatsInterface;
    private final Callback listener = new Callback() {
        public void loadMore(int offset) {
            if (HostReviewDetailsFragment.this.batchRequestListener != null && HostReviewDetailsFragment.this.requestManager.hasRequests(HostReviewDetailsFragment.this.batchRequestListener)) {
                return;
            }
            if (HostReviewDetailsFragment.this.selectedListingId == -1) {
                HostReviewDetailsFragment.this.loadReviewsForAllListingsWithOffset(offset);
            } else {
                HostReviewDetailsFragment.this.loadReviewsForListingWithOffset(HostReviewDetailsFragment.this.selectedListingId, offset);
            }
        }

        public void onReviewScoreCardClicked(Listing listing) {
            C0715L.m1189d(HostReviewDetailsFragment.TAG, "onReviewScoreCardClicked: " + listing.getId());
            HostReviewDetailsFragment.this.selectedListingId = listing.getId();
            HostReviewDetailsFragment.this.startActivity(HostStatsActivity.getIntentForFragment(HostReviewDetailsFragment.this.getContext(), HostReviewDetailsFragment.class, HostReviewDetailsFragment.this.listings, HostReviewDetailsFragment.getBundle(HostReviewDetailsFragment.this.similarHostAverageRating, listing), HostReviewDetailsFragment.this.hostStatsInterface.getListingSelectorBundle()));
        }
    };
    @State
    ArrayList<ListingRatingAggregation> listingRatingAggregations;
    @State
    HashMap<Long, HostRatingBreakdown> listingRatingBreakdownCache;
    @State
    HashMap<Long, ArrayList<Review>> listingReviewsCache;
    private final HostListingSelectorCallback listingSelectorCallback = new HostListingSelectorCallback() {
        public void onListingSelected(Listing listing) {
            C0715L.m1189d(HostReviewDetailsFragment.TAG, "onListingSelected: " + listing.getId());
            HostReviewDetailsFragment.this.selectedListingId = listing.getId();
            HostReviewDetailsFragment.this.populateDataForListing(listing);
        }

        public void onAllListingsSelected() {
            C0715L.m1189d(HostReviewDetailsFragment.TAG, "onAllListingsSelected");
            HostReviewDetailsFragment.this.populateDataForAllListings();
        }

        public boolean isListingSelectable(Listing listing) {
            return listing.getRatedReviewsCount() > 0;
        }
    };
    @State
    ArrayList<Listing> listings;
    /* access modifiers changed from: 0000 */
    @State
    public ArrayList<Listing> listingsWithReviewScores;
    final RequestListener<ReviewsResponse> loadMoreReviewsForAllListingsListener = new C0699RL().onResponse(HostReviewDetailsFragment$$Lambda$1.lambdaFactory$(this)).onError(HostReviewDetailsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    private final ReviewStarBreakdownView.Callback reviewBreakdownCallback = new ReviewStarBreakdownView.Callback() {
        public void onSectionSelected(int stars) {
        }

        public void onAllSectionsSelected() {
        }
    };
    private HostReviewDetailAdapter reviewDetailAdapter;
    @State
    long selectedListingId;
    @State
    Double similarHostAverageRating;
    HostStatsJitneyLogger statsJitneyLogger;
    @BindView
    AirToolbar toolbar;

    public static Bundle getBundle(Double simlarHostAverageRating) {
        BundleBuilder bundleBuilder = new BundleBuilder();
        if (simlarHostAverageRating != null) {
            bundleBuilder.putDouble(ARG_SIMILAR_HOST_AVERAGE_RATING, simlarHostAverageRating.doubleValue());
        }
        return bundleBuilder.toBundle();
    }

    public static Bundle getBundle(Double simlarHostAverageRating, Listing selectedListing) {
        Bundle bundle = getBundle(simlarHostAverageRating);
        bundle.putParcelable(ARG_SELECTED_LISTING, selectedListing);
        return bundle;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.host_stats_rating_details_fragment, container, false);
        ButterKnife.bind(this, view);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(1);
        this.toolbar.setTheme(3);
        setupListingSelector();
        boolean shouldLoadDataFromNetwork = false;
        if (this.listingRatingBreakdownCache == null) {
            this.listingRatingBreakdownCache = new HashMap<>();
            shouldLoadDataFromNetwork = true;
        }
        if (ListUtils.isEmpty((Collection<?>) this.listings)) {
            this.listings = getArguments().getParcelableArrayList("listings");
        }
        if (this.listingReviewsCache == null) {
            this.listingReviewsCache = new HashMap<>();
            shouldLoadDataFromNetwork = true;
        }
        if (this.listingsWithReviewScores == null) {
            shouldLoadDataFromNetwork = true;
        }
        if (this.similarHostAverageRating == null && getArguments().containsKey(ARG_SIMILAR_HOST_AVERAGE_RATING)) {
            this.similarHostAverageRating = Double.valueOf(getArguments().getDouble(ARG_SIMILAR_HOST_AVERAGE_RATING, -1.0d));
        }
        this.reviewDetailAdapter = new HostReviewDetailAdapter(this.listener, this.reviewBreakdownCallback, getContext());
        this.recyclerView.setAdapter(this.reviewDetailAdapter);
        if (savedInstanceState != null) {
            this.reviewDetailAdapter.onRestoreInstanceState(savedInstanceState);
        }
        Listing selectedListing = (Listing) getArguments().getParcelable(ARG_SELECTED_LISTING);
        this.reviewDetailAdapter.setRatedReviewsCount(selectedListing == null ? null : Integer.valueOf(selectedListing.getRatedReviewsCount()), getContext());
        if (shouldLoadDataFromNetwork) {
            loadDataFromNetwork(selectedListing);
        } else {
            Listing listing = (Listing) FluentIterable.from((Iterable<E>) this.listings).filter(HostReviewDetailsFragment$$Lambda$3.lambdaFactory$(this)).first().orNull();
            if (listing == null) {
                populateDataForAllListings();
            } else {
                populateDataForListing(listing);
            }
        }
        return view;
    }

    static /* synthetic */ boolean lambda$onCreateView$0(HostReviewDetailsFragment hostReviewDetailsFragment, Listing l) {
        return hostReviewDetailsFragment.selectedListingId == l.getId();
    }

    private void setupListingSelector() {
        if (this.hostStatsInterface.shouldEnableListingSelector()) {
            this.toolbar.setOnTitleClickListener(HostReviewDetailsFragment$$Lambda$4.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$setupListingSelector$1(HostReviewDetailsFragment hostReviewDetailsFragment, View v) {
        if (hostReviewDetailsFragment.batchRequestListener == null || !hostReviewDetailsFragment.requestManager.hasRequests(hostReviewDetailsFragment.batchRequestListener)) {
            hostReviewDetailsFragment.statsJitneyLogger.logListingPickerOpenEvent(HostStatsJitneyLogger.PAGE_REVIEW_DETAILS);
            hostReviewDetailsFragment.hostStatsInterface.showListingSelector(hostReviewDetailsFragment.listingSelectorCallback, true);
        }
    }

    private void loadDataFromNetwork(final Listing selectedListing) {
        updateToolbarTitle(selectedListing);
        this.selectedListingId = selectedListing == null ? -1 : selectedListing.getId();
        List<BaseRequestV2<?>> requests = Arrays.asList(new BaseRequestV2[]{constructReviewsRequestForBatch(selectedListing), constructReviewDetailsRequestForBatch(), constructRecentReviewsPerListingRequestForBatch()});
        this.batchRequestListener = new NonResubscribableRequestListener<AirBatchResponse>() {
            public void onResponse(AirBatchResponse data) {
                if (HostReviewDetailsFragment.this.hostStatsInterface != null) {
                    if (selectedListing != null) {
                        HostReviewDetailsFragment.this.populateDataForListing(selectedListing);
                    } else {
                        HostReviewDetailsFragment.this.populateDataForAllListings();
                    }
                }
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                Activity activity = HostReviewDetailsFragment.this.getActivity();
                if (activity != null) {
                    NetworkUtil.toastNetworkError((Context) activity, (NetworkException) e);
                    BugsnagWrapper.notify((Throwable) new RuntimeException("unable to load rating details", e));
                    activity.finish();
                }
            }
        };
        new AirBatchRequest(requests, this.batchRequestListener).execute(this.requestManager);
    }

    private void updateToolbarTitle(Listing selectedListing) {
        if (selectedListing == null) {
            this.toolbar.setTitle(C0880R.string.host_stats_page_title_all_listings);
        } else {
            this.toolbar.setTitle((CharSequence) selectedListing.getName());
        }
    }

    private BaseRequestV2<?> constructReviewsRequestForBatch(Listing selectedListing) {
        HostStatsReviewsRequest request;
        if (selectedListing != null) {
            request = HostStatsReviewsRequest.forListing(selectedListing.getId(), 0, 10);
        } else {
            request = HostStatsReviewsRequest.forUser(this.mAccountManager.getCurrentUserId(), 0, 10);
        }
        return request.withListener((Observer) new C0699RL().onResponse(HostReviewDetailsFragment$$Lambda$5.lambdaFactory$(this)).buildWithoutResubscription());
    }

    private BaseRequestV2<?> constructRecentReviewsPerListingRequestForBatch() {
        return HostRecentReviewsPerListingRequest.forUser(this.mAccountManager.getCurrentUserId()).withListener((Observer) new C0699RL().onResponse(HostReviewDetailsFragment$$Lambda$6.lambdaFactory$(this)).buildWithoutResubscription());
    }

    private BaseRequestV2<?> constructReviewDetailsRequestForBatch() {
        return HostReviewDetailsRequest.forListings(this.listings).withListener((Observer) new C0699RL().onResponse(HostReviewDetailsFragment$$Lambda$7.lambdaFactory$(this)).buildWithoutResubscription());
    }

    static /* synthetic */ void lambda$constructReviewDetailsRequestForBatch$4(HostReviewDetailsFragment hostReviewDetailsFragment, HostReviewDetailsResponse response) {
        hostReviewDetailsFragment.aggregateRatingStatistics = response.getRatingDistributionStatistics();
        hostReviewDetailsFragment.listingRatingAggregations = response.getListingRatingAggregations();
    }

    /* access modifiers changed from: private */
    public void populateDataForListing(Listing listing) {
        this.toolbar.setTitle((CharSequence) listing.getName());
        this.reviewDetailAdapter.setRatedReviewsCount(Integer.valueOf(listing.getRatedReviewsCount()), getContext());
        this.reviewDetailAdapter.clearReviews();
        loadListingRatingAggregations(listing);
        loadListingCategoryRatings(listing);
        loadReviews(listing);
        this.recyclerView.scrollToPosition(0);
    }

    private void loadReviews(Listing listing) {
        ArrayList<Review> cachedReviews = null;
        if (this.listingReviewsCache.containsKey(Long.valueOf(listing.getId()))) {
            cachedReviews = (ArrayList) this.listingReviewsCache.get(Long.valueOf(listing.getId()));
        }
        if (ListUtils.isEmpty((Collection<?>) cachedReviews)) {
            loadReviewsForListingWithOffset(listing.getId(), 0);
            return;
        }
        this.reviewDetailAdapter.setReviewsForListing(cachedReviews, true);
        if (cachedReviews.size() < 3) {
            loadReviewsForListingWithOffset(listing.getId(), cachedReviews.size());
        }
    }

    private void loadListingCategoryRatings(Listing listing) {
        if (this.listingRatingBreakdownCache.containsKey(Long.valueOf(listing.getId()))) {
            this.reviewDetailAdapter.setHostRatingBreakdown((HostRatingBreakdown) this.listingRatingBreakdownCache.get(Long.valueOf(listing.getId())));
            return;
        }
        this.reviewDetailAdapter.hideHostRatingBreakdown();
        HostRatingBreakdownRequest.forListing(listing.getId()).withListener((Observer) new C0699RL().onResponse(HostReviewDetailsFragment$$Lambda$8.lambdaFactory$(this, listing)).onError(HostReviewDetailsFragment$$Lambda$9.lambdaFactory$(this)).buildWithoutResubscription()).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$loadListingCategoryRatings$5(HostReviewDetailsFragment hostReviewDetailsFragment, Listing listing, HostRatingBreakdownResponse data) {
        hostReviewDetailsFragment.listingRatingBreakdownCache.put(Long.valueOf(listing.getId()), data.ratingBreakdown);
        hostReviewDetailsFragment.reviewDetailAdapter.setHostRatingBreakdown(data.ratingBreakdown);
    }

    private void loadListingRatingAggregations(Listing listing) {
        ListingRatingAggregation cachedAggregation = (ListingRatingAggregation) FluentIterable.from((Iterable<E>) this.listingRatingAggregations).filter(HostReviewDetailsFragment$$Lambda$10.lambdaFactory$(listing)).first().orNull();
        if (cachedAggregation != null) {
            this.reviewDetailAdapter.setReviewStatistics(cachedAggregation.getRatingAggregations());
            return;
        }
        this.reviewDetailAdapter.setReviewStatisticsLoading();
        HostReviewDetailsRequest.forListing(listing).withListener((Observer) new C0699RL().onResponse(HostReviewDetailsFragment$$Lambda$11.lambdaFactory$(this, listing)).buildWithoutResubscription()).execute(this.requestManager);
    }

    static /* synthetic */ boolean lambda$loadListingRatingAggregations$7(Listing listing, ListingRatingAggregation input) {
        return input != null && input.getListingId() == listing.getId();
    }

    static /* synthetic */ void lambda$loadListingRatingAggregations$9(HostReviewDetailsFragment hostReviewDetailsFragment, Listing listing, HostReviewDetailsResponse response) {
        hostReviewDetailsFragment.listingRatingAggregations.addAll(response.getListingRatingAggregations());
        if (hostReviewDetailsFragment.selectedListingId == listing.getId()) {
            ListingRatingAggregation aggregation = (ListingRatingAggregation) FluentIterable.from((Iterable<E>) hostReviewDetailsFragment.listingRatingAggregations).filter(HostReviewDetailsFragment$$Lambda$16.lambdaFactory$(listing)).first().orNull();
            Check.notNull(aggregation);
            hostReviewDetailsFragment.reviewDetailAdapter.setReviewStatistics(aggregation.getRatingAggregations());
        }
    }

    static /* synthetic */ boolean lambda$null$8(Listing listing, ListingRatingAggregation input) {
        return input != null && input.getListingId() == listing.getId();
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
    public void loadReviewsForListingWithOffset(long newListingId, int offset) {
        String newPaginationIdentifier = getPaginationIdentifier(newListingId, offset);
        if (!newPaginationIdentifier.equals(this.currentPaginationIdentifier)) {
            this.currentPaginationIdentifier = newPaginationIdentifier;
            HostStatsReviewsRequest.forListing(newListingId, offset, 10).withListener((Observer) new C0699RL().onResponse(HostReviewDetailsFragment$$Lambda$12.lambdaFactory$(this, newListingId)).onError(HostReviewDetailsFragment$$Lambda$13.lambdaFactory$(this)).buildWithoutResubscription()).execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$loadReviewsForListingWithOffset$11(HostReviewDetailsFragment hostReviewDetailsFragment, long newListingId, ReviewsResponse data) {
        boolean moreReviewsToLoad = false;
        List<Review> homeReviews = hostReviewDetailsFragment.filterForHomeReviews(data.getReviews());
        hostReviewDetailsFragment.addReviewsToCache(homeReviews);
        if (newListingId == hostReviewDetailsFragment.selectedListingId) {
            if (((Long) FluentIterable.from((Iterable<E>) homeReviews).transform(HostReviewDetailsFragment$$Lambda$15.lambdaFactory$()).first().orNull()) == null) {
                hostReviewDetailsFragment.reviewDetailAdapter.setHasMoreToLoad(false);
                return;
            }
            if (data.getReviews().size() == 10) {
                moreReviewsToLoad = true;
            }
            hostReviewDetailsFragment.reviewDetailAdapter.addReviewsForListing(new ArrayList(homeReviews), moreReviewsToLoad);
        }
    }

    static /* synthetic */ Long lambda$null$10(Review input) {
        if (input == null) {
            return null;
        }
        return Long.valueOf(input.getListingId());
    }

    private String getPaginationIdentifier(long newListingId, int offset) {
        return String.valueOf(newListingId) + "," + offset;
    }

    /* access modifiers changed from: private */
    public void loadReviewsForAllListingsWithOffset(int offset) {
        if (!getPaginationIdentifier(-1, offset).equals(this.currentPaginationIdentifier)) {
            this.currentPaginationIdentifier = getPaginationIdentifier(-1, offset);
            HostStatsReviewsRequest.forUser(this.mAccountManager.getCurrentUserId(), offset, 10).withListener((Observer) this.loadMoreReviewsForAllListingsListener).execute(this.requestManager);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.reviewDetailAdapter != null) {
            this.reviewDetailAdapter.onSaveInstanceState(outState);
        }
    }

    /* access modifiers changed from: private */
    public void populateDataForAllListings() {
        this.selectedListingId = -1;
        this.toolbar.setTitle(C0880R.string.host_stats_page_title_all_listings);
        this.reviewDetailAdapter.setRatedReviewsCount(null, getContext());
        this.reviewDetailAdapter.setReviewStatistics(this.aggregateRatingStatistics);
        this.reviewDetailAdapter.setReviewsForAllListings(getCachedReviews(), true);
        this.reviewDetailAdapter.setRecentReviewCards(this.listingsWithReviewScores);
    }

    private ArrayList<Review> getCachedReviews() {
        return new ArrayList<>(FluentIterable.from((Iterable<E>) this.listingReviewsCache.values()).transformAndConcat(new Function<ArrayList<Review>, Iterable<Review>>() {
            public Iterable<Review> apply(ArrayList<Review> input) {
                return input;
            }
        }).toList());
    }

    /* access modifiers changed from: private */
    public void addReviewsToCache(List<Review> reviews) {
        for (Review review : reviews) {
            if (!this.listingReviewsCache.containsKey(Long.valueOf(review.getListingId()))) {
                this.listingReviewsCache.put(Long.valueOf(review.getListingId()), new ArrayList());
            }
            ArrayList<Review> existingReviews = (ArrayList) this.listingReviewsCache.get(Long.valueOf(review.getListingId()));
            if (!existingReviews.contains(review)) {
                existingReviews.add(review);
                C0715L.m1189d(TAG, "review added to cache: " + review.getId());
            } else {
                C0715L.m1189d(TAG, "review already exists in cache: " + review.getId());
            }
        }
        if (BuildHelper.isDebugFeaturesEnabled()) {
            for (Entry<Long, ArrayList<Review>> entry : this.listingReviewsCache.entrySet()) {
                C0715L.m1189d(TAG, "listingId: " + entry.getKey() + "\t\tcount: " + ((ArrayList) entry.getValue()).size());
                C0715L.m1189d(TAG, "listingId: " + entry.getKey() + "\t\t" + TextUtils.join(",", FluentIterable.from((Iterable) entry.getValue()).transform(new Function<Review, String>() {
                    public String apply(Review input) {
                        if (input == null) {
                            return null;
                        }
                        return String.valueOf(input.getId());
                    }
                })));
            }
        }
    }

    static /* synthetic */ void lambda$new$13(HostReviewDetailsFragment hostReviewDetailsFragment, ReviewsResponse data) {
        List<Review> homeReviews = hostReviewDetailsFragment.filterForHomeReviews(data.getReviews());
        hostReviewDetailsFragment.addReviewsToCache(homeReviews);
        if (hostReviewDetailsFragment.selectedListingId == -1) {
            hostReviewDetailsFragment.reviewDetailAdapter.addReviewsForAllListings(new ArrayList(homeReviews), data.getReviews().size() >= 10);
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.HostStatsRatings;
    }

    public Strap getNavigationTrackingParams() {
        String valueOf;
        Strap navigationTrackingParams = super.getNavigationTrackingParams();
        String str = "listing_id";
        if (this.selectedListingId == -1) {
            valueOf = "all";
        } else {
            valueOf = String.valueOf(this.selectedListingId);
        }
        return navigationTrackingParams.mo11639kv(str, valueOf);
    }

    private List<Review> filterForHomeReviews(List<Review> allReviews) {
        return FluentIterable.from((Iterable<E>) allReviews).filter(HostReviewDetailsFragment$$Lambda$14.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$filterForHomeReviews$15(Review review) {
        return review.getReservation() != null;
    }
}
