package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.models.Thread;

public final class ThreadBlockActivityIntents {
    public static final String ARG_THREAD = "thread";

    private ThreadBlockActivityIntents() {
    }

    public static Intent newIntent(Context context, Thread thread) {
        return new Intent(context, Activities.threadBlock()).putExtra(ARG_THREAD, thread);
    }
}
