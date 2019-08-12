package com.airbnb.jitney.event.logging.Cohosting.p059v1;

import com.airbnb.jitney.event.logging.CohostContext.p058v1.C1926CohostContext;
import com.airbnb.jitney.event.logging.CohostingManageListingClickTarget.p065v2.C1955CohostingManageListingClickTarget;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingClickManageListingEvent */
public final class CohostingClickManageListingEvent implements Struct {
    public static final Adapter<CohostingClickManageListingEvent, Object> ADAPTER = new CohostingClickManageListingEventAdapter();
    public final C1955CohostingManageListingClickTarget cohost_click_target;
    public final C1926CohostContext cohost_context;
    public final Context context;
    public final String event_name;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.Cohosting.v1.CohostingClickManageListingEvent$CohostingClickManageListingEventAdapter */
    private static final class CohostingClickManageListingEventAdapter implements Adapter<CohostingClickManageListingEvent, Object> {
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
            protocol.writeFieldBegin("cohost_context", 4, PassportService.SF_DG12);
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
        if (!(other instanceof CohostingClickManageListingEvent)) {
            return false;
        }
        CohostingClickManageListingEvent that = (CohostingClickManageListingEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.cohost_click_target == that.cohost_click_target || this.cohost_click_target.equals(that.cohost_click_target)) && (this.cohost_context == that.cohost_context || this.cohost_context.equals(that.cohost_context)))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.cohost_click_target.hashCode()) * -2128831035) ^ this.cohost_context.hashCode()) * -2128831035;
    }

    public String toString() {
        return "CohostingClickManageListingEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", cohost_click_target=" + this.cohost_click_target + ", cohost_context=" + this.cohost_context + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
