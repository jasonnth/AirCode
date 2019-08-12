package com.airbnb.jitney.event.logging.Systems.p263v1;

import com.airbnb.jitney.event.logging.NativeModeType.p160v1.C2446NativeModeType;
import com.airbnb.jitney.event.logging.NetworkType.p163v1.C2449NetworkType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

/* renamed from: com.airbnb.jitney.event.logging.Systems.v1.SystemsNativeNetworkRequestTimeoutEvent */
public final class SystemsNativeNetworkRequestTimeoutEvent implements Struct {
    public static final Adapter<SystemsNativeNetworkRequestTimeoutEvent, Builder> ADAPTER = new SystemsNativeNetworkRequestTimeoutEventAdapter();
    public final C2446NativeModeType app_mode;
    public final Context context;
    public final String event_name;
    public final String network_request_identifier;
    public final C2449NetworkType network_type;
    public final String schema;
    public final Long timeout_ms;

    /* renamed from: com.airbnb.jitney.event.logging.Systems.v1.SystemsNativeNetworkRequestTimeoutEvent$Builder */
    public static final class Builder implements StructBuilder<SystemsNativeNetworkRequestTimeoutEvent> {
        /* access modifiers changed from: private */
        public C2446NativeModeType app_mode;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "systems_native_network_request_timeout";
        /* access modifiers changed from: private */
        public String network_request_identifier;
        /* access modifiers changed from: private */
        public C2449NetworkType network_type;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Systems:SystemsNativeNetworkRequestTimeoutEvent:1.0.0";
        /* access modifiers changed from: private */
        public Long timeout_ms;

        private Builder() {
        }

        public Builder(Context context2, String network_request_identifier2, C2449NetworkType network_type2, C2446NativeModeType app_mode2) {
            this.context = context2;
            this.network_request_identifier = network_request_identifier2;
            this.network_type = network_type2;
            this.app_mode = app_mode2;
        }

        public SystemsNativeNetworkRequestTimeoutEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.network_request_identifier == null) {
                throw new IllegalStateException("Required field 'network_request_identifier' is missing");
            } else if (this.network_type == null) {
                throw new IllegalStateException("Required field 'network_type' is missing");
            } else if (this.app_mode != null) {
                return new SystemsNativeNetworkRequestTimeoutEvent(this);
            } else {
                throw new IllegalStateException("Required field 'app_mode' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Systems.v1.SystemsNativeNetworkRequestTimeoutEvent$SystemsNativeNetworkRequestTimeoutEventAdapter */
    private static final class SystemsNativeNetworkRequestTimeoutEventAdapter implements Adapter<SystemsNativeNetworkRequestTimeoutEvent, Builder> {
        private SystemsNativeNetworkRequestTimeoutEventAdapter() {
        }

        public void write(Protocol protocol, SystemsNativeNetworkRequestTimeoutEvent struct) throws IOException {
            protocol.writeStructBegin("SystemsNativeNetworkRequestTimeoutEvent");
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
            protocol.writeFieldBegin("network_request_identifier", 3, PassportService.SF_DG11);
            protocol.writeString(struct.network_request_identifier);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(JPushReportInterface.NETWORK_TYPE, 4, 8);
            protocol.writeI32(struct.network_type.value);
            protocol.writeFieldEnd();
            if (struct.timeout_ms != null) {
                protocol.writeFieldBegin("timeout_ms", 5, 10);
                protocol.writeI64(struct.timeout_ms.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("app_mode", 6, 8);
            protocol.writeI32(struct.app_mode.value);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private SystemsNativeNetworkRequestTimeoutEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.network_request_identifier = builder.network_request_identifier;
        this.network_type = builder.network_type;
        this.timeout_ms = builder.timeout_ms;
        this.app_mode = builder.app_mode;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof SystemsNativeNetworkRequestTimeoutEvent)) {
            return false;
        }
        SystemsNativeNetworkRequestTimeoutEvent that = (SystemsNativeNetworkRequestTimeoutEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.network_request_identifier == that.network_request_identifier || this.network_request_identifier.equals(that.network_request_identifier)) && ((this.network_type == that.network_type || this.network_type.equals(that.network_type)) && ((this.timeout_ms == that.timeout_ms || (this.timeout_ms != null && this.timeout_ms.equals(that.timeout_ms))) && (this.app_mode == that.app_mode || this.app_mode.equals(that.app_mode)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.network_request_identifier.hashCode()) * -2128831035) ^ this.network_type.hashCode()) * -2128831035;
        if (this.timeout_ms != null) {
            i = this.timeout_ms.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.app_mode.hashCode()) * -2128831035;
    }

    public String toString() {
        return "SystemsNativeNetworkRequestTimeoutEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", network_request_identifier=" + this.network_request_identifier + ", network_type=" + this.network_type + ", timeout_ms=" + this.timeout_ms + ", app_mode=" + this.app_mode + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
