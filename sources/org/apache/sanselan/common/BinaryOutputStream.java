package org.apache.sanselan.common;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.sanselan.ImageWriteException;

public class BinaryOutputStream extends OutputStream {
    private int byteOrder = 77;
    private int count = 0;
    protected boolean debug = false;

    /* renamed from: os */
    private final OutputStream f6328os;

    public BinaryOutputStream(OutputStream os, int byteOrder2) {
        this.byteOrder = byteOrder2;
        this.f6328os = os;
    }

    public void write(int i) throws IOException {
        this.f6328os.write(i);
        this.count++;
    }

    public final void write4Bytes(int value) throws ImageWriteException, IOException {
        writeNBytes(value, 4);
    }

    public final void write2Bytes(int value) throws ImageWriteException, IOException {
        writeNBytes(value, 2);
    }

    public final void writeByteArray(byte[] bytes) throws IOException {
        this.f6328os.write(bytes, 0, bytes.length);
        this.count += bytes.length;
    }

    private byte[] convertValueToByteArray(int value, int n) {
        byte[] result = new byte[n];
        if (this.byteOrder == 77) {
            for (int i = 0; i < n; i++) {
                result[i] = (byte) ((value >> (((n - i) - 1) * 8)) & 255);
            }
        } else {
            for (int i2 = 0; i2 < n; i2++) {
                result[i2] = (byte) ((value >> (i2 * 8)) & 255);
            }
        }
        return result;
    }

    private final void writeNBytes(int value, int n) throws ImageWriteException, IOException {
        write(convertValueToByteArray(value, n));
    }
}
