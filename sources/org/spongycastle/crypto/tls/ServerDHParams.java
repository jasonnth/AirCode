package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;

public class ServerDHParams {
    protected DHPublicKeyParameters publicKey;

    public ServerDHParams(DHPublicKeyParameters publicKey2) {
        if (publicKey2 == null) {
            throw new IllegalArgumentException("'publicKey' cannot be null");
        }
        this.publicKey = publicKey2;
    }

    public DHPublicKeyParameters getPublicKey() {
        return this.publicKey;
    }

    public void encode(OutputStream output) throws IOException {
        DHParameters dhParameters = this.publicKey.getParameters();
        BigInteger Ys = this.publicKey.getY();
        TlsDHUtils.writeDHParameter(dhParameters.getP(), output);
        TlsDHUtils.writeDHParameter(dhParameters.getG(), output);
        TlsDHUtils.writeDHParameter(Ys, output);
    }

    public static ServerDHParams parse(InputStream input) throws IOException {
        return new ServerDHParams(TlsDHUtils.validateDHPublicKey(new DHPublicKeyParameters(TlsDHUtils.readDHParameter(input), new DHParameters(TlsDHUtils.readDHParameter(input), TlsDHUtils.readDHParameter(input)))));
    }
}
