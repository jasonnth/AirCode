package org.spongycastle.asn1.x509.qualified;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class BiometricData extends ASN1Object {
    private ASN1OctetString biometricDataHash;
    private AlgorithmIdentifier hashAlgorithm;
    private DERIA5String sourceDataUri;
    private TypeOfBiometricData typeOfBiometricData;

    public static BiometricData getInstance(Object obj) {
        if (obj instanceof BiometricData) {
            return (BiometricData) obj;
        }
        if (obj != null) {
            return new BiometricData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private BiometricData(ASN1Sequence seq) {
        Enumeration e = seq.getObjects();
        this.typeOfBiometricData = TypeOfBiometricData.getInstance(e.nextElement());
        this.hashAlgorithm = AlgorithmIdentifier.getInstance(e.nextElement());
        this.biometricDataHash = ASN1OctetString.getInstance(e.nextElement());
        if (e.hasMoreElements()) {
            this.sourceDataUri = DERIA5String.getInstance(e.nextElement());
        }
    }

    public BiometricData(TypeOfBiometricData typeOfBiometricData2, AlgorithmIdentifier hashAlgorithm2, ASN1OctetString biometricDataHash2, DERIA5String sourceDataUri2) {
        this.typeOfBiometricData = typeOfBiometricData2;
        this.hashAlgorithm = hashAlgorithm2;
        this.biometricDataHash = biometricDataHash2;
        this.sourceDataUri = sourceDataUri2;
    }

    public BiometricData(TypeOfBiometricData typeOfBiometricData2, AlgorithmIdentifier hashAlgorithm2, ASN1OctetString biometricDataHash2) {
        this.typeOfBiometricData = typeOfBiometricData2;
        this.hashAlgorithm = hashAlgorithm2;
        this.biometricDataHash = biometricDataHash2;
        this.sourceDataUri = null;
    }

    public TypeOfBiometricData getTypeOfBiometricData() {
        return this.typeOfBiometricData;
    }

    public AlgorithmIdentifier getHashAlgorithm() {
        return this.hashAlgorithm;
    }

    public ASN1OctetString getBiometricDataHash() {
        return this.biometricDataHash;
    }

    public DERIA5String getSourceDataUri() {
        return this.sourceDataUri;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector seq = new ASN1EncodableVector();
        seq.add(this.typeOfBiometricData);
        seq.add(this.hashAlgorithm);
        seq.add(this.biometricDataHash);
        if (this.sourceDataUri != null) {
            seq.add(this.sourceDataUri);
        }
        return new DERSequence(seq);
    }
}
