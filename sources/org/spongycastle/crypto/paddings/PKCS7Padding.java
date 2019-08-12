package org.spongycastle.crypto.paddings;

import java.security.SecureRandom;
import org.spongycastle.crypto.InvalidCipherTextException;

public class PKCS7Padding implements BlockCipherPadding {
    public void init(SecureRandom random) throws IllegalArgumentException {
    }

    public String getPaddingName() {
        return "PKCS7";
    }

    public int addPadding(byte[] in, int inOff) {
        byte code = (byte) (in.length - inOff);
        while (inOff < in.length) {
            in[inOff] = code;
            inOff++;
        }
        return code;
    }

    public int padCount(byte[] in) throws InvalidCipherTextException {
        boolean z;
        boolean z2;
        int count = in[in.length - 1] & 255;
        byte countAsbyte = (byte) count;
        if (count > in.length) {
            z = true;
        } else {
            z = false;
        }
        boolean failed = z | (count == 0);
        for (int i = 0; i < in.length; i++) {
            if (in.length - i <= count) {
                z2 = true;
            } else {
                z2 = false;
            }
            failed |= z2 & (in[i] != countAsbyte);
        }
        if (!failed) {
            return count;
        }
        throw new InvalidCipherTextException("pad block corrupted");
    }
}
