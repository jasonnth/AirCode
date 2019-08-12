package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingRoomBedDetailsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingRoomBedDetailsFragment_ObservableResubscriber(ManageListingRoomBedDetailsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.newRoomListener, "ManageListingRoomBedDetailsFragment_newRoomListener");
        group.resubscribeAll(target.newRoomListener);
        setTag((AutoTaggableObserver) target.fetchRoomsListener, "ManageListingRoomBedDetailsFragment_fetchRoomsListener");
        group.resubscribeAll(target.fetchRoomsListener);
    }
}
