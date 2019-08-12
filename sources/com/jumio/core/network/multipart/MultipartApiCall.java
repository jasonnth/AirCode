package com.jumio.core.network.multipart;

import android.content.Context;
import android.util.Pair;
import com.jumio.commons.log.LogUtils;
import com.jumio.commons.utils.IOUtils;
import com.jumio.core.mvp.model.Subscriber;
import com.jumio.core.network.ApiCall;
import com.jumio.core.network.ApiCall.DynamicProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import p005cn.jpush.android.JPushConstants;

public abstract class MultipartApiCall<T> extends ApiCall<T> {
    private static final int TIMEOUT = 90000;
    private final String boundary;
    private final String lineEnd;
    private final String partEnd;
    private final String partHeader;
    private List<Pair<String[], Object>> parts;
    private final String twoHyphens;

    /* access modifiers changed from: protected */
    public abstract void prepareData() throws Exception;

    public MultipartApiCall(Context context, DynamicProvider provider, Subscriber<T> subscriber) {
        super(context, provider, subscriber);
        this.twoHyphens = "--";
        this.lineEnd = "\r\n";
        this.boundary = "+++Android_JMSDK_mobile_" + System.currentTimeMillis() + "+++";
        this.partHeader = "--" + this.boundary + "\r\n";
        this.partEnd = "--" + this.boundary + "--" + "\r\n";
    }

    public MultipartApiCall(Context context, DynamicProvider provider) {
        super(context, provider);
        this.twoHyphens = "--";
        this.lineEnd = "\r\n";
        this.boundary = "+++Android_JMSDK_mobile_" + System.currentTimeMillis() + "+++";
        this.partHeader = "--" + this.boundary + "\r\n";
        this.partEnd = "--" + this.boundary + "--" + "\r\n";
        this.ioTimeout = TIMEOUT;
    }

    /* access modifiers changed from: protected */
    public String getBoundary() {
        return this.boundary;
    }

    /* access modifiers changed from: protected */
    public int prepareRequest() throws Exception {
        String[] strArr;
        this.parts = new ArrayList();
        prepareData();
        int size = 0;
        StringBuilder request = new StringBuilder();
        for (Pair<String[], Object> part : this.parts) {
            int size2 = size + this.partHeader.length();
            request.append(this.partHeader);
            for (String header : (String[]) part.first) {
                size2 = size2 + header.length() + "\r\n".length();
                request.append(header).append("\r\n");
            }
            int size3 = size2 + "\r\n".length();
            request.append("\r\n");
            if (part.second instanceof InputStream) {
                try {
                    size3 += ((InputStream) part.second).available();
                    request.append("<InputStream>");
                } catch (IOException e) {
                    size3 += 0;
                }
            } else if (part.second instanceof File) {
                size3 = (int) (((long) size3) + ((File) part.second).length());
                request.append("<File>");
            } else if (part.second instanceof String) {
                size3 += ((String) part.second).length();
                request.append((String) part.second);
            } else if (part.second instanceof byte[]) {
                size3 += ((byte[]) part.second).length;
                request.append("<byte[]>");
            }
            size = size3 + "\r\n".length();
            request.append("\r\n");
        }
        int size4 = size + this.partEnd.length();
        request.append(this.partEnd);
        LogUtils.logServerRequest(getClass().getSimpleName(), request.toString());
        return size4;
    }

    /* access modifiers changed from: protected */
    public void addPart(String[] headers, Object data) {
        this.parts.add(new Pair(headers, data));
    }

    /* access modifiers changed from: protected */
    public void fillRequest(OutputStream outputStream) throws IOException {
        for (Pair<String[], Object> part : this.parts) {
            outputStream.write(this.partHeader.getBytes(JPushConstants.ENCODING_UTF_8));
            for (String header : (String[]) part.first) {
                outputStream.write(header.getBytes(JPushConstants.ENCODING_UTF_8));
                outputStream.write("\r\n".getBytes(JPushConstants.ENCODING_UTF_8));
            }
            outputStream.write("\r\n".getBytes(JPushConstants.ENCODING_UTF_8));
            if (part.second instanceof InputStream) {
                IOUtils.copy((InputStream) part.second, outputStream);
                IOUtils.closeQuietly((InputStream) part.second);
            } else if (part.second instanceof File) {
                FileInputStream inputStream = new FileInputStream((File) part.second);
                IOUtils.copy(inputStream, outputStream);
                IOUtils.closeQuietly(inputStream);
            } else if (part.second instanceof String) {
                outputStream.write(((String) part.second).getBytes(JPushConstants.ENCODING_UTF_8));
            } else if (part.second instanceof byte[]) {
                outputStream.write((byte[]) part.second);
            }
            outputStream.write("\r\n".getBytes(JPushConstants.ENCODING_UTF_8));
        }
        outputStream.write(this.partEnd.getBytes(JPushConstants.ENCODING_UTF_8));
    }
}
