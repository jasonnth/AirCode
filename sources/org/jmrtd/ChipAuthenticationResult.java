package org.jmrtd;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PublicKey;

public class ChipAuthenticationResult {
    private byte[] keyHash;
    private BigInteger keyId;
    private KeyPair keyPair;
    private PublicKey publicKey;

    public ChipAuthenticationResult(BigInteger bigInteger, PublicKey publicKey2, byte[] bArr, KeyPair keyPair2) {
        this.keyId = bigInteger;
        this.publicKey = publicKey2;
        this.keyHash = bArr;
        this.keyPair = keyPair2;
    }

    public BigInteger getKeyId() {
        return this.keyId;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    public byte[] getKeyHash() {
        return this.keyHash;
    }

    public KeyPair getKeyPair() {
        return this.keyPair;
    }
}
