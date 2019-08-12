package com.facebook.react.devsupport;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.network.OkHttpCallUtil;
import com.facebook.react.devsupport.JSPackagerWebSocketClient.JSPackagerCallback;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Okio;
import okio.Sink;
import okio.Source;

public class DevServerHelper {
    private static final String BUNDLE_URL_FORMAT = "http://%s/%s.bundle?platform=android&dev=%s&hot=%s&minify=%s";
    private static final String HEAP_CAPTURE_UPLOAD_URL_FORMAT = "http://%s/jscheapcaptureupload";
    private static final int HTTP_CONNECT_TIMEOUT_MS = 5000;
    private static final String INSPECTOR_DEVICE_URL_FORMAT = "http://%s/inspector/device?name=%s";
    private static final String LAUNCH_JS_DEVTOOLS_COMMAND_URL_FORMAT = "http://%s/launch-js-devtools";
    private static final int LONG_POLL_FAILURE_DELAY_MS = 5000;
    private static final int LONG_POLL_KEEP_ALIVE_DURATION_MS = 120000;
    private static final String ONCHANGE_ENDPOINT_URL_FORMAT = "http://%s/onchange";
    private static final String PACKAGER_CONNECTION_URL_FORMAT = "ws://%s/message?role=shell";
    private static final String PACKAGER_OK_STATUS = "packager-status:running";
    private static final String PACKAGER_STATUS_URL_FORMAT = "http://%s/status";
    private static final String RELOAD_APP_ACTION_SUFFIX = ".RELOAD_APP_ACTION";
    public static final String RELOAD_APP_EXTRA_JS_PROXY = "jsproxy";
    private static final String RESOURCE_URL_FORMAT = "http://%s/%s";
    private static final String SOURCE_MAP_URL_FORMAT = BUNDLE_URL_FORMAT.replaceFirst("\\.bundle", ".map");
    private static final String WEBSOCKET_PROXY_URL_FORMAT = "ws://%s/debugger-proxy?role=client";
    private final OkHttpClient mClient = new Builder().connectTimeout(5000, TimeUnit.MILLISECONDS).readTimeout(0, TimeUnit.MILLISECONDS).writeTimeout(0, TimeUnit.MILLISECONDS).build();
    /* access modifiers changed from: private */
    public Call mDownloadBundleFromURLCall;
    /* access modifiers changed from: private */
    public InspectorPackagerConnection mInspectorPackagerConnection;
    private OkHttpClient mOnChangePollingClient;
    /* access modifiers changed from: private */
    public boolean mOnChangePollingEnabled;
    /* access modifiers changed from: private */
    public OnServerContentChangeListener mOnServerContentChangeListener;
    /* access modifiers changed from: private */
    public JSPackagerWebSocketClient mPackagerConnection;
    /* access modifiers changed from: private */
    public final Handler mRestartOnChangePollingHandler = new Handler();
    private final DevInternalSettings mSettings;

    public interface BundleDownloadCallback {
        void onFailure(Exception exc);

        void onSuccess();
    }

    public interface OnServerContentChangeListener {
        void onServerContentChanged();
    }

    public interface PackagerCommandListener {
        void onPackagerReloadCommand();
    }

    public interface PackagerStatusCallback {
        void onPackagerStatusFetched(boolean z);
    }

    public DevServerHelper(DevInternalSettings settings) {
        this.mSettings = settings;
    }

    public void openPackagerConnection(final PackagerCommandListener commandListener) {
        if (this.mPackagerConnection != null) {
            FLog.m1847w(ReactConstants.TAG, "Packager connection already open, nooping.");
        } else {
            new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                public Void doInBackground(Void... params) {
                    DevServerHelper.this.mPackagerConnection = new JSPackagerWebSocketClient(DevServerHelper.this.getPackagerConnectionURL(), new JSPackagerCallback() {
                        public void onMessage(String target, String action) {
                            if (commandListener != null && "bridge".equals(target) && "reload".equals(action)) {
                                commandListener.onPackagerReloadCommand();
                            }
                        }
                    });
                    DevServerHelper.this.mPackagerConnection.connect();
                    return null;
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void closePackagerConnection() {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... params) {
                if (DevServerHelper.this.mPackagerConnection != null) {
                    DevServerHelper.this.mPackagerConnection.closeQuietly();
                    DevServerHelper.this.mPackagerConnection = null;
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void openInspectorConnection() {
        if (this.mInspectorPackagerConnection != null) {
            FLog.m1847w(ReactConstants.TAG, "Inspector connection already open, nooping.");
        } else {
            new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                public Void doInBackground(Void... params) {
                    DevServerHelper.this.mInspectorPackagerConnection = new InspectorPackagerConnection(DevServerHelper.this.getInspectorDeviceUrl());
                    DevServerHelper.this.mInspectorPackagerConnection.connect();
                    return null;
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void openInspector(String id) {
        if (this.mInspectorPackagerConnection != null) {
            this.mInspectorPackagerConnection.sendOpenEvent(id);
        }
    }

    public void closeInspectorConnection() {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... params) {
                if (DevServerHelper.this.mInspectorPackagerConnection != null) {
                    DevServerHelper.this.mInspectorPackagerConnection.closeQuietly();
                    DevServerHelper.this.mInspectorPackagerConnection = null;
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public static String getReloadAppAction(Context context) {
        return context.getPackageName() + RELOAD_APP_ACTION_SUFFIX;
    }

    public String getWebsocketProxyURL() {
        return String.format(Locale.US, WEBSOCKET_PROXY_URL_FORMAT, new Object[]{getDebugServerHost()});
    }

    /* access modifiers changed from: private */
    public String getPackagerConnectionURL() {
        return String.format(Locale.US, PACKAGER_CONNECTION_URL_FORMAT, new Object[]{getDebugServerHost()});
    }

    public String getHeapCaptureUploadUrl() {
        return String.format(Locale.US, HEAP_CAPTURE_UPLOAD_URL_FORMAT, new Object[]{getDebugServerHost()});
    }

    public String getInspectorDeviceUrl() {
        return String.format(Locale.US, INSPECTOR_DEVICE_URL_FORMAT, new Object[]{getDebugServerHost(), AndroidInfoHelpers.getFriendlyDeviceName()});
    }

    private static String getHostForJSProxy() {
        return AndroidInfoHelpers.DEVICE_LOCALHOST;
    }

    private boolean getDevMode() {
        return this.mSettings.isJSDevModeEnabled();
    }

    private boolean getJSMinifyMode() {
        return this.mSettings.isJSMinifyEnabled();
    }

    private boolean getHMR() {
        return this.mSettings.isHotModuleReplacementEnabled();
    }

    private String getDebugServerHost() {
        String hostFromSettings = this.mSettings.getDebugServerHost();
        if (!TextUtils.isEmpty(hostFromSettings)) {
            return (String) Assertions.assertNotNull(hostFromSettings);
        }
        String host = AndroidInfoHelpers.getServerHost();
        if (host.equals(AndroidInfoHelpers.DEVICE_LOCALHOST)) {
            FLog.m1847w(ReactConstants.TAG, "You seem to be running on device. Run 'adb reverse tcp:8081 tcp:8081' to forward the debug server's port to the device.");
        }
        return host;
    }

    private static String createBundleURL(String host, String jsModulePath, boolean devMode, boolean hmr, boolean jsMinify) {
        return String.format(Locale.US, BUNDLE_URL_FORMAT, new Object[]{host, jsModulePath, Boolean.valueOf(devMode), Boolean.valueOf(hmr), Boolean.valueOf(jsMinify)});
    }

    private static String createResourceURL(String host, String resourcePath) {
        return String.format(Locale.US, RESOURCE_URL_FORMAT, new Object[]{host, resourcePath});
    }

    public String getDevServerBundleURL(String jsModulePath) {
        return createBundleURL(getDebugServerHost(), jsModulePath, getDevMode(), getHMR(), getJSMinifyMode());
    }

    public void downloadBundleFromURL(final BundleDownloadCallback callback, final File outputFile, String bundleURL) {
        this.mDownloadBundleFromURLCall = (Call) Assertions.assertNotNull(this.mClient.newCall(new Request.Builder().url(bundleURL).build()));
        this.mDownloadBundleFromURLCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                if (DevServerHelper.this.mDownloadBundleFromURLCall == null || DevServerHelper.this.mDownloadBundleFromURLCall.isCanceled()) {
                    DevServerHelper.this.mDownloadBundleFromURLCall = null;
                    return;
                }
                DevServerHelper.this.mDownloadBundleFromURLCall = null;
                callback.onFailure(DebugServerException.makeGeneric("Could not connect to development server.", "URL: " + call.request().url().toString(), e));
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (DevServerHelper.this.mDownloadBundleFromURLCall == null || DevServerHelper.this.mDownloadBundleFromURLCall.isCanceled()) {
                    DevServerHelper.this.mDownloadBundleFromURLCall = null;
                    return;
                }
                DevServerHelper.this.mDownloadBundleFromURLCall = null;
                if (!response.isSuccessful()) {
                    String body = response.body().string();
                    DebugServerException debugServerException = DebugServerException.parse(body);
                    if (debugServerException != null) {
                        callback.onFailure(debugServerException);
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("The development server returned response error code: ").append(response.code()).append("\n\n").append("URL: ").append(call.request().url().toString()).append("\n\n").append("Body:\n").append(body);
                    callback.onFailure(new DebugServerException(sb.toString()));
                    return;
                }
                Sink output = null;
                try {
                    output = Okio.sink(outputFile);
                    Okio.buffer((Source) response.body().source()).readAll(output);
                    callback.onSuccess();
                } finally {
                    if (output != null) {
                        output.close();
                    }
                }
            }
        });
    }

    public void cancelDownloadBundleFromURL() {
        if (this.mDownloadBundleFromURLCall != null) {
            this.mDownloadBundleFromURLCall.cancel();
            this.mDownloadBundleFromURLCall = null;
        }
    }

    public void isPackagerRunning(final PackagerStatusCallback callback) {
        this.mClient.newCall(new Request.Builder().url(createPackagerStatusURL(getDebugServerHost())).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                FLog.m1847w(ReactConstants.TAG, "The packager does not seem to be running as we got an IOException requesting its status: " + e.getMessage());
                callback.onPackagerStatusFetched(false);
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    FLog.m1807e(ReactConstants.TAG, "Got non-success http code from packager when requesting status: " + response.code());
                    callback.onPackagerStatusFetched(false);
                    return;
                }
                ResponseBody body = response.body();
                if (body == null) {
                    FLog.m1807e(ReactConstants.TAG, "Got null body response from packager when requesting status");
                    callback.onPackagerStatusFetched(false);
                } else if (!DevServerHelper.PACKAGER_OK_STATUS.equals(body.string())) {
                    FLog.m1807e(ReactConstants.TAG, "Got unexpected response from packager when requesting status: " + body.string());
                    callback.onPackagerStatusFetched(false);
                } else {
                    callback.onPackagerStatusFetched(true);
                }
            }
        });
    }

    private static String createPackagerStatusURL(String host) {
        return String.format(Locale.US, PACKAGER_STATUS_URL_FORMAT, new Object[]{host});
    }

    public void stopPollingOnChangeEndpoint() {
        this.mOnChangePollingEnabled = false;
        this.mRestartOnChangePollingHandler.removeCallbacksAndMessages(null);
        if (this.mOnChangePollingClient != null) {
            OkHttpCallUtil.cancelTag(this.mOnChangePollingClient, this);
            this.mOnChangePollingClient = null;
        }
        this.mOnServerContentChangeListener = null;
    }

    public void startPollingOnChangeEndpoint(OnServerContentChangeListener onServerContentChangeListener) {
        if (!this.mOnChangePollingEnabled) {
            this.mOnChangePollingEnabled = true;
            this.mOnServerContentChangeListener = onServerContentChangeListener;
            this.mOnChangePollingClient = new Builder().connectionPool(new ConnectionPool(1, 120000, TimeUnit.MINUTES)).connectTimeout(5000, TimeUnit.MILLISECONDS).build();
            enqueueOnChangeEndpointLongPolling();
        }
    }

    /* access modifiers changed from: private */
    public void handleOnChangePollingResponse(boolean didServerContentChanged) {
        if (this.mOnChangePollingEnabled) {
            if (didServerContentChanged) {
                UiThreadUtil.runOnUiThread(new Runnable() {
                    public void run() {
                        if (DevServerHelper.this.mOnServerContentChangeListener != null) {
                            DevServerHelper.this.mOnServerContentChangeListener.onServerContentChanged();
                        }
                    }
                });
            }
            enqueueOnChangeEndpointLongPolling();
        }
    }

    private void enqueueOnChangeEndpointLongPolling() {
        ((OkHttpClient) Assertions.assertNotNull(this.mOnChangePollingClient)).newCall(new Request.Builder().url(createOnChangeEndpointUrl()).tag(this).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                if (DevServerHelper.this.mOnChangePollingEnabled) {
                    FLog.m1800d(ReactConstants.TAG, "Error while requesting /onchange endpoint", (Throwable) e);
                    DevServerHelper.this.mRestartOnChangePollingHandler.postDelayed(new Runnable() {
                        public void run() {
                            DevServerHelper.this.handleOnChangePollingResponse(false);
                        }
                    }, 5000);
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                DevServerHelper.this.handleOnChangePollingResponse(response.code() == 205);
            }
        });
    }

    private String createOnChangeEndpointUrl() {
        return String.format(Locale.US, ONCHANGE_ENDPOINT_URL_FORMAT, new Object[]{getDebugServerHost()});
    }

    private String createLaunchJSDevtoolsCommandUrl() {
        return String.format(Locale.US, LAUNCH_JS_DEVTOOLS_COMMAND_URL_FORMAT, new Object[]{getDebugServerHost()});
    }

    public void launchJSDevtools() {
        this.mClient.newCall(new Request.Builder().url(createLaunchJSDevtoolsCommandUrl()).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
            }

            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }

    public String getSourceMapUrl(String mainModuleName) {
        return String.format(Locale.US, SOURCE_MAP_URL_FORMAT, new Object[]{getDebugServerHost(), mainModuleName, Boolean.valueOf(getDevMode()), Boolean.valueOf(getHMR()), Boolean.valueOf(getJSMinifyMode())});
    }

    public String getSourceUrl(String mainModuleName) {
        return String.format(Locale.US, BUNDLE_URL_FORMAT, new Object[]{getDebugServerHost(), mainModuleName, Boolean.valueOf(getDevMode()), Boolean.valueOf(getHMR()), Boolean.valueOf(getJSMinifyMode())});
    }

    public String getJSBundleURLForRemoteDebugging(String mainModuleName) {
        return createBundleURL(getHostForJSProxy(), mainModuleName, getDevMode(), getHMR(), getJSMinifyMode());
    }

    public File downloadBundleResourceFromUrlSync(String resourcePath, File outputFile) {
        Sink output;
        try {
            Response response = this.mClient.newCall(new Request.Builder().url(createResourceURL(getDebugServerHost(), resourcePath)).build()).execute();
            if (!response.isSuccessful()) {
                return null;
            }
            output = null;
            output = Okio.sink(outputFile);
            Okio.buffer((Source) response.body().source()).readAll(output);
            if (output == null) {
                return outputFile;
            }
            output.close();
            return outputFile;
        } catch (Exception ex) {
            FLog.m1809e(ReactConstants.TAG, "Failed to fetch resource synchronously - resourcePath: \"%s\", outputFile: \"%s\"", resourcePath, outputFile.getAbsolutePath(), ex);
            return null;
        } catch (Throwable th) {
            if (output != null) {
                output.close();
            }
            throw th;
        }
    }
}
