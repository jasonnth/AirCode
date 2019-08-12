package org.spongycastle.jcajce.provider.asymmetric.ecgost;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Null;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.spongycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.spongycastle.asn1.p325x9.X962Parameters;
import org.spongycastle.asn1.p325x9.X9ECParameters;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.spongycastle.jce.ECGOST3410NamedCurveTable;
import org.spongycastle.jce.interfaces.ECPointEncoder;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECNamedCurveParameterSpec;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.jce.spec.ECPrivateKeySpec;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.util.Strings;

public class BCECGOST3410PrivateKey implements ECPrivateKey, ECPointEncoder, org.spongycastle.jce.interfaces.ECPrivateKey, PKCS12BagAttributeCarrier {
    static final long serialVersionUID = 7245981689601667138L;
    private String algorithm = "ECGOST3410";
    private transient PKCS12BagAttributeCarrierImpl attrCarrier = new PKCS12BagAttributeCarrierImpl();

    /* renamed from: d */
    private transient BigInteger f6906d;
    private transient ECParameterSpec ecSpec;
    private transient GOST3410PublicKeyAlgParameters gostParams;
    private transient DERBitString publicKey;
    private boolean withCompression;

    protected BCECGOST3410PrivateKey() {
    }

    public BCECGOST3410PrivateKey(ECPrivateKey key) {
        this.f6906d = key.getS();
        this.algorithm = key.getAlgorithm();
        this.ecSpec = key.getParams();
    }

    public BCECGOST3410PrivateKey(ECPrivateKeySpec spec) {
        this.f6906d = spec.getD();
        if (spec.getParams() != null) {
            this.ecSpec = EC5Util.convertSpec(EC5Util.convertCurve(spec.getParams().getCurve(), spec.getParams().getSeed()), spec.getParams());
        } else {
            this.ecSpec = null;
        }
    }

    public BCECGOST3410PrivateKey(java.security.spec.ECPrivateKeySpec spec) {
        this.f6906d = spec.getS();
        this.ecSpec = spec.getParams();
    }

    public BCECGOST3410PrivateKey(BCECGOST3410PrivateKey key) {
        this.f6906d = key.f6906d;
        this.ecSpec = key.ecSpec;
        this.withCompression = key.withCompression;
        this.attrCarrier = key.attrCarrier;
        this.publicKey = key.publicKey;
        this.gostParams = key.gostParams;
    }

    public BCECGOST3410PrivateKey(String algorithm2, ECPrivateKeyParameters params, BCECGOST3410PublicKey pubKey, ECParameterSpec spec) {
        ECDomainParameters dp = params.getParameters();
        this.algorithm = algorithm2;
        this.f6906d = params.getD();
        if (spec == null) {
            this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(dp.getCurve(), dp.getSeed()), new ECPoint(dp.getG().getAffineXCoord().toBigInteger(), dp.getG().getAffineYCoord().toBigInteger()), dp.getN(), dp.getH().intValue());
        } else {
            this.ecSpec = spec;
        }
        this.gostParams = pubKey.getGostParams();
        this.publicKey = getPublicKeyDetails(pubKey);
    }

    public BCECGOST3410PrivateKey(String algorithm2, ECPrivateKeyParameters params, BCECGOST3410PublicKey pubKey, org.spongycastle.jce.spec.ECParameterSpec spec) {
        ECDomainParameters dp = params.getParameters();
        this.algorithm = algorithm2;
        this.f6906d = params.getD();
        if (spec == null) {
            this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(dp.getCurve(), dp.getSeed()), new ECPoint(dp.getG().getAffineXCoord().toBigInteger(), dp.getG().getAffineYCoord().toBigInteger()), dp.getN(), dp.getH().intValue());
        } else {
            this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(spec.getCurve(), spec.getSeed()), new ECPoint(spec.getG().getAffineXCoord().toBigInteger(), spec.getG().getAffineYCoord().toBigInteger()), spec.getN(), spec.getH().intValue());
        }
        this.gostParams = pubKey.getGostParams();
        this.publicKey = getPublicKeyDetails(pubKey);
    }

    public BCECGOST3410PrivateKey(String algorithm2, ECPrivateKeyParameters params) {
        this.algorithm = algorithm2;
        this.f6906d = params.getD();
        this.ecSpec = null;
    }

    BCECGOST3410PrivateKey(PrivateKeyInfo info) throws IOException {
        populateFromPrivKeyInfo(info);
    }

    private void populateFromPrivKeyInfo(PrivateKeyInfo info) throws IOException {
        ASN1Primitive p = info.getPrivateKeyAlgorithm().getParameters().toASN1Primitive();
        if (!(p instanceof ASN1Sequence) || !(ASN1Sequence.getInstance(p).size() == 2 || ASN1Sequence.getInstance(p).size() == 3)) {
            X962Parameters params = X962Parameters.getInstance(info.getPrivateKeyAlgorithm().getParameters());
            if (params.isNamedCurve()) {
                ASN1ObjectIdentifier oid = ASN1ObjectIdentifier.getInstance(params.getParameters());
                X9ECParameters ecP = ECUtil.getNamedCurveByOid(oid);
                if (ecP == null) {
                    ECDomainParameters gParam = ECGOST3410NamedCurves.getByOID(oid);
                    this.ecSpec = new ECNamedCurveSpec(ECGOST3410NamedCurves.getName(oid), EC5Util.convertCurve(gParam.getCurve(), gParam.getSeed()), new ECPoint(gParam.getG().getAffineXCoord().toBigInteger(), gParam.getG().getAffineYCoord().toBigInteger()), gParam.getN(), gParam.getH());
                } else {
                    this.ecSpec = new ECNamedCurveSpec(ECUtil.getCurveName(oid), EC5Util.convertCurve(ecP.getCurve(), ecP.getSeed()), new ECPoint(ecP.getG().getAffineXCoord().toBigInteger(), ecP.getG().getAffineYCoord().toBigInteger()), ecP.getN(), ecP.getH());
                }
            } else if (params.isImplicitlyCA()) {
                this.ecSpec = null;
            } else {
                X9ECParameters ecP2 = X9ECParameters.getInstance(params.getParameters());
                this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(ecP2.getCurve(), ecP2.getSeed()), new ECPoint(ecP2.getG().getAffineXCoord().toBigInteger(), ecP2.getG().getAffineYCoord().toBigInteger()), ecP2.getN(), ecP2.getH().intValue());
            }
            ASN1Encodable privKey = info.parsePrivateKey();
            if (privKey instanceof ASN1Integer) {
                this.f6906d = ASN1Integer.getInstance(privKey).getValue();
                return;
            }
            org.spongycastle.asn1.sec.ECPrivateKey ec = org.spongycastle.asn1.sec.ECPrivateKey.getInstance(privKey);
            this.f6906d = ec.getKey();
            this.publicKey = ec.getPublicKey();
            return;
        }
        this.gostParams = GOST3410PublicKeyAlgParameters.getInstance(info.getPrivateKeyAlgorithm().getParameters());
        ECNamedCurveParameterSpec spec = ECGOST3410NamedCurveTable.getParameterSpec(ECGOST3410NamedCurves.getName(this.gostParams.getPublicKeyParamSet()));
        this.ecSpec = new ECNamedCurveSpec(ECGOST3410NamedCurves.getName(this.gostParams.getPublicKeyParamSet()), EC5Util.convertCurve(spec.getCurve(), spec.getSeed()), new ECPoint(spec.getG().getAffineXCoord().toBigInteger(), spec.getG().getAffineYCoord().toBigInteger()), spec.getN(), spec.getH());
        ASN1Encodable privKey2 = info.parsePrivateKey();
        if (privKey2 instanceof ASN1Integer) {
            this.f6906d = ASN1Integer.getInstance(privKey2).getPositiveValue();
            return;
        }
        byte[] encVal = ASN1OctetString.getInstance(privKey2).getOctets();
        byte[] dVal = new byte[encVal.length];
        for (int i = 0; i != encVal.length; i++) {
            dVal[i] = encVal[(encVal.length - 1) - i];
        }
        this.f6906d = new BigInteger(1, dVal);
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        X962Parameters params;
        int orderBitLength;
        org.spongycastle.asn1.sec.ECPrivateKey keyStructure;
        if (this.gostParams != null) {
            byte[] encKey = new byte[32];
            extractBytes(encKey, 0, getS());
            try {
                return new PrivateKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_2001, this.gostParams), new DEROctetString(encKey)).getEncoded(ASN1Encoding.DER);
            } catch (IOException e) {
                return null;
            }
        } else {
            if (this.ecSpec instanceof ECNamedCurveSpec) {
                ASN1ObjectIdentifier curveOid = ECUtil.getNamedCurveOid(((ECNamedCurveSpec) this.ecSpec).getName());
                if (curveOid == null) {
                    curveOid = new ASN1ObjectIdentifier(((ECNamedCurveSpec) this.ecSpec).getName());
                }
                params = new X962Parameters(curveOid);
                orderBitLength = ECUtil.getOrderBitLength(this.ecSpec.getOrder(), getS());
            } else if (this.ecSpec == null) {
                params = new X962Parameters((ASN1Null) DERNull.INSTANCE);
                orderBitLength = ECUtil.getOrderBitLength(null, getS());
            } else {
                ECCurve curve = EC5Util.convertCurve(this.ecSpec.getCurve());
                params = new X962Parameters(new X9ECParameters(curve, EC5Util.convertPoint(curve, this.ecSpec.getGenerator(), this.withCompression), this.ecSpec.getOrder(), BigInteger.valueOf((long) this.ecSpec.getCofactor()), this.ecSpec.getCurve().getSeed()));
                orderBitLength = ECUtil.getOrderBitLength(this.ecSpec.getOrder(), getS());
            }
            if (this.publicKey != null) {
                keyStructure = new org.spongycastle.asn1.sec.ECPrivateKey(orderBitLength, getS(), this.publicKey, params);
            } else {
                keyStructure = new org.spongycastle.asn1.sec.ECPrivateKey(orderBitLength, getS(), (ASN1Encodable) params);
            }
            try {
                return new PrivateKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.gostR3410_2001, params.toASN1Primitive()), keyStructure.toASN1Primitive()).getEncoded(ASN1Encoding.DER);
            } catch (IOException e2) {
                return null;
            }
        }
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

    /* access modifiers changed from: 0000 */
    public org.spongycastle.jce.spec.ECParameterSpec engineGetSpec() {
        if (this.ecSpec != null) {
            return EC5Util.convertSpec(this.ecSpec, this.withCompression);
        }
        return BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
    }

    public BigInteger getS() {
        return this.f6906d;
    }

    public BigInteger getD() {
        return this.f6906d;
    }

    public void setBagAttribute(ASN1ObjectIdentifier oid, ASN1Encodable attribute) {
        this.attrCarrier.setBagAttribute(oid, attribute);
    }

    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier oid) {
        return this.attrCarrier.getBagAttribute(oid);
    }

    public Enumeration getBagAttributeKeys() {
        return this.attrCarrier.getBagAttributeKeys();
    }

    public void setPointFormat(String style) {
        this.withCompression = !"UNCOMPRESSED".equalsIgnoreCase(style);
    }

    public boolean equals(Object o) {
        if (!(o instanceof BCECGOST3410PrivateKey)) {
            return false;
        }
        BCECGOST3410PrivateKey other = (BCECGOST3410PrivateKey) o;
        if (!getD().equals(other.getD()) || !engineGetSpec().equals(other.engineGetSpec())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return getD().hashCode() ^ engineGetSpec().hashCode();
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        String nl = Strings.lineSeparator();
        buf.append("EC Private Key").append(nl);
        buf.append("             S: ").append(this.f6906d.toString(16)).append(nl);
        return buf.toString();
    }

    private DERBitString getPublicKeyDetails(BCECGOST3410PublicKey pub) {
        try {
            return SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(pub.getEncoded())).getPublicKeyData();
        } catch (IOException e) {
            return null;
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        populateFromPrivKeyInfo(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray((byte[]) in.readObject())));
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(getEncoded());
    }
}
