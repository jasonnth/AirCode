package org.spongycastle.jce.provider;

import java.io.OutputStream;
import java.security.KeyStore.LoadStoreParameter;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.ProtectionParameter;

public class JDKPKCS12StoreParameter implements LoadStoreParameter {
    private OutputStream outputStream;
    private ProtectionParameter protectionParameter;
    private boolean useDEREncoding;

    public OutputStream getOutputStream() {
        return this.outputStream;
    }

    public ProtectionParameter getProtectionParameter() {
        return this.protectionParameter;
    }

    public boolean isUseDEREncoding() {
        return this.useDEREncoding;
    }

    public void setOutputStream(OutputStream outputStream2) {
        this.outputStream = outputStream2;
    }

    public void setPassword(char[] password) {
        this.protectionParameter = new PasswordProtection(password);
    }

    public void setProtectionParameter(ProtectionParameter protectionParameter2) {
        this.protectionParameter = protectionParameter2;
    }

    public void setUseDEREncoding(boolean useDEREncoding2) {
        this.useDEREncoding = useDEREncoding2;
    }
}
