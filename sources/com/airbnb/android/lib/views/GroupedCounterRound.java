package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.presenters.FacetPresenter;

public class GroupedCounterRound extends GroupedCounter {
    private static final int DEFAULT_TEXT_SIZE = -1;
    private int defaultValue;
    @BindView
    TextView facetCountText;
    private int textColorDefault;
    private int textColorModified;
    private int textSize;

    public GroupedCounterRound(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    public GroupedCounterRound(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public GroupedCounterRound(Context context) {
        super(context);
        init(context, null, 0);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attrs, int defStyle) {
        super.init(context, attrs);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.GroupedCounterRound, defStyle, 0);
            this.textSize = a.getDimensionPixelSize(C0880R.styleable.GroupedCounterRound_textSize, -1);
            this.textColorDefault = a.getColor(C0880R.styleable.GroupedCounterRound_textColorDefault, getResources().getColor(C0880R.color.search_filter_text_color_default));
            this.textColorModified = a.getColor(C0880R.styleable.GroupedCounterRound_textColorModified, getResources().getColor(C0880R.color.search_filter_text_color_modified));
            this.defaultValue = a.getInteger(C0880R.styleable.GroupedCounter_defaultValue, this.minValue);
            a.recycle();
        }
        if (this.textSize > 0) {
            this.text.setTextSize(0, (float) this.textSize);
            this.facetCountText.setTextSize(0, (float) this.textSize);
        }
        this.text.setTextColor(this.textColorDefault);
        this.facetCountText.setTextColor(this.textColorDefault);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C0880R.layout.grouped_counter_round;
    }

    public void setFacetCount(int count) {
        FacetPresenter.setFacetCount(this.facetCountText, count);
        adjustLayoutParams();
    }

    /* access modifiers changed from: protected */
    public void updateUI() {
        super.updateUI();
        this.minusButton.setImageResource(this.minusButton.isEnabled() ? C0880R.C0881drawable.ic_grouped_counter_round_minus_enabled : C0880R.C0881drawable.ic_grouped_counter_round_minus_disabled);
        this.plusButton.setImageResource(this.plusButton.isEnabled() ? C0880R.C0881drawable.ic_grouped_counter_round_plus_enabled : C0880R.C0881drawable.ic_grouped_counter_round_plus_disabled);
        if (1 != 0) {
            int textColor = this.selectedValue == this.defaultValue ? this.textColorDefault : this.textColorModified;
            this.text.setTextColor(textColor);
            this.facetCountText.setTextColor(textColor);
        }
    }

    private void adjustLayoutParams() {
        LayoutParams textLayoutParams = (LayoutParams) this.text.getLayoutParams();
        LayoutParams facetLayoutParams = (LayoutParams) this.facetCountText.getLayoutParams();
        if (this.facetCountText.getVisibility() == 0) {
            facetLayoutParams.weight = 1.0f;
            facetLayoutParams.width = 0;
            textLayoutParams.weight = 0.0f;
            textLayoutParams.width = -2;
            return;
        }
        facetLayoutParams.weight = 0.0f;
        facetLayoutParams.width = -2;
        textLayoutParams.weight = 1.0f;
        textLayoutParams.width = 0;
    }

    public void setTextColor(ColorStateList color) {
        this.textColorDefault = color.getDefaultColor();
        this.text.setTextColor(this.textColorDefault);
        this.facetCountText.setTextColor(this.textColorDefault);
    }
}
