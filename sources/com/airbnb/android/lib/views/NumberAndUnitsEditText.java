package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.airbnb.android.lib.C0880R;

public class NumberAndUnitsEditText extends LinearLayout {
    boolean hideIfZero;
    TextView unitsTagLeft;
    TextView unitsTagRight;
    EditText valueEditText;

    public NumberAndUnitsEditText(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(C0880R.layout.value_input_field, this);
        setupViews(null);
    }

    public NumberAndUnitsEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(C0880R.layout.value_input_field, this);
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0880R.styleable.NumberAndUnitsEditText);
        setupViews(a);
        a.recycle();
    }

    private void setupViews(TypedArray a) {
        this.unitsTagLeft = (TextView) findViewById(C0880R.C0882id.units_tag_left);
        this.unitsTagRight = (TextView) findViewById(C0880R.C0882id.units_tag_right);
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        float unitsSize = ((float) a.getDimensionPixelSize(C0880R.styleable.NumberAndUnitsEditText_units_sign_size, getResources().getDimensionPixelSize(C0880R.dimen.price_edit_text_default_size))) / metrics.scaledDensity;
        this.unitsTagLeft.setTextSize(unitsSize);
        this.unitsTagRight.setTextSize(unitsSize);
        this.valueEditText = (EditText) findViewById(C0880R.C0882id.base_value_edit_text);
        this.valueEditText.setOnFocusChangeListener(NumberAndUnitsEditText$$Lambda$1.lambdaFactory$(this));
        this.valueEditText.setTextSize(((float) a.getDimensionPixelSize(C0880R.styleable.NumberAndUnitsEditText_number_size, getResources().getDimensionPixelSize(C0880R.dimen.price_edit_text_default_size))) / metrics.scaledDensity);
    }

    static /* synthetic */ void lambda$setupViews$0(NumberAndUnitsEditText numberAndUnitsEditText, View v, boolean hasFocus) {
        if (hasFocus) {
            Editable etext = numberAndUnitsEditText.valueEditText.getText();
            Selection.setSelection(etext, etext.length());
        }
    }

    public void setValue(String text) {
        this.valueEditText.setText(text);
    }

    public void setUnitsLeft(String units) {
        this.unitsTagLeft.setText(units);
    }

    public void setUnitsRight(String units) {
        this.unitsTagRight.setText(units);
    }

    public void setInputEnabled(boolean enabled) {
        this.valueEditText.setEnabled(enabled);
        this.valueEditText.setFocusable(enabled);
    }

    /* access modifiers changed from: protected */
    public void resetUnitViews() {
        this.unitsTagLeft.setVisibility(8);
        this.unitsTagRight.setVisibility(8);
        this.valueEditText.setFilters(new InputFilter[0]);
    }

    public boolean isValueEmpty() {
        return TextUtils.isEmpty(this.valueEditText.getText().toString());
    }

    public void hidePriceIfZero(boolean hide) {
        this.hideIfZero = hide;
    }

    public void setValueTextColor(int color) {
        this.valueEditText.setTextColor(color);
    }

    public void addValueTextChangedListener(TextWatcher textWatcher) {
        this.valueEditText.addTextChangedListener(textWatcher);
    }

    public void removeValueTextChangedListener(TextWatcher textWatcher) {
        this.valueEditText.removeTextChangedListener(textWatcher);
    }

    public void setValueTextWidthToMatchParent() {
        ((LinearLayout) findViewById(C0880R.C0882id.value_edit_text_root)).getLayoutParams().width = -1;
        ((LayoutParams) this.valueEditText.getLayoutParams()).weight = 1.0f;
    }
}
