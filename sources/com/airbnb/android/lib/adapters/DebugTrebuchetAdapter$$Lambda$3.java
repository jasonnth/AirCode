package com.airbnb.android.lib.adapters;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class DebugTrebuchetAdapter$$Lambda$3 implements OnCheckedChangeListener {
    private final DebugTrebuchetAdapter arg$1;
    private final String arg$2;

    private DebugTrebuchetAdapter$$Lambda$3(DebugTrebuchetAdapter debugTrebuchetAdapter, String str) {
        this.arg$1 = debugTrebuchetAdapter;
        this.arg$2 = str;
    }

    public static OnCheckedChangeListener lambdaFactory$(DebugTrebuchetAdapter debugTrebuchetAdapter, String str) {
        return new DebugTrebuchetAdapter$$Lambda$3(debugTrebuchetAdapter, str);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        DebugTrebuchetAdapter.lambda$rebuildModels$1(this.arg$1, this.arg$2, switchRowInterface, z);
    }
}
