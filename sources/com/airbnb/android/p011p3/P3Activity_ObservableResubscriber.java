package com.airbnb.android.p011p3;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.p3.P3Activity_ObservableResubscriber */
public class P3Activity_ObservableResubscriber extends BaseObservableResubscriber {
    public P3Activity_ObservableResubscriber(P3Activity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingDetailsRequestListener, "P3Activity_listingDetailsRequestListener");
        group.resubscribeAll(target.listingDetailsRequestListener);
        setTag((AutoTaggableObserver) target.similarListingsListener, "P3Activity_similarListingsListener");
        group.resubscribeAll(target.similarListingsListener);
        setTag((AutoTaggableObserver) target.listingResponseRequestListener, "P3Activity_listingResponseRequestListener");
        group.resubscribeAll(target.listingResponseRequestListener);
        setTag((AutoTaggableObserver) target.userFlagResponseRequestListener, "P3Activity_userFlagResponseRequestListener");
        group.resubscribeAll(target.userFlagResponseRequestListener);
        setTag((AutoTaggableObserver) target.pricingQuotesRequestListener, "P3Activity_pricingQuotesRequestListener");
        group.resubscribeAll(target.pricingQuotesRequestListener);
        setTag((AutoTaggableObserver) target.inquiryRequestListener, "P3Activity_inquiryRequestListener");
        group.resubscribeAll(target.inquiryRequestListener);
        setTag((AutoTaggableObserver) target.localAttractionsRequestListener, "P3Activity_localAttractionsRequestListener");
        group.resubscribeAll(target.localAttractionsRequestListener);
        setTag((AutoTaggableObserver) target.listingDetailsTranslateResponse, "P3Activity_listingDetailsTranslateResponse");
        group.resubscribeAll(target.listingDetailsTranslateResponse);
    }
}
