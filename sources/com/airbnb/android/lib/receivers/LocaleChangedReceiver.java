package com.airbnb.android.lib.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.events.LocaleChangedEvent;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.utils.AirbnbConstants;
import com.squareup.otto.Bus;

public class LocaleChangedReceiver extends BroadcastReceiver {
    Bus bus;
    AirbnbPreferences mPreferences;

    public void onReceive(Context context, Intent intent) {
        ((AirbnbGraph) AirbnbApplication.instance(context).component()).inject(this);
        this.bus.post(new LocaleChangedEvent());
        this.mPreferences.getSharedPreferences().edit().remove(AirbnbConstants.PREFS_CURRENCY).remove(AirbnbConstants.PREFS_CURRENCY_IS_USER_SET).apply();
    }
}
