package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p002v7.widget.Toolbar;
import android.text.TextUtils;
import android.webkit.WebView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.AlipayAnalytics;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.fragments.ProgressDialogFragment;
import com.airbnb.android.core.fragments.ProgressDialogFragment.ProgressDialogListener;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationPaymentRedirect;
import com.airbnb.android.core.models.UserWebSession;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.AlipayUrlRequest;
import com.airbnb.android.core.requests.ReservationRequest;
import com.airbnb.android.core.requests.WebSessionRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.AlipayUrlResponse;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.responses.WebSessionResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.android.core.views.AirWebView.AirWebViewCallbacks;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class PayWithAlipayActivity extends AirActivity {
    private static final String DIALOG_LOADING = "payment_processing_dialog";
    private static final int DIALOG_SUCCESS_DELAY = 1000;
    public static final String EXTRA_ERROR_MESSAGE = "extra_error_message";
    public static final String EXTRA_RESERVATION = "extra_reservation";
    private static final String EXTRA_RESULT_CODE = "extra_result_code";
    private static final String EXTRA_RESULT_DATA = "extra_result_data";
    private static final String EXTRA_STATE_ORDINAL = "extra_state";
    private static final String KEY_ALIPAY_REDIRECT = "key_alipay_redirect";
    private static final String KEY_WEB_SESSION = "key_web_session";
    private static final String REDIRECTED_PATH = "/payments/payment_result_available";
    @BindView
    AirWebView airWebView;
    AlipayAnalytics alipayAnalytics;
    /* access modifiers changed from: private */
    public ReservationPaymentRedirect alipayRedirect;
    private final NonResubscribableRequestListener<AlipayUrlResponse> alipayUrlRequestListener = new NonResubscribableRequestListener<AlipayUrlResponse>() {
        public void onResponse(AlipayUrlResponse response) {
            PayWithAlipayActivity.this.alipayRedirect = response.paymentRedirect;
        }

        public void onErrorResponse(AirRequestNetworkException error) {
            PayWithAlipayActivity.this.alipayAnalytics.trackError("redirect_request_failed");
        }
    };
    private final NonResubscribableRequestListener<AirBatchResponse> initializationBatchRequestListener = new NonResubscribableRequestListener<AirBatchResponse>() {
        public void onResponse(AirBatchResponse response) {
            if (PayWithAlipayActivity.this.webSession == null || PayWithAlipayActivity.this.alipayRedirect == null) {
                PayWithAlipayActivity.this.setState(PaymentState.InitializationFailed);
            } else if (PayWithAlipayActivity.this.alipayRedirect.isRedirectNeeded()) {
                PayWithAlipayActivity.this.setState(PaymentState.WaitingForUserAuthorization);
                PayWithAlipayActivity.this.airWebView.setAirbnbSession(PayWithAlipayActivity.this.webSession);
                PayWithAlipayActivity.this.airWebView.loadUrl(PayWithAlipayActivity.this.alipayRedirect.getUrl());
            } else {
                PayWithAlipayActivity.this.setState(PaymentState.AuthorizationFinished);
            }
        }

        public void onErrorResponse(AirRequestNetworkException exception) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("Alipay batch initialization failed"));
            PayWithAlipayActivity.this.setState(PaymentState.InitializationFailed);
        }
    };
    @BindView
    LoaderFrame loaderFrame;
    private Reservation reservation;
    final RequestListener<ReservationResponse> reservationStatusRequestListener = new C0699RL().onResponse(PayWithAlipayActivity$$Lambda$1.lambdaFactory$(this)).onError(PayWithAlipayActivity$$Lambda$2.lambdaFactory$(this)).build();
    private int resultCode;
    private Intent resultData;
    private final NonResubscribableRequestListener<WebSessionResponse> sessionRequestListener = new NonResubscribableRequestListener<WebSessionResponse>() {
        public void onResponse(WebSessionResponse response) {
            PayWithAlipayActivity.this.webSession = response.userSession;
        }

        public void onErrorResponse(AirRequestNetworkException exception) {
            PayWithAlipayActivity.this.alipayAnalytics.trackError("cookie_request_failed");
        }
    };
    /* access modifiers changed from: private */
    public PaymentState state;
    /* access modifiers changed from: private */
    public UserWebSession webSession;
    private final AirWebViewCallbacks webViewCallbacks = new AirWebViewCallbacks() {
        public void onPageStarted(WebView webView, String url) {
            super.onPageStarted(webView, url);
            PayWithAlipayActivity.this.alipayAnalytics.trackWebPageLoadStart(url);
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            PayWithAlipayActivity.this.alipayAnalytics.trackWebPageLoadFinish(url);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (!PayWithAlipayActivity.this.isRedirectedFromAlipayWebView(url)) {
                return false;
            }
            if (PayWithAlipayActivity.this.isActivityResumed()) {
                PayWithAlipayActivity.this.setState(PaymentState.AuthorizationFinished);
            } else {
                PayWithAlipayActivity.this.state = PaymentState.AuthorizationFinished;
            }
            return true;
        }
    };

    private enum PaymentState {
        InitializationRequest,
        WaitingForUserAuthorization,
        CanceledByUser,
        AuthorizationFinished,
        InitializationFailed,
        ReturningResult
    }

    /* access modifiers changed from: protected */
    public boolean setFlagSecure() {
        return BuildHelper.isReleaseBuild();
    }

    static /* synthetic */ void lambda$new$0(PayWithAlipayActivity payWithAlipayActivity, ReservationResponse response) {
        Reservation reservation2 = response.reservation;
        if (reservation2.isInitialChargeSuccessful()) {
            payWithAlipayActivity.alipayAnalytics.trackPaymentSuccess();
            payWithAlipayActivity.returnSuccess(reservation2);
        } else if (payWithAlipayActivity.state != PaymentState.CanceledByUser) {
            payWithAlipayActivity.alipayAnalytics.trackPaymentFailed();
            payWithAlipayActivity.returnError(payWithAlipayActivity.getString(C0880R.string.error_alipay));
        } else {
            payWithAlipayActivity.alipayAnalytics.trackPaymentCancelled();
            payWithAlipayActivity.returnCanceled();
        }
    }

    static /* synthetic */ void lambda$new$1(PayWithAlipayActivity payWithAlipayActivity, AirRequestNetworkException error) {
        String errorMsg;
        if (error == null) {
            errorMsg = payWithAlipayActivity.getString(C0880R.string.error_alipay_no_network);
        } else {
            errorMsg = payWithAlipayActivity.getString(C0880R.string.error_alipay);
        }
        payWithAlipayActivity.alipayAnalytics.trackError("reservation_request_failed");
        payWithAlipayActivity.returnError(errorMsg);
    }

    public static Intent intentForReservation(Context context, Reservation reservation2) {
        return new Intent(context, PayWithAlipayActivity.class).putExtra(EXTRA_RESERVATION, reservation2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        setContentView(C0880R.layout.activity_pay_with_alipay);
        ButterKnife.bind((Activity) this);
        setSupportActionBar((Toolbar) findViewById(C0880R.C0882id.toolbar));
        setupActionBar(C0880R.string.title_pay_with_alipay, new Object[0]);
        this.reservation = (Reservation) getIntent().getParcelableExtra(EXTRA_RESERVATION);
        this.alipayAnalytics.init(this.reservation.getId());
        this.airWebView.addCallbacks(this.webViewCallbacks);
        if (savedInstanceState == null) {
            this.alipayAnalytics.trackBeginFlow();
            this.state = PaymentState.InitializationRequest;
            return;
        }
        this.state = PaymentState.values()[savedInstanceState.getInt(EXTRA_STATE_ORDINAL)];
        this.alipayRedirect = (ReservationPaymentRedirect) savedInstanceState.getParcelable(KEY_ALIPAY_REDIRECT);
        this.webSession = (UserWebSession) savedInstanceState.getParcelable(KEY_WEB_SESSION);
        this.resultCode = savedInstanceState.getInt(EXTRA_RESULT_CODE);
        this.resultData = (Intent) savedInstanceState.getParcelable(EXTRA_RESULT_DATA);
        if (this.state == PaymentState.WaitingForUserAuthorization) {
            this.airWebView.setAirbnbSession(this.webSession);
            if (!this.airWebView.restoreState(savedInstanceState)) {
                this.airWebView.loadUrl(this.alipayRedirect.getUrl());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        setState(this.state);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.airWebView.removeCallbacks(this.webViewCallbacks);
        this.airWebView.onDestroy();
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.airWebView.saveState(outState);
        outState.putInt(EXTRA_STATE_ORDINAL, this.state.ordinal());
        outState.putParcelable(KEY_ALIPAY_REDIRECT, this.alipayRedirect);
        outState.putParcelable(KEY_WEB_SESSION, this.webSession);
        outState.putInt(EXTRA_RESULT_CODE, this.resultCode);
        outState.putParcelable(EXTRA_RESULT_DATA, this.resultData);
    }

    /* access modifiers changed from: private */
    public void setState(PaymentState state2) {
        this.state = state2;
        track(Strap.make().mo11639kv(BaseAnalytics.OPERATION, "set_state"));
        switch (this.state) {
            case InitializationRequest:
                sendInitializationRequest();
                return;
            case WaitingForUserAuthorization:
                waitForUserAuthorization();
                return;
            case InitializationFailed:
            case CanceledByUser:
            case AuthorizationFinished:
                queryReservationStatus();
                return;
            case ReturningResult:
                returnResult(false);
                return;
            default:
                if (BuildHelper.isDevelopmentBuild()) {
                    throw new IllegalStateException("No case for state " + this.state);
                }
                return;
        }
    }

    private void sendInitializationRequest() {
        this.airWebView.setVisibility(8);
        this.loaderFrame.startAnimation();
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        requests.add(new AlipayUrlRequest(this.reservation, this.alipayUrlRequestListener));
        requests.add(new WebSessionRequest(this.sessionRequestListener));
        AirBatchRequest batchRequest = new AirBatchRequest(requests, false, this.initializationBatchRequestListener);
        batchRequest.skipCache();
        this.requestManager.execute(batchRequest);
    }

    private void waitForUserAuthorization() {
        this.loaderFrame.finishImmediate();
        this.airWebView.setVisibility(0);
    }

    private void queryReservationStatus() {
        this.airWebView.setVisibility(8);
        this.loaderFrame.setVisibility(8);
        KeyboardUtils.dismissSoftKeyboard(this, this.airWebView);
        this.airWebView.loadUrl("about:blank");
        showLoadingDialog();
        this.requestManager.executeOrResubscribe(ReservationRequest.forChargeAttempted(this.reservation.getId()).withListener((Observer) this.reservationStatusRequestListener).skipCache(), this.reservationStatusRequestListener);
    }

    /* access modifiers changed from: private */
    public boolean isRedirectedFromAlipayWebView(String url) {
        if (TextUtils.isEmpty(url) || this.state != PaymentState.WaitingForUserAuthorization) {
            return false;
        }
        String path = Uri.parse(url).getPath();
        if (path == null || !path.contains(REDIRECTED_PATH) || !this.airWebView.isAirbnbDomain(url)) {
            return false;
        }
        return true;
    }

    private void showLoadingDialog() {
        if (getProgressFragment() == null) {
            ProgressDialogFragment fragment = ProgressDialogFragment.newInstance(this, C0880R.string.confirm_pay_loading, 0);
            fragment.setCancelable(false);
            fragment.show(getSupportFragmentManager(), DIALOG_LOADING);
        }
    }

    private ProgressDialogFragment getProgressFragment() {
        return (ProgressDialogFragment) getSupportFragmentManager().findFragmentByTag(DIALOG_LOADING);
    }

    private void returnSuccess(Reservation reservation2) {
        this.resultData = new Intent();
        this.resultData.putExtra(EXTRA_RESERVATION, reservation2);
        this.resultCode = -1;
        setState(PaymentState.ReturningResult);
    }

    private void returnError(String errorMessage) {
        this.resultData = new Intent();
        this.resultData.putExtra(EXTRA_ERROR_MESSAGE, errorMessage);
        this.resultCode = -1;
        setState(PaymentState.ReturningResult);
    }

    private void returnCanceled() {
        this.resultData = new Intent();
        this.resultCode = 0;
        setState(PaymentState.ReturningResult);
    }

    private void returnResult(boolean immediate) {
        setResult(this.resultCode, this.resultData);
        boolean successful = this.resultData.hasExtra(EXTRA_RESERVATION);
        ProgressDialogFragment progressFragment = getProgressFragment();
        if (progressFragment == null || !successful || immediate) {
            if (progressFragment != null) {
                progressFragment.dismiss();
            }
            finish();
            return;
        }
        progressFragment.setProgressDialogListener(new ProgressDialogListener() {
            public void onProgressCompleted() {
                PayWithAlipayActivity.this.finish();
            }

            public void onProgressCancelled() {
            }
        });
        if (!progressFragment.isProgressComplete()) {
            progressFragment.onProgressComplete(getString(this.reservation.isAccepted() ? C0880R.string.booking_confirm_complete : C0880R.string.booking_confirm_request_sent), "", C0880R.C0881drawable.booking_request_sent, 1000);
        }
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        onBackPressed();
    }

    public void onBackPressed() {
        switch (this.state) {
            case InitializationRequest:
                this.alipayAnalytics.trackCancelPressed();
                super.onBackPressed();
                return;
            case WaitingForUserAuthorization:
                if (this.airWebView.canGoBack()) {
                    this.airWebView.goBack();
                    return;
                }
                this.alipayAnalytics.trackCancelPressed();
                setState(PaymentState.CanceledByUser);
                return;
            case InitializationFailed:
            case CanceledByUser:
            case AuthorizationFinished:
                return;
            case ReturningResult:
                this.alipayAnalytics.trackCancelPressed();
                returnResult(true);
                return;
            default:
                throw new IllegalStateException("No case for state " + this.state);
        }
    }

    private void track(Strap strap) {
        AirbnbEventLogger.track("android_alipay", Strap.make().mo11639kv("state", this.state.name()).mo11638kv("reservation_id", this.reservation.getId()).mix(strap));
    }
}
