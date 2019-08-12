package p005cn.jpush.android.service;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.api.JPushInterface;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.data.JPushLocalNotification;
import p005cn.jpush.android.data.LocalNotificationDB;
import p005cn.jpush.android.data.LocalNotificationDBData;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.service.JPushLocalNotificationCenter */
public class JPushLocalNotificationCenter {
    private static final long FIVEMINS = 300000;
    private static final int HANDLER_TYPE = 0;
    private static final String TAG = "JPushLocalNotificationCenter";
    /* access modifiers changed from: private */

    /* renamed from: db */
    public static LocalNotificationDB f965db = null;
    /* access modifiers changed from: private */
    public static LocalNotificationDBData dbData = new LocalNotificationDBData();
    private static ExecutorService executor = Executors.newSingleThreadExecutor();
    private static JPushLocalNotificationCenter instance = null;
    private static Object mObject = new Object();
    /* access modifiers changed from: private */
    public String app = "";
    /* access modifiers changed from: private */
    public Context mContext = null;
    private Handler mHandler = null;

    private JPushLocalNotificationCenter(Context context) {
        Logger.m1416d(TAG, "Constructor : JPushLocalNotificationCenter");
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mContext = context;
        this.app = this.mContext.getPackageName();
    }

    public static JPushLocalNotificationCenter getInstance(Context context) {
        Logger.m1416d(TAG, "getInstance");
        synchronized (mObject) {
            if (instance == null) {
                instance = new JPushLocalNotificationCenter(context);
            }
        }
        return instance;
    }

    public synchronized void clear(Context context) {
        Logger.m1418dd(TAG, "clear all local notification ");
        if (f965db == null) {
            f965db = new LocalNotificationDB(context);
        }
        f965db.open(true);
        if (f965db.removeAllLN()) {
            Logger.m1416d(TAG, " success");
        }
        f965db.close();
    }

    public synchronized boolean remove(Context context, long notificationId) {
        Logger.m1418dd(TAG, "remove LocalNotification ");
        if (f965db == null) {
            f965db = new LocalNotificationDB(context);
        }
        Cursor cursor = null;
        try {
            f965db.open(true);
            Cursor cursor2 = f965db.fetchData(notificationId, 0);
            f965db.initData(cursor2, dbData);
            if (dbData.getLn_count() > 0) {
                Logger.m1416d(TAG, "remove local count : " + dbData.getLn_count());
                f965db.updateData(notificationId, 0, 1, 0, dbData.getLn_extra(), dbData.getLn_trigger_time(), dbData.getLn_add_time());
            }
            f965db.close();
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return true;
    }

    public synchronized boolean add(Context context, JPushLocalNotification notification) {
        boolean z;
        Logger.m1418dd(TAG, "add LocalNotification");
        long curTS = System.currentTimeMillis();
        long delayMillis = notification.getBroadcastTime() - curTS;
        if (ServiceInterface.isServiceStoped(context)) {
            Logger.m1416d(TAG, "push has stopped");
        }
        if (f965db == null) {
            f965db = new LocalNotificationDB(context);
        }
        Cursor cursor = null;
        try {
            f965db.open(true);
            cursor = f965db.fetchData(notification.getNotificationId(), 0);
            f965db.initData(cursor, dbData);
            if (dbData.getLn_id() != notification.getNotificationId()) {
                f965db.insertData(notification.getNotificationId(), 1, 0, 0, notification.toJSON(), notification.getBroadcastTime(), curTS);
            } else {
                f965db.updateData(notification.getNotificationId(), 1, 0, 0, notification.toJSON(), notification.getBroadcastTime(), curTS);
            }
            f965db.close();
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        if (delayMillis < FIVEMINS) {
            scheduleReadiedLN(notification.getNotificationId(), delayMillis, 0);
            z = true;
        } else {
            z = true;
        }
        return z;
    }

    public synchronized void initLocalNotifications(Context context) {
        Cursor cursor;
        Logger.m1416d(TAG, "init LocalNotification");
        try {
            if (f965db == null) {
                f965db = new LocalNotificationDB(context);
            }
            cursor = null;
            f965db.open(false);
            long curTime = System.currentTimeMillis();
            Cursor cursor2 = f965db.getRtcDatas(curTime, FIVEMINS);
            if (cursor2.moveToFirst()) {
                do {
                    f965db.initData(cursor2, dbData);
                    scheduleReadiedLN(dbData.getLn_id(), dbData.getLn_trigger_time() - curTime, 0);
                } while (cursor2.moveToNext());
            }
            f965db.close();
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e2) {
            Logger.m1416d(TAG, "init LocalNotification cast expt:" + e2);
            e2.printStackTrace();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return;
    }

    public void onHeartBeat(final Context context) {
        Logger.m1416d(TAG, "LocalNotification onHeartBeat");
        executor.execute(new Runnable() {
            public void run() {
                JPushLocalNotificationCenter.this.triggerLNKillProcess(context);
                JPushLocalNotificationCenter.this.initLocalNotifications(context);
            }
        });
    }

    /* access modifiers changed from: private */
    public synchronized void triggerLNKillProcess(Context context) {
        Logger.m1416d(TAG, "triggerLNKillProcess");
        Cursor cursor = null;
        try {
            if (f965db == null) {
                f965db = new LocalNotificationDB(context);
            }
            f965db.open(true);
            Cursor cursor2 = f965db.getOutDatas(1, System.currentTimeMillis());
            if (cursor2.moveToFirst()) {
                do {
                    f965db.initData(cursor2, dbData);
                    broadCastToPushReceiver(context, dbData.getLn_extra(), this.app, "");
                    f965db.updateData(dbData.getLn_id(), 0, 0, 0, dbData.getLn_extra(), dbData.getLn_trigger_time(), dbData.getLn_add_time());
                } while (cursor2.moveToNext());
            }
            f965db.close();
            if (cursor2 != null) {
                cursor2.close();
            }
        } catch (Exception e) {
            Logger.m1434ww(TAG, "triggerLNKillProcess: " + e.getMessage());
            e.printStackTrace();
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private synchronized void scheduleReadiedLN(final long localId, long delayMillis, int type) {
        Logger.m1416d(TAG, "LocalNotification scheduleReadiedLN");
        if (localId < 0) {
            Logger.m1420e(TAG, "notification is null");
        } else if (this.mHandler != null) {
            Runnable r = new Runnable() {
                public void run() {
                    if (JPushLocalNotificationCenter.f965db == null && JPushLocalNotificationCenter.this.mContext != null) {
                        JPushLocalNotificationCenter.f965db = new LocalNotificationDB(JPushLocalNotificationCenter.this.mContext);
                    }
                    Cursor cursor = null;
                    try {
                        JPushLocalNotificationCenter.f965db.open(true);
                        cursor = JPushLocalNotificationCenter.f965db.fetchData(localId, 0);
                        JPushLocalNotificationCenter.f965db.initData(cursor, JPushLocalNotificationCenter.dbData);
                        if (1 == JPushLocalNotificationCenter.dbData.getLn_remove()) {
                            Logger.m1416d(JPushLocalNotificationCenter.TAG, "remove ");
                            JPushLocalNotificationCenter.f965db.updateData(JPushLocalNotificationCenter.dbData.getLn_id(), 0, 1, 0, JPushLocalNotificationCenter.dbData.getLn_extra(), JPushLocalNotificationCenter.dbData.getLn_trigger_time(), JPushLocalNotificationCenter.dbData.getLn_add_time());
                        } else if (JPushLocalNotificationCenter.dbData.getLn_count() > 1) {
                            Logger.m1416d(JPushLocalNotificationCenter.TAG, "repeat add");
                            JPushLocalNotificationCenter.f965db.updateData(JPushLocalNotificationCenter.dbData.getLn_id(), JPushLocalNotificationCenter.dbData.getLn_count() - 1, 0, 0, JPushLocalNotificationCenter.dbData.getLn_extra(), JPushLocalNotificationCenter.dbData.getLn_trigger_time(), JPushLocalNotificationCenter.dbData.getLn_add_time());
                        } else if (JPushLocalNotificationCenter.dbData.getLn_count() == 1) {
                            Logger.m1416d(JPushLocalNotificationCenter.TAG, "send broadcast");
                            if (JPushLocalNotificationCenter.dbData.getLn_trigger_time() > System.currentTimeMillis()) {
                                Logger.m1416d(JPushLocalNotificationCenter.TAG, "repeat add");
                            } else {
                                JPushLocalNotificationCenter.this.broadCastToPushReceiver(JPushLocalNotificationCenter.this.mContext, JPushLocalNotificationCenter.dbData.getLn_extra(), JPushLocalNotificationCenter.this.app, "");
                                JPushLocalNotificationCenter.f965db.updateData(JPushLocalNotificationCenter.dbData.getLn_id(), 0, 0, 0, JPushLocalNotificationCenter.dbData.getLn_extra(), JPushLocalNotificationCenter.dbData.getLn_trigger_time(), JPushLocalNotificationCenter.dbData.getLn_add_time());
                            }
                        } else {
                            Logger.m1416d(JPushLocalNotificationCenter.TAG, "already triggered");
                        }
                        JPushLocalNotificationCenter.f965db.close();
                        if (cursor != null) {
                            cursor.close();
                        }
                    } catch (Exception e) {
                        if (cursor != null) {
                            cursor.close();
                        }
                    } catch (Throwable th) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            };
            if (delayMillis <= 0) {
                Logger.m1416d(TAG, "post right now ");
                this.mHandler.post(r);
            } else {
                Logger.m1416d(TAG, "post delayed : " + delayMillis);
                this.mHandler.postDelayed(r, delayMillis);
            }
        }
    }

    /* access modifiers changed from: private */
    public void broadCastToPushReceiver(Context context, String message, String app2, String sender) {
        Logger.m1416d(TAG, "start LocalNotification broadCastToPushReceiver");
        context.sendOrderedBroadcast(createPendingIntent(message, app2, sender), app2 + JPushConstants.PUSH_MESSAGE_PERMISSION_POSTFIX);
        Logger.m1416d(TAG, "end LocalNotification broadCastToPushReceiver");
    }

    private Intent createPendingIntent(String message, String app2, String sender) {
        Intent msgIntent = new Intent(JPushInterface.ACTION_NOTIFICATION_RECEIVED_PROXY);
        msgIntent.putExtra(JPushConstants.SENDER_ID, sender);
        msgIntent.putExtra(JPushConstants.APP_ID, app2);
        msgIntent.putExtra("message", message);
        msgIntent.putExtra(Entity.KEY_NOTIFICATION_TYPE, 1);
        msgIntent.addCategory(app2);
        return msgIntent;
    }
}
