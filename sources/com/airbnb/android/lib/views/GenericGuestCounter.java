package com.airbnb.android.lib.views;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.lib.C0880R;

public class GenericGuestCounter extends GroupedCounterRound {
    public GenericGuestCounter(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public GenericGuestCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GenericGuestCounter(Context context) {
        super(context);
        init();
    }

    private void init() {
        setQuantityStringResId(C0880R.plurals.x_guests);
        setMaxValue(getContext().getResources().getInteger(C0880R.integer.max_num_guests));
        int min = getContext().getResources().getInteger(C0880R.integer.min_num_guests);
        setMinValue(min);
        setSelectedValue(min);
    }
}
