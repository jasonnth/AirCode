package org.spongycastle.util.encoders;

import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;

public class HexTranslator implements Translator {
    private static final byte[] hexTable = {ISO7816.INS_DECREASE, 49, ISO7816.INS_INCREASE, 51, ISO7816.INS_DECREASE_STAMPED, 53, 54, 55, 56, 57, 97, ISOFileInfo.FCP_BYTE, 99, ISOFileInfo.FMD_BYTE, 101, 102};

    public int getEncodedBlockSize() {
        return 2;
    }

    public int encode(byte[] in, int inOff, int length, byte[] out, int outOff) {
        int i = 0;
        int j = 0;
        while (i < length) {
            out[outOff + j] = hexTable[(in[inOff] >> 4) & 15];
            out[outOff + j + 1] = hexTable[in[inOff] & 15];
            inOff++;
            i++;
            j += 2;
        }
        return length * 2;
    }

    public int getDecodedBlockSize() {
        return 1;
    }

    public int decode(byte[] in, int inOff, int length, byte[] out, int outOff) {
        int halfLength = length / 2;
        for (int i = 0; i < halfLength; i++) {
            byte left = in[(i * 2) + inOff];
            byte right = in[(i * 2) + inOff + 1];
            if (left < 97) {
                out[outOff] = (byte) ((left + ISO7816.INS_WRITE_BINARY) << 4);
            } else {
                out[outOff] = (byte) (((left - 97) + 10) << 4);
            }
            if (right < 97) {
                out[outOff] = (byte) (out[outOff] + ((byte) (right + ISO7816.INS_WRITE_BINARY)));
            } else {
                out[outOff] = (byte) (out[outOff] + ((byte) ((right - 97) + 10)));
            }
            outOff++;
        }
        return halfLength;
    }
}
