package com.altoros.ethnio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

public class EthnioActivity extends Activity implements OnEthnioCloseClickListener {
    private int mListenerId;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() == null) {
            showErrorToast();
            finish();
        }
        String mainUrl = getIntent().getStringExtra("MAIN_URL");
        String ethnioId = getIntent().getStringExtra("ETHNIO_ID");
        this.mListenerId = getIntent().getIntExtra("LISTENER_ID", 0);
        if (TextUtils.isEmpty(mainUrl) || TextUtils.isEmpty(ethnioId)) {
            showErrorToast();
            finish();
        }
        setContentView(EthnioManager.createEthnioView(this, mainUrl, ethnioId, this, this.mListenerId));
    }

    private void showErrorToast() {
        Toast.makeText(this, "For displaying this activity please use EthnioManager", 0).show();
    }

    public void onCloseClick() {
        finish();
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent("com.altoros.ethnio.ethniomanager.ACTION_ETHNIO_CLIENT_CLICK_CLOSE");
        intent.putExtra("ETHNIO_LISTENER_ID", this.mListenerId);
        sendBroadcast(intent);
    }
}
