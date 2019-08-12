package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import org.spongycastle.crypto.Signer;

class SignerInputBuffer extends ByteArrayOutputStream {
    SignerInputBuffer() {
    }

    /* access modifiers changed from: 0000 */
    public void updateSigner(Signer s) {
        s.update(this.buf, 0, this.count);
    }
}
