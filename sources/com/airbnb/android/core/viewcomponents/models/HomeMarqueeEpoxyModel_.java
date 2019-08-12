package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Photo;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.collections.Carousel.OnSnapToPositionListener;
import com.airbnb.p027n2.components.HomeMarquee;
import com.airbnb.p027n2.components.HomeMarquee.ImageCarouselItemClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class HomeMarqueeEpoxyModel_ extends HomeMarqueeEpoxyModel implements GeneratedModel<HomeMarquee> {
    private OnModelBoundListener<HomeMarqueeEpoxyModel_, HomeMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<HomeMarqueeEpoxyModel_, HomeMarquee> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, HomeMarquee object, int position) {
        if (this.reviewsClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.reviewsClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(HomeMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public HomeMarqueeEpoxyModel_ onBind(OnModelBoundListener<HomeMarqueeEpoxyModel_, HomeMarquee> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(HomeMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public HomeMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<HomeMarqueeEpoxyModel_, HomeMarquee> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public HomeMarqueeEpoxyModel_ title(String title) {
        onMutation();
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public HomeMarqueeEpoxyModel_ listing(Listing listing) {
        onMutation();
        this.listing = listing;
        super.listing(listing);
        return this;
    }

    public Listing listing() {
        return this.listing;
    }

    public HomeMarqueeEpoxyModel_ reviewsCount(int reviewsCount) {
        onMutation();
        this.reviewsCount = reviewsCount;
        return this;
    }

    public int reviewsCount() {
        return this.reviewsCount;
    }

    public HomeMarqueeEpoxyModel_ showStarRating(boolean showStarRating) {
        onMutation();
        this.showStarRating = showStarRating;
        return this;
    }

    public boolean showStarRating() {
        return this.showStarRating;
    }

    public HomeMarqueeEpoxyModel_ starRating(float starRating) {
        onMutation();
        this.starRating = starRating;
        return this;
    }

    public float starRating() {
        return this.starRating;
    }

    public HomeMarqueeEpoxyModel_ photos(List<Photo> photos) {
        onMutation();
        this.photos = photos;
        return this;
    }

    public List<Photo> photos() {
        return this.photos;
    }

    public HomeMarqueeEpoxyModel_ imageCarouselClickListener(ImageCarouselItemClickListener imageCarouselClickListener) {
        onMutation();
        this.imageCarouselClickListener = imageCarouselClickListener;
        return this;
    }

    public ImageCarouselItemClickListener imageCarouselClickListener() {
        return this.imageCarouselClickListener;
    }

    public HomeMarqueeEpoxyModel_ snapToPositionListener(OnSnapToPositionListener snapToPositionListener) {
        onMutation();
        this.snapToPositionListener = snapToPositionListener;
        return this;
    }

    public OnSnapToPositionListener snapToPositionListener() {
        return this.snapToPositionListener;
    }

    public HomeMarqueeEpoxyModel_ reviewsClickListener(OnModelClickListener<HomeMarqueeEpoxyModel_, HomeMarquee> reviewsClickListener) {
        onMutation();
        if (reviewsClickListener == null) {
            this.reviewsClickListener = null;
        } else {
            this.reviewsClickListener = new WrappedEpoxyModelClickListener(this, reviewsClickListener);
        }
        return this;
    }

    public HomeMarqueeEpoxyModel_ reviewsClickListener(OnClickListener reviewsClickListener) {
        onMutation();
        this.reviewsClickListener = reviewsClickListener;
        return this;
    }

    public OnClickListener reviewsClickListener() {
        return this.reviewsClickListener;
    }

    public HomeMarqueeEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public HomeMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public HomeMarqueeEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public HomeMarqueeEpoxyModel_ m4750id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public HomeMarqueeEpoxyModel_ m4755id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public HomeMarqueeEpoxyModel_ m4751id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public HomeMarqueeEpoxyModel_ m4752id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public HomeMarqueeEpoxyModel_ m4754id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public HomeMarqueeEpoxyModel_ m4753id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public HomeMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public HomeMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public HomeMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public HomeMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public HomeMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_home_marquee;
    }

    public HomeMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.listing = null;
        this.reviewsCount = 0;
        this.showStarRating = false;
        this.starRating = 0.0f;
        this.photos = null;
        this.imageCarouselClickListener = null;
        this.snapToPositionListener = null;
        this.reviewsClickListener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomeMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        HomeMarqueeEpoxyModel_ that = (HomeMarqueeEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (this.listing != null) {
            if (!this.listing.equals(that.listing)) {
                return false;
            }
        } else if (that.listing != null) {
            return false;
        }
        if (this.reviewsCount != that.reviewsCount || this.showStarRating != that.showStarRating || Float.compare(that.starRating, this.starRating) != 0) {
            return false;
        }
        if (this.photos != null) {
            if (!this.photos.equals(that.photos)) {
                return false;
            }
        } else if (that.photos != null) {
            return false;
        }
        if (this.imageCarouselClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.imageCarouselClickListener == null)) {
            return false;
        }
        if (this.snapToPositionListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.snapToPositionListener == null)) {
            return false;
        }
        if (this.reviewsClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.reviewsClickListener == null)) {
            return false;
        }
        if (this.showDivider != null) {
            if (!this.showDivider.equals(that.showDivider)) {
                return false;
            }
        } else if (that.showDivider != null) {
            return false;
        }
        if (this.numCarouselItemsShown != null) {
            if (!this.numCarouselItemsShown.equals(that.numCarouselItemsShown)) {
                return false;
            }
        } else if (that.numCarouselItemsShown != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10 = 1;
        int i11 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i12 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i13 = (i12 + i2) * 31;
        if (this.listing != null) {
            i3 = this.listing.hashCode();
        } else {
            i3 = 0;
        }
        int i14 = (((i13 + i3) * 31) + this.reviewsCount) * 31;
        if (this.showStarRating) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i15 = (i14 + i4) * 31;
        if (this.starRating != 0.0f) {
            i5 = Float.floatToIntBits(this.starRating);
        } else {
            i5 = 0;
        }
        int i16 = (i15 + i5) * 31;
        if (this.photos != null) {
            i6 = this.photos.hashCode();
        } else {
            i6 = 0;
        }
        int i17 = (i16 + i6) * 31;
        if (this.imageCarouselClickListener != null) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i18 = (i17 + i7) * 31;
        if (this.snapToPositionListener != null) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i19 = (i18 + i8) * 31;
        if (this.reviewsClickListener == null) {
            i10 = 0;
        }
        int i20 = (i19 + i10) * 31;
        if (this.showDivider != null) {
            i9 = this.showDivider.hashCode();
        } else {
            i9 = 0;
        }
        int i21 = (i20 + i9) * 31;
        if (this.numCarouselItemsShown != null) {
            i11 = this.numCarouselItemsShown.hashCode();
        }
        return i21 + i11;
    }

    public String toString() {
        return "HomeMarqueeEpoxyModel_{title=" + this.title + ", listing=" + this.listing + ", reviewsCount=" + this.reviewsCount + ", showStarRating=" + this.showStarRating + ", starRating=" + this.starRating + ", photos=" + this.photos + ", imageCarouselClickListener=" + this.imageCarouselClickListener + ", snapToPositionListener=" + this.snapToPositionListener + ", reviewsClickListener=" + this.reviewsClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
