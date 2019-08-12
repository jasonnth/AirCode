package com.airbnb.android.lib.businesstravel;

import com.airbnb.android.lib.businesstravel.network.AddWorkEmailResponse;
import p032rx.functions.Action1;

final /* synthetic */ class WorkEmailFragment$$Lambda$1 implements Action1 {
    private final WorkEmailFragment arg$1;

    private WorkEmailFragment$$Lambda$1(WorkEmailFragment workEmailFragment) {
        this.arg$1 = workEmailFragment;
    }

    public static Action1 lambdaFactory$(WorkEmailFragment workEmailFragment) {
        return new WorkEmailFragment$$Lambda$1(workEmailFragment);
    }

    public void call(Object obj) {
        this.arg$1.onAddWorkEmailSuccess((AddWorkEmailResponse) obj);
    }
}
