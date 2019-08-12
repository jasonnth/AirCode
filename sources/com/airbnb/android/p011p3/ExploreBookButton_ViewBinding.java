package com.airbnb.android.p011p3;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.android.p3.ExploreBookButton_ViewBinding */
public class ExploreBookButton_ViewBinding implements Unbinder {
    private ExploreBookButton target;

    public ExploreBookButton_ViewBinding(ExploreBookButton target2) {
        this(target2, target2);
    }

    public ExploreBookButton_ViewBinding(ExploreBookButton target2, View source) {
        this.target = target2;
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, C7532R.C7534id.loading_view, "field 'loadingView'", LoadingView.class);
        target2.buttonText = (AirTextView) Utils.findRequiredViewAsType(source, C7532R.C7534id.button_text, "field 'buttonText'", AirTextView.class);
        target2.ratingBar = (RatingBar) Utils.findRequiredViewAsType(source, C7532R.C7534id.rating_bar, "field 'ratingBar'", RatingBar.class);
        target2.ratingContainer = (ViewGroup) Utils.findRequiredViewAsType(source, C7532R.C7534id.book_button_reviews_container, "field 'ratingContainer'", ViewGroup.class);
        target2.numReviewsView = (AirTextView) Utils.findRequiredViewAsType(source, C7532R.C7534id.num_reviews, "field 'numReviewsView'", AirTextView.class);
        target2.priceDetails = (AirTextView) Utils.findRequiredViewAsType(source, C7532R.C7534id.price_details, "field 'priceDetails'", AirTextView.class);
        target2.percentageRecommend = (AirTextView) Utils.findRequiredViewAsType(source, C7532R.C7534id.percentage_recommend, "field 'percentageRecommend'", AirTextView.class);
        target2.referralCreditLayout = Utils.findRequiredView(source, C7532R.C7534id.referral_credit_layout, "field 'referralCreditLayout'");
        target2.referralCreditView = (AirTextView) Utils.findRequiredViewAsType(source, C7532R.C7534id.referral_credit_text, "field 'referralCreditView'", AirTextView.class);
        target2.contactHostButton = source.findViewById(C7532R.C7534id.contact_host_button);
    }

    public void unbind() {
        ExploreBookButton target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.loadingView = null;
        target2.buttonText = null;
        target2.ratingBar = null;
        target2.ratingContainer = null;
        target2.numReviewsView = null;
        target2.priceDetails = null;
        target2.percentageRecommend = null;
        target2.referralCreditLayout = null;
        target2.referralCreditView = null;
        target2.contactHostButton = null;
    }
}
