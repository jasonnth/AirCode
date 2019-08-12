package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.host.stats.HostRecentListingReviewsCarouselAdapter.CarouselItemClickListener;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;

public class ListingReviewScoreCardEpoxyModel_ extends ListingReviewScoreCardEpoxyModel implements GeneratedModel<Holder> {
    private OnModelBoundListener<ListingReviewScoreCardEpoxyModel_, Holder> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ListingReviewScoreCardEpoxyModel_, Holder> onModelUnboundListener_epoxyGeneratedModel;

    public ListingReviewScoreCardEpoxyModel_(Listing listing, int carouselIndex) {
        super(listing, carouselIndex);
    }

    public void handlePreBind(EpoxyViewHolder holder, Holder object, int position) {
    }

    public void handlePostBind(Holder object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ListingReviewScoreCardEpoxyModel_ onBind(OnModelBoundListener<ListingReviewScoreCardEpoxyModel_, Holder> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(Holder object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ListingReviewScoreCardEpoxyModel_ onUnbind(OnModelUnboundListener<ListingReviewScoreCardEpoxyModel_, Holder> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public Listing listing() {
        return this.listing;
    }

    public int carouselIndex() {
        return this.carouselIndex;
    }

    public ListingReviewScoreCardEpoxyModel_ carouselClickListener(CarouselItemClickListener carouselClickListener) {
        onMutation();
        this.carouselClickListener = carouselClickListener;
        return this;
    }

    public CarouselItemClickListener carouselClickListener() {
        return this.carouselClickListener;
    }

    public ListingReviewScoreCardEpoxyModel_ showDivider(Boolean showDivider) {
        onMutation();
        this.showDivider = showDivider;
        return this;
    }

    public Boolean showDivider() {
        return this.showDivider;
    }

    public ListingReviewScoreCardEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    /* renamed from: id */
    public ListingReviewScoreCardEpoxyModel_ m6035id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ListingReviewScoreCardEpoxyModel_ m6040id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ListingReviewScoreCardEpoxyModel_ m6036id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ListingReviewScoreCardEpoxyModel_ m6037id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ListingReviewScoreCardEpoxyModel_ m6039id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ListingReviewScoreCardEpoxyModel_ m6038id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ListingReviewScoreCardEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ListingReviewScoreCardEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ListingReviewScoreCardEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ListingReviewScoreCardEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ListingReviewScoreCardEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public Holder createNewHolder() {
        return new Holder();
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.view_holder_listing_card_review_status;
    }

    public ListingReviewScoreCardEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.carouselClickListener = null;
        this.showDivider = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        if (o == this) {
            return true;
        }
        if (!(o instanceof ListingReviewScoreCardEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ListingReviewScoreCardEpoxyModel_ that = (ListingReviewScoreCardEpoxyModel_) o;
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
        if (this.listing != null) {
            if (!this.listing.equals(that.listing)) {
                return false;
            }
        } else if (that.listing != null) {
            return false;
        }
        if (this.carouselIndex != that.carouselIndex) {
            return false;
        }
        if (this.carouselClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.carouselClickListener == null)) {
            return false;
        }
        if (this.showDivider != null) {
            if (!this.showDivider.equals(that.showDivider)) {
                return false;
            }
        } else if (that.showDivider != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3 = 1;
        int i4 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i5 = (hashCode + i) * 31;
        if (this.listing != null) {
            i2 = this.listing.hashCode();
        } else {
            i2 = 0;
        }
        int i6 = (((i5 + i2) * 31) + this.carouselIndex) * 31;
        if (this.carouselClickListener == null) {
            i3 = 0;
        }
        int i7 = (i6 + i3) * 31;
        if (this.showDivider != null) {
            i4 = this.showDivider.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "ListingReviewScoreCardEpoxyModel_{listing=" + this.listing + ", carouselIndex=" + this.carouselIndex + ", carouselClickListener=" + this.carouselClickListener + ", showDivider=" + this.showDivider + "}" + super.toString();
    }
}
