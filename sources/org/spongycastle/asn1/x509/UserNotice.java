package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class UserNotice extends ASN1Object {
    private DisplayText explicitText;
    private NoticeReference noticeRef;

    public UserNotice(NoticeReference noticeRef2, DisplayText explicitText2) {
        this.noticeRef = noticeRef2;
        this.explicitText = explicitText2;
    }

    public UserNotice(NoticeReference noticeRef2, String str) {
        this(noticeRef2, new DisplayText(str));
    }

    private UserNotice(ASN1Sequence as) {
        if (as.size() == 2) {
            this.noticeRef = NoticeReference.getInstance(as.getObjectAt(0));
            this.explicitText = DisplayText.getInstance(as.getObjectAt(1));
        } else if (as.size() != 1) {
            throw new IllegalArgumentException("Bad sequence size: " + as.size());
        } else if (as.getObjectAt(0).toASN1Primitive() instanceof ASN1Sequence) {
            this.noticeRef = NoticeReference.getInstance(as.getObjectAt(0));
        } else {
            this.explicitText = DisplayText.getInstance(as.getObjectAt(0));
        }
    }

    public static UserNotice getInstance(Object obj) {
        if (obj instanceof UserNotice) {
            return (UserNotice) obj;
        }
        if (obj != null) {
            return new UserNotice(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public NoticeReference getNoticeRef() {
        return this.noticeRef;
    }

    public DisplayText getExplicitText() {
        return this.explicitText;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector av = new ASN1EncodableVector();
        if (this.noticeRef != null) {
            av.add(this.noticeRef);
        }
        if (this.explicitText != null) {
            av.add(this.explicitText);
        }
        return new DERSequence(av);
    }
}
