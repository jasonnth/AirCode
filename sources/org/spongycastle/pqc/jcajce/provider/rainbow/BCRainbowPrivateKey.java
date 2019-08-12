package org.spongycastle.pqc.jcajce.provider.rainbow;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.Arrays;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.pqc.asn1.PQCObjectIdentifiers;
import org.spongycastle.pqc.asn1.RainbowPrivateKey;
import org.spongycastle.pqc.crypto.rainbow.Layer;
import org.spongycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.spongycastle.pqc.crypto.rainbow.util.RainbowUtil;
import org.spongycastle.pqc.jcajce.spec.RainbowPrivateKeySpec;

public class BCRainbowPrivateKey implements PrivateKey {
    private static final long serialVersionUID = 1;
    private short[][] A1inv;
    private short[][] A2inv;

    /* renamed from: b1 */
    private short[] f7201b1;

    /* renamed from: b2 */
    private short[] f7202b2;
    private Layer[] layers;

    /* renamed from: vi */
    private int[] f7203vi;

    public BCRainbowPrivateKey(short[][] A1inv2, short[] b1, short[][] A2inv2, short[] b2, int[] vi, Layer[] layers2) {
        this.A1inv = A1inv2;
        this.f7201b1 = b1;
        this.A2inv = A2inv2;
        this.f7202b2 = b2;
        this.f7203vi = vi;
        this.layers = layers2;
    }

    public BCRainbowPrivateKey(RainbowPrivateKeySpec keySpec) {
        this(keySpec.getInvA1(), keySpec.getB1(), keySpec.getInvA2(), keySpec.getB2(), keySpec.getVi(), keySpec.getLayers());
    }

    public BCRainbowPrivateKey(RainbowPrivateKeyParameters params) {
        this(params.getInvA1(), params.getB1(), params.getInvA2(), params.getB2(), params.getVi(), params.getLayers());
    }

    public short[][] getInvA1() {
        return this.A1inv;
    }

    public short[] getB1() {
        return this.f7201b1;
    }

    public short[] getB2() {
        return this.f7202b2;
    }

    public short[][] getInvA2() {
        return this.A2inv;
    }

    public Layer[] getLayers() {
        return this.layers;
    }

    public int[] getVi() {
        return this.f7203vi;
    }

    public boolean equals(Object other) {
        boolean eq;
        boolean eq2;
        boolean eq3;
        boolean eq4;
        boolean eq5;
        if (other == null || !(other instanceof BCRainbowPrivateKey)) {
            return false;
        }
        BCRainbowPrivateKey otherKey = (BCRainbowPrivateKey) other;
        if (1 == 0 || !RainbowUtil.equals(this.A1inv, otherKey.getInvA1())) {
            eq = false;
        } else {
            eq = true;
        }
        if (!eq || !RainbowUtil.equals(this.A2inv, otherKey.getInvA2())) {
            eq2 = false;
        } else {
            eq2 = true;
        }
        if (!eq2 || !RainbowUtil.equals(this.f7201b1, otherKey.getB1())) {
            eq3 = false;
        } else {
            eq3 = true;
        }
        if (!eq3 || !RainbowUtil.equals(this.f7202b2, otherKey.getB2())) {
            eq4 = false;
        } else {
            eq4 = true;
        }
        if (!eq4 || !Arrays.equals(this.f7203vi, otherKey.getVi())) {
            eq5 = false;
        } else {
            eq5 = true;
        }
        if (this.layers.length != otherKey.getLayers().length) {
            return false;
        }
        for (int i = this.layers.length - 1; i >= 0; i--) {
            eq5 &= this.layers[i].equals(otherKey.getLayers()[i]);
        }
        return eq5;
    }

    public int hashCode() {
        int hash = (((((((((this.layers.length * 37) + org.spongycastle.util.Arrays.hashCode(this.A1inv)) * 37) + org.spongycastle.util.Arrays.hashCode(this.f7201b1)) * 37) + org.spongycastle.util.Arrays.hashCode(this.A2inv)) * 37) + org.spongycastle.util.Arrays.hashCode(this.f7202b2)) * 37) + org.spongycastle.util.Arrays.hashCode(this.f7203vi);
        for (int i = this.layers.length - 1; i >= 0; i--) {
            hash = (hash * 37) + this.layers[i].hashCode();
        }
        return hash;
    }

    public final String getAlgorithm() {
        return "Rainbow";
    }

    public byte[] getEncoded() {
        byte[] bArr = null;
        try {
            try {
                return new PrivateKeyInfo(new AlgorithmIdentifier(PQCObjectIdentifiers.rainbow, DERNull.INSTANCE), new RainbowPrivateKey(this.A1inv, this.f7201b1, this.A2inv, this.f7202b2, this.f7203vi, this.layers)).getEncoded();
            } catch (IOException e) {
                e.printStackTrace();
                return bArr;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return bArr;
        }
    }

    public String getFormat() {
        return "PKCS#8";
    }
}
