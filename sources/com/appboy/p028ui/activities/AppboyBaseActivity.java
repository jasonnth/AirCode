package com.appboy.p028ui.activities;

import android.app.Activity;
import com.appboy.Appboy;
import com.appboy.p028ui.inappmessage.AppboyInAppMessageManager;

/* renamed from: com.appboy.ui.activities.AppboyBaseActivity */
public class AppboyBaseActivity extends Activity {
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
