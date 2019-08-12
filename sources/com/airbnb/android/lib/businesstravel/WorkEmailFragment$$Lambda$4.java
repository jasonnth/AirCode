package com.airbnb.android.lib.businesstravel;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class WorkEmailFragment$$Lambda$4 implements Action1 {
    private final WorkEmailFragment arg$1;

    private WorkEmailFragment$$Lambda$4(WorkEmailFragment workEmailFragment) {
        this.arg$1 = workEmailFragment;
    }

    public static Action1 lambdaFactory$(WorkEmailFragment workEmailFragment) {
        return new WorkEmailFragment$$Lambda$4(workEmailFragment);
    }

    public void call(Object obj) {
        this.arg$1.onRemoveWorkEmailError((AirRequestNetworkException) obj);
    }
}
