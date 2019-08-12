package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.CityRegistrationToggleRow;
import com.airbnb.p027n2.components.CityRegistrationToggleRow.OnCheckChangedListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class CityRegistrationToggleRowEpoxyModel_ extends CityRegistrationToggleRowEpoxyModel implements GeneratedModel<CityRegistrationToggleRow> {
    private OnModelBoundListener<CityRegistrationToggleRowEpoxyModel_, CityRegistrationToggleRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<CityRegistrationToggleRowEpoxyModel_, CityRegistrationToggleRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, CityRegistrationToggleRow object, int position) {
    }

    public void handlePostBind(CityRegistrationToggleRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public CityRegistrationToggleRowEpoxyModel_ onBind(OnModelBoundListener<CityRegistrationToggleRowEpoxyModel_, CityRegistrationToggleRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(CityRegistrationToggleRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public CityRegistrationToggleRowEpoxyModel_ onUnbind(OnModelUnboundListener<CityRegistrationToggleRowEpoxyModel_, CityRegistrationToggleRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public CityRegistrationToggleRowEpoxyModel_ checked(boolean checked) {
        onMutation();
        this.checked = checked;
        return this;
    }

    public boolean checked() {
        return this.checked;
    }

    public CityRegistrationToggleRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public CityRegistrationToggleRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public CityRegistrationToggleRowEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public CityRegistrationToggleRowEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public CityRegistrationToggleRowEpoxyModel_ checkChangedListener(OnCheckChangedListener checkChangedListener) {
        onMutation();
        this.checkChangedListener = checkChangedListener;
        return this;
    }

    public OnCheckChangedListener checkChangedListener() {
        return this.checkChangedListener;
    }

    public CityRegistrationToggleRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public CityRegistrationToggleRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public CityRegistrationToggleRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public CityRegistrationToggleRowEpoxyModel_ m4483id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public CityRegistrationToggleRowEpoxyModel_ m4488id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public CityRegistrationToggleRowEpoxyModel_ m4484id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public CityRegistrationToggleRowEpoxyModel_ m4485id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public CityRegistrationToggleRowEpoxyModel_ m4487id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public CityRegistrationToggleRowEpoxyModel_ m4486id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public CityRegistrationToggleRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public CityRegistrationToggleRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public CityRegistrationToggleRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public CityRegistrationToggleRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public CityRegistrationToggleRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_city_registration_toggle_row;
    }

    public CityRegistrationToggleRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.checked = false;
        this.title = null;
        this.titleRes = 0;
        this.subtitle = null;
        this.subtitleRes = 0;
        this.checkChangedListener = null;
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
        if (!(o instanceof CityRegistrationToggleRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        CityRegistrationToggleRowEpoxyModel_ that = (CityRegistrationToggleRowEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.checked != that.checked) {
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
        if (this.checkChangedListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.checkChangedListener == null)) {
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
        if (this.checked) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.title != null) {
            i3 = this.title.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (((i9 + i3) * 31) + this.titleRes) * 31;
        if (this.subtitle != null) {
            i4 = this.subtitle.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (((i10 + i4) * 31) + this.subtitleRes) * 31;
        if (this.checkChangedListener == null) {
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
        return "CityRegistrationToggleRowEpoxyModel_{checked=" + this.checked + ", title=" + this.title + ", titleRes=" + this.titleRes + ", subtitle=" + this.subtitle + ", subtitleRes=" + this.subtitleRes + ", checkChangedListener=" + this.checkChangedListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
