package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.HostEarnings;
import com.airbnb.android.core.utils.Check;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class HostEarningsResponse extends BaseResponse {
    @JsonProperty("host_earnings")
    public List<HostEarnings> earnings;

    public HostEarnings getEarnings() {
        boolean z = true;
        if (this.earnings.size() != 1) {
            z = false;
        }
        Check.state(z);
        return (HostEarnings) this.earnings.get(0);
    }
}
