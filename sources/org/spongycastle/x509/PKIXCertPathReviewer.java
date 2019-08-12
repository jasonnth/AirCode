package org.spongycastle.x509;

import com.facebook.common.util.UriUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.x509.AccessDescription;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.AuthorityInformationAccess;
import org.spongycastle.asn1.x509.AuthorityKeyIdentifier;
import org.spongycastle.asn1.x509.BasicConstraints;
import org.spongycastle.asn1.x509.CRLDistPoint;
import org.spongycastle.asn1.x509.DistributionPoint;
import org.spongycastle.asn1.x509.DistributionPointName;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.GeneralSubtree;
import org.spongycastle.asn1.x509.NameConstraints;
import org.spongycastle.asn1.x509.X509Extensions;
import org.spongycastle.asn1.x509.qualified.Iso4217CurrencyCode;
import org.spongycastle.asn1.x509.qualified.MonetaryValue;
import org.spongycastle.asn1.x509.qualified.QCStatement;
import org.spongycastle.i18n.ErrorBundle;
import org.spongycastle.i18n.LocaleString;
import org.spongycastle.i18n.filter.TrustedInput;
import org.spongycastle.i18n.filter.UntrustedInput;
import org.spongycastle.i18n.filter.UntrustedUrlInput;
import org.spongycastle.jce.provider.AnnotatedException;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.provider.PKIXNameConstraintValidator;
import org.spongycastle.jce.provider.PKIXNameConstraintValidatorException;
import org.spongycastle.util.Integers;
import org.spongycastle.x509.extension.X509ExtensionUtil;

public class PKIXCertPathReviewer extends CertPathValidatorUtilities {
    private static final String AUTH_INFO_ACCESS = X509Extensions.AuthorityInfoAccess.getId();
    private static final String CRL_DIST_POINTS = X509Extensions.CRLDistributionPoints.getId();
    private static final String QC_STATEMENT = X509Extensions.QCStatements.getId();
    private static final String RESOURCE_NAME = "org.spongycastle.x509.CertPathReviewerMessages";
    protected CertPath certPath;
    protected List certs;
    protected List[] errors;
    private boolean initialized;

    /* renamed from: n */
    protected int f7244n;
    protected List[] notifications;
    protected PKIXParameters pkixParams;
    protected PolicyNode policyTree;
    protected PublicKey subjectPublicKey;
    protected TrustAnchor trustAnchor;
    protected Date validDate;

    public void init(CertPath certPath2, PKIXParameters params) throws CertPathReviewerException {
        if (this.initialized) {
            throw new IllegalStateException("object is already initialized!");
        }
        this.initialized = true;
        if (certPath2 == null) {
            throw new NullPointerException("certPath was null");
        }
        this.certPath = certPath2;
        this.certs = certPath2.getCertificates();
        this.f7244n = this.certs.size();
        if (this.certs.isEmpty()) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.emptyCertPath"));
        }
        this.pkixParams = (PKIXParameters) params.clone();
        this.validDate = getValidDate(this.pkixParams);
        this.notifications = null;
        this.errors = null;
        this.trustAnchor = null;
        this.subjectPublicKey = null;
        this.policyTree = null;
    }

    public PKIXCertPathReviewer(CertPath certPath2, PKIXParameters params) throws CertPathReviewerException {
        init(certPath2, params);
    }

    public PKIXCertPathReviewer() {
    }

    public CertPath getCertPath() {
        return this.certPath;
    }

    public int getCertPathSize() {
        return this.f7244n;
    }

    public List[] getErrors() {
        doChecks();
        return this.errors;
    }

    public List getErrors(int index) {
        doChecks();
        return this.errors[index + 1];
    }

    public List[] getNotifications() {
        doChecks();
        return this.notifications;
    }

    public List getNotifications(int index) {
        doChecks();
        return this.notifications[index + 1];
    }

    public PolicyNode getPolicyTree() {
        doChecks();
        return this.policyTree;
    }

    public PublicKey getSubjectPublicKey() {
        doChecks();
        return this.subjectPublicKey;
    }

    public TrustAnchor getTrustAnchor() {
        doChecks();
        return this.trustAnchor;
    }

    public boolean isValidCertPath() {
        doChecks();
        for (List isEmpty : this.errors) {
            if (!isEmpty.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void addNotification(ErrorBundle msg) {
        this.notifications[0].add(msg);
    }

    /* access modifiers changed from: protected */
    public void addNotification(ErrorBundle msg, int index) {
        if (index < -1 || index >= this.f7244n) {
            throw new IndexOutOfBoundsException();
        }
        this.notifications[index + 1].add(msg);
    }

    /* access modifiers changed from: protected */
    public void addError(ErrorBundle msg) {
        this.errors[0].add(msg);
    }

    /* access modifiers changed from: protected */
    public void addError(ErrorBundle msg, int index) {
        if (index < -1 || index >= this.f7244n) {
            throw new IndexOutOfBoundsException();
        }
        this.errors[index + 1].add(msg);
    }

    /* access modifiers changed from: protected */
    public void doChecks() {
        if (!this.initialized) {
            throw new IllegalStateException("Object not initialized. Call init() first.");
        } else if (this.notifications == null) {
            this.notifications = new List[(this.f7244n + 1)];
            this.errors = new List[(this.f7244n + 1)];
            for (int i = 0; i < this.notifications.length; i++) {
                this.notifications[i] = new ArrayList();
                this.errors[i] = new ArrayList();
            }
            checkSignatures();
            checkNameConstraints();
            checkPathLength();
            checkPolicy();
            checkCriticalExtensions();
        }
    }

    private void checkNameConstraints() {
        GeneralName name;
        PKIXNameConstraintValidator nameConstraintValidator = new PKIXNameConstraintValidator();
        for (int index = this.certs.size() - 1; index > 0; index--) {
            int i = this.f7244n - index;
            X509Certificate cert = (X509Certificate) this.certs.get(index);
            if (!isSelfIssued(cert)) {
                X500Principal principal = getSubjectPrincipal(cert);
                try {
                    ASN1Sequence dns = (ASN1Sequence) new ASN1InputStream((InputStream) new ByteArrayInputStream(principal.getEncoded())).readObject();
                    nameConstraintValidator.checkPermittedDN(dns);
                    nameConstraintValidator.checkExcludedDN(dns);
                    ASN1Sequence altName = (ASN1Sequence) getExtensionValue(cert, SUBJECT_ALTERNATIVE_NAME);
                    if (altName != null) {
                        for (int j = 0; j < altName.size(); j++) {
                            name = GeneralName.getInstance(altName.getObjectAt(j));
                            nameConstraintValidator.checkPermitted(name);
                            nameConstraintValidator.checkExcluded(name);
                        }
                    }
                } catch (AnnotatedException ae) {
                    ErrorBundle errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncExtError");
                    CertPathReviewerException certPathReviewerException = new CertPathReviewerException(errorBundle, ae, this.certPath, index);
                    throw certPathReviewerException;
                } catch (PKIXNameConstraintValidatorException cpve) {
                    String str = RESOURCE_NAME;
                    UntrustedInput untrustedInput = new UntrustedInput(name);
                    ErrorBundle errorBundle2 = new ErrorBundle(str, "CertPathReviewer.notPermittedEmail", new Object[]{untrustedInput});
                    CertPathReviewerException certPathReviewerException2 = new CertPathReviewerException(errorBundle2, cpve, this.certPath, index);
                    throw certPathReviewerException2;
                } catch (AnnotatedException ae2) {
                    ErrorBundle errorBundle3 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.subjAltNameExtError");
                    CertPathReviewerException certPathReviewerException3 = new CertPathReviewerException(errorBundle3, ae2, this.certPath, index);
                    throw certPathReviewerException3;
                } catch (PKIXNameConstraintValidatorException cpve2) {
                    ErrorBundle errorBundle4 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.excludedDN", new Object[]{new UntrustedInput(principal.getName())});
                    CertPathReviewerException certPathReviewerException4 = new CertPathReviewerException(errorBundle4, cpve2, this.certPath, index);
                    throw certPathReviewerException4;
                } catch (PKIXNameConstraintValidatorException cpve3) {
                    ErrorBundle errorBundle5 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedDN", new Object[]{new UntrustedInput(principal.getName())});
                    CertPathReviewerException certPathReviewerException5 = new CertPathReviewerException(errorBundle5, cpve3, this.certPath, index);
                    throw certPathReviewerException5;
                } catch (IOException e) {
                    String str2 = RESOURCE_NAME;
                    UntrustedInput untrustedInput2 = new UntrustedInput(principal);
                    ErrorBundle errorBundle6 = new ErrorBundle(str2, "CertPathReviewer.ncSubjectNameError", new Object[]{untrustedInput2});
                    CertPathReviewerException certPathReviewerException6 = new CertPathReviewerException(errorBundle6, e, this.certPath, index);
                    throw certPathReviewerException6;
                } catch (CertPathReviewerException cpre) {
                    addError(cpre.getErrorMessage(), cpre.getIndex());
                    return;
                }
            }
            ASN1Sequence ncSeq = (ASN1Sequence) getExtensionValue(cert, NAME_CONSTRAINTS);
            if (ncSeq != null) {
                NameConstraints nc = NameConstraints.getInstance(ncSeq);
                GeneralSubtree[] permitted = nc.getPermittedSubtrees();
                if (permitted != null) {
                    nameConstraintValidator.intersectPermittedSubtree(permitted);
                }
                GeneralSubtree[] excluded = nc.getExcludedSubtrees();
                if (excluded != null) {
                    for (int c = 0; c != excluded.length; c++) {
                        nameConstraintValidator.addExcludedSubtree(excluded[c]);
                    }
                }
            }
        }
    }

    private void checkPathLength() {
        BasicConstraints bc;
        int maxPathLength = this.f7244n;
        int totalPathLength = 0;
        for (int index = this.certs.size() - 1; index > 0; index--) {
            int i = this.f7244n - index;
            X509Certificate cert = (X509Certificate) this.certs.get(index);
            if (!isSelfIssued(cert)) {
                if (maxPathLength <= 0) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pathLengthExtended"));
                }
                maxPathLength--;
                totalPathLength++;
            }
            try {
                bc = BasicConstraints.getInstance(getExtensionValue(cert, BASIC_CONSTRAINTS));
            } catch (AnnotatedException e) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.processLengthConstError"), index);
                bc = null;
            }
            if (bc != null) {
                BigInteger _pathLengthConstraint = bc.getPathLenConstraint();
                if (_pathLengthConstraint != null) {
                    int _plc = _pathLengthConstraint.intValue();
                    if (_plc < maxPathLength) {
                        maxPathLength = _plc;
                    }
                }
            }
        }
        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.totalPathLength", new Object[]{Integers.valueOf(totalPathLength)}));
    }

    private void checkSignatures() {
        PublicKey trustPublicKey;
        TrustAnchor trust = null;
        X500Principal trustPrincipal = null;
        ErrorBundle errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathValidDate", new Object[]{new TrustedInput(this.validDate), new TrustedInput(new Date())});
        addNotification(errorBundle);
        try {
            X509Certificate cert = (X509Certificate) this.certs.get(this.certs.size() - 1);
            Collection trustColl = getTrustAnchors(cert, this.pkixParams.getTrustAnchors());
            if (trustColl.size() > 1) {
                ErrorBundle errorBundle2 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.conflictingTrustAnchors", new Object[]{Integers.valueOf(trustColl.size()), new UntrustedInput(cert.getIssuerX500Principal())});
                addError(errorBundle2);
            } else if (trustColl.isEmpty()) {
                ErrorBundle errorBundle3 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noTrustAnchorFound", new Object[]{new UntrustedInput(cert.getIssuerX500Principal()), Integers.valueOf(this.pkixParams.getTrustAnchors().size())});
                addError(errorBundle3);
            } else {
                trust = (TrustAnchor) trustColl.iterator().next();
                if (trust.getTrustedCert() != null) {
                    trustPublicKey = trust.getTrustedCert().getPublicKey();
                } else {
                    trustPublicKey = trust.getCAPublicKey();
                }
                try {
                    CertPathValidatorUtilities.verifyX509Certificate(cert, trustPublicKey, this.pkixParams.getSigProvider());
                } catch (SignatureException e) {
                    ErrorBundle errorBundle4 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustButInvalidCert");
                    addError(errorBundle4);
                } catch (Exception e2) {
                }
            }
        } catch (CertPathReviewerException cpre) {
            addError(cpre.getErrorMessage());
        } catch (Throwable t) {
            UntrustedInput untrustedInput = new UntrustedInput(t);
            ErrorBundle errorBundle5 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknown", new Object[]{new UntrustedInput(t.getMessage()), untrustedInput});
            addError(errorBundle5);
        }
        if (trust != null) {
            X509Certificate sign = trust.getTrustedCert();
            if (sign != null) {
                try {
                    trustPrincipal = getSubjectPrincipal(sign);
                } catch (IllegalArgumentException e3) {
                    ErrorBundle errorBundle6 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustDNInvalid", new Object[]{new UntrustedInput(trust.getCAName())});
                    addError(errorBundle6);
                }
            } else {
                X500Principal x500Principal = new X500Principal(trust.getCAName());
                trustPrincipal = x500Principal;
            }
            if (sign != null) {
                boolean[] ku = sign.getKeyUsage();
                if (ku != null && !ku[5]) {
                    ErrorBundle errorBundle7 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustKeyUsage");
                    addNotification(errorBundle7);
                }
            }
        }
        PublicKey workingPublicKey = null;
        X500Principal workingIssuerName = trustPrincipal;
        X509Certificate sign2 = null;
        if (trust != null) {
            sign2 = trust.getTrustedCert();
            if (sign2 != null) {
                workingPublicKey = sign2.getPublicKey();
            } else {
                workingPublicKey = trust.getCAPublicKey();
            }
            try {
                AlgorithmIdentifier workingAlgId = getAlgorithmIdentifier(workingPublicKey);
                ASN1ObjectIdentifier workingPublicKeyAlgorithm = workingAlgId.getAlgorithm();
                ASN1Encodable workingPublicKeyParameters = workingAlgId.getParameters();
            } catch (CertPathValidatorException e4) {
                ErrorBundle errorBundle8 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustPubKeyError");
                addError(errorBundle8);
            }
        }
        for (int index = this.certs.size() - 1; index >= 0; index--) {
            int i = this.f7244n - index;
            X509Certificate cert2 = (X509Certificate) this.certs.get(index);
            if (workingPublicKey != null) {
                try {
                    CertPathValidatorUtilities.verifyX509Certificate(cert2, workingPublicKey, this.pkixParams.getSigProvider());
                } catch (GeneralSecurityException ex) {
                    ErrorBundle errorBundle9 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.signatureNotVerified", new Object[]{ex.getMessage(), ex, ex.getClass().getName()});
                    addError(errorBundle9, index);
                }
            } else if (isSelfIssued(cert2)) {
                try {
                    CertPathValidatorUtilities.verifyX509Certificate(cert2, cert2.getPublicKey(), this.pkixParams.getSigProvider());
                    ErrorBundle errorBundle10 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.rootKeyIsValidButNotATrustAnchor");
                    addError(errorBundle10, index);
                } catch (GeneralSecurityException ex2) {
                    ErrorBundle errorBundle11 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.signatureNotVerified", new Object[]{ex2.getMessage(), ex2, ex2.getClass().getName()});
                    addError(errorBundle11, index);
                }
            } else {
                ErrorBundle errorBundle12 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.NoIssuerPublicKey");
                byte[] akiBytes = cert2.getExtensionValue(X509Extensions.AuthorityKeyIdentifier.getId());
                if (akiBytes != null) {
                    try {
                        AuthorityKeyIdentifier aki = AuthorityKeyIdentifier.getInstance(X509ExtensionUtil.fromExtensionValue(akiBytes));
                        GeneralNames issuerNames = aki.getAuthorityCertIssuer();
                        if (issuerNames != null) {
                            GeneralName name = issuerNames.getNames()[0];
                            BigInteger serial = aki.getAuthorityCertSerialNumber();
                            if (serial != null) {
                                errorBundle12.setExtraArguments(new Object[]{new LocaleString(RESOURCE_NAME, "missingIssuer"), " \"", name, "\" ", new LocaleString(RESOURCE_NAME, "missingSerial"), " ", serial});
                            }
                        }
                    } catch (IOException e5) {
                    }
                }
                addError(errorBundle12, index);
            }
            try {
                cert2.checkValidity(this.validDate);
            } catch (CertificateNotYetValidException e6) {
                ErrorBundle errorBundle13 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certificateNotYetValid", new Object[]{new TrustedInput(cert2.getNotBefore())});
                addError(errorBundle13, index);
            } catch (CertificateExpiredException e7) {
                ErrorBundle errorBundle14 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certificateExpired", new Object[]{new TrustedInput(cert2.getNotAfter())});
                addError(errorBundle14, index);
            }
            if (this.pkixParams.isRevocationEnabled()) {
                CRLDistPoint crlDistPoints = null;
                try {
                    ASN1Primitive crl_dp = getExtensionValue(cert2, CRL_DIST_POINTS);
                    if (crl_dp != null) {
                        crlDistPoints = CRLDistPoint.getInstance(crl_dp);
                    }
                } catch (AnnotatedException e8) {
                    ErrorBundle errorBundle15 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlDistPtExtError");
                    addError(errorBundle15, index);
                }
                AuthorityInformationAccess authInfoAcc = null;
                try {
                    ASN1Primitive auth_info_acc = getExtensionValue(cert2, AUTH_INFO_ACCESS);
                    if (auth_info_acc != null) {
                        authInfoAcc = AuthorityInformationAccess.getInstance(auth_info_acc);
                    }
                } catch (AnnotatedException e9) {
                    ErrorBundle errorBundle16 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlAuthInfoAccError");
                    addError(errorBundle16, index);
                }
                Vector crlDistPointUrls = getCRLDistUrls(crlDistPoints);
                Vector ocspUrls = getOCSPUrls(authInfoAcc);
                Iterator urlIt = crlDistPointUrls.iterator();
                while (urlIt.hasNext()) {
                    ErrorBundle errorBundle17 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlDistPoint", new Object[]{new UntrustedUrlInput(urlIt.next())});
                    addNotification(errorBundle17, index);
                }
                Iterator urlIt2 = ocspUrls.iterator();
                while (urlIt2.hasNext()) {
                    ErrorBundle errorBundle18 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ocspLocation", new Object[]{new UntrustedUrlInput(urlIt2.next())});
                    addNotification(errorBundle18, index);
                }
                try {
                    checkRevocation(this.pkixParams, cert2, this.validDate, sign2, workingPublicKey, crlDistPointUrls, ocspUrls, index);
                } catch (CertPathReviewerException cpre2) {
                    addError(cpre2.getErrorMessage(), index);
                }
            }
            if (workingIssuerName != null && !cert2.getIssuerX500Principal().equals(workingIssuerName)) {
                ErrorBundle errorBundle19 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certWrongIssuer", new Object[]{workingIssuerName.getName(), cert2.getIssuerX500Principal().getName()});
                addError(errorBundle19, index);
            }
            if (i != this.f7244n) {
                if (cert2 != null && cert2.getVersion() == 1) {
                    ErrorBundle errorBundle20 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCACert");
                    addError(errorBundle20, index);
                }
                try {
                    BasicConstraints bc = BasicConstraints.getInstance(getExtensionValue(cert2, BASIC_CONSTRAINTS));
                    if (bc == null) {
                        ErrorBundle errorBundle21 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noBasicConstraints");
                        addError(errorBundle21, index);
                    } else if (!bc.isCA()) {
                        ErrorBundle errorBundle22 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCACert");
                        addError(errorBundle22, index);
                    }
                } catch (AnnotatedException e10) {
                    ErrorBundle errorBundle23 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.errorProcesingBC");
                    addError(errorBundle23, index);
                }
                boolean[] _usage = cert2.getKeyUsage();
                if (_usage != null && !_usage[5]) {
                    ErrorBundle errorBundle24 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCertSign");
                    addError(errorBundle24, index);
                }
            }
            sign2 = cert2;
            workingIssuerName = cert2.getSubjectX500Principal();
            try {
                workingPublicKey = getNextWorkingKey(this.certs, index);
                AlgorithmIdentifier workingAlgId2 = getAlgorithmIdentifier(workingPublicKey);
                ASN1ObjectIdentifier workingPublicKeyAlgorithm2 = workingAlgId2.getAlgorithm();
                ASN1Encodable workingPublicKeyParameters2 = workingAlgId2.getParameters();
            } catch (CertPathValidatorException e11) {
                ErrorBundle errorBundle25 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pubKeyError");
                addError(errorBundle25, index);
            }
        }
        this.trustAnchor = trust;
        this.subjectPublicKey = workingPublicKey;
    }

    /* JADX WARNING: Removed duplicated region for block: B:109:0x029e A[Catch:{ AnnotatedException -> 0x0570, AnnotatedException -> 0x0512, AnnotatedException -> 0x04d6, AnnotatedException -> 0x0429, CertPathValidatorException -> 0x0447, AnnotatedException -> 0x0346, CertPathValidatorException -> 0x021a, CertPathValidatorException -> 0x0137, AnnotatedException -> 0x0119, CertPathReviewerException -> 0x00f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:326:0x0268 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x017c A[Catch:{ AnnotatedException -> 0x0570, AnnotatedException -> 0x0512, AnnotatedException -> 0x04d6, AnnotatedException -> 0x0429, CertPathValidatorException -> 0x0447, AnnotatedException -> 0x0346, CertPathValidatorException -> 0x021a, CertPathValidatorException -> 0x0137, AnnotatedException -> 0x0119, CertPathReviewerException -> 0x00f1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x026c A[Catch:{ AnnotatedException -> 0x0570, AnnotatedException -> 0x0512, AnnotatedException -> 0x04d6, AnnotatedException -> 0x0429, CertPathValidatorException -> 0x0447, AnnotatedException -> 0x0346, CertPathValidatorException -> 0x021a, CertPathValidatorException -> 0x0137, AnnotatedException -> 0x0119, CertPathReviewerException -> 0x00f1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkPolicy() {
        /*
            r74 = this;
            r0 = r74
            java.security.cert.PKIXParameters r5 = r0.pkixParams
            java.util.Set r73 = r5.getInitialPolicies()
            r0 = r74
            int r5 = r0.f7244n
            int r5 = r5 + 1
            java.util.ArrayList[] r0 = new java.util.ArrayList[r5]
            r64 = r0
            r48 = 0
        L_0x0014:
            r0 = r64
            int r5 = r0.length
            r0 = r48
            if (r0 >= r5) goto L_0x0025
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r64[r48] = r5
            int r48 = r48 + 1
            goto L_0x0014
        L_0x0025:
            java.util.HashSet r7 = new java.util.HashSet
            r7.<init>()
            java.lang.String r5 = "2.5.29.32.0"
            r7.add(r5)
            org.spongycastle.jce.provider.PKIXPolicyNode r4 = new org.spongycastle.jce.provider.PKIXPolicyNode
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r6 = 0
            r8 = 0
            java.util.HashSet r9 = new java.util.HashSet
            r9.<init>()
            java.lang.String r10 = "2.5.29.32.0"
            r11 = 0
            r4.<init>(r5, r6, r7, r8, r9, r10, r11)
            r5 = 0
            r5 = r64[r5]
            r5.add(r4)
            r0 = r74
            java.security.cert.PKIXParameters r5 = r0.pkixParams
            boolean r5 = r5.isExplicitPolicyRequired()
            if (r5 == 0) goto L_0x0101
            r39 = 0
        L_0x0057:
            r0 = r74
            java.security.cert.PKIXParameters r5 = r0.pkixParams
            boolean r5 = r5.isAnyPolicyInhibited()
            if (r5 == 0) goto L_0x0109
            r43 = 0
        L_0x0063:
            r0 = r74
            java.security.cert.PKIXParameters r5 = r0.pkixParams
            boolean r5 = r5.isPolicyMappingInhibited()
            if (r5 == 0) goto L_0x0111
            r63 = 0
        L_0x006f:
            r29 = 0
            r31 = 0
            r0 = r74
            java.util.List r5 = r0.certs     // Catch:{ CertPathReviewerException -> 0x00f1 }
            int r5 = r5.size()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            int r42 = r5 + -1
        L_0x007d:
            if (r42 < 0) goto L_0x052e
            r0 = r74
            int r5 = r0.f7244n     // Catch:{ CertPathReviewerException -> 0x00f1 }
            int r10 = r5 - r42
            r0 = r74
            java.util.List r5 = r0.certs     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r42
            java.lang.Object r5 = r5.get(r0)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r5
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r31 = r0
            java.lang.String r5 = CERTIFICATE_POLICIES     // Catch:{ AnnotatedException -> 0x0119 }
            r0 = r31
            org.spongycastle.asn1.ASN1Primitive r32 = getExtensionValue(r0, r5)     // Catch:{ AnnotatedException -> 0x0119 }
            org.spongycastle.asn1.ASN1Sequence r32 = (org.spongycastle.asn1.ASN1Sequence) r32     // Catch:{ AnnotatedException -> 0x0119 }
            if (r32 == 0) goto L_0x02c6
            if (r4 == 0) goto L_0x02c6
            java.util.Enumeration r38 = r32.getObjects()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.util.HashSet r65 = new java.util.HashSet     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r65.<init>()     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x00ab:
            boolean r5 = r38.hasMoreElements()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x0155
            java.lang.Object r5 = r38.nextElement()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.asn1.x509.PolicyInformation r58 = org.spongycastle.asn1.x509.PolicyInformation.getInstance(r5)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.asn1.ASN1ObjectIdentifier r59 = r58.getPolicyIdentifier()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = r59.getId()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r65
            r0.add(r5)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "2.5.29.32.0"
            java.lang.String r6 = r59.getId()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            boolean r5 = r5.equals(r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 != 0) goto L_0x00ab
            org.spongycastle.asn1.ASN1Sequence r5 = r58.getPolicyQualifiers()     // Catch:{ CertPathValidatorException -> 0x0137 }
            java.util.Set r66 = getQualifierSet(r5)     // Catch:{ CertPathValidatorException -> 0x0137 }
            r0 = r64
            r1 = r59
            r2 = r66
            boolean r53 = processCertD1i(r10, r0, r1, r2)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r53 != 0) goto L_0x00ab
            r0 = r64
            r1 = r59
            r2 = r66
            processCertD1ii(r10, r0, r1, r2)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            goto L_0x00ab
        L_0x00f1:
            r34 = move-exception
            org.spongycastle.i18n.ErrorBundle r5 = r34.getErrorMessage()
            int r6 = r34.getIndex()
            r0 = r74
            r0.addError(r5, r6)
            r4 = 0
        L_0x0100:
            return
        L_0x0101:
            r0 = r74
            int r5 = r0.f7244n
            int r39 = r5 + 1
            goto L_0x0057
        L_0x0109:
            r0 = r74
            int r5 = r0.f7244n
            int r43 = r5 + 1
            goto L_0x0063
        L_0x0111:
            r0 = r74
            int r5 = r0.f7244n
            int r63 = r5 + 1
            goto L_0x006f
        L_0x0119:
            r30 = move-exception
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.policyExtError"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r74
            java.security.cert.CertPath r6 = r0.certPath     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r1 = r30
            r2 = r42
            r5.<init>(r0, r1, r6, r2)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x0137:
            r35 = move-exception
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.policyQualifierError"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r74
            java.security.cert.CertPath r6 = r0.certPath     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r1 = r35
            r2 = r42
            r5.<init>(r0, r1, r6, r2)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x0155:
            if (r29 == 0) goto L_0x0162
            java.lang.String r5 = "2.5.29.32.0"
            r0 = r29
            boolean r5 = r0.contains(r5)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x01f1
        L_0x0162:
            r29 = r65
        L_0x0164:
            if (r43 > 0) goto L_0x0172
            r0 = r74
            int r5 = r0.f7244n     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r10 >= r5) goto L_0x0268
            boolean r5 = isSelfIssued(r31)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x0268
        L_0x0172:
            java.util.Enumeration r38 = r32.getObjects()     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x0176:
            boolean r5 = r38.hasMoreElements()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x0268
            java.lang.Object r5 = r38.nextElement()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.asn1.x509.PolicyInformation r58 = org.spongycastle.asn1.x509.PolicyInformation.getInstance(r5)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "2.5.29.32.0"
            org.spongycastle.asn1.ASN1ObjectIdentifier r6 = r58.getPolicyIdentifier()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r6 = r6.getId()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            boolean r5 = r5.equals(r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x0176
            org.spongycastle.asn1.ASN1Sequence r5 = r58.getPolicyQualifiers()     // Catch:{ CertPathValidatorException -> 0x021a }
            java.util.Set r13 = getQualifierSet(r5)     // Catch:{ CertPathValidatorException -> 0x021a }
            int r5 = r10 + -1
            r23 = r64[r5]     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r49 = 0
        L_0x01a3:
            int r5 = r23.size()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r49
            if (r0 >= r5) goto L_0x0268
            r0 = r23
            r1 = r49
            java.lang.Object r12 = r0.get(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.jce.provider.PKIXPolicyNode r12 = (org.spongycastle.jce.provider.PKIXPolicyNode) r12     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.util.Set r5 = r12.getExpectedPolicies()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.util.Iterator r24 = r5.iterator()     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x01bd:
            boolean r5 = r24.hasNext()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x0264
            java.lang.Object r25 = r24.next()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r25
            boolean r5 = r0 instanceof java.lang.String     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x0238
            r0 = r25
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r14 = r0
        L_0x01d2:
            r19 = 0
            java.util.Iterator r18 = r12.getChildren()     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x01d8:
            boolean r5 = r18.hasNext()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x0245
            java.lang.Object r17 = r18.next()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.jce.provider.PKIXPolicyNode r17 = (org.spongycastle.jce.provider.PKIXPolicyNode) r17     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = r17.getValidPolicy()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            boolean r5 = r14.equals(r5)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x01d8
            r19 = 1
            goto L_0x01d8
        L_0x01f1:
            java.util.Iterator r46 = r29.iterator()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.util.HashSet r70 = new java.util.HashSet     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r70.<init>()     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x01fa:
            boolean r5 = r46.hasNext()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x0216
            java.lang.Object r57 = r46.next()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r65
            r1 = r57
            boolean r5 = r0.contains(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x01fa
            r0 = r70
            r1 = r57
            r0.add(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            goto L_0x01fa
        L_0x0216:
            r29 = r70
            goto L_0x0164
        L_0x021a:
            r35 = move-exception
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.policyQualifierError"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r74
            java.security.cert.CertPath r6 = r0.certPath     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r1 = r35
            r2 = r42
            r5.<init>(r0, r1, r6, r2)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x0238:
            r0 = r25
            boolean r5 = r0 instanceof org.spongycastle.asn1.ASN1ObjectIdentifier     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x01bd
            org.spongycastle.asn1.ASN1ObjectIdentifier r25 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r25     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r14 = r25.getId()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            goto L_0x01d2
        L_0x0245:
            if (r19 != 0) goto L_0x01bd
            java.util.HashSet r11 = new java.util.HashSet     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r11.<init>()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r11.add(r14)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.jce.provider.PKIXPolicyNode r8 = new org.spongycastle.jce.provider.PKIXPolicyNode     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r9.<init>()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r15 = 0
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r12.addChild(r8)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r5 = r64[r10]     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r5.add(r8)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            goto L_0x01bd
        L_0x0264:
            int r49 = r49 + 1
            goto L_0x01a3
        L_0x0268:
            int r48 = r10 + -1
        L_0x026a:
            if (r48 < 0) goto L_0x0298
            r56 = r64[r48]     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r49 = 0
        L_0x0270:
            int r5 = r56.size()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r49
            if (r0 >= r5) goto L_0x0292
            r0 = r56
            r1 = r49
            java.lang.Object r55 = r0.get(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.jce.provider.PKIXPolicyNode r55 = (org.spongycastle.jce.provider.PKIXPolicyNode) r55     // Catch:{ CertPathReviewerException -> 0x00f1 }
            boolean r5 = r55.hasChildren()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 != 0) goto L_0x0295
            r0 = r64
            r1 = r55
            org.spongycastle.jce.provider.PKIXPolicyNode r4 = removePolicyNode(r4, r0, r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r4 != 0) goto L_0x0295
        L_0x0292:
            int r48 = r48 + -1
            goto L_0x026a
        L_0x0295:
            int r49 = r49 + 1
            goto L_0x0270
        L_0x0298:
            java.util.Set r37 = r31.getCriticalExtensionOIDs()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r37 == 0) goto L_0x02c6
            java.lang.String r5 = CERTIFICATE_POLICIES     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r37
            boolean r36 = r0.contains(r5)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r56 = r64[r10]     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r48 = 0
        L_0x02aa:
            int r5 = r56.size()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r48
            if (r0 >= r5) goto L_0x02c6
            r0 = r56
            r1 = r48
            java.lang.Object r55 = r0.get(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.jce.provider.PKIXPolicyNode r55 = (org.spongycastle.jce.provider.PKIXPolicyNode) r55     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r55
            r1 = r36
            r0.setCritical(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            int r48 = r48 + 1
            goto L_0x02aa
        L_0x02c6:
            if (r32 != 0) goto L_0x02c9
            r4 = 0
        L_0x02c9:
            if (r39 > 0) goto L_0x02e2
            if (r4 != 0) goto L_0x02e2
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.noValidPolicyTree"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r5.<init>(r0)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x02e2:
            r0 = r74
            int r5 = r0.f7244n     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r10 == r5) goto L_0x050e
            java.lang.String r5 = POLICY_MAPPINGS     // Catch:{ AnnotatedException -> 0x0346 }
            r0 = r31
            org.spongycastle.asn1.ASN1Primitive r61 = getExtensionValue(r0, r5)     // Catch:{ AnnotatedException -> 0x0346 }
            if (r61 == 0) goto L_0x0390
            r0 = r61
            org.spongycastle.asn1.ASN1Sequence r0 = (org.spongycastle.asn1.ASN1Sequence) r0     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r52 = r0
            r48 = 0
        L_0x02fa:
            int r5 = r52.size()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r48
            if (r0 >= r5) goto L_0x0390
            r0 = r52
            r1 = r48
            org.spongycastle.asn1.ASN1Encodable r51 = r0.getObjectAt(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.asn1.ASN1Sequence r51 = (org.spongycastle.asn1.ASN1Sequence) r51     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r5 = 0
            r0 = r51
            org.spongycastle.asn1.ASN1Encodable r45 = r0.getObjectAt(r5)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.asn1.ASN1ObjectIdentifier r45 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r45     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r5 = 1
            r0 = r51
            org.spongycastle.asn1.ASN1Encodable r69 = r0.getObjectAt(r5)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.asn1.ASN1ObjectIdentifier r69 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r69     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "2.5.29.32.0"
            java.lang.String r6 = r45.getId()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            boolean r5 = r5.equals(r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x0364
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.invalidPolicyMapping"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r74
            java.security.cert.CertPath r6 = r0.certPath     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r1 = r42
            r5.<init>(r0, r6, r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x0346:
            r30 = move-exception
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.policyMapExtError"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r74
            java.security.cert.CertPath r6 = r0.certPath     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r1 = r30
            r2 = r42
            r5.<init>(r0, r1, r6, r2)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x0364:
            java.lang.String r5 = "2.5.29.32.0"
            java.lang.String r6 = r69.getId()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            boolean r5 = r5.equals(r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x038c
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.invalidPolicyMapping"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r74
            java.security.cert.CertPath r6 = r0.certPath     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r1 = r42
            r5.<init>(r0, r6, r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x038c:
            int r48 = r48 + 1
            goto L_0x02fa
        L_0x0390:
            if (r61 == 0) goto L_0x0470
            r0 = r61
            org.spongycastle.asn1.ASN1Sequence r0 = (org.spongycastle.asn1.ASN1Sequence) r0     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r52 = r0
            java.util.HashMap r50 = new java.util.HashMap     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r50.<init>()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.util.HashSet r67 = new java.util.HashSet     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r67.<init>()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r48 = 0
        L_0x03a4:
            int r5 = r52.size()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r48
            if (r0 >= r5) goto L_0x040b
            r0 = r52
            r1 = r48
            org.spongycastle.asn1.ASN1Encodable r51 = r0.getObjectAt(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.asn1.ASN1Sequence r51 = (org.spongycastle.asn1.ASN1Sequence) r51     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r5 = 0
            r0 = r51
            org.spongycastle.asn1.ASN1Encodable r5 = r0.getObjectAt(r5)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.asn1.ASN1ObjectIdentifier r5 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r41 = r5.getId()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r5 = 1
            r0 = r51
            org.spongycastle.asn1.ASN1Encodable r5 = r0.getObjectAt(r5)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.asn1.ASN1ObjectIdentifier r5 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r68 = r5.getId()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r50
            r1 = r41
            boolean r5 = r0.containsKey(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 != 0) goto L_0x03f9
            java.util.HashSet r71 = new java.util.HashSet     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r71.<init>()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r71
            r1 = r68
            r0.add(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r50
            r1 = r41
            r2 = r71
            r0.put(r1, r2)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r67
            r1 = r41
            r0.add(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x03f6:
            int r48 = r48 + 1
            goto L_0x03a4
        L_0x03f9:
            r0 = r50
            r1 = r41
            java.lang.Object r71 = r0.get(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.util.Set r71 = (java.util.Set) r71     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r71
            r1 = r68
            r0.add(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            goto L_0x03f6
        L_0x040b:
            java.util.Iterator r47 = r67.iterator()     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x040f:
            boolean r5 = r47.hasNext()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x0470
            java.lang.Object r41 = r47.next()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r41 = (java.lang.String) r41     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r63 <= 0) goto L_0x0465
            r0 = r64
            r1 = r41
            r2 = r50
            r3 = r31
            prepareNextCertB1(r10, r0, r1, r2, r3)     // Catch:{ AnnotatedException -> 0x0429, CertPathValidatorException -> 0x0447 }
            goto L_0x040f
        L_0x0429:
            r30 = move-exception
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.policyExtError"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r74
            java.security.cert.CertPath r6 = r0.certPath     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r1 = r30
            r2 = r42
            r5.<init>(r0, r1, r6, r2)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x0447:
            r35 = move-exception
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.policyQualifierError"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r74
            java.security.cert.CertPath r6 = r0.certPath     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r1 = r35
            r2 = r42
            r5.<init>(r0, r1, r6, r2)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x0465:
            if (r63 > 0) goto L_0x040f
            r0 = r64
            r1 = r41
            org.spongycastle.jce.provider.PKIXPolicyNode r4 = prepareNextCertB2(r10, r0, r1, r4)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            goto L_0x040f
        L_0x0470:
            boolean r5 = isSelfIssued(r31)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 != 0) goto L_0x0482
            if (r39 == 0) goto L_0x047a
            int r39 = r39 + -1
        L_0x047a:
            if (r63 == 0) goto L_0x047e
            int r63 = r63 + -1
        L_0x047e:
            if (r43 == 0) goto L_0x0482
            int r43 = r43 + -1
        L_0x0482:
            java.lang.String r5 = POLICY_CONSTRAINTS     // Catch:{ AnnotatedException -> 0x04d6 }
            r0 = r31
            org.spongycastle.asn1.ASN1Primitive r60 = getExtensionValue(r0, r5)     // Catch:{ AnnotatedException -> 0x04d6 }
            org.spongycastle.asn1.ASN1Sequence r60 = (org.spongycastle.asn1.ASN1Sequence) r60     // Catch:{ AnnotatedException -> 0x04d6 }
            if (r60 == 0) goto L_0x04f2
            java.util.Enumeration r62 = r60.getObjects()     // Catch:{ AnnotatedException -> 0x04d6 }
        L_0x0492:
            boolean r5 = r62.hasMoreElements()     // Catch:{ AnnotatedException -> 0x04d6 }
            if (r5 == 0) goto L_0x04f2
            java.lang.Object r33 = r62.nextElement()     // Catch:{ AnnotatedException -> 0x04d6 }
            org.spongycastle.asn1.ASN1TaggedObject r33 = (org.spongycastle.asn1.ASN1TaggedObject) r33     // Catch:{ AnnotatedException -> 0x04d6 }
            int r5 = r33.getTagNo()     // Catch:{ AnnotatedException -> 0x04d6 }
            switch(r5) {
                case 0: goto L_0x04a6;
                case 1: goto L_0x04be;
                default: goto L_0x04a5;
            }     // Catch:{ AnnotatedException -> 0x04d6 }
        L_0x04a5:
            goto L_0x0492
        L_0x04a6:
            r5 = 0
            r0 = r33
            org.spongycastle.asn1.ASN1Integer r5 = org.spongycastle.asn1.ASN1Integer.getInstance(r0, r5)     // Catch:{ AnnotatedException -> 0x04d6 }
            java.math.BigInteger r5 = r5.getValue()     // Catch:{ AnnotatedException -> 0x04d6 }
            int r72 = r5.intValue()     // Catch:{ AnnotatedException -> 0x04d6 }
            r0 = r72
            r1 = r39
            if (r0 >= r1) goto L_0x0492
            r39 = r72
            goto L_0x0492
        L_0x04be:
            r5 = 0
            r0 = r33
            org.spongycastle.asn1.ASN1Integer r5 = org.spongycastle.asn1.ASN1Integer.getInstance(r0, r5)     // Catch:{ AnnotatedException -> 0x04d6 }
            java.math.BigInteger r5 = r5.getValue()     // Catch:{ AnnotatedException -> 0x04d6 }
            int r72 = r5.intValue()     // Catch:{ AnnotatedException -> 0x04d6 }
            r0 = r72
            r1 = r63
            if (r0 >= r1) goto L_0x0492
            r63 = r72
            goto L_0x0492
        L_0x04d6:
            r30 = move-exception
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.policyConstExtError"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r74
            java.security.cert.CertPath r6 = r0.certPath     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r1 = r42
            r5.<init>(r0, r6, r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x04f2:
            java.lang.String r5 = INHIBIT_ANY_POLICY     // Catch:{ AnnotatedException -> 0x0512 }
            r0 = r31
            org.spongycastle.asn1.ASN1Primitive r40 = getExtensionValue(r0, r5)     // Catch:{ AnnotatedException -> 0x0512 }
            org.spongycastle.asn1.ASN1Integer r40 = (org.spongycastle.asn1.ASN1Integer) r40     // Catch:{ AnnotatedException -> 0x0512 }
            if (r40 == 0) goto L_0x050e
            java.math.BigInteger r5 = r40.getValue()     // Catch:{ AnnotatedException -> 0x0512 }
            int r20 = r5.intValue()     // Catch:{ AnnotatedException -> 0x0512 }
            r0 = r20
            r1 = r43
            if (r0 >= r1) goto L_0x050e
            r43 = r20
        L_0x050e:
            int r42 = r42 + -1
            goto L_0x007d
        L_0x0512:
            r30 = move-exception
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.policyInhibitExtError"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r74
            java.security.cert.CertPath r6 = r0.certPath     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r1 = r42
            r5.<init>(r0, r6, r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x052e:
            boolean r5 = isSelfIssued(r31)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 != 0) goto L_0x0538
            if (r39 <= 0) goto L_0x0538
            int r39 = r39 + -1
        L_0x0538:
            java.lang.String r5 = POLICY_CONSTRAINTS     // Catch:{ AnnotatedException -> 0x0570 }
            r0 = r31
            org.spongycastle.asn1.ASN1Primitive r60 = getExtensionValue(r0, r5)     // Catch:{ AnnotatedException -> 0x0570 }
            org.spongycastle.asn1.ASN1Sequence r60 = (org.spongycastle.asn1.ASN1Sequence) r60     // Catch:{ AnnotatedException -> 0x0570 }
            if (r60 == 0) goto L_0x058c
            java.util.Enumeration r62 = r60.getObjects()     // Catch:{ AnnotatedException -> 0x0570 }
        L_0x0548:
            boolean r5 = r62.hasMoreElements()     // Catch:{ AnnotatedException -> 0x0570 }
            if (r5 == 0) goto L_0x058c
            java.lang.Object r33 = r62.nextElement()     // Catch:{ AnnotatedException -> 0x0570 }
            org.spongycastle.asn1.ASN1TaggedObject r33 = (org.spongycastle.asn1.ASN1TaggedObject) r33     // Catch:{ AnnotatedException -> 0x0570 }
            int r5 = r33.getTagNo()     // Catch:{ AnnotatedException -> 0x0570 }
            switch(r5) {
                case 0: goto L_0x055c;
                default: goto L_0x055b;
            }     // Catch:{ AnnotatedException -> 0x0570 }
        L_0x055b:
            goto L_0x0548
        L_0x055c:
            r5 = 0
            r0 = r33
            org.spongycastle.asn1.ASN1Integer r5 = org.spongycastle.asn1.ASN1Integer.getInstance(r0, r5)     // Catch:{ AnnotatedException -> 0x0570 }
            java.math.BigInteger r5 = r5.getValue()     // Catch:{ AnnotatedException -> 0x0570 }
            int r72 = r5.intValue()     // Catch:{ AnnotatedException -> 0x0570 }
            if (r72 != 0) goto L_0x0548
            r39 = 0
            goto L_0x0548
        L_0x0570:
            r38 = move-exception
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.policyConstExtError"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r74
            java.security.cert.CertPath r6 = r0.certPath     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r1 = r42
            r5.<init>(r0, r6, r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x058c:
            if (r4 != 0) goto L_0x05ce
            r0 = r74
            java.security.cert.PKIXParameters r5 = r0.pkixParams     // Catch:{ CertPathReviewerException -> 0x00f1 }
            boolean r5 = r5.isExplicitPolicyRequired()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x05b3
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.explicitPolicy"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r74
            java.security.cert.CertPath r6 = r0.certPath     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r1 = r42
            r5.<init>(r0, r6, r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x05b3:
            r44 = 0
        L_0x05b5:
            if (r39 > 0) goto L_0x075a
            if (r44 != 0) goto L_0x075a
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.invalidPolicy"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r5.<init>(r0)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x05ce:
            boolean r5 = isAnyPolicy(r73)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x06a1
            r0 = r74
            java.security.cert.PKIXParameters r5 = r0.pkixParams     // Catch:{ CertPathReviewerException -> 0x00f1 }
            boolean r5 = r5.isExplicitPolicyRequired()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x069d
            boolean r5 = r29.isEmpty()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x05ff
            org.spongycastle.i18n.ErrorBundle r54 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r6 = "CertPathReviewer.explicitPolicy"
            r0 = r54
            r0.<init>(r5, r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.x509.CertPathReviewerException r5 = new org.spongycastle.x509.CertPathReviewerException     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r74
            java.security.cert.CertPath r6 = r0.certPath     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r54
            r1 = r42
            r5.<init>(r0, r6, r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            throw r5     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x05ff:
            java.util.HashSet r27 = new java.util.HashSet     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r27.<init>()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r48 = 0
        L_0x0606:
            r0 = r64
            int r5 = r0.length     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r48
            if (r0 >= r5) goto L_0x064a
            r22 = r64[r48]     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r49 = 0
        L_0x0611:
            int r5 = r22.size()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r49
            if (r0 >= r5) goto L_0x0647
            r0 = r22
            r1 = r49
            java.lang.Object r12 = r0.get(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.jce.provider.PKIXPolicyNode r12 = (org.spongycastle.jce.provider.PKIXPolicyNode) r12     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "2.5.29.32.0"
            java.lang.String r6 = r12.getValidPolicy()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            boolean r5 = r5.equals(r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x0644
            java.util.Iterator r21 = r12.getChildren()     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x0634:
            boolean r5 = r21.hasNext()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x0644
            java.lang.Object r5 = r21.next()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r27
            r0.add(r5)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            goto L_0x0634
        L_0x0644:
            int r49 = r49 + 1
            goto L_0x0611
        L_0x0647:
            int r48 = r48 + 1
            goto L_0x0606
        L_0x064a:
            java.util.Iterator r28 = r27.iterator()     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x064e:
            boolean r5 = r28.hasNext()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x0669
            java.lang.Object r12 = r28.next()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.jce.provider.PKIXPolicyNode r12 = (org.spongycastle.jce.provider.PKIXPolicyNode) r12     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r26 = r12.getValidPolicy()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r29
            r1 = r26
            boolean r5 = r0.contains(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 != 0) goto L_0x064e
            goto L_0x064e
        L_0x0669:
            if (r4 == 0) goto L_0x069d
            r0 = r74
            int r5 = r0.f7244n     // Catch:{ CertPathReviewerException -> 0x00f1 }
            int r48 = r5 + -1
        L_0x0671:
            if (r48 < 0) goto L_0x069d
            r56 = r64[r48]     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r49 = 0
        L_0x0677:
            int r5 = r56.size()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r49
            if (r0 >= r5) goto L_0x069a
            r0 = r56
            r1 = r49
            java.lang.Object r55 = r0.get(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.jce.provider.PKIXPolicyNode r55 = (org.spongycastle.jce.provider.PKIXPolicyNode) r55     // Catch:{ CertPathReviewerException -> 0x00f1 }
            boolean r5 = r55.hasChildren()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 != 0) goto L_0x0697
            r0 = r64
            r1 = r55
            org.spongycastle.jce.provider.PKIXPolicyNode r4 = removePolicyNode(r4, r0, r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x0697:
            int r49 = r49 + 1
            goto L_0x0677
        L_0x069a:
            int r48 = r48 + -1
            goto L_0x0671
        L_0x069d:
            r44 = r4
            goto L_0x05b5
        L_0x06a1:
            java.util.HashSet r27 = new java.util.HashSet     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r27.<init>()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r48 = 0
        L_0x06a8:
            r0 = r64
            int r5 = r0.length     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r48
            if (r0 >= r5) goto L_0x06fd
            r22 = r64[r48]     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r49 = 0
        L_0x06b3:
            int r5 = r22.size()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r49
            if (r0 >= r5) goto L_0x06fa
            r0 = r22
            r1 = r49
            java.lang.Object r12 = r0.get(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.jce.provider.PKIXPolicyNode r12 = (org.spongycastle.jce.provider.PKIXPolicyNode) r12     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "2.5.29.32.0"
            java.lang.String r6 = r12.getValidPolicy()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            boolean r5 = r5.equals(r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x06f7
            java.util.Iterator r21 = r12.getChildren()     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x06d6:
            boolean r5 = r21.hasNext()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x06f7
            java.lang.Object r16 = r21.next()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.jce.provider.PKIXPolicyNode r16 = (org.spongycastle.jce.provider.PKIXPolicyNode) r16     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r5 = "2.5.29.32.0"
            java.lang.String r6 = r16.getValidPolicy()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            boolean r5 = r5.equals(r6)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 != 0) goto L_0x06d6
            r0 = r27
            r1 = r16
            r0.add(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            goto L_0x06d6
        L_0x06f7:
            int r49 = r49 + 1
            goto L_0x06b3
        L_0x06fa:
            int r48 = r48 + 1
            goto L_0x06a8
        L_0x06fd:
            java.util.Iterator r28 = r27.iterator()     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x0701:
            boolean r5 = r28.hasNext()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 == 0) goto L_0x0722
            java.lang.Object r12 = r28.next()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.jce.provider.PKIXPolicyNode r12 = (org.spongycastle.jce.provider.PKIXPolicyNode) r12     // Catch:{ CertPathReviewerException -> 0x00f1 }
            java.lang.String r26 = r12.getValidPolicy()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r73
            r1 = r26
            boolean r5 = r0.contains(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 != 0) goto L_0x0701
            r0 = r64
            org.spongycastle.jce.provider.PKIXPolicyNode r4 = removePolicyNode(r4, r0, r12)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            goto L_0x0701
        L_0x0722:
            if (r4 == 0) goto L_0x0756
            r0 = r74
            int r5 = r0.f7244n     // Catch:{ CertPathReviewerException -> 0x00f1 }
            int r48 = r5 + -1
        L_0x072a:
            if (r48 < 0) goto L_0x0756
            r56 = r64[r48]     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r49 = 0
        L_0x0730:
            int r5 = r56.size()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            r0 = r49
            if (r0 >= r5) goto L_0x0753
            r0 = r56
            r1 = r49
            java.lang.Object r55 = r0.get(r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
            org.spongycastle.jce.provider.PKIXPolicyNode r55 = (org.spongycastle.jce.provider.PKIXPolicyNode) r55     // Catch:{ CertPathReviewerException -> 0x00f1 }
            boolean r5 = r55.hasChildren()     // Catch:{ CertPathReviewerException -> 0x00f1 }
            if (r5 != 0) goto L_0x0750
            r0 = r64
            r1 = r55
            org.spongycastle.jce.provider.PKIXPolicyNode r4 = removePolicyNode(r4, r0, r1)     // Catch:{ CertPathReviewerException -> 0x00f1 }
        L_0x0750:
            int r49 = r49 + 1
            goto L_0x0730
        L_0x0753:
            int r48 = r48 + -1
            goto L_0x072a
        L_0x0756:
            r44 = r4
            goto L_0x05b5
        L_0x075a:
            r4 = r44
            goto L_0x0100
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.PKIXCertPathReviewer.checkPolicy():void");
    }

    private void checkCriticalExtensions() {
        int index;
        List<PKIXCertPathChecker> pathCheckers = this.pkixParams.getCertPathCheckers();
        for (PKIXCertPathChecker init : pathCheckers) {
            try {
                init.init(false);
            } catch (CertPathValidatorException e) {
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.criticalExtensionError", new Object[]{e.getMessage(), e, e.getClass().getName()}), e.getCause(), this.certPath, index);
            } catch (CertPathValidatorException cpve) {
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathCheckerError", new Object[]{cpve.getMessage(), cpve, cpve.getClass().getName()}), cpve);
            } catch (CertPathReviewerException cpre) {
                addError(cpre.getErrorMessage(), cpre.getIndex());
                return;
            }
        }
        index = this.certs.size() - 1;
        while (index >= 0) {
            X509Certificate cert = (X509Certificate) this.certs.get(index);
            Set<String> criticalExtensions = cert.getCriticalExtensionOIDs();
            if (criticalExtensions != null && !criticalExtensions.isEmpty()) {
                criticalExtensions.remove(KEY_USAGE);
                criticalExtensions.remove(CERTIFICATE_POLICIES);
                criticalExtensions.remove(POLICY_MAPPINGS);
                criticalExtensions.remove(INHIBIT_ANY_POLICY);
                criticalExtensions.remove(ISSUING_DISTRIBUTION_POINT);
                criticalExtensions.remove(DELTA_CRL_INDICATOR);
                criticalExtensions.remove(POLICY_CONSTRAINTS);
                criticalExtensions.remove(BASIC_CONSTRAINTS);
                criticalExtensions.remove(SUBJECT_ALTERNATIVE_NAME);
                criticalExtensions.remove(NAME_CONSTRAINTS);
                if (criticalExtensions.contains(QC_STATEMENT) && processQcStatements(cert, index)) {
                    criticalExtensions.remove(QC_STATEMENT);
                }
                for (PKIXCertPathChecker check : pathCheckers) {
                    check.check(cert, criticalExtensions);
                }
                if (!criticalExtensions.isEmpty()) {
                    for (String aSN1ObjectIdentifier : criticalExtensions) {
                        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = new ASN1ObjectIdentifier(aSN1ObjectIdentifier);
                        Object[] objArr = {aSN1ObjectIdentifier2};
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknownCriticalExt", objArr), index);
                    }
                }
            }
            index--;
        }
    }

    private boolean processQcStatements(X509Certificate cert, int index) {
        ErrorBundle msg;
        boolean unknownStatement = false;
        try {
            ASN1Sequence qcSt = (ASN1Sequence) getExtensionValue(cert, QC_STATEMENT);
            for (int j = 0; j < qcSt.size(); j++) {
                QCStatement stmt = QCStatement.getInstance(qcSt.getObjectAt(j));
                if (QCStatement.id_etsi_qcs_QcCompliance.equals(stmt.getStatementId())) {
                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcEuCompliance"), index);
                } else if (!QCStatement.id_qcs_pkixQCSyntax_v1.equals(stmt.getStatementId())) {
                    if (QCStatement.id_etsi_qcs_QcSSCD.equals(stmt.getStatementId())) {
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcSSCD"), index);
                    } else if (QCStatement.id_etsi_qcs_LimiteValue.equals(stmt.getStatementId())) {
                        MonetaryValue limit = MonetaryValue.getInstance(stmt.getStatementInfo());
                        Iso4217CurrencyCode currency = limit.getCurrency();
                        double value = limit.getAmount().doubleValue() * Math.pow(10.0d, limit.getExponent().doubleValue());
                        if (limit.getCurrency().isAlphabetic()) {
                            Double d = new Double(value);
                            msg = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueAlpha", new Object[]{limit.getCurrency().getAlphabetic(), new TrustedInput(d), limit});
                        } else {
                            Double d2 = new Double(value);
                            msg = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueNum", new Object[]{Integers.valueOf(limit.getCurrency().getNumeric()), new TrustedInput(d2), limit});
                        }
                        addNotification(msg, index);
                    } else {
                        UntrustedInput untrustedInput = new UntrustedInput(stmt);
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcUnknownStatement", new Object[]{stmt.getStatementId(), untrustedInput}), index);
                        unknownStatement = true;
                    }
                }
            }
            return !unknownStatement;
        } catch (AnnotatedException e) {
            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcStatementExtError"), index);
            return false;
        }
    }

    private String IPtoString(byte[] ip) {
        try {
            return InetAddress.getByAddress(ip).getHostAddress();
        } catch (Exception e) {
            StringBuffer b = new StringBuffer();
            for (int i = 0; i != ip.length; i++) {
                b.append(Integer.toHexString(ip[i] & 255));
                b.append(' ');
            }
            return b.toString();
        }
    }

    /* access modifiers changed from: protected */
    public void checkRevocation(PKIXParameters paramsPKIX, X509Certificate cert, Date validDate2, X509Certificate sign, PublicKey workingPublicKey, Vector crlDistPointUrls, Vector ocspUrls, int index) throws CertPathReviewerException {
        checkCRLs(paramsPKIX, cert, validDate2, sign, workingPublicKey, crlDistPointUrls, index);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x02b3, code lost:
        r10 = r26;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkCRLs(java.security.cert.PKIXParameters r39, java.security.cert.X509Certificate r40, java.util.Date r41, java.security.cert.X509Certificate r42, java.security.PublicKey r43, java.util.Vector r44, int r45) throws org.spongycastle.x509.CertPathReviewerException {
        /*
            r38 = this;
            org.spongycastle.x509.X509CRLStoreSelector r14 = new org.spongycastle.x509.X509CRLStoreSelector
            r14.<init>()
            javax.security.auth.x500.X500Principal r32 = getEncodedIssuerPrincipal(r40)     // Catch:{ IOException -> 0x01a2 }
            byte[] r32 = r32.getEncoded()     // Catch:{ IOException -> 0x01a2 }
            r0 = r32
            r14.addIssuerName(r0)     // Catch:{ IOException -> 0x01a2 }
            r0 = r40
            r14.setCertificateChecking(r0)
            org.spongycastle.x509.PKIXCRLUtil r32 = CRL_UTIL     // Catch:{ AnnotatedException -> 0x005d }
            r0 = r32
            r1 = r39
            java.util.Set r11 = r0.findCRLs(r14, r1)     // Catch:{ AnnotatedException -> 0x005d }
            java.util.Iterator r13 = r11.iterator()     // Catch:{ AnnotatedException -> 0x005d }
            boolean r32 = r11.isEmpty()     // Catch:{ AnnotatedException -> 0x005d }
            if (r32 == 0) goto L_0x00af
            org.spongycastle.x509.PKIXCRLUtil r32 = CRL_UTIL     // Catch:{ AnnotatedException -> 0x005d }
            org.spongycastle.x509.X509CRLStoreSelector r33 = new org.spongycastle.x509.X509CRLStoreSelector     // Catch:{ AnnotatedException -> 0x005d }
            r33.<init>()     // Catch:{ AnnotatedException -> 0x005d }
            r0 = r32
            r1 = r33
            r2 = r39
            java.util.Set r11 = r0.findCRLs(r1, r2)     // Catch:{ AnnotatedException -> 0x005d }
            java.util.Iterator r19 = r11.iterator()     // Catch:{ AnnotatedException -> 0x005d }
            java.util.ArrayList r24 = new java.util.ArrayList     // Catch:{ AnnotatedException -> 0x005d }
            r24.<init>()     // Catch:{ AnnotatedException -> 0x005d }
        L_0x0045:
            boolean r32 = r19.hasNext()     // Catch:{ AnnotatedException -> 0x005d }
            if (r32 == 0) goto L_0x01c0
            java.lang.Object r32 = r19.next()     // Catch:{ AnnotatedException -> 0x005d }
            java.security.cert.X509CRL r32 = (java.security.cert.X509CRL) r32     // Catch:{ AnnotatedException -> 0x005d }
            javax.security.auth.x500.X500Principal r32 = r32.getIssuerX500Principal()     // Catch:{ AnnotatedException -> 0x005d }
            r0 = r24
            r1 = r32
            r0.add(r1)     // Catch:{ AnnotatedException -> 0x005d }
            goto L_0x0045
        L_0x005d:
            r4 = move-exception
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.crlExtractionError"
            r34 = 3
            r0 = r34
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r34 = r0
            r35 = 0
            java.lang.Throwable r36 = r4.getCause()
            java.lang.String r36 = r36.getMessage()
            r34[r35] = r36
            r35 = 1
            java.lang.Throwable r36 = r4.getCause()
            r34[r35] = r36
            r35 = 2
            java.lang.Throwable r36 = r4.getCause()
            java.lang.Class r36 = r36.getClass()
            java.lang.String r36 = r36.getName()
            r34[r35] = r36
            r0 = r23
            r1 = r32
            r2 = r33
            r3 = r34
            r0.<init>(r1, r2, r3)
            r0 = r38
            r1 = r23
            r2 = r45
            r0.addError(r1, r2)
            java.util.ArrayList r32 = new java.util.ArrayList
            r32.<init>()
            java.util.Iterator r13 = r32.iterator()
        L_0x00af:
            r31 = 0
            r10 = 0
        L_0x00b2:
            boolean r32 = r13.hasNext()
            if (r32 == 0) goto L_0x0112
            java.lang.Object r10 = r13.next()
            java.security.cert.X509CRL r10 = (java.security.cert.X509CRL) r10
            java.util.Date r32 = r10.getNextUpdate()
            if (r32 == 0) goto L_0x00d2
            java.util.Date r32 = r39.getDate()
            java.util.Date r33 = r10.getNextUpdate()
            boolean r32 = r32.before(r33)
            if (r32 == 0) goto L_0x020c
        L_0x00d2:
            r31 = 1
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.localValidCRL"
            r34 = 2
            r0 = r34
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r34 = r0
            r35 = 0
            org.spongycastle.i18n.filter.TrustedInput r36 = new org.spongycastle.i18n.filter.TrustedInput
            java.util.Date r37 = r10.getThisUpdate()
            r36.<init>(r37)
            r34[r35] = r36
            r35 = 1
            org.spongycastle.i18n.filter.TrustedInput r36 = new org.spongycastle.i18n.filter.TrustedInput
            java.util.Date r37 = r10.getNextUpdate()
            r36.<init>(r37)
            r34[r35] = r36
            r0 = r23
            r1 = r32
            r2 = r33
            r3 = r34
            r0.<init>(r1, r2, r3)
            r0 = r38
            r1 = r23
            r2 = r45
            r0.addNotification(r1, r2)
        L_0x0112:
            if (r31 != 0) goto L_0x02b5
            r26 = 0
            java.util.Iterator r30 = r44.iterator()
        L_0x011a:
            boolean r32 = r30.hasNext()
            if (r32 == 0) goto L_0x02b5
            java.lang.Object r21 = r30.next()     // Catch:{ CertPathReviewerException -> 0x0192 }
            java.lang.String r21 = (java.lang.String) r21     // Catch:{ CertPathReviewerException -> 0x0192 }
            r0 = r38
            r1 = r21
            java.security.cert.X509CRL r26 = r0.getCRL(r1)     // Catch:{ CertPathReviewerException -> 0x0192 }
            if (r26 == 0) goto L_0x011a
            javax.security.auth.x500.X500Principal r32 = r40.getIssuerX500Principal()     // Catch:{ CertPathReviewerException -> 0x0192 }
            javax.security.auth.x500.X500Principal r33 = r26.getIssuerX500Principal()     // Catch:{ CertPathReviewerException -> 0x0192 }
            boolean r32 = r32.equals(r33)     // Catch:{ CertPathReviewerException -> 0x0192 }
            if (r32 != 0) goto L_0x024c
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x0192 }
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.onlineCRLWrongCA"
            r34 = 3
            r0 = r34
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ CertPathReviewerException -> 0x0192 }
            r34 = r0
            r35 = 0
            org.spongycastle.i18n.filter.UntrustedInput r36 = new org.spongycastle.i18n.filter.UntrustedInput     // Catch:{ CertPathReviewerException -> 0x0192 }
            javax.security.auth.x500.X500Principal r37 = r26.getIssuerX500Principal()     // Catch:{ CertPathReviewerException -> 0x0192 }
            java.lang.String r37 = r37.getName()     // Catch:{ CertPathReviewerException -> 0x0192 }
            r36.<init>(r37)     // Catch:{ CertPathReviewerException -> 0x0192 }
            r34[r35] = r36     // Catch:{ CertPathReviewerException -> 0x0192 }
            r35 = 1
            org.spongycastle.i18n.filter.UntrustedInput r36 = new org.spongycastle.i18n.filter.UntrustedInput     // Catch:{ CertPathReviewerException -> 0x0192 }
            javax.security.auth.x500.X500Principal r37 = r40.getIssuerX500Principal()     // Catch:{ CertPathReviewerException -> 0x0192 }
            java.lang.String r37 = r37.getName()     // Catch:{ CertPathReviewerException -> 0x0192 }
            r36.<init>(r37)     // Catch:{ CertPathReviewerException -> 0x0192 }
            r34[r35] = r36     // Catch:{ CertPathReviewerException -> 0x0192 }
            r35 = 2
            org.spongycastle.i18n.filter.UntrustedUrlInput r36 = new org.spongycastle.i18n.filter.UntrustedUrlInput     // Catch:{ CertPathReviewerException -> 0x0192 }
            r0 = r36
            r1 = r21
            r0.<init>(r1)     // Catch:{ CertPathReviewerException -> 0x0192 }
            r34[r35] = r36     // Catch:{ CertPathReviewerException -> 0x0192 }
            r0 = r23
            r1 = r32
            r2 = r33
            r3 = r34
            r0.<init>(r1, r2, r3)     // Catch:{ CertPathReviewerException -> 0x0192 }
            r0 = r38
            r1 = r23
            r2 = r45
            r0.addNotification(r1, r2)     // Catch:{ CertPathReviewerException -> 0x0192 }
            goto L_0x011a
        L_0x0192:
            r9 = move-exception
            org.spongycastle.i18n.ErrorBundle r32 = r9.getErrorMessage()
            r0 = r38
            r1 = r32
            r2 = r45
            r0.addNotification(r1, r2)
            goto L_0x011a
        L_0x01a2:
            r16 = move-exception
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.crlIssuerException"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r2 = r16
            r0.<init>(r1, r2)
            throw r32
        L_0x01c0:
            int r25 = r24.size()     // Catch:{ AnnotatedException -> 0x005d }
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ AnnotatedException -> 0x005d }
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.noCrlInCertstore"
            r34 = 3
            r0 = r34
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ AnnotatedException -> 0x005d }
            r34 = r0
            r35 = 0
            org.spongycastle.i18n.filter.UntrustedInput r36 = new org.spongycastle.i18n.filter.UntrustedInput     // Catch:{ AnnotatedException -> 0x005d }
            java.util.Collection r37 = r14.getIssuerNames()     // Catch:{ AnnotatedException -> 0x005d }
            r36.<init>(r37)     // Catch:{ AnnotatedException -> 0x005d }
            r34[r35] = r36     // Catch:{ AnnotatedException -> 0x005d }
            r35 = 1
            org.spongycastle.i18n.filter.UntrustedInput r36 = new org.spongycastle.i18n.filter.UntrustedInput     // Catch:{ AnnotatedException -> 0x005d }
            r0 = r36
            r1 = r24
            r0.<init>(r1)     // Catch:{ AnnotatedException -> 0x005d }
            r34[r35] = r36     // Catch:{ AnnotatedException -> 0x005d }
            r35 = 2
            java.lang.Integer r36 = org.spongycastle.util.Integers.valueOf(r25)     // Catch:{ AnnotatedException -> 0x005d }
            r34[r35] = r36     // Catch:{ AnnotatedException -> 0x005d }
            r0 = r23
            r1 = r32
            r2 = r33
            r3 = r34
            r0.<init>(r1, r2, r3)     // Catch:{ AnnotatedException -> 0x005d }
            r0 = r38
            r1 = r23
            r2 = r45
            r0.addNotification(r1, r2)     // Catch:{ AnnotatedException -> 0x005d }
            goto L_0x00af
        L_0x020c:
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.localInvalidCRL"
            r34 = 2
            r0 = r34
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r34 = r0
            r35 = 0
            org.spongycastle.i18n.filter.TrustedInput r36 = new org.spongycastle.i18n.filter.TrustedInput
            java.util.Date r37 = r10.getThisUpdate()
            r36.<init>(r37)
            r34[r35] = r36
            r35 = 1
            org.spongycastle.i18n.filter.TrustedInput r36 = new org.spongycastle.i18n.filter.TrustedInput
            java.util.Date r37 = r10.getNextUpdate()
            r36.<init>(r37)
            r34[r35] = r36
            r0 = r23
            r1 = r32
            r2 = r33
            r3 = r34
            r0.<init>(r1, r2, r3)
            r0 = r38
            r1 = r23
            r2 = r45
            r0.addNotification(r1, r2)
            goto L_0x00b2
        L_0x024c:
            java.util.Date r32 = r26.getNextUpdate()     // Catch:{ CertPathReviewerException -> 0x0192 }
            if (r32 == 0) goto L_0x0266
            r0 = r38
            java.security.cert.PKIXParameters r0 = r0.pkixParams     // Catch:{ CertPathReviewerException -> 0x0192 }
            r32 = r0
            java.util.Date r32 = r32.getDate()     // Catch:{ CertPathReviewerException -> 0x0192 }
            java.util.Date r33 = r26.getNextUpdate()     // Catch:{ CertPathReviewerException -> 0x0192 }
            boolean r32 = r32.before(r33)     // Catch:{ CertPathReviewerException -> 0x0192 }
            if (r32 == 0) goto L_0x02ed
        L_0x0266:
            r31 = 1
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x0192 }
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.onlineValidCRL"
            r34 = 3
            r0 = r34
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ CertPathReviewerException -> 0x0192 }
            r34 = r0
            r35 = 0
            org.spongycastle.i18n.filter.TrustedInput r36 = new org.spongycastle.i18n.filter.TrustedInput     // Catch:{ CertPathReviewerException -> 0x0192 }
            java.util.Date r37 = r26.getThisUpdate()     // Catch:{ CertPathReviewerException -> 0x0192 }
            r36.<init>(r37)     // Catch:{ CertPathReviewerException -> 0x0192 }
            r34[r35] = r36     // Catch:{ CertPathReviewerException -> 0x0192 }
            r35 = 1
            org.spongycastle.i18n.filter.TrustedInput r36 = new org.spongycastle.i18n.filter.TrustedInput     // Catch:{ CertPathReviewerException -> 0x0192 }
            java.util.Date r37 = r26.getNextUpdate()     // Catch:{ CertPathReviewerException -> 0x0192 }
            r36.<init>(r37)     // Catch:{ CertPathReviewerException -> 0x0192 }
            r34[r35] = r36     // Catch:{ CertPathReviewerException -> 0x0192 }
            r35 = 2
            org.spongycastle.i18n.filter.UntrustedUrlInput r36 = new org.spongycastle.i18n.filter.UntrustedUrlInput     // Catch:{ CertPathReviewerException -> 0x0192 }
            r0 = r36
            r1 = r21
            r0.<init>(r1)     // Catch:{ CertPathReviewerException -> 0x0192 }
            r34[r35] = r36     // Catch:{ CertPathReviewerException -> 0x0192 }
            r0 = r23
            r1 = r32
            r2 = r33
            r3 = r34
            r0.<init>(r1, r2, r3)     // Catch:{ CertPathReviewerException -> 0x0192 }
            r0 = r38
            r1 = r23
            r2 = r45
            r0.addNotification(r1, r2)     // Catch:{ CertPathReviewerException -> 0x0192 }
            r10 = r26
        L_0x02b5:
            if (r10 == 0) goto L_0x06b0
            if (r42 == 0) goto L_0x033a
            boolean[] r20 = r42.getKeyUsage()
            if (r20 == 0) goto L_0x033a
            r0 = r20
            int r0 = r0.length
            r32 = r0
            r33 = 7
            r0 = r32
            r1 = r33
            if (r0 < r1) goto L_0x02d2
            r32 = 6
            boolean r32 = r20[r32]
            if (r32 != 0) goto L_0x033a
        L_0x02d2:
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.noCrlSigningPermited"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1)
            throw r32
        L_0x02ed:
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle     // Catch:{ CertPathReviewerException -> 0x0192 }
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.onlineInvalidCRL"
            r34 = 3
            r0 = r34
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ CertPathReviewerException -> 0x0192 }
            r34 = r0
            r35 = 0
            org.spongycastle.i18n.filter.TrustedInput r36 = new org.spongycastle.i18n.filter.TrustedInput     // Catch:{ CertPathReviewerException -> 0x0192 }
            java.util.Date r37 = r26.getThisUpdate()     // Catch:{ CertPathReviewerException -> 0x0192 }
            r36.<init>(r37)     // Catch:{ CertPathReviewerException -> 0x0192 }
            r34[r35] = r36     // Catch:{ CertPathReviewerException -> 0x0192 }
            r35 = 1
            org.spongycastle.i18n.filter.TrustedInput r36 = new org.spongycastle.i18n.filter.TrustedInput     // Catch:{ CertPathReviewerException -> 0x0192 }
            java.util.Date r37 = r26.getNextUpdate()     // Catch:{ CertPathReviewerException -> 0x0192 }
            r36.<init>(r37)     // Catch:{ CertPathReviewerException -> 0x0192 }
            r34[r35] = r36     // Catch:{ CertPathReviewerException -> 0x0192 }
            r35 = 2
            org.spongycastle.i18n.filter.UntrustedUrlInput r36 = new org.spongycastle.i18n.filter.UntrustedUrlInput     // Catch:{ CertPathReviewerException -> 0x0192 }
            r0 = r36
            r1 = r21
            r0.<init>(r1)     // Catch:{ CertPathReviewerException -> 0x0192 }
            r34[r35] = r36     // Catch:{ CertPathReviewerException -> 0x0192 }
            r0 = r23
            r1 = r32
            r2 = r33
            r3 = r34
            r0.<init>(r1, r2, r3)     // Catch:{ CertPathReviewerException -> 0x0192 }
            r0 = r38
            r1 = r23
            r2 = r45
            r0.addNotification(r1, r2)     // Catch:{ CertPathReviewerException -> 0x0192 }
            goto L_0x011a
        L_0x033a:
            if (r43 == 0) goto L_0x03f0
            java.lang.String r32 = "SC"
            r0 = r43
            r1 = r32
            r10.verify(r0, r1)     // Catch:{ Exception -> 0x03d2 }
            java.math.BigInteger r32 = r40.getSerialNumber()
            r0 = r32
            java.security.cert.X509CRLEntry r12 = r10.getRevokedCertificate(r0)
            if (r12 == 0) goto L_0x053a
            r28 = 0
            boolean r32 = r12.hasExtensions()
            if (r32 == 0) goto L_0x0378
            org.spongycastle.asn1.ASN1ObjectIdentifier r32 = org.spongycastle.asn1.x509.X509Extensions.ReasonCode     // Catch:{ AnnotatedException -> 0x040b }
            java.lang.String r32 = r32.getId()     // Catch:{ AnnotatedException -> 0x040b }
            r0 = r32
            org.spongycastle.asn1.ASN1Primitive r32 = getExtensionValue(r12, r0)     // Catch:{ AnnotatedException -> 0x040b }
            org.spongycastle.asn1.ASN1Enumerated r29 = org.spongycastle.asn1.ASN1Enumerated.getInstance(r32)     // Catch:{ AnnotatedException -> 0x040b }
            if (r29 == 0) goto L_0x0378
            java.lang.String[] r32 = crlReasons
            java.math.BigInteger r33 = r29.getValue()
            int r33 = r33.intValue()
            r28 = r32[r33]
        L_0x0378:
            if (r28 != 0) goto L_0x0380
            java.lang.String[] r32 = crlReasons
            r33 = 7
            r28 = r32[r33]
        L_0x0380:
            org.spongycastle.i18n.LocaleString r22 = new org.spongycastle.i18n.LocaleString
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            r0 = r22
            r1 = r32
            r2 = r28
            r0.<init>(r1, r2)
            java.util.Date r32 = r12.getRevocationDate()
            r0 = r41
            r1 = r32
            boolean r32 = r0.before(r1)
            if (r32 != 0) goto L_0x0427
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.certRevoked"
            r34 = 2
            r0 = r34
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r34 = r0
            r35 = 0
            org.spongycastle.i18n.filter.TrustedInput r36 = new org.spongycastle.i18n.filter.TrustedInput
            java.util.Date r37 = r12.getRevocationDate()
            r36.<init>(r37)
            r34[r35] = r36
            r35 = 1
            r34[r35] = r22
            r0 = r23
            r1 = r32
            r2 = r33
            r3 = r34
            r0.<init>(r1, r2, r3)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1)
            throw r32
        L_0x03d2:
            r16 = move-exception
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.crlVerifyFailed"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r2 = r16
            r0.<init>(r1, r2)
            throw r32
        L_0x03f0:
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.crlNoIssuerPublicKey"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1)
            throw r32
        L_0x040b:
            r4 = move-exception
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.crlReasonExtError"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1, r4)
            throw r32
        L_0x0427:
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.revokedAfterValidation"
            r34 = 2
            r0 = r34
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r34 = r0
            r35 = 0
            org.spongycastle.i18n.filter.TrustedInput r36 = new org.spongycastle.i18n.filter.TrustedInput
            java.util.Date r37 = r12.getRevocationDate()
            r36.<init>(r37)
            r34[r35] = r36
            r35 = 1
            r34[r35] = r22
            r0 = r23
            r1 = r32
            r2 = r33
            r3 = r34
            r0.<init>(r1, r2, r3)
            r0 = r38
            r1 = r23
            r2 = r45
            r0.addNotification(r1, r2)
        L_0x045c:
            java.util.Date r32 = r10.getNextUpdate()
            if (r32 == 0) goto L_0x04a7
            java.util.Date r32 = r10.getNextUpdate()
            r0 = r38
            java.security.cert.PKIXParameters r0 = r0.pkixParams
            r33 = r0
            java.util.Date r33 = r33.getDate()
            boolean r32 = r32.before(r33)
            if (r32 == 0) goto L_0x04a7
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.crlUpdateAvailable"
            r34 = 1
            r0 = r34
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r34 = r0
            r35 = 0
            org.spongycastle.i18n.filter.TrustedInput r36 = new org.spongycastle.i18n.filter.TrustedInput
            java.util.Date r37 = r10.getNextUpdate()
            r36.<init>(r37)
            r34[r35] = r36
            r0 = r23
            r1 = r32
            r2 = r33
            r3 = r34
            r0.<init>(r1, r2, r3)
            r0 = r38
            r1 = r23
            r2 = r45
            r0.addNotification(r1, r2)
        L_0x04a7:
            java.lang.String r32 = ISSUING_DISTRIBUTION_POINT     // Catch:{ AnnotatedException -> 0x0556 }
            r0 = r32
            org.spongycastle.asn1.ASN1Primitive r18 = getExtensionValue(r10, r0)     // Catch:{ AnnotatedException -> 0x0556 }
            java.lang.String r32 = DELTA_CRL_INDICATOR     // Catch:{ AnnotatedException -> 0x0572 }
            r0 = r32
            org.spongycastle.asn1.ASN1Primitive r15 = getExtensionValue(r10, r0)     // Catch:{ AnnotatedException -> 0x0572 }
            if (r15 == 0) goto L_0x060c
            org.spongycastle.x509.X509CRLStoreSelector r7 = new org.spongycastle.x509.X509CRLStoreSelector
            r7.<init>()
            javax.security.auth.x500.X500Principal r32 = getIssuerPrincipal(r10)     // Catch:{ IOException -> 0x058e }
            byte[] r32 = r32.getEncoded()     // Catch:{ IOException -> 0x058e }
            r0 = r32
            r7.addIssuerName(r0)     // Catch:{ IOException -> 0x058e }
            org.spongycastle.asn1.ASN1Integer r15 = (org.spongycastle.asn1.ASN1Integer) r15
            java.math.BigInteger r32 = r15.getPositiveValue()
            r0 = r32
            r7.setMinCRLNumber(r0)
            java.lang.String r32 = CRL_NUMBER     // Catch:{ AnnotatedException -> 0x05ac }
            r0 = r32
            org.spongycastle.asn1.ASN1Primitive r32 = getExtensionValue(r10, r0)     // Catch:{ AnnotatedException -> 0x05ac }
            org.spongycastle.asn1.ASN1Integer r32 = (org.spongycastle.asn1.ASN1Integer) r32     // Catch:{ AnnotatedException -> 0x05ac }
            java.math.BigInteger r32 = r32.getPositiveValue()     // Catch:{ AnnotatedException -> 0x05ac }
            r34 = 1
            java.math.BigInteger r33 = java.math.BigInteger.valueOf(r34)     // Catch:{ AnnotatedException -> 0x05ac }
            java.math.BigInteger r32 = r32.subtract(r33)     // Catch:{ AnnotatedException -> 0x05ac }
            r0 = r32
            r7.setMaxCRLNumber(r0)     // Catch:{ AnnotatedException -> 0x05ac }
            r17 = 0
            org.spongycastle.x509.PKIXCRLUtil r32 = CRL_UTIL     // Catch:{ AnnotatedException -> 0x05c8 }
            r0 = r32
            r1 = r39
            java.util.Set r32 = r0.findCRLs(r7, r1)     // Catch:{ AnnotatedException -> 0x05c8 }
            java.util.Iterator r19 = r32.iterator()     // Catch:{ AnnotatedException -> 0x05c8 }
        L_0x0503:
            boolean r32 = r19.hasNext()
            if (r32 == 0) goto L_0x051d
            java.lang.Object r5 = r19.next()
            java.security.cert.X509CRL r5 = (java.security.cert.X509CRL) r5
            java.lang.String r32 = ISSUING_DISTRIBUTION_POINT     // Catch:{ AnnotatedException -> 0x05e4 }
            r0 = r32
            org.spongycastle.asn1.ASN1Primitive r6 = getExtensionValue(r5, r0)     // Catch:{ AnnotatedException -> 0x05e4 }
            if (r18 != 0) goto L_0x0600
            if (r6 != 0) goto L_0x0503
            r17 = 1
        L_0x051d:
            if (r17 != 0) goto L_0x060c
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.noBaseCRL"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1)
            throw r32
        L_0x053a:
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.notRevoked"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            r0 = r38
            r1 = r23
            r2 = r45
            r0.addNotification(r1, r2)
            goto L_0x045c
        L_0x0556:
            r4 = move-exception
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.distrPtExtError"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1)
            throw r32
        L_0x0572:
            r4 = move-exception
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.deltaCrlExtError"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1)
            throw r32
        L_0x058e:
            r16 = move-exception
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.crlIssuerException"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r2 = r16
            r0.<init>(r1, r2)
            throw r32
        L_0x05ac:
            r4 = move-exception
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.crlNbrExtError"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1, r4)
            throw r32
        L_0x05c8:
            r4 = move-exception
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.crlExtractionError"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1, r4)
            throw r32
        L_0x05e4:
            r4 = move-exception
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.distrPtExtError"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1, r4)
            throw r32
        L_0x0600:
            r0 = r18
            boolean r32 = r0.equals(r6)
            if (r32 == 0) goto L_0x0503
            r17 = 1
            goto L_0x051d
        L_0x060c:
            if (r18 == 0) goto L_0x06b0
            org.spongycastle.asn1.x509.IssuingDistributionPoint r27 = org.spongycastle.asn1.x509.IssuingDistributionPoint.getInstance(r18)
            r8 = 0
            java.lang.String r32 = BASIC_CONSTRAINTS     // Catch:{ AnnotatedException -> 0x064a }
            r0 = r40
            r1 = r32
            org.spongycastle.asn1.ASN1Primitive r32 = getExtensionValue(r0, r1)     // Catch:{ AnnotatedException -> 0x064a }
            org.spongycastle.asn1.x509.BasicConstraints r8 = org.spongycastle.asn1.x509.BasicConstraints.getInstance(r32)     // Catch:{ AnnotatedException -> 0x064a }
            boolean r32 = r27.onlyContainsUserCerts()
            if (r32 == 0) goto L_0x0666
            if (r8 == 0) goto L_0x0666
            boolean r32 = r8.isCA()
            if (r32 == 0) goto L_0x0666
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.crlOnlyUserCert"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1)
            throw r32
        L_0x064a:
            r4 = move-exception
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.crlBCExtError"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1, r4)
            throw r32
        L_0x0666:
            boolean r32 = r27.onlyContainsCACerts()
            if (r32 == 0) goto L_0x068f
            if (r8 == 0) goto L_0x0674
            boolean r32 = r8.isCA()
            if (r32 != 0) goto L_0x068f
        L_0x0674:
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.crlOnlyCaCert"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1)
            throw r32
        L_0x068f:
            boolean r32 = r27.onlyContainsAttributeCerts()
            if (r32 == 0) goto L_0x06b0
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.crlOnlyAttrCert"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1)
            throw r32
        L_0x06b0:
            if (r31 != 0) goto L_0x06cd
            org.spongycastle.i18n.ErrorBundle r23 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r32 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r33 = "CertPathReviewer.noValidCrlFound"
            r0 = r23
            r1 = r32
            r2 = r33
            r0.<init>(r1, r2)
            org.spongycastle.x509.CertPathReviewerException r32 = new org.spongycastle.x509.CertPathReviewerException
            r0 = r32
            r1 = r23
            r0.<init>(r1)
            throw r32
        L_0x06cd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.PKIXCertPathReviewer.checkCRLs(java.security.cert.PKIXParameters, java.security.cert.X509Certificate, java.util.Date, java.security.cert.X509Certificate, java.security.PublicKey, java.util.Vector, int):void");
    }

    /* access modifiers changed from: protected */
    public Vector getCRLDistUrls(CRLDistPoint crlDistPoints) {
        Vector urls = new Vector();
        if (crlDistPoints != null) {
            DistributionPoint[] distPoints = crlDistPoints.getDistributionPoints();
            for (DistributionPoint distributionPoint : distPoints) {
                DistributionPointName dp_name = distributionPoint.getDistributionPoint();
                if (dp_name.getType() == 0) {
                    GeneralName[] generalNames = GeneralNames.getInstance(dp_name.getName()).getNames();
                    for (int j = 0; j < generalNames.length; j++) {
                        if (generalNames[j].getTagNo() == 6) {
                            urls.add(((DERIA5String) generalNames[j].getName()).getString());
                        }
                    }
                }
            }
        }
        return urls;
    }

    /* access modifiers changed from: protected */
    public Vector getOCSPUrls(AuthorityInformationAccess authInfoAccess) {
        Vector urls = new Vector();
        if (authInfoAccess != null) {
            AccessDescription[] ads = authInfoAccess.getAccessDescriptions();
            for (int i = 0; i < ads.length; i++) {
                if (ads[i].getAccessMethod().equals(AccessDescription.id_ad_ocsp)) {
                    GeneralName name = ads[i].getAccessLocation();
                    if (name.getTagNo() == 6) {
                        urls.add(((DERIA5String) name.getName()).getString());
                    }
                }
            }
        }
        return urls;
    }

    private X509CRL getCRL(String location) throws CertPathReviewerException {
        try {
            URL url = new URL(location);
            if (!url.getProtocol().equals(UriUtil.HTTP_SCHEME) && !url.getProtocol().equals(UriUtil.HTTPS_SCHEME)) {
                return null;
            }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                return (X509CRL) CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME).generateCRL(conn.getInputStream());
            }
            throw new Exception(conn.getResponseMessage());
        } catch (Exception e) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.loadCrlDistPointError", new Object[]{new UntrustedInput(location), e.getMessage(), e, e.getClass().getName()}));
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.security.cert.TrustAnchor>, for r16v0, types: [java.util.Set<java.security.cert.TrustAnchor>, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Collection getTrustAnchors(java.security.cert.X509Certificate r15, java.util.Set<java.security.cert.TrustAnchor> r16) throws org.spongycastle.x509.CertPathReviewerException {
        /*
            r14 = this;
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.Iterator r6 = r16.iterator()
            java.security.cert.X509CertSelector r3 = new java.security.cert.X509CertSelector
            r3.<init>()
            javax.security.auth.x500.X500Principal r12 = getEncodedIssuerPrincipal(r15)     // Catch:{ IOException -> 0x0070 }
            byte[] r12 = r12.getEncoded()     // Catch:{ IOException -> 0x0070 }
            r3.setSubject(r12)     // Catch:{ IOException -> 0x0070 }
            org.spongycastle.asn1.ASN1ObjectIdentifier r12 = org.spongycastle.asn1.x509.X509Extensions.AuthorityKeyIdentifier     // Catch:{ IOException -> 0x0070 }
            java.lang.String r12 = r12.getId()     // Catch:{ IOException -> 0x0070 }
            byte[] r5 = r15.getExtensionValue(r12)     // Catch:{ IOException -> 0x0070 }
            if (r5 == 0) goto L_0x0050
            org.spongycastle.asn1.ASN1Primitive r9 = org.spongycastle.asn1.ASN1Primitive.fromByteArray(r5)     // Catch:{ IOException -> 0x0070 }
            org.spongycastle.asn1.ASN1OctetString r9 = (org.spongycastle.asn1.ASN1OctetString) r9     // Catch:{ IOException -> 0x0070 }
            byte[] r12 = r9.getOctets()     // Catch:{ IOException -> 0x0070 }
            org.spongycastle.asn1.ASN1Primitive r12 = org.spongycastle.asn1.ASN1Primitive.fromByteArray(r12)     // Catch:{ IOException -> 0x0070 }
            org.spongycastle.asn1.x509.AuthorityKeyIdentifier r0 = org.spongycastle.asn1.x509.AuthorityKeyIdentifier.getInstance(r12)     // Catch:{ IOException -> 0x0070 }
            java.math.BigInteger r12 = r0.getAuthorityCertSerialNumber()     // Catch:{ IOException -> 0x0070 }
            r3.setSerialNumber(r12)     // Catch:{ IOException -> 0x0070 }
            byte[] r7 = r0.getKeyIdentifier()     // Catch:{ IOException -> 0x0070 }
            if (r7 == 0) goto L_0x0050
            org.spongycastle.asn1.DEROctetString r12 = new org.spongycastle.asn1.DEROctetString     // Catch:{ IOException -> 0x0070 }
            r12.<init>(r7)     // Catch:{ IOException -> 0x0070 }
            byte[] r12 = r12.getEncoded()     // Catch:{ IOException -> 0x0070 }
            r3.setSubjectKeyIdentifier(r12)     // Catch:{ IOException -> 0x0070 }
        L_0x0050:
            boolean r12 = r6.hasNext()
            if (r12 == 0) goto L_0x00a5
            java.lang.Object r10 = r6.next()
            java.security.cert.TrustAnchor r10 = (java.security.cert.TrustAnchor) r10
            java.security.cert.X509Certificate r12 = r10.getTrustedCert()
            if (r12 == 0) goto L_0x0082
            java.security.cert.X509Certificate r12 = r10.getTrustedCert()
            boolean r12 = r3.match(r12)
            if (r12 == 0) goto L_0x0050
            r11.add(r10)
            goto L_0x0050
        L_0x0070:
            r4 = move-exception
            org.spongycastle.i18n.ErrorBundle r8 = new org.spongycastle.i18n.ErrorBundle
            java.lang.String r12 = "org.spongycastle.x509.CertPathReviewerMessages"
            java.lang.String r13 = "CertPathReviewer.trustAnchorIssuerError"
            r8.<init>(r12, r13)
            org.spongycastle.x509.CertPathReviewerException r12 = new org.spongycastle.x509.CertPathReviewerException
            r12.<init>(r8)
            throw r12
        L_0x0082:
            java.lang.String r12 = r10.getCAName()
            if (r12 == 0) goto L_0x0050
            java.security.PublicKey r12 = r10.getCAPublicKey()
            if (r12 == 0) goto L_0x0050
            javax.security.auth.x500.X500Principal r2 = getEncodedIssuerPrincipal(r15)
            javax.security.auth.x500.X500Principal r1 = new javax.security.auth.x500.X500Principal
            java.lang.String r12 = r10.getCAName()
            r1.<init>(r12)
            boolean r12 = r2.equals(r1)
            if (r12 == 0) goto L_0x0050
            r11.add(r10)
            goto L_0x0050
        L_0x00a5:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.PKIXCertPathReviewer.getTrustAnchors(java.security.cert.X509Certificate, java.util.Set):java.util.Collection");
    }
}
