package com.airbnb.jitney.event.logging.Cohosting.p059v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.CohostContext.p058v1.C1926CohostContext;
import com.airbnb.jitney.event.logging.CohostingManageListingPage.p066v1.C1956CohostingManageListingPage;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingImpressionManageListingEvent */
public final class CohostingImpressionManageListingEvent implements Struct {
    public static final Adapter<CohostingImpressionManageListingEvent, Object> ADAPTER = new CohostingImpressionManageListingEventAdapter();
    public final C1926CohostContext cohost_context;
    public final C1956CohostingManageListingPage cohost_page;
    public final Context context;
    public final String event_name;
    public final C2451Operation operation;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingImpressionManageListingEvent$CohostingImpressionManageListingEventAdapter */
    private static final class CohostingImpressionManageListingEventAdapter implements Adapter<CohostingImpressionManageListingEvent, Object> {
        private CohostingImpressionManageListingEventAdapter() {
        }

        public void write(Protocol protocol, CohostingImpressionManageListingEvent struct) throws IOException {
            protocol.writeStructBegin("CohostingImpressionManageListingEvent");
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
            protocol.writeFieldBegin("cohost_context", 5, PassportService.SF_DG12);
            C1926CohostContext.ADAPTER.write(protocol, struct.cohost_context);
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
        if (!(other instanceof CohostingImpressionManageListingEvent)) {
            return false;
        }
        CohostingImpressionManageListingEvent that = (CohostingImpressionManageListingEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.cohost_page == that.cohost_page || this.cohost_page.equals(that.cohost_page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && (this.cohost_context == that.cohost_context || this.cohost_context.equals(that.cohost_context))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.cohost_page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.cohost_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CohostingImpressionManageListingEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", cohost_page=" + this.cohost_page + ", operation=" + this.operation + ", cohost_context=" + this.cohost_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
