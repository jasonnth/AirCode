package com.airbnb.android.places.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.beta.models.guidebook.Place;
import com.airbnb.android.core.models.PlaceActivityHours;
import com.airbnb.android.places.views.PlaceInfoView;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.utils.MapOptions;

public abstract class PlaceInfoEpoxyModel extends AirEpoxyModel<PlaceInfoView> {
    OnClickListener addressClickListener;
    OnClickListener hoursClickListener;
    OnClickListener mapClickListener;
    MapOptions mapOptions;
    OnClickListener phoneClickListener;
    Place place;
    OnClickListener websiteClickListener;

    public void bind(PlaceInfoView view) {
        super.bind(view);
        view.setName(new StringBuilder(this.place.getName()).append("  ").append(this.place.getAirmojiForDisplay()));
        view.setAddress(this.place.getAddressPlusCity(), this.addressClickListener);
        view.setPhoneNumber(this.place.getPhone(), this.phoneClickListener);
        view.setWebsite(this.place.getWebsite(), this.websiteClickListener);
        view.drawMap(this.mapOptions, this.mapClickListener, this.place.getAirmojiForDisplay());
        PlaceActivityHours openHours = this.place.getOpenHours();
        if (openHours != null) {
            view.setHours(openHours.getOpenStatus(), this.hoursClickListener);
        } else {
            view.setHours(null, null);
        }
    }
}
