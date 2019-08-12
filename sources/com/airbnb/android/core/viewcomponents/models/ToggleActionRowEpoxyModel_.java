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
import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.components.ToggleActionRow.OnCheckedChangeListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ToggleActionRowEpoxyModel_ extends ToggleActionRowEpoxyModel implements GeneratedModel<ToggleActionRow> {
    private OnModelBoundListener<ToggleActionRowEpoxyModel_, ToggleActionRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ToggleActionRowEpoxyModel_, ToggleActionRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ToggleActionRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(ToggleActionRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ToggleActionRowEpoxyModel_ onBind(OnModelBoundListener<ToggleActionRowEpoxyModel_, ToggleActionRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ToggleActionRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ToggleActionRowEpoxyModel_ onUnbind(OnModelUnboundListener<ToggleActionRowEpoxyModel_, ToggleActionRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ToggleActionRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public ToggleActionRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public ToggleActionRowEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public ToggleActionRowEpoxyModel_ subtitleRes(int subtitleRes) {
        onMutation();
        this.subtitleRes = subtitleRes;
        return this;
    }

    public int subtitleRes() {
        return this.subtitleRes;
    }

    public ToggleActionRowEpoxyModel_ label(CharSequence label) {
        onMutation();
        this.label = label;
        return this;
    }

    public CharSequence label() {
        return this.label;
    }

    public ToggleActionRowEpoxyModel_ labelRes(int labelRes) {
        onMutation();
        this.labelRes = labelRes;
        return this;
    }

    public int labelRes() {
        return this.labelRes;
    }

    public ToggleActionRowEpoxyModel_ radio(boolean radio) {
        onMutation();
        this.radio = radio;
        return this;
    }

    public boolean radio() {
        return this.radio;
    }

    public ToggleActionRowEpoxyModel_ readOnly(boolean readOnly) {
        onMutation();
        this.readOnly = readOnly;
        return this;
    }

    public boolean readOnly() {
        return this.readOnly;
    }

    public ToggleActionRowEpoxyModel_ checked(boolean checked) {
        onMutation();
        this.checked = checked;
        return this;
    }

    public boolean checked() {
        return this.checked;
    }

    public ToggleActionRowEpoxyModel_ enabled(boolean enabled) {
        onMutation();
        this.enabled = enabled;
        return this;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public ToggleActionRowEpoxyModel_ checkedChangedlistener(OnCheckedChangeListener checkedChangedlistener) {
        onMutation();
        this.checkedChangedlistener = checkedChangedlistener;
        return this;
    }

    public OnCheckedChangeListener checkedChangedlistener() {
        return this.checkedChangedlistener;
    }

    public ToggleActionRowEpoxyModel_ clickListener(OnModelClickListener<ToggleActionRowEpoxyModel_, ToggleActionRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public ToggleActionRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public ToggleActionRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ToggleActionRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ToggleActionRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ToggleActionRowEpoxyModel_ m5698id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ToggleActionRowEpoxyModel_ m5703id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ToggleActionRowEpoxyModel_ m5699id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ToggleActionRowEpoxyModel_ m5700id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ToggleActionRowEpoxyModel_ m5702id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ToggleActionRowEpoxyModel_ m5701id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ToggleActionRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ToggleActionRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ToggleActionRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ToggleActionRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ToggleActionRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_toggle_action_row;
    }

    public ToggleActionRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.subtitle = null;
        this.subtitleRes = 0;
        this.label = null;
        this.labelRes = 0;
        this.radio = false;
        this.readOnly = false;
        this.checked = false;
        this.enabled = false;
        this.checkedChangedlistener = null;
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
        if (!(o instanceof ToggleActionRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ToggleActionRowEpoxyModel_ that = (ToggleActionRowEpoxyModel_) o;
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
        if (this.label != null) {
            if (!this.label.equals(that.label)) {
                return false;
            }
        } else if (that.label != null) {
            return false;
        }
        if (this.labelRes != that.labelRes || this.radio != that.radio || this.readOnly != that.readOnly || this.checked != that.checked || this.enabled != that.enabled) {
            return false;
        }
        if ((this.checkedChangedlistener == null) != (that.checkedChangedlistener == null)) {
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
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11 = 1;
        int i12 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i13 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i14 = (((i13 + i2) * 31) + this.titleRes) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i15 = (((i14 + i3) * 31) + this.subtitleRes) * 31;
        if (this.label != null) {
            i4 = this.label.hashCode();
        } else {
            i4 = 0;
        }
        int i16 = (((i15 + i4) * 31) + this.labelRes) * 31;
        if (this.radio) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i17 = (i16 + i5) * 31;
        if (this.readOnly) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i18 = (i17 + i6) * 31;
        if (this.checked) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i19 = (i18 + i7) * 31;
        if (this.enabled) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i20 = (i19 + i8) * 31;
        if (this.checkedChangedlistener != null) {
            i9 = 1;
        } else {
            i9 = 0;
        }
        int i21 = (i20 + i9) * 31;
        if (this.clickListener == null) {
            i11 = 0;
        }
        int i22 = (i21 + i11) * 31;
        if (this.showDivider != null) {
            i10 = this.showDivider.hashCode();
        } else {
            i10 = 0;
        }
        int i23 = (i22 + i10) * 31;
        if (this.numCarouselItemsShown != null) {
            i12 = this.numCarouselItemsShown.hashCode();
        }
        return i23 + i12;
    }

    public String toString() {
        return "ToggleActionRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", subtitle=" + this.subtitle + ", subtitleRes=" + this.subtitleRes + ", label=" + this.label + ", labelRes=" + this.labelRes + ", radio=" + this.radio + ", readOnly=" + this.readOnly + ", checked=" + this.checked + ", enabled=" + this.enabled + ", checkedChangedlistener=" + this.checkedChangedlistener + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
