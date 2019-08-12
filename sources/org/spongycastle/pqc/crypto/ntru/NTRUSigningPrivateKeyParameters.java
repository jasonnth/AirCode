package org.spongycastle.pqc.crypto.ntru;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.SparseTernaryPolynomial;

public class NTRUSigningPrivateKeyParameters extends AsymmetricKeyParameter {
    private List<Basis> bases;
    private NTRUSigningPublicKeyParameters publicKey;

    public static class Basis {

        /* renamed from: f */
        public Polynomial f7167f;
        public Polynomial fPrime;

        /* renamed from: h */
        public IntegerPolynomial f7168h;
        NTRUSigningKeyGenerationParameters params;

        protected Basis(Polynomial f, Polynomial fPrime2, IntegerPolynomial h, NTRUSigningKeyGenerationParameters params2) {
            this.f7167f = f;
            this.fPrime = fPrime2;
            this.f7168h = h;
            this.params = params2;
        }

        Basis(InputStream is, NTRUSigningKeyGenerationParameters params2, boolean include_h) throws IOException {
            int N = params2.f7152N;
            int q = params2.f7157q;
            int d1 = params2.f7154d1;
            int d2 = params2.f7155d2;
            int d3 = params2.f7156d3;
            boolean sparse = params2.sparse;
            this.params = params2;
            if (params2.polyType == 1) {
                this.f7167f = ProductFormPolynomial.fromBinary(is, N, d1, d2, d3 + 1, d3);
            } else {
                IntegerPolynomial fInt = IntegerPolynomial.fromBinary3Tight(is, N);
                this.f7167f = sparse ? new SparseTernaryPolynomial(fInt) : new DenseTernaryPolynomial(fInt);
            }
            if (params2.basisType == 0) {
                IntegerPolynomial fPrimeInt = IntegerPolynomial.fromBinary(is, N, q);
                for (int i = 0; i < fPrimeInt.coeffs.length; i++) {
                    int[] iArr = fPrimeInt.coeffs;
                    iArr[i] = iArr[i] - (q / 2);
                }
                this.fPrime = fPrimeInt;
            } else if (params2.polyType == 1) {
                this.fPrime = ProductFormPolynomial.fromBinary(is, N, d1, d2, d3 + 1, d3);
            } else {
                this.fPrime = IntegerPolynomial.fromBinary3Tight(is, N);
            }
            if (include_h) {
                this.f7168h = IntegerPolynomial.fromBinary(is, N, q);
            }
        }

        /* access modifiers changed from: 0000 */
        public void encode(OutputStream os, boolean include_h) throws IOException {
            int q = this.params.f7157q;
            os.write(getEncoded(this.f7167f));
            if (this.params.basisType == 0) {
                IntegerPolynomial fPrimeInt = this.fPrime.toIntegerPolynomial();
                for (int i = 0; i < fPrimeInt.coeffs.length; i++) {
                    int[] iArr = fPrimeInt.coeffs;
                    iArr[i] = iArr[i] + (q / 2);
                }
                os.write(fPrimeInt.toBinary(q));
            } else {
                os.write(getEncoded(this.fPrime));
            }
            if (include_h) {
                os.write(this.f7168h.toBinary(q));
            }
        }

        private byte[] getEncoded(Polynomial p) {
            if (p instanceof ProductFormPolynomial) {
                return ((ProductFormPolynomial) p).toBinary();
            }
            return p.toIntegerPolynomial().toBinary3Tight();
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((((this.f7167f == null ? 0 : this.f7167f.hashCode()) + 31) * 31) + (this.fPrime == null ? 0 : this.fPrime.hashCode())) * 31) + (this.f7168h == null ? 0 : this.f7168h.hashCode())) * 31;
            if (this.params != null) {
                i = this.params.hashCode();
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
            if (!(obj instanceof Basis)) {
                return false;
            }
            Basis other = (Basis) obj;
            if (this.f7167f == null) {
                if (other.f7167f != null) {
                    return false;
                }
            } else if (!this.f7167f.equals(other.f7167f)) {
                return false;
            }
            if (this.fPrime == null) {
                if (other.fPrime != null) {
                    return false;
                }
            } else if (!this.fPrime.equals(other.fPrime)) {
                return false;
            }
            if (this.f7168h == null) {
                if (other.f7168h != null) {
                    return false;
                }
            } else if (!this.f7168h.equals(other.f7168h)) {
                return false;
            }
            if (this.params == null) {
                if (other.params != null) {
                    return false;
                }
                return true;
            } else if (!this.params.equals(other.params)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public NTRUSigningPrivateKeyParameters(byte[] b, NTRUSigningKeyGenerationParameters params) throws IOException {
        this((InputStream) new ByteArrayInputStream(b), params);
    }

    public NTRUSigningPrivateKeyParameters(InputStream is, NTRUSigningKeyGenerationParameters params) throws IOException {
        super(true);
        this.bases = new ArrayList();
        int i = 0;
        while (i <= params.f7151B) {
            add(new Basis(is, params, i != 0));
            i++;
        }
        this.publicKey = new NTRUSigningPublicKeyParameters(is, params.getSigningParameters());
    }

    public NTRUSigningPrivateKeyParameters(List<Basis> bases2, NTRUSigningPublicKeyParameters publicKey2) {
        super(true);
        this.bases = new ArrayList(bases2);
        this.publicKey = publicKey2;
    }

    private void add(Basis b) {
        this.bases.add(b);
    }

    public Basis getBasis(int i) {
        return (Basis) this.bases.get(i);
    }

    public NTRUSigningPublicKeyParameters getPublicKey() {
        return this.publicKey;
    }

    public byte[] getEncoded() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int i = 0;
        while (i < this.bases.size()) {
            ((Basis) this.bases.get(i)).encode(os, i != 0);
            i++;
        }
        os.write(this.publicKey.getEncoded());
        return os.toByteArray();
    }

    public void writeTo(OutputStream os) throws IOException {
        os.write(getEncoded());
    }

    public int hashCode() {
        int result = 1 * 31;
        if (this.bases == null) {
            return result;
        }
        int result2 = this.bases.hashCode() + 31;
        for (Basis basis : this.bases) {
            result2 += basis.hashCode();
        }
        return result2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NTRUSigningPrivateKeyParameters other = (NTRUSigningPrivateKeyParameters) obj;
        if ((this.bases == null) != (other.bases == null)) {
            return false;
        }
        if (this.bases == null) {
            return true;
        }
        if (this.bases.size() != other.bases.size()) {
            return false;
        }
        for (int i = 0; i < this.bases.size(); i++) {
            Basis basis1 = (Basis) this.bases.get(i);
            Basis basis2 = (Basis) other.bases.get(i);
            if (!basis1.f7167f.equals(basis2.f7167f) || !basis1.fPrime.equals(basis2.fPrime)) {
                return false;
            }
            if ((i != 0 && !basis1.f7168h.equals(basis2.f7168h)) || !basis1.params.equals(basis2.params)) {
                return false;
            }
        }
        return true;
    }
}
