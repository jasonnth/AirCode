package com.airbnb.android.sms;

import com.airbnb.android.core.explore.ChildScope;

@ChildScope
public interface SMSMonitorComponent {

    public interface Builder {
        SMSMonitorComponent build();
    }

    SMSMonitor smsMonitor();
}
