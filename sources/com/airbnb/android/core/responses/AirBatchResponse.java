package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.utils.Check;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class AirBatchResponse extends BaseResponse {
    @JsonProperty("operations")
    public List<BatchOperation> operations;

    public <T extends BaseResponse> T singleResponse(Class<T> clazz) {
        boolean z = true;
        FluentIterable<T> responses = filterResponses(clazz);
        if (responses.size() != 1) {
            z = false;
        }
        Check.state(z, "Expected a single, non-null response");
        return (BaseResponse) responses.get(0);
    }

    public <T extends BaseResponse> T singleResponseOrNull(Class<T> clazz) {
        return (BaseResponse) filterResponses(clazz).first().orNull();
    }

    public <T extends BaseResponse> FluentIterable<T> filterResponses(Class<T> clazz) {
        return FluentIterable.from((Iterable<E>) this.operations).filter(AirBatchResponse$$Lambda$1.lambdaFactory$(clazz)).transform(AirBatchResponse$$Lambda$2.lambdaFactory$(clazz));
    }

    static /* synthetic */ BaseResponse lambda$filterResponses$1(Class clazz, BatchOperation op) {
        return (BaseResponse) clazz.cast(op.convertedResponse);
    }
}
