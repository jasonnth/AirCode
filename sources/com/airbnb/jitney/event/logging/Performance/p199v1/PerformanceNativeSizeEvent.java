package com.airbnb.jitney.event.logging.Performance.p199v1;

import com.airbnb.jitney.event.logging.NativeSizeType.p161v1.C2447NativeSizeType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Performance.v1.PerformanceNativeSizeEvent */
public final class PerformanceNativeSizeEvent implements Struct {
    public static final Adapter<PerformanceNativeSizeEvent, Object> ADAPTER = new PerformanceNativeSizeEventAdapter();
    public final Context context;
    public final String event_name;
    public final String schema;
    public final Double size_in_bytes;
    public final C2447NativeSizeType size_type;

    /* renamed from: com.airbnb.jitney.event.logging.Performance.v1.PerformanceNativeSizeEvent$PerformanceNativeSizeEventAdapter */
    private static final class PerformanceNativeSizeEventAdapter implements Adapter<PerformanceNativeSizeEvent, Object> {
        private PerformanceNativeSizeEventAdapter() {
        }

        public void write(Protocol protocol, PerformanceNativeSizeEvent struct) throws IOException {
            protocol.writeStructBegin("PerformanceNativeSizeEvent");
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
            protocol.writeFieldBegin("size_type", 3, 8);
            protocol.writeI32(struct.size_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("size_in_bytes", 4, 4);
            protocol.writeDouble(struct.size_in_bytes.doubleValue());
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
        if (!(other instanceof PerformanceNativeSizeEvent)) {
            return false;
        }
        PerformanceNativeSizeEvent that = (PerformanceNativeSizeEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.size_type == that.size_type || this.size_type.equals(that.size_type)) && (this.size_in_bytes == that.size_in_bytes || this.size_in_bytes.equals(that.size_in_bytes)))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.size_type.hashCode()) * -2128831035) ^ this.size_in_bytes.hashCode()) * -2128831035;
    }

    public String toString() {
        return "PerformanceNativeSizeEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", size_type=" + this.size_type + ", size_in_bytes=" + this.size_in_bytes + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
