package org.spongycastle.pqc.math.ntru.polynomial;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import org.spongycastle.util.Arrays;

public class ProductFormPolynomial implements Polynomial {

    /* renamed from: f1 */
    private SparseTernaryPolynomial f7238f1;

    /* renamed from: f2 */
    private SparseTernaryPolynomial f7239f2;

    /* renamed from: f3 */
    private SparseTernaryPolynomial f7240f3;

    public ProductFormPolynomial(SparseTernaryPolynomial f1, SparseTernaryPolynomial f2, SparseTernaryPolynomial f3) {
        this.f7238f1 = f1;
        this.f7239f2 = f2;
        this.f7240f3 = f3;
    }

    public static ProductFormPolynomial generateRandom(int N, int df1, int df2, int df3Ones, int df3NegOnes, SecureRandom random) {
        return new ProductFormPolynomial(SparseTernaryPolynomial.generateRandom(N, df1, df1, random), SparseTernaryPolynomial.generateRandom(N, df2, df2, random), SparseTernaryPolynomial.generateRandom(N, df3Ones, df3NegOnes, random));
    }

    public static ProductFormPolynomial fromBinary(byte[] data, int N, int df1, int df2, int df3Ones, int df3NegOnes) throws IOException {
        return fromBinary((InputStream) new ByteArrayInputStream(data), N, df1, df2, df3Ones, df3NegOnes);
    }

    public static ProductFormPolynomial fromBinary(InputStream is, int N, int df1, int df2, int df3Ones, int df3NegOnes) throws IOException {
        return new ProductFormPolynomial(SparseTernaryPolynomial.fromBinary(is, N, df1, df1), SparseTernaryPolynomial.fromBinary(is, N, df2, df2), SparseTernaryPolynomial.fromBinary(is, N, df3Ones, df3NegOnes));
    }

    public byte[] toBinary() {
        byte[] f1Bin = this.f7238f1.toBinary();
        byte[] f2Bin = this.f7239f2.toBinary();
        byte[] f3Bin = this.f7240f3.toBinary();
        byte[] all = Arrays.copyOf(f1Bin, f1Bin.length + f2Bin.length + f3Bin.length);
        System.arraycopy(f2Bin, 0, all, f1Bin.length, f2Bin.length);
        System.arraycopy(f3Bin, 0, all, f1Bin.length + f2Bin.length, f3Bin.length);
        return all;
    }

    public IntegerPolynomial mult(IntegerPolynomial b) {
        IntegerPolynomial c = this.f7239f2.mult(this.f7238f1.mult(b));
        c.add(this.f7240f3.mult(b));
        return c;
    }

    public BigIntPolynomial mult(BigIntPolynomial b) {
        BigIntPolynomial c = this.f7239f2.mult(this.f7238f1.mult(b));
        c.add(this.f7240f3.mult(b));
        return c;
    }

    public IntegerPolynomial toIntegerPolynomial() {
        IntegerPolynomial i = this.f7238f1.mult(this.f7239f2.toIntegerPolynomial());
        i.add(this.f7240f3.toIntegerPolynomial());
        return i;
    }

    public IntegerPolynomial mult(IntegerPolynomial poly2, int modulus) {
        IntegerPolynomial c = mult(poly2);
        c.mod(modulus);
        return c;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((this.f7238f1 == null ? 0 : this.f7238f1.hashCode()) + 31) * 31) + (this.f7239f2 == null ? 0 : this.f7239f2.hashCode())) * 31;
        if (this.f7240f3 != null) {
            i = this.f7240f3.hashCode();
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProductFormPolynomial other = (ProductFormPolynomial) obj;
        if (this.f7238f1 == null) {
            if (other.f7238f1 != null) {
                return false;
            }
        } else if (!this.f7238f1.equals(other.f7238f1)) {
            return false;
        }
        if (this.f7239f2 == null) {
            if (other.f7239f2 != null) {
                return false;
            }
        } else if (!this.f7239f2.equals(other.f7239f2)) {
            return false;
        }
        if (this.f7240f3 == null) {
            if (other.f7240f3 != null) {
                return false;
            }
            return true;
        } else if (!this.f7240f3.equals(other.f7240f3)) {
            return false;
        } else {
            return true;
        }
    }
}
