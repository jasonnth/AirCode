package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.ListingRegistrationAnswer;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.airbnb.android.core.viewcomponents.models.CityRegistrationToggleRowEpoxyModel_;
import com.airbnb.p027n2.components.CityRegistrationToggleRow;
import com.airbnb.p027n2.components.CityRegistrationToggleRow.OnCheckChangedListener;
import java.util.TreeSet;

final /* synthetic */ class CityRegistrationInputGroupAdapter$$Lambda$3 implements OnCheckChangedListener {
    private final TreeSet arg$1;
    private final ListingRegistrationAnswer arg$2;
    private final ListingRegistrationQuestion arg$3;
    private final CityRegistrationToggleRowEpoxyModel_ arg$4;

    private CityRegistrationInputGroupAdapter$$Lambda$3(TreeSet treeSet, ListingRegistrationAnswer listingRegistrationAnswer, ListingRegistrationQuestion listingRegistrationQuestion, CityRegistrationToggleRowEpoxyModel_ cityRegistrationToggleRowEpoxyModel_) {
        this.arg$1 = treeSet;
        this.arg$2 = listingRegistrationAnswer;
        this.arg$3 = listingRegistrationQuestion;
        this.arg$4 = cityRegistrationToggleRowEpoxyModel_;
    }

    public static OnCheckChangedListener lambdaFactory$(TreeSet treeSet, ListingRegistrationAnswer listingRegistrationAnswer, ListingRegistrationQuestion listingRegistrationQuestion, CityRegistrationToggleRowEpoxyModel_ cityRegistrationToggleRowEpoxyModel_) {
        return new CityRegistrationInputGroupAdapter$$Lambda$3(treeSet, listingRegistrationAnswer, listingRegistrationQuestion, cityRegistrationToggleRowEpoxyModel_);
    }

    public void onCheckChanged(CityRegistrationToggleRow cityRegistrationToggleRow, boolean z) {
        CityRegistrationInputGroupAdapter.lambda$addCheckBoxQuestion$1(this.arg$1, this.arg$2, this.arg$3, this.arg$4, cityRegistrationToggleRow, z);
    }
}
