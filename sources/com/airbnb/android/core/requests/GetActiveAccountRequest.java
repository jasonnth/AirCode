package com.airbnb.android.core.requests;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.ButtonPartnership;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.authentication.AuthorizedAccountHelper;
import com.airbnb.android.core.models.Account;
import com.airbnb.android.core.responses.AccountResponse;
import com.airbnb.android.core.utils.CurrencyFormatter;
import java.lang.reflect.Type;

public class GetActiveAccountRequest extends BaseRequestV2<AccountResponse> {
    protected static final String TAG = GetActiveAccountRequest.class.getSimpleName();
    AirbnbAccountManager accountManager;
    private final Context context;
    CurrencyFormatter currencyFormatter;
    private final boolean mRestartTask;

    public GetActiveAccountRequest(Context context2, boolean restartTask) {
        this.context = context2;
        this.mRestartTask = restartTask;
        ((CoreGraph) CoreApplication.instance(context2).component()).inject(this);
    }

    public GetActiveAccountRequest(Context context2) {
        this(context2, true);
    }

    public String getPath() {
        return "accounts/me";
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public AirResponse<AccountResponse> transformResponse(AirResponse<AccountResponse> response) {
        Account data = ((AccountResponse) response.body()).account;
        this.accountManager.setCurrentUser(data.getUser());
        this.accountManager.storeCurrentUser();
        this.accountManager.setIsVRPlatformPoweredHost(data.isVrPlatformPoweredHost());
        if (!TextUtils.isEmpty(data.getCurrency()) && !this.currencyFormatter.getLocalCurrencyString().equalsIgnoreCase(data.getCurrency())) {
            this.currencyFormatter.setCurrency(data.getCurrency(), false, this.mRestartTask);
        }
        AuthorizedAccountHelper.get(this.context).addOrUpdateCurrentUser();
        ButtonPartnership.get().onLogin(data.getUser());
        return response;
    }

    public Type successResponseType() {
        return AccountResponse.class;
    }
}
