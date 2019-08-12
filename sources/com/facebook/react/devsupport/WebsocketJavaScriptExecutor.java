package com.facebook.react.devsupport;

import android.os.Handler;
import android.os.Looper;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.JavaJSExecutor.ProxyExecutorException;
import com.facebook.react.devsupport.JSDebuggerWebSocketClient.JSDebuggerCallback;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class WebsocketJavaScriptExecutor implements JavaJSExecutor {
    private static final int CONNECT_RETRY_COUNT = 3;
    private static final long CONNECT_TIMEOUT_MS = 5000;
    private final HashMap<String, String> mInjectedObjects = new HashMap<>();
    /* access modifiers changed from: private */
    public JSDebuggerWebSocketClient mWebSocketClient;

    private static class JSExecutorCallbackFuture implements JSDebuggerCallback {
        private Throwable mCause;
        private String mResponse;
        private final Semaphore mSemaphore;

        private JSExecutorCallbackFuture() {
            this.mSemaphore = new Semaphore(0);
        }

        public void onSuccess(String response) {
            this.mResponse = response;
            this.mSemaphore.release();
        }

        public void onFailure(Throwable cause) {
            this.mCause = cause;
            this.mSemaphore.release();
        }

        public String get() throws Throwable {
            this.mSemaphore.acquire();
            if (this.mCause == null) {
                return this.mResponse;
            }
            throw this.mCause;
        }
    }

    public interface JSExecutorConnectCallback {
        void onFailure(Throwable th);

        void onSuccess();
    }

    public static class WebsocketExecutorTimeoutException extends Exception {
        public WebsocketExecutorTimeoutException(String message) {
            super(message);
        }
    }

    public void connect(final String webSocketServerUrl, final JSExecutorConnectCallback callback) {
        final AtomicInteger retryCount = new AtomicInteger(3);
        connectInternal(webSocketServerUrl, new JSExecutorConnectCallback() {
            public void onSuccess() {
                callback.onSuccess();
            }

            public void onFailure(Throwable cause) {
                if (retryCount.decrementAndGet() <= 0) {
                    callback.onFailure(cause);
                } else {
                    WebsocketJavaScriptExecutor.this.connectInternal(webSocketServerUrl, this);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void connectInternal(String webSocketServerUrl, final JSExecutorConnectCallback callback) {
        final JSDebuggerWebSocketClient client = new JSDebuggerWebSocketClient();
        final Handler timeoutHandler = new Handler(Looper.getMainLooper());
        client.connect(webSocketServerUrl, new JSDebuggerCallback() {
            /* access modifiers changed from: private */
            public boolean didSendResult = false;

            public void onSuccess(String response) {
                client.prepareJSRuntime(new JSDebuggerCallback() {
                    public void onSuccess(String response) {
                        timeoutHandler.removeCallbacksAndMessages(null);
                        WebsocketJavaScriptExecutor.this.mWebSocketClient = client;
                        if (!C37992.this.didSendResult) {
                            callback.onSuccess();
                            C37992.this.didSendResult = true;
                        }
                    }

                    public void onFailure(Throwable cause) {
                        timeoutHandler.removeCallbacksAndMessages(null);
                        if (!C37992.this.didSendResult) {
                            callback.onFailure(cause);
                            C37992.this.didSendResult = true;
                        }
                    }
                });
            }

            public void onFailure(Throwable cause) {
                timeoutHandler.removeCallbacksAndMessages(null);
                if (!this.didSendResult) {
                    callback.onFailure(cause);
                    this.didSendResult = true;
                }
            }
        });
        timeoutHandler.postDelayed(new Runnable() {
            public void run() {
                client.closeQuietly();
                callback.onFailure(new WebsocketExecutorTimeoutException("Timeout while connecting to remote debugger"));
            }
        }, CONNECT_TIMEOUT_MS);
    }

    public void close() {
        if (this.mWebSocketClient != null) {
            this.mWebSocketClient.closeQuietly();
        }
    }

    public void loadApplicationScript(String sourceURL) throws ProxyExecutorException {
        JSExecutorCallbackFuture callback = new JSExecutorCallbackFuture();
        ((JSDebuggerWebSocketClient) Assertions.assertNotNull(this.mWebSocketClient)).loadApplicationScript(sourceURL, this.mInjectedObjects, callback);
        try {
            callback.get();
        } catch (Throwable cause) {
            throw new ProxyExecutorException(cause);
        }
    }

    public String executeJSCall(String methodName, String jsonArgsArray) throws ProxyExecutorException {
        JSExecutorCallbackFuture callback = new JSExecutorCallbackFuture();
        ((JSDebuggerWebSocketClient) Assertions.assertNotNull(this.mWebSocketClient)).executeJSCall(methodName, jsonArgsArray, callback);
        try {
            return callback.get();
        } catch (Throwable cause) {
            throw new ProxyExecutorException(cause);
        }
    }

    public void setGlobalVariable(String propertyName, String jsonEncodedValue) {
        this.mInjectedObjects.put(propertyName, jsonEncodedValue);
    }
}
