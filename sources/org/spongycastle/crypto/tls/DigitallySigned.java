package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DigitallySigned {
    protected SignatureAndHashAlgorithm algorithm;
    protected byte[] signature;

    public DigitallySigned(SignatureAndHashAlgorithm algorithm2, byte[] signature2) {
        if (signature2 == null) {
            throw new IllegalArgumentException("'signature' cannot be null");
        }
        this.algorithm = algorithm2;
        this.signature = signature2;
    }

    public SignatureAndHashAlgorithm getAlgorithm() {
        return this.algorithm;
    }

    public byte[] getSignature() {
        return this.signature;
    }

    public void encode(OutputStream output) throws IOException {
        if (this.algorithm != null) {
            this.algorithm.encode(output);
        }
        TlsUtils.writeOpaque16(this.signature, output);
    }

    public static DigitallySigned parse(TlsContext context, InputStream input) throws IOException {
        SignatureAndHashAlgorithm algorithm2 = null;
        if (TlsUtils.isTLSv12(context)) {
            algorithm2 = SignatureAndHashAlgorithm.parse(input);
        }
        return new DigitallySigned(algorithm2, TlsUtils.readOpaque16(input));
    }
}
