package com.airbnb.android.lib.activities.reviews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.widget.Toast;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.intents.LoginActivityIntents;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.requests.GetReviewRequest;
import com.airbnb.android.core.responses.ReviewResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.VerificationsAdapter;
import com.airbnb.android.lib.fragments.reviews.ReviewFeedbackFragment;
import java.util.List;

public class ReviewFeedbackActivity extends SolitAirActivity {
    private static final String KEY_EDIT_PRIVATE_FEEDBACK_FLAG = "edit_private";
    private static final String KEY_EDIT_PUBLIC_FEEDBACK_FLAG = "edit_public";
    private static final String KEY_NAME = "name";
    private static final String KEY_REVIEW = "review";
    private static final String KEY_REVIEW_ID = "review_id";
    private static final int LOGIN_REQUEST = 342;
    private String mName;
    private boolean mReloadOnResume;

    public static Intent intentForReview(Context context, Review review) {
        Intent intent = new Intent(context, ReviewFeedbackActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("review", review);
        intent.putExtras(bundle);
        return intent;
    }

    public static Intent intentForReviewId(Context context, long reviewId) {
        Intent intent = new Intent(context, ReviewFeedbackActivity.class);
        intent.putExtra(KEY_REVIEW_ID, reviewId);
        return intent;
    }

    public static Intent intentForEditPublicFeedback(Context context, Review review) {
        Intent intent = intentForReview(context, review);
        intent.putExtra(KEY_EDIT_PUBLIC_FEEDBACK_FLAG, true);
        return intent;
    }

    public static Intent intentForEditPrivateFeedback(Context context, Review review) {
        Intent intent = intentForReview(context, review);
        intent.putExtra(KEY_EDIT_PRIVATE_FEEDBACK_FLAG, true);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!this.accountManager.isCurrentUserAuthorized()) {
            startActivityForResult(LoginActivityIntents.intentWithToast(this, C0880R.string.toast_msg_log_in_to_leave_reviewy), LOGIN_REQUEST);
        } else if (savedInstanceState == null) {
            loadFragment();
        } else {
            this.mName = savedInstanceState.getString("name");
            setupActionBar(C0880R.string.review_for_user, this.mName);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", this.mName);
    }

    private void loadFragment() {
        long reviewId;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras == null || !extras.containsKey(KEY_REVIEW_ID)) {
            reviewId = getReviewIdFromWebIntent(intent);
        } else {
            reviewId = extras.getLong(KEY_REVIEW_ID, -1);
        }
        if (reviewId != -1) {
            LoaderFrame loaderFrame = getLoaderFrame();
            loaderFrame.setVisibility(0);
            loaderFrame.setBackgroundResource(C0880R.color.c_gray_6);
            new GetReviewRequest(reviewId, new NonResubscribableRequestListener<ReviewResponse>() {
                public void onResponse(ReviewResponse response) {
                    ReviewFeedbackActivity.this.showReview(response.review);
                }

                public void onErrorResponse(AirRequestNetworkException error) {
                    NetworkUtil.toastGenericNetworkError(ReviewFeedbackActivity.this);
                    ReviewFeedbackActivity.this.handleUpNavigation();
                }
            }).execute(this.requestManager);
        } else if (intent.hasExtra("review")) {
            showReview((Review) intent.getParcelableExtra("review"));
        } else {
            handleUpNavigation();
        }
    }

    /* access modifiers changed from: private */
    public void showReview(Review review) {
        Fragment fragment;
        String error;
        getLoaderFrame().finish();
        setupActionBar(C0880R.string.review_for_user, review.getRecipient().getFirstName());
        this.mName = review.getRecipient().getFirstName();
        if (review.isSubmitted()) {
            if (!TextUtils.isEmpty(review.getListingName())) {
                error = getString(C0880R.string.review_already_submitted_for_listing, new Object[]{review.getListingName()});
            } else {
                error = getString(C0880R.string.review_already_submitted);
            }
            Toast.makeText(this, error, 0).show();
            handleUpNavigation();
        } else if (!isFinishing()) {
            if (getIntent().getBooleanExtra(KEY_EDIT_PUBLIC_FEEDBACK_FLAG, false)) {
                fragment = ReviewFeedbackFragment.newInstanceEditPublicFeedback(review);
            } else if (getIntent().getBooleanExtra(KEY_EDIT_PRIVATE_FEEDBACK_FLAG, false)) {
                fragment = ReviewFeedbackFragment.newInstanceEditPrivateFeedback(review);
            } else {
                fragment = ReviewFeedbackFragment.newInstance(review);
            }
            showFragment(fragment, false);
        }
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        handleUpNavigation();
    }

    /* access modifiers changed from: private */
    public void handleUpNavigation() {
        ReviewFeedbackFragment fragment = (ReviewFeedbackFragment) getSupportFragmentManager().findFragmentById(C0880R.C0882id.content_container);
        if (fragment != null) {
            fragment.finishReviewFeedbackActivity();
        } else {
            navigateUp();
        }
    }

    public void navigateUp() {
        super.navigateUp();
    }

    private long getReviewIdFromWebIntent(Intent intent) {
        Uri intentData = intent.getData();
        long reviewId = -1;
        if (intentData == null) {
            return reviewId;
        }
        List<String> params = intentData.getPathSegments();
        if (params.size() <= 2 || !VerificationsAdapter.VERIFICATION_REVIEWS.equals(params.get(0))) {
            return reviewId;
        }
        if ("edit".equals(params.get(1))) {
            try {
                return Long.parseLong((String) params.get(2));
            } catch (NumberFormatException e) {
                BuildHelper.debugErrorLog(ReviewFeedbackActivity.class.getSimpleName(), "error parsing review id", e);
                return reviewId;
            }
        } else if (!"edit".equals(params.get(2))) {
            return reviewId;
        } else {
            try {
                return Long.parseLong((String) params.get(1));
            } catch (NumberFormatException e2) {
                BuildHelper.debugErrorLog(ReviewFeedbackActivity.class.getSimpleName(), "error parsing review id", e2);
                return reviewId;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mReloadOnResume) {
            loadFragment();
            this.mReloadOnResume = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != LOGIN_REQUEST) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (resultCode != -1 || !this.accountManager.isCurrentUserAuthorized()) {
            finish();
        } else {
            this.mReloadOnResume = true;
        }
    }

    public void onBackPressed() {
        handleUpNavigation();
    }
}
