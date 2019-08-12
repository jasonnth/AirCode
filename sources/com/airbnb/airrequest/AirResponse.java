package com.airbnb.airrequest;

import com.facebook.share.internal.ShareConstants;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public final class AirResponse<T> {
    private final Response<T> rawResponse;
    private final AirRequest request;

    public AirResponse(AirRequest request2, Response<T> rawResponse2) {
        this.request = (AirRequest) Utils.checkNotNull(request2, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        this.rawResponse = (Response) Utils.checkNotNull(rawResponse2, "rawResponse");
    }

    public AirRequest request() {
        return this.request;
    }

    public Response<T> response() {
        return this.rawResponse;
    }

    public okhttp3.Response raw() {
        return this.rawResponse.raw();
    }

    public int code() {
        return this.rawResponse.code();
    }

    public String message() {
        return this.rawResponse.message();
    }

    public Headers headers() {
        return this.rawResponse.headers();
    }

    public boolean isSuccess() {
        return this.rawResponse.isSuccessful();
    }

    public T body() {
        return this.rawResponse.body();
    }

    public ResponseBody errorBody() {
        return this.rawResponse.errorBody();
    }
}
