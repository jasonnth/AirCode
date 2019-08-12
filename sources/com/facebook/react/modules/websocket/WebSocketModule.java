package com.facebook.react.modules.websocket;

import com.airbnb.android.lib.cancellation.CancellationAnalytics;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

@ReactModule(name = "WebSocketModule")
public class WebSocketModule extends ReactContextBaseJavaModule {
    private ForwardingCookieHandler mCookieHandler;
    private ReactContext mReactContext;
    /* access modifiers changed from: private */
    public final Map<Integer, WebSocket> mWebSocketConnections = new HashMap();

    public WebSocketModule(ReactApplicationContext context) {
        super(context);
        this.mReactContext = context;
        this.mCookieHandler = new ForwardingCookieHandler(context);
    }

    /* access modifiers changed from: private */
    public void sendEvent(String eventName, WritableMap params) {
        ((RCTDeviceEventEmitter) this.mReactContext.getJSModule(RCTDeviceEventEmitter.class)).emit(eventName, params);
    }

    public String getName() {
        return "WebSocketModule";
    }

    @ReactMethod
    public void connect(String url, ReadableArray protocols, ReadableMap headers, int id) {
        OkHttpClient client = new Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(0, TimeUnit.MINUTES).build();
        Request.Builder builder = new Request.Builder().tag(Integer.valueOf(id)).url(url);
        String cookie = getCookie(url);
        if (cookie != null) {
            builder.addHeader("Cookie", cookie);
        }
        if (headers != null) {
            ReadableMapKeySetIterator iterator = headers.keySetIterator();
            if (!headers.hasKey("origin")) {
                builder.addHeader("origin", getDefaultOrigin(url));
            }
            while (iterator.hasNextKey()) {
                String key = iterator.nextKey();
                if (ReadableType.String.equals(headers.getType(key))) {
                    builder.addHeader(key, headers.getString(key));
                } else {
                    FLog.m1847w(ReactConstants.TAG, "Ignoring: requested " + key + ", value not a string");
                }
            }
        } else {
            builder.addHeader("origin", getDefaultOrigin(url));
        }
        if (protocols != null && protocols.size() > 0) {
            StringBuilder protocolsValue = new StringBuilder("");
            for (int i = 0; i < protocols.size(); i++) {
                String v = protocols.getString(i).trim();
                if (!v.isEmpty() && !v.contains(",")) {
                    protocolsValue.append(v);
                    protocolsValue.append(",");
                }
            }
            if (protocolsValue.length() > 0) {
                protocolsValue.replace(protocolsValue.length() - 1, protocolsValue.length(), "");
                builder.addHeader("Sec-WebSocket-Protocol", protocolsValue.toString());
            }
        }
        final int i2 = id;
        client.newWebSocket(builder.build(), new WebSocketListener() {
            public void onOpen(WebSocket webSocket, Response response) {
                WebSocketModule.this.mWebSocketConnections.put(Integer.valueOf(i2), webSocket);
                WritableMap params = Arguments.createMap();
                params.putInt("id", i2);
                WebSocketModule.this.sendEvent("websocketOpen", params);
            }

            public void onClosed(WebSocket webSocket, int code, String reason) {
                WritableMap params = Arguments.createMap();
                params.putInt("id", i2);
                params.putInt("code", code);
                params.putString(CancellationAnalytics.VALUE_PAGE_REASON, reason);
                WebSocketModule.this.sendEvent("websocketClosed", params);
            }

            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                WebSocketModule.this.notifyWebSocketFailed(i2, t.getMessage());
            }

            public void onMessage(WebSocket webSocket, String text) {
                WritableMap params = Arguments.createMap();
                params.putInt("id", i2);
                params.putString("data", text);
                params.putString("type", "text");
                WebSocketModule.this.sendEvent("websocketMessage", params);
            }

            public void onMessage(WebSocket webSocket, ByteString bytes) {
                String text = bytes.utf8();
                WritableMap params = Arguments.createMap();
                params.putInt("id", i2);
                params.putString("data", text);
                params.putString("type", "binary");
                WebSocketModule.this.sendEvent("websocketMessage", params);
            }
        });
        client.dispatcher().executorService().shutdown();
    }

    @ReactMethod
    public void close(int code, String reason, int id) {
        WebSocket client = (WebSocket) this.mWebSocketConnections.get(Integer.valueOf(id));
        if (client != null) {
            try {
                client.close(code, reason);
                this.mWebSocketConnections.remove(Integer.valueOf(id));
            } catch (Exception e) {
                FLog.m1808e(ReactConstants.TAG, "Could not close WebSocket connection for id " + id, (Throwable) e);
            }
        }
    }

    @ReactMethod
    public void send(String message, int id) {
        WebSocket client = (WebSocket) this.mWebSocketConnections.get(Integer.valueOf(id));
        if (client == null) {
            throw new RuntimeException("Cannot send a message. Unknown WebSocket id " + id);
        }
        try {
            client.send(message);
        } catch (Exception e) {
            notifyWebSocketFailed(id, e.getMessage());
        }
    }

    @ReactMethod
    public void sendBinary(String base64String, int id) {
        WebSocket client = (WebSocket) this.mWebSocketConnections.get(Integer.valueOf(id));
        if (client == null) {
            throw new RuntimeException("Cannot send a message. Unknown WebSocket id " + id);
        }
        try {
            client.send(ByteString.decodeBase64(base64String));
        } catch (Exception e) {
            notifyWebSocketFailed(id, e.getMessage());
        }
    }

    @ReactMethod
    public void ping(int id) {
        WebSocket client = (WebSocket) this.mWebSocketConnections.get(Integer.valueOf(id));
        if (client == null) {
            throw new RuntimeException("Cannot send a message. Unknown WebSocket id " + id);
        }
        try {
            client.send(ByteString.EMPTY);
        } catch (Exception e) {
            notifyWebSocketFailed(id, e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public void notifyWebSocketFailed(int id, String message) {
        WritableMap params = Arguments.createMap();
        params.putInt("id", id);
        params.putString("message", message);
        sendEvent("websocketFailed", params);
    }

    private static String getDefaultOrigin(String uri) {
        String scheme = "";
        try {
            URI requestURI = new URI(uri);
            if (requestURI.getScheme().equals("wss")) {
                scheme = scheme + UriUtil.HTTPS_SCHEME;
            } else if (requestURI.getScheme().equals("ws")) {
                scheme = scheme + UriUtil.HTTP_SCHEME;
            } else if (requestURI.getScheme().equals(UriUtil.HTTP_SCHEME) || requestURI.getScheme().equals(UriUtil.HTTPS_SCHEME)) {
                scheme = scheme + requestURI.getScheme();
            }
            if (requestURI.getPort() != -1) {
                return String.format("%s://%s:%s", new Object[]{scheme, requestURI.getHost(), Integer.valueOf(requestURI.getPort())});
            }
            return String.format("%s://%s/", new Object[]{scheme, requestURI.getHost()});
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Unable to set " + uri + " as default origin header");
        }
    }

    private String getCookie(String uri) {
        try {
            List<String> cookieList = (List) this.mCookieHandler.get(new URI(getDefaultOrigin(uri)), new HashMap()).get("Cookie");
            if (cookieList == null || cookieList.isEmpty()) {
                return null;
            }
            return (String) cookieList.get(0);
        } catch (IOException | URISyntaxException e) {
            throw new IllegalArgumentException("Unable to get cookie from " + uri);
        }
    }
}
