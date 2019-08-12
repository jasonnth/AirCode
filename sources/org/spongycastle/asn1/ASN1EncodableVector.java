package org.spongycastle.asn1;

import java.util.Enumeration;
import java.util.Vector;

public class ASN1EncodableVector {

    /* renamed from: v */
    private final Vector f6355v = new Vector();

    public void add(ASN1Encodable obj) {
        this.f6355v.addElement(obj);
    }

    public void addAll(ASN1EncodableVector other) {
        Enumeration en = other.f6355v.elements();
        while (en.hasMoreElements()) {
            this.f6355v.addElement(en.nextElement());
        }
    }

    public ASN1Encodable get(int i) {
        return (ASN1Encodable) this.f6355v.elementAt(i);
    }

    public int size() {
        return this.f6355v.size();
    }
}
