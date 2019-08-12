package org.spongycastle.crypto.agreement.kdf;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.crypto.DerivationParameters;

public class DHKDFParameters implements DerivationParameters {
    private ASN1ObjectIdentifier algorithm;
    private byte[] extraInfo;
    private int keySize;

    /* renamed from: z */
    private byte[] f6463z;

    public DHKDFParameters(ASN1ObjectIdentifier algorithm2, int keySize2, byte[] z) {
        this(algorithm2, keySize2, z, null);
    }

    public DHKDFParameters(ASN1ObjectIdentifier algorithm2, int keySize2, byte[] z, byte[] extraInfo2) {
        this.algorithm = algorithm2;
        this.keySize = keySize2;
        this.f6463z = z;
        this.extraInfo = extraInfo2;
    }

    public ASN1ObjectIdentifier getAlgorithm() {
        return this.algorithm;
    }

    public int getKeySize() {
        return this.keySize;
    }

    public byte[] getZ() {
        return this.f6463z;
    }

    public byte[] getExtraInfo() {
        return this.extraInfo;
    }
}
