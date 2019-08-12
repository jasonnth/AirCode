package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.WishListIconView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.HomeCard_ViewBinding */
public class HomeCard_ViewBinding implements Unbinder {
    private HomeCard target;

    public HomeCard_ViewBinding(HomeCard target2, View source) {
        this.target = target2;
        target2.detailsView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.listing_details, "field 'detailsView'", AirTextView.class);
        target2.subtitleView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.listing_subtitle, "field 'subtitleView'", AirTextView.class);
        target2.detailsContainer = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.details_box, "field 'detailsContainer'", LinearLayout.class);
        target2.secondaryDetailsContainer = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.secondary_details_container, "field 'secondaryDetailsContainer'", LinearLayout.class);
        target2.numReviewsView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.num_reviews, "field 'numReviewsView'", AirTextView.class);
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", AirImageView.class);
        target2.iconVisibilityGradient = Utils.findRequiredView(source, R.id.icon_visibility_gradient, "field 'iconVisibilityGradient'");
        target2.wishListHeart = (WishListIconView) Utils.findRequiredViewAsType(source, R.id.wish_list_heart, "field 'wishListHeart'", WishListIconView.class);
        target2.ratingBar = (RatingBar) Utils.findRequiredViewAsType(source, R.id.rating_bar, "field 'ratingBar'", RatingBar.class);
        target2.bottomSpace = Utils.findRequiredView(source, R.id.bottom_space, "field 'bottomSpace'");
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
        target2.clickOverlay = Utils.findRequiredView(source, R.id.click_overlay, "field 'clickOverlay'");
        target2.selectionHighlight = Utils.findRequiredView(source, R.id.selection_highlight, "field 'selectionHighlight'");
    }

    public void unbind() {
        HomeCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.detailsView = null;
        target2.subtitleView = null;
        target2.detailsContainer = null;
        target2.secondaryDetailsContainer = null;
        target2.numReviewsView = null;
        target2.imageView = null;
        target2.iconVisibilityGradient = null;
        target2.wishListHeart = null;
        target2.ratingBar = null;
        target2.bottomSpace = null;
        target2.divider = null;
        target2.clickOverlay = null;
        target2.selectionHighlight = null;
    }
}
