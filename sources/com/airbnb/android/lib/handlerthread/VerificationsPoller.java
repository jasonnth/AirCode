package com.airbnb.android.lib.handlerthread;

import android.util.Log;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.models.VerificationRequirements;
import com.airbnb.android.core.requests.VerificationsRequest;
import com.airbnb.android.core.responses.VerificationsResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import java.util.Timer;
import java.util.TimerTask;

public class VerificationsPoller {
    private static final String TAG = "VerificationsPoller";
    private static final long VERIFICATIONS_POLLING_INTERVAL = 5000;
    /* access modifiers changed from: private */
    public VerificationsListener mListener;
    private TimerTask mTimerTask;

    public interface VerificationsListener {
        void onVerificationsResponse(VerificationRequirements verificationRequirements);
    }

    public void setListener(VerificationsListener listener) {
        this.mListener = listener;
    }

    public VerificationsPoller(VerificationsListener listener) {
        this.mListener = listener;
    }

    public void startPolling() {
        Timer mTimer = new Timer();
        this.mTimerTask = new TimerTask() {
            public void run() {
                new VerificationsRequest(new NonResubscribableRequestListener<VerificationsResponse>() {
                    public void onResponse(VerificationsResponse response) {
                        VerificationsPoller.this.mListener.onVerificationsResponse(response.checkpointVerifications);
                    }

                    public void onErrorResponse(AirRequestNetworkException error) {
                        Log.e(VerificationsPoller.TAG, error.toString());
                    }
                }).execute(NetworkUtil.singleFireExecutor());
            }
        };
        mTimer.scheduleAtFixedRate(this.mTimerTask, 0, VERIFICATIONS_POLLING_INTERVAL);
    }

    public void cancelPolling() {
        if (this.mTimerTask != null) {
            this.mTimerTask.cancel();
        }
    }
}
