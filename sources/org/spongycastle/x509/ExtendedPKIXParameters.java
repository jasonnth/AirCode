package org.spongycastle.x509;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CertSelector;
import java.security.cert.PKIXParameters;
import java.security.cert.X509CertSelector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.spongycastle.util.Selector;
import org.spongycastle.util.Store;

public class ExtendedPKIXParameters extends PKIXParameters {
    public static final int CHAIN_VALIDITY_MODEL = 1;
    public static final int PKIX_VALIDITY_MODEL = 0;
    private boolean additionalLocationsEnabled;
    private List additionalStores = new ArrayList();
    private Set attrCertCheckers = new HashSet();
    private Set necessaryACAttributes = new HashSet();
    private Set prohibitedACAttributes = new HashSet();
    private Selector selector;
    private List stores = new ArrayList();
    private Set trustedACIssuers = new HashSet();
    private boolean useDeltas = false;
    private int validityModel = 0;

    public ExtendedPKIXParameters(Set trustAnchors) throws InvalidAlgorithmParameterException {
        super(trustAnchors);
    }

    public static ExtendedPKIXParameters getInstance(PKIXParameters pkixParams) {
        try {
            ExtendedPKIXParameters params = new ExtendedPKIXParameters(pkixParams.getTrustAnchors());
            params.setParams(pkixParams);
            return params;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void setParams(PKIXParameters params) {
        Selector selector2;
        setDate(params.getDate());
        setCertPathCheckers(params.getCertPathCheckers());
        setCertStores(params.getCertStores());
        setAnyPolicyInhibited(params.isAnyPolicyInhibited());
        setExplicitPolicyRequired(params.isExplicitPolicyRequired());
        setPolicyMappingInhibited(params.isPolicyMappingInhibited());
        setRevocationEnabled(params.isRevocationEnabled());
        setInitialPolicies(params.getInitialPolicies());
        setPolicyQualifiersRejected(params.getPolicyQualifiersRejected());
        setSigProvider(params.getSigProvider());
        setTargetCertConstraints(params.getTargetCertConstraints());
        try {
            setTrustAnchors(params.getTrustAnchors());
            if (params instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters _params = (ExtendedPKIXParameters) params;
                this.validityModel = _params.validityModel;
                this.useDeltas = _params.useDeltas;
                this.additionalLocationsEnabled = _params.additionalLocationsEnabled;
                if (_params.selector == null) {
                    selector2 = null;
                } else {
                    selector2 = (Selector) _params.selector.clone();
                }
                this.selector = selector2;
                this.stores = new ArrayList(_params.stores);
                this.additionalStores = new ArrayList(_params.additionalStores);
                this.trustedACIssuers = new HashSet(_params.trustedACIssuers);
                this.prohibitedACAttributes = new HashSet(_params.prohibitedACAttributes);
                this.necessaryACAttributes = new HashSet(_params.necessaryACAttributes);
                this.attrCertCheckers = new HashSet(_params.attrCertCheckers);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean isUseDeltasEnabled() {
        return this.useDeltas;
    }

    public void setUseDeltasEnabled(boolean useDeltas2) {
        this.useDeltas = useDeltas2;
    }

    public int getValidityModel() {
        return this.validityModel;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<java.security.cert.CertStore>, for r3v0, types: [java.util.List, java.util.List<java.security.cert.CertStore>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setCertStores(java.util.List<java.security.cert.CertStore> r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0016
            java.util.Iterator r0 = r3.iterator()
        L_0x0006:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0016
            java.lang.Object r1 = r0.next()
            java.security.cert.CertStore r1 = (java.security.cert.CertStore) r1
            r2.addCertStore(r1)
            goto L_0x0006
        L_0x0016:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.ExtendedPKIXParameters.setCertStores(java.util.List):void");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<java.lang.Object>, for r4v0, types: [java.util.List, java.util.Collection, java.util.List<java.lang.Object>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setStores(java.util.List<java.lang.Object> r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x000a
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r3.stores = r1
        L_0x0009:
            return
        L_0x000a:
            java.util.Iterator r0 = r4.iterator()
        L_0x000e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0025
            java.lang.Object r1 = r0.next()
            boolean r1 = r1 instanceof org.spongycastle.util.Store
            if (r1 != 0) goto L_0x000e
            java.lang.ClassCastException r1 = new java.lang.ClassCastException
            java.lang.String r2 = "All elements of list must be of type org.spongycastle.util.Store."
            r1.<init>(r2)
            throw r1
        L_0x0025:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r4)
            r3.stores = r1
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.ExtendedPKIXParameters.setStores(java.util.List):void");
    }

    public void addStore(Store store) {
        if (store != null) {
            this.stores.add(store);
        }
    }

    public void addAdditionalStore(Store store) {
        if (store != null) {
            this.additionalStores.add(store);
        }
    }

    public void addAddionalStore(Store store) {
        addAdditionalStore(store);
    }

    public List getAdditionalStores() {
        return Collections.unmodifiableList(this.additionalStores);
    }

    public List getStores() {
        return Collections.unmodifiableList(new ArrayList(this.stores));
    }

    public void setValidityModel(int validityModel2) {
        this.validityModel = validityModel2;
    }

    public Object clone() {
        try {
            ExtendedPKIXParameters params = new ExtendedPKIXParameters(getTrustAnchors());
            params.setParams(this);
            return params;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean isAdditionalLocationsEnabled() {
        return this.additionalLocationsEnabled;
    }

    public void setAdditionalLocationsEnabled(boolean enabled) {
        this.additionalLocationsEnabled = enabled;
    }

    public Selector getTargetConstraints() {
        if (this.selector != null) {
            return (Selector) this.selector.clone();
        }
        return null;
    }

    public void setTargetConstraints(Selector selector2) {
        if (selector2 != null) {
            this.selector = (Selector) selector2.clone();
        } else {
            this.selector = null;
        }
    }

    public void setTargetCertConstraints(CertSelector selector2) {
        super.setTargetCertConstraints(selector2);
        if (selector2 != null) {
            this.selector = X509CertStoreSelector.getInstance((X509CertSelector) selector2);
        } else {
            this.selector = null;
        }
    }

    public Set getTrustedACIssuers() {
        return Collections.unmodifiableSet(this.trustedACIssuers);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.Object>, for r5v0, types: [java.util.Set<java.lang.Object>, java.util.Collection, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setTrustedACIssuers(java.util.Set<java.lang.Object> r5) {
        /*
            r4 = this;
            if (r5 != 0) goto L_0x0008
            java.util.Set r1 = r4.trustedACIssuers
            r1.clear()
        L_0x0007:
            return
        L_0x0008:
            java.util.Iterator r0 = r5.iterator()
        L_0x000c:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0041
            java.lang.Object r1 = r0.next()
            boolean r1 = r1 instanceof java.security.cert.TrustAnchor
            if (r1 != 0) goto L_0x000c
            java.lang.ClassCastException r1 = new java.lang.ClassCastException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "All elements of set must be of type "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.Class<java.security.cert.TrustAnchor> r3 = java.security.cert.TrustAnchor.class
            java.lang.String r3 = r3.getName()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = "."
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0041:
            java.util.Set r1 = r4.trustedACIssuers
            r1.clear()
            java.util.Set r1 = r4.trustedACIssuers
            r1.addAll(r5)
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.ExtendedPKIXParameters.setTrustedACIssuers(java.util.Set):void");
    }

    public Set getNecessaryACAttributes() {
        return Collections.unmodifiableSet(this.necessaryACAttributes);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.Object>, for r4v0, types: [java.util.Set<java.lang.Object>, java.util.Collection, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setNecessaryACAttributes(java.util.Set<java.lang.Object> r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0008
            java.util.Set r1 = r3.necessaryACAttributes
            r1.clear()
        L_0x0007:
            return
        L_0x0008:
            java.util.Iterator r0 = r4.iterator()
        L_0x000c:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0023
            java.lang.Object r1 = r0.next()
            boolean r1 = r1 instanceof java.lang.String
            if (r1 != 0) goto L_0x000c
            java.lang.ClassCastException r1 = new java.lang.ClassCastException
            java.lang.String r2 = "All elements of set must be of type String."
            r1.<init>(r2)
            throw r1
        L_0x0023:
            java.util.Set r1 = r3.necessaryACAttributes
            r1.clear()
            java.util.Set r1 = r3.necessaryACAttributes
            r1.addAll(r4)
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.ExtendedPKIXParameters.setNecessaryACAttributes(java.util.Set):void");
    }

    public Set getProhibitedACAttributes() {
        return Collections.unmodifiableSet(this.prohibitedACAttributes);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.Object>, for r4v0, types: [java.util.Set<java.lang.Object>, java.util.Collection, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setProhibitedACAttributes(java.util.Set<java.lang.Object> r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L_0x0008
            java.util.Set r1 = r3.prohibitedACAttributes
            r1.clear()
        L_0x0007:
            return
        L_0x0008:
            java.util.Iterator r0 = r4.iterator()
        L_0x000c:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0023
            java.lang.Object r1 = r0.next()
            boolean r1 = r1 instanceof java.lang.String
            if (r1 != 0) goto L_0x000c
            java.lang.ClassCastException r1 = new java.lang.ClassCastException
            java.lang.String r2 = "All elements of set must be of type String."
            r1.<init>(r2)
            throw r1
        L_0x0023:
            java.util.Set r1 = r3.prohibitedACAttributes
            r1.clear()
            java.util.Set r1 = r3.prohibitedACAttributes
            r1.addAll(r4)
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.ExtendedPKIXParameters.setProhibitedACAttributes(java.util.Set):void");
    }

    public Set getAttrCertCheckers() {
        return Collections.unmodifiableSet(this.attrCertCheckers);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.Object>, for r5v0, types: [java.util.Set<java.lang.Object>, java.util.Collection, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setAttrCertCheckers(java.util.Set<java.lang.Object> r5) {
        /*
            r4 = this;
            if (r5 != 0) goto L_0x0008
            java.util.Set r1 = r4.attrCertCheckers
            r1.clear()
        L_0x0007:
            return
        L_0x0008:
            java.util.Iterator r0 = r5.iterator()
        L_0x000c:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0041
            java.lang.Object r1 = r0.next()
            boolean r1 = r1 instanceof org.spongycastle.x509.PKIXAttrCertChecker
            if (r1 != 0) goto L_0x000c
            java.lang.ClassCastException r1 = new java.lang.ClassCastException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "All elements of set must be of type "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.Class<org.spongycastle.x509.PKIXAttrCertChecker> r3 = org.spongycastle.x509.PKIXAttrCertChecker.class
            java.lang.String r3 = r3.getName()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = "."
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0041:
            java.util.Set r1 = r4.attrCertCheckers
            r1.clear()
            java.util.Set r1 = r4.attrCertCheckers
            r1.addAll(r5)
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.ExtendedPKIXParameters.setAttrCertCheckers(java.util.Set):void");
    }
}
