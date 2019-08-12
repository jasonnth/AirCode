package com.airbnb.android.lib.fragments.reviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.android.core.events.ReviewUpdatedEvent;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.reviews.ReviewSummaryActivity;
import com.airbnb.android.lib.adapters.ReviewRatingsAdapter.CanProgress;
import com.airbnb.android.lib.adapters.ReviewRatingsAdapter.SetEditMode;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.squareup.otto.Subscribe;

public class ReviewRecommendFragment extends AirFragment implements CanProgress, SetEditMode {
    private static final String KEY_REVIEW = "review";
    private static final long SHOW_SUMMARY_DELAY = 500;
    private final Handler handler = new Handler();
    private boolean mEditMode;
    private Drawable mNoSelectedDrawable;
    private Drawable mNoUnselectedDrawable;
    @BindView
    ImageView mRecommendNo;
    @BindView
    ViewGroup mRecommendNoHolder;
    @BindView
    TextView mRecommendSubtitle;
    @BindView
    TextView mRecommendTitle;
    @BindView
    ImageView mRecommendYes;
    @BindView
    ViewGroup mRecommendYesHolder;
    private Review mReview;
    private Drawable mYesSelectedDrawable;
    private Drawable mYesUnselectedDrawable;

    public static ReviewRecommendFragment newInstance(Review review) {
        ReviewRecommendFragment f = new ReviewRecommendFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("review", review);
        f.setArguments(bundle);
        return f;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_review_recommend, null);
        bindViews(view);
        setThumbColorizedDrawables();
        this.mReview = (Review) getArguments().getParcelable("review");
        if (this.mReview.isGuestReviewingHost()) {
            this.mRecommendTitle.setText(getString(C0880R.string.review_would_you_recommend_reviewing_host));
            this.mRecommendSubtitle.setText(getString(C0880R.string.review_anonymous_answer_reviewing_host));
        } else {
            this.mRecommendTitle.setText(getString(C0880R.string.review_would_you_recommend_reviewing_guest));
            this.mRecommendSubtitle.setText(getString(C0880R.string.review_anonymous_answer_reviewing_guest));
        }
        OnClickListener yesNoClickListener = ReviewRecommendFragment$$Lambda$1.lambdaFactory$(this);
        this.mRecommendNoHolder.setOnClickListener(yesNoClickListener);
        this.mRecommendYesHolder.setOnClickListener(yesNoClickListener);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$1(ReviewRecommendFragment reviewRecommendFragment, View v) {
        Boolean recommend = null;
        if (v == reviewRecommendFragment.mRecommendYesHolder) {
            recommend = Boolean.valueOf(true);
        } else if (v == reviewRecommendFragment.mRecommendNoHolder) {
            recommend = Boolean.valueOf(false);
        }
        if (recommend != null) {
            reviewRecommendFragment.mReview.setRecommended(recommend);
            reviewRecommendFragment.mBus.post(new ReviewUpdatedEvent(reviewRecommendFragment.mReview));
            reviewRecommendFragment.updateViews();
            reviewRecommendFragment.handler.removeCallbacksAndMessages(null);
            reviewRecommendFragment.handler.postDelayed(ReviewRecommendFragment$$Lambda$2.lambdaFactory$(reviewRecommendFragment), SHOW_SUMMARY_DELAY);
        } else if (BuildHelper.isDevelopmentBuild()) {
            throw new IllegalStateException("the on click listener is probably not set to correct view");
        }
    }

    static /* synthetic */ void lambda$null$0(ReviewRecommendFragment reviewRecommendFragment) {
        if (reviewRecommendFragment.mEditMode) {
            reviewRecommendFragment.getActivity().finish();
        }
        reviewRecommendFragment.startActivity(ReviewSummaryActivity.intentForReview(reviewRecommendFragment.getActivity(), reviewRecommendFragment.mReview));
    }

    public void onResume() {
        super.onResume();
        updateViews();
    }

    private void updateViews() {
        Boolean recommended = this.mReview.isRecommended();
        if (recommended != null) {
            this.mRecommendYes.setImageDrawable(recommended.booleanValue() ? this.mYesSelectedDrawable : this.mYesUnselectedDrawable);
            this.mRecommendNo.setImageDrawable(!recommended.booleanValue() ? this.mNoSelectedDrawable : this.mNoUnselectedDrawable);
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
        return this.mReview.isRecommended() != null;
    }

    @Subscribe
    public void reviewUpdated(ReviewUpdatedEvent update) {
        this.mReview = update.review;
        if (isResumed()) {
            updateViews();
        }
    }

    public void setEditMode(boolean editMode) {
        this.mEditMode = editMode;
    }

    private void setThumbColorizedDrawables() {
        Context context = getActivity();
        this.mYesSelectedDrawable = ColorizedDrawable.forIdWithColor(context, C0880R.C0881drawable.recomm_yes, C0880R.color.c_lima);
        this.mYesUnselectedDrawable = ColorizedDrawable.forIdWithColor(context, C0880R.C0881drawable.recomm_yes, C0880R.color.c_lima_special);
        this.mNoSelectedDrawable = ColorizedDrawable.forIdWithColor(context, C0880R.C0881drawable.recomm_no, C0880R.color.c_rausch);
        this.mNoUnselectedDrawable = ColorizedDrawable.forIdWithColor(context, C0880R.C0881drawable.recomm_no, C0880R.color.c_rausch_special);
        this.mRecommendYes.setImageDrawable(this.mYesUnselectedDrawable);
        this.mRecommendNo.setImageDrawable(this.mNoUnselectedDrawable);
    }
}
