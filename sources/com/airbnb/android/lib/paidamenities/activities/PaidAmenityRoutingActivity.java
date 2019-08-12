package com.airbnb.android.lib.paidamenities.activities;

import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.intents.PaidAmenityIntents;
import com.airbnb.android.core.paidamenities.enums.PaidAmenityArguments;
import com.airbnb.android.core.react.ReactExposedActivityParamsConstants;
import com.airbnb.android.core.utils.Check;

public class PaidAmenityRoutingActivity extends AirActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PaidAmenityArguments paidAmenityArguments = (PaidAmenityArguments) getIntent().getParcelableExtra(ReactExposedActivityParamsConstants.KEY_ARGUMENT);
        Check.notNull(paidAmenityArguments);
        if (paidAmenityArguments.hasPaidAmenityOrders()) {
            startActivity(GuestPendingAmenityActivity.intent(this, paidAmenityArguments.confirmationCode(), paidAmenityArguments.threadId(), paidAmenityArguments.listingId()));
        } else {
            startActivity(PaidAmenityIntents.purchaseAmenities(this, paidAmenityArguments.confirmationCode(), paidAmenityArguments.threadId(), paidAmenityArguments.listingId()));
        }
        finish();
    }
}
