package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

public class ServerNameList {
    protected Vector serverNameList;

    public ServerNameList(Vector serverNameList2) {
        if (serverNameList2 == null || serverNameList2.isEmpty()) {
            throw new IllegalArgumentException("'serverNameList' must not be null or empty");
        }
        this.serverNameList = serverNameList2;
    }

    public Vector getServerNameList() {
        return this.serverNameList;
    }

    public void encode(OutputStream output) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        for (int i = 0; i < this.serverNameList.size(); i++) {
            ((ServerName) this.serverNameList.elementAt(i)).encode(buf);
        }
        TlsUtils.checkUint16(buf.size());
        TlsUtils.writeUint16(buf.size(), output);
        buf.writeTo(output);
    }

    public static ServerNameList parse(InputStream input) throws IOException {
        int length = TlsUtils.readUint16(input);
        if (length < 1) {
            throw new TlsFatalAlert(50);
        }
        ByteArrayInputStream buf = new ByteArrayInputStream(TlsUtils.readFully(length, input));
        Vector server_name_list = new Vector();
        while (buf.available() > 0) {
            server_name_list.addElement(ServerName.parse(buf));
        }
        return new ServerNameList(server_name_list);
    }
}
