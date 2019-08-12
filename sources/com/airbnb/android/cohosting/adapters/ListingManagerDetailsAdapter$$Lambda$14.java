package com.airbnb.android.cohosting.adapters;

import com.airbnb.android.core.views.OptionsMenuFactory.Listener;

final /* synthetic */ class ListingManagerDetailsAdapter$$Lambda$14 implements Listener {
    private final ListingManagerDetailsAdapter arg$1;
    private final String arg$2;

    private ListingManagerDetailsAdapter$$Lambda$14(ListingManagerDetailsAdapter listingManagerDetailsAdapter, String str) {
        this.arg$1 = listingManagerDetailsAdapter;
        this.arg$2 = str;
    }

    public static Listener lambdaFactory$(ListingManagerDetailsAdapter listingManagerDetailsAdapter, String str) {
        return new ListingManagerDetailsAdapter$$Lambda$14(listingManagerDetailsAdapter, str);
    }

    public void itemSelected(Object obj) {
        this.arg$1.handlePhoneOptionClick((String) obj, this.arg$2);
    }
}
