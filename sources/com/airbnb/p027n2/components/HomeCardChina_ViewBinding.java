package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.RatingBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.WishListIconView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

/* renamed from: com.airbnb.n2.components.HomeCardChina_ViewBinding */
public class HomeCardChina_ViewBinding implements Unbinder {
    private HomeCardChina target;

    public HomeCardChina_ViewBinding(HomeCardChina target2, View source) {
        this.target = target2;
        target2.bookingDetailsView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.booking_details, "field 'bookingDetailsView'", AirTextView.class);
        target2.titleView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleView'", AirTextView.class);
        target2.listingDetailsView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.listing_details, "field 'listingDetailsView'", AirTextView.class);
        target2.ratingBar = (RatingBar) Utils.findRequiredViewAsType(source, R.id.rating_bar, "field 'ratingBar'", RatingBar.class);
        target2.numReviewsView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.num_reviews, "field 'numReviewsView'", AirTextView.class);
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", AirImageView.class);
        target2.wishListHeart = (WishListIconView) Utils.findRequiredViewAsType(source, R.id.wish_list_heart, "field 'wishListHeart'", WishListIconView.class);
        target2.iconVisibilityGradient = Utils.findRequiredView(source, R.id.icon_visibility_gradient, "field 'iconVisibilityGradient'");
        target2.clickOverlay = Utils.findRequiredView(source, R.id.click_overlay, "field 'clickOverlay'");
        target2.firstTag = (AirTextView) Utils.findRequiredViewAsType(source, R.id.first_tag, "field 'firstTag'", AirTextView.class);
        target2.secondTag = (AirTextView) Utils.findRequiredViewAsType(source, R.id.second_tag, "field 'secondTag'", AirTextView.class);
        target2.thirdTag = (AirTextView) Utils.findRequiredViewAsType(source, R.id.third_tag, "field 'thirdTag'", AirTextView.class);
        target2.hostBadge = (AirImageView) Utils.findRequiredViewAsType(source, R.id.host_badge, "field 'hostBadge'", AirImageView.class);
        target2.hostAvatar = (HaloImageView) Utils.findRequiredViewAsType(source, R.id.host_avatar, "field 'hostAvatar'", HaloImageView.class);
    }

    public void unbind() {
        HomeCardChina target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.bookingDetailsView = null;
        target2.titleView = null;
        target2.listingDetailsView = null;
        target2.ratingBar = null;
        target2.numReviewsView = null;
        target2.imageView = null;
        target2.wishListHeart = null;
        target2.iconVisibilityGradient = null;
        target2.clickOverlay = null;
        target2.firstTag = null;
        target2.secondTag = null;
        target2.thirdTag = null;
        target2.hostBadge = null;
        target2.hostAvatar = null;
    }
}
