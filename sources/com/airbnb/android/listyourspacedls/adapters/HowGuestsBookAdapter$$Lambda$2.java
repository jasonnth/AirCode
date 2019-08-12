package com.airbnb.android.listyourspacedls.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HowGuestsBookAdapter$$Lambda$2 implements OnClickListener {
    private final HowGuestsBookAdapter arg$1;
    private final boolean arg$2;

    private HowGuestsBookAdapter$$Lambda$2(HowGuestsBookAdapter howGuestsBookAdapter, boolean z) {
        this.arg$1 = howGuestsBookAdapter;
        this.arg$2 = z;
    }

    public static OnClickListener lambdaFactory$(HowGuestsBookAdapter howGuestsBookAdapter, boolean z) {
        return new HowGuestsBookAdapter$$Lambda$2(howGuestsBookAdapter, z);
    }

    public void onClick(View view) {
        HowGuestsBookAdapter.lambda$updateModels$1(this.arg$1, this.arg$2, view);
    }
}
