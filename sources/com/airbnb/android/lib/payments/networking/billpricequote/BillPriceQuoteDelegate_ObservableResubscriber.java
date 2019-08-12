package com.airbnb.android.lib.payments.networking.billpricequote;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class BillPriceQuoteDelegate_ObservableResubscriber extends BaseObservableResubscriber {
    public BillPriceQuoteDelegate_ObservableResubscriber(BillPriceQuoteDelegate target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.billPriceQuoteRequestListener, "BillPriceQuoteDelegate_billPriceQuoteRequestListener");
        group.resubscribeAll(target.billPriceQuoteRequestListener);
    }
}
