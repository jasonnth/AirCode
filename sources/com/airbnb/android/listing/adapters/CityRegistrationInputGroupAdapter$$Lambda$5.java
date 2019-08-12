package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.listing.adapters.CityRegistrationInputGroupAdapter.ErrorStateListener;

final /* synthetic */ class CityRegistrationInputGroupAdapter$$Lambda$5 implements ErrorStateListener {
    private final CityRegistrationInputGroupAdapter arg$1;
    private final StandardRowEpoxyModel_ arg$2;

    private CityRegistrationInputGroupAdapter$$Lambda$5(CityRegistrationInputGroupAdapter cityRegistrationInputGroupAdapter, StandardRowEpoxyModel_ standardRowEpoxyModel_) {
        this.arg$1 = cityRegistrationInputGroupAdapter;
        this.arg$2 = standardRowEpoxyModel_;
    }

    public static ErrorStateListener lambdaFactory$(CityRegistrationInputGroupAdapter cityRegistrationInputGroupAdapter, StandardRowEpoxyModel_ standardRowEpoxyModel_) {
        return new CityRegistrationInputGroupAdapter$$Lambda$5(cityRegistrationInputGroupAdapter, standardRowEpoxyModel_);
    }

    public void setErrorState(boolean z) {
        this.arg$1.notifyModelChanged(this.arg$2);
    }
}
