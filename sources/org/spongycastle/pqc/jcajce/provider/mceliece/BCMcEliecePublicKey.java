package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.io.IOException;
import java.security.PublicKey;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.asn1.McEliecePublicKey;
import org.spongycastle.pqc.crypto.mceliece.McElieceParameters;
import org.spongycastle.pqc.crypto.mceliece.McEliecePublicKeyParameters;
import org.spongycastle.pqc.jcajce.spec.McEliecePublicKeySpec;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

public class BCMcEliecePublicKey implements PublicKey, CipherParameters {
    private static final long serialVersionUID = 1;
    private McElieceParameters McElieceParams;

    /* renamed from: g */
    private GF2Matrix f7198g;

    /* renamed from: n */
    private int f7199n;
    private String oid;

    /* renamed from: t */
    private int f7200t;

    public BCMcEliecePublicKey(String oid2, int n, int t, GF2Matrix g) {
        this.oid = oid2;
        this.f7199n = n;
        this.f7200t = t;
        this.f7198g = g;
    }

    public BCMcEliecePublicKey(McEliecePublicKeySpec keySpec) {
        this(keySpec.getOIDString(), keySpec.getN(), keySpec.getT(), keySpec.getG());
    }

    public BCMcEliecePublicKey(McEliecePublicKeyParameters params) {
        this(params.getOIDString(), params.getN(), params.getT(), params.getG());
        this.McElieceParams = params.getParameters();
    }

    public String getAlgorithm() {
        return "McEliece";
    }

    public int getN() {
        return this.f7199n;
    }

    public int getK() {
        return this.f7198g.getNumRows();
    }

    public int getT() {
        return this.f7200t;
    }

    public GF2Matrix getG() {
        return this.f7198g;
    }

    public String toString() {
        return (("McEliecePublicKey:\n" + " length of the code         : " + this.f7199n + "\n") + " error correction capability: " + this.f7200t + "\n") + " generator matrix           : " + this.f7198g.toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof BCMcEliecePublicKey)) {
            return false;
        }
        BCMcEliecePublicKey otherKey = (BCMcEliecePublicKey) other;
        if (this.f7199n == otherKey.f7199n && this.f7200t == otherKey.f7200t && this.f7198g.equals(otherKey.f7198g)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.f7199n + this.f7200t + this.f7198g.hashCode();
    }

    public String getOIDString() {
        return this.oid;
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
        try {
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(getOID(), DERNull.INSTANCE), (ASN1Encodable) new McEliecePublicKey(new ASN1ObjectIdentifier(this.oid), this.f7199n, this.f7200t, this.f7198g)).getEncoded();
        } catch (IOException e) {
            return null;
        }
    }

    public String getFormat() {
        return null;
    }

    public McElieceParameters getMcElieceParameters() {
        return this.McElieceParams;
    }
}
