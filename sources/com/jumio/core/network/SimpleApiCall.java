package com.jumio.core.network;

import android.content.Context;
import com.jumio.commons.log.LogUtils;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.network.ApiCall.DynamicProvider;
import java.io.IOException;
import java.io.OutputStream;

public abstract class SimpleApiCall<T> extends ApiCall<T> {
    private String request;

    /* access modifiers changed from: protected */
    public abstract String getRequest() throws Exception;

    public SimpleApiCall(Context context, DynamicProvider provider, Subscriber<T> subscriber) {
        super(context, provider, subscriber);
    }

    public SimpleApiCall(Context context, DynamicProvider provider) {
        super(context, provider);
    }

    /* access modifiers changed from: protected */
    public String getBoundary() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int prepareRequest() throws Exception {
        this.request = getRequest();
        LogUtils.logServerRequest(getClass().getSimpleName(), this.request);
        return this.request.getBytes().length;
    }

    /* access modifiers changed from: protected */
    public void fillRequest(OutputStream outputStream) throws IOException {
        outputStream.write(this.request.getBytes());
    }
}
