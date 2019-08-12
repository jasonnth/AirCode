package com.airbnb.android.lib.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.p002v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import com.airbnb.android.lib.C0880R;

public class TintableImageButton extends AppCompatImageButton {
    private ColorStateList tint;

    public TintableImageButton(Context context) {
        super(context);
    }

    public TintableImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public TintableImageButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        TypedArray a = context.obtainStyledAttributes(attrs, C0880R.styleable.TintableImageButton, defStyle, 0);
        this.tint = a.getColorStateList(C0880R.styleable.TintableImageButton_customTint);
        a.recycle();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.tint != null && this.tint.isStateful()) {
            updateTintColor();
        }
    }

    private void updateTintColor() {
        setColorFilter(this.tint.getColorForState(getDrawableState(), 0));
    }

    public void setColorFilter(ColorStateList tint2) {
        this.tint = tint2;
        super.setColorFilter(tint2.getColorForState(getDrawableState(), 0));
    }
}
