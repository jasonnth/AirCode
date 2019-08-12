package com.airbnb.android.lib.utils.webintent;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.interfaces.WebIntentMatcher;

public class WebIntentMatcherModule {
    /* access modifiers changed from: 0000 */
    public WebIntentMatcher provideWebIntentMatcher(AirbnbAccountManager accountManager) {
        return new WebIntentMatcherUtil(accountManager);
    }
}
