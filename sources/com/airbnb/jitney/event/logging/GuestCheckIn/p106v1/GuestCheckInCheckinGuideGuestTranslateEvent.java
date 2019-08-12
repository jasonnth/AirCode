package com.airbnb.jitney.event.logging.GuestCheckIn.p106v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.GuestCheckIn.v1.GuestCheckInCheckinGuideGuestTranslateEvent */
public final class GuestCheckInCheckinGuideGuestTranslateEvent implements Struct {
    public static final Adapter<GuestCheckInCheckinGuideGuestTranslateEvent, Object> ADAPTER = new GuestCheckInCheckinGuideGuestTranslateEventAdapter();
    public final Context context;
    public final String device_language;
    public final String event_name;
    public final String guide_language;
    public final Long listing_id;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final Long step_index;
    public final Long user_id;

    /* renamed from: com.airbnb.jitney.event.logging.GuestCheckIn.v1.GuestCheckInCheckinGuideGuestTranslateEvent$GuestCheckInCheckinGuideGuestTranslateEventAdapter */
    private static final class GuestCheckInCheckinGuideGuestTranslateEventAdapter implements Adapter<GuestCheckInCheckinGuideGuestTranslateEvent, Object> {
        private GuestCheckInCheckinGuideGuestTranslateEventAdapter() {
        }

        public void write(Protocol protocol, GuestCheckInCheckinGuideGuestTranslateEvent struct) throws IOException {
            protocol.writeStructBegin("GuestCheckInCheckinGuideGuestTranslateEvent");
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
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 4, 8);
            protocol.writeI32(struct.operation.value);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 5, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("user_id", 6, 10);
            protocol.writeI64(struct.user_id.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("step_index", 7, 10);
            protocol.writeI64(struct.step_index.longValue());
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("device_language", 8, PassportService.SF_DG11);
            protocol.writeString(struct.device_language);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("guide_language", 9, PassportService.SF_DG11);
            protocol.writeString(struct.guide_language);
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
        if (!(other instanceof GuestCheckInCheckinGuideGuestTranslateEvent)) {
            return false;
        }
        GuestCheckInCheckinGuideGuestTranslateEvent that = (GuestCheckInCheckinGuideGuestTranslateEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.user_id == that.user_id || this.user_id.equals(that.user_id)) && ((this.step_index == that.step_index || this.step_index.equals(that.step_index)) && ((this.device_language == that.device_language || this.device_language.equals(that.device_language)) && (this.guide_language == that.guide_language || this.guide_language.equals(that.guide_language))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.user_id.hashCode()) * -2128831035) ^ this.step_index.hashCode()) * -2128831035) ^ this.device_language.hashCode()) * -2128831035) ^ this.guide_language.hashCode()) * -2128831035;
    }

    public String toString() {
        return "GuestCheckInCheckinGuideGuestTranslateEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", user_id=" + this.user_id + ", step_index=" + this.step_index + ", device_language=" + this.device_language + ", guide_language=" + this.guide_language + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
