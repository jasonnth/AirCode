package com.airbnb.android.login.oauth.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.login.C7331R;

public class OauthActivity extends SolitAirActivity {
    public static final String EXTRA_CODE = "code";
    public static final String OAUTH_SERVICE = "OAUTH_SERVICE";

    public enum Service {
        LINKEDIN,
        WEIBO
    }

    public static Intent intentForService(Context context, Service service) {
        Intent intent = new Intent(context, OauthActivity.class);
        intent.putExtra(OAUTH_SERVICE, service);
        return intent;
    }

    /* access modifiers changed from: protected */
    public boolean setFlagSecure() {
        return BuildHelper.isReleaseBuild();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Service service = (Service) getIntent().getSerializableExtra(OAUTH_SERVICE);
        setupActionBar(C7331R.string.verified_id_oauth_title, new Object[0]);
        if (savedInstanceState == null) {
            showFragment(OauthFragment.newInstance(service), true);
        }
    }

    public void returnCode(String code) {
        Intent data = new Intent();
        data.putExtra("code", code);
        setResult(-1, data);
        finish();
    }
}
