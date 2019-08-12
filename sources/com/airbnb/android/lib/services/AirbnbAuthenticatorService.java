package com.airbnb.android.lib.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.airbnb.android.core.authentication.AirbnbAuthenticator;

public class AirbnbAuthenticatorService extends Service {
    public IBinder onBind(Intent intent) {
        return new AirbnbAuthenticator(getApplication()).getIBinder();
    }
}
