package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;

final /* synthetic */ class UserProfileAdapter$$Lambda$6 implements OnClickListener {
    private final UserProfileAdapter arg$1;
    private final Listing arg$2;

    private UserProfileAdapter$$Lambda$6(UserProfileAdapter userProfileAdapter, Listing listing) {
        this.arg$1 = userProfileAdapter;
        this.arg$2 = listing;
    }

    public static OnClickListener lambdaFactory$(UserProfileAdapter userProfileAdapter, Listing listing) {
        return new UserProfileAdapter$$Lambda$6(userProfileAdapter, listing);
    }

    public void onClick(View view) {
        this.arg$1.clickListener.onListingClicked(this.arg$2);
    }
}
