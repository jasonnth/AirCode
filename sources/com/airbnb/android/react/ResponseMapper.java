package com.airbnb.android.react;

import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NetworkExceptionImpl;
import okhttp3.Response;
import p032rx.Single;
import p032rx.functions.Func1;
import retrofit2.Retrofit;

class ResponseMapper implements Func1<CallAndResponse, Single<? extends CallAndResponse>> {
    private final Retrofit retrofit;

    ResponseMapper(Retrofit retrofit3) {
        this.retrofit = retrofit3;
    }

    public Single<? extends CallAndResponse> call(CallAndResponse callAndResponse) {
        Response response = callAndResponse.response;
        if (response.isSuccessful()) {
            return Single.just(callAndResponse);
        }
        return Single.error(new NetworkExceptionImpl(this.retrofit, retrofit2.Response.error(response.code(), response.body()), ErrorResponse.class));
    }
}
