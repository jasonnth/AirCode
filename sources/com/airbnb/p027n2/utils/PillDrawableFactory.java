package com.airbnb.p027n2.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.p000v4.content.ContextCompat;
import butterknife.BindDimen;
import com.airbnb.p027n2.primitives.PillPreLollipopDrawable;
import com.airbnb.p027n2.primitives.PillRippleDrawable;

/* renamed from: com.airbnb.n2.utils.PillDrawableFactory */
public class PillDrawableFactory {
    private int colorRes;
    private final Context context;
    @BindDimen
    int preLollipopBorderWidth;
    private int preLollipopElevationBorderColorRes;
    private int preLollipopPressedColorRes;
    private int rippleColorRes;
    private int strokeColorRes;
    private int strokeWidthRes;

    public PillDrawableFactory(Context context2) {
        this.context = context2;
    }

    public PillDrawableFactory color(int colorRes2) {
        this.colorRes = colorRes2;
        return this;
    }

    public PillDrawableFactory preLollipopPressedColor(int preLollipopPressedColorRes2) {
        this.preLollipopPressedColorRes = preLollipopPressedColorRes2;
        return this;
    }

    public PillDrawableFactory reset() {
        this.colorRes = 0;
        this.strokeColorRes = 0;
        this.preLollipopPressedColorRes = 0;
        this.preLollipopElevationBorderColorRes = 0;
        this.rippleColorRes = 0;
        this.strokeWidthRes = 0;
        return this;
    }

    public Drawable build() {
        boolean z;
        ColorStateList rippleColor;
        if (this.strokeWidthRes != 0) {
            z = true;
        } else {
            z = false;
        }
        if (z ^ (this.strokeColorRes != 0)) {
            throw new IllegalArgumentException("Provided one of strokeColorRes or strokeWidthRes. Must specify both or neither.");
        } else if (this.colorRes == 0) {
            throw new IllegalArgumentException("Must provide a color");
        } else {
            GradientDrawable backgroundDrawable = new GradientDrawable();
            backgroundDrawable.setColor(ContextCompat.getColor(this.context, this.colorRes));
            int strokeWidth = 0;
            int strokeColor = 0;
            if (!(this.strokeWidthRes == 0 || this.strokeColorRes == 0)) {
                strokeWidth = this.context.getResources().getDimensionPixelSize(this.strokeWidthRes);
                strokeColor = ContextCompat.getColor(this.context, this.strokeColorRes);
                backgroundDrawable.setStroke(strokeWidth, strokeColor);
            }
            if (ViewLibUtils.isAtLeastLollipop()) {
                if (this.rippleColorRes == 0) {
                    TypedArray typedArray = this.context.obtainStyledAttributes(new int[]{16843820});
                    rippleColor = ColorStateList.valueOf(typedArray.getColor(0, 0));
                    typedArray.recycle();
                } else {
                    rippleColor = ColorStateList.valueOf(ContextCompat.getColor(this.context, this.rippleColorRes));
                }
                return new PillRippleDrawable(rippleColor, backgroundDrawable);
            }
            handlePreLollipopBorder(backgroundDrawable);
            if (this.preLollipopPressedColorRes == 0) {
                return new PillPreLollipopDrawable(backgroundDrawable);
            }
            GradientDrawable preLollipopPressedDrawable = new GradientDrawable();
            preLollipopPressedDrawable.setColor(ContextCompat.getColor(this.context, this.preLollipopPressedColorRes));
            if (!(strokeColor == 0 || strokeWidth == 0)) {
                preLollipopPressedDrawable.setStroke(strokeWidth, strokeColor);
            }
            handlePreLollipopBorder(preLollipopPressedDrawable);
            return new PillPreLollipopDrawable(backgroundDrawable, preLollipopPressedDrawable);
        }
    }

    private int handlePreLollipopBorder(GradientDrawable drawable) {
        if (this.preLollipopElevationBorderColorRes == 0) {
            return 0;
        }
        int preLollipopElevationBorderColor = ContextCompat.getColor(this.context, this.preLollipopElevationBorderColorRes);
        drawable.setStroke(this.preLollipopBorderWidth, preLollipopElevationBorderColor);
        return preLollipopElevationBorderColor;
    }
}
