package com.airbnb.android.lib.reviews.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.widget.Toast;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.SheetFlowActivity;
import com.airbnb.android.core.activities.SheetFlowActivity.SheetTheme;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.Review.RatingType;
import com.airbnb.android.core.models.ReviewRole;
import com.airbnb.android.core.requests.ReviewRequest;
import com.airbnb.android.core.requests.UpdateReviewRequest;
import com.airbnb.android.core.responses.ReviewResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.reviews.fragments.FeedbackIntroFragment;
import com.airbnb.android.lib.reviews.fragments.WriteFeedbackIntroFragment.FeedbackField;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;
import p032rx.Observer;

public class WriteReviewActivity extends SheetFlowActivity {
    private static final String KEY_REVIEW = "review";
    private static final String KEY_REVIEW_ID = "review_id";
    private static final int PRIVATE_FEEDBACK_WEIGHT = 1;
    private static final int PUBLIC_FEEDBACK_WEIGHT = 3;
    @State
    Review review;
    final RequestListener<ReviewResponse> reviewListener = new C0699RL().onResponse(WriteReviewActivity$$Lambda$1.lambdaFactory$(this)).onError(WriteReviewActivity$$Lambda$2.lambdaFactory$(this)).build();
    @State
    boolean skippedPrivateFeedback = false;

    public static Intent newIntent(Context context, long reviewId) {
        Check.state(reviewId > 0);
        return new Intent(context, WriteReviewActivity.class).putExtra(KEY_REVIEW_ID, reviewId);
    }

    public static Intent newIntent(Context context, Review review2) {
        Check.notNull(review2);
        return new Intent(context, WriteReviewActivity.class).putExtra("review", review2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        long reviewId;
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (savedInstanceState == null) {
            this.review = (Review) getIntent().getParcelableExtra("review");
        }
        if (this.review == null) {
            if (DeepLinkUtils.isDeepLink(intent)) {
                reviewId = DeepLinkUtils.getParamAsId(intent, "id");
            } else {
                reviewId = intent.getLongExtra(KEY_REVIEW_ID, -1);
            }
            Check.validId(reviewId);
            new ReviewRequest(reviewId).withListener((Observer) this.reviewListener).execute(this.requestManager);
            return;
        }
        setReview(this.review);
    }

    static /* synthetic */ void lambda$new$1(WriteReviewActivity writeReviewActivity, AirRequestNetworkException e) {
        NetworkUtil.toastGenericNetworkError(writeReviewActivity);
        writeReviewActivity.finish();
    }

    /* access modifiers changed from: private */
    public void setReview(Review review2) {
        validateReviewState(review2);
        this.review = review2;
        showFragment(FeedbackIntroFragment.newInstance());
    }

    private void validateReviewState(Review review2) {
        if (review2.isSubmitted()) {
            Toast.makeText(this, C0880R.string.review_already_submitted, 0).show();
            finish();
            return;
        }
        ReviewRole role = review2.getReviewRole();
        if (role != ReviewRole.Guest && role != ReviewRole.Host) {
            BugsnagWrapper.notify((Throwable) new IllegalArgumentException("review role is unsupported for review " + review2.getId() + " and user " + this.accountManager.getCurrentUser().getId()));
            NetworkUtil.toastGenericNetworkError(this);
            finish();
        }
    }

    public Review getReview() {
        return (Review) Check.notNull(this.review, "called getReview() when review is not yet loaded");
    }

    public boolean useHomeAsBack() {
        return true;
    }

    public SheetTheme getDefaultTheme() {
        return SheetTheme.WHITE;
    }

    public void showFragment(Fragment fragment) {
        super.showFragment(fragment);
        this.progressBar.setProgress(getProgress(), true);
    }

    public void popAndShowFragment(Fragment currentFragment, Fragment newFragment) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(C0880R.anim.fragment_enter, C0880R.anim.fragment_exit).remove(currentFragment).commit();
        getSupportFragmentManager().popBackStack();
        showFragment(newFragment);
    }

    public void markSkipPrivateFeedback() {
        this.skippedPrivateFeedback = true;
    }

    private float getProgress() {
        ArrayList<RatingType> ratingTypes = getReview().getRatingTypes();
        int totalPoints = ratingTypes.size() + 4;
        int pointsSoFar = 0;
        if (!TextUtils.isEmpty(getReview().getPublicFeedback())) {
            pointsSoFar = 0 + 3;
        }
        if (getReview().getPrivateFeedback() != null || this.skippedPrivateFeedback) {
            pointsSoFar++;
        }
        Iterator it = ratingTypes.iterator();
        while (it.hasNext()) {
            RatingType ratingType = (RatingType) it.next();
            if (ratingType != RatingType.Recommend || getReview().isRecommended() == null) {
                Integer currentValue = getReview().getRatingValue(ratingType);
                if (!(currentValue == null || currentValue.intValue() == 0)) {
                    pointsSoFar++;
                }
            } else {
                pointsSoFar++;
            }
        }
        return ((float) pointsSoFar) / ((float) totalPoints);
    }

    public void saveProgress(FeedbackField type, String feedback) {
        UpdateReviewRequest.forSaveProgress(getReview(), type == FeedbackField.PUBLIC ? UpdateReviewRequest.KEY_PUBLIC_FEEDBACK : UpdateReviewRequest.KEY_PRIVATE_FEEDBACK, feedback).execute(NetworkUtil.singleFireExecutor());
    }

    public void saveProgress(RatingType type, Object value) {
        String key;
        switch (type) {
            case Overall:
                key = UpdateReviewRequest.KEY_OVERALL;
                break;
            case Accuracy:
                key = UpdateReviewRequest.KEY_ACCURACY;
                break;
            case Cleanliness:
                key = UpdateReviewRequest.KEY_CLEANLINESS;
                break;
            case CheckIn:
                key = UpdateReviewRequest.KEY_CHECKIN;
                break;
            case Communication:
                key = UpdateReviewRequest.KEY_COMMUNICATION;
                break;
            case Location:
                key = "location";
                break;
            case Value:
                key = "value";
                break;
            case HouseRuleObservance:
                key = UpdateReviewRequest.KEY_RESPECT_HOUSE_RULES;
                break;
            case Recommend:
                throw new IllegalStateException("do not save progress for recommend : " + type.name());
            default:
                key = null;
                break;
        }
        if (key == null) {
            BugsnagWrapper.notify((Throwable) new UnsupportedOperationException("review save progress for ratingType " + type.name()));
        } else {
            UpdateReviewRequest.forSaveProgress(getReview(), key, value).execute(NetworkUtil.singleFireExecutor());
        }
    }
}
