package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.util.Arrays;

public abstract class DTLSProtocol {
    protected final SecureRandom secureRandom;

    protected DTLSProtocol(SecureRandom secureRandom2) {
        if (secureRandom2 == null) {
            throw new IllegalArgumentException("'secureRandom' cannot be null");
        }
        this.secureRandom = secureRandom2;
    }

    /* access modifiers changed from: protected */
    public void processFinished(byte[] body, byte[] expected_verify_data) throws IOException {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        byte[] verify_data = TlsUtils.readFully(expected_verify_data.length, (InputStream) buf);
        TlsProtocol.assertEmpty(buf);
        if (!Arrays.constantTimeAreEqual(expected_verify_data, verify_data)) {
            throw new TlsFatalAlert(40);
        }
    }

    protected static void applyMaxFragmentLengthExtension(DTLSRecordLayer recordLayer, short maxFragmentLength) throws IOException {
        if (maxFragmentLength < 0) {
            return;
        }
        if (!MaxFragmentLength.isValid(maxFragmentLength)) {
            throw new TlsFatalAlert(80);
        }
        recordLayer.setPlaintextLimit(1 << (maxFragmentLength + 8));
    }

    protected static short evaluateMaxFragmentLengthExtension(boolean resumedSession, Hashtable clientExtensions, Hashtable serverExtensions, short alertDescription) throws IOException {
        short maxFragmentLength = TlsExtensionsUtils.getMaxFragmentLengthExtension(serverExtensions);
        if (maxFragmentLength < 0 || (MaxFragmentLength.isValid(maxFragmentLength) && (resumedSession || maxFragmentLength == TlsExtensionsUtils.getMaxFragmentLengthExtension(clientExtensions)))) {
            return maxFragmentLength;
        }
        throw new TlsFatalAlert(alertDescription);
    }

    protected static byte[] generateCertificate(Certificate certificate) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        certificate.encode(buf);
        return buf.toByteArray();
    }

    protected static byte[] generateSupplementalData(Vector supplementalData) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        TlsProtocol.writeSupplementalData(buf, supplementalData);
        return buf.toByteArray();
    }

    protected static void validateSelectedCipherSuite(int selectedCipherSuite, short alertDescription) throws IOException {
        switch (TlsUtils.getEncryptionAlgorithm(selectedCipherSuite)) {
            case 1:
            case 2:
                throw new TlsFatalAlert(alertDescription);
            default:
                return;
        }
    }
}
