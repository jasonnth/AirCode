package com.airbnb.android.superhero;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.superhero.SuperHeroManager;

public class SuperHeroAlarmReceiver extends BroadcastReceiver {
    SuperHeroManager superHeroManager;

    public void onReceive(Context context, Intent intent) {
        ((SuperHeroGraph) CoreApplication.instance(context).component()).inject(this);
        this.superHeroManager.handleReceiverIntent(context, intent);
    }
}
