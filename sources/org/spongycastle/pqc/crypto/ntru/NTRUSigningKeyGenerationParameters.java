package org.spongycastle.pqc.crypto.ntru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.tls.CipherSuite;

public class NTRUSigningKeyGenerationParameters extends KeyGenerationParameters implements Cloneable {
    public static final NTRUSigningKeyGenerationParameters APR2011_439 = new NTRUSigningKeyGenerationParameters(439, 2048, CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA, 1, 1, 0.165d, 490.0d, 280.0d, false, true, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters APR2011_439_PROD = new NTRUSigningKeyGenerationParameters(439, 2048, 9, 8, 5, 1, 1, 0.165d, 490.0d, 280.0d, false, true, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters APR2011_743 = new NTRUSigningKeyGenerationParameters(743, 2048, 248, 1, 1, 0.127d, 560.0d, 360.0d, true, false, 0, new SHA512Digest());
    public static final NTRUSigningKeyGenerationParameters APR2011_743_PROD = new NTRUSigningKeyGenerationParameters(743, 2048, 11, 11, 15, 1, 1, 0.127d, 560.0d, 360.0d, true, false, 0, new SHA512Digest());
    public static final int BASIS_TYPE_STANDARD = 0;
    public static final int BASIS_TYPE_TRANSPOSE = 1;
    public static final int KEY_GEN_ALG_FLOAT = 1;
    public static final int KEY_GEN_ALG_RESULTANT = 0;
    public static final NTRUSigningKeyGenerationParameters TEST157 = new NTRUSigningKeyGenerationParameters(CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, 256, 29, 1, 1, 0.38d, 200.0d, 80.0d, false, false, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters TEST157_PROD = new NTRUSigningKeyGenerationParameters(CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, 256, 5, 5, 8, 1, 1, 0.38d, 200.0d, 80.0d, false, false, 0, new SHA256Digest());

    /* renamed from: B */
    public int f7151B;

    /* renamed from: N */
    public int f7152N;
    public int basisType;
    double beta;
    public double betaSq;
    int bitsF = 6;

    /* renamed from: d */
    public int f7153d;

    /* renamed from: d1 */
    public int f7154d1;

    /* renamed from: d2 */
    public int f7155d2;

    /* renamed from: d3 */
    public int f7156d3;
    public Digest hashAlg;
    public int keyGenAlg;
    double keyNormBound;
    public double keyNormBoundSq;
    double normBound;
    public double normBoundSq;
    public int polyType;
    public boolean primeCheck;

    /* renamed from: q */
    public int f7157q;
    public int signFailTolerance = 100;
    public boolean sparse;

    public NTRUSigningKeyGenerationParameters(int N, int q, int d, int B, int basisType2, double beta2, double normBound2, double keyNormBound2, boolean primeCheck2, boolean sparse2, int keyGenAlg2, Digest hashAlg2) {
        super(new SecureRandom(), N);
        this.f7152N = N;
        this.f7157q = q;
        this.f7153d = d;
        this.f7151B = B;
        this.basisType = basisType2;
        this.beta = beta2;
        this.normBound = normBound2;
        this.keyNormBound = keyNormBound2;
        this.primeCheck = primeCheck2;
        this.sparse = sparse2;
        this.keyGenAlg = keyGenAlg2;
        this.hashAlg = hashAlg2;
        this.polyType = 0;
        init();
    }

    public NTRUSigningKeyGenerationParameters(int N, int q, int d1, int d2, int d3, int B, int basisType2, double beta2, double normBound2, double keyNormBound2, boolean primeCheck2, boolean sparse2, int keyGenAlg2, Digest hashAlg2) {
        super(new SecureRandom(), N);
        this.f7152N = N;
        this.f7157q = q;
        this.f7154d1 = d1;
        this.f7155d2 = d2;
        this.f7156d3 = d3;
        this.f7151B = B;
        this.basisType = basisType2;
        this.beta = beta2;
        this.normBound = normBound2;
        this.keyNormBound = keyNormBound2;
        this.primeCheck = primeCheck2;
        this.sparse = sparse2;
        this.keyGenAlg = keyGenAlg2;
        this.hashAlg = hashAlg2;
        this.polyType = 1;
        init();
    }

    private void init() {
        this.betaSq = this.beta * this.beta;
        this.normBoundSq = this.normBound * this.normBound;
        this.keyNormBoundSq = this.keyNormBound * this.keyNormBound;
    }

    public NTRUSigningKeyGenerationParameters(InputStream is) throws IOException {
        super(new SecureRandom(), 0);
        DataInputStream dis = new DataInputStream(is);
        this.f7152N = dis.readInt();
        this.f7157q = dis.readInt();
        this.f7153d = dis.readInt();
        this.f7154d1 = dis.readInt();
        this.f7155d2 = dis.readInt();
        this.f7156d3 = dis.readInt();
        this.f7151B = dis.readInt();
        this.basisType = dis.readInt();
        this.beta = dis.readDouble();
        this.normBound = dis.readDouble();
        this.keyNormBound = dis.readDouble();
        this.signFailTolerance = dis.readInt();
        this.primeCheck = dis.readBoolean();
        this.sparse = dis.readBoolean();
        this.bitsF = dis.readInt();
        this.keyGenAlg = dis.read();
        String alg = dis.readUTF();
        if ("SHA-512".equals(alg)) {
            this.hashAlg = new SHA512Digest();
        } else if ("SHA-256".equals(alg)) {
            this.hashAlg = new SHA256Digest();
        }
        this.polyType = dis.read();
        init();
    }

    public void writeTo(OutputStream os) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeInt(this.f7152N);
        dos.writeInt(this.f7157q);
        dos.writeInt(this.f7153d);
        dos.writeInt(this.f7154d1);
        dos.writeInt(this.f7155d2);
        dos.writeInt(this.f7156d3);
        dos.writeInt(this.f7151B);
        dos.writeInt(this.basisType);
        dos.writeDouble(this.beta);
        dos.writeDouble(this.normBound);
        dos.writeDouble(this.keyNormBound);
        dos.writeInt(this.signFailTolerance);
        dos.writeBoolean(this.primeCheck);
        dos.writeBoolean(this.sparse);
        dos.writeInt(this.bitsF);
        dos.write(this.keyGenAlg);
        dos.writeUTF(this.hashAlg.getAlgorithmName());
        dos.write(this.polyType);
    }

    public NTRUSigningParameters getSigningParameters() {
        return new NTRUSigningParameters(this.f7152N, this.f7157q, this.f7153d, this.f7151B, this.beta, this.normBound, this.hashAlg);
    }

    public NTRUSigningKeyGenerationParameters clone() {
        if (this.polyType == 0) {
            return new NTRUSigningKeyGenerationParameters(this.f7152N, this.f7157q, this.f7153d, this.f7151B, this.basisType, this.beta, this.normBound, this.keyNormBound, this.primeCheck, this.sparse, this.keyGenAlg, this.hashAlg);
        }
        return new NTRUSigningKeyGenerationParameters(this.f7152N, this.f7157q, this.f7154d1, this.f7155d2, this.f7156d3, this.f7151B, this.basisType, this.beta, this.normBound, this.keyNormBound, this.primeCheck, this.sparse, this.keyGenAlg, this.hashAlg);
    }

    public int hashCode() {
        int hashCode;
        int i = 1231;
        int result = ((((this.f7151B + 31) * 31) + this.f7152N) * 31) + this.basisType;
        long temp = Double.doubleToLongBits(this.beta);
        int result2 = (result * 31) + ((int) ((temp >>> 32) ^ temp));
        long temp2 = Double.doubleToLongBits(this.betaSq);
        int i2 = ((((((((((((result2 * 31) + ((int) ((temp2 >>> 32) ^ temp2))) * 31) + this.bitsF) * 31) + this.f7153d) * 31) + this.f7154d1) * 31) + this.f7155d2) * 31) + this.f7156d3) * 31;
        if (this.hashAlg == null) {
            hashCode = 0;
        } else {
            hashCode = this.hashAlg.getAlgorithmName().hashCode();
        }
        int result3 = ((i2 + hashCode) * 31) + this.keyGenAlg;
        long temp3 = Double.doubleToLongBits(this.keyNormBound);
        int result4 = (result3 * 31) + ((int) ((temp3 >>> 32) ^ temp3));
        long temp4 = Double.doubleToLongBits(this.keyNormBoundSq);
        int result5 = (result4 * 31) + ((int) ((temp4 >>> 32) ^ temp4));
        long temp5 = Double.doubleToLongBits(this.normBound);
        int result6 = (result5 * 31) + ((int) ((temp5 >>> 32) ^ temp5));
        long temp6 = Double.doubleToLongBits(this.normBoundSq);
        int i3 = ((((((((((result6 * 31) + ((int) ((temp6 >>> 32) ^ temp6))) * 31) + this.polyType) * 31) + (this.primeCheck ? 1231 : 1237)) * 31) + this.f7157q) * 31) + this.signFailTolerance) * 31;
        if (!this.sparse) {
            i = 1237;
        }
        return i3 + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof NTRUSigningKeyGenerationParameters)) {
            return false;
        }
        NTRUSigningKeyGenerationParameters other = (NTRUSigningKeyGenerationParameters) obj;
        if (this.f7151B != other.f7151B) {
            return false;
        }
        if (this.f7152N != other.f7152N) {
            return false;
        }
        if (this.basisType != other.basisType) {
            return false;
        }
        if (Double.doubleToLongBits(this.beta) != Double.doubleToLongBits(other.beta)) {
            return false;
        }
        if (Double.doubleToLongBits(this.betaSq) != Double.doubleToLongBits(other.betaSq)) {
            return false;
        }
        if (this.bitsF != other.bitsF) {
            return false;
        }
        if (this.f7153d != other.f7153d) {
            return false;
        }
        if (this.f7154d1 != other.f7154d1) {
            return false;
        }
        if (this.f7155d2 != other.f7155d2) {
            return false;
        }
        if (this.f7156d3 != other.f7156d3) {
            return false;
        }
        if (this.hashAlg == null) {
            if (other.hashAlg != null) {
                return false;
            }
        } else if (!this.hashAlg.getAlgorithmName().equals(other.hashAlg.getAlgorithmName())) {
            return false;
        }
        if (this.keyGenAlg != other.keyGenAlg) {
            return false;
        }
        if (Double.doubleToLongBits(this.keyNormBound) != Double.doubleToLongBits(other.keyNormBound)) {
            return false;
        }
        if (Double.doubleToLongBits(this.keyNormBoundSq) != Double.doubleToLongBits(other.keyNormBoundSq)) {
            return false;
        }
        if (Double.doubleToLongBits(this.normBound) != Double.doubleToLongBits(other.normBound)) {
            return false;
        }
        if (Double.doubleToLongBits(this.normBoundSq) != Double.doubleToLongBits(other.normBoundSq)) {
            return false;
        }
        if (this.polyType != other.polyType) {
            return false;
        }
        if (this.primeCheck != other.primeCheck) {
            return false;
        }
        if (this.f7157q != other.f7157q) {
            return false;
        }
        if (this.signFailTolerance != other.signFailTolerance) {
            return false;
        }
        if (this.sparse != other.sparse) {
            return false;
        }
        return true;
    }

    public String toString() {
        DecimalFormat format = new DecimalFormat("0.00");
        StringBuilder output = new StringBuilder("SignatureParameters(N=" + this.f7152N + " q=" + this.f7157q);
        if (this.polyType == 0) {
            output.append(" polyType=SIMPLE d=" + this.f7153d);
        } else {
            output.append(" polyType=PRODUCT d1=" + this.f7154d1 + " d2=" + this.f7155d2 + " d3=" + this.f7156d3);
        }
        output.append(" B=" + this.f7151B + " basisType=" + this.basisType + " beta=" + format.format(this.beta) + " normBound=" + format.format(this.normBound) + " keyNormBound=" + format.format(this.keyNormBound) + " prime=" + this.primeCheck + " sparse=" + this.sparse + " keyGenAlg=" + this.keyGenAlg + " hashAlg=" + this.hashAlg + ")");
        return output.toString();
    }
}
