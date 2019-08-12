package com.facebook.react.modules.network;

import android.util.Base64;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import java.io.IOException;
import java.io.Reader;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ResponseCallback implements Callback {
    private static final int MAX_CHUNK_SIZE_BETWEEN_FLUSHES = 8192;
    private final RCTDeviceEventEmitter eventEmitter;
    private final NetworkingModule networkingModule;
    private final int requestId;
    private final String responseType;
    private final boolean useIncrementalUpdates;

    public ResponseCallback(int requestId2, String responseType2, RCTDeviceEventEmitter eventEmitter2, boolean useIncrementalUpdates2, NetworkingModule networkingModule2) {
        this.requestId = requestId2;
        this.responseType = responseType2;
        this.eventEmitter = eventEmitter2;
        this.useIncrementalUpdates = useIncrementalUpdates2;
        this.networkingModule = networkingModule2;
    }

    public void onFailure(Call call, IOException e) {
        if (!this.networkingModule.isShuttingDown()) {
            this.networkingModule.removeRequest(this.requestId);
            ResponseUtil.onRequestError(this.eventEmitter, this.requestId, e.getMessage(), toIOException(e));
        }
    }

    private static IOException toIOException(Throwable e) {
        if (e instanceof IOException) {
            return (IOException) e;
        }
        if (e.getCause() instanceof IOException) {
            return (IOException) e.getCause();
        }
        return new IOException(e.getMessage());
    }

    public void onResponse(Call call, Response response) {
        if (!this.networkingModule.isShuttingDown()) {
            this.networkingModule.removeRequest(this.requestId);
            ResponseUtil.onResponseReceived(this.eventEmitter, this.requestId, response.code(), translateHeaders(response.headers()), response.request().url().toString());
            ResponseBody responseBody = response.body();
            try {
                if (!this.useIncrementalUpdates || !this.responseType.equals("text")) {
                    String responseString = "";
                    if (this.responseType.equals("text")) {
                        responseString = responseBody.string();
                    } else if (this.responseType.equals("base64")) {
                        responseString = Base64.encodeToString(responseBody.bytes(), 2);
                    }
                    ResponseUtil.onDataReceived(this.eventEmitter, this.requestId, responseString);
                    ResponseUtil.onRequestSuccess(this.eventEmitter, this.requestId);
                    return;
                }
                readWithProgress(this.eventEmitter, this.requestId, responseBody);
                ResponseUtil.onRequestSuccess(this.eventEmitter, this.requestId);
            } catch (IOException e) {
                ResponseUtil.onRequestError(this.eventEmitter, this.requestId, e.getMessage(), e);
            }
        }
    }

    private void readWithProgress(RCTDeviceEventEmitter eventEmitter2, int requestId2, ResponseBody responseBody) throws IOException {
        long totalBytesRead = -1;
        long contentLength = -1;
        try {
            ProgressResponseBody progressResponseBody = (ProgressResponseBody) responseBody;
            totalBytesRead = progressResponseBody.totalBytesRead();
            contentLength = progressResponseBody.contentLength();
        } catch (ClassCastException e) {
        }
        Reader reader = responseBody.charStream();
        try {
            char[] buffer = new char[8192];
            while (true) {
                int read = reader.read(buffer);
                if (read != -1) {
                    ResponseUtil.onIncrementalDataReceived(eventEmitter2, requestId2, new String(buffer, 0, read), totalBytesRead, contentLength);
                } else {
                    return;
                }
            }
        } finally {
            reader.close();
        }
    }

    private static WritableMap translateHeaders(Headers headers) {
        WritableMap responseHeaders = Arguments.createMap();
        for (int i = 0; i < headers.size(); i++) {
            String headerName = headers.name(i);
            if (responseHeaders.hasKey(headerName)) {
                responseHeaders.putString(headerName, responseHeaders.getString(headerName) + ", " + headers.value(i));
            } else {
                responseHeaders.putString(headerName, headers.value(i));
            }
        }
        return responseHeaders;
    }
}
