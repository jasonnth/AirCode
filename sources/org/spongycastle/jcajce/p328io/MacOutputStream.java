package org.spongycastle.jcajce.p328io;

import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Mac;

/* renamed from: org.spongycastle.jcajce.io.MacOutputStream */
public final class MacOutputStream extends OutputStream {
    private Mac mac;

    public MacOutputStream(Mac mac2) {
        this.mac = mac2;
    }

    public void write(int b) throws IOException {
        this.mac.update((byte) b);
    }

    public void write(byte[] bytes, int off, int len) throws IOException {
        this.mac.update(bytes, off, len);
    }

    public byte[] getMac() {
        return this.mac.doFinal();
    }
}
