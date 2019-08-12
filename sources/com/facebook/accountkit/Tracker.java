package com.facebook.accountkit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.p000v4.content.LocalBroadcastManager;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.internal.Utility;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Tracker {
    public static final String EXTRA_LOGIN_ERROR = "com.facebook.accountkit.sdk.EXTRA_LOGIN_ERROR";
    public static final String EXTRA_LOGIN_MODEL = "com.facebook.accountkit.sdk.EXTRA_LOGIN_MODEL";
    public static final String EXTRA_LOGIN_STATUS = "com.facebook.accountkit.sdk.EXTRA_LOGIN_STATUS";
    private boolean isPaused = false;
    private boolean isTracking = false;
    /* access modifiers changed from: private */
    public final List<Intent> pendingBroadcasts = new ArrayList();
    private final BroadcastReceiver receiver = new TrackerBroadcastReceiver(this);

    private static class TrackerBroadcastReceiver extends BroadcastReceiver {
        final WeakReference<Tracker> trackerRef;

        TrackerBroadcastReceiver(Tracker tracker) {
            this.trackerRef = new WeakReference<>(tracker);
        }

        public void onReceive(Context context, Intent intent) {
            Tracker tracker = (Tracker) this.trackerRef.get();
            if (tracker == null || !Utility.areObjectsEqual(tracker.getActionStateChanged(), intent.getAction())) {
                return;
            }
            if (tracker.isPaused()) {
                tracker.pendingBroadcasts.add(intent);
            } else if (tracker.isTracking()) {
                tracker.onReceive(intent);
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract String getActionStateChanged();

    /* access modifiers changed from: protected */
    public abstract void onReceive(Intent intent);

    public void startTracking() {
        if (!this.isTracking) {
            this.isTracking = true;
            addBroadcastReceiver();
        }
        if (this.isPaused) {
            this.isPaused = false;
            ArrayList<Intent> intents = new ArrayList<>(this.pendingBroadcasts);
            this.pendingBroadcasts.clear();
            Iterator it = intents.iterator();
            while (it.hasNext()) {
                Intent intent = (Intent) it.next();
                if (isTracking()) {
                    onReceive(intent);
                }
            }
        }
    }

    public void stopTracking() {
        if (this.isTracking) {
            this.isTracking = false;
            unregisterReceiver(this.receiver);
            this.pendingBroadcasts.clear();
        }
    }

    public void pauseTracking() {
        this.isPaused = true;
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public boolean isTracking() {
        return this.isTracking;
    }

    /* access modifiers changed from: protected */
    public boolean isLocal() {
        return true;
    }

    private void addBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(getActionStateChanged());
        registerReceiver(this.receiver, filter);
    }

    private void registerReceiver(BroadcastReceiver receiver2, IntentFilter filter) {
        Context context = AccountKitController.getApplicationContext();
        if (isLocal()) {
            LocalBroadcastManager.getInstance(context).registerReceiver(receiver2, filter);
        } else {
            context.registerReceiver(receiver2, filter);
        }
    }

    private void unregisterReceiver(BroadcastReceiver receiver2) {
        Context context = AccountKitController.getApplicationContext();
        if (isLocal()) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver2);
        } else {
            context.unregisterReceiver(receiver2);
        }
    }
}
