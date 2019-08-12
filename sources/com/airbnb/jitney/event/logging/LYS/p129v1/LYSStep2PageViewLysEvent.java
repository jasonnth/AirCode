package com.airbnb.jitney.event.logging.LYS.p129v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.LysStep2PagesType.p143v1.C2380LysStep2PagesType;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.LYS.v1.LYSStep2PageViewLysEvent */
public final class LYSStep2PageViewLysEvent implements Struct {
    public static final Adapter<LYSStep2PageViewLysEvent, Object> ADAPTER = new LYSStep2PageViewLysEventAdapter();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final C2451Operation operation;
    public final C2380LysStep2PagesType page;
    public final String schema;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.LYS.v1.LYSStep2PageViewLysEvent$LYSStep2PageViewLysEventAdapter */
    private static final class LYSStep2PageViewLysEventAdapter implements Adapter<LYSStep2PageViewLysEvent, Object> {
        private LYSStep2PageViewLysEventAdapter() {
        }

        public void write(Protocol protocol, LYSStep2PageViewLysEvent struct) throws IOException {
            protocol.writeStructBegin("LYSStep2PageViewLysEvent");
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
            protocol.writeFieldBegin("page", 3, 8);
            protocol.writeI32(struct.page.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("user_id", 5, 10);
            protocol.writeI64(struct.user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 6, 10);
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
        if (!(other instanceof LYSStep2PageViewLysEvent)) {
            return false;
        }
        LYSStep2PageViewLysEvent that = (LYSStep2PageViewLysEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && (this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035;
    }

    public String toString() {
        return "LYSStep2PageViewLysEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", user_id=" + this.user_id + ", listing_id=" + this.listing_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
