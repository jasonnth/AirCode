package org.spongycastle.jcajce.spec;

import javax.crypto.spec.PBEKeySpec;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class PBKDF2KeySpec extends PBEKeySpec {
    private AlgorithmIdentifier prf;

    public PBKDF2KeySpec(char[] password, byte[] salt, int iterationCount, int keySize, AlgorithmIdentifier prf2) {
        super(password, salt, iterationCount, keySize);
        this.prf = prf2;
    }

    public AlgorithmIdentifier getPrf() {
        return this.prf;
    }
}
