package com.airbnb.android.lib.fragments.reviews;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class ReviewFeedbackFragment_ViewBinding implements Unbinder {
    private ReviewFeedbackFragment target;

    public ReviewFeedbackFragment_ViewBinding(ReviewFeedbackFragment target2, View source) {
        this.target = target2;
        target2.mHeader = (FrameLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.review_feedback_header, "field 'mHeader'", FrameLayout.class);
        target2.mHeaderBackground = (AirImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.review_header_background_image, "field 'mHeaderBackground'", AirImageView.class);
        target2.mHeaderImage = (HaloImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.guest_image_view, "field 'mHeaderImage'", HaloImageView.class);
        target2.mReservationDates = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.reservation_dates, "field 'mReservationDates'", TextView.class);
        target2.mGuestName = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.guest_name, "field 'mGuestName'", TextView.class);
        target2.mListingName = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.listing_name, "field 'mListingName'", TextView.class);
        target2.mNextButton = (Button) Utils.findRequiredViewAsType(source, C0880R.C0882id.review_next_button, "field 'mNextButton'", Button.class);
        target2.mTooltip = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.tooltip_image, "field 'mTooltip'", ImageView.class);
        target2.mPublicFeedbackTitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.public_feedback_title, "field 'mPublicFeedbackTitle'", TextView.class);
        target2.mPublicFeedbackDescription = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.public_feedback_description, "field 'mPublicFeedbackDescription'", TextView.class);
        target2.mPublicFeedbackEditText = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.public_feedback_field, "field 'mPublicFeedbackEditText'", EditText.class);
        target2.mReviewerImage = (HaloImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.private_feedback_image_reviewer, "field 'mReviewerImage'", HaloImageView.class);
        target2.mRevieweeImage = (HaloImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.private_feedback_image_reviewee, "field 'mRevieweeImage'", HaloImageView.class);
        target2.mPrivateFeedbackTitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.private_feedback_title, "field 'mPrivateFeedbackTitle'", TextView.class);
        target2.mPrivateFeedbackDescription = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.private_feedback_description, "field 'mPrivateFeedbackDescription'", TextView.class);
        target2.mPrivateFeedbackEditText = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.private_feedback_field, "field 'mPrivateFeedbackEditText'", EditText.class);
        target2.mPrivateFeedbackEditTextTwo = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.private_feedback_field_two, "field 'mPrivateFeedbackEditTextTwo'", EditText.class);
    }

    public void unbind() {
        ReviewFeedbackFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mHeader = null;
        target2.mHeaderBackground = null;
        target2.mHeaderImage = null;
        target2.mReservationDates = null;
        target2.mGuestName = null;
        target2.mListingName = null;
        target2.mNextButton = null;
        target2.mTooltip = null;
        target2.mPublicFeedbackTitle = null;
        target2.mPublicFeedbackDescription = null;
        target2.mPublicFeedbackEditText = null;
        target2.mReviewerImage = null;
        target2.mRevieweeImage = null;
        target2.mPrivateFeedbackTitle = null;
        target2.mPrivateFeedbackDescription = null;
        target2.mPrivateFeedbackEditText = null;
        target2.mPrivateFeedbackEditTextTwo = null;
    }
}
