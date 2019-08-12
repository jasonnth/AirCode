package com.mparticle.activity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import com.mparticle.MParticle;

@SuppressLint({"Registered"})
public class MPListActivity extends ListActivity {
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
