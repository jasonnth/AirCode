package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.RatingBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.HomeMarquee_ViewBinding */
public class HomeMarquee_ViewBinding implements Unbinder {
    private HomeMarquee target;

    public HomeMarquee_ViewBinding(HomeMarquee target2, View source) {
        this.target = target2;
        target2.marqueeTitleView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.marquee_title, "field 'marqueeTitleView'", AirTextView.class);
        target2.numReviewsView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.review_total, "field 'numReviewsView'", AirTextView.class);
        target2.starRating = (RatingBar) Utils.findRequiredViewAsType(source, R.id.rating_stars, "field 'starRating'", RatingBar.class);
        target2.reviewAndStarsContainer = Utils.findRequiredView(source, R.id.reviews_and_stars, "field 'reviewAndStarsContainer'");
        target2.imageCarousel = (Carousel) Utils.findRequiredViewAsType(source, R.id.image_carousel, "field 'imageCarousel'", Carousel.class);
    }

    public void unbind() {
        HomeMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marqueeTitleView = null;
        target2.numReviewsView = null;
        target2.starRating = null;
        target2.reviewAndStarsContainer = null;
        target2.imageCarousel = null;
    }
}
