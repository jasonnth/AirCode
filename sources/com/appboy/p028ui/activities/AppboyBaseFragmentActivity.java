package com.appboy.p028ui.activities;

import android.support.p000v4.app.FragmentActivity;
import com.appboy.Appboy;
import com.appboy.p028ui.inappmessage.AppboyInAppMessageManager;

/* renamed from: com.appboy.ui.activities.AppboyBaseFragmentActivity */
public class AppboyBaseFragmentActivity extends FragmentActivity {
    public void onStart() {
        super.onStart();
        Appboy.getInstance(this).openSession(this);
    }

    public void onResume() {
        super.onResume();
        AppboyInAppMessageManager.getInstance().registerInAppMessageManager(this);
    }

    public void onPause() {
        super.onPause();
        AppboyInAppMessageManager.getInstance().unregisterInAppMessageManager(this);
    }

    public void onStop() {
        super.onStop();
        Appboy.getInstance(this).closeSession(this);
    }
}
