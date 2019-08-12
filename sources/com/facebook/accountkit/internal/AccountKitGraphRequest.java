package com.facebook.accountkit.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;

final class AccountKitGraphRequest {
    private static final String ACCESS_TOKEN_PREFIX = "AA";
    private static final int DEFAULT_TIMEOUT_MILLISECONDS = 10000;
    private static final String GRAPH_API_VERSION = "v1.2";
    private static final String GRAPH_BASE_URL = "https://graph.accountkit.com";
    private static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String ISO_8601_FORMAT_STRING = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String MIME_BOUNDARY = "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f";
    private static final String PARAMETER_ACCESS_TOKEN = "access_token";
    public static final String TAG = AccountKitGraphRequest.class.getSimpleName();
    private static final String USER_AGENT_BASE = "AccountKitAndroidSDK";
    private static final String USER_AGENT_HEADER = "User-Agent";
    private static final Pattern versionPattern = Pattern.compile("^/?v\\d+\\.\\d+/(.*)");
    private AccessToken accessToken;
    private Handler callbackHandler;
    private final String graphPath;
    private HttpMethod httpMethod;
    private final boolean isLoginRequest;
    private Bundle parameters;
    private JSONObject requestObject;
    private Object tag;
    private String version;

    public interface Callback {
        void onCompleted(AccountKitGraphResponse accountKitGraphResponse);
    }

    private interface KeyValueSerializer {
        void writeString(String str, String str2) throws IOException;
    }

    private static class LazyUserAgentHolder {
        static final String userAgent = buildUserAgent();

        private LazyUserAgentHolder() {
        }

        private static String buildUserAgent() {
            String systemUserAgent = System.getProperty("http.agent");
            if (systemUserAgent == null) {
                systemUserAgent = "";
            }
            return systemUserAgent + " " + AccountKitGraphRequest.USER_AGENT_BASE + "/" + "4.23.0";
        }
    }

    private static class ParcelableResourceWithMimeType<RESOURCE extends Parcelable> implements Parcelable {
        public static final Creator<ParcelableResourceWithMimeType> CREATOR = new Creator<ParcelableResourceWithMimeType>() {
            public ParcelableResourceWithMimeType createFromParcel(Parcel in) {
                return new ParcelableResourceWithMimeType(in);
            }

            public ParcelableResourceWithMimeType[] newArray(int size) {
                return new ParcelableResourceWithMimeType[size];
            }
        };
        private final String mimeType;
        private final RESOURCE resource;

        /* access modifiers changed from: 0000 */
        public String getMimeType() {
            return this.mimeType;
        }

        public RESOURCE getResource() {
            return this.resource;
        }

        public int describeContents() {
            return 1;
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeString(this.mimeType);
            out.writeParcelable(this.resource, flags);
        }

        private ParcelableResourceWithMimeType(Parcel in) {
            this.mimeType = in.readString();
            this.resource = in.readParcelable(AccountKitController.getApplicationContext().getClassLoader());
        }
    }

    private static class Serializer implements KeyValueSerializer {
        private boolean firstWrite = true;
        private final OutputStream outputStream;
        private boolean useUrlEncode = false;

        Serializer(OutputStream outputStream2, boolean useUrlEncode2) {
            this.outputStream = outputStream2;
            this.useUrlEncode = useUrlEncode2;
        }

        /* access modifiers changed from: 0000 */
        public void writeObject(String key, Object value) throws IOException {
            if (AccountKitGraphRequest.isSupportedParameterType(value)) {
                writeString(key, AccountKitGraphRequest.parameterToString(value));
            } else if (value instanceof Bitmap) {
                writeBitmap(key, (Bitmap) value);
            } else if (value instanceof byte[]) {
                writeBytes(key, (byte[]) value);
            } else if (value instanceof Uri) {
                writeContentUri(key, (Uri) value, null);
            } else if (value instanceof ParcelFileDescriptor) {
                writeFile(key, (ParcelFileDescriptor) value, null);
            } else if (value instanceof ParcelableResourceWithMimeType) {
                ParcelableResourceWithMimeType resourceWithMimeType = (ParcelableResourceWithMimeType) value;
                Parcelable resource = resourceWithMimeType.getResource();
                String mimeType = resourceWithMimeType.getMimeType();
                if (resource instanceof ParcelFileDescriptor) {
                    writeFile(key, (ParcelFileDescriptor) resource, mimeType);
                } else if (resource instanceof Uri) {
                    writeContentUri(key, (Uri) resource, mimeType);
                } else {
                    throw getInvalidTypeError();
                }
            } else {
                throw getInvalidTypeError();
            }
        }

        private RuntimeException getInvalidTypeError() {
            return new IllegalArgumentException("value is not a supported type.");
        }

        public void writeString(String key, String value) throws IOException {
            writeContentDisposition(key, null, null);
            writeLine("%s", value);
            writeRecordBoundary();
        }

        /* access modifiers changed from: 0000 */
        public void writeBitmap(String key, Bitmap bitmap) throws IOException {
            writeContentDisposition(key, key, "image/png");
            bitmap.compress(CompressFormat.PNG, 100, this.outputStream);
            writeLine("", new Object[0]);
            writeRecordBoundary();
        }

        /* access modifiers changed from: 0000 */
        public void writeBytes(String key, byte[] bytes) throws IOException {
            writeContentDisposition(key, key, "content/unknown");
            this.outputStream.write(bytes);
            writeLine("", new Object[0]);
            writeRecordBoundary();
        }

        /* access modifiers changed from: 0000 */
        public void writeContentUri(String key, Uri contentUri, String mimeType) throws IOException {
            if (mimeType == null) {
                mimeType = "content/unknown";
            }
            writeContentDisposition(key, key, mimeType);
            Utility.copyAndCloseInputStream(AccountKitController.getApplicationContext().getContentResolver().openInputStream(contentUri), this.outputStream);
            writeLine("", new Object[0]);
            writeRecordBoundary();
        }

        /* access modifiers changed from: 0000 */
        public void writeFile(String key, ParcelFileDescriptor descriptor, String mimeType) throws IOException {
            if (mimeType == null) {
                mimeType = "content/unknown";
            }
            writeContentDisposition(key, key, mimeType);
            Utility.copyAndCloseInputStream(new AutoCloseInputStream(descriptor), this.outputStream);
            writeLine("", new Object[0]);
            writeRecordBoundary();
        }

        /* access modifiers changed from: 0000 */
        public void writeRecordBoundary() throws IOException {
            if (!this.useUrlEncode) {
                writeLine("--%s", AccountKitGraphRequest.MIME_BOUNDARY);
                return;
            }
            this.outputStream.write("&".getBytes());
        }

        /* access modifiers changed from: 0000 */
        public void writeContentDisposition(String name, String filename, String contentType) throws IOException {
            if (!this.useUrlEncode) {
                write("Content-Disposition: form-data; name=\"%s\"", name);
                if (filename != null) {
                    write("; filename=\"%s\"", filename);
                }
                writeLine("", new Object[0]);
                if (contentType != null) {
                    writeLine("%s: %s", "Content-Type", contentType);
                }
                writeLine("", new Object[0]);
                return;
            }
            this.outputStream.write(String.format("%s=", new Object[]{name}).getBytes());
        }

        /* access modifiers changed from: 0000 */
        public void write(String format, Object... args) throws IOException {
            if (!this.useUrlEncode) {
                if (this.firstWrite) {
                    this.outputStream.write("--".getBytes());
                    this.outputStream.write(AccountKitGraphRequest.MIME_BOUNDARY.getBytes());
                    this.outputStream.write("\r\n".getBytes());
                    this.firstWrite = false;
                }
                this.outputStream.write(String.format(format, args).getBytes());
                return;
            }
            this.outputStream.write(URLEncoder.encode(String.format(Locale.US, format, args), JPushConstants.ENCODING_UTF_8).getBytes());
        }

        /* access modifiers changed from: 0000 */
        public void writeLine(String format, Object... args) throws IOException {
            write(format, args);
            if (!this.useUrlEncode) {
                write("\r\n", new Object[0]);
            }
        }
    }

    public AccountKitGraphRequest(AccessToken accessToken2, String graphPath2, Bundle parameters2, boolean isLoginRequest2, HttpMethod httpMethod2) {
        this(accessToken2, graphPath2, parameters2, isLoginRequest2, httpMethod2, null);
    }

    public AccountKitGraphRequest(AccessToken accessToken2, String graphPath2, Bundle parameters2, boolean isLoginRequest2, HttpMethod httpMethod2, String version2) {
        this.accessToken = accessToken2;
        this.graphPath = graphPath2;
        this.isLoginRequest = isLoginRequest2;
        setHttpMethod(httpMethod2);
        if (parameters2 != null) {
            this.parameters = new Bundle(parameters2);
        } else {
            this.parameters = new Bundle();
        }
        if (version2 == null) {
            version2 = GRAPH_API_VERSION;
        }
        this.version = version2;
    }

    /* access modifiers changed from: 0000 */
    public boolean isLoginRequest() {
        return this.isLoginRequest;
    }

    /* access modifiers changed from: 0000 */
    public JSONObject getRequestObject() {
        return this.requestObject;
    }

    /* access modifiers changed from: 0000 */
    public void setRequestObject(JSONObject requestObject2) {
        this.requestObject = requestObject2;
    }

    /* access modifiers changed from: 0000 */
    public String getGraphPath() {
        return this.graphPath;
    }

    /* access modifiers changed from: 0000 */
    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public Bundle getParameters() {
        return this.parameters;
    }

    public void setParameters(Bundle parameters2) {
        this.parameters = parameters2;
    }

    public AccessToken getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(AccessToken accessToken2) {
        this.accessToken = accessToken2;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version2) {
        this.version = version2;
    }

    /* access modifiers changed from: 0000 */
    public void setHttpMethod(HttpMethod httpMethod2) {
        if (httpMethod2 == null) {
            httpMethod2 = HttpMethod.GET;
        }
        this.httpMethod = httpMethod2;
    }

    public void setTag(Object tag2) {
        this.tag = tag2;
    }

    public Object getTag() {
        return this.tag;
    }

    /* access modifiers changed from: 0000 */
    public AccountKitGraphResponse executeAndWait() {
        try {
            AccountKitGraphResponse response = executeConnectionAndWait(toHttpConnection(this), this);
            if (response != null) {
                return response;
            }
            throw new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.INVALID_GRAPH_RESPONSE);
        } catch (AccountKitException ex) {
            return new AccountKitGraphResponse(this, null, new AccountKitRequestError(ex));
        } catch (Exception ex2) {
            return new AccountKitGraphResponse(this, null, new AccountKitRequestError(new AccountKitException(Type.INTERNAL_ERROR, (Throwable) ex2)));
        }
    }

    static AccountKitGraphResponse executeConnectionAndWait(HttpURLConnection connection, AccountKitGraphRequest request) {
        AccountKitGraphResponse response = AccountKitGraphResponse.fromHttpConnection(connection, request);
        Utility.disconnectQuietly(connection);
        return response;
    }

    public String toString() {
        return "{Request:  accessToken: " + (this.accessToken == null ? "null" : this.accessToken) + ", graphPath: " + this.graphPath + ", requestObject: " + this.requestObject + ", httpMethod: " + this.httpMethod + ", parameters: " + this.parameters + "}";
    }

    private static HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty(USER_AGENT_HEADER, LazyUserAgentHolder.userAgent);
        connection.setChunkedStreamingMode(0);
        return connection;
    }

    private void addCommonParameters() {
        Utility.putNonNullString(this.parameters, AccountKitGraphConstants.PARAMETER_LOCALE, LocaleMapper.getSystemLocale());
        Utility.putNonNullString(this.parameters, "sdk", "android");
        this.parameters.putBoolean(AccountKitGraphConstants.PARAMETER_FACEBOOK_APP_EVENTS_ENABLED, AccountKit.getAccountKitFacebookAppEventsEnabled());
        if (this.accessToken != null) {
            if (!this.parameters.containsKey("access_token")) {
                this.parameters.putString("access_token", this.accessToken.getToken());
            }
        } else if (!this.parameters.containsKey("access_token")) {
            String appID = AccountKit.getApplicationId();
            String clientToken = AccountKit.getClientToken();
            if (Utility.isNullOrEmpty(appID) || Utility.isNullOrEmpty(clientToken)) {
                Log.d(TAG, "Warning: Request without access token missing application ID or client token.");
                return;
            }
            this.parameters.putString("access_token", "AA|" + appID + "|" + clientToken);
        }
    }

    private String getUrlForSingleRequest() {
        Builder builder = Uri.parse(GRAPH_BASE_URL).buildUpon();
        if (!versionPattern.matcher(this.graphPath).matches()) {
            builder.appendPath(this.version);
        }
        builder.appendPath(this.graphPath);
        addCommonParameters();
        if (this.httpMethod != HttpMethod.POST) {
            appendQueryParametersToUri(builder);
        }
        return builder.toString();
    }

    private void appendQueryParametersToUri(Builder uriBuilder) {
        List<String> keys = new ArrayList<>(this.parameters.keySet());
        Collections.sort(keys);
        for (String key : keys) {
            Object value = this.parameters.get(key);
            if (value == null) {
                value = "";
            }
            uriBuilder.appendQueryParameter(key, parameterToString(value));
        }
    }

    private static void setConnectionContentType(HttpURLConnection connection, boolean isMultipart) {
        if (isMultipart) {
            connection.setRequestProperty("Content-Type", getMimeContentType());
            return;
        }
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty(HEADER_CONTENT_ENCODING, "gzip");
    }

    private static void serializeParameters(Bundle bundle, Serializer serializer) throws IOException {
        for (String key : bundle.keySet()) {
            serializer.writeObject(key, bundle.get(key));
        }
    }

    private static String getMimeContentType() {
        return String.format("multipart/form-data; boundary=%s", new Object[]{MIME_BOUNDARY});
    }

    /* access modifiers changed from: private */
    public static boolean isSupportedParameterType(Object value) {
        return (value instanceof String) || (value instanceof Boolean) || (value instanceof Number) || (value instanceof Date);
    }

    /* access modifiers changed from: private */
    public static String parameterToString(Object value) {
        if (value instanceof String) {
            return (String) value;
        }
        if ((value instanceof Boolean) || (value instanceof Number)) {
            return value.toString();
        }
        if (value instanceof Date) {
            return new SimpleDateFormat(ISO_8601_FORMAT_STRING, Locale.US).format(value);
        }
        throw new IllegalArgumentException("Unsupported parameter type.");
    }

    static HttpURLConnection toHttpConnection(AccountKitGraphRequest request) {
        try {
            try {
                HttpURLConnection connection = createConnection(new URL(request.getUrlForSingleRequest()));
                serializeToUrlConnection(request, connection);
                return connection;
            } catch (UnknownHostException e) {
                throw new AccountKitException(Type.NETWORK_CONNECTION_ERROR, InternalAccountKitError.NO_NETWORK_CONNECTION);
            } catch (IOException | JSONException e2) {
                throw new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.CANNOT_CONSTRUCT_MESSAGE_BODY, (Throwable) e2);
            }
        } catch (MalformedURLException e3) {
            throw new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.CANNOT_CONSTRUCT_URL, (Throwable) e3);
        }
    }

    static AccountKitGraphRequestAsyncTask executeAsync(AccountKitGraphRequest request, Callback callback) {
        AccountKitGraphRequestAsyncTask asyncTask = new AccountKitGraphRequestAsyncTask(request, callback);
        asyncTask.executeOnExecutor(Utility.getThreadPoolExecutor(), new Void[0]);
        return asyncTask;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x008b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void serializeToUrlConnection(com.facebook.accountkit.internal.AccountKitGraphRequest r9, java.net.HttpURLConnection r10) throws java.io.IOException, org.json.JSONException {
        /*
            r8 = 10000(0x2710, float:1.4013E-41)
            com.facebook.accountkit.internal.ConsoleLogger r1 = new com.facebook.accountkit.internal.ConsoleLogger
            com.facebook.accountkit.LoggingBehavior r6 = com.facebook.accountkit.LoggingBehavior.REQUESTS
            java.lang.String r7 = "Request"
            r1.<init>(r6, r7)
            com.facebook.accountkit.internal.HttpMethod r0 = r9.httpMethod
            java.lang.String r6 = r0.name()
            r10.setRequestMethod(r6)
            android.os.Bundle r6 = r9.parameters
            boolean r2 = isMultiPart(r6)
            setConnectionContentType(r10, r2)
            java.net.URL r5 = r10.getURL()
            java.lang.String r6 = "Request:"
            r1.appendLine(r6)
            java.lang.String r6 = "AccessToken"
            com.facebook.accountkit.AccessToken r7 = r9.getAccessToken()
            r1.appendKeyValue(r6, r7)
            java.lang.String r6 = "URL"
            r1.appendKeyValue(r6, r5)
            java.lang.String r6 = "Method"
            java.lang.String r7 = r10.getRequestMethod()
            r1.appendKeyValue(r6, r7)
            java.lang.String r6 = "User-Agent"
            java.lang.String r7 = "User-Agent"
            java.lang.String r7 = r10.getRequestProperty(r7)
            r1.appendKeyValue(r6, r7)
            java.lang.String r6 = "Content-Type"
            java.lang.String r7 = "Content-Type"
            java.lang.String r7 = r10.getRequestProperty(r7)
            r1.appendKeyValue(r6, r7)
            r1.log()
            r10.setConnectTimeout(r8)
            r10.setReadTimeout(r8)
            com.facebook.accountkit.internal.HttpMethod r6 = com.facebook.accountkit.internal.HttpMethod.POST
            if (r0 == r6) goto L_0x006a
        L_0x0069:
            return
        L_0x006a:
            r6 = 1
            r10.setDoOutput(r6)
            r3 = 0
            java.io.OutputStream r3 = r10.getOutputStream()     // Catch:{ all -> 0x0088 }
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0088 }
            r4.<init>(r3)     // Catch:{ all -> 0x0088 }
            if (r2 != 0) goto L_0x0092
            java.util.zip.GZIPOutputStream r3 = new java.util.zip.GZIPOutputStream     // Catch:{ all -> 0x008f }
            r3.<init>(r4)     // Catch:{ all -> 0x008f }
        L_0x007f:
            processRequest(r9, r3, r2)     // Catch:{ all -> 0x0088 }
            if (r3 == 0) goto L_0x0069
            r3.close()
            goto L_0x0069
        L_0x0088:
            r6 = move-exception
        L_0x0089:
            if (r3 == 0) goto L_0x008e
            r3.close()
        L_0x008e:
            throw r6
        L_0x008f:
            r6 = move-exception
            r3 = r4
            goto L_0x0089
        L_0x0092:
            r3 = r4
            goto L_0x007f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.internal.AccountKitGraphRequest.serializeToUrlConnection(com.facebook.accountkit.internal.AccountKitGraphRequest, java.net.HttpURLConnection):void");
    }

    private static boolean isMultiPart(Bundle parameters2) {
        for (String key : parameters2.keySet()) {
            if (isMultipartType(parameters2.get(key))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMultipartType(Object value) {
        return (value instanceof Bitmap) || (value instanceof byte[]) || (value instanceof Uri) || (value instanceof ParcelFileDescriptor) || (value instanceof ParcelableResourceWithMimeType);
    }

    private static void processRequest(AccountKitGraphRequest request, OutputStream outputStream, boolean isMultipart) throws IOException {
        Serializer serializer = new Serializer(outputStream, !isMultipart);
        serializeParameters(request.parameters, serializer);
        if (request.requestObject != null) {
            processRequestObject(request.requestObject, serializer);
        }
    }

    /* access modifiers changed from: 0000 */
    public Handler getCallbackHandler() {
        return this.callbackHandler;
    }

    /* access modifiers changed from: 0000 */
    public void setCallbackHandler(Handler callbackHandler2) {
        this.callbackHandler = callbackHandler2;
    }

    private static void processRequestObject(JSONObject requestObject2, KeyValueSerializer serializer) throws IOException {
        Iterator<String> keyIterator = requestObject2.keys();
        while (keyIterator.hasNext()) {
            String key = (String) keyIterator.next();
            processRequestObjectProperty(key, requestObject2.opt(key), serializer);
        }
    }

    private static void processRequestObjectProperty(String key, Object value, KeyValueSerializer serializer) throws IOException {
        Class<?> valueClass = value.getClass();
        if (String.class.isAssignableFrom(valueClass) || Number.class.isAssignableFrom(valueClass) || Boolean.class.isAssignableFrom(valueClass)) {
            serializer.writeString(key, value.toString());
        } else if (Date.class.isAssignableFrom(valueClass)) {
            serializer.writeString(key, new SimpleDateFormat(ISO_8601_FORMAT_STRING, Locale.US).format((Date) value));
        }
    }
}
