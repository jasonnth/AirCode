package com.airbnb.airrequest;

import retrofit2.ObservableRequest;

final class ObservableAirRequest {
    private final AirRequest airRequest;
    private final ObservableRequest rawRequest;

    ObservableAirRequest(AirRequest airRequest2, ObservableRequest rawRequest2) {
        this.airRequest = airRequest2;
        this.rawRequest = rawRequest2;
    }

    /* access modifiers changed from: 0000 */
    public ObservableRequest rawRequest() {
        return this.rawRequest;
    }

    /* access modifiers changed from: 0000 */
    public AirRequest airRequest() {
        return this.airRequest;
    }
}
