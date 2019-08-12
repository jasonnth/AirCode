package com.airbnb.p027n2.primitives;

import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.StateSet;

/* renamed from: com.airbnb.n2.primitives.PillPreLollipopDrawable */
public class PillPreLollipopDrawable extends StateListDrawable {
    private final int[] PRESSED_STATE_SET = {16842919};
    private final GradientDrawable backgroundDrawable;
    private GradientDrawable preLollipopPressedDrawable;

    public PillPreLollipopDrawable(GradientDrawable backgroundDrawable2, GradientDrawable preLollipopPressedDrawable2) {
        this.backgroundDrawable = backgroundDrawable2;
        this.preLollipopPressedDrawable = preLollipopPressedDrawable2;
        addState(this.PRESSED_STATE_SET, preLollipopPressedDrawable2);
        addState(StateSet.WILD_CARD, backgroundDrawable2);
    }

    public PillPreLollipopDrawable(GradientDrawable backgroundDrawable2) {
        this.backgroundDrawable = backgroundDrawable2;
        addState(StateSet.WILD_CARD, backgroundDrawable2);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        int radius = bounds.height() / 2;
        this.backgroundDrawable.setCornerRadius((float) radius);
        if (this.preLollipopPressedDrawable != null) {
            this.preLollipopPressedDrawable.setCornerRadius((float) radius);
        }
    }
}
