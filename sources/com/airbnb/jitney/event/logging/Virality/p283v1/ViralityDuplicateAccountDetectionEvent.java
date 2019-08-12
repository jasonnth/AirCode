package com.airbnb.jitney.event.logging.Virality.p283v1;

import com.airbnb.jitney.event.logging.DetectionInformationType.p085v1.C1986DetectionInformationType;
import com.airbnb.jitney.event.logging.DuplicateDetectionPoint.p087v1.C1988DuplicateDetectionPoint;
import com.airbnb.jitney.event.logging.DuplicateType.p088v1.C1989DuplicateType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Virality.v1.ViralityDuplicateAccountDetectionEvent */
public final class ViralityDuplicateAccountDetectionEvent implements Struct {
    public static final Adapter<ViralityDuplicateAccountDetectionEvent, Object> ADAPTER = new ViralityDuplicateAccountDetectionEventAdapter();
    public final Context context;
    public final List<C1986DetectionInformationType> detection_information_types;
    public final C1988DuplicateDetectionPoint duplicate_detection_point;
    public final C1989DuplicateType duplicate_type;
    public final String event_name;
    public final Boolean is_referral;
    public final Long new_account_user_id;
    public final Long referral_id;
    public final Long referring_user_id;
    public final String schema;
    public final Long similar_user_id;

    /* renamed from: com.airbnb.jitney.event.logging.Virality.v1.ViralityDuplicateAccountDetectionEvent$ViralityDuplicateAccountDetectionEventAdapter */
    private static final class ViralityDuplicateAccountDetectionEventAdapter implements Adapter<ViralityDuplicateAccountDetectionEvent, Object> {
        private ViralityDuplicateAccountDetectionEventAdapter() {
        }

        public void write(Protocol protocol, ViralityDuplicateAccountDetectionEvent struct) throws IOException {
            protocol.writeStructBegin("ViralityDuplicateAccountDetectionEvent");
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
            protocol.writeFieldBegin("similar_user_id", 3, 10);
            protocol.writeI64(struct.similar_user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("detection_information_types", 4, 15);
            protocol.writeListBegin(16, struct.detection_information_types.size());
            for (C1986DetectionInformationType item0 : struct.detection_information_types) {
                protocol.writeI32(item0.value);
            }
            protocol.writeListEnd();
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("duplicate_detection_point", 5, 8);
            protocol.writeI32(struct.duplicate_detection_point.value);
            protocol.writeFieldEnd();
            if (struct.duplicate_type != null) {
                protocol.writeFieldBegin("duplicate_type", 6, 8);
                protocol.writeI32(struct.duplicate_type.value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("is_referral", 7, 2);
            protocol.writeBool(struct.is_referral.booleanValue());
            protocol.writeFieldEnd();
            if (struct.referral_id != null) {
                protocol.writeFieldBegin("referral_id", 8, 10);
                protocol.writeI64(struct.referral_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.referring_user_id != null) {
                protocol.writeFieldBegin("referring_user_id", 9, 10);
                protocol.writeI64(struct.referring_user_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.new_account_user_id != null) {
                protocol.writeFieldBegin("new_account_user_id", 10, 10);
                protocol.writeI64(struct.new_account_user_id.longValue());
                protocol.writeFieldEnd();
            }
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
        if (!(other instanceof ViralityDuplicateAccountDetectionEvent)) {
            return false;
        }
        ViralityDuplicateAccountDetectionEvent that = (ViralityDuplicateAccountDetectionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.similar_user_id == that.similar_user_id || this.similar_user_id.equals(that.similar_user_id)) && ((this.detection_information_types == that.detection_information_types || this.detection_information_types.equals(that.detection_information_types)) && ((this.duplicate_detection_point == that.duplicate_detection_point || this.duplicate_detection_point.equals(that.duplicate_detection_point)) && ((this.duplicate_type == that.duplicate_type || (this.duplicate_type != null && this.duplicate_type.equals(that.duplicate_type))) && ((this.is_referral == that.is_referral || this.is_referral.equals(that.is_referral)) && ((this.referral_id == that.referral_id || (this.referral_id != null && this.referral_id.equals(that.referral_id))) && (this.referring_user_id == that.referring_user_id || (this.referring_user_id != null && this.referring_user_id.equals(that.referring_user_id)))))))))))) {
            if (this.new_account_user_id == that.new_account_user_id) {
                return true;
            }
            if (this.new_account_user_id != null && this.new_account_user_id.equals(that.new_account_user_id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.schema == null) {
            hashCode = 0;
        } else {
            hashCode = this.schema.hashCode();
        }
        int code = (((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.similar_user_id.hashCode()) * -2128831035) ^ this.detection_information_types.hashCode()) * -2128831035) ^ this.duplicate_detection_point.hashCode()) * -2128831035) ^ (this.duplicate_type == null ? 0 : this.duplicate_type.hashCode())) * -2128831035) ^ this.is_referral.hashCode()) * -2128831035) ^ (this.referral_id == null ? 0 : this.referral_id.hashCode())) * -2128831035) ^ (this.referring_user_id == null ? 0 : this.referring_user_id.hashCode())) * -2128831035;
        if (this.new_account_user_id != null) {
            i = this.new_account_user_id.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "ViralityDuplicateAccountDetectionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", similar_user_id=" + this.similar_user_id + ", detection_information_types=" + this.detection_information_types + ", duplicate_detection_point=" + this.duplicate_detection_point + ", duplicate_type=" + this.duplicate_type + ", is_referral=" + this.is_referral + ", referral_id=" + this.referral_id + ", referring_user_id=" + this.referring_user_id + ", new_account_user_id=" + this.new_account_user_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
