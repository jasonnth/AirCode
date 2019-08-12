package org.spongycastle.crypto.tls;

import java.io.IOException;

public class DTLSTransport implements DatagramTransport {
    private final DTLSRecordLayer recordLayer;

    DTLSTransport(DTLSRecordLayer recordLayer2) {
        this.recordLayer = recordLayer2;
    }

    public int getReceiveLimit() throws IOException {
        return this.recordLayer.getReceiveLimit();
    }

    public int getSendLimit() throws IOException {
        return this.recordLayer.getSendLimit();
    }

    public int receive(byte[] buf, int off, int len, int waitMillis) throws IOException {
        try {
            return this.recordLayer.receive(buf, off, len, waitMillis);
        } catch (TlsFatalAlert fatalAlert) {
            this.recordLayer.fail(fatalAlert.getAlertDescription());
            throw fatalAlert;
        } catch (IOException e) {
            this.recordLayer.fail(80);
            throw e;
        } catch (RuntimeException e2) {
            this.recordLayer.fail(80);
            throw new TlsFatalAlert(80, e2);
        }
    }

    public void send(byte[] buf, int off, int len) throws IOException {
        try {
            this.recordLayer.send(buf, off, len);
        } catch (TlsFatalAlert fatalAlert) {
            this.recordLayer.fail(fatalAlert.getAlertDescription());
            throw fatalAlert;
        } catch (IOException e) {
            this.recordLayer.fail(80);
            throw e;
        } catch (RuntimeException e2) {
            this.recordLayer.fail(80);
            throw new TlsFatalAlert(80, e2);
        }
    }

    public void close() throws IOException {
        this.recordLayer.close();
    }
}
