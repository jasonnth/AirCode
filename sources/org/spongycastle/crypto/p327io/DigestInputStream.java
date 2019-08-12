package org.spongycastle.crypto.p327io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.crypto.Digest;

/* renamed from: org.spongycastle.crypto.io.DigestInputStream */
public class DigestInputStream extends FilterInputStream {
    protected Digest digest;

    public DigestInputStream(InputStream stream, Digest digest2) {
        super(stream);
        this.digest = digest2;
    }

    public int read() throws IOException {
        int b = this.in.read();
        if (b >= 0) {
            this.digest.update((byte) b);
        }
        return b;
    }

    public int read(byte[] b, int off, int len) throws IOException {
        int n = this.in.read(b, off, len);
        if (n > 0) {
            this.digest.update(b, off, n);
        }
        return n;
    }

    public Digest getDigest() {
        return this.digest;
    }
}
