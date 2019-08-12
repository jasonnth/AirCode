package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;

public class DebugMenuIntents {
    public static Intent create(Context context) {
        return new Intent(context, Activities.debugMenu());
    }
}
