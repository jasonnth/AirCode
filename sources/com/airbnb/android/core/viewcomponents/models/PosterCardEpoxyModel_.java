package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.TripTemplate;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.PosterCard;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class PosterCardEpoxyModel_ extends PosterCardEpoxyModel implements GeneratedModel<PosterCard> {
    private OnModelBoundListener<PosterCardEpoxyModel_, PosterCard> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<PosterCardEpoxyModel_, PosterCard> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, PosterCard object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(PosterCard object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public PosterCardEpoxyModel_ onBind(OnModelBoundListener<PosterCardEpoxyModel_, PosterCard> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(PosterCard object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public PosterCardEpoxyModel_ onUnbind(OnModelUnboundListener<PosterCardEpoxyModel_, PosterCard> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public PosterCardEpoxyModel_ tripTemplate(TripTemplate tripTemplate) {
        onMutation();
        this.tripTemplate = tripTemplate;
        return this;
    }

    public TripTemplate tripTemplate() {
        return this.tripTemplate;
    }

    public PosterCardEpoxyModel_ loading(boolean loading) {
        onMutation();
        this.loading = loading;
        return this;
    }

    public boolean loading() {
        return this.loading;
    }

    public PosterCardEpoxyModel_ displayOptions(DisplayOptions displayOptions) {
        onMutation();
        this.displayOptions = displayOptions;
        return this;
    }

    public DisplayOptions displayOptions() {
        return this.displayOptions;
    }

    public PosterCardEpoxyModel_ clickListener(OnModelClickListener<PosterCardEpoxyModel_, PosterCard> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public PosterCardEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public PosterCardEpoxyModel_ wishListableData(WishListableData wishListableData) {
        onMutation();
        this.wishListableData = wishListableData;
        return this;
    }

    public WishListableData wishListableData() {
        return this.wishListableData;
    }

    public PosterCardEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public PosterCardEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public PosterCardEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public PosterCardEpoxyModel_ m5302id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public PosterCardEpoxyModel_ m5307id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public PosterCardEpoxyModel_ m5303id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public PosterCardEpoxyModel_ m5304id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public PosterCardEpoxyModel_ m5306id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public PosterCardEpoxyModel_ m5305id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public PosterCardEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public PosterCardEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public PosterCardEpoxyModel_ show() {
        super.show();
        return this;
    }

    public PosterCardEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public PosterCardEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public PosterCardEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.tripTemplate = null;
        this.loading = false;
        this.displayOptions = null;
        this.clickListener = null;
        this.wishListableData = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        boolean z3;
        if (o == this) {
            return true;
        }
        if (!(o instanceof PosterCardEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        PosterCardEpoxyModel_ that = (PosterCardEpoxyModel_) o;
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
        if (this.tripTemplate != null) {
            if (!this.tripTemplate.equals(that.tripTemplate)) {
                return false;
            }
        } else if (that.tripTemplate != null) {
            return false;
        }
        if (this.loading != that.loading) {
            return false;
        }
        if (this.displayOptions != null) {
            if (!this.displayOptions.equals(that.displayOptions)) {
                return false;
            }
        } else if (that.displayOptions != null) {
            return false;
        }
        if (this.clickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.clickListener == null)) {
            return false;
        }
        if (this.wishListableData == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.wishListableData == null)) {
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
        if (this.tripTemplate != null) {
            i2 = this.tripTemplate.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.loading) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.displayOptions != null) {
            i4 = this.displayOptions.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.clickListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.wishListableData == null) {
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
        return "PosterCardEpoxyModel_{tripTemplate=" + this.tripTemplate + ", loading=" + this.loading + ", displayOptions=" + this.displayOptions + ", clickListener=" + this.clickListener + ", wishListableData=" + this.wishListableData + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
