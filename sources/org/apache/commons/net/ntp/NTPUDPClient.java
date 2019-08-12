package org.apache.commons.net.ntp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import org.apache.commons.net.DatagramSocketClient;
import org.spongycastle.asn1.eac.EACTags;

public final class NTPUDPClient extends DatagramSocketClient {
    private int _version = 3;

    public TimeInfo getTime(InetAddress host, int port) throws IOException {
        if (!isOpen()) {
            open();
        }
        NtpV3Packet message = new NtpV3Impl();
        message.setMode(3);
        message.setVersion(this._version);
        DatagramPacket sendPacket = message.getDatagramPacket();
        sendPacket.setAddress(host);
        sendPacket.setPort(port);
        NtpV3Packet recMessage = new NtpV3Impl();
        DatagramPacket receivePacket = recMessage.getDatagramPacket();
        message.setTransmitTime(TimeStamp.getCurrentTime());
        this._socket_.send(sendPacket);
        this._socket_.receive(receivePacket);
        return new TimeInfo(recMessage, System.currentTimeMillis(), false);
    }

    public TimeInfo getTime(InetAddress host) throws IOException {
        return getTime(host, EACTags.SECURITY_ENVIRONMENT_TEMPLATE);
    }
}
