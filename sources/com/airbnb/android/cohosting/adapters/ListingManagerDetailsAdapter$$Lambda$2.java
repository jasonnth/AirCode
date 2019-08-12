package com.airbnb.android.cohosting.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ListingManagerDetailsAdapter$$Lambda$2 implements OnClickListener {
    private final ListingManagerDetailsAdapter arg$1;
    private final long arg$2;

    private ListingManagerDetailsAdapter$$Lambda$2(ListingManagerDetailsAdapter listingManagerDetailsAdapter, long j) {
        this.arg$1 = listingManagerDetailsAdapter;
        this.arg$2 = j;
    }

    public static OnClickListener lambdaFactory$(ListingManagerDetailsAdapter listingManagerDetailsAdapter, long j) {
        return new ListingManagerDetailsAdapter$$Lambda$2(listingManagerDetailsAdapter, j);
    }

    public void onClick(View view) {
        ListingManagerDetailsAdapter.lambda$setupChatButton$1(this.arg$1, this.arg$2, view);
    }
}
