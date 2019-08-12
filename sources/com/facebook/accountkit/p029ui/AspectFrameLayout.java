package com.facebook.accountkit.p029ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.facebook.accountkit.C3354R;

/* renamed from: com.facebook.accountkit.ui.AspectFrameLayout */
public final class AspectFrameLayout extends FrameLayout {
    private int aspectHeight;
    private int aspectWidth;
    private Point displaySize;

    public AspectFrameLayout(Context context) {
        super(context);
    }

    public AspectFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AspectFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public AspectFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, C3354R.styleable.AspectFrameLayout);
        try {
            this.aspectWidth = a.getDimensionPixelSize(C3354R.styleable.AspectFrameLayout_com_accountkit_aspect_width, 0);
            this.aspectHeight = a.getDimensionPixelSize(C3354R.styleable.AspectFrameLayout_com_accountkit_aspect_height, 0);
        } finally {
            a.recycle();
        }
    }

    public float getAspectHeight() {
        return (float) this.aspectHeight;
    }

    public void setAspectHeight(int aspectHeight2) {
        if (this.aspectHeight != aspectHeight2) {
            this.aspectHeight = aspectHeight2;
            requestLayout();
        }
    }

    public float getAspectWidth() {
        return (float) this.aspectWidth;
    }

    public void setAspectWidth(int aspectWidth2) {
        if (this.aspectWidth != aspectWidth2) {
            this.aspectWidth = aspectWidth2;
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Point displaySize2 = new Point();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getSize(displaySize2);
        this.displaySize = displaySize2;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        if (this.aspectWidth == 0 || this.aspectHeight == 0 || this.displaySize == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int scaledHeight = (this.displaySize.x * this.aspectHeight) / this.aspectWidth;
        if (scaledHeight > this.displaySize.y) {
            width = this.displaySize.x;
            height = scaledHeight;
        } else {
            width = (this.displaySize.y * this.aspectWidth) / this.aspectHeight;
            height = this.displaySize.y;
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(width, 1073741824), MeasureSpec.makeMeasureSpec(height, 1073741824));
    }
}
