package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.util.Arrays;

public final class KDFCounterParameters implements DerivationParameters {
    private byte[] fixedInputDataCounterPrefix;
    private byte[] fixedInputDataCounterSuffix;

    /* renamed from: ki */
    private byte[] f6850ki;

    /* renamed from: r */
    private int f6851r;

    public KDFCounterParameters(byte[] ki, byte[] fixedInputDataCounterSuffix2, int r) {
        this(ki, null, fixedInputDataCounterSuffix2, r);
    }

    public KDFCounterParameters(byte[] ki, byte[] fixedInputDataCounterPrefix2, byte[] fixedInputDataCounterSuffix2, int r) {
        if (ki == null) {
            throw new IllegalArgumentException("A KDF requires Ki (a seed) as input");
        }
        this.f6850ki = Arrays.clone(ki);
        if (fixedInputDataCounterPrefix2 == null) {
            this.fixedInputDataCounterPrefix = new byte[0];
        } else {
            this.fixedInputDataCounterPrefix = Arrays.clone(fixedInputDataCounterPrefix2);
        }
        if (fixedInputDataCounterSuffix2 == null) {
            this.fixedInputDataCounterSuffix = new byte[0];
        } else {
            this.fixedInputDataCounterSuffix = Arrays.clone(fixedInputDataCounterSuffix2);
        }
        if (r == 8 || r == 16 || r == 24 || r == 32) {
            this.f6851r = r;
            return;
        }
        throw new IllegalArgumentException("Length of counter should be 8, 16, 24 or 32");
    }

    public byte[] getKI() {
        return this.f6850ki;
    }

    public byte[] getFixedInputData() {
        return Arrays.clone(this.fixedInputDataCounterSuffix);
    }

    public byte[] getFixedInputDataCounterPrefix() {
        return Arrays.clone(this.fixedInputDataCounterPrefix);
    }

    public byte[] getFixedInputDataCounterSuffix() {
        return Arrays.clone(this.fixedInputDataCounterSuffix);
    }

    public int getR() {
        return this.f6851r;
    }
}
