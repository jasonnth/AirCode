package org.spongycastle.asn1.dvcs;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Enumerated;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;

public class ServiceType extends ASN1Object {
    public static final ServiceType CCPD = new ServiceType(4);
    public static final ServiceType CPD = new ServiceType(1);
    public static final ServiceType VPKC = new ServiceType(3);
    public static final ServiceType VSD = new ServiceType(2);
    private ASN1Enumerated value;

    public ServiceType(int value2) {
        this.value = new ASN1Enumerated(value2);
    }

    private ServiceType(ASN1Enumerated value2) {
        this.value = value2;
    }

    public static ServiceType getInstance(Object obj) {
        if (obj instanceof ServiceType) {
            return (ServiceType) obj;
        }
        if (obj != null) {
            return new ServiceType(ASN1Enumerated.getInstance(obj));
        }
        return null;
    }

    public static ServiceType getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Enumerated.getInstance(obj, explicit));
    }

    public BigInteger getValue() {
        return this.value.getValue();
    }

    public ASN1Primitive toASN1Primitive() {
        return this.value;
    }

    public String toString() {
        int num = this.value.getValue().intValue();
        StringBuilder append = new StringBuilder().append("").append(num);
        String str = num == CPD.getValue().intValue() ? "(CPD)" : num == VSD.getValue().intValue() ? "(VSD)" : num == VPKC.getValue().intValue() ? "(VPKC)" : num == CCPD.getValue().intValue() ? "(CCPD)" : "?";
        return append.append(str).toString();
    }
}
