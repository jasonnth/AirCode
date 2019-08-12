package org.spongycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.PKIXParameters;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Set;
import org.spongycastle.jcajce.PKIXExtendedParameters;
import org.spongycastle.jcajce.PKIXExtendedParameters.Builder;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.util.Selector;
import org.spongycastle.x509.ExtendedPKIXParameters;
import org.spongycastle.x509.X509AttributeCertStoreSelector;
import org.spongycastle.x509.X509AttributeCertificate;

public class PKIXAttrCertPathValidatorSpi extends CertPathValidatorSpi {
    private final JcaJceHelper helper = new BCJcaJceHelper();

    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters params) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters paramsPKIX;
        if ((params instanceof ExtendedPKIXParameters) || (params instanceof PKIXExtendedParameters)) {
            Set attrCertCheckers = new HashSet();
            Set prohibitedACAttrbiutes = new HashSet();
            Set necessaryACAttributes = new HashSet();
            HashSet hashSet = new HashSet();
            if (params instanceof PKIXParameters) {
                Builder paramsPKIXBldr = new Builder((PKIXParameters) params);
                if (params instanceof ExtendedPKIXParameters) {
                    ExtendedPKIXParameters extPKIX = (ExtendedPKIXParameters) params;
                    paramsPKIXBldr.setUseDeltasEnabled(extPKIX.isUseDeltasEnabled());
                    paramsPKIXBldr.setValidityModel(extPKIX.getValidityModel());
                    attrCertCheckers = extPKIX.getAttrCertCheckers();
                    prohibitedACAttrbiutes = extPKIX.getProhibitedACAttributes();
                    necessaryACAttributes = extPKIX.getNecessaryACAttributes();
                }
                paramsPKIX = paramsPKIXBldr.build();
            } else {
                paramsPKIX = (PKIXExtendedParameters) params;
            }
            Selector certSelect = paramsPKIX.getTargetConstraints();
            if (!(certSelect instanceof X509AttributeCertStoreSelector)) {
                throw new InvalidAlgorithmParameterException("TargetConstraints must be an instance of " + X509AttributeCertStoreSelector.class.getName() + " for " + getClass().getName() + " class.");
            }
            X509AttributeCertificate attrCert = ((X509AttributeCertStoreSelector) certSelect).getAttributeCert();
            CertPath holderCertPath = RFC3281CertPathUtilities.processAttrCert1(attrCert, paramsPKIX);
            CertPathValidatorResult result = RFC3281CertPathUtilities.processAttrCert2(certPath, paramsPKIX);
            X509Certificate issuerCert = (X509Certificate) certPath.getCertificates().get(0);
            RFC3281CertPathUtilities.processAttrCert3(issuerCert, paramsPKIX);
            RFC3281CertPathUtilities.processAttrCert4(issuerCert, hashSet);
            RFC3281CertPathUtilities.processAttrCert5(attrCert, paramsPKIX);
            RFC3281CertPathUtilities.processAttrCert7(attrCert, certPath, holderCertPath, paramsPKIX, attrCertCheckers);
            RFC3281CertPathUtilities.additionalChecks(attrCert, prohibitedACAttrbiutes, necessaryACAttributes);
            try {
                RFC3281CertPathUtilities.checkCRLs(attrCert, paramsPKIX, issuerCert, CertPathValidatorUtilities.getValidCertDateFromValidityModel(paramsPKIX, null, -1), certPath.getCertificates(), this.helper);
                return result;
            } catch (AnnotatedException e) {
                throw new ExtCertPathValidatorException("Could not get validity date from attribute certificate.", e);
            }
        } else {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + ExtendedPKIXParameters.class.getName() + " instance.");
        }
    }
}
