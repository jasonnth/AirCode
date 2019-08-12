package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.SmallSheetSwitchRowSwitch$$Lambda$4 */
final /* synthetic */ class SmallSheetSwitchRowSwitch$$Lambda$4 implements OnClickListener {
    private final SmallSheetSwitchRowSwitch arg$1;

    private SmallSheetSwitchRowSwitch$$Lambda$4(SmallSheetSwitchRowSwitch smallSheetSwitchRowSwitch) {
        this.arg$1 = smallSheetSwitchRowSwitch;
    }

    public static OnClickListener lambdaFactory$(SmallSheetSwitchRowSwitch smallSheetSwitchRowSwitch) {
        return new SmallSheetSwitchRowSwitch$$Lambda$4(smallSheetSwitchRowSwitch);
    }

    public void onClick(View view) {
        this.arg$1.toggle();
    }
}
