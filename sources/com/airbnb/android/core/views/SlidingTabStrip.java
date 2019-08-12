package com.airbnb.android.core.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcelable;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.views.SlidingTabLayout.TabColorizer;
import icepick.State;

public class SlidingTabStrip extends LinearLayout {
    private static final byte DEFAULT_DIVIDER_COLOR_ALPHA = 32;
    private static final int DEFAULT_SELECTED_INDICATOR_COLOR = -13388315;
    private static final int SELECTED_INDICATOR_THICKNESS_DIPS = 2;
    private final Paint bottomDividerPaint;
    private final int bottomDividerThickness;
    private TabColorizer customTabColorizer;
    private final SimpleTabColorizer defaultTabColorizer;
    private final Paint selectedIndicatorPaint;
    private int selectedIndicatorThickness;
    @State
    int selectedPosition;
    @State
    float selectionOffset;
    private boolean showBottomDivider;

    private static class SimpleTabColorizer implements TabColorizer {
        private int[] mDividerColors;
        private int[] mIndicatorColors;

        private SimpleTabColorizer() {
        }

        public final int getIndicatorColor(int position) {
            return this.mIndicatorColors[position % this.mIndicatorColors.length];
        }

        public final int getDividerColor(int position) {
            return this.mDividerColors[position % this.mDividerColors.length];
        }

        /* access modifiers changed from: 0000 */
        public void setIndicatorColors(int... colors) {
            this.mIndicatorColors = colors;
        }

        /* access modifiers changed from: 0000 */
        public void setDividerColors(int... colors) {
            this.mDividerColors = colors;
        }
    }

    SlidingTabStrip(Context context) {
        this(context, null);
    }

    SlidingTabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        float density = getResources().getDisplayMetrics().density;
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(16842800, outValue, true);
        int themeForegroundColor = outValue.data;
        int bottomDividerColor = ContextCompat.getColor(getContext(), C0716R.color.n2_divider_color);
        this.defaultTabColorizer = new SimpleTabColorizer();
        this.defaultTabColorizer.setIndicatorColors(DEFAULT_SELECTED_INDICATOR_COLOR);
        this.defaultTabColorizer.setDividerColors(setColorAlpha(themeForegroundColor, 32));
        this.selectedIndicatorThickness = (int) (2.0f * density);
        this.selectedIndicatorPaint = new Paint();
        this.bottomDividerThickness = context.getResources().getDimensionPixelSize(C0716R.dimen.n2_divider_height);
        this.bottomDividerPaint = new Paint();
        this.bottomDividerPaint.setColor(bottomDividerColor);
    }

    public Parcelable onSaveInstanceState() {
        return IcepickWrapper.saveInstanceState(this, super.onSaveInstanceState());
    }

    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(IcepickWrapper.restoreInstanceState(this, state));
    }

    /* access modifiers changed from: 0000 */
    public void setCustomTabColorizer(TabColorizer customTabColorizer2) {
        this.customTabColorizer = customTabColorizer2;
        invalidate();
    }

    /* access modifiers changed from: 0000 */
    public void setSelectedIndicatorColors(int... colors) {
        this.customTabColorizer = null;
        this.defaultTabColorizer.setIndicatorColors(colors);
        invalidate();
    }

    /* access modifiers changed from: 0000 */
    public void setSelectedIndicatorThickness(int thickness) {
        this.selectedIndicatorThickness = thickness;
    }

    /* access modifiers changed from: 0000 */
    public void setDividerColors(int... colors) {
        this.customTabColorizer = null;
        this.defaultTabColorizer.setDividerColors(colors);
        invalidate();
    }

    /* access modifiers changed from: 0000 */
    public void showBottomDivider(boolean show) {
        this.showBottomDivider = show;
    }

    /* access modifiers changed from: 0000 */
    public void onViewPagerPageChanged(int position, float positionOffset) {
        this.selectedPosition = position;
        this.selectionOffset = positionOffset;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int height = getHeight();
        int childCount = getChildCount();
        TabColorizer tabColorizer = this.customTabColorizer != null ? this.customTabColorizer : this.defaultTabColorizer;
        if (childCount > 0) {
            View selectedTitle = getChildAt(this.selectedPosition);
            int left = selectedTitle.getLeft() + selectedTitle.getPaddingLeft();
            int right = selectedTitle.getRight() - selectedTitle.getPaddingRight();
            int color = tabColorizer.getIndicatorColor(this.selectedPosition);
            if (this.selectionOffset > 0.0f && this.selectedPosition < getChildCount() - 1) {
                int nextColor = tabColorizer.getIndicatorColor(this.selectedPosition + 1);
                if (color != nextColor) {
                    color = blendColors(nextColor, color, this.selectionOffset);
                }
                View nextTitle = getChildAt(this.selectedPosition + 1);
                left = (int) ((this.selectionOffset * ((float) (nextTitle.getLeft() + nextTitle.getPaddingLeft()))) + ((1.0f - this.selectionOffset) * ((float) left)));
                right = (int) ((this.selectionOffset * ((float) (nextTitle.getRight() - nextTitle.getPaddingRight()))) + ((1.0f - this.selectionOffset) * ((float) right)));
            }
            if (this.showBottomDivider) {
                canvas.drawRect(0.0f, (float) (height - this.bottomDividerThickness), (float) getWidth(), (float) height, this.bottomDividerPaint);
            }
            this.selectedIndicatorPaint.setColor(color);
            canvas.drawRect((float) left, (float) (height - this.selectedIndicatorThickness), (float) right, (float) height, this.selectedIndicatorPaint);
        }
    }

    private static int setColorAlpha(int color, byte alpha) {
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
    }

    private static int blendColors(int color1, int color2, float ratio) {
        float inverseRation = 1.0f - ratio;
        return Color.rgb((int) ((((float) Color.red(color1)) * ratio) + (((float) Color.red(color2)) * inverseRation)), (int) ((((float) Color.green(color1)) * ratio) + (((float) Color.green(color2)) * inverseRation)), (int) ((((float) Color.blue(color1)) * ratio) + (((float) Color.blue(color2)) * inverseRation)));
    }

    public void setSelectedPosition(int selectedPosition2) {
        this.selectedPosition = selectedPosition2;
    }
}
