package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.MessagingSyncs;
import com.airbnb.android.core.utils.Check;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MessagingSyncResponse extends BaseResponse {
    @JsonProperty("messaging_syncs")
    public List<MessagingSyncs> payload;

    public MessagingSyncs getSync() {
        boolean z = true;
        Check.notNull(this.payload);
        if (this.payload.size() != 1) {
            z = false;
        }
        Check.state(z);
        return (MessagingSyncs) this.payload.get(0);
    }
}
