package org.ejbca.cvc;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
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

public class CVCAuthenticatedRequest extends AbstractSequence implements Signable {
    private static CVCTagEnum[] allowedFields = {CVCTagEnum.CV_CERTIFICATE, CVCTagEnum.CA_REFERENCE, CVCTagEnum.SIGNATURE};
    private static final long serialVersionUID = 1;

    /* access modifiers changed from: protected */
    public CVCTagEnum[] getAllowedFields() {
        return allowedFields;
    }

    CVCAuthenticatedRequest() {
        super(CVCTagEnum.REQ_AUTHENTICATION);
    }

    public CVCAuthenticatedRequest(CVCertificate cVCertificate, CAReferenceField cAReferenceField) throws ConstructionException {
        this();
        addSubfield(cVCertificate);
        addSubfield(cAReferenceField);
    }

    public void setSignature(byte[] bArr) throws ConstructionException {
        addSubfield(new ByteField(CVCTagEnum.SIGNATURE, bArr));
    }

    public byte[] getTBS() throws ConstructionException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            getRequest().encode(dataOutputStream);
            getAuthorityReference().encode(dataOutputStream);
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (NoSuchFieldException e) {
            throw new ConstructionException((Throwable) e);
        } catch (IOException e2) {
            throw new ConstructionException((Throwable) e2);
        }
    }

    public CVCertificate getRequest() throws NoSuchFieldException {
        return (CVCertificate) getSubfield(CVCTagEnum.CV_CERTIFICATE);
    }

    public CAReferenceField getAuthorityReference() throws NoSuchFieldException {
        return (CAReferenceField) getSubfield(CVCTagEnum.CA_REFERENCE);
    }

    public byte[] getSignature() throws NoSuchFieldException {
        return ((ByteField) getSubfield(CVCTagEnum.SIGNATURE)).getData();
    }

    public void verify(PublicKey publicKey) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        String algorithmName;
        String str = "";
        try {
            if (publicKey instanceof CVCPublicKey) {
                algorithmName = AlgorithmUtil.getAlgorithmName(((CVCPublicKey) publicKey).getObjectIdentifier());
            } else {
                algorithmName = AlgorithmUtil.getAlgorithmName(getRequest().getCertificateBody().getPublicKey().getObjectIdentifier());
            }
            Signature instance = Signature.getInstance(algorithmName);
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

    public String toString() {
        return getAsText("", true);
    }
}
