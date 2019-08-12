package com.danikula.videocache;

import android.text.TextUtils;
import com.danikula.videocache.sourcestorage.SourceInfoStorage;
import com.danikula.videocache.sourcestorage.SourceInfoStorageFactory;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUrlSource implements Source {
    private static final Logger LOG = LoggerFactory.getLogger("HttpUrlSource");
    private HttpURLConnection connection;
    private InputStream inputStream;
    private SourceInfo sourceInfo;
    private final SourceInfoStorage sourceInfoStorage;

    public HttpUrlSource(String url) {
        this(url, SourceInfoStorageFactory.newEmptySourceInfoStorage());
    }

    public HttpUrlSource(String url, SourceInfoStorage sourceInfoStorage2) {
        this.sourceInfoStorage = (SourceInfoStorage) Preconditions.checkNotNull(sourceInfoStorage2);
        SourceInfo sourceInfo2 = sourceInfoStorage2.get(url);
        if (sourceInfo2 == null) {
            sourceInfo2 = new SourceInfo(url, -2147483648L, ProxyCacheUtils.getSupposablyMime(url));
        }
        this.sourceInfo = sourceInfo2;
    }

    public HttpUrlSource(HttpUrlSource source) {
        this.sourceInfo = source.sourceInfo;
        this.sourceInfoStorage = source.sourceInfoStorage;
    }

    public synchronized long length() throws ProxyCacheException {
        if (this.sourceInfo.length == -2147483648L) {
            fetchContentInfo();
        }
        return this.sourceInfo.length;
    }

    public void open(long offset) throws ProxyCacheException {
        try {
            this.connection = openConnection(offset, -1);
            String mime = this.connection.getContentType();
            this.inputStream = new BufferedInputStream(this.connection.getInputStream(), 8192);
            this.sourceInfo = new SourceInfo(this.sourceInfo.url, readSourceAvailableBytes(this.connection, offset, this.connection.getResponseCode()), mime);
            this.sourceInfoStorage.put(this.sourceInfo.url, this.sourceInfo);
        } catch (IOException e) {
            throw new ProxyCacheException("Error opening connection for " + this.sourceInfo.url + " with offset " + offset, e);
        }
    }

    private long readSourceAvailableBytes(HttpURLConnection connection2, long offset, int responseCode) throws IOException {
        long contentLength = getContentLength(connection2);
        if (responseCode == 200) {
            return contentLength;
        }
        return responseCode == 206 ? contentLength + offset : this.sourceInfo.length;
    }

    private long getContentLength(HttpURLConnection connection2) {
        String contentLengthValue = connection2.getHeaderField("Content-Length");
        if (contentLengthValue == null) {
            return -1;
        }
        return Long.parseLong(contentLengthValue);
    }

    public void close() throws ProxyCacheException {
        if (this.connection != null) {
            try {
                this.connection.disconnect();
            } catch (IllegalArgumentException | NullPointerException e) {
                throw new RuntimeException("Wait... but why? WTF!? Really shouldn't happen any more after fixing https://github.com/danikula/AndroidVideoCache/issues/43. If you read it on your device log, please, notify me danikula@gmail.com or create issue here https://github.com/danikula/AndroidVideoCache/issues.", e);
            } catch (ArrayIndexOutOfBoundsException e2) {
                LOG.error("Error closing connection correctly. Should happen only on Android L. If anybody know how to fix it, please visit https://github.com/danikula/AndroidVideoCache/issues/88. Until good solution is not know, just ignore this issue :(", e2);
            }
        }
    }

    public int read(byte[] buffer) throws ProxyCacheException {
        if (this.inputStream == null) {
            throw new ProxyCacheException("Error reading data from " + this.sourceInfo.url + ": connection is absent!");
        }
        try {
            return this.inputStream.read(buffer, 0, buffer.length);
        } catch (InterruptedIOException e) {
            throw new InterruptedProxyCacheException("Reading source " + this.sourceInfo.url + " is interrupted", e);
        } catch (IOException e2) {
            throw new ProxyCacheException("Error reading data from " + this.sourceInfo.url, e2);
        }
    }

    private void fetchContentInfo() throws ProxyCacheException {
        LOG.debug("Read content info from " + this.sourceInfo.url);
        HttpURLConnection urlConnection = null;
        InputStream inputStream2 = null;
        try {
            urlConnection = openConnection(0, 10000);
            long length = getContentLength(urlConnection);
            String mime = urlConnection.getContentType();
            inputStream2 = urlConnection.getInputStream();
            this.sourceInfo = new SourceInfo(this.sourceInfo.url, length, mime);
            this.sourceInfoStorage.put(this.sourceInfo.url, this.sourceInfo);
            LOG.debug("Source info fetched: " + this.sourceInfo);
            ProxyCacheUtils.close(inputStream2);
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } catch (IOException e) {
            LOG.error("Error fetching info from " + this.sourceInfo.url, e);
            ProxyCacheUtils.close(inputStream2);
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        } catch (Throwable th) {
            ProxyCacheUtils.close(inputStream2);
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            throw th;
        }
    }

    private HttpURLConnection openConnection(long offset, int timeout) throws IOException, ProxyCacheException {
        HttpURLConnection connection2;
        boolean redirected;
        int redirectCount = 0;
        String url = this.sourceInfo.url;
        do {
            LOG.debug("Open connection " + (offset > 0 ? " with offset " + offset : "") + " to " + url);
            connection2 = (HttpURLConnection) new URL(url).openConnection();
            if (offset > 0) {
                connection2.setRequestProperty("Range", "bytes=" + offset + "-");
            }
            if (timeout > 0) {
                connection2.setConnectTimeout(timeout);
                connection2.setReadTimeout(timeout);
            }
            int code = connection2.getResponseCode();
            redirected = code == 301 || code == 302 || code == 303;
            if (redirected) {
                url = connection2.getHeaderField("Location");
                redirectCount++;
                connection2.disconnect();
            }
            if (redirectCount > 5) {
                throw new ProxyCacheException("Too many redirects: " + redirectCount);
            }
        } while (redirected);
        return connection2;
    }

    public synchronized String getMime() throws ProxyCacheException {
        if (TextUtils.isEmpty(this.sourceInfo.mime)) {
            fetchContentInfo();
        }
        return this.sourceInfo.mime;
    }

    public String getUrl() {
        return this.sourceInfo.url;
    }

    public String toString() {
        return "HttpUrlSource{sourceInfo='" + this.sourceInfo + "}";
    }
}
