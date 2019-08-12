package com.airbnb.android.payout.manage;

import android.app.Activity;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.manage.controllers.ManagePayoutDataController;

public class ManagePayoutActivity extends AirActivity implements ManagePayoutControllerInterface {
    private ManagePayoutDataController dataController;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C7552R.layout.activity_simple_payout_fragment);
        ButterKnife.bind((Activity) this);
        this.dataController = new ManagePayoutDataController(this.requestManager, savedInstanceState);
        if (savedInstanceState == null) {
            showFragment(EditPayoutFragment.newInstance(), C7552R.C7554id.content_container, FragmentTransitionType.SlideInFromSide, true);
        }
    }

    public ManagePayoutDataController getDataController() {
        return this.dataController;
    }
}
