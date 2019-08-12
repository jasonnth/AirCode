package com.airbnb.p027n2.utils;

import android.os.Build.VERSION;
import android.support.p000v4.view.ViewCompat;
import android.view.View;
import android.view.ViewParent;

/* renamed from: com.airbnb.n2.utils.ViewOffsetHelper */
public class ViewOffsetHelper {
    private int layoutTop;
    private int verticalOffset;
    private final View view;

    public ViewOffsetHelper(View view2) {
        this.view = view2;
    }

    public void onViewLayout() {
        this.layoutTop = this.view.getTop();
        updateOffsets();
    }

    private void updateOffsets() {
        ViewCompat.offsetTopAndBottom(this.view, this.verticalOffset - (this.view.getTop() - this.layoutTop));
        if (VERSION.SDK_INT < 23) {
            tickleInvalidationFlag(this.view);
            ViewParent vp = this.view.getParent();
            if (vp instanceof View) {
                tickleInvalidationFlag((View) vp);
            }
        }
    }

    private static void tickleInvalidationFlag(View view2) {
        float x = ViewCompat.getTranslationX(view2);
        ViewCompat.setTranslationY(view2, 1.0f + x);
        ViewCompat.setTranslationY(view2, x);
    }

    public boolean setVerticalOffset(int offset) {
        if (this.verticalOffset == offset) {
            return false;
        }
        this.verticalOffset = offset;
        updateOffsets();
        return true;
    }

    public int getVerticalOffset() {
        return this.verticalOffset;
    }
}
