package com.airbnb.p027n2.components;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.airbnb.p027n2.utils.ViewLibUtils;

@Deprecated
/* renamed from: com.airbnb.n2.components.PrimaryButton */
public class PrimaryButton extends FrameLayout {
    @BindView
    LoadingView loadingView;
    @BindView
    AirTextView textView;

    public PrimaryButton(Context context) {
        super(context);
        init(null);
    }

    public PrimaryButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PrimaryButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_primary_button, this);
        ButterKnife.bind((View) this);
        setupAttributes(attrs);
    }

    private void setupAttributes(AttributeSet attrs) {
        Context context = getContext();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.n2_PrimaryButton, 0, 0);
        String buttonText = a.getString(R.styleable.n2_PrimaryButton_n2_buttonText);
        Drawable drawableLeft = ViewLibUtils.getDrawableCompat(context, a, R.styleable.n2_PrimaryButton_n2_drawableLeft);
        ColorStateList drawableColor = a.getColorStateList(R.styleable.n2_PrimaryButton_n2_drawableColor);
        setText((CharSequence) buttonText);
        setDrawableLeft(drawableLeft, drawableColor);
        a.recycle();
    }

    public void setText(int stringRes) {
        this.textView.setText(stringRes);
    }

    public void setText(CharSequence buttonText) {
        this.textView.setText(buttonText);
    }

    public CharSequence getText() {
        return this.textView.getText();
    }

    public void setDrawableLeft(Drawable drawableLeft) {
        setDrawableLeft(drawableLeft, null);
    }

    public void setDrawableLeft(Drawable drawableLeft, ColorStateList colorStateList) {
        if (drawableLeft == null) {
            this.textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            return;
        }
        if (colorStateList != null) {
            drawableLeft = ColorizedDrawable.mutateDrawableWithColor(drawableLeft, colorStateList.getDefaultColor());
        }
        this.textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
    }

    public void setLoading() {
        this.textView.setVisibility(4);
        this.loadingView.setVisibility(0);
    }

    public void setNormal() {
        this.loadingView.setVisibility(8);
        this.textView.setVisibility(0);
    }

    public static void mock(PrimaryButton button) {
        button.setText((CharSequence) "Primary button");
        button.setBackgroundResource(R.drawable.n2_primary_button_background);
    }
}
