package com.facebook.accountkit.p029ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import com.facebook.accountkit.C3354R;

/* renamed from: com.facebook.accountkit.ui.ConstrainedLinearLayout */
public final class ConstrainedLinearLayout extends LinearLayout {
    private int maxHeight = -1;
    private int maxWidth = -1;
    private int minHeight = -1;

    public ConstrainedLinearLayout(Context context) {
        super(context);
    }

    public ConstrainedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ConstrainedLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public ConstrainedLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null && !isInEditMode()) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, C3354R.styleable.ConstrainedLinearLayout);
            try {
                this.maxHeight = typedArray.getDimensionPixelSize(C3354R.styleable.ConstrainedLinearLayout_com_accountkit_max_height, this.maxHeight);
                this.maxWidth = typedArray.getDimensionPixelSize(C3354R.styleable.ConstrainedLinearLayout_com_accountkit_max_width, this.maxWidth);
                this.minHeight = typedArray.getDimensionPixelSize(C3354R.styleable.ConstrainedLinearLayout_com_accountkit_min_height, this.minHeight);
            } finally {
                typedArray.recycle();
            }
        }
    }

    public int getMinHeight() {
        return this.minHeight;
    }

    public void setMinHeight(int minHeight2) {
        this.minHeight = minHeight2;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        boolean constrained = false;
        if (this.maxWidth >= 0 && width > this.maxWidth) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(this.maxWidth, 1073741824);
            constrained = true;
        }
        if (this.maxHeight >= 0 && height > this.maxHeight) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(this.maxHeight, 1073741824);
            constrained = true;
        }
        if (this.minHeight >= 0 && height < this.minHeight) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(this.minHeight, 1073741824);
            constrained = true;
        }
        if (constrained) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
