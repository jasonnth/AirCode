package com.airbnb.android.lib.adapters.settings;

import android.view.View;
import android.view.View.OnLongClickListener;

final /* synthetic */ class AdvancedSettingsEpoxyController$$Lambda$3 implements OnLongClickListener {
    private final AdvancedSettingsEpoxyController arg$1;

    private AdvancedSettingsEpoxyController$$Lambda$3(AdvancedSettingsEpoxyController advancedSettingsEpoxyController) {
        this.arg$1 = advancedSettingsEpoxyController;
    }

    public static OnLongClickListener lambdaFactory$(AdvancedSettingsEpoxyController advancedSettingsEpoxyController) {
        return new AdvancedSettingsEpoxyController$$Lambda$3(advancedSettingsEpoxyController);
    }

    public boolean onLongClick(View view) {
        return this.arg$1.listener.onBandwidthModeLongClicked();
    }
}
