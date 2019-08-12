package org.spongycastle.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.Target;
import org.spongycastle.asn1.x509.TargetInformation;
import org.spongycastle.asn1.x509.Targets;
import org.spongycastle.asn1.x509.X509Extensions;
import org.spongycastle.util.Selector;

public class X509AttributeCertStoreSelector implements Selector {
    private X509AttributeCertificate attributeCert;
    private Date attributeCertificateValid;
    private AttributeCertificateHolder holder;
    private AttributeCertificateIssuer issuer;
    private BigInteger serialNumber;
    private Collection targetGroups = new HashSet();
    private Collection targetNames = new HashSet();

    public boolean match(Object obj) {
        if (!(obj instanceof X509AttributeCertificate)) {
            return false;
        }
        X509AttributeCertificate attrCert = (X509AttributeCertificate) obj;
        if (this.attributeCert != null && !this.attributeCert.equals(attrCert)) {
            return false;
        }
        if (this.serialNumber != null && !attrCert.getSerialNumber().equals(this.serialNumber)) {
            return false;
        }
        if (this.holder != null && !attrCert.getHolder().equals(this.holder)) {
            return false;
        }
        if (this.issuer != null && !attrCert.getIssuer().equals(this.issuer)) {
            return false;
        }
        if (this.attributeCertificateValid != null) {
            try {
                attrCert.checkValidity(this.attributeCertificateValid);
            } catch (CertificateExpiredException e) {
                return false;
            } catch (CertificateNotYetValidException e2) {
                return false;
            }
        }
        if (!this.targetNames.isEmpty() || !this.targetGroups.isEmpty()) {
            byte[] targetInfoExt = attrCert.getExtensionValue(X509Extensions.TargetInformation.getId());
            if (targetInfoExt != null) {
                try {
                    Targets[] targetss = TargetInformation.getInstance(new ASN1InputStream(((DEROctetString) DEROctetString.fromByteArray(targetInfoExt)).getOctets()).readObject()).getTargetsObjects();
                    if (!this.targetNames.isEmpty()) {
                        boolean found = false;
                        for (Targets t : targetss) {
                            Target[] targets = t.getTargets();
                            int j = 0;
                            while (true) {
                                if (j >= targets.length) {
                                    break;
                                } else if (this.targetNames.contains(GeneralName.getInstance(targets[j].getTargetName()))) {
                                    found = true;
                                    break;
                                } else {
                                    j++;
                                }
                            }
                        }
                        if (!found) {
                            return false;
                        }
                    }
                    if (!this.targetGroups.isEmpty()) {
                        boolean found2 = false;
                        for (Targets t2 : targetss) {
                            Target[] targets2 = t2.getTargets();
                            int j2 = 0;
                            while (true) {
                                if (j2 >= targets2.length) {
                                    break;
                                } else if (this.targetGroups.contains(GeneralName.getInstance(targets2[j2].getTargetGroup()))) {
                                    found2 = true;
                                    break;
                                } else {
                                    j2++;
                                }
                            }
                        }
                        if (!found2) {
                            return false;
                        }
                    }
                } catch (IOException e3) {
                    return false;
                } catch (IllegalArgumentException e4) {
                    return false;
                }
            }
        }
        return true;
    }

    public Object clone() {
        X509AttributeCertStoreSelector sel = new X509AttributeCertStoreSelector();
        sel.attributeCert = this.attributeCert;
        sel.attributeCertificateValid = getAttributeCertificateValid();
        sel.holder = this.holder;
        sel.issuer = this.issuer;
        sel.serialNumber = this.serialNumber;
        sel.targetGroups = getTargetGroups();
        sel.targetNames = getTargetNames();
        return sel;
    }

    public X509AttributeCertificate getAttributeCert() {
        return this.attributeCert;
    }

    public void setAttributeCert(X509AttributeCertificate attributeCert2) {
        this.attributeCert = attributeCert2;
    }

    public Date getAttributeCertificateValid() {
        if (this.attributeCertificateValid != null) {
            return new Date(this.attributeCertificateValid.getTime());
        }
        return null;
    }

    public void setAttributeCertificateValid(Date attributeCertificateValid2) {
        if (attributeCertificateValid2 != null) {
            this.attributeCertificateValid = new Date(attributeCertificateValid2.getTime());
        } else {
            this.attributeCertificateValid = null;
        }
    }

    public AttributeCertificateHolder getHolder() {
        return this.holder;
    }

    public void setHolder(AttributeCertificateHolder holder2) {
        this.holder = holder2;
    }

    public AttributeCertificateIssuer getIssuer() {
        return this.issuer;
    }

    public void setIssuer(AttributeCertificateIssuer issuer2) {
        this.issuer = issuer2;
    }

    public BigInteger getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(BigInteger serialNumber2) {
        this.serialNumber = serialNumber2;
    }

    public void addTargetName(GeneralName name) {
        this.targetNames.add(name);
    }

    public void addTargetName(byte[] name) throws IOException {
        addTargetName(GeneralName.getInstance(ASN1Primitive.fromByteArray(name)));
    }

    public void setTargetNames(Collection names) throws IOException {
        this.targetNames = extractGeneralNames(names);
    }

    public Collection getTargetNames() {
        return Collections.unmodifiableCollection(this.targetNames);
    }

    public void addTargetGroup(GeneralName group) {
        this.targetGroups.add(group);
    }

    public void addTargetGroup(byte[] name) throws IOException {
        addTargetGroup(GeneralName.getInstance(ASN1Primitive.fromByteArray(name)));
    }

    public void setTargetGroups(Collection names) throws IOException {
        this.targetGroups = extractGeneralNames(names);
    }

    public Collection getTargetGroups() {
        return Collections.unmodifiableCollection(this.targetGroups);
    }

    private Set extractGeneralNames(Collection names) throws IOException {
        if (names == null || names.isEmpty()) {
            return new HashSet();
        }
        Set temp = new HashSet();
        for (Object o : names) {
            if (o instanceof GeneralName) {
                temp.add(o);
            } else {
                temp.add(GeneralName.getInstance(ASN1Primitive.fromByteArray((byte[]) o)));
            }
        }
        return temp;
    }
}
