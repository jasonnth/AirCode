package com.airbnb.jitney.event.logging.BusinessTravel.p043v1;

import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelBtrFilterNumListingsEvent */
public final class BusinessTravelBtrFilterNumListingsEvent implements Struct {
    public static final Adapter<BusinessTravelBtrFilterNumListingsEvent, Builder> ADAPTER = new BusinessTravelBtrFilterNumListingsEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long n_results;
    public final String schema;
    public final C2731SearchContext search_context;

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelBtrFilterNumListingsEvent$Builder */
    public static final class Builder implements StructBuilder<BusinessTravelBtrFilterNumListingsEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name = "businesstravel_btr_filter_num_listings";
        /* access modifiers changed from: private */
        public Long n_results;
        /* access modifiers changed from: private */
        public String schema = "com.airbnb.jitney.event.logging.BusinessTravel:BusinessTravelBtrFilterNumListingsEvent:1.0.0";
        /* access modifiers changed from: private */
        public C2731SearchContext search_context;

        private Builder() {
        }

        public Builder(Context context2, Long n_results2) {
            this.context = context2;
            this.n_results = n_results2;
        }

        public BusinessTravelBtrFilterNumListingsEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.n_results != null) {
                return new BusinessTravelBtrFilterNumListingsEvent(this);
            } else {
                throw new IllegalStateException("Required field 'n_results' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.BusinessTravel.v1.BusinessTravelBtrFilterNumListingsEvent$BusinessTravelBtrFilterNumListingsEventAdapter */
    private static final class BusinessTravelBtrFilterNumListingsEventAdapter implements Adapter<BusinessTravelBtrFilterNumListingsEvent, Builder> {
        private BusinessTravelBtrFilterNumListingsEventAdapter() {
        }

        public void write(Protocol protocol, BusinessTravelBtrFilterNumListingsEvent struct) throws IOException {
            protocol.writeStructBegin("BusinessTravelBtrFilterNumListingsEvent");
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
            protocol.writeFieldBegin("n_results", 3, 10);
            protocol.writeI64(struct.n_results.longValue());
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

    private BusinessTravelBtrFilterNumListingsEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.n_results = builder.n_results;
        this.search_context = builder.search_context;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof BusinessTravelBtrFilterNumListingsEvent)) {
            return false;
        }
        BusinessTravelBtrFilterNumListingsEvent that = (BusinessTravelBtrFilterNumListingsEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && (this.n_results == that.n_results || this.n_results.equals(that.n_results))))) {
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
        int code = (((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.n_results.hashCode()) * -2128831035;
        if (this.search_context != null) {
            i = this.search_context.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "BusinessTravelBtrFilterNumListingsEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", n_results=" + this.n_results + ", search_context=" + this.search_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
