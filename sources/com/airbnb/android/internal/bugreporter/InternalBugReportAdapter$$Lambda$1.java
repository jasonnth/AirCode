package com.airbnb.android.internal.bugreporter;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class InternalBugReportAdapter$$Lambda$1 implements OnCheckedChangeListener {
    private final InternalBugReportAdapter arg$1;

    private InternalBugReportAdapter$$Lambda$1(InternalBugReportAdapter internalBugReportAdapter) {
        this.arg$1 = internalBugReportAdapter;
    }

    public static OnCheckedChangeListener lambdaFactory$(InternalBugReportAdapter internalBugReportAdapter) {
        return new InternalBugReportAdapter$$Lambda$1(internalBugReportAdapter);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        this.arg$1.includeUserInfo = z;
    }
}
