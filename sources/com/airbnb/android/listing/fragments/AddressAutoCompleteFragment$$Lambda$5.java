package com.airbnb.android.listing.fragments;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class AddressAutoCompleteFragment$$Lambda$5 implements OnEditorActionListener {
    private final AddressAutoCompleteFragment arg$1;

    private AddressAutoCompleteFragment$$Lambda$5(AddressAutoCompleteFragment addressAutoCompleteFragment) {
        this.arg$1 = addressAutoCompleteFragment;
    }

    public static OnEditorActionListener lambdaFactory$(AddressAutoCompleteFragment addressAutoCompleteFragment) {
        return new AddressAutoCompleteFragment$$Lambda$5(addressAutoCompleteFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return AddressAutoCompleteFragment.lambda$onCreateView$4(this.arg$1, textView, i, keyEvent);
    }
}
