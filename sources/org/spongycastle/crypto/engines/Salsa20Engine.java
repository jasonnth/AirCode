package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.MaxBytesExceededException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.SkippingStreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Pack;
import org.spongycastle.util.Strings;

public class Salsa20Engine implements SkippingStreamCipher {
    public static final int DEFAULT_ROUNDS = 20;
    private static final int STATE_SIZE = 16;
    protected static final byte[] sigma = Strings.toByteArray("expand 32-byte k");
    protected static final byte[] tau = Strings.toByteArray("expand 16-byte k");
    private int cW0;
    private int cW1;
    private int cW2;
    protected int[] engineState;
    private int index;
    private boolean initialised;
    private byte[] keyStream;
    protected int rounds;

    /* renamed from: x */
    protected int[] f6704x;

    public Salsa20Engine() {
        this(20);
    }

    public Salsa20Engine(int rounds2) {
        this.index = 0;
        this.engineState = new int[16];
        this.f6704x = new int[16];
        this.keyStream = new byte[64];
        this.initialised = false;
        if (rounds2 <= 0 || (rounds2 & 1) != 0) {
            throw new IllegalArgumentException("'rounds' must be a positive, even number");
        }
        this.rounds = rounds2;
    }

    public void init(boolean forEncryption, CipherParameters params) {
        if (!(params instanceof ParametersWithIV)) {
            throw new IllegalArgumentException(getAlgorithmName() + " Init parameters must include an IV");
        }
        ParametersWithIV ivParams = (ParametersWithIV) params;
        byte[] iv = ivParams.getIV();
        if (iv == null || iv.length != getNonceSize()) {
            throw new IllegalArgumentException(getAlgorithmName() + " requires exactly " + getNonceSize() + " bytes of IV");
        }
        CipherParameters keyParam = ivParams.getParameters();
        if (keyParam == null) {
            if (!this.initialised) {
                throw new IllegalStateException(getAlgorithmName() + " KeyParameter can not be null for first initialisation");
            }
            setKey(null, iv);
        } else if (keyParam instanceof KeyParameter) {
            setKey(((KeyParameter) keyParam).getKey(), iv);
        } else {
            throw new IllegalArgumentException(getAlgorithmName() + " Init parameters must contain a KeyParameter (or null for re-init)");
        }
        reset();
        this.initialised = true;
    }

    /* access modifiers changed from: protected */
    public int getNonceSize() {
        return 8;
    }

    public String getAlgorithmName() {
        String name = "Salsa20";
        if (this.rounds != 20) {
            return name + "/" + this.rounds;
        }
        return name;
    }

    public byte returnByte(byte in) {
        if (limitExceeded()) {
            throw new MaxBytesExceededException("2^70 byte limit per IV; Change IV");
        }
        byte out = (byte) (this.keyStream[this.index] ^ in);
        this.index = (this.index + 1) & 63;
        if (this.index == 0) {
            advanceCounter();
            generateKeyStream(this.keyStream);
        }
        return out;
    }

    /* access modifiers changed from: protected */
    public void advanceCounter(long diff) {
        int hi = (int) (diff >>> 32);
        int lo = (int) diff;
        if (hi > 0) {
            int[] iArr = this.engineState;
            iArr[9] = iArr[9] + hi;
        }
        int oldState = this.engineState[8];
        int[] iArr2 = this.engineState;
        iArr2[8] = iArr2[8] + lo;
        if (oldState != 0 && this.engineState[8] < oldState) {
            int[] iArr3 = this.engineState;
            iArr3[9] = iArr3[9] + 1;
        }
    }

    /* access modifiers changed from: protected */
    public void advanceCounter() {
        int[] iArr = this.engineState;
        int i = iArr[8] + 1;
        iArr[8] = i;
        if (i == 0) {
            int[] iArr2 = this.engineState;
            iArr2[9] = iArr2[9] + 1;
        }
    }

    /* access modifiers changed from: protected */
    public void retreatCounter(long diff) {
        int hi = (int) (diff >>> 32);
        int lo = (int) diff;
        if (hi != 0) {
            if ((((long) this.engineState[9]) & 4294967295L) >= (((long) hi) & 4294967295L)) {
                int[] iArr = this.engineState;
                iArr[9] = iArr[9] - hi;
            } else {
                throw new IllegalStateException("attempt to reduce counter past zero.");
            }
        }
        if ((((long) this.engineState[8]) & 4294967295L) >= (((long) lo) & 4294967295L)) {
            int[] iArr2 = this.engineState;
            iArr2[8] = iArr2[8] - lo;
        } else if (this.engineState[9] != 0) {
            int[] iArr3 = this.engineState;
            iArr3[9] = iArr3[9] - 1;
            int[] iArr4 = this.engineState;
            iArr4[8] = iArr4[8] - lo;
        } else {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
    }

    /* access modifiers changed from: protected */
    public void retreatCounter() {
        if (this.engineState[8] == 0 && this.engineState[9] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        int[] iArr = this.engineState;
        int i = iArr[8] - 1;
        iArr[8] = i;
        if (i == -1) {
            int[] iArr2 = this.engineState;
            iArr2[9] = iArr2[9] - 1;
        }
    }

    public int processBytes(byte[] in, int inOff, int len, byte[] out, int outOff) {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (inOff + len > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (outOff + len > out.length) {
            throw new OutputLengthException("output buffer too short");
        } else if (limitExceeded(len)) {
            throw new MaxBytesExceededException("2^70 byte limit per IV would be exceeded; Change IV");
        } else {
            for (int i = 0; i < len; i++) {
                out[i + outOff] = (byte) (this.keyStream[this.index] ^ in[i + inOff]);
                this.index = (this.index + 1) & 63;
                if (this.index == 0) {
                    advanceCounter();
                    generateKeyStream(this.keyStream);
                }
            }
            return len;
        }
    }

    public long skip(long numberOfBytes) {
        if (numberOfBytes >= 0) {
            long remaining = numberOfBytes;
            if (remaining >= 64) {
                long count = remaining / 64;
                advanceCounter(count);
                remaining -= count * 64;
            }
            int oldIndex = this.index;
            this.index = (this.index + ((int) remaining)) & 63;
            if (this.index < oldIndex) {
                advanceCounter();
            }
        } else {
            long remaining2 = -numberOfBytes;
            if (remaining2 >= 64) {
                long count2 = remaining2 / 64;
                retreatCounter(count2);
                remaining2 -= count2 * 64;
            }
            for (long i = 0; i < remaining2; i++) {
                if (this.index == 0) {
                    retreatCounter();
                }
                this.index = (this.index - 1) & 63;
            }
        }
        generateKeyStream(this.keyStream);
        return numberOfBytes;
    }

    public long seekTo(long position) {
        reset();
        return skip(position);
    }

    public long getPosition() {
        return (getCounter() * 64) + ((long) this.index);
    }

    public void reset() {
        this.index = 0;
        resetLimitCounter();
        resetCounter();
        generateKeyStream(this.keyStream);
    }

    /* access modifiers changed from: protected */
    public long getCounter() {
        return (((long) this.engineState[9]) << 32) | (((long) this.engineState[8]) & 4294967295L);
    }

    /* access modifiers changed from: protected */
    public void resetCounter() {
        int[] iArr = this.engineState;
        this.engineState[9] = 0;
        iArr[8] = 0;
    }

    /* access modifiers changed from: protected */
    public void setKey(byte[] keyBytes, byte[] ivBytes) {
        byte[] constants;
        int offset;
        if (keyBytes != null) {
            if (keyBytes.length == 16 || keyBytes.length == 32) {
                this.engineState[1] = Pack.littleEndianToInt(keyBytes, 0);
                this.engineState[2] = Pack.littleEndianToInt(keyBytes, 4);
                this.engineState[3] = Pack.littleEndianToInt(keyBytes, 8);
                this.engineState[4] = Pack.littleEndianToInt(keyBytes, 12);
                if (keyBytes.length == 32) {
                    constants = sigma;
                    offset = 16;
                } else {
                    constants = tau;
                    offset = 0;
                }
                this.engineState[11] = Pack.littleEndianToInt(keyBytes, offset);
                this.engineState[12] = Pack.littleEndianToInt(keyBytes, offset + 4);
                this.engineState[13] = Pack.littleEndianToInt(keyBytes, offset + 8);
                this.engineState[14] = Pack.littleEndianToInt(keyBytes, offset + 12);
                this.engineState[0] = Pack.littleEndianToInt(constants, 0);
                this.engineState[5] = Pack.littleEndianToInt(constants, 4);
                this.engineState[10] = Pack.littleEndianToInt(constants, 8);
                this.engineState[15] = Pack.littleEndianToInt(constants, 12);
            } else {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 128 bit or 256 bit key");
            }
        }
        this.engineState[6] = Pack.littleEndianToInt(ivBytes, 0);
        this.engineState[7] = Pack.littleEndianToInt(ivBytes, 4);
    }

    /* access modifiers changed from: protected */
    public void generateKeyStream(byte[] output) {
        salsaCore(this.rounds, this.engineState, this.f6704x);
        Pack.intToLittleEndian(this.f6704x, output, 0);
    }

    public static void salsaCore(int rounds2, int[] input, int[] x) {
        if (input.length != 16) {
            throw new IllegalArgumentException();
        } else if (x.length != 16) {
            throw new IllegalArgumentException();
        } else if (rounds2 % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        } else {
            int x00 = input[0];
            int x01 = input[1];
            int x02 = input[2];
            int x03 = input[3];
            int x04 = input[4];
            int x05 = input[5];
            int x06 = input[6];
            int x07 = input[7];
            int x08 = input[8];
            int x09 = input[9];
            int x10 = input[10];
            int x11 = input[11];
            int x12 = input[12];
            int x13 = input[13];
            int x14 = input[14];
            int x15 = input[15];
            for (int i = rounds2; i > 0; i -= 2) {
                int x042 = x04 ^ rotl(x00 + x12, 7);
                int x082 = x08 ^ rotl(x042 + x00, 9);
                int x122 = x12 ^ rotl(x082 + x042, 13);
                int x002 = x00 ^ rotl(x122 + x082, 18);
                int x092 = x09 ^ rotl(x05 + x01, 7);
                int x132 = x13 ^ rotl(x092 + x05, 9);
                int x012 = x01 ^ rotl(x132 + x092, 13);
                int x052 = x05 ^ rotl(x012 + x132, 18);
                int x142 = x14 ^ rotl(x10 + x06, 7);
                int x022 = x02 ^ rotl(x142 + x10, 9);
                int x062 = x06 ^ rotl(x022 + x142, 13);
                int x102 = x10 ^ rotl(x062 + x022, 18);
                int x032 = x03 ^ rotl(x15 + x11, 7);
                int x072 = x07 ^ rotl(x032 + x15, 9);
                int x112 = x11 ^ rotl(x072 + x032, 13);
                int x152 = x15 ^ rotl(x112 + x072, 18);
                x01 = x012 ^ rotl(x002 + x032, 7);
                x02 = x022 ^ rotl(x01 + x002, 9);
                x03 = x032 ^ rotl(x02 + x01, 13);
                x00 = x002 ^ rotl(x03 + x02, 18);
                x06 = x062 ^ rotl(x052 + x042, 7);
                x07 = x072 ^ rotl(x06 + x052, 9);
                x04 = x042 ^ rotl(x07 + x06, 13);
                x05 = x052 ^ rotl(x04 + x07, 18);
                x11 = x112 ^ rotl(x102 + x092, 7);
                x08 = x082 ^ rotl(x11 + x102, 9);
                x09 = x092 ^ rotl(x08 + x11, 13);
                x10 = x102 ^ rotl(x09 + x08, 18);
                x12 = x122 ^ rotl(x152 + x142, 7);
                x13 = x132 ^ rotl(x12 + x152, 9);
                x14 = x142 ^ rotl(x13 + x12, 13);
                x15 = x152 ^ rotl(x14 + x13, 18);
            }
            x[0] = input[0] + x00;
            x[1] = input[1] + x01;
            x[2] = input[2] + x02;
            x[3] = input[3] + x03;
            x[4] = input[4] + x04;
            x[5] = input[5] + x05;
            x[6] = input[6] + x06;
            x[7] = input[7] + x07;
            x[8] = input[8] + x08;
            x[9] = input[9] + x09;
            x[10] = input[10] + x10;
            x[11] = input[11] + x11;
            x[12] = input[12] + x12;
            x[13] = input[13] + x13;
            x[14] = input[14] + x14;
            x[15] = input[15] + x15;
        }
    }

    protected static int rotl(int x, int y) {
        return (x << y) | (x >>> (-y));
    }

    private void resetLimitCounter() {
        this.cW0 = 0;
        this.cW1 = 0;
        this.cW2 = 0;
    }

    private boolean limitExceeded() {
        int i = this.cW0 + 1;
        this.cW0 = i;
        if (i != 0) {
            return false;
        }
        int i2 = this.cW1 + 1;
        this.cW1 = i2;
        if (i2 != 0) {
            return false;
        }
        int i3 = this.cW2 + 1;
        this.cW2 = i3;
        if ((i3 & 32) != 0) {
            return true;
        }
        return false;
    }

    private boolean limitExceeded(int len) {
        this.cW0 += len;
        if (this.cW0 >= len || this.cW0 < 0) {
            return false;
        }
        int i = this.cW1 + 1;
        this.cW1 = i;
        if (i != 0) {
            return false;
        }
        int i2 = this.cW2 + 1;
        this.cW2 = i2;
        if ((i2 & 32) != 0) {
            return true;
        }
        return false;
    }
}
