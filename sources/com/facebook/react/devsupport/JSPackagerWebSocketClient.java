package com.facebook.react.devsupport;

import android.os.Handler;
import android.os.Looper;
import android.util.JsonReader;
import android.util.JsonToken;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class JSPackagerWebSocketClient extends WebSocketListener {
    private static final int RECONNECT_DELAY_MS = 2000;
    private static final String TAG = "JSPackagerWebSocketClient";
    private JSPackagerCallback mCallback;
    /* access modifiers changed from: private */
    public boolean mClosed = false;
    private final Handler mHandler;
    private boolean mSuppressConnectionErrors;
    private final String mUrl;
    private WebSocket mWebSocket;

    public interface JSPackagerCallback {
        void onMessage(String str, String str2);
    }

    public JSPackagerWebSocketClient(String url, JSPackagerCallback callback) {
        this.mUrl = url;
        this.mCallback = callback;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public void connect() {
        if (this.mClosed) {
            throw new IllegalStateException("Can't connect closed client");
        }
        new Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(0, TimeUnit.MINUTES).build().newWebSocket(new Request.Builder().url(this.mUrl).build(), this);
    }

    private void reconnect() {
        if (this.mClosed) {
            throw new IllegalStateException("Can't reconnect closed client");
        }
        if (!this.mSuppressConnectionErrors) {
            FLog.m1847w(TAG, "Couldn't connect to packager, will silently retry");
            this.mSuppressConnectionErrors = true;
        }
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (!JSPackagerWebSocketClient.this.mClosed) {
                    JSPackagerWebSocketClient.this.connect();
                }
            }
        }, 2000);
    }

    public void closeQuietly() {
        this.mClosed = true;
        closeWebSocketQuietly();
    }

    private void closeWebSocketQuietly() {
        if (this.mWebSocket != null) {
            try {
                this.mWebSocket.close(1000, "End of session");
            } catch (Exception e) {
            }
            this.mWebSocket = null;
        }
    }

    private void triggerMessageCallback(String target, String action) {
        if (this.mCallback != null) {
            this.mCallback.onMessage(target, action);
        }
    }

    public void onMessage(WebSocket webSocket, String text) {
        try {
            JsonReader reader = new JsonReader(new StringReader(text));
            Integer version = null;
            String target = null;
            String action = null;
            reader.beginObject();
            while (reader.hasNext()) {
                String field = reader.nextName();
                if (JsonToken.NULL == reader.peek()) {
                    reader.skipValue();
                } else if ("version".equals(field)) {
                    version = Integer.valueOf(reader.nextInt());
                } else if (BaseAnalytics.TARGET.equals(field)) {
                    target = reader.nextString();
                } else if ("action".equals(field)) {
                    action = reader.nextString();
                }
            }
            if (version.intValue() == 1 && target != null && action != null) {
                triggerMessageCallback(target, action);
            }
        } catch (IOException e) {
            abort("Parsing response message from websocket failed", e);
        }
    }

    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        if (this.mWebSocket != null) {
            abort("Websocket exception", t);
        }
        if (!this.mClosed) {
            reconnect();
        }
    }

    public void onOpen(WebSocket webSocket, Response response) {
        this.mWebSocket = webSocket;
        this.mSuppressConnectionErrors = false;
    }

    public void onClosed(WebSocket webSocket, int code, String reason) {
        this.mWebSocket = null;
        if (!this.mClosed) {
            reconnect();
        }
    }

    private void abort(String message, Throwable cause) {
        FLog.m1808e(TAG, "Error occurred, shutting down websocket connection: " + message, cause);
        closeWebSocketQuietly();
    }
}
