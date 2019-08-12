package com.airbnb.android.insights;

import android.view.View.OnClickListener;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ListingInsightsEpoxyModel_ extends ListingInsightsEpoxyModel implements GeneratedModel<ListingInsightsView> {
    private OnModelBoundListener<ListingInsightsEpoxyModel_, ListingInsightsView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ListingInsightsEpoxyModel_, ListingInsightsView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ListingInsightsView object, int position) {
        if (this.goToCardsClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.goToCardsClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(ListingInsightsView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ListingInsightsEpoxyModel_ onBind(OnModelBoundListener<ListingInsightsEpoxyModel_, ListingInsightsView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ListingInsightsView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ListingInsightsEpoxyModel_ onUnbind(OnModelUnboundListener<ListingInsightsEpoxyModel_, ListingInsightsView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ListingInsightsEpoxyModel_ title(String title) {
        onMutation();
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public ListingInsightsEpoxyModel_ description(String description) {
        onMutation();
        this.description = description;
        return this;
    }

    public String description() {
        return this.description;
    }

    public ListingInsightsEpoxyModel_ dismissText(String dismissText) {
        onMutation();
        this.dismissText = dismissText;
        return this;
    }

    public String dismissText() {
        return this.dismissText;
    }

    public ListingInsightsEpoxyModel_ listingImageUrl(String listingImageUrl) {
        onMutation();
        this.listingImageUrl = listingImageUrl;
        return this;
    }

    public String listingImageUrl() {
        return this.listingImageUrl;
    }

    public ListingInsightsEpoxyModel_ goToCardsClickListener(OnModelClickListener<ListingInsightsEpoxyModel_, ListingInsightsView> goToCardsClickListener) {
        onMutation();
        if (goToCardsClickListener == null) {
            this.goToCardsClickListener = null;
        } else {
            this.goToCardsClickListener = new WrappedEpoxyModelClickListener(this, goToCardsClickListener);
        }
        return this;
    }

    public ListingInsightsEpoxyModel_ goToCardsClickListener(OnClickListener goToCardsClickListener) {
        onMutation();
        this.goToCardsClickListener = goToCardsClickListener;
        return this;
    }

    public OnClickListener goToCardsClickListener() {
        return this.goToCardsClickListener;
    }

    public ListingInsightsEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ListingInsightsEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ListingInsightsEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ListingInsightsEpoxyModel_ m6023id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ListingInsightsEpoxyModel_ m6028id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ListingInsightsEpoxyModel_ m6024id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ListingInsightsEpoxyModel_ m6025id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ListingInsightsEpoxyModel_ m6027id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ListingInsightsEpoxyModel_ m6026id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ListingInsightsEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ListingInsightsEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ListingInsightsEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ListingInsightsEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ListingInsightsEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C6552R.layout.listing_insights_view_holder;
    }

    public ListingInsightsEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.description = null;
        this.dismissText = null;
        this.listingImageUrl = null;
        this.goToCardsClickListener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        if (o == this) {
            return true;
        }
        if (!(o instanceof ListingInsightsEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ListingInsightsEpoxyModel_ that = (ListingInsightsEpoxyModel_) o;
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
        if (this.description != null) {
            if (!this.description.equals(that.description)) {
                return false;
            }
        } else if (that.description != null) {
            return false;
        }
        if (this.dismissText != null) {
            if (!this.dismissText.equals(that.dismissText)) {
                return false;
            }
        } else if (that.dismissText != null) {
            return false;
        }
        if (this.listingImageUrl != null) {
            if (!this.listingImageUrl.equals(that.listingImageUrl)) {
                return false;
            }
        } else if (that.listingImageUrl != null) {
            return false;
        }
        if (this.goToCardsClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.goToCardsClickListener == null)) {
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
        int i7 = 1;
        int i8 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i9 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.description != null) {
            i3 = this.description.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.dismissText != null) {
            i4 = this.dismissText.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.listingImageUrl != null) {
            i5 = this.listingImageUrl.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.goToCardsClickListener == null) {
            i7 = 0;
        }
        int i14 = (i13 + i7) * 31;
        if (this.showDivider != null) {
            i6 = this.showDivider.hashCode();
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.numCarouselItemsShown != null) {
            i8 = this.numCarouselItemsShown.hashCode();
        }
        return i15 + i8;
    }

    public String toString() {
        return "ListingInsightsEpoxyModel_{title=" + this.title + ", description=" + this.description + ", dismissText=" + this.dismissText + ", listingImageUrl=" + this.listingImageUrl + ", goToCardsClickListener=" + this.goToCardsClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
