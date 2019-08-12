package com.airbnb.android.booking.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.booking.events.AlipayDeeplinkResult;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.utils.DeepLinkUtils;

public class AlipayDeeplinkHandlerActivity extends AirActivity {
    private static final String PARAM_NAME_IS_SUCCESS = "is_success";
    private static final String PARAM_VALUE_FALSE = "F";
    private static final String PARAM_VALUE_TRUE = "T";

    public static Intent intentForReturnDeeplink(Context context) {
        return new Intent(context, AlipayDeeplinkHandlerActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleDeeplink(getIntent());
        finish();
    }

    private void handleDeeplink(Intent intent) {
        if (DeepLinkUtils.isDeepLink(intent) && intent.getExtras().containsKey(PARAM_NAME_IS_SUCCESS)) {
            String result = intent.getStringExtra(PARAM_NAME_IS_SUCCESS);
            char c = 65535;
            switch (result.hashCode()) {
                case 70:
                    if (result.equals(PARAM_VALUE_FALSE)) {
                        c = 1;
                        break;
                    }
                    break;
                case 84:
                    if (result.equals(PARAM_VALUE_TRUE)) {
                        c = 0;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    this.bus.post(new AlipayDeeplinkResult(true));
                    return;
                case 1:
                    this.bus.post(new AlipayDeeplinkResult(false));
                    return;
                default:
                    return;
            }
        }
    }
}
