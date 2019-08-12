package org.spongycastle.crypto.params;

public class GOST3410ValidationParameters {

    /* renamed from: c */
    private int f6847c;

    /* renamed from: cL */
    private long f6848cL;

    /* renamed from: x0 */
    private int f6849x0;
    private long x0L;

    public GOST3410ValidationParameters(int x0, int c) {
        this.f6849x0 = x0;
        this.f6847c = c;
    }

    public GOST3410ValidationParameters(long x0L2, long cL) {
        this.x0L = x0L2;
        this.f6848cL = cL;
    }

    public int getC() {
        return this.f6847c;
    }

    public int getX0() {
        return this.f6849x0;
    }

    public long getCL() {
        return this.f6848cL;
    }

    public long getX0L() {
        return this.x0L;
    }

    public boolean equals(Object o) {
        if (!(o instanceof GOST3410ValidationParameters)) {
            return false;
        }
        GOST3410ValidationParameters other = (GOST3410ValidationParameters) o;
        if (other.f6847c == this.f6847c && other.f6849x0 == this.f6849x0 && other.f6848cL == this.f6848cL && other.x0L == this.x0L) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f6849x0 ^ this.f6847c) ^ ((int) this.x0L)) ^ ((int) (this.x0L >> 32))) ^ ((int) this.f6848cL)) ^ ((int) (this.f6848cL >> 32));
    }
}
