package com.airbnb.android.airmapview;

import android.content.res.Resources;
import android.os.Bundle;

public class MapboxWebMapType extends AirMapType {
    protected static final String ARG_MAPBOX_ACCESS_TOKEN = "MAPBOX_ACCESS_TOKEN";
    protected static final String ARG_MAPBOX_MAPID = "MAPBOX_MAPID";
    private final String accessToken;
    private final String mapId;

    public MapboxWebMapType(String accessToken2, String mapId2) {
        super("mapbox.html", "https://api.tiles.mapbox.com/mapbox.js/v2.2.1", "www.mapbox.com");
        this.accessToken = accessToken2;
        this.mapId = mapId2;
    }

    private MapboxWebMapType(String fileName, String mapUrl, String domain, String accessToken2, String mapId2) {
        super(fileName, mapUrl, domain);
        this.accessToken = accessToken2;
        this.mapId = mapId2;
    }

    public Bundle toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putString(ARG_MAPBOX_ACCESS_TOKEN, this.accessToken);
        bundle.putString(ARG_MAPBOX_MAPID, this.mapId);
        return bundle;
    }

    public static MapboxWebMapType fromBundle(Bundle bundle) {
        AirMapType airMapType = AirMapType.fromBundle(bundle);
        return new MapboxWebMapType(airMapType.getFileName(), airMapType.getMapUrl(), airMapType.getDomain(), bundle.getString(ARG_MAPBOX_ACCESS_TOKEN, ""), bundle.getString(ARG_MAPBOX_MAPID, ""));
    }

    public String getMapData(Resources resources) {
        return super.getMapData(resources).replace(ARG_MAPBOX_ACCESS_TOKEN, this.accessToken).replace(ARG_MAPBOX_MAPID, this.mapId);
    }
}
