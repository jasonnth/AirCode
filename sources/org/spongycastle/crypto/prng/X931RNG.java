package org.spongycastle.crypto.prng;

import org.spongycastle.crypto.BlockCipher;

public class X931RNG {
    private static final int BLOCK128_MAX_BITS_REQUEST = 262144;
    private static final long BLOCK128_RESEED_MAX = 8388608;
    private static final int BLOCK64_MAX_BITS_REQUEST = 4096;
    private static final long BLOCK64_RESEED_MAX = 32768;

    /* renamed from: DT */
    private final byte[] f6871DT;

    /* renamed from: I */
    private final byte[] f6872I;

    /* renamed from: R */
    private final byte[] f6873R;

    /* renamed from: V */
    private byte[] f6874V;
    private final BlockCipher engine;
    private final EntropySource entropySource;
    private long reseedCounter = 1;

    public X931RNG(BlockCipher engine2, byte[] dateTimeVector, EntropySource entropySource2) {
        this.engine = engine2;
        this.entropySource = entropySource2;
        this.f6871DT = new byte[engine2.getBlockSize()];
        System.arraycopy(dateTimeVector, 0, this.f6871DT, 0, this.f6871DT.length);
        this.f6872I = new byte[engine2.getBlockSize()];
        this.f6873R = new byte[engine2.getBlockSize()];
    }

    /* access modifiers changed from: 0000 */
    public int generate(byte[] output, boolean predictionResistant) {
        if (this.f6873R.length == 8) {
            if (this.reseedCounter > 32768) {
                return -1;
            }
            if (isTooLarge(output, 512)) {
                throw new IllegalArgumentException("Number of bits per request limited to 4096");
            }
        } else if (this.reseedCounter > BLOCK128_RESEED_MAX) {
            return -1;
        } else {
            if (isTooLarge(output, 32768)) {
                throw new IllegalArgumentException("Number of bits per request limited to 262144");
            }
        }
        if (predictionResistant || this.f6874V == null) {
            this.f6874V = this.entropySource.getEntropy();
            if (this.f6874V.length != this.engine.getBlockSize()) {
                throw new IllegalStateException("Insufficient entropy returned");
            }
        }
        int m = output.length / this.f6873R.length;
        for (int i = 0; i < m; i++) {
            this.engine.processBlock(this.f6871DT, 0, this.f6872I, 0);
            process(this.f6873R, this.f6872I, this.f6874V);
            process(this.f6874V, this.f6873R, this.f6872I);
            System.arraycopy(this.f6873R, 0, output, this.f6873R.length * i, this.f6873R.length);
            increment(this.f6871DT);
        }
        int bytesToCopy = output.length - (this.f6873R.length * m);
        if (bytesToCopy > 0) {
            this.engine.processBlock(this.f6871DT, 0, this.f6872I, 0);
            process(this.f6873R, this.f6872I, this.f6874V);
            process(this.f6874V, this.f6873R, this.f6872I);
            System.arraycopy(this.f6873R, 0, output, this.f6873R.length * m, bytesToCopy);
            increment(this.f6871DT);
        }
        this.reseedCounter++;
        return output.length;
    }

    /* access modifiers changed from: 0000 */
    public void reseed() {
        this.f6874V = this.entropySource.getEntropy();
        if (this.f6874V.length != this.engine.getBlockSize()) {
            throw new IllegalStateException("Insufficient entropy returned");
        }
        this.reseedCounter = 1;
    }

    /* access modifiers changed from: 0000 */
    public EntropySource getEntropySource() {
        return this.entropySource;
    }

    private void process(byte[] res, byte[] a, byte[] b) {
        for (int i = 0; i != res.length; i++) {
            res[i] = (byte) (a[i] ^ b[i]);
        }
        this.engine.processBlock(res, 0, res, 0);
    }

    private void increment(byte[] val) {
        int i = val.length - 1;
        while (i >= 0) {
            byte b = (byte) (val[i] + 1);
            val[i] = b;
            if (b == 0) {
                i--;
            } else {
                return;
            }
        }
    }

    private static boolean isTooLarge(byte[] bytes, int maxBytes) {
        return bytes != null && bytes.length > maxBytes;
    }
}
