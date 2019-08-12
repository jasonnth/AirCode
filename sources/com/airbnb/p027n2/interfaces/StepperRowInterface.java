package com.airbnb.p027n2.interfaces;

import android.view.View;

/* renamed from: com.airbnb.n2.interfaces.StepperRowInterface */
public interface StepperRowInterface {

    /* renamed from: com.airbnb.n2.interfaces.StepperRowInterface$OnValueChangedListener */
    public interface OnValueChangedListener {
        void onValueChanged(int i, int i2);
    }

    int getValue();

    View getView();

    void setMinValue(int i);

    void setText(int i);

    void setValue(int i);

    void setValueChangedListener(OnValueChangedListener onValueChangedListener);
}
