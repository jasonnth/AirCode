package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.GetCancellationPolicyResponse;
import java.lang.reflect.Type;
import p032rx.Observer;

public class GetCancellationPolicyRequest extends BaseRequestV2<GetCancellationPolicyResponse> {
    private final String policy;

    public GetCancellationPolicyRequest(String policy2, BaseRequestListener<GetCancellationPolicyResponse> listener) {
        withListener((Observer) listener);
        this.policy = policy2;
    }

    public GetCancellationPolicyRequest(String policy2) {
        this.policy = policy2;
    }

    public String getPath() {
        return String.format("cancellation_policies/%s", new Object[]{this.policy});
    }

    public long getCacheTimeoutMs() {
        return 86400000;
    }

    public Type successResponseType() {
        return GetCancellationPolicyResponse.class;
    }
}
