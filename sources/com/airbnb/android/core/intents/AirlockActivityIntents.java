package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.airlock.models.Airlock;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.rxgroups.Preconditions;

public final class AirlockActivityIntents {
    private static final String AIRLOCK_URL = "https://www.airbnb.com/airlock?al_id=";
    public static final String EXTRA_IS_LOGIN_AIRLOCK = "extra_is_login_airlock";

    public static Intent forAirlock(Context context, Airlock airlock, boolean isLoginAirlock) {
        if (Trebuchet.launch(TrebuchetKeys.AIRLOCK_FORCE_REACT_NATIVE) || (Trebuchet.launch(TrebuchetKeys.AIRLOCK_REACT_NATIVE) && airlock.areAllFrictionsSupportedByRN())) {
            return ReactNativeIntents.intentForAirlock(context, airlock.mo8253id());
        }
        return webViewIntentForAirlock(context, airlock.mo8253id(), isLoginAirlock);
    }

    public static Intent webViewIntentForAirlock(Context context, long airlockId, boolean isLoginAirlock) {
        Preconditions.checkArgument(airlockId > 0, "Invalid airlockId: " + airlockId);
        return WebViewIntentBuilder.newBuilder(context, AIRLOCK_URL + airlockId).setClass(context, Activities.airlock()).title(C0716R.string.airlock_title).authenticate().toIntent().putExtra(EXTRA_IS_LOGIN_AIRLOCK, isLoginAirlock);
    }
}
