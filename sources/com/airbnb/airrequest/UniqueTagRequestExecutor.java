package com.airbnb.airrequest;

import com.airbnb.rxgroups.RequestSubscription;

public class UniqueTagRequestExecutor implements RequestExecutor {
    private final RequestManager requestManager;

    public UniqueTagRequestExecutor(RequestManager requestManager2) {
        this.requestManager = requestManager2;
    }

    public boolean isDebugFeaturesEnabled() {
        return this.requestManager.isDebugFeaturesEnabled();
    }

    public <T> RequestSubscription execute(BaseRequest<T> request) {
        return this.requestManager.executeWithTag(request, request.getClass().getSimpleName() + DateTimeUtils.currentTimeMillis());
    }
}
