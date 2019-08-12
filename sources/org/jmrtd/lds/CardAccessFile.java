package org.jmrtd.lds;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.DLSet;

public class CardAccessFile {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long serialVersionUID = -3536507558193769951L;
    private Set<SecurityInfo> securityInfos;

    public CardAccessFile(Collection<SecurityInfo> collection) {
        if (collection == null) {
            throw new IllegalArgumentException("Null securityInfos");
        }
        this.securityInfos = new HashSet(collection);
    }

    public CardAccessFile(InputStream inputStream) throws IOException {
        readContent(inputStream);
    }

    /* access modifiers changed from: protected */
    public void readContent(InputStream inputStream) throws IOException {
        this.securityInfos = new HashSet();
        ASN1Set aSN1Set = (ASN1Set) new ASN1InputStream(inputStream).readObject();
        for (int i = 0; i < aSN1Set.size(); i++) {
            SecurityInfo instance = SecurityInfo.getInstance(aSN1Set.getObjectAt(i).toASN1Primitive());
            if (instance != null) {
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

    public Collection<SecurityInfo> getSecurityInfos() {
        return this.securityInfos;
    }

    public Collection<PACEInfo> getPACEInfos() {
        ArrayList arrayList = new ArrayList(this.securityInfos.size());
        for (SecurityInfo securityInfo : this.securityInfos) {
            if (securityInfo instanceof PACEInfo) {
                arrayList.add((PACEInfo) securityInfo);
            }
        }
        return arrayList;
    }

    public String toString() {
        return "CardAccessFile [" + this.securityInfos.toString() + "]";
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        CardAccessFile cardAccessFile = (CardAccessFile) obj;
        if (this.securityInfos == null) {
            if (cardAccessFile.securityInfos != null) {
                z = false;
            }
            return z;
        } else if (cardAccessFile.securityInfos != null) {
            return this.securityInfos.equals(cardAccessFile.securityInfos);
        } else {
            if (this.securityInfos != null) {
                z = false;
            }
            return z;
        }
    }

    public int hashCode() {
        return (this.securityInfos.hashCode() * 7) + 61;
    }
}
