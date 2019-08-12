package com.jumio.core.network;

import android.content.Context;
import android.net.ConnectivityManager;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.jumio.commons.log.Log;
import com.jumio.commons.log.Log.LogLevel;
import com.jumio.commons.log.LogUtils;
import com.jumio.commons.remote.exception.UnexpectedResponseException;
import com.jumio.commons.utils.IOUtils;
import com.jumio.core.mvp.model.Publisher;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.network.ale.AleKeyUpdateException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;

public abstract class ApiCall<T> extends Publisher<T> implements Callable<T> {
    private static String mTrackingId;
    public final String TAG = ("Network/" + getClass().getSimpleName());
    private int connectTimeout = 10000;
    protected Context context;
    protected DynamicProvider dynamicProvider;
    protected int ioTimeout = 10000;
    protected EncryptionProvider mEncryptionProvider;
    protected String urlPrefix;
    private String userAgent;

    public interface DynamicProvider {
        EncryptionProvider getEncryptionProvider();
    }

    /* access modifiers changed from: protected */
    public abstract void fillRequest(OutputStream outputStream) throws IOException;

    /* access modifiers changed from: protected */
    public abstract String getBoundary();

    /* access modifiers changed from: protected */
    public abstract String getUri();

    /* access modifiers changed from: protected */
    public abstract T parseResponse(String str);

    /* access modifiers changed from: protected */
    public abstract int prepareRequest() throws Exception;

    public ApiCall(Context context2, DynamicProvider provider, Subscriber<T> callback) {
        init(context2, provider);
        if (callback != null) {
            add(callback);
        }
    }

    protected static synchronized void setTrackingId(String trackingId) {
        synchronized (ApiCall.class) {
            mTrackingId = trackingId;
        }
    }

    protected ApiCall(Context context2, DynamicProvider provider) {
        init(context2, provider);
    }

    private void init(Context context2, DynamicProvider dynamicProvider2) {
        this.context = context2;
        this.dynamicProvider = dynamicProvider2;
    }

    public void configure(String endpoint, String userAgent2) {
        this.urlPrefix = endpoint;
        this.userAgent = userAgent2;
    }

    public T execute() throws SocketTimeoutException, NetworkException, UnexpectedResponseException, SSLException {
        OutputStream outputStream;
        HttpURLConnection connection = null;
        try {
            connection = createClient(this.urlPrefix + getUri());
            this.mEncryptionProvider = this.dynamicProvider.getEncryptionProvider();
            outputStream = null;
            outputStream = this.mEncryptionProvider.createRequest(connection.getOutputStream(), prepareRequest(), getBoundary());
            fillRequest(outputStream);
            outputStream.flush();
            IOUtils.closeQuietly(outputStream);
            Log.m1909d(this.TAG, String.format("Sending request %s", new Object[]{connection.getURL().toString()}));
            if (Log.isLogEnabledForLevel(LogLevel.VERBOSE)) {
                for (Entry<String, List<String>> header : connection.getRequestProperties().entrySet()) {
                    Log.m1924v(this.TAG, "Headers: " + ((String) header.getKey()) + "=" + header.getValue());
                }
            }
            double roundTripTime = ((double) (System.nanoTime() - System.nanoTime())) / 1000000.0d;
            int httpStatus = connection.getResponseCode();
            Log.m1909d(this.TAG, String.format(Locale.ENGLISH, "Response was %d, %s", new Object[]{Integer.valueOf(httpStatus), connection.getResponseMessage()}));
            Log.m1924v(this.TAG, String.format(Locale.ENGLISH, "Received response for %s in %.1fms", new Object[]{connection.getURL().toString(), Double.valueOf(roundTripTime)}));
            String plainTextAnswer = this.mEncryptionProvider.getResponse(httpStatus == 200 ? connection.getInputStream() : connection.getErrorStream());
            responseReceived(httpStatus, connection.getResponseMessage(), (long) roundTripTime, plainTextAnswer);
            if (httpStatus == 200) {
                Log.m1909d(this.TAG, "parsing response");
                Log.m1924v(this.TAG, "Response: " + (plainTextAnswer != null ? plainTextAnswer : "null"));
                T parseResponse = parseResponse(plainTextAnswer);
                if (connection != null) {
                    connection.disconnect();
                }
                return parseResponse;
            }
            throw new UnexpectedResponseException(httpStatus, connection.getResponseMessage());
        } catch (Exception e) {
            if (e instanceof SocketTimeoutException) {
                throw ((SocketTimeoutException) e);
            } else if (e instanceof AleKeyUpdateException) {
                throw ((AleKeyUpdateException) e);
            } else if (e instanceof UnexpectedResponseException) {
                throw ((UnexpectedResponseException) e);
            } else if (e instanceof SSLException) {
                throw ((SSLException) e);
            } else {
                throw new NetworkException(e);
            }
        } catch (Throwable th) {
            if (connection != null) {
                connection.disconnect();
            }
            throw th;
        }
    }

    private HttpURLConnection createClient(String useUrl) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        HttpURLConnection connection;
        URL url = new URL(useUrl);
        if (useUrl.contains("https://")) {
            connection = (HttpsURLConnection) url.openConnection();
            ((HttpsURLConnection) connection).setSSLSocketFactory(new TLSSocketFactory(new TrustManager[]{new JumioTrustManagerV3()}));
        } else {
            connection = (HttpURLConnection) url.openConnection();
        }
        if (getMethod() != null) {
            connection.setRequestMethod(getMethod());
            if (!connection.getRequestMethod().equals(getMethod())) {
                throw new AssertionError();
            }
        }
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setRequestProperty(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE, "application/ale");
        connection.setRequestProperty("Content-Encoding", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
        connection.setRequestProperty("Accept-Encoding", InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("User-Agent", this.userAgent);
        connection.setRequestProperty("X-TrackingId", mTrackingId != null ? mTrackingId : "");
        connection.setConnectTimeout(this.connectTimeout);
        connection.setReadTimeout(this.ioTimeout);
        return connection;
    }

    public final T call() throws Exception {
        T t;
        Log.m1919i(this.TAG, "-> call()");
        try {
            if (isDeviceOffline()) {
                Log.m1929w(this.TAG, "Device is offline");
                throw new NetworkException("Device is offline");
            }
            Log.m1924v(this.TAG, "execute()");
            try {
                t = execute();
            } catch (AleKeyUpdateException e) {
                Log.m1919i(this.TAG, "### ALE key update required. Re-execute call");
                t = execute();
            }
            publishResult(t);
            Log.m1919i(this.TAG, "<- call(success)");
            return t;
        } catch (SocketTimeoutException e2) {
            e = e2;
            Log.m1919i(this.TAG, "<- call(failed)");
            publishError(e);
            return null;
        } catch (NetworkException e3) {
            e = e3;
            Log.m1919i(this.TAG, "<- call(failed)");
            publishError(e);
            return null;
        } catch (Exception e4) {
            Log.m1930w(this.TAG, "<- call(failed general error)", (Throwable) e4);
            publishError(e4);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String getMethod() {
        return "POST";
    }

    /* access modifiers changed from: protected */
    public void responseReceived(int status, String message, long time, String response) {
        LogUtils.logServerResponse(getClass().getSimpleName(), status, time, response);
    }

    public void setTimeout(int timeoutMs) {
        this.connectTimeout = timeoutMs;
        this.ioTimeout = timeoutMs;
    }

    /* access modifiers changed from: protected */
    public boolean isDeviceOffline() {
        return ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo() == null;
    }
}
