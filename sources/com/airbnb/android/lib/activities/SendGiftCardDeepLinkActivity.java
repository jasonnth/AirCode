package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.intents.ReactNativeIntents;

public class SendGiftCardDeepLinkActivity extends AirActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(ReactNativeIntents.intentForGiftCardsApp(this));
        finish();
    }
}
