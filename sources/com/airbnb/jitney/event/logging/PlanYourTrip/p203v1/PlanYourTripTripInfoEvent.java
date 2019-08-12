package com.airbnb.jitney.event.logging.PlanYourTrip.p203v1;

import com.airbnb.jitney.event.logging.SearchInputType.p250v1.C2733SearchInputType;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.PlanYourTrip.v1.PlanYourTripTripInfoEvent */
public final class PlanYourTripTripInfoEvent implements Struct {
    public static final Adapter<PlanYourTripTripInfoEvent, Object> ADAPTER = new PlanYourTripTripInfoEventAdapter();
    public final String checkin_date;
    public final String checkout_date;
    public final Context context;
    public final C2733SearchInputType date_source;
    public final String event_name;
    public final String location;
    public final C2733SearchInputType location_source;
    public final String referrer;
    public final String schema;

    /* renamed from: com.airbnb.jitney.event.logging.PlanYourTrip.v1.PlanYourTripTripInfoEvent$PlanYourTripTripInfoEventAdapter */
    private static final class PlanYourTripTripInfoEventAdapter implements Adapter<PlanYourTripTripInfoEvent, Object> {
        private PlanYourTripTripInfoEventAdapter() {
        }

        public void write(Protocol protocol, PlanYourTripTripInfoEvent struct) throws IOException {
            protocol.writeStructBegin("PlanYourTripTripInfoEvent");
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
            protocol.writeFieldBegin("location", 3, PassportService.SF_DG11);
            protocol.writeString(struct.location);
            protocol.writeFieldEnd();
            if (struct.location_source != null) {
                protocol.writeFieldBegin("location_source", 4, 8);
                protocol.writeI32(struct.location_source.value);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("checkin_date", 5, PassportService.SF_DG11);
            protocol.writeString(struct.checkin_date);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("checkout_date", 6, PassportService.SF_DG11);
            protocol.writeString(struct.checkout_date);
            protocol.writeFieldEnd();
            if (struct.date_source != null) {
                protocol.writeFieldBegin("date_source", 7, 8);
                protocol.writeI32(struct.date_source.value);
                protocol.writeFieldEnd();
            }
            if (struct.referrer != null) {
                protocol.writeFieldBegin("referrer", 8, PassportService.SF_DG11);
                protocol.writeString(struct.referrer);
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
        if (!(other instanceof PlanYourTripTripInfoEvent)) {
            return false;
        }
        PlanYourTripTripInfoEvent that = (PlanYourTripTripInfoEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.location == that.location || this.location.equals(that.location)) && ((this.location_source == that.location_source || (this.location_source != null && this.location_source.equals(that.location_source))) && ((this.checkin_date == that.checkin_date || this.checkin_date.equals(that.checkin_date)) && ((this.checkout_date == that.checkout_date || this.checkout_date.equals(that.checkout_date)) && (this.date_source == that.date_source || (this.date_source != null && this.date_source.equals(that.date_source)))))))))) {
            if (this.referrer == that.referrer) {
                return true;
            }
            if (this.referrer != null && this.referrer.equals(that.referrer)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.location.hashCode()) * -2128831035) ^ (this.location_source == null ? 0 : this.location_source.hashCode())) * -2128831035) ^ this.checkin_date.hashCode()) * -2128831035) ^ this.checkout_date.hashCode()) * -2128831035) ^ (this.date_source == null ? 0 : this.date_source.hashCode())) * -2128831035;
        if (this.referrer != null) {
            i = this.referrer.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "PlanYourTripTripInfoEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", location=" + this.location + ", location_source=" + this.location_source + ", checkin_date=" + this.checkin_date + ", checkout_date=" + this.checkout_date + ", date_source=" + this.date_source + ", referrer=" + this.referrer + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
