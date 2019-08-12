package com.airbnb.android.core.utils;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.FragmentManager;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.intents.ShakeFeedbackDialogIntents;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.ShakeEventListener;

public class ShakeFeedbackSensorListenerImpl implements ShakeFeedbackSensorListener {
    public static final String KEY_SHAKE_FEEDBACK = "shake_feedback_pref";
    private final SharedPreferences preferences;
    private ShakeEventListener sensorListener;
    private SensorManager sensorManager;

    public ShakeFeedbackSensorListenerImpl(AirbnbPreferences preferences2) {
        this.preferences = preferences2.getGlobalSharedPreferences();
    }

    public void onResume(FragmentActivity activity) {
        if (isEnabled()) {
            this.sensorManager = (SensorManager) activity.getSystemService("sensor");
            Sensor accelerometerSensor = this.sensorManager.getDefaultSensor(1);
            if (accelerometerSensor != null) {
                this.sensorListener = new ShakeEventListener();
                this.sensorListener.setOnShakeListener(ShakeFeedbackSensorListenerImpl$$Lambda$1.lambdaFactory$(this, activity));
                this.sensorManager.registerListener(this.sensorListener, accelerometerSensor, 2);
            }
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(17)
    public void onShake(FragmentActivity activity) {
        if (activity.isFinishing()) {
            return;
        }
        if (!AndroidVersion.isAtLeastJellyBeanMR1() || !activity.isDestroyed()) {
            showFeedbackDialog(activity.getSupportFragmentManager());
        }
    }

    public boolean isEnabled() {
        return this.preferences.getBoolean(KEY_SHAKE_FEEDBACK, true);
    }

    public void setEnabled(boolean enabled) {
        this.preferences.edit().putBoolean(KEY_SHAKE_FEEDBACK, enabled).apply();
    }

    public void onPause() {
        if (this.sensorManager != null) {
            this.sensorManager.unregisterListener(this.sensorListener);
        }
        if (this.sensorListener != null) {
            this.sensorListener.setOnShakeListener(null);
        }
    }

    public void showFeedbackDialog(FragmentActivity activity) {
        showFeedbackDialog(activity.getSupportFragmentManager());
    }

    private void showFeedbackDialog(FragmentManager fragmentManager) {
        ShakeFeedbackDialogIntents.newInstanceForShakeFeedback().showAllowingStateLoss(fragmentManager, null);
    }
}
