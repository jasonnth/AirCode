package com.airbnb.android.hostcalendar.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.requests.DeleteSmartPromotionRequest;

final /* synthetic */ class HostSmartPromoAdapter$$Lambda$1 implements OnClickListener {
    private final HostSmartPromoAdapter arg$1;
    private final long arg$2;

    private HostSmartPromoAdapter$$Lambda$1(HostSmartPromoAdapter hostSmartPromoAdapter, long j) {
        this.arg$1 = hostSmartPromoAdapter;
        this.arg$2 = j;
    }

    public static OnClickListener lambdaFactory$(HostSmartPromoAdapter hostSmartPromoAdapter, long j) {
        return new HostSmartPromoAdapter$$Lambda$1(hostSmartPromoAdapter, j);
    }

    public void onClick(View view) {
        DeleteSmartPromotionRequest.forInsight(this.arg$2).execute(this.arg$1.requestManager);
    }
}
