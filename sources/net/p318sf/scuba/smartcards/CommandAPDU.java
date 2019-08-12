package net.p318sf.scuba.smartcards;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* renamed from: net.sf.scuba.smartcards.CommandAPDU */
public final class CommandAPDU implements Serializable {
    private static final int MAX_APDU_SIZE = 65544;
    private static final long serialVersionUID = 398698301286670877L;
    private byte[] apdu;
    private transient int dataOffset;

    /* renamed from: nc */
    private transient int f6305nc;

    /* renamed from: ne */
    private transient int f6306ne;

    public CommandAPDU(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, null, 0, 0, 0);
    }

    public CommandAPDU(int i, int i2, int i3, int i4, int i5) {
        this(i, i2, i3, i4, null, 0, 0, i5);
    }

    public CommandAPDU(int i, int i2, int i3, int i4, byte[] bArr) {
        this(i, i2, i3, i4, bArr, 0, arrayLength(bArr), 0);
    }

    public CommandAPDU(int i, int i2, int i3, int i4, byte[] bArr, int i5) {
        this(i, i2, i3, i4, bArr, 0, arrayLength(bArr), i5);
    }

    public CommandAPDU(int i, int i2, int i3, int i4, byte[] bArr, int i5, int i6) {
        this(i, i2, i3, i4, bArr, i5, i6, 0);
    }

    public CommandAPDU(int i, int i2, int i3, int i4, byte[] bArr, int i5, int i6, int i7) {
        byte b;
        byte b2 = 0;
        checkArrayBounds(bArr, i5, i6);
        if (i6 > 65535) {
            throw new IllegalArgumentException("dataLength is too large");
        } else if (i7 < 0) {
            throw new IllegalArgumentException("ne must not be negative");
        } else if (i7 > 65536) {
            throw new IllegalArgumentException("ne is too large");
        } else {
            this.f6306ne = i7;
            this.f6305nc = i6;
            if (i6 == 0) {
                if (i7 == 0) {
                    this.apdu = new byte[4];
                    setHeader(i, i2, i3, i4);
                } else if (i7 <= 256) {
                    if (i7 != 256) {
                        b2 = (byte) i7;
                    }
                    this.apdu = new byte[5];
                    setHeader(i, i2, i3, i4);
                    this.apdu[4] = b2;
                } else {
                    if (i7 == 65536) {
                        b = 0;
                    } else {
                        b = (byte) (i7 >> 8);
                        b2 = (byte) i7;
                    }
                    this.apdu = new byte[7];
                    setHeader(i, i2, i3, i4);
                    this.apdu[5] = b;
                    this.apdu[6] = b2;
                }
            } else if (i7 == 0) {
                if (i6 <= 255) {
                    this.apdu = new byte[(i6 + 5)];
                    setHeader(i, i2, i3, i4);
                    this.apdu[4] = (byte) i6;
                    this.dataOffset = 5;
                    System.arraycopy(bArr, i5, this.apdu, 5, i6);
                    return;
                }
                this.apdu = new byte[(i6 + 7)];
                setHeader(i, i2, i3, i4);
                this.apdu[4] = 0;
                this.apdu[5] = (byte) (i6 >> 8);
                this.apdu[6] = (byte) i6;
                this.dataOffset = 7;
                System.arraycopy(bArr, i5, this.apdu, 7, i6);
            } else if (i6 > 255 || i7 > 256) {
                this.apdu = new byte[(i6 + 9)];
                setHeader(i, i2, i3, i4);
                this.apdu[4] = 0;
                this.apdu[5] = (byte) (i6 >> 8);
                this.apdu[6] = (byte) i6;
                this.dataOffset = 7;
                System.arraycopy(bArr, i5, this.apdu, 7, i6);
                if (i7 != 65536) {
                    int length = this.apdu.length - 2;
                    this.apdu[length] = (byte) (i7 >> 8);
                    this.apdu[length + 1] = (byte) i7;
                }
            } else {
                this.apdu = new byte[(i6 + 6)];
                setHeader(i, i2, i3, i4);
                this.apdu[4] = (byte) i6;
                this.dataOffset = 5;
                System.arraycopy(bArr, i5, this.apdu, 5, i6);
                byte[] bArr2 = this.apdu;
                int length2 = this.apdu.length - 1;
                if (i7 != 256) {
                    b2 = (byte) i7;
                }
                bArr2[length2] = b2;
            }
        }
    }

    public CommandAPDU(ByteBuffer byteBuffer) {
        this.apdu = new byte[byteBuffer.remaining()];
        byteBuffer.get(this.apdu);
        parse();
    }

    public CommandAPDU(byte[] bArr) {
        this.apdu = (byte[]) bArr.clone();
        parse();
    }

    public CommandAPDU(byte[] bArr, int i, int i2) {
        checkArrayBounds(bArr, i, i2);
        this.apdu = new byte[i2];
        System.arraycopy(bArr, i, this.apdu, 0, i2);
        parse();
    }

    private static int arrayLength(byte[] bArr) {
        if (bArr != null) {
            return bArr.length;
        }
        return 0;
    }

    private void checkArrayBounds(byte[] bArr, int i, int i2) {
        if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("Offset and length must not be negative");
        } else if (bArr == null) {
            if (i != 0 && i2 != 0) {
                throw new IllegalArgumentException("offset and length must be 0 if array is null");
            }
        } else if (i > bArr.length - i2) {
            throw new IllegalArgumentException("Offset plus length exceed array size");
        }
    }

    private void parse() {
        byte b = 65536;
        byte b2 = 256;
        if (this.apdu.length < 4) {
            throw new IllegalArgumentException("apdu must be at least 4 bytes long");
        } else if (this.apdu.length != 4) {
            byte b3 = this.apdu[4] & 255;
            if (this.apdu.length == 5) {
                if (b3 != 0) {
                    b2 = b3;
                }
                this.f6306ne = b2;
            } else if (b3 != 0) {
                if (this.apdu.length == b3 + 5) {
                    this.f6305nc = b3;
                    this.dataOffset = 5;
                } else if (this.apdu.length == b3 + 6) {
                    this.f6305nc = b3;
                    this.dataOffset = 5;
                    byte b4 = this.apdu[this.apdu.length - 1] & 255;
                    if (b4 != 0) {
                        b2 = b4;
                    }
                    this.f6306ne = b2;
                } else {
                    throw new IllegalArgumentException("Invalid APDU: length=" + this.apdu.length + ", b1=" + b3);
                }
            } else if (this.apdu.length < 7) {
                throw new IllegalArgumentException("Invalid APDU: length=" + this.apdu.length + ", b1=" + b3);
            } else {
                byte b5 = ((this.apdu[5] & 255) << 8) | (this.apdu[6] & 255);
                if (this.apdu.length == 7) {
                    if (b5 == 0) {
                        b5 = 65536;
                    }
                    this.f6306ne = b5;
                } else if (b5 == 0) {
                    throw new IllegalArgumentException("Invalid APDU: length=" + this.apdu.length + ", b1=" + b3 + ", b2||b3=" + b5);
                } else if (this.apdu.length == b5 + 7) {
                    this.f6305nc = b5;
                    this.dataOffset = 7;
                } else if (this.apdu.length == b5 + 9) {
                    this.f6305nc = b5;
                    this.dataOffset = 7;
                    int length = this.apdu.length - 2;
                    byte b6 = (this.apdu[length + 1] & 255) | ((this.apdu[length] & 255) << 8);
                    if (b6 != 0) {
                        b = b6;
                    }
                    this.f6306ne = b;
                } else {
                    throw new IllegalArgumentException("Invalid APDU: length=" + this.apdu.length + ", b1=" + b3 + ", b2||b3=" + b5);
                }
            }
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.apdu = (byte[]) objectInputStream.readUnshared();
        parse();
    }

    private void setHeader(int i, int i2, int i3, int i4) {
        this.apdu[0] = (byte) i;
        this.apdu[1] = (byte) i2;
        this.apdu[2] = (byte) i3;
        this.apdu[3] = (byte) i4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommandAPDU)) {
            return false;
        }
        return Arrays.equals(this.apdu, ((CommandAPDU) obj).apdu);
    }

    public byte[] getBytes() {
        return (byte[]) this.apdu.clone();
    }

    public int getCLA() {
        return this.apdu[0] & 255;
    }

    public byte[] getData() {
        byte[] bArr = new byte[this.f6305nc];
        System.arraycopy(this.apdu, this.dataOffset, bArr, 0, this.f6305nc);
        return bArr;
    }

    public int getINS() {
        return this.apdu[1] & 255;
    }

    public int getNc() {
        return this.f6305nc;
    }

    public int getNe() {
        return this.f6306ne;
    }

    public int getP1() {
        return this.apdu[2] & 255;
    }

    public int getP2() {
        return this.apdu[3] & 255;
    }

    public int hashCode() {
        return Arrays.hashCode(this.apdu);
    }

    public String toString() {
        return "CommmandAPDU: " + this.apdu.length + " bytes, nc=" + this.f6305nc + ", ne=" + this.f6306ne;
    }
}
