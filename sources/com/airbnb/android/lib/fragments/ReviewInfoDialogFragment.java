package com.airbnb.android.lib.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.ReviewsAnalytics;

public class ReviewInfoDialogFragment extends ZenDialog {
    private static final String BODY_ID = "body_id";
    private static final String HEADING_ID = "heading_id";
    public static final int REQUEST_CODE_REVIEW_INFO_CANCEL = 1012;
    public static final int REQUEST_CODE_REVIEW_INFO_CLOSED = 1011;
    public static final int REQUEST_CODE_SEE_REVIEW = 1010;

    public static ReviewInfoDialogFragment newInstance(int headingId, int bodyId, int buttonId, int buttonReturnCode, Fragment targetFragment) {
        ReviewInfoDialogFragment dialog = (ReviewInfoDialogFragment) new ZenBuilder(new ReviewInfoDialogFragment()).withCustomLayout().withSingleButton(buttonId, buttonReturnCode, targetFragment).create();
        dialog.getArguments().putInt(HEADING_ID, headingId);
        dialog.getArguments().putInt(BODY_ID, bodyId);
        return dialog;
    }

    public static ReviewInfoDialogFragment newInstanceForPostReview(Review review, Fragment targetFragment) {
        if (review.isTwinCompleted()) {
            ReviewsAnalytics.trackPostDoubleBlindVisibleDialog(review);
            return newInstance(C0880R.string.review_thank_you, review.isHostReviewingGuest() ? C0880R.string.review_post_submit_body_finished_host : C0880R.string.review_post_submit_body_finished_guest, C0880R.string.review_see_their_review, REQUEST_CODE_SEE_REVIEW, targetFragment);
        }
        ReviewsAnalytics.trackPostDoubleBlindHiddenDialog(review);
        return newInstance(C0880R.string.review_thank_you, review.isHostReviewingGuest() ? C0880R.string.review_post_submit_body_waiting_host : C0880R.string.review_post_submit_body_waiting_guest, C0880R.string.okay, 1011, targetFragment);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        View frame = inflater.inflate(C0880R.layout.fragment_review_info_dialog, container, false);
        ((TextView) ButterKnife.findById(frame, C0880R.C0882id.review_info_heading)).setText(getArguments().getInt(HEADING_ID));
        ((TextView) ButterKnife.findById(frame, C0880R.C0882id.review_info_body)).setText(getArguments().getInt(BODY_ID));
        setCustomView(frame);
        return view;
    }

    public void onCancel(DialogInterface dialog) {
        super.onDismiss(dialog);
        sendActivityResult(getTargetRequestCode(), -1, null);
    }
}
