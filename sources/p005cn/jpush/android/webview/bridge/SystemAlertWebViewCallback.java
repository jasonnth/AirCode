package p005cn.jpush.android.webview.bridge;

import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import p005cn.jpush.android.api.JPushInterface;
import p005cn.jpush.android.api.SystemAlertHelper;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.webview.bridge.SystemAlertWebViewCallback */
public class SystemAlertWebViewCallback {
    private static final String TAG = "SystemAlertWebViewCallback";
    private ImageButton imageClose = null;
    private Context mContext = null;
    private WebView webView = null;

    /* renamed from: wm */
    private WindowManager f1928wm = null;

    public SystemAlertWebViewCallback(Context context, Entity entity, WindowManager wm, WebView webView2, ImageButton imageClose2) {
        Logger.m1416d(TAG, TAG);
        this.mContext = context;
        this.f1928wm = wm;
        this.webView = webView2;
        this.imageClose = imageClose2;
    }

    public void startActivityByName(String activityName, String params) {
        Logger.m1416d(TAG, "action --- startActivityByName--------activityName : " + activityName + "----- params : " + params);
        if (StringUtils.isEmpty(activityName)) {
            Logger.m1422ee(TAG, "The activity name is null or empty, Give up..");
        }
        if (this.mContext != null) {
            try {
                Class activityClass = Class.forName(activityName);
                if (activityClass != null) {
                    Intent intent = new Intent(this.mContext, activityClass);
                    intent.putExtra(JPushInterface.EXTRA_ACTIVITY_PARAM, params);
                    intent.setFlags(268435456);
                    this.mContext.startActivity(intent);
                    close();
                }
            } catch (Exception e) {
                Logger.m1422ee(TAG, "The activity name is invalid, Give up..");
            }
        }
    }

    public void close() {
        Logger.m1416d(TAG, "action --- close");
        SystemAlertHelper.close(this.f1928wm, this.webView, this.imageClose);
    }
}
