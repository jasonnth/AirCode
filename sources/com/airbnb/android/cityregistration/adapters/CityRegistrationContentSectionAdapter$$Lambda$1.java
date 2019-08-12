package com.airbnb.android.cityregistration.adapters;

import android.content.Context;
import android.view.View;
import com.airbnb.android.core.models.ListingRegistrationHelpLink;
import com.airbnb.p027n2.utils.AirTextBuilder.OnLinkClickListener;

final /* synthetic */ class CityRegistrationContentSectionAdapter$$Lambda$1 implements OnLinkClickListener {
    private final ListingRegistrationHelpLink arg$1;
    private final Context arg$2;

    private CityRegistrationContentSectionAdapter$$Lambda$1(ListingRegistrationHelpLink listingRegistrationHelpLink, Context context) {
        this.arg$1 = listingRegistrationHelpLink;
        this.arg$2 = context;
    }

    public static OnLinkClickListener lambdaFactory$(ListingRegistrationHelpLink listingRegistrationHelpLink, Context context) {
        return new CityRegistrationContentSectionAdapter$$Lambda$1(listingRegistrationHelpLink, context);
    }

    public void onClick(View view, CharSequence charSequence) {
        CityRegistrationContentSectionAdapter.lambda$addLinkToText$0(this.arg$1, this.arg$2, view, charSequence);
    }
}
