package com.airbnb.android.lib.payments.paymentinstruments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PaymentInstrumentsDelegate_ObservableResubscriber extends BaseObservableResubscriber {
    public PaymentInstrumentsDelegate_ObservableResubscriber(PaymentInstrumentsDelegate target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.digitalRiverVaultListener, "PaymentInstrumentsDelegate_digitalRiverVaultListener");
        group.resubscribeAll(target.digitalRiverVaultListener);
    }
}
