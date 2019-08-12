package org.spongycastle.crypto.agreement.kdf;

import java.io.IOException;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Pack;

public class DHKEKGenerator implements DerivationFunction {
    private ASN1ObjectIdentifier algorithm;
    private final Digest digest;
    private int keySize;
    private byte[] partyAInfo;

    /* renamed from: z */
    private byte[] f6464z;

    public DHKEKGenerator(Digest digest2) {
        this.digest = digest2;
    }

    public void init(DerivationParameters param) {
        DHKDFParameters params = (DHKDFParameters) param;
        this.algorithm = params.getAlgorithm();
        this.keySize = params.getKeySize();
        this.f6464z = params.getZ();
        this.partyAInfo = params.getExtraInfo();
    }

    public Digest getDigest() {
        return this.digest;
    }

    public int generateBytes(byte[] out, int outOff, int len) throws DataLengthException, IllegalArgumentException {
        if (out.length - len < outOff) {
            throw new DataLengthException("output buffer too small");
        }
        long oBytes = (long) len;
        int outLen = this.digest.getDigestSize();
        if (oBytes > 8589934591L) {
            throw new IllegalArgumentException("Output length too large");
        }
        int cThreshold = (int) (((((long) outLen) + oBytes) - 1) / ((long) outLen));
        byte[] dig = new byte[this.digest.getDigestSize()];
        int counter = 1;
        int i = 0;
        while (i < cThreshold) {
            this.digest.update(this.f6464z, 0, this.f6464z.length);
            ASN1EncodableVector v1 = new ASN1EncodableVector();
            ASN1EncodableVector v2 = new ASN1EncodableVector();
            v2.add(this.algorithm);
            v2.add(new DEROctetString(Pack.intToBigEndian(counter)));
            v1.add(new DERSequence(v2));
            if (this.partyAInfo != null) {
                v1.add(new DERTaggedObject(true, 0, new DEROctetString(this.partyAInfo)));
            }
            v1.add(new DERTaggedObject(true, 2, new DEROctetString(Pack.intToBigEndian(this.keySize))));
            try {
                byte[] other = new DERSequence(v1).getEncoded(ASN1Encoding.DER);
                this.digest.update(other, 0, other.length);
                this.digest.doFinal(dig, 0);
                if (len > outLen) {
                    System.arraycopy(dig, 0, out, outOff, outLen);
                    outOff += outLen;
                    len -= outLen;
                } else {
                    System.arraycopy(dig, 0, out, outOff, len);
                }
                counter++;
                i++;
            } catch (IOException e) {
                throw new IllegalArgumentException("unable to encode parameter info: " + e.getMessage());
            }
        }
        this.digest.reset();
        return (int) oBytes;
    }
}
