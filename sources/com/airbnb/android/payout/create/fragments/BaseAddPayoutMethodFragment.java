package com.airbnb.android.payout.create.fragments;

import android.os.Bundle;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.payout.PayoutGraph;
import com.airbnb.android.payout.create.controllers.AddPayoutMethodDataController;
import com.airbnb.android.payout.create.controllers.AddPayoutMethodNavigationController;
import com.airbnb.android.payout.create.interfaces.AddPayoutMethodControllerInterface;
import com.airbnb.android.payout.create.interfaces.AddPayoutMethodDataChangedListener;
import com.airbnb.android.payout.logging.AddPayoutMethodJitneyLogger;
import com.airbnb.android.payout.models.PayoutInfoForm;
import com.airbnb.jitney.event.logging.PayoutMethodAction.p190v1.C2545PayoutMethodAction;
import com.airbnb.jitney.event.logging.PayoutMethodSetupPage.p193v1.C2548PayoutMethodSetupPage;
import java.util.List;

public class BaseAddPayoutMethodFragment extends AirFragment implements AddPayoutMethodDataChangedListener {
    AddPayoutMethodJitneyLogger addPayoutMethodJitneyLogger;
    protected AddPayoutMethodDataController dataController;
    protected AddPayoutMethodNavigationController navigationController;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PayoutGraph) CoreApplication.instance(getActivity()).component()).inject(this);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.navigationController = ((AddPayoutMethodControllerInterface) getActivity()).getNavigationController();
        this.dataController = ((AddPayoutMethodControllerInterface) getActivity()).getDataController();
        this.dataController.addDataChangedListener(this);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.dataController.removeDataChangedListener(this);
    }

    public void onFetchedAvailablePayoutMethods(List<PayoutInfoForm> list) {
    }

    public void onFetchRedirectUrlSuccess() {
    }

    public void onFetchRedirectUrlError(AirRequestNetworkException error) {
    }

    public void onFetchAvailablePayoutMethodError(AirRequestNetworkException error) {
    }

    public void onCreatePayoutMethodSuccess() {
    }

    public void onCreatePayoutMethodError(AirRequestNetworkException error) {
    }

    public void onFetchUserAddressSuccess(List<AirAddress> list) {
    }

    public void onFetchUserAddressError(AirRequestNetworkException error) {
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.dataController.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public void logPayoutSetup(C2548PayoutMethodSetupPage payoutMethodSetupPage, C2545PayoutMethodAction payoutMethodAction) {
        this.addPayoutMethodJitneyLogger.payoutMethodSetup(this.dataController.getPayoutCurrency(), payoutMethodSetupPage, this.dataController.getSelectedPayoutInfoForm().payoutMethodType(), payoutMethodAction);
    }
}
