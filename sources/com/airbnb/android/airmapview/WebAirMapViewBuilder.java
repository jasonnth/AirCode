package com.airbnb.android.airmapview;

public class WebAirMapViewBuilder implements AirMapViewBuilder<WebViewMapFragment, AirMapType> {
    private AirMapType options;

    public AirMapViewBuilder<WebViewMapFragment, AirMapType> withOptions(AirMapType options2) {
        this.options = options2;
        return this;
    }

    public WebViewMapFragment build() {
        if (this.options == null) {
            this.options = new GoogleWebMapType();
        }
        if (this.options instanceof GoogleWebMapType) {
            return GoogleWebViewMapFragment.newInstance(this.options);
        }
        if (this.options instanceof GoogleChinaMapType) {
            return GoogleChinaWebViewMapFragment.newInstance(this.options);
        }
        return null;
    }
}
