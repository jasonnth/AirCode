package com.airbnb.android.places.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.airbnb.android.airmapview.AirMapMarker;
import com.airbnb.android.airmapview.AirMapMarker.Builder;
import com.airbnb.android.core.beta.models.guidebook.Place;
import com.airbnb.android.core.models.Mappable;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.AirTextView;
import com.google.android.gms.maps.model.LatLng;

public class PlaceMarkerGenerator {
    private final AirTextView markerView;

    public PlaceMarkerGenerator(Context context, ViewGroup root) {
        this.markerView = (AirTextView) LayoutInflater.from(context).inflate(C7627R.layout.marker_place_map, root, false);
    }

    public Bitmap getBitmap(String airmoji) {
        this.markerView.setText(airmoji);
        return ViewUtils.getBitmapFromUnmeasuredView(this.markerView);
    }

    public AirMapMarker<Mappable> buildMarker(Place place) {
        LatLng latLng = new LatLng(place.getLat(), place.getLng());
        return new Builder().object(place).mo19456id(place.getId()).position(latLng).anchor(0.5f, 1.0f).bitmap(getBitmap(place.getAirmojiForDisplay())).build();
    }
}
