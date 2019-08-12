package com.airbnb.jitney.event.logging.GuestCheckIn.p106v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.GuestCheckIn.v1.GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent */
public final class GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent implements Struct {
    public static final Adapter<GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent, Builder> ADAPTER = new C2172xc6b0afaa();
    public final Context context;
    public final String event_name;
    public final Long listing_id;
    public final C2451Operation operation;
    public final String page;
    public final String schema;
    public final String visible_date;

    /* renamed from: com.airbnb.jitney.event.logging.GuestCheckIn.v1.GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent$Builder */
    public static final class Builder implements StructBuilder<GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public C2451Operation operation;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String visible_date;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.GuestCheckIn:GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent:1.0.0";
            this.event_name = "guestcheckin_checkin_guide_guest_successful_loading_offline";
            this.page = "checkin_guide_guest_view";
            this.operation = C2451Operation.Save;
        }

        public Builder(Context context2, Long listing_id2, String visible_date2) {
            this.schema = "com.airbnb.jitney.event.logging.GuestCheckIn:GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent:1.0.0";
            this.event_name = "guestcheckin_checkin_guide_guest_successful_loading_offline";
            this.context = context2;
            this.page = "checkin_guide_guest_view";
            this.operation = C2451Operation.Save;
            this.listing_id = listing_id2;
            this.visible_date = visible_date2;
        }

        public GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent build() {
            if (this.event_name == null) {
                throw new IllegalStateException("Required field 'event_name' is missing");
            } else if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.page == null) {
                throw new IllegalStateException("Required field 'page' is missing");
            } else if (this.operation == null) {
                throw new IllegalStateException("Required field 'operation' is missing");
            } else if (this.listing_id == null) {
                throw new IllegalStateException("Required field 'listing_id' is missing");
            } else if (this.visible_date != null) {
                return new GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent(this);
            } else {
                throw new IllegalStateException("Required field 'visible_date' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.GuestCheckIn.v1.GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent$GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEventAdapter */
    private static final class C2172xc6b0afaa implements Adapter<GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent, Builder> {
        private C2172xc6b0afaa() {
        }

        public void write(Protocol protocol, GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent struct) throws IOException {
            protocol.writeStructBegin("GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent");
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
            protocol.writeFieldBegin("visible_date", 6, PassportService.SF_DG11);
            protocol.writeString(struct.visible_date);
            protocol.writeFieldEnd();
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent(Builder builder) {
        this.schema = builder.schema;
        this.event_name = builder.event_name;
        this.context = builder.context;
        this.page = builder.page;
        this.operation = builder.operation;
        this.listing_id = builder.listing_id;
        this.visible_date = builder.visible_date;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent)) {
            return false;
        }
        GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent that = (GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.context == that.context || this.context.equals(that.context)) && ((this.page == that.page || this.page.equals(that.page)) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && (this.visible_date == that.visible_date || this.visible_date.equals(that.visible_date)))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.page.hashCode()) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ this.visible_date.hashCode()) * -2128831035;
    }

    public String toString() {
        return "GuestCheckInCheckinGuideGuestSuccessfulLoadingOfflineEvent{schema=" + this.schema + ", event_name=" + this.event_name + ", context=" + this.context + ", page=" + this.page + ", operation=" + this.operation + ", listing_id=" + this.listing_id + ", visible_date=" + this.visible_date + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
