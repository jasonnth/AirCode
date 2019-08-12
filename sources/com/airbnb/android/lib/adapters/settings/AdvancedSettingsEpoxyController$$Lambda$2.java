package com.airbnb.android.lib.adapters.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AdvancedSettingsEpoxyController$$Lambda$2 implements OnClickListener {
    private final AdvancedSettingsEpoxyController arg$1;

    private AdvancedSettingsEpoxyController$$Lambda$2(AdvancedSettingsEpoxyController advancedSettingsEpoxyController) {
        this.arg$1 = advancedSettingsEpoxyController;
    }

    public static OnClickListener lambdaFactory$(AdvancedSettingsEpoxyController advancedSettingsEpoxyController) {
        return new AdvancedSettingsEpoxyController$$Lambda$2(advancedSettingsEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.onBandwidthModeChanged();
    }
}
