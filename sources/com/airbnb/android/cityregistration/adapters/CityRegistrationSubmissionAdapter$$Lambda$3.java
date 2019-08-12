package com.airbnb.android.cityregistration.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;

final /* synthetic */ class CityRegistrationSubmissionAdapter$$Lambda$3 implements OnClickListener {
    private final CityRegistrationSubmissionAdapter arg$1;
    private final ListingRegistrationProcessInputGroup arg$2;

    private CityRegistrationSubmissionAdapter$$Lambda$3(CityRegistrationSubmissionAdapter cityRegistrationSubmissionAdapter, ListingRegistrationProcessInputGroup listingRegistrationProcessInputGroup) {
        this.arg$1 = cityRegistrationSubmissionAdapter;
        this.arg$2 = listingRegistrationProcessInputGroup;
    }

    public static OnClickListener lambdaFactory$(CityRegistrationSubmissionAdapter cityRegistrationSubmissionAdapter, ListingRegistrationProcessInputGroup listingRegistrationProcessInputGroup) {
        return new CityRegistrationSubmissionAdapter$$Lambda$3(cityRegistrationSubmissionAdapter, listingRegistrationProcessInputGroup);
    }

    public void onClick(View view) {
        CityRegistrationSubmissionAdapter.lambda$buildModelFromInputGroup$0(this.arg$1, this.arg$2, view);
    }
}
