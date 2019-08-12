package com.airbnb.android.listing.utils;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ListingCheckInTimeOptions;
import com.airbnb.android.core.views.OptionsMenuFactory;
import com.airbnb.android.listing.utils.CheckInOutSettingsHelper.Listener;

final /* synthetic */ class CheckInOutSettingsHelper$$Lambda$1 implements OnClickListener {
    private final CheckInOutSettingsHelper arg$1;
    private final Context arg$2;
    private final ListingCheckInTimeOptions arg$3;
    private final Listener arg$4;

    private CheckInOutSettingsHelper$$Lambda$1(CheckInOutSettingsHelper checkInOutSettingsHelper, Context context, ListingCheckInTimeOptions listingCheckInTimeOptions, Listener listener) {
        this.arg$1 = checkInOutSettingsHelper;
        this.arg$2 = context;
        this.arg$3 = listingCheckInTimeOptions;
        this.arg$4 = listener;
    }

    public static OnClickListener lambdaFactory$(CheckInOutSettingsHelper checkInOutSettingsHelper, Context context, ListingCheckInTimeOptions listingCheckInTimeOptions, Listener listener) {
        return new CheckInOutSettingsHelper$$Lambda$1(checkInOutSettingsHelper, context, listingCheckInTimeOptions, listener);
    }

    public void onClick(View view) {
        OptionsMenuFactory.forItems(this.arg$2, this.arg$3.getValidCheckInTimeStartOptions()).setTitleTransformer(CheckInOutSettingsHelper$$Lambda$9.lambdaFactory$()).setListener(CheckInOutSettingsHelper$$Lambda$10.lambdaFactory$(this.arg$1, this.arg$4)).buildAndShow();
    }
}
