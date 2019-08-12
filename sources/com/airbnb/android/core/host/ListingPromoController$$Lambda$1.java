package com.airbnb.android.core.host;

import com.airbnb.android.core.models.ListingPickerInfo;
import com.airbnb.android.core.responses.ListingPickerInfoResponse;
import com.google.common.collect.FluentIterable;
import p032rx.functions.Action1;

final /* synthetic */ class ListingPromoController$$Lambda$1 implements Action1 {
    private final ListingPromoController arg$1;

    private ListingPromoController$$Lambda$1(ListingPromoController listingPromoController) {
        this.arg$1 = listingPromoController;
    }

    public static Action1 lambdaFactory$(ListingPromoController listingPromoController) {
        return new ListingPromoController$$Lambda$1(listingPromoController);
    }

    public void call(Object obj) {
        this.arg$1.listing = (ListingPickerInfo) FluentIterable.from((Iterable<E>) ((ListingPickerInfoResponse) obj).getListings()).filter(ListingPromoController$$Lambda$2.lambdaFactory$()).first().orNull();
    }
}
