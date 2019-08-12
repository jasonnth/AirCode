package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.BundleBuilder;

public abstract class BaseCounter extends FrameLayout {
    protected static final String SAVED_STATE_SELECTED_VALUE = "saved_state_selected_value";
    private static final String SAVED_STATE_SUPER = "saved_state_super";
    protected boolean alwaysShowPlus;
    protected boolean isEnabled = true;
    protected OnValueChangeListener mValueChangeListener;
    protected int maxValue = 100;
    protected int minValue = 0;
    protected int minusButtonContentDescriptionId = 0;
    protected int originalValue;
    protected int plusButtonContentDescriptionId = 0;
    protected int quantityStringResId = 0;
    protected int selectedValue = 1;
    protected boolean showPlusOnMax;
    protected boolean withTopBorder;

    public interface OnValueChangeListener {
        void onValueChange(BaseCounter baseCounter, int i);
    }

    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    /* access modifiers changed from: protected */
    public abstract void updateUI();

    public BaseCounter(Context context) {
        super(context);
        init(context, null);
    }

    public BaseCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BaseCounter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, null);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attrs) {
        ButterKnife.bind(this, (FrameLayout) LayoutInflater.from(context).inflate(getLayoutId(), this, true));
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.GroupedCounter, 0, 0);
            this.withTopBorder = a.getBoolean(C0880R.styleable.GroupedCounter_withTopBorder, false);
            this.quantityStringResId = a.getResourceId(C0880R.styleable.GroupedCounter_quantityString, this.quantityStringResId);
            this.minValue = a.getInteger(C0880R.styleable.GroupedCounter_minValue, this.minValue);
            this.maxValue = a.getInteger(C0880R.styleable.GroupedCounter_maxValue, this.maxValue);
            this.minusButtonContentDescriptionId = a.getResourceId(C0880R.styleable.GroupedCounter_minusButtonContentDescription, 0);
            this.plusButtonContentDescriptionId = a.getResourceId(C0880R.styleable.GroupedCounter_plusButtonContentDescription, 0);
            this.showPlusOnMax = a.getBoolean(C0880R.styleable.GroupedCounter_showPlusOnMax, false);
            this.alwaysShowPlus = a.getBoolean(C0880R.styleable.GroupedCounter_showPlusAlways, false);
            this.selectedValue = a.getInteger(C0880R.styleable.GroupedCounter_defaultValue, this.minValue);
            a.recycle();
        }
        updateUI();
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        return ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putInt(SAVED_STATE_SELECTED_VALUE, this.selectedValue)).putParcelable(SAVED_STATE_SUPER, super.onSaveInstanceState())).toBundle();
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.selectedValue = bundle.getInt(SAVED_STATE_SELECTED_VALUE, this.minValue);
            updateUI();
            state = bundle.getParcelable(SAVED_STATE_SUPER);
        }
        super.onRestoreInstanceState(state);
    }

    /* access modifiers changed from: protected */
    public String getQuantityString(int quantity, String valueToUse) {
        Resources resources = getContext().getResources();
        if (this.quantityStringResId <= 0) {
            return valueToUse;
        }
        return resources.getQuantityString(this.quantityStringResId, quantity, new Object[]{Integer.valueOf(quantity)});
    }

    /* access modifiers changed from: protected */
    public void setQuantityStringResId(int resId) {
        this.quantityStringResId = resId;
        updateUI();
    }

    public String getSelectedValueString() {
        return String.valueOf(getSelectedValue());
    }

    public void setUserInputEnabled(boolean enabled) {
        if (enabled != this.isEnabled) {
            this.isEnabled = enabled;
            updateUI();
        }
    }

    public void setMaxValue(int max) {
        this.maxValue = max;
        updateUI();
    }

    public void setMinValue(int min) {
        this.minValue = min;
        updateUI();
    }

    public void showPlusOnMax(boolean show) {
        this.showPlusOnMax = show;
        updateUI();
    }

    public void setOnValueChangeListener(OnValueChangeListener listener) {
        this.mValueChangeListener = listener;
    }

    public void setSelectedValue(int value) {
        int oldSelectedValue = this.selectedValue;
        if (this.selectedValue < this.minValue) {
            this.selectedValue = this.minValue;
        } else if (this.selectedValue > this.maxValue) {
            this.selectedValue = this.maxValue;
        } else {
            this.selectedValue = value;
        }
        updateUI();
        if (this.mValueChangeListener != null && oldSelectedValue != this.selectedValue) {
            this.mValueChangeListener.onValueChange(this, this.selectedValue);
        }
    }

    public void setSelectedValueWithoutNotifyingListener(int value) {
        OnValueChangeListener listener = this.mValueChangeListener;
        this.mValueChangeListener = null;
        setSelectedValue(value);
        this.mValueChangeListener = listener;
    }

    public int getSelectedValue() {
        return this.selectedValue;
    }
}
