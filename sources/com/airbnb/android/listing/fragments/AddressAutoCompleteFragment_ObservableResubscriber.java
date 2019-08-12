package com.airbnb.android.listing.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AddressAutoCompleteFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AddressAutoCompleteFragment_ObservableResubscriber(AddressAutoCompleteFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.autocompleteRequestListener, "AddressAutoCompleteFragment_autocompleteRequestListener");
        group.resubscribeAll(target.autocompleteRequestListener);
        setTag((AutoTaggableObserver) target.placeDetailsRequestListener, "AddressAutoCompleteFragment_placeDetailsRequestListener");
        group.resubscribeAll(target.placeDetailsRequestListener);
    }
}
