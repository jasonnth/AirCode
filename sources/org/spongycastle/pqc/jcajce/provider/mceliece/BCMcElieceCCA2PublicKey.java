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
import org.spongycastle.pqc.asn1.McElieceCCA2PublicKey;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2Parameters;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2PublicKeyParameters;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2PublicKeySpec;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

public class BCMcElieceCCA2PublicKey implements PublicKey, CipherParameters {
    private static final long serialVersionUID = 1;
    private McElieceCCA2Parameters McElieceCCA2Params;

    /* renamed from: g */
    private GF2Matrix f7190g;

    /* renamed from: n */
    private int f7191n;
    private String oid;

    /* renamed from: t */
    private int f7192t;

    public BCMcElieceCCA2PublicKey(String oid2, int n, int t, GF2Matrix g) {
        this.oid = oid2;
        this.f7191n = n;
        this.f7192t = t;
        this.f7190g = g;
    }

    public BCMcElieceCCA2PublicKey(McElieceCCA2PublicKeySpec keySpec) {
        this(keySpec.getOIDString(), keySpec.getN(), keySpec.getT(), keySpec.getMatrixG());
    }

    public BCMcElieceCCA2PublicKey(McElieceCCA2PublicKeyParameters params) {
        this(params.getOIDString(), params.getN(), params.getT(), params.getMatrixG());
        this.McElieceCCA2Params = params.getParameters();
    }

    public String getAlgorithm() {
        return "McEliece";
    }

    public int getN() {
        return this.f7191n;
    }

    public int getK() {
        return this.f7190g.getNumRows();
    }

    public int getT() {
        return this.f7192t;
    }

    public GF2Matrix getG() {
        return this.f7190g;
    }

    public String toString() {
        return (("McEliecePublicKey:\n" + " length of the code         : " + this.f7191n + "\n") + " error correction capability: " + this.f7192t + "\n") + " generator matrix           : " + this.f7190g.toString();
    }

    public boolean equals(Object other) {
        if (other == null || !(other instanceof BCMcElieceCCA2PublicKey)) {
            return false;
        }
        BCMcElieceCCA2PublicKey otherKey = (BCMcElieceCCA2PublicKey) other;
        if (this.f7191n == otherKey.f7191n && this.f7192t == otherKey.f7192t && this.f7190g.equals(otherKey.f7190g)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.f7191n + this.f7192t + this.f7190g.hashCode();
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
        try {
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(getOID(), DERNull.INSTANCE), (ASN1Encodable) new McElieceCCA2PublicKey(new ASN1ObjectIdentifier(this.oid), this.f7191n, this.f7192t, this.f7190g)).getEncoded();
        } catch (IOException e) {
            return null;
        }
    }

    public String getFormat() {
        return null;
    }

    public McElieceCCA2Parameters getMcElieceCCA2Parameters() {
        return this.McElieceCCA2Params;
    }
}
