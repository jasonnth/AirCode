package com.mparticle.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import com.mparticle.MParticle;

@SuppressLint({"Registered"})
public class MPActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        MParticle.getInstance().activityStarted(this);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        MParticle.getInstance().activityStopped(this);
    }
}
