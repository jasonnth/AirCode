package org.spongycastle.pqc.crypto.ntru;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.SparseTernaryPolynomial;

public class NTRUEncryptionPrivateKeyParameters extends NTRUEncryptionKeyParameters {

    /* renamed from: fp */
    public IntegerPolynomial f7147fp;

    /* renamed from: h */
    public IntegerPolynomial f7148h;

    /* renamed from: t */
    public Polynomial f7149t;

    public NTRUEncryptionPrivateKeyParameters(IntegerPolynomial h, Polynomial t, IntegerPolynomial fp, NTRUEncryptionParameters params) {
        super(true, params);
        this.f7148h = h;
        this.f7149t = t;
        this.f7147fp = fp;
    }

    public NTRUEncryptionPrivateKeyParameters(byte[] b, NTRUEncryptionParameters params) throws IOException {
        this((InputStream) new ByteArrayInputStream(b), params);
    }

    public NTRUEncryptionPrivateKeyParameters(InputStream is, NTRUEncryptionParameters params) throws IOException {
        super(true, params);
        if (params.polyType == 1) {
            int N = params.f7140N;
            int df1 = params.df1;
            int df2 = params.df2;
            int df3Ones = params.df3;
            int df3NegOnes = params.fastFp ? params.df3 : params.df3 - 1;
            this.f7148h = IntegerPolynomial.fromBinary(is, params.f7140N, params.f7146q);
            this.f7149t = ProductFormPolynomial.fromBinary(is, N, df1, df2, df3Ones, df3NegOnes);
        } else {
            this.f7148h = IntegerPolynomial.fromBinary(is, params.f7140N, params.f7146q);
            IntegerPolynomial fInt = IntegerPolynomial.fromBinary3Tight(is, params.f7140N);
            this.f7149t = params.sparse ? new SparseTernaryPolynomial(fInt) : new DenseTernaryPolynomial(fInt);
        }
        init();
    }

    private void init() {
        if (this.params.fastFp) {
            this.f7147fp = new IntegerPolynomial(this.params.f7140N);
            this.f7147fp.coeffs[0] = 1;
            return;
        }
        this.f7147fp = this.f7149t.toIntegerPolynomial().invertF3();
    }

    public byte[] getEncoded() {
        byte[] tBytes;
        byte[] hBytes = this.f7148h.toBinary(this.params.f7146q);
        if (this.f7149t instanceof ProductFormPolynomial) {
            tBytes = ((ProductFormPolynomial) this.f7149t).toBinary();
        } else {
            tBytes = this.f7149t.toIntegerPolynomial().toBinary3Tight();
        }
        byte[] res = new byte[(hBytes.length + tBytes.length)];
        System.arraycopy(hBytes, 0, res, 0, hBytes.length);
        System.arraycopy(tBytes, 0, res, hBytes.length, tBytes.length);
        return res;
    }

    public void writeTo(OutputStream os) throws IOException {
        os.write(getEncoded());
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((this.params == null ? 0 : this.params.hashCode()) + 31) * 31) + (this.f7149t == null ? 0 : this.f7149t.hashCode())) * 31;
        if (this.f7148h != null) {
            i = this.f7148h.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof NTRUEncryptionPrivateKeyParameters)) {
            return false;
        }
        NTRUEncryptionPrivateKeyParameters other = (NTRUEncryptionPrivateKeyParameters) obj;
        if (this.params == null) {
            if (other.params != null) {
                return false;
            }
        } else if (!this.params.equals(other.params)) {
            return false;
        }
        if (this.f7149t == null) {
            if (other.f7149t != null) {
                return false;
            }
        } else if (!this.f7149t.equals(other.f7149t)) {
            return false;
        }
        if (!this.f7148h.equals(other.f7148h)) {
            return false;
        }
        return true;
    }
}
