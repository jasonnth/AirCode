package org.spongycastle.pqc.crypto.rainbow;

public class RainbowPrivateKeyParameters extends RainbowKeyParameters {
    private short[][] A1inv;
    private short[][] A2inv;

    /* renamed from: b1 */
    private short[] f7179b1;

    /* renamed from: b2 */
    private short[] f7180b2;
    private Layer[] layers;

    /* renamed from: vi */
    private int[] f7181vi;

    public RainbowPrivateKeyParameters(short[][] A1inv2, short[] b1, short[][] A2inv2, short[] b2, int[] vi, Layer[] layers2) {
        super(true, vi[vi.length - 1] - vi[0]);
        this.A1inv = A1inv2;
        this.f7179b1 = b1;
        this.A2inv = A2inv2;
        this.f7180b2 = b2;
        this.f7181vi = vi;
        this.layers = layers2;
    }

    public short[] getB1() {
        return this.f7179b1;
    }

    public short[][] getInvA1() {
        return this.A1inv;
    }

    public short[] getB2() {
        return this.f7180b2;
    }

    public short[][] getInvA2() {
        return this.A2inv;
    }

    public Layer[] getLayers() {
        return this.layers;
    }

    public int[] getVi() {
        return this.f7181vi;
    }
}
