package org.spongycastle.crypto.tls;

import org.spongycastle.util.Arrays;

class TlsSessionImpl implements TlsSession {
    final byte[] sessionID;
    SessionParameters sessionParameters;

    TlsSessionImpl(byte[] sessionID2, SessionParameters sessionParameters2) {
        if (sessionID2 == null) {
            throw new IllegalArgumentException("'sessionID' cannot be null");
        } else if (sessionID2.length < 1 || sessionID2.length > 32) {
            throw new IllegalArgumentException("'sessionID' must have length between 1 and 32 bytes, inclusive");
        } else {
            this.sessionID = Arrays.clone(sessionID2);
            this.sessionParameters = sessionParameters2;
        }
    }

    public synchronized SessionParameters exportSessionParameters() {
        return this.sessionParameters == null ? null : this.sessionParameters.copy();
    }

    public synchronized byte[] getSessionID() {
        return this.sessionID;
    }

    public synchronized void invalidate() {
        if (this.sessionParameters != null) {
            this.sessionParameters.clear();
            this.sessionParameters = null;
        }
    }

    public synchronized boolean isResumable() {
        return this.sessionParameters != null;
    }
}
