package org.spongycastle.crypto.util;

import java.io.IOException;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class DEROtherInfo {
    private final DERSequence sequence;

    public static final class Builder {
        private final AlgorithmIdentifier algorithmID;
        private final ASN1OctetString partyUVInfo;
        private final ASN1OctetString partyVInfo;
        private ASN1TaggedObject suppPrivInfo;
        private ASN1TaggedObject suppPubInfo;

        public Builder(AlgorithmIdentifier algorithmID2, byte[] partyUInfo, byte[] partyVInfo2) {
            this.algorithmID = algorithmID2;
            this.partyUVInfo = DerUtil.getOctetString(partyUInfo);
            this.partyVInfo = DerUtil.getOctetString(partyVInfo2);
        }

        public Builder withSuppPubInfo(byte[] suppPubInfo2) {
            this.suppPubInfo = new DERTaggedObject(false, 0, DerUtil.getOctetString(suppPubInfo2));
            return this;
        }

        public Builder withSuppPrivInfo(byte[] suppPrivInfo2) {
            this.suppPrivInfo = new DERTaggedObject(false, 1, DerUtil.getOctetString(suppPrivInfo2));
            return this;
        }

        public DEROtherInfo build() {
            ASN1EncodableVector v = new ASN1EncodableVector();
            v.add(this.algorithmID);
            v.add(this.partyUVInfo);
            v.add(this.partyVInfo);
            if (this.suppPubInfo != null) {
                v.add(this.suppPubInfo);
            }
            if (this.suppPrivInfo != null) {
                v.add(this.suppPrivInfo);
            }
            return new DEROtherInfo(new DERSequence(v));
        }
    }

    private DEROtherInfo(DERSequence sequence2) {
        this.sequence = sequence2;
    }

    public byte[] getEncoded() throws IOException {
        return this.sequence.getEncoded();
    }
}
