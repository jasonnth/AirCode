package com.airbnb.android.core.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p002v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.utils.ColorizedDrawable;

public class ColorizedIconView extends AppCompatImageView {
    private int mDrawableId;

    public ColorizedIconView(Context context) {
        this(context, null);
    }

    public ColorizedIconView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorizedIconView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, C0716R.styleable.ColorizedIconView, 0, 0);
        this.mDrawableId = a.getResourceId(C0716R.styleable.ColorizedIconView_drawableId, 0);
        setColorStateListRes(a.getResourceId(C0716R.styleable.ColorizedIconView_colorStates, 0));
        a.recycle();
    }

    public void setColorStateListRes(int colorStateListRes) {
        if (this.mDrawableId != 0 && colorStateListRes != 0) {
            setImageDrawable(ColorizedDrawable.forIdStateList(getContext(), this.mDrawableId, colorStateListRes));
        }
    }

    public void setDrawableId(int drawableId) {
        this.mDrawableId = drawableId;
    }

    public void setColor(int colorId) {
        setImageDrawable(ColorizedDrawable.forIdWithColor(getContext(), this.mDrawableId, colorId));
    }
}
