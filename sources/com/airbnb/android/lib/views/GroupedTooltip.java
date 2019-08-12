package com.airbnb.android.lib.views;

import android.content.Context;
import android.support.p002v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.utils.ColorizedDrawable;

public class GroupedTooltip extends AppCompatImageView {
    public GroupedTooltip(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public GroupedTooltip(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GroupedTooltip(Context context) {
        this(context, null);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        setVisibility(4);
    }

    public void setOnClickListener(OnClickListener listener) {
        super.setOnClickListener(listener);
        if (getDrawable() == null) {
            setTooltipIcon(C0880R.C0881drawable.ic_tooltip, C0880R.color.c_foggy, true);
        }
    }

    public void setTooltipIcon(int overrideDrawable, int overrideColor, boolean withPressState) {
        if (overrideDrawable > 0) {
            if (overrideColor > 0) {
                setImageDrawable(ColorizedDrawable.forIdWithColor(getContext(), overrideDrawable, overrideColor));
            } else {
                setImageResource(overrideDrawable);
            }
            if (withPressState) {
                setBackgroundResource(C0880R.C0881drawable.blue_halo_overlay_selector);
            }
            setVisibility(0);
        }
    }
}
