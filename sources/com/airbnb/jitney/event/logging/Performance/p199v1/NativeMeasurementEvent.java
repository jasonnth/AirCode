package com.airbnb.jitney.event.logging.Performance.p199v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1.C2445NativeMeasurementType;
import com.airbnb.jitney.event.logging.NativeModeType.p160v1.C2446NativeModeType;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Performance.v1.NativeMeasurementEvent */
public final class NativeMeasurementEvent implements Struct {
    public static final Adapter<NativeMeasurementEvent, Object> ADAPTER = new NativeMeasurementEventAdapter();
    public final C2446NativeModeType app_mode;
    public final Context context;
    public final String event_name;
    public final Map<String, String> information;
    public final C2445NativeMeasurementType measurement_type;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final Long value;

    /* renamed from: com.airbnb.jitney.event.logging.Performance.v1.NativeMeasurementEvent$NativeMeasurementEventAdapter */
    private static final class NativeMeasurementEventAdapter implements Adapter<NativeMeasurementEvent, Object> {
        private NativeMeasurementEventAdapter() {
        }

        public void write(Protocol protocol, NativeMeasurementEvent struct) throws IOException {
            protocol.writeStructBegin("NativeMeasurementEvent");
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
            protocol.writeFieldBegin("measurement_type", 3, 8);
            protocol.writeI32(struct.measurement_type.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("value", 5, 10);
            protocol.writeI64(struct.value.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("app_mode", 6, 8);
            protocol.writeI32(struct.app_mode.value);
            protocol.writeFieldEnd();
            if (struct.page != null) {
                protocol.writeFieldBegin("page", 7, PassportService.SF_DG11);
                protocol.writeString(struct.page);
                protocol.writeFieldEnd();
            }
            if (struct.information != null) {
                protocol.writeFieldBegin("information", 8, 13);
                protocol.writeMapBegin(PassportService.SF_DG11, PassportService.SF_DG11, struct.information.size());
                for (Entry<String, String> entry0 : struct.information.entrySet()) {
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
        if (!(other instanceof NativeMeasurementEvent)) {
            return false;
        }
        NativeMeasurementEvent that = (NativeMeasurementEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.measurement_type == that.measurement_type || this.measurement_type.equals(that.measurement_type)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.value == that.value || this.value.equals(that.value)) && ((this.app_mode == that.app_mode || this.app_mode.equals(that.app_mode)) && (this.page == that.page || (this.page != null && this.page.equals(that.page)))))))))) {
            if (this.information == that.information) {
                return true;
            }
            if (this.information != null && this.information.equals(that.information)) {
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
        int code = (((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.measurement_type.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.value.hashCode()) * -2128831035) ^ this.app_mode.hashCode()) * -2128831035) ^ (this.page == null ? 0 : this.page.hashCode())) * -2128831035;
        if (this.information != null) {
            i = this.information.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "NativeMeasurementEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", measurement_type=" + this.measurement_type + ", operation=" + this.operation + ", value=" + this.value + ", app_mode=" + this.app_mode + ", page=" + this.page + ", information=" + this.information + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
