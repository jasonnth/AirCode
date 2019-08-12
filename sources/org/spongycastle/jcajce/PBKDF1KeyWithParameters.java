package org.spongycastle.jcajce;

import javax.crypto.interfaces.PBEKey;
import org.spongycastle.crypto.CharToByteConverter;
import org.spongycastle.util.Arrays;

public class PBKDF1KeyWithParameters extends PBKDF1Key implements PBEKey {
    private final int iterationCount;
    private final byte[] salt;

    public PBKDF1KeyWithParameters(char[] password, CharToByteConverter converter, byte[] salt2, int iterationCount2) {
        super(password, converter);
        this.salt = Arrays.clone(salt2);
        this.iterationCount = iterationCount2;
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public int getIterationCount() {
        return this.iterationCount;
    }
}
