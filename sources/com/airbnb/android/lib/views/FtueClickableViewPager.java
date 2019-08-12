package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.core.views.ClickableViewPager;

public class FtueClickableViewPager extends ClickableViewPager {
    public FtueClickableViewPager(Context context) {
        super(context);
    }

    public FtueClickableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /* access modifiers changed from: protected */
    public boolean captureTouchEvents() {
        if (getCurrentItem() == getAdapter().getCount() - 1) {
            return false;
        }
        return super.captureTouchEvents();
    }
}
