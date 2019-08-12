package com.devbrackets.android.exomedia.p306ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.RelativeLayout;

/* renamed from: com.devbrackets.android.exomedia.ui.widget.FitsSystemWindowRelativeLayout */
public class FitsSystemWindowRelativeLayout extends RelativeLayout {
    private Rect originalPadding;

    public FitsSystemWindowRelativeLayout(Context context) {
        super(context);
        setup();
    }

    public FitsSystemWindowRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public FitsSystemWindowRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    @TargetApi(21)
    public FitsSystemWindowRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (navBarCanMove()) {
            setup();
        }
    }

    /* access modifiers changed from: protected */
    public boolean fitSystemWindows(Rect insets) {
        updatePadding(insets);
        return false;
    }

    @TargetApi(20)
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        fitSystemWindows(new Rect(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop(), insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom()));
        return insets;
    }

    private void setup() {
        if (VERSION.SDK_INT >= 14) {
            setFitsSystemWindows(true);
        }
        if (this.originalPadding == null) {
            this.originalPadding = new Rect(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        updatePadding(new Rect());
    }

    private void updatePadding(Rect insets) {
        int topPadding = this.originalPadding.top + insets.top;
        setPadding(this.originalPadding.left, topPadding, this.originalPadding.right + insets.right, this.originalPadding.bottom + insets.bottom);
    }

    private boolean navBarCanMove() {
        if (VERSION.SDK_INT < 13 || getResources().getConfiguration().smallestScreenWidthDp > 600) {
            return false;
        }
        return true;
    }
}
