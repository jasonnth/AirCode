package org.ejbca.cvc;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;

public class CardVerifiableCertificate extends Certificate {
    private CVCertificate cvc;

    public CardVerifiableCertificate(CVCertificate cVCertificate) {
        super("CVC");
        this.cvc = cVCertificate;
    }

    public CVCertificate getCVCertificate() {
        return this.cvc;
    }

    public byte[] getEncoded() throws CertificateEncodingException {
        try {
            return this.cvc.getDEREncoded();
        } catch (IOException e) {
            throw new CertificateEncodingException(e);
        }
    }

    public PublicKey getPublicKey() {
        try {
            return this.cvc.getCertificateBody().getPublicKey();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void verify(PublicKey publicKey) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        verify(publicKey, "BC");
    }

    public void verify(PublicKey publicKey, String str) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        this.cvc.verify(publicKey, str);
    }

    public String toString() {
        return this.cvc.toString();
    }
}
