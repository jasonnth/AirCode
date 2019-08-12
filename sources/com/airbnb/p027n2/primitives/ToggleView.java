package com.airbnb.p027n2.primitives;

import android.content.Context;
import android.support.p002v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.primitives.ToggleView */
public class ToggleView extends CompoundButton {
    public ToggleView(Context context) {
        super(context);
        init();
    }

    public ToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToggleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setButtonDrawable(AppCompatResources.getDrawable(getContext(), R.drawable.n2_toggle_button));
    }
}
