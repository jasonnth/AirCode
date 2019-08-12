package com.airbnb.android.cohosting.adapters;

import p032rx.functions.Func1;

final /* synthetic */ class ListingManagerDetailsAdapter$$Lambda$13 implements Func1 {
    private final ListingManagerDetailsAdapter arg$1;
    private final String arg$2;

    private ListingManagerDetailsAdapter$$Lambda$13(ListingManagerDetailsAdapter listingManagerDetailsAdapter, String str) {
        this.arg$1 = listingManagerDetailsAdapter;
        this.arg$2 = str;
    }

    public static Func1 lambdaFactory$(ListingManagerDetailsAdapter listingManagerDetailsAdapter, String str) {
        return new ListingManagerDetailsAdapter$$Lambda$13(listingManagerDetailsAdapter, str);
    }

    public Object call(Object obj) {
        return this.arg$1.getPhoneOperationText((String) obj, this.arg$2);
    }
}
