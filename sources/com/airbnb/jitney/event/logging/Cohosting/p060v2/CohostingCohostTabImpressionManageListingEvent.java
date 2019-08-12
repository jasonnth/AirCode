package com.airbnb.jitney.event.logging.Cohosting.p060v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.CohostingManageListingPage.p067v2.C1957CohostingManageListingPage;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v2.CohostingCohostTabImpressionManageListingEvent */
public final class CohostingCohostTabImpressionManageListingEvent implements Struct {
    public static final Adapter<CohostingCohostTabImpressionManageListingEvent, Builder> ADAPTER = new CohostingCohostTabImpressionManageListingEventAdapter();
    public final C1957CohostingManageListingPage cohost_page;
    public final Context context;
    public final String event_name;
    public final Boolean from_old_ml;
    public final Long listing_id;
    public final C2451Operation operation;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v2.CohostingCohostTabImpressionManageListingEvent$Builder */
    public static final class Builder implements StructBuilder<CohostingCohostTabImpressionManageListingEvent> {
        /* access modifiers changed from: private */
        public C1957CohostingManageListingPage cohost_page;
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
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingCohostTabImpressionManageListingEvent:2.0.0";
            this.event_name = "cohosting_cohost_tab_impression_manage_listing";
            this.cohost_page = C1957CohostingManageListingPage.ManageListingCohostsTab;
            this.operation = C2451Operation.Impression;
            this.from_old_ml = Boolean.valueOf(false);
        }

        public Builder(Context context2, Long listing_id2) {
            this.schema = "com.airbnb.jitney.event.logging.Cohosting:CohostingCohostTabImpressionManageListingEvent:2.0.0";
            this.event_name = "cohosting_cohost_tab_impression_manage_listing";
            this.context = context2;
            this.cohost_page = C1957CohostingManageListingPage.ManageListingCohostsTab;
            this.operation = C2451Operation.Impression;
            this.listing_id = listing_id2;
            this.from_old_ml = Boolean.valueOf(false);
        }

        public CohostingCohostTabImpressionManageListingEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.cohost_page == null) {
                throw new IllegalStateException("Required field 'cohost_page' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.listing_id != null) {
                return new CohostingCohostTabImpressionManageListingEvent(this);
            } else {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v2.CohostingCohostTabImpressionManageListingEvent$CohostingCohostTabImpressionManageListingEventAdapter */
    private static final class CohostingCohostTabImpressionManageListingEventAdapter implements Adapter<CohostingCohostTabImpressionManageListingEvent, Builder> {
        private CohostingCohostTabImpressionManageListingEventAdapter() {
        }

        public void write(Protocol protocol, CohostingCohostTabImpressionManageListingEvent struct) throws IOException {
            protocol.writeStructBegin("CohostingCohostTabImpressionManageListingEvent");
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
            protocol.writeFieldBegin("cohost_page", 3, 8);
            protocol.writeI32(struct.cohost_page.value);
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

    private CohostingCohostTabImpressionManageListingEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.cohost_page = builder.cohost_page;
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
        if (!(other instanceof CohostingCohostTabImpressionManageListingEvent)) {
            return false;
        }
        CohostingCohostTabImpressionManageListingEvent that = (CohostingCohostTabImpressionManageListingEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.cohost_page == that.cohost_page || this.cohost_page.equals(that.cohost_page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id))))))) {
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
        int code = (((((((((((16777619 ^ hashCode) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.cohost_page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035;
        if (this.from_old_ml != null) {
            i = this.from_old_ml.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "CohostingCohostTabImpressionManageListingEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", cohost_page=" + this.cohost_page + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", from_old_ml=" + this.from_old_ml + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
