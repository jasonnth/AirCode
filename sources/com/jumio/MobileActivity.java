package com.jumio;

import android.os.Bundle;
import android.support.p002v7.app.AppCompatActivity;
import com.jumio.sdk.models.CredentialsModel;

public class MobileActivity extends AppCompatActivity {
    public static final String EXTRA_CREDENTIALS = "com.jumio.nv.MobileActivity.EXTRA_CREDENTIALS";
    public static final String EXTRA_CUSTOM_THEME = "com.jumio.nv.MobileActivity.EXTRA_CUSTOM_THEME";
    private CredentialsModel credentialsModel = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            int themeId = getIntent().getExtras().getInt(EXTRA_CUSTOM_THEME);
            if (themeId != 0) {
                setTheme(themeId);
            }
            if (this.credentialsModel != null) {
                return;
            }
            if (getIntent() != null) {
                this.credentialsModel = (CredentialsModel) getIntent().getSerializableExtra(EXTRA_CREDENTIALS);
            } else if (savedInstanceState != null) {
                this.credentialsModel = (CredentialsModel) savedInstanceState.getSerializable(EXTRA_CREDENTIALS);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            outState.putSerializable(EXTRA_CREDENTIALS, this.credentialsModel);
        }
    }

    /* access modifiers changed from: protected */
    public CredentialsModel getCredentialsModel() {
        return this.credentialsModel;
    }
}
