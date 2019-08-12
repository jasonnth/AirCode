package org.spongycastle.jce.provider;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1String;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x500.RDN;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x500.style.BCStyle;
import org.spongycastle.asn1.x509.BasicConstraints;
import org.spongycastle.asn1.x509.CRLDistPoint;
import org.spongycastle.asn1.x509.DistributionPoint;
import org.spongycastle.asn1.x509.DistributionPointName;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.GeneralSubtree;
import org.spongycastle.asn1.x509.IssuingDistributionPoint;
import org.spongycastle.asn1.x509.NameConstraints;
import org.spongycastle.asn1.x509.PolicyInformation;
import org.spongycastle.jcajce.PKIXCRLStore;
import org.spongycastle.jcajce.PKIXCRLStoreSelector;
import org.spongycastle.jcajce.PKIXCertStoreSelector;
import org.spongycastle.jcajce.PKIXCertStoreSelector.Builder;
import org.spongycastle.jcajce.PKIXExtendedBuilderParameters;
import org.spongycastle.jcajce.PKIXExtendedParameters;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.util.Arrays;

class RFC3280CertPathUtilities {
    public static final String ANY_POLICY = "2.5.29.32.0";
    public static final String AUTHORITY_KEY_IDENTIFIER = Extension.authorityKeyIdentifier.getId();
    public static final String BASIC_CONSTRAINTS = Extension.basicConstraints.getId();
    public static final String CERTIFICATE_POLICIES = Extension.certificatePolicies.getId();
    public static final String CRL_DISTRIBUTION_POINTS = Extension.cRLDistributionPoints.getId();
    public static final String CRL_NUMBER = Extension.cRLNumber.getId();
    protected static final int CRL_SIGN = 6;
    private static final PKIXCRLUtil CRL_UTIL = new PKIXCRLUtil();
    public static final String DELTA_CRL_INDICATOR = Extension.deltaCRLIndicator.getId();
    public static final String FRESHEST_CRL = Extension.freshestCRL.getId();
    public static final String INHIBIT_ANY_POLICY = Extension.inhibitAnyPolicy.getId();
    public static final String ISSUING_DISTRIBUTION_POINT = Extension.issuingDistributionPoint.getId();
    protected static final int KEY_CERT_SIGN = 5;
    public static final String KEY_USAGE = Extension.keyUsage.getId();
    public static final String NAME_CONSTRAINTS = Extension.nameConstraints.getId();
    public static final String POLICY_CONSTRAINTS = Extension.policyConstraints.getId();
    public static final String POLICY_MAPPINGS = Extension.policyMappings.getId();
    public static final String SUBJECT_ALTERNATIVE_NAME = Extension.subjectAlternativeName.getId();
    protected static final String[] crlReasons = {"unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", "unknown", "removeFromCRL", "privilegeWithdrawn", "aACompromise"};

    RFC3280CertPathUtilities() {
    }

    protected static void processCRLB2(DistributionPoint dp, Object cert, X509CRL crl) throws AnnotatedException {
        GeneralName[] genNames;
        try {
            IssuingDistributionPoint idp = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(crl, ISSUING_DISTRIBUTION_POINT));
            if (idp != null) {
                if (idp.getDistributionPoint() != null) {
                    DistributionPointName dpName = IssuingDistributionPoint.getInstance(idp).getDistributionPoint();
                    List names = new ArrayList();
                    if (dpName.getType() == 0) {
                        GeneralName[] genNames2 = GeneralNames.getInstance(dpName.getName()).getNames();
                        for (GeneralName add : genNames2) {
                            names.add(add);
                        }
                    }
                    if (dpName.getType() == 1) {
                        ASN1EncodableVector vec = new ASN1EncodableVector();
                        try {
                            Enumeration e = ASN1Sequence.getInstance(PrincipalUtils.getIssuerPrincipal(crl)).getObjects();
                            while (e.hasMoreElements()) {
                                vec.add((ASN1Encodable) e.nextElement());
                            }
                            vec.add(dpName.getName());
                            names.add(new GeneralName(X500Name.getInstance(new DERSequence(vec))));
                        } catch (Exception e2) {
                            throw new AnnotatedException("Could not read CRL issuer.", e2);
                        }
                    }
                    boolean matches = false;
                    if (dp.getDistributionPoint() != null) {
                        DistributionPointName dpName2 = dp.getDistributionPoint();
                        GeneralName[] genNames3 = null;
                        if (dpName2.getType() == 0) {
                            genNames3 = GeneralNames.getInstance(dpName2.getName()).getNames();
                        }
                        if (dpName2.getType() == 1) {
                            if (dp.getCRLIssuer() != null) {
                                genNames = dp.getCRLIssuer().getNames();
                            } else {
                                genNames = new GeneralName[1];
                                try {
                                    genNames[0] = new GeneralName(X500Name.getInstance(PrincipalUtils.getEncodedIssuerPrincipal(cert).getEncoded()));
                                } catch (Exception e3) {
                                    throw new AnnotatedException("Could not read certificate issuer.", e3);
                                }
                            }
                            for (int j = 0; j < genNames.length; j++) {
                                Enumeration e4 = ASN1Sequence.getInstance(genNames[j].getName().toASN1Primitive()).getObjects();
                                ASN1EncodableVector vec2 = new ASN1EncodableVector();
                                while (e4.hasMoreElements()) {
                                    vec2.add((ASN1Encodable) e4.nextElement());
                                }
                                vec2.add(dpName2.getName());
                                genNames[j] = new GeneralName(X500Name.getInstance(new DERSequence(vec2)));
                            }
                        }
                        if (genNames != null) {
                            int j2 = 0;
                            while (true) {
                                if (j2 >= genNames.length) {
                                    break;
                                } else if (names.contains(genNames[j2])) {
                                    matches = true;
                                    break;
                                } else {
                                    j2++;
                                }
                            }
                        }
                        if (!matches) {
                            throw new AnnotatedException("No match for certificate CRL issuing distribution point name to cRLIssuer CRL distribution point.");
                        }
                    } else if (dp.getCRLIssuer() == null) {
                        throw new AnnotatedException("Either the cRLIssuer or the distributionPoint field must be contained in DistributionPoint.");
                    } else {
                        GeneralName[] genNames4 = dp.getCRLIssuer().getNames();
                        int j3 = 0;
                        while (true) {
                            if (j3 >= genNames4.length) {
                                break;
                            } else if (names.contains(genNames4[j3])) {
                                matches = true;
                                break;
                            } else {
                                j3++;
                            }
                        }
                        if (!matches) {
                            throw new AnnotatedException("No match for certificate CRL issuing distribution point name to cRLIssuer CRL distribution point.");
                        }
                    }
                }
                try {
                    BasicConstraints bc = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Extension) cert, BASIC_CONSTRAINTS));
                    if (cert instanceof X509Certificate) {
                        if (idp.onlyContainsUserCerts() && bc != null && bc.isCA()) {
                            throw new AnnotatedException("CA Cert CRL only contains user certificates.");
                        } else if (idp.onlyContainsCACerts() && (bc == null || !bc.isCA())) {
                            throw new AnnotatedException("End CRL only contains CA certificates.");
                        }
                    }
                    if (idp.onlyContainsAttributeCerts()) {
                        throw new AnnotatedException("onlyContainsAttributeCerts boolean is asserted.");
                    }
                } catch (Exception e5) {
                    throw new AnnotatedException("Basic constraints extension could not be decoded.", e5);
                }
            }
        } catch (Exception e6) {
            throw new AnnotatedException("Issuing distribution point extension could not be decoded.", e6);
        }
    }

    protected static void processCRLB1(DistributionPoint dp, Object cert, X509CRL crl) throws AnnotatedException {
        ASN1Primitive idp = CertPathValidatorUtilities.getExtensionValue(crl, ISSUING_DISTRIBUTION_POINT);
        boolean isIndirect = false;
        if (idp != null && IssuingDistributionPoint.getInstance(idp).isIndirectCRL()) {
            isIndirect = true;
        }
        try {
            byte[] issuerBytes = PrincipalUtils.getIssuerPrincipal(crl).getEncoded();
            boolean matchIssuer = false;
            if (dp.getCRLIssuer() != null) {
                GeneralName[] genNames = dp.getCRLIssuer().getNames();
                for (int j = 0; j < genNames.length; j++) {
                    if (genNames[j].getTagNo() == 4) {
                        try {
                            if (Arrays.areEqual(genNames[j].getName().toASN1Primitive().getEncoded(), issuerBytes)) {
                                matchIssuer = true;
                            }
                        } catch (IOException e) {
                            throw new AnnotatedException("CRL issuer information from distribution point cannot be decoded.", e);
                        }
                    }
                }
                if (matchIssuer && !isIndirect) {
                    throw new AnnotatedException("Distribution point contains cRLIssuer field but CRL is not indirect.");
                } else if (!matchIssuer) {
                    throw new AnnotatedException("CRL issuer of CRL does not match CRL issuer of distribution point.");
                }
            } else if (PrincipalUtils.getIssuerPrincipal(crl).equals(PrincipalUtils.getEncodedIssuerPrincipal(cert))) {
                matchIssuer = true;
            }
            if (!matchIssuer) {
                throw new AnnotatedException("Cannot find matching CRL issuer for certificate.");
            }
        } catch (IOException e2) {
            throw new AnnotatedException("Exception encoding CRL issuer: " + e2.getMessage(), e2);
        }
    }

    protected static ReasonsMask processCRLD(X509CRL crl, DistributionPoint dp) throws AnnotatedException {
        ReasonsMask reasonsMask;
        ReasonsMask reasonsMask2;
        try {
            IssuingDistributionPoint idp = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(crl, ISSUING_DISTRIBUTION_POINT));
            if (idp != null && idp.getOnlySomeReasons() != null && dp.getReasons() != null) {
                return new ReasonsMask(dp.getReasons()).intersect(new ReasonsMask(idp.getOnlySomeReasons()));
            }
            if ((idp == null || idp.getOnlySomeReasons() == null) && dp.getReasons() == null) {
                return ReasonsMask.allReasons;
            }
            if (dp.getReasons() == null) {
                reasonsMask = ReasonsMask.allReasons;
            } else {
                reasonsMask = new ReasonsMask(dp.getReasons());
            }
            if (idp == null) {
                reasonsMask2 = ReasonsMask.allReasons;
            } else {
                reasonsMask2 = new ReasonsMask(idp.getOnlySomeReasons());
            }
            return reasonsMask.intersect(reasonsMask2);
        } catch (Exception e) {
            throw new AnnotatedException("Issuing distribution point extension could not be decoded.", e);
        }
    }

    protected static Set processCRLF(X509CRL crl, Object cert, X509Certificate defaultCRLSignCert, PublicKey defaultCRLSignKey, PKIXExtendedParameters paramsPKIX, List certPathCerts, JcaJceHelper helper) throws AnnotatedException {
        X509CertSelector certSelector = new X509CertSelector();
        try {
            certSelector.setSubject(PrincipalUtils.getIssuerPrincipal(crl).getEncoded());
            Builder builder = new Builder(certSelector);
            PKIXCertStoreSelector selector = builder.build();
            try {
                Collection<X509Certificate> coll = CertPathValidatorUtilities.findCertificates(selector, paramsPKIX.getCertificateStores());
                coll.addAll(CertPathValidatorUtilities.findCertificates(selector, paramsPKIX.getCertStores()));
                coll.add(defaultCRLSignCert);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (X509Certificate signingCert : coll) {
                    if (signingCert.equals(defaultCRLSignCert)) {
                        arrayList.add(signingCert);
                        arrayList2.add(defaultCRLSignKey);
                    } else {
                        try {
                            PKIXCertPathBuilderSpi builder2 = new PKIXCertPathBuilderSpi();
                            X509CertSelector tmpCertSelector = new X509CertSelector();
                            tmpCertSelector.setCertificate(signingCert);
                            PKIXExtendedParameters.Builder builder3 = new PKIXExtendedParameters.Builder(paramsPKIX);
                            Builder builder4 = new Builder(tmpCertSelector);
                            PKIXExtendedParameters.Builder paramsBuilder = builder3.setTargetConstraints(builder4.build());
                            if (certPathCerts.contains(signingCert)) {
                                paramsBuilder.setRevocationEnabled(false);
                            } else {
                                paramsBuilder.setRevocationEnabled(true);
                            }
                            List certs = builder2.engineBuild(new PKIXExtendedBuilderParameters.Builder(paramsBuilder.build()).build()).getCertPath().getCertificates();
                            arrayList.add(signingCert);
                            arrayList2.add(CertPathValidatorUtilities.getNextWorkingKey(certs, 0, helper));
                        } catch (CertPathBuilderException e) {
                            AnnotatedException annotatedException = new AnnotatedException("CertPath for CRL signer failed to validate.", e);
                            throw annotatedException;
                        } catch (CertPathValidatorException e2) {
                            AnnotatedException annotatedException2 = new AnnotatedException("Public key of issuer certificate of CRL could not be retrieved.", e2);
                            throw annotatedException2;
                        } catch (Exception e3) {
                            throw new AnnotatedException(e3.getMessage());
                        }
                    }
                }
                Set checkKeys = new HashSet();
                AnnotatedException lastException = null;
                for (int i = 0; i < arrayList.size(); i++) {
                    boolean[] keyusage = ((X509Certificate) arrayList.get(i)).getKeyUsage();
                    if (keyusage == null || (keyusage.length >= 7 && keyusage[6])) {
                        checkKeys.add(arrayList2.get(i));
                    } else {
                        lastException = new AnnotatedException("Issuer certificate key usage extension does not permit CRL signing.");
                    }
                }
                if (checkKeys.isEmpty() && lastException == null) {
                    throw new AnnotatedException("Cannot find a valid issuer certificate.");
                } else if (!checkKeys.isEmpty() || lastException == null) {
                    return checkKeys;
                } else {
                    throw lastException;
                }
            } catch (AnnotatedException e4) {
                AnnotatedException annotatedException3 = new AnnotatedException("Issuer certificate for CRL cannot be searched.", e4);
                throw annotatedException3;
            }
        } catch (IOException e5) {
            AnnotatedException annotatedException4 = new AnnotatedException("Subject criteria for certificate selector to find issuer certificate for CRL could not be set.", e5);
            throw annotatedException4;
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.security.PublicKey>, for r7v0, types: [java.util.Set<java.security.PublicKey>, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.security.PublicKey processCRLG(java.security.cert.X509CRL r6, java.util.Set<java.security.PublicKey> r7) throws org.spongycastle.jce.provider.AnnotatedException {
        /*
            r3 = 0
            java.util.Iterator r1 = r7.iterator()
        L_0x0005:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0018
            java.lang.Object r2 = r1.next()
            java.security.PublicKey r2 = (java.security.PublicKey) r2
            r6.verify(r2)     // Catch:{ Exception -> 0x0015 }
            return r2
        L_0x0015:
            r0 = move-exception
            r3 = r0
            goto L_0x0005
        L_0x0018:
            org.spongycastle.jce.provider.AnnotatedException r4 = new org.spongycastle.jce.provider.AnnotatedException
            java.lang.String r5 = "Cannot verify CRL."
            r4.<init>(r5, r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.RFC3280CertPathUtilities.processCRLG(java.security.cert.X509CRL, java.util.Set):java.security.PublicKey");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.security.cert.X509CRL>, for r6v0, types: [java.util.Set, java.util.Set<java.security.cert.X509CRL>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.security.cert.X509CRL processCRLH(java.util.Set<java.security.cert.X509CRL> r6, java.security.PublicKey r7) throws org.spongycastle.jce.provider.AnnotatedException {
        /*
            r3 = 0
            java.util.Iterator r2 = r6.iterator()
        L_0x0005:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0018
            java.lang.Object r0 = r2.next()
            java.security.cert.X509CRL r0 = (java.security.cert.X509CRL) r0
            r0.verify(r7)     // Catch:{ Exception -> 0x0015 }
        L_0x0014:
            return r0
        L_0x0015:
            r1 = move-exception
            r3 = r1
            goto L_0x0005
        L_0x0018:
            if (r3 == 0) goto L_0x0023
            org.spongycastle.jce.provider.AnnotatedException r4 = new org.spongycastle.jce.provider.AnnotatedException
            java.lang.String r5 = "Cannot verify delta CRL."
            r4.<init>(r5, r3)
            throw r4
        L_0x0023:
            r0 = 0
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.RFC3280CertPathUtilities.processCRLH(java.util.Set, java.security.PublicKey):java.security.cert.X509CRL");
    }

    protected static Set processCRLA1i(Date currentDate, PKIXExtendedParameters paramsPKIX, X509Certificate cert, X509CRL crl) throws AnnotatedException {
        Set set = new HashSet();
        if (paramsPKIX.isUseDeltasEnabled()) {
            try {
                CRLDistPoint freshestCRL = CRLDistPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(cert, FRESHEST_CRL));
                if (freshestCRL == null) {
                    try {
                        freshestCRL = CRLDistPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(crl, FRESHEST_CRL));
                    } catch (AnnotatedException e) {
                        throw new AnnotatedException("Freshest CRL extension could not be decoded from CRL.", e);
                    }
                }
                if (freshestCRL != null) {
                    List crlStores = new ArrayList();
                    crlStores.addAll(paramsPKIX.getCRLStores());
                    try {
                        crlStores.addAll(CertPathValidatorUtilities.getAdditionalStoresFromCRLDistributionPoint(freshestCRL, paramsPKIX.getNamedCRLStoreMap()));
                        try {
                            set.addAll(CertPathValidatorUtilities.getDeltaCRLs(currentDate, crl, paramsPKIX.getCertStores(), crlStores));
                        } catch (AnnotatedException e2) {
                            throw new AnnotatedException("Exception obtaining delta CRLs.", e2);
                        }
                    } catch (AnnotatedException e3) {
                        throw new AnnotatedException("No new delta CRL locations could be added from Freshest CRL extension.", e3);
                    }
                }
            } catch (AnnotatedException e4) {
                throw new AnnotatedException("Freshest CRL extension could not be decoded from certificate.", e4);
            }
        }
        return set;
    }

    protected static Set[] processCRLA1ii(Date currentDate, PKIXExtendedParameters paramsPKIX, X509Certificate cert, X509CRL crl) throws AnnotatedException {
        Set deltaSet = new HashSet();
        X509CRLSelector crlselect = new X509CRLSelector();
        crlselect.setCertificateChecking(cert);
        try {
            crlselect.addIssuerName(PrincipalUtils.getIssuerPrincipal(crl).getEncoded());
            PKIXCRLStoreSelector extSelect = new PKIXCRLStoreSelector.Builder(crlselect).setCompleteCRLEnabled(true).build();
            Date validityDate = currentDate;
            if (paramsPKIX.getDate() != null) {
                validityDate = paramsPKIX.getDate();
            }
            Set completeSet = CRL_UTIL.findCRLs(extSelect, validityDate, paramsPKIX.getCertStores(), paramsPKIX.getCRLStores());
            if (paramsPKIX.isUseDeltasEnabled()) {
                try {
                    deltaSet.addAll(CertPathValidatorUtilities.getDeltaCRLs(validityDate, crl, paramsPKIX.getCertStores(), paramsPKIX.getCRLStores()));
                } catch (AnnotatedException e) {
                    throw new AnnotatedException("Exception obtaining delta CRLs.", e);
                }
            }
            return new Set[]{completeSet, deltaSet};
        } catch (IOException e2) {
            throw new AnnotatedException("Cannot extract issuer from CRL." + e2, e2);
        }
    }

    protected static void processCRLC(X509CRL deltaCRL, X509CRL completeCRL, PKIXExtendedParameters pkixParams) throws AnnotatedException {
        if (deltaCRL != null) {
            try {
                IssuingDistributionPoint completeidp = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(completeCRL, ISSUING_DISTRIBUTION_POINT));
                if (!pkixParams.isUseDeltasEnabled()) {
                    return;
                }
                if (!PrincipalUtils.getIssuerPrincipal(deltaCRL).equals(PrincipalUtils.getIssuerPrincipal(completeCRL))) {
                    throw new AnnotatedException("Complete CRL issuer does not match delta CRL issuer.");
                }
                try {
                    IssuingDistributionPoint deltaidp = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(deltaCRL, ISSUING_DISTRIBUTION_POINT));
                    boolean match = false;
                    if (completeidp == null) {
                        if (deltaidp == null) {
                            match = true;
                        }
                    } else if (completeidp.equals(deltaidp)) {
                        match = true;
                    }
                    if (!match) {
                        throw new AnnotatedException("Issuing distribution point extension from delta CRL and complete CRL does not match.");
                    }
                    try {
                        ASN1Primitive completeKeyIdentifier = CertPathValidatorUtilities.getExtensionValue(completeCRL, AUTHORITY_KEY_IDENTIFIER);
                        try {
                            ASN1Primitive deltaKeyIdentifier = CertPathValidatorUtilities.getExtensionValue(deltaCRL, AUTHORITY_KEY_IDENTIFIER);
                            if (completeKeyIdentifier == null) {
                                throw new AnnotatedException("CRL authority key identifier is null.");
                            } else if (deltaKeyIdentifier == null) {
                                throw new AnnotatedException("Delta CRL authority key identifier is null.");
                            } else if (!completeKeyIdentifier.equals(deltaKeyIdentifier)) {
                                throw new AnnotatedException("Delta CRL authority key identifier does not match complete CRL authority key identifier.");
                            }
                        } catch (AnnotatedException e) {
                            throw new AnnotatedException("Authority key identifier extension could not be extracted from delta CRL.", e);
                        }
                    } catch (AnnotatedException e2) {
                        throw new AnnotatedException("Authority key identifier extension could not be extracted from complete CRL.", e2);
                    }
                } catch (Exception e3) {
                    throw new AnnotatedException("Issuing distribution point extension from delta CRL could not be decoded.", e3);
                }
            } catch (Exception e4) {
                throw new AnnotatedException("Issuing distribution point extension could not be decoded.", e4);
            }
        }
    }

    protected static void processCRLI(Date validDate, X509CRL deltacrl, Object cert, CertStatus certStatus, PKIXExtendedParameters pkixParams) throws AnnotatedException {
        if (pkixParams.isUseDeltasEnabled() && deltacrl != null) {
            CertPathValidatorUtilities.getCertStatus(validDate, deltacrl, cert, certStatus);
        }
    }

    protected static void processCRLJ(Date validDate, X509CRL completecrl, Object cert, CertStatus certStatus) throws AnnotatedException {
        if (certStatus.getCertStatus() == 11) {
            CertPathValidatorUtilities.getCertStatus(validDate, completecrl, cert, certStatus);
        }
    }

    protected static PKIXPolicyNode prepareCertB(CertPath certPath, int index, List[] policyNodes, PKIXPolicyNode validPolicyTree, int policyMapping) throws CertPathValidatorException {
        List certs = certPath.getCertificates();
        X509Certificate cert = (X509Certificate) certs.get(index);
        int i = certs.size() - index;
        try {
            ASN1Sequence pm = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue(cert, POLICY_MAPPINGS));
            PKIXPolicyNode _validPolicyTree = validPolicyTree;
            if (pm != null) {
                ASN1Sequence mappings = pm;
                HashMap hashMap = new HashMap();
                HashSet<String> hashSet = new HashSet<>();
                for (int j = 0; j < mappings.size(); j++) {
                    ASN1Sequence mapping = (ASN1Sequence) mappings.getObjectAt(j);
                    String id_p = ((ASN1ObjectIdentifier) mapping.getObjectAt(0)).getId();
                    String sd_p = ((ASN1ObjectIdentifier) mapping.getObjectAt(1)).getId();
                    if (!hashMap.containsKey(id_p)) {
                        HashSet hashSet2 = new HashSet();
                        hashSet2.add(sd_p);
                        hashMap.put(id_p, hashSet2);
                        hashSet.add(id_p);
                    } else {
                        ((Set) hashMap.get(id_p)).add(sd_p);
                    }
                }
                for (String id_p2 : hashSet) {
                    if (policyMapping > 0) {
                        boolean idp_found = false;
                        Iterator nodes_i = policyNodes[i].iterator();
                        while (true) {
                            if (!nodes_i.hasNext()) {
                                break;
                            }
                            PKIXPolicyNode node = (PKIXPolicyNode) nodes_i.next();
                            if (node.getValidPolicy().equals(id_p2)) {
                                idp_found = true;
                                node.expectedPolicies = (Set) hashMap.get(id_p2);
                                break;
                            }
                        }
                        if (!idp_found) {
                            Iterator nodes_i2 = policyNodes[i].iterator();
                            while (true) {
                                if (!nodes_i2.hasNext()) {
                                    break;
                                }
                                PKIXPolicyNode node2 = (PKIXPolicyNode) nodes_i2.next();
                                if (ANY_POLICY.equals(node2.getValidPolicy())) {
                                    Set pq = null;
                                    try {
                                        Enumeration e = ((ASN1Sequence) CertPathValidatorUtilities.getExtensionValue(cert, CERTIFICATE_POLICIES)).getObjects();
                                        while (true) {
                                            if (!e.hasMoreElements()) {
                                                break;
                                            }
                                            try {
                                                PolicyInformation pinfo = PolicyInformation.getInstance(e.nextElement());
                                                if (ANY_POLICY.equals(pinfo.getPolicyIdentifier().getId())) {
                                                    try {
                                                        pq = CertPathValidatorUtilities.getQualifierSet(pinfo.getPolicyQualifiers());
                                                        break;
                                                    } catch (CertPathValidatorException ex) {
                                                        throw new ExtCertPathValidatorException("Policy qualifier info set could not be decoded.", ex, certPath, index);
                                                    }
                                                }
                                            } catch (Exception ex2) {
                                                throw new CertPathValidatorException("Policy information could not be decoded.", ex2, certPath, index);
                                            }
                                        }
                                        boolean ci = false;
                                        if (cert.getCriticalExtensionOIDs() != null) {
                                            ci = cert.getCriticalExtensionOIDs().contains(CERTIFICATE_POLICIES);
                                        }
                                        PKIXPolicyNode p_node = (PKIXPolicyNode) node2.getParent();
                                        if (ANY_POLICY.equals(p_node.getValidPolicy())) {
                                            PKIXPolicyNode c_node = new PKIXPolicyNode(new ArrayList(), i, (Set) hashMap.get(id_p2), p_node, pq, id_p2, ci);
                                            p_node.addChild(c_node);
                                            policyNodes[i].add(c_node);
                                        }
                                    } catch (AnnotatedException e2) {
                                        throw new ExtCertPathValidatorException("Certificate policies extension could not be decoded.", e2, certPath, index);
                                    }
                                }
                            }
                        }
                    } else if (policyMapping <= 0) {
                        Iterator nodes_i3 = policyNodes[i].iterator();
                        while (nodes_i3.hasNext()) {
                            PKIXPolicyNode node3 = (PKIXPolicyNode) nodes_i3.next();
                            if (node3.getValidPolicy().equals(id_p2)) {
                                ((PKIXPolicyNode) node3.getParent()).removeChild(node3);
                                nodes_i3.remove();
                                for (int k = i - 1; k >= 0; k--) {
                                    List nodes = policyNodes[k];
                                    for (int l = 0; l < nodes.size(); l++) {
                                        PKIXPolicyNode node22 = (PKIXPolicyNode) nodes.get(l);
                                        if (!node22.hasChildren()) {
                                            _validPolicyTree = CertPathValidatorUtilities.removePolicyNode(_validPolicyTree, policyNodes, node22);
                                            if (_validPolicyTree == null) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return _validPolicyTree;
        } catch (AnnotatedException ex3) {
            throw new ExtCertPathValidatorException("Policy mappings extension could not be decoded.", ex3, certPath, index);
        }
    }

    protected static void prepareNextCertA(CertPath certPath, int index) throws CertPathValidatorException {
        try {
            ASN1Sequence pm = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), POLICY_MAPPINGS));
            if (pm != null) {
                ASN1Sequence mappings = pm;
                int j = 0;
                while (j < mappings.size()) {
                    try {
                        ASN1Sequence mapping = DERSequence.getInstance(mappings.getObjectAt(j));
                        ASN1ObjectIdentifier issuerDomainPolicy = ASN1ObjectIdentifier.getInstance(mapping.getObjectAt(0));
                        ASN1ObjectIdentifier subjectDomainPolicy = ASN1ObjectIdentifier.getInstance(mapping.getObjectAt(1));
                        if (ANY_POLICY.equals(issuerDomainPolicy.getId())) {
                            throw new CertPathValidatorException("IssuerDomainPolicy is anyPolicy", null, certPath, index);
                        } else if (ANY_POLICY.equals(subjectDomainPolicy.getId())) {
                            throw new CertPathValidatorException("SubjectDomainPolicy is anyPolicy,", null, certPath, index);
                        } else {
                            j++;
                        }
                    } catch (Exception e) {
                        throw new ExtCertPathValidatorException("Policy mappings extension contents could not be decoded.", e, certPath, index);
                    }
                }
            }
        } catch (AnnotatedException ex) {
            throw new ExtCertPathValidatorException("Policy mappings extension could not be decoded.", ex, certPath, index);
        }
    }

    protected static void processCertF(CertPath certPath, int index, PKIXPolicyNode validPolicyTree, int explicitPolicy) throws CertPathValidatorException {
        if (explicitPolicy <= 0 && validPolicyTree == null) {
            throw new ExtCertPathValidatorException("No valid policy tree found when one expected.", null, certPath, index);
        }
    }

    protected static PKIXPolicyNode processCertE(CertPath certPath, int index, PKIXPolicyNode validPolicyTree) throws CertPathValidatorException {
        try {
            if (DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), CERTIFICATE_POLICIES)) == null) {
                return null;
            }
            return validPolicyTree;
        } catch (AnnotatedException e) {
            throw new ExtCertPathValidatorException("Could not read certificate policies extension from certificate.", e, certPath, index);
        }
    }

    protected static void processCertBC(CertPath certPath, int index, PKIXNameConstraintValidator nameConstraintValidator) throws CertPathValidatorException {
        List certs = certPath.getCertificates();
        X509Certificate cert = (X509Certificate) certs.get(index);
        int n = certs.size();
        int i = n - index;
        if (!CertPathValidatorUtilities.isSelfIssued(cert) || i >= n) {
            try {
                ASN1Sequence dns = DERSequence.getInstance(PrincipalUtils.getSubjectPrincipal(cert).getEncoded());
                try {
                    nameConstraintValidator.checkPermittedDN(dns);
                    nameConstraintValidator.checkExcludedDN(dns);
                    try {
                        GeneralNames altName = GeneralNames.getInstance(CertPathValidatorUtilities.getExtensionValue(cert, SUBJECT_ALTERNATIVE_NAME));
                        RDN[] emails = X500Name.getInstance(dns).getRDNs(BCStyle.EmailAddress);
                        int eI = 0;
                        while (eI != emails.length) {
                            GeneralName emailAsGeneralName = new GeneralName(1, ((ASN1String) emails[eI].getFirst().getValue()).getString());
                            try {
                                nameConstraintValidator.checkPermitted(emailAsGeneralName);
                                nameConstraintValidator.checkExcluded(emailAsGeneralName);
                                eI++;
                            } catch (PKIXNameConstraintValidatorException ex) {
                                CertPathValidatorException certPathValidatorException = new CertPathValidatorException("Subtree check for certificate subject alternative email failed.", ex, certPath, index);
                                throw certPathValidatorException;
                            }
                        }
                        if (altName != null) {
                            try {
                                GeneralName[] genNames = altName.getNames();
                                int j = 0;
                                while (j < genNames.length) {
                                    try {
                                        nameConstraintValidator.checkPermitted(genNames[j]);
                                        nameConstraintValidator.checkExcluded(genNames[j]);
                                        j++;
                                    } catch (PKIXNameConstraintValidatorException e) {
                                        CertPathValidatorException certPathValidatorException2 = new CertPathValidatorException("Subtree check for certificate subject alternative name failed.", e, certPath, index);
                                        throw certPathValidatorException2;
                                    }
                                }
                            } catch (Exception e2) {
                                CertPathValidatorException certPathValidatorException3 = new CertPathValidatorException("Subject alternative name contents could not be decoded.", e2, certPath, index);
                                throw certPathValidatorException3;
                            }
                        }
                    } catch (Exception e3) {
                        CertPathValidatorException certPathValidatorException4 = new CertPathValidatorException("Subject alternative name extension could not be decoded.", e3, certPath, index);
                        throw certPathValidatorException4;
                    }
                } catch (PKIXNameConstraintValidatorException e4) {
                    CertPathValidatorException certPathValidatorException5 = new CertPathValidatorException("Subtree check for certificate subject failed.", e4, certPath, index);
                    throw certPathValidatorException5;
                }
            } catch (Exception e5) {
                CertPathValidatorException certPathValidatorException6 = new CertPathValidatorException("Exception extracting subject name when checking subtrees.", e5, certPath, index);
                throw certPathValidatorException6;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:111:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01d3  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0199 A[EDGE_INSN: B:88:0x0199->B:65:0x0199 ?: BREAK  
    EDGE_INSN: B:88:0x0199->B:65:0x0199 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static org.spongycastle.jce.provider.PKIXPolicyNode processCertD(java.security.cert.CertPath r38, int r39, java.util.Set r40, org.spongycastle.jce.provider.PKIXPolicyNode r41, java.util.List[] r42, int r43) throws java.security.cert.CertPathValidatorException {
        /*
            java.util.List r20 = r38.getCertificates()
            r0 = r20
            r1 = r39
            java.lang.Object r18 = r0.get(r1)
            java.security.cert.X509Certificate r18 = (java.security.cert.X509Certificate) r18
            int r29 = r20.size()
            int r5 = r29 - r39
            r19 = 0
            java.lang.String r4 = CERTIFICATE_POLICIES     // Catch:{ AnnotatedException -> 0x0077 }
            r0 = r18
            org.spongycastle.asn1.ASN1Primitive r4 = org.spongycastle.jce.provider.CertPathValidatorUtilities.getExtensionValue(r0, r4)     // Catch:{ AnnotatedException -> 0x0077 }
            org.spongycastle.asn1.ASN1Sequence r19 = org.spongycastle.asn1.DERSequence.getInstance(r4)     // Catch:{ AnnotatedException -> 0x0077 }
            if (r19 == 0) goto L_0x01fb
            if (r41 == 0) goto L_0x01fb
            java.util.Enumeration r23 = r19.getObjects()
            java.util.HashSet r35 = new java.util.HashSet
            r35.<init>()
        L_0x002f:
            boolean r4 = r23.hasMoreElements()
            if (r4 == 0) goto L_0x0097
            java.lang.Object r4 = r23.nextElement()
            org.spongycastle.asn1.x509.PolicyInformation r33 = org.spongycastle.asn1.x509.PolicyInformation.getInstance(r4)
            org.spongycastle.asn1.ASN1ObjectIdentifier r34 = r33.getPolicyIdentifier()
            java.lang.String r4 = r34.getId()
            r0 = r35
            r0.add(r4)
            java.lang.String r4 = "2.5.29.32.0"
            java.lang.String r10 = r34.getId()
            boolean r4 = r4.equals(r10)
            if (r4 != 0) goto L_0x002f
            r36 = 0
            org.spongycastle.asn1.ASN1Sequence r4 = r33.getPolicyQualifiers()     // Catch:{ CertPathValidatorException -> 0x0087 }
            java.util.Set r36 = org.spongycastle.jce.provider.CertPathValidatorUtilities.getQualifierSet(r4)     // Catch:{ CertPathValidatorException -> 0x0087 }
            r0 = r42
            r1 = r34
            r2 = r36
            boolean r28 = org.spongycastle.jce.provider.CertPathValidatorUtilities.processCertD1i(r5, r0, r1, r2)
            if (r28 != 0) goto L_0x002f
            r0 = r42
            r1 = r34
            r2 = r36
            org.spongycastle.jce.provider.CertPathValidatorUtilities.processCertD1ii(r5, r0, r1, r2)
            goto L_0x002f
        L_0x0077:
            r23 = move-exception
            org.spongycastle.jce.exception.ExtCertPathValidatorException r4 = new org.spongycastle.jce.exception.ExtCertPathValidatorException
            java.lang.String r10 = "Could not read certificate policies extension from certificate."
            r0 = r23
            r1 = r38
            r2 = r39
            r4.<init>(r10, r0, r1, r2)
            throw r4
        L_0x0087:
            r24 = move-exception
            org.spongycastle.jce.exception.ExtCertPathValidatorException r4 = new org.spongycastle.jce.exception.ExtCertPathValidatorException
            java.lang.String r10 = "Policy qualifier info set could not be build."
            r0 = r24
            r1 = r38
            r2 = r39
            r4.<init>(r10, r0, r1, r2)
            throw r4
        L_0x0097:
            boolean r4 = r40.isEmpty()
            if (r4 != 0) goto L_0x00a8
            java.lang.String r4 = "2.5.29.32.0"
            r0 = r40
            boolean r4 = r0.contains(r4)
            if (r4 == 0) goto L_0x0138
        L_0x00a8:
            r40.clear()
            r0 = r40
            r1 = r35
            r0.addAll(r1)
        L_0x00b2:
            if (r43 > 0) goto L_0x00be
            r0 = r29
            if (r5 >= r0) goto L_0x0199
            boolean r4 = org.spongycastle.jce.provider.CertPathValidatorUtilities.isSelfIssued(r18)
            if (r4 == 0) goto L_0x0199
        L_0x00be:
            java.util.Enumeration r23 = r19.getObjects()
        L_0x00c2:
            boolean r4 = r23.hasMoreElements()
            if (r4 == 0) goto L_0x0199
            java.lang.Object r4 = r23.nextElement()
            org.spongycastle.asn1.x509.PolicyInformation r33 = org.spongycastle.asn1.x509.PolicyInformation.getInstance(r4)
            java.lang.String r4 = "2.5.29.32.0"
            org.spongycastle.asn1.ASN1ObjectIdentifier r10 = r33.getPolicyIdentifier()
            java.lang.String r10 = r10.getId()
            boolean r4 = r4.equals(r10)
            if (r4 == 0) goto L_0x00c2
            org.spongycastle.asn1.ASN1Sequence r4 = r33.getPolicyQualifiers()
            java.util.Set r8 = org.spongycastle.jce.provider.CertPathValidatorUtilities.getQualifierSet(r4)
            int r4 = r5 + -1
            r14 = r42[r4]
            r27 = 0
        L_0x00ef:
            int r4 = r14.size()
            r0 = r27
            if (r0 >= r4) goto L_0x0199
            r0 = r27
            java.lang.Object r7 = r14.get(r0)
            org.spongycastle.jce.provider.PKIXPolicyNode r7 = (org.spongycastle.jce.provider.PKIXPolicyNode) r7
            java.util.Set r4 = r7.getExpectedPolicies()
            java.util.Iterator r15 = r4.iterator()
        L_0x0107:
            boolean r4 = r15.hasNext()
            if (r4 == 0) goto L_0x0195
            java.lang.Object r16 = r15.next()
            r0 = r16
            boolean r4 = r0 instanceof java.lang.String
            if (r4 == 0) goto L_0x0169
            r9 = r16
            java.lang.String r9 = (java.lang.String) r9
        L_0x011b:
            r13 = 0
            java.util.Iterator r12 = r7.getChildren()
        L_0x0120:
            boolean r4 = r12.hasNext()
            if (r4 == 0) goto L_0x0176
            java.lang.Object r11 = r12.next()
            org.spongycastle.jce.provider.PKIXPolicyNode r11 = (org.spongycastle.jce.provider.PKIXPolicyNode) r11
            java.lang.String r4 = r11.getValidPolicy()
            boolean r4 = r9.equals(r4)
            if (r4 == 0) goto L_0x0120
            r13 = 1
            goto L_0x0120
        L_0x0138:
            java.util.Iterator r25 = r40.iterator()
            java.util.HashSet r37 = new java.util.HashSet
            r37.<init>()
        L_0x0141:
            boolean r4 = r25.hasNext()
            if (r4 == 0) goto L_0x015d
            java.lang.Object r32 = r25.next()
            r0 = r35
            r1 = r32
            boolean r4 = r0.contains(r1)
            if (r4 == 0) goto L_0x0141
            r0 = r37
            r1 = r32
            r0.add(r1)
            goto L_0x0141
        L_0x015d:
            r40.clear()
            r0 = r40
            r1 = r37
            r0.addAll(r1)
            goto L_0x00b2
        L_0x0169:
            r0 = r16
            boolean r4 = r0 instanceof org.spongycastle.asn1.ASN1ObjectIdentifier
            if (r4 == 0) goto L_0x0107
            org.spongycastle.asn1.ASN1ObjectIdentifier r16 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r16
            java.lang.String r9 = r16.getId()
            goto L_0x011b
        L_0x0176:
            if (r13 != 0) goto L_0x0107
            java.util.HashSet r6 = new java.util.HashSet
            r6.<init>()
            r6.add(r9)
            org.spongycastle.jce.provider.PKIXPolicyNode r3 = new org.spongycastle.jce.provider.PKIXPolicyNode
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r10 = 0
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            r7.addChild(r3)
            r4 = r42[r5]
            r4.add(r3)
            goto L_0x0107
        L_0x0195:
            int r27 = r27 + 1
            goto L_0x00ef
        L_0x0199:
            r17 = r41
            int r26 = r5 + -1
        L_0x019d:
            if (r26 < 0) goto L_0x01cd
            r31 = r42[r26]
            r27 = 0
        L_0x01a3:
            int r4 = r31.size()
            r0 = r27
            if (r0 >= r4) goto L_0x01c7
            r0 = r31
            r1 = r27
            java.lang.Object r30 = r0.get(r1)
            org.spongycastle.jce.provider.PKIXPolicyNode r30 = (org.spongycastle.jce.provider.PKIXPolicyNode) r30
            boolean r4 = r30.hasChildren()
            if (r4 != 0) goto L_0x01ca
            r0 = r17
            r1 = r42
            r2 = r30
            org.spongycastle.jce.provider.PKIXPolicyNode r17 = org.spongycastle.jce.provider.CertPathValidatorUtilities.removePolicyNode(r0, r1, r2)
            if (r17 != 0) goto L_0x01ca
        L_0x01c7:
            int r26 = r26 + -1
            goto L_0x019d
        L_0x01ca:
            int r27 = r27 + 1
            goto L_0x01a3
        L_0x01cd:
            java.util.Set r22 = r18.getCriticalExtensionOIDs()
            if (r22 == 0) goto L_0x01fd
            java.lang.String r4 = CERTIFICATE_POLICIES
            r0 = r22
            boolean r21 = r0.contains(r4)
            r31 = r42[r5]
            r26 = 0
        L_0x01df:
            int r4 = r31.size()
            r0 = r26
            if (r0 >= r4) goto L_0x01fd
            r0 = r31
            r1 = r26
            java.lang.Object r30 = r0.get(r1)
            org.spongycastle.jce.provider.PKIXPolicyNode r30 = (org.spongycastle.jce.provider.PKIXPolicyNode) r30
            r0 = r30
            r1 = r21
            r0.setCritical(r1)
            int r26 = r26 + 1
            goto L_0x01df
        L_0x01fb:
            r17 = 0
        L_0x01fd:
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.RFC3280CertPathUtilities.processCertD(java.security.cert.CertPath, int, java.util.Set, org.spongycastle.jce.provider.PKIXPolicyNode, java.util.List[], int):org.spongycastle.jce.provider.PKIXPolicyNode");
    }

    protected static void processCertA(CertPath certPath, PKIXExtendedParameters paramsPKIX, int index, PublicKey workingPublicKey, boolean verificationAlreadyPerformed, X500Name workingIssuerName, X509Certificate sign, JcaJceHelper helper) throws ExtCertPathValidatorException {
        List certs = certPath.getCertificates();
        X509Certificate cert = (X509Certificate) certs.get(index);
        if (!verificationAlreadyPerformed) {
            try {
                CertPathValidatorUtilities.verifyX509Certificate(cert, workingPublicKey, paramsPKIX.getSigProvider());
            } catch (GeneralSecurityException e) {
                throw new ExtCertPathValidatorException("Could not validate certificate signature.", e, certPath, index);
            }
        }
        try {
            cert.checkValidity(CertPathValidatorUtilities.getValidCertDateFromValidityModel(paramsPKIX, certPath, index));
            if (paramsPKIX.isRevocationEnabled()) {
                try {
                    checkCRLs(paramsPKIX, cert, CertPathValidatorUtilities.getValidCertDateFromValidityModel(paramsPKIX, certPath, index), sign, workingPublicKey, certs, helper);
                } catch (AnnotatedException e2) {
                    Throwable cause = e2;
                    if (e2.getCause() != null) {
                        cause = e2.getCause();
                    }
                    throw new ExtCertPathValidatorException(e2.getMessage(), cause, certPath, index);
                }
            }
            if (!PrincipalUtils.getEncodedIssuerPrincipal(cert).equals(workingIssuerName)) {
                throw new ExtCertPathValidatorException("IssuerName(" + PrincipalUtils.getEncodedIssuerPrincipal(cert) + ") does not match SubjectName(" + workingIssuerName + ") of signing certificate.", null, certPath, index);
            }
        } catch (CertificateExpiredException e3) {
            throw new ExtCertPathValidatorException("Could not validate certificate: " + e3.getMessage(), e3, certPath, index);
        } catch (CertificateNotYetValidException e4) {
            throw new ExtCertPathValidatorException("Could not validate certificate: " + e4.getMessage(), e4, certPath, index);
        } catch (AnnotatedException e5) {
            throw new ExtCertPathValidatorException("Could not validate time of certificate.", e5, certPath, index);
        }
    }

    protected static int prepareNextCertI1(CertPath certPath, int index, int explicitPolicy) throws CertPathValidatorException {
        try {
            ASN1Sequence pc = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), POLICY_CONSTRAINTS));
            if (pc != null) {
                Enumeration policyConstraints = pc.getObjects();
                while (true) {
                    if (!policyConstraints.hasMoreElements()) {
                        break;
                    }
                    try {
                        ASN1TaggedObject constraint = ASN1TaggedObject.getInstance(policyConstraints.nextElement());
                        if (constraint.getTagNo() == 0) {
                            int tmpInt = ASN1Integer.getInstance(constraint, false).getValue().intValue();
                            if (tmpInt < explicitPolicy) {
                                return tmpInt;
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        throw new ExtCertPathValidatorException("Policy constraints extension contents cannot be decoded.", e, certPath, index);
                    }
                }
            }
            return explicitPolicy;
        } catch (Exception e2) {
            throw new ExtCertPathValidatorException("Policy constraints extension cannot be decoded.", e2, certPath, index);
        }
    }

    protected static int prepareNextCertI2(CertPath certPath, int index, int policyMapping) throws CertPathValidatorException {
        try {
            ASN1Sequence pc = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), POLICY_CONSTRAINTS));
            if (pc != null) {
                Enumeration policyConstraints = pc.getObjects();
                while (true) {
                    if (!policyConstraints.hasMoreElements()) {
                        break;
                    }
                    try {
                        ASN1TaggedObject constraint = ASN1TaggedObject.getInstance(policyConstraints.nextElement());
                        if (constraint.getTagNo() == 1) {
                            int tmpInt = ASN1Integer.getInstance(constraint, false).getValue().intValue();
                            if (tmpInt < policyMapping) {
                                return tmpInt;
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        throw new ExtCertPathValidatorException("Policy constraints extension contents cannot be decoded.", e, certPath, index);
                    }
                }
            }
            return policyMapping;
        } catch (Exception e2) {
            throw new ExtCertPathValidatorException("Policy constraints extension cannot be decoded.", e2, certPath, index);
        }
    }

    protected static void prepareNextCertG(CertPath certPath, int index, PKIXNameConstraintValidator nameConstraintValidator) throws CertPathValidatorException {
        NameConstraints nc = null;
        try {
            ASN1Sequence ncSeq = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), NAME_CONSTRAINTS));
            if (ncSeq != null) {
                nc = NameConstraints.getInstance(ncSeq);
            }
            if (nc != null) {
                GeneralSubtree[] permitted = nc.getPermittedSubtrees();
                if (permitted != null) {
                    try {
                        nameConstraintValidator.intersectPermittedSubtree(permitted);
                    } catch (Exception ex) {
                        throw new ExtCertPathValidatorException("Permitted subtrees cannot be build from name constraints extension.", ex, certPath, index);
                    }
                }
                GeneralSubtree[] excluded = nc.getExcludedSubtrees();
                if (excluded != null) {
                    int i = 0;
                    while (i != excluded.length) {
                        try {
                            nameConstraintValidator.addExcludedSubtree(excluded[i]);
                            i++;
                        } catch (Exception ex2) {
                            throw new ExtCertPathValidatorException("Excluded subtrees cannot be build from name constraints extension.", ex2, certPath, index);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new ExtCertPathValidatorException("Name constraints extension could not be decoded.", e, certPath, index);
        }
    }

    private static void checkCRL(DistributionPoint dp, PKIXExtendedParameters paramsPKIX, X509Certificate cert, Date validDate, X509Certificate defaultCRLSignCert, PublicKey defaultCRLSignKey, CertStatus certStatus, ReasonsMask reasonMask, List certPathCerts, JcaJceHelper helper) throws AnnotatedException {
        Date date = new Date(System.currentTimeMillis());
        if (validDate.getTime() > date.getTime()) {
            throw new AnnotatedException("Validation time is in future.");
        }
        boolean validCrlFound = false;
        AnnotatedException lastException = null;
        Iterator crl_iter = CertPathValidatorUtilities.getCompleteCRLs(dp, cert, date, paramsPKIX).iterator();
        while (crl_iter.hasNext() && certStatus.getCertStatus() == 11 && !reasonMask.isAllReasons()) {
            try {
                X509CRL crl = (X509CRL) crl_iter.next();
                ReasonsMask interimReasonsMask = processCRLD(crl, dp);
                if (interimReasonsMask.hasNewReasons(reasonMask)) {
                    PublicKey key = processCRLG(crl, processCRLF(crl, cert, defaultCRLSignCert, defaultCRLSignKey, paramsPKIX, certPathCerts, helper));
                    X509CRL deltaCRL = null;
                    Date validityDate = date;
                    if (paramsPKIX.getDate() != null) {
                        validityDate = paramsPKIX.getDate();
                    }
                    if (paramsPKIX.isUseDeltasEnabled()) {
                        deltaCRL = processCRLH(CertPathValidatorUtilities.getDeltaCRLs(validityDate, crl, paramsPKIX.getCertStores(), paramsPKIX.getCRLStores()), key);
                    }
                    if (paramsPKIX.getValidityModel() == 1 || cert.getNotAfter().getTime() >= crl.getThisUpdate().getTime()) {
                        processCRLB1(dp, cert, crl);
                        processCRLB2(dp, cert, crl);
                        processCRLC(deltaCRL, crl, paramsPKIX);
                        processCRLI(validDate, deltaCRL, cert, certStatus, paramsPKIX);
                        processCRLJ(validDate, crl, cert, certStatus);
                        if (certStatus.getCertStatus() == 8) {
                            certStatus.setCertStatus(11);
                        }
                        reasonMask.addReasons(interimReasonsMask);
                        Set criticalExtensions = crl.getCriticalExtensionOIDs();
                        if (criticalExtensions != null) {
                            HashSet hashSet = new HashSet(criticalExtensions);
                            hashSet.remove(Extension.issuingDistributionPoint.getId());
                            hashSet.remove(Extension.deltaCRLIndicator.getId());
                            if (!hashSet.isEmpty()) {
                                throw new AnnotatedException("CRL contains unsupported critical extensions.");
                            }
                            HashSet hashSet2 = hashSet;
                        }
                        if (deltaCRL != null) {
                            Set criticalExtensions2 = deltaCRL.getCriticalExtensionOIDs();
                            if (criticalExtensions2 != null) {
                                HashSet hashSet3 = new HashSet(criticalExtensions2);
                                hashSet3.remove(Extension.issuingDistributionPoint.getId());
                                hashSet3.remove(Extension.deltaCRLIndicator.getId());
                                if (!hashSet3.isEmpty()) {
                                    throw new AnnotatedException("Delta CRL contains unsupported critical extension.");
                                }
                                HashSet hashSet4 = hashSet3;
                            }
                        }
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

    protected static void checkCRLs(PKIXExtendedParameters paramsPKIX, X509Certificate cert, Date validDate, X509Certificate sign, PublicKey workingPublicKey, List certPathCerts, JcaJceHelper helper) throws AnnotatedException {
        AnnotatedException lastException = null;
        try {
            CRLDistPoint crldp = CRLDistPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(cert, CRL_DISTRIBUTION_POINTS));
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder(paramsPKIX);
            try {
                for (PKIXCRLStore addCRLStore : CertPathValidatorUtilities.getAdditionalStoresFromCRLDistributionPoint(crldp, paramsPKIX.getNamedCRLStoreMap())) {
                    builder.addCRLStore(addCRLStore);
                }
                CertStatus certStatus = new CertStatus();
                ReasonsMask reasonsMask = new ReasonsMask();
                PKIXExtendedParameters finalParams = builder.build();
                boolean validCrlFound = false;
                if (crldp != null) {
                    try {
                        DistributionPoint[] dps = crldp.getDistributionPoints();
                        if (dps != null) {
                            for (int i = 0; i < dps.length && certStatus.getCertStatus() == 11 && !reasonsMask.isAllReasons(); i++) {
                                try {
                                    checkCRL(dps[i], finalParams, cert, validDate, sign, workingPublicKey, certStatus, reasonsMask, certPathCerts, helper);
                                    validCrlFound = true;
                                } catch (AnnotatedException e) {
                                    lastException = e;
                                }
                            }
                        }
                    } catch (Exception e2) {
                        throw new AnnotatedException("Distribution points could not be read.", e2);
                    }
                }
                if (certStatus.getCertStatus() == 11 && !reasonsMask.isAllReasons()) {
                    try {
                        checkCRL(new DistributionPoint(new DistributionPointName(0, new GeneralNames(new GeneralName(4, (ASN1Encodable) new ASN1InputStream(PrincipalUtils.getEncodedIssuerPrincipal(cert).getEncoded()).readObject()))), null, null), (PKIXExtendedParameters) paramsPKIX.clone(), cert, validDate, sign, workingPublicKey, certStatus, reasonsMask, certPathCerts, helper);
                        validCrlFound = true;
                    } catch (Exception e3) {
                        throw new AnnotatedException("Issuer from certificate for CRL could not be reencoded.", e3);
                    } catch (AnnotatedException e4) {
                        lastException = e4;
                    }
                }
                if (!validCrlFound) {
                    if (lastException instanceof AnnotatedException) {
                        throw lastException;
                    }
                    throw new AnnotatedException("No valid CRL found.", lastException);
                } else if (certStatus.getCertStatus() != 11) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    throw new AnnotatedException(("Certificate revocation after " + simpleDateFormat.format(certStatus.getRevocationDate())) + ", reason: " + crlReasons[certStatus.getCertStatus()]);
                } else {
                    if (!reasonsMask.isAllReasons() && certStatus.getCertStatus() == 11) {
                        certStatus.setCertStatus(12);
                    }
                    if (certStatus.getCertStatus() == 12) {
                        throw new AnnotatedException("Certificate status could not be determined.");
                    }
                }
            } catch (AnnotatedException e5) {
                throw new AnnotatedException("No additional CRL locations could be decoded from CRL distribution point extension.", e5);
            }
        } catch (Exception e6) {
            throw new AnnotatedException("CRL distribution point extension could not be read.", e6);
        }
    }

    protected static int prepareNextCertJ(CertPath certPath, int index, int inhibitAnyPolicy) throws CertPathValidatorException {
        try {
            ASN1Integer iap = ASN1Integer.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), INHIBIT_ANY_POLICY));
            if (iap != null) {
                int _inhibitAnyPolicy = iap.getValue().intValue();
                if (_inhibitAnyPolicy < inhibitAnyPolicy) {
                    return _inhibitAnyPolicy;
                }
            }
            return inhibitAnyPolicy;
        } catch (Exception e) {
            throw new ExtCertPathValidatorException("Inhibit any-policy extension cannot be decoded.", e, certPath, index);
        }
    }

    protected static void prepareNextCertK(CertPath certPath, int index) throws CertPathValidatorException {
        try {
            BasicConstraints bc = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), BASIC_CONSTRAINTS));
            if (bc == null) {
                throw new CertPathValidatorException("Intermediate certificate lacks BasicConstraints");
            } else if (!bc.isCA()) {
                throw new CertPathValidatorException("Not a CA certificate");
            }
        } catch (Exception e) {
            throw new ExtCertPathValidatorException("Basic constraints extension cannot be decoded.", e, certPath, index);
        }
    }

    protected static int prepareNextCertL(CertPath certPath, int index, int maxPathLength) throws CertPathValidatorException {
        if (CertPathValidatorUtilities.isSelfIssued((X509Certificate) certPath.getCertificates().get(index))) {
            return maxPathLength;
        }
        if (maxPathLength > 0) {
            return maxPathLength - 1;
        }
        throw new ExtCertPathValidatorException("Max path length not greater than zero", null, certPath, index);
    }

    protected static int prepareNextCertM(CertPath certPath, int index, int maxPathLength) throws CertPathValidatorException {
        try {
            BasicConstraints bc = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), BASIC_CONSTRAINTS));
            if (bc != null) {
                BigInteger _pathLengthConstraint = bc.getPathLenConstraint();
                if (_pathLengthConstraint != null) {
                    int _plc = _pathLengthConstraint.intValue();
                    if (_plc < maxPathLength) {
                        return _plc;
                    }
                }
            }
            return maxPathLength;
        } catch (Exception e) {
            throw new ExtCertPathValidatorException("Basic constraints extension cannot be decoded.", e, certPath, index);
        }
    }

    protected static void prepareNextCertN(CertPath certPath, int index) throws CertPathValidatorException {
        boolean[] _usage = ((X509Certificate) certPath.getCertificates().get(index)).getKeyUsage();
        if (_usage != null && !_usage[5]) {
            throw new ExtCertPathValidatorException("Issuer certificate keyusage extension is critical and does not permit key signing.", null, certPath, index);
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<java.security.cert.PKIXCertPathChecker>, for r10v0, types: [java.util.List, java.util.List<java.security.cert.PKIXCertPathChecker>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void prepareNextCertO(java.security.cert.CertPath r7, int r8, java.util.Set r9, java.util.List<java.security.cert.PKIXCertPathChecker> r10) throws java.security.cert.CertPathValidatorException {
        /*
            java.util.List r1 = r7.getCertificates()
            java.lang.Object r0 = r1.get(r8)
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0
            java.util.Iterator r3 = r10.iterator()
        L_0x000e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x002d
            java.lang.Object r4 = r3.next()     // Catch:{ CertPathValidatorException -> 0x001e }
            java.security.cert.PKIXCertPathChecker r4 = (java.security.cert.PKIXCertPathChecker) r4     // Catch:{ CertPathValidatorException -> 0x001e }
            r4.check(r0, r9)     // Catch:{ CertPathValidatorException -> 0x001e }
            goto L_0x000e
        L_0x001e:
            r2 = move-exception
            java.security.cert.CertPathValidatorException r4 = new java.security.cert.CertPathValidatorException
            java.lang.String r5 = r2.getMessage()
            java.lang.Throwable r6 = r2.getCause()
            r4.<init>(r5, r6, r7, r8)
            throw r4
        L_0x002d:
            boolean r4 = r9.isEmpty()
            if (r4 != 0) goto L_0x004e
            org.spongycastle.jce.exception.ExtCertPathValidatorException r4 = new org.spongycastle.jce.exception.ExtCertPathValidatorException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Certificate has unsupported critical extension: "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r9)
            java.lang.String r5 = r5.toString()
            r6 = 0
            r4.<init>(r5, r6, r7, r8)
            throw r4
        L_0x004e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.RFC3280CertPathUtilities.prepareNextCertO(java.security.cert.CertPath, int, java.util.Set, java.util.List):void");
    }

    protected static int prepareNextCertH1(CertPath certPath, int index, int explicitPolicy) {
        if (CertPathValidatorUtilities.isSelfIssued((X509Certificate) certPath.getCertificates().get(index)) || explicitPolicy == 0) {
            return explicitPolicy;
        }
        return explicitPolicy - 1;
    }

    protected static int prepareNextCertH2(CertPath certPath, int index, int policyMapping) {
        if (CertPathValidatorUtilities.isSelfIssued((X509Certificate) certPath.getCertificates().get(index)) || policyMapping == 0) {
            return policyMapping;
        }
        return policyMapping - 1;
    }

    protected static int prepareNextCertH3(CertPath certPath, int index, int inhibitAnyPolicy) {
        if (CertPathValidatorUtilities.isSelfIssued((X509Certificate) certPath.getCertificates().get(index)) || inhibitAnyPolicy == 0) {
            return inhibitAnyPolicy;
        }
        return inhibitAnyPolicy - 1;
    }

    protected static int wrapupCertA(int explicitPolicy, X509Certificate cert) {
        if (CertPathValidatorUtilities.isSelfIssued(cert) || explicitPolicy == 0) {
            return explicitPolicy;
        }
        return explicitPolicy - 1;
    }

    protected static int wrapupCertB(CertPath certPath, int index, int explicitPolicy) throws CertPathValidatorException {
        try {
            ASN1Sequence pc = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), POLICY_CONSTRAINTS));
            if (pc == null) {
                return explicitPolicy;
            }
            Enumeration policyConstraints = pc.getObjects();
            while (policyConstraints.hasMoreElements()) {
                ASN1TaggedObject constraint = (ASN1TaggedObject) policyConstraints.nextElement();
                switch (constraint.getTagNo()) {
                    case 0:
                        try {
                            if (ASN1Integer.getInstance(constraint, false).getValue().intValue() != 0) {
                                break;
                            } else {
                                return 0;
                            }
                        } catch (Exception e) {
                            throw new ExtCertPathValidatorException("Policy constraints requireExplicitPolicy field could not be decoded.", e, certPath, index);
                        }
                }
            }
            return explicitPolicy;
        } catch (AnnotatedException e2) {
            throw new ExtCertPathValidatorException("Policy constraints could not be decoded.", e2, certPath, index);
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<java.security.cert.PKIXCertPathChecker>, for r9v0, types: [java.util.List, java.util.List<java.security.cert.PKIXCertPathChecker>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void wrapupCertF(java.security.cert.CertPath r7, int r8, java.util.List<java.security.cert.PKIXCertPathChecker> r9, java.util.Set r10) throws java.security.cert.CertPathValidatorException {
        /*
            java.util.List r1 = r7.getCertificates()
            java.lang.Object r0 = r1.get(r8)
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0
            java.util.Iterator r3 = r9.iterator()
        L_0x000e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0028
            java.lang.Object r4 = r3.next()     // Catch:{ CertPathValidatorException -> 0x001e }
            java.security.cert.PKIXCertPathChecker r4 = (java.security.cert.PKIXCertPathChecker) r4     // Catch:{ CertPathValidatorException -> 0x001e }
            r4.check(r0, r10)     // Catch:{ CertPathValidatorException -> 0x001e }
            goto L_0x000e
        L_0x001e:
            r2 = move-exception
            org.spongycastle.jce.exception.ExtCertPathValidatorException r4 = new org.spongycastle.jce.exception.ExtCertPathValidatorException
            java.lang.String r5 = "Additional certificate path checker failed."
            r4.<init>(r5, r2, r7, r8)
            throw r4
        L_0x0028:
            boolean r4 = r10.isEmpty()
            if (r4 != 0) goto L_0x0049
            org.spongycastle.jce.exception.ExtCertPathValidatorException r4 = new org.spongycastle.jce.exception.ExtCertPathValidatorException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Certificate has unsupported critical extension: "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r10)
            java.lang.String r5 = r5.toString()
            r6 = 0
            r4.<init>(r5, r6, r7, r8)
            throw r4
        L_0x0049:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.RFC3280CertPathUtilities.wrapupCertF(java.security.cert.CertPath, int, java.util.List, java.util.Set):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x009f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static org.spongycastle.jce.provider.PKIXPolicyNode wrapupCertG(java.security.cert.CertPath r21, org.spongycastle.jcajce.PKIXExtendedParameters r22, java.util.Set r23, int r24, java.util.List[] r25, org.spongycastle.jce.provider.PKIXPolicyNode r26, java.util.Set r27) throws java.security.cert.CertPathValidatorException {
        /*
            java.util.List r18 = r21.getCertificates()
            int r15 = r18.size()
            if (r26 != 0) goto L_0x0027
            boolean r18 = r22.isExplicitPolicyRequired()
            if (r18 == 0) goto L_0x0025
            org.spongycastle.jce.exception.ExtCertPathValidatorException r18 = new org.spongycastle.jce.exception.ExtCertPathValidatorException
            java.lang.String r19 = "Explicit policy requested but none available."
            r20 = 0
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r24
            r0.<init>(r1, r2, r3, r4)
            throw r18
        L_0x0025:
            r12 = 0
        L_0x0026:
            return r12
        L_0x0027:
            boolean r18 = org.spongycastle.jce.provider.CertPathValidatorUtilities.isAnyPolicy(r23)
            if (r18 == 0) goto L_0x00e5
            boolean r18 = r22.isExplicitPolicyRequired()
            if (r18 == 0) goto L_0x00e1
            boolean r18 = r27.isEmpty()
            if (r18 == 0) goto L_0x004e
            org.spongycastle.jce.exception.ExtCertPathValidatorException r18 = new org.spongycastle.jce.exception.ExtCertPathValidatorException
            java.lang.String r19 = "Explicit policy requested but none available."
            r20 = 0
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r24
            r0.<init>(r1, r2, r3, r4)
            throw r18
        L_0x004e:
            java.util.HashSet r10 = new java.util.HashSet
            r10.<init>()
            r13 = 0
        L_0x0054:
            r0 = r25
            int r0 = r0.length
            r18 = r0
            r0 = r18
            if (r13 >= r0) goto L_0x0095
            r8 = r25[r13]
            r14 = 0
        L_0x0060:
            int r18 = r8.size()
            r0 = r18
            if (r14 >= r0) goto L_0x0092
            java.lang.Object r7 = r8.get(r14)
            org.spongycastle.jce.provider.PKIXPolicyNode r7 = (org.spongycastle.jce.provider.PKIXPolicyNode) r7
            java.lang.String r18 = "2.5.29.32.0"
            java.lang.String r19 = r7.getValidPolicy()
            boolean r18 = r18.equals(r19)
            if (r18 == 0) goto L_0x008f
            java.util.Iterator r6 = r7.getChildren()
        L_0x007f:
            boolean r18 = r6.hasNext()
            if (r18 == 0) goto L_0x008f
            java.lang.Object r18 = r6.next()
            r0 = r18
            r10.add(r0)
            goto L_0x007f
        L_0x008f:
            int r14 = r14 + 1
            goto L_0x0060
        L_0x0092:
            int r13 = r13 + 1
            goto L_0x0054
        L_0x0095:
            java.util.Iterator r11 = r10.iterator()
        L_0x0099:
            boolean r18 = r11.hasNext()
            if (r18 == 0) goto L_0x00b2
            java.lang.Object r7 = r11.next()
            org.spongycastle.jce.provider.PKIXPolicyNode r7 = (org.spongycastle.jce.provider.PKIXPolicyNode) r7
            java.lang.String r9 = r7.getValidPolicy()
            r0 = r27
            boolean r18 = r0.contains(r9)
            if (r18 != 0) goto L_0x0099
            goto L_0x0099
        L_0x00b2:
            if (r26 == 0) goto L_0x00e1
            int r13 = r15 + -1
        L_0x00b6:
            if (r13 < 0) goto L_0x00e1
            r17 = r25[r13]
            r14 = 0
        L_0x00bb:
            int r18 = r17.size()
            r0 = r18
            if (r14 >= r0) goto L_0x00de
            r0 = r17
            java.lang.Object r16 = r0.get(r14)
            org.spongycastle.jce.provider.PKIXPolicyNode r16 = (org.spongycastle.jce.provider.PKIXPolicyNode) r16
            boolean r18 = r16.hasChildren()
            if (r18 != 0) goto L_0x00db
            r0 = r26
            r1 = r25
            r2 = r16
            org.spongycastle.jce.provider.PKIXPolicyNode r26 = org.spongycastle.jce.provider.CertPathValidatorUtilities.removePolicyNode(r0, r1, r2)
        L_0x00db:
            int r14 = r14 + 1
            goto L_0x00bb
        L_0x00de:
            int r13 = r13 + -1
            goto L_0x00b6
        L_0x00e1:
            r12 = r26
            goto L_0x0026
        L_0x00e5:
            java.util.HashSet r10 = new java.util.HashSet
            r10.<init>()
            r13 = 0
        L_0x00eb:
            r0 = r25
            int r0 = r0.length
            r18 = r0
            r0 = r18
            if (r13 >= r0) goto L_0x0139
            r8 = r25[r13]
            r14 = 0
        L_0x00f7:
            int r18 = r8.size()
            r0 = r18
            if (r14 >= r0) goto L_0x0136
            java.lang.Object r7 = r8.get(r14)
            org.spongycastle.jce.provider.PKIXPolicyNode r7 = (org.spongycastle.jce.provider.PKIXPolicyNode) r7
            java.lang.String r18 = "2.5.29.32.0"
            java.lang.String r19 = r7.getValidPolicy()
            boolean r18 = r18.equals(r19)
            if (r18 == 0) goto L_0x0133
            java.util.Iterator r6 = r7.getChildren()
        L_0x0116:
            boolean r18 = r6.hasNext()
            if (r18 == 0) goto L_0x0133
            java.lang.Object r5 = r6.next()
            org.spongycastle.jce.provider.PKIXPolicyNode r5 = (org.spongycastle.jce.provider.PKIXPolicyNode) r5
            java.lang.String r18 = "2.5.29.32.0"
            java.lang.String r19 = r5.getValidPolicy()
            boolean r18 = r18.equals(r19)
            if (r18 != 0) goto L_0x0116
            r10.add(r5)
            goto L_0x0116
        L_0x0133:
            int r14 = r14 + 1
            goto L_0x00f7
        L_0x0136:
            int r13 = r13 + 1
            goto L_0x00eb
        L_0x0139:
            java.util.Iterator r11 = r10.iterator()
        L_0x013d:
            boolean r18 = r11.hasNext()
            if (r18 == 0) goto L_0x015e
            java.lang.Object r7 = r11.next()
            org.spongycastle.jce.provider.PKIXPolicyNode r7 = (org.spongycastle.jce.provider.PKIXPolicyNode) r7
            java.lang.String r9 = r7.getValidPolicy()
            r0 = r23
            boolean r18 = r0.contains(r9)
            if (r18 != 0) goto L_0x013d
            r0 = r26
            r1 = r25
            org.spongycastle.jce.provider.PKIXPolicyNode r26 = org.spongycastle.jce.provider.CertPathValidatorUtilities.removePolicyNode(r0, r1, r7)
            goto L_0x013d
        L_0x015e:
            if (r26 == 0) goto L_0x018d
            int r13 = r15 + -1
        L_0x0162:
            if (r13 < 0) goto L_0x018d
            r17 = r25[r13]
            r14 = 0
        L_0x0167:
            int r18 = r17.size()
            r0 = r18
            if (r14 >= r0) goto L_0x018a
            r0 = r17
            java.lang.Object r16 = r0.get(r14)
            org.spongycastle.jce.provider.PKIXPolicyNode r16 = (org.spongycastle.jce.provider.PKIXPolicyNode) r16
            boolean r18 = r16.hasChildren()
            if (r18 != 0) goto L_0x0187
            r0 = r26
            r1 = r25
            r2 = r16
            org.spongycastle.jce.provider.PKIXPolicyNode r26 = org.spongycastle.jce.provider.CertPathValidatorUtilities.removePolicyNode(r0, r1, r2)
        L_0x0187:
            int r14 = r14 + 1
            goto L_0x0167
        L_0x018a:
            int r13 = r13 + -1
            goto L_0x0162
        L_0x018d:
            r12 = r26
            goto L_0x0026
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.provider.RFC3280CertPathUtilities.wrapupCertG(java.security.cert.CertPath, org.spongycastle.jcajce.PKIXExtendedParameters, java.util.Set, int, java.util.List[], org.spongycastle.jce.provider.PKIXPolicyNode, java.util.Set):org.spongycastle.jce.provider.PKIXPolicyNode");
    }
}
