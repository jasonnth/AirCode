package org.spongycastle.pqc.crypto.ntru;

import java.nio.ByteBuffer;
import org.spongycastle.crypto.Digest;

public class NTRUSignerPrng {
    private int counter = 0;
    private Digest hashAlg;
    private byte[] seed;

    NTRUSignerPrng(byte[] seed2, Digest hashAlg2) {
        this.seed = seed2;
        this.hashAlg = hashAlg2;
    }

    /* access modifiers changed from: 0000 */
    public byte[] nextBytes(int n) {
        ByteBuffer buf = ByteBuffer.allocate(n);
        while (buf.hasRemaining()) {
            ByteBuffer cbuf = ByteBuffer.allocate(this.seed.length + 4);
            cbuf.put(this.seed);
            cbuf.putInt(this.counter);
            byte[] array = cbuf.array();
            byte[] hash = new byte[this.hashAlg.getDigestSize()];
            this.hashAlg.update(array, 0, array.length);
            this.hashAlg.doFinal(hash, 0);
            if (buf.remaining() < hash.length) {
                buf.put(hash, 0, buf.remaining());
            } else {
                buf.put(hash);
            }
            this.counter++;
        }
        return buf.array();
    }
}
