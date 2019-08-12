package org.spongycastle.x509.util;

import com.facebook.appevents.AppEventsConstants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.asn1.x509.CertificatePair;
import org.spongycastle.jce.X509LDAPCertStoreParameters;
import org.spongycastle.jce.provider.X509CertPairParser;
import org.spongycastle.util.StoreException;
import org.spongycastle.x509.X509AttributeCertStoreSelector;
import org.spongycastle.x509.X509CRLStoreSelector;
import org.spongycastle.x509.X509CertPairStoreSelector;
import org.spongycastle.x509.X509CertStoreSelector;
import org.spongycastle.x509.X509CertificatePair;

public class LDAPStoreHelper {
    private static String LDAP_PROVIDER = "com.sun.jndi.ldap.LdapCtxFactory";
    private static String REFERRALS_IGNORE = "ignore";
    private static final String SEARCH_SECURITY_LEVEL = "none";
    private static final String URL_CONTEXT_PREFIX = "com.sun.jndi.url";
    private static int cacheSize = 32;
    private static long lifeTime = 60000;
    private Map cacheMap = new HashMap(cacheSize);
    private X509LDAPCertStoreParameters params;

    public LDAPStoreHelper(X509LDAPCertStoreParameters params2) {
        this.params = params2;
    }

    private DirContext connectLDAP() throws NamingException {
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", LDAP_PROVIDER);
        props.setProperty("java.naming.batchsize", AppEventsConstants.EVENT_PARAM_VALUE_NO);
        props.setProperty("java.naming.provider.url", this.params.getLdapURL());
        props.setProperty("java.naming.factory.url.pkgs", URL_CONTEXT_PREFIX);
        props.setProperty("java.naming.referral", REFERRALS_IGNORE);
        props.setProperty("java.naming.security.authentication", SEARCH_SECURITY_LEVEL);
        return new InitialDirContext(props);
    }

    private String parseDN(String subject, String dNAttributeName) {
        int end;
        String temp = subject;
        int begin = temp.toLowerCase().indexOf(dNAttributeName.toLowerCase() + "=");
        if (begin == -1) {
            return "";
        }
        String temp2 = temp.substring(dNAttributeName.length() + begin);
        int end2 = temp2.indexOf(44);
        if (end2 == -1) {
            end2 = temp2.length();
        }
        while (temp2.charAt(end - 1) == '\\') {
            end = temp2.indexOf(44, end + 1);
            if (end == -1) {
                end = temp2.length();
            }
        }
        String temp3 = temp2.substring(0, end);
        String temp4 = temp3.substring(temp3.indexOf(61) + 1);
        if (temp4.charAt(0) == ' ') {
            temp4 = temp4.substring(1);
        }
        if (temp4.startsWith("\"")) {
            temp4 = temp4.substring(1);
        }
        if (temp4.endsWith("\"")) {
            temp4 = temp4.substring(0, temp4.length() - 1);
        }
        return temp4;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<byte[]>, for r7v0, types: [java.util.List, java.util.List<byte[]>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set createCerts(java.util.List<byte[]> r7, org.spongycastle.x509.X509CertStoreSelector r8) throws org.spongycastle.util.StoreException {
        /*
            r6 = this;
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.util.Iterator r2 = r7.iterator()
            org.spongycastle.jce.provider.X509CertParser r3 = new org.spongycastle.jce.provider.X509CertParser
            r3.<init>()
        L_0x000e:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0036
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0034 }
            java.lang.Object r4 = r2.next()     // Catch:{ Exception -> 0x0034 }
            byte[] r4 = (byte[]) r4     // Catch:{ Exception -> 0x0034 }
            byte[] r4 = (byte[]) r4     // Catch:{ Exception -> 0x0034 }
            r5.<init>(r4)     // Catch:{ Exception -> 0x0034 }
            r3.engineInit(r5)     // Catch:{ Exception -> 0x0034 }
            java.lang.Object r0 = r3.engineRead()     // Catch:{ Exception -> 0x0034 }
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0     // Catch:{ Exception -> 0x0034 }
            boolean r4 = r8.match(r0)     // Catch:{ Exception -> 0x0034 }
            if (r4 == 0) goto L_0x000e
            r1.add(r0)     // Catch:{ Exception -> 0x0034 }
            goto L_0x000e
        L_0x0034:
            r4 = move-exception
            goto L_0x000e
        L_0x0036:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.util.LDAPStoreHelper.createCerts(java.util.List, org.spongycastle.x509.X509CertStoreSelector):java.util.Set");
    }

    private List certSubjectSerialSearch(X509CertStoreSelector xselector, String[] attrs, String[] attrNames, String[] subjectAttributeNames) throws StoreException {
        List list = new ArrayList();
        String serial = null;
        String subject = getSubjectAsString(xselector);
        if (xselector.getSerialNumber() != null) {
            serial = xselector.getSerialNumber().toString();
        }
        if (xselector.getCertificate() != null) {
            subject = xselector.getCertificate().getSubjectX500Principal().getName("RFC1779");
            serial = xselector.getCertificate().getSerialNumber().toString();
        }
        if (subject != null) {
            for (String parseDN : subjectAttributeNames) {
                list.addAll(search(attrNames, "*" + parseDN(subject, parseDN) + "*", attrs));
            }
        }
        if (!(serial == null || this.params.getSearchForSerialNumberIn() == null)) {
            list.addAll(search(splitString(this.params.getSearchForSerialNumberIn()), serial, attrs));
        }
        if (serial == null && subject == null) {
            list.addAll(search(attrNames, "*", attrs));
        }
        return list;
    }

    private List crossCertificatePairSubjectSearch(X509CertPairStoreSelector xselector, String[] attrs, String[] attrNames, String[] subjectAttributeNames) throws StoreException {
        List list = new ArrayList();
        String subject = null;
        if (xselector.getForwardSelector() != null) {
            subject = getSubjectAsString(xselector.getForwardSelector());
        }
        if (!(xselector.getCertPair() == null || xselector.getCertPair().getForward() == null)) {
            subject = xselector.getCertPair().getForward().getSubjectX500Principal().getName("RFC1779");
        }
        if (subject != null) {
            for (String parseDN : subjectAttributeNames) {
                list.addAll(search(attrNames, "*" + parseDN(subject, parseDN) + "*", attrs));
            }
        }
        if (subject == null) {
            list.addAll(search(attrNames, "*", attrs));
        }
        return list;
    }

    private List attrCertSubjectSerialSearch(X509AttributeCertStoreSelector xselector, String[] attrs, String[] attrNames, String[] subjectAttributeNames) throws StoreException {
        List list = new ArrayList();
        String subject = null;
        Collection<String> serials = new HashSet<>();
        Principal[] principals = null;
        if (xselector.getHolder() != null) {
            if (xselector.getHolder().getSerialNumber() != null) {
                serials.add(xselector.getHolder().getSerialNumber().toString());
            }
            if (xselector.getHolder().getEntityNames() != null) {
                principals = xselector.getHolder().getEntityNames();
            }
        }
        if (xselector.getAttributeCert() != null) {
            if (xselector.getAttributeCert().getHolder().getEntityNames() != null) {
                principals = xselector.getAttributeCert().getHolder().getEntityNames();
            }
            serials.add(xselector.getAttributeCert().getSerialNumber().toString());
        }
        if (principals != null) {
            if (principals[0] instanceof X500Principal) {
                subject = ((X500Principal) principals[0]).getName("RFC1779");
            } else {
                subject = principals[0].getName();
            }
        }
        if (xselector.getSerialNumber() != null) {
            serials.add(xselector.getSerialNumber().toString());
        }
        if (subject != null) {
            for (String parseDN : subjectAttributeNames) {
                list.addAll(search(attrNames, "*" + parseDN(subject, parseDN) + "*", attrs));
            }
        }
        if (serials.size() > 0 && this.params.getSearchForSerialNumberIn() != null) {
            for (String serial : serials) {
                list.addAll(search(splitString(this.params.getSearchForSerialNumberIn()), serial, attrs));
            }
        }
        if (serials.size() == 0 && subject == null) {
            list.addAll(search(attrNames, "*", attrs));
        }
        return list;
    }

    private List cRLIssuerSearch(X509CRLStoreSelector xselector, String[] attrs, String[] attrNames, String[] issuerAttributeNames) throws StoreException {
        List list = new ArrayList();
        String issuer = null;
        Collection<X500Principal> issuers = new HashSet<>();
        if (xselector.getIssuers() != null) {
            issuers.addAll(xselector.getIssuers());
        }
        if (xselector.getCertificateChecking() != null) {
            issuers.add(getCertificateIssuer(xselector.getCertificateChecking()));
        }
        if (xselector.getAttrCertificateChecking() != null) {
            Principal[] principals = xselector.getAttrCertificateChecking().getIssuer().getPrincipals();
            for (int i = 0; i < principals.length; i++) {
                if (principals[i] instanceof X500Principal) {
                    issuers.add(principals[i]);
                }
            }
        }
        for (X500Principal name : issuers) {
            issuer = name.getName("RFC1779");
            for (String parseDN : issuerAttributeNames) {
                list.addAll(search(attrNames, "*" + parseDN(issuer, parseDN) + "*", attrs));
            }
        }
        if (issuer == null) {
            list.addAll(search(attrNames, "*", attrs));
        }
        return list;
    }

    /* JADX WARNING: Incorrect condition in loop: B:25:0x0111 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List search(java.lang.String[] r17, java.lang.String r18, java.lang.String[] r19) throws org.spongycastle.util.StoreException {
        /*
            r16 = this;
            r5 = 0
            if (r17 != 0) goto L_0x0031
            r5 = 0
        L_0x0004:
            java.lang.String r6 = ""
            r8 = 0
        L_0x0008:
            r0 = r19
            int r13 = r0.length
            if (r8 >= r13) goto L_0x0096
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.StringBuilder r13 = r13.append(r6)
            java.lang.String r14 = "("
            java.lang.StringBuilder r13 = r13.append(r14)
            r14 = r19[r8]
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r14 = "=*)"
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r6 = r13.toString()
            int r8 = r8 + 1
            goto L_0x0008
        L_0x0031:
            java.lang.String r5 = ""
            java.lang.String r13 = "**"
            r0 = r18
            boolean r13 = r0.equals(r13)
            if (r13 == 0) goto L_0x0042
            java.lang.String r18 = "*"
        L_0x0042:
            r8 = 0
        L_0x0043:
            r0 = r17
            int r13 = r0.length
            if (r8 >= r13) goto L_0x0079
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.StringBuilder r13 = r13.append(r5)
            java.lang.String r14 = "("
            java.lang.StringBuilder r13 = r13.append(r14)
            r14 = r17[r8]
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r14 = "="
            java.lang.StringBuilder r13 = r13.append(r14)
            r0 = r18
            java.lang.StringBuilder r13 = r13.append(r0)
            java.lang.String r14 = ")"
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r5 = r13.toString()
            int r8 = r8 + 1
            goto L_0x0043
        L_0x0079:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "(|"
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.StringBuilder r13 = r13.append(r5)
            java.lang.String r14 = ")"
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r5 = r13.toString()
            goto L_0x0004
        L_0x0096:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "(|"
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.StringBuilder r13 = r13.append(r6)
            java.lang.String r14 = ")"
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r6 = r13.toString()
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "(&"
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.StringBuilder r13 = r13.append(r5)
            java.lang.String r14 = ""
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.StringBuilder r13 = r13.append(r6)
            java.lang.String r14 = ")"
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r7 = r13.toString()
            if (r5 != 0) goto L_0x00da
            r7 = r6
        L_0x00da:
            r0 = r16
            java.util.List r9 = r0.getFromCache(r7)
            if (r9 == 0) goto L_0x00e4
            r10 = r9
        L_0x00e3:
            return r10
        L_0x00e4:
            r3 = 0
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            javax.naming.directory.DirContext r3 = r16.connectLDAP()     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            javax.naming.directory.SearchControls r2 = new javax.naming.directory.SearchControls     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            r2.<init>()     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            r13 = 2
            r2.setSearchScope(r13)     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            r14 = 0
            r2.setCountLimit(r14)     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            r0 = r19
            r2.setReturningAttributes(r0)     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            r0 = r16
            org.spongycastle.jce.X509LDAPCertStoreParameters r13 = r0.params     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            java.lang.String r13 = r13.getBaseDN()     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            javax.naming.NamingEnumeration r11 = r3.search(r13, r7, r2)     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
        L_0x010d:
            boolean r13 = r11.hasMoreElements()     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            if (r13 == 0) goto L_0x0141
            java.lang.Object r12 = r11.next()     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            javax.naming.directory.SearchResult r12 = (javax.naming.directory.SearchResult) r12     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            javax.naming.directory.Attributes r13 = r12.getAttributes()     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            javax.naming.NamingEnumeration r13 = r13.getAll()     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            java.lang.Object r13 = r13.next()     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            javax.naming.directory.Attribute r13 = (javax.naming.directory.Attribute) r13     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            javax.naming.NamingEnumeration r4 = r13.getAll()     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
        L_0x012b:
            boolean r13 = r4.hasMore()     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            if (r13 == 0) goto L_0x010d
            java.lang.Object r13 = r4.next()     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            r9.add(r13)     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            goto L_0x012b
        L_0x0139:
            r13 = move-exception
            if (r3 == 0) goto L_0x013f
            r3.close()     // Catch:{ Exception -> 0x0155 }
        L_0x013f:
            r10 = r9
            goto L_0x00e3
        L_0x0141:
            r0 = r16
            r0.addToCache(r7, r9)     // Catch:{ NamingException -> 0x0139, all -> 0x014e }
            if (r3 == 0) goto L_0x013f
            r3.close()     // Catch:{ Exception -> 0x014c }
            goto L_0x013f
        L_0x014c:
            r13 = move-exception
            goto L_0x013f
        L_0x014e:
            r13 = move-exception
            if (r3 == 0) goto L_0x0154
            r3.close()     // Catch:{ Exception -> 0x0157 }
        L_0x0154:
            throw r13
        L_0x0155:
            r13 = move-exception
            goto L_0x013f
        L_0x0157:
            r14 = move-exception
            goto L_0x0154
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.util.LDAPStoreHelper.search(java.lang.String[], java.lang.String, java.lang.String[]):java.util.List");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<byte[]>, for r7v0, types: [java.util.List, java.util.List<byte[]>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set createCRLs(java.util.List<byte[]> r7, org.spongycastle.x509.X509CRLStoreSelector r8) throws org.spongycastle.util.StoreException {
        /*
            r6 = this;
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            org.spongycastle.jce.provider.X509CRLParser r3 = new org.spongycastle.jce.provider.X509CRLParser
            r3.<init>()
            java.util.Iterator r2 = r7.iterator()
        L_0x000e:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0036
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream     // Catch:{ StreamParsingException -> 0x0034 }
            java.lang.Object r4 = r2.next()     // Catch:{ StreamParsingException -> 0x0034 }
            byte[] r4 = (byte[]) r4     // Catch:{ StreamParsingException -> 0x0034 }
            byte[] r4 = (byte[]) r4     // Catch:{ StreamParsingException -> 0x0034 }
            r5.<init>(r4)     // Catch:{ StreamParsingException -> 0x0034 }
            r3.engineInit(r5)     // Catch:{ StreamParsingException -> 0x0034 }
            java.lang.Object r0 = r3.engineRead()     // Catch:{ StreamParsingException -> 0x0034 }
            java.security.cert.X509CRL r0 = (java.security.cert.X509CRL) r0     // Catch:{ StreamParsingException -> 0x0034 }
            boolean r4 = r8.match(r0)     // Catch:{ StreamParsingException -> 0x0034 }
            if (r4 == 0) goto L_0x000e
            r1.add(r0)     // Catch:{ StreamParsingException -> 0x0034 }
            goto L_0x000e
        L_0x0034:
            r4 = move-exception
            goto L_0x000e
        L_0x0036:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.util.LDAPStoreHelper.createCRLs(java.util.List, org.spongycastle.x509.X509CRLStoreSelector):java.util.Set");
    }

    private Set createCrossCertificatePairs(List list, X509CertPairStoreSelector xselector) throws StoreException {
        X509CertificatePair pair;
        Set certPairSet = new HashSet();
        int i = 0;
        while (i < list.size()) {
            try {
                X509CertPairParser parser = new X509CertPairParser();
                parser.engineInit(new ByteArrayInputStream((byte[]) list.get(i)));
                pair = (X509CertificatePair) parser.engineRead();
            } catch (StreamParsingException e) {
                pair = new X509CertificatePair(new CertificatePair(Certificate.getInstance(new ASN1InputStream((byte[]) list.get(i)).readObject()), Certificate.getInstance(new ASN1InputStream((byte[]) list.get(i + 1)).readObject())));
                i++;
            }
            try {
                if (xselector.match(pair)) {
                    certPairSet.add(pair);
                }
            } catch (IOException | CertificateParsingException e2) {
            }
            i++;
        }
        return certPairSet;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<byte[]>, for r7v0, types: [java.util.List, java.util.List<byte[]>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set createAttributeCertificates(java.util.List<byte[]> r7, org.spongycastle.x509.X509AttributeCertStoreSelector r8) throws org.spongycastle.util.StoreException {
        /*
            r6 = this;
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.util.Iterator r2 = r7.iterator()
            org.spongycastle.jce.provider.X509AttrCertParser r3 = new org.spongycastle.jce.provider.X509AttrCertParser
            r3.<init>()
        L_0x000e:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0036
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream     // Catch:{ StreamParsingException -> 0x0034 }
            java.lang.Object r4 = r2.next()     // Catch:{ StreamParsingException -> 0x0034 }
            byte[] r4 = (byte[]) r4     // Catch:{ StreamParsingException -> 0x0034 }
            byte[] r4 = (byte[]) r4     // Catch:{ StreamParsingException -> 0x0034 }
            r5.<init>(r4)     // Catch:{ StreamParsingException -> 0x0034 }
            r3.engineInit(r5)     // Catch:{ StreamParsingException -> 0x0034 }
            java.lang.Object r0 = r3.engineRead()     // Catch:{ StreamParsingException -> 0x0034 }
            org.spongycastle.x509.X509AttributeCertificate r0 = (org.spongycastle.x509.X509AttributeCertificate) r0     // Catch:{ StreamParsingException -> 0x0034 }
            boolean r4 = r8.match(r0)     // Catch:{ StreamParsingException -> 0x0034 }
            if (r4 == 0) goto L_0x000e
            r1.add(r0)     // Catch:{ StreamParsingException -> 0x0034 }
            goto L_0x000e
        L_0x0034:
            r4 = move-exception
            goto L_0x000e
        L_0x0036:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.util.LDAPStoreHelper.createAttributeCertificates(java.util.List, org.spongycastle.x509.X509AttributeCertStoreSelector):java.util.Set");
    }

    public Collection getAuthorityRevocationLists(X509CRLStoreSelector selector) throws StoreException {
        String[] attrs = splitString(this.params.getAuthorityRevocationListAttribute());
        String[] attrNames = splitString(this.params.getLdapAuthorityRevocationListAttributeName());
        String[] issuerAttributeNames = splitString(this.params.getAuthorityRevocationListIssuerAttributeName());
        Set resultSet = createCRLs(cRLIssuerSearch(selector, attrs, attrNames, issuerAttributeNames), selector);
        if (resultSet.size() == 0) {
            resultSet.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), attrs, attrNames, issuerAttributeNames), selector));
        }
        return resultSet;
    }

    public Collection getAttributeCertificateRevocationLists(X509CRLStoreSelector selector) throws StoreException {
        String[] attrs = splitString(this.params.getAttributeCertificateRevocationListAttribute());
        String[] attrNames = splitString(this.params.getLdapAttributeCertificateRevocationListAttributeName());
        String[] issuerAttributeNames = splitString(this.params.getAttributeCertificateRevocationListIssuerAttributeName());
        Set resultSet = createCRLs(cRLIssuerSearch(selector, attrs, attrNames, issuerAttributeNames), selector);
        if (resultSet.size() == 0) {
            resultSet.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), attrs, attrNames, issuerAttributeNames), selector));
        }
        return resultSet;
    }

    public Collection getAttributeAuthorityRevocationLists(X509CRLStoreSelector selector) throws StoreException {
        String[] attrs = splitString(this.params.getAttributeAuthorityRevocationListAttribute());
        String[] attrNames = splitString(this.params.getLdapAttributeAuthorityRevocationListAttributeName());
        String[] issuerAttributeNames = splitString(this.params.getAttributeAuthorityRevocationListIssuerAttributeName());
        Set resultSet = createCRLs(cRLIssuerSearch(selector, attrs, attrNames, issuerAttributeNames), selector);
        if (resultSet.size() == 0) {
            resultSet.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), attrs, attrNames, issuerAttributeNames), selector));
        }
        return resultSet;
    }

    public Collection getCrossCertificatePairs(X509CertPairStoreSelector selector) throws StoreException {
        String[] attrs = splitString(this.params.getCrossCertificateAttribute());
        String[] attrNames = splitString(this.params.getLdapCrossCertificateAttributeName());
        String[] subjectAttributeNames = splitString(this.params.getCrossCertificateSubjectAttributeName());
        Set resultSet = createCrossCertificatePairs(crossCertificatePairSubjectSearch(selector, attrs, attrNames, subjectAttributeNames), selector);
        if (resultSet.size() == 0) {
            X509CertStoreSelector emptyCertselector = new X509CertStoreSelector();
            X509CertPairStoreSelector emptySelector = new X509CertPairStoreSelector();
            emptySelector.setForwardSelector(emptyCertselector);
            emptySelector.setReverseSelector(emptyCertselector);
            resultSet.addAll(createCrossCertificatePairs(crossCertificatePairSubjectSearch(emptySelector, attrs, attrNames, subjectAttributeNames), selector));
        }
        return resultSet;
    }

    public Collection getUserCertificates(X509CertStoreSelector selector) throws StoreException {
        String[] attrs = splitString(this.params.getUserCertificateAttribute());
        String[] attrNames = splitString(this.params.getLdapUserCertificateAttributeName());
        String[] subjectAttributeNames = splitString(this.params.getUserCertificateSubjectAttributeName());
        Set resultSet = createCerts(certSubjectSerialSearch(selector, attrs, attrNames, subjectAttributeNames), selector);
        if (resultSet.size() == 0) {
            resultSet.addAll(createCerts(certSubjectSerialSearch(new X509CertStoreSelector(), attrs, attrNames, subjectAttributeNames), selector));
        }
        return resultSet;
    }

    public Collection getAACertificates(X509AttributeCertStoreSelector selector) throws StoreException {
        String[] attrs = splitString(this.params.getAACertificateAttribute());
        String[] attrNames = splitString(this.params.getLdapAACertificateAttributeName());
        String[] subjectAttributeNames = splitString(this.params.getAACertificateSubjectAttributeName());
        Set resultSet = createAttributeCertificates(attrCertSubjectSerialSearch(selector, attrs, attrNames, subjectAttributeNames), selector);
        if (resultSet.size() == 0) {
            resultSet.addAll(createAttributeCertificates(attrCertSubjectSerialSearch(new X509AttributeCertStoreSelector(), attrs, attrNames, subjectAttributeNames), selector));
        }
        return resultSet;
    }

    public Collection getAttributeDescriptorCertificates(X509AttributeCertStoreSelector selector) throws StoreException {
        String[] attrs = splitString(this.params.getAttributeDescriptorCertificateAttribute());
        String[] attrNames = splitString(this.params.getLdapAttributeDescriptorCertificateAttributeName());
        String[] subjectAttributeNames = splitString(this.params.getAttributeDescriptorCertificateSubjectAttributeName());
        Set resultSet = createAttributeCertificates(attrCertSubjectSerialSearch(selector, attrs, attrNames, subjectAttributeNames), selector);
        if (resultSet.size() == 0) {
            resultSet.addAll(createAttributeCertificates(attrCertSubjectSerialSearch(new X509AttributeCertStoreSelector(), attrs, attrNames, subjectAttributeNames), selector));
        }
        return resultSet;
    }

    public Collection getCACertificates(X509CertStoreSelector selector) throws StoreException {
        String[] attrs = splitString(this.params.getCACertificateAttribute());
        String[] attrNames = splitString(this.params.getLdapCACertificateAttributeName());
        String[] subjectAttributeNames = splitString(this.params.getCACertificateSubjectAttributeName());
        Set resultSet = createCerts(certSubjectSerialSearch(selector, attrs, attrNames, subjectAttributeNames), selector);
        if (resultSet.size() == 0) {
            resultSet.addAll(createCerts(certSubjectSerialSearch(new X509CertStoreSelector(), attrs, attrNames, subjectAttributeNames), selector));
        }
        return resultSet;
    }

    public Collection getDeltaCertificateRevocationLists(X509CRLStoreSelector selector) throws StoreException {
        String[] attrs = splitString(this.params.getDeltaRevocationListAttribute());
        String[] attrNames = splitString(this.params.getLdapDeltaRevocationListAttributeName());
        String[] issuerAttributeNames = splitString(this.params.getDeltaRevocationListIssuerAttributeName());
        Set resultSet = createCRLs(cRLIssuerSearch(selector, attrs, attrNames, issuerAttributeNames), selector);
        if (resultSet.size() == 0) {
            resultSet.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), attrs, attrNames, issuerAttributeNames), selector));
        }
        return resultSet;
    }

    public Collection getAttributeCertificateAttributes(X509AttributeCertStoreSelector selector) throws StoreException {
        String[] attrs = splitString(this.params.getAttributeCertificateAttributeAttribute());
        String[] attrNames = splitString(this.params.getLdapAttributeCertificateAttributeAttributeName());
        String[] subjectAttributeNames = splitString(this.params.getAttributeCertificateAttributeSubjectAttributeName());
        Set resultSet = createAttributeCertificates(attrCertSubjectSerialSearch(selector, attrs, attrNames, subjectAttributeNames), selector);
        if (resultSet.size() == 0) {
            resultSet.addAll(createAttributeCertificates(attrCertSubjectSerialSearch(new X509AttributeCertStoreSelector(), attrs, attrNames, subjectAttributeNames), selector));
        }
        return resultSet;
    }

    public Collection getCertificateRevocationLists(X509CRLStoreSelector selector) throws StoreException {
        String[] attrs = splitString(this.params.getCertificateRevocationListAttribute());
        String[] attrNames = splitString(this.params.getLdapCertificateRevocationListAttributeName());
        String[] issuerAttributeNames = splitString(this.params.getCertificateRevocationListIssuerAttributeName());
        Set resultSet = createCRLs(cRLIssuerSearch(selector, attrs, attrNames, issuerAttributeNames), selector);
        if (resultSet.size() == 0) {
            resultSet.addAll(createCRLs(cRLIssuerSearch(new X509CRLStoreSelector(), attrs, attrNames, issuerAttributeNames), selector));
        }
        return resultSet;
    }

    private synchronized void addToCache(String searchCriteria, List list) {
        Date now = new Date(System.currentTimeMillis());
        List cacheEntry = new ArrayList();
        cacheEntry.add(now);
        cacheEntry.add(list);
        if (this.cacheMap.containsKey(searchCriteria)) {
            this.cacheMap.put(searchCriteria, cacheEntry);
        } else {
            if (this.cacheMap.size() >= cacheSize) {
                long oldest = now.getTime();
                Object obj = null;
                for (Entry entry : this.cacheMap.entrySet()) {
                    long current = ((Date) ((List) entry.getValue()).get(0)).getTime();
                    if (current < oldest) {
                        oldest = current;
                        obj = entry.getKey();
                    }
                }
                this.cacheMap.remove(obj);
            }
            this.cacheMap.put(searchCriteria, cacheEntry);
        }
    }

    private List getFromCache(String searchCriteria) {
        List entry = (List) this.cacheMap.get(searchCriteria);
        long now = System.currentTimeMillis();
        if (entry == null) {
            return null;
        }
        if (((Date) entry.get(0)).getTime() < now - lifeTime) {
            return null;
        }
        return (List) entry.get(1);
    }

    private String[] splitString(String str) {
        return str.split("\\s+");
    }

    private String getSubjectAsString(X509CertStoreSelector xselector) {
        try {
            byte[] encSubject = xselector.getSubjectAsBytes();
            if (encSubject != null) {
                return new X500Principal(encSubject).getName("RFC1779");
            }
            return null;
        } catch (IOException e) {
            throw new StoreException("exception processing name: " + e.getMessage(), e);
        }
    }

    private X500Principal getCertificateIssuer(X509Certificate cert) {
        return cert.getIssuerX500Principal();
    }
}
