package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;

public final class ResetPasswordIntents {
    public static final String SECRET = "secret";

    private ResetPasswordIntents() {
    }

    public static Intent forWebLink(Context context, String secret) {
        return new Intent(context, Activities.login()).putExtra("secret", secret);
    }
}
