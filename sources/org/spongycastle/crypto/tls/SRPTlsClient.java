package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;
import org.spongycastle.util.Arrays;

public class SRPTlsClient extends AbstractTlsClient {
    protected TlsSRPGroupVerifier groupVerifier;
    protected byte[] identity;
    protected byte[] password;

    public SRPTlsClient(byte[] identity2, byte[] password2) {
        this(new DefaultTlsCipherFactory(), new DefaultTlsSRPGroupVerifier(), identity2, password2);
    }

    public SRPTlsClient(TlsCipherFactory cipherFactory, byte[] identity2, byte[] password2) {
        this(cipherFactory, new DefaultTlsSRPGroupVerifier(), identity2, password2);
    }

    public SRPTlsClient(TlsCipherFactory cipherFactory, TlsSRPGroupVerifier groupVerifier2, byte[] identity2, byte[] password2) {
        super(cipherFactory);
        this.groupVerifier = groupVerifier2;
        this.identity = Arrays.clone(identity2);
        this.password = Arrays.clone(password2);
    }

    /* access modifiers changed from: protected */
    public boolean requireSRPServerExtension() {
        return false;
    }

    public int[] getCipherSuites() {
        return new int[]{49182};
    }

    public Hashtable getClientExtensions() throws IOException {
        Hashtable clientExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(super.getClientExtensions());
        TlsSRPUtils.addSRPExtension(clientExtensions, this.identity);
        return clientExtensions;
    }

    public void processServerExtensions(Hashtable serverExtensions) throws IOException {
        if (TlsUtils.hasExpectedEmptyExtensionData(serverExtensions, TlsSRPUtils.EXT_SRP, 47) || !requireSRPServerExtension()) {
            super.processServerExtensions(serverExtensions);
            return;
        }
        throw new TlsFatalAlert(47);
    }

    public TlsKeyExchange getKeyExchange() throws IOException {
        int keyExchangeAlgorithm = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        switch (keyExchangeAlgorithm) {
            case 21:
            case 22:
            case 23:
                return createSRPKeyExchange(keyExchangeAlgorithm);
            default:
                throw new TlsFatalAlert(80);
        }
    }

    public TlsAuthentication getAuthentication() throws IOException {
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createSRPKeyExchange(int keyExchange) {
        return new TlsSRPKeyExchange(keyExchange, this.supportedSignatureAlgorithms, this.groupVerifier, this.identity, this.password);
    }
}
