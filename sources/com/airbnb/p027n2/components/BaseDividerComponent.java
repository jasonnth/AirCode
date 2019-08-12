package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.components.BaseDividerComponent */
public abstract class BaseDividerComponent extends BaseComponent implements DividerView {
    public BaseDividerComponent(Context context) {
        super(context);
    }

    public BaseDividerComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseDividerComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void init(AttributeSet attrs) {
        super.init(attrs);
        Paris.style(this).apply(attrs);
    }

    public void showDivider(boolean showDivider) {
        super.showDivider(showDivider);
    }

    public void setDividerColor(int dividerColor) {
        super.setDividerColor(dividerColor);
    }

    public void setDividerColorRes(int dividerColor) {
        super.setDividerColorRes(dividerColor);
    }

    public void setDividerHeight(int dividerHeight) {
        super.setDividerHeight(dividerHeight);
    }

    public void setDividerHeightRes(int dividerHeight) {
        super.setDividerHeightRes(dividerHeight);
    }
}
