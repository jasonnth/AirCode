package org.spongycastle.asn1.x509;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;
import org.spongycastle.util.Strings;

public class PKIXNameConstraintValidator implements NameConstraintValidator {
    private Set excludedSubtreesDN = new HashSet();
    private Set excludedSubtreesDNS = new HashSet();
    private Set excludedSubtreesEmail = new HashSet();
    private Set excludedSubtreesIP = new HashSet();
    private Set excludedSubtreesURI = new HashSet();
    private Set permittedSubtreesDN;
    private Set permittedSubtreesDNS;
    private Set permittedSubtreesEmail;
    private Set permittedSubtreesIP;
    private Set permittedSubtreesURI;

    public void checkPermitted(GeneralName name) throws NameConstraintValidatorException {
        switch (name.getTagNo()) {
            case 1:
                checkPermittedEmail(this.permittedSubtreesEmail, extractNameAsString(name));
                return;
            case 2:
                checkPermittedDNS(this.permittedSubtreesDNS, DERIA5String.getInstance(name.getName()).getString());
                return;
            case 4:
                checkPermittedDN(X500Name.getInstance(name.getName()));
                return;
            case 6:
                checkPermittedURI(this.permittedSubtreesURI, DERIA5String.getInstance(name.getName()).getString());
                return;
            case 7:
                checkPermittedIP(this.permittedSubtreesIP, ASN1OctetString.getInstance(name.getName()).getOctets());
                return;
            default:
                return;
        }
    }

    public void checkExcluded(GeneralName name) throws NameConstraintValidatorException {
        switch (name.getTagNo()) {
            case 1:
                checkExcludedEmail(this.excludedSubtreesEmail, extractNameAsString(name));
                return;
            case 2:
                checkExcludedDNS(this.excludedSubtreesDNS, DERIA5String.getInstance(name.getName()).getString());
                return;
            case 4:
                checkExcludedDN(X500Name.getInstance(name.getName()));
                return;
            case 6:
                checkExcludedURI(this.excludedSubtreesURI, DERIA5String.getInstance(name.getName()).getString());
                return;
            case 7:
                checkExcludedIP(this.excludedSubtreesIP, ASN1OctetString.getInstance(name.getName()).getOctets());
                return;
            default:
                return;
        }
    }

    public void intersectPermittedSubtree(GeneralSubtree permitted) {
        intersectPermittedSubtree(new GeneralSubtree[]{permitted});
    }

    public void intersectPermittedSubtree(GeneralSubtree[] permitted) {
        Map subtreesMap = new HashMap();
        for (int i = 0; i != permitted.length; i++) {
            GeneralSubtree subtree = permitted[i];
            Integer tagNo = Integers.valueOf(subtree.getBase().getTagNo());
            if (subtreesMap.get(tagNo) == null) {
                subtreesMap.put(tagNo, new HashSet());
            }
            ((Set) subtreesMap.get(tagNo)).add(subtree);
        }
        for (Entry entry : subtreesMap.entrySet()) {
            switch (((Integer) entry.getKey()).intValue()) {
                case 1:
                    this.permittedSubtreesEmail = intersectEmail(this.permittedSubtreesEmail, (Set) entry.getValue());
                    break;
                case 2:
                    this.permittedSubtreesDNS = intersectDNS(this.permittedSubtreesDNS, (Set) entry.getValue());
                    break;
                case 4:
                    this.permittedSubtreesDN = intersectDN(this.permittedSubtreesDN, (Set) entry.getValue());
                    break;
                case 6:
                    this.permittedSubtreesURI = intersectURI(this.permittedSubtreesURI, (Set) entry.getValue());
                    break;
                case 7:
                    this.permittedSubtreesIP = intersectIP(this.permittedSubtreesIP, (Set) entry.getValue());
                    break;
            }
        }
    }

    public void intersectEmptyPermittedSubtree(int nameType) {
        switch (nameType) {
            case 1:
                this.permittedSubtreesEmail = new HashSet();
                return;
            case 2:
                this.permittedSubtreesDNS = new HashSet();
                return;
            case 4:
                this.permittedSubtreesDN = new HashSet();
                return;
            case 6:
                this.permittedSubtreesURI = new HashSet();
                return;
            case 7:
                this.permittedSubtreesIP = new HashSet();
                return;
            default:
                return;
        }
    }

    public void addExcludedSubtree(GeneralSubtree subtree) {
        GeneralName base = subtree.getBase();
        switch (base.getTagNo()) {
            case 1:
                this.excludedSubtreesEmail = unionEmail(this.excludedSubtreesEmail, extractNameAsString(base));
                return;
            case 2:
                this.excludedSubtreesDNS = unionDNS(this.excludedSubtreesDNS, extractNameAsString(base));
                return;
            case 4:
                this.excludedSubtreesDN = unionDN(this.excludedSubtreesDN, (ASN1Sequence) base.getName().toASN1Primitive());
                return;
            case 6:
                this.excludedSubtreesURI = unionURI(this.excludedSubtreesURI, extractNameAsString(base));
                return;
            case 7:
                this.excludedSubtreesIP = unionIP(this.excludedSubtreesIP, ASN1OctetString.getInstance(base.getName()).getOctets());
                return;
            default:
                return;
        }
    }

    public int hashCode() {
        return hashCollection(this.excludedSubtreesDN) + hashCollection(this.excludedSubtreesDNS) + hashCollection(this.excludedSubtreesEmail) + hashCollection(this.excludedSubtreesIP) + hashCollection(this.excludedSubtreesURI) + hashCollection(this.permittedSubtreesDN) + hashCollection(this.permittedSubtreesDNS) + hashCollection(this.permittedSubtreesEmail) + hashCollection(this.permittedSubtreesIP) + hashCollection(this.permittedSubtreesURI);
    }

    public boolean equals(Object o) {
        if (!(o instanceof PKIXNameConstraintValidator)) {
            return false;
        }
        PKIXNameConstraintValidator constraintValidator = (PKIXNameConstraintValidator) o;
        if (!collectionsAreEqual(constraintValidator.excludedSubtreesDN, this.excludedSubtreesDN) || !collectionsAreEqual(constraintValidator.excludedSubtreesDNS, this.excludedSubtreesDNS) || !collectionsAreEqual(constraintValidator.excludedSubtreesEmail, this.excludedSubtreesEmail) || !collectionsAreEqual(constraintValidator.excludedSubtreesIP, this.excludedSubtreesIP) || !collectionsAreEqual(constraintValidator.excludedSubtreesURI, this.excludedSubtreesURI) || !collectionsAreEqual(constraintValidator.permittedSubtreesDN, this.permittedSubtreesDN) || !collectionsAreEqual(constraintValidator.permittedSubtreesDNS, this.permittedSubtreesDNS) || !collectionsAreEqual(constraintValidator.permittedSubtreesEmail, this.permittedSubtreesEmail) || !collectionsAreEqual(constraintValidator.permittedSubtreesIP, this.permittedSubtreesIP) || !collectionsAreEqual(constraintValidator.permittedSubtreesURI, this.permittedSubtreesURI)) {
            return false;
        }
        return true;
    }

    public String toString() {
        String temp = "" + "permitted:\n";
        if (this.permittedSubtreesDN != null) {
            temp = (temp + "DN:\n") + this.permittedSubtreesDN.toString() + "\n";
        }
        if (this.permittedSubtreesDNS != null) {
            temp = (temp + "DNS:\n") + this.permittedSubtreesDNS.toString() + "\n";
        }
        if (this.permittedSubtreesEmail != null) {
            temp = (temp + "Email:\n") + this.permittedSubtreesEmail.toString() + "\n";
        }
        if (this.permittedSubtreesURI != null) {
            temp = (temp + "URI:\n") + this.permittedSubtreesURI.toString() + "\n";
        }
        if (this.permittedSubtreesIP != null) {
            temp = (temp + "IP:\n") + stringifyIPCollection(this.permittedSubtreesIP) + "\n";
        }
        String temp2 = temp + "excluded:\n";
        if (!this.excludedSubtreesDN.isEmpty()) {
            temp2 = (temp2 + "DN:\n") + this.excludedSubtreesDN.toString() + "\n";
        }
        if (!this.excludedSubtreesDNS.isEmpty()) {
            temp2 = (temp2 + "DNS:\n") + this.excludedSubtreesDNS.toString() + "\n";
        }
        if (!this.excludedSubtreesEmail.isEmpty()) {
            temp2 = (temp2 + "Email:\n") + this.excludedSubtreesEmail.toString() + "\n";
        }
        if (!this.excludedSubtreesURI.isEmpty()) {
            temp2 = (temp2 + "URI:\n") + this.excludedSubtreesURI.toString() + "\n";
        }
        if (this.excludedSubtreesIP.isEmpty()) {
            return temp2;
        }
        return (temp2 + "IP:\n") + stringifyIPCollection(this.excludedSubtreesIP) + "\n";
    }

    private void checkPermittedDN(X500Name dns) throws NameConstraintValidatorException {
        checkPermittedDN(this.permittedSubtreesDN, ASN1Sequence.getInstance(dns.toASN1Primitive()));
    }

    private void checkExcludedDN(X500Name dns) throws NameConstraintValidatorException {
        checkExcludedDN(this.excludedSubtreesDN, ASN1Sequence.getInstance(dns));
    }

    private static boolean withinDNSubtree(ASN1Sequence dns, ASN1Sequence subtree) {
        if (subtree.size() < 1 || subtree.size() > dns.size()) {
            return false;
        }
        for (int j = subtree.size() - 1; j >= 0; j--) {
            if (!subtree.getObjectAt(j).equals(dns.getObjectAt(j))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<org.spongycastle.asn1.ASN1Sequence>, for r5v0, types: [java.util.Set, java.util.Set<org.spongycastle.asn1.ASN1Sequence>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkPermittedDN(java.util.Set<org.spongycastle.asn1.ASN1Sequence> r5, org.spongycastle.asn1.ASN1Sequence r6) throws org.spongycastle.asn1.x509.NameConstraintValidatorException {
        /*
            r4 = this;
            if (r5 != 0) goto L_0x0003
        L_0x0002:
            return
        L_0x0003:
            boolean r2 = r5.isEmpty()
            if (r2 == 0) goto L_0x000f
            int r2 = r6.size()
            if (r2 == 0) goto L_0x0002
        L_0x000f:
            java.util.Iterator r0 = r5.iterator()
        L_0x0013:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0026
            java.lang.Object r1 = r0.next()
            org.spongycastle.asn1.ASN1Sequence r1 = (org.spongycastle.asn1.ASN1Sequence) r1
            boolean r2 = withinDNSubtree(r6, r1)
            if (r2 == 0) goto L_0x0013
            goto L_0x0002
        L_0x0026:
            org.spongycastle.asn1.x509.NameConstraintValidatorException r2 = new org.spongycastle.asn1.x509.NameConstraintValidatorException
            java.lang.String r3 = "Subject distinguished name is not from a permitted subtree"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.checkPermittedDN(java.util.Set, org.spongycastle.asn1.ASN1Sequence):void");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<org.spongycastle.asn1.ASN1Sequence>, for r5v0, types: [java.util.Set, java.util.Set<org.spongycastle.asn1.ASN1Sequence>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkExcludedDN(java.util.Set<org.spongycastle.asn1.ASN1Sequence> r5, org.spongycastle.asn1.ASN1Sequence r6) throws org.spongycastle.asn1.x509.NameConstraintValidatorException {
        /*
            r4 = this;
            boolean r2 = r5.isEmpty()
            if (r2 == 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            java.util.Iterator r0 = r5.iterator()
        L_0x000b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0006
            java.lang.Object r1 = r0.next()
            org.spongycastle.asn1.ASN1Sequence r1 = (org.spongycastle.asn1.ASN1Sequence) r1
            boolean r2 = withinDNSubtree(r6, r1)
            if (r2 == 0) goto L_0x000b
            org.spongycastle.asn1.x509.NameConstraintValidatorException r2 = new org.spongycastle.asn1.x509.NameConstraintValidatorException
            java.lang.String r3 = "Subject distinguished name is from an excluded subtree"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.checkExcludedDN(java.util.Set, org.spongycastle.asn1.ASN1Sequence):void");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<org.spongycastle.asn1.ASN1Sequence>, for r7v0, types: [java.util.Set, java.util.Set<org.spongycastle.asn1.ASN1Sequence>] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree>, for r8v0, types: [java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree>, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set intersectDN(java.util.Set<org.spongycastle.asn1.ASN1Sequence> r7, java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree> r8) {
        /*
            r6 = this;
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.Iterator r3 = r8.iterator()
        L_0x0009:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0051
            java.lang.Object r5 = r3.next()
            org.spongycastle.asn1.x509.GeneralSubtree r5 = (org.spongycastle.asn1.x509.GeneralSubtree) r5
            org.spongycastle.asn1.x509.GeneralName r5 = r5.getBase()
            org.spongycastle.asn1.ASN1Encodable r5 = r5.getName()
            org.spongycastle.asn1.ASN1Primitive r5 = r5.toASN1Primitive()
            org.spongycastle.asn1.ASN1Sequence r1 = org.spongycastle.asn1.ASN1Sequence.getInstance(r5)
            if (r7 != 0) goto L_0x002d
            if (r1 == 0) goto L_0x0009
            r2.add(r1)
            goto L_0x0009
        L_0x002d:
            java.util.Iterator r0 = r7.iterator()
        L_0x0031:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x0009
            java.lang.Object r4 = r0.next()
            org.spongycastle.asn1.ASN1Sequence r4 = (org.spongycastle.asn1.ASN1Sequence) r4
            boolean r5 = withinDNSubtree(r1, r4)
            if (r5 == 0) goto L_0x0047
            r2.add(r1)
            goto L_0x0031
        L_0x0047:
            boolean r5 = withinDNSubtree(r4, r1)
            if (r5 == 0) goto L_0x0031
            r2.add(r4)
            goto L_0x0031
        L_0x0051:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.intersectDN(java.util.Set, java.util.Set):java.util.Set");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<org.spongycastle.asn1.ASN1Sequence>, for r5v0, types: [java.util.Set, java.util.Set<org.spongycastle.asn1.ASN1Sequence>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set unionDN(java.util.Set<org.spongycastle.asn1.ASN1Sequence> r5, org.spongycastle.asn1.ASN1Sequence r6) {
        /*
            r4 = this;
            boolean r3 = r5.isEmpty()
            if (r3 == 0) goto L_0x000d
            if (r6 != 0) goto L_0x0009
        L_0x0008:
            return r5
        L_0x0009:
            r5.add(r6)
            goto L_0x0008
        L_0x000d:
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            java.util.Iterator r1 = r5.iterator()
        L_0x0016:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x003d
            java.lang.Object r2 = r1.next()
            org.spongycastle.asn1.ASN1Sequence r2 = (org.spongycastle.asn1.ASN1Sequence) r2
            boolean r3 = withinDNSubtree(r6, r2)
            if (r3 == 0) goto L_0x002c
            r0.add(r2)
            goto L_0x0016
        L_0x002c:
            boolean r3 = withinDNSubtree(r2, r6)
            if (r3 == 0) goto L_0x0036
            r0.add(r6)
            goto L_0x0016
        L_0x0036:
            r0.add(r2)
            r0.add(r6)
            goto L_0x0016
        L_0x003d:
            r5 = r0
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.unionDN(java.util.Set, org.spongycastle.asn1.ASN1Sequence):java.util.Set");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.String>, for r7v0, types: [java.util.Set, java.util.Set<java.lang.String>] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree>, for r8v0, types: [java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree>, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set intersectEmail(java.util.Set<java.lang.String> r7, java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree> r8) {
        /*
            r6 = this;
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.Iterator r3 = r8.iterator()
        L_0x0009:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0039
            java.lang.Object r5 = r3.next()
            org.spongycastle.asn1.x509.GeneralSubtree r5 = (org.spongycastle.asn1.x509.GeneralSubtree) r5
            org.spongycastle.asn1.x509.GeneralName r5 = r5.getBase()
            java.lang.String r1 = r6.extractNameAsString(r5)
            if (r7 != 0) goto L_0x0025
            if (r1 == 0) goto L_0x0009
            r2.add(r1)
            goto L_0x0009
        L_0x0025:
            java.util.Iterator r4 = r7.iterator()
        L_0x0029:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0009
            java.lang.Object r0 = r4.next()
            java.lang.String r0 = (java.lang.String) r0
            r6.intersectEmail(r1, r0, r2)
            goto L_0x0029
        L_0x0039:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.intersectEmail(java.util.Set, java.util.Set):java.util.Set");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.String>, for r5v0, types: [java.util.Set, java.util.Set<java.lang.String>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set unionEmail(java.util.Set<java.lang.String> r5, java.lang.String r6) {
        /*
            r4 = this;
            boolean r3 = r5.isEmpty()
            if (r3 == 0) goto L_0x000d
            if (r6 != 0) goto L_0x0009
        L_0x0008:
            return r5
        L_0x0009:
            r5.add(r6)
            goto L_0x0008
        L_0x000d:
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.Iterator r1 = r5.iterator()
        L_0x0016:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0026
            java.lang.Object r0 = r1.next()
            java.lang.String r0 = (java.lang.String) r0
            r4.unionEmail(r0, r6, r2)
            goto L_0x0016
        L_0x0026:
            r5 = r2
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.unionEmail(java.util.Set, java.lang.String):java.util.Set");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<byte[]>, for r7v0, types: [java.util.Set<byte[]>, java.util.Set] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree>, for r8v0, types: [java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree>, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set intersectIP(java.util.Set<byte[]> r7, java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree> r8) {
        /*
            r6 = this;
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.util.Iterator r3 = r8.iterator()
        L_0x0009:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0048
            java.lang.Object r5 = r3.next()
            org.spongycastle.asn1.x509.GeneralSubtree r5 = (org.spongycastle.asn1.x509.GeneralSubtree) r5
            org.spongycastle.asn1.x509.GeneralName r5 = r5.getBase()
            org.spongycastle.asn1.ASN1Encodable r5 = r5.getName()
            org.spongycastle.asn1.ASN1OctetString r5 = org.spongycastle.asn1.ASN1OctetString.getInstance(r5)
            byte[] r2 = r5.getOctets()
            if (r7 != 0) goto L_0x002d
            if (r2 == 0) goto L_0x0009
            r1.add(r2)
            goto L_0x0009
        L_0x002d:
            java.util.Iterator r4 = r7.iterator()
        L_0x0031:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0009
            java.lang.Object r5 = r4.next()
            byte[] r5 = (byte[]) r5
            r0 = r5
            byte[] r0 = (byte[]) r0
            java.util.Set r5 = r6.intersectIPRange(r0, r2)
            r1.addAll(r5)
            goto L_0x0031
        L_0x0048:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.intersectIP(java.util.Set, java.util.Set):java.util.Set");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<byte[]>, for r5v0, types: [java.util.Set<byte[]>, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set unionIP(java.util.Set<byte[]> r5, byte[] r6) {
        /*
            r4 = this;
            boolean r3 = r5.isEmpty()
            if (r3 == 0) goto L_0x000d
            if (r6 != 0) goto L_0x0009
        L_0x0008:
            return r5
        L_0x0009:
            r5.add(r6)
            goto L_0x0008
        L_0x000d:
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.Iterator r1 = r5.iterator()
        L_0x0016:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x002d
            java.lang.Object r3 = r1.next()
            byte[] r3 = (byte[]) r3
            r0 = r3
            byte[] r0 = (byte[]) r0
            java.util.Set r3 = r4.unionIPRange(r0, r6)
            r2.addAll(r3)
            goto L_0x0016
        L_0x002d:
            r5 = r2
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.unionIP(java.util.Set, byte[]):java.util.Set");
    }

    private Set unionIPRange(byte[] ipWithSubmask1, byte[] ipWithSubmask2) {
        Set set = new HashSet();
        if (Arrays.areEqual(ipWithSubmask1, ipWithSubmask2)) {
            set.add(ipWithSubmask1);
        } else {
            set.add(ipWithSubmask1);
            set.add(ipWithSubmask2);
        }
        return set;
    }

    private Set intersectIPRange(byte[] ipWithSubmask1, byte[] ipWithSubmask2) {
        if (ipWithSubmask1.length != ipWithSubmask2.length) {
            return Collections.EMPTY_SET;
        }
        byte[][] temp = extractIPsAndSubnetMasks(ipWithSubmask1, ipWithSubmask2);
        byte[] ip1 = temp[0];
        byte[] subnetmask1 = temp[1];
        byte[] ip2 = temp[2];
        byte[] subnetmask2 = temp[3];
        byte[][] minMax = minMaxIPs(ip1, subnetmask1, ip2, subnetmask2);
        if (compareTo(max(minMax[0], minMax[2]), min(minMax[1], minMax[3])) == 1) {
            return Collections.EMPTY_SET;
        }
        return Collections.singleton(ipWithSubnetMask(m3957or(minMax[0], minMax[2]), m3957or(subnetmask1, subnetmask2)));
    }

    private byte[] ipWithSubnetMask(byte[] ip, byte[] subnetMask) {
        int ipLength = ip.length;
        byte[] temp = new byte[(ipLength * 2)];
        System.arraycopy(ip, 0, temp, 0, ipLength);
        System.arraycopy(subnetMask, 0, temp, ipLength, ipLength);
        return temp;
    }

    private byte[][] extractIPsAndSubnetMasks(byte[] ipWithSubmask1, byte[] ipWithSubmask2) {
        int ipLength = ipWithSubmask1.length / 2;
        byte[] ip1 = new byte[ipLength];
        byte[] subnetmask1 = new byte[ipLength];
        System.arraycopy(ipWithSubmask1, 0, ip1, 0, ipLength);
        System.arraycopy(ipWithSubmask1, ipLength, subnetmask1, 0, ipLength);
        byte[] ip2 = new byte[ipLength];
        byte[] subnetmask2 = new byte[ipLength];
        System.arraycopy(ipWithSubmask2, 0, ip2, 0, ipLength);
        System.arraycopy(ipWithSubmask2, ipLength, subnetmask2, 0, ipLength);
        return new byte[][]{ip1, subnetmask1, ip2, subnetmask2};
    }

    private byte[][] minMaxIPs(byte[] ip1, byte[] subnetmask1, byte[] ip2, byte[] subnetmask2) {
        int ipLength = ip1.length;
        byte[] min1 = new byte[ipLength];
        byte[] max1 = new byte[ipLength];
        byte[] min2 = new byte[ipLength];
        byte[] max2 = new byte[ipLength];
        for (int i = 0; i < ipLength; i++) {
            min1[i] = (byte) (ip1[i] & subnetmask1[i]);
            max1[i] = (byte) ((ip1[i] & subnetmask1[i]) | (subnetmask1[i] ^ -1));
            min2[i] = (byte) (ip2[i] & subnetmask2[i]);
            max2[i] = (byte) ((ip2[i] & subnetmask2[i]) | (subnetmask2[i] ^ -1));
        }
        return new byte[][]{min1, max1, min2, max2};
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.String>, for r5v0, types: [java.util.Set, java.util.Set<java.lang.String>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkPermittedEmail(java.util.Set<java.lang.String> r5, java.lang.String r6) throws org.spongycastle.asn1.x509.NameConstraintValidatorException {
        /*
            r4 = this;
            if (r5 != 0) goto L_0x0003
        L_0x0002:
            return
        L_0x0003:
            java.util.Iterator r0 = r5.iterator()
        L_0x0007:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x001a
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r4.emailIsConstrained(r6, r1)
            if (r2 == 0) goto L_0x0007
            goto L_0x0002
        L_0x001a:
            int r2 = r6.length()
            if (r2 != 0) goto L_0x0026
            int r2 = r5.size()
            if (r2 == 0) goto L_0x0002
        L_0x0026:
            org.spongycastle.asn1.x509.NameConstraintValidatorException r2 = new org.spongycastle.asn1.x509.NameConstraintValidatorException
            java.lang.String r3 = "Subject email address is not from a permitted subtree."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.checkPermittedEmail(java.util.Set, java.lang.String):void");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.String>, for r5v0, types: [java.util.Set, java.util.Set<java.lang.String>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkExcludedEmail(java.util.Set<java.lang.String> r5, java.lang.String r6) throws org.spongycastle.asn1.x509.NameConstraintValidatorException {
        /*
            r4 = this;
            boolean r2 = r5.isEmpty()
            if (r2 == 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            java.util.Iterator r0 = r5.iterator()
        L_0x000b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0006
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r4.emailIsConstrained(r6, r1)
            if (r2 == 0) goto L_0x000b
            org.spongycastle.asn1.x509.NameConstraintValidatorException r2 = new org.spongycastle.asn1.x509.NameConstraintValidatorException
            java.lang.String r3 = "Email address is from an excluded subtree."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.checkExcludedEmail(java.util.Set, java.lang.String):void");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<byte[]>, for r5v0, types: [java.util.Set<byte[]>, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkPermittedIP(java.util.Set<byte[]> r5, byte[] r6) throws org.spongycastle.asn1.x509.NameConstraintValidatorException {
        /*
            r4 = this;
            if (r5 != 0) goto L_0x0003
        L_0x0002:
            return
        L_0x0003:
            java.util.Iterator r1 = r5.iterator()
        L_0x0007:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x001d
            java.lang.Object r2 = r1.next()
            byte[] r2 = (byte[]) r2
            r0 = r2
            byte[] r0 = (byte[]) r0
            boolean r2 = r4.isIPConstrained(r6, r0)
            if (r2 == 0) goto L_0x0007
            goto L_0x0002
        L_0x001d:
            int r2 = r6.length
            if (r2 != 0) goto L_0x0026
            int r2 = r5.size()
            if (r2 == 0) goto L_0x0002
        L_0x0026:
            org.spongycastle.asn1.x509.NameConstraintValidatorException r2 = new org.spongycastle.asn1.x509.NameConstraintValidatorException
            java.lang.String r3 = "IP is not from a permitted subtree."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.checkPermittedIP(java.util.Set, byte[]):void");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<byte[]>, for r5v0, types: [java.util.Set<byte[]>, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkExcludedIP(java.util.Set<byte[]> r5, byte[] r6) throws org.spongycastle.asn1.x509.NameConstraintValidatorException {
        /*
            r4 = this;
            boolean r2 = r5.isEmpty()
            if (r2 == 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            java.util.Iterator r1 = r5.iterator()
        L_0x000b:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0006
            java.lang.Object r2 = r1.next()
            byte[] r2 = (byte[]) r2
            r0 = r2
            byte[] r0 = (byte[]) r0
            boolean r2 = r4.isIPConstrained(r6, r0)
            if (r2 == 0) goto L_0x000b
            org.spongycastle.asn1.x509.NameConstraintValidatorException r2 = new org.spongycastle.asn1.x509.NameConstraintValidatorException
            java.lang.String r3 = "IP is from an excluded subtree."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.checkExcludedIP(java.util.Set, byte[]):void");
    }

    private boolean isIPConstrained(byte[] ip, byte[] constraint) {
        int ipLength = ip.length;
        if (ipLength != constraint.length / 2) {
            return false;
        }
        byte[] subnetMask = new byte[ipLength];
        System.arraycopy(constraint, ipLength, subnetMask, 0, ipLength);
        byte[] permittedSubnetAddress = new byte[ipLength];
        byte[] ipSubnetAddress = new byte[ipLength];
        for (int i = 0; i < ipLength; i++) {
            permittedSubnetAddress[i] = (byte) (constraint[i] & subnetMask[i]);
            ipSubnetAddress[i] = (byte) (ip[i] & subnetMask[i]);
        }
        return Arrays.areEqual(permittedSubnetAddress, ipSubnetAddress);
    }

    private boolean emailIsConstrained(String email, String constraint) {
        String sub = email.substring(email.indexOf(64) + 1);
        if (constraint.indexOf(64) != -1) {
            if (email.equalsIgnoreCase(constraint)) {
                return true;
            }
        } else if (constraint.charAt(0) != '.') {
            if (sub.equalsIgnoreCase(constraint)) {
                return true;
            }
        } else if (withinDomain(sub, constraint)) {
            return true;
        }
        return false;
    }

    private boolean withinDomain(String testDomain, String domain) {
        String tempDomain = domain;
        if (tempDomain.startsWith(".")) {
            tempDomain = tempDomain.substring(1);
        }
        String[] domainParts = Strings.split(tempDomain, '.');
        String[] testDomainParts = Strings.split(testDomain, '.');
        if (testDomainParts.length <= domainParts.length) {
            return false;
        }
        int d = testDomainParts.length - domainParts.length;
        for (int i = -1; i < domainParts.length; i++) {
            if (i == -1) {
                if (testDomainParts[i + d].equals("")) {
                    return false;
                }
            } else if (!domainParts[i].equalsIgnoreCase(testDomainParts[i + d])) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.String>, for r5v0, types: [java.util.Set, java.util.Set<java.lang.String>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkPermittedDNS(java.util.Set<java.lang.String> r5, java.lang.String r6) throws org.spongycastle.asn1.x509.NameConstraintValidatorException {
        /*
            r4 = this;
            if (r5 != 0) goto L_0x0003
        L_0x0002:
            return
        L_0x0003:
            java.util.Iterator r0 = r5.iterator()
        L_0x0007:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0020
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r4.withinDomain(r6, r1)
            if (r2 != 0) goto L_0x0002
            boolean r2 = r6.equalsIgnoreCase(r1)
            if (r2 == 0) goto L_0x0007
            goto L_0x0002
        L_0x0020:
            int r2 = r6.length()
            if (r2 != 0) goto L_0x002c
            int r2 = r5.size()
            if (r2 == 0) goto L_0x0002
        L_0x002c:
            org.spongycastle.asn1.x509.NameConstraintValidatorException r2 = new org.spongycastle.asn1.x509.NameConstraintValidatorException
            java.lang.String r3 = "DNS is not from a permitted subtree."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.checkPermittedDNS(java.util.Set, java.lang.String):void");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.String>, for r5v0, types: [java.util.Set, java.util.Set<java.lang.String>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkExcludedDNS(java.util.Set<java.lang.String> r5, java.lang.String r6) throws org.spongycastle.asn1.x509.NameConstraintValidatorException {
        /*
            r4 = this;
            boolean r2 = r5.isEmpty()
            if (r2 == 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            java.util.Iterator r0 = r5.iterator()
        L_0x000b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0006
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r4.withinDomain(r6, r1)
            if (r2 != 0) goto L_0x0023
            boolean r2 = r6.equalsIgnoreCase(r1)
            if (r2 == 0) goto L_0x000b
        L_0x0023:
            org.spongycastle.asn1.x509.NameConstraintValidatorException r2 = new org.spongycastle.asn1.x509.NameConstraintValidatorException
            java.lang.String r3 = "DNS is from an excluded subtree."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.checkExcludedDNS(java.util.Set, java.lang.String):void");
    }

    private void unionEmail(String email1, String email2, Set union) {
        if (email1.indexOf(64) != -1) {
            String _sub = email1.substring(email1.indexOf(64) + 1);
            if (email2.indexOf(64) != -1) {
                if (email1.equalsIgnoreCase(email2)) {
                    union.add(email1);
                    return;
                }
                union.add(email1);
                union.add(email2);
            } else if (email2.startsWith(".")) {
                if (withinDomain(_sub, email2)) {
                    union.add(email2);
                    return;
                }
                union.add(email1);
                union.add(email2);
            } else if (_sub.equalsIgnoreCase(email2)) {
                union.add(email2);
            } else {
                union.add(email1);
                union.add(email2);
            }
        } else if (email1.startsWith(".")) {
            if (email2.indexOf(64) != -1) {
                if (withinDomain(email2.substring(email1.indexOf(64) + 1), email1)) {
                    union.add(email1);
                    return;
                }
                union.add(email1);
                union.add(email2);
            } else if (email2.startsWith(".")) {
                if (withinDomain(email1, email2) || email1.equalsIgnoreCase(email2)) {
                    union.add(email2);
                } else if (withinDomain(email2, email1)) {
                    union.add(email1);
                } else {
                    union.add(email1);
                    union.add(email2);
                }
            } else if (withinDomain(email2, email1)) {
                union.add(email1);
            } else {
                union.add(email1);
                union.add(email2);
            }
        } else if (email2.indexOf(64) != -1) {
            if (email2.substring(email1.indexOf(64) + 1).equalsIgnoreCase(email1)) {
                union.add(email1);
                return;
            }
            union.add(email1);
            union.add(email2);
        } else if (email2.startsWith(".")) {
            if (withinDomain(email1, email2)) {
                union.add(email2);
                return;
            }
            union.add(email1);
            union.add(email2);
        } else if (email1.equalsIgnoreCase(email2)) {
            union.add(email1);
        } else {
            union.add(email1);
            union.add(email2);
        }
    }

    private void unionURI(String email1, String email2, Set union) {
        if (email1.indexOf(64) != -1) {
            String _sub = email1.substring(email1.indexOf(64) + 1);
            if (email2.indexOf(64) != -1) {
                if (email1.equalsIgnoreCase(email2)) {
                    union.add(email1);
                    return;
                }
                union.add(email1);
                union.add(email2);
            } else if (email2.startsWith(".")) {
                if (withinDomain(_sub, email2)) {
                    union.add(email2);
                    return;
                }
                union.add(email1);
                union.add(email2);
            } else if (_sub.equalsIgnoreCase(email2)) {
                union.add(email2);
            } else {
                union.add(email1);
                union.add(email2);
            }
        } else if (email1.startsWith(".")) {
            if (email2.indexOf(64) != -1) {
                if (withinDomain(email2.substring(email1.indexOf(64) + 1), email1)) {
                    union.add(email1);
                    return;
                }
                union.add(email1);
                union.add(email2);
            } else if (email2.startsWith(".")) {
                if (withinDomain(email1, email2) || email1.equalsIgnoreCase(email2)) {
                    union.add(email2);
                } else if (withinDomain(email2, email1)) {
                    union.add(email1);
                } else {
                    union.add(email1);
                    union.add(email2);
                }
            } else if (withinDomain(email2, email1)) {
                union.add(email1);
            } else {
                union.add(email1);
                union.add(email2);
            }
        } else if (email2.indexOf(64) != -1) {
            if (email2.substring(email1.indexOf(64) + 1).equalsIgnoreCase(email1)) {
                union.add(email1);
                return;
            }
            union.add(email1);
            union.add(email2);
        } else if (email2.startsWith(".")) {
            if (withinDomain(email1, email2)) {
                union.add(email2);
                return;
            }
            union.add(email1);
            union.add(email2);
        } else if (email1.equalsIgnoreCase(email2)) {
            union.add(email1);
        } else {
            union.add(email1);
            union.add(email2);
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.String>, for r7v0, types: [java.util.Set, java.util.Set<java.lang.String>] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree>, for r8v0, types: [java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree>, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set intersectDNS(java.util.Set<java.lang.String> r7, java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree> r8) {
        /*
            r6 = this;
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.Iterator r4 = r8.iterator()
        L_0x0009:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0049
            java.lang.Object r5 = r4.next()
            org.spongycastle.asn1.x509.GeneralSubtree r5 = (org.spongycastle.asn1.x509.GeneralSubtree) r5
            org.spongycastle.asn1.x509.GeneralName r5 = r5.getBase()
            java.lang.String r2 = r6.extractNameAsString(r5)
            if (r7 != 0) goto L_0x0025
            if (r2 == 0) goto L_0x0009
            r3.add(r2)
            goto L_0x0009
        L_0x0025:
            java.util.Iterator r0 = r7.iterator()
        L_0x0029:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x0009
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r5 = r6.withinDomain(r1, r2)
            if (r5 == 0) goto L_0x003f
            r3.add(r1)
            goto L_0x0029
        L_0x003f:
            boolean r5 = r6.withinDomain(r2, r1)
            if (r5 == 0) goto L_0x0029
            r3.add(r2)
            goto L_0x0029
        L_0x0049:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.intersectDNS(java.util.Set, java.util.Set):java.util.Set");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.String>, for r5v0, types: [java.util.Set, java.util.Set<java.lang.String>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set unionDNS(java.util.Set<java.lang.String> r5, java.lang.String r6) {
        /*
            r4 = this;
            boolean r3 = r5.isEmpty()
            if (r3 == 0) goto L_0x000d
            if (r6 != 0) goto L_0x0009
        L_0x0008:
            return r5
        L_0x0009:
            r5.add(r6)
            goto L_0x0008
        L_0x000d:
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.Iterator r0 = r5.iterator()
        L_0x0016:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x003d
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r3 = r4.withinDomain(r1, r6)
            if (r3 == 0) goto L_0x002c
            r2.add(r6)
            goto L_0x0016
        L_0x002c:
            boolean r3 = r4.withinDomain(r6, r1)
            if (r3 == 0) goto L_0x0036
            r2.add(r1)
            goto L_0x0016
        L_0x0036:
            r2.add(r1)
            r2.add(r6)
            goto L_0x0016
        L_0x003d:
            r5 = r2
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.unionDNS(java.util.Set, java.lang.String):java.util.Set");
    }

    private void intersectEmail(String email1, String email2, Set intersect) {
        if (email1.indexOf(64) != -1) {
            String _sub = email1.substring(email1.indexOf(64) + 1);
            if (email2.indexOf(64) != -1) {
                if (email1.equalsIgnoreCase(email2)) {
                    intersect.add(email1);
                }
            } else if (email2.startsWith(".")) {
                if (withinDomain(_sub, email2)) {
                    intersect.add(email1);
                }
            } else if (_sub.equalsIgnoreCase(email2)) {
                intersect.add(email1);
            }
        } else if (email1.startsWith(".")) {
            if (email2.indexOf(64) != -1) {
                if (withinDomain(email2.substring(email1.indexOf(64) + 1), email1)) {
                    intersect.add(email2);
                }
            } else if (email2.startsWith(".")) {
                if (withinDomain(email1, email2) || email1.equalsIgnoreCase(email2)) {
                    intersect.add(email1);
                } else if (withinDomain(email2, email1)) {
                    intersect.add(email2);
                }
            } else if (withinDomain(email2, email1)) {
                intersect.add(email2);
            }
        } else if (email2.indexOf(64) != -1) {
            if (email2.substring(email2.indexOf(64) + 1).equalsIgnoreCase(email1)) {
                intersect.add(email2);
            }
        } else if (email2.startsWith(".")) {
            if (withinDomain(email1, email2)) {
                intersect.add(email1);
            }
        } else if (email1.equalsIgnoreCase(email2)) {
            intersect.add(email1);
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.String>, for r5v0, types: [java.util.Set, java.util.Set<java.lang.String>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkExcludedURI(java.util.Set<java.lang.String> r5, java.lang.String r6) throws org.spongycastle.asn1.x509.NameConstraintValidatorException {
        /*
            r4 = this;
            boolean r2 = r5.isEmpty()
            if (r2 == 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            java.util.Iterator r0 = r5.iterator()
        L_0x000b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0006
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r4.isUriConstrained(r6, r1)
            if (r2 == 0) goto L_0x000b
            org.spongycastle.asn1.x509.NameConstraintValidatorException r2 = new org.spongycastle.asn1.x509.NameConstraintValidatorException
            java.lang.String r3 = "URI is from an excluded subtree."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.checkExcludedURI(java.util.Set, java.lang.String):void");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.String>, for r7v0, types: [java.util.Set, java.util.Set<java.lang.String>] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree>, for r8v0, types: [java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree>, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set intersectURI(java.util.Set<java.lang.String> r7, java.util.Set<org.spongycastle.asn1.x509.GeneralSubtree> r8) {
        /*
            r6 = this;
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.Iterator r3 = r8.iterator()
        L_0x0009:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0039
            java.lang.Object r5 = r3.next()
            org.spongycastle.asn1.x509.GeneralSubtree r5 = (org.spongycastle.asn1.x509.GeneralSubtree) r5
            org.spongycastle.asn1.x509.GeneralName r5 = r5.getBase()
            java.lang.String r4 = r6.extractNameAsString(r5)
            if (r7 != 0) goto L_0x0025
            if (r4 == 0) goto L_0x0009
            r2.add(r4)
            goto L_0x0009
        L_0x0025:
            java.util.Iterator r0 = r7.iterator()
        L_0x0029:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x0009
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            r6.intersectURI(r1, r4, r2)
            goto L_0x0029
        L_0x0039:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.intersectURI(java.util.Set, java.util.Set):java.util.Set");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.String>, for r5v0, types: [java.util.Set, java.util.Set<java.lang.String>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Set unionURI(java.util.Set<java.lang.String> r5, java.lang.String r6) {
        /*
            r4 = this;
            boolean r3 = r5.isEmpty()
            if (r3 == 0) goto L_0x000d
            if (r6 != 0) goto L_0x0009
        L_0x0008:
            return r5
        L_0x0009:
            r5.add(r6)
            goto L_0x0008
        L_0x000d:
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.Iterator r1 = r5.iterator()
        L_0x0016:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0026
            java.lang.Object r0 = r1.next()
            java.lang.String r0 = (java.lang.String) r0
            r4.unionURI(r0, r6, r2)
            goto L_0x0016
        L_0x0026:
            r5 = r2
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.unionURI(java.util.Set, java.lang.String):java.util.Set");
    }

    private void intersectURI(String email1, String email2, Set intersect) {
        if (email1.indexOf(64) != -1) {
            String _sub = email1.substring(email1.indexOf(64) + 1);
            if (email2.indexOf(64) != -1) {
                if (email1.equalsIgnoreCase(email2)) {
                    intersect.add(email1);
                }
            } else if (email2.startsWith(".")) {
                if (withinDomain(_sub, email2)) {
                    intersect.add(email1);
                }
            } else if (_sub.equalsIgnoreCase(email2)) {
                intersect.add(email1);
            }
        } else if (email1.startsWith(".")) {
            if (email2.indexOf(64) != -1) {
                if (withinDomain(email2.substring(email1.indexOf(64) + 1), email1)) {
                    intersect.add(email2);
                }
            } else if (email2.startsWith(".")) {
                if (withinDomain(email1, email2) || email1.equalsIgnoreCase(email2)) {
                    intersect.add(email1);
                } else if (withinDomain(email2, email1)) {
                    intersect.add(email2);
                }
            } else if (withinDomain(email2, email1)) {
                intersect.add(email2);
            }
        } else if (email2.indexOf(64) != -1) {
            if (email2.substring(email2.indexOf(64) + 1).equalsIgnoreCase(email1)) {
                intersect.add(email2);
            }
        } else if (email2.startsWith(".")) {
            if (withinDomain(email1, email2)) {
                intersect.add(email1);
            }
        } else if (email1.equalsIgnoreCase(email2)) {
            intersect.add(email1);
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.String>, for r5v0, types: [java.util.Set, java.util.Set<java.lang.String>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkPermittedURI(java.util.Set<java.lang.String> r5, java.lang.String r6) throws org.spongycastle.asn1.x509.NameConstraintValidatorException {
        /*
            r4 = this;
            if (r5 != 0) goto L_0x0003
        L_0x0002:
            return
        L_0x0003:
            java.util.Iterator r0 = r5.iterator()
        L_0x0007:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x001a
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r4.isUriConstrained(r6, r1)
            if (r2 == 0) goto L_0x0007
            goto L_0x0002
        L_0x001a:
            int r2 = r6.length()
            if (r2 != 0) goto L_0x0026
            int r2 = r5.size()
            if (r2 == 0) goto L_0x0002
        L_0x0026:
            org.spongycastle.asn1.x509.NameConstraintValidatorException r2 = new org.spongycastle.asn1.x509.NameConstraintValidatorException
            java.lang.String r3 = "URI is not from a permitted subtree."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.checkPermittedURI(java.util.Set, java.lang.String):void");
    }

    private boolean isUriConstrained(String uri, String constraint) {
        String host = extractHostFromURL(uri);
        if (!constraint.startsWith(".")) {
            if (host.equalsIgnoreCase(constraint)) {
                return true;
            }
        } else if (withinDomain(host, constraint)) {
            return true;
        }
        return false;
    }

    private static String extractHostFromURL(String url) {
        String sub = url.substring(url.indexOf(58) + 1);
        if (sub.indexOf("//") != -1) {
            sub = sub.substring(sub.indexOf("//") + 2);
        }
        if (sub.lastIndexOf(58) != -1) {
            sub = sub.substring(0, sub.lastIndexOf(58));
        }
        String sub2 = sub.substring(sub.indexOf(58) + 1);
        String sub3 = sub2.substring(sub2.indexOf(64) + 1);
        if (sub3.indexOf(47) != -1) {
            return sub3.substring(0, sub3.indexOf(47));
        }
        return sub3;
    }

    private String extractNameAsString(GeneralName name) {
        return DERIA5String.getInstance(name.getName()).getString();
    }

    private static byte[] max(byte[] ip1, byte[] ip2) {
        for (int i = 0; i < ip1.length; i++) {
            if ((ip1[i] & 65535) > (ip2[i] & 65535)) {
                return ip1;
            }
        }
        return ip2;
    }

    private static byte[] min(byte[] ip1, byte[] ip2) {
        for (int i = 0; i < ip1.length; i++) {
            if ((ip1[i] & 65535) < (ip2[i] & 65535)) {
                return ip1;
            }
        }
        return ip2;
    }

    private static int compareTo(byte[] ip1, byte[] ip2) {
        if (Arrays.areEqual(ip1, ip2)) {
            return 0;
        }
        if (Arrays.areEqual(max(ip1, ip2), ip1)) {
            return 1;
        }
        return -1;
    }

    /* renamed from: or */
    private static byte[] m3957or(byte[] ip1, byte[] ip2) {
        byte[] temp = new byte[ip1.length];
        for (int i = 0; i < ip1.length; i++) {
            temp[i] = (byte) (ip1[i] | ip2[i]);
        }
        return temp;
    }

    private int hashCollection(Collection coll) {
        if (coll == null) {
            return 0;
        }
        int hash = 0;
        for (Object o : coll) {
            if (o instanceof byte[]) {
                hash += Arrays.hashCode((byte[]) (byte[]) o);
            } else {
                hash += o.hashCode();
            }
        }
        return hash;
    }

    private boolean collectionsAreEqual(Collection coll1, Collection coll2) {
        if (coll1 == coll2) {
            return true;
        }
        if (coll1 == null || coll2 == null) {
            return false;
        }
        if (coll1.size() != coll2.size()) {
            return false;
        }
        for (Object a : coll1) {
            Iterator it2 = coll2.iterator();
            boolean found = false;
            while (true) {
                if (it2.hasNext()) {
                    if (equals(a, it2.next())) {
                        found = true;
                        continue;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    private boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        if (o1 == null || o2 == null) {
            return false;
        }
        if (!(o1 instanceof byte[]) || !(o2 instanceof byte[])) {
            return o1.equals(o2);
        }
        return Arrays.areEqual((byte[]) (byte[]) o1, (byte[]) (byte[]) o2);
    }

    private String stringifyIP(byte[] ip) {
        String temp = "";
        for (int i = 0; i < ip.length / 2; i++) {
            temp = temp + Integer.toString(ip[i] & 255) + ".";
        }
        String temp2 = temp.substring(0, temp.length() - 1) + "/";
        for (int i2 = ip.length / 2; i2 < ip.length; i2++) {
            temp2 = temp2 + Integer.toString(ip[i2] & 255) + ".";
        }
        return temp2.substring(0, temp2.length() - 1);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<byte[]>, for r5v0, types: [java.util.Set<byte[]>, java.util.Set] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String stringifyIPCollection(java.util.Set<byte[]> r5) {
        /*
            r4 = this;
            java.lang.String r1 = ""
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r2 = r2.append(r1)
            java.lang.String r3 = "["
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r1 = r2.toString()
            java.util.Iterator r0 = r5.iterator()
        L_0x001b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0046
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r3 = r2.append(r1)
            java.lang.Object r2 = r0.next()
            byte[] r2 = (byte[]) r2
            byte[] r2 = (byte[]) r2
            java.lang.String r2 = r4.stringifyIP(r2)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r3 = ","
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r1 = r2.toString()
            goto L_0x001b
        L_0x0046:
            int r2 = r1.length()
            r3 = 1
            if (r2 <= r3) goto L_0x0058
            r2 = 0
            int r3 = r1.length()
            int r3 = r3 + -1
            java.lang.String r1 = r1.substring(r2, r3)
        L_0x0058:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r2 = r2.append(r1)
            java.lang.String r3 = "]"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r1 = r2.toString()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.x509.PKIXNameConstraintValidator.stringifyIPCollection(java.util.Set):java.lang.String");
    }
}
