package com.airbnb.p027n2.primitives;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.animation.LinearOutSlowInInterpolator;
import android.support.p002v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Interpolator;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.SwitchStyle;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.paris.Paris;

/* renamed from: com.airbnb.n2.primitives.AirSwitch */
public final class AirSwitch extends FrameLayout implements Checkable {
    private final AnimatorUpdateListener animationListener;
    private final ArgbEvaluator argbEvaluator;
    private boolean checked;
    private Drawable checkedDrawable;
    private int checkedStrokeColor;
    @BindView
    FrameLayout container;
    private final Interpolator interpolator;
    private OnCheckedChangeListener listener;
    @BindDimen
    int strokeWidth;
    private final GradientDrawable thumbBackground;
    private int thumbCheckedColor;
    private float thumbProgress;
    private int thumbUncheckedColor;
    @BindView
    AirImageView thumbView;
    private int trackCheckedColor;
    private final GradientDrawable trackDrawable;
    private int trackUncheckedColor;
    private Drawable uncheckedDrawable;
    private int uncheckedStrokeColor;

    /* renamed from: com.airbnb.n2.primitives.AirSwitch$OnCheckedChangeListener */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(AirSwitch airSwitch, boolean z);
    }

    static /* synthetic */ void lambda$new$0(AirSwitch airSwitch, ValueAnimator animation) {
        airSwitch.thumbProgress = ((Float) animation.getAnimatedValue()).floatValue();
        airSwitch.updateColorsAndDrawable();
    }

    public AirSwitch(Context context) {
        super(context);
        this.animationListener = AirSwitch$$Lambda$1.lambdaFactory$(this);
        this.argbEvaluator = new ArgbEvaluator();
        this.interpolator = new LinearOutSlowInInterpolator();
        this.thumbBackground = new GradientDrawable();
        this.trackDrawable = new GradientDrawable();
        init(null);
    }

    public AirSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.animationListener = AirSwitch$$Lambda$2.lambdaFactory$(this);
        this.argbEvaluator = new ArgbEvaluator();
        this.interpolator = new LinearOutSlowInInterpolator();
        this.thumbBackground = new GradientDrawable();
        this.trackDrawable = new GradientDrawable();
        init(attrs);
    }

    public AirSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.animationListener = AirSwitch$$Lambda$3.lambdaFactory$(this);
        this.argbEvaluator = new ArgbEvaluator();
        this.interpolator = new LinearOutSlowInInterpolator();
        this.thumbBackground = new GradientDrawable();
        this.trackDrawable = new GradientDrawable();
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.n2_air_switch, this);
        ButterKnife.bind((View) this);
        Paris.style(this).apply(attrs);
        this.thumbView.setBackground(this.thumbBackground);
        this.container.setBackground(this.trackDrawable);
        super.setOnClickListener(AirSwitch$$Lambda$4.lambdaFactory$(this));
        MarginLayoutParams mlp = (MarginLayoutParams) this.container.getLayoutParams();
        mlp.setMargins(this.strokeWidth * 2, this.strokeWidth, this.strokeWidth * 2, this.strokeWidth);
        this.container.setLayoutParams(mlp);
    }

    public void setSwitchStyle(int styleIndex) {
        setStyle(SwitchStyle.values()[styleIndex]);
    }

    public void setStyle(SwitchStyle style) {
        switch (style) {
            case Sheet:
                this.trackUncheckedColor = 0;
                this.trackCheckedColor = 0;
                this.thumbUncheckedColor = Color.argb(0, 255, 255, 255);
                this.thumbCheckedColor = -1;
                this.checkedStrokeColor = -1;
                this.uncheckedStrokeColor = -1;
                this.checkedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.n2_air_switch_sheet_checked);
                this.uncheckedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.n2_air_switch_sheet_unchecked);
                break;
            case Filled:
                int checkedColor = ContextCompat.getColor(getContext(), R.color.n2_switch_checked);
                this.trackDrawable.setStroke(this.strokeWidth, checkedColor);
                this.trackCheckedColor = ContextCompat.getColor(getContext(), R.color.n2_switch_checked);
                this.trackUncheckedColor = ContextCompat.getColor(getContext(), R.color.n2_switch_unchecked);
                this.thumbUncheckedColor = -1;
                this.thumbCheckedColor = -1;
                this.checkedStrokeColor = checkedColor;
                this.uncheckedStrokeColor = ContextCompat.getColor(getContext(), R.color.n2_switch_border_off);
                this.checkedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.n2_air_switch_checked);
                this.uncheckedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.n2_air_switch_unchecked);
                break;
            case Outlined:
                int checkedColor2 = ContextCompat.getColor(getContext(), R.color.n2_switch_checked);
                this.trackCheckedColor = checkedColor2;
                this.trackUncheckedColor = -1;
                this.thumbUncheckedColor = -1;
                this.thumbCheckedColor = -1;
                this.checkedStrokeColor = checkedColor2;
                this.uncheckedStrokeColor = checkedColor2;
                this.checkedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.n2_air_switch_checked);
                this.uncheckedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.n2_air_switch_unchecked_babu);
                break;
        }
        updateColorsAndDrawable();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.trackDrawable.setCornerRadius((float) (h / 2));
        this.thumbBackground.setCornerRadius((float) ((h - (getThumbMargin() * 2)) / 2));
        int thumbHeight = getHeight() - (getThumbMargin() * 2);
        int padding = (thumbHeight - ((int) (((double) thumbHeight) * 0.6d))) / 2;
        if (padding > 0) {
            this.thumbView.setPadding(padding, padding, padding, padding);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getMeasuredWidth() <= 0 || getMeasuredHeight() == 16777215 || getMeasuredWidth() > getMeasuredHeight()) {
            int spec = MeasureSpec.makeMeasureSpec(this.container.getMeasuredHeight() - (getThumbMargin() * 2), 1073741824);
            this.thumbView.measure(spec, spec);
            return;
        }
        throw new IllegalStateException("Switch must wider than it is tall " + getMeasuredWidth() + "x" + getMeasuredHeight());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            updateColorsAndDrawable();
        }
    }

    public void setChecked(boolean checked2) {
        setChecked(checked2, true);
    }

    public void setChecked(boolean checked2, boolean animated) {
        if (this.checked != checked2) {
            this.checked = checked2;
            if (this.listener != null) {
                this.listener.onCheckedChanged(this, checked2);
            }
            float[] fArr = new float[2];
            fArr[0] = this.thumbProgress;
            fArr[1] = checked2 ? 1.0f : 0.0f;
            ValueAnimator animator = ValueAnimator.ofFloat(fArr);
            animator.addUpdateListener(this.animationListener);
            animator.setInterpolator(this.interpolator);
            if (!animated) {
                animator.setDuration(1);
            }
            animator.start();
        }
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void toggle() {
        setChecked(!this.checked);
    }

    public void setOnClickListener(OnClickListener l) {
        throw new IllegalStateException("You may not set a click listener on AirSwitch. Consider using a checked change listener.");
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener2) {
        this.listener = listener2;
    }

    private void updateColorsAndDrawable() {
        this.thumbView.setImageDrawable(this.checked ? this.checkedDrawable : this.uncheckedDrawable);
        this.trackDrawable.setColor(((Integer) this.argbEvaluator.evaluate(this.thumbProgress, Integer.valueOf(this.trackUncheckedColor), Integer.valueOf(this.trackCheckedColor))).intValue());
        this.thumbBackground.setColor(((Integer) this.argbEvaluator.evaluate(this.thumbProgress, Integer.valueOf(this.thumbUncheckedColor), Integer.valueOf(this.thumbCheckedColor))).intValue());
        int strokeColor = ((Integer) this.argbEvaluator.evaluate(this.thumbProgress, Integer.valueOf(this.uncheckedStrokeColor), Integer.valueOf(this.checkedStrokeColor))).intValue();
        this.trackDrawable.setStroke(this.strokeWidth, strokeColor);
        this.thumbBackground.setStroke(this.strokeWidth, strokeColor);
        float minTranslation = ((float) ((MarginLayoutParams) this.container.getLayoutParams()).leftMargin) - ((float) (this.strokeWidth * 2));
        this.thumbView.setTranslationX((this.thumbProgress * (((float) (this.container.getWidth() - this.thumbView.getWidth())) - minTranslation)) + minTranslation);
    }

    private int getThumbMargin() {
        return ((LayoutParams) this.thumbView.getLayoutParams()).topMargin;
    }
}
