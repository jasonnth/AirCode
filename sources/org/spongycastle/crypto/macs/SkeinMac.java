package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.digests.SkeinEngine;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.SkeinParameters;
import org.spongycastle.crypto.params.SkeinParameters.Builder;

public class SkeinMac implements Mac {
    public static final int SKEIN_1024 = 1024;
    public static final int SKEIN_256 = 256;
    public static final int SKEIN_512 = 512;
    private SkeinEngine engine;

    public SkeinMac(int stateSizeBits, int digestSizeBits) {
        this.engine = new SkeinEngine(stateSizeBits, digestSizeBits);
    }

    public SkeinMac(SkeinMac mac) {
        this.engine = new SkeinEngine(mac.engine);
    }

    public String getAlgorithmName() {
        return "Skein-MAC-" + (this.engine.getBlockSize() * 8) + "-" + (this.engine.getOutputSize() * 8);
    }

    public void init(CipherParameters params) throws IllegalArgumentException {
        SkeinParameters skeinParameters;
        if (params instanceof SkeinParameters) {
            skeinParameters = (SkeinParameters) params;
        } else if (params instanceof KeyParameter) {
            skeinParameters = new Builder().setKey(((KeyParameter) params).getKey()).build();
        } else {
            throw new IllegalArgumentException("Invalid parameter passed to Skein MAC init - " + params.getClass().getName());
        }
        if (skeinParameters.getKey() == null) {
            throw new IllegalArgumentException("Skein MAC requires a key parameter.");
        }
        this.engine.init(skeinParameters);
    }

    public int getMacSize() {
        return this.engine.getOutputSize();
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
