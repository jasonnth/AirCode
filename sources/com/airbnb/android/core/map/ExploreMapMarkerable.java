package com.airbnb.android.core.map;

import android.content.Context;
import android.graphics.Bitmap;
import com.airbnb.android.airmapview.AirMapMarker;
import com.airbnb.android.airmapview.AirMapMarker.Builder;
import com.airbnb.android.core.models.Mappable;
import com.airbnb.android.core.utils.Check;
import com.facebook.places.model.PlaceFields;
import com.google.android.gms.maps.model.LatLng;

public abstract class ExploreMapMarkerable {
    protected final Context context;
    protected final boolean isWishListed;
    private final LatLng latLng;
    private final Mappable mappable;

    public abstract Bitmap getBitmap(boolean z, boolean z2);

    public ExploreMapMarkerable(Mappable mappable2, boolean isWishListed2, Context context2) {
        this.mappable = mappable2;
        this.isWishListed = isWishListed2;
        this.context = (Context) Check.notNull(context2, PlaceFields.CONTEXT);
        this.latLng = new LatLng(mappable2.getLatitude(), mappable2.getLongitude());
    }

    public long getId() {
        return this.mappable.getMappableId();
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public AirMapMarker<Mappable> buildMarker(boolean isSelected, boolean isViewed) {
        return new Builder().object(this.mappable).mo19456id(getId()).position(this.latLng).anchor(0.5f, 1.0f).bitmap(getBitmap(isSelected, isViewed)).build();
    }
}
