package com.airbnb.jitney.event.logging.BusinessTravel.p043v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.ToggleMethod.p268v1.C2759ToggleMethod;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelBtrFilterToggleEvent */
public final class BusinessTravelBtrFilterToggleEvent implements Struct {
    public static final Adapter<BusinessTravelBtrFilterToggleEvent, Builder> ADAPTER = new BusinessTravelBtrFilterToggleEventAdapter();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String schema;
    public final C2731SearchContext search_context;
    public final C2759ToggleMethod toggle_method;

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelBtrFilterToggleEvent$Builder */
    public static final class Builder implements StructBuilder<BusinessTravelBtrFilterToggleEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public C2731SearchContext search_context;
        /* access modifiers changed from: private */
        public C2759ToggleMethod toggle_method;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.BusinessTravel:BusinessTravelBtrFilterToggleEvent:1.0.0";
            this.event_name = "businesstravel_btr_filter_toggle";
            this.operation = C2451Operation.Toggle;
        }

        public Builder(Context context2, C2759ToggleMethod toggle_method2) {
            this.schema = "com.airbnb.jitney.event.logging.BusinessTravel:BusinessTravelBtrFilterToggleEvent:1.0.0";
            this.event_name = "businesstravel_btr_filter_toggle";
            this.context = context2;
            this.operation = C2451Operation.Toggle;
            this.toggle_method = toggle_method2;
        }

        public BusinessTravelBtrFilterToggleEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.toggle_method != null) {
                return new BusinessTravelBtrFilterToggleEvent(this);
            } else {
                throw new IllegalStateException("Required field 'toggle_method' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelBtrFilterToggleEvent$BusinessTravelBtrFilterToggleEventAdapter */
    private static final class BusinessTravelBtrFilterToggleEventAdapter implements Adapter<BusinessTravelBtrFilterToggleEvent, Builder> {
        private BusinessTravelBtrFilterToggleEventAdapter() {
        }

        public void write(Protocol protocol, BusinessTravelBtrFilterToggleEvent struct) throws IOException {
            protocol.writeStructBegin("BusinessTravelBtrFilterToggleEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 3, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("toggle_method", 4, 8);
            protocol.writeI32(struct.toggle_method.value);
            protocol.writeFieldEnd();
            if (struct.search_context != null) {
                protocol.writeFieldBegin("search_context", 5, PassportService.SF_DG12);
                C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private BusinessTravelBtrFilterToggleEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.operation = builder.operation;
        this.toggle_method = builder.toggle_method;
        this.search_context = builder.search_context;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof BusinessTravelBtrFilterToggleEvent)) {
            return false;
        }
        BusinessTravelBtrFilterToggleEvent that = (BusinessTravelBtrFilterToggleEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.toggle_method == that.toggle_method || this.toggle_method.equals(that.toggle_method)))))) {
            if (this.search_context == that.search_context) {
                return true;
            }
            if (this.search_context != null && this.search_context.equals(that.search_context)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.toggle_method.hashCode()) * -2128831035;
        if (this.search_context != null) {
            i = this.search_context.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "BusinessTravelBtrFilterToggleEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", operation=" + this.operation + ", toggle_method=" + this.toggle_method + ", search_context=" + this.search_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
