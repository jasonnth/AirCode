package org.ejbca.cvc;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import org.ejbca.cvc.exception.ConstructionException;
import org.ejbca.cvc.util.BCECUtil;

public class CVCertificate extends AbstractSequence implements Signable {
    private static CVCTagEnum[] allowedFields = {CVCTagEnum.CERTIFICATE_BODY, CVCTagEnum.SIGNATURE};
    private static final long serialVersionUID = 1;

    /* access modifiers changed from: protected */
    public CVCTagEnum[] getAllowedFields() {
        return allowedFields;
    }

    CVCertificate() {
        super(CVCTagEnum.CV_CERTIFICATE);
    }

    public CVCertificate(CVCertificateBody cVCertificateBody) throws ConstructionException {
        this();
        if (cVCertificateBody == null) {
            throw new IllegalArgumentException("body is null");
        }
        addSubfield(cVCertificateBody);
    }

    public void setSignature(byte[] bArr) throws ConstructionException {
        addSubfield(new ByteField(CVCTagEnum.SIGNATURE, bArr));
    }

    public CVCertificateBody getCertificateBody() throws NoSuchFieldException {
        return (CVCertificateBody) getSubfield(CVCTagEnum.CERTIFICATE_BODY);
    }

    public byte[] getSignature() throws NoSuchFieldException {
        return ((ByteField) getSubfield(CVCTagEnum.SIGNATURE)).getData();
    }

    public byte[] getTBS() throws ConstructionException {
        try {
            return getCertificateBody().getDEREncoded();
        } catch (IOException e) {
            throw new ConstructionException((Throwable) e);
        } catch (NoSuchFieldException e2) {
            throw new ConstructionException((Throwable) e2);
        }
    }

    public String toString() {
        return getAsText("");
    }

    public void verify(PublicKey publicKey, String str) throws CertificateException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        try {
            String algorithmName = AlgorithmUtil.getAlgorithmName(getCertificateBody().getPublicKey().getObjectIdentifier());
            Signature instance = Signature.getInstance(algorithmName, str);
            instance.initVerify(publicKey);
            instance.update(getTBS());
            if (!instance.verify(BCECUtil.convertCVCSigToX962(algorithmName, getSignature()))) {
                throw new SignatureException("Signature verification failed!");
            }
        } catch (NoSuchFieldException e) {
            throw new CertificateException("CV-Certificate is corrupt", e);
        } catch (ConstructionException e2) {
            throw new CertificateException("CV-Certificate is corrupt", e2);
        }
    }
}
