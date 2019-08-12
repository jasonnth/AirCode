package com.airbnb.android.listing.adapters;

import com.airbnb.android.listing.adapters.ListingRegistrationAdapter.Listener;
import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;

final /* synthetic */ class ListingRegistrationAdapter$$Lambda$1 implements OnInputChangedListener {
    private final Listener arg$1;
    private final String arg$2;

    private ListingRegistrationAdapter$$Lambda$1(Listener listener, String str) {
        this.arg$1 = listener;
        this.arg$2 = str;
    }

    public static OnInputChangedListener lambdaFactory$(Listener listener, String str) {
        return new ListingRegistrationAdapter$$Lambda$1(listener, str);
    }

    public void onInputChanged(String str) {
        ListingRegistrationAdapter.lambda$new$0(this.arg$1, this.arg$2, str);
    }
}
