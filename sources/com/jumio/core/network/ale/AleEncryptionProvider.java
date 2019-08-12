package com.jumio.core.network.ale;

import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.jumio.ale.swig.ALECore;
import com.jumio.ale.swig.ALEHeader;
import com.jumio.ale.swig.ALEInputStream;
import com.jumio.ale.swig.ALEOutputStream;
import com.jumio.ale.swig.ALERequest;
import com.jumio.commons.log.Log;
import com.jumio.commons.remote.exception.UnexpectedResponseException;
import com.jumio.commons.utils.IOUtils;
import com.jumio.core.network.EncryptionProvider;
import com.jumio.core.network.NetworkException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import javax.net.ssl.SSLException;
import p005cn.jpush.android.JPushConstants;

public class AleEncryptionProvider implements EncryptionProvider {
    static final String TAG = "AleEncryptionProvider";
    private ALERequest aleRequest;
    private ALECore mAleCore;
    private final String mCredentials;

    public AleEncryptionProvider(ALECore aleCore, String credentials) {
        this.mCredentials = credentials;
        this.mAleCore = aleCore;
    }

    public OutputStream createRequest(OutputStream outputStream, int dataLength, String boundary) throws Exception {
        try {
            this.aleRequest = this.mAleCore.createRequest();
            Log.m1909d(TAG, "encrypting plaintext");
            ALEHeader aleHeader = new ALEHeader();
            if (boundary != null) {
                aleHeader.add(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE, "multipart/form-data; boundary=" + boundary);
            } else {
                aleHeader.add(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE, "application/json");
            }
            aleHeader.add("Authorization", "Basic " + this.mCredentials);
            return new ALEOutputStream(new BufferedOutputStream(outputStream), this.aleRequest, aleHeader, dataLength);
        } catch (Exception e) {
            throw new SSLException(e.getMessage());
        }
    }

    public String getResponse(InputStream inputStream) throws NetworkException, UnexpectedResponseException {
        String plainTextAnswer;
        Log.m1909d(TAG, "decrypting response stream");
        InputStream is = new ALEInputStream(new BufferedInputStream(inputStream), this.aleRequest);
        try {
            plainTextAnswer = readPlainInput(is);
            Log.m1924v("Network/ALE", "Response " + (this.aleRequest.isVerified() ? "valid" : "invalid"));
            Log.m1924v("Network/ALE", "Errorcode " + this.aleRequest.getErrorCode());
            Log.m1924v("Network/ALE", "KeyUpdate " + (this.aleRequest.isKeyUpdate() ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE));
        } catch (IOException e) {
            plainTextAnswer = null;
        } finally {
            IOUtils.closeQuietly(is);
        }
        try {
            if (!this.aleRequest.isVerified()) {
                if (this.aleRequest.getErrorCode() != 0) {
                    throw new UnexpectedResponseException(this.aleRequest.getErrorCode(), "Response returned " + this.aleRequest.getErrorCode());
                }
                throw new UnexpectedResponseException(0, "Response not verified");
            } else if (!this.aleRequest.isKeyUpdate()) {
                return plainTextAnswer;
            } else {
                throw new AleKeyUpdateException("keyupdate - re-execute call!");
            }
        } finally {
            this.mAleCore.destroyRequest(this.aleRequest);
            this.aleRequest = null;
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public String readPlainInput(InputStream is) throws IOException {
        if (is == null) {
            return null;
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, JPushConstants.ENCODING_UTF_8));
            while (true) {
                int numBytes = reader.read(buffer);
                if (numBytes != -1) {
                    writer.write(buffer, 0, numBytes);
                } else {
                    is.close();
                    return writer.toString();
                }
            }
        } catch (Throwable th) {
            is.close();
            throw th;
        }
    }
}
