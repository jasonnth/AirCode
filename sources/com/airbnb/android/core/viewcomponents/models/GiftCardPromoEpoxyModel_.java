package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.GiftCardPromo;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class GiftCardPromoEpoxyModel_ extends GiftCardPromoEpoxyModel implements GeneratedModel<GiftCardPromo> {
    private OnModelBoundListener<GiftCardPromoEpoxyModel_, GiftCardPromo> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<GiftCardPromoEpoxyModel_, GiftCardPromo> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, GiftCardPromo object, int position) {
        if (this.onClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(GiftCardPromo object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public GiftCardPromoEpoxyModel_ onBind(OnModelBoundListener<GiftCardPromoEpoxyModel_, GiftCardPromo> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(GiftCardPromo object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public GiftCardPromoEpoxyModel_ onUnbind(OnModelUnboundListener<GiftCardPromoEpoxyModel_, GiftCardPromo> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public GiftCardPromoEpoxyModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public GiftCardPromoEpoxyModel_ onClickListener(OnModelClickListener<GiftCardPromoEpoxyModel_, GiftCardPromo> onClickListener) {
        onMutation();
        if (onClickListener == null) {
            this.onClickListener = null;
        } else {
            this.onClickListener = new WrappedEpoxyModelClickListener(this, onClickListener);
        }
        return this;
    }

    public GiftCardPromoEpoxyModel_ onClickListener(OnClickListener onClickListener) {
        onMutation();
        this.onClickListener = onClickListener;
        return this;
    }

    public OnClickListener onClickListener() {
        return this.onClickListener;
    }

    public GiftCardPromoEpoxyModel_ displayOptions(DisplayOptions displayOptions) {
        onMutation();
        this.displayOptions = displayOptions;
        return this;
    }

    public DisplayOptions displayOptions() {
        return this.displayOptions;
    }

    public GiftCardPromoEpoxyModel_ isTablet(boolean isTablet) {
        onMutation();
        this.isTablet = isTablet;
        return this;
    }

    public boolean isTablet() {
        return this.isTablet;
    }

    public GiftCardPromoEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public GiftCardPromoEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public GiftCardPromoEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public GiftCardPromoEpoxyModel_ m4630id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public GiftCardPromoEpoxyModel_ m4635id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public GiftCardPromoEpoxyModel_ m4631id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public GiftCardPromoEpoxyModel_ m4632id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public GiftCardPromoEpoxyModel_ m4634id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public GiftCardPromoEpoxyModel_ m4633id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public GiftCardPromoEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public GiftCardPromoEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public GiftCardPromoEpoxyModel_ show() {
        super.show();
        return this;
    }

    public GiftCardPromoEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public GiftCardPromoEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public GiftCardPromoEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.imageUrl = null;
        this.onClickListener = null;
        this.displayOptions = null;
        this.isTablet = false;
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
        if (!(o instanceof GiftCardPromoEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        GiftCardPromoEpoxyModel_ that = (GiftCardPromoEpoxyModel_) o;
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
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(that.imageUrl)) {
                return false;
            }
        } else if (that.imageUrl != null) {
            return false;
        }
        if (this.onClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.onClickListener == null)) {
            return false;
        }
        if (this.displayOptions != null) {
            if (!this.displayOptions.equals(that.displayOptions)) {
                return false;
            }
        } else if (that.displayOptions != null) {
            return false;
        }
        if (this.isTablet != that.isTablet) {
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
        int i6 = 1;
        int i7 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i8 = (hashCode + i) * 31;
        if (this.imageUrl != null) {
            i2 = this.imageUrl.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.onClickListener != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.displayOptions != null) {
            i4 = this.displayOptions.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (!this.isTablet) {
            i6 = 0;
        }
        int i12 = (i11 + i6) * 31;
        if (this.showDivider != null) {
            i5 = this.showDivider.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.numCarouselItemsShown != null) {
            i7 = this.numCarouselItemsShown.hashCode();
        }
        return i13 + i7;
    }

    public String toString() {
        return "GiftCardPromoEpoxyModel_{imageUrl=" + this.imageUrl + ", onClickListener=" + this.onClickListener + ", displayOptions=" + this.displayOptions + ", isTablet=" + this.isTablet + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
