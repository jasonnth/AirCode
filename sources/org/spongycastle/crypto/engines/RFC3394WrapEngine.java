package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.Arrays;

public class RFC3394WrapEngine implements Wrapper {
    private BlockCipher engine;
    private boolean forWrapping;

    /* renamed from: iv */
    private byte[] f6691iv;
    private KeyParameter param;
    private boolean wrapCipherMode;

    public RFC3394WrapEngine(BlockCipher engine2) {
        this(engine2, false);
    }

    public RFC3394WrapEngine(BlockCipher engine2, boolean useReverseDirection) {
        this.f6691iv = new byte[]{-90, -90, -90, -90, -90, -90, -90, -90};
        this.engine = engine2;
        this.wrapCipherMode = !useReverseDirection;
    }

    public void init(boolean forWrapping2, CipherParameters param2) {
        this.forWrapping = forWrapping2;
        if (param2 instanceof ParametersWithRandom) {
            param2 = ((ParametersWithRandom) param2).getParameters();
        }
        if (param2 instanceof KeyParameter) {
            this.param = (KeyParameter) param2;
        } else if (param2 instanceof ParametersWithIV) {
            this.f6691iv = ((ParametersWithIV) param2).getIV();
            this.param = (KeyParameter) ((ParametersWithIV) param2).getParameters();
            if (this.f6691iv.length != 8) {
                throw new IllegalArgumentException("IV not equal to 8");
            }
        }
    }

    public String getAlgorithmName() {
        return this.engine.getAlgorithmName();
    }

    public byte[] wrap(byte[] in, int inOff, int inLen) {
        if (!this.forWrapping) {
            throw new IllegalStateException("not set for wrapping");
        }
        int n = inLen / 8;
        if (n * 8 != inLen) {
            throw new DataLengthException("wrap data must be a multiple of 8 bytes");
        }
        byte[] block = new byte[(this.f6691iv.length + inLen)];
        byte[] buf = new byte[(this.f6691iv.length + 8)];
        System.arraycopy(this.f6691iv, 0, block, 0, this.f6691iv.length);
        System.arraycopy(in, inOff, block, this.f6691iv.length, inLen);
        this.engine.init(this.wrapCipherMode, this.param);
        for (int j = 0; j != 6; j++) {
            for (int i = 1; i <= n; i++) {
                System.arraycopy(block, 0, buf, 0, this.f6691iv.length);
                System.arraycopy(block, i * 8, buf, this.f6691iv.length, 8);
                this.engine.processBlock(buf, 0, buf, 0);
                int t = (n * j) + i;
                int k = 1;
                while (t != 0) {
                    int length = this.f6691iv.length - k;
                    buf[length] = (byte) (buf[length] ^ ((byte) t));
                    t >>>= 8;
                    k++;
                }
                System.arraycopy(buf, 0, block, 0, 8);
                System.arraycopy(buf, 8, block, i * 8, 8);
            }
        }
        return block;
    }

    public byte[] unwrap(byte[] in, int inOff, int inLen) throws InvalidCipherTextException {
        if (this.forWrapping) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int n = inLen / 8;
        if (n * 8 != inLen) {
            throw new InvalidCipherTextException("unwrap data must be a multiple of 8 bytes");
        }
        byte[] block = new byte[(inLen - this.f6691iv.length)];
        byte[] a = new byte[this.f6691iv.length];
        byte[] buf = new byte[(this.f6691iv.length + 8)];
        System.arraycopy(in, inOff, a, 0, this.f6691iv.length);
        System.arraycopy(in, this.f6691iv.length + inOff, block, 0, inLen - this.f6691iv.length);
        this.engine.init(!this.wrapCipherMode, this.param);
        int n2 = n - 1;
        for (int j = 5; j >= 0; j--) {
            for (int i = n2; i >= 1; i--) {
                System.arraycopy(a, 0, buf, 0, this.f6691iv.length);
                System.arraycopy(block, (i - 1) * 8, buf, this.f6691iv.length, 8);
                int t = (n2 * j) + i;
                int k = 1;
                while (t != 0) {
                    int length = this.f6691iv.length - k;
                    buf[length] = (byte) (buf[length] ^ ((byte) t));
                    t >>>= 8;
                    k++;
                }
                this.engine.processBlock(buf, 0, buf, 0);
                System.arraycopy(buf, 0, a, 0, 8);
                System.arraycopy(buf, 8, block, (i - 1) * 8, 8);
            }
        }
        if (Arrays.constantTimeAreEqual(a, this.f6691iv)) {
            return block;
        }
        throw new InvalidCipherTextException("checksum failed");
    }
}
