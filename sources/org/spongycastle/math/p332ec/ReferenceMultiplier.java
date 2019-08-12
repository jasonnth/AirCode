package org.spongycastle.math.p332ec;

import java.math.BigInteger;

/* renamed from: org.spongycastle.math.ec.ReferenceMultiplier */
public class ReferenceMultiplier extends AbstractECMultiplier {
    /* access modifiers changed from: protected */
    public ECPoint multiplyPositive(ECPoint p, BigInteger k) {
        return ECAlgorithms.referenceMultiply(p, k);
    }
}
