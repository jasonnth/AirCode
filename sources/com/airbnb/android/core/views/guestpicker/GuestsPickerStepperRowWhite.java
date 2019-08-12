package com.airbnb.android.core.views.guestpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import butterknife.BindView;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.BaseDividerComponent;
import com.airbnb.p027n2.interfaces.StepperRowInterface;
import com.airbnb.p027n2.interfaces.StepperRowInterface.OnValueChangedListener;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;

public final class GuestsPickerStepperRowWhite extends BaseDividerComponent implements StepperRowInterface {
    private OnValueChangedListener changeListener;
    @BindView
    AirTextView descriptionView;
    private int maxValue = Integer.MAX_VALUE;
    private int minValue = 0;
    @BindView
    ImageButton minusButton;
    private int pluralsRes;
    @BindView
    ImageButton plusButton;
    @BindView
    AirTextView titleView;
    private int value;
    @BindView
    AirTextView valueView;

    public GuestsPickerStepperRowWhite(Context context) {
        super(context);
    }

    public GuestsPickerStepperRowWhite(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GuestsPickerStepperRowWhite(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public int layout() {
        return C0716R.layout.guests_picker_stepper_row;
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        setupAttributes(attrs);
        this.plusButton.setOnClickListener(GuestsPickerStepperRowWhite$$Lambda$1.lambdaFactory$(this));
        this.minusButton.setOnClickListener(GuestsPickerStepperRowWhite$$Lambda$2.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void handleValueChange(int newValue) {
        boolean changed = this.value != newValue;
        int oldValue = this.value;
        this.value = newValue;
        if (changed && this.changeListener != null) {
            this.changeListener.onValueChanged(oldValue, this.value);
        }
        enableOrDisableButtons();
        updateValueText();
    }

    private void setupAttributes(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, C0716R.styleable.GuestsPickerStepperRowWhite);
            int defaultStringRes = ta.getResourceId(C0716R.styleable.GuestsPickerStepperRowWhite_textRes, 0);
            int pluralStringRes = ta.getResourceId(C0716R.styleable.GuestsPickerStepperRowWhite_pluralsValueRes, 0);
            String description = ta.getString(C0716R.styleable.GuestsPickerStepperRowWhite_descriptionText);
            int minValue2 = ta.getInteger(C0716R.styleable.GuestsPickerStepperRowWhite_minValue, Integer.MAX_VALUE);
            int maxValue2 = ta.getInteger(C0716R.styleable.GuestsPickerStepperRowWhite_maxValue, Integer.MIN_VALUE);
            if (defaultStringRes != 0) {
                setText(defaultStringRes);
            }
            if (pluralStringRes != 0) {
                setValueResource(pluralStringRes);
            } else {
                updateValueText();
            }
            if (!TextUtils.isEmpty(description)) {
                setDescription((CharSequence) description);
            }
            if (minValue2 != Integer.MAX_VALUE) {
                setMinValue(minValue2);
            }
            if (maxValue2 != Integer.MIN_VALUE) {
                setMaxValue(maxValue2);
            }
            ta.recycle();
        }
    }

    public void setText(int defaultRes) {
        setText((CharSequence) getResources().getString(defaultRes));
    }

    public void setText(CharSequence defaultText) {
        this.titleView.setText(defaultText);
    }

    public void setValueResource(int pluralsRes2) {
        this.pluralsRes = pluralsRes2;
        updateValueText();
    }

    public void setDescription(CharSequence text) {
        ViewLibUtils.setVisibleIf(this.descriptionView, !TextUtils.isEmpty(text));
        this.descriptionView.setText(text);
    }

    public void setDescription(int resId) {
        ViewLibUtils.setVisibleIf(this.descriptionView, resId != 0);
        if (resId == 0) {
            this.descriptionView.setText(null);
        } else {
            this.descriptionView.setText(resId);
        }
    }

    public void setMinValue(int minValue2) {
        this.minValue = minValue2;
        if (this.value < minValue2) {
            handleValueChange(minValue2);
        } else {
            enableOrDisableButtons();
        }
    }

    public void setMaxValue(int maxValue2) {
        this.maxValue = maxValue2;
        if (this.value > maxValue2) {
            handleValueChange(maxValue2);
        } else {
            enableOrDisableButtons();
        }
    }

    public void setValue(int value2) {
        if (value2 >= this.minValue && value2 <= this.maxValue) {
            handleValueChange(value2);
        }
    }

    public int getValue() {
        return this.value;
    }

    public void setValueChangedListener(OnValueChangedListener changeListener2) {
        this.changeListener = changeListener2;
    }

    public View getView() {
        return this;
    }

    private void updateValueText() {
        if (this.pluralsRes == 0) {
            this.valueView.setText(String.valueOf(this.value));
            return;
        }
        this.valueView.setText(getResources().getQuantityString(this.pluralsRes, this.value, new Object[]{Integer.valueOf(this.value)}));
    }

    private void enableOrDisableButtons() {
        boolean z;
        boolean z2 = true;
        ImageButton imageButton = this.plusButton;
        if (this.value < this.maxValue) {
            z = true;
        } else {
            z = false;
        }
        imageButton.setEnabled(z);
        ImageButton imageButton2 = this.minusButton;
        if (this.value <= this.minValue) {
            z2 = false;
        }
        imageButton2.setEnabled(z2);
    }
}
