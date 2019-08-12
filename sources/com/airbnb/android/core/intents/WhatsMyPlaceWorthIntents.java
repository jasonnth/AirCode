package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Fragments;
import com.airbnb.android.core.activities.AutoFragmentActivity;

public class WhatsMyPlaceWorthIntents {
    public static Intent createIntent(Context context) {
        return AutoFragmentActivity.create(context, Fragments.whatsMyPlaceWorthFragment().getClass()).build();
    }
}
