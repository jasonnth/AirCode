package com.airbnb.p027n2.primitives.imaging;

import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.primitives.imaging.SizeDeterminer */
public class SizeDeterminer {
    private Point screenSize;
    private final View view;

    public SizeDeterminer(View view2) {
        this.view = view2;
    }

    public int getViewHeight() {
        if (isSizeValid(this.view.getHeight())) {
            return this.view.getHeight();
        }
        LayoutParams layoutParams = this.view.getLayoutParams();
        if (layoutParams != null) {
            return getSizeForParam(layoutParams.height, true);
        }
        return 0;
    }

    public int getViewWidth() {
        if (isSizeValid(this.view.getWidth())) {
            return this.view.getWidth();
        }
        LayoutParams layoutParams = this.view.getLayoutParams();
        if (layoutParams != null) {
            return getSizeForParam(layoutParams.width, false);
        }
        return 0;
    }

    private boolean isSizeValid(int size) {
        return size > 0;
    }

    private int getSizeForParam(int param, boolean isHeight) {
        if (param != -2) {
            return param;
        }
        Point screenSize2 = getScreenSize();
        return isHeight ? screenSize2.y : screenSize2.x;
    }

    private Point getScreenSize() {
        if (this.screenSize == null) {
            this.screenSize = ViewLibUtils.getScreenSize(this.view.getContext());
        }
        return this.screenSize;
    }

    public boolean hasWidthAndHeight() {
        return isSizeValid(getViewHeight()) && isSizeValid(getViewWidth());
    }
}
