package com.airbnb.jitney.event.logging.Cohosting.p059v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.ReusableRemoveModalType.p230v1.C2637ReusableRemoveModalType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingImpressionReusableRemoveFlowEvent */
public final class CohostingImpressionReusableRemoveFlowEvent implements Struct {
    public static final Adapter<CohostingImpressionReusableRemoveFlowEvent, Object> ADAPTER = new CohostingImpressionReusableRemoveFlowEventAdapter();
    public final String action;
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final C2637ReusableRemoveModalType reusable_remove_modal;
    public final String schema;
    public final Long source_id;
    public final String source_type;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingImpressionReusableRemoveFlowEvent$CohostingImpressionReusableRemoveFlowEventAdapter */
    private static final class CohostingImpressionReusableRemoveFlowEventAdapter implements Adapter<CohostingImpressionReusableRemoveFlowEvent, Object> {
        private CohostingImpressionReusableRemoveFlowEventAdapter() {
        }

        public void write(Protocol protocol, CohostingImpressionReusableRemoveFlowEvent struct) throws IOException {
            protocol.writeStructBegin("CohostingImpressionReusableRemoveFlowEvent");
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
            protocol.writeFieldBegin("reusable_remove_modal", 3, 8);
            protocol.writeI32(struct.reusable_remove_modal.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("user_id", 5, 10);
            protocol.writeI64(struct.user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("source_id", 6, 10);
            protocol.writeI64(struct.source_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("source_type", 7, PassportService.SF_DG11);
            protocol.writeString(struct.source_type);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("action", 8, PassportService.SF_DG11);
            protocol.writeString(struct.action);
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
        if (!(other instanceof CohostingImpressionReusableRemoveFlowEvent)) {
            return false;
        }
        CohostingImpressionReusableRemoveFlowEvent that = (CohostingImpressionReusableRemoveFlowEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.reusable_remove_modal == that.reusable_remove_modal || this.reusable_remove_modal.equals(that.reusable_remove_modal)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && ((this.source_id == that.source_id || this.source_id.equals(that.source_id)) && ((this.source_type == that.source_type || this.source_type.equals(that.source_type)) && (this.action == that.action || this.action.equals(that.action)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.reusable_remove_modal.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035) ^ this.source_id.hashCode()) * -2128831035) ^ this.source_type.hashCode()) * -2128831035) ^ this.action.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CohostingImpressionReusableRemoveFlowEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", reusable_remove_modal=" + this.reusable_remove_modal + ", operation=" + this.operation + ", user_id=" + this.user_id + ", source_id=" + this.source_id + ", source_type=" + this.source_type + ", action=" + this.action + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
