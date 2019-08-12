package com.facebook.react.devsupport;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.C3704R;
import com.facebook.react.bridge.DefaultNativeModuleCallExceptionHandler;
import com.facebook.react.bridge.Inspector;
import com.facebook.react.bridge.Inspector.Page;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.JavaJSExecutor.Factory;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.ShakeDetector;
import com.facebook.react.common.ShakeDetector.ShakeListener;
import com.facebook.react.common.futures.SimpleSettableFuture;
import com.facebook.react.devsupport.DevServerHelper.BundleDownloadCallback;
import com.facebook.react.devsupport.DevServerHelper.OnServerContentChangeListener;
import com.facebook.react.devsupport.DevServerHelper.PackagerCommandListener;
import com.facebook.react.devsupport.DevServerHelper.PackagerStatusCallback;
import com.facebook.react.devsupport.JSCSamplingProfiler.ProfilerException;
import com.facebook.react.devsupport.StackTraceHelper.StackFrame;
import com.facebook.react.devsupport.WebsocketJavaScriptExecutor.JSExecutorConnectCallback;
import com.facebook.react.modules.debug.DeveloperSettings;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;

public class DevSupportManagerImpl implements PackagerCommandListener, DevSupportManager {
    private static final String EXOPACKAGE_LOCATION_FORMAT = "/data/local/tmp/exopackage/%s//secondary-dex";
    private static final int JAVA_ERROR_COOKIE = -1;
    private static final int JSEXCEPTION_ERROR_COOKIE = -1;
    private static final String JS_BUNDLE_FILE_NAME = "ReactNativeDevBundle.js";
    /* access modifiers changed from: private */
    public final Context mApplicationContext;
    /* access modifiers changed from: private */
    public ReactContext mCurrentContext;
    private final LinkedHashMap<String, DevOptionHandler> mCustomDevOptions;
    private DebugOverlayController mDebugOverlayController;
    private final DefaultNativeModuleCallExceptionHandler mDefaultNativeModuleCallExceptionHandler;
    /* access modifiers changed from: private */
    public AlertDialog mDevOptionsDialog;
    /* access modifiers changed from: private */
    public final DevServerHelper mDevServerHelper;
    /* access modifiers changed from: private */
    public DevInternalSettings mDevSettings;
    private boolean mIsDevSupportEnabled;
    private boolean mIsReceiverRegistered;
    private boolean mIsShakeDetectorStarted;
    private final String mJSAppBundleName;
    private final File mJSBundleTempFile;
    /* access modifiers changed from: private */
    public int mLastErrorCookie;
    private StackFrame[] mLastErrorStack;
    private String mLastErrorTitle;
    private ErrorType mLastErrorType;
    /* access modifiers changed from: private */
    public final ReactInstanceDevCommandsHandler mReactInstanceCommandsHandler;
    /* access modifiers changed from: private */
    public RedBoxDialog mRedBoxDialog;
    /* access modifiers changed from: private */
    public RedBoxHandler mRedBoxHandler;
    private final BroadcastReceiver mReloadAppBroadcastReceiver;
    private final ShakeDetector mShakeDetector;

    private enum ErrorType {
        JS,
        NATIVE
    }

    private static class JscProfileTask extends AsyncTask<String, Void, Void> {
        private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        private final String mSourceUrl;

        private JscProfileTask(String sourceUrl) {
            this.mSourceUrl = sourceUrl;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(String... jsonData) {
            try {
                String jscProfileUrl = Uri.parse(this.mSourceUrl).buildUpon().path("/jsc-profile").query(null).build().toString();
                OkHttpClient client = new OkHttpClient();
                for (String json : jsonData) {
                    client.newCall(new Builder().url(jscProfileUrl).post(RequestBody.create(JSON, json)).build()).execute();
                }
            } catch (IOException e) {
                FLog.m1808e(ReactConstants.TAG, "Failed not talk to server", (Throwable) e);
            }
            return null;
        }
    }

    public DevSupportManagerImpl(Context applicationContext, ReactInstanceDevCommandsHandler reactInstanceCommandsHandler, String packagerPathForJSBundleName, boolean enableOnCreate) {
        this(applicationContext, reactInstanceCommandsHandler, packagerPathForJSBundleName, enableOnCreate, null);
    }

    public DevSupportManagerImpl(Context applicationContext, ReactInstanceDevCommandsHandler reactInstanceCommandsHandler, String packagerPathForJSBundleName, boolean enableOnCreate, RedBoxHandler redBoxHandler) {
        this.mCustomDevOptions = new LinkedHashMap<>();
        this.mIsReceiverRegistered = false;
        this.mIsShakeDetectorStarted = false;
        this.mIsDevSupportEnabled = false;
        this.mLastErrorCookie = 0;
        this.mReactInstanceCommandsHandler = reactInstanceCommandsHandler;
        this.mApplicationContext = applicationContext;
        this.mJSAppBundleName = packagerPathForJSBundleName;
        this.mDevSettings = new DevInternalSettings(applicationContext, this);
        this.mDevServerHelper = new DevServerHelper(this.mDevSettings);
        this.mShakeDetector = new ShakeDetector(new ShakeListener() {
            public void onShake() {
                DevSupportManagerImpl.this.showDevOptionsDialog();
            }
        });
        this.mReloadAppBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (DevServerHelper.getReloadAppAction(context).equals(intent.getAction())) {
                    if (intent.getBooleanExtra(DevServerHelper.RELOAD_APP_EXTRA_JS_PROXY, false)) {
                        DevSupportManagerImpl.this.mDevSettings.setRemoteJSDebugEnabled(true);
                        DevSupportManagerImpl.this.mDevServerHelper.launchJSDevtools();
                    } else {
                        DevSupportManagerImpl.this.mDevSettings.setRemoteJSDebugEnabled(false);
                    }
                    DevSupportManagerImpl.this.handleReloadJS();
                }
            }
        };
        this.mJSBundleTempFile = new File(applicationContext.getFilesDir(), JS_BUNDLE_FILE_NAME);
        this.mDefaultNativeModuleCallExceptionHandler = new DefaultNativeModuleCallExceptionHandler();
        setDevSupportEnabled(enableOnCreate);
        this.mRedBoxHandler = redBoxHandler;
    }

    public void handleException(Exception e) {
        if (!this.mIsDevSupportEnabled) {
            this.mDefaultNativeModuleCallExceptionHandler.handleException(e);
        } else if (e instanceof JSException) {
            FLog.m1808e(ReactConstants.TAG, "Exception in native call from JS", (Throwable) e);
            showNewError(e.getMessage() + "\n\n" + ((JSException) e).getStack(), new StackFrame[0], -1, ErrorType.JS);
        } else {
            showNewJavaError(e.getMessage(), e);
        }
    }

    public void showNewJavaError(String message, Throwable e) {
        FLog.m1808e(ReactConstants.TAG, "Exception in native call", e);
        showNewError(message, StackTraceHelper.convertJavaStackTrace(e), -1, ErrorType.NATIVE);
    }

    public void addCustomDevOption(String optionName, DevOptionHandler optionHandler) {
        this.mCustomDevOptions.put(optionName, optionHandler);
    }

    public void showNewJSError(String message, ReadableArray details, int errorCookie) {
        showNewError(message, StackTraceHelper.convertJsStackTrace(details), errorCookie, ErrorType.JS);
    }

    public void updateJSError(final String message, final ReadableArray details, final int errorCookie) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                if (DevSupportManagerImpl.this.mRedBoxDialog != null && DevSupportManagerImpl.this.mRedBoxDialog.isShowing() && errorCookie == DevSupportManagerImpl.this.mLastErrorCookie) {
                    StackFrame[] stack = StackTraceHelper.convertJsStackTrace(details);
                    DevSupportManagerImpl.this.mRedBoxDialog.setExceptionDetails(message, stack);
                    DevSupportManagerImpl.this.updateLastErrorInfo(message, stack, errorCookie, ErrorType.JS);
                    if (DevSupportManagerImpl.this.mRedBoxHandler != null) {
                        DevSupportManagerImpl.this.mRedBoxHandler.handleRedbox(message, stack, com.facebook.react.devsupport.RedBoxHandler.ErrorType.JS);
                        DevSupportManagerImpl.this.mRedBoxDialog.resetReporting(true);
                    }
                    DevSupportManagerImpl.this.mRedBoxDialog.show();
                }
            }
        });
    }

    public void hideRedboxDialog() {
        if (this.mRedBoxDialog != null) {
            this.mRedBoxDialog.dismiss();
        }
    }

    private void showNewError(String message, StackFrame[] stack, int errorCookie, ErrorType errorType) {
        final String str = message;
        final StackFrame[] stackFrameArr = stack;
        final int i = errorCookie;
        final ErrorType errorType2 = errorType;
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                if (DevSupportManagerImpl.this.mRedBoxDialog == null) {
                    DevSupportManagerImpl.this.mRedBoxDialog = new RedBoxDialog(DevSupportManagerImpl.this.mApplicationContext, DevSupportManagerImpl.this, DevSupportManagerImpl.this.mRedBoxHandler);
                    DevSupportManagerImpl.this.mRedBoxDialog.getWindow().setType(2003);
                }
                if (!DevSupportManagerImpl.this.mRedBoxDialog.isShowing()) {
                    DevSupportManagerImpl.this.mRedBoxDialog.setExceptionDetails(str, stackFrameArr);
                    DevSupportManagerImpl.this.updateLastErrorInfo(str, stackFrameArr, i, errorType2);
                    if (DevSupportManagerImpl.this.mRedBoxHandler == null || errorType2 != ErrorType.NATIVE) {
                        DevSupportManagerImpl.this.mRedBoxDialog.resetReporting(false);
                    } else {
                        DevSupportManagerImpl.this.mRedBoxHandler.handleRedbox(str, stackFrameArr, com.facebook.react.devsupport.RedBoxHandler.ErrorType.NATIVE);
                        DevSupportManagerImpl.this.mRedBoxDialog.resetReporting(true);
                    }
                    DevSupportManagerImpl.this.mRedBoxDialog.show();
                }
            }
        });
    }

    public void showDevOptionsDialog() {
        String string;
        String string2;
        String string3;
        String string4;
        if (this.mDevOptionsDialog == null && this.mIsDevSupportEnabled && !ActivityManager.isUserAMonkey()) {
            LinkedHashMap<String, DevOptionHandler> options = new LinkedHashMap<>();
            options.put(this.mApplicationContext.getString(C3704R.string.catalyst_reloadjs), new DevOptionHandler() {
                public void onOptionSelected() {
                    DevSupportManagerImpl.this.handleReloadJS();
                }
            });
            if (this.mDevSettings.isRemoteJSDebugEnabled()) {
                string = this.mApplicationContext.getString(C3704R.string.catalyst_debugjs_off);
            } else {
                string = this.mApplicationContext.getString(C3704R.string.catalyst_debugjs);
            }
            options.put(string, new DevOptionHandler() {
                public void onOptionSelected() {
                    DevSupportManagerImpl.this.mDevSettings.setRemoteJSDebugEnabled(!DevSupportManagerImpl.this.mDevSettings.isRemoteJSDebugEnabled());
                    DevSupportManagerImpl.this.handleReloadJS();
                }
            });
            if (Inspector.isSupported()) {
                options.put("Debug JS on-device (experimental)", new DevOptionHandler() {
                    public void onOptionSelected() {
                        List<Page> pages = Inspector.getPages();
                        if (pages.size() > 0) {
                            DevSupportManagerImpl.this.mDevServerHelper.openInspector(String.valueOf(((Page) pages.get(0)).getId()));
                        }
                    }
                });
            }
            if (this.mDevSettings.isReloadOnJSChangeEnabled()) {
                string2 = this.mApplicationContext.getString(C3704R.string.catalyst_live_reload_off);
            } else {
                string2 = this.mApplicationContext.getString(C3704R.string.catalyst_live_reload);
            }
            options.put(string2, new DevOptionHandler() {
                public void onOptionSelected() {
                    DevSupportManagerImpl.this.mDevSettings.setReloadOnJSChangeEnabled(!DevSupportManagerImpl.this.mDevSettings.isReloadOnJSChangeEnabled());
                }
            });
            if (this.mDevSettings.isHotModuleReplacementEnabled()) {
                string3 = this.mApplicationContext.getString(C3704R.string.catalyst_hot_module_replacement_off);
            } else {
                string3 = this.mApplicationContext.getString(C3704R.string.catalyst_hot_module_replacement);
            }
            options.put(string3, new DevOptionHandler() {
                public void onOptionSelected() {
                    DevSupportManagerImpl.this.mDevSettings.setHotModuleReplacementEnabled(!DevSupportManagerImpl.this.mDevSettings.isHotModuleReplacementEnabled());
                    DevSupportManagerImpl.this.handleReloadJS();
                }
            });
            options.put(this.mApplicationContext.getString(C3704R.string.catalyst_element_inspector), new DevOptionHandler() {
                public void onOptionSelected() {
                    DevSupportManagerImpl.this.mDevSettings.setElementInspectorEnabled(!DevSupportManagerImpl.this.mDevSettings.isElementInspectorEnabled());
                    DevSupportManagerImpl.this.mReactInstanceCommandsHandler.toggleElementInspector();
                }
            });
            if (this.mDevSettings.isFpsDebugEnabled()) {
                string4 = this.mApplicationContext.getString(C3704R.string.catalyst_perf_monitor_off);
            } else {
                string4 = this.mApplicationContext.getString(C3704R.string.catalyst_perf_monitor);
            }
            options.put(string4, new DevOptionHandler() {
                public void onOptionSelected() {
                    DevSupportManagerImpl.this.mDevSettings.setFpsDebugEnabled(!DevSupportManagerImpl.this.mDevSettings.isFpsDebugEnabled());
                }
            });
            options.put(this.mApplicationContext.getString(C3704R.string.catalyst_heap_capture), new DevOptionHandler() {
                public void onOptionSelected() {
                    JSCHeapCapture.captureHeap(DevSupportManagerImpl.this.mApplicationContext.getCacheDir().getPath(), JSCHeapUpload.captureCallback(DevSupportManagerImpl.this.mDevServerHelper.getHeapCaptureUploadUrl()));
                }
            });
            options.put(this.mApplicationContext.getString(C3704R.string.catalyst_poke_sampling_profiler), new DevOptionHandler() {
                public void onOptionSelected() {
                    try {
                        for (String result : JSCSamplingProfiler.poke(60000)) {
                            Toast.makeText(DevSupportManagerImpl.this.mCurrentContext, result == null ? "Started JSC Sampling Profiler" : "Stopped JSC Sampling Profiler", 1).show();
                            if (result != null) {
                                new JscProfileTask(DevSupportManagerImpl.this.getSourceUrl()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{result});
                            }
                        }
                    } catch (ProfilerException e) {
                        DevSupportManagerImpl.this.showNewJavaError(e.getMessage(), e);
                    }
                }
            });
            options.put(this.mApplicationContext.getString(C3704R.string.catalyst_settings), new DevOptionHandler() {
                public void onOptionSelected() {
                    Intent intent = new Intent(DevSupportManagerImpl.this.mApplicationContext, DevSettingsActivity.class);
                    intent.setFlags(268435456);
                    DevSupportManagerImpl.this.mApplicationContext.startActivity(intent);
                }
            });
            if (this.mCustomDevOptions.size() > 0) {
                options.putAll(this.mCustomDevOptions);
            }
            final DevOptionHandler[] optionHandlers = (DevOptionHandler[]) options.values().toArray(new DevOptionHandler[0]);
            this.mDevOptionsDialog = new AlertDialog.Builder(this.mApplicationContext).setItems((CharSequence[]) options.keySet().toArray(new String[0]), new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    optionHandlers[which].onOptionSelected();
                    DevSupportManagerImpl.this.mDevOptionsDialog = null;
                }
            }).setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    DevSupportManagerImpl.this.mDevOptionsDialog = null;
                }
            }).create();
            this.mDevOptionsDialog.getWindow().setType(2003);
            this.mDevOptionsDialog.show();
        }
    }

    public void setDevSupportEnabled(boolean isDevSupportEnabled) {
        this.mIsDevSupportEnabled = isDevSupportEnabled;
        reload();
    }

    public boolean getDevSupportEnabled() {
        return this.mIsDevSupportEnabled;
    }

    public DeveloperSettings getDevSettings() {
        return this.mDevSettings;
    }

    public void onNewReactContextCreated(ReactContext reactContext) {
        resetCurrentContext(reactContext);
    }

    public void onReactInstanceDestroyed(ReactContext reactContext) {
        if (reactContext == this.mCurrentContext) {
            resetCurrentContext(null);
        }
    }

    public String getSourceMapUrl() {
        if (this.mJSAppBundleName == null) {
            return "";
        }
        return this.mDevServerHelper.getSourceMapUrl((String) Assertions.assertNotNull(this.mJSAppBundleName));
    }

    public String getSourceUrl() {
        if (this.mJSAppBundleName == null) {
            return "";
        }
        return this.mDevServerHelper.getSourceUrl((String) Assertions.assertNotNull(this.mJSAppBundleName));
    }

    public String getJSBundleURLForRemoteDebugging() {
        return this.mDevServerHelper.getJSBundleURLForRemoteDebugging((String) Assertions.assertNotNull(this.mJSAppBundleName));
    }

    public String getDownloadedJSBundleFile() {
        return this.mJSBundleTempFile.getAbsolutePath();
    }

    public String getHeapCaptureUploadUrl() {
        return this.mDevServerHelper.getHeapCaptureUploadUrl();
    }

    public boolean hasUpToDateJSBundleInCache() {
        if (this.mIsDevSupportEnabled && this.mJSBundleTempFile.exists()) {
            try {
                String packageName = this.mApplicationContext.getPackageName();
                if (this.mJSBundleTempFile.lastModified() > this.mApplicationContext.getPackageManager().getPackageInfo(packageName, 0).lastUpdateTime) {
                    File exopackageDir = new File(String.format(Locale.US, EXOPACKAGE_LOCATION_FORMAT, new Object[]{packageName}));
                    if (!exopackageDir.exists() || this.mJSBundleTempFile.lastModified() > exopackageDir.lastModified()) {
                        return true;
                    }
                    return false;
                }
            } catch (NameNotFoundException e) {
                FLog.m1807e(ReactConstants.TAG, "DevSupport is unable to get current app info");
            }
        }
        return false;
    }

    public boolean hasBundleInAssets(String bundleAssetName) {
        try {
            String[] assets = this.mApplicationContext.getAssets().list("");
            for (String equals : assets) {
                if (equals.equals(bundleAssetName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            FLog.m1807e(ReactConstants.TAG, "Error while loading assets list");
        }
        return false;
    }

    private void resetCurrentContext(ReactContext reactContext) {
        if (this.mCurrentContext != reactContext) {
            this.mCurrentContext = reactContext;
            if (this.mDebugOverlayController != null) {
                this.mDebugOverlayController.setFpsDebugViewVisible(false);
            }
            if (reactContext != null) {
                this.mDebugOverlayController = new DebugOverlayController(reactContext);
            }
            if (this.mDevSettings.isHotModuleReplacementEnabled() && this.mCurrentContext != null) {
                try {
                    URL sourceUrl = new URL(getSourceUrl());
                    ((HMRClient) this.mCurrentContext.getJSModule(HMRClient.class)).enable("android", sourceUrl.getPath().substring(1), sourceUrl.getHost(), sourceUrl.getPort());
                } catch (MalformedURLException e) {
                    showNewJavaError(e.getMessage(), e);
                }
            }
            reloadSettings();
        }
    }

    public void reloadSettings() {
        reload();
    }

    public void handleReloadJS() {
        UiThreadUtil.assertOnUiThread();
        if (this.mRedBoxDialog != null) {
            this.mRedBoxDialog.dismiss();
        }
        if (this.mDevSettings.isRemoteJSDebugEnabled()) {
            reloadJSInProxyMode(showProgressDialog());
        } else {
            reloadJSFromServer(this.mDevServerHelper.getDevServerBundleURL((String) Assertions.assertNotNull(this.mJSAppBundleName)));
        }
    }

    public void isPackagerRunning(PackagerStatusCallback callback) {
        this.mDevServerHelper.isPackagerRunning(callback);
    }

    public File downloadBundleResourceFromUrlSync(String resourceURL, File outputFile) {
        return this.mDevServerHelper.downloadBundleResourceFromUrlSync(resourceURL, outputFile);
    }

    public String getLastErrorTitle() {
        return this.mLastErrorTitle;
    }

    public StackFrame[] getLastErrorStack() {
        return this.mLastErrorStack;
    }

    public void onPackagerReloadCommand() {
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                DevSupportManagerImpl.this.handleReloadJS();
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateLastErrorInfo(String message, StackFrame[] stack, int errorCookie, ErrorType errorType) {
        this.mLastErrorTitle = message;
        this.mLastErrorStack = stack;
        this.mLastErrorCookie = errorCookie;
        this.mLastErrorType = errorType;
    }

    private void reloadJSInProxyMode(final AlertDialog progressDialog) {
        this.mDevServerHelper.launchJSDevtools();
        this.mReactInstanceCommandsHandler.onReloadWithJSDebugger(new Factory() {
            public JavaJSExecutor create() throws Exception {
                WebsocketJavaScriptExecutor executor = new WebsocketJavaScriptExecutor();
                SimpleSettableFuture<Boolean> future = new SimpleSettableFuture<>();
                executor.connect(DevSupportManagerImpl.this.mDevServerHelper.getWebsocketProxyURL(), DevSupportManagerImpl.this.getExecutorConnectCallback(progressDialog, future));
                try {
                    future.get(90, TimeUnit.SECONDS);
                    return executor;
                } catch (ExecutionException e) {
                    throw ((Exception) e.getCause());
                } catch (InterruptedException | TimeoutException e2) {
                    throw new RuntimeException(e2);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public JSExecutorConnectCallback getExecutorConnectCallback(final AlertDialog progressDialog, final SimpleSettableFuture<Boolean> future) {
        return new JSExecutorConnectCallback() {
            public void onSuccess() {
                future.set(Boolean.valueOf(true));
                progressDialog.dismiss();
            }

            public void onFailure(Throwable cause) {
                progressDialog.dismiss();
                FLog.m1808e(ReactConstants.TAG, "Unable to connect to remote debugger", cause);
                future.setException(new IOException(DevSupportManagerImpl.this.mApplicationContext.getString(C3704R.string.catalyst_remotedbg_error), cause));
            }
        };
    }

    private AlertDialog showProgressDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this.mApplicationContext).setTitle(C3704R.string.catalyst_jsload_title).setMessage(this.mApplicationContext.getString(this.mDevSettings.isRemoteJSDebugEnabled() ? C3704R.string.catalyst_remotedbg_message : C3704R.string.catalyst_jsload_message)).create();
        dialog.getWindow().setType(2003);
        dialog.show();
        return dialog;
    }

    public void reloadJSFromServer(String bundleURL) {
        final AlertDialog progressDialog = showProgressDialog();
        this.mDevServerHelper.downloadBundleFromURL(new BundleDownloadCallback() {
            public void onSuccess() {
                progressDialog.dismiss();
                UiThreadUtil.runOnUiThread(new Runnable() {
                    public void run() {
                        DevSupportManagerImpl.this.mReactInstanceCommandsHandler.onJSBundleLoadedFromServer();
                    }
                });
            }

            public void onFailure(final Exception cause) {
                progressDialog.dismiss();
                FLog.m1808e(ReactConstants.TAG, "Unable to download JS bundle", (Throwable) cause);
                UiThreadUtil.runOnUiThread(new Runnable() {
                    public void run() {
                        if (cause instanceof DebugServerException) {
                            DevSupportManagerImpl.this.showNewJavaError(((DebugServerException) cause).getMessage(), cause);
                            return;
                        }
                        DevSupportManagerImpl.this.showNewJavaError(DevSupportManagerImpl.this.mApplicationContext.getString(C3704R.string.catalyst_jsload_error), cause);
                    }
                });
            }
        }, this.mJSBundleTempFile, bundleURL);
        progressDialog.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                DevSupportManagerImpl.this.mDevServerHelper.cancelDownloadBundleFromURL();
            }
        });
        progressDialog.setCancelable(true);
    }

    private void reload() {
        if (this.mIsDevSupportEnabled) {
            if (this.mDebugOverlayController != null) {
                this.mDebugOverlayController.setFpsDebugViewVisible(this.mDevSettings.isFpsDebugEnabled());
            }
            if (!this.mIsShakeDetectorStarted) {
                this.mShakeDetector.start((SensorManager) this.mApplicationContext.getSystemService("sensor"));
                this.mIsShakeDetectorStarted = true;
            }
            if (!this.mIsReceiverRegistered) {
                IntentFilter filter = new IntentFilter();
                filter.addAction(DevServerHelper.getReloadAppAction(this.mApplicationContext));
                this.mApplicationContext.registerReceiver(this.mReloadAppBroadcastReceiver, filter);
                this.mIsReceiverRegistered = true;
            }
            this.mDevServerHelper.openPackagerConnection(this);
            this.mDevServerHelper.openInspectorConnection();
            if (this.mDevSettings.isReloadOnJSChangeEnabled()) {
                this.mDevServerHelper.startPollingOnChangeEndpoint(new OnServerContentChangeListener() {
                    public void onServerContentChanged() {
                        DevSupportManagerImpl.this.handleReloadJS();
                    }
                });
            } else {
                this.mDevServerHelper.stopPollingOnChangeEndpoint();
            }
        } else {
            if (this.mDebugOverlayController != null) {
                this.mDebugOverlayController.setFpsDebugViewVisible(false);
            }
            if (this.mIsShakeDetectorStarted) {
                this.mShakeDetector.stop();
                this.mIsShakeDetectorStarted = false;
            }
            if (this.mIsReceiverRegistered) {
                this.mApplicationContext.unregisterReceiver(this.mReloadAppBroadcastReceiver);
                this.mIsReceiverRegistered = false;
            }
            if (this.mRedBoxDialog != null) {
                this.mRedBoxDialog.dismiss();
            }
            if (this.mDevOptionsDialog != null) {
                this.mDevOptionsDialog.dismiss();
            }
            this.mDevServerHelper.closePackagerConnection();
            this.mDevServerHelper.closeInspectorConnection();
            this.mDevServerHelper.stopPollingOnChangeEndpoint();
        }
    }
}
