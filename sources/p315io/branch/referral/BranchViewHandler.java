package p315io.branch.referral;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.airbnb.android.core.analytics.BaseAnalytics;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;
import p315io.branch.referral.Defines.Jsonkey;

/* renamed from: io.branch.referral.BranchViewHandler */
public class BranchViewHandler {
    private static BranchViewHandler thisInstance_;
    /* access modifiers changed from: private */
    public Dialog branchViewDialog_;
    /* access modifiers changed from: private */
    public boolean isBranchViewAccepted_;
    /* access modifiers changed from: private */
    public boolean isBranchViewDialogShowing_;
    /* access modifiers changed from: private */
    public boolean loadingHtmlInBackGround_ = false;
    private BranchView openOrInstallPendingBranchView_ = null;
    private String parentActivityClassName_;
    /* access modifiers changed from: private */
    public boolean webViewLoadError_;

    /* renamed from: io.branch.referral.BranchViewHandler$BranchView */
    private class BranchView {
        /* access modifiers changed from: private */
        public String branchViewAction_;
        /* access modifiers changed from: private */
        public String branchViewID_;
        private int num_of_use_;
        /* access modifiers changed from: private */
        public String webViewHtml_;
        /* access modifiers changed from: private */
        public String webViewUrl_;

        private BranchView(JSONObject branchViewJson, String actionName) {
            this.branchViewID_ = "";
            this.branchViewAction_ = "";
            this.num_of_use_ = 1;
            this.webViewUrl_ = "";
            this.webViewHtml_ = "";
            try {
                this.branchViewAction_ = actionName;
                if (branchViewJson.has(Jsonkey.BranchViewID.getKey())) {
                    this.branchViewID_ = branchViewJson.getString(Jsonkey.BranchViewID.getKey());
                }
                if (branchViewJson.has(Jsonkey.BranchViewNumOfUse.getKey())) {
                    this.num_of_use_ = branchViewJson.getInt(Jsonkey.BranchViewNumOfUse.getKey());
                }
                if (branchViewJson.has(Jsonkey.BranchViewUrl.getKey())) {
                    this.webViewUrl_ = branchViewJson.getString(Jsonkey.BranchViewUrl.getKey());
                }
                if (branchViewJson.has(Jsonkey.BranchViewHtml.getKey())) {
                    this.webViewHtml_ = branchViewJson.getString(Jsonkey.BranchViewHtml.getKey());
                }
            } catch (Exception e) {
            }
        }

        /* access modifiers changed from: private */
        public boolean isAvailable(Context context) {
            return this.num_of_use_ > PrefHelper.getInstance(context).getBranchViewUsageCount(this.branchViewID_) || this.num_of_use_ == -1;
        }

        public void updateUsageCount(Context context, String branchViewID) {
            PrefHelper.getInstance(context).updateBranchViewUsageCount(branchViewID);
        }
    }

    /* renamed from: io.branch.referral.BranchViewHandler$IBranchViewEvents */
    public interface IBranchViewEvents {
        void onBranchViewAccepted(String str, String str2);

        void onBranchViewCancelled(String str, String str2);

        void onBranchViewError(int i, String str, String str2);

        void onBranchViewVisible(String str, String str2);
    }

    /* renamed from: io.branch.referral.BranchViewHandler$loadBranchViewTask */
    private class loadBranchViewTask extends AsyncTask<Void, Void, Boolean> {
        private final BranchView branchView;
        private final IBranchViewEvents callback;
        private final Context context;

        public loadBranchViewTask(BranchView branchView2, Context context2, IBranchViewEvents callback2) {
            this.branchView = branchView2;
            this.context = context2;
            this.callback = callback2;
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(Void... params) {
            boolean z = false;
            int code = -1;
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(this.branchView.webViewUrl_).openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                code = connection.getResponseCode();
                if (code == 200) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    InputStream inputStream = connection.getInputStream();
                    byte[] buffer = new byte[1024];
                    while (true) {
                        int length = inputStream.read(buffer);
                        if (length == -1) {
                            break;
                        }
                        outputStream.write(buffer, 0, length);
                    }
                    this.branchView.webViewHtml_ = outputStream.toString(JPushConstants.ENCODING_UTF_8);
                    outputStream.close();
                    inputStream.close();
                }
            } catch (Exception e) {
            }
            if (code == 200) {
                z = true;
            }
            return Boolean.valueOf(z);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean loadHtml) {
            super.onPostExecute(loadHtml);
            if (loadHtml.booleanValue()) {
                BranchViewHandler.this.createAndShowBranchView(this.branchView, this.context, this.callback);
            } else if (this.callback != null) {
                this.callback.onBranchViewError(-202, "Unable to create a Branch view due to a temporary network error", this.branchView.branchViewAction_);
            }
            BranchViewHandler.this.loadingHtmlInBackGround_ = false;
        }
    }

    private BranchViewHandler() {
    }

    public static BranchViewHandler getInstance() {
        if (thisInstance_ == null) {
            thisInstance_ = new BranchViewHandler();
        }
        return thisInstance_;
    }

    public boolean showPendingBranchView(Context appContext) {
        boolean isBranchViewShowed = showBranchView(this.openOrInstallPendingBranchView_, appContext, null);
        if (isBranchViewShowed) {
            this.openOrInstallPendingBranchView_ = null;
        }
        return isBranchViewShowed;
    }

    public boolean showBranchView(JSONObject branchViewObj, String actionName, Context appContext, IBranchViewEvents callback) {
        return showBranchView(new BranchView(branchViewObj, actionName), appContext, callback);
    }

    private boolean showBranchView(BranchView branchView, Context appContext, IBranchViewEvents callback) {
        if (this.isBranchViewDialogShowing_ || this.loadingHtmlInBackGround_) {
            if (callback != null) {
                callback.onBranchViewError(-200, "Unable to create a Branch view. A Branch view is already showing", branchView.branchViewAction_);
            }
            return false;
        }
        this.isBranchViewDialogShowing_ = false;
        this.isBranchViewAccepted_ = false;
        if (!(appContext == null || branchView == null)) {
            if (branchView.isAvailable(appContext)) {
                if (!TextUtils.isEmpty(branchView.webViewHtml_)) {
                    createAndShowBranchView(branchView, appContext, callback);
                    return true;
                }
                this.loadingHtmlInBackGround_ = true;
                new loadBranchViewTask(branchView, appContext, callback).execute(new Void[0]);
                return true;
            } else if (callback != null) {
                callback.onBranchViewError(-203, "Unable to create this Branch view. Reached maximum usage limit ", branchView.branchViewAction_);
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void createAndShowBranchView(final BranchView branchView, Context appContext, final IBranchViewEvents callback) {
        if (appContext != null && branchView != null) {
            final WebView webView = new WebView(appContext);
            webView.getSettings().setJavaScriptEnabled(true);
            if (VERSION.SDK_INT >= 19) {
                webView.setLayerType(2, null);
            }
            this.webViewLoadError_ = false;
            if (!TextUtils.isEmpty(branchView.webViewHtml_)) {
                webView.loadDataWithBaseURL(null, branchView.webViewHtml_, "text/html", "utf-8", null);
                webView.setWebViewClient(new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        boolean isHandled = BranchViewHandler.this.handleUserActionRedirect(url);
                        if (!isHandled) {
                            view.loadUrl(url);
                        } else if (BranchViewHandler.this.branchViewDialog_ != null) {
                            BranchViewHandler.this.branchViewDialog_.dismiss();
                        }
                        return isHandled;
                    }

                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                    }

                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                        super.onReceivedError(view, errorCode, description, failingUrl);
                        BranchViewHandler.this.webViewLoadError_ = true;
                    }

                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        BranchViewHandler.this.openBranchViewDialog(branchView, callback, webView);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void openBranchViewDialog(final BranchView branchView, final IBranchViewEvents callback, WebView webView) {
        if (this.webViewLoadError_ || Branch.getInstance() == null || Branch.getInstance().currentActivityReference_ == null) {
            this.isBranchViewDialogShowing_ = false;
            if (callback != null) {
                callback.onBranchViewError(-202, "Unable to create a Branch view due to a temporary network error", branchView.branchViewAction_);
                return;
            }
            return;
        }
        Activity currentActivity = (Activity) Branch.getInstance().currentActivityReference_.get();
        if (currentActivity != null) {
            branchView.updateUsageCount(currentActivity.getApplicationContext(), branchView.branchViewID_);
            this.parentActivityClassName_ = currentActivity.getClass().getName();
            RelativeLayout layout = new RelativeLayout(currentActivity);
            layout.setVisibility(8);
            layout.addView(webView, new LayoutParams(-1, -1));
            layout.setBackgroundColor(0);
            if (this.branchViewDialog_ == null || !this.branchViewDialog_.isShowing()) {
                this.branchViewDialog_ = new Dialog(currentActivity, 16973834);
                this.branchViewDialog_.setContentView(layout);
                layout.setVisibility(0);
                webView.setVisibility(0);
                this.branchViewDialog_.show();
                showViewWithAlphaAnimation(layout);
                showViewWithAlphaAnimation(webView);
                this.isBranchViewDialogShowing_ = true;
                if (callback != null) {
                    callback.onBranchViewVisible(branchView.branchViewAction_, branchView.branchViewID_);
                }
                this.branchViewDialog_.setOnDismissListener(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialog) {
                        BranchViewHandler.this.isBranchViewDialogShowing_ = false;
                        BranchViewHandler.this.branchViewDialog_ = null;
                        if (callback == null) {
                            return;
                        }
                        if (BranchViewHandler.this.isBranchViewAccepted_) {
                            callback.onBranchViewAccepted(branchView.branchViewAction_, branchView.branchViewID_);
                        } else {
                            callback.onBranchViewCancelled(branchView.branchViewAction_, branchView.branchViewID_);
                        }
                    }
                });
            } else if (callback != null) {
                callback.onBranchViewError(-200, "Unable to create a Branch view. A Branch view is already showing", branchView.branchViewAction_);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean handleUserActionRedirect(String url) {
        try {
            URI uri = new URI(url);
            if (!uri.getScheme().equalsIgnoreCase("branch-cta")) {
                return false;
            }
            if (uri.getHost().equalsIgnoreCase("accept")) {
                this.isBranchViewAccepted_ = true;
                return true;
            } else if (!uri.getHost().equalsIgnoreCase(BaseAnalytics.CANCEL)) {
                return false;
            } else {
                this.isBranchViewAccepted_ = false;
                return true;
            }
        } catch (URISyntaxException e) {
            return false;
        }
    }

    private void showViewWithAlphaAnimation(View view) {
        AlphaAnimation animation1 = new AlphaAnimation(0.1f, 1.0f);
        animation1.setDuration(500);
        animation1.setStartOffset(10);
        animation1.setInterpolator(new AccelerateInterpolator());
        animation1.setFillAfter(true);
        view.setVisibility(0);
        view.startAnimation(animation1);
    }

    public boolean markInstallOrOpenBranchViewPending(JSONObject branchViewObj, String action) {
        BranchView branchView = new BranchView(branchViewObj, action);
        if (branchView == null || Branch.getInstance().currentActivityReference_ == null) {
            return false;
        }
        Activity currentActivity = (Activity) Branch.getInstance().currentActivityReference_.get();
        if (currentActivity == null || !branchView.isAvailable(currentActivity)) {
            return false;
        }
        this.openOrInstallPendingBranchView_ = new BranchView(branchViewObj, action);
        return true;
    }

    public boolean isInstallOrOpenBranchViewPending(Context context) {
        return this.openOrInstallPendingBranchView_ != null && this.openOrInstallPendingBranchView_.isAvailable(context);
    }

    public void onCurrentActivityDestroyed(Activity activity) {
        if (this.parentActivityClassName_ != null && this.parentActivityClassName_.equalsIgnoreCase(activity.getClass().getName())) {
            this.isBranchViewDialogShowing_ = false;
        }
    }
}
