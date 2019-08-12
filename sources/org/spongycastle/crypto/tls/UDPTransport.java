package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPTransport implements DatagramTransport {
    protected static final int MAX_IP_OVERHEAD = 84;
    protected static final int MIN_IP_OVERHEAD = 20;
    protected static final int UDP_OVERHEAD = 8;
    protected final int receiveLimit;
    protected final int sendLimit;
    protected final DatagramSocket socket;

    public UDPTransport(DatagramSocket socket2, int mtu) throws IOException {
        if (!socket2.isBound() || !socket2.isConnected()) {
            throw new IllegalArgumentException("'socket' must be bound and connected");
        }
        this.socket = socket2;
        this.receiveLimit = (mtu - 20) - 8;
        this.sendLimit = (mtu - 84) - 8;
    }

    public int getReceiveLimit() {
        return this.receiveLimit;
    }

    public int getSendLimit() {
        return this.sendLimit;
    }

    public int receive(byte[] buf, int off, int len, int waitMillis) throws IOException {
        this.socket.setSoTimeout(waitMillis);
        DatagramPacket packet = new DatagramPacket(buf, off, len);
        this.socket.receive(packet);
        return packet.getLength();
    }

    public void send(byte[] buf, int off, int len) throws IOException {
        if (len > getSendLimit()) {
            throw new TlsFatalAlert(80);
        }
        this.socket.send(new DatagramPacket(buf, off, len));
    }

    public void close() throws IOException {
        this.socket.close();
    }
}
