package com.airbnb.android.p011p3;

import android.os.Bundle;
import com.airbnb.airrequest.BaseRequest;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.enums.FlagContent;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.UserFlag;
import com.airbnb.android.core.requests.GetUserFlagRequest;
import com.airbnb.android.core.requests.ReviewsRequest;
import com.airbnb.android.core.requests.TranslateReviewsRequest;
import com.airbnb.android.core.responses.ReviewsResponse;
import com.airbnb.android.core.responses.TranslateReviewsResponse;
import com.airbnb.android.core.responses.TranslatedReview;
import com.airbnb.android.core.responses.UserFlagResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.adapters.VerificationsAdapter;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import p032rx.Observer;

/* renamed from: com.airbnb.android.p3.ReviewsController */
public class ReviewsController {
    private static final int NUM_REVIEWS_TO_KEEP_IN_SAVED_STATE = 100;
    private ListingDetailsController p3Controller;
    private final RequestManager requestManager;
    @State
    ArrayList<Review> reviews = new ArrayList<>();
    final RequestListener<ReviewsResponse> reviewsListener = new C0699RL().onResponse(ReviewsController$$Lambda$1.lambdaFactory$(this)).onError(ReviewsController$$Lambda$2.lambdaFactory$(this)).onComplete(ReviewsController$$Lambda$3.lambdaFactory$(this)).build();
    final RequestListener<TranslateReviewsResponse> translationListener = new C0699RL().onResponse(ReviewsController$$Lambda$6.lambdaFactory$(this)).onError(ReviewsController$$Lambda$7.lambdaFactory$(this)).build();
    @State
    HashMap<Long, Boolean> translationState = new HashMap<>();
    final RequestListener<UserFlagResponse> userFlagListener = new C0699RL().onResponse(ReviewsController$$Lambda$4.lambdaFactory$(this)).onError(ReviewsController$$Lambda$5.lambdaFactory$(this)).build();

    public ReviewsController(ListingDetailsController p3Controller2, RequestManager requestManager2, Bundle savedInstanceState) {
        this.p3Controller = p3Controller2;
        this.requestManager = requestManager2;
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        requestManager2.subscribe(this);
    }

    public void trimReviewCount(int numReviewsToKeep) {
        if (ListUtils.trimList(this.reviews, numReviewsToKeep)) {
            this.requestManager.cancelRequests(this.reviewsListener);
        }
    }

    public void trimToOnePage() {
        trimReviewCount(20);
    }

    /* access modifiers changed from: private */
    public void notifyChange() {
        this.p3Controller.notifyStateChange();
    }

    public boolean hasReviews() {
        return !this.reviews.isEmpty();
    }

    public FluentIterable<Review> getReviews() {
        return FluentIterable.from((Iterable<E>) this.reviews);
    }

    public int getNumReviewsLoaded() {
        return this.reviews.size();
    }

    public int getTotalReviewsCount() {
        Listing listing = this.p3Controller.getState().listing();
        if (listing != null) {
            return listing.getReviewsCount();
        }
        return 0;
    }

    public float getStarRating() {
        Listing listing = this.p3Controller.getState().listing();
        if (listing != null) {
            return listing.getStarRating();
        }
        return 0.0f;
    }

    public float getReviewStarRatingAccuracy() {
        Listing listing = this.p3Controller.getState().listing();
        if (listing != null) {
            return listing.getReviewStarRatingAccuracy();
        }
        return 0.0f;
    }

    public float getReviewStarRatingCheckin() {
        Listing listing = this.p3Controller.getState().listing();
        if (listing != null) {
            return listing.getReviewStarRatingCheckin();
        }
        return 0.0f;
    }

    public float getReviewStarRatingCleanliness() {
        Listing listing = this.p3Controller.getState().listing();
        if (listing != null) {
            return listing.getReviewStarRatingCleanliness();
        }
        return 0.0f;
    }

    public float getReviewStarRatingCommunication() {
        Listing listing = this.p3Controller.getState().listing();
        if (listing != null) {
            return listing.getReviewStarRatingCommunication();
        }
        return 0.0f;
    }

    public float getReviewStarRatingLocation() {
        Listing listing = this.p3Controller.getState().listing();
        if (listing != null) {
            return listing.getReviewStarRatingLocation();
        }
        return 0.0f;
    }

    public float getReviewStarRatingValue() {
        Listing listing = this.p3Controller.getState().listing();
        if (listing != null) {
            return listing.getReviewStarRatingValue();
        }
        return 0.0f;
    }

    public void fetchNextPage() {
        boolean isFirstPage;
        boolean z = true;
        if (!isFetchingReviews()) {
            Check.state(hasMoreToLoad());
            if (this.reviews.size() == 0) {
                isFirstPage = true;
            } else {
                isFirstPage = false;
            }
            BaseRequest doubleResponse = ReviewsRequest.forListing(this.p3Controller.getState().listingId(), this.reviews.size()).doubleResponse(isFirstPage);
            if (isFirstPage) {
                z = false;
            }
            doubleResponse.skipCache(z).withListener(this.reviewsListener).execute(this.requestManager);
        }
    }

    public void fetchFirstPageIfNeeded() {
        if (this.reviews.isEmpty() && hasMoreToLoad()) {
            fetchNextPage();
        }
    }

    public boolean hasMoreToLoad() {
        Listing listing = this.p3Controller.getState().listing();
        return listing != null && this.reviews.size() < listing.getReviewsCount();
    }

    static /* synthetic */ void lambda$new$0(ReviewsController reviewsController, ReviewsResponse data) {
        if (((ReviewsRequest) data.metadata.request()).getOffset() == 0) {
            reviewsController.reviews.clear();
        }
        reviewsController.trimReviews(data.getReviews());
        reviewsController.reviews.addAll(data.getReviews());
        if (data.metadata.isCached()) {
            reviewsController.notifyChange();
        }
    }

    private void trimReviews(List<Review> reviews2) {
        for (Review review : reviews2) {
            review.getAuthor().setPictureUrl(null);
            review.setPartialListing(null);
            review.setRecipient(null);
        }
    }

    public boolean isFetchingReviews() {
        return this.requestManager.hasRequests(this.reviewsListener);
    }

    public void refreshFlagForReview(long reviewId, long currentUserId) {
        new GetUserFlagRequest(FlagContent.Review, reviewId, currentUserId).withListener((Observer) this.userFlagListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$3(ReviewsController reviewsController, UserFlagResponse data) {
        UserFlag flag = data.flag;
        for (int i = 0; i < reviewsController.reviews.size(); i++) {
            Review review = (Review) reviewsController.reviews.get(i);
            if (review.getId() == flag.getFlaggableId()) {
                review.setUserFlag(flag);
                reviewsController.notifyChange();
                if (i < 20) {
                    ReviewsRequest.forListing(reviewsController.p3Controller.getState().listingId(), 0).skipCache().execute(NetworkUtil.singleFireExecutor());
                    return;
                }
                return;
            }
        }
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
        notifyChange();
        trackToggleListingReviewTranslation(isCurrentlyShowingTranslation);
    }

    private static void trackToggleListingReviewTranslation(boolean showTranslatedReview) {
        AirbnbEventLogger.track("p3", Strap.make().mo11639kv("page", VerificationsAdapter.VERIFICATION_REVIEWS).mo11639kv(BaseAnalytics.OPERATION, "click").mo11639kv(BaseAnalytics.TARGET, showTranslatedReview ? "see_original_language" : "translate"));
    }

    private void requestTranslation(long reviewId) {
        new TranslateReviewsRequest(reviewId).withListener((Observer) this.translationListener).doubleResponse().execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$5(ReviewsController reviewsController, TranslateReviewsResponse response) {
        for (TranslatedReview translatedReview : response.translatedReviews) {
            Iterator it = reviewsController.reviews.iterator();
            while (it.hasNext()) {
                Review review = (Review) it.next();
                if (review.getId() == translatedReview.reviewId) {
                    review.setTranslation(translatedReview);
                    reviewsController.translationState.put(Long.valueOf(review.getId()), Boolean.valueOf(review.hasTranslation()));
                }
            }
        }
        reviewsController.notifyChange();
    }

    public void onSaveInstanceState(Bundle outState) {
        trimReviewCount(100);
        IcepickWrapper.saveInstanceState(this, outState);
    }
}
