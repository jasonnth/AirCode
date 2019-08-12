package com.airbnb.p027n2.primitives;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.p002v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.LinkClickableTextView;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;
import com.airbnb.p027n2.interfaces.Typefaceable;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.airbnb.p027n2.primitives.fonts.FontHelper;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.primitives.AirTextView */
public class AirTextView extends AppCompatTextView implements LinkClickableTextView, Typefaceable {
    private static final int[] STATE_INVERTED = {R.attr.n2_state_inverted};
    private Font font;
    private boolean hasInvertedColors;
    private ColorStateList mDefaultDrawableColor;
    private LinkOnClickListener mLinkClickListener;

    public AirTextView(Context context) {
        super(context);
        init(context, null);
    }

    public AirTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AirTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @TargetApi(21)
    public AirTextView(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    /* access modifiers changed from: protected */
    public void init(Context context, AttributeSet attrs) {
        Paris.style(this).apply(attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.n2_AirTextView);
        compatSetDrawables(a);
        this.mDefaultDrawableColor = a.getColorStateList(R.styleable.n2_AirTextView_n2_drawableColor);
        ColorStateList[] colors = {a.getColorStateList(R.styleable.n2_AirTextView_n2_drawableLeftColor), a.getColorStateList(R.styleable.n2_AirTextView_n2_drawableRightColor), a.getColorStateList(R.styleable.n2_AirTextView_n2_drawableTopColor), a.getColorStateList(R.styleable.n2_AirTextView_n2_drawableBottomColor)};
        a.recycle();
        Drawable[] drawables = getCompoundDrawables();
        for (int i = 0; i < drawables.length; i++) {
            mutateDrawable(drawables[i], colors[i]);
        }
        if (VERSION.SDK_INT < 21 && getLineSpacingMultiplier() < 1.0f) {
            setLineSpacing(getLineSpacingExtra(), 1.0f);
        }
    }

    private void compatSetDrawables(TypedArray typedArray) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(ViewLibUtils.getDrawableCompat(context, typedArray, R.styleable.n2_AirTextView_n2_drawableLeftCompat), ViewLibUtils.getDrawableCompat(context, typedArray, R.styleable.n2_AirTextView_n2_drawableTopCompat), ViewLibUtils.getDrawableCompat(context, typedArray, R.styleable.n2_AirTextView_n2_drawableRightCompat), ViewLibUtils.getDrawableCompat(context, typedArray, R.styleable.n2_AirTextView_n2_drawableBottomCompat));
    }

    private void mutateDrawable(Drawable drawable, ColorStateList newColorStateList) {
        int newColor;
        if (drawable == null) {
            return;
        }
        if (newColorStateList != null || this.mDefaultDrawableColor != null) {
            if (newColorStateList != null) {
                newColor = newColorStateList.getDefaultColor();
            } else {
                newColor = this.mDefaultDrawableColor.getDefaultColor();
            }
            ColorizedDrawable.mutateDrawableWithColor(drawable, newColor);
        }
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int extraSpace) {
        int[] state = super.onCreateDrawableState(extraSpace + 1);
        if (this.hasInvertedColors) {
            mergeDrawableStates(state, STATE_INVERTED);
        }
        return state;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        getViewTreeObserver().removeOnPreDrawListener(this);
        super.onDetachedFromWindow();
    }

    public void setFont(Font font2) {
        this.font = font2;
        FontHelper.setFont(this, font2);
    }

    public Font getFont() {
        return this.font;
    }

    public void setTextAppearance(Context context, int resid) {
        Typeface typeface = getTypeface();
        super.setTextAppearance(context, resid);
        setTypeface(typeface);
    }

    public void setOnLinkClickListener(LinkOnClickListener listener) {
        this.mLinkClickListener = listener;
    }

    public void onLinkClick(int linkIndex) {
        if (this.mLinkClickListener != null) {
            this.mLinkClickListener.onClickLink(linkIndex);
        }
    }

    public void setHasInvertedColors(boolean hasInvertedColors2) {
        this.hasInvertedColors = hasInvertedColors2;
        refreshDrawableState();
    }
}
