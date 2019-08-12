package com.airbnb.android.cohosting.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;

final /* synthetic */ class CohostingListingPickerAdapter$$Lambda$3 implements OnClickListener {
    private final CohostingListingPickerAdapter arg$1;
    private final Listing arg$2;

    private CohostingListingPickerAdapter$$Lambda$3(CohostingListingPickerAdapter cohostingListingPickerAdapter, Listing listing) {
        this.arg$1 = cohostingListingPickerAdapter;
        this.arg$2 = listing;
    }

    public static OnClickListener lambdaFactory$(CohostingListingPickerAdapter cohostingListingPickerAdapter, Listing listing) {
        return new CohostingListingPickerAdapter$$Lambda$3(cohostingListingPickerAdapter, listing);
    }

    public void onClick(View view) {
        this.arg$1.context.startActivity(this.arg$1.createIntent(this.arg$2.getId()));
    }
}
