package org.spongycastle.jce.provider;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.x509.CRLDistPoint;
import org.spongycastle.asn1.x509.DistributionPoint;
import org.spongycastle.asn1.x509.DistributionPointName;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.X509Extensions;
import org.spongycastle.jcajce.PKIXCRLStore;
import org.spongycastle.jcajce.PKIXCertStoreSelector;
import org.spongycastle.jcajce.PKIXExtendedBuilderParameters;
import org.spongycastle.jcajce.PKIXExtendedParameters;
import org.spongycastle.jcajce.PKIXExtendedParameters.Builder;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.x509.X509AttributeCertificate;
import org.spongycastle.x509.X509CertStoreSelector;

class RFC3281CertPathUtilities {
    private static final String AUTHORITY_INFO_ACCESS = X509Extensions.AuthorityInfoAccess.getId();
    private static final String CRL_DISTRIBUTION_POINTS = X509Extensions.CRLDistributionPoints.getId();
    private static final String NO_REV_AVAIL = X509Extensions.NoRevAvail.getId();
    private static final String TARGET_INFORMATION = X509Extensions.TargetInformation.getId();

    RFC3281CertPathUtilities() {
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<org.spongycastle.x509.PKIXAttrCertChecker>, for r10v0, types: [java.util.Set, java.util.Set<org.spongycastle.x509.PKIXAttrCertChecker>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void processAttrCert7(org.spongycastle.x509.X509AttributeCertificate r6, java.security.cert.CertPath r7, java.security.cert.CertPath r8, org.spongycastle.jcajce.PKIXExtendedParameters r9, java.util.Set<org.spongycastle.x509.PKIXAttrCertChecker> r10) throws java.security.cert.CertPathValidatorException {
        /*
            java.util.Set r2 = r6.getCriticalExtensionOIDs()
            java.lang.String r3 = TARGET_INFORMATION
            boolean r3 = r2.contains(r3)
            if (r3 == 0) goto L_0x0015
            java.lang.String r3 = TARGET_INFORMATION     // Catch:{ AnnotatedException -> 0x002e, IllegalArgumentException -> 0x0038 }
            org.spongycastle.asn1.ASN1Primitive r3 = org.spongycastle.jce.provider.CertPathValidatorUtilities.getExtensionValue(r6, r3)     // Catch:{ AnnotatedException -> 0x002e, IllegalArgumentException -> 0x0038 }
            org.spongycastle.asn1.x509.TargetInformation.getInstance(r3)     // Catch:{ AnnotatedException -> 0x002e, IllegalArgumentException -> 0x0038 }
        L_0x0015:
            java.lang.String r3 = TARGET_INFORMATION
            r2.remove(r3)
            java.util.Iterator r1 = r10.iterator()
        L_0x001e:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0042
            java.lang.Object r3 = r1.next()
            org.spongycastle.x509.PKIXAttrCertChecker r3 = (org.spongycastle.x509.PKIXAttrCertChecker) r3
            r3.check(r6, r7, r8, r2)
            goto L_0x001e
        L_0x002e:
            r0 = move-exception
            org.spongycastle.jce.exception.ExtCertPathValidatorException r3 = new org.spongycastle.jce.exception.ExtCertPathValidatorException
            java.lang.String r4 = "Target information extension could not be read."
            r3.<init>(r4, r0)
            throw r3
        L_0x0038:
            r0 = move-exception
            org.spongycastle.jce.exception.ExtCertPathValidatorException r3 = new org.spongycastle.jce.exception.ExtCertPathValidatorException
            java.lang.String r4 = "Target information extension could not be read."
            r3.<init>(r4, r0)
            throw r3
        L_0x0042:
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L_0x0062
            java.security.cert.CertPathValidatorException r3 = new java.security.cert.CertPathValidatorException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Attribute certificate contains unsupported critical extensions: "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r4 = r4.toString()
            r3.<init>(r4)
            throw r3
        L_0x0062:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.RFC3281CertPathUtilities.processAttrCert7(org.spongycastle.x509.X509AttributeCertificate, java.security.cert.CertPath, java.security.cert.CertPath, org.spongycastle.jcajce.PKIXExtendedParameters, java.util.Set):void");
    }

    protected static void checkCRLs(X509AttributeCertificate attrCert, PKIXExtendedParameters paramsPKIX, X509Certificate issuerCert, Date validDate, List certPathCerts, JcaJceHelper helper) throws CertPathValidatorException {
        if (paramsPKIX.isRevocationEnabled()) {
            if (attrCert.getExtensionValue(NO_REV_AVAIL) == null) {
                try {
                    CRLDistPoint crldp = CRLDistPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(attrCert, CRL_DISTRIBUTION_POINTS));
                    ArrayList arrayList = new ArrayList();
                    try {
                        arrayList.addAll(CertPathValidatorUtilities.getAdditionalStoresFromCRLDistributionPoint(crldp, paramsPKIX.getNamedCRLStoreMap()));
                        Builder bldr = new Builder(paramsPKIX);
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            bldr.addCRLStore((PKIXCRLStore) arrayList);
                        }
                        PKIXExtendedParameters paramsPKIX2 = bldr.build();
                        CertStatus certStatus = new CertStatus();
                        ReasonsMask reasonsMask = new ReasonsMask();
                        AnnotatedException lastException = null;
                        boolean validCrlFound = false;
                        if (crldp != null) {
                            try {
                                DistributionPoint[] dps = crldp.getDistributionPoints();
                                int i = 0;
                                while (i < dps.length && certStatus.getCertStatus() == 11 && !reasonsMask.isAllReasons()) {
                                    try {
                                        checkCRL(dps[i], attrCert, (PKIXExtendedParameters) paramsPKIX2.clone(), validDate, issuerCert, certStatus, reasonsMask, certPathCerts, helper);
                                        validCrlFound = true;
                                        i++;
                                    } catch (AnnotatedException e) {
                                        lastException = new AnnotatedException("No valid CRL for distribution point found.", e);
                                    }
                                }
                            } catch (Exception e2) {
                                throw new ExtCertPathValidatorException("Distribution points could not be read.", e2);
                            }
                        }
                        if (certStatus.getCertStatus() == 11 && !reasonsMask.isAllReasons()) {
                            try {
                                checkCRL(new DistributionPoint(new DistributionPointName(0, new GeneralNames(new GeneralName(4, (ASN1Encodable) new ASN1InputStream(((X500Principal) attrCert.getIssuer().getPrincipals()[0]).getEncoded()).readObject()))), null, null), attrCert, (PKIXExtendedParameters) paramsPKIX2.clone(), validDate, issuerCert, certStatus, reasonsMask, certPathCerts, helper);
                                validCrlFound = true;
                            } catch (Exception e3) {
                                throw new AnnotatedException("Issuer from certificate for CRL could not be reencoded.", e3);
                            } catch (AnnotatedException e4) {
                                lastException = new AnnotatedException("No valid CRL for distribution point found.", e4);
                            }
                        }
                        if (!validCrlFound) {
                            throw new ExtCertPathValidatorException("No valid CRL found.", lastException);
                        } else if (certStatus.getCertStatus() != 11) {
                            throw new CertPathValidatorException(("Attribute certificate revocation after " + certStatus.getRevocationDate()) + ", reason: " + RFC3280CertPathUtilities.crlReasons[certStatus.getCertStatus()]);
                        } else {
                            if (!reasonsMask.isAllReasons() && certStatus.getCertStatus() == 11) {
                                certStatus.setCertStatus(12);
                            }
                            if (certStatus.getCertStatus() == 12) {
                                throw new CertPathValidatorException("Attribute certificate status could not be determined.");
                            }
                        }
                    } catch (AnnotatedException e5) {
                        throw new CertPathValidatorException("No additional CRL locations could be decoded from CRL distribution point extension.", e5);
                    }
                } catch (AnnotatedException e6) {
                    throw new CertPathValidatorException("CRL distribution point extension could not be read.", e6);
                }
            } else {
                if (attrCert.getExtensionValue(CRL_DISTRIBUTION_POINTS) == null) {
                    if (attrCert.getExtensionValue(AUTHORITY_INFO_ACCESS) == null) {
                        return;
                    }
                }
                throw new CertPathValidatorException("No rev avail extension is set, but also an AC revocation pointer.");
            }
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.String>, for r6v0, types: [java.util.Set, java.util.Set<java.lang.String>] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.String>, for r7v0, types: [java.util.Set, java.util.Set<java.lang.String>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void additionalChecks(org.spongycastle.x509.X509AttributeCertificate r5, java.util.Set<java.lang.String> r6, java.util.Set<java.lang.String> r7) throws java.security.cert.CertPathValidatorException {
        /*
            java.util.Iterator r0 = r6.iterator()
        L_0x0004:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0037
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            org.spongycastle.x509.X509Attribute[] r2 = r5.getAttributes(r1)
            if (r2 == 0) goto L_0x0004
            java.security.cert.CertPathValidatorException r2 = new java.security.cert.CertPathValidatorException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Attribute certificate contains prohibited attribute: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r4 = "."
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L_0x0037:
            java.util.Iterator r0 = r7.iterator()
        L_0x003b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x006e
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            org.spongycastle.x509.X509Attribute[] r2 = r5.getAttributes(r1)
            if (r2 != 0) goto L_0x003b
            java.security.cert.CertPathValidatorException r2 = new java.security.cert.CertPathValidatorException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Attribute certificate does not contain necessary attribute: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r1)
            java.lang.String r4 = "."
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L_0x006e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.RFC3281CertPathUtilities.additionalChecks(org.spongycastle.x509.X509AttributeCertificate, java.util.Set, java.util.Set):void");
    }

    protected static void processAttrCert5(X509AttributeCertificate attrCert, PKIXExtendedParameters pkixParams) throws CertPathValidatorException {
        try {
            attrCert.checkValidity(CertPathValidatorUtilities.getValidDate(pkixParams));
        } catch (CertificateExpiredException e) {
            throw new ExtCertPathValidatorException("Attribute certificate is not valid.", e);
        } catch (CertificateNotYetValidException e2) {
            throw new ExtCertPathValidatorException("Attribute certificate is not valid.", e2);
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.security.cert.TrustAnchor>, for r7v0, types: [java.util.Set<java.security.cert.TrustAnchor>, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void processAttrCert4(java.security.cert.X509Certificate r6, java.util.Set<java.security.cert.TrustAnchor> r7) throws java.security.cert.CertPathValidatorException {
        /*
            r2 = r7
            r3 = 0
            java.util.Iterator r1 = r2.iterator()
        L_0x0006:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0033
            java.lang.Object r0 = r1.next()
            java.security.cert.TrustAnchor r0 = (java.security.cert.TrustAnchor) r0
            javax.security.auth.x500.X500Principal r4 = r6.getSubjectX500Principal()
            java.lang.String r5 = "RFC2253"
            java.lang.String r4 = r4.getName(r5)
            java.lang.String r5 = r0.getCAName()
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0031
            java.security.cert.X509Certificate r4 = r0.getTrustedCert()
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L_0x0006
        L_0x0031:
            r3 = 1
            goto L_0x0006
        L_0x0033:
            if (r3 != 0) goto L_0x003e
            java.security.cert.CertPathValidatorException r4 = new java.security.cert.CertPathValidatorException
            java.lang.String r5 = "Attribute certificate issuer is not directly trusted."
            r4.<init>(r5)
            throw r4
        L_0x003e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.RFC3281CertPathUtilities.processAttrCert4(java.security.cert.X509Certificate, java.util.Set):void");
    }

    protected static void processAttrCert3(X509Certificate acIssuerCert, PKIXExtendedParameters pkixParams) throws CertPathValidatorException {
        if (acIssuerCert.getKeyUsage() != null && !acIssuerCert.getKeyUsage()[0] && !acIssuerCert.getKeyUsage()[1]) {
            throw new CertPathValidatorException("Attribute certificate issuer public key cannot be used to validate digital signatures.");
        } else if (acIssuerCert.getBasicConstraints() != -1) {
            throw new CertPathValidatorException("Attribute certificate issuer is also a public key certificate issuer.");
        }
    }

    protected static CertPathValidatorResult processAttrCert2(CertPath certPath, PKIXExtendedParameters pkixParams) throws CertPathValidatorException {
        try {
            try {
                return CertPathValidator.getInstance("PKIX", BouncyCastleProvider.PROVIDER_NAME).validate(certPath, pkixParams);
            } catch (CertPathValidatorException e) {
                throw new ExtCertPathValidatorException("Certification path for issuer certificate of attribute certificate could not be validated.", e);
            } catch (InvalidAlgorithmParameterException e2) {
                throw new RuntimeException(e2.getMessage());
            }
        } catch (NoSuchProviderException e3) {
            throw new ExtCertPathValidatorException("Support class could not be created.", e3);
        } catch (NoSuchAlgorithmException e4) {
            throw new ExtCertPathValidatorException("Support class could not be created.", e4);
        }
    }

    protected static CertPath processAttrCert1(X509AttributeCertificate attrCert, PKIXExtendedParameters pkixParams) throws CertPathValidatorException {
        CertPathBuilderResult result = null;
        Set<X509Certificate> holderPKCs = new HashSet<>();
        if (attrCert.getHolder().getIssuer() != null) {
            X509CertSelector selector = new X509CertSelector();
            selector.setSerialNumber(attrCert.getHolder().getSerialNumber());
            Principal[] principals = attrCert.getHolder().getIssuer();
            int i = 0;
            while (i < principals.length) {
                try {
                    if (principals[i] instanceof X500Principal) {
                        selector.setIssuer(((X500Principal) principals[i]).getEncoded());
                    }
                    holderPKCs.addAll(CertPathValidatorUtilities.findCertificates(new PKIXCertStoreSelector.Builder(selector).build(), pkixParams.getCertStores()));
                    i++;
                } catch (AnnotatedException e) {
                    throw new ExtCertPathValidatorException("Public key certificate for attribute certificate cannot be searched.", e);
                } catch (IOException e2) {
                    throw new ExtCertPathValidatorException("Unable to encode X500 principal.", e2);
                }
            }
            if (holderPKCs.isEmpty()) {
                throw new CertPathValidatorException("Public key certificate specified in base certificate ID for attribute certificate cannot be found.");
            }
        }
        if (attrCert.getHolder().getEntityNames() != null) {
            X509CertStoreSelector selector2 = new X509CertStoreSelector();
            Principal[] principals2 = attrCert.getHolder().getEntityNames();
            int i2 = 0;
            while (i2 < principals2.length) {
                try {
                    if (principals2[i2] instanceof X500Principal) {
                        selector2.setIssuer(((X500Principal) principals2[i2]).getEncoded());
                    }
                    holderPKCs.addAll(CertPathValidatorUtilities.findCertificates(new PKIXCertStoreSelector.Builder(selector2).build(), pkixParams.getCertStores()));
                    i2++;
                } catch (AnnotatedException e3) {
                    throw new ExtCertPathValidatorException("Public key certificate for attribute certificate cannot be searched.", e3);
                } catch (IOException e4) {
                    throw new ExtCertPathValidatorException("Unable to encode X500 principal.", e4);
                }
            }
            if (holderPKCs.isEmpty()) {
                throw new CertPathValidatorException("Public key certificate specified in entity name for attribute certificate cannot be found.");
            }
        }
        Builder paramsBldr = new Builder(pkixParams);
        CertPathValidatorException lastException = null;
        for (X509Certificate certificate : holderPKCs) {
            X509CertStoreSelector selector3 = new X509CertStoreSelector();
            selector3.setCertificate(certificate);
            paramsBldr.setTargetConstraints(new PKIXCertStoreSelector.Builder(selector3).build());
            try {
                try {
                    result = CertPathBuilder.getInstance("PKIX", BouncyCastleProvider.PROVIDER_NAME).build(new PKIXExtendedBuilderParameters.Builder(paramsBldr.build()).build());
                } catch (CertPathBuilderException e5) {
                    lastException = new ExtCertPathValidatorException("Certification path for public key certificate of attribute certificate could not be build.", e5);
                } catch (InvalidAlgorithmParameterException e6) {
                    throw new RuntimeException(e6.getMessage());
                }
            } catch (NoSuchProviderException e7) {
                throw new ExtCertPathValidatorException("Support class could not be created.", e7);
            } catch (NoSuchAlgorithmException e8) {
                throw new ExtCertPathValidatorException("Support class could not be created.", e8);
            }
        }
        if (lastException == null) {
            return result.getCertPath();
        }
        throw lastException;
    }

    private static void checkCRL(DistributionPoint dp, X509AttributeCertificate attrCert, PKIXExtendedParameters paramsPKIX, Date validDate, X509Certificate issuerCert, CertStatus certStatus, ReasonsMask reasonMask, List certPathCerts, JcaJceHelper helper) throws AnnotatedException {
        if (attrCert.getExtensionValue(X509Extensions.NoRevAvail.getId()) == null) {
            Date currentDate = new Date(System.currentTimeMillis());
            if (validDate.getTime() > currentDate.getTime()) {
                throw new AnnotatedException("Validation time is in future.");
            }
            boolean validCrlFound = false;
            AnnotatedException lastException = null;
            Iterator crl_iter = CertPathValidatorUtilities.getCompleteCRLs(dp, attrCert, currentDate, paramsPKIX).iterator();
            while (crl_iter.hasNext() && certStatus.getCertStatus() == 11 && !reasonMask.isAllReasons()) {
                try {
                    X509CRL crl = (X509CRL) crl_iter.next();
                    ReasonsMask interimReasonsMask = RFC3280CertPathUtilities.processCRLD(crl, dp);
                    if (interimReasonsMask.hasNewReasons(reasonMask)) {
                        PublicKey key = RFC3280CertPathUtilities.processCRLG(crl, RFC3280CertPathUtilities.processCRLF(crl, attrCert, null, null, paramsPKIX, certPathCerts, helper));
                        X509CRL deltaCRL = null;
                        if (paramsPKIX.isUseDeltasEnabled()) {
                            deltaCRL = RFC3280CertPathUtilities.processCRLH(CertPathValidatorUtilities.getDeltaCRLs(currentDate, crl, paramsPKIX.getCertStores(), paramsPKIX.getCRLStores()), key);
                        }
                        if (paramsPKIX.getValidityModel() == 1 || attrCert.getNotAfter().getTime() >= crl.getThisUpdate().getTime()) {
                            RFC3280CertPathUtilities.processCRLB1(dp, attrCert, crl);
                            RFC3280CertPathUtilities.processCRLB2(dp, attrCert, crl);
                            RFC3280CertPathUtilities.processCRLC(deltaCRL, crl, paramsPKIX);
                            RFC3280CertPathUtilities.processCRLI(validDate, deltaCRL, attrCert, certStatus, paramsPKIX);
                            RFC3280CertPathUtilities.processCRLJ(validDate, crl, attrCert, certStatus);
                            if (certStatus.getCertStatus() == 8) {
                                certStatus.setCertStatus(11);
                            }
                            reasonMask.addReasons(interimReasonsMask);
                            validCrlFound = true;
                        } else {
                            throw new AnnotatedException("No valid CRL for current time found.");
                        }
                    } else {
                        continue;
                    }
                } catch (AnnotatedException e) {
                    lastException = e;
                }
            }
            if (!validCrlFound) {
                throw lastException;
            }
        }
    }
}
