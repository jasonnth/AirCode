package com.airbnb.android.cohosting.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ListingManagerDetailsAdapter$$Lambda$3 implements OnClickListener {
    private final ListingManagerDetailsAdapter arg$1;
    private final String arg$2;

    private ListingManagerDetailsAdapter$$Lambda$3(ListingManagerDetailsAdapter listingManagerDetailsAdapter, String str) {
        this.arg$1 = listingManagerDetailsAdapter;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(ListingManagerDetailsAdapter listingManagerDetailsAdapter, String str) {
        return new ListingManagerDetailsAdapter$$Lambda$3(listingManagerDetailsAdapter, str);
    }

    public void onClick(View view) {
        ListingManagerDetailsAdapter.lambda$setupEmailButton$2(this.arg$1, this.arg$2, view);
    }
}
