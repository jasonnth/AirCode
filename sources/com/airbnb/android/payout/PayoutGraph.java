package com.airbnb.android.payout;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.payout.create.PayoutRedirectWebviewActivity;
import com.airbnb.android.payout.create.controllers.AddPayoutMethodDataController;
import com.airbnb.android.payout.create.fragments.BaseAddPayoutMethodFragment;
import com.airbnb.android.payout.manage.EditPayoutFragment;
import com.airbnb.android.payout.manage.SelectPayoutCountryActivity;

public interface PayoutGraph extends BaseGraph {
    void inject(PayoutRedirectWebviewActivity payoutRedirectWebviewActivity);

    void inject(AddPayoutMethodDataController addPayoutMethodDataController);

    void inject(BaseAddPayoutMethodFragment baseAddPayoutMethodFragment);

    void inject(EditPayoutFragment editPayoutFragment);

    void inject(SelectPayoutCountryActivity selectPayoutCountryActivity);
}
