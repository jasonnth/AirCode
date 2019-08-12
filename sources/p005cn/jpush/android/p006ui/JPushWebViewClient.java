package p005cn.jpush.android.p006ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.common.util.UriUtil;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.helpers.ReportHelper;
import p005cn.jpush.android.service.StatusCode;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.ui.JPushWebViewClient */
public class JPushWebViewClient extends WebViewClient {
    private static final String PARAM_DIRECT = "direct=";
    private static final String PARAM_TITLE = "title=";
    private static final String TAG = "JPushWebViewClient";
    private final Entity entity;

    public JPushWebViewClient(Entity entity2) {
        this.entity = entity2;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Context context = view.getContext();
        Logger.m1424i(TAG, "Url vaule is :" + url);
        try {
            String reportJson = String.format("{\"url\":\"%s\"}", new Object[]{url});
            if (this.entity.isGoBroswer) {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                ReportHelper.reportMsgActionResult(this.entity.messageId, StatusCode.RESULT_TYPE_CLICK_CONTENT, reportJson, JPush.mApplicationContext);
                return true;
            } else if (url.endsWith(".mp3")) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(Uri.parse(url), "audio/*");
                view.getContext().startActivity(intent);
                return true;
            } else if (url.endsWith(".mp4") || url.endsWith(".3gp")) {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setDataAndType(Uri.parse(url), "video/*");
                view.getContext().startActivity(intent2);
                return true;
            } else {
                if (url.startsWith(UriUtil.HTTP_SCHEME)) {
                    view.loadUrl(url);
                    ReportHelper.reportMsgActionResult(this.entity.messageId, StatusCode.RESULT_TYPE_CLICK_CONTENT, reportJson, JPush.mApplicationContext);
                } else if (url != null && url.startsWith("mailto")) {
                    if (url.lastIndexOf(PARAM_DIRECT) < 0 && !url.startsWith("mailto")) {
                        if (url.indexOf("?") > 0) {
                            url = url + "&direct=false";
                        } else {
                            url = url + "?direct=false";
                        }
                        int index = url.lastIndexOf(PARAM_DIRECT);
                    }
                    int index2 = url.indexOf("?");
                    String uri = url.substring(0, index2);
                    String queryString = url.substring(index2);
                    Logger.m1428v(TAG, "Uri: " + uri);
                    Logger.m1428v(TAG, "QueryString: " + queryString);
                    Intent intent3 = null;
                    if (uri.startsWith("mailto")) {
                        String[] temp = uri.split(":");
                        if (temp != null && temp.length == 2) {
                            String str = "&content=";
                            int fIndex = queryString.indexOf(PARAM_TITLE) + PARAM_TITLE.length();
                            int lIndex = queryString.indexOf("&content=");
                            String title = queryString.substring(fIndex, lIndex);
                            String content = queryString.substring("&content=".length() + lIndex);
                            String[] emailReciver = {temp[1]};
                            intent3 = new Intent("android.intent.action.SEND");
                            intent3.setType("plain/text");
                            intent3.putExtra("android.intent.extra.EMAIL", emailReciver);
                            intent3.putExtra("android.intent.extra.SUBJECT", title);
                            intent3.putExtra("android.intent.extra.TEXT", content);
                        }
                    }
                    if (intent3 != null) {
                        context.startActivity(intent3);
                    }
                    ReportHelper.reportMsgActionResult(this.entity.messageId, StatusCode.RESULT_TYPE_CLICK_CONTENT, reportJson, JPush.mApplicationContext);
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            Logger.m1420e(TAG, "Invalid url");
            return true;
        }
    }

    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }
}
