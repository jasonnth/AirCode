package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.Activities;

public final class VerifyEmailActivityIntents {
    public static final String EXTRA_CODE = "code";

    private VerifyEmailActivityIntents() {
    }

    public static Intent forWeb(Context context, String code) {
        return new Intent(context, Activities.verifyEmail()).putExtra("code", code);
    }

    public static Intent forDeepLink(Context context, Bundle bundle) {
        return new Intent(context, Activities.verifyEmail()).putExtra("code", bundle.getString("code"));
    }
}
