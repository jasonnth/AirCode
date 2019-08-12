package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class EditAddressAdapter$$Lambda$2 implements OnClickListener {
    private final EditAddressAdapter arg$1;

    private EditAddressAdapter$$Lambda$2(EditAddressAdapter editAddressAdapter) {
        this.arg$1 = editAddressAdapter;
    }

    public static OnClickListener lambdaFactory$(EditAddressAdapter editAddressAdapter) {
        return new EditAddressAdapter$$Lambda$2(editAddressAdapter);
    }

    public void onClick(View view) {
        this.arg$1.streetClicked();
    }
}
