package org.spongycastle.pqc.crypto.ntru;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;

public class NTRUEncryptionPublicKeyParameters extends NTRUEncryptionKeyParameters {

    /* renamed from: h */
    public IntegerPolynomial f7150h;

    public NTRUEncryptionPublicKeyParameters(IntegerPolynomial h, NTRUEncryptionParameters params) {
        super(false, params);
        this.f7150h = h;
    }

    public NTRUEncryptionPublicKeyParameters(byte[] b, NTRUEncryptionParameters params) {
        super(false, params);
        this.f7150h = IntegerPolynomial.fromBinary(b, params.f7140N, params.f7146q);
    }

    public NTRUEncryptionPublicKeyParameters(InputStream is, NTRUEncryptionParameters params) throws IOException {
        super(false, params);
        this.f7150h = IntegerPolynomial.fromBinary(is, params.f7140N, params.f7146q);
    }

    public byte[] getEncoded() {
        return this.f7150h.toBinary(this.params.f7146q);
    }

    public void writeTo(OutputStream os) throws IOException {
        os.write(getEncoded());
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f7150h == null ? 0 : this.f7150h.hashCode()) + 31) * 31;
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
        if (!(obj instanceof NTRUEncryptionPublicKeyParameters)) {
            return false;
        }
        NTRUEncryptionPublicKeyParameters other = (NTRUEncryptionPublicKeyParameters) obj;
        if (this.f7150h == null) {
            if (other.f7150h != null) {
                return false;
            }
        } else if (!this.f7150h.equals(other.f7150h)) {
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
