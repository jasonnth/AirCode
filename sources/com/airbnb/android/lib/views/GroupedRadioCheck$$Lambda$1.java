package com.airbnb.android.lib.views;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

final /* synthetic */ class GroupedRadioCheck$$Lambda$1 implements OnCheckedChangeListener {
    private final GroupedRadioCheck arg$1;
    private final GroupedRadioCheck.OnCheckedChangeListener arg$2;

    private GroupedRadioCheck$$Lambda$1(GroupedRadioCheck groupedRadioCheck, GroupedRadioCheck.OnCheckedChangeListener onCheckedChangeListener) {
        this.arg$1 = groupedRadioCheck;
        this.arg$2 = onCheckedChangeListener;
    }

    public static OnCheckedChangeListener lambdaFactory$(GroupedRadioCheck groupedRadioCheck, GroupedRadioCheck.OnCheckedChangeListener onCheckedChangeListener) {
        return new GroupedRadioCheck$$Lambda$1(groupedRadioCheck, onCheckedChangeListener);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.arg$2.onCheckedChanged(this.arg$1, z);
    }
}
