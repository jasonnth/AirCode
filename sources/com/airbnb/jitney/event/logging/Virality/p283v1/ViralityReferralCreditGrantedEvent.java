package com.airbnb.jitney.event.logging.Virality.p283v1;

import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.jitney.event.logging.ReferralCreditType.p219v1.C2600ReferralCreditType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Virality.v1.ViralityReferralCreditGrantedEvent */
public final class ViralityReferralCreditGrantedEvent implements Struct {
    public static final Adapter<ViralityReferralCreditGrantedEvent, Object> ADAPTER = new ViralityReferralCreditGrantedEventAdapter();
    public final Context context;
    public final String currency;
    public final String event_name;
    public final Long referral_credit_amount_micros;
    public final Long referral_credit_id;
    public final C2600ReferralCreditType referral_credit_type;
    public final Long referral_id;
    public final Long referring_user_id;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Virality.v1.ViralityReferralCreditGrantedEvent$ViralityReferralCreditGrantedEventAdapter */
    private static final class ViralityReferralCreditGrantedEventAdapter implements Adapter<ViralityReferralCreditGrantedEvent, Object> {
        private ViralityReferralCreditGrantedEventAdapter() {
        }

        public void write(Protocol protocol, ViralityReferralCreditGrantedEvent struct) throws IOException {
            protocol.writeStructBegin("ViralityReferralCreditGrantedEvent");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("event_name", 1, PassportService.SF_DG11);
            protocol.writeString(struct.event_name);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(PlaceFields.CONTEXT, 2, PassportService.SF_DG12);
            Context.ADAPTER.write(protocol, struct.context);
            protocol.writeFieldEnd();
            if (struct.referring_user_id != null) {
                protocol.writeFieldBegin("referring_user_id", 3, 10);
                protocol.writeI64(struct.referring_user_id.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("referral_id", 4, 10);
            protocol.writeI64(struct.referral_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("referral_credit_id", 5, 10);
            protocol.writeI64(struct.referral_credit_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("referral_credit_type", 6, 8);
            protocol.writeI32(struct.referral_credit_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("referral_credit_amount_micros", 7, 10);
            protocol.writeI64(struct.referral_credit_amount_micros.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(AirbnbConstants.PREFS_CURRENCY, 8, PassportService.SF_DG11);
            protocol.writeString(struct.currency);
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
        if (!(other instanceof ViralityReferralCreditGrantedEvent)) {
            return false;
        }
        ViralityReferralCreditGrantedEvent that = (ViralityReferralCreditGrantedEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.referring_user_id == that.referring_user_id || (this.referring_user_id != null && this.referring_user_id.equals(that.referring_user_id))) && ((this.referral_id == that.referral_id || this.referral_id.equals(that.referral_id)) && ((this.referral_credit_id == that.referral_credit_id || this.referral_credit_id.equals(that.referral_credit_id)) && ((this.referral_credit_type == that.referral_credit_type || this.referral_credit_type.equals(that.referral_credit_type)) && ((this.referral_credit_amount_micros == that.referral_credit_amount_micros || this.referral_credit_amount_micros.equals(that.referral_credit_amount_micros)) && (this.currency == that.currency || this.currency.equals(that.currency)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035;
        if (this.referring_user_id != null) {
            i = this.referring_user_id.hashCode();
        }
        return (((((((((((code ^ i) * -2128831035) ^ this.referral_id.hashCode()) * -2128831035) ^ this.referral_credit_id.hashCode()) * -2128831035) ^ this.referral_credit_type.hashCode()) * -2128831035) ^ this.referral_credit_amount_micros.hashCode()) * -2128831035) ^ this.currency.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ViralityReferralCreditGrantedEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", referring_user_id=" + this.referring_user_id + ", referral_id=" + this.referral_id + ", referral_credit_id=" + this.referral_credit_id + ", referral_credit_type=" + this.referral_credit_type + ", referral_credit_amount_micros=" + this.referral_credit_amount_micros + ", currency=" + this.currency + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
