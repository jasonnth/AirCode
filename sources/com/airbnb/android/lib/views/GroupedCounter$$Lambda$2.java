package com.airbnb.android.lib.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GroupedCounter$$Lambda$2 implements OnClickListener {
    private final GroupedCounter arg$1;

    private GroupedCounter$$Lambda$2(GroupedCounter groupedCounter) {
        this.arg$1 = groupedCounter;
    }

    public static OnClickListener lambdaFactory$(GroupedCounter groupedCounter) {
        return new GroupedCounter$$Lambda$2(groupedCounter);
    }

    public void onClick(View view) {
        this.arg$1.setSelectedValue(this.arg$1.selectedValue + 1);
    }
}
