package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.util.Arrays;

public final class KDFDoublePipelineIterationParameters implements DerivationParameters {
    private static final int UNUSED_R = 32;
    private final byte[] fixedInputData;

    /* renamed from: ki */
    private final byte[] f6852ki;

    /* renamed from: r */
    private final int f6853r;
    private final boolean useCounter;

    private KDFDoublePipelineIterationParameters(byte[] ki, byte[] fixedInputData2, int r, boolean useCounter2) {
        if (ki == null) {
            throw new IllegalArgumentException("A KDF requires Ki (a seed) as input");
        }
        this.f6852ki = Arrays.clone(ki);
        if (fixedInputData2 == null) {
            this.fixedInputData = new byte[0];
        } else {
            this.fixedInputData = Arrays.clone(fixedInputData2);
        }
        if (r == 8 || r == 16 || r == 24 || r == 32) {
            this.f6853r = r;
            this.useCounter = useCounter2;
            return;
        }
        throw new IllegalArgumentException("Length of counter should be 8, 16, 24 or 32");
    }

    public static KDFDoublePipelineIterationParameters createWithCounter(byte[] ki, byte[] fixedInputData2, int r) {
        return new KDFDoublePipelineIterationParameters(ki, fixedInputData2, r, true);
    }

    public static KDFDoublePipelineIterationParameters createWithoutCounter(byte[] ki, byte[] fixedInputData2) {
        return new KDFDoublePipelineIterationParameters(ki, fixedInputData2, 32, false);
    }

    public byte[] getKI() {
        return this.f6852ki;
    }

    public boolean useCounter() {
        return this.useCounter;
    }

    public int getR() {
        return this.f6853r;
    }

    public byte[] getFixedInputData() {
        return Arrays.clone(this.fixedInputData);
    }
}
