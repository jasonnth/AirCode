package com.airbnb.p027n2.components;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.p000v4.view.animation.LinearOutSlowInInterpolator;
import android.support.p002v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.animation.Interpolator;
import android.widget.Checkable;
import android.widget.FrameLayout;
import butterknife.BindDimen;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.SmallSheetSwitchRowSwitch */
public class SmallSheetSwitchRowSwitch extends FrameLayout implements Checkable {
    private final AnimatorUpdateListener animationListener;
    private final ArgbEvaluator argbEvaluator;
    private Boolean checked;
    private Drawable checkedDrawable;
    private final Interpolator interpolator;
    private OnCheckedChangeListener listener;
    @BindDimen
    int strokeWidth;
    private final GradientDrawable thumbBackground;
    private int thumbCheckedColor;
    private float thumbProgress;
    private int thumbUncheckedColor;
    private AirImageView thumbView;
    private final GradientDrawable trackDrawable;
    private Drawable uncheckedDrawable;

    /* renamed from: com.airbnb.n2.components.SmallSheetSwitchRowSwitch$OnCheckedChangeListener */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(SmallSheetSwitchRowSwitch smallSheetSwitchRowSwitch, boolean z);
    }

    static /* synthetic */ void lambda$new$0(SmallSheetSwitchRowSwitch smallSheetSwitchRowSwitch, ValueAnimator animation) {
        smallSheetSwitchRowSwitch.thumbProgress = ((Float) animation.getAnimatedValue()).floatValue();
        smallSheetSwitchRowSwitch.updateColorsAndDrawable();
    }

    public SmallSheetSwitchRowSwitch(Context context) {
        super(context);
        this.animationListener = SmallSheetSwitchRowSwitch$$Lambda$1.lambdaFactory$(this);
        this.argbEvaluator = new ArgbEvaluator();
        this.interpolator = new LinearOutSlowInInterpolator();
        this.thumbBackground = new GradientDrawable();
        this.trackDrawable = new GradientDrawable();
        init();
    }

    public SmallSheetSwitchRowSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.animationListener = SmallSheetSwitchRowSwitch$$Lambda$2.lambdaFactory$(this);
        this.argbEvaluator = new ArgbEvaluator();
        this.interpolator = new LinearOutSlowInInterpolator();
        this.thumbBackground = new GradientDrawable();
        this.trackDrawable = new GradientDrawable();
        init();
    }

    public SmallSheetSwitchRowSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.animationListener = SmallSheetSwitchRowSwitch$$Lambda$3.lambdaFactory$(this);
        this.argbEvaluator = new ArgbEvaluator();
        this.interpolator = new LinearOutSlowInInterpolator();
        this.thumbBackground = new GradientDrawable();
        this.trackDrawable = new GradientDrawable();
        init();
    }

    private void init() {
        ButterKnife.bind((View) this);
        this.thumbView = new AirImageView(getContext());
        addView(this.thumbView);
        this.thumbBackground.setStroke(this.strokeWidth, -1);
        this.trackDrawable.setStroke(this.strokeWidth, -1);
        this.thumbUncheckedColor = -1;
        this.thumbCheckedColor = -1;
        this.checkedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.n2_small_sheet_switch_row_switch_checked);
        this.uncheckedDrawable = AppCompatResources.getDrawable(getContext(), R.drawable.n2_small_sheet_switch_row_switch_unchecked);
        this.thumbView.setBackground(this.thumbBackground);
        setBackground(this.trackDrawable);
        setChecked(false);
        super.setOnClickListener(SmallSheetSwitchRowSwitch$$Lambda$4.lambdaFactory$(this));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.trackDrawable.setCornerRadius(((float) h) / 2.0f);
        this.thumbBackground.setCornerRadius(((float) h) / 2.0f);
        int thumbHeight = getHeight();
        int padding = (thumbHeight - ((int) (((double) thumbHeight) * 0.6d))) / 2;
        if (padding > 0) {
            this.thumbView.setPadding(padding, padding, padding, padding);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getMeasuredWidth() <= 0 || getMeasuredHeight() == 16777215 || getMeasuredWidth() > getMeasuredHeight()) {
            int spec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
            this.thumbView.measure(spec, spec);
            return;
        }
        throw new IllegalStateException("Switch must wider than it is tall " + getMeasuredWidth() + "x" + getMeasuredHeight());
    }

    public void setChecked(boolean checked2) {
        setChecked(checked2, true);
    }

    public void setChecked(boolean checked2, boolean animated) {
        if (this.checked == null || this.checked.booleanValue() != checked2) {
            this.checked = Boolean.valueOf(checked2);
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
        return this.checked.booleanValue();
    }

    public void toggle() {
        setChecked(!this.checked.booleanValue());
    }

    public void setOnClickListener(OnClickListener l) {
        throw new IllegalStateException("You may not set a click listener on SmallSheetSwitchRowSwitch. Consider using a checked change listener.");
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener2) {
        this.listener = listener2;
    }

    private void updateColorsAndDrawable() {
        this.thumbView.setImageDrawable(this.checked.booleanValue() ? this.checkedDrawable : this.uncheckedDrawable);
        this.thumbBackground.setColor(((Integer) this.argbEvaluator.evaluate(this.thumbProgress, Integer.valueOf(this.thumbUncheckedColor), Integer.valueOf(this.thumbCheckedColor))).intValue());
        this.thumbView.setTranslationX(this.thumbProgress * ((float) (getWidth() - this.thumbView.getWidth())));
    }

    public static void mock(SmallSheetSwitchRowSwitch view) {
    }
}
