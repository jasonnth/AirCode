package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.GuestControlType;
import com.airbnb.p027n2.interfaces.ThreeWayToggle.ToggleState;
import com.airbnb.p027n2.primitives.TriStateSwitch;
import com.airbnb.p027n2.primitives.TriStateSwitch.OnCheckedChangeListener;

final /* synthetic */ class HouseRulesSettingsAdapter$$Lambda$4 implements OnCheckedChangeListener {
    private final HouseRulesSettingsAdapter arg$1;
    private final GuestControlType arg$2;

    private HouseRulesSettingsAdapter$$Lambda$4(HouseRulesSettingsAdapter houseRulesSettingsAdapter, GuestControlType guestControlType) {
        this.arg$1 = houseRulesSettingsAdapter;
        this.arg$2 = guestControlType;
    }

    public static OnCheckedChangeListener lambdaFactory$(HouseRulesSettingsAdapter houseRulesSettingsAdapter, GuestControlType guestControlType) {
        return new HouseRulesSettingsAdapter$$Lambda$4(houseRulesSettingsAdapter, guestControlType);
    }

    public void onCheckedChanged(TriStateSwitch triStateSwitch, ToggleState toggleState) {
        this.arg$1.onCheckedChanged(this.arg$2, toggleState);
    }
}
