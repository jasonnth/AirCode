package com.braintreepayments.api.threedsecure;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.braintreepayments.api.models.ThreeDSecureAuthenticationResponse;
import com.braintreepayments.api.models.ThreeDSecureLookup;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Stack;
import p005cn.jpush.android.JPushConstants;

public class ThreeDSecureWebViewActivity extends Activity {
    private ActionBar mActionBar;
    private FrameLayout mRootView;
    private Stack<ThreeDSecureWebView> mThreeDSecureWebViews;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(2);
        ThreeDSecureLookup threeDSecureLookup = (ThreeDSecureLookup) getIntent().getParcelableExtra("com.braintreepayments.api.EXTRA_THREE_D_SECURE_LOOKUP");
        if (threeDSecureLookup == null) {
            throw new IllegalArgumentException("A ThreeDSecureLookup must be specified with " + ThreeDSecureLookup.class.getSimpleName() + ".EXTRA_THREE_D_SECURE_LOOKUP extra");
        }
        setupActionBar();
        this.mThreeDSecureWebViews = new Stack<>();
        this.mRootView = (FrameLayout) findViewById(16908290);
        StringBuilder params = new StringBuilder();
        try {
            params.append("PaReq=");
            params.append(URLEncoder.encode(threeDSecureLookup.getPareq(), JPushConstants.ENCODING_UTF_8));
            params.append("&MD=");
            params.append(URLEncoder.encode(threeDSecureLookup.getMd(), JPushConstants.ENCODING_UTF_8));
            params.append("&TermUrl=");
            params.append(URLEncoder.encode(threeDSecureLookup.getTermUrl(), JPushConstants.ENCODING_UTF_8));
        } catch (UnsupportedEncodingException e) {
            finish();
        }
        ThreeDSecureWebView webView = new ThreeDSecureWebView(this);
        webView.init(this);
        webView.postUrl(threeDSecureLookup.getAcsUrl(), params.toString().getBytes());
        pushNewWebView(webView);
    }

    /* access modifiers changed from: protected */
    public void pushNewWebView(ThreeDSecureWebView webView) {
        this.mThreeDSecureWebViews.push(webView);
        this.mRootView.removeAllViews();
        this.mRootView.addView(webView);
    }

    /* access modifiers changed from: protected */
    public void popCurrentWebView() {
        this.mThreeDSecureWebViews.pop();
        pushNewWebView((ThreeDSecureWebView) this.mThreeDSecureWebViews.pop());
    }

    /* access modifiers changed from: protected */
    public void finishWithResult(ThreeDSecureAuthenticationResponse threeDSecureAuthenticationResponse) {
        setResult(-1, new Intent().putExtra("com.braintreepayments.api.EXTRA_THREE_D_SECURE_RESULT", threeDSecureAuthenticationResponse));
        finish();
    }

    public void onBackPressed() {
        if (((ThreeDSecureWebView) this.mThreeDSecureWebViews.peek()).canGoBack()) {
            ((ThreeDSecureWebView) this.mThreeDSecureWebViews.peek()).goBack();
        } else if (this.mThreeDSecureWebViews.size() > 1) {
            popCurrentWebView();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void setActionBarTitle(String title) {
        if (this.mActionBar != null) {
            this.mActionBar.setTitle(title);
        }
    }

    private void setupActionBar() {
        this.mActionBar = getActionBar();
        if (this.mActionBar != null) {
            setActionBarTitle("");
            this.mActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        setResult(0);
        finish();
        return true;
    }
}
