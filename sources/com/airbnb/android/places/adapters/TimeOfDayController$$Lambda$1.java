package com.airbnb.android.places.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.places.adapters.TimeOfDayController.TimeOfDay;

final /* synthetic */ class TimeOfDayController$$Lambda$1 implements OnClickListener {
    private final TimeOfDayController arg$1;
    private final TimeOfDay arg$2;
    private final int arg$3;

    private TimeOfDayController$$Lambda$1(TimeOfDayController timeOfDayController, TimeOfDay timeOfDay, int i) {
        this.arg$1 = timeOfDayController;
        this.arg$2 = timeOfDay;
        this.arg$3 = i;
    }

    public static OnClickListener lambdaFactory$(TimeOfDayController timeOfDayController, TimeOfDay timeOfDay, int i) {
        return new TimeOfDayController$$Lambda$1(timeOfDayController, timeOfDay, i);
    }

    public void onClick(View view) {
        this.arg$1.onTimeSelectedListener.onTimeTapped(this.arg$2, this.arg$3);
    }
}
