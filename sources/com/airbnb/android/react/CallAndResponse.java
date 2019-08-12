package com.airbnb.android.react;

import okhttp3.Call;
import okhttp3.Response;

class CallAndResponse {
    final Call call;
    final Response response;

    CallAndResponse(Call call2, Response response2) {
        this.call = call2;
        this.response = response2;
    }
}
