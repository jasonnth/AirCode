package org.apache.commons.net.ntp;

import java.net.DatagramPacket;
import java.util.Arrays;
import org.spongycastle.asn1.eac.EACTags;

public class NtpV3Impl implements NtpV3Packet {
    private final byte[] buf = new byte[48];

    /* renamed from: dp */
    private volatile DatagramPacket f6326dp;

    public int getMode() {
        return (m3951ui(this.buf[0]) >> 0) & 7;
    }

    public void setMode(int mode) {
        this.buf[0] = (byte) ((this.buf[0] & 248) | (mode & 7));
    }

    public int getPoll() {
        return this.buf[2];
    }

    public int getPrecision() {
        return this.buf[3];
    }

    public int getVersion() {
        return (m3951ui(this.buf[0]) >> 3) & 7;
    }

    public void setVersion(int version) {
        this.buf[0] = (byte) ((this.buf[0] & 199) | ((version & 7) << 3));
    }

    public int getStratum() {
        return m3951ui(this.buf[1]);
    }

    public int getRootDelay() {
        return getInt(4);
    }

    public int getRootDispersion() {
        return getInt(8);
    }

    public double getRootDispersionInMillisDouble() {
        return ((double) getRootDispersion()) / 65.536d;
    }

    public int getReferenceId() {
        return getInt(12);
    }

    public String getReferenceIdString() {
        int version = getVersion();
        int stratum = getStratum();
        if (version == 3 || version == 4) {
            if (stratum == 0 || stratum == 1) {
                return idAsString();
            }
            if (version == 4) {
                return idAsHex();
            }
        }
        if (stratum >= 2) {
            return idAsIPAddress();
        }
        return idAsHex();
    }

    private String idAsIPAddress() {
        return m3951ui(this.buf[12]) + "." + m3951ui(this.buf[13]) + "." + m3951ui(this.buf[14]) + "." + m3951ui(this.buf[15]);
    }

    private String idAsString() {
        StringBuilder id = new StringBuilder();
        for (int i = 0; i <= 3; i++) {
            char c = (char) this.buf[i + 12];
            if (c == 0) {
                break;
            }
            id.append(c);
        }
        return id.toString();
    }

    private String idAsHex() {
        return Integer.toHexString(getReferenceId());
    }

    public TimeStamp getTransmitTimeStamp() {
        return getTimestamp(40);
    }

    public void setTransmitTime(TimeStamp ts) {
        setTimestamp(40, ts);
    }

    public TimeStamp getOriginateTimeStamp() {
        return getTimestamp(24);
    }

    public TimeStamp getReceiveTimeStamp() {
        return getTimestamp(32);
    }

    private int getInt(int index) {
        return (m3951ui(this.buf[index]) << 24) | (m3951ui(this.buf[index + 1]) << 16) | (m3951ui(this.buf[index + 2]) << 8) | m3951ui(this.buf[index + 3]);
    }

    private TimeStamp getTimestamp(int index) {
        return new TimeStamp(getLong(index));
    }

    private long getLong(int index) {
        return (m3952ul(this.buf[index]) << 56) | (m3952ul(this.buf[index + 1]) << 48) | (m3952ul(this.buf[index + 2]) << 40) | (m3952ul(this.buf[index + 3]) << 32) | (m3952ul(this.buf[index + 4]) << 24) | (m3952ul(this.buf[index + 5]) << 16) | (m3952ul(this.buf[index + 6]) << 8) | m3952ul(this.buf[index + 7]);
    }

    private void setTimestamp(int index, TimeStamp t) {
        long ntpTime = t == null ? 0 : t.ntpValue();
        for (int i = 7; i >= 0; i--) {
            this.buf[index + i] = (byte) ((int) (255 & ntpTime));
            ntpTime >>>= 8;
        }
    }

    public synchronized DatagramPacket getDatagramPacket() {
        if (this.f6326dp == null) {
            this.f6326dp = new DatagramPacket(this.buf, this.buf.length);
            this.f6326dp.setPort(EACTags.SECURITY_ENVIRONMENT_TEMPLATE);
        }
        return this.f6326dp;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.buf, ((NtpV3Impl) obj).buf);
    }

    public int hashCode() {
        return Arrays.hashCode(this.buf);
    }

    /* renamed from: ui */
    protected static final int m3951ui(byte b) {
        return b & 255;
    }

    /* renamed from: ul */
    protected static final long m3952ul(byte b) {
        return (long) (b & 255);
    }

    public String toString() {
        return "[version:" + getVersion() + ", mode:" + getMode() + ", poll:" + getPoll() + ", precision:" + getPrecision() + ", delay:" + getRootDelay() + ", dispersion(ms):" + getRootDispersionInMillisDouble() + ", id:" + getReferenceIdString() + ", xmitTime:" + getTransmitTimeStamp().toDateString() + " ]";
    }
}
