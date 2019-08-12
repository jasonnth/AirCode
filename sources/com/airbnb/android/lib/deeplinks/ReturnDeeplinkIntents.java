package com.airbnb.android.lib.deeplinks;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.booking.activities.AlipayDeeplinkHandlerActivity;

public class ReturnDeeplinkIntents {
    public static Intent intentForAlipayPayauth(Context context) {
        return AlipayDeeplinkHandlerActivity.intentForReturnDeeplink(context);
    }

    public static Intent intentForRedirectPay(Context context) {
        return AlipayDeeplinkHandlerActivity.intentForReturnDeeplink(context);
    }
}
