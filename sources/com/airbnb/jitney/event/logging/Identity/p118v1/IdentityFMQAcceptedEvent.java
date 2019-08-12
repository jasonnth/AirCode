package com.airbnb.jitney.event.logging.Identity.p118v1;

import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Identity.v1.IdentityFMQAcceptedEvent */
public final class IdentityFMQAcceptedEvent implements Struct {
    public static final Adapter<IdentityFMQAcceptedEvent, Object> ADAPTER = new IdentityFMQAcceptedEventAdapter();
    public final Context context;
    public final String event_name;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Identity.v1.IdentityFMQAcceptedEvent$IdentityFMQAcceptedEventAdapter */
    private static final class IdentityFMQAcceptedEventAdapter implements Adapter<IdentityFMQAcceptedEvent, Object> {
        private IdentityFMQAcceptedEventAdapter() {
        }

        public void write(Protocol protocol, IdentityFMQAcceptedEvent struct) throws IOException {
            protocol.writeStructBegin("IdentityFMQAcceptedEvent");
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
        if (!(other instanceof IdentityFMQAcceptedEvent)) {
            return false;
        }
        IdentityFMQAcceptedEvent that = (IdentityFMQAcceptedEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && (this.context == that.context || this.context.equals(that.context)))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "IdentityFMQAcceptedEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
