package com.airbnb.p027n2.components;

import com.airbnb.p027n2.components.SmallSheetSwitchRowSwitch.OnCheckedChangeListener;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;

/* renamed from: com.airbnb.n2.components.SmallSheetSwitchRow$$Lambda$1 */
final /* synthetic */ class SmallSheetSwitchRow$$Lambda$1 implements OnCheckedChangeListener {
    private final SmallSheetSwitchRow arg$1;
    private final SwitchRowInterface.OnCheckedChangeListener arg$2;

    private SmallSheetSwitchRow$$Lambda$1(SmallSheetSwitchRow smallSheetSwitchRow, SwitchRowInterface.OnCheckedChangeListener onCheckedChangeListener) {
        this.arg$1 = smallSheetSwitchRow;
        this.arg$2 = onCheckedChangeListener;
    }

    public static OnCheckedChangeListener lambdaFactory$(SmallSheetSwitchRow smallSheetSwitchRow, SwitchRowInterface.OnCheckedChangeListener onCheckedChangeListener) {
        return new SmallSheetSwitchRow$$Lambda$1(smallSheetSwitchRow, onCheckedChangeListener);
    }

    public void onCheckedChanged(SmallSheetSwitchRowSwitch smallSheetSwitchRowSwitch, boolean z) {
        this.arg$2.onCheckedChanged(this.arg$1, z);
    }
}
