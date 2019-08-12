package com.airbnb.android.airmapview;

public class MapboxWebMapViewBuilder implements AirMapViewBuilder<WebViewMapFragment, AirMapType> {
    private final String accessToken;
    private final String mapId;
    private AirMapType options;

    public MapboxWebMapViewBuilder(String accessToken2, String mapId2) {
        this.accessToken = accessToken2;
        this.mapId = mapId2;
    }

    public AirMapViewBuilder<WebViewMapFragment, AirMapType> withOptions(AirMapType options2) {
        this.options = options2;
        return this;
    }

    public WebViewMapFragment build() {
        if (this.options == null) {
            this.options = new MapboxWebMapType(this.accessToken, this.mapId);
        }
        if (this.options instanceof MapboxWebMapType) {
            return MapboxWebViewMapFragment.newInstance(this.options);
        }
        throw new IllegalStateException("Unable to build MapboxWebMapViewFragment.  options == '" + this.options + "'");
    }
}
