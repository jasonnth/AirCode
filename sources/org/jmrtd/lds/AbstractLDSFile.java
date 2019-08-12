package org.jmrtd.lds;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

abstract class AbstractLDSFile implements LDSFile {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = -4908935713109830409L;

    /* access modifiers changed from: protected */
    public abstract void readObject(InputStream inputStream) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void writeObject(OutputStream outputStream) throws IOException;

    AbstractLDSFile() {
    }

    public byte[] getEncoded() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            writeObject(byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            return null;
        }
    }
}
