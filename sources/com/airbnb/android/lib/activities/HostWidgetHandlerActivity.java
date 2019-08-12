package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.host.HostReservationIntentFactory;

public class HostWidgetHandlerActivity extends AirActivity {
    private static final String EXTRA_CONFIRMATION_CODE = "confirmation_code";

    public static Intent createGenericIntent(Context context) {
        return new Intent(context, HostWidgetHandlerActivity.class);
    }

    public static Intent createIntentForConfirmationCode(Context context, String confirmationCode) {
        return createGenericIntent(context).putExtra("confirmation_code", Check.notEmpty(confirmationCode));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(HostReservationIntentFactory.forConfirmationCode(this, Check.notEmpty(getIntent().getExtras().getString("confirmation_code")), ROLaunchSource.HostHomeWidget));
        finish();
    }
}
