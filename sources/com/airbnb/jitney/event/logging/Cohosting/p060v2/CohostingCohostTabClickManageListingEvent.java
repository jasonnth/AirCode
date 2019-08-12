package com.airbnb.jitney.event.logging.Cohosting.p060v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
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

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v2.CohostingCohostTabClickManageListingEvent */
public final class CohostingCohostTabClickManageListingEvent implements Struct {
    public static final Adapter<CohostingCohostTabClickManageListingEvent, Builder> ADAPTER = new CohostingCohostTabClickManageListingEventAdapter();
    public final C1955CohostingManageListingClickTarget cohost_click_target;
    public final Context context;
    public final String event_name;
    public final Boolean from_old_ml;
    public final Long listing_id;
    public final C2451Operation operation;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v2.CohostingCohostTabClickManageListingEvent$Builder */
    public static final class Builder implements StructBuilder<CohostingCohostTabClickManageListingEvent> {
        /* access modifiers changed from: private */
        public C1955CohostingManageListingClickTarget cohost_click_target;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Boolean from_old_ml;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String schema;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingCohostTabClickManageListingEvent:2.0.0";
            this.event_name = "cohosting_cohost_tab_click_manage_listing";
            this.cohost_click_target = C1955CohostingManageListingClickTarget.CohostsTabFromNavView;
            this.operation = C2451Operation.Click;
            this.from_old_ml = Boolean.valueOf(false);
        }

        public Builder(Context context2, Long listing_id2) {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingCohostTabClickManageListingEvent:2.0.0";
            this.event_name = "cohosting_cohost_tab_click_manage_listing";
            this.context = context2;
            this.cohost_click_target = C1955CohostingManageListingClickTarget.CohostsTabFromNavView;
            this.operation = C2451Operation.Click;
            this.listing_id = listing_id2;
            this.from_old_ml = Boolean.valueOf(false);
        }

        public CohostingCohostTabClickManageListingEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.cohost_click_target == null) {
                throw new IllegalStateException("Required field 'cohost_click_target' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.listing_id != null) {
                return new CohostingCohostTabClickManageListingEvent(this);
            } else {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v2.CohostingCohostTabClickManageListingEvent$CohostingCohostTabClickManageListingEventAdapter */
    private static final class CohostingCohostTabClickManageListingEventAdapter implements Adapter<CohostingCohostTabClickManageListingEvent, Builder> {
        private CohostingCohostTabClickManageListingEventAdapter() {
        }

        public void write(Protocol protocol, CohostingCohostTabClickManageListingEvent struct) throws IOException {
            protocol.writeStructBegin("CohostingCohostTabClickManageListingEvent");
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
            protocol.writeFieldBegin("listing_id", 5, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            if (struct.from_old_ml != null) {
                protocol.writeFieldBegin("from_old_ml", 6, 2);
                protocol.writeBool(struct.from_old_ml.booleanValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private CohostingCohostTabClickManageListingEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.cohost_click_target = builder.cohost_click_target;
        this.operation = builder.operation;
        this.listing_id = builder.listing_id;
        this.from_old_ml = builder.from_old_ml;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof CohostingCohostTabClickManageListingEvent)) {
            return false;
        }
        CohostingCohostTabClickManageListingEvent that = (CohostingCohostTabClickManageListingEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.cohost_click_target == that.cohost_click_target || this.cohost_click_target.equals(that.cohost_click_target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id))))))) {
            if (this.from_old_ml == that.from_old_ml) {
                return true;
            }
            if (this.from_old_ml != null && this.from_old_ml.equals(that.from_old_ml)) {
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
        int code = (((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.cohost_click_target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035;
        if (this.from_old_ml != null) {
            i = this.from_old_ml.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "CohostingCohostTabClickManageListingEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", cohost_click_target=" + this.cohost_click_target + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", from_old_ml=" + this.from_old_ml + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
