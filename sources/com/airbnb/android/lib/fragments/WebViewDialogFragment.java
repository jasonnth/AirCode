package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.android.core.views.AirWebView.AirWebViewCallbacks;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.Strap;

public class WebViewDialogFragment extends ZenDialog {
    private static final int INVALID_ID = -1;
    private static final String TITLE = "title";
    private static final String URL = "url";
    private static final String WAIT_FOR_PAGE_LOAD = "disable_button_until_page_has_loaded";
    AirWebView webView;

    public static WebViewDialogFragment newInstance(int title, String url, boolean waitForPageLoad, int requestCode, Fragment targetFragment) {
        Bundle arguments = new Bundle();
        arguments.putInt("title", title);
        arguments.putString("url", url);
        arguments.putBoolean(WAIT_FOR_PAGE_LOAD, waitForPageLoad);
        return (WebViewDialogFragment) new ZenBuilder(new WebViewDialogFragment()).withTitle(title).withCustomLayout().withArguments(arguments).withSingleButton(C0880R.string.okay, requestCode, targetFragment).create();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        this.webView = (AirWebView) inflater.inflate(C0880R.layout.dialog_webview, container, false);
        setCustomView(this.webView);
        String url = getArguments().getString("url");
        if (!TextUtils.isEmpty(url)) {
            if (getArguments().getBoolean(WAIT_FOR_PAGE_LOAD, false)) {
                final View singleButton = getSingleButton();
                singleButton.setEnabled(false);
                this.webView.addCallbacks(new AirWebViewCallbacks() {
                    public void onPageFinished(WebView view, String url) {
                        singleButton.setEnabled(true);
                    }
                });
            }
            this.webView.loadUrl(url);
        }
        return view;
    }

    public void onDestroyView() {
        this.webView.onDestroy();
        super.onDestroyView();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.WebView;
    }

    public Strap getNavigationTrackingParams() {
        Strap params = super.getNavigationTrackingParams();
        int titleId = getArguments().getInt("title", -1);
        return titleId != -1 ? params.mo11639kv("title", getString(titleId)) : params;
    }
}
