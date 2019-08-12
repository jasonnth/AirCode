package org.spongycastle.crypto.params;

import java.security.SecureRandom;

public class DSAParameterGenerationParameters {
    public static final int DIGITAL_SIGNATURE_USAGE = 1;
    public static final int KEY_ESTABLISHMENT_USAGE = 2;
    private final int certainty;

    /* renamed from: l */
    private final int f6825l;

    /* renamed from: n */
    private final int f6826n;
    private final SecureRandom random;
    private final int usageIndex;

    public DSAParameterGenerationParameters(int L, int N, int certainty2, SecureRandom random2) {
        this(L, N, certainty2, random2, -1);
    }

    public DSAParameterGenerationParameters(int L, int N, int certainty2, SecureRandom random2, int usageIndex2) {
        this.f6825l = L;
        this.f6826n = N;
        this.certainty = certainty2;
        this.usageIndex = usageIndex2;
        this.random = random2;
    }

    public int getL() {
        return this.f6825l;
    }

    public int getN() {
        return this.f6826n;
    }

    public int getCertainty() {
        return this.certainty;
    }

    public SecureRandom getRandom() {
        return this.random;
    }

    public int getUsageIndex() {
        return this.usageIndex;
    }
}
