package com.airbnb.jitney.event.logging.Virality.p284v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.OperationResult.p166v1.C2452OperationResult;
import com.airbnb.jitney.event.logging.ShareModule.p252v1.C2735ShareModule;
import com.airbnb.jitney.event.logging.ViralityEntryPoint.p286v1.C2803ViralityEntryPoint;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Virality.v2.ViralityReferralActionEvent */
public final class ViralityReferralActionEvent implements Struct {
    public static final Adapter<ViralityReferralActionEvent, Object> ADAPTER = new ViralityReferralActionEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final C2452OperationResult operation_result;
    public final List<String> recipients;
    public final String schema;
    public final C2735ShareModule share_module;
    public final Long share_service;
    public final String share_service_freeform;
    public final String target;
    public final Long total_invites_sent;
    public final C2803ViralityEntryPoint virality_entry_point;

    /* renamed from: com.airbnb.jitney.event.logging.Virality.v2.ViralityReferralActionEvent$ViralityReferralActionEventAdapter */
    private static final class ViralityReferralActionEventAdapter implements Adapter<ViralityReferralActionEvent, Object> {
        private ViralityReferralActionEventAdapter() {
        }

        public void write(Protocol protocol, ViralityReferralActionEvent struct) throws IOException {
            protocol.writeStructBegin("ViralityReferralActionEvent");
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
            protocol.writeFieldBegin("share_service", 3, 10);
            protocol.writeI64(struct.share_service.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("share_service_freeform", 4, PassportService.SF_DG11);
            protocol.writeString(struct.share_service_freeform);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("virality_entry_point", 5, 8);
            protocol.writeI32(struct.virality_entry_point.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 6, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 7, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("operation_result", 8, 8);
            protocol.writeI32(struct.operation_result.value);
            protocol.writeFieldEnd();
            if (struct.total_invites_sent != null) {
                protocol.writeFieldBegin("total_invites_sent", 9, 10);
                protocol.writeI64(struct.total_invites_sent.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.recipients != null) {
                protocol.writeFieldBegin("recipients", 10, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.recipients.size());
                for (String item0 : struct.recipients) {
                    protocol.writeString(item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("share_module", 11, 8);
            protocol.writeI32(struct.share_module.value);
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
        if (!(other instanceof ViralityReferralActionEvent)) {
            return false;
        }
        ViralityReferralActionEvent that = (ViralityReferralActionEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.share_service == that.share_service || this.share_service.equals(that.share_service)) && ((this.share_service_freeform == that.share_service_freeform || this.share_service_freeform.equals(that.share_service_freeform)) && ((this.virality_entry_point == that.virality_entry_point || this.virality_entry_point.equals(that.virality_entry_point)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation_result == that.operation_result || this.operation_result.equals(that.operation_result)) && ((this.total_invites_sent == that.total_invites_sent || (this.total_invites_sent != null && this.total_invites_sent.equals(that.total_invites_sent))) && ((this.recipients == that.recipients || (this.recipients != null && this.recipients.equals(that.recipients))) && (this.share_module == that.share_module || this.share_module.equals(that.share_module))))))))))))) {
            return true;
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
        int code = (((((((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.share_service.hashCode()) * -2128831035) ^ this.share_service_freeform.hashCode()) * -2128831035) ^ this.virality_entry_point.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation_result.hashCode()) * -2128831035) ^ (this.total_invites_sent == null ? 0 : this.total_invites_sent.hashCode())) * -2128831035;
        if (this.recipients != null) {
            i = this.recipients.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.share_module.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ViralityReferralActionEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", share_service=" + this.share_service + ", share_service_freeform=" + this.share_service_freeform + ", virality_entry_point=" + this.virality_entry_point + ", operation=" + this.operation + ", target=" + this.target + ", operation_result=" + this.operation_result + ", total_invites_sent=" + this.total_invites_sent + ", recipients=" + this.recipients + ", share_module=" + this.share_module + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
