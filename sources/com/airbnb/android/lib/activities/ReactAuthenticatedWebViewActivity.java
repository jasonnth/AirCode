package com.airbnb.android.lib.activities;

import com.airbnb.android.core.activities.WebViewActivity;
import com.airbnb.android.core.models.ReactAuthenticatedWebViewArguments;
import com.airbnb.android.core.react.ReactExposedActivityParamsConstants;

public class ReactAuthenticatedWebViewActivity extends WebViewActivity {
    /* access modifiers changed from: protected */
    public String getOriginalUrl() {
        ReactAuthenticatedWebViewArguments arguments = (ReactAuthenticatedWebViewArguments) getIntent().getParcelableExtra(ReactExposedActivityParamsConstants.KEY_ARGUMENT);
        if (arguments == null) {
            return super.getOriginalUrl();
        }
        return arguments.getUrl();
    }
}
