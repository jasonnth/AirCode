package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.CarouselItemClickListener;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.ListingTrayItem;
import com.airbnb.android.core.views.ListingsTray;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.collections.Carousel.OnSnapToPositionListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class ListingsTrayEpoxyModel_ extends ListingsTrayEpoxyModel implements GeneratedModel<ListingsTray> {
    private OnModelBoundListener<ListingsTrayEpoxyModel_, ListingsTray> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ListingsTrayEpoxyModel_, ListingsTray> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ListingsTray object, int position) {
    }

    public void handlePostBind(ListingsTray object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ListingsTrayEpoxyModel_ onBind(OnModelBoundListener<ListingsTrayEpoxyModel_, ListingsTray> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ListingsTray object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ListingsTrayEpoxyModel_ onUnbind(OnModelUnboundListener<ListingsTrayEpoxyModel_, ListingsTray> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ListingsTrayEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public ListingsTrayEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public ListingsTrayEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public ListingsTrayEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public ListingsTrayEpoxyModel_ items(List<ListingTrayItem> items) {
        onMutation();
        this.items = items;
        return this;
    }

    public List<ListingTrayItem> items() {
        return this.items;
    }

    public ListingsTrayEpoxyModel_ showSmallTitle(boolean showSmallTitle) {
        onMutation();
        this.showSmallTitle = showSmallTitle;
        return this;
    }

    public boolean showSmallTitle() {
        return this.showSmallTitle;
    }

    public ListingsTrayEpoxyModel_ carouselItemClickListener(CarouselItemClickListener carouselItemClickListener) {
        onMutation();
        this.carouselItemClickListener = carouselItemClickListener;
        return this;
    }

    public CarouselItemClickListener carouselItemClickListener() {
        return this.carouselItemClickListener;
    }

    public ListingsTrayEpoxyModel_ snapToPositionListener(OnSnapToPositionListener snapToPositionListener) {
        onMutation();
        this.snapToPositionListener = snapToPositionListener;
        return this;
    }

    public OnSnapToPositionListener snapToPositionListener() {
        return this.snapToPositionListener;
    }

    public ListingsTrayEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ListingsTrayEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ListingsTrayEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ListingsTrayEpoxyModel_ m5098id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ListingsTrayEpoxyModel_ m5103id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ListingsTrayEpoxyModel_ m5099id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ListingsTrayEpoxyModel_ m5100id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ListingsTrayEpoxyModel_ m5102id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ListingsTrayEpoxyModel_ m5101id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ListingsTrayEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ListingsTrayEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ListingsTrayEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ListingsTrayEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ListingsTrayEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public ListingsTrayEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.subtitle = null;
        this.subtitleRes = 0;
        this.items = null;
        this.showSmallTitle = false;
        this.carouselItemClickListener = null;
        this.snapToPositionListener = null;
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
        if (!(o instanceof ListingsTrayEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ListingsTrayEpoxyModel_ that = (ListingsTrayEpoxyModel_) o;
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
        if (this.titleRes != that.titleRes) {
            return false;
        }
        if (this.subtitle != null) {
            if (!this.subtitle.equals(that.subtitle)) {
                return false;
            }
        } else if (that.subtitle != null) {
            return false;
        }
        if (this.subtitleRes != that.subtitleRes) {
            return false;
        }
        if (this.items != null) {
            if (!this.items.equals(that.items)) {
                return false;
            }
        } else if (that.items != null) {
            return false;
        }
        if (this.showSmallTitle != that.showSmallTitle) {
            return false;
        }
        if (this.carouselItemClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.carouselItemClickListener == null)) {
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
        int i8 = 1;
        int i9 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i10 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i11 = (((i10 + i2) * 31) + this.titleRes) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (((i11 + i3) * 31) + this.subtitleRes) * 31;
        if (this.items != null) {
            i4 = this.items.hashCode();
        } else {
            i4 = 0;
        }
        int i13 = (i12 + i4) * 31;
        if (this.showSmallTitle) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i14 = (i13 + i5) * 31;
        if (this.carouselItemClickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.snapToPositionListener == null) {
            i8 = 0;
        }
        int i16 = (i15 + i8) * 31;
        if (this.showDivider != null) {
            i7 = this.showDivider.hashCode();
        } else {
            i7 = 0;
        }
        int i17 = (i16 + i7) * 31;
        if (this.numCarouselItemsShown != null) {
            i9 = this.numCarouselItemsShown.hashCode();
        }
        return i17 + i9;
    }

    public String toString() {
        return "ListingsTrayEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", subtitle=" + this.subtitle + ", subtitleRes=" + this.subtitleRes + ", items=" + this.items + ", showSmallTitle=" + this.showSmallTitle + ", carouselItemClickListener=" + this.carouselItemClickListener + ", snapToPositionListener=" + this.snapToPositionListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
