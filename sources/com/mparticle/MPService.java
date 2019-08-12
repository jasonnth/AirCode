package com.mparticle;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.p000v4.app.NotificationCompat;
import android.util.Log;
import com.mparticle.internal.AppStateManager;
import com.mparticle.internal.C4607e;
import com.mparticle.internal.ConfigManager;
import com.mparticle.internal.KitFrameworkWrapper;
import com.mparticle.messaging.AbstractCloudMessage;
import com.mparticle.messaging.CloudAction;
import com.mparticle.messaging.MPCloudNotificationMessage;
import com.mparticle.messaging.MPMessagingAPI;
import com.mparticle.messaging.ProviderCloudMessage;
import java.util.List;

@SuppressLint({"Registered"})
public class MPService extends IntentService {
    private static final String INTERNAL_DELAYED_RECEIVE = "com.mparticle.delayeddelivery";
    public static final String INTERNAL_NOTIFICATION_TAP = "com.mparticle.push.notification_tapped";
    /* access modifiers changed from: private */
    public static final Object LOCK = MPService.class;
    private static final String TAG = "mParticle SDK";
    /* access modifiers changed from: private */
    public static WakeLock sWakeLock;

    public MPService() {
        super("com.mparticle.MPService");
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                try {
                    Class.forName("android.os.AsyncTask");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void runIntentInService(Context context, Intent intent) {
        synchronized (LOCK) {
            if (sWakeLock == null) {
                sWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, TAG);
            }
        }
        sWakeLock.acquire();
        intent.setClass(context, MPService.class);
        context.startService(intent);
    }

    public final void onHandleIntent(Intent intent) {
        boolean z;
        try {
            String action = intent.getAction();
            Log.i("MPService", "Handling action: " + action);
            if (action.equals("com.google.android.c2dm.intent.REGISTRATION")) {
                z = true;
            } else if (action.equals("com.google.android.c2dm.intent.RECEIVE")) {
                generateCloudMessage(intent);
                z = true;
            } else if (action.startsWith(INTERNAL_NOTIFICATION_TAP)) {
                handleNotificationTapInternal(intent);
                z = true;
            } else if (action.equals(MPMessagingAPI.BROADCAST_NOTIFICATION_TAPPED)) {
                handleNotificationTap(intent);
                z = true;
            } else if (action.equals(MPMessagingAPI.BROADCAST_NOTIFICATION_RECEIVED)) {
                showNotification((AbstractCloudMessage) intent.getParcelableExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA));
                z = false;
            } else {
                if (action.equals(INTERNAL_DELAYED_RECEIVE)) {
                    broadcastNotificationReceived((MPCloudNotificationMessage) intent.getParcelableExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA));
                }
                z = true;
            }
            synchronized (LOCK) {
                if (z) {
                    if (sWakeLock != null && sWakeLock.isHeld()) {
                        sWakeLock.release();
                    }
                }
            }
        } catch (Throwable th) {
            synchronized (LOCK) {
                if (sWakeLock != null && sWakeLock.isHeld()) {
                    sWakeLock.release();
                }
                throw th;
            }
        }
    }

    private void showNotification(final AbstractCloudMessage message) {
        if (!message.getDisplayed()) {
            new AsyncTask<AbstractCloudMessage, Void, Notification>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Notification doInBackground(AbstractCloudMessage... abstractCloudMessageArr) {
                    return message.buildNotification(MPService.this, System.currentTimeMillis());
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(Notification notification) {
                    super.onPostExecute(notification);
                    if (notification != null) {
                        NotificationManager notificationManager = (NotificationManager) MPService.this.getSystemService("notification");
                        notificationManager.cancel(message.getId());
                        notificationManager.notify(message.getId(), notification);
                    }
                    synchronized (MPService.LOCK) {
                        if (MPService.sWakeLock != null && MPService.sWakeLock.isHeld()) {
                            MPService.sWakeLock.release();
                        }
                    }
                }
            }.execute(new AbstractCloudMessage[]{message});
        }
        String appState = getAppState();
        if (message instanceof ProviderCloudMessage) {
            MParticle.getInstance().logNotification((ProviderCloudMessage) message, false, appState);
        } else if (message instanceof MPCloudNotificationMessage) {
            MParticle.getInstance().logNotification((MPCloudNotificationMessage) message, null, false, appState, 17);
        }
    }

    private void handleNotificationTap(Intent intent) {
        CloudAction cloudAction = (CloudAction) intent.getParcelableExtra(MPMessagingAPI.CLOUD_ACTION_EXTRA);
        PendingIntent intent2 = cloudAction.getIntent(getApplicationContext(), (AbstractCloudMessage) intent.getParcelableExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA), cloudAction);
        if (intent2 != null) {
            try {
                intent2.send();
            } catch (CanceledException e) {
            }
        }
    }

    /* access modifiers changed from: private */
    public String getAppState() {
        String str = AppStateManager.APP_STATE_NOTRUNNING;
        if (!AppStateManager.mInitialized) {
            return str;
        }
        if (MParticle.getInstance().mAppStateManager.isBackgrounded()) {
            return "background";
        }
        return AppStateManager.APP_STATE_FOREGROUND;
    }

    private void generateCloudMessage(final Intent intent) {
        if (!processSilentPush(getApplicationContext(), intent.getExtras())) {
            try {
                KitFrameworkWrapper.setKitsLoadedListener(new C4607e() {
                    /* renamed from: a */
                    public void mo44540a() {
                        try {
                            MParticle.getInstance().getKitManager().loadKitLibrary();
                            boolean onMessageReceived = MParticle.getInstance().getKitManager().onMessageReceived(MPService.this.getApplicationContext(), intent);
                            AbstractCloudMessage createMessage = AbstractCloudMessage.createMessage(intent, ConfigManager.getPushKeys(MPService.this));
                            createMessage.setDisplayed(onMessageReceived);
                            String access$200 = MPService.this.getAppState();
                            if (createMessage instanceof MPCloudNotificationMessage) {
                                MParticle.getInstance().saveGcmMessage((MPCloudNotificationMessage) createMessage, access$200);
                                if (((MPCloudNotificationMessage) createMessage).isDelayed()) {
                                    MParticle.getInstance().logNotification((MPCloudNotificationMessage) createMessage, null, false, access$200, 1);
                                    MPService.this.scheduleFutureNotification((MPCloudNotificationMessage) createMessage);
                                    return;
                                }
                            } else if (createMessage instanceof ProviderCloudMessage) {
                                MParticle.getInstance().saveGcmMessage((ProviderCloudMessage) createMessage, access$200);
                            }
                            MPService.this.broadcastNotificationReceived(createMessage);
                        } catch (Exception e) {
                            Log.w(MPService.TAG, "GCM parsing error: " + e.toString());
                        }
                    }
                });
                MParticle.start(this);
            } catch (Exception e) {
                Log.w(TAG, "GCM parsing error: " + e.toString());
            }
        }
    }

    public boolean processSilentPush(Context context, Bundle extras) {
        if (extras == null || !extras.containsKey(MPCloudNotificationMessage.COMMAND)) {
            return false;
        }
        switch (Integer.parseInt(extras.getString(MPCloudNotificationMessage.COMMAND))) {
            case 0:
                break;
            case 4:
                MParticle.start(context);
                MParticle.getInstance().refreshConfiguration();
                break;
            default:
                return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void scheduleFutureNotification(MPCloudNotificationMessage message) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(INTERNAL_DELAYED_RECEIVE);
        intent.setClass(this, MPService.class);
        intent.putExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA, message);
        PendingIntent service = PendingIntent.getService(this, message.getId(), intent, 134217728);
        if (VERSION.SDK_INT >= 19) {
            alarmManager.setExact(1, message.getDeliveryTime(), service);
        } else {
            alarmManager.set(1, message.getDeliveryTime(), service);
        }
    }

    /* access modifiers changed from: private */
    public void broadcastNotificationReceived(AbstractCloudMessage message) {
        Intent intent = new Intent(MPMessagingAPI.BROADCAST_NOTIFICATION_RECEIVED);
        intent.putExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA, message);
        intent.addCategory(getPackageName());
        List queryBroadcastReceivers = getPackageManager().queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
            onHandleIntent(intent);
        } else {
            sendBroadcast(intent, null);
        }
    }

    private void handleNotificationTapInternal(Intent intent) {
        AbstractCloudMessage abstractCloudMessage = (AbstractCloudMessage) intent.getParcelableExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA);
        CloudAction cloudAction = (CloudAction) intent.getParcelableExtra(MPMessagingAPI.CLOUD_ACTION_EXTRA);
        ((NotificationManager) getSystemService("notification")).cancel(abstractCloudMessage.getId());
        MParticle.start(getApplicationContext());
        if (abstractCloudMessage instanceof MPCloudNotificationMessage) {
            MParticle.getInstance().logNotification((MPCloudNotificationMessage) abstractCloudMessage, cloudAction, true, getAppState(), 6);
        }
        Intent intent2 = new Intent(MPMessagingAPI.BROADCAST_NOTIFICATION_TAPPED);
        intent2.putExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA, abstractCloudMessage);
        intent2.putExtra(MPMessagingAPI.CLOUD_ACTION_EXTRA, cloudAction);
        List queryBroadcastReceivers = getPackageManager().queryBroadcastReceivers(intent2, 0);
        if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
            onHandleIntent(intent2);
        } else {
            sendBroadcast(intent2, null);
        }
    }
}
