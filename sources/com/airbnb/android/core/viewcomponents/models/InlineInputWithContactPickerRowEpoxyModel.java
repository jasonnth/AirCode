package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.InlineInputWithContactPickerRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class InlineInputWithContactPickerRowEpoxyModel extends AirEpoxyModel<InlineInputWithContactPickerRow> {
    private static final boolean DEFAULT_ENABLED = true;
    private static final int DEFAULT_INPUT_TYPE = 1;
    private static final int DEFAULT_MAX_INPUT_LINES = 1;
    OnClickListener addButtonClickListener;
    OnClickListener clickListener;
    boolean enabled = DEFAULT_ENABLED;
    OnFocusChangeListener focusChangeListener;
    CharSequence input;
    int inputMaxLines = 1;
    int inputRes;
    int inputType = 1;
    TextWatcher textChangedListener;
    private final SimpleTextWatcher textWatcher = new SimpleTextWatcher() {
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            InlineInputWithContactPickerRowEpoxyModel.this.input = s.toString();
            if (InlineInputWithContactPickerRowEpoxyModel.this.textChangedListener != null) {
                InlineInputWithContactPickerRowEpoxyModel.this.textChangedListener.onTextChanged(s, start, before, count);
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (InlineInputWithContactPickerRowEpoxyModel.this.textChangedListener != null) {
                InlineInputWithContactPickerRowEpoxyModel.this.textChangedListener.beforeTextChanged(s, start, count, after);
            }
        }

        public void afterTextChanged(Editable s) {
            if (InlineInputWithContactPickerRowEpoxyModel.this.textChangedListener != null) {
                InlineInputWithContactPickerRowEpoxyModel.this.textChangedListener.afterTextChanged(s);
            }
        }
    };
    CharSequence title;
    int titleRes;
    boolean updateModelData = DEFAULT_ENABLED;

    public void bind(InlineInputWithContactPickerRow view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence actualTitle = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence actualInput = this.inputRes != 0 ? context.getString(this.inputRes) : this.input;
        view.setTitle(actualTitle);
        view.setInputText(actualInput);
        view.setInputType(this.inputType);
        view.setEnabled(this.enabled);
        view.setOnFocusChangeListener(this.focusChangeListener);
        view.setOnClickListener(this.clickListener);
        view.setAddButtonClicklistener(this.addButtonClickListener);
        if (this.updateModelData) {
            view.setOnTextChangedListener(this.textWatcher);
        } else {
            view.setOnTextChangedListener(this.textChangedListener);
        }
    }

    public void unbind(InlineInputWithContactPickerRow view) {
        super.unbind(view);
        view.setOnFocusChangeListener(null);
        view.setOnClickListener(null);
        view.setOnTextChangedListener(null);
        view.setAddButtonClicklistener(null);
    }

    public AirEpoxyModel<InlineInputWithContactPickerRow> reset() {
        this.enabled = DEFAULT_ENABLED;
        this.inputType = 1;
        this.inputMaxLines = 1;
        return super.reset();
    }
}
