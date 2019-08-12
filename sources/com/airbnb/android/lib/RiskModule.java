package com.airbnb.android.lib;

import android.content.Context;
import com.airbnb.android.core.PostApplicationCreatedInitializer;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.security.ThreatMetrixClient;
import com.threatmetrix.TrustDefender.Config;

public class RiskModule {

    public interface Declarations {
        PostApplicationCreatedInitializer bindThreadMetrixClient(ThreatMetrixClient threatMetrixClient);
    }

    /* access modifiers changed from: 0000 */
    public Config provideConfig(Context context) {
        return new Config().setOrgId(ThreatMetrixClient.ORG_ID).setContext(context).setTimeout(10).setRegisterForLocationServices(true);
    }

    /* access modifiers changed from: 0000 */
    public ThreatMetrixClient provideThreatMetrixClient(AirbnbAccountManager accountManager, Config config) {
        return _provideThreatMetrixClient(accountManager, config);
    }

    /* access modifiers changed from: protected */
    public ThreatMetrixClient _provideThreatMetrixClient(AirbnbAccountManager accountManager, Config config) {
        return new ThreatMetrixClient(accountManager, config);
    }
}
