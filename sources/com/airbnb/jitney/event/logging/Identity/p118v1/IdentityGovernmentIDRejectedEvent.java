package com.airbnb.jitney.event.logging.Identity.p118v1;

import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Identity.v1.IdentityGovernmentIDRejectedEvent */
public final class IdentityGovernmentIDRejectedEvent implements Struct {
    public static final Adapter<IdentityGovernmentIDRejectedEvent, Object> ADAPTER = new IdentityGovernmentIDRejectedEventAdapter();
    public final Context context;
    public final String event_name;
    public final String reject_reason;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Identity.v1.IdentityGovernmentIDRejectedEvent$IdentityGovernmentIDRejectedEventAdapter */
    private static final class IdentityGovernmentIDRejectedEventAdapter implements Adapter<IdentityGovernmentIDRejectedEvent, Object> {
        private IdentityGovernmentIDRejectedEventAdapter() {
        }

        public void write(Protocol protocol, IdentityGovernmentIDRejectedEvent struct) throws IOException {
            protocol.writeStructBegin("IdentityGovernmentIDRejectedEvent");
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
            protocol.writeFieldBegin("reject_reason", 3, PassportService.SF_DG11);
            protocol.writeString(struct.reject_reason);
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
        if (!(other instanceof IdentityGovernmentIDRejectedEvent)) {
            return false;
        }
        IdentityGovernmentIDRejectedEvent that = (IdentityGovernmentIDRejectedEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && (this.reject_reason == that.reject_reason || this.reject_reason.equals(that.reject_reason))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.reject_reason.hashCode()) * -2128831035;
    }

    public String toString() {
        return "IdentityGovernmentIDRejectedEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", reject_reason=" + this.reject_reason + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
