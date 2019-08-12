package com.airbnb.jitney.event.logging.ExternalApp.p099v1;

import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.ExternalApp.v1.ExternalAppServiceEvent */
public final class ExternalAppServiceEvent implements Struct {
    public static final Adapter<ExternalAppServiceEvent, Object> ADAPTER = new ExternalAppServiceEventAdapter();
    public final Context context;
    public final String event_name;
    public final Map<String, String> external_app_existence;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.ExternalApp.v1.ExternalAppServiceEvent$ExternalAppServiceEventAdapter */
    private static final class ExternalAppServiceEventAdapter implements Adapter<ExternalAppServiceEvent, Object> {
        private ExternalAppServiceEventAdapter() {
        }

        public void write(Protocol protocol, ExternalAppServiceEvent struct) throws IOException {
            protocol.writeStructBegin("ExternalAppServiceEvent");
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
            protocol.writeFieldBegin("external_app_existence", 3, 13);
            protocol.writeMapBegin(PassportService.SF_DG11, PassportService.SF_DG11, struct.external_app_existence.size());
            for (Entry<String, String> entry0 : struct.external_app_existence.entrySet()) {
                String value0 = (String) entry0.getValue();
                protocol.writeString((String) entry0.getKey());
                protocol.writeString(value0);
            }
            protocol.writeMapEnd();
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
        if (!(other instanceof ExternalAppServiceEvent)) {
            return false;
        }
        ExternalAppServiceEvent that = (ExternalAppServiceEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && (this.external_app_existence == that.external_app_existence || this.external_app_existence.equals(that.external_app_existence))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.external_app_existence.hashCode()) * -2128831035;
    }

    public String toString() {
        return "ExternalAppServiceEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", external_app_existence=" + this.external_app_existence + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
