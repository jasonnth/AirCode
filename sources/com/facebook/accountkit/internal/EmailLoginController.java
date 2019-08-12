package com.facebook.accountkit.internal;

import android.os.Bundle;
import android.os.Handler;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.EmailLoginTracker;
import com.facebook.accountkit.internal.AccountKitGraphRequest.Callback;
import com.facebook.internal.ServerProtocol;
import org.json.JSONException;
import org.json.JSONObject;

final class EmailLoginController extends LoginController<EmailLoginModelImpl> {
    private static final String PARAMETER_EMAIL = "email";
    private static final int SECONDS_TO_MILLIS = 1000;
    /* access modifiers changed from: private */
    public static final String TAG = EmailLoginController.class.getName();

    private class LoginModelCodeCallback implements Callback {
        final EmailLoginModelImpl loginModel;

        LoginModelCodeCallback(EmailLoginModelImpl loginModel2) {
            this.loginModel = loginModel2;
        }

        /* JADX INFO: finally extract failed */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x01b5  */
        /* JADX WARNING: Removed duplicated region for block: B:89:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onCompleted(com.facebook.accountkit.internal.AccountKitGraphResponse r29) {
            /*
                r28 = this;
                com.facebook.accountkit.internal.Utility.assertUIThread()
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginController r7 = com.facebook.accountkit.internal.EmailLoginController.this
                com.facebook.accountkit.internal.LoginManager r20 = r7.getLoginManager()
                if (r20 != 0) goto L_0x000e
            L_0x000d:
                return
            L_0x000e:
                boolean r7 = r20.isActivityAvailable()
                if (r7 == 0) goto L_0x001a
                boolean r7 = r20.isLoginInProgress()
                if (r7 != 0) goto L_0x0025
            L_0x001a:
                java.lang.String r7 = com.facebook.accountkit.internal.EmailLoginController.TAG
                java.lang.String r10 = "Warning: Callback issues while activity not available."
                android.util.Log.w(r7, r10)
                goto L_0x000d
            L_0x0025:
                com.facebook.accountkit.internal.AccountKitRequestError r7 = r29.getError()     // Catch:{ all -> 0x025b }
                if (r7 == 0) goto L_0x006c
                com.facebook.accountkit.internal.AccountKitRequestError r7 = r29.getError()     // Catch:{ all -> 0x025b }
                android.util.Pair r13 = com.facebook.accountkit.internal.Utility.createErrorFromServerError(r7)     // Catch:{ all -> 0x025b }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginController r10 = com.facebook.accountkit.internal.EmailLoginController.this     // Catch:{ all -> 0x025b }
                java.lang.Object r7 = r13.first     // Catch:{ all -> 0x025b }
                com.facebook.accountkit.AccountKitError r7 = (com.facebook.accountkit.AccountKitError) r7     // Catch:{ all -> 0x025b }
                r10.onError(r7)     // Catch:{ all -> 0x025b }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel
                if (r7 == 0) goto L_0x000d
                int[] r7 = com.facebook.accountkit.internal.EmailLoginController.C33763.$SwitchMap$com$facebook$accountkit$internal$LoginStatus
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r10 = r0.loginModel
                com.facebook.accountkit.internal.LoginStatus r10 = r10.getStatus()
                int r10 = r10.ordinal()
                r7 = r7[r10]
                switch(r7) {
                    case 1: goto L_0x0058;
                    case 2: goto L_0x0058;
                    default: goto L_0x0057;
                }
            L_0x0057:
                goto L_0x000d
            L_0x0058:
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel
                r0 = r20
                r0.onLoginComplete(r7)
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginController r7 = com.facebook.accountkit.internal.EmailLoginController.this
                r7.broadcastLoginStateChange()
                r20.clearLogIn()
                goto L_0x000d
            L_0x006c:
                org.json.JSONObject r22 = r29.getResponseObject()     // Catch:{ all -> 0x025b }
                if (r22 != 0) goto L_0x00af
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginController r7 = com.facebook.accountkit.internal.EmailLoginController.this     // Catch:{ all -> 0x025b }
                com.facebook.accountkit.AccountKitError$Type r10 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED     // Catch:{ all -> 0x025b }
                com.facebook.accountkit.internal.InternalAccountKitError r25 = com.facebook.accountkit.internal.InternalAccountKitError.NO_RESULT_FOUND     // Catch:{ all -> 0x025b }
                r0 = r25
                r7.onError(r10, r0)     // Catch:{ all -> 0x025b }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel
                if (r7 == 0) goto L_0x000d
                int[] r7 = com.facebook.accountkit.internal.EmailLoginController.C33763.$SwitchMap$com$facebook$accountkit$internal$LoginStatus
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r10 = r0.loginModel
                com.facebook.accountkit.internal.LoginStatus r10 = r10.getStatus()
                int r10 = r10.ordinal()
                r7 = r7[r10]
                switch(r7) {
                    case 1: goto L_0x009a;
                    case 2: goto L_0x009a;
                    default: goto L_0x0098;
                }
            L_0x0098:
                goto L_0x000d
            L_0x009a:
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel
                r0 = r20
                r0.onLoginComplete(r7)
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginController r7 = com.facebook.accountkit.internal.EmailLoginController.this
                r7.broadcastLoginStateChange()
                r20.clearLogIn()
                goto L_0x000d
            L_0x00af:
                java.lang.String r7 = "status"
                r0 = r22
                java.lang.String r23 = r0.getString(r7)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                java.lang.String r7 = "pending"
                r0 = r23
                boolean r7 = r0.equals(r7)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                if (r7 == 0) goto L_0x01df
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginController r7 = com.facebook.accountkit.internal.EmailLoginController.this     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r10 = r0.loginModel     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                com.facebook.accountkit.internal.EmailLoginController$LoginModelCodeCallback r25 = new com.facebook.accountkit.internal.EmailLoginController$LoginModelCodeCallback     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginController r0 = com.facebook.accountkit.internal.EmailLoginController.this     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r26 = r0
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r0 = r0.loginModel     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r27 = r0
                r25.<init>(r27)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r25
                java.lang.Runnable r21 = r7.createPolling(r10, r0)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                if (r21 != 0) goto L_0x0114
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel
                if (r7 == 0) goto L_0x000d
                int[] r7 = com.facebook.accountkit.internal.EmailLoginController.C33763.$SwitchMap$com$facebook$accountkit$internal$LoginStatus
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r10 = r0.loginModel
                com.facebook.accountkit.internal.LoginStatus r10 = r10.getStatus()
                int r10 = r10.ordinal()
                r7 = r7[r10]
                switch(r7) {
                    case 1: goto L_0x00ff;
                    case 2: goto L_0x00ff;
                    default: goto L_0x00fd;
                }
            L_0x00fd:
                goto L_0x000d
            L_0x00ff:
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel
                r0 = r20
                r0.onLoginComplete(r7)
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginController r7 = com.facebook.accountkit.internal.EmailLoginController.this
                r7.broadcastLoginStateChange()
                r20.clearLogIn()
                goto L_0x000d
            L_0x0114:
                java.lang.String r7 = "interval_sec"
                r0 = r22
                java.lang.String r19 = r0.getString(r7)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                int r18 = java.lang.Integer.parseInt(r19)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r18
                r7.setInterval(r0)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                java.lang.String r7 = "expires_in_sec"
                r0 = r22
                java.lang.String r16 = r0.getString(r7)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                long r14 = java.lang.Long.parseLong(r16)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r7.setExpiresInSeconds(r14)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                int r7 = r7.getInterval()     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                long r0 = (long) r7     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r26 = r0
                int r7 = (r14 > r26 ? 1 : (r14 == r26 ? 0 : -1))
                if (r7 >= 0) goto L_0x018a
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginController r7 = com.facebook.accountkit.internal.EmailLoginController.this     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                com.facebook.accountkit.AccountKitError$Type r10 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                com.facebook.accountkit.internal.InternalAccountKitError r25 = com.facebook.accountkit.internal.InternalAccountKitError.EXPIRED_EMAIL_REQUEST     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r25
                r7.onError(r10, r0)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel
                if (r7 == 0) goto L_0x000d
                int[] r7 = com.facebook.accountkit.internal.EmailLoginController.C33763.$SwitchMap$com$facebook$accountkit$internal$LoginStatus
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r10 = r0.loginModel
                com.facebook.accountkit.internal.LoginStatus r10 = r10.getStatus()
                int r10 = r10.ordinal()
                r7 = r7[r10]
                switch(r7) {
                    case 1: goto L_0x0175;
                    case 2: goto L_0x0175;
                    default: goto L_0x0173;
                }
            L_0x0173:
                goto L_0x000d
            L_0x0175:
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel
                r0 = r20
                r0.onLoginComplete(r7)
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginController r7 = com.facebook.accountkit.internal.EmailLoginController.this
                r7.broadcastLoginStateChange()
                r20.clearLogIn()
                goto L_0x000d
            L_0x018a:
                boolean r7 = r20.isActivityAvailable()     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                if (r7 != 0) goto L_0x0196
                boolean r7 = r20.isLoginInProgress()     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                if (r7 == 0) goto L_0x01af
            L_0x0196:
                android.os.Handler r7 = new android.os.Handler     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r7.<init>()     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r10 = r0.loginModel     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                int r10 = r10.getInterval()     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                int r10 = r10 * 1000
                long r0 = (long) r10     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r26 = r0
                r0 = r21
                r1 = r26
                r7.postDelayed(r0, r1)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
            L_0x01af:
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel
                if (r7 == 0) goto L_0x000d
                int[] r7 = com.facebook.accountkit.internal.EmailLoginController.C33763.$SwitchMap$com$facebook$accountkit$internal$LoginStatus
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r10 = r0.loginModel
                com.facebook.accountkit.internal.LoginStatus r10 = r10.getStatus()
                int r10 = r10.ordinal()
                r7 = r7[r10]
                switch(r7) {
                    case 1: goto L_0x01ca;
                    case 2: goto L_0x01ca;
                    default: goto L_0x01c8;
                }
            L_0x01c8:
                goto L_0x000d
            L_0x01ca:
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel
                r0 = r20
                r0.onLoginComplete(r7)
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginController r7 = com.facebook.accountkit.internal.EmailLoginController.this
                r7.broadcastLoginStateChange()
                r20.clearLogIn()
                goto L_0x000d
            L_0x01df:
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                java.lang.String r7 = r7.getResponseType()     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                java.lang.String r10 = "token"
                boolean r7 = com.facebook.accountkit.internal.Utility.areObjectsEqual(r7, r10)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                if (r7 == 0) goto L_0x0278
                java.lang.String r7 = "access_token"
                r0 = r22
                java.lang.String r5 = r0.getString(r7)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                java.lang.String r7 = "id"
                r0 = r22
                java.lang.String r6 = r0.getString(r7)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                java.lang.String r7 = "token_refresh_interval_sec"
                r0 = r22
                java.lang.String r24 = r0.getString(r7)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                long r8 = java.lang.Long.parseLong(r24)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                com.facebook.accountkit.AccessToken r4 = new com.facebook.accountkit.AccessToken     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                java.lang.String r7 = com.facebook.accountkit.AccountKit.getApplicationId()     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                java.util.Date r10 = new java.util.Date     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r10.<init>()     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r4.<init>(r5, r6, r7, r8, r10)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginController r7 = com.facebook.accountkit.internal.EmailLoginController.this     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                com.facebook.accountkit.internal.AccessTokenManager r7 = r7.accessTokenManager     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r7.setCurrentAccessToken(r4)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                java.lang.String r7 = "state"
                r0 = r22
                java.lang.String r17 = r0.optString(r7)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r17
                r7.setFinalAuthState(r0)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r7.setAccessToken(r4)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                com.facebook.accountkit.internal.LoginStatus r10 = com.facebook.accountkit.internal.LoginStatus.SUCCESS     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r7.setStatus(r10)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                goto L_0x01af
            L_0x024a:
                r7 = move-exception
                r12 = r7
            L_0x024c:
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginController r7 = com.facebook.accountkit.internal.EmailLoginController.this     // Catch:{ all -> 0x025b }
                com.facebook.accountkit.AccountKitError$Type r10 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED     // Catch:{ all -> 0x025b }
                com.facebook.accountkit.internal.InternalAccountKitError r25 = com.facebook.accountkit.internal.InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT     // Catch:{ all -> 0x025b }
                r0 = r25
                r7.onError(r10, r0)     // Catch:{ all -> 0x025b }
                goto L_0x01af
            L_0x025b:
                r7 = move-exception
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r10 = r0.loginModel
                if (r10 == 0) goto L_0x0277
                int[] r10 = com.facebook.accountkit.internal.EmailLoginController.C33763.$SwitchMap$com$facebook$accountkit$internal$LoginStatus
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r0 = r0.loginModel
                r25 = r0
                com.facebook.accountkit.internal.LoginStatus r25 = r25.getStatus()
                int r25 = r25.ordinal()
                r10 = r10[r25]
                switch(r10) {
                    case 1: goto L_0x02a8;
                    case 2: goto L_0x02a8;
                    default: goto L_0x0277;
                }
            L_0x0277:
                throw r7
            L_0x0278:
                java.lang.String r7 = "code"
                r0 = r22
                java.lang.String r11 = r0.getString(r7)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r7.setCode(r11)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                java.lang.String r7 = "state"
                r0 = r22
                java.lang.String r17 = r0.optString(r7)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r17
                r7.setFinalAuthState(r0)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r7 = r0.loginModel     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                com.facebook.accountkit.internal.LoginStatus r10 = com.facebook.accountkit.internal.LoginStatus.SUCCESS     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                r7.setStatus(r10)     // Catch:{ JSONException -> 0x024a, NumberFormatException -> 0x02a5 }
                goto L_0x01af
            L_0x02a5:
                r7 = move-exception
                r12 = r7
                goto L_0x024c
            L_0x02a8:
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginModelImpl r10 = r0.loginModel
                r0 = r20
                r0.onLoginComplete(r10)
                r0 = r28
                com.facebook.accountkit.internal.EmailLoginController r10 = com.facebook.accountkit.internal.EmailLoginController.this
                r10.broadcastLoginStateChange()
                r20.clearLogIn()
                goto L_0x0277
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.EmailLoginController.LoginModelCodeCallback.onCompleted(com.facebook.accountkit.internal.AccountKitGraphResponse):void");
        }
    }

    EmailLoginController(AccessTokenManager accessTokenManager, LoginManager loginManager, EmailLoginModelImpl loginModel) {
        super(accessTokenManager, loginManager, loginModel);
    }

    /* access modifiers changed from: protected */
    public String getCredentialsType() {
        return "email";
    }

    /* access modifiers changed from: protected */
    public String getLoginStateChangedIntentName() {
        return EmailLoginTracker.ACTION_EMAIL_LOGIN_STATE_CHANGED;
    }

    public void logIn(String initialAuthState) {
        Callback requestCallback = new Callback() {
            public void onCompleted(AccountKitGraphResponse response) {
                LoginManager loginManager = EmailLoginController.this.getLoginManager();
                if (loginManager != null) {
                    try {
                        if (response.getError() != null) {
                            EmailLoginController.this.onError((AccountKitError) Utility.createErrorFromServerError(response.getError()).first);
                            return;
                        }
                        JSONObject result = response.getResponseObject();
                        if (result == null) {
                            EmailLoginController.this.onError(Type.LOGIN_INVALIDATED, InternalAccountKitError.NO_RESULT_FOUND);
                            EmailLoginController.this.broadcastLoginStateChange();
                            return;
                        }
                        try {
                            ((EmailLoginModelImpl) EmailLoginController.this.loginModel).setLoginCode(result.getString(AccountKitGraphConstants.LOGIN_REQUEST_CODE_KEY));
                            ((EmailLoginModelImpl) EmailLoginController.this.loginModel).setExpiresInSeconds(Long.parseLong(result.getString(AccountKitGraphConstants.EXPIRES_IN_SEC_KEY)));
                            ((EmailLoginModelImpl) EmailLoginController.this.loginModel).setInterval(Integer.parseInt(result.getString(AccountKitGraphConstants.INTERVAL_SEC)));
                            ((EmailLoginModelImpl) EmailLoginController.this.loginModel).setStatus(LoginStatus.PENDING);
                            loginManager.handle(EmailLoginController.this.loginModel);
                        } catch (JSONException e) {
                            JSONException jSONException = e;
                            EmailLoginController.this.onError(Type.LOGIN_INVALIDATED, InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT);
                            EmailLoginController.this.broadcastLoginStateChange();
                        } catch (NumberFormatException e2) {
                            NumberFormatException numberFormatException = e2;
                            EmailLoginController.this.onError(Type.LOGIN_INVALIDATED, InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT);
                            EmailLoginController.this.broadcastLoginStateChange();
                        }
                        EmailLoginController.this.broadcastLoginStateChange();
                    } finally {
                        EmailLoginController.this.broadcastLoginStateChange();
                    }
                }
            }
        };
        Bundle parameters = new Bundle();
        Utility.putNonNullString(parameters, "email", ((EmailLoginModelImpl) this.loginModel).getEmail());
        Utility.putNonNullString(parameters, ServerProtocol.DIALOG_PARAM_REDIRECT_URI, Utility.getRedirectURL());
        Utility.putNonNullString(parameters, "state", initialAuthState);
        Utility.putNonNullString(parameters, ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, ((EmailLoginModelImpl) this.loginModel).getResponseType());
        AccountKitGraphRequest graphRequest = buildGraphRequest("start_login", parameters);
        AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
        AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(AccountKitGraphRequest.executeAsync(graphRequest, requestCallback));
    }

    public void onCancel() {
        ((EmailLoginModelImpl) this.loginModel).setStatus(LoginStatus.CANCELLED);
        broadcastLoginStateChange();
        AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
    }

    public void onPending() {
        LoginManager loginManager = getLoginManager();
        if (loginManager != null && loginManager.isActivityAvailable()) {
            Runnable poll = createPolling((EmailLoginModelImpl) this.loginModel, new LoginModelCodeCallback((EmailLoginModelImpl) this.loginModel));
            if (poll != null) {
                new Handler().postDelayed(poll, (long) (((EmailLoginModelImpl) this.loginModel).getInterval() * 1000));
            }
        }
    }

    public void onAccountVerified() {
    }

    /* access modifiers changed from: private */
    public Runnable createPolling(final EmailLoginModelImpl loginModel, final Callback callback) {
        LoginManager loginManager = getLoginManager();
        if (loginManager == null) {
            return null;
        }
        final String requestInstanceToken = loginManager.getRequestInstanceToken();
        return new Runnable() {
            public void run() {
                Utility.assertUIThread();
                if (checkLoginManager()) {
                    Bundle parameters = new Bundle();
                    Utility.putNonNullString(parameters, "email", loginModel.getEmail());
                    AccountKitGraphRequest graphRequest = EmailLoginController.this.buildGraphRequest("poll_login", parameters);
                    AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
                    AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(AccountKitGraphRequest.executeAsync(graphRequest, new Callback() {
                        public void onCompleted(AccountKitGraphResponse response) {
                            callback.onCompleted(response);
                        }
                    }));
                }
            }

            private boolean checkLoginManager() {
                LoginManager loginManager = EmailLoginController.this.getLoginManager();
                return loginManager != null && requestInstanceToken.equals(loginManager.getRequestInstanceToken()) && loginManager.isLoginInProgress();
            }
        };
    }
}
