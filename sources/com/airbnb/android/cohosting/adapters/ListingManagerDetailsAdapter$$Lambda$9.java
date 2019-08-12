package com.airbnb.android.cohosting.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ListingManagerDetailsAdapter$$Lambda$9 implements OnClickListener {
    private final ListingManagerDetailsAdapter arg$1;

    private ListingManagerDetailsAdapter$$Lambda$9(ListingManagerDetailsAdapter listingManagerDetailsAdapter) {
        this.arg$1 = listingManagerDetailsAdapter;
    }

    public static OnClickListener lambdaFactory$(ListingManagerDetailsAdapter listingManagerDetailsAdapter) {
        return new ListingManagerDetailsAdapter$$Lambda$9(listingManagerDetailsAdapter);
    }

    public void onClick(View view) {
        ListingManagerDetailsAdapter.lambda$setupRemoveCohostRow$8(this.arg$1, view);
    }
}
