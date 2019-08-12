package com.airbnb.jitney.event.logging.Cohosting.p060v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.CohostingContext.p062v1.C1951CohostingContext;
import com.airbnb.jitney.event.logging.CohostingManageListingClickTarget.p065v2.C1955CohostingManageListingClickTarget;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v2.CohostingClickManageListingEvent */
public final class CohostingClickManageListingEvent implements Struct {
    public static final Adapter<CohostingClickManageListingEvent, Builder> ADAPTER = new CohostingClickManageListingEventAdapter();
    public final C1955CohostingManageListingClickTarget cohost_click_target;
    public final C1951CohostingContext cohosting_context;
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v2.CohostingClickManageListingEvent$Builder */
    public static final class Builder implements StructBuilder<CohostingClickManageListingEvent> {
        /* access modifiers changed from: private */
        public C1955CohostingManageListingClickTarget cohost_click_target;
        /* access modifiers changed from: private */
        public C1951CohostingContext cohosting_context;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingClickManageListingEvent:2.0.0";
            this.event_name = "cohosting_click_manage_listing";
            this.operation = C2451Operation.Click;
        }

        public Builder(Context context2, C1955CohostingManageListingClickTarget cohost_click_target2, C1951CohostingContext cohosting_context2) {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingClickManageListingEvent:2.0.0";
            this.event_name = "cohosting_click_manage_listing";
            this.context = context2;
            this.cohost_click_target = cohost_click_target2;
            this.operation = C2451Operation.Click;
            this.cohosting_context = cohosting_context2;
        }

        public CohostingClickManageListingEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.cohost_click_target == null) {
                throw new IllegalStateException("Required field 'cohost_click_target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.cohosting_context != null) {
                return new CohostingClickManageListingEvent(this);
            } else {
                throw new IllegalStateException("Required field 'cohosting_context' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v2.CohostingClickManageListingEvent$CohostingClickManageListingEventAdapter */
    private static final class CohostingClickManageListingEventAdapter implements Adapter<CohostingClickManageListingEvent, Builder> {
        private CohostingClickManageListingEventAdapter() {
        }

        public void write(Protocol protocol, CohostingClickManageListingEvent struct) throws IOException {
            protocol.writeStructBegin("CohostingClickManageListingEvent");
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
            protocol.writeFieldBegin("cohost_click_target", 3, 8);
            protocol.writeI32(struct.cohost_click_target.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("cohosting_context", 5, PassportService.SF_DG12);
            C1951CohostingContext.ADAPTER.write(protocol, struct.cohosting_context);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private CohostingClickManageListingEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.cohost_click_target = builder.cohost_click_target;
        this.operation = builder.operation;
        this.cohosting_context = builder.cohosting_context;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof CohostingClickManageListingEvent)) {
            return false;
        }
        CohostingClickManageListingEvent that = (CohostingClickManageListingEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.cohost_click_target == that.cohost_click_target || this.cohost_click_target.equals(that.cohost_click_target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.cohosting_context == that.cohosting_context || this.cohosting_context.equals(that.cohosting_context))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.cohost_click_target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.cohosting_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CohostingClickManageListingEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", cohost_click_target=" + this.cohost_click_target + ", operation=" + this.operation + ", cohosting_context=" + this.cohosting_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
