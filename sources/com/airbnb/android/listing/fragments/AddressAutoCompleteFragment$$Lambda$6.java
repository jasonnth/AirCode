package com.airbnb.android.listing.fragments;

import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;

final /* synthetic */ class AddressAutoCompleteFragment$$Lambda$6 implements OnInputChangedListener {
    private final AddressAutoCompleteFragment arg$1;

    private AddressAutoCompleteFragment$$Lambda$6(AddressAutoCompleteFragment addressAutoCompleteFragment) {
        this.arg$1 = addressAutoCompleteFragment;
    }

    public static OnInputChangedListener lambdaFactory$(AddressAutoCompleteFragment addressAutoCompleteFragment) {
        return new AddressAutoCompleteFragment$$Lambda$6(addressAutoCompleteFragment);
    }

    public void onInputChanged(String str) {
        this.arg$1.onSearchQueryChanged(str);
    }
}
