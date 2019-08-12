package com.facebook.accountkit.p029ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/* renamed from: com.facebook.accountkit.ui.AccountKitEmailRedirectActivity */
public class AccountKitEmailRedirectActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, AccountKitActivity.class);
        intent.putExtra("url", getIntent().getDataString());
        intent.addFlags(335544320);
        startActivity(intent);
    }
}
