package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.SwitchRow;
import com.airbnb.p027n2.components.SwitchStyle;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

public class SwitchRowEpoxyModel_ extends SwitchRowEpoxyModel implements GeneratedModel<SwitchRow> {
    private OnModelBoundListener<SwitchRowEpoxyModel_, SwitchRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SwitchRowEpoxyModel_, SwitchRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, SwitchRow object, int position) {
    }

    public void handlePostBind(SwitchRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public SwitchRowEpoxyModel_ onBind(OnModelBoundListener<SwitchRowEpoxyModel_, SwitchRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(SwitchRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public SwitchRowEpoxyModel_ onUnbind(OnModelUnboundListener<SwitchRowEpoxyModel_, SwitchRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public SwitchRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public SwitchRowEpoxyModel_ descriptionRes(int descriptionRes) {
        onMutation();
        this.descriptionRes = descriptionRes;
        return this;
    }

    public int descriptionRes() {
        return this.descriptionRes;
    }

    public SwitchRowEpoxyModel_ title(String title) {
        onMutation();
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public SwitchRowEpoxyModel_ checked(boolean checked) {
        onMutation();
        this.checked = checked;
        return this;
    }

    public boolean checked() {
        return this.checked;
    }

    public SwitchRowEpoxyModel_ enabled(boolean enabled) {
        onMutation();
        this.enabled = enabled;
        return this;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public SwitchRowEpoxyModel_ style(SwitchStyle style) {
        onMutation();
        this.style = style;
        super.style(style);
        return this;
    }

    public SwitchStyle style() {
        return this.style;
    }

    public SwitchRowEpoxyModel_ checkedChangeListener(OnCheckedChangeListener checkedChangeListener) {
        onMutation();
        this.checkedChangeListener = checkedChangeListener;
        return this;
    }

    public OnCheckedChangeListener checkedChangeListener() {
        return this.checkedChangeListener;
    }

    public SwitchRowEpoxyModel_ updateModelWithCheckedState(boolean updateModelWithCheckedState) {
        onMutation();
        this.updateModelWithCheckedState = updateModelWithCheckedState;
        return this;
    }

    public boolean updateModelWithCheckedState() {
        return this.updateModelWithCheckedState;
    }

    public SwitchRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SwitchRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SwitchRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SwitchRowEpoxyModel_ m5674id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SwitchRowEpoxyModel_ m5679id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SwitchRowEpoxyModel_ m5675id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SwitchRowEpoxyModel_ m5676id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SwitchRowEpoxyModel_ m5678id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SwitchRowEpoxyModel_ m5677id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SwitchRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SwitchRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SwitchRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public SwitchRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SwitchRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_switch_row_sheet;
    }

    public SwitchRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.titleRes = 0;
        this.descriptionRes = 0;
        this.title = null;
        this.checked = false;
        this.enabled = false;
        this.style = null;
        this.checkedChangeListener = null;
        this.updateModelWithCheckedState = false;
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
        if (!(o instanceof SwitchRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        SwitchRowEpoxyModel_ that = (SwitchRowEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || this.titleRes != that.titleRes || this.descriptionRes != that.descriptionRes) {
            return false;
        }
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (this.checked != that.checked || this.enabled != that.enabled) {
            return false;
        }
        if (this.style != null) {
            if (!this.style.equals(that.style)) {
                return false;
            }
        } else if (that.style != null) {
            return false;
        }
        if (this.checkedChangeListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.checkedChangeListener == null) || this.updateModelWithCheckedState != that.updateModelWithCheckedState) {
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
        int i10 = (((((hashCode + i) * 31) + this.titleRes) * 31) + this.descriptionRes) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i11 = (i10 + i2) * 31;
        if (this.checked) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i12 = (i11 + i3) * 31;
        if (this.enabled) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i13 = (i12 + i4) * 31;
        if (this.style != null) {
            i5 = this.style.hashCode();
        } else {
            i5 = 0;
        }
        int i14 = (i13 + i5) * 31;
        if (this.checkedChangeListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (!this.updateModelWithCheckedState) {
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
        return "SwitchRowEpoxyModel_{titleRes=" + this.titleRes + ", descriptionRes=" + this.descriptionRes + ", title=" + this.title + ", checked=" + this.checked + ", enabled=" + this.enabled + ", style=" + this.style + ", checkedChangeListener=" + this.checkedChangeListener + ", updateModelWithCheckedState=" + this.updateModelWithCheckedState + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
