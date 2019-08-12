package com.airbnb.android.lib;

import com.airbnb.android.core.messaging.MessagingRequestFactory;
import com.airbnb.android.core.security.ThreatMetrixClient;

public interface RiskGraph {
    MessagingRequestFactory messagingRequestFactory();

    ThreatMetrixClient threatMetrixClient();
}
