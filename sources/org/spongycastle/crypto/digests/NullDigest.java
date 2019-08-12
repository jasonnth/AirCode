package org.spongycastle.crypto.digests;

import java.io.ByteArrayOutputStream;
import org.spongycastle.crypto.Digest;

public class NullDigest implements Digest {
    private ByteArrayOutputStream bOut = new ByteArrayOutputStream();

    public String getAlgorithmName() {
        return "NULL";
    }

    public int getDigestSize() {
        return this.bOut.size();
    }

    public void update(byte in) {
        this.bOut.write(in);
    }

    public void update(byte[] in, int inOff, int len) {
        this.bOut.write(in, inOff, len);
    }

    public int doFinal(byte[] out, int outOff) {
        byte[] res = this.bOut.toByteArray();
        System.arraycopy(res, 0, out, outOff, res.length);
        reset();
        return res.length;
    }

    public void reset() {
        this.bOut.reset();
    }
}
