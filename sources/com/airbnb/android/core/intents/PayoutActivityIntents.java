package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;

public class PayoutActivityIntents {
    public static final String EXTRA_COUNTRY_CODE = "extra_country_code";
    public static final int REQUEST_CODE_PAYONEER_REDIRECT = 281;

    public static Intent legacyPayoutIntent(Context context, String countryCode) {
        return new Intent(context, Activities.legacyPayout()).putExtra("extra_country_code", countryCode);
    }

    public static Intent forAddPayoutMethod(Context context, String countryCode) {
        return new Intent(context, Activities.addPayoutMethod()).putExtra("extra_country_code", countryCode);
    }

    public static Intent forManagePayoutMethods(Context context) {
        return new Intent(context, Activities.managePayoutMethods());
    }

    public static Intent forSelectPayoutCountry(Context context) {
        return new Intent(context, Activities.selectPayoutCountry());
    }

    public static Intent forPayoutRedirectWebview(Context context, String url) {
        return new WebViewIntentBuilder(context, Activities.payoutRedirectWebview()).url(url).disableLoader().toIntent();
    }
}
