package com.airbnb.android.lib.paidamenities.fragments.create;

import com.airbnb.android.lib.paidamenities.responses.PaidAmenityTypesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class SelectAmenityTypeFragment$$Lambda$1 implements Action1 {
    private final SelectAmenityTypeFragment arg$1;

    private SelectAmenityTypeFragment$$Lambda$1(SelectAmenityTypeFragment selectAmenityTypeFragment) {
        this.arg$1 = selectAmenityTypeFragment;
    }

    public static Action1 lambdaFactory$(SelectAmenityTypeFragment selectAmenityTypeFragment) {
        return new SelectAmenityTypeFragment$$Lambda$1(selectAmenityTypeFragment);
    }

    public void call(Object obj) {
        SelectAmenityTypeFragment.lambda$new$0(this.arg$1, (PaidAmenityTypesResponse) obj);
    }
}
