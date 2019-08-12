package com.airbnb.android.cohosting.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ListingManagersPickerAdapter$$Lambda$2 implements OnClickListener {
    private final ListingManagersPickerAdapter arg$1;

    private ListingManagersPickerAdapter$$Lambda$2(ListingManagersPickerAdapter listingManagersPickerAdapter) {
        this.arg$1 = listingManagersPickerAdapter;
    }

    public static OnClickListener lambdaFactory$(ListingManagersPickerAdapter listingManagersPickerAdapter) {
        return new ListingManagersPickerAdapter$$Lambda$2(listingManagersPickerAdapter);
    }

    public void onClick(View view) {
        ListingManagersPickerAdapter.lambda$displayEmptyState$1(this.arg$1, view);
    }
}
