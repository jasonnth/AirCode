package com.jumio.core.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface EncryptionProvider {
    OutputStream createRequest(OutputStream outputStream, int i, String str) throws Exception;

    String getResponse(InputStream inputStream) throws IOException, NetworkException;
}
