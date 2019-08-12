package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;

public class SSL3Mac implements Mac {
    static final byte[] IPAD = genPad(IPAD_BYTE, 48);
    private static final byte IPAD_BYTE = 54;
    static final byte[] OPAD = genPad(OPAD_BYTE, 48);
    private static final byte OPAD_BYTE = 92;
    private Digest digest;
    private int padLength;
    private byte[] secret;

    public SSL3Mac(Digest digest2) {
        this.digest = digest2;
        if (digest2.getDigestSize() == 20) {
            this.padLength = 40;
        } else {
            this.padLength = 48;
        }
    }

    public String getAlgorithmName() {
        return this.digest.getAlgorithmName() + "/SSL3MAC";
    }

    public Digest getUnderlyingDigest() {
        return this.digest;
    }

    public void init(CipherParameters params) {
        this.secret = Arrays.clone(((KeyParameter) params).getKey());
        reset();
    }

    public int getMacSize() {
        return this.digest.getDigestSize();
    }

    public void update(byte in) {
        this.digest.update(in);
    }

    public void update(byte[] in, int inOff, int len) {
        this.digest.update(in, inOff, len);
    }

    public int doFinal(byte[] out, int outOff) {
        byte[] tmp = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(tmp, 0);
        this.digest.update(this.secret, 0, this.secret.length);
        this.digest.update(OPAD, 0, this.padLength);
        this.digest.update(tmp, 0, tmp.length);
        int len = this.digest.doFinal(out, outOff);
        reset();
        return len;
    }

    public void reset() {
        this.digest.reset();
        this.digest.update(this.secret, 0, this.secret.length);
        this.digest.update(IPAD, 0, this.padLength);
    }

    private static byte[] genPad(byte b, int count) {
        byte[] padding = new byte[count];
        Arrays.fill(padding, b);
        return padding;
    }
}
