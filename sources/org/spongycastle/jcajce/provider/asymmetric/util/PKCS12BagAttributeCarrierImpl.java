package org.spongycastle.jcajce.provider.asymmetric.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OutputStream;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;

public class PKCS12BagAttributeCarrierImpl implements PKCS12BagAttributeCarrier {
    private Hashtable pkcs12Attributes;
    private Vector pkcs12Ordering;

    PKCS12BagAttributeCarrierImpl(Hashtable attributes, Vector ordering) {
        this.pkcs12Attributes = attributes;
        this.pkcs12Ordering = ordering;
    }

    public PKCS12BagAttributeCarrierImpl() {
        this(new Hashtable(), new Vector());
    }

    public void setBagAttribute(ASN1ObjectIdentifier oid, ASN1Encodable attribute) {
        if (this.pkcs12Attributes.containsKey(oid)) {
            this.pkcs12Attributes.put(oid, attribute);
            return;
        }
        this.pkcs12Attributes.put(oid, attribute);
        this.pkcs12Ordering.addElement(oid);
    }

    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier oid) {
        return (ASN1Encodable) this.pkcs12Attributes.get(oid);
    }

    public Enumeration getBagAttributeKeys() {
        return this.pkcs12Ordering.elements();
    }

    /* access modifiers changed from: 0000 */
    public int size() {
        return this.pkcs12Ordering.size();
    }

    /* access modifiers changed from: 0000 */
    public Hashtable getAttributes() {
        return this.pkcs12Attributes;
    }

    /* access modifiers changed from: 0000 */
    public Vector getOrdering() {
        return this.pkcs12Ordering;
    }

    public void writeObject(ObjectOutputStream out) throws IOException {
        if (this.pkcs12Ordering.size() == 0) {
            out.writeObject(new Hashtable());
            out.writeObject(new Vector());
            return;
        }
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ASN1OutputStream aOut = new ASN1OutputStream(bOut);
        Enumeration e = getBagAttributeKeys();
        while (e.hasMoreElements()) {
            ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier) e.nextElement();
            aOut.writeObject(oid);
            aOut.writeObject((ASN1Encodable) this.pkcs12Attributes.get(oid));
        }
        out.writeObject(bOut.toByteArray());
    }

    public void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        Object obj = in.readObject();
        if (obj instanceof Hashtable) {
            this.pkcs12Attributes = (Hashtable) obj;
            this.pkcs12Ordering = (Vector) in.readObject();
            return;
        }
        ASN1InputStream aIn = new ASN1InputStream((byte[]) (byte[]) obj);
        while (true) {
            ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier) aIn.readObject();
            if (oid != null) {
                setBagAttribute(oid, aIn.readObject());
            } else {
                return;
            }
        }
    }
}
