package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.spongycastle.crypto.InvalidCipherTextException;

public class ISO7816d4Padding implements BlockCipherPadding {
    public void init(SecureRandom random) throws IllegalArgumentException {
    }

    public String getPaddingName() {
        return "ISO7816-4";
    }

    public int addPadding(byte[] in, int inOff) {
        int added = in.length - inOff;
        in[inOff] = ISOFileInfo.DATA_BYTES1;
        while (true) {
            inOff++;
            if (inOff >= in.length) {
                return added;
            }
            in[inOff] = 0;
        }
    }

    public int padCount(byte[] in) throws InvalidCipherTextException {
        int count = in.length - 1;
        while (count > 0 && in[count] == 0) {
            count--;
        }
        if (in[count] == Byte.MIN_VALUE) {
            return in.length - count;
        }
        throw new InvalidCipherTextException("pad block corrupted");
    }
}
