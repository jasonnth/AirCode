package com.altoros.ethnio;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class EthnioClient extends WebViewClient {
    private OnEthnioCloseClickListener mCloseClickListener;
    private int mListenerId;

    interface OnEthnioCloseClickListener {
        void onCloseClick();
    }

    public EthnioClient(OnEthnioCloseClickListener closeClickListener, int listenerId) {
        this.mCloseClickListener = closeClickListener;
        this.mListenerId = listenerId;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (!url.equals("app://close")) {
            return false;
        }
        sendBroadcast(view.getContext(), "com.altoros.ethnio.ethniomanager.ACTION_ETHNIO_CLIENT_CLICK_CLOSE");
        if (this.mCloseClickListener != null) {
            this.mCloseClickListener.onCloseClick();
        }
        return true;
    }

    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        sendBroadcast(view.getContext(), "com.altoros.ethnio.ethniomanager.ACTION_ETHNIO_LOADED");
    }

    private void sendBroadcast(Context context, String action) {
        Intent intent = new Intent(action);
        intent.putExtra("ETHNIO_LISTENER_ID", this.mListenerId);
        context.sendBroadcast(intent);
    }
}
