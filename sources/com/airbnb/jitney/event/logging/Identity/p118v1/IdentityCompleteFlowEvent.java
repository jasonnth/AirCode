package com.airbnb.jitney.event.logging.Identity.p118v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.IdentityFlowPagesType.p121v1.C2232IdentityFlowPagesType;
import com.airbnb.jitney.event.logging.IdentityFlowSource.p122v1.C2233IdentityFlowSource;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Identity.v1.IdentityCompleteFlowEvent */
public final class IdentityCompleteFlowEvent implements Struct {
    public static final Adapter<IdentityCompleteFlowEvent, Object> ADAPTER = new IdentityCompleteFlowEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final C2232IdentityFlowPagesType page;
    public final String schema;
    public final C2233IdentityFlowSource source;

    /* renamed from: com.airbnb.jitney.event.logging.Identity.v1.IdentityCompleteFlowEvent$IdentityCompleteFlowEventAdapter */
    private static final class IdentityCompleteFlowEventAdapter implements Adapter<IdentityCompleteFlowEvent, Object> {
        private IdentityCompleteFlowEventAdapter() {
        }

        public void write(Protocol protocol, IdentityCompleteFlowEvent struct) throws IOException {
            protocol.writeStructBegin("IdentityCompleteFlowEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 3, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("source", 4, 8);
            protocol.writeI32(struct.source.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("page", 5, 8);
            protocol.writeI32(struct.page.value);
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
        if (!(other instanceof IdentityCompleteFlowEvent)) {
            return false;
        }
        IdentityCompleteFlowEvent that = (IdentityCompleteFlowEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.source == that.source || this.source.equals(that.source)) && (this.page == that.page || this.page.equals(that.page))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.source.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035;
    }

    public String toString() {
        return "IdentityCompleteFlowEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", operation=" + this.operation + ", source=" + this.source + ", page=" + this.page + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}