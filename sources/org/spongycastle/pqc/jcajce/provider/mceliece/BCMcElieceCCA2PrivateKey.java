package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.io.IOException;
import java.security.PrivateKey;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.asn1.McElieceCCA2PrivateKey;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2Parameters;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2PrivateKeySpec;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public class BCMcElieceCCA2PrivateKey implements PrivateKey, CipherParameters {
    private static final long serialVersionUID = 1;
    private GF2mField field;
    private PolynomialGF2mSmallM goppaPoly;

    /* renamed from: h */
    private GF2Matrix f7186h;

    /* renamed from: k */
    private int f7187k;
    private McElieceCCA2Parameters mcElieceCCA2Params;

    /* renamed from: n */
    private int f7188n;
    private String oid;

    /* renamed from: p */
    private Permutation f7189p;
    private PolynomialGF2mSmallM[] qInv;

    public BCMcElieceCCA2PrivateKey(String oid2, int n, int k, GF2mField field2, PolynomialGF2mSmallM gp, Permutation p, GF2Matrix h, PolynomialGF2mSmallM[] qInv2) {
        this.oid = oid2;
        this.f7188n = n;
        this.f7187k = k;
        this.field = field2;
        this.goppaPoly = gp;
        this.f7189p = p;
        this.f7186h = h;
        this.qInv = qInv2;
    }

    public BCMcElieceCCA2PrivateKey(McElieceCCA2PrivateKeySpec keySpec) {
        this(keySpec.getOIDString(), keySpec.getN(), keySpec.getK(), keySpec.getField(), keySpec.getGoppaPoly(), keySpec.getP(), keySpec.getH(), keySpec.getQInv());
    }

    public BCMcElieceCCA2PrivateKey(McElieceCCA2PrivateKeyParameters params) {
        this(params.getOIDString(), params.getN(), params.getK(), params.getField(), params.getGoppaPoly(), params.getP(), params.getH(), params.getQInv());
        this.mcElieceCCA2Params = params.getParameters();
    }

    public String getAlgorithm() {
        return "McEliece";
    }

    public int getN() {
        return this.f7188n;
    }

    public int getK() {
        return this.f7187k;
    }

    public int getT() {
        return this.goppaPoly.getDegree();
    }

    public GF2mField getField() {
        return this.field;
    }

    public PolynomialGF2mSmallM getGoppaPoly() {
        return this.goppaPoly;
    }

    public Permutation getP() {
        return this.f7189p;
    }

    public GF2Matrix getH() {
        return this.f7186h;
    }

    public PolynomialGF2mSmallM[] getQInv() {
        return this.qInv;
    }

    public String toString() {
        return (("" + " extension degree of the field      : " + this.f7188n + "\n") + " dimension of the code              : " + this.f7187k + "\n") + " irreducible Goppa polynomial       : " + this.goppaPoly + "\n";
    }

    public boolean equals(Object other) {
        if (other == null || !(other instanceof BCMcElieceCCA2PrivateKey)) {
            return false;
        }
        BCMcElieceCCA2PrivateKey otherKey = (BCMcElieceCCA2PrivateKey) other;
        if (this.f7188n != otherKey.f7188n || this.f7187k != otherKey.f7187k || !this.field.equals(otherKey.field) || !this.goppaPoly.equals(otherKey.goppaPoly) || !this.f7189p.equals(otherKey.f7189p) || !this.f7186h.equals(otherKey.f7186h)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.f7187k + this.f7188n + this.field.hashCode() + this.goppaPoly.hashCode() + this.f7189p.hashCode() + this.f7186h.hashCode();
    }

    public String getOIDString() {
        return this.oid;
    }

    /* access modifiers changed from: protected */
    public ASN1ObjectIdentifier getOID() {
        return new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.4.2");
    }

    /* access modifiers changed from: protected */
    public ASN1Primitive getAlgParams() {
        return null;
    }

    public byte[] getEncoded() {
        byte[] bArr = null;
        try {
            try {
                return new PrivateKeyInfo(new AlgorithmIdentifier(getOID(), DERNull.INSTANCE), new McElieceCCA2PrivateKey(new ASN1ObjectIdentifier(this.oid), this.f7188n, this.f7187k, this.field, this.goppaPoly, this.f7189p, this.f7186h, this.qInv)).getEncoded();
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

    public McElieceCCA2Parameters getMcElieceCCA2Parameters() {
        return this.mcElieceCCA2Params;
    }
}
