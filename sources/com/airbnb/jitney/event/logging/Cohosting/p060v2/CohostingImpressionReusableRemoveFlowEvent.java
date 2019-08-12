package com.airbnb.jitney.event.logging.Cohosting.p060v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.ReusableRemoveModalType.p230v1.C2637ReusableRemoveModalType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v2.CohostingImpressionReusableRemoveFlowEvent */
public final class CohostingImpressionReusableRemoveFlowEvent implements Struct {
    public static final Adapter<CohostingImpressionReusableRemoveFlowEvent, Builder> ADAPTER = new CohostingImpressionReusableRemoveFlowEventAdapter();
    public final String action;
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final Long removed_cohost_id;
    public final C2637ReusableRemoveModalType reusable_remove_modal;
    public final String schema;
    public final Long source_id;
    public final String source_type;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v2.CohostingImpressionReusableRemoveFlowEvent$Builder */
    public static final class Builder implements StructBuilder<CohostingImpressionReusableRemoveFlowEvent> {
        /* access modifiers changed from: private */
        public String action;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public Long removed_cohost_id;
        /* access modifiers changed from: private */
        public C2637ReusableRemoveModalType reusable_remove_modal;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public Long source_id;
        /* access modifiers changed from: private */
        public String source_type;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingImpressionReusableRemoveFlowEvent:2.0.0";
            this.event_name = "cohosting_impression_reusable_remove_flow";
            this.operation = C2451Operation.Impression;
        }

        public Builder(Context context2, C2637ReusableRemoveModalType reusable_remove_modal2, Long source_id2, String source_type2, String action2) {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingImpressionReusableRemoveFlowEvent:2.0.0";
            this.event_name = "cohosting_impression_reusable_remove_flow";
            this.context = context2;
            this.reusable_remove_modal = reusable_remove_modal2;
            this.operation = C2451Operation.Impression;
            this.source_id = source_id2;
            this.source_type = source_type2;
            this.action = action2;
        }

        public Builder removed_cohost_id(Long removed_cohost_id2) {
            this.removed_cohost_id = removed_cohost_id2;
            return this;
        }

        public CohostingImpressionReusableRemoveFlowEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.reusable_remove_modal == null) {
                throw new IllegalStateException("Required field 'reusable_remove_modal' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.source_id == null) {
                throw new IllegalStateException("Required field 'source_id' is missing");
            } else if (this.source_type == null) {
                throw new IllegalStateException("Required field 'source_type' is missing");
            } else if (this.action != null) {
                return new CohostingImpressionReusableRemoveFlowEvent(this);
            } else {
                throw new IllegalStateException("Required field 'action' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v2.CohostingImpressionReusableRemoveFlowEvent$CohostingImpressionReusableRemoveFlowEventAdapter */
    private static final class CohostingImpressionReusableRemoveFlowEventAdapter implements Adapter<CohostingImpressionReusableRemoveFlowEvent, Builder> {
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
            protocol.writeFieldBegin("source_id", 5, 10);
            protocol.writeI64(struct.source_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("source_type", 6, PassportService.SF_DG11);
            protocol.writeString(struct.source_type);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("action", 7, PassportService.SF_DG11);
            protocol.writeString(struct.action);
            protocol.writeFieldEnd();
            if (struct.removed_cohost_id != null) {
                protocol.writeFieldBegin("removed_cohost_id", 8, 10);
                protocol.writeI64(struct.removed_cohost_id.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private CohostingImpressionReusableRemoveFlowEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.reusable_remove_modal = builder.reusable_remove_modal;
        this.operation = builder.operation;
        this.source_id = builder.source_id;
        this.source_type = builder.source_type;
        this.action = builder.action;
        this.removed_cohost_id = builder.removed_cohost_id;
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
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.reusable_remove_modal == that.reusable_remove_modal || this.reusable_remove_modal.equals(that.reusable_remove_modal)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.source_id == that.source_id || this.source_id.equals(that.source_id)) && ((this.source_type == that.source_type || this.source_type.equals(that.source_type)) && (this.action == that.action || this.action.equals(that.action))))))))) {
            if (this.removed_cohost_id == that.removed_cohost_id) {
                return true;
            }
            if (this.removed_cohost_id != null && this.removed_cohost_id.equals(that.removed_cohost_id)) {
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
        int code = (((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.reusable_remove_modal.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.source_id.hashCode()) * -2128831035) ^ this.source_type.hashCode()) * -2128831035) ^ this.action.hashCode()) * -2128831035;
        if (this.removed_cohost_id != null) {
            i = this.removed_cohost_id.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "CohostingImpressionReusableRemoveFlowEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", reusable_remove_modal=" + this.reusable_remove_modal + ", operation=" + this.operation + ", source_id=" + this.source_id + ", source_type=" + this.source_type + ", action=" + this.action + ", removed_cohost_id=" + this.removed_cohost_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
