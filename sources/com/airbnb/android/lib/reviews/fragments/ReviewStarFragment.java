package com.airbnb.android.lib.reviews.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.SheetFlowActivity.SheetTheme;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.Review.RatingType;
import com.airbnb.android.core.models.ReviewRole;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.KonaReviewAnalytics;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import java.util.List;

public class ReviewStarFragment extends BaseWriteReviewFragment {
    private static final String ARG_RATING_TYPE = "rating_type";
    @BindView
    FloatingActionButton fab;
    @BindView
    SheetMarquee marqee;
    @BindView
    RatingBar ratingBar;
    @BindView
    TextView ratingDescription;
    private RatingType ratingType;

    public static ReviewStarFragment newInstanceFirstPage(Review review) {
        return newInstance((RatingType) review.getRatingTypes().get(0));
    }

    public static ReviewStarFragment newInstance(RatingType rating) {
        return (ReviewStarFragment) ((FragmentBundleBuilder) FragmentBundler.make(new ReviewStarFragment()).putParcelable(ARG_RATING_TYPE, rating)).build();
    }

    @SuppressLint({"NewApi"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_review_star, null);
        bindViews(view);
        this.ratingType = (RatingType) getArguments().getParcelable(ARG_RATING_TYPE);
        this.fab.setBackgroundTintList(getResources().getColorStateList(C0880R.color.white));
        this.fab.setImageDrawable(ColorizedDrawable.forIdWithColor(getActivity(), C0880R.C0881drawable.icon_next, C0880R.color.n2_babu));
        setupText();
        Integer currentValue = getReview().getRatingValue(this.ratingType);
        if (!(currentValue == null || currentValue.intValue() == 0)) {
            this.ratingBar.setRating((float) getReview().getRatingValue(this.ratingType).intValue());
            this.fab.setOnClickListener(ReviewStarFragment$$Lambda$1.lambdaFactory$(this));
            this.fab.setVisibility(0);
            showRatingDescription();
        }
        this.ratingBar.setOnRatingBarChangeListener(ReviewStarFragment$$Lambda$2.lambdaFactory$(this));
        KonaReviewAnalytics.trackRatingImpression(getReview(), this.ratingType.name().toLowerCase());
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$1(ReviewStarFragment reviewStarFragment, RatingBar ratingBar2, float rating, boolean fromUser) {
        if (fromUser) {
            reviewStarFragment.getReview().setRatingValue(reviewStarFragment.ratingType, Integer.valueOf((int) rating));
            reviewStarFragment.showRatingDescription();
            ratingBar2.postDelayed(ReviewStarFragment$$Lambda$3.lambdaFactory$(reviewStarFragment), 500);
            reviewStarFragment.wrInterface.saveProgress(reviewStarFragment.ratingType, (Object) Float.valueOf(rating));
            KonaReviewAnalytics.trackRatingClick(reviewStarFragment.getReview(), reviewStarFragment.ratingType.name().toLowerCase());
        }
    }

    public void onDestroyView() {
        this.ratingBar.setOnRatingBarChangeListener(null);
        super.onDestroyView();
    }

    /* access modifiers changed from: private */
    public void goToNextRating() {
        this.wrInterface.showFragment(getNext(this.ratingType));
        KonaReviewAnalytics.trackRatingSubmit(getReview(), this.ratingType.name().toLowerCase());
    }

    private BaseWriteReviewFragment getNext(RatingType ratingType2) {
        List<RatingType> allRatings = getReview().getRatingTypes();
        int currentPosition = allRatings.indexOf(ratingType2);
        if (currentPosition == -1) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("unexpected RatingType: " + ratingType2.name() + " for review id " + this.wrInterface.getReview().getId()));
            getActivity().finish();
        }
        RatingType nextRating = (RatingType) allRatings.get(currentPosition + 1);
        if (nextRating == RatingType.Recommend) {
            return ReviewThumbFragment.newInstance();
        }
        return newInstance(nextRating);
    }

    private void setupText() {
        boolean isHostRole;
        int titleId;
        int subtitleId;
        boolean z = true;
        if (getReview().getReviewRole() == ReviewRole.Host) {
            isHostRole = true;
        } else {
            isHostRole = false;
        }
        switch (this.ratingType) {
            case Overall:
                if (isHostRole) {
                    z = false;
                }
                Check.state(z);
                titleId = C0880R.string.review_overall_title;
                subtitleId = C0880R.string.review_overall_subtitle;
                break;
            case Accuracy:
                if (isHostRole) {
                    z = false;
                }
                Check.state(z);
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
                Check.state(isHostRole);
                titleId = C0880R.string.review_house_rules_title;
                subtitleId = C0880R.string.review_house_rules_subtitle;
                break;
            case CheckIn:
                if (isHostRole) {
                    z = false;
                }
                Check.state(z);
                titleId = C0880R.string.review_check_in_title;
                subtitleId = C0880R.string.review_check_in_subtitle;
                break;
            case Location:
                if (isHostRole) {
                    z = false;
                }
                Check.state(z);
                titleId = C0880R.string.review_location_title;
                subtitleId = C0880R.string.review_location_subtitle;
                break;
            case Value:
                if (isHostRole) {
                    z = false;
                }
                Check.state(z);
                titleId = C0880R.string.review_value_title;
                subtitleId = C0880R.string.review_value_subtitle;
                break;
            default:
                throw new IllegalStateException("unexpected enum " + this.ratingType.name());
        }
        this.marqee.setTitle(titleId);
        this.marqee.setSubtitle(subtitleId);
    }

    /* access modifiers changed from: 0000 */
    public SheetTheme getTheme() {
        return SheetTheme.BABU;
    }

    private void showRatingDescription() {
        int ratingString;
        switch ((int) this.ratingBar.getRating()) {
            case 1:
                ratingString = C0880R.string.review_rating_one;
                break;
            case 2:
                ratingString = C0880R.string.review_rating_two;
                break;
            case 3:
                ratingString = C0880R.string.review_rating_three;
                break;
            case 4:
                ratingString = C0880R.string.review_rating_four;
                break;
            case 5:
                ratingString = C0880R.string.review_rating_five;
                break;
            default:
                ratingString = 0;
                break;
        }
        this.ratingDescription.setText(ratingString);
    }
}
