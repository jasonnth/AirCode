package p005cn.jpush.android.p006ui;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import java.io.File;
import p005cn.jpush.android.api.NotificationHelper;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.data.ShowEntity;
import p005cn.jpush.android.helpers.ProtocolHelper;
import p005cn.jpush.android.helpers.ReportHelper;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.ui.PushActivity */
public class PushActivity extends Activity {
    public static final String FROM_OTHER_WAY = "from_way";
    private static final int MSG_FINISH_ACTIVITY = 2;
    private static final int MSG_SHOW_PROCESS = 1;
    private static final String TAG = "PushActivity";
    private boolean fromOtherway = false;
    private FullScreenView fullScreenView = null;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Entity entity = (Entity) msg.obj;
            switch (msg.what) {
                case 1:
                    PushActivity.this.setRequestedOrientation(1);
                    PushActivity.this.processShow(entity);
                    return;
                case 2:
                    PushActivity.this.finish();
                    return;
                default:
                    return;
            }
        }
    };
    private String msgId;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            try {
                this.fromOtherway = getIntent().getBooleanExtra(FROM_OTHER_WAY, false);
                Entity entity = (Entity) getIntent().getSerializableExtra("body");
                if (entity != null) {
                    this.msgId = entity.messageId;
                    processEntity(entity);
                    return;
                }
                Logger.m1434ww(TAG, "Warning，null message entity! Close PushActivity!");
                finish();
            } catch (Exception e) {
                Logger.m1422ee(TAG, "Extra data is not serializable!");
                e.printStackTrace();
                finish();
            }
        } else {
            Logger.m1434ww(TAG, "PushActivity get NULL intent!");
            finish();
        }
    }

    private void processEntity(Entity entity) {
        if (entity != null) {
            switch (entity.type) {
                case 0:
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = entity;
                    this.mHandler.sendMessageDelayed(msg, 500);
                    return;
                default:
                    Logger.m1432w(TAG, "Invalid msg type to show - " + entity.type);
                    NotificationHelper.cancelNotification(this, entity, 0);
                    finish();
                    return;
            }
        } else {
            Logger.m1434ww(TAG, "Null message entity! Close PushActivity!");
            finish();
        }
    }

    /* access modifiers changed from: private */
    public void processShow(Entity entity) {
        Logger.m1424i(TAG, "Action: processShow");
        if (entity == null) {
            Logger.m1434ww(TAG, "Null message entity! Close PushActivity!");
            finish();
        }
        ShowEntity showEntity = (ShowEntity) entity;
        if (showEntity.showMode == 0) {
            int webviewLayoutId = getResources().getIdentifier("jpush_webview_layout", "layout", getPackageName());
            if (webviewLayoutId == 0) {
                Logger.m1422ee(TAG, "Please add layout resource jpush_webview_layout.xml to res/layout !");
                finish();
                return;
            }
            setContentView(webviewLayoutId);
            String showUrl = showEntity.showUrl;
            if (!ProtocolHelper.checkValidUrl(showUrl)) {
                NotificationHelper.cancelNotification(this, entity, 0);
                finish();
                return;
            }
            String savedPath = showEntity._webPagePath;
            if (showEntity.isFullScreen) {
                int titleViewId = getResources().getIdentifier("actionbarLayoutId", "id", getPackageName());
                if (titleViewId == 0) {
                    Logger.m1422ee(TAG, "Please use default code in jpush_webview_layout.xml!");
                    finish();
                    return;
                }
                this.fullScreenView = (FullScreenView) findViewById(titleViewId);
                this.fullScreenView.initModule(this, entity);
                if (TextUtils.isEmpty(savedPath) || !new File(savedPath.replace("file://", "")).exists() || this.fromOtherway) {
                    this.fullScreenView.loadUrl(showUrl);
                } else {
                    this.fullScreenView.loadUrl(savedPath);
                }
            }
            if (!this.fromOtherway) {
                ReportHelper.reportMsgResult(this.msgId, 1000, this);
            }
        }
        if (1 == showEntity.richType) {
        }
    }

    public void showTitleBar() {
        if (this.fullScreenView != null) {
            this.fullScreenView.showTitleBar();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.fullScreenView != null) {
            this.fullScreenView.resume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.fullScreenView != null) {
            this.fullScreenView.pause();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.fullScreenView != null) {
            this.fullScreenView.destory();
        }
        if (this.mHandler.hasMessages(2)) {
            this.mHandler.removeMessages(2);
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        if (this.fullScreenView == null || !this.fullScreenView.webviewCanGoBack()) {
            ReportHelper.reportMsgResult(this.msgId, 1006, this);
            finish();
            return;
        }
        this.fullScreenView.webviewGoBack();
    }

    private void startMainActivity() {
        PackageManager pm = getPackageManager();
        String pkgName = getApplicationContext().getPackageName();
        if (!pkgName.isEmpty()) {
            Intent intent = pm.getLaunchIntentForPackage(pkgName);
            if (intent == null) {
                Logger.m1434ww(TAG, "Can't get launch intent for this package!");
                return;
            }
            intent.addFlags(335544320);
            startActivity(intent);
            return;
        }
        Logger.m1434ww(TAG, "The package with the given name cannot be found!");
    }
}
