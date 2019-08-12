package com.airbnb.android.managelisting.picker;

import com.airbnb.android.core.models.ListingPickerInfo;
import java.util.Comparator;

final /* synthetic */ class ManageListingPickerEpoxyController$$Lambda$7 implements Comparator {
    private static final ManageListingPickerEpoxyController$$Lambda$7 instance = new ManageListingPickerEpoxyController$$Lambda$7();

    private ManageListingPickerEpoxyController$$Lambda$7() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ManageListingPickerEpoxyController.lambda$static$0((ListingPickerInfo) obj, (ListingPickerInfo) obj2);
    }
}
