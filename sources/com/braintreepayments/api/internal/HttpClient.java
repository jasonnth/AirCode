package com.braintreepayments.api.internal;

import android.os.Handler;
import android.os.Looper;
import com.airbnb.android.cityregistration.controller.CityRegistrationController;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.braintreepayments.api.exceptions.AuthenticationException;
import com.braintreepayments.api.exceptions.AuthorizationException;
import com.braintreepayments.api.exceptions.DownForMaintenanceException;
import com.braintreepayments.api.exceptions.RateLimitException;
import com.braintreepayments.api.exceptions.ServerException;
import com.braintreepayments.api.exceptions.UnexpectedException;
import com.braintreepayments.api.exceptions.UnprocessableEntityException;
import com.braintreepayments.api.exceptions.UpgradeRequiredException;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.internal.HttpClient;
import com.facebook.common.util.UriUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocketFactory;
import p005cn.jpush.android.JPushConstants;

public class HttpClient<T extends HttpClient> {
    protected String mBaseUrl;
    private int mConnectTimeout = ((int) TimeUnit.SECONDS.toMillis(30));
    private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
    private int mReadTimeout = ((int) TimeUnit.SECONDS.toMillis(30));
    private SSLSocketFactory mSSLSocketFactory;
    protected final ExecutorService mThreadPool = Executors.newCachedThreadPool();
    private String mUserAgent = "braintree/core/2.5.2";

    public HttpClient() {
        try {
            this.mSSLSocketFactory = new TLSSocketFactory();
        } catch (SSLException e) {
            this.mSSLSocketFactory = null;
        }
    }

    public T setUserAgent(String userAgent) {
        this.mUserAgent = userAgent;
        return this;
    }

    public T setSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.mSSLSocketFactory = sslSocketFactory;
        return this;
    }

    public T setBaseUrl(String baseUrl) {
        if (baseUrl == null) {
            baseUrl = "";
        }
        this.mBaseUrl = baseUrl;
        return this;
    }

    public T setConnectTimeout(int timeout) {
        this.mConnectTimeout = timeout;
        return this;
    }

    public void get(String path, final HttpResponseCallback callback) {
        final String url;
        if (path == null) {
            postCallbackOnMainThread(callback, (Exception) new IllegalArgumentException("Path cannot be null"));
            return;
        }
        if (path.startsWith(UriUtil.HTTP_SCHEME)) {
            url = path;
        } else {
            url = this.mBaseUrl + path;
        }
        this.mThreadPool.submit(new Runnable() {
            public void run() {
                HttpURLConnection connection = null;
                try {
                    connection = HttpClient.this.init(url);
                    connection.setRequestMethod("GET");
                    HttpClient.this.postCallbackOnMainThread(callback, HttpClient.this.parseResponse(connection));
                    if (connection != null) {
                        connection.disconnect();
                    }
                } catch (Exception e) {
                    HttpClient.this.postCallbackOnMainThread(callback, e);
                    if (connection != null) {
                        connection.disconnect();
                    }
                } catch (Throwable th) {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    throw th;
                }
            }
        });
    }

    public void post(final String path, final String data, final HttpResponseCallback callback) {
        if (path == null) {
            postCallbackOnMainThread(callback, (Exception) new IllegalArgumentException("Path cannot be null"));
        } else {
            this.mThreadPool.submit(new Runnable() {
                public void run() {
                    try {
                        HttpClient.this.postCallbackOnMainThread(callback, HttpClient.this.post(path, data));
                    } catch (Exception e) {
                        HttpClient.this.postCallbackOnMainThread(callback, e);
                    }
                }
            });
        }
    }

    /* JADX INFO: finally extract failed */
    public String post(String path, String data) throws Exception {
        HttpURLConnection connection;
        HttpURLConnection connection2 = null;
        try {
            if (path.startsWith(UriUtil.HTTP_SCHEME)) {
                connection = init(path);
            } else {
                connection = init(this.mBaseUrl + path);
            }
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            writeOutputStream(connection.getOutputStream(), data);
            String parseResponse = parseResponse(connection);
            if (connection != null) {
                connection.disconnect();
            }
            return parseResponse;
        } catch (Throwable th) {
            if (connection2 != null) {
                connection2.disconnect();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection init(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        if (connection instanceof HttpsURLConnection) {
            if (this.mSSLSocketFactory == null) {
                throw new SSLException("SSLSocketFactory was not set or failed to initialize");
            }
            ((HttpsURLConnection) connection).setSSLSocketFactory(this.mSSLSocketFactory);
        }
        connection.setRequestProperty(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE, "application/json");
        connection.setRequestProperty("User-Agent", this.mUserAgent);
        connection.setRequestProperty("Accept-Language", Locale.getDefault().getLanguage());
        connection.setRequestProperty("Accept-Encoding", "gzip");
        connection.setConnectTimeout(this.mConnectTimeout);
        connection.setReadTimeout(this.mReadTimeout);
        return connection;
    }

    /* access modifiers changed from: protected */
    public void writeOutputStream(OutputStream outputStream, String data) throws IOException {
        Writer out = new OutputStreamWriter(outputStream, JPushConstants.ENCODING_UTF_8);
        out.write(data, 0, data.length());
        out.flush();
        out.close();
    }

    /* access modifiers changed from: protected */
    public String parseResponse(HttpURLConnection connection) throws Exception {
        int responseCode = connection.getResponseCode();
        boolean gzip = "gzip".equals(connection.getContentEncoding());
        switch (responseCode) {
            case 200:
            case 201:
            case CityRegistrationController.RC_CHOOSE_PHOTO /*202*/:
                return readStream(connection.getInputStream(), gzip);
            case 401:
                throw new AuthenticationException(readStream(connection.getErrorStream(), gzip));
            case 403:
                throw new AuthorizationException(readStream(connection.getErrorStream(), gzip));
            case 422:
                throw new UnprocessableEntityException(readStream(connection.getErrorStream(), gzip));
            case 426:
                throw new UpgradeRequiredException(readStream(connection.getErrorStream(), gzip));
            case 429:
                throw new RateLimitException("You are being rate-limited. Please try again in a few minutes.");
            case 500:
                throw new ServerException(readStream(connection.getErrorStream(), gzip));
            case 503:
                throw new DownForMaintenanceException(readStream(connection.getErrorStream(), gzip));
            default:
                throw new UnexpectedException(readStream(connection.getErrorStream(), gzip));
        }
    }

    /* access modifiers changed from: 0000 */
    public void postCallbackOnMainThread(final HttpResponseCallback callback, final String response) {
        if (callback != null) {
            this.mMainThreadHandler.post(new Runnable() {
                public void run() {
                    callback.success(response);
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public void postCallbackOnMainThread(final HttpResponseCallback callback, final Exception exception) {
        if (callback != null) {
            this.mMainThreadHandler.post(new Runnable() {
                public void run() {
                    callback.failure(exception);
                }
            });
        }
    }

    private String readStream(InputStream in, boolean gzip) throws IOException {
        if (in == null) {
            return null;
        }
        if (gzip) {
            try {
                in = new GZIPInputStream(in);
            } catch (Throwable th) {
                try {
                    in.close();
                } catch (IOException e) {
                }
                throw th;
            }
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while (true) {
            int count = in.read(buffer);
            if (count != -1) {
                out.write(buffer, 0, count);
            } else {
                String str = new String(out.toByteArray(), JPushConstants.ENCODING_UTF_8);
                try {
                    in.close();
                    return str;
                } catch (IOException e2) {
                    return str;
                }
            }
        }
    }
}
