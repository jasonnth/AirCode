package com.kount.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebView;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;

public class DataCollector {
    private static DataCollector instance = null;
    /* access modifiers changed from: private */
    public static final Object lock = new Object();
    /* access modifiers changed from: private */
    public Context context;
    private boolean debug;
    /* access modifiers changed from: private */
    public int environment;
    private ExecutorService executor;
    /* access modifiers changed from: private */
    public LocationConfig locationConfig;
    /* access modifiers changed from: private */
    public int merchantID;
    /* access modifiers changed from: private */
    public int timeoutInMS;
    protected String urlString;
    /* access modifiers changed from: private */
    public WebView webView;
    protected boolean webViewCreated;

    public interface CompletionHandler {
        void completed(String str);

        void failed(String str, Error error);
    }

    public enum Error {
        NO_NETWORK(0, "No network"),
        INVALID_ENVIRONMENT(1, "Invalid Environment"),
        INVALID_MERCHANT(2, "Invalid Merchant ID"),
        INVALID_SESSION(3, "Invalid Session ID"),
        RUNTIME_FAILURE(4, "Runtime Failure"),
        VALIDATION_FAILURE(5, "Validation Failure"),
        TIMEOUT(6, "Timeout"),
        CONTEXT_NOT_SET(7, "Context Not Set");
        
        private final int code;
        private final String description;

        private Error(int code2, String description2) {
            this.code = code2;
            this.description = description2;
        }

        public String toString() {
            return this.code + ": " + this.description;
        }
    }

    public enum LocationConfig {
        COLLECT,
        SKIP
    }

    public void setContext(Context context2) {
        this.context = context2;
        createWebView(context2);
    }

    /* access modifiers changed from: protected */
    public void createWebView(final Context context2) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                synchronized (DataCollector.lock) {
                    DataCollector.this.webView = new WebView(context2);
                    DataCollector.this.webViewCreated = true;
                }
            }
        });
    }

    public void setMerchantID(int merchantID2) {
        debugMessage(null, String.format(Locale.US, "Setting Merchant ID to %d.", new Object[]{Integer.valueOf(merchantID2)}));
        this.merchantID = merchantID2;
    }

    public void setEnvironment(int env) {
        switch (env) {
            case 1:
                debugMessage(null, "Setting Environment to Test");
                setURL("https://tst.kaptcha.com/logo.htm");
                break;
            case 2:
                debugMessage(null, "Setting Environment to Production");
                setURL("https://ssl.kaptcha.com/logo.htm");
                break;
            case 999999:
                debugMessage(null, "Setting Environment to QA");
                setURL("https://mqa.kaptcha.com/logo.htm");
                break;
            default:
                this.environment = 0;
                debugMessage(null, "Invalid Environment");
                this.urlString = null;
                return;
        }
        this.environment = env;
    }

    public void setLocationCollectorConfig(LocationConfig config) {
        switch (config) {
            case COLLECT:
                debugMessage(null, "Location collection enabled.");
                break;
            case SKIP:
                debugMessage(null, "Skipping location collection.");
                break;
        }
        this.locationConfig = config;
    }

    protected DataCollector() {
        this.environment = 0;
        this.timeoutInMS = JPushConstants.DURATION_HEARTBEAT_AFTER_LOGGEDIN;
        this.locationConfig = LocationConfig.COLLECT;
        this.context = null;
        this.webViewCreated = false;
        this.executor = null;
        this.executor = Executors.newSingleThreadExecutor();
    }

    /* access modifiers changed from: protected */
    public void setURL(String urlString2) {
        debugMessage(null, String.format("Setting CollectionURL to %s.", new Object[]{urlString2}));
        this.urlString = urlString2;
    }

    /* access modifiers changed from: private */
    public void debugMessage(Object debugHandler, String message) {
        if (this.debug) {
            Log.d("DataCollector", String.format("<Data Collector> %s", new Object[]{message}));
        }
        if (debugHandler != null) {
            try {
                Method method = debugHandler.getClass().getDeclaredMethod("collectorDebugMessage", new Class[]{String.class});
                method.setAccessible(true);
                method.invoke(debugHandler, new Object[]{message});
            } catch (Exception e) {
                Log.d("DataCollector", String.format("Exception: %s", new Object[]{e.getMessage()}));
            }
        }
    }

    public static DataCollector getInstance() {
        if (instance == null) {
            instance = new DataCollector();
        }
        return instance;
    }

    public void collectForSession(String sessionID, CompletionHandler handler) {
        collectForSession(sessionID, handler, null);
    }

    /* access modifiers changed from: protected */
    public boolean noNetwork(Context ctx) {
        try {
            NetworkInfo ni = ((ConnectivityManager) ctx.getSystemService("connectivity")).getActiveNetworkInfo();
            if (ni == null || !ni.isConnectedOrConnecting()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /* access modifiers changed from: private */
    public String formatDataForServer(Hashtable<String, String> dataForSubmission, Hashtable<String, String> softErrorsForSubmission, String sessionID) {
        StringBuilder urlString2 = new StringBuilder();
        try {
            urlString2.append(String.format(Locale.US, "%s=%d&%s=%s", new Object[]{URLEncoder.encode(PostKey.MERCHANT_ID.toString(), JPushConstants.ENCODING_UTF_8), Integer.valueOf(this.merchantID), URLEncoder.encode(PostKey.SESSION_ID.toString(), JPushConstants.ENCODING_UTF_8), URLEncoder.encode(sessionID, JPushConstants.ENCODING_UTF_8)}));
            for (String key : dataForSubmission.keySet()) {
                urlString2.append(String.format("&%s=%s", new Object[]{URLEncoder.encode(key, JPushConstants.ENCODING_UTF_8), URLEncoder.encode((String) dataForSubmission.get(key), JPushConstants.ENCODING_UTF_8)}));
            }
            if (softErrorsForSubmission.size() > 0) {
                urlString2.append(String.format(Locale.US, "&%s=%s", new Object[]{URLEncoder.encode(PostKey.SOFT_ERRORS.toString(), JPushConstants.ENCODING_UTF_8), URLEncoder.encode(new JSONObject(softErrorsForSubmission).toString(), JPushConstants.ENCODING_UTF_8)}));
            }
            return urlString2.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void callCompletionHandler(CompletionHandler handler, Object debugHandler, String sessionID, Boolean success, Error error) {
        if (handler != null) {
            final Boolean bool = success;
            final Object obj = debugHandler;
            final String str = sessionID;
            final CompletionHandler completionHandler = handler;
            final Error error2 = error;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (bool.booleanValue()) {
                        DataCollector.this.debugMessage(obj, String.format(Locale.US, "(%s) Collector completed successfully.", new Object[]{str}));
                        completionHandler.completed(str);
                        return;
                    }
                    completionHandler.failed(str, error2);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void sendMobileDataToServer(Object debugHandler, String sessionID, String serverName, String payload) {
        if (serverName != null) {
            HttpsURLConnection urlConnection = null;
            try {
                String urlString2 = serverName + "/m.html";
                debugMessage(debugHandler, String.format(Locale.US, "(%s) Posting data:\n%s", new Object[]{sessionID, payload}));
                URL url = new URL(urlString2);
                urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                OutputStream stream = urlConnection.getOutputStream();
                stream.write(payload.getBytes());
                stream.flush();
                stream.close();
                if (urlConnection.getResponseCode() == 200) {
                    debugMessage(debugHandler, String.format(Locale.US, "(%s) Sent data to %s.", new Object[]{sessionID, url}));
                } else {
                    debugMessage(debugHandler, String.format(Locale.US, "(%s) Failed to send data to %s: Response code: %d", new Object[]{sessionID, url, Integer.valueOf(urlConnection.getResponseCode())}));
                }
                if (urlConnection != null) {
                    try {
                        urlConnection.disconnect();
                    } catch (Exception e) {
                        debugMessage(debugHandler, String.format(Locale.US, "(%s) Exception encountered sending data: %s", new Object[]{sessionID, e.getMessage()}));
                    }
                }
            } catch (Exception e2) {
                debugMessage(debugHandler, String.format(Locale.US, "(%s) Exception encountered sending data: %s", new Object[]{sessionID, e2.getMessage()}));
                if (urlConnection != null) {
                    try {
                        urlConnection.disconnect();
                    } catch (Exception e3) {
                        debugMessage(debugHandler, String.format(Locale.US, "(%s) Exception encountered sending data: %s", new Object[]{sessionID, e3.getMessage()}));
                    }
                }
            } finally {
                if (urlConnection != null) {
                    try {
                        urlConnection.disconnect();
                    } catch (Exception e4) {
                        debugMessage(debugHandler, String.format(Locale.US, "(%s) Exception encountered sending data: %s", new Object[]{sessionID, e4.getMessage()}));
                    }
                }
            }
        } else {
            debugMessage(debugHandler, String.format("(%s) No server URL to send data to, skipping send.", new Object[]{sessionID}));
        }
    }

    /* access modifiers changed from: protected */
    public WebCollector createWebCollector(Object debugHandler) {
        return new WebCollector(debugHandler, this.webView, this.urlString, this.merchantID);
    }

    /* access modifiers changed from: protected */
    public LocationCollector createLocationCollector(Object debugHandler) {
        return new LocationCollector(debugHandler, this.context);
    }

    /* access modifiers changed from: protected */
    public void collectForSession(final String sessionID, final CompletionHandler handler, final Object debugHandler) {
        if (!this.webViewCreated) {
            callCompletionHandler(handler, debugHandler, sessionID, Boolean.valueOf(false), Error.CONTEXT_NOT_SET);
            return;
        }
        debugMessage(debugHandler, String.format(Locale.US, "(%s) Adding to queue", new Object[]{sessionID}));
        this.executor.execute(new Runnable() {
            public void run() {
                Date startTime = new Date();
                DataCollector.this.debugMessage(debugHandler, String.format(Locale.US, "(%s) Starting collection", new Object[]{sessionID}));
                if (DataCollector.this.urlString == null || !DataCollector.this.urlString.matches("^https?://[\\w-]+(\\.[\\w-]+)+(/[^?]*)?$")) {
                    DataCollector.this.callCompletionHandler(handler, debugHandler, sessionID, Boolean.valueOf(false), Error.INVALID_ENVIRONMENT);
                } else if (DataCollector.this.environment != 2 && DataCollector.this.environment != 1 && DataCollector.this.environment != 999999) {
                    DataCollector.this.callCompletionHandler(handler, debugHandler, sessionID, Boolean.valueOf(false), Error.INVALID_ENVIRONMENT);
                } else if (DataCollector.this.merchantID <= 0 || DataCollector.this.merchantID > 999999) {
                    DataCollector.this.callCompletionHandler(handler, debugHandler, sessionID, Boolean.valueOf(false), Error.INVALID_MERCHANT);
                } else if (sessionID == null || !sessionID.matches("^[\\w-]{1,32}$")) {
                    DataCollector.this.callCompletionHandler(handler, debugHandler, sessionID, Boolean.valueOf(false), Error.INVALID_SESSION);
                } else if (DataCollector.this.noNetwork(DataCollector.this.context)) {
                    DataCollector.this.callCompletionHandler(handler, debugHandler, sessionID, Boolean.valueOf(false), Error.NO_NETWORK);
                } else {
                    final Hashtable<String, String> dataForSubmission = new Hashtable<>();
                    final Hashtable<String, String> softErrorsForSubmission = new Hashtable<>();
                    final ArrayList<Error> errors = new ArrayList<>();
                    ThreadPoolExecutor sessionExecutor = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
                    ArrayList<CollectorTaskBase> collectors = new ArrayList<>();
                    WebCollector webCollector = DataCollector.this.createWebCollector(debugHandler);
                    collectors.add(webCollector);
                    if (DataCollector.this.locationConfig == LocationConfig.COLLECT) {
                        collectors.add(DataCollector.this.createLocationCollector(debugHandler));
                    } else {
                        softErrorsForSubmission.put(LocationCollector.internalName(), SoftError.SKIPPED.toString());
                    }
                    collectors.add(new SystemCollector(debugHandler, DataCollector.this.context));
                    collectors.add(new FingerprintCollector(debugHandler, DataCollector.this.context));
                    Iterator it = collectors.iterator();
                    while (it.hasNext()) {
                        final CollectorTaskBase collector = (CollectorTaskBase) it.next();
                        sessionExecutor.execute(new Runnable() {
                            public void run() {
                                final Semaphore semaphore = new Semaphore(1);
                                try {
                                    semaphore.acquire();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                collector.collectForSession(sessionID, new RequestHandler() {
                                    public void completed(Boolean success, Error error, Hashtable<String, String> collectedData, Hashtable<String, String> softErrors) {
                                        synchronized (DataCollector.lock) {
                                            for (String key : collectedData.keySet()) {
                                                dataForSubmission.put(key, collectedData.get(key));
                                            }
                                            for (String key2 : softErrors.keySet()) {
                                                softErrorsForSubmission.put(key2, softErrors.get(key2));
                                            }
                                            if (error != null) {
                                                errors.add(error);
                                            }
                                        }
                                        semaphore.release();
                                    }
                                });
                                try {
                                    semaphore.tryAcquire((long) DataCollector.this.timeoutInMS, TimeUnit.MILLISECONDS);
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        });
                    }
                    sessionExecutor.shutdown();
                    try {
                        sessionExecutor.awaitTermination((long) DataCollector.this.timeoutInMS, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        DataCollector.this.debugMessage(debugHandler, String.format(Locale.US, "(%s) Exception encountered waiting for threads: %s", new Object[]{sessionID, e.getMessage()}));
                    }
                    if (errors.size() > 0) {
                        DataCollector.this.callCompletionHandler(handler, debugHandler, sessionID, Boolean.valueOf(false), (Error) errors.get(0));
                    } else if (webCollector.done) {
                        long elapsed = new Date().getTime() - startTime.getTime();
                        DataCollector.this.debugMessage(debugHandler, String.format(Locale.US, "(%s) Collection time: %d ms.", new Object[]{sessionID, Long.valueOf(elapsed)}));
                        dataForSubmission.put(PostKey.ELAPSED.toString(), Long.toString(elapsed));
                        dataForSubmission.put(PostKey.SDK_TYPE.toString(), "A");
                        dataForSubmission.put(PostKey.SDK_VERSION.toString(), "3.1");
                        String postBodyString = DataCollector.this.formatDataForServer(dataForSubmission, softErrorsForSubmission, sessionID);
                        if (postBodyString != null) {
                            DataCollector.this.sendMobileDataToServer(debugHandler, sessionID, webCollector.calledServerName, postBodyString);
                            DataCollector.this.callCompletionHandler(handler, debugHandler, sessionID, Boolean.valueOf(true), null);
                            return;
                        }
                        DataCollector.this.callCompletionHandler(handler, debugHandler, sessionID, Boolean.valueOf(false), Error.RUNTIME_FAILURE);
                    } else {
                        DataCollector.this.debugMessage(debugHandler, String.format(Locale.US, "(%s) Required collectors did not finish.", new Object[]{sessionID}));
                        DataCollector.this.callCompletionHandler(handler, debugHandler, sessionID, Boolean.valueOf(false), Error.TIMEOUT);
                    }
                }
            }
        });
    }
}
