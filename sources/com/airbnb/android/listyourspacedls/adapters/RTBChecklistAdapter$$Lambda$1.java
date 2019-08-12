package com.airbnb.android.listyourspacedls.adapters;

import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.components.ToggleActionRow.OnCheckedChangeListener;

final /* synthetic */ class RTBChecklistAdapter$$Lambda$1 implements OnCheckedChangeListener {
    private final RTBChecklistAdapter arg$1;

    private RTBChecklistAdapter$$Lambda$1(RTBChecklistAdapter rTBChecklistAdapter) {
        this.arg$1 = rTBChecklistAdapter;
    }

    public static OnCheckedChangeListener lambdaFactory$(RTBChecklistAdapter rTBChecklistAdapter) {
        return new RTBChecklistAdapter$$Lambda$1(rTBChecklistAdapter);
    }

    public void onCheckedChanged(ToggleActionRow toggleActionRow, boolean z) {
        RTBChecklistAdapter.lambda$new$0(this.arg$1, toggleActionRow, z);
    }
}
