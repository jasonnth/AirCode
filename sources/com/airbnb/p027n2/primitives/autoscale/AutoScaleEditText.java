package com.airbnb.p027n2.primitives.autoscale;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.p027n2.primitives.AirEditTextView;

/* renamed from: com.airbnb.n2.primitives.autoscale.AutoScaleEditText */
public class AutoScaleEditText extends AirEditTextView {
    private AutoScaleViewHelper autoScaleViewHelper;

    public AutoScaleEditText(Context context) {
        this(context, null);
    }

    public AutoScaleEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public AutoScaleEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        this.autoScaleViewHelper = new AutoScaleViewHelper(this, attrs);
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (this.autoScaleViewHelper != null) {
            this.autoScaleViewHelper.onTextChanged();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (this.autoScaleViewHelper != null) {
            this.autoScaleViewHelper.onLayout(left, right);
        }
        super.onLayout(changed, left, top, right, bottom);
    }
}
