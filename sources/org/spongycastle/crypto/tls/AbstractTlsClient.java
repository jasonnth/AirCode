package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

public abstract class AbstractTlsClient extends AbstractTlsPeer implements TlsClient {
    protected TlsCipherFactory cipherFactory;
    protected short[] clientECPointFormats;
    protected TlsClientContext context;
    protected int[] namedCurves;
    protected int selectedCipherSuite;
    protected short selectedCompressionMethod;
    protected short[] serverECPointFormats;
    protected Vector supportedSignatureAlgorithms;

    public AbstractTlsClient() {
        this(new DefaultTlsCipherFactory());
    }

    public AbstractTlsClient(TlsCipherFactory cipherFactory2) {
        this.cipherFactory = cipherFactory2;
    }

    /* access modifiers changed from: protected */
    public boolean allowUnexpectedServerExtension(Integer extensionType, byte[] extensionData) throws IOException {
        switch (extensionType.intValue()) {
            case 10:
                TlsECCUtils.readSupportedEllipticCurvesExtension(extensionData);
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: protected */
    public void checkForUnexpectedServerExtension(Hashtable serverExtensions, Integer extensionType) throws IOException {
        byte[] extensionData = TlsUtils.getExtensionData(serverExtensions, extensionType);
        if (extensionData != null && !allowUnexpectedServerExtension(extensionType, extensionData)) {
            throw new TlsFatalAlert(47);
        }
    }

    public void init(TlsClientContext context2) {
        this.context = context2;
    }

    public TlsSession getSessionToResume() {
        return null;
    }

    public ProtocolVersion getClientHelloRecordLayerVersion() {
        return getClientVersion();
    }

    public ProtocolVersion getClientVersion() {
        return ProtocolVersion.TLSv12;
    }

    public boolean isFallback() {
        return false;
    }

    public Hashtable getClientExtensions() throws IOException {
        Hashtable clientExtensions = null;
        if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(this.context.getClientVersion())) {
            this.supportedSignatureAlgorithms = TlsUtils.getDefaultSupportedSignatureAlgorithms();
            clientExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(null);
            TlsUtils.addSignatureAlgorithmsExtension(clientExtensions, this.supportedSignatureAlgorithms);
        }
        if (!TlsECCUtils.containsECCCipherSuites(getCipherSuites())) {
            return clientExtensions;
        }
        this.namedCurves = new int[]{23, 24};
        this.clientECPointFormats = new short[]{0, 1, 2};
        Hashtable clientExtensions2 = TlsExtensionsUtils.ensureExtensionsInitialised(clientExtensions);
        TlsECCUtils.addSupportedEllipticCurvesExtension(clientExtensions2, this.namedCurves);
        TlsECCUtils.addSupportedPointFormatsExtension(clientExtensions2, this.clientECPointFormats);
        return clientExtensions2;
    }

    public ProtocolVersion getMinimumVersion() {
        return ProtocolVersion.TLSv10;
    }

    public void notifyServerVersion(ProtocolVersion serverVersion) throws IOException {
        if (!getMinimumVersion().isEqualOrEarlierVersionOf(serverVersion)) {
            throw new TlsFatalAlert(70);
        }
    }

    public short[] getCompressionMethods() {
        return new short[]{0};
    }

    public void notifySessionID(byte[] sessionID) {
    }

    public void notifySelectedCipherSuite(int selectedCipherSuite2) {
        this.selectedCipherSuite = selectedCipherSuite2;
    }

    public void notifySelectedCompressionMethod(short selectedCompressionMethod2) {
        this.selectedCompressionMethod = selectedCompressionMethod2;
    }

    public void processServerExtensions(Hashtable serverExtensions) throws IOException {
        if (serverExtensions != null) {
            checkForUnexpectedServerExtension(serverExtensions, TlsUtils.EXT_signature_algorithms);
            checkForUnexpectedServerExtension(serverExtensions, TlsECCUtils.EXT_elliptic_curves);
            if (TlsECCUtils.isECCCipherSuite(this.selectedCipherSuite)) {
                this.serverECPointFormats = TlsECCUtils.getSupportedPointFormatsExtension(serverExtensions);
            } else {
                checkForUnexpectedServerExtension(serverExtensions, TlsECCUtils.EXT_ec_point_formats);
            }
        }
    }

    public void processServerSupplementalData(Vector serverSupplementalData) throws IOException {
        if (serverSupplementalData != null) {
            throw new TlsFatalAlert(10);
        }
    }

    public Vector getClientSupplementalData() throws IOException {
        return null;
    }

    public TlsCompression getCompression() throws IOException {
        switch (this.selectedCompressionMethod) {
            case 0:
                return new TlsNullCompression();
            default:
                throw new TlsFatalAlert(80);
        }
    }

    public TlsCipher getCipher() throws IOException {
        return this.cipherFactory.createCipher(this.context, TlsUtils.getEncryptionAlgorithm(this.selectedCipherSuite), TlsUtils.getMACAlgorithm(this.selectedCipherSuite));
    }

    public void notifyNewSessionTicket(NewSessionTicket newSessionTicket) throws IOException {
    }
}
