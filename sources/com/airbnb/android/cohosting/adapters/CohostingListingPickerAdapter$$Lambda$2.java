package com.airbnb.android.cohosting.adapters;

import com.airbnb.android.core.models.Listing;
import com.google.common.base.Function;

final /* synthetic */ class CohostingListingPickerAdapter$$Lambda$2 implements Function {
    private final CohostingListingPickerAdapter arg$1;

    private CohostingListingPickerAdapter$$Lambda$2(CohostingListingPickerAdapter cohostingListingPickerAdapter) {
        this.arg$1 = cohostingListingPickerAdapter;
    }

    public static Function lambdaFactory$(CohostingListingPickerAdapter cohostingListingPickerAdapter) {
        return new CohostingListingPickerAdapter$$Lambda$2(cohostingListingPickerAdapter);
    }

    public Object apply(Object obj) {
        return this.arg$1.createListingModel((Listing) obj);
    }
}
