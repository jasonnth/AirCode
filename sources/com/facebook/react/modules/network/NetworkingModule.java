package com.facebook.react.modules.network;

import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.network.OkHttpCallUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@ReactModule(name = "RCTNetworking", supportsWebWorkers = true)
public final class NetworkingModule extends ReactContextBaseJavaModule {
    private static final int CHUNK_TIMEOUT_NS = 100000000;
    private static final String CONTENT_ENCODING_HEADER_NAME = "content-encoding";
    private static final String CONTENT_TYPE_HEADER_NAME = "content-type";
    private static final String REQUEST_BODY_KEY_FORMDATA = "formData";
    private static final String REQUEST_BODY_KEY_STRING = "string";
    private static final String REQUEST_BODY_KEY_URI = "uri";
    private static final String USER_AGENT_HEADER_NAME = "user-agent";
    /* access modifiers changed from: private */
    public final OkHttpClient mClient;
    private final ForwardingCookieHandler mCookieHandler;
    private final CookieJarContainer mCookieJarContainer;
    private final String mDefaultUserAgent;
    private final Set<Integer> mRequestIds;
    private boolean mShuttingDown;

    NetworkingModule(ReactApplicationContext reactContext, String defaultUserAgent, OkHttpClient client, List<NetworkInterceptorCreator> networkInterceptorCreators) {
        super(reactContext);
        if (networkInterceptorCreators != null) {
            Builder clientBuilder = client.newBuilder();
            for (NetworkInterceptorCreator networkInterceptorCreator : networkInterceptorCreators) {
                clientBuilder.addNetworkInterceptor(networkInterceptorCreator.create());
            }
            client = clientBuilder.build();
        }
        this.mClient = client;
        OkHttpClientProvider.replaceOkHttpClient(client);
        this.mCookieHandler = new ForwardingCookieHandler(reactContext);
        this.mCookieJarContainer = (CookieJarContainer) this.mClient.cookieJar();
        this.mShuttingDown = false;
        this.mDefaultUserAgent = defaultUserAgent;
        this.mRequestIds = new HashSet();
    }

    NetworkingModule(ReactApplicationContext context, String defaultUserAgent, OkHttpClient client) {
        this(context, defaultUserAgent, client, null);
    }

    public NetworkingModule(ReactApplicationContext context) {
        this(context, null, OkHttpClientProvider.getOkHttpClient(), null);
    }

    public NetworkingModule(ReactApplicationContext context, List<NetworkInterceptorCreator> networkInterceptorCreators) {
        this(context, null, OkHttpClientProvider.getOkHttpClient(), networkInterceptorCreators);
    }

    public NetworkingModule(ReactApplicationContext context, String defaultUserAgent) {
        this(context, defaultUserAgent, OkHttpClientProvider.getOkHttpClient(), null);
    }

    public void initialize() {
        this.mCookieJarContainer.setCookieJar(new JavaNetCookieJar(this.mCookieHandler));
    }

    public String getName() {
        return "RCTNetworking";
    }

    public void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
        cancelAllRequests();
        this.mCookieHandler.destroy();
        this.mCookieJarContainer.removeCookieJar();
    }

    @ReactMethod
    public void sendRequest(ExecutorToken executorToken, String method, String url, int requestId, ReadableArray headers, ReadableMap data, String responseType, boolean useIncrementalUpdates, int timeout) {
        RCTDeviceEventEmitter eventEmitter = getEventEmitter(executorToken);
        Request.Builder requestBuilder = buildRequest(executorToken, method, url, requestId, headers, data, eventEmitter);
        if (requestBuilder != null) {
            OkHttpClient client = buildOkHttpClient(responseType, useIncrementalUpdates, timeout, eventEmitter, requestId);
            addRequest(requestId);
            fireRequest(requestBuilder, client, new ResponseCallback(requestId, responseType, eventEmitter, useIncrementalUpdates, this));
        }
    }

    public OkHttpClient buildOkHttpClient(final String responseType, boolean useIncrementalUpdates, int timeout, final RCTDeviceEventEmitter eventEmitter, final int requestId) {
        Builder clientBuilder = this.mClient.newBuilder();
        if (useIncrementalUpdates) {
            clientBuilder.addNetworkInterceptor(new Interceptor() {
                public Response intercept(Chain chain) throws IOException {
                    Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder().body(new ProgressResponseBody(originalResponse.body(), new ProgressListener() {
                        long last = System.nanoTime();

                        public void onProgress(long bytesWritten, long contentLength, boolean done) {
                            long now = System.nanoTime();
                            if ((done || NetworkingModule.shouldDispatch(now, this.last)) && !responseType.equals("text")) {
                                ResponseUtil.onDataReceivedProgress(eventEmitter, requestId, bytesWritten, contentLength);
                                this.last = now;
                            }
                        }
                    })).build();
                }
            });
        }
        if (timeout != this.mClient.connectTimeoutMillis()) {
            clientBuilder.readTimeout((long) timeout, TimeUnit.MILLISECONDS);
        }
        return clientBuilder.build();
    }

    public Request.Builder buildRequest(ExecutorToken executorToken, String method, String url, int requestId, ReadableArray headers, ReadableMap data, RCTDeviceEventEmitter eventEmitter) {
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (requestId != 0) {
            requestBuilder.tag(Integer.valueOf(requestId));
        }
        Headers requestHeaders = extractHeaders(headers, data);
        if (requestHeaders == null) {
            ResponseUtil.onRequestError(eventEmitter, requestId, "Unrecognized headers format", null);
            return null;
        }
        String contentType = requestHeaders.get(CONTENT_TYPE_HEADER_NAME);
        String contentEncoding = requestHeaders.get(CONTENT_ENCODING_HEADER_NAME);
        requestBuilder.headers(requestHeaders);
        if (data == null) {
            requestBuilder.method(method, RequestBodyUtil.getEmptyBody(method));
            return requestBuilder;
        }
        if (!data.hasKey(REQUEST_BODY_KEY_STRING)) {
            if (!data.hasKey("uri")) {
                if (data.hasKey(REQUEST_BODY_KEY_FORMDATA)) {
                    if (contentType == null) {
                        contentType = "multipart/form-data";
                    }
                    MultipartBody.Builder multipartBuilder = constructMultipartBody(executorToken, data.getArray(REQUEST_BODY_KEY_FORMDATA), contentType, requestId);
                    if (multipartBuilder == null) {
                        return null;
                    }
                    final RCTDeviceEventEmitter rCTDeviceEventEmitter = eventEmitter;
                    final int i = requestId;
                    requestBuilder.method(method, RequestBodyUtil.createProgressRequest(multipartBuilder.build(), new ProgressListener() {
                        long last = System.nanoTime();

                        public void onProgress(long bytesWritten, long contentLength, boolean done) {
                            long now = System.nanoTime();
                            if (done || NetworkingModule.shouldDispatch(now, this.last)) {
                                ResponseUtil.onDataSend(rCTDeviceEventEmitter, i, bytesWritten, contentLength);
                                this.last = now;
                            }
                        }
                    }));
                    return requestBuilder;
                }
                requestBuilder.method(method, RequestBodyUtil.getEmptyBody(method));
                return requestBuilder;
            } else if (contentType == null) {
                ResponseUtil.onRequestError(eventEmitter, requestId, "Payload is set but no content-type header specified", null);
                return null;
            } else {
                String uri = data.getString("uri");
                InputStream fileInputStream = RequestBodyUtil.getFileInputStream(getReactApplicationContext(), uri);
                if (fileInputStream == null) {
                    ResponseUtil.onRequestError(eventEmitter, requestId, "Could not retrieve file for uri " + uri, null);
                    return null;
                }
                requestBuilder.method(method, RequestBodyUtil.create(MediaType.parse(contentType), fileInputStream));
                return requestBuilder;
            }
        } else if (contentType == null) {
            ResponseUtil.onRequestError(eventEmitter, requestId, "Payload is set but no content-type header specified", null);
            return null;
        } else {
            String body = data.getString(REQUEST_BODY_KEY_STRING);
            MediaType contentMediaType = MediaType.parse(contentType);
            if (RequestBodyUtil.isGzipEncoding(contentEncoding)) {
                RequestBody requestBody = RequestBodyUtil.createGzip(contentMediaType, body);
                if (requestBody == null) {
                    ResponseUtil.onRequestError(eventEmitter, requestId, "Failed to gzip request body", null);
                    return null;
                }
                requestBuilder.method(method, requestBody);
                return requestBuilder;
            }
            requestBuilder.method(method, RequestBody.create(contentMediaType, body));
            return requestBuilder;
        }
    }

    public void fireRequest(Request.Builder requestBuilder, OkHttpClient client, Callback callback) {
        client.newCall(requestBuilder.build()).enqueue(callback);
    }

    /* access modifiers changed from: private */
    public static boolean shouldDispatch(long now, long last) {
        return 100000000 + last < now;
    }

    public synchronized void addRequest(int requestId) {
        this.mRequestIds.add(Integer.valueOf(requestId));
    }

    public boolean isShuttingDown() {
        return this.mShuttingDown;
    }

    public synchronized void removeRequest(int requestId) {
        this.mRequestIds.remove(Integer.valueOf(requestId));
    }

    private synchronized void cancelAllRequests() {
        for (Integer requestId : this.mRequestIds) {
            cancelRequest(requestId.intValue());
        }
        this.mRequestIds.clear();
    }

    @ReactMethod
    public void abortRequest(ExecutorToken executorToken, int requestId) {
        cancelRequest(requestId);
        removeRequest(requestId);
    }

    private void cancelRequest(final int requestId) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... params) {
                OkHttpCallUtil.cancelTag(NetworkingModule.this.mClient, Integer.valueOf(requestId));
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public void clearCookies(ExecutorToken executorToken, com.facebook.react.bridge.Callback callback) {
        this.mCookieHandler.clearCookies(callback);
    }

    public boolean supportsWebWorkers() {
        return true;
    }

    private MultipartBody.Builder constructMultipartBody(ExecutorToken ExecutorToken, ReadableArray body, String contentType, int requestId) {
        RCTDeviceEventEmitter eventEmitter = getEventEmitter(ExecutorToken);
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
        multipartBuilder.setType(MediaType.parse(contentType));
        int size = body.size();
        for (int i = 0; i < size; i++) {
            ReadableMap bodyPart = body.getMap(i);
            Headers headers = extractHeaders(bodyPart.getArray("headers"), null);
            if (headers == null) {
                ResponseUtil.onRequestError(eventEmitter, requestId, "Missing or invalid header format for FormData part.", null);
                return null;
            }
            MediaType partContentType = null;
            String partContentTypeStr = headers.get(CONTENT_TYPE_HEADER_NAME);
            if (partContentTypeStr != null) {
                partContentType = MediaType.parse(partContentTypeStr);
                headers = headers.newBuilder().removeAll(CONTENT_TYPE_HEADER_NAME).build();
            }
            if (bodyPart.hasKey(REQUEST_BODY_KEY_STRING)) {
                multipartBuilder.addPart(headers, RequestBody.create(partContentType, bodyPart.getString(REQUEST_BODY_KEY_STRING)));
            } else if (!bodyPart.hasKey("uri")) {
                ResponseUtil.onRequestError(eventEmitter, requestId, "Unrecognized FormData part.", null);
            } else if (partContentType == null) {
                ResponseUtil.onRequestError(eventEmitter, requestId, "Binary FormData part needs a content-type header.", null);
                return null;
            } else {
                String fileContentUriStr = bodyPart.getString("uri");
                InputStream fileInputStream = RequestBodyUtil.getFileInputStream(getReactApplicationContext(), fileContentUriStr);
                if (fileInputStream == null) {
                    ResponseUtil.onRequestError(eventEmitter, requestId, "Could not retrieve file for uri " + fileContentUriStr, null);
                    return null;
                }
                multipartBuilder.addPart(headers, RequestBodyUtil.create(partContentType, fileInputStream));
            }
        }
        return multipartBuilder;
    }

    private Headers extractHeaders(ReadableArray headersArray, ReadableMap requestData) {
        boolean isGzipSupported = true;
        if (headersArray == null) {
            return null;
        }
        Headers.Builder headersBuilder = new Headers.Builder();
        int size = headersArray.size();
        for (int headersIdx = 0; headersIdx < size; headersIdx++) {
            ReadableArray header = headersArray.getArray(headersIdx);
            if (header == null || header.size() != 2) {
                return null;
            }
            String headerName = header.getString(0);
            String headerValue = header.getString(1);
            if (headerName == null || headerValue == null) {
                return null;
            }
            headersBuilder.add(headerName, headerValue);
        }
        if (headersBuilder.get(USER_AGENT_HEADER_NAME) == null && this.mDefaultUserAgent != null) {
            headersBuilder.add(USER_AGENT_HEADER_NAME, this.mDefaultUserAgent);
        }
        if (requestData == null || !requestData.hasKey(REQUEST_BODY_KEY_STRING)) {
            isGzipSupported = false;
        }
        if (!isGzipSupported) {
            headersBuilder.removeAll(CONTENT_ENCODING_HEADER_NAME);
        }
        return headersBuilder.build();
    }

    public RCTDeviceEventEmitter getEventEmitter(ExecutorToken ExecutorToken) {
        return (RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(ExecutorToken, RCTDeviceEventEmitter.class);
    }
}
