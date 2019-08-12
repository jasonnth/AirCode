package com.airbnb.android.cityregistration.adapters;

import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;
import com.google.common.base.Function;

final /* synthetic */ class CityRegistrationSubmissionAdapter$$Lambda$1 implements Function {
    private final CityRegistrationSubmissionAdapter arg$1;

    private CityRegistrationSubmissionAdapter$$Lambda$1(CityRegistrationSubmissionAdapter cityRegistrationSubmissionAdapter) {
        this.arg$1 = cityRegistrationSubmissionAdapter;
    }

    public static Function lambdaFactory$(CityRegistrationSubmissionAdapter cityRegistrationSubmissionAdapter) {
        return new CityRegistrationSubmissionAdapter$$Lambda$1(cityRegistrationSubmissionAdapter);
    }

    public Object apply(Object obj) {
        return this.arg$1.buildModelFromInputGroup((ListingRegistrationProcessInputGroup) obj);
    }
}
