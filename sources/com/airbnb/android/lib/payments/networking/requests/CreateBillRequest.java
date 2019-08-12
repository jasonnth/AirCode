package com.airbnb.android.lib.payments.networking.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.QuickPayErrorResponse;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.CreateBillRequestBody;
import com.airbnb.android.lib.payments.networking.responses.CreateBillResponse;
import java.lang.reflect.Type;

public class CreateBillRequest extends BaseRequestV2<CreateBillResponse> {
    private final CreateBillRequestBody body;

    public static CreateBillRequest forBody(CreateBillRequestBody body2) {
        return new CreateBillRequest(body2);
    }

    private CreateBillRequest(CreateBillRequestBody body2) {
        this.body = body2;
    }

    public String getPath() {
        return "bills";
    }

    public CreateBillRequestBody getBody() {
        return this.body;
    }

    public Type successResponseType() {
        return CreateBillResponse.class;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type errorResponseType() {
        return QuickPayErrorResponse.class;
    }
}
