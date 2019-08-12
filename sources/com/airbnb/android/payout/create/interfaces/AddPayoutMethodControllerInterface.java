package com.airbnb.android.payout.create.interfaces;

import com.airbnb.android.payout.create.controllers.AddPayoutMethodDataController;
import com.airbnb.android.payout.create.controllers.AddPayoutMethodNavigationController;

public interface AddPayoutMethodControllerInterface {
    AddPayoutMethodDataController getDataController();

    AddPayoutMethodNavigationController getNavigationController();
}
