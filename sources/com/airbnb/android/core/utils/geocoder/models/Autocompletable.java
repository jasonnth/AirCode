package com.airbnb.android.core.utils.geocoder.models;

import android.content.Context;
import java.util.List;

public interface Autocompletable {
    String getDescription();

    CharSequence getLocationTag(Context context);

    String getPlaceId();

    List<AutocompleteTerm> getTerms();

    List<String> getTypes();

    void setPlaceId(String str);
}
