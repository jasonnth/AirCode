package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ListingRoom;

final /* synthetic */ class BedDetailsAdapter$$Lambda$1 implements OnClickListener {
    private final BedDetailsAdapter arg$1;
    private final ListingRoom arg$2;

    private BedDetailsAdapter$$Lambda$1(BedDetailsAdapter bedDetailsAdapter, ListingRoom listingRoom) {
        this.arg$1 = bedDetailsAdapter;
        this.arg$2 = listingRoom;
    }

    public static OnClickListener lambdaFactory$(BedDetailsAdapter bedDetailsAdapter, ListingRoom listingRoom) {
        return new BedDetailsAdapter$$Lambda$1(bedDetailsAdapter, listingRoom);
    }

    public void onClick(View view) {
        this.arg$1.listener.roomSelected(this.arg$2.getRoomNumber());
    }
}
