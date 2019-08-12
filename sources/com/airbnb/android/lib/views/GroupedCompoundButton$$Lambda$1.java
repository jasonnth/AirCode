package com.airbnb.android.lib.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GroupedCompoundButton$$Lambda$1 implements OnClickListener {
    private final GroupedCompoundButton arg$1;

    private GroupedCompoundButton$$Lambda$1(GroupedCompoundButton groupedCompoundButton) {
        this.arg$1 = groupedCompoundButton;
    }

    public static OnClickListener lambdaFactory$(GroupedCompoundButton groupedCompoundButton) {
        return new GroupedCompoundButton$$Lambda$1(groupedCompoundButton);
    }

    public void onClick(View view) {
        this.arg$1.toggle();
    }
}
