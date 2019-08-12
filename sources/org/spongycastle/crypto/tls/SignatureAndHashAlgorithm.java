package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SignatureAndHashAlgorithm {
    protected short hash;
    protected short signature;

    public SignatureAndHashAlgorithm(short hash2, short signature2) {
        if (!TlsUtils.isValidUint8(hash2)) {
            throw new IllegalArgumentException("'hash' should be a uint8");
        } else if (!TlsUtils.isValidUint8(signature2)) {
            throw new IllegalArgumentException("'signature' should be a uint8");
        } else if (signature2 == 0) {
            throw new IllegalArgumentException("'signature' MUST NOT be \"anonymous\"");
        } else {
            this.hash = hash2;
            this.signature = signature2;
        }
    }

    public short getHash() {
        return this.hash;
    }

    public short getSignature() {
        return this.signature;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SignatureAndHashAlgorithm)) {
            return false;
        }
        SignatureAndHashAlgorithm other = (SignatureAndHashAlgorithm) obj;
        if (other.getHash() == getHash() && other.getSignature() == getSignature()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (getHash() << 16) | getSignature();
    }

    public void encode(OutputStream output) throws IOException {
        TlsUtils.writeUint8(getHash(), output);
        TlsUtils.writeUint8(getSignature(), output);
    }

    public static SignatureAndHashAlgorithm parse(InputStream input) throws IOException {
        return new SignatureAndHashAlgorithm(TlsUtils.readUint8(input), TlsUtils.readUint8(input));
    }
}
