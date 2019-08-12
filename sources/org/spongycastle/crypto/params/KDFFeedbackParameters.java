package org.spongycastle.crypto.params;

import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.util.Arrays;

public final class KDFFeedbackParameters implements DerivationParameters {
    private static final int UNUSED_R = -1;
    private final byte[] fixedInputData;

    /* renamed from: iv */
    private final byte[] f6854iv;

    /* renamed from: ki */
    private final byte[] f6855ki;

    /* renamed from: r */
    private final int f6856r;
    private final boolean useCounter;

    private KDFFeedbackParameters(byte[] ki, byte[] iv, byte[] fixedInputData2, int r, boolean useCounter2) {
        if (ki == null) {
            throw new IllegalArgumentException("A KDF requires Ki (a seed) as input");
        }
        this.f6855ki = Arrays.clone(ki);
        if (fixedInputData2 == null) {
            this.fixedInputData = new byte[0];
        } else {
            this.fixedInputData = Arrays.clone(fixedInputData2);
        }
        this.f6856r = r;
        if (iv == null) {
            this.f6854iv = new byte[0];
        } else {
            this.f6854iv = Arrays.clone(iv);
        }
        this.useCounter = useCounter2;
    }

    public static KDFFeedbackParameters createWithCounter(byte[] ki, byte[] iv, byte[] fixedInputData2, int r) {
        if (r == 8 || r == 16 || r == 24 || r == 32) {
            return new KDFFeedbackParameters(ki, iv, fixedInputData2, r, true);
        }
        throw new IllegalArgumentException("Length of counter should be 8, 16, 24 or 32");
    }

    public static KDFFeedbackParameters createWithoutCounter(byte[] ki, byte[] iv, byte[] fixedInputData2) {
        return new KDFFeedbackParameters(ki, iv, fixedInputData2, -1, false);
    }

    public byte[] getKI() {
        return this.f6855ki;
    }

    public byte[] getIV() {
        return this.f6854iv;
    }

    public boolean useCounter() {
        return this.useCounter;
    }

    public int getR() {
        return this.f6856r;
    }

    public byte[] getFixedInputData() {
        return Arrays.clone(this.fixedInputData);
    }
}
