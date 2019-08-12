package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.p027n2.interfaces.ThreeWayToggle.ToggleState;
import com.airbnb.p027n2.primitives.TriStateSwitch;
import com.airbnb.p027n2.primitives.TriStateSwitch.OnCheckedChangeListener;

final /* synthetic */ class TriStateSwitchRowEpoxyModel$$Lambda$1 implements OnCheckedChangeListener {
    private final TriStateSwitchRowEpoxyModel arg$1;

    private TriStateSwitchRowEpoxyModel$$Lambda$1(TriStateSwitchRowEpoxyModel triStateSwitchRowEpoxyModel) {
        this.arg$1 = triStateSwitchRowEpoxyModel;
    }

    public static OnCheckedChangeListener lambdaFactory$(TriStateSwitchRowEpoxyModel triStateSwitchRowEpoxyModel) {
        return new TriStateSwitchRowEpoxyModel$$Lambda$1(triStateSwitchRowEpoxyModel);
    }

    public void onCheckedChanged(TriStateSwitch triStateSwitch, ToggleState toggleState) {
        this.arg$1.checkedChangedListenerWrapper(triStateSwitch, toggleState);
    }
}
