package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.requests.WishlistsRequest;
import com.airbnb.android.core.responses.WishlistsResponse;
import com.airbnb.android.lib.C0880R;
import icepick.State;
import okhttp3.Response;

public class RequestTestSuiteActivity extends AirActivity {
    @BindView
    CheckBox checkDouble;
    private boolean isDouble;
    private int lastRequestTime = ((int) (System.currentTimeMillis() / 1000));
    private final NonResubscribableRequestListener<WishlistsResponse> listener = new NonResubscribableRequestListener<WishlistsResponse>() {
        public void onResponse(WishlistsResponse response) {
            RequestTestSuiteActivity.this.appendLog("*");
            RequestTestSuiteActivity.this.appendLog("onResponse collectionsCount=" + response.getWishLists().size());
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            Log.e("RequestTestSuite", "onError()", e);
            RequestTestSuiteActivity.this.appendLog("*");
            RequestTestSuiteActivity.this.appendLog("onErrorResponse e=" + e.getMessage());
        }
    };
    @State
    String logOutput;
    @BindView
    ScrollView scrollView;
    @BindView
    TextView txtOutput;
    @BindView
    TextView txtSoftTTL;
    @BindView
    TextView txtTTL;

    private final class DebugRequest extends WishlistsRequest {
        DebugRequest(BaseRequestListener<WishlistsResponse> listener) {
            super(0, listener);
        }

        public AirResponse<WishlistsResponse> transformResponse(AirResponse<WishlistsResponse> response) {
            Response rawResponse = response.raw();
            RequestTestSuiteActivity.this.appendLog("Response was cached=" + (rawResponse.networkResponse() == null));
            RequestTestSuiteActivity.this.appendLog("Response is stale=" + RequestTestSuiteActivity.this.isStale(response));
            RequestTestSuiteActivity.this.appendLog("Request Cache-Control=" + rawResponse.request().header("Cache-Control"));
            RequestTestSuiteActivity.this.appendLog("Response Cache-Control=" + rawResponse.header("Cache-Control"));
            return super.transformResponse(response);
        }

        public long getCacheOnlyTimeoutMs() {
            return (long) Integer.parseInt(RequestTestSuiteActivity.this.txtSoftTTL.getText().toString());
        }

        public long getCacheTimeoutMs() {
            return (long) Integer.parseInt(RequestTestSuiteActivity.this.txtTTL.getText().toString());
        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, RequestTestSuiteActivity.class);
    }

    /* access modifiers changed from: private */
    public void appendLog(String text) {
        this.txtOutput.post(RequestTestSuiteActivity$$Lambda$1.lambdaFactory$(this, text));
        this.scrollView.post(RequestTestSuiteActivity$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$appendLog$0(RequestTestSuiteActivity requestTestSuiteActivity, String text) {
        requestTestSuiteActivity.logOutput = requestTestSuiteActivity.txtOutput.getText() + "\n" + text;
        requestTestSuiteActivity.txtOutput.setText(requestTestSuiteActivity.logOutput);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_airrequest_test_suite);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState != null) {
            appendLog(this.logOutput);
        }
    }

    @OnClick
    public void onClickExecute() {
        DebugRequest request = new DebugRequest(this.listener);
        if (this.isDouble) {
            request.doubleResponse().execute(this.requestManager);
        } else {
            request.execute(this.requestManager);
        }
        int currTimeSeconds = (int) (System.currentTimeMillis() / 1000);
        appendLog("-----");
        appendLog("Seconds since last request=" + (currTimeSeconds - this.lastRequestTime));
        appendLog("Double=" + this.isDouble);
        this.lastRequestTime = currTimeSeconds;
    }

    @OnCheckedChanged
    public void onClickDouble() {
        this.isDouble = this.checkDouble.isChecked();
    }

    @OnClick
    public void onClickClearLogs() {
        this.txtOutput.setText("");
        this.logOutput = "";
    }

    /* access modifiers changed from: private */
    public boolean isStale(AirResponse<?> airResponse) {
        for (String warningHeader : airResponse.raw().headers("Warning")) {
            if (warningHeader.startsWith("110")) {
                return true;
            }
        }
        return false;
    }
}
