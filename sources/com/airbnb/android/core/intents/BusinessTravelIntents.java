package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.businesstravel.WorkEmailLaunchSource;

public class BusinessTravelIntents {
    public static final String EXTRA_CONFIRMATION_CODE = "extra_confirmation_code";
    public static final String EXTRA_LAUNCH_SOURCE = "extra_launch_source";
    public static final String EXTRA_WORK_EMAIL = "extra_work_email";
    public static final String KEY_WORK_EMAIL = "email";
    public static final int REQUEST_CODE_ADD_EMAIL = 468;
    public static final String RESULT_UPDATE_WORK_EMAIL = "update_work_email";

    private BusinessTravelIntents() {
    }

    public static Intent intentForAddWorkEmailFromP5Promo(Context context, String confirmationCode) {
        return intentForWorkEmail(context, WorkEmailLaunchSource.MobileP5Promo).putExtra("extra_confirmation_code", confirmationCode);
    }

    public static Intent intentForWorkEmail(Context context, WorkEmailLaunchSource launchSource) {
        return new Intent(context, Activities.workEmail()).putExtra(EXTRA_LAUNCH_SOURCE, launchSource);
    }

    public static Intent intentForDeepLinkWorkEmailVerified(Context context, Bundle bundle) {
        return new Intent(context, Activities.businessTravelDeepLink()).putExtra(EXTRA_WORK_EMAIL, getStringFromDeeplink(bundle, "email"));
    }

    public static Intent intentForDeepLinkAddWorkEmail(Context context, Bundle bundle) {
        return new Intent(context, Activities.workEmail()).putExtra(EXTRA_LAUNCH_SOURCE, WorkEmailLaunchSource.DeepLink);
    }

    private static String getStringFromDeeplink(Bundle deepLinkBundle, String key) {
        return deepLinkBundle.getString(key);
    }
}
