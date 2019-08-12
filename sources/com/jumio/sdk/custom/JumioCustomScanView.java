package com.jumio.sdk.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;

public abstract class JumioCustomScanView extends RelativeLayout {
    protected static final float MIN_RATIO = 1.33f;
    protected float ratio = MIN_RATIO;

    /* access modifiers changed from: protected */
    public abstract void init(Context context, AttributeSet attributeSet);

    public JumioCustomScanView(Context context) {
        super(context);
    }

    public JumioCustomScanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public JumioCustomScanView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public float getRatio() {
        return this.ratio;
    }

    public void setRatio(float ratio2) {
        this.ratio = ratio2;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = widthSize;
        int height = MeasureSpec.getSize(heightMeasureSpec);
        boolean isPortrait = getContext().getResources().getConfiguration().orientation == 1;
        if (isPortrait && this.ratio != 0.0f && this.ratio > MIN_RATIO) {
            throw new IllegalArgumentException("Portrait ratio must be <= 1.33");
        } else if (isPortrait || this.ratio == 0.0f || this.ratio >= MIN_RATIO) {
            boolean widthVariable = widthMode == Integer.MIN_VALUE || widthMode == 0;
            boolean heightVariable = heightMode == Integer.MIN_VALUE || heightMode == 0;
            if (width != 0 && heightVariable && this.ratio != 0.0f) {
                height = (int) (((float) width) / this.ratio);
            } else if (widthVariable && height != 0 && this.ratio != 0.0f) {
                width = (int) (((float) height) * this.ratio);
            } else if (widthVariable && heightVariable && this.ratio != 0.0f) {
                throw new IllegalArgumentException("layout_width or layout_height should be set to a fixed value when ratio is used");
            } else if ((widthVariable || heightVariable) && this.ratio == 0.0f) {
                throw new IllegalArgumentException("ratio should be set");
            }
            super.onMeasure(MeasureSpec.makeMeasureSpec(width, 1073741824), MeasureSpec.makeMeasureSpec(height, 1073741824));
        } else {
            throw new IllegalArgumentException("Landscape ratio must be >= 1.33");
        }
    }
}
