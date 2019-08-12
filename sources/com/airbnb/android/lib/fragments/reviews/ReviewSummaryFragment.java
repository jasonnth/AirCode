package com.airbnb.android.lib.fragments.reviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.events.AlertsChangedEvent;
import com.airbnb.android.core.events.ReviewUpdatedEvent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.SubmitReviewRequest;
import com.airbnb.android.core.responses.ReviewResponse;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.reviews.ReviewFeedbackActivity;
import com.airbnb.android.lib.activities.reviews.ReviewRatingsActivity;
import com.airbnb.android.lib.analytics.ReviewsAnalytics;
import com.airbnb.android.lib.fragments.NPSFragment;
import com.airbnb.android.lib.views.StickyButton;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.squareup.otto.Subscribe;

public class ReviewSummaryFragment extends AirFragment {
    private static final String KEY_REVIEW = "review";
    private TextView mGuestName;
    private HaloImageView mHeaderImage;
    private TextView mListingName;
    /* access modifiers changed from: private */
    public LoaderFrame mLoaderFrame;
    private RatingBar mOverallRating;
    private LinearLayout mOverallRatingLayout;
    private TextView mPrivateFeedback;
    private LinearLayout mPrivateFeedbackLayout;
    private TextView mPrivateFeedbackTitle;
    private TextView mPublicFeedback;
    private LinearLayout mPublicFeedbackLayout;
    private ImageView mRecommendImage;
    private TextView mRecommendTitle;
    private LinearLayout mRecommendedLayout;
    private TextView mReservationDates;
    /* access modifiers changed from: private */
    public Review mReview;
    private HaloImageView mRevieweeImage;
    private HaloImageView mReviewerImage;

    public static ReviewSummaryFragment newInstance(Review review) {
        ReviewSummaryFragment f = new ReviewSummaryFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("review", review);
        f.setArguments(bundle);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_review_summary, null);
        this.mReview = (Review) getArguments().getParcelable("review");
        initializeViews(view);
        return view;
    }

    public void onResume() {
        super.onResume();
        loadFields();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBus.register(this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBus.unregister(this);
    }

    private void loadFields() {
        int i;
        boolean recommended;
        int i2 = 0;
        Reservation reservation = this.mReview.getReservation();
        this.mReservationDates.setText(DateHelper.getStringDateSpan((Context) getActivity(), reservation.getStartDate(), reservation.getReservedNightsCount()));
        this.mListingName.setText(this.mReview.getListingName());
        this.mPublicFeedback.setText(this.mReview.getPublicFeedback());
        this.mPrivateFeedback.setText(this.mReview.getCombinedPrivateFeedback());
        LinearLayout linearLayout = this.mPrivateFeedbackLayout;
        if (TextUtils.isEmpty(this.mReview.getCombinedPrivateFeedback())) {
            i = 8;
        } else {
            i = 0;
        }
        linearLayout.setVisibility(i);
        if (this.mReview.isRecommended() == null || !this.mReview.isRecommended().booleanValue()) {
            recommended = false;
        } else {
            recommended = true;
        }
        this.mRecommendImage.setImageDrawable(ColorizedDrawable.forIdStateList(this.mRecommendImage.getContext(), recommended ? C0880R.C0881drawable.recomm_yes : C0880R.C0881drawable.recomm_no, recommended ? C0880R.color.c_lima : C0880R.color.c_rausch));
        this.mReviewerImage.setImageUrl(this.mReview.getAuthor().getPictureUrl());
        Integer averageRating = this.mReview.getAverageRating();
        if (averageRating != null) {
            i2 = averageRating.intValue();
        }
        this.mOverallRating.setRating((float) Integer.valueOf(i2).intValue());
        User reviewee = this.mReview.getRecipient();
        this.mRevieweeImage.setImageUrl(reviewee.getPictureUrl());
        this.mHeaderImage.setImageUrl(reviewee.getPictureUrl());
        this.mGuestName.setText(reviewee.getFirstName());
        if (this.mReview.isGuestReviewingHost()) {
            this.mPrivateFeedbackTitle.setText(getString(C0880R.string.review_private_feedback_title_reviewing_host));
            this.mRecommendTitle.setText(getString(C0880R.string.review_would_you_recommend_reviewing_host));
            return;
        }
        this.mPrivateFeedbackTitle.setText(getString(C0880R.string.review_private_feedback_title_reviewing_guest));
        this.mRecommendTitle.setText(getString(C0880R.string.review_would_you_recommend_reviewing_guest));
    }

    private void initializeViews(View v) {
        FrameLayout mHeader = (FrameLayout) ButterKnife.findById(v, C0880R.C0882id.review_summary_header);
        mHeader.setOnClickListener(ReviewSummaryFragment$$Lambda$1.lambdaFactory$(this));
        AirImageView mHeaderBackground = (AirImageView) ButterKnife.findById((View) mHeader, C0880R.C0882id.review_header_background_image);
        Listing listing = this.mReview.getReservation() != null ? this.mReview.getReservation().getListing() : null;
        if (listing != null) {
            mHeaderBackground.setImageUrl(listing.getPictureUrl());
        } else {
            mHeaderBackground.setImageResource(C0880R.C0881drawable.hh_default_photo);
        }
        this.mHeaderImage = (HaloImageView) ButterKnife.findById((View) mHeader, C0880R.C0882id.guest_image_view);
        this.mGuestName = (TextView) ButterKnife.findById((View) mHeader, C0880R.C0882id.guest_name);
        this.mListingName = (TextView) ButterKnife.findById((View) mHeader, C0880R.C0882id.listing_name);
        this.mReservationDates = (TextView) ButterKnife.findById((View) mHeader, C0880R.C0882id.reservation_dates);
        this.mPublicFeedback = (TextView) ButterKnife.findById(v, C0880R.C0882id.public_feedback);
        this.mPrivateFeedbackTitle = (TextView) ButterKnife.findById(v, C0880R.C0882id.private_feedback_title);
        this.mPrivateFeedback = (TextView) ButterKnife.findById(v, C0880R.C0882id.private_feedback);
        this.mRecommendTitle = (TextView) ButterKnife.findById(v, C0880R.C0882id.recommend_title);
        this.mRecommendImage = (ImageView) ButterKnife.findById(v, C0880R.C0882id.recommend_image);
        this.mRevieweeImage = (HaloImageView) ButterKnife.findById(v, C0880R.C0882id.reviewee_image);
        this.mReviewerImage = (HaloImageView) ButterKnife.findById(v, C0880R.C0882id.reviewer_image);
        this.mOverallRating = (RatingBar) ButterKnife.findById(v, C0880R.C0882id.overall_rating);
        this.mLoaderFrame = (LoaderFrame) ButterKnife.findById(v, C0880R.C0882id.loading_overlay);
        StickyButton mSubmitButton = (StickyButton) ButterKnife.findById(v, C0880R.C0882id.submit_button);
        mSubmitButton.setTitle(C0880R.string.submit);
        mSubmitButton.setOnClickListener(ReviewSummaryFragment$$Lambda$2.lambdaFactory$(this));
        OnClickListener sectionClickListener = ReviewSummaryFragment$$Lambda$3.lambdaFactory$(this);
        this.mPublicFeedbackLayout = (LinearLayout) ButterKnife.findById(v, C0880R.C0882id.public_feedback_layout);
        this.mPublicFeedbackLayout.setOnClickListener(sectionClickListener);
        this.mPrivateFeedbackLayout = (LinearLayout) ButterKnife.findById(v, C0880R.C0882id.private_feedback_layout);
        this.mPrivateFeedbackLayout.setOnClickListener(sectionClickListener);
        this.mOverallRatingLayout = (LinearLayout) ButterKnife.findById(v, C0880R.C0882id.overall_rating_layout);
        this.mOverallRatingLayout.setOnClickListener(sectionClickListener);
        this.mRecommendedLayout = (LinearLayout) ButterKnife.findById(v, C0880R.C0882id.recommend_layout);
        this.mRecommendedLayout.setOnClickListener(sectionClickListener);
    }

    static /* synthetic */ void lambda$initializeViews$1(ReviewSummaryFragment reviewSummaryFragment, View v1) {
        reviewSummaryFragment.mLoaderFrame.setVisibility(0);
        new SubmitReviewRequest(reviewSummaryFragment.mReview, new NonResubscribableRequestListener<ReviewResponse>() {
            public void onResponse(ReviewResponse response) {
                ReviewSummaryFragment.this.mReview = response.review;
                ReviewSummaryFragment.this.mBus.post(new ReviewUpdatedEvent(ReviewSummaryFragment.this.mReview));
                ReviewSummaryFragment.this.mBus.post(new AlertsChangedEvent());
                if (ReviewSummaryFragment.this.isResumed()) {
                    ReviewSummaryFragment.this.mLoaderFrame.finish();
                    ReviewSummaryFragment.this.startActivity(NPSFragment.intentForDefault(ReviewSummaryFragment.this.getActivity(), ReviewSummaryFragment.this.mReview));
                    ReviewSummaryFragment.this.getActivity().finish();
                }
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                if (ReviewSummaryFragment.this.isResumed()) {
                    ReviewSummaryFragment.this.mLoaderFrame.finish();
                    NetworkUtil.toastGenericNetworkError(ReviewSummaryFragment.this.getActivity());
                }
            }
        }).execute(NetworkUtil.singleFireExecutor());
        ReviewsAnalytics.trackSubmitReview(reviewSummaryFragment.mReview);
    }

    static /* synthetic */ void lambda$initializeViews$2(ReviewSummaryFragment reviewSummaryFragment, View v1) {
        Intent intent = null;
        if (v1.getId() == reviewSummaryFragment.mPublicFeedbackLayout.getId()) {
            intent = ReviewFeedbackActivity.intentForEditPublicFeedback(reviewSummaryFragment.getActivity(), reviewSummaryFragment.mReview);
            ReviewsAnalytics.trackEditFeedback(reviewSummaryFragment.mReview);
        } else if (v1.getId() == reviewSummaryFragment.mPrivateFeedbackLayout.getId()) {
            intent = ReviewFeedbackActivity.intentForEditPrivateFeedback(reviewSummaryFragment.getActivity(), reviewSummaryFragment.mReview);
            ReviewsAnalytics.trackEditFeedback(reviewSummaryFragment.mReview);
        } else if (v1.getId() == reviewSummaryFragment.mOverallRatingLayout.getId()) {
            intent = ReviewRatingsActivity.intentForEditReview(reviewSummaryFragment.getActivity(), reviewSummaryFragment.mReview);
            ReviewsAnalytics.trackEditRating(reviewSummaryFragment.mReview);
        } else if (v1.getId() == reviewSummaryFragment.mRecommendedLayout.getId()) {
            intent = ReviewRatingsActivity.intentForEditRecommend(reviewSummaryFragment.getActivity(), reviewSummaryFragment.mReview);
            ReviewsAnalytics.trackEditRecommend(reviewSummaryFragment.mReview);
        }
        if (intent != null) {
            reviewSummaryFragment.startActivity(intent);
        }
    }

    @Subscribe
    public void reviewUpdated(ReviewUpdatedEvent update) {
        this.mReview = update.review;
        if (isResumed() && !this.mReview.isSubmitted()) {
            loadFields();
        }
    }
}
