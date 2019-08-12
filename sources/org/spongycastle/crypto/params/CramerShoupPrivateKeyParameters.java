package org.spongycastle.crypto.params;

import java.math.BigInteger;

public class CramerShoupPrivateKeyParameters extends CramerShoupKeyParameters {

    /* renamed from: pk */
    private CramerShoupPublicKeyParameters f6808pk;

    /* renamed from: x1 */
    private BigInteger f6809x1;

    /* renamed from: x2 */
    private BigInteger f6810x2;

    /* renamed from: y1 */
    private BigInteger f6811y1;

    /* renamed from: y2 */
    private BigInteger f6812y2;

    /* renamed from: z */
    private BigInteger f6813z;

    public CramerShoupPrivateKeyParameters(CramerShoupParameters params, BigInteger x1, BigInteger x2, BigInteger y1, BigInteger y2, BigInteger z) {
        super(true, params);
        this.f6809x1 = x1;
        this.f6810x2 = x2;
        this.f6811y1 = y1;
        this.f6812y2 = y2;
        this.f6813z = z;
    }

    public BigInteger getX1() {
        return this.f6809x1;
    }

    public BigInteger getX2() {
        return this.f6810x2;
    }

    public BigInteger getY1() {
        return this.f6811y1;
    }

    public BigInteger getY2() {
        return this.f6812y2;
    }

    public BigInteger getZ() {
        return this.f6813z;
    }

    public void setPk(CramerShoupPublicKeyParameters pk) {
        this.f6808pk = pk;
    }

    public CramerShoupPublicKeyParameters getPk() {
        return this.f6808pk;
    }

    public int hashCode() {
        return ((((this.f6809x1.hashCode() ^ this.f6810x2.hashCode()) ^ this.f6811y1.hashCode()) ^ this.f6812y2.hashCode()) ^ this.f6813z.hashCode()) ^ super.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CramerShoupPrivateKeyParameters)) {
            return false;
        }
        CramerShoupPrivateKeyParameters other = (CramerShoupPrivateKeyParameters) obj;
        if (!other.getX1().equals(this.f6809x1) || !other.getX2().equals(this.f6810x2) || !other.getY1().equals(this.f6811y1) || !other.getY2().equals(this.f6812y2) || !other.getZ().equals(this.f6813z) || !super.equals(obj)) {
            return false;
        }
        return true;
    }
}
