package com.airbnb.android.managelisting.settings;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class ManageListingInstantBookSettingsAdapter$$Lambda$3 implements OnCheckedChangeListener {
    private final ManageListingInstantBookSettingsAdapter arg$1;

    private ManageListingInstantBookSettingsAdapter$$Lambda$3(ManageListingInstantBookSettingsAdapter manageListingInstantBookSettingsAdapter) {
        this.arg$1 = manageListingInstantBookSettingsAdapter;
    }

    public static OnCheckedChangeListener lambdaFactory$(ManageListingInstantBookSettingsAdapter manageListingInstantBookSettingsAdapter) {
        return new ManageListingInstantBookSettingsAdapter$$Lambda$3(manageListingInstantBookSettingsAdapter);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        this.arg$1.requirementChecked(z);
    }
}
