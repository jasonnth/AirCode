package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.NestedListingEditRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class NestedListingEditRowEpoxyModel_ extends NestedListingEditRowEpoxyModel implements GeneratedModel<NestedListingEditRow> {
    private OnModelBoundListener<NestedListingEditRowEpoxyModel_, NestedListingEditRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<NestedListingEditRowEpoxyModel_, NestedListingEditRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, NestedListingEditRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(NestedListingEditRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public NestedListingEditRowEpoxyModel_ onBind(OnModelBoundListener<NestedListingEditRowEpoxyModel_, NestedListingEditRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(NestedListingEditRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public NestedListingEditRowEpoxyModel_ onUnbind(OnModelUnboundListener<NestedListingEditRowEpoxyModel_, NestedListingEditRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public NestedListingEditRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public NestedListingEditRowEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public NestedListingEditRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public NestedListingEditRowEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public NestedListingEditRowEpoxyModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public NestedListingEditRowEpoxyModel_ imageDrawableRes(int imageDrawableRes) {
        onMutation();
        this.imageDrawableRes = imageDrawableRes;
        return this;
    }

    public int imageDrawableRes() {
        return this.imageDrawableRes;
    }

    public NestedListingEditRowEpoxyModel_ clickListener(OnModelClickListener<NestedListingEditRowEpoxyModel_, NestedListingEditRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public NestedListingEditRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public NestedListingEditRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public NestedListingEditRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public NestedListingEditRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public NestedListingEditRowEpoxyModel_ m5206id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public NestedListingEditRowEpoxyModel_ m5211id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public NestedListingEditRowEpoxyModel_ m5207id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public NestedListingEditRowEpoxyModel_ m5208id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public NestedListingEditRowEpoxyModel_ m5210id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public NestedListingEditRowEpoxyModel_ m5209id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public NestedListingEditRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public NestedListingEditRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public NestedListingEditRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public NestedListingEditRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public NestedListingEditRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_nested_listing_edit_row;
    }

    public NestedListingEditRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.subtitle = null;
        this.titleRes = 0;
        this.subtitleRes = 0;
        this.imageUrl = null;
        this.imageDrawableRes = 0;
        this.clickListener = null;
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
        if (!(o instanceof NestedListingEditRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        NestedListingEditRowEpoxyModel_ that = (NestedListingEditRowEpoxyModel_) o;
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
        if (this.subtitle != null) {
            if (!this.subtitle.equals(that.subtitle)) {
                return false;
            }
        } else if (that.subtitle != null) {
            return false;
        }
        if (this.titleRes != that.titleRes || this.subtitleRes != that.subtitleRes) {
            return false;
        }
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(that.imageUrl)) {
                return false;
            }
        } else if (that.imageUrl != null) {
            return false;
        }
        if (this.imageDrawableRes != that.imageDrawableRes) {
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
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (((((i9 + i3) * 31) + this.titleRes) * 31) + this.subtitleRes) * 31;
        if (this.imageUrl != null) {
            i4 = this.imageUrl.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (((i10 + i4) * 31) + this.imageDrawableRes) * 31;
        if (this.clickListener == null) {
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
        return "NestedListingEditRowEpoxyModel_{title=" + this.title + ", subtitle=" + this.subtitle + ", titleRes=" + this.titleRes + ", subtitleRes=" + this.subtitleRes + ", imageUrl=" + this.imageUrl + ", imageDrawableRes=" + this.imageDrawableRes + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
