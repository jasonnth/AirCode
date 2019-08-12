package com.airbnb.android.lib.host.stats;

import android.content.Context;
import android.view.View;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.host.stats.HostStatsJitneyLogger;
import com.airbnb.android.core.models.HostRatingBreakdown;
import com.airbnb.android.core.models.HostRatingDistributionStatistic;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DeprecatedCarouselEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.DeprecatedCarouselEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.HomeReviewRowEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.HomeReviewRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.HostReservationObjectActivity;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HostReviewDetailAdapter extends AirEpoxyAdapter {
    private static final String TAG = HostReviewDetailAdapter.class.getSimpleName();
    private final Callback callback;
    private final String checkInDateFormat;
    private final String checkOutDateFormat;
    private final Context context;
    private final SectionHeaderEpoxyModel_ headerEpoxyModel = new SectionHeaderEpoxyModel_().showDivider(false);
    private final LoadingRowEpoxyModel loaderEpoxyModel = new LoadingRowEpoxyModel_();
    private final DecimalFormat ratingCountFormatter = new DecimalFormat("#,###");
    private final com.airbnb.android.lib.host.stats.views.ReviewStarBreakdownView.Callback reviewBreakdownCallback;
    private final ReviewCategoryBreakdownEpoxyModel_ reviewCategoryBreakdownModel = new ReviewCategoryBreakdownEpoxyModel_();
    private final DeprecatedCarouselEpoxyModel<HostRecentListingReviewsCarouselAdapter> reviewScoreCardCarouselEpoxyModel = new DeprecatedCarouselEpoxyModel_();
    private final SectionHeaderEpoxyModel_ reviewScoreCardSectionHeaderEpoxyModel = getSectionHeaderWithTitle(C0880R.string.host_stats_review_details_recent_ratings_section_title);
    private final ReviewStarBreakdownEpoxyModel_ reviewStarBreakdownModel = new ReviewStarBreakdownEpoxyModel_();
    private final HostRecentListingReviewsCarouselAdapter reviewsCarouselAdapter;
    private final SectionHeaderEpoxyModel_ reviewsSectionHeaderEpoxyModel = getSectionHeaderWithTitle(C0880R.string.host_stats_guest_reviews_section_title);
    HostStatsJitneyLogger statsJitneyLogger;

    public interface Callback {
        void loadMore(int i);

        void onReviewScoreCardClicked(Listing listing);
    }

    public HostReviewDetailAdapter(Callback callback2, com.airbnb.android.lib.host.stats.views.ReviewStarBreakdownView.Callback reviewBreakdownCallback2, Context context2) {
        super(false);
        ((AirbnbGraph) AirbnbApplication.instance(context2).component()).inject(this);
        this.callback = callback2;
        this.context = context2;
        this.reviewBreakdownCallback = reviewBreakdownCallback2;
        this.checkInDateFormat = context2.getString(C0880R.string.date_name_format);
        this.checkOutDateFormat = context2.getString(C0880R.string.mdy_format_shorter);
        this.reviewCategoryBreakdownModel.hide();
        this.reviewsCarouselAdapter = new HostRecentListingReviewsCarouselAdapter(HostReviewDetailAdapter$$Lambda$1.lambdaFactory$(this, callback2));
        this.reviewScoreCardCarouselEpoxyModel.adapter(this.reviewsCarouselAdapter);
        this.reviewStarBreakdownModel.hide();
        this.reviewCategoryBreakdownModel.hide();
        this.reviewScoreCardSectionHeaderEpoxyModel.hide();
        this.reviewScoreCardCarouselEpoxyModel.hide();
        this.reviewsSectionHeaderEpoxyModel.hide();
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.headerEpoxyModel, this.reviewStarBreakdownModel, this.reviewCategoryBreakdownModel, this.reviewScoreCardSectionHeaderEpoxyModel, this.reviewScoreCardCarouselEpoxyModel, this.reviewsSectionHeaderEpoxyModel, this.loaderEpoxyModel});
    }

    static /* synthetic */ void lambda$new$0(HostReviewDetailAdapter hostReviewDetailAdapter, Callback callback2, View view, Listing listing, int carouselIndex) {
        hostReviewDetailAdapter.statsJitneyLogger.logRatingsRecentRatingTapEvent(listing, (long) carouselIndex);
        callback2.onReviewScoreCardClicked(listing);
    }

    public void setHostRatingBreakdown(HostRatingBreakdown ratingBreakdown) {
        String similarHostRatingString;
        setReviewScoreCardModelsVisible(false);
        this.reviewCategoryBreakdownModel.setRatingBreakdown(ratingBreakdown);
        this.reviewCategoryBreakdownModel.show();
        notifyModelChanged(this.reviewCategoryBreakdownModel);
        if (ratingBreakdown.hasSimilarHostFiveStarRating()) {
            similarHostRatingString = this.context.getString(C0880R.string.host_review_details_screen_market_average_rating, new Object[]{ratingBreakdown.getMarketAverageFiveStarRatingPercentage()});
        } else {
            similarHostRatingString = null;
        }
        this.headerEpoxyModel.description(similarHostRatingString);
        notifyModelChanged(this.headerEpoxyModel);
    }

    /* access modifiers changed from: 0000 */
    public void setReviewStatistics(List<HostRatingDistributionStatistic> statistics) {
        this.reviewStarBreakdownModel.show();
        this.reviewStarBreakdownModel.setReviewData(statistics, this.reviewBreakdownCallback);
        notifyModelChanged(this.reviewStarBreakdownModel);
    }

    /* access modifiers changed from: 0000 */
    public void setReviewStatisticsLoading() {
        this.reviewStarBreakdownModel.hide();
        notifyModelChanged(this.reviewStarBreakdownModel);
    }

    /* access modifiers changed from: 0000 */
    public void setRatedReviewsCount(Integer ratingsCount, Context context2) {
        if (ratingsCount != null) {
            this.headerEpoxyModel.title(context2.getString(C0880R.string.host_review_details_screen_title, new Object[]{this.ratingCountFormatter.format(ratingsCount)}));
            return;
        }
        this.headerEpoxyModel.title(context2.getString(C0880R.string.host_review_details_title));
    }

    public void setReviewsForListing(ArrayList<Review> newReviews, boolean hasMoreToLoad) {
        clearReviews();
        insertModelsForReviews(newReviews);
        setHasMoreToLoad(hasMoreToLoad);
    }

    /* access modifiers changed from: 0000 */
    public void addReviewsForListing(ArrayList<Review> newReviews, boolean hasMoreToLoad) {
        insertModelsForReviews(newReviews);
        setHasMoreToLoad(hasMoreToLoad);
    }

    public void setReviewsForAllListings(ArrayList<Review> newReviews, boolean moreToLoad) {
        clearReviews();
        hideHostRatingBreakdown();
        insertModelsForReviews(newReviews);
        setHasMoreToLoad(moreToLoad);
    }

    public void clearReviews() {
        removeAllAfterModel(this.reviewsSectionHeaderEpoxyModel);
        addModel(this.loaderEpoxyModel);
    }

    /* access modifiers changed from: 0000 */
    public void addReviewsForAllListings(ArrayList<Review> reviews, boolean hasMoreToLoad) {
        insertModelsForReviews(reviews);
        setHasMoreToLoad(hasMoreToLoad);
    }

    private synchronized void insertModelsForReviews(List<Review> reviews) {
        if (!this.reviewsSectionHeaderEpoxyModel.isShown()) {
            this.reviewsSectionHeaderEpoxyModel.show();
            notifyModelChanged(this.reviewsSectionHeaderEpoxyModel);
        }
        int insertionIndex = getModelPosition(this.loaderEpoxyModel);
        this.models.addAll(insertionIndex, reviewsToEpoxyModels(reviews));
        notifyItemRangeInserted(insertionIndex, reviews.size());
        if (BuildHelper.isDebugFeaturesEnabled()) {
            for (Review review : reviews) {
                C0715L.m1189d(TAG, "inserted review: " + review.getListingId() + "\t\t" + review.getId());
            }
        }
    }

    private ImmutableList<? extends EpoxyModel<?>> reviewsToEpoxyModels(List<Review> reviews) {
        return FluentIterable.from((Iterable<E>) reviews).transform(HostReviewDetailAdapter$$Lambda$2.lambdaFactory$(this)).toList();
    }

    /* access modifiers changed from: private */
    public HomeReviewRowEpoxyModel reviewToEpoxyModel(Review review) {
        String checkIn = review.getReservation().getStartDate().formatDate(this.checkInDateFormat);
        String checkOut = review.getReservation().getEndDate().formatDate(this.checkOutDateFormat);
        String dateString = this.context.getString(C0880R.string.separator_with_values, new Object[]{checkIn, checkOut});
        String price = review.getReservation().getHostPayoutAmountPerNightRounded();
        String priceString = this.context.getString(C0880R.string.host_stats_review_details_price_per_night_average, new Object[]{price});
        return new HomeReviewRowEpoxyModel_().review(review).showPrivateComment(true).showPublicFeedback(true).showStarRating(true).showListingName(true).onClickListener(HostReviewDetailAdapter$$Lambda$3.lambdaFactory$(review)).reviewDateString(this.context.getString(C0880R.string.bullet_with_space_parameterized, new Object[]{dateString, priceString}));
    }

    static /* synthetic */ void lambda$reviewToEpoxyModel$1(Review review, View v) {
        Context context1 = v.getContext();
        context1.startActivity(HostReservationObjectActivity.intentForReservationId(context1, review.getReservation().getId(), ROLaunchSource.HostStats));
    }

    public void setRecentReviewCards(List<Listing> listings) {
        if (!BuildHelper.isDevelopmentBuild() || ((Listing) FluentIterable.from((Iterable<E>) listings).firstMatch(HostReviewDetailAdapter$$Lambda$4.lambdaFactory$()).orNull()) == null) {
            this.reviewCategoryBreakdownModel.hide();
            notifyModelChanged(this.reviewCategoryBreakdownModel);
            this.reviewsCarouselAdapter.setItems(listings);
            setReviewScoreCardModelsVisible(!ListUtils.isEmpty((Collection<?>) listings));
            notifyModelChanged(this.reviewScoreCardSectionHeaderEpoxyModel);
            notifyModelChanged(this.reviewScoreCardCarouselEpoxyModel);
            this.headerEpoxyModel.description(null);
            notifyModelChanged(this.headerEpoxyModel);
            return;
        }
        throw new IllegalArgumentException("listings passed in which don't have review scores");
    }

    static /* synthetic */ boolean lambda$setRecentReviewCards$2(Listing input) {
        return input == null || input.getReviewScores() == null;
    }

    private void setReviewScoreCardModelsVisible(boolean visible) {
        this.reviewScoreCardSectionHeaderEpoxyModel.show(visible);
        this.reviewScoreCardCarouselEpoxyModel.show(visible);
        notifyModelChanged(this.reviewScoreCardSectionHeaderEpoxyModel);
        notifyModelChanged(this.reviewScoreCardCarouselEpoxyModel);
    }

    public void setHasMoreToLoad(boolean hasMoreToLoad) {
        this.loaderEpoxyModel.show(hasMoreToLoad);
        notifyModelChanged(this.loaderEpoxyModel);
    }

    private SectionHeaderEpoxyModel_ getSectionHeaderWithTitle(int titleResId) {
        return new SectionHeaderEpoxyModel_().showDivider(false).titleRes(titleResId);
    }

    public void hideHostRatingBreakdown() {
        this.reviewCategoryBreakdownModel.hide();
    }

    /* access modifiers changed from: protected */
    public void onModelBound(EpoxyViewHolder holder, EpoxyModel<?> model, int position) {
        super.onModelBound(holder, model, position);
        if (model == this.loaderEpoxyModel) {
            this.callback.loadMore(FluentIterable.from((Iterable<E>) this.models).filter(HostReviewDetailAdapter$$Lambda$5.lambdaFactory$()).size());
        }
    }

    static /* synthetic */ boolean lambda$onModelBound$3(EpoxyModel input) {
        return input != null && (input instanceof HomeReviewRowEpoxyModel);
    }
}
