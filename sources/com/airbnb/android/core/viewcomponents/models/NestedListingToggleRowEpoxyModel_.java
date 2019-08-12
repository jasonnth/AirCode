package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.NestedListingToggleRow;
import com.airbnb.p027n2.components.NestedListingToggleRow.OnCheckChangedListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class NestedListingToggleRowEpoxyModel_ extends NestedListingToggleRowEpoxyModel implements GeneratedModel<NestedListingToggleRow> {
    private OnModelBoundListener<NestedListingToggleRowEpoxyModel_, NestedListingToggleRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<NestedListingToggleRowEpoxyModel_, NestedListingToggleRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, NestedListingToggleRow object, int position) {
    }

    public void handlePostBind(NestedListingToggleRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public NestedListingToggleRowEpoxyModel_ onBind(OnModelBoundListener<NestedListingToggleRowEpoxyModel_, NestedListingToggleRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(NestedListingToggleRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public NestedListingToggleRowEpoxyModel_ onUnbind(OnModelUnboundListener<NestedListingToggleRowEpoxyModel_, NestedListingToggleRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public NestedListingToggleRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public NestedListingToggleRowEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public NestedListingToggleRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public NestedListingToggleRowEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public NestedListingToggleRowEpoxyModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public NestedListingToggleRowEpoxyModel_ imageDrawableRes(int imageDrawableRes) {
        onMutation();
        this.imageDrawableRes = imageDrawableRes;
        return this;
    }

    public int imageDrawableRes() {
        return this.imageDrawableRes;
    }

    public NestedListingToggleRowEpoxyModel_ enabled(boolean enabled) {
        onMutation();
        this.enabled = enabled;
        return this;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public NestedListingToggleRowEpoxyModel_ checked(boolean checked) {
        onMutation();
        this.checked = checked;
        return this;
    }

    public boolean checked() {
        return this.checked;
    }

    public NestedListingToggleRowEpoxyModel_ listener(OnCheckChangedListener listener) {
        onMutation();
        this.listener = listener;
        return this;
    }

    public OnCheckChangedListener listener() {
        return this.listener;
    }

    public NestedListingToggleRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public NestedListingToggleRowEpoxyModel_ isActiveListing(boolean isActiveListing) {
        super.isActiveListing(isActiveListing);
        return this;
    }

    public NestedListingToggleRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public NestedListingToggleRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public NestedListingToggleRowEpoxyModel_ m5230id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public NestedListingToggleRowEpoxyModel_ m5235id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public NestedListingToggleRowEpoxyModel_ m5231id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public NestedListingToggleRowEpoxyModel_ m5232id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public NestedListingToggleRowEpoxyModel_ m5234id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public NestedListingToggleRowEpoxyModel_ m5233id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public NestedListingToggleRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public NestedListingToggleRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public NestedListingToggleRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public NestedListingToggleRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public NestedListingToggleRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_nested_listing_toggle_row;
    }

    public NestedListingToggleRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.subtitle = null;
        this.titleRes = 0;
        this.subtitleRes = 0;
        this.imageUrl = null;
        this.imageDrawableRes = 0;
        this.enabled = false;
        this.checked = false;
        this.listener = null;
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
        if (!(o instanceof NestedListingToggleRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        NestedListingToggleRowEpoxyModel_ that = (NestedListingToggleRowEpoxyModel_) o;
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
        if (this.imageDrawableRes != that.imageDrawableRes || this.enabled != that.enabled || this.checked != that.checked) {
            return false;
        }
        if ((this.listener == null) != (that.listener == null)) {
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
        int i11 = (i10 + i2) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (((((i11 + i3) * 31) + this.titleRes) * 31) + this.subtitleRes) * 31;
        if (this.imageUrl != null) {
            i4 = this.imageUrl.hashCode();
        } else {
            i4 = 0;
        }
        int i13 = (((i12 + i4) * 31) + this.imageDrawableRes) * 31;
        if (this.enabled) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i14 = (i13 + i5) * 31;
        if (this.checked) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.listener == null) {
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
        return "NestedListingToggleRowEpoxyModel_{title=" + this.title + ", subtitle=" + this.subtitle + ", titleRes=" + this.titleRes + ", subtitleRes=" + this.subtitleRes + ", imageUrl=" + this.imageUrl + ", imageDrawableRes=" + this.imageDrawableRes + ", enabled=" + this.enabled + ", checked=" + this.checked + ", listener=" + this.listener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
