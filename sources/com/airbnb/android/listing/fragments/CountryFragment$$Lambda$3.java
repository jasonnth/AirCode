package com.airbnb.android.listing.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CountryFragment$$Lambda$3 implements OnClickListener {
    private final CountryFragment arg$1;

    private CountryFragment$$Lambda$3(CountryFragment countryFragment) {
        this.arg$1 = countryFragment;
    }

    public static OnClickListener lambdaFactory$(CountryFragment countryFragment) {
        return new CountryFragment$$Lambda$3(countryFragment);
    }

    public void onClick(View view) {
        this.arg$1.makeCountriesRequest();
    }
}
