package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.p333io.Streams;

public abstract class ASN1BitString extends ASN1Primitive implements ASN1String {
    private static final char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    protected final byte[] data;
    protected final int padBits;

    /* access modifiers changed from: 0000 */
    public abstract void encode(ASN1OutputStream aSN1OutputStream) throws IOException;

    protected static int getPadBits(int bitString) {
        int val = 0;
        int i = 3;
        while (true) {
            if (i < 0) {
                break;
            }
            if (i != 0) {
                if ((bitString >> (i * 8)) != 0) {
                    val = (bitString >> (i * 8)) & 255;
                    break;
                }
            } else if (bitString != 0) {
                val = bitString & 255;
                break;
            }
            i--;
        }
        if (val == 0) {
            return 0;
        }
        int bits = 1;
        while (true) {
            val <<= 1;
            if ((val & 255) == 0) {
                return 8 - bits;
            }
            bits++;
        }
    }

    protected static byte[] getBytes(int bitString) {
        if (bitString == 0) {
            return new byte[0];
        }
        int bytes = 4;
        int i = 3;
        while (i >= 1 && ((255 << (i * 8)) & bitString) == 0) {
            bytes--;
            i--;
        }
        byte[] result = new byte[bytes];
        for (int i2 = 0; i2 < bytes; i2++) {
            result[i2] = (byte) ((bitString >> (i2 * 8)) & 255);
        }
        return result;
    }

    public ASN1BitString(byte[] data2, int padBits2) {
        if (data2 == null) {
            throw new NullPointerException("data cannot be null");
        } else if (data2.length == 0 && padBits2 != 0) {
            throw new IllegalArgumentException("zero length data with non-zero pad bits");
        } else if (padBits2 > 7 || padBits2 < 0) {
            throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
        } else {
            this.data = Arrays.clone(data2);
            this.padBits = padBits2;
        }
    }

    public String getString() {
        StringBuffer buf = new StringBuffer("#");
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        try {
            new ASN1OutputStream(bOut).writeObject(this);
            byte[] string = bOut.toByteArray();
            for (int i = 0; i != string.length; i++) {
                buf.append(table[(string[i] >>> 4) & 15]);
                buf.append(table[string[i] & 15]);
            }
            return buf.toString();
        } catch (IOException e) {
            throw new ASN1ParsingException("Internal error encoding BitString: " + e.getMessage(), e);
        }
    }

    public int intValue() {
        int value = 0;
        byte[] string = this.data;
        if (this.padBits > 0 && this.data.length <= 4) {
            string = derForm(this.data, this.padBits);
        }
        int i = 0;
        while (i != string.length && i != 4) {
            value |= (string[i] & 255) << (i * 8);
            i++;
        }
        return value;
    }

    public byte[] getOctets() {
        if (this.padBits == 0) {
            return Arrays.clone(this.data);
        }
        throw new IllegalStateException("attempt to get non-octet aligned data from BIT STRING");
    }

    public byte[] getBytes() {
        return derForm(this.data, this.padBits);
    }

    public int getPadBits() {
        return this.padBits;
    }

    public String toString() {
        return getString();
    }

    public int hashCode() {
        return this.padBits ^ Arrays.hashCode(getBytes());
    }

    /* access modifiers changed from: protected */
    public boolean asn1Equals(ASN1Primitive o) {
        if (!(o instanceof ASN1BitString)) {
            return false;
        }
        ASN1BitString other = (ASN1BitString) o;
        if (this.padBits != other.padBits || !Arrays.areEqual(getBytes(), other.getBytes())) {
            return false;
        }
        return true;
    }

    protected static byte[] derForm(byte[] data2, int padBits2) {
        byte[] rv = Arrays.clone(data2);
        if (padBits2 > 0) {
            int length = data2.length - 1;
            rv[length] = (byte) (rv[length] & (255 << padBits2));
        }
        return rv;
    }

    static ASN1BitString fromInputStream(int length, InputStream stream) throws IOException {
        if (length < 1) {
            throw new IllegalArgumentException("truncated BIT STRING detected");
        }
        int padBits2 = stream.read();
        byte[] data2 = new byte[(length - 1)];
        if (data2.length != 0) {
            if (Streams.readFully(stream, data2) != data2.length) {
                throw new EOFException("EOF encountered in middle of BIT STRING");
            } else if (padBits2 > 0 && padBits2 < 8 && data2[data2.length - 1] != ((byte) (data2[data2.length - 1] & (255 << padBits2)))) {
                return new DLBitString(data2, padBits2);
            }
        }
        return new DERBitString(data2, padBits2);
    }

    public ASN1Primitive getLoadedObject() {
        return toASN1Primitive();
    }

    /* access modifiers changed from: 0000 */
    public ASN1Primitive toDERObject() {
        return new DERBitString(this.data, this.padBits);
    }

    /* access modifiers changed from: 0000 */
    public ASN1Primitive toDLObject() {
        return new DLBitString(this.data, this.padBits);
    }
}
