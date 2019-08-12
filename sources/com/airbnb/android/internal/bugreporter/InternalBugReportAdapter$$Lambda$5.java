package com.airbnb.android.internal.bugreporter;

import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;

final /* synthetic */ class InternalBugReportAdapter$$Lambda$5 implements OnInputChangedListener {
    private final InternalBugReportAdapter arg$1;

    private InternalBugReportAdapter$$Lambda$5(InternalBugReportAdapter internalBugReportAdapter) {
        this.arg$1 = internalBugReportAdapter;
    }

    public static OnInputChangedListener lambdaFactory$(InternalBugReportAdapter internalBugReportAdapter) {
        return new InternalBugReportAdapter$$Lambda$5(internalBugReportAdapter);
    }

    public void onInputChanged(String str) {
        this.arg$1.details = str;
    }
}
