package com.braintreepayments.api.internal;

import android.net.Uri;
import com.braintreepayments.api.exceptions.AuthorizationException;
import com.braintreepayments.api.exceptions.ErrorWithResponse;
import com.braintreepayments.api.exceptions.UnprocessableEntityException;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.models.Authorization;
import com.braintreepayments.api.models.ClientToken;
import com.braintreepayments.api.models.TokenizationKey;
import com.facebook.common.util.UriUtil;
import java.io.IOException;
import java.net.HttpURLConnection;
import javax.net.ssl.SSLException;
import org.json.JSONException;
import org.json.JSONObject;

public class BraintreeHttpClient extends HttpClient {
    private final Authorization mAuthorization;

    public BraintreeHttpClient(Authorization authorization) {
        setUserAgent(getUserAgent());
        try {
            setSSLSocketFactory(new TLSSocketFactory(BraintreeGatewayCertificate.getCertInputStream()));
        } catch (SSLException e) {
            setSSLSocketFactory(null);
        }
        this.mAuthorization = authorization;
    }

    public static String getUserAgent() {
        return "braintree/android/2.5.2";
    }

    public void get(String path, HttpResponseCallback callback) {
        Uri uri;
        if (path == null) {
            postCallbackOnMainThread(callback, (Exception) new IllegalArgumentException("Path cannot be null"));
            return;
        }
        if (path.startsWith(UriUtil.HTTP_SCHEME)) {
            uri = Uri.parse(path);
        } else {
            uri = Uri.parse(this.mBaseUrl + path);
        }
        if (this.mAuthorization instanceof ClientToken) {
            uri = uri.buildUpon().appendQueryParameter("authorizationFingerprint", ((ClientToken) this.mAuthorization).getAuthorizationFingerprint()).build();
        }
        super.get(uri.toString(), callback);
    }

    public void post(String path, String data, HttpResponseCallback callback) {
        try {
            if (this.mAuthorization instanceof ClientToken) {
                data = new JSONObject(data).put("authorizationFingerprint", ((ClientToken) this.mAuthorization).getAuthorizationFingerprint()).toString();
            }
            super.post(path, data, callback);
        } catch (JSONException e) {
            postCallbackOnMainThread(callback, (Exception) e);
        }
    }

    public String post(String path, String data) throws Exception {
        if (this.mAuthorization instanceof ClientToken) {
            data = new JSONObject(data).put("authorizationFingerprint", ((ClientToken) this.mAuthorization).getAuthorizationFingerprint()).toString();
        }
        return super.post(path, data);
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection init(String url) throws IOException {
        HttpURLConnection connection = super.init(url);
        if (this.mAuthorization instanceof TokenizationKey) {
            connection.setRequestProperty("Client-Key", this.mAuthorization.toString());
        }
        return connection;
    }

    /* access modifiers changed from: protected */
    public String parseResponse(HttpURLConnection connection) throws Exception {
        try {
            return super.parseResponse(connection);
        } catch (AuthorizationException | UnprocessableEntityException e) {
            if (e instanceof AuthorizationException) {
                throw new AuthorizationException(new ErrorWithResponse(403, e.getMessage()).getMessage());
            }
            throw new ErrorWithResponse(422, e.getMessage());
        }
    }
}
