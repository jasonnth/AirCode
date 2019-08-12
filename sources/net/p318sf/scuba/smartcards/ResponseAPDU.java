package net.p318sf.scuba.smartcards;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;

/* renamed from: net.sf.scuba.smartcards.ResponseAPDU */
public final class ResponseAPDU implements Serializable {
    private static final long serialVersionUID = 6962744978375594225L;
    private byte[] apdu;

    public ResponseAPDU(byte[] bArr) {
        byte[] bArr2 = (byte[]) bArr.clone();
        check(bArr2);
        this.apdu = bArr2;
    }

    private static void check(byte[] bArr) {
        if (bArr.length < 2) {
            throw new IllegalArgumentException("apdu must be at least 2 bytes long");
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.apdu = (byte[]) objectInputStream.readUnshared();
        check(this.apdu);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResponseAPDU)) {
            return false;
        }
        return Arrays.equals(this.apdu, ((ResponseAPDU) obj).apdu);
    }

    public byte[] getBytes() {
        return (byte[]) this.apdu.clone();
    }

    public byte[] getData() {
        byte[] bArr = new byte[(this.apdu.length - 2)];
        System.arraycopy(this.apdu, 0, bArr, 0, bArr.length);
        return bArr;
    }

    public int getNr() {
        return this.apdu.length - 2;
    }

    public int getSW() {
        return (getSW1() << 8) | getSW2();
    }

    public int getSW1() {
        return this.apdu[this.apdu.length - 2] & 255;
    }

    public int getSW2() {
        return this.apdu[this.apdu.length - 1] & 255;
    }

    public int hashCode() {
        return Arrays.hashCode(this.apdu);
    }

    public String toString() {
        return "ResponseAPDU: " + this.apdu.length + " bytes, SW=" + Integer.toHexString(getSW());
    }
}
