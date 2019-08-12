package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import com.airbnb.android.lib.C0880R;

public final class GroupedCounterFloats extends GroupedCounter {
    private int DENOMINATOR;

    public GroupedCounterFloats(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public GroupedCounterFloats(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GroupedCounterFloats(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.DENOMINATOR = (int) (1.0f / 0.5f);
        this.maxValue *= this.DENOMINATOR;
        this.minValue *= this.DENOMINATOR;
        this.selectedValue *= this.DENOMINATOR;
        this.minusButton.setOnClickListener(GroupedCounterFloats$$Lambda$1.lambdaFactory$(this));
        this.plusButton.setOnClickListener(GroupedCounterFloats$$Lambda$2.lambdaFactory$(this));
        updateUI();
    }

    /* access modifiers changed from: protected */
    public void updateUI() {
        String valueOf;
        boolean z;
        boolean z2 = true;
        float bathroomCount = ((float) this.selectedValue) / ((float) this.DENOMINATOR);
        String valueToUse = String.valueOf(bathroomCount);
        if (this.alwaysShowPlus || (this.showPlusOnMax && this.selectedValue == this.maxValue)) {
            valueToUse = getContext().getString(C0880R.string.over_maximum_search_filter_price, new Object[]{valueToUse});
        }
        TextView textView = this.text;
        if (this.quantityStringResId > 0) {
            valueOf = getContext().getResources().getQuantityString(this.quantityStringResId, Math.round(bathroomCount), new Object[]{valueToUse});
        } else {
            valueOf = String.valueOf(valueToUse);
        }
        textView.setText(valueOf);
        ImageView imageView = this.minusButton;
        if (this.selectedValue > this.minValue) {
            z = true;
        } else {
            z = false;
        }
        imageView.setEnabled(z);
        ImageView imageView2 = this.plusButton;
        if (this.selectedValue >= this.maxValue) {
            z2 = false;
        }
        imageView2.setEnabled(z2);
        if (this.mValueChangeListener != null) {
            this.mValueChangeListener.onValueChange(this, this.selectedValue);
        }
    }

    @Deprecated
    public void setSelectedValue(int value) {
        throw new UnsupportedOperationException("this not supported in grouped counter floats, use setSelectedValueFloats()");
    }

    public void setSelectedValueFloat(float value) {
        super.setSelectedValue((int) (((float) this.DENOMINATOR) * value));
    }

    /* access modifiers changed from: private */
    public void superSetSelectedValue(int value) {
        super.setSelectedValue(value);
    }

    @Deprecated
    public int getSelectedValue() {
        throw new UnsupportedOperationException("this not supported in grouped counter floats, use getSelectedValueFloats()");
    }

    public float getSelectedValueFloat() {
        return ((float) this.selectedValue) / ((float) this.DENOMINATOR);
    }

    public String getSelectedValueString() {
        return String.valueOf(getSelectedValueFloat());
    }
}
