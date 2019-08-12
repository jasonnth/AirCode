package com.airbnb.p027n2.primitives;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import java.util.Arrays;

@TargetApi(21)
/* renamed from: com.airbnb.n2.primitives.PillRippleDrawable */
public class PillRippleDrawable extends RippleDrawable {
    private final GradientDrawable backgroundDrawable;

    public PillRippleDrawable(ColorStateList color, Drawable content) {
        super(color, content, new ShapeDrawable());
        this.backgroundDrawable = (GradientDrawable) content;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        int radius = bounds.height() / 2;
        this.backgroundDrawable.setCornerRadius((float) radius);
        this.backgroundDrawable.setBounds(bounds);
        setDrawableByLayerId(16908334, getMaskDrawable(radius));
    }

    private ShapeDrawable getMaskDrawable(int radius) {
        float[] outerRadii = new float[8];
        Arrays.fill(outerRadii, (float) radius);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(outerRadii, null, null));
        shapeDrawable.getPaint().setColor(-1);
        return shapeDrawable;
    }
}
