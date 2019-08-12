package com.facebook.react.views.slider;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class ReactSlider extends SeekBar {
    private static int DEFAULT_TOTAL_STEPS = 128;
    private double mMaxValue = 0.0d;
    private double mMinValue = 0.0d;
    private double mStep = 0.0d;
    private double mStepCalculated = 0.0d;
    private double mValue = 0.0d;

    public ReactSlider(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
    }

    /* access modifiers changed from: 0000 */
    public void setMaxValue(double max) {
        this.mMaxValue = max;
        updateAll();
    }

    /* access modifiers changed from: 0000 */
    public void setMinValue(double min) {
        this.mMinValue = min;
        updateAll();
    }

    /* access modifiers changed from: 0000 */
    public void setValue(double value) {
        this.mValue = value;
        updateValue();
    }

    /* access modifiers changed from: 0000 */
    public void setStep(double step) {
        this.mStep = step;
        updateAll();
    }

    public double toRealProgress(int seekBarProgress) {
        if (seekBarProgress == getMax()) {
            return this.mMaxValue;
        }
        return (((double) seekBarProgress) * getStepValue()) + this.mMinValue;
    }

    private void updateAll() {
        if (this.mStep == 0.0d) {
            this.mStepCalculated = (this.mMaxValue - this.mMinValue) / ((double) DEFAULT_TOTAL_STEPS);
        }
        setMax(getTotalSteps());
        updateValue();
    }

    private void updateValue() {
        setProgress((int) Math.round(((this.mValue - this.mMinValue) / (this.mMaxValue - this.mMinValue)) * ((double) getTotalSteps())));
    }

    private int getTotalSteps() {
        return (int) Math.ceil((this.mMaxValue - this.mMinValue) / getStepValue());
    }

    private double getStepValue() {
        return this.mStep > 0.0d ? this.mStep : this.mStepCalculated;
    }
}
