package org.spongycastle.math.p332ec;

/* renamed from: org.spongycastle.math.ec.ScaleYPointMap */
public class ScaleYPointMap implements ECPointMap {
    protected final ECFieldElement scale;

    public ScaleYPointMap(ECFieldElement scale2) {
        this.scale = scale2;
    }

    public ECPoint map(ECPoint p) {
        return p.scaleY(this.scale);
    }
}
