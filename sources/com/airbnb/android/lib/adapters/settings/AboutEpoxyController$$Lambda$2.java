package com.airbnb.android.lib.adapters.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AboutEpoxyController$$Lambda$2 implements OnClickListener {
    private final AboutEpoxyController arg$1;

    private AboutEpoxyController$$Lambda$2(AboutEpoxyController aboutEpoxyController) {
        this.arg$1 = aboutEpoxyController;
    }

    public static OnClickListener lambdaFactory$(AboutEpoxyController aboutEpoxyController) {
        return new AboutEpoxyController$$Lambda$2(aboutEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.onWhyHostClicked();
    }
}
