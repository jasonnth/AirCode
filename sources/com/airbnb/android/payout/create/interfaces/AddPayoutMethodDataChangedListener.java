package com.airbnb.android.payout.create.interfaces;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.payout.models.PayoutInfoForm;
import java.util.List;

public interface AddPayoutMethodDataChangedListener {
    void onCreatePayoutMethodError(AirRequestNetworkException airRequestNetworkException);

    void onCreatePayoutMethodSuccess();

    void onFetchAvailablePayoutMethodError(AirRequestNetworkException airRequestNetworkException);

    void onFetchRedirectUrlError(AirRequestNetworkException airRequestNetworkException);

    void onFetchRedirectUrlSuccess();

    void onFetchUserAddressError(AirRequestNetworkException airRequestNetworkException);

    void onFetchUserAddressSuccess(List<AirAddress> list);

    void onFetchedAvailablePayoutMethods(List<PayoutInfoForm> list);
}
