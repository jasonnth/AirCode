package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ListingPickerInfo;

final /* synthetic */ class AccountPageAdapter$$Lambda$20 implements OnClickListener {
    private final AccountPageAdapter arg$1;
    private final ListingPickerInfo arg$2;

    private AccountPageAdapter$$Lambda$20(AccountPageAdapter accountPageAdapter, ListingPickerInfo listingPickerInfo) {
        this.arg$1 = accountPageAdapter;
        this.arg$2 = listingPickerInfo;
    }

    public static OnClickListener lambdaFactory$(AccountPageAdapter accountPageAdapter, ListingPickerInfo listingPickerInfo) {
        return new AccountPageAdapter$$Lambda$20(accountPageAdapter, listingPickerInfo);
    }

    public void onClick(View view) {
        this.arg$1.listener.continueNewListing(this.arg$2.getId());
    }
}
