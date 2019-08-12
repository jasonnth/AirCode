package com.airbnb.jitney.event.logging.MobilePerf.p154v1;

import com.airbnb.jitney.event.logging.PerfStatus.p198v1.C2556PerfStatus;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.MobilePerf.v1.MobilePerfGenericEvent */
public final class MobilePerfGenericEvent implements Struct {
    public static final Adapter<MobilePerfGenericEvent, Object> ADAPTER = new MobilePerfGenericEventAdapter();
    public final Context context;
    public final String event_name;
    public final Map<String, String> perf_info;
    public final String perf_operation;
    public final C2556PerfStatus perf_status;
    public final String schema;
    public final Long total_time_ms;

    /* renamed from: com.airbnb.jitney.event.logging.MobilePerf.v1.MobilePerfGenericEvent$MobilePerfGenericEventAdapter */
    private static final class MobilePerfGenericEventAdapter implements Adapter<MobilePerfGenericEvent, Object> {
        private MobilePerfGenericEventAdapter() {
        }

        public void write(Protocol protocol, MobilePerfGenericEvent struct) throws IOException {
            protocol.writeStructBegin("MobilePerfGenericEvent");
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
            protocol.writeFieldBegin("perf_operation", 3, PassportService.SF_DG11);
            protocol.writeString(struct.perf_operation);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("total_time_ms", 4, 10);
            protocol.writeI64(struct.total_time_ms.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("perf_status", 5, 8);
            protocol.writeI32(struct.perf_status.value);
            protocol.writeFieldEnd();
            if (struct.perf_info != null) {
                protocol.writeFieldBegin("perf_info", 6, 13);
                protocol.writeMapBegin(PassportService.SF_DG11, PassportService.SF_DG11, struct.perf_info.size());
                for (Entry<String, String> entry0 : struct.perf_info.entrySet()) {
                    String value0 = (String) entry0.getValue();
                    protocol.writeString((String) entry0.getKey());
                    protocol.writeString(value0);
                }
                protocol.writeMapEnd();
                protocol.writeFieldEnd();
            }
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
        if (!(other instanceof MobilePerfGenericEvent)) {
            return false;
        }
        MobilePerfGenericEvent that = (MobilePerfGenericEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.perf_operation == that.perf_operation || this.perf_operation.equals(that.perf_operation)) && ((this.total_time_ms == that.total_time_ms || this.total_time_ms.equals(that.total_time_ms)) && (this.perf_status == that.perf_status || this.perf_status.equals(that.perf_status))))))) {
            if (this.perf_info == that.perf_info) {
                return true;
            }
            if (this.perf_info != null && this.perf_info.equals(that.perf_info)) {
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
        int code = (((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.perf_operation.hashCode()) * -2128831035) ^ this.total_time_ms.hashCode()) * -2128831035) ^ this.perf_status.hashCode()) * -2128831035;
        if (this.perf_info != null) {
            i = this.perf_info.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "MobilePerfGenericEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", perf_operation=" + this.perf_operation + ", total_time_ms=" + this.total_time_ms + ", perf_status=" + this.perf_status + ", perf_info=" + this.perf_info + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
