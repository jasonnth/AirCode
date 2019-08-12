package com.airbnb.p027n2.primitives;

import com.airbnb.p027n2.primitives.TriStateSwitchHalf.OnCheckedChangeListener;

/* renamed from: com.airbnb.n2.primitives.TriStateSwitch$$Lambda$1 */
final /* synthetic */ class TriStateSwitch$$Lambda$1 implements OnCheckedChangeListener {
    private final TriStateSwitch arg$1;

    private TriStateSwitch$$Lambda$1(TriStateSwitch triStateSwitch) {
        this.arg$1 = triStateSwitch;
    }

    public static OnCheckedChangeListener lambdaFactory$(TriStateSwitch triStateSwitch) {
        return new TriStateSwitch$$Lambda$1(triStateSwitch);
    }

    public void onCheckedChanged(TriStateSwitchHalf triStateSwitchHalf, boolean z) {
        TriStateSwitch.lambda$setListeners$0(this.arg$1, triStateSwitchHalf, z);
    }
}
