package org.apache.sanselan.common;

import java.io.IOException;
import java.io.InputStream;
import org.apache.sanselan.ImageReadException;

public class BinaryFileParser extends BinaryFileFunctions {
    private int byteOrder = 77;

    /* access modifiers changed from: protected */
    public void setByteOrder(int a, int b) throws ImageReadException, IOException {
        if (a != b) {
            throw new ImageReadException("Byte Order bytes don't match (" + a + ", " + b + ").");
        } else if (a == 77) {
            this.byteOrder = a;
        } else if (a == 73) {
            this.byteOrder = a;
        } else {
            throw new ImageReadException("Unknown Byte Order hint: " + a);
        }
    }

    /* access modifiers changed from: protected */
    public void setByteOrder(int byteOrder2) {
        this.byteOrder = byteOrder2;
    }

    /* access modifiers changed from: protected */
    public int getByteOrder() {
        return this.byteOrder;
    }

    /* access modifiers changed from: protected */
    public final int convertByteArrayToInt(String name, byte[] bytes) {
        return convertByteArrayToInt(name, bytes, this.byteOrder);
    }

    public final int read4Bytes(String name, InputStream is, String exception) throws ImageReadException, IOException {
        return read4Bytes(name, is, exception, this.byteOrder);
    }

    public final int read2Bytes(String name, InputStream is, String exception) throws ImageReadException, IOException {
        return read2Bytes(name, is, exception, this.byteOrder);
    }

    public static boolean byteArrayHasPrefix(byte[] bytes, byte[] prefix) {
        if (bytes == null || bytes.length < prefix.length) {
            return false;
        }
        for (int i = 0; i < prefix.length; i++) {
            if (bytes[i] != prefix[i]) {
                return false;
            }
        }
        return true;
    }
}
