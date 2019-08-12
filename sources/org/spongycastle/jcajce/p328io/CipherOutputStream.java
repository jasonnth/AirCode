package org.spongycastle.jcajce.p328io;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import org.spongycastle.crypto.p327io.InvalidCipherTextIOException;

/* renamed from: org.spongycastle.jcajce.io.CipherOutputStream */
public class CipherOutputStream extends FilterOutputStream {
    private final Cipher cipher;
    private final byte[] oneByte = new byte[1];

    public CipherOutputStream(OutputStream output, Cipher cipher2) {
        super(output);
        this.cipher = cipher2;
    }

    public void write(int b) throws IOException {
        this.oneByte[0] = (byte) b;
        write(this.oneByte, 0, 1);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        byte[] outData = this.cipher.update(b, off, len);
        if (outData != null) {
            this.out.write(outData);
        }
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void close() throws IOException {
        IOException error = null;
        try {
            byte[] outData = this.cipher.doFinal();
            if (outData != null) {
                this.out.write(outData);
            }
        } catch (GeneralSecurityException e) {
            error = new InvalidCipherTextIOException("Error during cipher finalisation", e);
        } catch (Exception e2) {
            error = new IOException("Error closing stream: " + e2);
        }
        try {
            flush();
            this.out.close();
        } catch (IOException e3) {
            if (error == null) {
                error = e3;
            }
        }
        if (error != null) {
            throw error;
        }
    }
}
