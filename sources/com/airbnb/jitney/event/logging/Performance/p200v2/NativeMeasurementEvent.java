package com.airbnb.jitney.event.logging.Performance.p200v2;

import com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1.C2445NativeMeasurementType;
import com.airbnb.jitney.event.logging.NativeModeType.p160v1.C2446NativeModeType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Performance.v2.NativeMeasurementEvent */
public final class NativeMeasurementEvent implements Struct {
    public static final Adapter<NativeMeasurementEvent, Builder> ADAPTER = new NativeMeasurementEventAdapter();
    public final C2446NativeModeType app_mode;
    public final Context context;
    public final String event_name;
    public final Map<String, String> information;
    public final C2445NativeMeasurementType measurement_type;
    public final String native_measurement_operation;
    public final String schema;
    public final Long value;
    public final String view;

    /* renamed from: com.airbnb.jitney.event.logging.Performance.v2.NativeMeasurementEvent$Builder */
    public static final class Builder implements StructBuilder<NativeMeasurementEvent> {
        /* access modifiers changed from: private */
        public C2446NativeModeType app_mode;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "native_measurement";
        /* access modifiers changed from: private */
        public Map<String, String> information;
        /* access modifiers changed from: private */
        public C2445NativeMeasurementType measurement_type;
        /* access modifiers changed from: private */
        public String native_measurement_operation;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.Performance:NativeMeasurementEvent:2.0.0";
        /* access modifiers changed from: private */
        public Long value;
        /* access modifiers changed from: private */
        public String view;

        private Builder() {
        }

        public Builder(Context context2, C2445NativeMeasurementType measurement_type2, String native_measurement_operation2, Long value2, C2446NativeModeType app_mode2) {
            this.context = context2;
            this.measurement_type = measurement_type2;
            this.native_measurement_operation = native_measurement_operation2;
            this.value = value2;
            this.app_mode = app_mode2;
        }

        public Builder view(String view2) {
            this.view = view2;
            return this;
        }

        public Builder information(Map<String, String> information2) {
            this.information = information2;
            return this;
        }

        public NativeMeasurementEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.measurement_type == null) {
                throw new IllegalStateException("Required field 'measurement_type' is missing");
            } else if (this.native_measurement_operation == null) {
                throw new IllegalStateException("Required field 'native_measurement_operation' is missing");
            } else if (this.value == null) {
                throw new IllegalStateException("Required field 'value' is missing");
            } else if (this.app_mode != null) {
                return new NativeMeasurementEvent(this);
            } else {
                throw new IllegalStateException("Required field 'app_mode' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Performance.v2.NativeMeasurementEvent$NativeMeasurementEventAdapter */
    private static final class NativeMeasurementEventAdapter implements Adapter<NativeMeasurementEvent, Builder> {
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
            protocol.writeFieldBegin("native_measurement_operation", 4, PassportService.SF_DG11);
            protocol.writeString(struct.native_measurement_operation);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("value", 5, 10);
            protocol.writeI64(struct.value.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("app_mode", 6, 8);
            protocol.writeI32(struct.app_mode.value);
            protocol.writeFieldEnd();
            if (struct.view != null) {
                protocol.writeFieldBegin("view", 7, PassportService.SF_DG11);
                protocol.writeString(struct.view);
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

    private NativeMeasurementEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.measurement_type = builder.measurement_type;
        this.native_measurement_operation = builder.native_measurement_operation;
        this.value = builder.value;
        this.app_mode = builder.app_mode;
        this.view = builder.view;
        this.information = builder.information == null ? null : Collections.unmodifiableMap(builder.information);
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
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.measurement_type == that.measurement_type || this.measurement_type.equals(that.measurement_type)) && ((this.native_measurement_operation == that.native_measurement_operation || this.native_measurement_operation.equals(that.native_measurement_operation)) && ((this.value == that.value || this.value.equals(that.value)) && ((this.app_mode == that.app_mode || this.app_mode.equals(that.app_mode)) && (this.view == that.view || (this.view != null && this.view.equals(that.view)))))))))) {
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
        int code = (((((((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.measurement_type.hashCode()) * -2128831035) ^ this.native_measurement_operation.hashCode()) * -2128831035) ^ this.value.hashCode()) * -2128831035) ^ this.app_mode.hashCode()) * -2128831035) ^ (this.view == null ? 0 : this.view.hashCode())) * -2128831035;
        if (this.information != null) {
            i = this.information.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "NativeMeasurementEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", measurement_type=" + this.measurement_type + ", native_measurement_operation=" + this.native_measurement_operation + ", value=" + this.value + ", app_mode=" + this.app_mode + ", view=" + this.view + ", information=" + this.information + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
