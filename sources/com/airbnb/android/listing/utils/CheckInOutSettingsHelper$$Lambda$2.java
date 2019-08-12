package com.airbnb.android.listing.utils;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ListingCheckInTimeOptions;
import com.airbnb.android.core.views.OptionsMenuFactory;
import com.airbnb.android.listing.utils.CheckInOutSettingsHelper.Listener;
import com.google.common.collect.FluentIterable;

final /* synthetic */ class CheckInOutSettingsHelper$$Lambda$2 implements OnClickListener {
    private final CheckInOutSettingsHelper arg$1;
    private final ListingCheckInTimeOptions arg$2;
    private final Context arg$3;
    private final Listener arg$4;

    private CheckInOutSettingsHelper$$Lambda$2(CheckInOutSettingsHelper checkInOutSettingsHelper, ListingCheckInTimeOptions listingCheckInTimeOptions, Context context, Listener listener) {
        this.arg$1 = checkInOutSettingsHelper;
        this.arg$2 = listingCheckInTimeOptions;
        this.arg$3 = context;
        this.arg$4 = listener;
    }

    public static OnClickListener lambdaFactory$(CheckInOutSettingsHelper checkInOutSettingsHelper, ListingCheckInTimeOptions listingCheckInTimeOptions, Context context, Listener listener) {
        return new CheckInOutSettingsHelper$$Lambda$2(checkInOutSettingsHelper, listingCheckInTimeOptions, context, listener);
    }

    public void onClick(View view) {
        OptionsMenuFactory.forItems(this.arg$3, FluentIterable.from((Iterable<E>) this.arg$2.getValidCheckInTimeEndOptions()).filter(CheckInOutSettingsHelper$$Lambda$6.lambdaFactory$(this.arg$1)).toList()).setTitleTransformer(CheckInOutSettingsHelper$$Lambda$7.lambdaFactory$()).setListener(CheckInOutSettingsHelper$$Lambda$8.lambdaFactory$(this.arg$1, this.arg$4)).buildAndShow();
    }
}
