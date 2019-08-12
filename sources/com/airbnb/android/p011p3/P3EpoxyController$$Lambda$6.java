package com.airbnb.android.p011p3;

import com.airbnb.android.core.models.UrgencyMessageData;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.OnModelBoundListener;

/* renamed from: com.airbnb.android.p3.P3EpoxyController$$Lambda$6 */
final /* synthetic */ class P3EpoxyController$$Lambda$6 implements OnModelBoundListener {
    private final P3EpoxyController arg$1;
    private final UrgencyMessageData arg$2;

    private P3EpoxyController$$Lambda$6(P3EpoxyController p3EpoxyController, UrgencyMessageData urgencyMessageData) {
        this.arg$1 = p3EpoxyController;
        this.arg$2 = urgencyMessageData;
    }

    public static OnModelBoundListener lambdaFactory$(P3EpoxyController p3EpoxyController, UrgencyMessageData urgencyMessageData) {
        return new P3EpoxyController$$Lambda$6(p3EpoxyController, urgencyMessageData);
    }

    public void onModelBound(EpoxyModel epoxyModel, Object obj, int i) {
        this.arg$1.controller.getAnalytics().trackUrgencyImpression(this.arg$2);
    }
}
