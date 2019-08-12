package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.CityRegistrationCheckmarkRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class CityRegistrationCheckmarkRowEpoxyModel_ extends CityRegistrationCheckmarkRowEpoxyModel implements GeneratedModel<CityRegistrationCheckmarkRow> {
    private OnModelBoundListener<CityRegistrationCheckmarkRowEpoxyModel_, CityRegistrationCheckmarkRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<CityRegistrationCheckmarkRowEpoxyModel_, CityRegistrationCheckmarkRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, CityRegistrationCheckmarkRow object, int position) {
    }

    public void handlePostBind(CityRegistrationCheckmarkRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ onBind(OnModelBoundListener<CityRegistrationCheckmarkRowEpoxyModel_, CityRegistrationCheckmarkRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(CityRegistrationCheckmarkRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ onUnbind(OnModelUnboundListener<CityRegistrationCheckmarkRowEpoxyModel_, CityRegistrationCheckmarkRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ iconDrawableRes(int iconDrawableRes) {
        onMutation();
        this.iconDrawableRes = iconDrawableRes;
        return this;
    }

    public int iconDrawableRes() {
        return this.iconDrawableRes;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public CityRegistrationCheckmarkRowEpoxyModel_ m4459id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public CityRegistrationCheckmarkRowEpoxyModel_ m4464id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public CityRegistrationCheckmarkRowEpoxyModel_ m4460id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public CityRegistrationCheckmarkRowEpoxyModel_ m4461id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public CityRegistrationCheckmarkRowEpoxyModel_ m4463id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public CityRegistrationCheckmarkRowEpoxyModel_ m4462id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_city_registration_checkmark_row;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ withSmallPaddingLayout() {
        layout(C0716R.layout.n2_view_holder_city_registration_checkmark_row_small_padding);
        return this;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ withTinyPaddingLayout() {
        layout(C0716R.layout.n2_view_holder_city_registration_checkmark_row_tiny_padding);
        return this;
    }

    public CityRegistrationCheckmarkRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.subtitle = null;
        this.subtitleRes = 0;
        this.iconDrawableRes = 0;
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
        if (!(o instanceof CityRegistrationCheckmarkRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        CityRegistrationCheckmarkRowEpoxyModel_ that = (CityRegistrationCheckmarkRowEpoxyModel_) o;
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
        if (this.subtitleRes != that.subtitleRes || this.iconDrawableRes != that.iconDrawableRes) {
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
        int i4 = 1;
        int i5 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i4 = 0;
        }
        int i6 = (hashCode + i4) * 31;
        if (this.title != null) {
            i = this.title.hashCode();
        } else {
            i = 0;
        }
        int i7 = (((i6 + i) * 31) + this.titleRes) * 31;
        if (this.subtitle != null) {
            i2 = this.subtitle.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (((((i7 + i2) * 31) + this.subtitleRes) * 31) + this.iconDrawableRes) * 31;
        if (this.showDivider != null) {
            i3 = this.showDivider.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.numCarouselItemsShown != null) {
            i5 = this.numCarouselItemsShown.hashCode();
        }
        return i9 + i5;
    }

    public String toString() {
        return "CityRegistrationCheckmarkRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", subtitle=" + this.subtitle + ", subtitleRes=" + this.subtitleRes + ", iconDrawableRes=" + this.iconDrawableRes + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
