package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import com.airbnb.p027n2.components.InlineInputRow;
import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class InlineInputRowEpoxyModel extends AirEpoxyModel<InlineInputRow> {
    private static final boolean DEFAULT_ENABLED = true;
    private static final int DEFAULT_INPUT_TYPE = 1;
    private static final int DEFAULT_MAX_INPUT_LINES = 1;
    private static final int DEFAULT_MAX_TIP_LINES = Integer.MAX_VALUE;
    boolean autoHideTipOnInputChange;
    OnClickListener clickListener;
    boolean enabled = DEFAULT_ENABLED;
    CharSequence error;
    int errorRes;
    OnFocusChangeListener focusChangeListener;
    CharSequence hint;
    int hintRes;
    CharSequence input;
    OnInputChangedListener inputChangedListener;
    int inputMaxLines = 1;
    int inputRes;
    int inputType = 1;
    OnInputChangedListener inputValueChangedListener;
    private final OnInputChangedListener listenerWrapper = new OnInputChangedListener() {
        public void onInputChanged(String value) {
            InlineInputRowEpoxyModel.this.showError = false;
            InlineInputRowEpoxyModel.this.input = value;
            if (InlineInputRowEpoxyModel.this.inputChangedListener != null) {
                InlineInputRowEpoxyModel.this.inputChangedListener.onInputChanged(value);
            }
        }
    };
    boolean removeHintOnFocusMode;
    boolean showError;
    CharSequence subTitle;
    int subTitleRes;
    CharSequence tip;
    int tipMaxLine = Integer.MAX_VALUE;
    int tipRes;
    String tipValue;
    CharSequence title;
    int titleRes;
    boolean updateModelData = DEFAULT_ENABLED;

    public void bind(InlineInputRow view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence actualTitle = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence actualSubTitle = this.subTitleRes != 0 ? context.getString(this.subTitleRes) : this.subTitle;
        CharSequence actualHint = this.hintRes != 0 ? context.getString(this.hintRes) : this.hint;
        CharSequence actualInput = this.inputRes != 0 ? context.getString(this.inputRes) : this.input;
        CharSequence actualTip = this.tipRes != 0 ? context.getString(this.tipRes) : this.tip;
        CharSequence actualError = this.errorRes != 0 ? context.getString(this.errorRes) : this.error;
        view.setTitle(actualTitle);
        view.setSubTitleText(actualSubTitle);
        view.setHint(actualHint);
        view.setError(actualError);
        view.setInputType(this.inputType);
        view.setMaxLines(this.inputMaxLines);
        view.setTip(actualTip);
        view.setTipMaxLine(this.tipMaxLine);
        view.setTipValue(this.tipValue);
        view.setOnFocusChangeListener(this.focusChangeListener);
        view.setRemoveHintOnFocus(this.removeHintOnFocusMode);
        view.setAutoHideTipOnInputChange(this.autoHideTipOnInputChange);
        view.setEnabled(this.enabled);
        view.setOnClickListener(this.clickListener);
        view.showError(this.showError);
        view.setOnInputChangedListener(null);
        view.setInputText(actualInput);
        view.setOnInputChangedListener(this.updateModelData ? this.listenerWrapper : this.inputValueChangedListener);
    }

    public void unbind(InlineInputRow view) {
        super.unbind(view);
        view.setOnFocusChangeListener(null);
        view.setOnInputChangedListener(null);
        view.setOnClickListener(null);
    }

    public AirEpoxyModel<InlineInputRow> reset() {
        this.enabled = DEFAULT_ENABLED;
        this.inputType = 1;
        this.inputMaxLines = 1;
        this.tipMaxLine = Integer.MAX_VALUE;
        return super.reset();
    }
}
