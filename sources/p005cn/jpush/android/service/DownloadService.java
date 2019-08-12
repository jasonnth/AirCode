package p005cn.jpush.android.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.JPushConstants.PushReceiver;
import p005cn.jpush.android.api.NotificationHelper;
import p005cn.jpush.android.data.DbHelper;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.data.PkEntity;
import p005cn.jpush.android.data.VideoEntity;
import p005cn.jpush.android.helpers.ProtocolHelper;
import p005cn.jpush.android.helpers.ReportHelper;
import p005cn.jpush.android.service.DownloadControl.DownloadListener;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.service.DownloadService */
public class DownloadService extends IntentService {
    private static final String ID_ICON = "icon";
    private static final String ID_PROGRESS_BAR = "progress_bar";
    private static final String ID_TEXT_PROGRESS = "text_progress";
    private static final String ID_TITLE = "title";
    private static final String LAYOUT_FIELD_NAME = "notification_layout";
    private static final String TAG = "DownloadService";
    public static ConcurrentLinkedQueue<Entity> mDownladTasks = new ConcurrentLinkedQueue<>();
    private static Bundle mDownloadInfos;
    /* access modifiers changed from: private */
    public Entity entity;
    private Integer imageViewId = Integer.valueOf(0);
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NotificationHelper.cancelNotification(DownloadService.this, msg.what);
        }
    };
    /* access modifiers changed from: private */
    public NotificationManager mNotificationManager;
    private ToastHandler mToastHandler;
    private Notification notification;
    private Builder notificationBuilder;
    private Integer notificationLayoutId = Integer.valueOf(0);
    private Integer progressViewId = Integer.valueOf(0);
    private RemoteViews remoteViews;
    private Integer textProgressId = Integer.valueOf(0);
    private Integer titleViewId = Integer.valueOf(0);

    /* renamed from: cn.jpush.android.service.DownloadService$ToastHandler */
    private class ToastHandler extends Handler {
        private Context context;

        public ToastHandler(Context context2) {
            super(context2.getMainLooper());
            this.context = context2;
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(this.context, "SDCard can not work", 1).show();
        }
    }

    public DownloadService() {
        super(TAG);
    }

    public void onCreate() {
        Logger.m1416d(TAG, "onCreate()");
        super.onCreate();
        this.mToastHandler = new ToastHandler(getApplicationContext());
        this.mNotificationManager = (NotificationManager) getSystemService("notification");
        if (mDownloadInfos == null) {
            mDownloadInfos = new Bundle();
        }
        try {
            if (this.notificationLayoutId.intValue() == 0) {
                HashMap<String, Integer> idMap = NotificationHelper.getResourceId(NotificationHelper.RES_TYPE_LAYOUT, new String[]{LAYOUT_FIELD_NAME});
                if (idMap.size() > 0) {
                    this.notificationLayoutId = (Integer) idMap.get(LAYOUT_FIELD_NAME);
                }
                HashMap<String, Integer> idMap2 = NotificationHelper.getResourceId(NotificationHelper.RES_TYPE_ID, new String[]{"title", ID_PROGRESS_BAR, ID_ICON, ID_TEXT_PROGRESS});
                if (idMap2.size() > 0) {
                    this.titleViewId = (Integer) idMap2.get("title");
                    this.progressViewId = (Integer) idMap2.get(ID_PROGRESS_BAR);
                    this.imageViewId = (Integer) idMap2.get(ID_ICON);
                    this.textProgressId = (Integer) idMap2.get(ID_TEXT_PROGRESS);
                }
            }
        } catch (Exception e) {
            Logger.m1433w(TAG, "Init progress bar error", e);
        }
    }

    public void onDestroy() {
        Logger.m1416d(TAG, "onDestroy()");
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        Logger.m1416d(TAG, "action:onHandleIntent");
        this.entity = (Entity) intent.getSerializableExtra("body");
        if (this.entity == null) {
            Logger.m1432w(TAG, "NULL msg entity");
        } else if (!Environment.getExternalStorageState().equals("mounted")) {
            Logger.m1432w(TAG, "SDCard can not work");
            this.mToastHandler.sendEmptyMessage(0);
        } else if (this.entity._isDownloadFinisehd) {
            Logger.m1416d(TAG, "The Msg download is already finished.");
        } else {
            if (this.entity._isEverDownloadFailed) {
                ReportHelper.reportMsgResult(this.entity.messageId, 1012, this);
            }
            if (!mDownladTasks.contains(this.entity)) {
                mDownladTasks.offer(this.entity);
            }
            final int notifiId = NotificationHelper.getNofiticationID(this.entity, 1);
            final boolean isSilent = isSlt(this.entity, notifiId);
            Thread.currentThread().setPriority(1);
            new DownloadControl(this, this.entity, mDownloadInfos, new DownloadListener() {
                public void onDownloading(long downloadLenth, long totalLength) {
                    Logger.m1416d(DownloadService.TAG, "pecent:" + ((int) ((((float) downloadLenth) / ((float) totalLength)) * 100.0f)) + ", downloaded:" + downloadLenth + ", total:" + totalLength);
                    if (!isSilent) {
                        DownloadService.this.downloadingNotification(DownloadService.this.entity, notifiId, downloadLenth, totalLength);
                    }
                }

                public void onDownloadSucceed(String fileSaveTotalPath, boolean existed) {
                    DownloadService.this.entity._isDownloadFinisehd = true;
                    DownloadService.mDownladTasks.remove(DownloadService.this.entity);
                    int reportCode = 1001;
                    if (DownloadService.this.entity.isMsgTypeAAndUpdate()) {
                        PkEntity aEntity = (PkEntity) DownloadService.this.entity;
                        aEntity._aSavedPath = fileSaveTotalPath;
                        aEntity._isAPreloadSucceed = false;
                        if (ProtocolHelper.isNeedSDownload(aEntity.needSDownload, aEntity.sDownloadNetworkOption, DownloadService.this)) {
                            reportCode = 1003;
                            aEntity._isAPreloadSucceed = true;
                        }
                    } else if (DownloadService.this.entity.isMsgTypeVideo()) {
                        ((VideoEntity) DownloadService.this.entity)._videoSavedPath = fileSaveTotalPath;
                        reportCode = 1004;
                    } else if (DownloadService.this.entity.isRichPush()) {
                        DownloadService.this.entity.richPushSavedPath = fileSaveTotalPath;
                    }
                    if (existed) {
                        reportCode = StatusCode.RESULT_TYPE_DOWN_EXIST;
                    }
                    ReportHelper.reportMsgResult(DownloadService.this.entity.messageId, reportCode, DownloadService.this);
                    if (!DownloadService.this.entity.isRichPush()) {
                        DownloadService.this.mHandler.sendEmptyMessageDelayed(notifiId, 500);
                    }
                    DownloadService.this.entity._isDownloadInterrupted = true;
                    DownloadService.this.downloadSucceed(DownloadService.this.entity);
                }

                public void onDownloadFailed(int failType) {
                    DownloadService.this.mNotificationManager.cancel(notifiId);
                    if (DownloadControl.isRealFailed(failType)) {
                        DownloadService.this.entity._isEverDownloadFailed = true;
                        ReportHelper.reportMsgResult(DownloadService.this.entity.messageId, 1011, DownloadService.this);
                        String aUrl = "";
                        try {
                            aUrl = ((PkEntity) DownloadService.this.entity).aDownloadUrl;
                        } catch (Exception e) {
                        }
                        ReportHelper.reportMsgActionResult(DownloadService.this.entity.messageId, 1022, AndroidUtil.getDownloadFailedClientInfo(DownloadService.this, aUrl), DownloadService.this);
                    }
                    DownloadService.this.entity._isDownloadInterrupted = true;
                    DownloadService.this.downFailNotification(notifiId, DownloadService.this.entity, failType);
                }
            }, 3000);
        }
    }

    private boolean isSlt(Entity entity2, int notifiId) {
        boolean isASlt = false;
        if (entity2.isRichPush()) {
            return true;
        }
        if (entity2.isMsgTypeAAndUpdate()) {
            PkEntity aEntity = (PkEntity) entity2;
            if (!ProtocolHelper.isNeedSDownload(aEntity.needSDownload, aEntity.sDownloadNetworkOption, this)) {
                downloadingNotification(entity2, notifiId, 0, 0);
            } else {
                isASlt = true;
            }
        }
        return isASlt || entity2.isMsgTypeVideo();
    }

    /* access modifiers changed from: private */
    public void downloadingNotification(Entity entity2, int notifiId, long downloadLenth, long totalLength) {
        if (VERSION.SDK_INT >= 11) {
            if (this.notificationBuilder == null) {
                this.notificationBuilder = new Builder(getApplicationContext()).setSmallIcon(17301633).setWhen(System.currentTimeMillis()).setDefaults(4).setOngoing(true);
                this.notificationBuilder.setContentIntent(PendingIntent.getActivity(getApplicationContext(), notifiId, new Intent(), 134217728));
            }
            String title = entity2.notificationTitle;
            String content = "下载中... ";
            int percent = (int) ((((float) downloadLenth) / ((float) totalLength)) * 100.0f);
            if (totalLength > 0) {
                content = content + percent + "%";
            }
            if (this.notificationLayoutId == null || this.notificationLayoutId.intValue() <= 0) {
                this.notificationBuilder.setContentTitle(title).setContentText(content).setContentIntent(PendingIntent.getActivity(getApplicationContext(), notifiId, new Intent(), 134217728));
            } else {
                if (this.remoteViews == null) {
                    this.remoteViews = new RemoteViews(getPackageName(), this.notificationLayoutId.intValue());
                    this.remoteViews.setTextViewText(this.titleViewId.intValue(), title);
                    this.remoteViews.setImageViewResource(this.imageViewId.intValue(), JPush.mPackageIconId);
                }
                this.remoteViews.setTextViewText(this.textProgressId.intValue(), percent + "%");
                this.remoteViews.setProgressBar(this.progressViewId.intValue(), 100, percent, false);
                this.notificationBuilder.setContent(this.remoteViews);
            }
            this.mNotificationManager.notify(notifiId, this.notificationBuilder.getNotification());
            return;
        }
        if (this.notification == null) {
            this.notification = new Notification();
            this.notification.icon = 17301633;
            this.notification.when = System.currentTimeMillis();
            this.notification.flags = 2;
            this.notification.defaults = 4;
            this.notification.contentIntent = PendingIntent.getActivity(getApplicationContext(), notifiId, new Intent(), 134217728);
        }
        String title2 = entity2.notificationTitle;
        String content2 = "下载中... ";
        int percent2 = (int) ((((float) downloadLenth) / ((float) totalLength)) * 100.0f);
        if (totalLength > 0) {
            content2 = content2 + percent2 + "%";
        }
        if (this.notificationLayoutId == null || this.notificationLayoutId.intValue() <= 0) {
            NotificationHelper.methodInvokeNoti(this.notification, this, title2, content2, PendingIntent.getActivity(getApplicationContext(), notifiId, new Intent(), 134217728));
        } else {
            if (this.remoteViews == null) {
                this.remoteViews = new RemoteViews(getPackageName(), this.notificationLayoutId.intValue());
                this.remoteViews.setTextViewText(this.titleViewId.intValue(), title2);
                this.remoteViews.setImageViewResource(this.imageViewId.intValue(), JPush.mPackageIconId);
            }
            this.remoteViews.setTextViewText(this.textProgressId.intValue(), percent2 + "%");
            this.remoteViews.setProgressBar(this.progressViewId.intValue(), 100, percent2, false);
            this.notification.contentView = this.remoteViews;
        }
        if (this.notification != null) {
            this.mNotificationManager.notify(notifiId, this.notification);
        }
    }

    /* access modifiers changed from: private */
    public void downloadSucceed(Entity entity2) {
        String mainActivity;
        if (entity2.isRichPush()) {
            AndroidUtil.sendBroadcastToApp(getApplicationContext(), entity2);
            return;
        }
        String filePath = entity2.getDownloadedSavedPath();
        if (entity2.isMsgTypeAAndUpdate() && !TextUtils.isEmpty(filePath)) {
            PkEntity aEntity = (PkEntity) entity2;
            String str = "";
            if (aEntity.needAutoRunup) {
                mainActivity = aEntity.aMainActivity;
            } else {
                mainActivity = PkEntity.MAIN_ACTIVITY_CONST_NOT_AUTO_RUN;
            }
            PackageInfo info = null;
            try {
                info = getPackageManager().getPackageArchiveInfo(filePath, 16384);
            } catch (Exception e) {
                Logger.m1433w(TAG, "analyze package error !", e);
            }
            String appName = info != null ? info.packageName : "";
            if (TextUtils.isEmpty(appName) && !TextUtils.isEmpty(aEntity.aPackageName)) {
                appName = aEntity.aPackageName;
            }
            DbHelper.updateUpTable(this, entity2, appName, mainActivity);
            if (ProtocolHelper.isNeedSDownload(aEntity.needSDownload, aEntity.sDownloadNetworkOption, getApplicationContext())) {
                downloadEndNotification(entity2, true);
                return;
            }
            if (aEntity.needShowFinishedNotification) {
                downloadEndNotification(entity2, false);
            }
            if (aEntity.needAutoInstall) {
                AndroidUtil.installPackage(getApplicationContext(), filePath);
            }
            if (!aEntity.needShowFinishedNotification && !aEntity.needAutoInstall) {
                Logger.m1416d(TAG, "NOTE: no action for this downloaded pk");
            }
        } else if (!entity2.isMsgTypeVideo() || TextUtils.isEmpty(filePath)) {
            Logger.m1416d(TAG, "No end notification. is filePath empty ? - " + filePath);
        } else {
            downloadEndNotification(entity2, false);
        }
    }

    private void downloadEndNotification(Entity entity2, boolean isSlcDownloadedA) {
        Intent intent;
        boolean needOriginalPakcageIcon = entity2.isMsgTypeUpdate();
        boolean needBroadcast = false;
        if (!entity2.isMsgTypeAAndUpdate() || isSlcDownloadedA) {
            intent = AndroidUtil.getIntentForStartPushActivity(getApplicationContext(), entity2, false);
        } else {
            needBroadcast = true;
            intent = new Intent();
            intent.putExtra("body", entity2);
            intent.setClass(getApplicationContext(), PushReceiver.class);
            intent.setAction(PushReceiver.INTENT_NOTIFICATION_INSTALL_CLICKED);
            if (entity2.isMsgTypeUpdate()) {
                entity2.notificationContent = JPushConstants.NOTIFICATION_CONTENT_UPDATE_INSTALL;
            } else {
                entity2.notificationContent = JPushConstants.NOTIFICATION_CONTENT_A_INSTALL;
            }
        }
        int notifyId = NotificationHelper.getNofiticationID(entity2, 0);
        Notification notification2 = NotificationHelper.getDefaultNotification(getApplicationContext(), notifyId, intent, entity2, needOriginalPakcageIcon, needBroadcast);
        if (notification2 != null) {
            this.mNotificationManager.notify(notifyId, notification2);
        } else {
            Logger.m1420e(TAG, "get notification failed");
        }
    }

    /* access modifiers changed from: private */
    public void downFailNotification(int notifiId, Entity entity2, int failType) {
        String content;
        if (failType != 0 && !entity2.isRichPush()) {
            int flags = 4;
            if (2 == failType) {
                content = "下载失败。请稍后点击重新下载！";
            } else if (3 == failType) {
                content = "下载资源失效。请稍后点击重新下载！";
            } else if (1 == failType) {
                content = "当前网络不可用。稍后会继续下载！";
                flags = 2;
            } else {
                return;
            }
            String title = entity2.notificationTitle;
            Intent intent = new Intent();
            if (DownloadControl.isRealFailed(failType)) {
                intent.setClass(getApplicationContext(), DownloadService.class);
                entity2._downloadRetryTimes = -1;
                intent.putExtra("body", entity2);
            }
            PendingIntent pendingIntent = PendingIntent.getService(this, notifiId, intent, 134217728);
            if (VERSION.SDK_INT >= 11) {
                new Builder(getApplicationContext()).setContentTitle(title).setContentText(content).setContentIntent(pendingIntent).setWhen(System.currentTimeMillis()).setSmallIcon(17301634).getNotification().flags = flags;
            } else {
                Notification notification2 = new Notification();
                notification2.icon = 17301634;
                notification2.when = System.currentTimeMillis();
                notification2.flags = flags;
                NotificationHelper.methodInvokeNoti(notification2, getApplicationContext(), title, content, pendingIntent);
            }
            if (this.notification != null) {
                this.mNotificationManager.notify(notifiId, this.notification);
            }
        }
    }

    public static boolean hasDownladTask() {
        return mDownladTasks.size() > 0;
    }

    public static void startDownloadTasks(Context context) {
        Logger.m1416d(TAG, "Execute old download task - size:" + mDownladTasks.size());
        ArrayList<Entity> taskList = new ArrayList<>();
        while (true) {
            Entity entity2 = (Entity) mDownladTasks.poll();
            if (entity2 == null) {
                break;
            } else if (entity2._isDownloadInterrupted) {
                Logger.m1416d(TAG, "Starting to download - messageId:" + entity2.messageId);
                ServiceInterface.executeDownload(context, entity2);
            } else {
                Logger.m1428v(TAG, "Downloading is still there.");
                taskList.add(entity2);
            }
        }
        Iterator it = taskList.iterator();
        while (it.hasNext()) {
            mDownladTasks.offer((Entity) it.next());
        }
    }
}
