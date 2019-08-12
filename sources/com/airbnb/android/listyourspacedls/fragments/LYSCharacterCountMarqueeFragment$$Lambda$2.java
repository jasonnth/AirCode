package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSCharacterCountMarqueeFragment$$Lambda$2 implements Action1 {
    private final LYSCharacterCountMarqueeFragment arg$1;

    private LYSCharacterCountMarqueeFragment$$Lambda$2(LYSCharacterCountMarqueeFragment lYSCharacterCountMarqueeFragment) {
        this.arg$1 = lYSCharacterCountMarqueeFragment;
    }

    public static Action1 lambdaFactory$(LYSCharacterCountMarqueeFragment lYSCharacterCountMarqueeFragment) {
        return new LYSCharacterCountMarqueeFragment$$Lambda$2(lYSCharacterCountMarqueeFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
