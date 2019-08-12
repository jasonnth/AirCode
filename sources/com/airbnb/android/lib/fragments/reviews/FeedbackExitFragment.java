package com.airbnb.android.lib.fragments.reviews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.enums.ReviewsMode;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.ReferralsIntents;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.ReviewRole;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.ReviewsActivity;
import com.airbnb.p027n2.components.HeroMarquee;

public class FeedbackExitFragment extends AirFragment {
    private static final String EXTRA_REVIEW = "review";
    @BindView
    HeroMarquee heroMarquee;

    public static Intent defaultIntent(Context context, Review review) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("review", review);
        return AutoAirActivity.intentForFragment(context, FeedbackExitFragment.class, bundle);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_feedback_exit, null);
        bindViews(view);
        if (getActionBar() != null) {
            getActionBar().hide();
        }
        Review review = (Review) Check.notNull(getArguments().getParcelable("review"));
        boolean twinCompleted = review.isTwinCompleted();
        HeroMarquee heroMarquee2 = this.heroMarquee;
        int i = review.getReviewRole() == ReviewRole.Guest ? twinCompleted ? C0880R.string.review_post_submit_body_finished_guest : C0880R.string.review_post_submit_body_waiting_guest : twinCompleted ? C0880R.string.review_post_submit_body_finished_host : C0880R.string.review_post_submit_body_waiting_host;
        heroMarquee2.setCaption(i);
        this.heroMarquee.setFirstButtonText(twinCompleted ? C0880R.string.review_see_their_review : C0880R.string.post_review_got_it);
        this.heroMarquee.setFirstButtonClickListener(FeedbackExitFragment$$Lambda$1.lambdaFactory$(this, twinCompleted, review));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(FeedbackExitFragment feedbackExitFragment, boolean twinCompleted, Review review, View v) {
        feedbackExitFragment.getActivity().finish();
        if (twinCompleted) {
            feedbackExitFragment.startActivity(ReviewsActivity.intentForUser(feedbackExitFragment.getActivity(), review.getAuthor(), ReviewsMode.MODE_ALL));
        } else {
            ReferralsIntents.startIfNeededFromPostReview(feedbackExitFragment.getContext(), review);
        }
    }
}
