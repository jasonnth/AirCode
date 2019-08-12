package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.PlaceCard;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class GuidebookPlaceEpoxyModel_ extends GuidebookPlaceEpoxyModel implements GeneratedModel<PlaceCard> {
    private OnModelBoundListener<GuidebookPlaceEpoxyModel_, PlaceCard> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<GuidebookPlaceEpoxyModel_, PlaceCard> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, PlaceCard object, int position) {
        if (this.cardClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.cardClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(PlaceCard object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public GuidebookPlaceEpoxyModel_ onBind(OnModelBoundListener<GuidebookPlaceEpoxyModel_, PlaceCard> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(PlaceCard object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public GuidebookPlaceEpoxyModel_ onUnbind(OnModelUnboundListener<GuidebookPlaceEpoxyModel_, PlaceCard> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public GuidebookPlaceEpoxyModel_ loading(boolean loading) {
        onMutation();
        this.loading = loading;
        return this;
    }

    public boolean loading() {
        return this.loading;
    }

    public GuidebookPlaceEpoxyModel_ selectionHighlight(boolean selectionHighlight) {
        onMutation();
        this.selectionHighlight = selectionHighlight;
        return this;
    }

    public boolean selectionHighlight() {
        return this.selectionHighlight;
    }

    public GuidebookPlaceEpoxyModel_ showBottomSpace(boolean showBottomSpace) {
        onMutation();
        this.showBottomSpace = showBottomSpace;
        return this;
    }

    public boolean showBottomSpace() {
        return this.showBottomSpace;
    }

    public GuidebookPlaceEpoxyModel_ photo(Photo photo) {
        onMutation();
        this.photo = photo;
        return this;
    }

    public Photo photo() {
        return this.photo;
    }

    public GuidebookPlaceEpoxyModel_ regularText(String regularText) {
        onMutation();
        this.regularText = regularText;
        return this;
    }

    public String regularText() {
        return this.regularText;
    }

    public GuidebookPlaceEpoxyModel_ boldText(String boldText) {
        onMutation();
        this.boldText = boldText;
        return this;
    }

    public String boldText() {
        return this.boldText;
    }

    public GuidebookPlaceEpoxyModel_ invisible(boolean invisible) {
        onMutation();
        this.invisible = invisible;
        return this;
    }

    public boolean invisible() {
        return this.invisible;
    }

    public GuidebookPlaceEpoxyModel_ cardClickListener(OnModelClickListener<GuidebookPlaceEpoxyModel_, PlaceCard> cardClickListener) {
        onMutation();
        if (cardClickListener == null) {
            this.cardClickListener = null;
        } else {
            this.cardClickListener = new WrappedEpoxyModelClickListener(this, cardClickListener);
        }
        return this;
    }

    public GuidebookPlaceEpoxyModel_ cardClickListener(OnClickListener cardClickListener) {
        onMutation();
        this.cardClickListener = cardClickListener;
        return this;
    }

    public OnClickListener cardClickListener() {
        return this.cardClickListener;
    }

    public GuidebookPlaceEpoxyModel_ wishListableData(WishListableData wishListableData) {
        onMutation();
        this.wishListableData = wishListableData;
        return this;
    }

    public WishListableData wishListableData() {
        return this.wishListableData;
    }

    public GuidebookPlaceEpoxyModel_ displayOptions(DisplayOptions displayOptions) {
        onMutation();
        this.displayOptions = displayOptions;
        return this;
    }

    public DisplayOptions displayOptions() {
        return this.displayOptions;
    }

    public GuidebookPlaceEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public GuidebookPlaceEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public GuidebookPlaceEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public GuidebookPlaceEpoxyModel_ m4678id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public GuidebookPlaceEpoxyModel_ m4683id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public GuidebookPlaceEpoxyModel_ m4679id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public GuidebookPlaceEpoxyModel_ m4680id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public GuidebookPlaceEpoxyModel_ m4682id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public GuidebookPlaceEpoxyModel_ m4681id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public GuidebookPlaceEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public GuidebookPlaceEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public GuidebookPlaceEpoxyModel_ show() {
        super.show();
        return this;
    }

    public GuidebookPlaceEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public GuidebookPlaceEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public GuidebookPlaceEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.loading = false;
        this.selectionHighlight = false;
        this.showBottomSpace = false;
        this.photo = null;
        this.regularText = null;
        this.boldText = null;
        this.invisible = false;
        this.cardClickListener = null;
        this.wishListableData = null;
        this.displayOptions = null;
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
        if (!(o instanceof GuidebookPlaceEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        GuidebookPlaceEpoxyModel_ that = (GuidebookPlaceEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (that.onModelUnboundListener_epoxyGeneratedModel == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z != z2 || this.loading != that.loading || this.selectionHighlight != that.selectionHighlight || this.showBottomSpace != that.showBottomSpace) {
            return false;
        }
        if (this.photo != null) {
            if (!this.photo.equals(that.photo)) {
                return false;
            }
        } else if (that.photo != null) {
            return false;
        }
        if (this.regularText != null) {
            if (!this.regularText.equals(that.regularText)) {
                return false;
            }
        } else if (that.regularText != null) {
            return false;
        }
        if (this.boldText != null) {
            if (!this.boldText.equals(that.boldText)) {
                return false;
            }
        } else if (that.boldText != null) {
            return false;
        }
        if (this.invisible != that.invisible) {
            return false;
        }
        if (this.cardClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.cardClickListener == null)) {
            return false;
        }
        if (this.wishListableData == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.wishListableData == null)) {
            return false;
        }
        if (this.displayOptions != null) {
            if (!this.displayOptions.equals(that.displayOptions)) {
                return false;
            }
        } else if (that.displayOptions != null) {
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
        int i10;
        int i11;
        int i12 = 1;
        int i13 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i14 = (hashCode + i) * 31;
        if (this.loading) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i15 = (i14 + i2) * 31;
        if (this.selectionHighlight) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i16 = (i15 + i3) * 31;
        if (this.showBottomSpace) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i17 = (i16 + i4) * 31;
        if (this.photo != null) {
            i5 = this.photo.hashCode();
        } else {
            i5 = 0;
        }
        int i18 = (i17 + i5) * 31;
        if (this.regularText != null) {
            i6 = this.regularText.hashCode();
        } else {
            i6 = 0;
        }
        int i19 = (i18 + i6) * 31;
        if (this.boldText != null) {
            i7 = this.boldText.hashCode();
        } else {
            i7 = 0;
        }
        int i20 = (i19 + i7) * 31;
        if (this.invisible) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i21 = (i20 + i8) * 31;
        if (this.cardClickListener != null) {
            i9 = 1;
        } else {
            i9 = 0;
        }
        int i22 = (i21 + i9) * 31;
        if (this.wishListableData == null) {
            i12 = 0;
        }
        int i23 = (i22 + i12) * 31;
        if (this.displayOptions != null) {
            i10 = this.displayOptions.hashCode();
        } else {
            i10 = 0;
        }
        int i24 = (i23 + i10) * 31;
        if (this.showDivider != null) {
            i11 = this.showDivider.hashCode();
        } else {
            i11 = 0;
        }
        int i25 = (i24 + i11) * 31;
        if (this.numCarouselItemsShown != null) {
            i13 = this.numCarouselItemsShown.hashCode();
        }
        return i25 + i13;
    }

    public String toString() {
        return "GuidebookPlaceEpoxyModel_{loading=" + this.loading + ", selectionHighlight=" + this.selectionHighlight + ", showBottomSpace=" + this.showBottomSpace + ", photo=" + this.photo + ", regularText=" + this.regularText + ", boldText=" + this.boldText + ", invisible=" + this.invisible + ", cardClickListener=" + this.cardClickListener + ", wishListableData=" + this.wishListableData + ", displayOptions=" + this.displayOptions + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
