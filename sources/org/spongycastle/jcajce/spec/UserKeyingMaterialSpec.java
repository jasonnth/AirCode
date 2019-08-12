package org.spongycastle.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.util.Arrays;

public class UserKeyingMaterialSpec implements AlgorithmParameterSpec {
    private final byte[] userKeyingMaterial;

    public UserKeyingMaterialSpec(byte[] userKeyingMaterial2) {
        this.userKeyingMaterial = Arrays.clone(userKeyingMaterial2);
    }

    public byte[] getUserKeyingMaterial() {
        return Arrays.clone(this.userKeyingMaterial);
    }
}
