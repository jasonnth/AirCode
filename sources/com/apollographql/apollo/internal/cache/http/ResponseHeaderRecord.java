package com.apollographql.apollo.internal.cache.http;

import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import okhttp3.CipherSuite;
import okhttp3.Handshake;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.TlsVersion;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Sink;
import okio.Source;

final class ResponseHeaderRecord {
    private static final String RECEIVED_MILLIS = (Platform.get().getPrefix() + "-Received-Millis");
    private static final String SENT_MILLIS = (Platform.get().getPrefix() + "-Sent-Millis");
    private final int code;
    private final Handshake handshake;
    private final String message;
    private final Protocol protocol;
    private final long receivedResponseMillis;
    private final String requestMethod;
    private final Headers responseHeaders;
    private final long sentRequestMillis;
    private final String url;
    private final Headers varyHeaders;

    ResponseHeaderRecord(Source in) throws IOException {
        try {
            BufferedSource source = Okio.buffer(in);
            this.url = source.readUtf8LineStrict();
            this.requestMethod = source.readUtf8LineStrict();
            Builder varyHeadersBuilder = new Builder();
            int varyRequestHeaderLineCount = readInt(source);
            for (int i = 0; i < varyRequestHeaderLineCount; i++) {
                addHeaderLenient(varyHeadersBuilder, source.readUtf8LineStrict());
            }
            this.varyHeaders = varyHeadersBuilder.build();
            StatusLine statusLine = StatusLine.parse(source.readUtf8LineStrict());
            this.protocol = statusLine.protocol;
            this.code = statusLine.code;
            this.message = statusLine.message;
            Builder responseHeadersBuilder = new Builder();
            int responseHeaderLineCount = readInt(source);
            for (int i2 = 0; i2 < responseHeaderLineCount; i2++) {
                addHeaderLenient(responseHeadersBuilder, source.readUtf8LineStrict());
            }
            String sendRequestMillisString = responseHeadersBuilder.get(SENT_MILLIS);
            String receivedResponseMillisString = responseHeadersBuilder.get(RECEIVED_MILLIS);
            responseHeadersBuilder.removeAll(SENT_MILLIS);
            responseHeadersBuilder.removeAll(RECEIVED_MILLIS);
            this.sentRequestMillis = sendRequestMillisString != null ? Long.parseLong(sendRequestMillisString) : 0;
            this.receivedResponseMillis = receivedResponseMillisString != null ? Long.parseLong(receivedResponseMillisString) : 0;
            this.responseHeaders = responseHeadersBuilder.build();
            if (isHttps()) {
                String blank = source.readUtf8LineStrict();
                if (blank.length() > 0) {
                    throw new IOException("expected \"\" but was \"" + blank + "\"");
                }
                this.handshake = Handshake.get(!source.exhausted() ? TlsVersion.forJavaName(source.readUtf8LineStrict()) : null, CipherSuite.forJavaName(source.readUtf8LineStrict()), readCertificateList(source), readCertificateList(source));
            } else {
                this.handshake = null;
            }
        } finally {
            in.close();
        }
    }

    ResponseHeaderRecord(Response response) {
        this.url = response.request().url().toString();
        this.varyHeaders = HttpHeaders.varyHeaders(response);
        this.requestMethod = response.request().method();
        this.protocol = response.protocol();
        this.code = response.code();
        this.message = response.message();
        this.responseHeaders = response.headers();
        this.handshake = response.handshake();
        this.sentRequestMillis = response.sentRequestAtMillis();
        this.receivedResponseMillis = response.receivedResponseAtMillis();
    }

    /* access modifiers changed from: 0000 */
    public void writeTo(Sink sink) throws IOException {
        BufferedSink bufferedSink = Okio.buffer(sink);
        bufferedSink.writeUtf8(this.url).writeByte(10);
        bufferedSink.writeUtf8(this.requestMethod).writeByte(10);
        bufferedSink.writeDecimalLong((long) this.varyHeaders.size()).writeByte(10);
        int size = this.varyHeaders.size();
        for (int i = 0; i < size; i++) {
            bufferedSink.writeUtf8(this.varyHeaders.name(i)).writeUtf8(": ").writeUtf8(this.varyHeaders.value(i)).writeByte(10);
        }
        bufferedSink.writeUtf8(new StatusLine(this.protocol, this.code, this.message).toString()).writeByte(10);
        bufferedSink.writeDecimalLong((long) (this.responseHeaders.size() + 2)).writeByte(10);
        int size2 = this.responseHeaders.size();
        for (int i2 = 0; i2 < size2; i2++) {
            bufferedSink.writeUtf8(this.responseHeaders.name(i2)).writeUtf8(": ").writeUtf8(this.responseHeaders.value(i2)).writeByte(10);
        }
        bufferedSink.writeUtf8(SENT_MILLIS).writeUtf8(": ").writeDecimalLong(this.sentRequestMillis).writeByte(10);
        bufferedSink.writeUtf8(RECEIVED_MILLIS).writeUtf8(": ").writeDecimalLong(this.receivedResponseMillis).writeByte(10);
        if (isHttps()) {
            bufferedSink.writeByte(10);
            bufferedSink.writeUtf8(this.handshake.cipherSuite().javaName()).writeByte(10);
            writeCertList(bufferedSink, this.handshake.peerCertificates());
            writeCertList(bufferedSink, this.handshake.localCertificates());
            if (this.handshake.tlsVersion() != null) {
                bufferedSink.writeUtf8(this.handshake.tlsVersion().javaName()).writeByte(10);
            }
        }
        bufferedSink.close();
    }

    private boolean isHttps() {
        return this.url.startsWith("https://");
    }

    private List<Certificate> readCertificateList(BufferedSource source) throws IOException {
        int length = readInt(source);
        if (length == -1) {
            return Collections.emptyList();
        }
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            List<Certificate> result = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                String line = source.readUtf8LineStrict();
                Buffer bytes = new Buffer();
                bytes.write(ByteString.decodeBase64(line));
                result.add(certificateFactory.generateCertificate(bytes.inputStream()));
            }
            return result;
        } catch (CertificateException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void writeCertList(BufferedSink sink, List<Certificate> certificates) throws IOException {
        try {
            sink.writeDecimalLong((long) certificates.size()).writeByte(10);
            int size = certificates.size();
            for (int i = 0; i < size; i++) {
                sink.writeUtf8(ByteString.m3949of(((Certificate) certificates.get(i)).getEncoded()).base64()).writeByte(10);
            }
        } catch (CertificateEncodingException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* access modifiers changed from: 0000 */
    public Response response() {
        return new Response.Builder().request(new Request.Builder().url(this.url).method(this.requestMethod, RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "")).headers(this.varyHeaders).build()).protocol(this.protocol).code(this.code).message(this.message).headers(this.responseHeaders).handshake(this.handshake).sentRequestAtMillis(this.sentRequestMillis).receivedResponseAtMillis(this.receivedResponseMillis).build();
    }

    private static int readInt(BufferedSource source) throws IOException {
        try {
            long result = source.readDecimalLong();
            String line = source.readUtf8LineStrict();
            if (result >= 0 && result <= 2147483647L && line.isEmpty()) {
                return (int) result;
            }
            throw new IOException("expected an int but was \"" + result + line + "\"");
        } catch (NumberFormatException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void addHeaderLenient(Builder headersBuilder, String line) {
        int index = line.indexOf(":", 1);
        if (index != -1) {
            headersBuilder.add(line.substring(0, index), line.substring(index + 1));
        } else if (line.startsWith(":")) {
            headersBuilder.add("", line.substring(1));
        } else {
            headersBuilder.add("", line);
        }
    }
}
