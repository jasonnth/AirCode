package com.airbnb.android.lib.utils.animation;

import com.airbnb.android.lib.views.GroupedRadioCheck;
import com.airbnb.android.lib.views.GroupedRadioCheck.OnCheckedChangeListener;

final /* synthetic */ class GroupedRadioCheckManager$$Lambda$1 implements OnCheckedChangeListener {
    private final GroupedRadioCheckManager arg$1;

    private GroupedRadioCheckManager$$Lambda$1(GroupedRadioCheckManager groupedRadioCheckManager) {
        this.arg$1 = groupedRadioCheckManager;
    }

    public static OnCheckedChangeListener lambdaFactory$(GroupedRadioCheckManager groupedRadioCheckManager) {
        return new GroupedRadioCheckManager$$Lambda$1(groupedRadioCheckManager);
    }

    public void onCheckedChanged(GroupedRadioCheck groupedRadioCheck, boolean z) {
        GroupedRadioCheckManager.lambda$new$0(this.arg$1, groupedRadioCheck, z);
    }
}
