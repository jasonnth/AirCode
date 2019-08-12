package com.airbnb.jitney.event.logging.Sessionization.p251v1;

import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Sessionization.v1.SessionizationSessionChangeEvent */
public final class SessionizationSessionChangeEvent implements Struct {
    public static final Adapter<SessionizationSessionChangeEvent, Builder> ADAPTER = new SessionizationSessionChangeEventAdapter();
    public final String client_session_id;
    public final String client_session_id_previous;
    public final Context context;
    public final String event_name;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Sessionization.v1.SessionizationSessionChangeEvent$Builder */
    public static final class Builder implements StructBuilder<SessionizationSessionChangeEvent> {
        /* access modifiers changed from: private */
        public String client_session_id;
        /* access modifiers changed from: private */
        public String client_session_id_previous;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "sessionization_session_change";
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Sessionization:SessionizationSessionChangeEvent:1.0.0";

        private Builder() {
        }

        public Builder(Context context2, String client_session_id2) {
            this.context = context2;
            this.client_session_id = client_session_id2;
        }

        public Builder client_session_id_previous(String client_session_id_previous2) {
            this.client_session_id_previous = client_session_id_previous2;
            return this;
        }

        public SessionizationSessionChangeEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.client_session_id != null) {
                return new SessionizationSessionChangeEvent(this);
            } else {
                throw new IllegalStateException("Required field 'client_session_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Sessionization.v1.SessionizationSessionChangeEvent$SessionizationSessionChangeEventAdapter */
    private static final class SessionizationSessionChangeEventAdapter implements Adapter<SessionizationSessionChangeEvent, Builder> {
        private SessionizationSessionChangeEventAdapter() {
        }

        public void write(Protocol protocol, SessionizationSessionChangeEvent struct) throws IOException {
            protocol.writeStructBegin("SessionizationSessionChangeEvent");
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
            protocol.writeFieldBegin("client_session_id", 3, PassportService.SF_DG11);
            protocol.writeString(struct.client_session_id);
            protocol.writeFieldEnd();
            if (struct.client_session_id_previous != null) {
                protocol.writeFieldBegin("client_session_id_previous", 4, PassportService.SF_DG11);
                protocol.writeString(struct.client_session_id_previous);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private SessionizationSessionChangeEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.client_session_id = builder.client_session_id;
        this.client_session_id_previous = builder.client_session_id_previous;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof SessionizationSessionChangeEvent)) {
            return false;
        }
        SessionizationSessionChangeEvent that = (SessionizationSessionChangeEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && (this.client_session_id == that.client_session_id || this.client_session_id.equals(that.client_session_id))))) {
            if (this.client_session_id_previous == that.client_session_id_previous) {
                return true;
            }
            if (this.client_session_id_previous != null && this.client_session_id_previous.equals(that.client_session_id_previous)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.client_session_id.hashCode()) * -2128831035;
        if (this.client_session_id_previous != null) {
            i = this.client_session_id_previous.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "SessionizationSessionChangeEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", client_session_id=" + this.client_session_id + ", client_session_id_previous=" + this.client_session_id_previous + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
