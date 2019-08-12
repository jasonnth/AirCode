package com.airbnb.jitney.event.logging.Cohosting.p059v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.CohostingManageListingClickTarget.p065v2.C1955CohostingManageListingClickTarget;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingCohostTabClickManageListingEvent */
public final class CohostingCohostTabClickManageListingEvent implements Struct {
    public static final Adapter<CohostingCohostTabClickManageListingEvent, Object> ADAPTER = new CohostingCohostTabClickManageListingEventAdapter();
    public final C1955CohostingManageListingClickTarget cohost_click_target;
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final C2451Operation operation;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingCohostTabClickManageListingEvent$CohostingCohostTabClickManageListingEventAdapter */
    private static final class CohostingCohostTabClickManageListingEventAdapter implements Adapter<CohostingCohostTabClickManageListingEvent, Object> {
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
        if (!(other instanceof CohostingCohostTabClickManageListingEvent)) {
            return false;
        }
        CohostingCohostTabClickManageListingEvent that = (CohostingCohostTabClickManageListingEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.cohost_click_target == that.cohost_click_target || this.cohost_click_target.equals(that.cohost_click_target)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.cohost_click_target.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CohostingCohostTabClickManageListingEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", cohost_click_target=" + this.cohost_click_target + ", operation=" + this.operation + ", listing_id=" + this.listing_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
