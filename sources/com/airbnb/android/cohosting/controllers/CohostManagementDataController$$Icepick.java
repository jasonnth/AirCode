package com.airbnb.android.cohosting.controllers;

import android.os.Bundle;
import com.airbnb.android.cohosting.controllers.CohostManagementDataController;
import com.airbnb.android.cohosting.enums.CohostManagementLaunchType;
import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;
import com.airbnb.android.core.models.CohostingNotification.MuteType;
import com.airbnb.android.core.models.Listing;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostManagementDataController$$Icepick<T extends CohostManagementDataController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7795H = new Helper("com.airbnb.android.cohosting.controllers.CohostManagementDataController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.loading = f7795H.getBoolean(state, "loading");
            target.listing = (Listing) f7795H.getParcelable(state, "listing");
            target.listingManagers = f7795H.getParcelableArrayList(state, "listingManagers");
            target.cohostInvitations = f7795H.getParcelableArrayList(state, "cohostInvitations");
            target.listingManagerIdOfCurrentUser = f7795H.getString(state, "listingManagerIdOfCurrentUser");
            target.isCurrentUserListingAdmin = f7795H.getBoolean(state, "isCurrentUserListingAdmin");
            target.canCurrentUserAccessResolutionCenter = f7795H.getBoolean(state, "canCurrentUserAccessResolutionCenter");
            target.feeType = (FeeType) f7795H.getSerializable(state, "feeType");
            target.type = (CohostManagementLaunchType) f7795H.getSerializable(state, "type");
            target.muteType = (MuteType) f7795H.getSerializable(state, "muteType");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7795H.putBoolean(state, "loading", target.loading);
        f7795H.putParcelable(state, "listing", target.listing);
        f7795H.putParcelableArrayList(state, "listingManagers", target.listingManagers);
        f7795H.putParcelableArrayList(state, "cohostInvitations", target.cohostInvitations);
        f7795H.putString(state, "listingManagerIdOfCurrentUser", target.listingManagerIdOfCurrentUser);
        f7795H.putBoolean(state, "isCurrentUserListingAdmin", target.isCurrentUserListingAdmin);
        f7795H.putBoolean(state, "canCurrentUserAccessResolutionCenter", target.canCurrentUserAccessResolutionCenter);
        f7795H.putSerializable(state, "feeType", target.feeType);
        f7795H.putSerializable(state, "type", target.type);
        f7795H.putSerializable(state, "muteType", target.muteType);
    }
}
