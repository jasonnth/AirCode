package com.facebook.accountkit.internal;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import com.facebook.GraphRequest;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.PhoneLoginTracker;
import com.facebook.accountkit.internal.AccountKitGraphRequest.Callback;
import com.facebook.internal.ServerProtocol;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

final class PhoneLoginController extends LoginController<PhoneLoginModelImpl> {
    private static final String PARAMETER_CONFIRMATION_CODE = "confirmation_code";
    private static final String PARAMETER_PHONE = "phone_number";
    private static final String PARAMETER_USER_TOKEN = "fb_user_token";
    /* access modifiers changed from: private */
    public static final String TAG = PhoneLoginController.class.getName();

    PhoneLoginController(AccessTokenManager accessTokenManager, LoginManager loginManager, PhoneLoginModelImpl loginModel) {
        super(accessTokenManager, loginManager, loginModel);
    }

    /* access modifiers changed from: protected */
    public String getCredentialsType() {
        return PARAMETER_PHONE;
    }

    /* access modifiers changed from: protected */
    public String getLoginStateChangedIntentName() {
        return PhoneLoginTracker.ACTION_PHONE_LOGIN_STATE_CHANGED;
    }

    public void logIn(String initialAuthState) {
        Callback requestCallback = new Callback() {
            public void onCompleted(AccountKitGraphResponse response) {
                if (PhoneLoginController.this.getLoginManager() != null && response != null) {
                    try {
                        if (response.getError() != null) {
                            Pair<AccountKitError, InternalAccountKitError> error = Utility.createErrorFromServerError(response.getError());
                            PhoneLoginController.this.onError((AccountKitError) error.first);
                            return;
                        }
                        JSONObject result = response.getResponseObject();
                        if (result == null) {
                            PhoneLoginController.this.onError(Type.LOGIN_INVALIDATED, InternalAccountKitError.NO_RESULT_FOUND);
                            PhoneLoginController.this.broadcastLoginStateChange();
                            return;
                        }
                        String privacyPolicy = result.optString(AccountKitGraphConstants.PRIVACY_POLICY);
                        if (!Utility.isNullOrEmpty(privacyPolicy)) {
                            ((PhoneLoginModelImpl) PhoneLoginController.this.loginModel).putField(AccountKitGraphConstants.PRIVACY_POLICY, privacyPolicy);
                        }
                        String termsOfService = result.optString(AccountKitGraphConstants.TERMS_OF_SERVICE);
                        if (!Utility.isNullOrEmpty(termsOfService)) {
                            ((PhoneLoginModelImpl) PhoneLoginController.this.loginModel).putField(AccountKitGraphConstants.TERMS_OF_SERVICE, termsOfService);
                        }
                        try {
                            long expiresAtMillis = Long.parseLong(result.getString("expires_at")) * 1000;
                            if (result.getBoolean(AccountKitGraphConstants.CAN_ATTEMPT_SEAMLESS_LOGIN_KEY) && expiresAtMillis > System.currentTimeMillis()) {
                                ((PhoneLoginModelImpl) PhoneLoginController.this.loginModel).setStatus(LoginStatus.ACCOUNT_VERIFIED);
                                PhoneLoginController.this.broadcastLoginStateChange();
                                return;
                            }
                        } catch (JSONException e) {
                        }
                        try {
                            String loginModelCode = result.getString(AccountKitGraphConstants.LOGIN_REQUEST_CODE_KEY);
                            ((PhoneLoginModelImpl) PhoneLoginController.this.loginModel).setExpiresInSeconds(Long.parseLong(result.getString(AccountKitGraphConstants.EXPIRES_IN_SEC_KEY)));
                            String minResendIntervalSecString = result.optString(AccountKitGraphConstants.MIN_RESEND_INTERVAL_SEC);
                            if (!Utility.isNullOrEmpty(minResendIntervalSecString)) {
                                ((PhoneLoginModelImpl) PhoneLoginController.this.loginModel).setResendTime(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(Long.parseLong(minResendIntervalSecString)));
                            } else {
                                ((PhoneLoginModelImpl) PhoneLoginController.this.loginModel).setResendTime(System.currentTimeMillis());
                            }
                            ((PhoneLoginModelImpl) PhoneLoginController.this.loginModel).setStatus(LoginStatus.PENDING);
                            ((PhoneLoginModelImpl) PhoneLoginController.this.loginModel).setLoginCode(loginModelCode);
                        } catch (JSONException e2) {
                            JSONException jSONException = e2;
                        } catch (NumberFormatException e3) {
                            NumberFormatException numberFormatException = e3;
                        }
                        PhoneLoginController.this.broadcastLoginStateChange();
                    } finally {
                        PhoneLoginController.this.broadcastLoginStateChange();
                    }
                } else {
                    return;
                }
                PhoneLoginController.this.onError(Type.LOGIN_INVALIDATED, InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT);
                PhoneLoginController.this.broadcastLoginStateChange();
            }
        };
        String phoneNumberString = ((PhoneLoginModelImpl) this.loginModel).getPhoneNumber().toString();
        Bundle parameters = new Bundle();
        Utility.putNonNullString(parameters, PARAMETER_PHONE, phoneNumberString);
        Utility.putNonNullString(parameters, "state", initialAuthState);
        Utility.putNonNullString(parameters, ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, ((PhoneLoginModelImpl) this.loginModel).getResponseType());
        Utility.putNonNullString(parameters, GraphRequest.FIELDS_PARAM, AccountKitGraphConstants.TERMS_OF_SERVICE_AND_PRIVACY_POLICY);
        switch (((PhoneLoginModelImpl) this.loginModel).getNotificationChannel()) {
            case FACEBOOK:
                Utility.putNonNullString(parameters, AccountKitGraphConstants.PARAMETER_NOTIFICATION_MEDIUM, "facebook");
                break;
            case VOICE_CALLBACK:
                Utility.putNonNullString(parameters, AccountKitGraphConstants.PARAMETER_NOTIFICATION_MEDIUM, "voice");
                break;
        }
        LoginManager loginManager = getLoginManager();
        if (loginManager != null) {
            if (loginManager.isSeamlessLoginRunning()) {
                loginManager.getLogger().logFetchEvent(InternalLogger.EVENT_NAME_FETCH_SEAMLESS_LOGIN_TOKEN, InternalLogger.EVENT_PARAM_EXTRAS_NOT_COMPLETED);
            } else {
                Utility.putNonNullString(parameters, PARAMETER_USER_TOKEN, loginManager.getSeamlessLoginToken());
            }
        }
        ((PhoneLoginModelImpl) this.loginModel).setInitialAuthState(initialAuthState);
        AccountKitGraphRequest request = buildGraphRequest("start_login", parameters);
        AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
        AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(AccountKitGraphRequest.executeAsync(request, requestCallback));
    }

    public void onCancel() {
        ((PhoneLoginModelImpl) this.loginModel).setStatus(LoginStatus.CANCELLED);
        broadcastLoginStateChange();
        AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
    }

    public void onPending() {
        if (!Utility.isNullOrEmpty(((PhoneLoginModelImpl) this.loginModel).getConfirmationCode())) {
            Validate.loginModelInProgress(this.loginModel);
            final LoginManager loginManager = getLoginManager();
            if (loginManager != null) {
                loginManager.onLoginVerify(this.loginModel);
                Callback requestCallback = new Callback() {
                    /* JADX WARNING: Removed duplicated region for block: B:47:0x0154  */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void onCompleted(com.facebook.accountkit.internal.AccountKitGraphResponse r8) {
                        /*
                            r7 = this;
                            r6 = 0
                            com.facebook.accountkit.internal.LoginManager r3 = r0
                            boolean r3 = r3.isActivityAvailable()
                            if (r3 != 0) goto L_0x0014
                            java.lang.String r3 = com.facebook.accountkit.internal.PhoneLoginController.TAG
                            java.lang.String r4 = "Warning: Callback issues while activity not available."
                            android.util.Log.w(r3, r4)
                        L_0x0013:
                            return
                        L_0x0014:
                            if (r8 == 0) goto L_0x0013
                            r1 = 0
                            com.facebook.accountkit.internal.AccountKitRequestError r3 = r8.getError()     // Catch:{ all -> 0x0175 }
                            if (r3 == 0) goto L_0x0097
                            com.facebook.accountkit.internal.AccountKitRequestError r3 = r8.getError()     // Catch:{ all -> 0x0175 }
                            android.util.Pair r1 = com.facebook.accountkit.internal.Utility.createErrorFromServerError(r3)     // Catch:{ all -> 0x0175 }
                            java.lang.Object r3 = r1.second     // Catch:{ all -> 0x0175 }
                            com.facebook.accountkit.internal.InternalAccountKitError r3 = (com.facebook.accountkit.internal.InternalAccountKitError) r3     // Catch:{ all -> 0x0175 }
                            boolean r3 = com.facebook.accountkit.internal.Utility.isConfirmationCodeRetryable(r3)     // Catch:{ all -> 0x0175 }
                            if (r3 != 0) goto L_0x0038
                            com.facebook.accountkit.internal.PhoneLoginController r4 = com.facebook.accountkit.internal.PhoneLoginController.this     // Catch:{ all -> 0x0175 }
                            java.lang.Object r3 = r1.first     // Catch:{ all -> 0x0175 }
                            com.facebook.accountkit.AccountKitError r3 = (com.facebook.accountkit.AccountKitError) r3     // Catch:{ all -> 0x0175 }
                            r4.onError(r3)     // Catch:{ all -> 0x0175 }
                        L_0x0038:
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r3 = r3.getStatus()
                            com.facebook.accountkit.internal.LoginStatus r4 = com.facebook.accountkit.internal.LoginStatus.ERROR
                            if (r3 != r4) goto L_0x0066
                            if (r1 == 0) goto L_0x0066
                            java.lang.Object r3 = r1.second
                            com.facebook.accountkit.internal.InternalAccountKitError r3 = (com.facebook.accountkit.internal.InternalAccountKitError) r3
                            boolean r3 = com.facebook.accountkit.internal.Utility.isConfirmationCodeRetryable(r3)
                            if (r3 == 0) goto L_0x0066
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r4 = com.facebook.accountkit.internal.LoginStatus.PENDING
                            r3.setStatus(r4)
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            r3.setError(r6)
                        L_0x0066:
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            r3.broadcastLoginStateChange()
                            com.facebook.accountkit.internal.LoginManager r3 = r0
                            com.facebook.accountkit.internal.PhoneLoginController r4 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r4 = r4.loginModel
                            r3.onLoginComplete(r4)
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r3 = r3.getStatus()
                            com.facebook.accountkit.internal.LoginStatus r4 = com.facebook.accountkit.internal.LoginStatus.SUCCESS
                            if (r3 == r4) goto L_0x0090
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r3 = r3.getStatus()
                            com.facebook.accountkit.internal.LoginStatus r4 = com.facebook.accountkit.internal.LoginStatus.ERROR
                            if (r3 != r4) goto L_0x0013
                        L_0x0090:
                            com.facebook.accountkit.internal.LoginManager r3 = r0
                            r3.clearLogIn()
                            goto L_0x0013
                        L_0x0097:
                            org.json.JSONObject r2 = r8.getResponseObject()     // Catch:{ all -> 0x0175 }
                            if (r2 != 0) goto L_0x0105
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this     // Catch:{ all -> 0x0175 }
                            com.facebook.accountkit.AccountKitError$Type r4 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED     // Catch:{ all -> 0x0175 }
                            com.facebook.accountkit.internal.InternalAccountKitError r5 = com.facebook.accountkit.internal.InternalAccountKitError.NO_RESULT_FOUND     // Catch:{ all -> 0x0175 }
                            r3.onError(r4, r5)     // Catch:{ all -> 0x0175 }
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r3 = r3.getStatus()
                            com.facebook.accountkit.internal.LoginStatus r4 = com.facebook.accountkit.internal.LoginStatus.ERROR
                            if (r3 != r4) goto L_0x00d4
                            if (r1 == 0) goto L_0x00d4
                            java.lang.Object r3 = r1.second
                            com.facebook.accountkit.internal.InternalAccountKitError r3 = (com.facebook.accountkit.internal.InternalAccountKitError) r3
                            boolean r3 = com.facebook.accountkit.internal.Utility.isConfirmationCodeRetryable(r3)
                            if (r3 == 0) goto L_0x00d4
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r4 = com.facebook.accountkit.internal.LoginStatus.PENDING
                            r3.setStatus(r4)
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            r3.setError(r6)
                        L_0x00d4:
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            r3.broadcastLoginStateChange()
                            com.facebook.accountkit.internal.LoginManager r3 = r0
                            com.facebook.accountkit.internal.PhoneLoginController r4 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r4 = r4.loginModel
                            r3.onLoginComplete(r4)
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r3 = r3.getStatus()
                            com.facebook.accountkit.internal.LoginStatus r4 = com.facebook.accountkit.internal.LoginStatus.SUCCESS
                            if (r3 == r4) goto L_0x00fe
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r3 = r3.getStatus()
                            com.facebook.accountkit.internal.LoginStatus r4 = com.facebook.accountkit.internal.LoginStatus.ERROR
                            if (r3 != r4) goto L_0x0013
                        L_0x00fe:
                            com.facebook.accountkit.internal.LoginManager r3 = r0
                            r3.clearLogIn()
                            goto L_0x0013
                        L_0x0105:
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this     // Catch:{ JSONException -> 0x0169, NumberFormatException -> 0x01d5 }
                            r3.extractAccessTokenOrCodeIntoModel(r2)     // Catch:{ JSONException -> 0x0169, NumberFormatException -> 0x01d5 }
                        L_0x010a:
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r3 = r3.getStatus()
                            com.facebook.accountkit.internal.LoginStatus r4 = com.facebook.accountkit.internal.LoginStatus.ERROR
                            if (r3 != r4) goto L_0x0138
                            if (r1 == 0) goto L_0x0138
                            java.lang.Object r3 = r1.second
                            com.facebook.accountkit.internal.InternalAccountKitError r3 = (com.facebook.accountkit.internal.InternalAccountKitError) r3
                            boolean r3 = com.facebook.accountkit.internal.Utility.isConfirmationCodeRetryable(r3)
                            if (r3 == 0) goto L_0x0138
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r4 = com.facebook.accountkit.internal.LoginStatus.PENDING
                            r3.setStatus(r4)
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            r3.setError(r6)
                        L_0x0138:
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            r3.broadcastLoginStateChange()
                            com.facebook.accountkit.internal.LoginManager r3 = r0
                            com.facebook.accountkit.internal.PhoneLoginController r4 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r4 = r4.loginModel
                            r3.onLoginComplete(r4)
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r3 = r3.getStatus()
                            com.facebook.accountkit.internal.LoginStatus r4 = com.facebook.accountkit.internal.LoginStatus.SUCCESS
                            if (r3 == r4) goto L_0x0162
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r3 = r3.getStatus()
                            com.facebook.accountkit.internal.LoginStatus r4 = com.facebook.accountkit.internal.LoginStatus.ERROR
                            if (r3 != r4) goto L_0x0013
                        L_0x0162:
                            com.facebook.accountkit.internal.LoginManager r3 = r0
                            r3.clearLogIn()
                            goto L_0x0013
                        L_0x0169:
                            r3 = move-exception
                            r0 = r3
                        L_0x016b:
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this     // Catch:{ all -> 0x0175 }
                            com.facebook.accountkit.AccountKitError$Type r4 = com.facebook.accountkit.AccountKitError.Type.LOGIN_INVALIDATED     // Catch:{ all -> 0x0175 }
                            com.facebook.accountkit.internal.InternalAccountKitError r5 = com.facebook.accountkit.internal.InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT     // Catch:{ all -> 0x0175 }
                            r3.onError(r4, r5)     // Catch:{ all -> 0x0175 }
                            goto L_0x010a
                        L_0x0175:
                            r3 = move-exception
                            r4 = r3
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r3 = r3.getStatus()
                            com.facebook.accountkit.internal.LoginStatus r5 = com.facebook.accountkit.internal.LoginStatus.ERROR
                            if (r3 != r5) goto L_0x01a5
                            if (r1 == 0) goto L_0x01a5
                            java.lang.Object r3 = r1.second
                            com.facebook.accountkit.internal.InternalAccountKitError r3 = (com.facebook.accountkit.internal.InternalAccountKitError) r3
                            boolean r3 = com.facebook.accountkit.internal.Utility.isConfirmationCodeRetryable(r3)
                            if (r3 == 0) goto L_0x01a5
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r5 = com.facebook.accountkit.internal.LoginStatus.PENDING
                            r3.setStatus(r5)
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            r3.setError(r6)
                        L_0x01a5:
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            r3.broadcastLoginStateChange()
                            com.facebook.accountkit.internal.LoginManager r3 = r0
                            com.facebook.accountkit.internal.PhoneLoginController r5 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r5 = r5.loginModel
                            r3.onLoginComplete(r5)
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r3 = r3.getStatus()
                            com.facebook.accountkit.internal.LoginStatus r5 = com.facebook.accountkit.internal.LoginStatus.SUCCESS
                            if (r3 == r5) goto L_0x01cf
                            com.facebook.accountkit.internal.PhoneLoginController r3 = com.facebook.accountkit.internal.PhoneLoginController.this
                            com.facebook.accountkit.internal.LoginModelImpl r3 = r3.loginModel
                            com.facebook.accountkit.internal.PhoneLoginModelImpl r3 = (com.facebook.accountkit.internal.PhoneLoginModelImpl) r3
                            com.facebook.accountkit.internal.LoginStatus r3 = r3.getStatus()
                            com.facebook.accountkit.internal.LoginStatus r5 = com.facebook.accountkit.internal.LoginStatus.ERROR
                            if (r3 != r5) goto L_0x01d4
                        L_0x01cf:
                            com.facebook.accountkit.internal.LoginManager r3 = r0
                            r3.clearLogIn()
                        L_0x01d4:
                            throw r4
                        L_0x01d5:
                            r3 = move-exception
                            r0 = r3
                            goto L_0x016b
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.PhoneLoginController.C33892.onCompleted(com.facebook.accountkit.internal.AccountKitGraphResponse):void");
                    }
                };
                Bundle parameters = new Bundle();
                Utility.putNonNullString(parameters, "confirmation_code", ((PhoneLoginModelImpl) this.loginModel).getConfirmationCode());
                Utility.putNonNullString(parameters, PARAMETER_PHONE, ((PhoneLoginModelImpl) this.loginModel).getPhoneNumber().toString());
                AccountKitGraphRequest request = buildGraphRequest("confirm_login", parameters);
                AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
                AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(AccountKitGraphRequest.executeAsync(request, requestCallback));
            }
        }
    }

    /* access modifiers changed from: private */
    public void extractAccessTokenOrCodeIntoModel(JSONObject result) throws JSONException {
        if (Utility.areObjectsEqual(((PhoneLoginModelImpl) this.loginModel).getResponseType(), "token")) {
            AccessToken token = new AccessToken(result.getString("access_token"), result.getString("id"), AccountKit.getApplicationId(), Long.parseLong(result.getString(AccountKitGraphConstants.TOKEN_REFRESH_INTERVAL_SEC)), new Date());
            this.accessTokenManager.setCurrentAccessToken(token);
            ((PhoneLoginModelImpl) this.loginModel).setFinalAuthState(result.optString("state"));
            ((PhoneLoginModelImpl) this.loginModel).setAccessToken(token);
            ((PhoneLoginModelImpl) this.loginModel).setStatus(LoginStatus.SUCCESS);
            return;
        }
        ((PhoneLoginModelImpl) this.loginModel).setCode(result.getString("code"));
        ((PhoneLoginModelImpl) this.loginModel).setFinalAuthState(result.optString("state"));
        ((PhoneLoginModelImpl) this.loginModel).setStatus(LoginStatus.SUCCESS);
    }

    public void onAccountVerified() {
        Validate.loginModelInProgress(this.loginModel);
        final LoginManager loginManager = getLoginManager();
        if (loginManager != null) {
            loginManager.onSeamlessLoginPending(this.loginModel);
            Callback requestCallback = new Callback() {
                public void onCompleted(AccountKitGraphResponse response) {
                    if (!loginManager.isActivityAvailable()) {
                        Log.w(PhoneLoginController.TAG, "Warning: Callback issues while activity not available.");
                        return;
                    }
                    try {
                        if (response.getError() != null) {
                            PhoneLoginController.this.onError((AccountKitError) Utility.createErrorFromServerError(response.getError()).first);
                            PhoneLoginController.this.broadcastLoginStateChange();
                            loginManager.onLoginComplete(PhoneLoginController.this.loginModel);
                            if (((PhoneLoginModelImpl) PhoneLoginController.this.loginModel).getStatus() == LoginStatus.SUCCESS || ((PhoneLoginModelImpl) PhoneLoginController.this.loginModel).getStatus() == LoginStatus.ERROR) {
                                loginManager.clearLogIn();
                                return;
                            }
                            return;
                        }
                        PhoneLoginController.this.extractAccessTokenOrCodeIntoModel(response.getResponseObject());
                        PhoneLoginController.this.broadcastLoginStateChange();
                        loginManager.onLoginComplete(PhoneLoginController.this.loginModel);
                        if (((PhoneLoginModelImpl) PhoneLoginController.this.loginModel).getStatus() == LoginStatus.SUCCESS || ((PhoneLoginModelImpl) PhoneLoginController.this.loginModel).getStatus() == LoginStatus.ERROR) {
                            loginManager.clearLogIn();
                        }
                    } catch (JSONException e) {
                        PhoneLoginController.this.onError(Type.LOGIN_INVALIDATED, InternalAccountKitError.INVALID_GRAPH_RESULTS_FORMAT);
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        PhoneLoginController.this.broadcastLoginStateChange();
                        loginManager.onLoginComplete(PhoneLoginController.this.loginModel);
                        if (((PhoneLoginModelImpl) PhoneLoginController.this.loginModel).getStatus() == LoginStatus.SUCCESS || ((PhoneLoginModelImpl) PhoneLoginController.this.loginModel).getStatus() == LoginStatus.ERROR) {
                            loginManager.clearLogIn();
                        }
                        throw th2;
                    }
                }
            };
            Bundle parameters = new Bundle();
            Utility.putNonNullString(parameters, PARAMETER_USER_TOKEN, loginManager.getSeamlessLoginToken());
            Utility.putNonNullString(parameters, PARAMETER_PHONE, ((PhoneLoginModelImpl) this.loginModel).getPhoneNumber().toString());
            Utility.putNonNullString(parameters, ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, ((PhoneLoginModelImpl) this.loginModel).getResponseType());
            Utility.putNonNullString(parameters, "state", ((PhoneLoginModelImpl) this.loginModel).getInitialAuthState());
            AccountKitGraphRequest request = buildGraphRequest("instant_verification_login", parameters);
            AccountKitGraphRequestAsyncTask.cancelCurrentAsyncTask();
            AccountKitGraphRequestAsyncTask.setCurrentAsyncTask(AccountKitGraphRequest.executeAsync(request, requestCallback));
        }
    }
}
