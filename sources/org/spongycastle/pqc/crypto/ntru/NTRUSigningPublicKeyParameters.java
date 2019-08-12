package org.spongycastle.pqc.crypto.ntru;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;

public class NTRUSigningPublicKeyParameters extends AsymmetricKeyParameter {

    /* renamed from: h */
    public IntegerPolynomial f7169h;
    private NTRUSigningParameters params;

    public NTRUSigningPublicKeyParameters(IntegerPolynomial h, NTRUSigningParameters params2) {
        super(false);
        this.f7169h = h;
        this.params = params2;
    }

    public NTRUSigningPublicKeyParameters(byte[] b, NTRUSigningParameters params2) {
        super(false);
        this.f7169h = IntegerPolynomial.fromBinary(b, params2.f7161N, params2.f7166q);
        this.params = params2;
    }

    public NTRUSigningPublicKeyParameters(InputStream is, NTRUSigningParameters params2) throws IOException {
        super(false);
        this.f7169h = IntegerPolynomial.fromBinary(is, params2.f7161N, params2.f7166q);
        this.params = params2;
    }

    public byte[] getEncoded() {
        return this.f7169h.toBinary(this.params.f7166q);
    }

    public void writeTo(OutputStream os) throws IOException {
        os.write(getEncoded());
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f7169h == null ? 0 : this.f7169h.hashCode()) + 31) * 31;
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        NTRUSigningPublicKeyParameters other = (NTRUSigningPublicKeyParameters) obj;
        if (this.f7169h == null) {
            if (other.f7169h != null) {
                return false;
            }
        } else if (!this.f7169h.equals(other.f7169h)) {
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
