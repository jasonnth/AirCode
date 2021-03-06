package org.spongycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.jcajce.PKIXExtendedBuilderParameters;
import org.spongycastle.jcajce.PKIXExtendedParameters;
import org.spongycastle.jcajce.PKIXExtendedParameters.Builder;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.x509.ExtendedPKIXParameters;

public class PKIXCertPathValidatorSpi extends CertPathValidatorSpi {
    private final JcaJceHelper helper = new BCJcaJceHelper();

    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters params) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters paramsPKIX;
        int explicitPolicy;
        int inhibitAnyPolicy;
        int policyMapping;
        X500Name workingIssuerName;
        PublicKey workingPublicKey;
        HashSet hashSet;
        HashSet hashSet2;
        if (params instanceof PKIXParameters) {
            Builder builder = new Builder((PKIXParameters) params);
            if (params instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extPKIX = (ExtendedPKIXParameters) params;
                builder.setUseDeltasEnabled(extPKIX.isUseDeltasEnabled());
                builder.setValidityModel(extPKIX.getValidityModel());
            }
            paramsPKIX = builder.build();
        } else if (params instanceof PKIXExtendedBuilderParameters) {
            paramsPKIX = ((PKIXExtendedBuilderParameters) params).getBaseParameters();
        } else if (params instanceof PKIXExtendedParameters) {
            paramsPKIX = (PKIXExtendedParameters) params;
        } else {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
        }
        if (paramsPKIX.getTrustAnchors() == null) {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
        List certs = certPath.getCertificates();
        int n = certs.size();
        if (certs.isEmpty()) {
            throw new CertPathValidatorException("Certification path is empty.", null, certPath, -1);
        }
        Set userInitialPolicySet = paramsPKIX.getInitialPolicies();
        try {
            TrustAnchor trust = CertPathValidatorUtilities.findTrustAnchor((X509Certificate) certs.get(certs.size() - 1), paramsPKIX.getTrustAnchors(), paramsPKIX.getSigProvider());
            if (trust == null) {
                throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath, -1);
            }
            PKIXExtendedParameters paramsPKIX2 = new Builder(paramsPKIX).setTrustAnchor(trust).build();
            ArrayList[] arrayListArr = new ArrayList[(n + 1)];
            for (int j = 0; j < arrayListArr.length; j++) {
                arrayListArr[j] = new ArrayList();
            }
            Set policySet = new HashSet();
            policySet.add(RFC3280CertPathUtilities.ANY_POLICY);
            PKIXPolicyNode validPolicyTree = new PKIXPolicyNode(new ArrayList(), 0, policySet, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
            arrayListArr[0].add(validPolicyTree);
            PKIXNameConstraintValidator nameConstraintValidator = new PKIXNameConstraintValidator();
            HashSet hashSet3 = new HashSet();
            if (paramsPKIX2.isExplicitPolicyRequired()) {
                explicitPolicy = 0;
            } else {
                explicitPolicy = n + 1;
            }
            if (paramsPKIX2.isAnyPolicyInhibited()) {
                inhibitAnyPolicy = 0;
            } else {
                inhibitAnyPolicy = n + 1;
            }
            if (paramsPKIX2.isPolicyMappingInhibited()) {
                policyMapping = 0;
            } else {
                policyMapping = n + 1;
            }
            X509Certificate sign = trust.getTrustedCert();
            if (sign != null) {
                try {
                    workingIssuerName = PrincipalUtils.getSubjectPrincipal(sign);
                    workingPublicKey = sign.getPublicKey();
                } catch (IllegalArgumentException ex) {
                    throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", ex, certPath, -1);
                }
            } else {
                workingIssuerName = PrincipalUtils.getCA(trust);
                workingPublicKey = trust.getCAPublicKey();
            }
            try {
                AlgorithmIdentifier workingAlgId = CertPathValidatorUtilities.getAlgorithmIdentifier(workingPublicKey);
                ASN1ObjectIdentifier algorithm = workingAlgId.getAlgorithm();
                ASN1Encodable parameters = workingAlgId.getParameters();
                int maxPathLength = n;
                if (paramsPKIX2.getTargetConstraints() == null || paramsPKIX2.getTargetConstraints().match((Certificate) (X509Certificate) certs.get(0))) {
                    List<PKIXCertPathChecker> pathCheckers = paramsPKIX2.getCertPathCheckers();
                    for (PKIXCertPathChecker init : pathCheckers) {
                        init.init(false);
                    }
                    X509Certificate cert = null;
                    int index = certs.size() - 1;
                    while (index >= 0) {
                        int i = n - index;
                        cert = (X509Certificate) certs.get(index);
                        RFC3280CertPathUtilities.processCertA(certPath, paramsPKIX2, index, workingPublicKey, index == certs.size() + -1, workingIssuerName, sign, this.helper);
                        RFC3280CertPathUtilities.processCertBC(certPath, index, nameConstraintValidator);
                        validPolicyTree = RFC3280CertPathUtilities.processCertE(certPath, index, RFC3280CertPathUtilities.processCertD(certPath, index, hashSet3, validPolicyTree, arrayListArr, inhibitAnyPolicy));
                        RFC3280CertPathUtilities.processCertF(certPath, index, validPolicyTree, explicitPolicy);
                        if (i != n) {
                            if (cert == null || cert.getVersion() != 1) {
                                RFC3280CertPathUtilities.prepareNextCertA(certPath, index);
                                validPolicyTree = RFC3280CertPathUtilities.prepareCertB(certPath, index, arrayListArr, validPolicyTree, policyMapping);
                                RFC3280CertPathUtilities.prepareNextCertG(certPath, index, nameConstraintValidator);
                                int explicitPolicy2 = RFC3280CertPathUtilities.prepareNextCertH1(certPath, index, explicitPolicy);
                                int policyMapping2 = RFC3280CertPathUtilities.prepareNextCertH2(certPath, index, policyMapping);
                                int inhibitAnyPolicy2 = RFC3280CertPathUtilities.prepareNextCertH3(certPath, index, inhibitAnyPolicy);
                                explicitPolicy = RFC3280CertPathUtilities.prepareNextCertI1(certPath, index, explicitPolicy2);
                                policyMapping = RFC3280CertPathUtilities.prepareNextCertI2(certPath, index, policyMapping2);
                                inhibitAnyPolicy = RFC3280CertPathUtilities.prepareNextCertJ(certPath, index, inhibitAnyPolicy2);
                                RFC3280CertPathUtilities.prepareNextCertK(certPath, index);
                                maxPathLength = RFC3280CertPathUtilities.prepareNextCertM(certPath, index, RFC3280CertPathUtilities.prepareNextCertL(certPath, index, maxPathLength));
                                RFC3280CertPathUtilities.prepareNextCertN(certPath, index);
                                Set criticalExtensions = cert.getCriticalExtensionOIDs();
                                if (criticalExtensions != null) {
                                    HashSet hashSet4 = new HashSet(criticalExtensions);
                                    hashSet4.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                    hashSet4.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                    hashSet4.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                    hashSet4.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                    hashSet4.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                    hashSet4.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                    hashSet4.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                    hashSet4.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                    hashSet4.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                    hashSet4.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                    hashSet2 = hashSet4;
                                } else {
                                    hashSet2 = new HashSet();
                                }
                                RFC3280CertPathUtilities.prepareNextCertO(certPath, index, hashSet2, pathCheckers);
                                sign = cert;
                                workingIssuerName = PrincipalUtils.getSubjectPrincipal(sign);
                                try {
                                    workingPublicKey = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), index, this.helper);
                                    AlgorithmIdentifier workingAlgId2 = CertPathValidatorUtilities.getAlgorithmIdentifier(workingPublicKey);
                                    ASN1ObjectIdentifier workingPublicKeyAlgorithm = workingAlgId2.getAlgorithm();
                                    ASN1Encodable workingPublicKeyParameters = workingAlgId2.getParameters();
                                } catch (CertPathValidatorException e) {
                                    throw new CertPathValidatorException("Next working key could not be retrieved.", e, certPath, index);
                                }
                            } else {
                                throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, index);
                            }
                        }
                        index--;
                    }
                    int explicitPolicy3 = RFC3280CertPathUtilities.wrapupCertB(certPath, index + 1, RFC3280CertPathUtilities.wrapupCertA(explicitPolicy, cert));
                    Set criticalExtensions2 = cert.getCriticalExtensionOIDs();
                    if (criticalExtensions2 != null) {
                        HashSet hashSet5 = new HashSet(criticalExtensions2);
                        hashSet5.remove(RFC3280CertPathUtilities.KEY_USAGE);
                        hashSet5.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                        hashSet5.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                        hashSet5.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                        hashSet5.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                        hashSet5.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                        hashSet5.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                        hashSet5.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                        hashSet5.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                        hashSet5.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                        hashSet5.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                        hashSet5.remove(Extension.extendedKeyUsage.getId());
                        hashSet = hashSet5;
                    } else {
                        hashSet = new HashSet();
                    }
                    RFC3280CertPathUtilities.wrapupCertF(certPath, index + 1, pathCheckers, hashSet);
                    PKIXPolicyNode intersection = RFC3280CertPathUtilities.wrapupCertG(certPath, paramsPKIX2, userInitialPolicySet, index + 1, arrayListArr, validPolicyTree, hashSet3);
                    if (explicitPolicy3 > 0 || intersection != null) {
                        return new PKIXCertPathValidatorResult(trust, intersection, cert.getPublicKey());
                    }
                    throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, index);
                }
                throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath, 0);
            } catch (CertPathValidatorException e2) {
                throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e2, certPath, -1);
            }
        } catch (AnnotatedException e3) {
            throw new CertPathValidatorException(e3.getMessage(), e3, certPath, certs.size() - 1);
        }
    }
}
