package org.spongycastle.jce.provider;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Principal;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidator;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.jcajce.PKIXCertStoreSelector;
import org.spongycastle.jcajce.PKIXExtendedBuilderParameters;
import org.spongycastle.jcajce.PKIXExtendedBuilderParameters.Builder;
import org.spongycastle.jce.exception.ExtCertPathBuilderException;
import org.spongycastle.util.Selector;
import org.spongycastle.util.Store;
import org.spongycastle.util.StoreException;
import org.spongycastle.x509.ExtendedPKIXBuilderParameters;
import org.spongycastle.x509.ExtendedPKIXParameters;
import org.spongycastle.x509.X509AttributeCertStoreSelector;
import org.spongycastle.x509.X509AttributeCertificate;
import org.spongycastle.x509.X509CertStoreSelector;

public class PKIXAttrCertPathBuilderSpi extends CertPathBuilderSpi {
    private Exception certPathException;

    public CertPathBuilderResult engineBuild(CertPathParameters params) throws CertPathBuilderException, InvalidAlgorithmParameterException {
        PKIXExtendedBuilderParameters paramsPKIX;
        if ((params instanceof PKIXBuilderParameters) || (params instanceof ExtendedPKIXBuilderParameters) || (params instanceof PKIXExtendedBuilderParameters)) {
            List targetStores = new ArrayList();
            if (params instanceof PKIXBuilderParameters) {
                Builder paramsPKIXBldr = new Builder((PKIXBuilderParameters) params);
                if (params instanceof ExtendedPKIXParameters) {
                    ExtendedPKIXBuilderParameters extPKIX = (ExtendedPKIXBuilderParameters) params;
                    paramsPKIXBldr.addExcludedCerts(extPKIX.getExcludedCerts());
                    paramsPKIXBldr.setMaxPathLength(extPKIX.getMaxPathLength());
                    targetStores = extPKIX.getStores();
                }
                paramsPKIX = paramsPKIXBldr.build();
            } else {
                paramsPKIX = (PKIXExtendedBuilderParameters) params;
            }
            List certPathList = new ArrayList();
            Selector certSelect = paramsPKIX.getBaseParameters().getTargetConstraints();
            if (!(certSelect instanceof X509AttributeCertStoreSelector)) {
                throw new CertPathBuilderException("TargetConstraints must be an instance of " + X509AttributeCertStoreSelector.class.getName() + " for " + getClass().getName() + " class.");
            }
            try {
                Collection targets = findCertificates((X509AttributeCertStoreSelector) certSelect, targetStores);
                if (targets.isEmpty()) {
                    throw new CertPathBuilderException("No attribute certificate found matching targetContraints.");
                }
                CertPathBuilderResult result = null;
                Iterator targetIter = targets.iterator();
                while (targetIter.hasNext() && result == null) {
                    X509AttributeCertificate cert = (X509AttributeCertificate) targetIter.next();
                    X509CertStoreSelector selector = new X509CertStoreSelector();
                    Principal[] principals = cert.getIssuer().getPrincipals();
                    Set issuers = new HashSet();
                    int i = 0;
                    while (i < principals.length) {
                        try {
                            if (principals[i] instanceof X500Principal) {
                                selector.setSubject(((X500Principal) principals[i]).getEncoded());
                            }
                            PKIXCertStoreSelector.Builder builder = new PKIXCertStoreSelector.Builder(selector);
                            PKIXCertStoreSelector certStoreSelector = builder.build();
                            issuers.addAll(CertPathValidatorUtilities.findCertificates(certStoreSelector, paramsPKIX.getBaseParameters().getCertStores()));
                            issuers.addAll(CertPathValidatorUtilities.findCertificates(certStoreSelector, paramsPKIX.getBaseParameters().getCertificateStores()));
                            i++;
                        } catch (AnnotatedException e) {
                            ExtCertPathBuilderException extCertPathBuilderException = new ExtCertPathBuilderException("Public key certificate for attribute certificate cannot be searched.", e);
                            throw extCertPathBuilderException;
                        } catch (IOException e2) {
                            ExtCertPathBuilderException extCertPathBuilderException2 = new ExtCertPathBuilderException("cannot encode X500Principal.", e2);
                            throw extCertPathBuilderException2;
                        }
                    }
                    if (issuers.isEmpty()) {
                        throw new CertPathBuilderException("Public key certificate for attribute certificate cannot be found.");
                    }
                    Iterator it = issuers.iterator();
                    while (it.hasNext() && result == null) {
                        result = build(cert, (X509Certificate) it.next(), paramsPKIX, certPathList);
                    }
                }
                if (result == null && this.certPathException != null) {
                    throw new ExtCertPathBuilderException("Possible certificate chain could not be validated.", this.certPathException);
                } else if (result != null || this.certPathException != null) {
                    return result;
                } else {
                    throw new CertPathBuilderException("Unable to find certificate chain.");
                }
            } catch (AnnotatedException e3) {
                ExtCertPathBuilderException extCertPathBuilderException3 = new ExtCertPathBuilderException("Error finding target attribute certificate.", e3);
                throw extCertPathBuilderException3;
            }
        } else {
            throw new InvalidAlgorithmParameterException("Parameters must be an instance of " + PKIXBuilderParameters.class.getName() + " or " + PKIXExtendedBuilderParameters.class.getName() + ".");
        }
    }

    private CertPathBuilderResult build(X509AttributeCertificate attrCert, X509Certificate tbvCert, PKIXExtendedBuilderParameters pkixParams, List tbvPath) {
        if (tbvPath.contains(tbvCert)) {
            return null;
        }
        if (pkixParams.getExcludedCerts().contains(tbvCert)) {
            return null;
        }
        if (pkixParams.getMaxPathLength() != -1 && tbvPath.size() - 1 > pkixParams.getMaxPathLength()) {
            return null;
        }
        tbvPath.add(tbvCert);
        CertPathBuilderResult builderResult = null;
        try {
            CertificateFactory cFact = CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME);
            CertPathValidator validator = CertPathValidator.getInstance("RFC3281", BouncyCastleProvider.PROVIDER_NAME);
            try {
                if (CertPathValidatorUtilities.findTrustAnchor(tbvCert, pkixParams.getBaseParameters().getTrustAnchors(), pkixParams.getBaseParameters().getSigProvider()) != null) {
                    CertPath certPath = cFact.generateCertPath(tbvPath);
                    PKIXCertPathValidatorResult result = (PKIXCertPathValidatorResult) validator.validate(certPath, pkixParams);
                    return new PKIXCertPathBuilderResult(certPath, result.getTrustAnchor(), result.getPolicyTree(), result.getPublicKey());
                }
                List stores = new ArrayList();
                stores.addAll(pkixParams.getBaseParameters().getCertificateStores());
                stores.addAll(CertPathValidatorUtilities.getAdditionalStoresFromAltNames(tbvCert.getExtensionValue(Extension.issuerAlternativeName.getId()), pkixParams.getBaseParameters().getNamedCertificateStoreMap()));
                Collection issuers = new HashSet();
                issuers.addAll(CertPathValidatorUtilities.findIssuerCerts(tbvCert, pkixParams.getBaseParameters().getCertStores(), stores));
                if (issuers.isEmpty()) {
                    throw new AnnotatedException("No issuer certificate for certificate in certification path found.");
                }
                Iterator it = issuers.iterator();
                while (it.hasNext() && builderResult == null) {
                    X509Certificate issuer = (X509Certificate) it.next();
                    if (!issuer.getIssuerX500Principal().equals(issuer.getSubjectX500Principal())) {
                        builderResult = build(attrCert, issuer, pkixParams, tbvPath);
                    }
                }
                if (builderResult != null) {
                    return builderResult;
                }
                tbvPath.remove(tbvCert);
                return builderResult;
            } catch (AnnotatedException e) {
                throw new AnnotatedException("Cannot find issuer certificate for certificate in certification path.", e);
            } catch (CertificateParsingException e2) {
                throw new AnnotatedException("No additional X.509 stores can be added from certificate locations.", e2);
            } catch (Exception e3) {
                throw new AnnotatedException("Certification path could not be validated.", e3);
            } catch (Exception e4) {
                throw new AnnotatedException("Certification path could not be constructed from certificate list.", e4);
            } catch (AnnotatedException e5) {
                this.certPathException = new AnnotatedException("No valid certification path could be build.", e5);
            }
        } catch (Exception e6) {
            throw new RuntimeException("Exception creating support classes.");
        }
    }

    protected static Collection findCertificates(X509AttributeCertStoreSelector certSelect, List certStores) throws AnnotatedException {
        Set certs = new HashSet();
        for (Object obj : certStores) {
            if (obj instanceof Store) {
                try {
                    certs.addAll(((Store) obj).getMatches(certSelect));
                } catch (StoreException e) {
                    throw new AnnotatedException("Problem while picking certificates from X.509 store.", e);
                }
            }
        }
        return certs;
    }
}
