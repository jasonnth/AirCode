package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.intents.LoginActivityIntents;
import com.airbnb.android.lib.C0880R;

public class ExpiredOauthTokenActivity extends AirActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_expired_oauth_token);
        ButterKnife.bind((Activity) this);
        this.airbnbApi.logoutWithoutRevokingOauth();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void startLogin() {
        startActivity(LoginActivityIntents.intent(this));
        finish();
    }

    /* access modifiers changed from: protected */
    public boolean denyRequireAccountFromChild() {
        return true;
    }
}
