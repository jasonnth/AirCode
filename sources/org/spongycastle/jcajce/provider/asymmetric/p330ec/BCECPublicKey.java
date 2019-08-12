package org.spongycastle.jcajce.provider.asymmetric.p330ec;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Null;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.p325x9.X962Parameters;
import org.spongycastle.asn1.p325x9.X9ECParameters;
import org.spongycastle.asn1.p325x9.X9ECPoint;
import org.spongycastle.asn1.p325x9.X9IntegerConverter;
import org.spongycastle.asn1.p325x9.X9ObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.spongycastle.jcajce.provider.config.ProviderConfiguration;
import org.spongycastle.jce.interfaces.ECPointEncoder;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.util.Strings;

/* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.BCECPublicKey */
public class BCECPublicKey implements ECPublicKey, ECPointEncoder, org.spongycastle.jce.interfaces.ECPublicKey {
    static final long serialVersionUID = 2422789860422731812L;
    private String algorithm = "EC";
    private transient ProviderConfiguration configuration;
    private transient ECParameterSpec ecSpec;

    /* renamed from: q */
    private transient ECPoint f6905q;
    private boolean withCompression;

    public BCECPublicKey(String algorithm2, BCECPublicKey key) {
        this.algorithm = algorithm2;
        this.f6905q = key.f6905q;
        this.ecSpec = key.ecSpec;
        this.withCompression = key.withCompression;
        this.configuration = key.configuration;
    }

    public BCECPublicKey(String algorithm2, ECPublicKeySpec spec, ProviderConfiguration configuration2) {
        this.algorithm = algorithm2;
        this.ecSpec = spec.getParams();
        this.f6905q = EC5Util.convertPoint(this.ecSpec, spec.getW(), false);
        this.configuration = configuration2;
    }

    public BCECPublicKey(String algorithm2, org.spongycastle.jce.spec.ECPublicKeySpec spec, ProviderConfiguration configuration2) {
        this.algorithm = algorithm2;
        this.f6905q = spec.getQ();
        if (spec.getParams() != null) {
            EllipticCurve ellipticCurve = EC5Util.convertCurve(spec.getParams().getCurve(), spec.getParams().getSeed());
            this.f6905q = EC5Util.convertCurve(ellipticCurve).createPoint(spec.getQ().getAffineXCoord().toBigInteger(), spec.getQ().getAffineYCoord().toBigInteger());
            this.ecSpec = EC5Util.convertSpec(ellipticCurve, spec.getParams());
        } else {
            if (this.f6905q.getCurve() == null) {
                this.f6905q = configuration2.getEcImplicitlyCa().getCurve().createPoint(this.f6905q.getXCoord().toBigInteger(), this.f6905q.getYCoord().toBigInteger(), false);
            }
            this.ecSpec = null;
        }
        this.configuration = configuration2;
    }

    public BCECPublicKey(String algorithm2, ECPublicKeyParameters params, ECParameterSpec spec, ProviderConfiguration configuration2) {
        ECDomainParameters dp = params.getParameters();
        this.algorithm = algorithm2;
        this.f6905q = params.getQ();
        if (spec == null) {
            this.ecSpec = createSpec(EC5Util.convertCurve(dp.getCurve(), dp.getSeed()), dp);
        } else {
            this.ecSpec = spec;
        }
        this.configuration = configuration2;
    }

    public BCECPublicKey(String algorithm2, ECPublicKeyParameters params, org.spongycastle.jce.spec.ECParameterSpec spec, ProviderConfiguration configuration2) {
        ECDomainParameters dp = params.getParameters();
        this.algorithm = algorithm2;
        if (spec == null) {
            this.ecSpec = createSpec(EC5Util.convertCurve(dp.getCurve(), dp.getSeed()), dp);
        } else {
            this.ecSpec = EC5Util.convertSpec(EC5Util.convertCurve(spec.getCurve(), spec.getSeed()), spec);
        }
        this.f6905q = EC5Util.convertCurve(this.ecSpec.getCurve()).createPoint(params.getQ().getAffineXCoord().toBigInteger(), params.getQ().getAffineYCoord().toBigInteger());
        this.configuration = configuration2;
    }

    public BCECPublicKey(String algorithm2, ECPublicKeyParameters params, ProviderConfiguration configuration2) {
        this.algorithm = algorithm2;
        this.f6905q = params.getQ();
        this.ecSpec = null;
        this.configuration = configuration2;
    }

    public BCECPublicKey(ECPublicKey key, ProviderConfiguration configuration2) {
        this.algorithm = key.getAlgorithm();
        this.ecSpec = key.getParams();
        this.f6905q = EC5Util.convertPoint(this.ecSpec, key.getW(), false);
    }

    BCECPublicKey(String algorithm2, SubjectPublicKeyInfo info, ProviderConfiguration configuration2) {
        this.algorithm = algorithm2;
        this.configuration = configuration2;
        populateFromPubKeyInfo(info);
    }

    private ECParameterSpec createSpec(EllipticCurve ellipticCurve, ECDomainParameters dp) {
        return new ECParameterSpec(ellipticCurve, new java.security.spec.ECPoint(dp.getG().getAffineXCoord().toBigInteger(), dp.getG().getAffineYCoord().toBigInteger()), dp.getN(), dp.getH().intValue());
    }

    private void populateFromPubKeyInfo(SubjectPublicKeyInfo info) {
        X962Parameters params = new X962Parameters((ASN1Primitive) info.getAlgorithm().getParameters());
        ECCurve curve = EC5Util.getCurve(this.configuration, params);
        this.ecSpec = EC5Util.convertToSpec(params, curve);
        byte[] data = info.getPublicKeyData().getBytes();
        ASN1OctetString key = new DEROctetString(data);
        if (data[0] == 4 && data[1] == data.length - 2 && ((data[2] == 2 || data[2] == 3) && new X9IntegerConverter().getByteLength(curve) >= data.length - 3)) {
            try {
                key = (ASN1OctetString) ASN1Primitive.fromByteArray(data);
            } catch (IOException e) {
                throw new IllegalArgumentException("error recovering public key");
            }
        }
        this.f6905q = new X9ECPoint(curve, key).getPoint();
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        ASN1Encodable params;
        ASN1OctetString p;
        if (this.ecSpec instanceof ECNamedCurveSpec) {
            ASN1ObjectIdentifier curveOid = ECUtil.getNamedCurveOid(((ECNamedCurveSpec) this.ecSpec).getName());
            if (curveOid == null) {
                curveOid = new ASN1ObjectIdentifier(((ECNamedCurveSpec) this.ecSpec).getName());
            }
            params = new X962Parameters(curveOid);
        } else if (this.ecSpec == null) {
            params = new X962Parameters((ASN1Null) DERNull.INSTANCE);
        } else {
            ECCurve curve = EC5Util.convertCurve(this.ecSpec.getCurve());
            params = new X962Parameters(new X9ECParameters(curve, EC5Util.convertPoint(curve, this.ecSpec.getGenerator(), this.withCompression), this.ecSpec.getOrder(), BigInteger.valueOf((long) this.ecSpec.getCofactor()), this.ecSpec.getCurve().getSeed()));
        }
        ECCurve curve2 = engineGetQ().getCurve();
        if (this.ecSpec == null) {
            p = (ASN1OctetString) new X9ECPoint(curve2.createPoint(getQ().getXCoord().toBigInteger(), getQ().getYCoord().toBigInteger(), this.withCompression)).toASN1Primitive();
        } else {
            p = (ASN1OctetString) new X9ECPoint(curve2.createPoint(getQ().getAffineXCoord().toBigInteger(), getQ().getAffineYCoord().toBigInteger(), this.withCompression)).toASN1Primitive();
        }
        return KeyUtil.getEncodedSubjectPublicKeyInfo(new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, params), p.getOctets()));
    }

    private void extractBytes(byte[] encKey, int offSet, BigInteger bI) {
        byte[] val = bI.toByteArray();
        if (val.length < 32) {
            byte[] tmp = new byte[32];
            System.arraycopy(val, 0, tmp, tmp.length - val.length, val.length);
            val = tmp;
        }
        for (int i = 0; i != 32; i++) {
            encKey[offSet + i] = val[(val.length - 1) - i];
        }
    }

    public ECParameterSpec getParams() {
        return this.ecSpec;
    }

    public org.spongycastle.jce.spec.ECParameterSpec getParameters() {
        if (this.ecSpec == null) {
            return null;
        }
        return EC5Util.convertSpec(this.ecSpec, this.withCompression);
    }

    public java.security.spec.ECPoint getW() {
        return new java.security.spec.ECPoint(this.f6905q.getAffineXCoord().toBigInteger(), this.f6905q.getAffineYCoord().toBigInteger());
    }

    public ECPoint getQ() {
        if (this.ecSpec == null) {
            return this.f6905q.getDetachedPoint();
        }
        return this.f6905q;
    }

    public ECPoint engineGetQ() {
        return this.f6905q;
    }

    /* access modifiers changed from: 0000 */
    public org.spongycastle.jce.spec.ECParameterSpec engineGetSpec() {
        if (this.ecSpec != null) {
            return EC5Util.convertSpec(this.ecSpec, this.withCompression);
        }
        return this.configuration.getEcImplicitlyCa();
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        String nl = Strings.lineSeparator();
        buf.append("EC Public Key").append(nl);
        buf.append("            X: ").append(this.f6905q.getAffineXCoord().toBigInteger().toString(16)).append(nl);
        buf.append("            Y: ").append(this.f6905q.getAffineYCoord().toBigInteger().toString(16)).append(nl);
        return buf.toString();
    }

    public void setPointFormat(String style) {
        this.withCompression = !"UNCOMPRESSED".equalsIgnoreCase(style);
    }

    public boolean equals(Object o) {
        if (!(o instanceof BCECPublicKey)) {
            return false;
        }
        BCECPublicKey other = (BCECPublicKey) o;
        if (!engineGetQ().equals(other.engineGetQ()) || !engineGetSpec().equals(other.engineGetSpec())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return engineGetQ().hashCode() ^ engineGetSpec().hashCode();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        populateFromPubKeyInfo(SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray((byte[]) in.readObject())));
        this.configuration = BouncyCastleProvider.CONFIGURATION;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(getEncoded());
    }
}
