package com.airbnb.android.core.viewcomponents.models;

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
import com.airbnb.p027n2.components.InlineInputRow;
import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class InlineInputRowEpoxyModel_ extends InlineInputRowEpoxyModel implements GeneratedModel<InlineInputRow> {
    private OnModelBoundListener<InlineInputRowEpoxyModel_, InlineInputRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InlineInputRowEpoxyModel_, InlineInputRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, InlineInputRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(InlineInputRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InlineInputRowEpoxyModel_ onBind(OnModelBoundListener<InlineInputRowEpoxyModel_, InlineInputRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(InlineInputRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InlineInputRowEpoxyModel_ onUnbind(OnModelUnboundListener<InlineInputRowEpoxyModel_, InlineInputRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InlineInputRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public InlineInputRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public InlineInputRowEpoxyModel_ subTitle(CharSequence subTitle) {
        onMutation();
        this.subTitle = subTitle;
        return this;
    }

    public CharSequence subTitle() {
        return this.subTitle;
    }

    public InlineInputRowEpoxyModel_ subTitleRes(int subTitleRes) {
        onMutation();
        this.subTitleRes = subTitleRes;
        return this;
    }

    public int subTitleRes() {
        return this.subTitleRes;
    }

    public InlineInputRowEpoxyModel_ hint(CharSequence hint) {
        onMutation();
        this.hint = hint;
        return this;
    }

    public CharSequence hint() {
        return this.hint;
    }

    public InlineInputRowEpoxyModel_ hintRes(int hintRes) {
        onMutation();
        this.hintRes = hintRes;
        return this;
    }

    public int hintRes() {
        return this.hintRes;
    }

    public InlineInputRowEpoxyModel_ input(CharSequence input) {
        onMutation();
        this.input = input;
        return this;
    }

    public CharSequence input() {
        return this.input;
    }

    public InlineInputRowEpoxyModel_ inputRes(int inputRes) {
        onMutation();
        this.inputRes = inputRes;
        return this;
    }

    public int inputRes() {
        return this.inputRes;
    }

    public InlineInputRowEpoxyModel_ inputType(int inputType) {
        onMutation();
        this.inputType = inputType;
        return this;
    }

    public int inputType() {
        return this.inputType;
    }

    public InlineInputRowEpoxyModel_ inputMaxLines(int inputMaxLines) {
        onMutation();
        this.inputMaxLines = inputMaxLines;
        return this;
    }

    public int inputMaxLines() {
        return this.inputMaxLines;
    }

    public InlineInputRowEpoxyModel_ tip(CharSequence tip) {
        onMutation();
        this.tip = tip;
        return this;
    }

    public CharSequence tip() {
        return this.tip;
    }

    public InlineInputRowEpoxyModel_ tipRes(int tipRes) {
        onMutation();
        this.tipRes = tipRes;
        return this;
    }

    public int tipRes() {
        return this.tipRes;
    }

    public InlineInputRowEpoxyModel_ tipMaxLine(int tipMaxLine) {
        onMutation();
        this.tipMaxLine = tipMaxLine;
        return this;
    }

    public int tipMaxLine() {
        return this.tipMaxLine;
    }

    public InlineInputRowEpoxyModel_ tipValue(String tipValue) {
        onMutation();
        this.tipValue = tipValue;
        return this;
    }

    public String tipValue() {
        return this.tipValue;
    }

    public InlineInputRowEpoxyModel_ removeHintOnFocusMode(boolean removeHintOnFocusMode) {
        onMutation();
        this.removeHintOnFocusMode = removeHintOnFocusMode;
        return this;
    }

    public boolean removeHintOnFocusMode() {
        return this.removeHintOnFocusMode;
    }

    public InlineInputRowEpoxyModel_ autoHideTipOnInputChange(boolean autoHideTipOnInputChange) {
        onMutation();
        this.autoHideTipOnInputChange = autoHideTipOnInputChange;
        return this;
    }

    public boolean autoHideTipOnInputChange() {
        return this.autoHideTipOnInputChange;
    }

    public InlineInputRowEpoxyModel_ inputChangedListener(OnInputChangedListener inputChangedListener) {
        onMutation();
        this.inputChangedListener = inputChangedListener;
        return this;
    }

    public OnInputChangedListener inputChangedListener() {
        return this.inputChangedListener;
    }

    public InlineInputRowEpoxyModel_ enabled(boolean enabled) {
        onMutation();
        this.enabled = enabled;
        return this;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public InlineInputRowEpoxyModel_ clickListener(OnModelClickListener<InlineInputRowEpoxyModel_, InlineInputRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public InlineInputRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public InlineInputRowEpoxyModel_ focusChangeListener(OnFocusChangeListener focusChangeListener) {
        onMutation();
        this.focusChangeListener = focusChangeListener;
        return this;
    }

    public OnFocusChangeListener focusChangeListener() {
        return this.focusChangeListener;
    }

    public InlineInputRowEpoxyModel_ inputValueChangedListener(OnInputChangedListener inputValueChangedListener) {
        onMutation();
        this.inputValueChangedListener = inputValueChangedListener;
        return this;
    }

    public OnInputChangedListener inputValueChangedListener() {
        return this.inputValueChangedListener;
    }

    public InlineInputRowEpoxyModel_ errorRes(int errorRes) {
        onMutation();
        this.errorRes = errorRes;
        return this;
    }

    public int errorRes() {
        return this.errorRes;
    }

    public InlineInputRowEpoxyModel_ error(CharSequence error) {
        onMutation();
        this.error = error;
        return this;
    }

    public CharSequence error() {
        return this.error;
    }

    public InlineInputRowEpoxyModel_ showError(boolean showError) {
        onMutation();
        this.showError = showError;
        return this;
    }

    public boolean showError() {
        return this.showError;
    }

    public InlineInputRowEpoxyModel_ updateModelData(boolean updateModelData) {
        onMutation();
        this.updateModelData = updateModelData;
        return this;
    }

    public boolean updateModelData() {
        return this.updateModelData;
    }

    public InlineInputRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InlineInputRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InlineInputRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InlineInputRowEpoxyModel_ m4882id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InlineInputRowEpoxyModel_ m4887id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InlineInputRowEpoxyModel_ m4883id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InlineInputRowEpoxyModel_ m4884id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InlineInputRowEpoxyModel_ m4886id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InlineInputRowEpoxyModel_ m4885id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InlineInputRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InlineInputRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InlineInputRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InlineInputRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InlineInputRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_inline_input_row;
    }

    public InlineInputRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.subTitle = null;
        this.subTitleRes = 0;
        this.hint = null;
        this.hintRes = 0;
        this.input = null;
        this.inputRes = 0;
        this.inputType = 0;
        this.inputMaxLines = 0;
        this.tip = null;
        this.tipRes = 0;
        this.tipMaxLine = 0;
        this.tipValue = null;
        this.removeHintOnFocusMode = false;
        this.autoHideTipOnInputChange = false;
        this.inputChangedListener = null;
        this.enabled = false;
        this.clickListener = null;
        this.focusChangeListener = null;
        this.inputValueChangedListener = null;
        this.errorRes = 0;
        this.error = null;
        this.showError = false;
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
        boolean z5;
        if (o == this) {
            return true;
        }
        if (!(o instanceof InlineInputRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InlineInputRowEpoxyModel_ that = (InlineInputRowEpoxyModel_) o;
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
        if (this.subTitle != null) {
            if (!this.subTitle.equals(that.subTitle)) {
                return false;
            }
        } else if (that.subTitle != null) {
            return false;
        }
        if (this.subTitleRes != that.subTitleRes) {
            return false;
        }
        if (this.hint != null) {
            if (!this.hint.equals(that.hint)) {
                return false;
            }
        } else if (that.hint != null) {
            return false;
        }
        if (this.hintRes != that.hintRes) {
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
        if (this.tip != null) {
            if (!this.tip.equals(that.tip)) {
                return false;
            }
        } else if (that.tip != null) {
            return false;
        }
        if (this.tipRes != that.tipRes || this.tipMaxLine != that.tipMaxLine) {
            return false;
        }
        if (this.tipValue != null) {
            if (!this.tipValue.equals(that.tipValue)) {
                return false;
            }
        } else if (that.tipValue != null) {
            return false;
        }
        if (this.removeHintOnFocusMode != that.removeHintOnFocusMode || this.autoHideTipOnInputChange != that.autoHideTipOnInputChange) {
            return false;
        }
        if (this.inputChangedListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.inputChangedListener == null) || this.enabled != that.enabled) {
            return false;
        }
        if (this.clickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.clickListener == null)) {
            return false;
        }
        if (this.focusChangeListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.focusChangeListener == null)) {
            return false;
        }
        if (this.inputValueChangedListener == null) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z5 != (that.inputValueChangedListener == null) || this.errorRes != that.errorRes) {
            return false;
        }
        if (this.error != null) {
            if (!this.error.equals(that.error)) {
                return false;
            }
        } else if (that.error != null) {
            return false;
        }
        if (this.showError != that.showError || this.updateModelData != that.updateModelData) {
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
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18 = 1;
        int i19 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i20 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i21 = (((i20 + i2) * 31) + this.titleRes) * 31;
        if (this.subTitle != null) {
            i3 = this.subTitle.hashCode();
        } else {
            i3 = 0;
        }
        int i22 = (((i21 + i3) * 31) + this.subTitleRes) * 31;
        if (this.hint != null) {
            i4 = this.hint.hashCode();
        } else {
            i4 = 0;
        }
        int i23 = (((i22 + i4) * 31) + this.hintRes) * 31;
        if (this.input != null) {
            i5 = this.input.hashCode();
        } else {
            i5 = 0;
        }
        int i24 = (((((((i23 + i5) * 31) + this.inputRes) * 31) + this.inputType) * 31) + this.inputMaxLines) * 31;
        if (this.tip != null) {
            i6 = this.tip.hashCode();
        } else {
            i6 = 0;
        }
        int i25 = (((((i24 + i6) * 31) + this.tipRes) * 31) + this.tipMaxLine) * 31;
        if (this.tipValue != null) {
            i7 = this.tipValue.hashCode();
        } else {
            i7 = 0;
        }
        int i26 = (i25 + i7) * 31;
        if (this.removeHintOnFocusMode) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i27 = (i26 + i8) * 31;
        if (this.autoHideTipOnInputChange) {
            i9 = 1;
        } else {
            i9 = 0;
        }
        int i28 = (i27 + i9) * 31;
        if (this.inputChangedListener != null) {
            i10 = 1;
        } else {
            i10 = 0;
        }
        int i29 = (i28 + i10) * 31;
        if (this.enabled) {
            i11 = 1;
        } else {
            i11 = 0;
        }
        int i30 = (i29 + i11) * 31;
        if (this.clickListener != null) {
            i12 = 1;
        } else {
            i12 = 0;
        }
        int i31 = (i30 + i12) * 31;
        if (this.focusChangeListener != null) {
            i13 = 1;
        } else {
            i13 = 0;
        }
        int i32 = (i31 + i13) * 31;
        if (this.inputValueChangedListener != null) {
            i14 = 1;
        } else {
            i14 = 0;
        }
        int i33 = (((i32 + i14) * 31) + this.errorRes) * 31;
        if (this.error != null) {
            i15 = this.error.hashCode();
        } else {
            i15 = 0;
        }
        int i34 = (i33 + i15) * 31;
        if (this.showError) {
            i16 = 1;
        } else {
            i16 = 0;
        }
        int i35 = (i34 + i16) * 31;
        if (!this.updateModelData) {
            i18 = 0;
        }
        int i36 = (i35 + i18) * 31;
        if (this.showDivider != null) {
            i17 = this.showDivider.hashCode();
        } else {
            i17 = 0;
        }
        int i37 = (i36 + i17) * 31;
        if (this.numCarouselItemsShown != null) {
            i19 = this.numCarouselItemsShown.hashCode();
        }
        return i37 + i19;
    }

    public String toString() {
        return "InlineInputRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", subTitle=" + this.subTitle + ", subTitleRes=" + this.subTitleRes + ", hint=" + this.hint + ", hintRes=" + this.hintRes + ", input=" + this.input + ", inputRes=" + this.inputRes + ", inputType=" + this.inputType + ", inputMaxLines=" + this.inputMaxLines + ", tip=" + this.tip + ", tipRes=" + this.tipRes + ", tipMaxLine=" + this.tipMaxLine + ", tipValue=" + this.tipValue + ", removeHintOnFocusMode=" + this.removeHintOnFocusMode + ", autoHideTipOnInputChange=" + this.autoHideTipOnInputChange + ", inputChangedListener=" + this.inputChangedListener + ", enabled=" + this.enabled + ", clickListener=" + this.clickListener + ", focusChangeListener=" + this.focusChangeListener + ", inputValueChangedListener=" + this.inputValueChangedListener + ", errorRes=" + this.errorRes + ", error=" + this.error + ", showError=" + this.showError + ", updateModelData=" + this.updateModelData + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
