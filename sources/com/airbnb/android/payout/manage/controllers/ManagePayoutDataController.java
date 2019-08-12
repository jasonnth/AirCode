package com.airbnb.android.payout.manage.controllers;

import android.os.Bundle;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.core.requests.GetExistingPayoutMethodRequest;
import com.airbnb.android.core.responses.GetExistingPayoutMethodResponse;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class ManagePayoutDataController {
    private List<ManagePayoutDataChangedListener> dataChangedListeners = Lists.newArrayList();
    @State
    ArrayList<PaymentInstrument> payoutInstruments;
    final RequestListener<GetExistingPayoutMethodResponse> payoutInstrumentsListener = new C0699RL().onResponse(ManagePayoutDataController$$Lambda$1.lambdaFactory$(this)).onError(ManagePayoutDataController$$Lambda$2.lambdaFactory$(this)).build();
    private final RequestManager requestManager;

    public interface ManagePayoutDataChangedListener {
        void onFetchExistingPayoutMethodError(AirRequestNetworkException airRequestNetworkException);

        void onFetchExistingPayoutMethodSuccess(List<PaymentInstrument> list);
    }

    public ManagePayoutDataController(RequestManager requestManager2, Bundle savedInstance) {
        IcepickWrapper.restoreInstanceState(this, savedInstance);
        requestManager2.subscribe(this);
        this.requestManager = requestManager2;
    }

    public void addListener(ManagePayoutDataChangedListener listener) {
        this.dataChangedListeners.add(listener);
    }

    public void fetchExistingPayoutMethods() {
        if (this.payoutInstruments == null) {
        }
        GetExistingPayoutMethodRequest.forHostPayouts().withListener((Observer) this.payoutInstrumentsListener).execute(this.requestManager);
    }

    public boolean hasExistingPayoutMethods() {
        return this.payoutInstruments != null;
    }

    public ArrayList<PaymentInstrument> getExistingPayoutMethods() {
        return this.payoutInstruments;
    }

    /* access modifiers changed from: private */
    public void onFetchExistingPayoutMethodError(AirRequestNetworkException error) {
        for (ManagePayoutDataChangedListener dataChangedListener : this.dataChangedListeners) {
            dataChangedListener.onFetchExistingPayoutMethodError(error);
        }
    }

    /* access modifiers changed from: private */
    public void onFetchExistingPayoutMethodSuccess(GetExistingPayoutMethodResponse response) {
        this.payoutInstruments = response.paymentInstruments;
        for (ManagePayoutDataChangedListener dataChangedListener : this.dataChangedListeners) {
            dataChangedListener.onFetchExistingPayoutMethodSuccess(this.payoutInstruments);
        }
    }
}
