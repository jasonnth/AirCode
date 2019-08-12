package com.airbnb.android.sms;

import com.airbnb.android.core.apprater.GraphBindings;
import com.airbnb.android.sms.SMSMonitorComponent.Builder;
import javax.inject.Provider;

public interface SMSMonitorBindings extends GraphBindings {
    Provider<Builder> smsMonitorComponentProvider();
}
