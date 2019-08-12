package com.airbnb.jitney.event.logging.ShareRecipient.p253v1;

import com.airbnb.jitney.event.logging.ShareRecipientType.p254v1.C2738ShareRecipientType;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ShareRecipient.v1.ShareRecipient */
public final class C2736ShareRecipient implements Struct {
    public static final Adapter<C2736ShareRecipient, Object> ADAPTER = new ShareRecipientAdapter();
    public final String share_recipient_id;
    public final C2738ShareRecipientType share_recipient_type;

    /* renamed from: com.airbnb.jitney.event.logging.ShareRecipient.v1.ShareRecipient$ShareRecipientAdapter */
    private static final class ShareRecipientAdapter implements Adapter<C2736ShareRecipient, Object> {
        private ShareRecipientAdapter() {
        }

        public void write(Protocol protocol, C2736ShareRecipient struct) throws IOException {
            protocol.writeStructBegin("ShareRecipient");
            protocol.writeFieldBegin("share_recipient_type", 1, 8);
            protocol.writeI32(struct.share_recipient_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("share_recipient_id", 2, PassportService.SF_DG11);
            protocol.writeString(struct.share_recipient_id);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C2736ShareRecipient)) {
            return false;
        }
        C2736ShareRecipient that = (C2736ShareRecipient) other;
        if ((this.share_recipient_type == that.share_recipient_type || this.share_recipient_type.equals(that.share_recipient_type)) && (this.share_recipient_id == that.share_recipient_id || this.share_recipient_id.equals(that.share_recipient_id))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((16777619 ^ this.share_recipient_type.hashCode()) * -2128831035) ^ this.share_recipient_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ShareRecipient{share_recipient_type=" + this.share_recipient_type + ", share_recipient_id=" + this.share_recipient_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
