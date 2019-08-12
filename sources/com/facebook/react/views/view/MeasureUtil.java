package com.facebook.react.views.view;

import android.view.View.MeasureSpec;
import com.facebook.yoga.YogaMeasureMode;

public class MeasureUtil {
    public static int getMeasureSpec(float size, YogaMeasureMode mode) {
        if (mode == YogaMeasureMode.EXACTLY) {
            return MeasureSpec.makeMeasureSpec((int) size, 1073741824);
        }
        if (mode == YogaMeasureMode.AT_MOST) {
            return MeasureSpec.makeMeasureSpec((int) size, Integer.MIN_VALUE);
        }
        return MeasureSpec.makeMeasureSpec(0, 0);
    }
}
