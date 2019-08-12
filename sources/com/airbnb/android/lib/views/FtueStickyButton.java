package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.lib.C0880R;

public class FtueStickyButton extends StickyButton {
    public FtueStickyButton(Context context) {
        super(context);
        init();
    }

    public FtueStickyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FtueStickyButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setCustomBackgroundColorResource(C0880R.color.transparent);
    }
}
