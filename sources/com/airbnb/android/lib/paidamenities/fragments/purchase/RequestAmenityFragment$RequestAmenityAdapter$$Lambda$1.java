package com.airbnb.android.lib.paidamenities.fragments.purchase;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.PaidAmenity;
import com.airbnb.android.lib.paidamenities.fragments.purchase.RequestAmenityFragment.RequestAmenityAdapter;

final /* synthetic */ class RequestAmenityFragment$RequestAmenityAdapter$$Lambda$1 implements OnClickListener {
    private final RequestAmenityAdapter arg$1;
    private final PaidAmenity arg$2;

    private RequestAmenityFragment$RequestAmenityAdapter$$Lambda$1(RequestAmenityAdapter requestAmenityAdapter, PaidAmenity paidAmenity) {
        this.arg$1 = requestAmenityAdapter;
        this.arg$2 = paidAmenity;
    }

    public static OnClickListener lambdaFactory$(RequestAmenityAdapter requestAmenityAdapter, PaidAmenity paidAmenity) {
        return new RequestAmenityFragment$RequestAmenityAdapter$$Lambda$1(requestAmenityAdapter, paidAmenity);
    }

    public void onClick(View view) {
        RequestAmenityAdapter.lambda$paidAmenityToRowEpoxyModel$0(this.arg$1, this.arg$2, view);
    }
}
