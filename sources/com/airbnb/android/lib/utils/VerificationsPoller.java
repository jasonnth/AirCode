package com.airbnb.android.lib.utils;

import android.os.Handler;
import android.util.Log;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.requests.VerificationsRequest;
import com.airbnb.android.core.requests.VerificationsRequest.Filter;
import com.airbnb.android.core.responses.VerificationsResponse;
import com.airbnb.rxgroups.TaggedObserver;

public class VerificationsPoller {
    private static final int DELAY_MILLIS = 10000;
    private static final String TAG = VerificationsPoller.class.getSimpleName();
    private final Filter filter;
    private final Handler handler = new Handler();
    private VerificationsListener listener;
    final RequestListener<VerificationsResponse> requestListener = new C0699RL().onResponse(VerificationsPoller$$Lambda$1.lambdaFactory$(this)).onError(VerificationsPoller$$Lambda$2.lambdaFactory$(this)).build();
    private final RequestManager requestManager;

    public interface VerificationsListener {
        void onReceivedVerifications(VerificationsResponse verificationsResponse);
    }

    public VerificationsPoller(RequestManager requestManager2, Filter filter2, VerificationsListener listener2) {
        this.requestManager = requestManager2;
        this.filter = filter2;
        this.listener = listener2;
        requestManager2.subscribe(this);
    }

    public void start() {
        if (this.listener == null) {
            throw new IllegalStateException(TAG + " needs a listener to deliver the response to.");
        }
        startVerificationsRequest();
    }

    public void stop() {
        this.handler.removeCallbacksAndMessages(null);
    }

    static /* synthetic */ void lambda$new$0(VerificationsPoller verificationsPoller, VerificationsResponse response) {
        verificationsPoller.listener.onReceivedVerifications(response);
        verificationsPoller.startVerificationsRequestAfterDelay();
    }

    static /* synthetic */ void lambda$new$1(VerificationsPoller verificationsPoller, AirRequestNetworkException error) {
        Log.e(TAG, "Error executing v1/verifications request in " + TAG);
        verificationsPoller.startVerificationsRequestAfterDelay();
    }

    private void startVerificationsRequestAfterDelay() {
        this.handler.postDelayed(VerificationsPoller$$Lambda$3.lambdaFactory$(this), 10000);
    }

    /* access modifiers changed from: private */
    public void startVerificationsRequest() {
        if (this.requestManager.hasRequest((BaseRequestListener<T>) this.requestListener, VerificationsRequest.class)) {
            this.requestManager.resubscribe((TaggedObserver<T>) this.requestListener, VerificationsRequest.class);
            return;
        }
        this.requestManager.execute(new VerificationsRequest(this.filter, this.requestListener));
    }
}
