package com.airbnb.android.core.security;

import android.util.Log;
import com.airbnb.android.core.PostApplicationCreatedInitializer;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.User;
import com.threatmetrix.TrustDefender.C4751TrustDefender;
import com.threatmetrix.TrustDefender.Config;
import com.threatmetrix.TrustDefender.ProfilingOptions;
import com.threatmetrix.TrustDefender.ProfilingResult;
import com.threatmetrix.TrustDefender.THMStatusCode;
import java.util.ArrayList;
import java.util.List;

public class ThreatMetrixClient implements PostApplicationCreatedInitializer {
    public static final int INIT_CONFIG_TIMEOUT = 10;
    public static final String ORG_ID = "kfgn8s24";
    public static final int PACKAGE_SCAN_TIMEOUT = 0;
    private static final String TAG = ThreatMetrixClient.class.getSimpleName();
    private final AirbnbAccountManager accountManager;
    private final Config config;

    public ThreatMetrixClient(AirbnbAccountManager accountManager2, Config config2) {
        this.accountManager = accountManager2;
        this.config = config2;
    }

    public void initialize() {
        THMStatusCode statusCode = C4751TrustDefender.getInstance().init(this.config);
        if (statusCode == THMStatusCode.THM_OK || statusCode == THMStatusCode.THM_Already_Initialised) {
            doProfile(new ProfilingOptions().setCustomAttributes(customAttributes(this.accountManager.getCurrentUser())).setEndNotifier(ThreatMetrixClient$$Lambda$1.lambdaFactory$()));
        } else {
            Log.e(TAG, "Init was not successful " + statusCode.getDesc() + ". Can't perform profiling.");
        }
    }

    static /* synthetic */ void lambda$initialize$0(ProfilingResult result) {
        Log.i(TAG, "Profile completed with: " + result.getStatus().toString() + " - " + result.getStatus().getDesc());
        if (result.getStatus() == THMStatusCode.THM_OK) {
        }
        C4751TrustDefender.getInstance().doPackageScan(0);
    }

    public void doProfile(ProfilingOptions profilingOptions) {
        THMStatusCode statusCode = C4751TrustDefender.getInstance().doProfileRequest(profilingOptions);
        ProfilingResult result = C4751TrustDefender.getInstance().getResult();
        if (statusCode == THMStatusCode.THM_OK) {
            Log.d(TAG, "My session id is " + result.getSessionID());
        } else {
            if (result.getStatus() == THMStatusCode.THM_NotYet) {
            }
        }
    }

    public void doPackageScan(int timeout) {
        C4751TrustDefender.getInstance().doPackageScan(timeout);
    }

    public List<String> customAttributes(String paymentInstrumentId, String transactionAmount, String transactionCurrency) {
        List<String> customAttributes = customAttributes(this.accountManager.getCurrentUser());
        if (paymentInstrumentId != null) {
            customAttributes.add(paymentInstrumentId);
        }
        customAttributes.add(transactionAmount);
        customAttributes.add(transactionCurrency);
        return customAttributes;
    }

    private List<String> customAttributes(User user) {
        List<String> customAttributes = new ArrayList<>();
        if (user != null) {
            customAttributes.add(user.getName());
            customAttributes.add(user.getEmailAddress());
        }
        return customAttributes;
    }
}
