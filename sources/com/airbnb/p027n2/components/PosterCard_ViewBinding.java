package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.RatingBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.WishListIconView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.PosterCard_ViewBinding */
public class PosterCard_ViewBinding implements Unbinder {
    private PosterCard target;

    public PosterCard_ViewBinding(PosterCard target2, View source) {
        this.target = target2;
        target2.posterImage = (AirImageView) Utils.findRequiredViewAsType(source, R.id.poster_image, "field 'posterImage'", AirImageView.class);
        target2.iconVisibilityGradient = Utils.findRequiredView(source, R.id.icon_visibility_gradient, "field 'iconVisibilityGradient'");
        target2.bottomSpace = Utils.findRequiredView(source, R.id.bottom_space, "field 'bottomSpace'");
        target2.clickOverlay = Utils.findRequiredView(source, R.id.click_overlay, "field 'clickOverlay'");
        target2.priceAndDescriptionText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.price_and_description_text, "field 'priceAndDescriptionText'", AirTextView.class);
        target2.ratingBar = (RatingBar) Utils.findRequiredViewAsType(source, R.id.rating_bar, "field 'ratingBar'", RatingBar.class);
        target2.numReviewsView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.num_reviews, "field 'numReviewsView'", AirTextView.class);
        target2.wishListHeart = (WishListIconView) Utils.findRequiredViewAsType(source, R.id.wish_list_heart, "field 'wishListHeart'", WishListIconView.class);
        target2.posterTag = (AirTextView) Utils.findRequiredViewAsType(source, R.id.poster_tag, "field 'posterTag'", AirTextView.class);
    }

    public void unbind() {
        PosterCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.posterImage = null;
        target2.iconVisibilityGradient = null;
        target2.bottomSpace = null;
        target2.clickOverlay = null;
        target2.priceAndDescriptionText = null;
        target2.ratingBar = null;
        target2.numReviewsView = null;
        target2.wishListHeart = null;
        target2.posterTag = null;
    }
}
