package com.altoros.ethnio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.webkit.WebView;

public class EthnioManager {
    @SuppressLint({"SetJavaScriptEnabled"})
    static View createEthnioView(Context context, String mainUrl, String ethnioId, OnEthnioCloseClickListener closeClickListener, int listenerId) {
        WebView view = new WebView(context);
        view.setId(1);
        view.setWebViewClient(new EthnioClient(closeClickListener, listenerId));
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(new StringBuilder(String.valueOf(mainUrl)).append("mob/").append(ethnioId).toString());
        return view;
    }
}
