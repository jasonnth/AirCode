package com.airbnb.android.cohosting.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ListingManagerDetailsAdapter$$Lambda$7 implements OnClickListener {
    private final ListingManagerDetailsAdapter arg$1;

    private ListingManagerDetailsAdapter$$Lambda$7(ListingManagerDetailsAdapter listingManagerDetailsAdapter) {
        this.arg$1 = listingManagerDetailsAdapter;
    }

    public static OnClickListener lambdaFactory$(ListingManagerDetailsAdapter listingManagerDetailsAdapter) {
        return new ListingManagerDetailsAdapter$$Lambda$7(listingManagerDetailsAdapter);
    }

    public void onClick(View view) {
        ListingManagerDetailsAdapter.lambda$setupMakePrimaryHostRow$6(this.arg$1, view);
    }
}
