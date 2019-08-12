package com.airbnb.android.lib.businesstravel;

import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;

final /* synthetic */ class WorkEmailEpoxyController$$Lambda$2 implements OnInputChangedListener {
    private final WorkEmailEpoxyController arg$1;

    private WorkEmailEpoxyController$$Lambda$2(WorkEmailEpoxyController workEmailEpoxyController) {
        this.arg$1 = workEmailEpoxyController;
    }

    public static OnInputChangedListener lambdaFactory$(WorkEmailEpoxyController workEmailEpoxyController) {
        return new WorkEmailEpoxyController$$Lambda$2(workEmailEpoxyController);
    }

    public void onInputChanged(String str) {
        this.arg$1.email = str;
    }
}
