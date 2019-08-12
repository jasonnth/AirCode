package com.airbnb.android.lib.fragments.reviews;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.enums.ReviewsMode;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.AirRequestFactory;
import com.airbnb.android.core.requests.ReviewsRequest;
import com.airbnb.android.core.requests.ReviewsRequest.ReviewsFrom;
import com.airbnb.android.core.requests.TranslateReviewsRequest;
import com.airbnb.android.core.responses.ReviewsResponse;
import com.airbnb.android.core.responses.TranslateReviewsResponse;
import com.airbnb.android.core.responses.TranslatedReview;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.ReservationReviewsController;
import com.airbnb.android.lib.adapters.ReservationReviewsController.Listener;
import com.airbnb.android.lib.analytics.ReservationReviewsAnalytics;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import p032rx.Observer;

public class ReservationReviewsFragment extends AirFragment implements Listener {
    private static final String ARG_FROM_MODAL = "from_modal";
    private static final String ARG_LISTING = "listing";
    private static final String ARG_REVIEW_MODE = "reviewMode";
    private static final String ARG_USER = "user";
    @BindView
    AirRecyclerView airRecyclerView;
    @State
    ArrayList<Review> allReviews = new ArrayList<>();
    private AirRequestFactory<ReviewsRequest, ReviewsResponse> factory;
    /* access modifiers changed from: private */
    public Listing listing;
    private ReservationReviewsController reviewListController;
    private ReviewsMode reviewMode;
    final RequestListener<ReviewsResponse> reviewsRequestListener = new C0699RL().onResponse(ReservationReviewsFragment$$Lambda$1.lambdaFactory$(this)).onError(ReservationReviewsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    boolean showLoading = true;
    @BindView
    AirToolbar toolbar;
    final RequestListener<TranslateReviewsResponse> translationListener = new C0699RL().onResponse(ReservationReviewsFragment$$Lambda$3.lambdaFactory$(this)).onError(ReservationReviewsFragment$$Lambda$4.lambdaFactory$(this)).build();
    @State
    HashMap<Long, Boolean> translationState = new HashMap<>();
    /* access modifiers changed from: private */
    public User user;

    public static ReservationReviewsFragment newInstanceForUser(User user2, ReviewsMode reviewMode2) {
        return newInstanceForUser(user2, reviewMode2, false);
    }

    public static ReservationReviewsFragment newInstanceForUser(User user2, ReviewsMode reviewMode2, boolean fromModalFragment) {
        if (reviewMode2 != ReviewsMode.MODE_LISTING) {
            return newInstance(null, (User) Check.notNull(user2), reviewMode2, fromModalFragment);
        }
        throw new IllegalArgumentException("reviewMode for user cannot be MODE_LISTING");
    }

    public static ReservationReviewsFragment newInstanceForListing(Listing listing2) {
        return newInstanceForListing(listing2, false);
    }

    public static ReservationReviewsFragment newInstanceForListing(Listing listing2, boolean fromModalFragment) {
        return newInstance((Listing) Check.notNull(listing2), listing2.getHost(), ReviewsMode.MODE_LISTING, fromModalFragment);
    }

    private static ReservationReviewsFragment newInstance(Listing listing2, User user2, ReviewsMode reviewMode2, boolean fromModalFragment) {
        return (ReservationReviewsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ReservationReviewsFragment()).putSerializable(ARG_REVIEW_MODE, (Serializable) Check.notNull(reviewMode2))).putParcelable("user", (Parcelable) Check.notNull(user2))).putParcelable("listing", listing2)).putBoolean(ARG_FROM_MODAL, fromModalFragment)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.listing = (Listing) args.getParcelable("listing");
        this.user = (User) args.getParcelable("user");
        this.reviewMode = (ReviewsMode) args.getSerializable(ARG_REVIEW_MODE);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int reviewCount;
        View v = inflater.inflate(C0880R.layout.fragment_reservation_reviews, container, false);
        bindViews(v);
        setToolbar(this.toolbar);
        if (getArguments().getBoolean(ARG_FROM_MODAL)) {
            this.toolbar.setNavigationIcon(1);
        } else {
            this.toolbar.setNavigationIcon(2);
        }
        switch (this.reviewMode) {
            case MODE_ALL:
                reviewCount = this.user.getRevieweeCount();
                break;
            case MODE_GUEST:
                reviewCount = this.user.getReviewsCountAsGuest();
                break;
            case MODE_HOST:
                reviewCount = this.user.getRevieweeCount() - this.user.getReviewsCountAsGuest();
                break;
            default:
                reviewCount = this.listing.getReviewsCount();
                break;
        }
        this.factory = createFactory(this.reviewMode);
        setUpController(reviewCount);
        return v;
    }

    static /* synthetic */ void lambda$new$0(ReservationReviewsFragment reservationReviewsFragment, ReviewsResponse reviewsResponse) {
        List<Review> reviews = reviewsResponse.getReviews();
        reservationReviewsFragment.allReviews.addAll(reviews);
        reservationReviewsFragment.showLoading = !reviews.isEmpty();
        reservationReviewsFragment.reviewListController.setData((List<Review>) reservationReviewsFragment.allReviews, Boolean.valueOf(reservationReviewsFragment.showLoading));
    }

    static /* synthetic */ void lambda$new$2(ReservationReviewsFragment reservationReviewsFragment, AirRequestNetworkException error) {
        reservationReviewsFragment.showLoading = false;
        NetworkUtil.tryShowRetryableErrorWithSnackbar(reservationReviewsFragment.getView(), (NetworkException) error, ReservationReviewsFragment$$Lambda$5.lambdaFactory$(reservationReviewsFragment));
        reservationReviewsFragment.reviewListController.setData((List<Review>) reservationReviewsFragment.allReviews, Boolean.valueOf(reservationReviewsFragment.showLoading));
    }

    private AirRequestFactory<ReviewsRequest, ReviewsResponse> createFactory(final ReviewsMode reviewsMode) {
        return new AirRequestFactory<ReviewsRequest, ReviewsResponse>() {
            public ReviewsRequest getNextOffset(int offset, BaseRequestListener<ReviewsResponse> listener) {
                switch (C69782.$SwitchMap$com$airbnb$android$core$enums$ReviewsMode[reviewsMode.ordinal()]) {
                    case 4:
                        return (ReviewsRequest) ReviewsRequest.forListing(ReservationReviewsFragment.this.listing.getId(), offset).withListener((Observer) listener);
                    default:
                        return (ReviewsRequest) ReviewsRequest.forUser(ReservationReviewsFragment.this.mAccountManager, ReservationReviewsFragment.this.user.getId(), ReviewsFrom.transformFrom(reviewsMode), offset, 20).withListener((Observer) listener);
                }
            }
        };
    }

    public void loadMoreReviews() {
        if (this.allReviews.size() > 0) {
            ReservationReviewsAnalytics.trackLoadMoreReviews(this.reviewMode, this.reviewMode == ReviewsMode.MODE_LISTING ? this.listing.getId() : this.user.getId());
        }
        ((ReviewsRequest) this.factory.getNextOffset(this.allReviews.size(), this.reviewsRequestListener)).execute(this.requestManager);
    }

    private void setUpController(int reviewCount) {
        this.reviewListController = new ReservationReviewsController(getActivity(), this, reviewCount);
        this.reviewListController.setData((List<Review>) this.allReviews, Boolean.valueOf(this.showLoading));
        this.airRecyclerView.setEpoxyController(this.reviewListController);
    }

    static /* synthetic */ void lambda$new$3(ReservationReviewsFragment reservationReviewsFragment, TranslateReviewsResponse response) {
        for (TranslatedReview translatedReview : response.translatedReviews) {
            Iterator it = reservationReviewsFragment.allReviews.iterator();
            while (it.hasNext()) {
                Review review = (Review) it.next();
                if (review.getId() == translatedReview.reviewId) {
                    review.setTranslation(translatedReview);
                    reservationReviewsFragment.translationState.put(Long.valueOf(review.getId()), Boolean.valueOf(review.hasTranslation()));
                }
            }
        }
        reservationReviewsFragment.reviewListController.setData((List<Review>) reservationReviewsFragment.allReviews, Boolean.valueOf(reservationReviewsFragment.showLoading));
    }

    public boolean shouldShowTranslation(Review review) {
        Boolean showTranslation = (Boolean) this.translationState.get(Long.valueOf(review.getId()));
        return showTranslation != null && showTranslation.booleanValue();
    }

    public void toggleTranslationState(Review review) {
        boolean isCurrentlyShowingTranslation = shouldShowTranslation(review);
        if (isCurrentlyShowingTranslation) {
            this.translationState.put(Long.valueOf(review.getId()), Boolean.valueOf(false));
        } else if (review.hasTranslation()) {
            this.translationState.put(Long.valueOf(review.getId()), Boolean.valueOf(true));
        } else {
            requestTranslation(review.getId());
        }
        this.reviewListController.setData((List<Review>) this.allReviews, Boolean.valueOf(this.showLoading));
        ReservationReviewsAnalytics.trackToggleReservationReviewsTranslate(isCurrentlyShowingTranslation);
    }

    private void requestTranslation(long reviewId) {
        new TranslateReviewsRequest(reviewId).withListener((Observer) this.translationListener).doubleResponse().execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.HostReservationReviews;
    }
}
