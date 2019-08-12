package org.spongycastle.math.p332ec;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.endo.GLVEndomorphism;

/* renamed from: org.spongycastle.math.ec.GLVMultiplier */
public class GLVMultiplier extends AbstractECMultiplier {
    protected final ECCurve curve;
    protected final GLVEndomorphism glvEndomorphism;

    public GLVMultiplier(ECCurve curve2, GLVEndomorphism glvEndomorphism2) {
        if (curve2 == null || curve2.getOrder() == null) {
            throw new IllegalArgumentException("Need curve with known group order");
        }
        this.curve = curve2;
        this.glvEndomorphism = glvEndomorphism2;
    }

    /* access modifiers changed from: protected */
    public ECPoint multiplyPositive(ECPoint p, BigInteger k) {
        if (!this.curve.equals(p.getCurve())) {
            throw new IllegalStateException();
        }
        BigInteger[] ab = this.glvEndomorphism.decomposeScalar(k.mod(p.getCurve().getOrder()));
        BigInteger a = ab[0];
        BigInteger b = ab[1];
        ECPointMap pointMap = this.glvEndomorphism.getPointMap();
        if (this.glvEndomorphism.hasEfficientPointMap()) {
            return ECAlgorithms.implShamirsTrickWNaf(p, a, pointMap, b);
        }
        return ECAlgorithms.implShamirsTrickWNaf(p, a, pointMap.map(p), b);
    }
}
