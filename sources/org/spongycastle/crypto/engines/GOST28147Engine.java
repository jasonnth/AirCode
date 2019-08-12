package org.spongycastle.crypto.engines;

import com.airbnb.android.airmapview.AirMapInterface;
import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar;
import java.util.Hashtable;
import org.jmrtd.PassportService;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithSBox;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

public class GOST28147Engine implements BlockCipher {
    protected static final int BLOCK_SIZE = 8;
    private static byte[] DSbox_A = {10, 4, 5, 6, 8, 1, 3, 7, 13, PassportService.SF_DG12, 14, 0, 9, 2, PassportService.SF_DG11, 15, 5, 15, 4, 0, 2, 13, PassportService.SF_DG11, 9, 1, 7, 6, 3, PassportService.SF_DG12, 14, 10, 8, 7, 15, PassportService.SF_DG12, 14, 9, 4, 1, 0, 3, PassportService.SF_DG11, 5, 2, 6, 10, 8, 13, 4, 10, 7, PassportService.SF_DG12, 0, 15, 2, 8, 14, 1, 6, 5, 13, PassportService.SF_DG11, 9, 3, 7, 6, 4, PassportService.SF_DG11, 9, PassportService.SF_DG12, 2, 10, 1, 8, 0, 14, 15, 13, 3, 5, 7, 6, 2, 4, 13, 9, 15, 0, 10, 1, 5, PassportService.SF_DG11, 8, 14, PassportService.SF_DG12, 3, 13, 14, 4, 1, 7, 0, 5, 10, 3, PassportService.SF_DG12, 8, 15, 6, 2, 9, PassportService.SF_DG11, 1, 3, 10, 9, 5, PassportService.SF_DG11, 4, 15, 8, 6, 7, 14, 13, 0, 2, PassportService.SF_DG12};
    private static byte[] DSbox_Test = {4, 10, 9, 2, 13, 8, 0, 14, 6, PassportService.SF_DG11, 1, PassportService.SF_DG12, 7, 15, 5, 3, 14, PassportService.SF_DG11, 4, PassportService.SF_DG12, 6, 13, 15, 10, 2, 3, 8, 1, 0, 7, 5, 9, 5, 8, 1, 13, 10, 3, 4, 2, 14, 15, PassportService.SF_DG12, 7, 6, 0, 9, PassportService.SF_DG11, 7, 13, 10, 1, 0, 8, 9, 15, 14, 4, 6, PassportService.SF_DG12, PassportService.SF_DG11, 2, 5, 3, 6, PassportService.SF_DG12, 7, 1, 5, 15, 13, 8, 4, 10, 9, 14, 0, 3, PassportService.SF_DG11, 2, 4, PassportService.SF_DG11, 10, 0, 7, 2, 1, 13, 3, 6, 8, 5, 9, PassportService.SF_DG12, 15, 14, 13, PassportService.SF_DG11, 4, 1, 3, 15, 5, 9, 0, 10, 14, 7, 6, 8, 2, PassportService.SF_DG12, 1, 15, 13, 0, 5, 7, 10, 4, 9, 2, 3, 14, 6, PassportService.SF_DG11, 8, PassportService.SF_DG12};
    private static byte[] ESbox_A = {9, 6, 3, 2, 8, PassportService.SF_DG11, 1, 7, 10, 4, 14, 15, PassportService.SF_DG12, 0, 13, 5, 3, 7, 14, 9, 8, 10, 15, 0, 5, 2, 6, PassportService.SF_DG12, PassportService.SF_DG11, 4, 13, 1, 14, 4, 6, 2, PassportService.SF_DG11, 3, 13, 8, PassportService.SF_DG12, 15, 5, 10, 0, 7, 1, 9, 14, 7, 10, PassportService.SF_DG12, 13, 1, 3, 9, 0, 2, PassportService.SF_DG11, 4, 15, 8, 5, 6, PassportService.SF_DG11, 5, 1, 9, 8, 13, 15, 0, 14, 4, 2, 3, PassportService.SF_DG12, 7, 10, 6, 3, 10, 13, PassportService.SF_DG12, 1, 2, 0, PassportService.SF_DG11, 7, 5, 9, 4, 8, 15, 14, 6, 1, 13, 2, 9, 7, 10, 6, 0, 8, PassportService.SF_DG12, 4, 5, 15, 3, PassportService.SF_DG11, 14, PassportService.SF_DG11, 10, 15, 5, 0, PassportService.SF_DG12, 14, 8, 6, 2, 3, 9, 1, 7, 13, 4};
    private static byte[] ESbox_B = {8, 4, PassportService.SF_DG11, 1, 3, 5, 0, 9, 2, 14, 10, PassportService.SF_DG12, 13, 6, 7, 15, 0, 1, 2, 10, 4, 13, 5, PassportService.SF_DG12, 9, 7, 3, 15, PassportService.SF_DG11, 8, 6, 14, 14, PassportService.SF_DG12, 0, 10, 9, 2, 13, PassportService.SF_DG11, 7, 5, 8, 15, 3, 6, 1, 4, 7, 5, 0, 13, PassportService.SF_DG11, 6, 1, 2, 3, 10, PassportService.SF_DG12, 15, 4, 14, 9, 8, 2, 7, PassportService.SF_DG12, 15, 9, 5, 10, PassportService.SF_DG11, 1, 4, 0, 13, 6, 8, 14, 3, 8, 3, 2, 6, 4, 13, 14, PassportService.SF_DG11, PassportService.SF_DG12, 1, 7, 15, 10, 0, 9, 5, 5, 2, 10, PassportService.SF_DG11, 9, 1, PassportService.SF_DG12, 3, 7, 4, 13, 0, 6, 15, 8, 14, 0, 4, PassportService.SF_DG11, 14, 8, 3, 7, 1, 10, 2, 9, 6, 15, 13, 5, PassportService.SF_DG12};
    private static byte[] ESbox_C = {1, PassportService.SF_DG11, PassportService.SF_DG12, 2, 9, 13, 0, 15, 4, 5, 8, 14, 10, 7, 6, 3, 0, 1, 7, 13, PassportService.SF_DG11, 4, 5, 2, 8, 14, 15, PassportService.SF_DG12, 9, 10, 6, 3, 8, 2, 5, 0, 4, 9, 15, 10, 3, 7, PassportService.SF_DG12, 13, 6, 14, 1, PassportService.SF_DG11, 3, 6, 0, 1, 5, 13, 10, 8, PassportService.SF_DG11, 2, 9, 7, 14, 15, PassportService.SF_DG12, 4, 8, 13, PassportService.SF_DG11, 0, 4, 5, 1, 2, 9, 3, PassportService.SF_DG12, 14, 6, 15, 10, 7, PassportService.SF_DG12, 9, PassportService.SF_DG11, 1, 8, 14, 2, 4, 7, 3, 6, 5, 10, 0, 15, 13, 10, 9, 6, 8, 13, 14, 2, 0, 15, 3, 5, PassportService.SF_DG11, 4, 1, PassportService.SF_DG12, 7, 7, 4, 0, 5, 10, 2, 15, 14, PassportService.SF_DG12, 6, 1, PassportService.SF_DG11, 13, 9, 3, 8};
    private static byte[] ESbox_D = {15, PassportService.SF_DG12, 2, 10, 6, 4, 5, 0, 7, 9, 14, 13, 1, PassportService.SF_DG11, 8, 3, PassportService.SF_DG11, 6, 3, 4, PassportService.SF_DG12, 15, 14, 2, 7, 13, 8, 0, 5, 10, 9, 1, 1, PassportService.SF_DG12, PassportService.SF_DG11, 0, 15, 14, 6, 5, 10, 13, 4, 8, 9, 3, 7, 2, 1, 5, 14, PassportService.SF_DG12, 10, 7, 0, 13, 6, 2, PassportService.SF_DG11, 4, 9, 3, 15, 8, 0, PassportService.SF_DG12, 8, 9, 13, 2, 10, PassportService.SF_DG11, 7, 3, 6, 5, 4, 14, 15, 1, 8, 0, 15, 3, 2, 5, 14, PassportService.SF_DG11, 1, 10, 4, 7, PassportService.SF_DG12, 9, 13, 6, 3, 0, 6, 15, 1, 14, 9, 2, 13, 8, PassportService.SF_DG12, 4, PassportService.SF_DG11, 10, 5, 7, 1, 10, 6, 8, 15, PassportService.SF_DG11, 0, 4, PassportService.SF_DG12, 3, 5, 9, 7, 13, 2, 14};
    private static byte[] ESbox_Test = {4, 2, 15, 5, 9, 1, 0, 8, 14, 3, PassportService.SF_DG11, PassportService.SF_DG12, 13, 7, 10, 6, PassportService.SF_DG12, 9, 15, 14, 8, 1, 3, 10, 2, 7, 4, 13, 6, 0, PassportService.SF_DG11, 5, 13, 8, 14, PassportService.SF_DG12, 7, 3, 9, 10, 1, 5, 2, 4, 6, 15, 0, PassportService.SF_DG11, 14, 9, PassportService.SF_DG11, 2, 5, 15, 7, 1, 0, 13, PassportService.SF_DG12, 6, 10, 4, 3, 8, 3, 14, 5, 9, 6, 8, 0, 13, 10, PassportService.SF_DG11, 7, PassportService.SF_DG12, 2, 1, 15, 4, 8, 15, 6, PassportService.SF_DG11, 1, 9, PassportService.SF_DG12, 5, 13, 3, 7, 10, 0, 14, 2, 4, 9, PassportService.SF_DG11, PassportService.SF_DG12, 0, 3, 6, 7, 5, 4, 8, 14, 15, 1, 10, 2, 13, PassportService.SF_DG12, 6, 5, 2, PassportService.SF_DG11, 0, 9, 13, 3, 14, 7, 10, 15, 4, 1, 8};
    private static byte[] Sbox_Default = {4, 10, 9, 2, 13, 8, 0, 14, 6, PassportService.SF_DG11, 1, PassportService.SF_DG12, 7, 15, 5, 3, 14, PassportService.SF_DG11, 4, PassportService.SF_DG12, 6, 13, 15, 10, 2, 3, 8, 1, 0, 7, 5, 9, 5, 8, 1, 13, 10, 3, 4, 2, 14, 15, PassportService.SF_DG12, 7, 6, 0, 9, PassportService.SF_DG11, 7, 13, 10, 1, 0, 8, 9, 15, 14, 4, 6, PassportService.SF_DG12, PassportService.SF_DG11, 2, 5, 3, 6, PassportService.SF_DG12, 7, 1, 5, 15, 13, 8, 4, 10, 9, 14, 0, 3, PassportService.SF_DG11, 2, 4, PassportService.SF_DG11, 10, 0, 7, 2, 1, 13, 3, 6, 8, 5, 9, PassportService.SF_DG12, 15, 14, 13, PassportService.SF_DG11, 4, 1, 3, 15, 5, 9, 0, 10, 14, 7, 6, 8, 2, PassportService.SF_DG12, 1, 15, 13, 0, 5, 7, 10, 4, 9, 2, 3, 14, 6, PassportService.SF_DG11, 8, PassportService.SF_DG12};
    private static Hashtable sBoxes = new Hashtable();

    /* renamed from: S */
    private byte[] f6672S = Sbox_Default;
    private boolean forEncryption;
    private int[] workingKey = null;

    static {
        addSBox("Default", Sbox_Default);
        addSBox("E-TEST", ESbox_Test);
        addSBox("E-A", ESbox_A);
        addSBox("E-B", ESbox_B);
        addSBox("E-C", ESbox_C);
        addSBox("E-D", ESbox_D);
        addSBox("D-TEST", DSbox_Test);
        addSBox("D-A", DSbox_A);
    }

    private static void addSBox(String sBoxName, byte[] sBox) {
        sBoxes.put(Strings.toUpperCase(sBoxName), sBox);
    }

    public void init(boolean forEncryption2, CipherParameters params) {
        if (params instanceof ParametersWithSBox) {
            ParametersWithSBox param = (ParametersWithSBox) params;
            byte[] sBox = param.getSBox();
            if (sBox.length != Sbox_Default.length) {
                throw new IllegalArgumentException("invalid S-box passed to GOST28147 init");
            }
            this.f6672S = Arrays.clone(sBox);
            if (param.getParameters() != null) {
                this.workingKey = generateWorkingKey(forEncryption2, ((KeyParameter) param.getParameters()).getKey());
            }
        } else if (params instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(forEncryption2, ((KeyParameter) params).getKey());
        } else if (params != null) {
            throw new IllegalArgumentException("invalid parameter passed to GOST28147 init - " + params.getClass().getName());
        }
    }

    public String getAlgorithmName() {
        return "GOST28147";
    }

    public int getBlockSize() {
        return 8;
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) {
        if (this.workingKey == null) {
            throw new IllegalStateException("GOST28147 engine not initialised");
        } else if (inOff + 8 > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (outOff + 8 > out.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            GOST28147Func(this.workingKey, in, inOff, out, outOff);
            return 8;
        }
    }

    public void reset() {
    }

    private int[] generateWorkingKey(boolean forEncryption2, byte[] userKey) {
        this.forEncryption = forEncryption2;
        if (userKey.length != 32) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        int[] key = new int[8];
        for (int i = 0; i != 8; i++) {
            key[i] = bytesToint(userKey, i * 4);
        }
        return key;
    }

    private int GOST28147_mainStep(int n1, int key) {
        int cm = key + n1;
        int om = (this.f6672S[((cm >> 0) & 15) + 0] << 0) + (this.f6672S[((cm >> 4) & 15) + 16] << 4) + (this.f6672S[((cm >> 8) & 15) + 32] << 8) + (this.f6672S[((cm >> 12) & 15) + 48] << PassportService.SF_DG12) + (this.f6672S[((cm >> 16) & 15) + 64] << 16) + (this.f6672S[((cm >> 20) & 15) + 80] << 20) + (this.f6672S[((cm >> 24) & 15) + 96] << 24) + (this.f6672S[((cm >> 28) & 15) + 112] << PassportService.SF_CVCA);
        return (om << 11) | (om >>> 21);
    }

    private void GOST28147Func(int[] workingKey2, byte[] in, int inOff, byte[] out, int outOff) {
        int N1 = bytesToint(in, inOff);
        int N2 = bytesToint(in, inOff + 4);
        if (this.forEncryption) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 8; j++) {
                    int tmp = N1;
                    N1 = N2 ^ GOST28147_mainStep(N1, workingKey2[j]);
                    N2 = tmp;
                }
            }
            for (int j2 = 7; j2 > 0; j2--) {
                int tmp2 = N1;
                N1 = N2 ^ GOST28147_mainStep(N1, workingKey2[j2]);
                N2 = tmp2;
            }
        } else {
            for (int j3 = 0; j3 < 8; j3++) {
                int tmp3 = N1;
                N1 = N2 ^ GOST28147_mainStep(N1, workingKey2[j3]);
                N2 = tmp3;
            }
            int k2 = 0;
            while (k2 < 3) {
                int j4 = 7;
                while (j4 >= 0 && (k2 != 2 || j4 != 0)) {
                    int tmp4 = N1;
                    N1 = N2 ^ GOST28147_mainStep(N1, workingKey2[j4]);
                    N2 = tmp4;
                    j4--;
                }
                k2++;
            }
        }
        int N22 = N2 ^ GOST28147_mainStep(N1, workingKey2[0]);
        intTobytes(N1, out, outOff);
        intTobytes(N22, out, outOff + 4);
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

    public static byte[] getSBox(String sBoxName) {
        byte[] sBox = (byte[]) sBoxes.get(Strings.toUpperCase(sBoxName));
        if (sBox != null) {
            return Arrays.clone(sBox);
        }
        throw new IllegalArgumentException("Unknown S-Box - possible types: \"Default\", \"E-Test\", \"E-A\", \"E-B\", \"E-C\", \"E-D\", \"D-Test\", \"D-A\".");
    }
}
