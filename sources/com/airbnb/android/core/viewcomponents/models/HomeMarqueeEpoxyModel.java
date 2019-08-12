package com.airbnb.android.core.viewcomponents.models;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.ListingReviewsUtil;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.collections.Carousel.OnSnapToPositionListener;
import com.airbnb.p027n2.components.HomeMarquee;
import com.airbnb.p027n2.components.HomeMarquee.HomeMarqueeCarouselItem;
import com.airbnb.p027n2.components.HomeMarquee.ImageCarouselItemClickListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class HomeMarqueeEpoxyModel extends AirEpoxyModel<HomeMarquee> {
    ImageCarouselItemClickListener imageCarouselClickListener;
    Listing listing;
    List<Photo> photos;
    OnClickListener reviewsClickListener;
    int reviewsCount;
    boolean showStarRating;
    OnSnapToPositionListener snapToPositionListener;
    float starRating;
    String title;

    public void bind(HomeMarquee marquee) {
        ImmutableList<HomeMarqueeCarouselItem> items;
        super.bind(marquee);
        marquee.setMarqueeTitle(this.title);
        marquee.showStarRatingAndReviews(this.showStarRating);
        if (this.showStarRating) {
            marquee.setupStarRatingAndReviews(this.starRating, this.reviewsCount, ListingReviewsUtil.getNumReviewsText(marquee.getContext(), this.reviewsCount));
        }
        if (Experiments.useDynamicImageSizeOnP3()) {
            items = FluentIterable.from((Iterable<E>) this.photos).transform(HomeMarqueeEpoxyModel$$Lambda$1.lambdaFactory$()).toList();
        } else {
            items = FluentIterable.from((Iterable<E>) this.photos).transform(HomeMarqueeEpoxyModel$$Lambda$2.lambdaFactory$()).toList();
        }
        marquee.setCarouselItems(this.listing.getId(), items);
        marquee.setImageCarouselClickListener(this.imageCarouselClickListener);
        marquee.setSnapToPositionListener(this.snapToPositionListener);
        marquee.setReviewsClickListener(this.reviewsClickListener);
        if (BuildHelper.isDebugFeaturesEnabled()) {
            marquee.setOnLongClickListener(HomeMarqueeEpoxyModel$$Lambda$3.lambdaFactory$(this, marquee));
        }
    }

    static /* synthetic */ HomeMarqueeCarouselItem lambda$bind$0(Photo photo) {
        return new HomeMarqueeCarouselItem(photo.getLargeUrl(), photo.getBase64Preview());
    }

    static /* synthetic */ boolean lambda$bind$1(HomeMarqueeEpoxyModel homeMarqueeEpoxyModel, HomeMarquee marquee, View v) {
        Context context = marquee.getContext();
        Toast.makeText(context, "Listing id: " + homeMarqueeEpoxyModel.listing.getId(), 1).show();
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Listing id", Long.toString(homeMarqueeEpoxyModel.listing.getId())));
        return false;
    }

    public void unbind(HomeMarquee marquee) {
        super.unbind(marquee);
        marquee.setImageCarouselClickListener(null);
        marquee.setSnapToPositionListener(null);
    }

    public HomeMarqueeEpoxyModel listing(Listing listing2) {
        this.reviewsCount = listing2.getReviewsCount();
        this.starRating = listing2.getStarRating();
        this.photos = ListUtils.isEmpty((Collection<?>) listing2.getPhotos()) ? Collections.singletonList(listing2.getPhoto()) : listing2.getPhotos();
        this.listing = listing2;
        this.title = listing2.getName();
        return this;
    }

    public int getDividerViewType() {
        return 2;
    }
}
