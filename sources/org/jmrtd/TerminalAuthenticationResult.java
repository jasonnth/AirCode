package org.jmrtd;

import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.jmrtd.cert.CVCPrincipal;
import org.jmrtd.cert.CardVerifiableCertificate;

public class TerminalAuthenticationResult {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private CVCPrincipal caReference;
    private byte[] cardChallenge;
    private ChipAuthenticationResult chipAuthenticationResult;
    private String documentNumber;
    private List<CardVerifiableCertificate> terminalCertificates = new ArrayList();
    private PrivateKey terminalKey;

    public TerminalAuthenticationResult(ChipAuthenticationResult chipAuthenticationResult2, CVCPrincipal cVCPrincipal, List<CardVerifiableCertificate> list, PrivateKey privateKey, String str, byte[] bArr) {
        this.chipAuthenticationResult = chipAuthenticationResult2;
        this.caReference = cVCPrincipal;
        for (CardVerifiableCertificate add : list) {
            this.terminalCertificates.add(add);
        }
        this.terminalKey = privateKey;
        this.documentNumber = str;
        this.cardChallenge = bArr;
    }

    public ChipAuthenticationResult getChipAuthenticationResult() {
        return this.chipAuthenticationResult;
    }

    public CVCPrincipal getCAReference() {
        return this.caReference;
    }

    public List<CardVerifiableCertificate> getCVCertificates() {
        return this.terminalCertificates;
    }

    public PrivateKey getTerminalKey() {
        return this.terminalKey;
    }

    public String getDocumentNumber() {
        return this.documentNumber;
    }

    public byte[] getCardChallenge() {
        return this.cardChallenge;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("EACEvent [chipAuthenticationResult = " + this.chipAuthenticationResult + ", ");
        stringBuffer.append("caReference = " + this.caReference + ", ");
        for (CardVerifiableCertificate holderReference : this.terminalCertificates) {
            try {
                CVCPrincipal holderReference2 = holderReference.getHolderReference();
                if (!this.caReference.equals(holderReference2)) {
                    stringBuffer.append("holderReference = " + holderReference2 + ", ");
                }
            } catch (CertificateException e) {
                stringBuffer.append("holderReference = ???, ");
                LOGGER.severe("Exception: " + e.getMessage());
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
