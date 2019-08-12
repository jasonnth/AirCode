package com.airbnb.android.booking.fragments.redirectpay;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.ReservationRequest;
import com.airbnb.android.core.requests.ReservationRequest.Format;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.rxgroups.RequestSubscription;
import java.util.concurrent.TimeUnit;
import p032rx.Observable;
import p032rx.Observer;
import p032rx.Subscription;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.functions.Action0;
import p032rx.functions.Action1;

public class RedirectPayResultHandler {
    private static final long POLLING_INTERVAL = 1000;
    private static final long POLLING_TIMEOUT = 10000;
    /* access modifiers changed from: private */
    public final String appName;
    /* access modifiers changed from: private */
    public boolean isFinished;
    private final Action0 onError;
    /* access modifiers changed from: private */
    public final Action1<Reservation> onSuccess;
    /* access modifiers changed from: private */
    public int queriedTimes;
    private final NonResubscribableRequestListener<ReservationResponse> queryListener = new NonResubscribableRequestListener<ReservationResponse>() {
        public void onResponse(ReservationResponse data) {
            if (data.reservation == null || (!data.reservation.isPending() && !data.reservation.isAccepted())) {
                RedirectPayResultHandler.this.queryPaymentStatusDelay();
                return;
            }
            RedirectPayResultHandler.this.stopPolling();
            RedirectPayResultHandler.this.onSuccess.call(data.reservation);
            RedirectPayResultHandler.this.isFinished = true;
            RedirectPayAnalytics.trackQuerySuccess(RedirectPayResultHandler.this.appName, RedirectPayResultHandler.this.queriedTimes);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            RedirectPayResultHandler.this.queryPaymentStatusDelay();
        }
    };
    private RequestSubscription queryPaymentStateSubscription;
    private final RequestManager requestManager;
    private final long reservationId;
    private Subscription timeoutSubscription;
    private Subscription timerSubscription;

    public RedirectPayResultHandler(RequestManager requestManager2, String appName2, long reservationId2, Action1<Reservation> onSuccess2, Action0 onError2) {
        this.requestManager = requestManager2;
        this.appName = appName2;
        this.reservationId = reservationId2;
        this.onSuccess = onSuccess2;
        this.onError = onError2;
    }

    public void startPolling() {
        this.queriedTimes = 0;
        this.timeoutSubscription = Observable.timer(POLLING_TIMEOUT, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(RedirectPayResultHandler$$Lambda$1.lambdaFactory$(this));
        queryPaymentStatus();
        RedirectPayAnalytics.trackQueryStart(this.appName);
    }

    static /* synthetic */ void lambda$startPolling$0(RedirectPayResultHandler redirectPayResultHandler, Long aLong) {
        redirectPayResultHandler.stopPolling();
        redirectPayResultHandler.onError.call();
        redirectPayResultHandler.isFinished = true;
        RedirectPayAnalytics.trackQueryFail(redirectPayResultHandler.appName, redirectPayResultHandler.queriedTimes);
    }

    public void stopPolling() {
        if (this.timeoutSubscription != null) {
            this.timeoutSubscription.unsubscribe();
        }
        if (this.timerSubscription != null) {
            this.timerSubscription.unsubscribe();
        }
        if (this.queryPaymentStateSubscription != null) {
            this.queryPaymentStateSubscription.unsubscribe();
        }
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    /* access modifiers changed from: private */
    public void queryPaymentStatus() {
        this.queriedTimes++;
        this.queryPaymentStateSubscription = ReservationRequest.forReservationId(this.reservationId, Format.Guest).withListener((Observer) this.queryListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void queryPaymentStatusDelay() {
        this.timerSubscription = Observable.timer(1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(RedirectPayResultHandler$$Lambda$2.lambdaFactory$(this));
    }
}
