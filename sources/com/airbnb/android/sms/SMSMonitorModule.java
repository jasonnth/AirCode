package com.airbnb.android.sms;

import android.content.Context;
import com.airbnb.android.core.explore.ChildScope;

public class SMSMonitorModule {
    @ChildScope
    public SMSMonitor provideSMSMonitor(Context context) {
        return new SMSMonitor(context);
    }
}
