package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.io.IOException;
import java.security.PrivateKey;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.asn1.McEliecePrivateKey;
import org.spongycastle.pqc.crypto.mceliece.McElieceParameters;
import org.spongycastle.pqc.crypto.mceliece.McEliecePrivateKeyParameters;
import org.spongycastle.pqc.jcajce.spec.McEliecePrivateKeySpec;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public class BCMcEliecePrivateKey implements PrivateKey, CipherParameters {
    private static final long serialVersionUID = 1;
    private GF2mField field;
    private PolynomialGF2mSmallM goppaPoly;

    /* renamed from: h */
    private GF2Matrix f7193h;

    /* renamed from: k */
    private int f7194k;
    private McElieceParameters mcElieceParams;

    /* renamed from: n */
    private int f7195n;
    private String oid;

    /* renamed from: p1 */
    private Permutation f7196p1;

    /* renamed from: p2 */
    private Permutation f7197p2;
    private PolynomialGF2mSmallM[] qInv;
    private GF2Matrix sInv;

    public BCMcEliecePrivateKey(String oid2, int n, int k, GF2mField field2, PolynomialGF2mSmallM goppaPoly2, GF2Matrix sInv2, Permutation p1, Permutation p2, GF2Matrix h, PolynomialGF2mSmallM[] qInv2) {
        this.oid = oid2;
        this.f7195n = n;
        this.f7194k = k;
        this.field = field2;
        this.goppaPoly = goppaPoly2;
        this.sInv = sInv2;
        this.f7196p1 = p1;
        this.f7197p2 = p2;
        this.f7193h = h;
        this.qInv = qInv2;
    }

    public BCMcEliecePrivateKey(McEliecePrivateKeySpec keySpec) {
        this(keySpec.getOIDString(), keySpec.getN(), keySpec.getK(), keySpec.getField(), keySpec.getGoppaPoly(), keySpec.getSInv(), keySpec.getP1(), keySpec.getP2(), keySpec.getH(), keySpec.getQInv());
    }

    public BCMcEliecePrivateKey(McEliecePrivateKeyParameters params) {
        this(params.getOIDString(), params.getN(), params.getK(), params.getField(), params.getGoppaPoly(), params.getSInv(), params.getP1(), params.getP2(), params.getH(), params.getQInv());
        this.mcElieceParams = params.getParameters();
    }

    public String getAlgorithm() {
        return "McEliece";
    }

    public int getN() {
        return this.f7195n;
    }

    public int getK() {
        return this.f7194k;
    }

    public GF2mField getField() {
        return this.field;
    }

    public PolynomialGF2mSmallM getGoppaPoly() {
        return this.goppaPoly;
    }

    public GF2Matrix getSInv() {
        return this.sInv;
    }

    public Permutation getP1() {
        return this.f7196p1;
    }

    public Permutation getP2() {
        return this.f7197p2;
    }

    public GF2Matrix getH() {
        return this.f7193h;
    }

    public PolynomialGF2mSmallM[] getQInv() {
        return this.qInv;
    }

    public String getOIDString() {
        return this.oid;
    }

    public String toString() {
        return (((((" length of the code          : " + this.f7195n + "\n") + " dimension of the code       : " + this.f7194k + "\n") + " irreducible Goppa polynomial: " + this.goppaPoly + "\n") + " (k x k)-matrix S^-1         : " + this.sInv + "\n") + " permutation P1              : " + this.f7196p1 + "\n") + " permutation P2              : " + this.f7197p2;
    }

    public boolean equals(Object other) {
        if (!(other instanceof BCMcEliecePrivateKey)) {
            return false;
        }
        BCMcEliecePrivateKey otherKey = (BCMcEliecePrivateKey) other;
        if (this.f7195n != otherKey.f7195n || this.f7194k != otherKey.f7194k || !this.field.equals(otherKey.field) || !this.goppaPoly.equals(otherKey.goppaPoly) || !this.sInv.equals(otherKey.sInv) || !this.f7196p1.equals(otherKey.f7196p1) || !this.f7197p2.equals(otherKey.f7197p2) || !this.f7193h.equals(otherKey.f7193h)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.f7194k + this.f7195n + this.field.hashCode() + this.goppaPoly.hashCode() + this.sInv.hashCode() + this.f7196p1.hashCode() + this.f7197p2.hashCode() + this.f7193h.hashCode();
    }

    /* access modifiers changed from: protected */
    public ASN1ObjectIdentifier getOID() {
        return new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.4.1");
    }

    /* access modifiers changed from: protected */
    public ASN1Primitive getAlgParams() {
        return null;
    }

    public byte[] getEncoded() {
        byte[] bArr = null;
        try {
            try {
                return new PrivateKeyInfo(new AlgorithmIdentifier(getOID(), DERNull.INSTANCE), new McEliecePrivateKey(new ASN1ObjectIdentifier(this.oid), this.f7195n, this.f7194k, this.field, this.goppaPoly, this.sInv, this.f7196p1, this.f7197p2, this.f7193h, this.qInv)).getEncoded();
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
        return null;
    }

    public McElieceParameters getMcElieceParameters() {
        return this.mcElieceParams;
    }
}
