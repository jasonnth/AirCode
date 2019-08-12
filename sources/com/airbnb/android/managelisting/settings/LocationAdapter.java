package com.airbnb.android.managelisting.settings;

import android.content.Context;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MapInterstitialEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.utils.LatLng;
import com.airbnb.p027n2.utils.MapOptions;
import com.airbnb.p027n2.utils.MapOptions.CircleOptions;
import com.airbnb.p027n2.utils.MapOptions.MarkerOptions;

public class LocationAdapter extends AirEpoxyAdapter {
    private static final int ZOOM_LEVEL = 17;

    interface Listener {
        void address();

        void exactLocationMap();
    }

    public LocationAdapter(Listing listing, Listener listener, Context context) {
        DocumentMarqueeEpoxyModel_ documentMarquee = new DocumentMarqueeEpoxyModel_().titleRes(C7368R.string.ml_location).captionRes(C7368R.string.location_description);
        StandardRowEpoxyModel_ addressRow = new StandardRowEpoxyModel_().titleRes(C7368R.string.address).subtitle((CharSequence) listing.getFormattedAddress());
        if (listing.isAddressEditable()) {
            addressRow.actionText(C7368R.string.edit).clickListener(LocationAdapter$$Lambda$1.lambdaFactory$(listener));
        } else {
            addressRow.placeholderText(C7368R.string.edit).clickListener(LocationAdapter$$Lambda$4.lambdaFactory$());
        }
        int circleRadiusMeters = context.getResources().getDimensionPixelSize(C7368R.dimen.map_circle_radius_location);
        LatLng latLng = listing.getLatLng();
        MapInterstitialEpoxyModel_ mapInterstitial = new MapInterstitialEpoxyModel_().mapOptions(MapOptions.builder(AppLaunchUtils.isUserInChina()).center(listing.getLatLng()).marker(MarkerOptions.create(latLng)).circle(CircleOptions.create(latLng, circleRadiusMeters)).zoom(17).useDlsMapType(true).build()).hideText(true);
        if (listing.isLocationEditable()) {
            mapInterstitial.clickListener(LocationAdapter$$Lambda$5.lambdaFactory$(listener));
        }
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{documentMarquee, addressRow, mapInterstitial});
    }
}
