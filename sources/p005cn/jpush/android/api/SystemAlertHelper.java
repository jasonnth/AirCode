package p005cn.jpush.android.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView;
import android.widget.ImageButton;
import com.facebook.common.util.UriUtil;
import java.io.IOException;
import java.io.InputStream;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.data.ShowEntity;
import p005cn.jpush.android.p006ui.JPushWebViewClient;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.webview.bridge.CustomChromeClient;
import p005cn.jpush.android.webview.bridge.HostJsScope;
import p005cn.jpush.android.webview.bridge.SystemAlertWebViewCallback;

/* renamed from: cn.jpush.android.api.SystemAlertHelper */
public class SystemAlertHelper {
    private static final String TAG = "SystemAlertHelper";
    private static int WINDOWHEIGHT = 600;
    private static int WINDOWWIDTH = 400;
    public static SystemAlertWebViewCallback systemAlertHelper = null;

    public static void close(WindowManager wm, WebView webView, ImageButton imageClose) {
        wm.removeView(webView);
        wm.removeView(imageClose);
    }

    public static void systemAlert() {
    }

    public static void systemAlert(final Context context, final Entity entity) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                String str = "";
                if (!(entity instanceof ShowEntity) || !entity.isRichPush()) {
                    Logger.m1420e(SystemAlertHelper.TAG, " not Rich Notification");
                    return;
                }
                Logger.m1416d(SystemAlertHelper.TAG, "ShowEntity");
                String url = ((ShowEntity) entity).showUrl;
                final WindowManager wm = (WindowManager) context.getSystemService("window");
                final WebView webView = new WebView(context);
                final ImageButton imageClose = new ImageButton(context);
                SystemAlertHelper.systemAlertCreate(context, wm, webView, imageClose);
                webView.setHorizontalScrollBarEnabled(false);
                webView.setVerticalScrollBarEnabled(false);
                webView.setScrollbarFadingEnabled(true);
                webView.setScrollBarStyle(33554432);
                AndroidUtil.webSettings(webView.getSettings());
                SystemAlertHelper.systemAlertHelper = new SystemAlertWebViewCallback(context, entity, wm, webView, imageClose);
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.setWebChromeClient(new CustomChromeClient(JPushConstants.PARAM_JS_MODULE, HostJsScope.class));
                webView.setWebViewClient(new JPushWebViewClient(entity));
                if (!TextUtils.isEmpty(url) && url.startsWith(UriUtil.HTTP_SCHEME)) {
                    webView.loadUrl(url);
                }
                imageClose.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        SystemAlertHelper.close(wm, webView, imageClose);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public static void systemAlertCreate(Context context, WindowManager wm, WebView webView, ImageButton imageClose) {
        int closeX = 200;
        int closeY = -300;
        DisplayMetrics dm = getResolution(context);
        if (dm != null) {
            WINDOWWIDTH = (dm.widthPixels * 3) / 4;
            WINDOWHEIGHT = dm.heightPixels / 2;
            closeX = WINDOWWIDTH / 2;
            closeY = -(WINDOWHEIGHT / 2);
        }
        LayoutParams wmParams = new LayoutParams();
        wmParams.format = 1;
        wmParams.gravity = 17;
        wmParams.type = 2003;
        wmParams.flags |= 552;
        wmParams.width = WINDOWWIDTH;
        wmParams.height = WINDOWHEIGHT;
        wmParams.x = -1;
        wmParams.y = -1;
        wm.addView(webView, wmParams);
        imageClose.setBackgroundColor(context.getResources().getColor(17170445));
        imageClose.setImageBitmap(getImageFromAssetsFile(context, "jpush_close.png"));
        wmParams.width = -2;
        wmParams.height = -2;
        wmParams.y = closeY;
        wmParams.x = closeX;
        wm.addView(imageClose, wmParams);
    }

    public static int getWindowWidth() {
        return WINDOWWIDTH;
    }

    public static int getWindowHeight() {
        return WINDOWHEIGHT;
    }

    private static DisplayMetrics getResolution(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        if (dm == null) {
            return null;
        }
        Logger.m1416d(TAG, "width : " + dm.widthPixels);
        Logger.m1416d(TAG, "height : " + dm.heightPixels);
        Logger.m1416d(TAG, "density : " + dm.density);
        Logger.m1416d(TAG, "densityDpi : " + dm.densityDpi);
        Logger.m1416d(TAG, "xdpi : " + dm.xdpi);
        Logger.m1416d(TAG, "ydpi : " + dm.ydpi);
        return dm;
    }

    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {
        Bitmap image = null;
        try {
            InputStream is = context.getResources().getAssets().open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return image;
        }
    }
}
