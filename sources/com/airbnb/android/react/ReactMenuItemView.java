package com.airbnb.android.react;

import android.content.Context;
import android.support.p002v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.airbnb.p027n2.components.AirToolbar.TintableMenuItem;

public class ReactMenuItemView extends AppCompatImageView implements TintableMenuItem {
    public ReactMenuItemView(Context context) {
        super(context);
    }

    public ReactMenuItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReactMenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setForegroundColor(int color) {
    }
}
