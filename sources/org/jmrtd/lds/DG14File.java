package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.DLSet;

public class DG14File extends DataGroup {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = -3536507558193769953L;
    private Set<SecurityInfo> securityInfos;

    public DG14File(Collection<SecurityInfo> collection) {
        super(110);
        if (collection == null) {
            throw new IllegalArgumentException("Null securityInfos");
        }
        this.securityInfos = new HashSet(collection);
    }

    public DG14File(InputStream inputStream) throws IOException {
        super(110, inputStream);
    }

    /* access modifiers changed from: protected */
    public void readContent(InputStream inputStream) throws IOException {
        this.securityInfos = new HashSet();
        ASN1Set aSN1Set = (ASN1Set) new ASN1InputStream(inputStream).readObject();
        for (int i = 0; i < aSN1Set.size(); i++) {
            SecurityInfo instance = SecurityInfo.getInstance(aSN1Set.getObjectAt(i).toASN1Primitive());
            if (instance == null) {
                LOGGER.warning("Skipping this unsupported SecurityInfo");
            } else {
                this.securityInfos.add(instance);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void writeContent(OutputStream outputStream) throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (SecurityInfo dERObject : this.securityInfos) {
            aSN1EncodableVector.add(dERObject.getDERObject());
        }
        outputStream.write(new DLSet(aSN1EncodableVector).getEncoded(ASN1Encoding.DER));
    }

    public List<Short> getCVCAFileIds() {
        ArrayList arrayList = new ArrayList();
        for (SecurityInfo securityInfo : this.securityInfos) {
            if (securityInfo instanceof TerminalAuthenticationInfo) {
                int fileId = ((TerminalAuthenticationInfo) securityInfo).getFileId();
                if (fileId != -1) {
                    arrayList.add(Short.valueOf((short) fileId));
                }
            }
        }
        return arrayList;
    }

    public byte getCVCAShortFileId(int i) {
        for (SecurityInfo securityInfo : this.securityInfos) {
            if ((securityInfo instanceof TerminalAuthenticationInfo) && ((TerminalAuthenticationInfo) securityInfo).getFileId() == i) {
                return ((TerminalAuthenticationInfo) securityInfo).getShortFileId();
            }
        }
        return -1;
    }

    public Map<BigInteger, String> getChipAuthenticationInfos() {
        TreeMap treeMap = new TreeMap();
        for (SecurityInfo securityInfo : this.securityInfos) {
            if (securityInfo instanceof ChipAuthenticationInfo) {
                ChipAuthenticationInfo chipAuthenticationInfo = (ChipAuthenticationInfo) securityInfo;
                treeMap.put(chipAuthenticationInfo.getKeyId(), chipAuthenticationInfo.getObjectIdentifier());
                if (chipAuthenticationInfo.getKeyId().compareTo(BigInteger.ZERO) < 0) {
                    return treeMap;
                }
            }
        }
        return treeMap;
    }

    public List<ActiveAuthenticationInfo> getActiveAuthenticationInfos() {
        ArrayList arrayList = new ArrayList();
        for (SecurityInfo securityInfo : this.securityInfos) {
            if (securityInfo instanceof ActiveAuthenticationInfo) {
                arrayList.add((ActiveAuthenticationInfo) securityInfo);
            }
        }
        return arrayList;
    }

    public Map<BigInteger, PublicKey> getChipAuthenticationPublicKeyInfos() {
        boolean z;
        TreeMap treeMap = new TreeMap();
        boolean z2 = false;
        Iterator it = this.securityInfos.iterator();
        while (true) {
            z = z2;
            if (!it.hasNext()) {
                break;
            }
            SecurityInfo securityInfo = (SecurityInfo) it.next();
            if (securityInfo instanceof ChipAuthenticationPublicKeyInfo) {
                ChipAuthenticationPublicKeyInfo chipAuthenticationPublicKeyInfo = (ChipAuthenticationPublicKeyInfo) securityInfo;
                treeMap.put(chipAuthenticationPublicKeyInfo.getKeyId(), chipAuthenticationPublicKeyInfo.getSubjectPublicKey());
                z2 = true;
            } else {
                z2 = z;
            }
        }
        if (z) {
            return treeMap;
        }
        throw new IllegalStateException("No keys?");
    }

    public Collection<SecurityInfo> getSecurityInfos() {
        return this.securityInfos;
    }

    public String toString() {
        return "DG14File [" + this.securityInfos.toString() + "]";
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        DG14File dG14File = (DG14File) obj;
        if (this.securityInfos == null) {
            if (dG14File.securityInfos != null) {
                z = false;
            }
            return z;
        } else if (dG14File.securityInfos != null) {
            return this.securityInfos.equals(dG14File.securityInfos);
        } else {
            if (this.securityInfos != null) {
                z = false;
            }
            return z;
        }
    }

    public int hashCode() {
        return (this.securityInfos.hashCode() * 5) + 41;
    }
}
