package com.airbnb.android.lib.adapters.settings;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class AdvancedSettingsEpoxyController$$Lambda$1 implements OnCheckedChangeListener {
    private final AdvancedSettingsEpoxyController arg$1;

    private AdvancedSettingsEpoxyController$$Lambda$1(AdvancedSettingsEpoxyController advancedSettingsEpoxyController) {
        this.arg$1 = advancedSettingsEpoxyController;
    }

    public static OnCheckedChangeListener lambdaFactory$(AdvancedSettingsEpoxyController advancedSettingsEpoxyController) {
        return new AdvancedSettingsEpoxyController$$Lambda$1(advancedSettingsEpoxyController);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        this.arg$1.listener.onFontOverrideSettingsClicked(z);
    }
}
