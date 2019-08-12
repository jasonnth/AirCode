package org.spongycastle.jcajce;

import java.io.OutputStream;
import java.security.KeyStore.LoadStoreParameter;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.ProtectionParameter;

public class PKCS12StoreParameter implements LoadStoreParameter {
    private final boolean forDEREncoding;
    private final OutputStream out;
    private final ProtectionParameter protectionParameter;

    public PKCS12StoreParameter(OutputStream out2, char[] password) {
        this(out2, password, false);
    }

    public PKCS12StoreParameter(OutputStream out2, ProtectionParameter protectionParameter2) {
        this(out2, protectionParameter2, false);
    }

    public PKCS12StoreParameter(OutputStream out2, char[] password, boolean forDEREncoding2) {
        this(out2, (ProtectionParameter) new PasswordProtection(password), forDEREncoding2);
    }

    public PKCS12StoreParameter(OutputStream out2, ProtectionParameter protectionParameter2, boolean forDEREncoding2) {
        this.out = out2;
        this.protectionParameter = protectionParameter2;
        this.forDEREncoding = forDEREncoding2;
    }

    public OutputStream getOutputStream() {
        return this.out;
    }

    public ProtectionParameter getProtectionParameter() {
        return this.protectionParameter;
    }

    public boolean isForDEREncoding() {
        return this.forDEREncoding;
    }
}
