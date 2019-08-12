package com.airbnb.android.cityregistration.adapters;

import com.airbnb.android.core.models.ListingRegistrationSubmissionData;
import com.google.common.base.Function;

final /* synthetic */ class CityRegistrationSubmissionAdapter$$Lambda$2 implements Function {
    private final CityRegistrationSubmissionAdapter arg$1;

    private CityRegistrationSubmissionAdapter$$Lambda$2(CityRegistrationSubmissionAdapter cityRegistrationSubmissionAdapter) {
        this.arg$1 = cityRegistrationSubmissionAdapter;
    }

    public static Function lambdaFactory$(CityRegistrationSubmissionAdapter cityRegistrationSubmissionAdapter) {
        return new CityRegistrationSubmissionAdapter$$Lambda$2(cityRegistrationSubmissionAdapter);
    }

    public Object apply(Object obj) {
        return this.arg$1.buildSubmissionDataModel((ListingRegistrationSubmissionData) obj);
    }
}
