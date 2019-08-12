package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import org.spongycastle.util.Arrays;

public class ServerSRPParams {

    /* renamed from: B */
    protected BigInteger f6889B;

    /* renamed from: N */
    protected BigInteger f6890N;

    /* renamed from: g */
    protected BigInteger f6891g;

    /* renamed from: s */
    protected byte[] f6892s;

    public ServerSRPParams(BigInteger N, BigInteger g, byte[] s, BigInteger B) {
        this.f6890N = N;
        this.f6891g = g;
        this.f6892s = Arrays.clone(s);
        this.f6889B = B;
    }

    public BigInteger getB() {
        return this.f6889B;
    }

    public BigInteger getG() {
        return this.f6891g;
    }

    public BigInteger getN() {
        return this.f6890N;
    }

    public byte[] getS() {
        return this.f6892s;
    }

    public void encode(OutputStream output) throws IOException {
        TlsSRPUtils.writeSRPParameter(this.f6890N, output);
        TlsSRPUtils.writeSRPParameter(this.f6891g, output);
        TlsUtils.writeOpaque8(this.f6892s, output);
        TlsSRPUtils.writeSRPParameter(this.f6889B, output);
    }

    public static ServerSRPParams parse(InputStream input) throws IOException {
        return new ServerSRPParams(TlsSRPUtils.readSRPParameter(input), TlsSRPUtils.readSRPParameter(input), TlsUtils.readOpaque8(input), TlsSRPUtils.readSRPParameter(input));
    }
}
