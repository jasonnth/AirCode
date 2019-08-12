package p005cn.jpush.android.webview.bridge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.api.JPushInterface;
import p005cn.jpush.android.api.NotificationHelper;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.data.PkEntity;
import p005cn.jpush.android.data.VideoEntity;
import p005cn.jpush.android.helpers.ProtocolHelper;
import p005cn.jpush.android.helpers.ReportHelper;
import p005cn.jpush.android.p006ui.PopWinActivity;
import p005cn.jpush.android.p006ui.PushActivity;
import p005cn.jpush.android.service.ServiceInterface;
import p005cn.jpush.android.service.StatusCode;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.webview.bridge.WebViewHelper */
public class WebViewHelper {
    private static final String TAG = "WebViewHelper";
    private final WeakReference<Activity> mActivity;
    private final Entity mEntity;

    public WebViewHelper(Context context, Entity entity) {
        this.mActivity = new WeakReference<>((Activity) context);
        this.mEntity = entity;
    }

    public void createShortcut(String name, String url, String iconId) {
        int id = 0;
        try {
            id = Integer.parseInt(iconId);
        } catch (Exception e) {
            Logger.m1416d(TAG, "iconId should be int - " + iconId);
        }
        if (this.mActivity.get() != null) {
            Logger.m1416d(TAG, "Web callback:createShortcut - name:" + name + ", url:" + url);
            AndroidUtil.createWebUrlShortcut((Context) this.mActivity.get(), name, url, NotificationHelper.getNotifiIcon(id));
        }
    }

    public void click(String actionId, String shouldClose, String shouldCancelNotification) {
        if (this.mActivity.get() != null) {
            Logger.m1416d(TAG, "Web callback:click - actionId:" + actionId + ", shouldClose:" + shouldClose + ", shouldCancelNotification:" + shouldCancelNotification);
            userClick(actionId);
            boolean needClose = false;
            boolean needCancel = false;
            try {
                needClose = Boolean.parseBoolean(shouldClose);
                needCancel = Boolean.parseBoolean(shouldCancelNotification);
            } catch (Exception e) {
            }
            if (needCancel) {
                NotificationHelper.cancelNotification((Context) this.mActivity.get(), this.mEntity, 0);
            }
            if (needClose) {
                ((Activity) this.mActivity.get()).finish();
            }
        }
    }

    private void userClick(String param) {
        int actionId = StatusCode.RESULT_TPPE_INVALID_PARAM;
        try {
            actionId = Integer.parseInt(param);
        } catch (Exception e) {
            Logger.m1420e(TAG, "Invalid actionId from Web - " + param);
        }
        ReportHelper.reportMsgResult(this.mEntity.messageId, actionId, (Context) this.mActivity.get());
    }

    @JavascriptInterface
    public void download(String actionId, String downladUrl, String msgType) {
        Logger.m1428v(TAG, "msgType from web: " + msgType);
        download(actionId, downladUrl);
    }

    public void startActivityByName(String activityName, String params) {
        Logger.m1416d(TAG, "activityName = " + activityName);
        if (StringUtils.isEmpty(activityName)) {
            Logger.m1422ee(TAG, "The activity name is null or empty, Give up..");
        }
        Context context = (Context) this.mActivity.get();
        if (context != null) {
            try {
                Class activityClass = Class.forName(activityName);
                if (activityClass != null) {
                    Intent intent = new Intent(context, activityClass);
                    intent.putExtra(JPushInterface.EXTRA_ACTIVITY_PARAM, params);
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                }
            } catch (Exception e) {
                Logger.m1422ee(TAG, "The activity name is invalid, Give up..");
            }
        }
    }

    public void startActivityByIntent(String intentName, String params) {
        Context context = (Context) this.mActivity.get();
        if (context != null) {
            try {
                Intent intent = new Intent(intentName);
                intent.addCategory(context.getPackageName());
                intent.putExtra(JPushInterface.EXTRA_EXTRA, params);
                intent.setFlags(268435456);
                context.startActivity(intent);
            } catch (Exception e) {
                Logger.m1422ee(TAG, "Unhandle intent : " + intentName);
            }
        }
    }

    public void triggerNativeAction(String params) {
        Context context = (Context) this.mActivity.get();
        if (context != null) {
            AndroidUtil.sendBroadcast(context, JPushInterface.ACTION_RICHPUSH_CALLBACK, params);
        }
    }

    public void startMainActivity(String params) {
        Context context = (Context) this.mActivity.get();
        if (context != null) {
            try {
                AndroidUtil.startMainActivity(context, params);
                ((Activity) context).finish();
            } catch (Exception e) {
                Logger.m1422ee(TAG, "Unhandle intent : cn.jpush.android.intent.ACTION_ACTIVITY_OPENDED");
            }
        }
    }

    public void download(String actionId, String downladUrl) {
        if (this.mActivity.get() != null) {
            userClick(actionId);
            download(downladUrl);
            NotificationHelper.cancelNotification((Context) this.mActivity.get(), this.mEntity, 0);
            ((Activity) this.mActivity.get()).finish();
        }
    }

    public void download(String downladUrl) {
        if (this.mActivity.get() != null) {
            Logger.m1416d(TAG, "Web callback:download - " + downladUrl);
            Context context = (Context) this.mActivity.get();
            Entity entity = this.mEntity;
            if (entity.isMsgTypeAAndUpdate()) {
                PkEntity pkEntity = (PkEntity) entity;
                if (TextUtils.isEmpty(pkEntity.aDownloadUrl)) {
                    pkEntity.aDownloadUrl = downladUrl;
                }
                if (!TextUtils.isEmpty(pkEntity._aSavedPath)) {
                    AndroidUtil.installPackage(context, pkEntity._aSavedPath);
                    NotificationHelper.cancelNotification(context, pkEntity, 0);
                    NotificationHelper.cancelNotification(context, pkEntity, 1);
                    return;
                }
            } else if (entity.isMsgTypeVideo()) {
                VideoEntity videoEntity = (VideoEntity) entity;
                if (TextUtils.isEmpty(videoEntity.videoPlayUrl)) {
                    videoEntity.videoPlayUrl = downladUrl;
                }
                if (!TextUtils.isEmpty(videoEntity._videoSavedPath)) {
                    context.startActivity(AndroidUtil.getIntentForStartPushActivity(context, entity, false));
                    return;
                }
            } else {
                Logger.m1432w(TAG, "Invalid messageType for download - " + entity.type);
                return;
            }
            ServiceInterface.executeDownload(context, entity);
        }
    }

    public void close() {
        if (this.mActivity.get() != null) {
            Logger.m1416d(TAG, "Web callback:close");
            ((Activity) this.mActivity.get()).finish();
        }
    }

    public void showToast(String toast) {
        if (this.mActivity.get() != null) {
            Logger.m1416d(TAG, "Web callback:showToast - " + toast);
            Toast.makeText((Context) this.mActivity.get(), toast, 0).show();
        }
    }

    public void executeMsgMessage(String json) {
        if (JPush.DEBUG_MODE) {
            Logger.m1416d(TAG, "Web callback:executeMsgMessage - " + json);
            if (this.mActivity.get() != null) {
                ProtocolHelper.parseOriginalMsgMessage((Context) this.mActivity.get(), json);
            }
        }
    }

    public void showTitleBar() {
        if (this.mActivity.get() != null && (this.mActivity.get() instanceof PushActivity)) {
            ((PushActivity) this.mActivity.get()).showTitleBar();
        }
    }

    public void startPushActivity(String url) {
        if (this.mActivity.get() != null && (this.mActivity.get() instanceof PopWinActivity)) {
            ((PopWinActivity) this.mActivity.get()).startPushActivity(url);
        }
    }
}
