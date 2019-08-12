package org.spongycastle.crypto.p327io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.crypto.Signer;

/* renamed from: org.spongycastle.crypto.io.SignerInputStream */
public class SignerInputStream extends FilterInputStream {
    protected Signer signer;

    public SignerInputStream(InputStream stream, Signer signer2) {
        super(stream);
        this.signer = signer2;
    }

    public int read() throws IOException {
        int b = this.in.read();
        if (b >= 0) {
            this.signer.update((byte) b);
        }
        return b;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int n = this.in.read(b, off, len);
        if (n > 0) {
            this.signer.update(b, off, n);
        }
        return n;
    }

    public Signer getSigner() {
        return this.signer;
    }
}
