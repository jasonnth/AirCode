package com.airbnb.airrequest;

import com.airbnb.rxgroups.RequestSubscription;

public interface RequestExecutor {
    <T> RequestSubscription execute(BaseRequest<T> baseRequest);

    boolean isDebugFeaturesEnabled();
}
