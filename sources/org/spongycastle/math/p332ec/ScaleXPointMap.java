package org.spongycastle.math.p332ec;

/* renamed from: org.spongycastle.math.ec.ScaleXPointMap */
public class ScaleXPointMap implements ECPointMap {
    protected final ECFieldElement scale;

    public ScaleXPointMap(ECFieldElement scale2) {
        this.scale = scale2;
    }

    public ECPoint map(ECPoint p) {
        return p.scaleX(this.scale);
    }
}
