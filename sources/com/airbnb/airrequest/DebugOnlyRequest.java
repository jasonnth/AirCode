package com.airbnb.airrequest;

import com.airbnb.rxgroups.RequestSubscription;

abstract class DebugOnlyRequest<T> extends BaseRequestV2<T> {
    DebugOnlyRequest() {
    }

    public RequestSubscription execute(RequestExecutor executor) {
        if (executor.isDebugFeaturesEnabled()) {
            return super.execute(executor);
        }
        throw new IllegalStateException("Sorry, this request can only be executed on debug builds.");
    }
}
