package com.airbnb.android.p011p3;

import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.p3.P3Fragment$$Lambda$2 */
final /* synthetic */ class P3Fragment$$Lambda$2 implements Action1 {
    private final P3Fragment arg$1;

    private P3Fragment$$Lambda$2(P3Fragment p3Fragment) {
        this.arg$1 = p3Fragment;
    }

    public static Action1 lambdaFactory$(P3Fragment p3Fragment) {
        return new P3Fragment$$Lambda$2(p3Fragment);
    }

    public void call(Object obj) {
        this.arg$1.bookButton.setReferralCredit("");
    }
}
