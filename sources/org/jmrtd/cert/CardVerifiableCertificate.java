package org.jmrtd.cert;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Date;
import java.util.logging.Logger;
import net.p318sf.scuba.data.Country;
import org.ejbca.cvc.AlgorithmUtil;
import org.ejbca.cvc.AuthorizationRoleEnum;
import org.ejbca.cvc.CAReferenceField;
import org.ejbca.cvc.CVCPublicKey;
import org.ejbca.cvc.CVCertificate;
import org.ejbca.cvc.CVCertificateBody;
import org.ejbca.cvc.HolderReferenceField;
import org.ejbca.cvc.exception.ConstructionException;
import org.jmrtd.cert.CVCAuthorizationTemplate.Permission;
import org.jmrtd.cert.CVCAuthorizationTemplate.Role;

public class CardVerifiableCertificate extends Certificate {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = -3585440601605666288L;
    private CVCertificate cvCertificate;
    private transient KeyFactory rsaKeyFactory;

    protected CardVerifiableCertificate(CVCertificate cVCertificate) {
        super("CVC");
        try {
            this.rsaKeyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.severe("Exception: " + e.getMessage());
        }
        this.cvCertificate = cVCertificate;
    }

    public CardVerifiableCertificate(CVCPrincipal cVCPrincipal, CVCPrincipal cVCPrincipal2, PublicKey publicKey, String str, Date date, Date date2, Role role, Permission permission, byte[] bArr) {
        super("CVC");
        try {
            CAReferenceField cAReferenceField = new CAReferenceField(cVCPrincipal.getCountry().toAlpha2Code(), cVCPrincipal.getMnemonic(), cVCPrincipal.getSeqNumber());
            HolderReferenceField holderReferenceField = new HolderReferenceField(cVCPrincipal2.getCountry().toAlpha2Code(), cVCPrincipal2.getMnemonic(), cVCPrincipal2.getSeqNumber());
            AuthorizationRoleEnum fromRole = CVCAuthorizationTemplate.fromRole(role);
            this.cvCertificate = new CVCertificate(new CVCertificateBody(cAReferenceField, org.ejbca.cvc.KeyFactory.createInstance(publicKey, str, fromRole), holderReferenceField, fromRole, CVCAuthorizationTemplate.fromPermission(permission), date, date2));
            this.cvCertificate.setSignature(bArr);
            this.cvCertificate.getTBS();
        } catch (ConstructionException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public String getSigAlgName() {
        try {
            return AlgorithmUtil.getAlgorithmName(this.cvCertificate.getCertificateBody().getPublicKey().getObjectIdentifier());
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    public String getSigAlgOID() {
        try {
            return this.cvCertificate.getCertificateBody().getPublicKey().getObjectIdentifier().getAsText();
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    public byte[] getEncoded() throws CertificateEncodingException {
        try {
            return this.cvCertificate.getDEREncoded();
        } catch (IOException e) {
            throw new CertificateEncodingException(e.getMessage());
        }
    }

    public PublicKey getPublicKey() {
        try {
            CVCPublicKey publicKey = this.cvCertificate.getCertificateBody().getPublicKey();
            if (!publicKey.getAlgorithm().equals("RSA")) {
                return publicKey;
            }
            RSAPublicKey rSAPublicKey = (RSAPublicKey) publicKey;
            try {
                return this.rsaKeyFactory.generatePublic(new RSAPublicKeySpec(rSAPublicKey.getModulus(), rSAPublicKey.getPublicExponent()));
            } catch (GeneralSecurityException e) {
                LOGGER.severe("Exception: " + e.getMessage());
                return publicKey;
            }
        } catch (NoSuchFieldException e2) {
            LOGGER.severe("Exception: " + e2.getMessage());
            return null;
        }
    }

    public String toString() {
        return this.cvCertificate.toString();
    }

    public void verify(PublicKey publicKey) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        boolean z = false;
        Provider[] providers = Security.getProviders();
        int length = providers.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            try {
                this.cvCertificate.verify(publicKey, providers[i].getName());
                z = true;
                break;
            } catch (NoSuchAlgorithmException e) {
                i++;
            }
        }
        if (!z) {
            throw new NoSuchAlgorithmException("Tried all security providers: None was able to provide this signature algorithm.");
        }
    }

    public void verify(PublicKey publicKey, String str) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        this.cvCertificate.verify(publicKey, str);
    }

    public byte[] getCertBodyData() throws CertificateException, IOException {
        try {
            return this.cvCertificate.getCertificateBody().getDEREncoded();
        } catch (NoSuchFieldException e) {
            throw new CertificateException(e.getMessage());
        }
    }

    public Date getNotBefore() throws CertificateException {
        try {
            return this.cvCertificate.getCertificateBody().getValidFrom();
        } catch (NoSuchFieldException e) {
            throw new CertificateException(e.getMessage());
        }
    }

    public Date getNotAfter() throws CertificateException {
        try {
            return this.cvCertificate.getCertificateBody().getValidTo();
        } catch (NoSuchFieldException e) {
            throw new CertificateException(e.getMessage());
        }
    }

    public CVCPrincipal getAuthorityReference() throws CertificateException {
        try {
            CAReferenceField authorityReference = this.cvCertificate.getCertificateBody().getAuthorityReference();
            return new CVCPrincipal(Country.getInstance(authorityReference.getCountry().toUpperCase()), authorityReference.getMnemonic(), authorityReference.getSequence());
        } catch (NoSuchFieldException e) {
            throw new CertificateException(e.getMessage());
        }
    }

    public CVCPrincipal getHolderReference() throws CertificateException {
        try {
            HolderReferenceField holderReference = this.cvCertificate.getCertificateBody().getHolderReference();
            return new CVCPrincipal(Country.getInstance(holderReference.getCountry().toUpperCase()), holderReference.getMnemonic(), holderReference.getSequence());
        } catch (NoSuchFieldException e) {
            throw new CertificateException(e.getMessage());
        }
    }

    public CVCAuthorizationTemplate getAuthorizationTemplate() throws CertificateException {
        try {
            return new CVCAuthorizationTemplate(this.cvCertificate.getCertificateBody().getAuthorizationTemplate());
        } catch (NoSuchFieldException e) {
            throw new CertificateException(e.getMessage());
        }
    }

    public byte[] getSignature() throws CertificateException {
        try {
            return this.cvCertificate.getSignature();
        } catch (NoSuchFieldException e) {
            throw new CertificateException(e.getMessage());
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass().equals(obj.getClass())) {
            return this.cvCertificate.equals(((CardVerifiableCertificate) obj).cvCertificate);
        }
        return false;
    }

    public int hashCode() {
        return (this.cvCertificate.hashCode() * 2) - 1030507011;
    }
}
