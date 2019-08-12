package org.spongycastle.crypto.tls;

import java.io.IOException;

public class TlsFatalAlert extends IOException {
    private static final long serialVersionUID = 3584313123679111168L;
    protected Throwable alertCause;
    protected short alertDescription;

    public TlsFatalAlert(short alertDescription2) {
        this(alertDescription2, null);
    }

    public TlsFatalAlert(short alertDescription2, Throwable alertCause2) {
        super(AlertDescription.getText(alertDescription2));
        this.alertDescription = alertDescription2;
        this.alertCause = alertCause2;
    }

    public short getAlertDescription() {
        return this.alertDescription;
    }

    public Throwable getCause() {
        return this.alertCause;
    }
}
