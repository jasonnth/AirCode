package com.airbnb.android.core.fragments;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.ColorizedIconView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class LocalAttractionDetailFragment_ViewBinding implements Unbinder {
    private LocalAttractionDetailFragment target;

    public LocalAttractionDetailFragment_ViewBinding(LocalAttractionDetailFragment target2, View source) {
        this.target = target2;
        target2.mHostImageView = (HaloImageView) Utils.findRequiredViewAsType(source, C0716R.C0718id.host_halo_image, "field 'mHostImageView'", HaloImageView.class);
        target2.mHostName = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.host_name, "field 'mHostName'", AirTextView.class);
        target2.mAttractionDescription = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.host_comment, "field 'mAttractionDescription'", AirTextView.class);
        target2.mAttractionName = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.attraction_name, "field 'mAttractionName'", AirTextView.class);
        target2.mAttractionCategoryPrice = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.attraction_category_price, "field 'mAttractionCategoryPrice'", AirTextView.class);
        target2.mAttractionHeroImage = (AirImageView) Utils.findRequiredViewAsType(source, C0716R.C0718id.attraction_hero_image, "field 'mAttractionHeroImage'", AirImageView.class);
        target2.mAttractionDaysOpenContainer = Utils.findRequiredView(source, C0716R.C0718id.attraction_days_open_container, "field 'mAttractionDaysOpenContainer'");
        target2.mAttractionDaysOpen = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.attraction_days_open, "field 'mAttractionDaysOpen'", AirTextView.class);
        target2.mAttractionDaysOpenCaret = (ColorizedIconView) Utils.findRequiredViewAsType(source, C0716R.C0718id.attraction_days_open_caret, "field 'mAttractionDaysOpenCaret'", ColorizedIconView.class);
        target2.mAttractionHoursContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C0716R.C0718id.attraction_hours_container, "field 'mAttractionHoursContainer'", LinearLayout.class);
        target2.mAttractionAddress = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.attraction_address, "field 'mAttractionAddress'", AirTextView.class);
        target2.mAttractionAddressSection = Utils.findRequiredView(source, C0716R.C0718id.attraction_address_section, "field 'mAttractionAddressSection'");
        target2.mAttractionPhone = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.attraction_phone, "field 'mAttractionPhone'", AirTextView.class);
        target2.mAttractionPhoneSection = Utils.findRequiredView(source, C0716R.C0718id.attraction_phone_section, "field 'mAttractionPhoneSection'");
        target2.mAttractionUrl = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.attraction_url, "field 'mAttractionUrl'", AirTextView.class);
        target2.mAttractionUrlSection = Utils.findRequiredView(source, C0716R.C0718id.attraction_url_section, "field 'mAttractionUrlSection'");
        target2.mRatingContainer = Utils.findRequiredView(source, C0716R.C0718id.rating_container, "field 'mRatingContainer'");
        target2.mStarRating = (RatingBar) Utils.findRequiredViewAsType(source, C0716R.C0718id.attraction_star_rating, "field 'mStarRating'", RatingBar.class);
        target2.mNumReview = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.attraction_num_reviews, "field 'mNumReview'", AirTextView.class);
    }

    public void unbind() {
        LocalAttractionDetailFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mHostImageView = null;
        target2.mHostName = null;
        target2.mAttractionDescription = null;
        target2.mAttractionName = null;
        target2.mAttractionCategoryPrice = null;
        target2.mAttractionHeroImage = null;
        target2.mAttractionDaysOpenContainer = null;
        target2.mAttractionDaysOpen = null;
        target2.mAttractionDaysOpenCaret = null;
        target2.mAttractionHoursContainer = null;
        target2.mAttractionAddress = null;
        target2.mAttractionAddressSection = null;
        target2.mAttractionPhone = null;
        target2.mAttractionPhoneSection = null;
        target2.mAttractionUrl = null;
        target2.mAttractionUrlSection = null;
        target2.mRatingContainer = null;
        target2.mStarRating = null;
        target2.mNumReview = null;
    }
}
