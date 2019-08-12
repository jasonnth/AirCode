package org.spongycastle.crypto.modes;

import com.airbnb.android.airmapview.AirMapInterface;
import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.StreamBlockCipher;
import org.spongycastle.crypto.params.ParametersWithIV;

public class GOFBBlockCipher extends StreamBlockCipher {

    /* renamed from: C1 */
    static final int f6785C1 = 16843012;

    /* renamed from: C2 */
    static final int f6786C2 = 16843009;

    /* renamed from: IV */
    private byte[] f6787IV;

    /* renamed from: N3 */
    int f6788N3;

    /* renamed from: N4 */
    int f6789N4;
    private final int blockSize;
    private int byteCount;
    private final BlockCipher cipher;
    boolean firstStep = true;
    private byte[] ofbOutV;
    private byte[] ofbV;

    public GOFBBlockCipher(BlockCipher cipher2) {
        super(cipher2);
        this.cipher = cipher2;
        this.blockSize = cipher2.getBlockSize();
        if (this.blockSize != 8) {
            throw new IllegalArgumentException("GCTR only for 64 bit block ciphers");
        }
        this.f6787IV = new byte[cipher2.getBlockSize()];
        this.ofbV = new byte[cipher2.getBlockSize()];
        this.ofbOutV = new byte[cipher2.getBlockSize()];
    }

    public void init(boolean encrypting, CipherParameters params) throws IllegalArgumentException {
        this.firstStep = true;
        this.f6788N3 = 0;
        this.f6789N4 = 0;
        if (params instanceof ParametersWithIV) {
            ParametersWithIV ivParam = (ParametersWithIV) params;
            byte[] iv = ivParam.getIV();
            if (iv.length < this.f6787IV.length) {
                System.arraycopy(iv, 0, this.f6787IV, this.f6787IV.length - iv.length, iv.length);
                for (int i = 0; i < this.f6787IV.length - iv.length; i++) {
                    this.f6787IV[i] = 0;
                }
            } else {
                System.arraycopy(iv, 0, this.f6787IV, 0, this.f6787IV.length);
            }
            reset();
            if (ivParam.getParameters() != null) {
                this.cipher.init(true, ivParam.getParameters());
                return;
            }
            return;
        }
        reset();
        if (params != null) {
            this.cipher.init(true, params);
        }
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/GCTR";
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        processBytes(in, inOff, this.blockSize, out, outOff);
        return this.blockSize;
    }

    public void reset() {
        this.firstStep = true;
        this.f6788N3 = 0;
        this.f6789N4 = 0;
        System.arraycopy(this.f6787IV, 0, this.ofbV, 0, this.f6787IV.length);
        this.byteCount = 0;
        this.cipher.reset();
    }

    private int bytesToint(byte[] in, int inOff) {
        return ((in[inOff + 3] << 24) & AirMapInterface.CIRCLE_BORDER_COLOR) + ((in[inOff + 2] << 16) & 16711680) + ((in[inOff + 1] << 8) & ExploreBaseRangeSeekBar.ACTION_POINTER_INDEX_MASK) + (in[inOff] & 255);
    }

    private void intTobytes(int num, byte[] out, int outOff) {
        out[outOff + 3] = (byte) (num >>> 24);
        out[outOff + 2] = (byte) (num >>> 16);
        out[outOff + 1] = (byte) (num >>> 8);
        out[outOff] = (byte) num;
    }

    /* access modifiers changed from: protected */
    public byte calculateByte(byte b) {
        if (this.byteCount == 0) {
            if (this.firstStep) {
                this.firstStep = false;
                this.cipher.processBlock(this.ofbV, 0, this.ofbOutV, 0);
                this.f6788N3 = bytesToint(this.ofbOutV, 0);
                this.f6789N4 = bytesToint(this.ofbOutV, 4);
            }
            this.f6788N3 += f6786C2;
            this.f6789N4 += f6785C1;
            intTobytes(this.f6788N3, this.ofbV, 0);
            intTobytes(this.f6789N4, this.ofbV, 4);
            this.cipher.processBlock(this.ofbV, 0, this.ofbOutV, 0);
        }
        byte[] bArr = this.ofbOutV;
        int i = this.byteCount;
        this.byteCount = i + 1;
        byte rv = (byte) (bArr[i] ^ b);
        if (this.byteCount == this.blockSize) {
            this.byteCount = 0;
            System.arraycopy(this.ofbV, this.blockSize, this.ofbV, 0, this.ofbV.length - this.blockSize);
            System.arraycopy(this.ofbOutV, 0, this.ofbV, this.ofbV.length - this.blockSize, this.blockSize);
        }
        return rv;
    }
}
