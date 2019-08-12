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
import com.airbnb.p027n2.components.InlineFormattedIntegerInputRow;
import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.text.NumberFormat;

public class InlineFormattedIntegerInputRowEpoxyModel_ extends InlineFormattedIntegerInputRowEpoxyModel implements GeneratedModel<InlineFormattedIntegerInputRow> {
    private OnModelBoundListener<InlineFormattedIntegerInputRowEpoxyModel_, InlineFormattedIntegerInputRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<InlineFormattedIntegerInputRowEpoxyModel_, InlineFormattedIntegerInputRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, InlineFormattedIntegerInputRow object, int position) {
        if (this.tipClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.tipClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(InlineFormattedIntegerInputRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ onBind(OnModelBoundListener<InlineFormattedIntegerInputRowEpoxyModel_, InlineFormattedIntegerInputRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(InlineFormattedIntegerInputRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ onUnbind(OnModelUnboundListener<InlineFormattedIntegerInputRowEpoxyModel_, InlineFormattedIntegerInputRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ titleRes(int titleRes) {
        onMutation();
        this.titleRes = titleRes;
        return this;
    }

    public int titleRes() {
        return this.titleRes;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ subTitle(CharSequence subTitle) {
        onMutation();
        this.subTitle = subTitle;
        return this;
    }

    public CharSequence subTitle() {
        return this.subTitle;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ subTitleRes(int subTitleRes) {
        onMutation();
        this.subTitleRes = subTitleRes;
        return this;
    }

    public int subTitleRes() {
        return this.subTitleRes;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ hint(CharSequence hint) {
        onMutation();
        this.hint = hint;
        return this;
    }

    public CharSequence hint() {
        return this.hint;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ hintRes(int hintRes) {
        onMutation();
        this.hintRes = hintRes;
        return this;
    }

    public int hintRes() {
        return this.hintRes;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ numberFormat(NumberFormat numberFormat) {
        onMutation();
        this.numberFormat = numberFormat;
        return this;
    }

    public NumberFormat numberFormat() {
        return this.numberFormat;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ inputAmount(Integer inputAmount) {
        onMutation();
        this.inputAmount = inputAmount;
        return this;
    }

    public Integer inputAmount() {
        return this.inputAmount;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ tip(CharSequence tip) {
        onMutation();
        this.tip = tip;
        return this;
    }

    public CharSequence tip() {
        return this.tip;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ tipRes(int tipRes) {
        onMutation();
        this.tipRes = tipRes;
        return this;
    }

    public int tipRes() {
        return this.tipRes;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ tipAmount(Integer tipAmount) {
        onMutation();
        this.tipAmount = tipAmount;
        return this;
    }

    public Integer tipAmount() {
        return this.tipAmount;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ enabled(boolean enabled) {
        onMutation();
        this.enabled = enabled;
        return this;
    }

    public boolean enabled() {
        return this.enabled;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ removeHintOnFocusMode(boolean removeHintOnFocusMode) {
        onMutation();
        this.removeHintOnFocusMode = removeHintOnFocusMode;
        return this;
    }

    public boolean removeHintOnFocusMode() {
        return this.removeHintOnFocusMode;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ amountChangedListener(Listener amountChangedListener) {
        onMutation();
        this.amountChangedListener = amountChangedListener;
        return this;
    }

    public Listener amountChangedListener() {
        return this.amountChangedListener;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ focusChangeListener(OnFocusChangeListener focusChangeListener) {
        onMutation();
        this.focusChangeListener = focusChangeListener;
        return this;
    }

    public OnFocusChangeListener focusChangeListener() {
        return this.focusChangeListener;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ tipClickListener(OnModelClickListener<InlineFormattedIntegerInputRowEpoxyModel_, InlineFormattedIntegerInputRow> tipClickListener) {
        onMutation();
        if (tipClickListener == null) {
            this.tipClickListener = null;
        } else {
            this.tipClickListener = new WrappedEpoxyModelClickListener(this, tipClickListener);
        }
        return this;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ tipClickListener(OnClickListener tipClickListener) {
        onMutation();
        this.tipClickListener = tipClickListener;
        return this;
    }

    public OnClickListener tipClickListener() {
        return this.tipClickListener;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ showError(boolean showError) {
        onMutation();
        this.showError = showError;
        return this;
    }

    public boolean showError() {
        return this.showError;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ updateModelData(boolean updateModelData) {
        onMutation();
        this.updateModelData = updateModelData;
        return this;
    }

    public boolean updateModelData() {
        return this.updateModelData;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public InlineFormattedIntegerInputRowEpoxyModel_ m4870id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public InlineFormattedIntegerInputRowEpoxyModel_ m4875id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public InlineFormattedIntegerInputRowEpoxyModel_ m4871id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public InlineFormattedIntegerInputRowEpoxyModel_ m4872id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public InlineFormattedIntegerInputRowEpoxyModel_ m4874id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public InlineFormattedIntegerInputRowEpoxyModel_ m4873id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_inline_formatted_integer_input_row;
    }

    public InlineFormattedIntegerInputRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.titleRes = 0;
        this.subTitle = null;
        this.subTitleRes = 0;
        this.hint = null;
        this.hintRes = 0;
        this.numberFormat = null;
        this.inputAmount = null;
        this.tip = null;
        this.tipRes = 0;
        this.tipAmount = null;
        this.enabled = false;
        this.removeHintOnFocusMode = false;
        this.amountChangedListener = null;
        this.focusChangeListener = null;
        this.tipClickListener = null;
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
        if (o == this) {
            return true;
        }
        if (!(o instanceof InlineFormattedIntegerInputRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        InlineFormattedIntegerInputRowEpoxyModel_ that = (InlineFormattedIntegerInputRowEpoxyModel_) o;
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
        if (this.numberFormat != null) {
            if (!this.numberFormat.equals(that.numberFormat)) {
                return false;
            }
        } else if (that.numberFormat != null) {
            return false;
        }
        if (this.inputAmount != null) {
            if (!this.inputAmount.equals(that.inputAmount)) {
                return false;
            }
        } else if (that.inputAmount != null) {
            return false;
        }
        if (this.tip != null) {
            if (!this.tip.equals(that.tip)) {
                return false;
            }
        } else if (that.tip != null) {
            return false;
        }
        if (this.tipRes != that.tipRes) {
            return false;
        }
        if (this.tipAmount != null) {
            if (!this.tipAmount.equals(that.tipAmount)) {
                return false;
            }
        } else if (that.tipAmount != null) {
            return false;
        }
        if (this.enabled != that.enabled || this.removeHintOnFocusMode != that.removeHintOnFocusMode) {
            return false;
        }
        if (this.amountChangedListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.amountChangedListener == null)) {
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
        if (this.tipClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.tipClickListener == null) || this.showError != that.showError || this.updateModelData != that.updateModelData) {
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
        int i16 = 1;
        int i17 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i18 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i19 = (((i18 + i2) * 31) + this.titleRes) * 31;
        if (this.subTitle != null) {
            i3 = this.subTitle.hashCode();
        } else {
            i3 = 0;
        }
        int i20 = (((i19 + i3) * 31) + this.subTitleRes) * 31;
        if (this.hint != null) {
            i4 = this.hint.hashCode();
        } else {
            i4 = 0;
        }
        int i21 = (((i20 + i4) * 31) + this.hintRes) * 31;
        if (this.numberFormat != null) {
            i5 = this.numberFormat.hashCode();
        } else {
            i5 = 0;
        }
        int i22 = (i21 + i5) * 31;
        if (this.inputAmount != null) {
            i6 = this.inputAmount.hashCode();
        } else {
            i6 = 0;
        }
        int i23 = (i22 + i6) * 31;
        if (this.tip != null) {
            i7 = this.tip.hashCode();
        } else {
            i7 = 0;
        }
        int i24 = (((i23 + i7) * 31) + this.tipRes) * 31;
        if (this.tipAmount != null) {
            i8 = this.tipAmount.hashCode();
        } else {
            i8 = 0;
        }
        int i25 = (i24 + i8) * 31;
        if (this.enabled) {
            i9 = 1;
        } else {
            i9 = 0;
        }
        int i26 = (i25 + i9) * 31;
        if (this.removeHintOnFocusMode) {
            i10 = 1;
        } else {
            i10 = 0;
        }
        int i27 = (i26 + i10) * 31;
        if (this.amountChangedListener != null) {
            i11 = 1;
        } else {
            i11 = 0;
        }
        int i28 = (i27 + i11) * 31;
        if (this.focusChangeListener != null) {
            i12 = 1;
        } else {
            i12 = 0;
        }
        int i29 = (i28 + i12) * 31;
        if (this.tipClickListener != null) {
            i13 = 1;
        } else {
            i13 = 0;
        }
        int i30 = (i29 + i13) * 31;
        if (this.showError) {
            i14 = 1;
        } else {
            i14 = 0;
        }
        int i31 = (i30 + i14) * 31;
        if (!this.updateModelData) {
            i16 = 0;
        }
        int i32 = (i31 + i16) * 31;
        if (this.showDivider != null) {
            i15 = this.showDivider.hashCode();
        } else {
            i15 = 0;
        }
        int i33 = (i32 + i15) * 31;
        if (this.numCarouselItemsShown != null) {
            i17 = this.numCarouselItemsShown.hashCode();
        }
        return i33 + i17;
    }

    public String toString() {
        return "InlineFormattedIntegerInputRowEpoxyModel_{title=" + this.title + ", titleRes=" + this.titleRes + ", subTitle=" + this.subTitle + ", subTitleRes=" + this.subTitleRes + ", hint=" + this.hint + ", hintRes=" + this.hintRes + ", numberFormat=" + this.numberFormat + ", inputAmount=" + this.inputAmount + ", tip=" + this.tip + ", tipRes=" + this.tipRes + ", tipAmount=" + this.tipAmount + ", enabled=" + this.enabled + ", removeHintOnFocusMode=" + this.removeHintOnFocusMode + ", amountChangedListener=" + this.amountChangedListener + ", focusChangeListener=" + this.focusChangeListener + ", tipClickListener=" + this.tipClickListener + ", showError=" + this.showError + ", updateModelData=" + this.updateModelData + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
