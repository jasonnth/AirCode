package com.airbnb.jitney.event.logging.Cohosting.p059v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.CohostingInviteFlowPage.p064v1.C1954CohostingInviteFlowPage;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingImpressionInviteFlowEvent */
public final class CohostingImpressionInviteFlowEvent implements Struct {
    public static final Adapter<CohostingImpressionInviteFlowEvent, Builder> ADAPTER = new CohostingImpressionInviteFlowEventAdapter();
    public final Context context;
    public final String event_name;
    public final C1954CohostingInviteFlowPage invite_page;
    public final Long invitee_user_id;
    public final C2451Operation operation;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingImpressionInviteFlowEvent$Builder */
    public static final class Builder implements StructBuilder<CohostingImpressionInviteFlowEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C1954CohostingInviteFlowPage invite_page;
        /* access modifiers changed from: private */
        public Long invitee_user_id;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingImpressionInviteFlowEvent:1.0.0";
            this.event_name = "cohosting_impression_invite_flow";
            this.operation = C2451Operation.Impression;
        }

        public Builder(Context context2, C1954CohostingInviteFlowPage invite_page2, Long invitee_user_id2) {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingImpressionInviteFlowEvent:1.0.0";
            this.event_name = "cohosting_impression_invite_flow";
            this.context = context2;
            this.invite_page = invite_page2;
            this.operation = C2451Operation.Impression;
            this.invitee_user_id = invitee_user_id2;
        }

        public CohostingImpressionInviteFlowEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.invite_page == null) {
                throw new IllegalStateException("Required field 'invite_page' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.invitee_user_id != null) {
                return new CohostingImpressionInviteFlowEvent(this);
            } else {
                throw new IllegalStateException("Required field 'invitee_user_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingImpressionInviteFlowEvent$CohostingImpressionInviteFlowEventAdapter */
    private static final class CohostingImpressionInviteFlowEventAdapter implements Adapter<CohostingImpressionInviteFlowEvent, Builder> {
        private CohostingImpressionInviteFlowEventAdapter() {
        }

        public void write(Protocol protocol, CohostingImpressionInviteFlowEvent struct) throws IOException {
            protocol.writeStructBegin("CohostingImpressionInviteFlowEvent");
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
            protocol.writeFieldBegin("invite_page", 3, 8);
            protocol.writeI32(struct.invite_page.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("invitee_user_id", 5, 10);
            protocol.writeI64(struct.invitee_user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private CohostingImpressionInviteFlowEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.invite_page = builder.invite_page;
        this.operation = builder.operation;
        this.invitee_user_id = builder.invitee_user_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof CohostingImpressionInviteFlowEvent)) {
            return false;
        }
        CohostingImpressionInviteFlowEvent that = (CohostingImpressionInviteFlowEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.invite_page == that.invite_page || this.invite_page.equals(that.invite_page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.invitee_user_id == that.invitee_user_id || this.invitee_user_id.equals(that.invitee_user_id))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.invite_page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.invitee_user_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CohostingImpressionInviteFlowEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", invite_page=" + this.invite_page + ", operation=" + this.operation + ", invitee_user_id=" + this.invitee_user_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
