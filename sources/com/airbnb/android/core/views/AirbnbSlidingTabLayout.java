package com.airbnb.android.core.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.view.ViewPager;
import android.util.AttributeSet;
import com.airbnb.android.core.C0716R;

public class AirbnbSlidingTabLayout extends SlidingTabLayout {
    private int viewPagerId;

    public AirbnbSlidingTabLayout(Context context) {
        super(context);
        init(context, null);
    }

    public AirbnbSlidingTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AirbnbSlidingTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, C0716R.styleable.AirbnbSlidingTabLayout, 0, 0);
        int selectedIndicatorColor = a.getColor(C0716R.styleable.AirbnbSlidingTabLayout_selectedIndicatorColor, 0);
        int selectedIndicatorThickness = a.getDimensionPixelSize(C0716R.styleable.AirbnbSlidingTabLayout_selectedIndicatorThickness, 0);
        boolean showBottomDivider = a.getBoolean(C0716R.styleable.AirbnbSlidingTabLayout_showBottomDivider, false);
        int customTabViewLayout = a.getResourceId(C0716R.styleable.AirbnbSlidingTabLayout_customTabView, -1);
        this.viewPagerId = a.getResourceId(C0716R.styleable.AirbnbSlidingTabLayout_viewPager, -1);
        a.recycle();
        setSelectedIndicatorColors(selectedIndicatorColor);
        setSelectedIndicatorThickness(selectedIndicatorThickness);
        showBottomDivider(showBottomDivider);
        if (customTabViewLayout != -1) {
            setCustomTabView(customTabViewLayout);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.viewPagerId != -1) {
            setViewPager((ViewPager) getRootView().findViewById(this.viewPagerId));
        }
    }
}
