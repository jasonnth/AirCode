package com.airbnb.android.lib.fragments.reviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.android.core.events.ReviewUpdatedEvent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.Review.RatingType;
import com.airbnb.android.core.models.ReviewRole;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.ReviewRatingsAdapter.CanProgress;
import com.squareup.otto.Subscribe;

public class ReviewRatingFragment extends AirFragment implements CanProgress {
    private static final String KEY_RATING_TYPE = "rating_type";
    private static final String KEY_REVIEW = "review";
    private TextView mExclamation;
    long mRatingChangeTime;
    private RatingBar mRatingStars;
    private RatingUpdatedCallback mRatingUpdatedCallback;
    private Review mReview;
    private RatingType mType;

    public interface RatingUpdatedCallback {
        void onRatingUpdated();
    }

    public static ReviewRatingFragment newInstance(Review review, RatingType type) {
        ReviewRatingFragment f = new ReviewRatingFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("review", review);
        bundle.putInt(KEY_RATING_TYPE, type.ordinal());
        f.setArguments(bundle);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_review_rating, container, false);
        this.mReview = (Review) getArguments().getParcelable("review");
        this.mType = RatingType.values()[getArguments().getInt(KEY_RATING_TYPE)];
        initializeViews(view);
        return view;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof RatingUpdatedCallback)) {
            throw new IllegalArgumentException(activity.toString() + " must implement RatingUpdatedCallback");
        }
        this.mRatingUpdatedCallback = (RatingUpdatedCallback) activity;
    }

    public void onResume() {
        super.onResume();
        updateRating();
    }

    private void updateRating() {
        Integer rating = this.mReview.getRatingValue(this.mType);
        if (rating != null) {
            this.mRatingStars.setRating((float) rating.intValue());
            this.mExclamation.setText(getExclamation(rating));
        }
    }

    private String getExclamation(Integer rating) {
        if (rating == null) {
            return "";
        }
        switch (rating.intValue()) {
            case 1:
                return getString(C0880R.string.review_rating_one);
            case 2:
                return getString(C0880R.string.review_rating_two);
            case 3:
                return getString(C0880R.string.review_rating_three);
            case 4:
                return getString(C0880R.string.review_rating_four);
            case 5:
                return getString(C0880R.string.review_rating_five);
            default:
                return "";
        }
    }

    private void initializeViews(View v) {
        TextView title = (TextView) ButterKnife.findById(v, C0880R.C0882id.review_rating_title);
        TextView subtitle = (TextView) ButterKnife.findById(v, C0880R.C0882id.review_rating_description);
        int titleId = 0;
        int subtitleId = 0;
        boolean isHostRole = this.mReview.getReviewRole() == ReviewRole.Host;
        switch (this.mType) {
            case Overall:
                titleId = C0880R.string.review_overall_title;
                subtitleId = C0880R.string.review_overall_subtitle;
                break;
            case Accuracy:
                titleId = C0880R.string.review_accuracy_title;
                subtitleId = C0880R.string.review_accuracy_subtitle;
                break;
            case Cleanliness:
                titleId = C0880R.string.review_cleanliness_title;
                if (!isHostRole) {
                    subtitleId = C0880R.string.review_cleanliness_subtitle_for_host;
                    break;
                } else {
                    subtitleId = C0880R.string.review_cleanliness_subtitle_for_guest;
                    break;
                }
            case Communication:
                titleId = C0880R.string.review_communication_title;
                if (!isHostRole) {
                    subtitleId = C0880R.string.review_communication_subtitle_for_host;
                    break;
                } else {
                    subtitleId = C0880R.string.review_communication_subtitle_for_guest;
                    break;
                }
            case HouseRuleObservance:
                titleId = C0880R.string.review_house_rules_title;
                subtitleId = C0880R.string.review_house_rules_subtitle;
                break;
            case CheckIn:
                titleId = C0880R.string.review_check_in_title;
                subtitleId = C0880R.string.review_check_in_subtitle;
                break;
            case Location:
                titleId = C0880R.string.review_location_title;
                subtitleId = C0880R.string.review_location_subtitle;
                break;
            case Value:
                titleId = C0880R.string.review_value_title;
                subtitleId = C0880R.string.review_value_subtitle;
                break;
        }
        title.setText(titleId);
        subtitle.setText(subtitleId);
        this.mExclamation = (TextView) ButterKnife.findById(v, C0880R.C0882id.review_rating_exclamation);
        this.mRatingStars = (RatingBar) ButterKnife.findById(v, C0880R.C0882id.review_rating_stars);
        OnRatingBarChangeListener ratingBarListener = ReviewRatingFragment$$Lambda$1.lambdaFactory$(this);
        this.mRatingStars.setOnRatingBarChangeListener(ratingBarListener);
        this.mRatingStars.setOnTouchListener(ReviewRatingFragment$$Lambda$2.lambdaFactory$(this, ratingBarListener));
    }

    static /* synthetic */ void lambda$initializeViews$0(ReviewRatingFragment reviewRatingFragment, RatingBar ratingBar, float rating, boolean fromUser) {
        if (fromUser) {
            reviewRatingFragment.mReview.setRatingValue(reviewRatingFragment.mType, Integer.valueOf((int) rating));
            reviewRatingFragment.mBus.post(new ReviewUpdatedEvent(reviewRatingFragment.mReview));
            reviewRatingFragment.mRatingUpdatedCallback.onRatingUpdated();
            reviewRatingFragment.mRatingChangeTime = System.currentTimeMillis();
        }
    }

    static /* synthetic */ boolean lambda$initializeViews$2(ReviewRatingFragment reviewRatingFragment, OnRatingBarChangeListener ratingBarListener, View view, MotionEvent event) {
        if (event.getAction() == 1) {
            reviewRatingFragment.mRatingStars.postDelayed(ReviewRatingFragment$$Lambda$3.lambdaFactory$(reviewRatingFragment, ratingBarListener), 100);
        }
        return false;
    }

    static /* synthetic */ void lambda$null$1(ReviewRatingFragment reviewRatingFragment, OnRatingBarChangeListener ratingBarListener) {
        if (System.currentTimeMillis() - reviewRatingFragment.mRatingChangeTime > 200) {
            ratingBarListener.onRatingChanged(null, reviewRatingFragment.mRatingStars.getRating(), true);
        }
    }

    public void onStart() {
        super.onStart();
        if (this.mReview.isSubmitted()) {
            getActivity().finish();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBus.register(this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mBus.unregister(this);
    }

    public boolean canProgress() {
        return this.mReview.getRatingValue(this.mType) != null;
    }

    @Subscribe
    public void reviewUpdated(ReviewUpdatedEvent update) {
        this.mReview = update.review;
        if (isResumed()) {
            updateRating();
        }
    }
}
