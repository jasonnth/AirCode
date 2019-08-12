package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BedDetailsAdapter$$Lambda$2 implements OnClickListener {
    private final BedDetailsAdapter arg$1;
    private final int arg$2;

    private BedDetailsAdapter$$Lambda$2(BedDetailsAdapter bedDetailsAdapter, int i) {
        this.arg$1 = bedDetailsAdapter;
        this.arg$2 = i;
    }

    public static OnClickListener lambdaFactory$(BedDetailsAdapter bedDetailsAdapter, int i) {
        return new BedDetailsAdapter$$Lambda$2(bedDetailsAdapter, i);
    }

    public void onClick(View view) {
        this.arg$1.listener.roomSelected(this.arg$2);
    }
}
