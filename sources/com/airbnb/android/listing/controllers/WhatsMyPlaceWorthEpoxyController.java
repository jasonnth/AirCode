package com.airbnb.android.listing.controllers;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.viewcomponents.models.ImpactMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.p027n2.epoxy.Typed3AirEpoxyController;

public class WhatsMyPlaceWorthEpoxyController extends Typed3AirEpoxyController<AirAddress, SpaceType, Integer> {
    InlineInputRowEpoxyModel_ addressModel;
    InlineInputRowEpoxyModel_ capacityModel;
    private final Context context;
    private final Listener listener;
    InlineInputRowEpoxyModel_ placeTypeModel;
    ImpactMarqueeEpoxyModel_ titleModel;

    public interface Listener {
        void addressRequested();

        void capacityRequested();

        void placeTypeRequested();
    }

    public WhatsMyPlaceWorthEpoxyController(Context context2, Listener listener2) {
        this.context = context2;
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels(AirAddress address, SpaceType spaceType, Integer capacity) {
        this.titleModel.titleRes(C7213R.string.wmpw_title).backgroundColor(ContextCompat.getColor(this.context, C7213R.color.n2_babu)).addTo(this);
        this.addressModel.titleRes(C7213R.string.wmpw_city).hintRes(C7213R.string.wmpw_city_hint).input(SanitizeUtils.emptyIfNull(address != null ? address.city() : null)).clickListener(WhatsMyPlaceWorthEpoxyController$$Lambda$1.lambdaFactory$(this)).addTo(this);
        this.capacityModel.titleRes(C7213R.string.lys_dls_how_many_guests_section_title).input(ListingTextUtils.createCountWithMaxPlus(this.context, capacity.intValue(), 16)).clickListener(WhatsMyPlaceWorthEpoxyController$$Lambda$2.lambdaFactory$(this)).addTo(this);
        this.placeTypeModel.titleRes(C7213R.string.manage_listing_rooms_and_guests_listing_type_setting).inputRes(spaceType.titleId).clickListener(WhatsMyPlaceWorthEpoxyController$$Lambda$3.lambdaFactory$(this)).addTo(this);
    }
}
