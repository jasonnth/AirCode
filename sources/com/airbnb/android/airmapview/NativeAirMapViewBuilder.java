package com.airbnb.android.airmapview;

import com.google.android.gms.maps.GoogleMapOptions;

public class NativeAirMapViewBuilder implements AirMapViewBuilder<NativeGoogleMapFragment, AirGoogleMapOptions> {
    private AirGoogleMapOptions options;

    public AirMapViewBuilder<NativeGoogleMapFragment, AirGoogleMapOptions> withOptions(AirGoogleMapOptions options2) {
        this.options = options2;
        return this;
    }

    public NativeGoogleMapFragment build() {
        if (this.options == null) {
            this.options = new AirGoogleMapOptions(new GoogleMapOptions());
        }
        return NativeGoogleMapFragment.newInstance(this.options);
    }
}
