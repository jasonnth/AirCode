package com.airbnb.android.lib.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GroupedCounterFloats$$Lambda$2 implements OnClickListener {
    private final GroupedCounterFloats arg$1;

    private GroupedCounterFloats$$Lambda$2(GroupedCounterFloats groupedCounterFloats) {
        this.arg$1 = groupedCounterFloats;
    }

    public static OnClickListener lambdaFactory$(GroupedCounterFloats groupedCounterFloats) {
        return new GroupedCounterFloats$$Lambda$2(groupedCounterFloats);
    }

    public void onClick(View view) {
        this.arg$1.superSetSelectedValue(this.arg$1.selectedValue + 1);
    }
}
