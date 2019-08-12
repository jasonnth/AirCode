package org.spongycastle.jcajce.provider.asymmetric.p330ec;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.NullDigest;
import org.spongycastle.crypto.digests.RIPEMD160Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.digests.SHA224Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA384Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.signers.ECDSASigner;
import org.spongycastle.crypto.signers.ECNRSigner;
import org.spongycastle.crypto.signers.HMacDSAKCalculator;
import org.spongycastle.jcajce.provider.asymmetric.util.DSABase;
import org.spongycastle.jcajce.provider.asymmetric.util.DSAEncoder;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;

/* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi */
public class SignatureSpi extends DSABase {

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$PlainDSAEncoder */
    private static class PlainDSAEncoder implements DSAEncoder {
        private PlainDSAEncoder() {
        }

        public byte[] encode(BigInteger r, BigInteger s) throws IOException {
            byte[] res;
            byte[] first = makeUnsigned(r);
            byte[] second = makeUnsigned(s);
            if (first.length > second.length) {
                res = new byte[(first.length * 2)];
            } else {
                res = new byte[(second.length * 2)];
            }
            System.arraycopy(first, 0, res, (res.length / 2) - first.length, first.length);
            System.arraycopy(second, 0, res, res.length - second.length, second.length);
            return res;
        }

        private byte[] makeUnsigned(BigInteger val) {
            byte[] res = val.toByteArray();
            if (res[0] != 0) {
                return res;
            }
            byte[] tmp = new byte[(res.length - 1)];
            System.arraycopy(res, 1, tmp, 0, tmp.length);
            return tmp;
        }

        public BigInteger[] decode(byte[] encoding) throws IOException {
            byte[] first = new byte[(encoding.length / 2)];
            byte[] second = new byte[(encoding.length / 2)];
            System.arraycopy(encoding, 0, first, 0, first.length);
            System.arraycopy(encoding, first.length, second, 0, second.length);
            return new BigInteger[]{new BigInteger(1, first), new BigInteger(1, second)};
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$StdDSAEncoder */
    private static class StdDSAEncoder implements DSAEncoder {
        private StdDSAEncoder() {
        }

        public byte[] encode(BigInteger r, BigInteger s) throws IOException {
            ASN1EncodableVector v = new ASN1EncodableVector();
            v.add(new ASN1Integer(r));
            v.add(new ASN1Integer(s));
            return new DERSequence(v).getEncoded(ASN1Encoding.DER);
        }

        public BigInteger[] decode(byte[] encoding) throws IOException {
            ASN1Sequence s = (ASN1Sequence) ASN1Primitive.fromByteArray(encoding);
            return new BigInteger[]{ASN1Integer.getInstance(s.getObjectAt(0)).getValue(), ASN1Integer.getInstance(s.getObjectAt(1)).getValue()};
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA */
    public static class ecCVCDSA extends SignatureSpi {
        public ecCVCDSA() {
            super(new SHA1Digest(), new ECDSASigner(), new PlainDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA224 */
    public static class ecCVCDSA224 extends SignatureSpi {
        public ecCVCDSA224() {
            super(new SHA224Digest(), new ECDSASigner(), new PlainDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA256 */
    public static class ecCVCDSA256 extends SignatureSpi {
        public ecCVCDSA256() {
            super(new SHA256Digest(), new ECDSASigner(), new PlainDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA384 */
    public static class ecCVCDSA384 extends SignatureSpi {
        public ecCVCDSA384() {
            super(new SHA384Digest(), new ECDSASigner(), new PlainDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA512 */
    public static class ecCVCDSA512 extends SignatureSpi {
        public ecCVCDSA512() {
            super(new SHA512Digest(), new ECDSASigner(), new PlainDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA */
    public static class ecDSA extends SignatureSpi {
        public ecDSA() {
            super(new SHA1Digest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA224 */
    public static class ecDSA224 extends SignatureSpi {
        public ecDSA224() {
            super(new SHA224Digest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA256 */
    public static class ecDSA256 extends SignatureSpi {
        public ecDSA256() {
            super(new SHA256Digest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA384 */
    public static class ecDSA384 extends SignatureSpi {
        public ecDSA384() {
            super(new SHA384Digest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA512 */
    public static class ecDSA512 extends SignatureSpi {
        public ecDSA512() {
            super(new SHA512Digest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSARipeMD160 */
    public static class ecDSARipeMD160 extends SignatureSpi {
        public ecDSARipeMD160() {
            super(new RIPEMD160Digest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSAnone */
    public static class ecDSAnone extends SignatureSpi {
        public ecDSAnone() {
            super(new NullDigest(), new ECDSASigner(), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA */
    public static class ecDetDSA extends SignatureSpi {
        public ecDetDSA() {
            super(new SHA1Digest(), new ECDSASigner(new HMacDSAKCalculator(new SHA1Digest())), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA224 */
    public static class ecDetDSA224 extends SignatureSpi {
        public ecDetDSA224() {
            super(new SHA224Digest(), new ECDSASigner(new HMacDSAKCalculator(new SHA224Digest())), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA256 */
    public static class ecDetDSA256 extends SignatureSpi {
        public ecDetDSA256() {
            super(new SHA256Digest(), new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest())), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA384 */
    public static class ecDetDSA384 extends SignatureSpi {
        public ecDetDSA384() {
            super(new SHA384Digest(), new ECDSASigner(new HMacDSAKCalculator(new SHA384Digest())), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecDetDSA512 */
    public static class ecDetDSA512 extends SignatureSpi {
        public ecDetDSA512() {
            super(new SHA512Digest(), new ECDSASigner(new HMacDSAKCalculator(new SHA512Digest())), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR */
    public static class ecNR extends SignatureSpi {
        public ecNR() {
            super(new SHA1Digest(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR224 */
    public static class ecNR224 extends SignatureSpi {
        public ecNR224() {
            super(new SHA224Digest(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR256 */
    public static class ecNR256 extends SignatureSpi {
        public ecNR256() {
            super(new SHA256Digest(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR384 */
    public static class ecNR384 extends SignatureSpi {
        public ecNR384() {
            super(new SHA384Digest(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR512 */
    public static class ecNR512 extends SignatureSpi {
        public ecNR512() {
            super(new SHA512Digest(), new ECNRSigner(), new StdDSAEncoder());
        }
    }

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.ec.SignatureSpi$ecPlainDSARP160 */
    public static class ecPlainDSARP160 extends SignatureSpi {
        public ecPlainDSARP160() {
            super(new RIPEMD160Digest(), new ECDSASigner(), new PlainDSAEncoder());
        }
    }

    SignatureSpi(Digest digest, DSA signer, DSAEncoder encoder) {
        super(digest, signer, encoder);
    }

    /* access modifiers changed from: protected */
    public void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        CipherParameters param = ECUtil.generatePublicKeyParameter(publicKey);
        this.digest.reset();
        this.signer.init(false, param);
    }

    /* access modifiers changed from: protected */
    public void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        CipherParameters param = ECUtil.generatePrivateKeyParameter(privateKey);
        this.digest.reset();
        if (this.appRandom != null) {
            this.signer.init(true, new ParametersWithRandom(param, this.appRandom));
        } else {
            this.signer.init(true, param);
        }
    }
}
