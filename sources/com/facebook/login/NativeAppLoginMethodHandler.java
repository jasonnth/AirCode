package com.facebook.login;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;

abstract class NativeAppLoginMethodHandler extends LoginMethodHandler {
    /* access modifiers changed from: 0000 */
    public abstract boolean tryAuthorize(Request request);

    NativeAppLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    NativeAppLoginMethodHandler(Parcel source) {
        super(source);
    }

    /* access modifiers changed from: 0000 */
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        Result outcome;
        Request request = this.loginClient.getPendingRequest();
        if (data == null) {
            outcome = Result.createCancelResult(request, "Operation canceled");
        } else if (resultCode == 0) {
            outcome = handleResultCancel(request, data);
        } else if (resultCode != -1) {
            outcome = Result.createErrorResult(request, "Unexpected resultCode from authorization.", null);
        } else {
            outcome = handleResultOk(request, data);
        }
        if (outcome != null) {
            this.loginClient.completeAndValidate(outcome);
        } else {
            this.loginClient.tryNextHandler();
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [com.facebook.login.LoginClient$Result, java.lang.String] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r7v0, types: [com.facebook.login.LoginClient$Result, java.lang.String]
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
      uses: [com.facebook.login.LoginClient$Result, java.lang.String]
      mth insns count: 35
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.login.LoginClient.Result handleResultOk(com.facebook.login.LoginClient.Request r12, android.content.Intent r13) {
        /*
            r11 = this;
            r7 = 0
            android.os.Bundle r5 = r13.getExtras()
            java.lang.String r1 = r11.getError(r5)
            java.lang.String r8 = "error_code"
            java.lang.String r2 = r5.getString(r8)
            java.lang.String r3 = r11.getErrorMessage(r5)
            java.lang.String r8 = "e2e"
            java.lang.String r0 = r5.getString(r8)
            boolean r8 = com.facebook.internal.Utility.isNullOrEmpty(r0)
            if (r8 != 0) goto L_0x0024
            r11.logWebLoginCompleted(r0)
        L_0x0024:
            if (r1 != 0) goto L_0x0047
            if (r2 != 0) goto L_0x0047
            if (r3 != 0) goto L_0x0047
            java.util.Set r8 = r12.getPermissions()     // Catch:{ FacebookException -> 0x003d }
            com.facebook.AccessTokenSource r9 = com.facebook.AccessTokenSource.FACEBOOK_APPLICATION_WEB     // Catch:{ FacebookException -> 0x003d }
            java.lang.String r10 = r12.getApplicationId()     // Catch:{ FacebookException -> 0x003d }
            com.facebook.AccessToken r6 = createAccessTokenFromWebBundle(r8, r5, r9, r10)     // Catch:{ FacebookException -> 0x003d }
            com.facebook.login.LoginClient$Result r7 = com.facebook.login.LoginClient.Result.createTokenResult(r12, r6)     // Catch:{ FacebookException -> 0x003d }
        L_0x003c:
            return r7
        L_0x003d:
            r4 = move-exception
            java.lang.String r8 = r4.getMessage()
            com.facebook.login.LoginClient$Result r7 = com.facebook.login.LoginClient.Result.createErrorResult(r12, r7, r8)
            goto L_0x003c
        L_0x0047:
            java.util.Collection<java.lang.String> r8 = com.facebook.internal.ServerProtocol.errorsProxyAuthDisabled
            boolean r8 = r8.contains(r1)
            if (r8 != 0) goto L_0x003c
            java.util.Collection<java.lang.String> r8 = com.facebook.internal.ServerProtocol.errorsUserCanceled
            boolean r8 = r8.contains(r1)
            if (r8 == 0) goto L_0x005c
            com.facebook.login.LoginClient$Result r7 = com.facebook.login.LoginClient.Result.createCancelResult(r12, r7)
            goto L_0x003c
        L_0x005c:
            com.facebook.login.LoginClient$Result r7 = com.facebook.login.LoginClient.Result.createErrorResult(r12, r1, r3, r2)
            goto L_0x003c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.login.NativeAppLoginMethodHandler.handleResultOk(com.facebook.login.LoginClient$Request, android.content.Intent):com.facebook.login.LoginClient$Result");
    }

    private Result handleResultCancel(Request request, Intent data) {
        Bundle extras = data.getExtras();
        String error = getError(extras);
        String errorCode = extras.getString("error_code");
        if (ServerProtocol.errorConnectionFailure.equals(errorCode)) {
            return Result.createErrorResult(request, error, getErrorMessage(extras), errorCode);
        }
        return Result.createCancelResult(request, error);
    }

    private String getError(Bundle extras) {
        String error = extras.getString("error");
        if (error == null) {
            return extras.getString("error_type");
        }
        return error;
    }

    private String getErrorMessage(Bundle extras) {
        String errorMessage = extras.getString("error_message");
        if (errorMessage == null) {
            return extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
        }
        return errorMessage;
    }

    /* access modifiers changed from: protected */
    public boolean tryIntent(Intent intent, int requestCode) {
        if (intent == null) {
            return false;
        }
        try {
            this.loginClient.getFragment().startActivityForResult(intent, requestCode);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }
}
