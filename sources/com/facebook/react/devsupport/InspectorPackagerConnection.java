package com.facebook.react.devsupport;

import android.os.Handler;
import android.os.Looper;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Inspector;
import com.facebook.react.bridge.Inspector.LocalConnection;
import com.facebook.react.bridge.Inspector.Page;
import com.facebook.react.bridge.Inspector.RemoteConnection;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InspectorPackagerConnection {
    private static final String TAG = "InspectorPackagerConnection";
    private final Connection mConnection;
    /* access modifiers changed from: private */
    public final Map<String, LocalConnection> mInspectorConnections = new HashMap();

    private class Connection extends WebSocketListener {
        private static final int RECONNECT_DELAY_MS = 2000;
        /* access modifiers changed from: private */
        public boolean mClosed;
        private final Handler mHandler = new Handler(Looper.getMainLooper());
        private boolean mSuppressConnectionErrors;
        private final String mUrl;
        private WebSocket mWebSocket;

        public Connection(String url) {
            this.mUrl = url;
        }

        public void onOpen(WebSocket webSocket, Response response) {
            this.mWebSocket = webSocket;
        }

        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            if (this.mWebSocket != null) {
                abort("Websocket exception", t);
            }
            if (!this.mClosed) {
                reconnect();
            }
        }

        public void onMessage(WebSocket webSocket, String text) {
            try {
                InspectorPackagerConnection.this.handleProxyMessage(new JSONObject(text));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public void onClosed(WebSocket webSocket, int code, String reason) {
            this.mWebSocket = null;
            InspectorPackagerConnection.this.closeAllConnections();
            if (!this.mClosed) {
                reconnect();
            }
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
                FLog.m1847w(InspectorPackagerConnection.TAG, "Couldn't connect to packager, will silently retry");
                this.mSuppressConnectionErrors = true;
            }
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    if (!Connection.this.mClosed) {
                        Connection.this.connect();
                    }
                }
            }, 2000);
        }

        public void close() {
            this.mClosed = true;
            if (this.mWebSocket != null) {
                try {
                    this.mWebSocket.close(1000, "End of session");
                } catch (Exception e) {
                }
                this.mWebSocket = null;
            }
        }

        public void send(JSONObject object) throws IOException {
            if (this.mWebSocket != null) {
                try {
                    this.mWebSocket.send(object.toString());
                } catch (Exception e) {
                    FLog.m1848w(InspectorPackagerConnection.TAG, "Couldn't send event to packager", (Throwable) e);
                }
            }
        }

        private void abort(String message, Throwable cause) {
            FLog.m1808e(InspectorPackagerConnection.TAG, "Error occurred, shutting down websocket connection: " + message, cause);
            InspectorPackagerConnection.this.closeAllConnections();
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
    }

    public InspectorPackagerConnection(String url) {
        this.mConnection = new Connection(url);
    }

    public void connect() {
        this.mConnection.connect();
    }

    public void closeQuietly() {
        this.mConnection.close();
    }

    public void sendOpenEvent(String pageId) {
        try {
            sendEvent("open", makePageIdPayload(pageId));
        } catch (IOException | JSONException e) {
            FLog.m1808e(TAG, "Failed to open page", (Throwable) e);
        }
    }

    /* access modifiers changed from: 0000 */
    public void handleProxyMessage(JSONObject message) throws JSONException, IOException {
        String event = message.getString("event");
        char c = 65535;
        switch (event.hashCode()) {
            case 530405532:
                if (event.equals("disconnect")) {
                    c = 3;
                    break;
                }
                break;
            case 951351530:
                if (event.equals("connect")) {
                    c = 2;
                    break;
                }
                break;
            case 1328613653:
                if (event.equals("wrappedEvent")) {
                    c = 1;
                    break;
                }
                break;
            case 1962251790:
                if (event.equals("getPages")) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                sendEvent("getPages", getPages());
                return;
            case 1:
                handleWrappedEvent(message.getJSONObject("payload"));
                return;
            case 2:
                handleConnect(message.getJSONObject("payload"));
                return;
            case 3:
                handleDisconnect(message.getJSONObject("payload"));
                return;
            default:
                throw new IllegalArgumentException("Unknown event: " + event);
        }
    }

    /* access modifiers changed from: 0000 */
    public void closeAllConnections() {
        for (Entry<String, LocalConnection> entry : this.mInspectorConnections.entrySet()) {
            ((LocalConnection) entry.getValue()).disconnect();
        }
        this.mInspectorConnections.clear();
    }

    private void handleConnect(JSONObject payload) throws JSONException, IOException {
        final String pageId = payload.getString("pageId");
        if (((LocalConnection) this.mInspectorConnections.remove(pageId)) != null) {
            throw new IllegalStateException("Already connected: " + pageId);
        }
        try {
            this.mInspectorConnections.put(pageId, Inspector.connect(Integer.parseInt(pageId), new RemoteConnection() {
                public void onMessage(String message) {
                    try {
                        InspectorPackagerConnection.this.sendWrappedEvent(pageId, message);
                    } catch (IOException | JSONException e) {
                        FLog.m1848w(InspectorPackagerConnection.TAG, "Couldn't send event to packager", (Throwable) e);
                    }
                }

                public void onDisconnect() {
                    try {
                        InspectorPackagerConnection.this.mInspectorConnections.remove(pageId);
                        InspectorPackagerConnection.this.sendEvent("disconnect", InspectorPackagerConnection.this.makePageIdPayload(pageId));
                    } catch (IOException | JSONException e) {
                        FLog.m1848w(InspectorPackagerConnection.TAG, "Couldn't send event to packager", (Throwable) e);
                    }
                }
            }));
        } catch (Exception e) {
            FLog.m1848w(TAG, "Failed to open page: " + pageId, (Throwable) e);
            sendEvent("disconnect", makePageIdPayload(pageId));
        }
    }

    private void handleDisconnect(JSONObject payload) throws JSONException {
        LocalConnection inspectorConnection = (LocalConnection) this.mInspectorConnections.remove(payload.getString("pageId"));
        if (inspectorConnection != null) {
            inspectorConnection.disconnect();
        }
    }

    private void handleWrappedEvent(JSONObject payload) throws JSONException, IOException {
        String pageId = payload.getString("pageId");
        String wrappedEvent = payload.getString("wrappedEvent");
        LocalConnection inspectorConnection = (LocalConnection) this.mInspectorConnections.get(pageId);
        if (inspectorConnection == null) {
            throw new IllegalStateException("Not connected: " + pageId);
        }
        inspectorConnection.sendMessage(wrappedEvent);
    }

    private JSONArray getPages() throws JSONException {
        List<Page> pages = Inspector.getPages();
        JSONArray array = new JSONArray();
        for (Page page : pages) {
            JSONObject jsonPage = new JSONObject();
            jsonPage.put("id", String.valueOf(page.getId()));
            jsonPage.put("title", page.getTitle());
            array.put(jsonPage);
        }
        return array;
    }

    /* access modifiers changed from: private */
    public void sendWrappedEvent(String pageId, String message) throws IOException, JSONException {
        JSONObject payload = new JSONObject();
        payload.put("pageId", pageId);
        payload.put("wrappedEvent", message);
        sendEvent("wrappedEvent", payload);
    }

    /* access modifiers changed from: private */
    public void sendEvent(String name, Object payload) throws JSONException, IOException {
        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put("event", name);
        jsonMessage.put("payload", payload);
        this.mConnection.send(jsonMessage);
    }

    /* access modifiers changed from: private */
    public JSONObject makePageIdPayload(String pageId) throws JSONException {
        JSONObject payload = new JSONObject();
        payload.put("pageId", pageId);
        return payload;
    }
}
