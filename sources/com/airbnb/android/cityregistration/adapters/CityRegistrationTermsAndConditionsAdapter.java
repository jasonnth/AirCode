package com.airbnb.android.cityregistration.adapters;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.models.ListingRegistrationContentSection;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;

public class CityRegistrationTermsAndConditionsAdapter extends CityRegistrationContentSectionAdapter {
    public CityRegistrationTermsAndConditionsAdapter(ListingRegistrationContentSection termsAndConditions, Context context, Bundle savedInstanceState) {
        onRestoreInstanceState(savedInstanceState);
        addModel(new DocumentMarqueeEpoxyModel_().titleText((CharSequence) termsAndConditions.getTitle()));
        addListingRegistrationContentObjects(termsAndConditions.getSubtitles(), context);
        addListingRegistrationContentObjects(termsAndConditions.getBody(), context);
    }
}
