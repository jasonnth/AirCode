package com.airbnb.android.lib.reviews.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.android.core.activities.SheetFlowActivity.SheetTheme;
import com.airbnb.android.core.models.ReviewRole;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.KonaReviewAnalytics;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.DocumentMarquee;

public class WriteFeedbackIntroFragment extends BaseWriteReviewFragment {
    public static final String ARG_FEEDBACK_FIELD = "feedback_field";
    @BindView
    DocumentMarquee documentMarquee;
    private FeedbackField feedbackField;
    @BindView
    TextView reviewText;
    @BindView
    TextView writeButton;

    public enum FeedbackField {
        PUBLIC,
        PRIVATE;

        /* access modifiers changed from: 0000 */
        public boolean isSkippable() {
            return this == PRIVATE;
        }

        /* access modifiers changed from: 0000 */
        public int getTitleString(ReviewRole role) {
            switch (role) {
                case Guest:
                    return this == PUBLIC ? C0880R.string.review_public_feedback_title_reviewing_host : C0880R.string.review_private_feedback_title_reviewing_host;
                case Host:
                    return this == PUBLIC ? C0880R.string.review_public_feedback_title_reviewing_guest : C0880R.string.review_private_feedback_title_reviewing_guest;
                default:
                    throw new IllegalArgumentException("Cannot handle role type: " + role);
            }
        }

        /* access modifiers changed from: 0000 */
        public int getSubtitleString(ReviewRole role) {
            switch (role) {
                case Guest:
                    return this == PUBLIC ? C0880R.string.review_public_feedback_description_reviewing_host : C0880R.string.review_private_feedback_for_reviewing_host;
                case Host:
                    return this == PUBLIC ? C0880R.string.review_public_feedback_description_reviewing_guest : C0880R.string.review_private_feedback_for_reviewing_guest;
                default:
                    throw new IllegalArgumentException("Cannot handle role type: " + role);
            }
        }
    }

    public static WriteFeedbackIntroFragment newInstance(FeedbackField field) {
        return (WriteFeedbackIntroFragment) ((FragmentBundleBuilder) FragmentBundler.make(new WriteFeedbackIntroFragment()).putSerializable(ARG_FEEDBACK_FIELD, field)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.feedbackField = (FeedbackField) getArguments().getSerializable(ARG_FEEDBACK_FIELD);
        if (this.feedbackField == null) {
            throw new IllegalStateException("FeedbackField is null. init fragment using newInstance()");
        }
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0880R.C0883menu.next_and_skip, menu);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(C0880R.C0882id.next).setVisible(hasFeedback());
        menu.findItem(C0880R.C0882id.skip).setVisible(!hasFeedback() && this.feedbackField.isSkippable());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0880R.C0882id.skip) {
            this.wrInterface.markSkipPrivateFeedback();
            this.wrInterface.showFragment(ReviewStarFragment.newInstanceFirstPage(getReview()));
            KonaReviewAnalytics.trackPrivateClickSkip(getReview());
            return true;
        } else if (item.getItemId() != C0880R.C0882id.next) {
            return super.onOptionsItemSelected(item);
        } else {
            if (this.feedbackField == FeedbackField.PUBLIC) {
                this.wrInterface.showFragment(newInstance(FeedbackField.PRIVATE));
                KonaReviewAnalytics.trackFeedbackSubmit(getReview());
                return true;
            }
            this.wrInterface.showFragment(ReviewStarFragment.newInstanceFirstPage(getReview()));
            KonaReviewAnalytics.trackPrivateSubmit(getReview());
            return true;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_write_review_intro, null);
        bindViews(view);
        this.documentMarquee.setTitle(this.feedbackField.getTitleString(getReview().getReviewRole()));
        this.documentMarquee.setCaption(this.feedbackField.getSubtitleString(getReview().getReviewRole()));
        String existingComments = this.feedbackField == FeedbackField.PUBLIC ? getReview().getPublicFeedback() : getReview().getPrivateFeedback();
        this.reviewText.setText(existingComments);
        ViewUtils.setGoneIf(this.reviewText, TextUtils.isEmpty(existingComments));
        boolean isEdit = hasFeedback();
        if (isEdit) {
            this.writeButton.setText(this.feedbackField == FeedbackField.PUBLIC ? C0880R.string.edit_feedback_public : C0880R.string.edit_feedback_private);
        } else {
            this.writeButton.setText(this.feedbackField == FeedbackField.PUBLIC ? C0880R.string.leave_feedback_public : C0880R.string.leave_feedback_private);
        }
        this.writeButton.setOnClickListener(WriteFeedbackIntroFragment$$Lambda$1.lambdaFactory$(this, isEdit));
        if (this.feedbackField == FeedbackField.PUBLIC) {
            KonaReviewAnalytics.trackFeedbackImpression(getReview());
        } else {
            KonaReviewAnalytics.trackPrivateImpression(getReview());
        }
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(WriteFeedbackIntroFragment writeFeedbackIntroFragment, boolean isEdit, View v) {
        writeFeedbackIntroFragment.wrInterface.showFragment(WriteFeedbackFragment.newInstance(writeFeedbackIntroFragment.feedbackField));
        if (isEdit) {
            if (writeFeedbackIntroFragment.feedbackField == FeedbackField.PUBLIC) {
                KonaReviewAnalytics.trackFeedbackClickEdit(writeFeedbackIntroFragment.getReview());
            } else {
                KonaReviewAnalytics.trackPrivateClickEdit(writeFeedbackIntroFragment.getReview());
            }
        } else if (writeFeedbackIntroFragment.feedbackField == FeedbackField.PUBLIC) {
            KonaReviewAnalytics.trackFeedbackClickLeave(writeFeedbackIntroFragment.getReview());
        } else {
            KonaReviewAnalytics.trackPrivateClickLeave(writeFeedbackIntroFragment.getReview());
        }
    }

    private boolean hasFeedback() {
        return !TextUtils.isEmpty(this.feedbackField == FeedbackField.PUBLIC ? getReview().getPublicFeedback() : getReview().getPrivateFeedback());
    }

    /* access modifiers changed from: 0000 */
    public SheetTheme getTheme() {
        return SheetTheme.WHITE;
    }
}
