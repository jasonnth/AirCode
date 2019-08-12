package org.spongycastle.math.p332ec;

import org.spongycastle.math.p332ec.ECPoint.AbstractF2m;

/* renamed from: org.spongycastle.math.ec.WTauNafPreCompInfo */
public class WTauNafPreCompInfo implements PreCompInfo {
    protected AbstractF2m[] preComp = null;

    public AbstractF2m[] getPreComp() {
        return this.preComp;
    }

    public void setPreComp(AbstractF2m[] preComp2) {
        this.preComp = preComp2;
    }
}
