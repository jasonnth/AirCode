package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final /* synthetic */ class EditAddressAdapter$$Lambda$4 implements OnFocusChangeListener {
    private final EditAddressAdapter arg$1;

    private EditAddressAdapter$$Lambda$4(EditAddressAdapter editAddressAdapter) {
        this.arg$1 = editAddressAdapter;
    }

    public static OnFocusChangeListener lambdaFactory$(EditAddressAdapter editAddressAdapter) {
        return new EditAddressAdapter$$Lambda$4(editAddressAdapter);
    }

    public void onFocusChange(View view, boolean z) {
        this.arg$1.focusChanged();
    }
}
