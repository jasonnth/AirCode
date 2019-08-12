package org.spongycastle.crypto.tls;

import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

public class BasicTlsPSKIdentity implements TlsPSKIdentity {
    protected byte[] identity;
    protected byte[] psk;

    public BasicTlsPSKIdentity(byte[] identity2, byte[] psk2) {
        this.identity = Arrays.clone(identity2);
        this.psk = Arrays.clone(psk2);
    }

    public BasicTlsPSKIdentity(String identity2, byte[] psk2) {
        this.identity = Strings.toUTF8ByteArray(identity2);
        this.psk = Arrays.clone(psk2);
    }

    public void skipIdentityHint() {
    }

    public void notifyIdentityHint(byte[] psk_identity_hint) {
    }

    public byte[] getPSKIdentity() {
        return this.identity;
    }

    public byte[] getPSK() {
        return this.psk;
    }
}
