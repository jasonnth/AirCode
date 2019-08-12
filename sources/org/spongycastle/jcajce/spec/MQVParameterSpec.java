package org.spongycastle.jcajce.spec;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.util.Arrays;

public class MQVParameterSpec implements AlgorithmParameterSpec {
    private final PrivateKey ephemeralPrivateKey;
    private final PublicKey ephemeralPublicKey;
    private final PublicKey otherPartyEphemeralKey;
    private final byte[] userKeyingMaterial;

    public MQVParameterSpec(PublicKey ephemeralPublicKey2, PrivateKey ephemeralPrivateKey2, PublicKey otherPartyEphemeralKey2, byte[] userKeyingMaterial2) {
        this.ephemeralPublicKey = ephemeralPublicKey2;
        this.ephemeralPrivateKey = ephemeralPrivateKey2;
        this.otherPartyEphemeralKey = otherPartyEphemeralKey2;
        this.userKeyingMaterial = Arrays.clone(userKeyingMaterial2);
    }

    public MQVParameterSpec(PublicKey ephemeralPublicKey2, PrivateKey ephemeralPrivateKey2, PublicKey otherPartyEphemeralKey2) {
        this(ephemeralPublicKey2, ephemeralPrivateKey2, otherPartyEphemeralKey2, null);
    }

    public MQVParameterSpec(KeyPair ephemeralKeyPair, PublicKey otherPartyEphemeralKey2, byte[] userKeyingMaterial2) {
        this(ephemeralKeyPair.getPublic(), ephemeralKeyPair.getPrivate(), otherPartyEphemeralKey2, userKeyingMaterial2);
    }

    public MQVParameterSpec(PrivateKey ephemeralPrivateKey2, PublicKey otherPartyEphemeralKey2, byte[] userKeyingMaterial2) {
        this(null, ephemeralPrivateKey2, otherPartyEphemeralKey2, userKeyingMaterial2);
    }

    public MQVParameterSpec(KeyPair ephemeralKeyPair, PublicKey otherPartyEphemeralKey2) {
        this(ephemeralKeyPair.getPublic(), ephemeralKeyPair.getPrivate(), otherPartyEphemeralKey2, null);
    }

    public MQVParameterSpec(PrivateKey ephemeralPrivateKey2, PublicKey otherPartyEphemeralKey2) {
        this(null, ephemeralPrivateKey2, otherPartyEphemeralKey2, null);
    }

    public PrivateKey getEphemeralPrivateKey() {
        return this.ephemeralPrivateKey;
    }

    public PublicKey getEphemeralPublicKey() {
        return this.ephemeralPublicKey;
    }

    public PublicKey getOtherPartyEphemeralKey() {
        return this.otherPartyEphemeralKey;
    }

    public byte[] getUserKeyingMaterial() {
        return Arrays.clone(this.userKeyingMaterial);
    }
}
