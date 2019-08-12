package com.airbnb.android.payout.create.interfaces;

import com.airbnb.android.core.models.AirAddress;

public interface AddPayoutMethodDataFacade {
    void addDataChangedListener(AddPayoutMethodDataChangedListener addPayoutMethodDataChangedListener);

    void createPayoutMethod();

    void fetchPayoutInfoForms(String str);

    void fetchRedirectUrl();

    void fetchUserAddress();

    String getPayoutCountryCode();

    void removeDataChangedListener(AddPayoutMethodDataChangedListener addPayoutMethodDataChangedListener);

    void setPayoutAddress(AirAddress airAddress);
}
