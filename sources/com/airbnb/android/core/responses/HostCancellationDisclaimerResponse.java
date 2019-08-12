package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.HostCancellationDisclaimer;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HostCancellationDisclaimerResponse {
    @JsonProperty("cancellation_disclaimer")
    public HostCancellationDisclaimer cancellationDisclaimer;
}
