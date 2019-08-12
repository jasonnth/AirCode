package org.apache.sanselan.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;

public class BinaryFileFunctions {
    protected boolean debug = false;

    public final void debugNumber(String msg, int data) {
        debugNumber(msg, data, 1);
    }

    public final void debugNumber(String msg, int data, int bytes) {
        PrintWriter pw = new PrintWriter(System.out);
        debugNumber(pw, msg, data, bytes);
        pw.flush();
    }

    public final void debugNumber(PrintWriter pw, String msg, int data, int bytes) {
        pw.print(msg + ": " + data + " (");
        int byteData = data;
        for (int i = 0; i < bytes; i++) {
            if (i > 0) {
                pw.print(",");
            }
            int singleByte = byteData & 255;
            pw.print(((char) singleByte) + " [" + singleByte + "]");
            byteData >>= 8;
        }
        pw.println(") [0x" + Integer.toHexString(data) + ", " + Integer.toBinaryString(data) + "]");
        pw.flush();
    }

    public final void readAndVerifyBytes(InputStream is, byte[] expected, String exception) throws ImageReadException, IOException {
        int i = 0;
        while (i < expected.length) {
            int data = is.read();
            byte b = (byte) (data & 255);
            if (data < 0) {
                throw new ImageReadException("Unexpected EOF.");
            } else if (b != expected[i]) {
                throw new ImageReadException(exception);
            } else {
                i++;
            }
        }
    }

    public final void skipBytes(InputStream is, int length, String exception) throws IOException {
        long total = 0;
        while (((long) length) != total) {
            long skipped = is.skip(((long) length) - total);
            if (skipped < 1) {
                throw new IOException(exception + " (" + skipped + ")");
            }
            total += skipped;
        }
    }

    public final byte readByte(String name, InputStream is, String exception) throws ImageReadException, IOException {
        int result = is.read();
        if (result < 0) {
            System.out.println(name + ": " + result);
            throw new IOException(exception);
        }
        if (this.debug) {
            debugNumber(name, result);
        }
        return (byte) (result & 255);
    }

    /* access modifiers changed from: protected */
    public final RationalNumber[] convertByteArrayToRationalArray(String name, byte[] bytes, int start, int length, int byteOrder) {
        int expectedLength = start + (length * 8);
        if (bytes.length < expectedLength) {
            System.out.println(name + ": expected length: " + expectedLength + ", actual length: " + bytes.length);
            return null;
        }
        RationalNumber[] result = new RationalNumber[length];
        for (int i = 0; i < length; i++) {
            result[i] = convertByteArrayToRational(name, bytes, (i * 8) + start, byteOrder);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public final RationalNumber convertByteArrayToRational(String name, byte[] bytes, int byteOrder) {
        return convertByteArrayToRational(name, bytes, 0, byteOrder);
    }

    /* access modifiers changed from: protected */
    public final RationalNumber convertByteArrayToRational(String name, byte[] bytes, int start, int byteOrder) {
        return new RationalNumber(convertByteArrayToInt(name, bytes, start + 0, byteOrder), convertByteArrayToInt(name, bytes, start + 4, byteOrder));
    }

    /* access modifiers changed from: protected */
    public final int convertByteArrayToInt(String name, byte[] bytes, int byteOrder) {
        return convertByteArrayToInt(name, bytes, 0, byteOrder);
    }

    /* access modifiers changed from: protected */
    public final int convertByteArrayToInt(String name, byte[] bytes, int start, int byteOrder) {
        int result;
        byte byte0 = bytes[start + 0];
        byte byte1 = bytes[start + 1];
        byte byte2 = bytes[start + 2];
        byte byte3 = bytes[start + 3];
        if (byteOrder == 77) {
            result = ((byte0 & 255) << 24) | ((byte1 & 255) << 16) | ((byte2 & 255) << 8) | ((byte3 & 255) << 0);
        } else {
            result = ((byte3 & 255) << 24) | ((byte2 & 255) << 16) | ((byte1 & 255) << 8) | ((byte0 & 255) << 0);
        }
        if (this.debug) {
            debugNumber(name, result, 4);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public final int[] convertByteArrayToIntArray(String name, byte[] bytes, int start, int length, int byteOrder) {
        int expectedLength = start + (length * 4);
        if (bytes.length < expectedLength) {
            System.out.println(name + ": expected length: " + expectedLength + ", actual length: " + bytes.length);
            return null;
        }
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = convertByteArrayToInt(name, bytes, (i * 4) + start, byteOrder);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public final void writeIntInToByteArray(int value, byte[] bytes, int start, int byteOrder) {
        if (byteOrder == 77) {
            bytes[start + 0] = (byte) (value >> 24);
            bytes[start + 1] = (byte) (value >> 16);
            bytes[start + 2] = (byte) (value >> 8);
            bytes[start + 3] = (byte) (value >> 0);
            return;
        }
        bytes[start + 3] = (byte) (value >> 24);
        bytes[start + 2] = (byte) (value >> 16);
        bytes[start + 1] = (byte) (value >> 8);
        bytes[start + 0] = (byte) (value >> 0);
    }

    /* access modifiers changed from: protected */
    public final byte[] convertIntArrayToByteArray(int[] values, int byteOrder) {
        byte[] result = new byte[(values.length * 4)];
        for (int i = 0; i < values.length; i++) {
            writeIntInToByteArray(values[i], result, i * 4, byteOrder);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertShortArrayToByteArray(int[] values, int byteOrder) {
        byte[] result = new byte[(values.length * 2)];
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            if (byteOrder == 77) {
                result[(i * 2) + 0] = (byte) (value >> 8);
                result[(i * 2) + 1] = (byte) (value >> 0);
            } else {
                result[(i * 2) + 1] = (byte) (value >> 8);
                result[(i * 2) + 0] = (byte) (value >> 0);
            }
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertShortToByteArray(int value, int byteOrder) {
        byte[] result = new byte[2];
        if (byteOrder == 77) {
            result[0] = (byte) (value >> 8);
            result[1] = (byte) (value >> 0);
        } else {
            result[1] = (byte) (value >> 8);
            result[0] = (byte) (value >> 0);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertRationalArrayToByteArray(RationalNumber[] numbers, int byteOrder) throws ImageWriteException {
        byte[] result = new byte[(numbers.length * 8)];
        for (int i = 0; i < numbers.length; i++) {
            writeIntInToByteArray(numbers[i].numerator, result, i * 8, byteOrder);
            writeIntInToByteArray(numbers[i].divisor, result, (i * 8) + 4, byteOrder);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertRationalToByteArray(RationalNumber number, int byteOrder) throws ImageWriteException {
        byte[] result = new byte[8];
        writeIntInToByteArray(number.numerator, result, 0, byteOrder);
        writeIntInToByteArray(number.divisor, result, 4, byteOrder);
        return result;
    }

    /* access modifiers changed from: protected */
    public final int convertByteArrayToShort(String name, byte[] bytes, int byteOrder) throws ImageReadException {
        return convertByteArrayToShort(name, 0, bytes, byteOrder);
    }

    /* access modifiers changed from: protected */
    public final int convertByteArrayToShort(String name, int index, byte[] bytes, int byteOrder) throws ImageReadException {
        int result;
        if (index + 1 >= bytes.length) {
            throw new ImageReadException("Index out of bounds. Array size: " + bytes.length + ", index: " + index);
        }
        int byte0 = bytes[index + 0] & 255;
        int byte1 = bytes[index + 1] & 255;
        if (byteOrder == 77) {
            result = (byte0 << 8) | byte1;
        } else {
            result = (byte1 << 8) | byte0;
        }
        if (this.debug) {
            debugNumber(name, result, 2);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public final int[] convertByteArrayToShortArray(String name, byte[] bytes, int start, int length, int byteOrder) throws ImageReadException {
        int expectedLength = start + (length * 2);
        if (bytes.length < expectedLength) {
            System.out.println(name + ": expected length: " + expectedLength + ", actual length: " + bytes.length);
            return null;
        }
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = convertByteArrayToShort(name, (i * 2) + start, bytes, byteOrder);
        }
        return result;
    }

    public final byte[] readByteArray(String name, int length, InputStream is, String exception) throws IOException {
        byte[] result = new byte[length];
        int read = 0;
        while (read < length) {
            int count = is.read(result, read, length - read);
            if (count < 1) {
                throw new IOException(exception);
            }
            read += count;
        }
        if (this.debug) {
            int i = 0;
            while (i < length && i < 50) {
                debugNumber(name + " (" + i + ")", result[i] & 255);
                i++;
            }
        }
        return result;
    }

    public final void debugByteArray(String name, byte[] bytes) {
        System.out.println(name + ": " + bytes.length);
        int i = 0;
        while (i < bytes.length && i < 50) {
            debugNumber("\t (" + i + ")", bytes[i] & 255);
            i++;
        }
    }

    public final byte[] readBytearray(String name, byte[] bytes, int start, int count) throws ImageReadException {
        if (bytes.length < start + count) {
            throw new ImageReadException("Invalid read. bytes.length: " + bytes.length + ", start: " + start + ", count: " + count);
        }
        byte[] result = new byte[count];
        System.arraycopy(bytes, start, result, 0, count);
        if (this.debug) {
            debugByteArray(name, result);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public final byte[] getByteArrayTail(String name, byte[] bytes, int count) throws ImageReadException {
        return readBytearray(name, bytes, count, bytes.length - count);
    }

    public static final boolean compareBytes(byte[] a, int aStart, byte[] b, int bStart, int length) {
        if (a.length < aStart + length || b.length < bStart + length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (a[aStart + i] != b[bStart + i]) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final int read4Bytes(String name, InputStream is, String exception, int byteOrder) throws ImageReadException, IOException {
        byte[] bytes = new byte[4];
        int read = 0;
        while (read < 4) {
            int count = is.read(bytes, read, 4 - read);
            if (count < 1) {
                throw new IOException(exception);
            }
            read += count;
        }
        return convertByteArrayToInt(name, bytes, byteOrder);
    }

    /* access modifiers changed from: protected */
    public final int read2Bytes(String name, InputStream is, String exception, int byteOrder) throws ImageReadException, IOException {
        byte[] bytes = new byte[2];
        int read = 0;
        while (read < 2) {
            int count = is.read(bytes, read, 2 - read);
            if (count < 1) {
                throw new IOException(exception);
            }
            read += count;
        }
        return convertByteArrayToShort(name, bytes, byteOrder);
    }

    /* access modifiers changed from: protected */
    public final float convertByteArrayToFloat(String name, byte[] bytes, int byteOrder) {
        return convertByteArrayToFloat(name, bytes, 0, byteOrder);
    }

    /* access modifiers changed from: protected */
    public final float convertByteArrayToFloat(String name, byte[] bytes, int start, int byteOrder) {
        int bits;
        byte byte0 = bytes[start + 0];
        byte byte1 = bytes[start + 1];
        byte byte2 = bytes[start + 2];
        byte byte3 = bytes[start + 3];
        if (byteOrder == 77) {
            bits = ((byte0 & 255) << 24) | ((byte1 & 255) << 16) | ((byte2 & 255) << 8) | ((byte3 & 255) << 0);
        } else {
            bits = ((byte3 & 255) << 24) | ((byte2 & 255) << 16) | ((byte1 & 255) << 8) | ((byte0 & 255) << 0);
        }
        return Float.intBitsToFloat(bits);
    }

    /* access modifiers changed from: protected */
    public final float[] convertByteArrayToFloatArray(String name, byte[] bytes, int start, int length, int byteOrder) {
        int expectedLength = start + (length * 4);
        if (bytes.length < expectedLength) {
            System.out.println(name + ": expected length: " + expectedLength + ", actual length: " + bytes.length);
            return null;
        }
        float[] result = new float[length];
        for (int i = 0; i < length; i++) {
            result[i] = convertByteArrayToFloat(name, bytes, (i * 4) + start, byteOrder);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertFloatToByteArray(float value, int byteOrder) {
        byte[] result = new byte[4];
        int bits = Float.floatToRawIntBits(value);
        if (byteOrder == 77) {
            result[0] = (byte) ((bits >> 0) & 255);
            result[1] = (byte) ((bits >> 8) & 255);
            result[2] = (byte) ((bits >> 16) & 255);
            result[3] = (byte) ((bits >> 24) & 255);
        } else {
            result[3] = (byte) ((bits >> 0) & 255);
            result[2] = (byte) ((bits >> 8) & 255);
            result[1] = (byte) ((bits >> 16) & 255);
            result[0] = (byte) ((bits >> 24) & 255);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertFloatArrayToByteArray(float[] values, int byteOrder) {
        byte[] result = new byte[(values.length * 4)];
        for (int i = 0; i < values.length; i++) {
            int bits = Float.floatToRawIntBits(values[i]);
            int start = i * 4;
            if (byteOrder == 77) {
                result[start + 0] = (byte) ((bits >> 0) & 255);
                result[start + 1] = (byte) ((bits >> 8) & 255);
                result[start + 2] = (byte) ((bits >> 16) & 255);
                result[start + 3] = (byte) ((bits >> 24) & 255);
            } else {
                result[start + 3] = (byte) ((bits >> 0) & 255);
                result[start + 2] = (byte) ((bits >> 8) & 255);
                result[start + 1] = (byte) ((bits >> 16) & 255);
                result[start + 0] = (byte) ((bits >> 24) & 255);
            }
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertDoubleToByteArray(double value, int byteOrder) {
        byte[] result = new byte[8];
        long bits = Double.doubleToRawLongBits(value);
        if (byteOrder == 77) {
            result[0] = (byte) ((int) ((bits >> 0) & 255));
            result[1] = (byte) ((int) ((bits >> 8) & 255));
            result[2] = (byte) ((int) ((bits >> 16) & 255));
            result[3] = (byte) ((int) ((bits >> 24) & 255));
            result[4] = (byte) ((int) ((bits >> 32) & 255));
            result[5] = (byte) ((int) ((bits >> 40) & 255));
            result[6] = (byte) ((int) ((bits >> 48) & 255));
            result[7] = (byte) ((int) ((bits >> 56) & 255));
        } else {
            result[7] = (byte) ((int) ((bits >> 0) & 255));
            result[6] = (byte) ((int) ((bits >> 8) & 255));
            result[5] = (byte) ((int) ((bits >> 16) & 255));
            result[4] = (byte) ((int) ((bits >> 24) & 255));
            result[3] = (byte) ((int) ((bits >> 32) & 255));
            result[2] = (byte) ((int) ((bits >> 40) & 255));
            result[1] = (byte) ((int) ((bits >> 48) & 255));
            result[0] = (byte) ((int) ((bits >> 56) & 255));
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertDoubleArrayToByteArray(double[] values, int byteOrder) {
        byte[] result = new byte[(values.length * 8)];
        for (int i = 0; i < values.length; i++) {
            long bits = Double.doubleToRawLongBits(values[i]);
            int start = i * 8;
            if (byteOrder == 77) {
                result[start + 0] = (byte) ((int) (255 & (bits >> 0)));
                result[start + 1] = (byte) ((int) (255 & (bits >> 8)));
                result[start + 2] = (byte) ((int) (255 & (bits >> 16)));
                result[start + 3] = (byte) ((int) (255 & (bits >> 24)));
                result[start + 4] = (byte) ((int) (255 & (bits >> 32)));
                result[start + 5] = (byte) ((int) (255 & (bits >> 40)));
                result[start + 6] = (byte) ((int) (255 & (bits >> 48)));
                result[start + 7] = (byte) ((int) (255 & (bits >> 56)));
            } else {
                result[start + 7] = (byte) ((int) (255 & (bits >> 0)));
                result[start + 6] = (byte) ((int) (255 & (bits >> 8)));
                result[start + 5] = (byte) ((int) (255 & (bits >> 16)));
                result[start + 4] = (byte) ((int) (255 & (bits >> 24)));
                result[start + 3] = (byte) ((int) (255 & (bits >> 32)));
                result[start + 2] = (byte) ((int) (255 & (bits >> 40)));
                result[start + 1] = (byte) ((int) (255 & (bits >> 48)));
                result[start + 0] = (byte) ((int) (255 & (bits >> 56)));
            }
        }
        return result;
    }
}
