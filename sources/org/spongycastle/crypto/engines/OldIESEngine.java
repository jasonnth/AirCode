package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.Mac;
import org.spongycastle.util.Pack;

public class OldIESEngine extends IESEngine {
    public OldIESEngine(BasicAgreement agree, DerivationFunction kdf, Mac mac) {
        super(agree, kdf, mac);
    }

    public OldIESEngine(BasicAgreement agree, DerivationFunction kdf, Mac mac, BufferedBlockCipher cipher) {
        super(agree, kdf, mac, cipher);
    }

    /* access modifiers changed from: protected */
    public byte[] getLengthTag(byte[] p2) {
        byte[] L2 = new byte[4];
        if (p2 != null) {
            Pack.intToBigEndian(p2.length * 8, L2, 0);
        }
        return L2;
    }
}
