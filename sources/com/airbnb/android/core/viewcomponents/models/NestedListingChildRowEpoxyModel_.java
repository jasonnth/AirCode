package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.NestedListingChildRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class NestedListingChildRowEpoxyModel_ extends NestedListingChildRowEpoxyModel implements GeneratedModel<NestedListingChildRow> {
    private OnModelBoundListener<NestedListingChildRowEpoxyModel_, NestedListingChildRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<NestedListingChildRowEpoxyModel_, NestedListingChildRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, NestedListingChildRow object, int position) {
    }

    public void handlePostBind(NestedListingChildRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public NestedListingChildRowEpoxyModel_ onBind(OnModelBoundListener<NestedListingChildRowEpoxyModel_, NestedListingChildRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(NestedListingChildRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public NestedListingChildRowEpoxyModel_ onUnbind(OnModelUnboundListener<NestedListingChildRowEpoxyModel_, NestedListingChildRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public NestedListingChildRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public NestedListingChildRowEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public NestedListingChildRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public NestedListingChildRowEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public NestedListingChildRowEpoxyModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public NestedListingChildRowEpoxyModel_ imageDrawableRes(int imageDrawableRes) {
        onMutation();
        this.imageDrawableRes = imageDrawableRes;
        return this;
    }

    public int imageDrawableRes() {
        return this.imageDrawableRes;
    }

    public NestedListingChildRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public NestedListingChildRowEpoxyModel_ isActiveListing(boolean isActiveListing) {
        super.isActiveListing(isActiveListing);
        return this;
    }

    public NestedListingChildRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public NestedListingChildRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public NestedListingChildRowEpoxyModel_ m5194id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public NestedListingChildRowEpoxyModel_ m5199id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public NestedListingChildRowEpoxyModel_ m5195id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public NestedListingChildRowEpoxyModel_ m5196id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public NestedListingChildRowEpoxyModel_ m5198id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public NestedListingChildRowEpoxyModel_ m5197id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public NestedListingChildRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public NestedListingChildRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public NestedListingChildRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public NestedListingChildRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public NestedListingChildRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_nested_listing_child_row;
    }

    public NestedListingChildRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.subtitle = null;
        this.titleRes = 0;
        this.subtitleRes = 0;
        this.imageUrl = null;
        this.imageDrawableRes = 0;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        if (o == this) {
            return true;
        }
        if (!(o instanceof NestedListingChildRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        NestedListingChildRowEpoxyModel_ that = (NestedListingChildRowEpoxyModel_) o;
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
        int i5 = 1;
        int i6 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i5 = 0;
        }
        int i7 = (hashCode + i5) * 31;
        if (this.title != null) {
            i = this.title.hashCode();
        } else {
            i = 0;
        }
        int i8 = (i7 + i) * 31;
        if (this.subtitle != null) {
            i2 = this.subtitle.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (((((i8 + i2) * 31) + this.titleRes) * 31) + this.subtitleRes) * 31;
        if (this.imageUrl != null) {
            i3 = this.imageUrl.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (((i9 + i3) * 31) + this.imageDrawableRes) * 31;
        if (this.showDivider != null) {
            i4 = this.showDivider.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.numCarouselItemsShown != null) {
            i6 = this.numCarouselItemsShown.hashCode();
        }
        return i11 + i6;
    }

    public String toString() {
        return "NestedListingChildRowEpoxyModel_{title=" + this.title + ", subtitle=" + this.subtitle + ", titleRes=" + this.titleRes + ", subtitleRes=" + this.subtitleRes + ", imageUrl=" + this.imageUrl + ", imageDrawableRes=" + this.imageDrawableRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
