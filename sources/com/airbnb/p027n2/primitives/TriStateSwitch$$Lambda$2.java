package com.airbnb.p027n2.primitives;

import com.airbnb.p027n2.primitives.TriStateSwitchHalf.OnCheckedChangeListener;

/* renamed from: com.airbnb.n2.primitives.TriStateSwitch$$Lambda$2 */
final /* synthetic */ class TriStateSwitch$$Lambda$2 implements OnCheckedChangeListener {
    private final TriStateSwitch arg$1;

    private TriStateSwitch$$Lambda$2(TriStateSwitch triStateSwitch) {
        this.arg$1 = triStateSwitch;
    }

    public static OnCheckedChangeListener lambdaFactory$(TriStateSwitch triStateSwitch) {
        return new TriStateSwitch$$Lambda$2(triStateSwitch);
    }

    public void onCheckedChanged(TriStateSwitchHalf triStateSwitchHalf, boolean z) {
        TriStateSwitch.lambda$setListeners$1(this.arg$1, triStateSwitchHalf, z);
    }
}
