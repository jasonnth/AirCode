package org.jmrtd.cbeff;

import org.jmrtd.cbeff.BiometricDataBlock;

public class SimpleCBEFFInfo<B extends BiometricDataBlock> implements CBEFFInfo {
    private B bdb;

    public SimpleCBEFFInfo(B b) {
        this.bdb = b;
    }

    public B getBiometricDataBlock() {
        return this.bdb;
    }
}
