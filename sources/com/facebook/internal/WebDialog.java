package com.facebook.internal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.AccessToken;
import com.facebook.C3344R;
import com.facebook.FacebookDialogException;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.FacebookServiceException;
import com.facebook.GraphRequest.Callback;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.share.internal.ShareConstants;
import com.facebook.share.internal.ShareInternalUtility;
import com.facebook.share.widget.ShareDialog;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONObject;

public class WebDialog extends Dialog {
    private static final int API_EC_DIALOG_CANCEL = 4201;
    private static final int BACKGROUND_GRAY = -872415232;
    static final String CANCEL_URI = "fbconnect://cancel";
    static final boolean DISABLE_SSL_CHECK_FOR_TESTING = false;
    private static final String DISPLAY_TOUCH = "touch";
    private static final String LOG_TAG = "FacebookSDK.WebDialog";
    private static final int MAX_PADDING_SCREEN_HEIGHT = 1280;
    private static final int MAX_PADDING_SCREEN_WIDTH = 800;
    private static final double MIN_SCALE_FACTOR = 0.5d;
    private static final int NO_PADDING_SCREEN_HEIGHT = 800;
    private static final int NO_PADDING_SCREEN_WIDTH = 480;
    static final String REDIRECT_URI = "fbconnect://success";
    /* access modifiers changed from: private */
    public FrameLayout contentFrameLayout;
    /* access modifiers changed from: private */
    public ImageView crossImageView;
    /* access modifiers changed from: private */
    public String expectedRedirectUrl;
    /* access modifiers changed from: private */
    public boolean isDetached;
    /* access modifiers changed from: private */
    public boolean isPageFinished;
    private boolean listenerCalled;
    private OnCompleteListener onCompleteListener;
    /* access modifiers changed from: private */
    public ProgressDialog spinner;
    private UploadStagingResourcesTask uploadTask;
    /* access modifiers changed from: private */
    public String url;
    /* access modifiers changed from: private */
    public WebView webView;

    public static class Builder {
        private AccessToken accessToken;
        private String action;
        private String applicationId;
        private Context context;
        private OnCompleteListener listener;
        private Bundle parameters;
        private int theme;

        public Builder(Context context2, String action2, Bundle parameters2) {
            this.accessToken = AccessToken.getCurrentAccessToken();
            if (this.accessToken == null) {
                String applicationId2 = Utility.getMetadataApplicationId(context2);
                if (applicationId2 != null) {
                    this.applicationId = applicationId2;
                } else {
                    throw new FacebookException("Attempted to create a builder without a valid access token or a valid default Application ID.");
                }
            }
            finishInit(context2, action2, parameters2);
        }

        public Builder(Context context2, String applicationId2, String action2, Bundle parameters2) {
            if (applicationId2 == null) {
                applicationId2 = Utility.getMetadataApplicationId(context2);
            }
            Validate.notNullOrEmpty(applicationId2, "applicationId");
            this.applicationId = applicationId2;
            finishInit(context2, action2, parameters2);
        }

        public Builder setTheme(int theme2) {
            this.theme = theme2;
            return this;
        }

        public Builder setOnCompleteListener(OnCompleteListener listener2) {
            this.listener = listener2;
            return this;
        }

        public WebDialog build() {
            if (this.accessToken != null) {
                this.parameters.putString("app_id", this.accessToken.getApplicationId());
                this.parameters.putString("access_token", this.accessToken.getToken());
            } else {
                this.parameters.putString("app_id", this.applicationId);
            }
            return new WebDialog(this.context, this.action, this.parameters, this.theme, this.listener);
        }

        public String getApplicationId() {
            return this.applicationId;
        }

        public Context getContext() {
            return this.context;
        }

        public int getTheme() {
            return this.theme;
        }

        public Bundle getParameters() {
            return this.parameters;
        }

        public OnCompleteListener getListener() {
            return this.listener;
        }

        private void finishInit(Context context2, String action2, Bundle parameters2) {
            this.context = context2;
            this.action = action2;
            if (parameters2 != null) {
                this.parameters = parameters2;
            } else {
                this.parameters = new Bundle();
            }
        }
    }

    private class DialogWebViewClient extends WebViewClient {
        private DialogWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Utility.logd(WebDialog.LOG_TAG, "Redirect URL: " + url);
            if (url.startsWith(WebDialog.this.expectedRedirectUrl)) {
                Bundle values = WebDialog.this.parseResponseUri(url);
                String error = values.getString("error");
                if (error == null) {
                    error = values.getString("error_type");
                }
                String errorMessage = values.getString(AccountKitGraphConstants.BODY_ERROR_MESSAGE_KEY);
                if (errorMessage == null) {
                    errorMessage = values.getString("error_message");
                }
                if (errorMessage == null) {
                    errorMessage = values.getString(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
                }
                String errorCodeString = values.getString("error_code");
                int errorCode = -1;
                if (!Utility.isNullOrEmpty(errorCodeString)) {
                    try {
                        errorCode = Integer.parseInt(errorCodeString);
                    } catch (NumberFormatException e) {
                        errorCode = -1;
                    }
                }
                if (Utility.isNullOrEmpty(error) && Utility.isNullOrEmpty(errorMessage) && errorCode == -1) {
                    WebDialog.this.sendSuccessToListener(values);
                } else if (error != null && (error.equals("access_denied") || error.equals("OAuthAccessDeniedException"))) {
                    WebDialog.this.cancel();
                } else if (errorCode == WebDialog.API_EC_DIALOG_CANCEL) {
                    WebDialog.this.cancel();
                } else {
                    WebDialog.this.sendErrorToListener(new FacebookServiceException(new FacebookRequestError(errorCode, error, errorMessage), errorMessage));
                }
                return true;
            } else if (url.startsWith("fbconnect://cancel")) {
                WebDialog.this.cancel();
                return true;
            } else if (url.contains("touch")) {
                return false;
            } else {
                try {
                    WebDialog.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                    return true;
                } catch (ActivityNotFoundException e2) {
                    return false;
                }
            }
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            WebDialog.this.sendErrorToListener(new FacebookDialogException(description, errorCode, failingUrl));
        }

        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            handler.cancel();
            WebDialog.this.sendErrorToListener(new FacebookDialogException(null, -11, null));
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Utility.logd(WebDialog.LOG_TAG, "Webview loading URL: " + url);
            super.onPageStarted(view, url, favicon);
            if (!WebDialog.this.isDetached) {
                WebDialog.this.spinner.show();
            }
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (!WebDialog.this.isDetached) {
                WebDialog.this.spinner.dismiss();
            }
            WebDialog.this.contentFrameLayout.setBackgroundColor(0);
            WebDialog.this.webView.setVisibility(0);
            WebDialog.this.crossImageView.setVisibility(0);
            WebDialog.this.isPageFinished = true;
        }
    }

    public interface OnCompleteListener {
        void onComplete(Bundle bundle, FacebookException facebookException);
    }

    private class UploadStagingResourcesTask extends AsyncTask<Void, Void, String[]> {
        private String action;
        /* access modifiers changed from: private */
        public Exception[] exceptions;
        private Bundle parameters;

        UploadStagingResourcesTask(String action2, Bundle parameters2) {
            this.action = action2;
            this.parameters = parameters2;
        }

        /* access modifiers changed from: protected */
        public String[] doInBackground(Void... args) {
            String[] params = this.parameters.getStringArray(ShareConstants.WEB_DIALOG_PARAM_MEDIA);
            final String[] results = new String[params.length];
            this.exceptions = new Exception[params.length];
            final CountDownLatch latch = new CountDownLatch(params.length);
            ConcurrentLinkedQueue<GraphRequestAsyncTask> tasks = new ConcurrentLinkedQueue<>();
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            int i = 0;
            while (i < params.length) {
                try {
                    if (isCancelled()) {
                        Iterator it = tasks.iterator();
                        while (it.hasNext()) {
                            ((AsyncTask) it.next()).cancel(true);
                        }
                        return null;
                    }
                    Uri uri = Uri.parse(params[i]);
                    final int writeIndex = i;
                    if (Utility.isWebUri(uri)) {
                        results[writeIndex] = uri.toString();
                        latch.countDown();
                    } else {
                        tasks.add(ShareInternalUtility.newUploadStagingResourceWithImageRequest(accessToken, uri, new Callback() {
                            public void onCompleted(GraphResponse response) {
                                try {
                                    FacebookRequestError error = response.getError();
                                    if (error != null) {
                                        String message = error.getErrorMessage();
                                        if (message == null) {
                                            message = "Error staging photo.";
                                        }
                                        throw new FacebookGraphResponseException(response, message);
                                    }
                                    JSONObject data = response.getJSONObject();
                                    if (data == null) {
                                        throw new FacebookException("Error staging photo.");
                                    }
                                    String stagedImageUri = data.optString("uri");
                                    if (stagedImageUri == null) {
                                        throw new FacebookException("Error staging photo.");
                                    }
                                    results[writeIndex] = stagedImageUri;
                                    latch.countDown();
                                } catch (Exception e) {
                                    UploadStagingResourcesTask.this.exceptions[writeIndex] = e;
                                }
                            }
                        }).executeAsync());
                    }
                    i++;
                } catch (Exception e) {
                    Iterator it2 = tasks.iterator();
                    while (it2.hasNext()) {
                        ((AsyncTask) it2.next()).cancel(true);
                    }
                    return null;
                }
            }
            latch.await();
            return results;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String[] results) {
            Exception[] excArr;
            WebDialog.this.spinner.dismiss();
            for (Exception e : this.exceptions) {
                if (e != null) {
                    WebDialog.this.sendErrorToListener(e);
                    return;
                }
            }
            if (results == null) {
                WebDialog.this.sendErrorToListener(new FacebookException("Failed to stage photos for web dialog"));
                return;
            }
            List<String> resultList = Arrays.asList(results);
            if (resultList.contains(null)) {
                WebDialog.this.sendErrorToListener(new FacebookException("Failed to stage photos for web dialog"));
                return;
            }
            Utility.putJSONValueInBundle(this.parameters, ShareConstants.WEB_DIALOG_PARAM_MEDIA, new JSONArray(resultList));
            WebDialog.this.url = Utility.buildUri(ServerProtocol.getDialogAuthority(), FacebookSdk.getGraphApiVersion() + "/" + ServerProtocol.DIALOG_PATH + this.action, this.parameters).toString();
            WebDialog.this.setUpWebView((WebDialog.this.crossImageView.getDrawable().getIntrinsicWidth() / 2) + 1);
        }
    }

    public WebDialog(Context context, String url2) {
        this(context, url2, FacebookSdk.getWebDialogTheme());
    }

    public WebDialog(Context context, String url2, int theme) {
        if (theme == 0) {
            theme = FacebookSdk.getWebDialogTheme();
        }
        super(context, theme);
        this.expectedRedirectUrl = "fbconnect://success";
        this.listenerCalled = false;
        this.isDetached = false;
        this.isPageFinished = false;
        this.url = url2;
    }

    public WebDialog(Context context, String action, Bundle parameters, int theme, OnCompleteListener listener) {
        if (theme == 0) {
            theme = FacebookSdk.getWebDialogTheme();
        }
        super(context, theme);
        this.expectedRedirectUrl = "fbconnect://success";
        this.listenerCalled = false;
        this.isDetached = false;
        this.isPageFinished = false;
        if (parameters == null) {
            parameters = new Bundle();
        }
        parameters.putString(ServerProtocol.DIALOG_PARAM_REDIRECT_URI, "fbconnect://success");
        parameters.putString(ServerProtocol.DIALOG_PARAM_DISPLAY, "touch");
        parameters.putString("sdk", String.format(Locale.ROOT, "android-%s", new Object[]{FacebookSdk.getSdkVersion()}));
        this.onCompleteListener = listener;
        if (!action.equals(ShareDialog.WEB_SHARE_DIALOG) || !parameters.containsKey(ShareConstants.WEB_DIALOG_PARAM_MEDIA)) {
            this.url = Utility.buildUri(ServerProtocol.getDialogAuthority(), FacebookSdk.getGraphApiVersion() + "/" + ServerProtocol.DIALOG_PATH + action, parameters).toString();
        } else {
            this.uploadTask = new UploadStagingResourcesTask(action, parameters);
        }
    }

    public void setOnCompleteListener(OnCompleteListener listener) {
        this.onCompleteListener = listener;
    }

    public OnCompleteListener getOnCompleteListener() {
        return this.onCompleteListener;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            cancel();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void dismiss() {
        if (this.webView != null) {
            this.webView.stopLoading();
        }
        if (!this.isDetached && this.spinner != null && this.spinner.isShowing()) {
            this.spinner.dismiss();
        }
        super.dismiss();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.uploadTask == null || this.uploadTask.getStatus() != Status.PENDING) {
            resize();
            return;
        }
        this.uploadTask.execute(new Void[0]);
        this.spinner.show();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (this.uploadTask != null) {
            this.uploadTask.cancel(true);
            this.spinner.dismiss();
        }
        super.onStop();
    }

    public void onDetachedFromWindow() {
        this.isDetached = true;
        super.onDetachedFromWindow();
    }

    public void onAttachedToWindow() {
        this.isDetached = false;
        super.onAttachedToWindow();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.spinner = new ProgressDialog(getContext());
        this.spinner.requestWindowFeature(1);
        this.spinner.setMessage(getContext().getString(C3344R.string.com_facebook_loading));
        this.spinner.setCanceledOnTouchOutside(false);
        this.spinner.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                WebDialog.this.cancel();
            }
        });
        requestWindowFeature(1);
        this.contentFrameLayout = new FrameLayout(getContext());
        resize();
        getWindow().setGravity(17);
        getWindow().setSoftInputMode(16);
        createCrossImage();
        if (this.url != null) {
            setUpWebView((this.crossImageView.getDrawable().getIntrinsicWidth() / 2) + 1);
        }
        this.contentFrameLayout.addView(this.crossImageView, new LayoutParams(-2, -2));
        setContentView(this.contentFrameLayout);
    }

    /* access modifiers changed from: protected */
    public void setExpectedRedirectUrl(String expectedRedirectUrl2) {
        this.expectedRedirectUrl = expectedRedirectUrl2;
    }

    /* access modifiers changed from: protected */
    public Bundle parseResponseUri(String urlString) {
        Uri u = Uri.parse(urlString);
        Bundle b = Utility.parseUrlQueryString(u.getQuery());
        b.putAll(Utility.parseUrlQueryString(u.getFragment()));
        return b;
    }

    /* access modifiers changed from: protected */
    public boolean isListenerCalled() {
        return this.listenerCalled;
    }

    /* access modifiers changed from: protected */
    public boolean isPageFinished() {
        return this.isPageFinished;
    }

    /* access modifiers changed from: protected */
    public WebView getWebView() {
        return this.webView;
    }

    public void resize() {
        Display display = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        getWindow().setLayout(Math.min(getScaledSize(metrics.widthPixels < metrics.heightPixels ? metrics.widthPixels : metrics.heightPixels, metrics.density, NO_PADDING_SCREEN_WIDTH, 800), metrics.widthPixels), Math.min(getScaledSize(metrics.widthPixels < metrics.heightPixels ? metrics.heightPixels : metrics.widthPixels, metrics.density, 800, MAX_PADDING_SCREEN_HEIGHT), metrics.heightPixels));
    }

    private int getScaledSize(int screenSize, float density, int noPaddingSize, int maxPaddingSize) {
        double scaleFactor;
        int scaledSize = (int) (((float) screenSize) / density);
        if (scaledSize <= noPaddingSize) {
            scaleFactor = 1.0d;
        } else if (scaledSize >= maxPaddingSize) {
            scaleFactor = MIN_SCALE_FACTOR;
        } else {
            scaleFactor = MIN_SCALE_FACTOR + ((((double) (maxPaddingSize - scaledSize)) / ((double) (maxPaddingSize - noPaddingSize))) * MIN_SCALE_FACTOR);
        }
        return (int) (((double) screenSize) * scaleFactor);
    }

    /* access modifiers changed from: protected */
    public void sendSuccessToListener(Bundle values) {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            this.onCompleteListener.onComplete(values, null);
            dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void sendErrorToListener(Throwable error) {
        FacebookException facebookException;
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            if (error instanceof FacebookException) {
                facebookException = (FacebookException) error;
            } else {
                facebookException = new FacebookException(error);
            }
            this.onCompleteListener.onComplete(null, facebookException);
            dismiss();
        }
    }

    public void cancel() {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            sendErrorToListener(new FacebookOperationCanceledException());
        }
    }

    private void createCrossImage() {
        this.crossImageView = new ImageView(getContext());
        this.crossImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                WebDialog.this.cancel();
            }
        });
        this.crossImageView.setImageDrawable(getContext().getResources().getDrawable(C3344R.C3345drawable.com_facebook_close));
        this.crossImageView.setVisibility(4);
    }

    /* access modifiers changed from: private */
    @SuppressLint({"SetJavaScriptEnabled"})
    public void setUpWebView(int margin) {
        LinearLayout webViewContainer = new LinearLayout(getContext());
        this.webView = new WebView(getContext().getApplicationContext()) {
            public void onWindowFocusChanged(boolean hasWindowFocus) {
                try {
                    super.onWindowFocusChanged(hasWindowFocus);
                } catch (NullPointerException e) {
                }
            }
        };
        this.webView.setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.setWebViewClient(new DialogWebViewClient());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl(this.url);
        this.webView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.webView.setVisibility(4);
        this.webView.getSettings().setSavePassword(false);
        this.webView.getSettings().setSaveFormData(false);
        this.webView.setFocusable(true);
        this.webView.setFocusableInTouchMode(true);
        this.webView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (!v.hasFocus()) {
                    v.requestFocus();
                }
                return false;
            }
        });
        webViewContainer.setPadding(margin, margin, margin, margin);
        webViewContainer.addView(this.webView);
        webViewContainer.setBackgroundColor(BACKGROUND_GRAY);
        this.contentFrameLayout.addView(webViewContainer);
    }
}
