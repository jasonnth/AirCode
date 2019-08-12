package com.airbnb.jitney.event.logging.Systems.p263v1;

import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Systems.v1.SystemsNativeApplicationLaunchEvent */
public final class SystemsNativeApplicationLaunchEvent implements Struct {
    public static final Adapter<SystemsNativeApplicationLaunchEvent, Builder> ADAPTER = new SystemsNativeApplicationLaunchEventAdapter();
    public final Context context;
    public final String event_name;
    public final Boolean is_logged_in;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Systems.v1.SystemsNativeApplicationLaunchEvent$Builder */
    public static final class Builder implements StructBuilder<SystemsNativeApplicationLaunchEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "systems_native_application_launch";
        /* access modifiers changed from: private */
        public Boolean is_logged_in;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Systems:SystemsNativeApplicationLaunchEvent:1.0.0";

        private Builder() {
        }

        public Builder(Context context2) {
            this.context = context2;
        }

        public SystemsNativeApplicationLaunchEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context != null) {
                return new SystemsNativeApplicationLaunchEvent(this);
            } else {
                throw new IllegalStateException("Required field 'context' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Systems.v1.SystemsNativeApplicationLaunchEvent$SystemsNativeApplicationLaunchEventAdapter */
    private static final class SystemsNativeApplicationLaunchEventAdapter implements Adapter<SystemsNativeApplicationLaunchEvent, Builder> {
        private SystemsNativeApplicationLaunchEventAdapter() {
        }

        public void write(Protocol protocol, SystemsNativeApplicationLaunchEvent struct) throws IOException {
            protocol.writeStructBegin("SystemsNativeApplicationLaunchEvent");
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
            if (struct.is_logged_in != null) {
                protocol.writeFieldBegin("is_logged_in", 3, 2);
                protocol.writeBool(struct.is_logged_in.booleanValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private SystemsNativeApplicationLaunchEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.is_logged_in = builder.is_logged_in;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof SystemsNativeApplicationLaunchEvent)) {
            return false;
        }
        SystemsNativeApplicationLaunchEvent that = (SystemsNativeApplicationLaunchEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && (this.context == that.context || this.context.equals(that.context)))) {
            if (this.is_logged_in == that.is_logged_in) {
                return true;
            }
            if (this.is_logged_in != null && this.is_logged_in.equals(that.is_logged_in)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035;
        if (this.is_logged_in != null) {
            i = this.is_logged_in.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "SystemsNativeApplicationLaunchEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", is_logged_in=" + this.is_logged_in + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
