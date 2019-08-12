package com.airbnb.android.listing.fragments;

import com.airbnb.android.core.requests.AutocompleteRequest;

final /* synthetic */ class AddressAutoCompleteFragment$$Lambda$7 implements Runnable {
    private final AddressAutoCompleteFragment arg$1;
    private final AutocompleteRequest arg$2;

    private AddressAutoCompleteFragment$$Lambda$7(AddressAutoCompleteFragment addressAutoCompleteFragment, AutocompleteRequest autocompleteRequest) {
        this.arg$1 = addressAutoCompleteFragment;
        this.arg$2 = autocompleteRequest;
    }

    public static Runnable lambdaFactory$(AddressAutoCompleteFragment addressAutoCompleteFragment, AutocompleteRequest autocompleteRequest) {
        return new AddressAutoCompleteFragment$$Lambda$7(addressAutoCompleteFragment, autocompleteRequest);
    }

    public void run() {
        this.arg$2.withListener(this.arg$1.autocompleteRequestListener).execute(this.arg$1.requestManager);
    }
}
