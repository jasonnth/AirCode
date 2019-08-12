package org.spongycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.cert.CRLException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyQualifierInfo;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.DSAPublicKeySpec;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Enumerated;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1OutputStream;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.isismtt.ISISMTTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.CRLDistPoint;
import org.spongycastle.asn1.x509.DistributionPoint;
import org.spongycastle.asn1.x509.DistributionPointName;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.IssuingDistributionPoint;
import org.spongycastle.asn1.x509.PolicyInformation;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.jcajce.PKIXCertStoreSelector;
import org.spongycastle.jcajce.provider.asymmetric.x509.CertificateFactory;
import org.spongycastle.jce.X509LDAPCertStoreParameters;
import org.spongycastle.jce.X509LDAPCertStoreParameters.Builder;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.jce.provider.AnnotatedException;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.provider.PKIXPolicyNode;
import org.spongycastle.util.Encodable;
import org.spongycastle.util.Integers;
import org.spongycastle.util.Store;
import org.spongycastle.util.StoreException;

class CertPathValidatorUtilities {
    protected static final String ANY_POLICY = "2.5.29.32.0";
    protected static final String AUTHORITY_KEY_IDENTIFIER = Extension.authorityKeyIdentifier.getId();
    protected static final String BASIC_CONSTRAINTS = Extension.basicConstraints.getId();
    protected static final String CERTIFICATE_POLICIES = Extension.certificatePolicies.getId();
    protected static final String CRL_DISTRIBUTION_POINTS = Extension.cRLDistributionPoints.getId();
    protected static final String CRL_NUMBER = Extension.cRLNumber.getId();
    protected static final int CRL_SIGN = 6;
    protected static final PKIXCRLUtil CRL_UTIL = new PKIXCRLUtil();
    protected static final String DELTA_CRL_INDICATOR = Extension.deltaCRLIndicator.getId();
    protected static final String FRESHEST_CRL = Extension.freshestCRL.getId();
    protected static final String INHIBIT_ANY_POLICY = Extension.inhibitAnyPolicy.getId();
    protected static final String ISSUING_DISTRIBUTION_POINT = Extension.issuingDistributionPoint.getId();
    protected static final int KEY_CERT_SIGN = 5;
    protected static final String KEY_USAGE = Extension.keyUsage.getId();
    protected static final String NAME_CONSTRAINTS = Extension.nameConstraints.getId();
    protected static final String POLICY_CONSTRAINTS = Extension.policyConstraints.getId();
    protected static final String POLICY_MAPPINGS = Extension.policyMappings.getId();
    protected static final String SUBJECT_ALTERNATIVE_NAME = Extension.subjectAlternativeName.getId();
    protected static final String[] crlReasons = {"unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", "unknown", "removeFromCRL", "privilegeWithdrawn", "aACompromise"};

    CertPathValidatorUtilities() {
    }

    protected static TrustAnchor findTrustAnchor(X509Certificate cert, Set trustAnchors) throws AnnotatedException {
        return findTrustAnchor(cert, trustAnchors, null);
    }

    protected static TrustAnchor findTrustAnchor(X509Certificate cert, Set trustAnchors, String sigProvider) throws AnnotatedException {
        TrustAnchor trust = null;
        PublicKey trustPublicKey = null;
        Exception invalidKeyEx = null;
        X509CertSelector certSelectX509 = new X509CertSelector();
        X500Principal certIssuer = getEncodedIssuerPrincipal(cert);
        try {
            certSelectX509.setSubject(certIssuer.getEncoded());
            Iterator iter = trustAnchors.iterator();
            while (iter.hasNext() && trust == null) {
                trust = (TrustAnchor) iter.next();
                if (trust.getTrustedCert() != null) {
                    if (certSelectX509.match(trust.getTrustedCert())) {
                        trustPublicKey = trust.getTrustedCert().getPublicKey();
                    } else {
                        trust = null;
                    }
                } else if (trust.getCAName() == null || trust.getCAPublicKey() == null) {
                    trust = null;
                } else {
                    try {
                        if (certIssuer.equals(new X500Principal(trust.getCAName()))) {
                            trustPublicKey = trust.getCAPublicKey();
                        } else {
                            trust = null;
                        }
                    } catch (IllegalArgumentException e) {
                        trust = null;
                    }
                }
                if (trustPublicKey != null) {
                    try {
                        verifyX509Certificate(cert, trustPublicKey, sigProvider);
                    } catch (Exception ex) {
                        invalidKeyEx = ex;
                        trust = null;
                        trustPublicKey = null;
                    }
                }
            }
            if (trust != null || invalidKeyEx == null) {
                return trust;
            }
            throw new AnnotatedException("TrustAnchor found but certificate validation failed.", invalidKeyEx);
        } catch (IOException ex2) {
            throw new AnnotatedException("Cannot set subject search criteria for trust anchor.", ex2);
        }
    }

    protected static void addAdditionalStoresFromAltNames(X509Certificate cert, ExtendedPKIXParameters pkixParams) throws CertificateParsingException {
        if (cert.getIssuerAlternativeNames() != null) {
            for (List list : cert.getIssuerAlternativeNames()) {
                if (list.get(0).equals(Integers.valueOf(6))) {
                    addAdditionalStoreFromLocation((String) list.get(1), pkixParams);
                }
            }
        }
    }

    protected static X500Principal getEncodedIssuerPrincipal(Object cert) {
        if (cert instanceof X509Certificate) {
            return ((X509Certificate) cert).getIssuerX500Principal();
        }
        return (X500Principal) ((X509AttributeCertificate) cert).getIssuer().getPrincipals()[0];
    }

    protected static Date getValidDate(PKIXParameters paramsPKIX) {
        Date validDate = paramsPKIX.getDate();
        if (validDate == null) {
            return new Date();
        }
        return validDate;
    }

    protected static X500Principal getSubjectPrincipal(X509Certificate cert) {
        return cert.getSubjectX500Principal();
    }

    protected static boolean isSelfIssued(X509Certificate cert) {
        return cert.getSubjectDN().equals(cert.getIssuerDN());
    }

    protected static ASN1Primitive getExtensionValue(X509Extension ext, String oid) throws AnnotatedException {
        byte[] bytes = ext.getExtensionValue(oid);
        if (bytes == null) {
            return null;
        }
        return getObject(oid, bytes);
    }

    private static ASN1Primitive getObject(String oid, byte[] ext) throws AnnotatedException {
        try {
            return new ASN1InputStream(((ASN1OctetString) new ASN1InputStream(ext).readObject()).getOctets()).readObject();
        } catch (Exception e) {
            throw new AnnotatedException("exception processing extension " + oid, e);
        }
    }

    protected static X500Principal getIssuerPrincipal(X509CRL crl) {
        return crl.getIssuerX500Principal();
    }

    protected static AlgorithmIdentifier getAlgorithmIdentifier(PublicKey key) throws CertPathValidatorException {
        try {
            return SubjectPublicKeyInfo.getInstance(new ASN1InputStream(key.getEncoded()).readObject()).getAlgorithmId();
        } catch (Exception e) {
            throw new ExtCertPathValidatorException("Subject public key cannot be decoded.", e);
        }
    }

    protected static final Set getQualifierSet(ASN1Sequence qualifiers) throws CertPathValidatorException {
        Set pq = new HashSet();
        if (qualifiers != null) {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            ASN1OutputStream aOut = new ASN1OutputStream(bOut);
            Enumeration e = qualifiers.getObjects();
            while (e.hasMoreElements()) {
                try {
                    aOut.writeObject((ASN1Encodable) e.nextElement());
                    pq.add(new PolicyQualifierInfo(bOut.toByteArray()));
                    bOut.reset();
                } catch (IOException ex) {
                    throw new ExtCertPathValidatorException("Policy qualifier info cannot be decoded.", ex);
                }
            }
        }
        return pq;
    }

    protected static PKIXPolicyNode removePolicyNode(PKIXPolicyNode validPolicyTree, List[] policyNodes, PKIXPolicyNode _node) {
        PKIXPolicyNode _parent = (PKIXPolicyNode) _node.getParent();
        if (validPolicyTree == null) {
            return null;
        }
        if (_parent == null) {
            for (int j = 0; j < policyNodes.length; j++) {
                policyNodes[j] = new ArrayList();
            }
            return null;
        }
        _parent.removeChild(_node);
        removePolicyNodeRecurse(policyNodes, _node);
        return validPolicyTree;
    }

    private static void removePolicyNodeRecurse(List[] policyNodes, PKIXPolicyNode _node) {
        policyNodes[_node.getDepth()].remove(_node);
        if (_node.hasChildren()) {
            Iterator _iter = _node.getChildren();
            while (_iter.hasNext()) {
                removePolicyNodeRecurse(policyNodes, (PKIXPolicyNode) _iter.next());
            }
        }
    }

    protected static boolean processCertD1i(int index, List[] policyNodes, ASN1ObjectIdentifier pOid, Set pq) {
        List policyNodeVec = policyNodes[index - 1];
        for (int j = 0; j < policyNodeVec.size(); j++) {
            PKIXPolicyNode node = (PKIXPolicyNode) policyNodeVec.get(j);
            if (node.getExpectedPolicies().contains(pOid.getId())) {
                Set childExpectedPolicies = new HashSet();
                childExpectedPolicies.add(pOid.getId());
                PKIXPolicyNode child = new PKIXPolicyNode(new ArrayList(), index, childExpectedPolicies, node, pq, pOid.getId(), false);
                node.addChild(child);
                policyNodes[index].add(child);
                return true;
            }
        }
        return false;
    }

    protected static void processCertD1ii(int index, List[] policyNodes, ASN1ObjectIdentifier _poid, Set _pq) {
        List policyNodeVec = policyNodes[index - 1];
        for (int j = 0; j < policyNodeVec.size(); j++) {
            PKIXPolicyNode _node = (PKIXPolicyNode) policyNodeVec.get(j);
            if ("2.5.29.32.0".equals(_node.getValidPolicy())) {
                Set _childExpectedPolicies = new HashSet();
                _childExpectedPolicies.add(_poid.getId());
                PKIXPolicyNode _child = new PKIXPolicyNode(new ArrayList(), index, _childExpectedPolicies, _node, _pq, _poid.getId(), false);
                _node.addChild(_child);
                policyNodes[index].add(_child);
                return;
            }
        }
    }

    protected static void prepareNextCertB1(int i, List[] policyNodes, String id_p, Map m_idp, X509Certificate cert) throws AnnotatedException, CertPathValidatorException {
        boolean idp_found = false;
        Iterator nodes_i = policyNodes[i].iterator();
        while (true) {
            if (!nodes_i.hasNext()) {
                break;
            }
            PKIXPolicyNode node = (PKIXPolicyNode) nodes_i.next();
            if (node.getValidPolicy().equals(id_p)) {
                idp_found = true;
                node.setExpectedPolicies((Set) m_idp.get(id_p));
                break;
            }
        }
        if (!idp_found) {
            for (PKIXPolicyNode node2 : policyNodes[i]) {
                if ("2.5.29.32.0".equals(node2.getValidPolicy())) {
                    Set pq = null;
                    try {
                        Enumeration e = DERSequence.getInstance(getExtensionValue(cert, CERTIFICATE_POLICIES)).getObjects();
                        while (true) {
                            if (!e.hasMoreElements()) {
                                break;
                            }
                            try {
                                PolicyInformation pinfo = PolicyInformation.getInstance(e.nextElement());
                                if ("2.5.29.32.0".equals(pinfo.getPolicyIdentifier().getId())) {
                                    try {
                                        pq = getQualifierSet(pinfo.getPolicyQualifiers());
                                        break;
                                    } catch (CertPathValidatorException ex) {
                                        throw new ExtCertPathValidatorException("Policy qualifier info set could not be built.", ex);
                                    }
                                }
                            } catch (Exception ex2) {
                                throw new AnnotatedException("Policy information cannot be decoded.", ex2);
                            }
                        }
                        boolean ci = false;
                        if (cert.getCriticalExtensionOIDs() != null) {
                            ci = cert.getCriticalExtensionOIDs().contains(CERTIFICATE_POLICIES);
                        }
                        PKIXPolicyNode p_node = (PKIXPolicyNode) node2.getParent();
                        if ("2.5.29.32.0".equals(p_node.getValidPolicy())) {
                            PKIXPolicyNode c_node = new PKIXPolicyNode(new ArrayList(), i, (Set) m_idp.get(id_p), p_node, pq, id_p, ci);
                            p_node.addChild(c_node);
                            policyNodes[i].add(c_node);
                            return;
                        }
                        return;
                    } catch (Exception e2) {
                        throw new AnnotatedException("Certificate policies cannot be decoded.", e2);
                    }
                }
            }
        }
    }

    protected static PKIXPolicyNode prepareNextCertB2(int i, List[] policyNodes, String id_p, PKIXPolicyNode validPolicyTree) {
        Iterator nodes_i = policyNodes[i].iterator();
        while (nodes_i.hasNext()) {
            PKIXPolicyNode node = (PKIXPolicyNode) nodes_i.next();
            if (node.getValidPolicy().equals(id_p)) {
                ((PKIXPolicyNode) node.getParent()).removeChild(node);
                nodes_i.remove();
                for (int k = i - 1; k >= 0; k--) {
                    List nodes = policyNodes[k];
                    for (int l = 0; l < nodes.size(); l++) {
                        PKIXPolicyNode node2 = (PKIXPolicyNode) nodes.get(l);
                        if (!node2.hasChildren()) {
                            validPolicyTree = removePolicyNode(validPolicyTree, policyNodes, node2);
                            if (validPolicyTree == null) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return validPolicyTree;
    }

    protected static boolean isAnyPolicy(Set policySet) {
        return policySet == null || policySet.contains("2.5.29.32.0") || policySet.isEmpty();
    }

    protected static void addAdditionalStoreFromLocation(String location, ExtendedPKIXParameters pkixParams) {
        String url;
        if (pkixParams.isAdditionalLocationsEnabled()) {
            try {
                if (location.startsWith("ldap://")) {
                    String location2 = location.substring(7);
                    String base = null;
                    if (location2.indexOf("/") != -1) {
                        base = location2.substring(location2.indexOf("/"));
                        url = "ldap://" + location2.substring(0, location2.indexOf("/"));
                    } else {
                        url = "ldap://" + location2;
                    }
                    X509LDAPCertStoreParameters params = new Builder(url, base).build();
                    pkixParams.addAdditionalStore(X509Store.getInstance("CERTIFICATE/LDAP", (X509StoreParameters) params, BouncyCastleProvider.PROVIDER_NAME));
                    pkixParams.addAdditionalStore(X509Store.getInstance("CRL/LDAP", (X509StoreParameters) params, BouncyCastleProvider.PROVIDER_NAME));
                    pkixParams.addAdditionalStore(X509Store.getInstance("ATTRIBUTECERTIFICATE/LDAP", (X509StoreParameters) params, BouncyCastleProvider.PROVIDER_NAME));
                    pkixParams.addAdditionalStore(X509Store.getInstance("CERTIFICATEPAIR/LDAP", (X509StoreParameters) params, BouncyCastleProvider.PROVIDER_NAME));
                }
            } catch (Exception e) {
                throw new RuntimeException("Exception adding X.509 stores.");
            }
        }
    }

    protected static Collection findCertificates(X509CertStoreSelector certSelect, List certStores) throws AnnotatedException {
        Set certs = new HashSet();
        CertificateFactory certFact = new CertificateFactory();
        for (Object obj : certStores) {
            if (obj instanceof Store) {
                try {
                    for (Object cert : ((Store) obj).getMatches(certSelect)) {
                        if (cert instanceof Encodable) {
                            certs.add(certFact.engineGenerateCertificate(new ByteArrayInputStream(((Encodable) cert).getEncoded())));
                        } else if (cert instanceof Certificate) {
                            certs.add(cert);
                        } else {
                            throw new AnnotatedException("Unknown object found in certificate store.");
                        }
                    }
                    continue;
                } catch (StoreException e) {
                    throw new AnnotatedException("Problem while picking certificates from X.509 store.", e);
                } catch (IOException e2) {
                    throw new AnnotatedException("Problem while extracting certificates from X.509 store.", e2);
                } catch (CertificateException e3) {
                    throw new AnnotatedException("Problem while extracting certificates from X.509 store.", e3);
                }
            } else {
                try {
                    certs.addAll(((CertStore) obj).getCertificates(certSelect));
                } catch (CertStoreException e4) {
                    throw new AnnotatedException("Problem while picking certificates from certificate store.", e4);
                }
            }
        }
        return certs;
    }

    protected static Collection findCertificates(PKIXCertStoreSelector certSelect, List certStores) throws AnnotatedException {
        Set certs = new HashSet();
        for (Object obj : certStores) {
            if (obj instanceof Store) {
                try {
                    certs.addAll(((Store) obj).getMatches(certSelect));
                } catch (StoreException e) {
                    throw new AnnotatedException("Problem while picking certificates from X.509 store.", e);
                }
            } else {
                try {
                    certs.addAll(PKIXCertStoreSelector.getCertificates(certSelect, (CertStore) obj));
                } catch (CertStoreException e2) {
                    throw new AnnotatedException("Problem while picking certificates from certificate store.", e2);
                }
            }
        }
        return certs;
    }

    protected static Collection findCertificates(X509AttributeCertStoreSelector certSelect, List certStores) throws AnnotatedException {
        Set certs = new HashSet();
        for (Object obj : certStores) {
            if (obj instanceof X509Store) {
                try {
                    certs.addAll(((X509Store) obj).getMatches(certSelect));
                } catch (StoreException e) {
                    throw new AnnotatedException("Problem while picking certificates from X.509 store.", e);
                }
            }
        }
        return certs;
    }

    protected static void addAdditionalStoresFromCRLDistributionPoint(CRLDistPoint crldp, ExtendedPKIXParameters pkixParams) throws AnnotatedException {
        if (crldp != null) {
            try {
                DistributionPoint[] dps = crldp.getDistributionPoints();
                for (DistributionPoint distributionPoint : dps) {
                    DistributionPointName dpn = distributionPoint.getDistributionPoint();
                    if (dpn != null && dpn.getType() == 0) {
                        GeneralName[] genNames = GeneralNames.getInstance(dpn.getName()).getNames();
                        for (int j = 0; j < genNames.length; j++) {
                            if (genNames[j].getTagNo() == 6) {
                                addAdditionalStoreFromLocation(DERIA5String.getInstance(genNames[j].getName()).getString(), pkixParams);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new AnnotatedException("Distribution points could not be read.", e);
            }
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Collection, code=java.util.Collection<javax.security.auth.x500.X500Principal>, for r9v0, types: [java.util.Collection, java.util.Collection<javax.security.auth.x500.X500Principal>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void getCRLIssuersFromDistributionPoint(org.spongycastle.asn1.x509.DistributionPoint r8, java.util.Collection<javax.security.auth.x500.X500Principal> r9, java.security.cert.X509CRLSelector r10, org.spongycastle.x509.ExtendedPKIXParameters r11) throws org.spongycastle.jce.provider.AnnotatedException {
        /*
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            org.spongycastle.asn1.x509.GeneralNames r6 = r8.getCRLIssuer()
            if (r6 == 0) goto L_0x0043
            org.spongycastle.asn1.x509.GeneralNames r6 = r8.getCRLIssuer()
            org.spongycastle.asn1.x509.GeneralName[] r2 = r6.getNames()
            r5 = 0
        L_0x0014:
            int r6 = r2.length
            if (r5 >= r6) goto L_0x0066
            r6 = r2[r5]
            int r6 = r6.getTagNo()
            r7 = 4
            if (r6 != r7) goto L_0x0036
            javax.security.auth.x500.X500Principal r6 = new javax.security.auth.x500.X500Principal     // Catch:{ IOException -> 0x0039 }
            r7 = r2[r5]     // Catch:{ IOException -> 0x0039 }
            org.spongycastle.asn1.ASN1Encodable r7 = r7.getName()     // Catch:{ IOException -> 0x0039 }
            org.spongycastle.asn1.ASN1Primitive r7 = r7.toASN1Primitive()     // Catch:{ IOException -> 0x0039 }
            byte[] r7 = r7.getEncoded()     // Catch:{ IOException -> 0x0039 }
            r6.<init>(r7)     // Catch:{ IOException -> 0x0039 }
            r3.add(r6)     // Catch:{ IOException -> 0x0039 }
        L_0x0036:
            int r5 = r5 + 1
            goto L_0x0014
        L_0x0039:
            r0 = move-exception
            org.spongycastle.jce.provider.AnnotatedException r6 = new org.spongycastle.jce.provider.AnnotatedException
            java.lang.String r7 = "CRL issuer information from distribution point cannot be decoded."
            r6.<init>(r7, r0)
            throw r6
        L_0x0043:
            org.spongycastle.asn1.x509.DistributionPointName r6 = r8.getDistributionPoint()
            if (r6 != 0) goto L_0x0052
            org.spongycastle.jce.provider.AnnotatedException r6 = new org.spongycastle.jce.provider.AnnotatedException
            java.lang.String r7 = "CRL issuer is omitted from distribution point but no distributionPoint field present."
            r6.<init>(r7)
            throw r6
        L_0x0052:
            java.util.Iterator r4 = r9.iterator()
        L_0x0056:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x0066
            java.lang.Object r6 = r4.next()
            javax.security.auth.x500.X500Principal r6 = (javax.security.auth.x500.X500Principal) r6
            r3.add(r6)
            goto L_0x0056
        L_0x0066:
            java.util.Iterator r4 = r3.iterator()
        L_0x006a:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x0088
            java.lang.Object r6 = r4.next()     // Catch:{ IOException -> 0x007e }
            javax.security.auth.x500.X500Principal r6 = (javax.security.auth.x500.X500Principal) r6     // Catch:{ IOException -> 0x007e }
            byte[] r6 = r6.getEncoded()     // Catch:{ IOException -> 0x007e }
            r10.addIssuerName(r6)     // Catch:{ IOException -> 0x007e }
            goto L_0x006a
        L_0x007e:
            r1 = move-exception
            org.spongycastle.jce.provider.AnnotatedException r6 = new org.spongycastle.jce.provider.AnnotatedException
            java.lang.String r7 = "Cannot decode CRL issuer information."
            r6.<init>(r7, r1)
            throw r6
        L_0x0088:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.CertPathValidatorUtilities.getCRLIssuersFromDistributionPoint(org.spongycastle.asn1.x509.DistributionPoint, java.util.Collection, java.security.cert.X509CRLSelector, org.spongycastle.x509.ExtendedPKIXParameters):void");
    }

    private static BigInteger getSerialNumber(Object cert) {
        if (cert instanceof X509Certificate) {
            return ((X509Certificate) cert).getSerialNumber();
        }
        return ((X509AttributeCertificate) cert).getSerialNumber();
    }

    protected static void getCertStatus(Date validDate, X509CRL crl, Object cert, CertStatus certStatus) throws AnnotatedException {
        X509CRLEntry crl_entry;
        try {
            if (isIndirectCRL(crl)) {
                crl_entry = crl.getRevokedCertificate(getSerialNumber(cert));
                if (crl_entry != null) {
                    X500Principal certIssuer = crl_entry.getCertificateIssuer();
                    if (certIssuer == null) {
                        certIssuer = getIssuerPrincipal(crl);
                    }
                    if (!getEncodedIssuerPrincipal(cert).equals(certIssuer)) {
                        return;
                    }
                } else {
                    return;
                }
            } else if (getEncodedIssuerPrincipal(cert).equals(getIssuerPrincipal(crl))) {
                crl_entry = crl.getRevokedCertificate(getSerialNumber(cert));
                if (crl_entry == null) {
                    return;
                }
            } else {
                return;
            }
            ASN1Enumerated reasonCode = null;
            if (crl_entry.hasExtensions()) {
                try {
                    reasonCode = ASN1Enumerated.getInstance(getExtensionValue(crl_entry, org.spongycastle.asn1.x509.X509Extension.reasonCode.getId()));
                } catch (Exception e) {
                    throw new AnnotatedException("Reason code CRL entry extension could not be decoded.", e);
                }
            }
            if (validDate.getTime() >= crl_entry.getRevocationDate().getTime() || reasonCode == null || reasonCode.getValue().intValue() == 0 || reasonCode.getValue().intValue() == 1 || reasonCode.getValue().intValue() == 2 || reasonCode.getValue().intValue() == 8) {
                if (reasonCode != null) {
                    certStatus.setCertStatus(reasonCode.getValue().intValue());
                } else {
                    certStatus.setCertStatus(0);
                }
                certStatus.setRevocationDate(crl_entry.getRevocationDate());
            }
        } catch (CRLException exception) {
            throw new AnnotatedException("Failed check for indirect CRL.", exception);
        }
    }

    protected static Set getDeltaCRLs(Date currentDate, ExtendedPKIXParameters paramsPKIX, X509CRL completeCRL) throws AnnotatedException {
        BigInteger add;
        X509CRLStoreSelector deltaSelect = new X509CRLStoreSelector();
        try {
            deltaSelect.addIssuerName(getIssuerPrincipal(completeCRL).getEncoded());
            BigInteger completeCRLNumber = null;
            try {
                ASN1Primitive derObject = getExtensionValue(completeCRL, CRL_NUMBER);
                if (derObject != null) {
                    completeCRLNumber = ASN1Integer.getInstance(derObject).getPositiveValue();
                }
                try {
                    byte[] idp = completeCRL.getExtensionValue(ISSUING_DISTRIBUTION_POINT);
                    if (completeCRLNumber == null) {
                        add = null;
                    } else {
                        add = completeCRLNumber.add(BigInteger.valueOf(1));
                    }
                    deltaSelect.setMinCRLNumber(add);
                    deltaSelect.setIssuingDistributionPoint(idp);
                    deltaSelect.setIssuingDistributionPointEnabled(true);
                    deltaSelect.setMaxBaseCRLNumber(completeCRLNumber);
                    Set<X509CRL> temp = CRL_UTIL.findCRLs(deltaSelect, paramsPKIX, currentDate);
                    Set result = new HashSet();
                    for (X509CRL crl : temp) {
                        if (isDeltaCRL(crl)) {
                            result.add(crl);
                        }
                    }
                    return result;
                } catch (Exception e) {
                    throw new AnnotatedException("Issuing distribution point extension value could not be read.", e);
                }
            } catch (Exception e2) {
                throw new AnnotatedException("CRL number extension could not be extracted from CRL.", e2);
            }
        } catch (IOException e3) {
            throw new AnnotatedException("Cannot extract issuer from CRL.", e3);
        }
    }

    private static boolean isDeltaCRL(X509CRL crl) {
        Set critical = crl.getCriticalExtensionOIDs();
        if (critical == null) {
            return false;
        }
        return critical.contains(Extension.deltaCRLIndicator.getId());
    }

    protected static Set getCompleteCRLs(DistributionPoint dp, Object cert, Date currentDate, ExtendedPKIXParameters paramsPKIX) throws AnnotatedException {
        X509CRLStoreSelector crlselect = new X509CRLStoreSelector();
        try {
            Set issuers = new HashSet();
            if (cert instanceof X509AttributeCertificate) {
                issuers.add(((X509AttributeCertificate) cert).getIssuer().getPrincipals()[0]);
            } else {
                issuers.add(getEncodedIssuerPrincipal(cert));
            }
            getCRLIssuersFromDistributionPoint(dp, issuers, crlselect, paramsPKIX);
            if (cert instanceof X509Certificate) {
                crlselect.setCertificateChecking((X509Certificate) cert);
            } else if (cert instanceof X509AttributeCertificate) {
                crlselect.setAttrCertificateChecking((X509AttributeCertificate) cert);
            }
            crlselect.setCompleteCRLEnabled(true);
            Set crls = CRL_UTIL.findCRLs(crlselect, paramsPKIX, currentDate);
            if (!crls.isEmpty()) {
                return crls;
            }
            if (cert instanceof X509AttributeCertificate) {
                throw new AnnotatedException("No CRLs found for issuer \"" + ((X509AttributeCertificate) cert).getIssuer().getPrincipals()[0] + "\"");
            }
            throw new AnnotatedException("No CRLs found for issuer \"" + ((X509Certificate) cert).getIssuerX500Principal() + "\"");
        } catch (AnnotatedException e) {
            throw new AnnotatedException("Could not get issuer information from distribution point.", e);
        }
    }

    protected static Date getValidCertDateFromValidityModel(ExtendedPKIXParameters paramsPKIX, CertPath certPath, int index) throws AnnotatedException {
        if (paramsPKIX.getValidityModel() != 1) {
            return getValidDate(paramsPKIX);
        }
        if (index <= 0) {
            return getValidDate(paramsPKIX);
        }
        if (index - 1 != 0) {
            return ((X509Certificate) certPath.getCertificates().get(index - 1)).getNotBefore();
        }
        ASN1GeneralizedTime dateOfCertgen = null;
        try {
            byte[] extBytes = ((X509Certificate) certPath.getCertificates().get(index - 1)).getExtensionValue(ISISMTTObjectIdentifiers.id_isismtt_at_dateOfCertGen.getId());
            if (extBytes != null) {
                dateOfCertgen = ASN1GeneralizedTime.getInstance(ASN1Primitive.fromByteArray(extBytes));
            }
            if (dateOfCertgen == null) {
                return ((X509Certificate) certPath.getCertificates().get(index - 1)).getNotBefore();
            }
            try {
                return dateOfCertgen.getDate();
            } catch (ParseException e) {
                throw new AnnotatedException("Date from date of cert gen extension could not be parsed.", e);
            }
        } catch (IOException e2) {
            throw new AnnotatedException("Date of cert gen extension could not be read.");
        } catch (IllegalArgumentException e3) {
            throw new AnnotatedException("Date of cert gen extension could not be read.");
        }
    }

    protected static PublicKey getNextWorkingKey(List certs, int index) throws CertPathValidatorException {
        PublicKey pubKey = ((Certificate) certs.get(index)).getPublicKey();
        if (!(pubKey instanceof DSAPublicKey)) {
            return pubKey;
        }
        DSAPublicKey dsaPubKey = (DSAPublicKey) pubKey;
        if (dsaPubKey.getParams() != null) {
            return dsaPubKey;
        }
        int i = index + 1;
        while (i < certs.size()) {
            PublicKey pubKey2 = ((X509Certificate) certs.get(i)).getPublicKey();
            if (!(pubKey2 instanceof DSAPublicKey)) {
                throw new CertPathValidatorException("DSA parameters cannot be inherited from previous certificate.");
            }
            DSAPublicKey prevDSAPubKey = (DSAPublicKey) pubKey2;
            if (prevDSAPubKey.getParams() == null) {
                i++;
            } else {
                DSAParams dsaParams = prevDSAPubKey.getParams();
                try {
                    return KeyFactory.getInstance("DSA", BouncyCastleProvider.PROVIDER_NAME).generatePublic(new DSAPublicKeySpec(dsaPubKey.getY(), dsaParams.getP(), dsaParams.getQ(), dsaParams.getG()));
                } catch (Exception exception) {
                    throw new RuntimeException(exception.getMessage());
                }
            }
        }
        throw new CertPathValidatorException("DSA parameters cannot be inherited from previous certificate.");
    }

    static Collection findIssuerCerts(X509Certificate cert, List certStores, List pkixCertStores) throws AnnotatedException {
        X509CertSelector selector = new X509CertSelector();
        try {
            selector.setSubject(cert.getIssuerX500Principal().getEncoded());
            PKIXCertStoreSelector certSelect = new PKIXCertStoreSelector.Builder(selector).build();
            Set certs = new HashSet();
            try {
                List<X509Certificate> matches = new ArrayList<>();
                matches.addAll(findCertificates(certSelect, certStores));
                matches.addAll(findCertificates(certSelect, pkixCertStores));
                for (X509Certificate issuer : matches) {
                    certs.add(issuer);
                }
                return certs;
            } catch (AnnotatedException e) {
                throw new AnnotatedException("Issuer certificate cannot be searched.", e);
            }
        } catch (IOException e2) {
            throw new AnnotatedException("Subject criteria for certificate selector to find issuer certificate could not be set.", e2);
        }
    }

    protected static void verifyX509Certificate(X509Certificate cert, PublicKey publicKey, String sigProvider) throws GeneralSecurityException {
        if (sigProvider == null) {
            cert.verify(publicKey);
        } else {
            cert.verify(publicKey, sigProvider);
        }
    }

    static boolean isIndirectCRL(X509CRL crl) throws CRLException {
        try {
            byte[] idp = crl.getExtensionValue(Extension.issuingDistributionPoint.getId());
            return idp != null && IssuingDistributionPoint.getInstance(ASN1OctetString.getInstance(idp).getOctets()).isIndirectCRL();
        } catch (Exception e) {
            throw new CRLException("Exception reading IssuingDistributionPoint: " + e);
        }
    }
}
