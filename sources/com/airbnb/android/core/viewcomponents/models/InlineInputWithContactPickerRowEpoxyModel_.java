package com.airbnb.android.core.viewcomponents.models;

import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.InlineInputWithContactPickerRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class InlineInputWithContactPickerRowEpoxyModel_ extends InlineInputWithContactPickerRowEpoxyModel implements GeneratedModel<InlineInputWithContactPickerRow> {
    private OnModelBoundListener<InlineInputWithContactPickerRowEpoxyModel_, InlineInputWithContactPickerRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InlineInputWithContactPickerRowEpoxyModel_, InlineInputWithContactPickerRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, InlineInputWithContactPickerRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
        if (this.addButtonClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.addButtonClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(InlineInputWithContactPickerRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InlineInputWithContactPickerRowEpoxyModel_ onBind(OnModelBoundListener<InlineInputWithContactPickerRowEpoxyModel_, InlineInputWithContactPickerRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(InlineInputWithContactPickerRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InlineInputWithContactPickerRowEpoxyModel_ onUnbind(OnModelUnboundListener<InlineInputWithContactPickerRowEpoxyModel_, InlineInputWithContactPickerRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ input(CharSequence input) {
        onMutation();
        this.input = input;
        return this;
    }

    public CharSequence input() {
        return this.input;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ inputRes(int inputRes) {
        onMutation();
        this.inputRes = inputRes;
        return this;
    }

    public int inputRes() {
        return this.inputRes;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ inputType(int inputType) {
        onMutation();
        this.inputType = inputType;
        return this;
    }

    public int inputType() {
        return this.inputType;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ inputMaxLines(int inputMaxLines) {
        onMutation();
        this.inputMaxLines = inputMaxLines;
        return this;
    }

    public int inputMaxLines() {
        return this.inputMaxLines;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ textChangedListener(TextWatcher textChangedListener) {
        onMutation();
        this.textChangedListener = textChangedListener;
        return this;
    }

    public TextWatcher textChangedListener() {
        return this.textChangedListener;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ enabled(boolean enabled) {
        onMutation();
        this.enabled = enabled;
        return this;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ clickListener(OnModelClickListener<InlineInputWithContactPickerRowEpoxyModel_, InlineInputWithContactPickerRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ focusChangeListener(OnFocusChangeListener focusChangeListener) {
        onMutation();
        this.focusChangeListener = focusChangeListener;
        return this;
    }

    public OnFocusChangeListener focusChangeListener() {
        return this.focusChangeListener;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ addButtonClickListener(OnModelClickListener<InlineInputWithContactPickerRowEpoxyModel_, InlineInputWithContactPickerRow> addButtonClickListener) {
        onMutation();
        if (addButtonClickListener == null) {
            this.addButtonClickListener = null;
        } else {
            this.addButtonClickListener = new WrappedEpoxyModelClickListener(this, addButtonClickListener);
        }
        return this;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ addButtonClickListener(OnClickListener addButtonClickListener) {
        onMutation();
        this.addButtonClickListener = addButtonClickListener;
        return this;
    }

    public OnClickListener addButtonClickListener() {
        return this.addButtonClickListener;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ updateModelData(boolean updateModelData) {
        onMutation();
        this.updateModelData = updateModelData;
        return this;
    }

    public boolean updateModelData() {
        return this.updateModelData;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InlineInputWithContactPickerRowEpoxyModel_ m4894id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InlineInputWithContactPickerRowEpoxyModel_ m4899id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InlineInputWithContactPickerRowEpoxyModel_ m4895id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InlineInputWithContactPickerRowEpoxyModel_ m4896id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InlineInputWithContactPickerRowEpoxyModel_ m4898id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InlineInputWithContactPickerRowEpoxyModel_ m4897id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_inline_input_with_contact_picker_row;
    }

    public InlineInputWithContactPickerRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.input = null;
        this.inputRes = 0;
        this.inputType = 0;
        this.inputMaxLines = 0;
        this.textChangedListener = null;
        this.enabled = false;
        this.clickListener = null;
        this.focusChangeListener = null;
        this.addButtonClickListener = null;
        this.updateModelData = false;
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
        if (!(o instanceof InlineInputWithContactPickerRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InlineInputWithContactPickerRowEpoxyModel_ that = (InlineInputWithContactPickerRowEpoxyModel_) o;
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
        if (this.input != null) {
            if (!this.input.equals(that.input)) {
                return false;
            }
        } else if (that.input != null) {
            return false;
        }
        if (this.inputRes != that.inputRes || this.inputType != that.inputType || this.inputMaxLines != that.inputMaxLines) {
            return false;
        }
        if ((this.textChangedListener == null) != (that.textChangedListener == null) || this.enabled != that.enabled) {
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
        if (this.focusChangeListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.focusChangeListener == null)) {
            return false;
        }
        if (this.addButtonClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.addButtonClickListener == null) || this.updateModelData != that.updateModelData) {
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
        int i10 = 1;
        int i11 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i12 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i13 = (((i12 + i2) * 31) + this.titleRes) * 31;
        if (this.input != null) {
            i3 = this.input.hashCode();
        } else {
            i3 = 0;
        }
        int i14 = (((((((i13 + i3) * 31) + this.inputRes) * 31) + this.inputType) * 31) + this.inputMaxLines) * 31;
        if (this.textChangedListener != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i15 = (i14 + i4) * 31;
        if (this.enabled) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i16 = (i15 + i5) * 31;
        if (this.clickListener != null) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i17 = (i16 + i6) * 31;
        if (this.focusChangeListener != null) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i18 = (i17 + i7) * 31;
        if (this.addButtonClickListener != null) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i19 = (i18 + i8) * 31;
        if (!this.updateModelData) {
            i10 = 0;
        }
        int i20 = (i19 + i10) * 31;
        if (this.showDivider != null) {
            i9 = this.showDivider.hashCode();
        } else {
            i9 = 0;
        }
        int i21 = (i20 + i9) * 31;
        if (this.numCarouselItemsShown != null) {
            i11 = this.numCarouselItemsShown.hashCode();
        }
        return i21 + i11;
    }

    public String toString() {
        return "InlineInputWithContactPickerRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", input=" + this.input + ", inputRes=" + this.inputRes + ", inputType=" + this.inputType + ", inputMaxLines=" + this.inputMaxLines + ", textChangedListener=" + this.textChangedListener + ", enabled=" + this.enabled + ", clickListener=" + this.clickListener + ", focusChangeListener=" + this.focusChangeListener + ", addButtonClickListener=" + this.addButtonClickListener + ", updateModelData=" + this.updateModelData + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
