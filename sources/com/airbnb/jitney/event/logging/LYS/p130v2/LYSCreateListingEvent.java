package com.airbnb.jitney.event.logging.LYS.p130v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.LYS.v2.LYSCreateListingEvent */
public final class LYSCreateListingEvent implements Struct {
    public static final Adapter<LYSCreateListingEvent, Builder> ADAPTER = new LYSCreateListingEventAdapter();
    public final Context context;
    public final String event_name;
    public final Boolean is_successful;
    public final Long listing_id;
    public final String lys_session_id;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final String target;

    /* renamed from: com.airbnb.jitney.event.logging.LYS.v2.LYSCreateListingEvent$Builder */
    public static final class Builder implements StructBuilder<LYSCreateListingEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Boolean is_successful;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public String lys_session_id;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String target;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.LYS:LYSCreateListingEvent:2.0.0";
            this.event_name = "lys_create_listing";
            this.page = "AddressForm";
            this.target = "address_next_button";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, Boolean is_successful2, Long listing_id2, String lys_session_id2) {
            this.schema = "com.airbnb.jitney.event.logging.LYS:LYSCreateListingEvent:2.0.0";
            this.event_name = "lys_create_listing";
            this.context = context2;
            this.page = "AddressForm";
            this.target = "address_next_button";
            this.operation = C2451Operation.Click;
            this.is_successful = is_successful2;
            this.listing_id = listing_id2;
            this.lys_session_id = lys_session_id2;
        }

        public LYSCreateListingEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.target == null) {
                throw new IllegalStateException("Required field 'target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.is_successful == null) {
                throw new IllegalStateException("Required field 'is_successful' is missing");
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.lys_session_id != null) {
                return new LYSCreateListingEvent(this);
            } else {
                throw new IllegalStateException("Required field 'lys_session_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.LYS.v2.LYSCreateListingEvent$LYSCreateListingEventAdapter */
    private static final class LYSCreateListingEventAdapter implements Adapter<LYSCreateListingEvent, Builder> {
        private LYSCreateListingEventAdapter() {
        }

        public void write(Protocol protocol, LYSCreateListingEvent struct) throws IOException {
            protocol.writeStructBegin("LYSCreateListingEvent");
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
            protocol.writeFieldBegin("page", 3, PassportService.SF_DG11);
            protocol.writeString(struct.page);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.TARGET, 4, PassportService.SF_DG11);
            protocol.writeString(struct.target);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 5, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("is_successful", 6, 2);
            protocol.writeBool(struct.is_successful.booleanValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 7, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(ManageListingIntents.INTENT_EXTRA_SESSION_ID, 8, PassportService.SF_DG11);
            protocol.writeString(struct.lys_session_id);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private LYSCreateListingEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.target = builder.target;
        this.operation = builder.operation;
        this.is_successful = builder.is_successful;
        this.listing_id = builder.listing_id;
        this.lys_session_id = builder.lys_session_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof LYSCreateListingEvent)) {
            return false;
        }
        LYSCreateListingEvent that = (LYSCreateListingEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.target == that.target || this.target.equals(that.target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.is_successful == that.is_successful || this.is_successful.equals(that.is_successful)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && (this.lys_session_id == that.lys_session_id || this.lys_session_id.equals(that.lys_session_id)))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.is_successful.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.lys_session_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "LYSCreateListingEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", target=" + this.target + ", operation=" + this.operation + ", is_successful=" + this.is_successful + ", listing_id=" + this.listing_id + ", lys_session_id=" + this.lys_session_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
