package com.airbnb.p027n2.components;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.ExpandableTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.HomeReviewRow_ViewBinding */
public class HomeReviewRow_ViewBinding implements Unbinder {
    private HomeReviewRow target;

    public HomeReviewRow_ViewBinding(HomeReviewRow target2, View source) {
        this.target = target2;
        target2.reviewerName = (TextView) Utils.findRequiredViewAsType(source, R.id.text_reviewer_name, "field 'reviewerName'", TextView.class);
        target2.reviewDate = (TextView) Utils.findRequiredViewAsType(source, R.id.text_review_stay_date, "field 'reviewDate'", TextView.class);
        target2.publicComment = (ExpandableTextView) Utils.findRequiredViewAsType(source, R.id.text_review_public_comment, "field 'publicComment'", ExpandableTextView.class);
        target2.translationDetails = (TextView) Utils.findRequiredViewAsType(source, R.id.translation_details, "field 'translationDetails'", TextView.class);
        target2.privateComment = (TextView) Utils.findRequiredViewAsType(source, R.id.text_review_private_comments, "field 'privateComment'", TextView.class);
        target2.privateCommentTitle = (TextView) Utils.findRequiredViewAsType(source, R.id.text_review_private_comments_title, "field 'privateCommentTitle'", TextView.class);
        target2.privateCommentsLayout = (ViewGroup) Utils.findRequiredViewAsType(source, R.id.private_comments_layout, "field 'privateCommentsLayout'", ViewGroup.class);
        target2.publicResponseComments = (TextView) Utils.findRequiredViewAsType(source, R.id.text_review_public_response, "field 'publicResponseComments'", TextView.class);
        target2.publicResponseTitle = (TextView) Utils.findRequiredViewAsType(source, R.id.text_review_public_response_title, "field 'publicResponseTitle'", TextView.class);
        target2.publicResponseLayout = (ViewGroup) Utils.findRequiredViewAsType(source, R.id.public_response_layout, "field 'publicResponseLayout'", ViewGroup.class);
        target2.listingName = (TextView) Utils.findRequiredViewAsType(source, R.id.listing_name, "field 'listingName'", TextView.class);
        target2.thumbnail = (AirImageView) Utils.findRequiredViewAsType(source, R.id.reviewer_photo, "field 'thumbnail'", AirImageView.class);
        target2.ratingBar = (RatingBar) Utils.findRequiredViewAsType(source, R.id.rating_bar, "field 'ratingBar'", RatingBar.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
        target2.reportLink = (TextView) Utils.findRequiredViewAsType(source, R.id.text_review_report_link, "field 'reportLink'", TextView.class);
    }

    public void unbind() {
        HomeReviewRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.reviewerName = null;
        target2.reviewDate = null;
        target2.publicComment = null;
        target2.translationDetails = null;
        target2.privateComment = null;
        target2.privateCommentTitle = null;
        target2.privateCommentsLayout = null;
        target2.publicResponseComments = null;
        target2.publicResponseTitle = null;
        target2.publicResponseLayout = null;
        target2.listingName = null;
        target2.thumbnail = null;
        target2.ratingBar = null;
        target2.divider = null;
        target2.reportLink = null;
    }
}
