package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.fragments.HourPickerDialog.HourAdapter;

final /* synthetic */ class HourPickerDialog$HourAdapter$$Lambda$1 implements OnClickListener {
    private final HourAdapter arg$1;
    private final int arg$2;

    private HourPickerDialog$HourAdapter$$Lambda$1(HourAdapter hourAdapter, int i) {
        this.arg$1 = hourAdapter;
        this.arg$2 = i;
    }

    public static OnClickListener lambdaFactory$(HourAdapter hourAdapter, int i) {
        return new HourPickerDialog$HourAdapter$$Lambda$1(hourAdapter, i);
    }

    public void onClick(View view) {
        HourPickerDialog.this.onHourSelected(((Integer) HourPickerDialog.this.hours.get(this.arg$2)).intValue());
    }
}
