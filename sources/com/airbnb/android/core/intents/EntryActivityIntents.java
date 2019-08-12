package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;

public final class EntryActivityIntents {
    public static final String EXTRA_INTENT_TO_LAUNCH = "extra_intent_to_launch";

    private EntryActivityIntents() {
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, Activities.entry());
    }

    public static Intent newIntent(Context context, Intent intentToLaunch) {
        return new Intent(context, Activities.entry()).putExtra(EXTRA_INTENT_TO_LAUNCH, intentToLaunch);
    }
}
