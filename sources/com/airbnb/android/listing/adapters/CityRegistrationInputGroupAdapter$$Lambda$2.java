package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.listing.adapters.CityRegistrationInputGroupAdapter.ErrorStateListener;

final /* synthetic */ class CityRegistrationInputGroupAdapter$$Lambda$2 implements ErrorStateListener {
    private final CityRegistrationInputGroupAdapter arg$1;
    private final InlineInputRowEpoxyModel_ arg$2;

    private CityRegistrationInputGroupAdapter$$Lambda$2(CityRegistrationInputGroupAdapter cityRegistrationInputGroupAdapter, InlineInputRowEpoxyModel_ inlineInputRowEpoxyModel_) {
        this.arg$1 = cityRegistrationInputGroupAdapter;
        this.arg$2 = inlineInputRowEpoxyModel_;
    }

    public static ErrorStateListener lambdaFactory$(CityRegistrationInputGroupAdapter cityRegistrationInputGroupAdapter, InlineInputRowEpoxyModel_ inlineInputRowEpoxyModel_) {
        return new CityRegistrationInputGroupAdapter$$Lambda$2(cityRegistrationInputGroupAdapter, inlineInputRowEpoxyModel_);
    }

    public void setErrorState(boolean z) {
        CityRegistrationInputGroupAdapter.lambda$addTextQuestion$0(this.arg$1, this.arg$2, z);
    }
}
