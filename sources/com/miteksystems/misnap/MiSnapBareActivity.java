package com.miteksystems.misnap;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p002v7.app.AppCompatActivity;
import com.miteksystems.misnap.events.OnCapturedFrameEvent;
import com.miteksystems.misnap.events.OnShutdownEvent;
import com.miteksystems.misnap.events.ShutdownEvent;
import com.miteksystems.misnap.params.MiSnapIntentCheck;
import com.miteksystems.misnap.params.ParamsHelper;
import p314de.greenrobot.event.EventBus;

public class MiSnapBareActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (MiSnapIntentCheck.isDangerous(getIntent())) {
            setResult(0);
            finish();
            return;
        }
        getWindow().addFlags(1024);
        if (!ParamsHelper.areScreenshotsAllowed(getIntent()) && VERSION.SDK_INT >= 11) {
            getWindow().addFlags(8192);
        }
        setContentView(C4540R.layout.misnap_activity);
        getSupportFragmentManager().beginTransaction().replace(C4540R.C4542id.misnap_container, new MiSnapCameraFragment(), MiSnapCameraFragment.class.getSimpleName()).commit();
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
        EventBus.getDefault().register(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void onEvent(OnCapturedFrameEvent onCapturedFrameEvent) {
        setResult(-1, onCapturedFrameEvent.returnIntent);
        EventBus.getDefault().post(new ShutdownEvent(0));
    }

    public void onEvent(OnShutdownEvent onShutdownEvent) {
        finish();
    }
}
