package com.airbnb.p027n2.collections;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.collections.SheetScrollView */
public class SheetScrollView extends VerboseScrollView {
    public SheetScrollView(Context context) {
        super(context);
    }

    public SheetScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SheetScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (h < getChildAt(0).getHeight()) {
            showBackground();
        } else {
            hideBackground();
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (getChildAt(0).getBottom() - (getHeight() + getScrollY()) <= 0) {
            hideBackground();
        } else {
            showBackground();
        }
    }

    private void showBackground() {
        setBackgroundResource(R.drawable.n2_sheet_scroll_view_background);
    }

    private void hideBackground() {
        setBackground(null);
    }
}
