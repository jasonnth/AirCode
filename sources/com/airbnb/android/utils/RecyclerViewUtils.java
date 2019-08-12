package com.airbnb.android.utils;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewConfiguration;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.lang.reflect.Field;

public class RecyclerViewUtils {
    private static final double TOUCH_SLOP_SCALE_FACTOR_FOR_NESTED_SCROLLING = 3.5d;

    public static void setTouchSlopForNestedScrolling(RecyclerView recyclerView) {
        setTouchSlopForNestedScrolling(recyclerView, TOUCH_SLOP_SCALE_FACTOR_FOR_NESTED_SCROLLING);
    }

    public static void setTouchSlopForNestedScrolling(RecyclerView recyclerView, double slopFactor) {
        Field touchSlopField = null;
        try {
            touchSlopField = RecyclerView.class.getDeclaredField("mTouchSlop");
            touchSlopField.setAccessible(true);
        } catch (NoSuchFieldException e) {
        }
        if (touchSlopField != null) {
            try {
                touchSlopField.setInt(recyclerView, (int) (((double) ViewConfiguration.get(recyclerView.getContext()).getScaledTouchSlop()) * slopFactor));
            } catch (IllegalAccessException e2) {
            }
        }
    }

    public static boolean isAtTop(RecyclerView recyclerView) {
        if (recyclerView == null || recyclerView.getChildCount() == 0) {
            return true;
        }
        View firstChild = recyclerView.getChildAt(0);
        if (recyclerView.getChildLayoutPosition(firstChild) != 0 || firstChild.getTop() < ViewLibUtils.getTopMargin(firstChild)) {
            return false;
        }
        return true;
    }
}
