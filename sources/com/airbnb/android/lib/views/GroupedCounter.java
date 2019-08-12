package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;

public class GroupedCounter extends BaseCounter {
    @BindView
    ImageView minusButton;
    @BindView
    ImageView plusButton;
    @BindView
    TextView text;
    @BindView
    View topBorder;

    public GroupedCounter(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GroupedCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupedCounter(Context context) {
        super(context, null);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attrs) {
        super.init(context, attrs);
        ViewUtils.setVisibleIf(this.topBorder, this.withTopBorder);
        if (this.minusButtonContentDescriptionId > 0) {
            this.minusButton.setContentDescription(context.getString(this.minusButtonContentDescriptionId));
        }
        if (this.plusButtonContentDescriptionId > 0) {
            this.plusButton.setContentDescription(context.getString(this.plusButtonContentDescriptionId));
        }
        this.minusButton.setOnClickListener(GroupedCounter$$Lambda$1.lambdaFactory$(this));
        this.plusButton.setOnClickListener(GroupedCounter$$Lambda$2.lambdaFactory$(this));
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C0880R.layout.grouped_counter;
    }

    /* access modifiers changed from: protected */
    public void updateUI() {
        boolean z;
        boolean z2 = true;
        String valueToUse = String.valueOf(this.selectedValue);
        if (this.alwaysShowPlus || (this.showPlusOnMax && this.selectedValue == this.maxValue)) {
            valueToUse = getContext().getString(C0880R.string.over_maximum_search_filter_price, new Object[]{valueToUse});
        }
        if (!isInEditMode()) {
            this.text.setText(getQuantityString(this.selectedValue, valueToUse));
        }
        ImageView imageView = this.minusButton;
        if (!this.isEnabled || this.selectedValue <= this.minValue) {
            z = false;
        } else {
            z = true;
        }
        imageView.setEnabled(z);
        ImageView imageView2 = this.plusButton;
        if (!this.isEnabled || this.selectedValue >= this.maxValue) {
            z2 = false;
        }
        imageView2.setEnabled(z2);
    }

    public void setTextAppearance(int styleRes) {
        this.text.setTextAppearance(getContext(), styleRes);
    }

    public void setText(int textRes) {
        setText(getResources().getString(textRes));
    }

    public void setText(String text2) {
        this.text.setText(text2);
    }

    public void setTextSize(int unit, float textSize) {
        this.text.setTextSize(unit, textSize);
    }

    public void setMinusButtonContentDescription(String contentDescription) {
        this.minusButton.setContentDescription(contentDescription);
    }

    public void setPlusButtonContentDescription(String contentDescription) {
        this.plusButton.setContentDescription(contentDescription);
    }
}
