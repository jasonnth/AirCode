package com.airbnb.jitney.event.logging.BusinessTravel.p043v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelBtrFilterZeroListingsFoundEditFilterClickEvent */
public final class BusinessTravelBtrFilterZeroListingsFoundEditFilterClickEvent implements Struct {
    public static final Adapter<BusinessTravelBtrFilterZeroListingsFoundEditFilterClickEvent, Object> ADAPTER = new C1816x7266228e();
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String schema;
    public final C2731SearchContext search_context;

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelBtrFilterZeroListingsFoundEditFilterClickEvent$BusinessTravelBtrFilterZeroListingsFoundEditFilterClickEventAdapter */
    private static final class C1816x7266228e implements Adapter<BusinessTravelBtrFilterZeroListingsFoundEditFilterClickEvent, Object> {
        private C1816x7266228e() {
        }

        public void write(Protocol protocol, BusinessTravelBtrFilterZeroListingsFoundEditFilterClickEvent struct) throws IOException {
            protocol.writeStructBegin("BusinessTravelBtrFilterZeroListingsFoundEditFilterClickEvent");
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
            if (struct.search_context != null) {
                protocol.writeFieldBegin("search_context", 4, PassportService.SF_DG12);
                C2731SearchContext.ADAPTER.write(protocol, struct.search_context);
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
        if (!(other instanceof BusinessTravelBtrFilterZeroListingsFoundEditFilterClickEvent)) {
            return false;
        }
        BusinessTravelBtrFilterZeroListingsFoundEditFilterClickEvent that = (BusinessTravelBtrFilterZeroListingsFoundEditFilterClickEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && (this.operation == that.operation || this.operation.equals(that.operation))))) {
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
        int code = (((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035;
        if (this.search_context != null) {
            i = this.search_context.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "BusinessTravelBtrFilterZeroListingsFoundEditFilterClickEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", operation=" + this.operation + ", search_context=" + this.search_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
