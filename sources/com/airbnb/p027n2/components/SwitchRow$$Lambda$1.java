package com.airbnb.p027n2.components;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.primitives.AirSwitch;
import com.airbnb.p027n2.primitives.AirSwitch.OnCheckedChangeListener;

/* renamed from: com.airbnb.n2.components.SwitchRow$$Lambda$1 */
final /* synthetic */ class SwitchRow$$Lambda$1 implements OnCheckedChangeListener {
    private final SwitchRow arg$1;
    private final SwitchRowInterface.OnCheckedChangeListener arg$2;

    private SwitchRow$$Lambda$1(SwitchRow switchRow, SwitchRowInterface.OnCheckedChangeListener onCheckedChangeListener) {
        this.arg$1 = switchRow;
        this.arg$2 = onCheckedChangeListener;
    }

    public static OnCheckedChangeListener lambdaFactory$(SwitchRow switchRow, SwitchRowInterface.OnCheckedChangeListener onCheckedChangeListener) {
        return new SwitchRow$$Lambda$1(switchRow, onCheckedChangeListener);
    }

    public void onCheckedChanged(AirSwitch airSwitch, boolean z) {
        this.arg$2.onCheckedChanged(this.arg$1, z);
    }
}
