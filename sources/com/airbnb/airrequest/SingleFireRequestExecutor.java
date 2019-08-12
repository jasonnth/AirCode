package com.airbnb.airrequest;

public final class SingleFireRequestExecutor extends UniqueTagRequestExecutor {
    public SingleFireRequestExecutor(AirRequestInitializer initializer) {
        this(RequestManager.onCreate(initializer, null, null));
    }

    SingleFireRequestExecutor(RequestManager requestManager) {
        super(requestManager);
        requestManager.onResume();
    }
}
