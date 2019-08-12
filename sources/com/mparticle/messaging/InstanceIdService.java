package com.mparticle.messaging;

import com.google.android.gms.iid.InstanceIDListenerService;
import com.mparticle.MParticle.LogLevel;
import com.mparticle.internal.ConfigManager;
import com.mparticle.internal.PushRegistrationHelper;

public class InstanceIdService extends InstanceIDListenerService {
    public void onTokenRefresh() {
        super.onTokenRefresh();
        try {
            PushRegistrationHelper.requestInstanceId(getApplicationContext());
        } catch (Exception e) {
            ConfigManager.log(LogLevel.ERROR, "Error refreshing Instance ID: " + e.getMessage());
        }
    }
}
