package com.airbnb.android.lib.adapters.settings;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class AdvancedSettingsEpoxyController$$Lambda$4 implements OnCheckedChangeListener {
    private final AdvancedSettingsEpoxyController arg$1;

    private AdvancedSettingsEpoxyController$$Lambda$4(AdvancedSettingsEpoxyController advancedSettingsEpoxyController) {
        this.arg$1 = advancedSettingsEpoxyController;
    }

    public static OnCheckedChangeListener lambdaFactory$(AdvancedSettingsEpoxyController advancedSettingsEpoxyController) {
        return new AdvancedSettingsEpoxyController$$Lambda$4(advancedSettingsEpoxyController);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        this.arg$1.shakeFeedbackSensorListener.setEnabled(z);
    }
}
