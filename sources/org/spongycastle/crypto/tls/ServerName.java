package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ServerName {
    protected Object name;
    protected short nameType;

    public ServerName(short nameType2, Object name2) {
        if (!isCorrectType(nameType2, name2)) {
            throw new IllegalArgumentException("'name' is not an instance of the correct type");
        }
        this.nameType = nameType2;
        this.name = name2;
    }

    public short getNameType() {
        return this.nameType;
    }

    public Object getName() {
        return this.name;
    }

    public String getHostName() {
        if (isCorrectType(0, this.name)) {
            return (String) this.name;
        }
        throw new IllegalStateException("'name' is not a HostName string");
    }

    public void encode(OutputStream output) throws IOException {
        TlsUtils.writeUint8(this.nameType, output);
        switch (this.nameType) {
            case 0:
                byte[] asciiEncoding = ((String) this.name).getBytes("ASCII");
                if (asciiEncoding.length < 1) {
                    throw new TlsFatalAlert(80);
                }
                TlsUtils.writeOpaque16(asciiEncoding, output);
                return;
            default:
                throw new TlsFatalAlert(80);
        }
    }

    public static ServerName parse(InputStream input) throws IOException {
        short name_type = TlsUtils.readUint8(input);
        switch (name_type) {
            case 0:
                byte[] asciiEncoding = TlsUtils.readOpaque16(input);
                if (asciiEncoding.length >= 1) {
                    return new ServerName(name_type, new String(asciiEncoding, "ASCII"));
                }
                throw new TlsFatalAlert(50);
            default:
                throw new TlsFatalAlert(50);
        }
    }

    protected static boolean isCorrectType(short nameType2, Object name2) {
        switch (nameType2) {
            case 0:
                return name2 instanceof String;
            default:
                throw new IllegalArgumentException("'name' is an unsupported value");
        }
    }
}
