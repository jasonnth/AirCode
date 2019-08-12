package com.airbnb.android.guestrecovery.adapter;

import android.view.View;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.CarouselItemClickListener;
import com.airbnb.android.core.adapters.ListingTrayCarouselAdapter.ListingTrayItem;

final /* synthetic */ class GuestRecoveryEpoxyController$$Lambda$1 implements CarouselItemClickListener {
    private final GuestRecoveryEpoxyController arg$1;

    private GuestRecoveryEpoxyController$$Lambda$1(GuestRecoveryEpoxyController guestRecoveryEpoxyController) {
        this.arg$1 = guestRecoveryEpoxyController;
    }

    public static CarouselItemClickListener lambdaFactory$(GuestRecoveryEpoxyController guestRecoveryEpoxyController) {
        return new GuestRecoveryEpoxyController$$Lambda$1(guestRecoveryEpoxyController);
    }

    public void onCarouselItemClicked(View view, ListingTrayItem listingTrayItem) {
        GuestRecoveryEpoxyController.lambda$populateSimilarListingsModel$0(this.arg$1, view, listingTrayItem);
    }
}
