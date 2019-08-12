package org.spongycastle.pqc.crypto.ntru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;

public class NTRUSigningParameters implements Cloneable {

    /* renamed from: B */
    public int f7160B;

    /* renamed from: N */
    public int f7161N;
    double beta;
    public double betaSq;
    int bitsF = 6;

    /* renamed from: d */
    public int f7162d;

    /* renamed from: d1 */
    public int f7163d1;

    /* renamed from: d2 */
    public int f7164d2;

    /* renamed from: d3 */
    public int f7165d3;
    public Digest hashAlg;
    double normBound;
    public double normBoundSq;

    /* renamed from: q */
    public int f7166q;
    public int signFailTolerance = 100;

    public NTRUSigningParameters(int N, int q, int d, int B, double beta2, double normBound2, Digest hashAlg2) {
        this.f7161N = N;
        this.f7166q = q;
        this.f7162d = d;
        this.f7160B = B;
        this.beta = beta2;
        this.normBound = normBound2;
        this.hashAlg = hashAlg2;
        init();
    }

    public NTRUSigningParameters(int N, int q, int d1, int d2, int d3, int B, double beta2, double normBound2, double keyNormBound, Digest hashAlg2) {
        this.f7161N = N;
        this.f7166q = q;
        this.f7163d1 = d1;
        this.f7164d2 = d2;
        this.f7165d3 = d3;
        this.f7160B = B;
        this.beta = beta2;
        this.normBound = normBound2;
        this.hashAlg = hashAlg2;
        init();
    }

    private void init() {
        this.betaSq = this.beta * this.beta;
        this.normBoundSq = this.normBound * this.normBound;
    }

    public NTRUSigningParameters(InputStream is) throws IOException {
        DataInputStream dis = new DataInputStream(is);
        this.f7161N = dis.readInt();
        this.f7166q = dis.readInt();
        this.f7162d = dis.readInt();
        this.f7163d1 = dis.readInt();
        this.f7164d2 = dis.readInt();
        this.f7165d3 = dis.readInt();
        this.f7160B = dis.readInt();
        this.beta = dis.readDouble();
        this.normBound = dis.readDouble();
        this.signFailTolerance = dis.readInt();
        this.bitsF = dis.readInt();
        String alg = dis.readUTF();
        if ("SHA-512".equals(alg)) {
            this.hashAlg = new SHA512Digest();
        } else if ("SHA-256".equals(alg)) {
            this.hashAlg = new SHA256Digest();
        }
        init();
    }

    public void writeTo(OutputStream os) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeInt(this.f7161N);
        dos.writeInt(this.f7166q);
        dos.writeInt(this.f7162d);
        dos.writeInt(this.f7163d1);
        dos.writeInt(this.f7164d2);
        dos.writeInt(this.f7165d3);
        dos.writeInt(this.f7160B);
        dos.writeDouble(this.beta);
        dos.writeDouble(this.normBound);
        dos.writeInt(this.signFailTolerance);
        dos.writeInt(this.bitsF);
        dos.writeUTF(this.hashAlg.getAlgorithmName());
    }

    public NTRUSigningParameters clone() {
        return new NTRUSigningParameters(this.f7161N, this.f7166q, this.f7162d, this.f7160B, this.beta, this.normBound, this.hashAlg);
    }

    public int hashCode() {
        int result = ((this.f7160B + 31) * 31) + this.f7161N;
        long temp = Double.doubleToLongBits(this.beta);
        int result2 = (result * 31) + ((int) ((temp >>> 32) ^ temp));
        long temp2 = Double.doubleToLongBits(this.betaSq);
        int result3 = (((((((((((((result2 * 31) + ((int) ((temp2 >>> 32) ^ temp2))) * 31) + this.bitsF) * 31) + this.f7162d) * 31) + this.f7163d1) * 31) + this.f7164d2) * 31) + this.f7165d3) * 31) + (this.hashAlg == null ? 0 : this.hashAlg.getAlgorithmName().hashCode());
        long temp3 = Double.doubleToLongBits(this.normBound);
        int result4 = (result3 * 31) + ((int) ((temp3 >>> 32) ^ temp3));
        long temp4 = Double.doubleToLongBits(this.normBoundSq);
        return (((((result4 * 31) + ((int) ((temp4 >>> 32) ^ temp4))) * 31) + this.f7166q) * 31) + this.signFailTolerance;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof NTRUSigningParameters)) {
            return false;
        }
        NTRUSigningParameters other = (NTRUSigningParameters) obj;
        if (this.f7160B != other.f7160B) {
            return false;
        }
        if (this.f7161N != other.f7161N) {
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
        if (this.f7162d != other.f7162d) {
            return false;
        }
        if (this.f7163d1 != other.f7163d1) {
            return false;
        }
        if (this.f7164d2 != other.f7164d2) {
            return false;
        }
        if (this.f7165d3 != other.f7165d3) {
            return false;
        }
        if (this.hashAlg == null) {
            if (other.hashAlg != null) {
                return false;
            }
        } else if (!this.hashAlg.getAlgorithmName().equals(other.hashAlg.getAlgorithmName())) {
            return false;
        }
        if (Double.doubleToLongBits(this.normBound) != Double.doubleToLongBits(other.normBound)) {
            return false;
        }
        if (Double.doubleToLongBits(this.normBoundSq) != Double.doubleToLongBits(other.normBoundSq)) {
            return false;
        }
        if (this.f7166q != other.f7166q) {
            return false;
        }
        if (this.signFailTolerance != other.signFailTolerance) {
            return false;
        }
        return true;
    }

    public String toString() {
        DecimalFormat format = new DecimalFormat("0.00");
        StringBuilder output = new StringBuilder("SignatureParameters(N=" + this.f7161N + " q=" + this.f7166q);
        output.append(" B=" + this.f7160B + " beta=" + format.format(this.beta) + " normBound=" + format.format(this.normBound) + " hashAlg=" + this.hashAlg + ")");
        return output.toString();
    }
}
