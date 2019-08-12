package com.airbnb.android.cohosting.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ListingManager;

final /* synthetic */ class ListingManagersPickerAdapter$$Lambda$4 implements OnClickListener {
    private final ListingManagersPickerAdapter arg$1;
    private final ListingManager arg$2;

    private ListingManagersPickerAdapter$$Lambda$4(ListingManagersPickerAdapter listingManagersPickerAdapter, ListingManager listingManager) {
        this.arg$1 = listingManagersPickerAdapter;
        this.arg$2 = listingManager;
    }

    public static OnClickListener lambdaFactory$(ListingManagersPickerAdapter listingManagersPickerAdapter, ListingManager listingManager) {
        return new ListingManagersPickerAdapter$$Lambda$4(listingManagersPickerAdapter, listingManager);
    }

    public void onClick(View view) {
        ListingManagersPickerAdapter.lambda$addListingManagerModels$3(this.arg$1, this.arg$2, view);
    }
}
