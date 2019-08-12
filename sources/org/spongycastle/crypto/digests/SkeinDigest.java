package org.spongycastle.crypto.digests;

import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.params.SkeinParameters;
import org.spongycastle.util.Memoable;

public class SkeinDigest implements ExtendedDigest, Memoable {
    public static final int SKEIN_1024 = 1024;
    public static final int SKEIN_256 = 256;
    public static final int SKEIN_512 = 512;
    private SkeinEngine engine;

    public SkeinDigest(int stateSizeBits, int digestSizeBits) {
        this.engine = new SkeinEngine(stateSizeBits, digestSizeBits);
        init(null);
    }

    public SkeinDigest(SkeinDigest digest) {
        this.engine = new SkeinEngine(digest.engine);
    }

    public void reset(Memoable other) {
        this.engine.reset(((SkeinDigest) other).engine);
    }

    public Memoable copy() {
        return new SkeinDigest(this);
    }

    public String getAlgorithmName() {
        return "Skein-" + (this.engine.getBlockSize() * 8) + "-" + (this.engine.getOutputSize() * 8);
    }

    public int getDigestSize() {
        return this.engine.getOutputSize();
    }

    public int getByteLength() {
        return this.engine.getBlockSize();
    }

    public void init(SkeinParameters params) {
        this.engine.init(params);
    }

    public void reset() {
        this.engine.reset();
    }

    public void update(byte in) {
        this.engine.update(in);
    }

    public void update(byte[] in, int inOff, int len) {
        this.engine.update(in, inOff, len);
    }

    public int doFinal(byte[] out, int outOff) {
        return this.engine.doFinal(out, outOff);
    }
}
