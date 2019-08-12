package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.TriStateSwitchRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.interfaces.ThreeWayToggle.ToggleState;
import com.airbnb.p027n2.primitives.TriStateSwitch.OnCheckedChangeListener;
import com.airbnb.p027n2.primitives.TriStateSwitch.TriStateSwitchStyle;

public class TriStateSwitchRowEpoxyModel_ extends TriStateSwitchRowEpoxyModel implements GeneratedModel<TriStateSwitchRow> {
    private OnModelBoundListener<TriStateSwitchRowEpoxyModel_, TriStateSwitchRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<TriStateSwitchRowEpoxyModel_, TriStateSwitchRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, TriStateSwitchRow object, int position) {
    }

    public void handlePostBind(TriStateSwitchRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public TriStateSwitchRowEpoxyModel_ onBind(OnModelBoundListener<TriStateSwitchRowEpoxyModel_, TriStateSwitchRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(TriStateSwitchRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public TriStateSwitchRowEpoxyModel_ onUnbind(OnModelUnboundListener<TriStateSwitchRowEpoxyModel_, TriStateSwitchRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public TriStateSwitchRowEpoxyModel_ title(String title) {
        onMutation();
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public TriStateSwitchRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public TriStateSwitchRowEpoxyModel_ description(String description) {
        onMutation();
        this.description = description;
        return this;
    }

    public String description() {
        return this.description;
    }

    public TriStateSwitchRowEpoxyModel_ style(TriStateSwitchStyle style) {
        onMutation();
        this.style = style;
        super.style(style);
        return this;
    }

    public TriStateSwitchStyle style() {
        return this.style;
    }

    public TriStateSwitchRowEpoxyModel_ checkedChangeListener(OnCheckedChangeListener checkedChangeListener) {
        onMutation();
        this.checkedChangeListener = checkedChangeListener;
        return this;
    }

    public OnCheckedChangeListener checkedChangeListener() {
        return this.checkedChangeListener;
    }

    public TriStateSwitchRowEpoxyModel_ toggleState(ToggleState toggleState) {
        onMutation();
        this.toggleState = toggleState;
        return this;
    }

    public ToggleState toggleState() {
        return this.toggleState;
    }

    public TriStateSwitchRowEpoxyModel_ enabled(boolean enabled) {
        onMutation();
        this.enabled = enabled;
        return this;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public TriStateSwitchRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public TriStateSwitchRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public TriStateSwitchRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public TriStateSwitchRowEpoxyModel_ m5734id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public TriStateSwitchRowEpoxyModel_ m5739id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public TriStateSwitchRowEpoxyModel_ m5735id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public TriStateSwitchRowEpoxyModel_ m5736id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public TriStateSwitchRowEpoxyModel_ m5738id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public TriStateSwitchRowEpoxyModel_ m5737id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public TriStateSwitchRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public TriStateSwitchRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public TriStateSwitchRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public TriStateSwitchRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public TriStateSwitchRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_tri_state_switch_row_outlined;
    }

    public TriStateSwitchRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.description = null;
        this.style = null;
        this.checkedChangeListener = null;
        this.toggleState = null;
        this.enabled = false;
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
        if (!(o instanceof TriStateSwitchRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        TriStateSwitchRowEpoxyModel_ that = (TriStateSwitchRowEpoxyModel_) o;
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
        if (this.description != null) {
            if (!this.description.equals(that.description)) {
                return false;
            }
        } else if (that.description != null) {
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
        if (z2 != (that.checkedChangeListener == null)) {
            return false;
        }
        if (this.toggleState != null) {
            if (!this.toggleState.equals(that.toggleState)) {
                return false;
            }
        } else if (that.toggleState != null) {
            return false;
        }
        if (this.enabled != that.enabled) {
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
        if (this.description != null) {
            i3 = this.description.hashCode();
        } else {
            i3 = 0;
        }
        int i12 = (i11 + i3) * 31;
        if (this.style != null) {
            i4 = this.style.hashCode();
        } else {
            i4 = 0;
        }
        int i13 = (i12 + i4) * 31;
        if (this.checkedChangeListener != null) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i14 = (i13 + i5) * 31;
        if (this.toggleState != null) {
            i6 = this.toggleState.hashCode();
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (!this.enabled) {
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
        return "TriStateSwitchRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", description=" + this.description + ", style=" + this.style + ", checkedChangeListener=" + this.checkedChangeListener + ", toggleState=" + this.toggleState + ", enabled=" + this.enabled + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
