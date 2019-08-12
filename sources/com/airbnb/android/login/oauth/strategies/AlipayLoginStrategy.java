package com.airbnb.android.login.oauth.strategies;

import android.content.Context;
import android.os.AsyncTask;
import android.support.p002v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.requests.AlipayAuthCodeParamsRequest;
import com.airbnb.android.core.responses.AlipayAuthCodeParamsResponse;
import com.airbnb.android.core.responses.AlipayAuthCodeParamsResponse.AlipayPrepareSDKResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.login.oauth.OAuthLoginManager;
import com.airbnb.android.login.oauth.OAuthOption;
import com.alipay.sdk.app.AuthResult;
import com.alipay.sdk.app.AuthTask;
import p032rx.Observer;

class AlipayLoginStrategy extends OAuthLoginStrategy {
    final RequestListener<AlipayAuthCodeParamsResponse> alipayAuthCodeParamsResponseRequestListener = new C0699RL().onResponse(AlipayLoginStrategy$$Lambda$1.lambdaFactory$(this)).onError(AlipayLoginStrategy$$Lambda$2.lambdaFactory$(this)).build();

    private class AlipayLoginAuthCodeTask extends AsyncTask<String, Void, String> {
        private final Context context;

        public AlipayLoginAuthCodeTask(Context context2) {
            this.context = context2;
        }

        /* access modifiers changed from: protected */
        public String doInBackground(String... strings) {
            if (strings.length != 1) {
                return null;
            }
            return new AuthTask(this.context).auth(strings[0]);
        }
    }

    protected AlipayLoginStrategy(AppCompatActivity appCompatActivity, OAuthLoginManager oauthLoginManager) {
        super(appCompatActivity, oauthLoginManager);
    }

    public void login() {
        CoreApplication.instance().component().singleFireRequestExecutor();
        new AlipayAuthCodeParamsRequest().withListener((Observer) this.alipayAuthCodeParamsResponseRequestListener).execute(NetworkUtil.singleFireExecutor());
    }

    /* access modifiers changed from: private */
    public void onAlipayResponse(AlipayAuthCodeParamsResponse data) {
        new AlipayLoginAuthCodeTask(getActivity()) {
            private static final String RESULT_OK = "200";
            private static final String RESULT_SUCCESS = "9000";

            /* access modifiers changed from: protected */
            public void onPostExecute(String alipayResponse) {
                AuthResult result = new AuthResult(alipayResponse);
                if (!TextUtils.equals(RESULT_OK, result.getResultCode()) || !TextUtils.equals(RESULT_SUCCESS, result.getResultStatus())) {
                    AlipayLoginStrategy.this.finishWithError();
                } else {
                    AlipayLoginStrategy.this.finishWithToken(result.getAuthCode());
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{((AlipayPrepareSDKResponse) data.responseList.get(0)).params});
    }

    /* access modifiers changed from: protected */
    public OAuthOption getOAuthOption() {
        return OAuthOption.Alipay;
    }
}
