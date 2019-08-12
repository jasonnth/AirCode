package com.airbnb.android.react;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import p032rx.SingleSubscriber;

class DelegatingCallback implements Callback {
    private final SingleSubscriber<? super CallAndResponse> subscriber;

    public DelegatingCallback(SingleSubscriber<? super CallAndResponse> subscriber2) {
        this.subscriber = subscriber2;
    }

    public void onFailure(Call call, IOException e) {
        if (!this.subscriber.isUnsubscribed() && !call.isCanceled()) {
            this.subscriber.onError(e);
        }
    }

    public void onResponse(Call call, Response response) throws IOException {
        if (!this.subscriber.isUnsubscribed() && !call.isCanceled()) {
            this.subscriber.onSuccess(new CallAndResponse(call, response));
        }
    }
}
