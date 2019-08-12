package com.danikula.videocache;

import android.text.TextUtils;
import com.danikula.videocache.file.FileCache;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Locale;
import p005cn.jpush.android.JPushConstants;

class HttpProxyCache extends ProxyCache {
    private final FileCache cache;
    private CacheListener listener;
    private final HttpUrlSource source;

    public HttpProxyCache(HttpUrlSource source2, FileCache cache2) {
        super(source2, cache2);
        this.cache = cache2;
        this.source = source2;
    }

    public void registerCacheListener(CacheListener cacheListener) {
        this.listener = cacheListener;
    }

    public void processRequest(GetRequest request, Socket socket) throws IOException, ProxyCacheException {
        OutputStream out = new BufferedOutputStream(socket.getOutputStream());
        out.write(newResponseHeaders(request).getBytes(JPushConstants.ENCODING_UTF_8));
        long offset = request.rangeOffset;
        if (isUseCache(request)) {
            responseWithCache(out, offset);
        } else {
            responseWithoutCache(out, offset);
        }
    }

    private boolean isUseCache(GetRequest request) throws ProxyCacheException {
        boolean sourceLengthKnown;
        long sourceLength = this.source.length();
        if (sourceLength > 0) {
            sourceLengthKnown = true;
        } else {
            sourceLengthKnown = false;
        }
        long cacheAvailable = this.cache.available();
        if (!sourceLengthKnown || !request.partial || ((float) request.rangeOffset) <= ((float) cacheAvailable) + (((float) sourceLength) * 0.2f)) {
            return true;
        }
        return false;
    }

    private String newResponseHeaders(GetRequest request) throws IOException, ProxyCacheException {
        long contentLength;
        String mime = this.source.getMime();
        boolean mimeKnown = !TextUtils.isEmpty(mime);
        long length = this.cache.isCompleted() ? this.cache.available() : this.source.length();
        boolean lengthKnown = length >= 0;
        if (request.partial) {
            contentLength = length - request.rangeOffset;
        } else {
            contentLength = length;
        }
        return (request.partial ? "HTTP/1.1 206 PARTIAL CONTENT\n" : "HTTP/1.1 200 OK\n") + "Accept-Ranges: bytes\n" + (lengthKnown ? format("Content-Length: %d\n", Long.valueOf(contentLength)) : "") + (lengthKnown && request.partial ? format("Content-Range: bytes %d-%d/%d\n", Long.valueOf(request.rangeOffset), Long.valueOf(length - 1), Long.valueOf(length)) : "") + (mimeKnown ? format("Content-Type: %s\n", mime) : "") + "\n";
    }

    private void responseWithCache(OutputStream out, long offset) throws ProxyCacheException, IOException {
        byte[] buffer = new byte[8192];
        while (true) {
            int readBytes = read(buffer, offset, buffer.length);
            if (readBytes != -1) {
                out.write(buffer, 0, readBytes);
                offset += (long) readBytes;
            } else {
                out.flush();
                return;
            }
        }
    }

    private void responseWithoutCache(OutputStream out, long offset) throws ProxyCacheException, IOException {
        HttpUrlSource newSourceNoCache = new HttpUrlSource(this.source);
        try {
            newSourceNoCache.open((long) ((int) offset));
            byte[] buffer = new byte[8192];
            while (true) {
                int readBytes = newSourceNoCache.read(buffer);
                if (readBytes != -1) {
                    out.write(buffer, 0, readBytes);
                    offset += (long) readBytes;
                } else {
                    out.flush();
                    return;
                }
            }
        } finally {
            newSourceNoCache.close();
        }
    }

    private String format(String pattern, Object... args) {
        return String.format(Locale.US, pattern, args);
    }

    /* access modifiers changed from: protected */
    public void onCachePercentsAvailableChanged(int percents) {
        if (this.listener != null) {
            this.listener.onCacheAvailable(this.cache.file, this.source.getUrl(), percents);
        }
    }
}
