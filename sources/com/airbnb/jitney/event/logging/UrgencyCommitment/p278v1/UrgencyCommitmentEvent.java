package com.airbnb.jitney.event.logging.UrgencyCommitment.p278v1;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.airbnb.jitney.event.logging.core.request.p026v1.Request;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.ShareConstants;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.UrgencyCommitment.v1.UrgencyCommitmentEvent */
public final class UrgencyCommitmentEvent implements Struct {
    public static final Adapter<UrgencyCommitmentEvent, Builder> ADAPTER = new UrgencyCommitmentEventAdapter();
    public final UserAction action;
    public final String backend_req_uuid;
    public final String checkin_date;
    public final String checkout_date;
    public final CollisionData collision_data;
    public final Context context;
    public final String event_name;
    public final Long guests;
    public final ImpressionData impression_data;
    public final Long listing_id;
    public final String operation;
    public final String p3_impression_id;
    public final String page;
    public final String parent_req_uuid;
    public final Request request;
    public final String schema;
    public final String search_ranking_id;
    public final String section;
    public final Long user_id;
    public final Long wishlist_id;

    /* renamed from: com.airbnb.jitney.event.logging.UrgencyCommitment.v1.UrgencyCommitmentEvent$Builder */
    public static final class Builder implements StructBuilder<UrgencyCommitmentEvent> {
        /* access modifiers changed from: private */
        public UserAction action;
        /* access modifiers changed from: private */
        public String backend_req_uuid;
        /* access modifiers changed from: private */
        public String checkin_date;
        /* access modifiers changed from: private */
        public String checkout_date;
        /* access modifiers changed from: private */
        public CollisionData collision_data;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String event_name;
        /* access modifiers changed from: private */
        public Long guests;
        /* access modifiers changed from: private */
        public ImpressionData impression_data;
        /* access modifiers changed from: private */
        public Long listing_id;
        /* access modifiers changed from: private */
        public String operation;
        /* access modifiers changed from: private */
        public String p3_impression_id;
        /* access modifiers changed from: private */
        public String page;
        /* access modifiers changed from: private */
        public String parent_req_uuid;
        /* access modifiers changed from: private */
        public Request request;
        /* access modifiers changed from: private */
        public String schema;
        /* access modifiers changed from: private */
        public String search_ranking_id;
        /* access modifiers changed from: private */
        public String section;
        /* access modifiers changed from: private */
        public Long user_id;
        /* access modifiers changed from: private */
        public Long wishlist_id;

        private Builder() {
            this.schema = "com.airbnb.jitney.event.logging.UrgencyCommitment:UrgencyCommitmentEvent:1.0.3";
            this.event_name = "urgency_commitment";
        }

        public Builder(Context context2) {
            this.schema = "com.airbnb.jitney.event.logging.UrgencyCommitment:UrgencyCommitmentEvent:1.0.3";
            this.context = context2;
            this.event_name = "urgency_commitment";
        }

        public Builder operation(String operation2) {
            this.operation = operation2;
            return this;
        }

        public Builder impression_data(ImpressionData impression_data2) {
            this.impression_data = impression_data2;
            return this;
        }

        public Builder listing_id(Long listing_id2) {
            this.listing_id = listing_id2;
            return this;
        }

        public Builder page(String page2) {
            this.page = page2;
            return this;
        }

        public Builder checkin_date(String checkin_date2) {
            this.checkin_date = checkin_date2;
            return this;
        }

        public Builder checkout_date(String checkout_date2) {
            this.checkout_date = checkout_date2;
            return this;
        }

        public Builder guests(Long guests2) {
            this.guests = guests2;
            return this;
        }

        public Builder search_ranking_id(String search_ranking_id2) {
            this.search_ranking_id = search_ranking_id2;
            return this;
        }

        public Builder p3_impression_id(String p3_impression_id2) {
            this.p3_impression_id = p3_impression_id2;
            return this;
        }

        public UrgencyCommitmentEvent build() {
            if (this.context == null) {
                throw new IllegalStateException("Required field 'context' is missing");
            } else if (this.event_name != null) {
                return new UrgencyCommitmentEvent(this);
            } else {
                throw new IllegalStateException("Required field 'event_name' is missing");
            }
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.UrgencyCommitment.v1.UrgencyCommitmentEvent$UrgencyCommitmentEventAdapter */
    private static final class UrgencyCommitmentEventAdapter implements Adapter<UrgencyCommitmentEvent, Builder> {
        private UrgencyCommitmentEventAdapter() {
        }

        public void write(Protocol protocol, UrgencyCommitmentEvent struct) throws IOException {
            protocol.writeStructBegin("UrgencyCommitmentEvent");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            if (struct.operation != null) {
                protocol.writeFieldBegin(BaseAnalytics.OPERATION, 1, PassportService.SF_DG11);
                protocol.writeString(struct.operation);
                protocol.writeFieldEnd();
            }
            if (struct.action != null) {
                protocol.writeFieldBegin("action", 2, 8);
                protocol.writeI32(struct.action.value);
                protocol.writeFieldEnd();
            }
            if (struct.collision_data != null) {
                protocol.writeFieldBegin("collision_data", 3, PassportService.SF_DG12);
                CollisionData.ADAPTER.write(protocol, struct.collision_data);
                protocol.writeFieldEnd();
            }
            if (struct.impression_data != null) {
                protocol.writeFieldBegin("impression_data", 4, PassportService.SF_DG12);
                ImpressionData.ADAPTER.write(protocol, struct.impression_data);
                protocol.writeFieldEnd();
            }
            if (struct.listing_id != null) {
                protocol.writeFieldBegin("listing_id", 5, 10);
                protocol.writeI64(struct.listing_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.wishlist_id != null) {
                protocol.writeFieldBegin("wishlist_id", 6, 10);
                protocol.writeI64(struct.wishlist_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.user_id != null) {
                protocol.writeFieldBegin("user_id", 7, 10);
                protocol.writeI64(struct.user_id.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.page != null) {
                protocol.writeFieldBegin("page", 8, PassportService.SF_DG11);
                protocol.writeString(struct.page);
                protocol.writeFieldEnd();
            }
            if (struct.section != null) {
                protocol.writeFieldBegin(BaseAnalytics.SECTION, 9, PassportService.SF_DG11);
                protocol.writeString(struct.section);
                protocol.writeFieldEnd();
            }
            if (struct.checkin_date != null) {
                protocol.writeFieldBegin("checkin_date", 10, PassportService.SF_DG11);
                protocol.writeString(struct.checkin_date);
                protocol.writeFieldEnd();
            }
            if (struct.checkout_date != null) {
                protocol.writeFieldBegin("checkout_date", 11, PassportService.SF_DG11);
                protocol.writeString(struct.checkout_date);
                protocol.writeFieldEnd();
            }
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 12, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.parent_req_uuid != null) {
                protocol.writeFieldBegin("parent_req_uuid", 13, PassportService.SF_DG11);
                protocol.writeString(struct.parent_req_uuid);
                protocol.writeFieldEnd();
            }
            if (struct.backend_req_uuid != null) {
                protocol.writeFieldBegin("backend_req_uuid", 14, PassportService.SF_DG11);
                protocol.writeString(struct.backend_req_uuid);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(PlaceFields.CONTEXT, 15, PassportService.SF_DG12);
            Context.ADAPTER.write(protocol, struct.context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("event_name", 16, PassportService.SF_DG11);
            protocol.writeString(struct.event_name);
            protocol.writeFieldEnd();
            if (struct.request != null) {
                protocol.writeFieldBegin(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, 17, PassportService.SF_DG12);
                Request.ADAPTER.write(protocol, struct.request);
                protocol.writeFieldEnd();
            }
            if (struct.search_ranking_id != null) {
                protocol.writeFieldBegin("search_ranking_id", 18, PassportService.SF_DG11);
                protocol.writeString(struct.search_ranking_id);
                protocol.writeFieldEnd();
            }
            if (struct.p3_impression_id != null) {
                protocol.writeFieldBegin("p3_impression_id", 19, PassportService.SF_DG11);
                protocol.writeString(struct.p3_impression_id);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private UrgencyCommitmentEvent(Builder builder) {
        this.schema = builder.schema;
        this.operation = builder.operation;
        this.action = builder.action;
        this.collision_data = builder.collision_data;
        this.impression_data = builder.impression_data;
        this.listing_id = builder.listing_id;
        this.wishlist_id = builder.wishlist_id;
        this.user_id = builder.user_id;
        this.page = builder.page;
        this.section = builder.section;
        this.checkin_date = builder.checkin_date;
        this.checkout_date = builder.checkout_date;
        this.guests = builder.guests;
        this.parent_req_uuid = builder.parent_req_uuid;
        this.backend_req_uuid = builder.backend_req_uuid;
        this.context = builder.context;
        this.event_name = builder.event_name;
        this.request = builder.request;
        this.search_ranking_id = builder.search_ranking_id;
        this.p3_impression_id = builder.p3_impression_id;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof UrgencyCommitmentEvent)) {
            return false;
        }
        UrgencyCommitmentEvent that = (UrgencyCommitmentEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.operation == that.operation || (this.operation != null && this.operation.equals(that.operation))) && ((this.action == that.action || (this.action != null && this.action.equals(that.action))) && ((this.collision_data == that.collision_data || (this.collision_data != null && this.collision_data.equals(that.collision_data))) && ((this.impression_data == that.impression_data || (this.impression_data != null && this.impression_data.equals(that.impression_data))) && ((this.listing_id == that.listing_id || (this.listing_id != null && this.listing_id.equals(that.listing_id))) && ((this.wishlist_id == that.wishlist_id || (this.wishlist_id != null && this.wishlist_id.equals(that.wishlist_id))) && ((this.user_id == that.user_id || (this.user_id != null && this.user_id.equals(that.user_id))) && ((this.page == that.page || (this.page != null && this.page.equals(that.page))) && ((this.section == that.section || (this.section != null && this.section.equals(that.section))) && ((this.checkin_date == that.checkin_date || (this.checkin_date != null && this.checkin_date.equals(that.checkin_date))) && ((this.checkout_date == that.checkout_date || (this.checkout_date != null && this.checkout_date.equals(that.checkout_date))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.parent_req_uuid == that.parent_req_uuid || (this.parent_req_uuid != null && this.parent_req_uuid.equals(that.parent_req_uuid))) && ((this.backend_req_uuid == that.backend_req_uuid || (this.backend_req_uuid != null && this.backend_req_uuid.equals(that.backend_req_uuid))) && ((this.context == that.context || this.context.equals(that.context)) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.request == that.request || (this.request != null && this.request.equals(that.request))) && (this.search_ranking_id == that.search_ranking_id || (this.search_ranking_id != null && this.search_ranking_id.equals(that.search_ranking_id))))))))))))))))))))) {
            if (this.p3_impression_id == that.p3_impression_id) {
                return true;
            }
            if (this.p3_impression_id != null && this.p3_impression_id.equals(that.p3_impression_id)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ (this.operation == null ? 0 : this.operation.hashCode())) * -2128831035) ^ (this.action == null ? 0 : this.action.hashCode())) * -2128831035) ^ (this.collision_data == null ? 0 : this.collision_data.hashCode())) * -2128831035) ^ (this.impression_data == null ? 0 : this.impression_data.hashCode())) * -2128831035) ^ (this.listing_id == null ? 0 : this.listing_id.hashCode())) * -2128831035) ^ (this.wishlist_id == null ? 0 : this.wishlist_id.hashCode())) * -2128831035) ^ (this.user_id == null ? 0 : this.user_id.hashCode())) * -2128831035) ^ (this.page == null ? 0 : this.page.hashCode())) * -2128831035) ^ (this.section == null ? 0 : this.section.hashCode())) * -2128831035) ^ (this.checkin_date == null ? 0 : this.checkin_date.hashCode())) * -2128831035) ^ (this.checkout_date == null ? 0 : this.checkout_date.hashCode())) * -2128831035) ^ (this.guests == null ? 0 : this.guests.hashCode())) * -2128831035) ^ (this.parent_req_uuid == null ? 0 : this.parent_req_uuid.hashCode())) * -2128831035) ^ (this.backend_req_uuid == null ? 0 : this.backend_req_uuid.hashCode())) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ (this.request == null ? 0 : this.request.hashCode())) * -2128831035) ^ (this.search_ranking_id == null ? 0 : this.search_ranking_id.hashCode())) * -2128831035;
        if (this.p3_impression_id != null) {
            i = this.p3_impression_id.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "UrgencyCommitmentEvent{schema=" + this.schema + ", operation=" + this.operation + ", action=" + this.action + ", collision_data=" + this.collision_data + ", impression_data=" + this.impression_data + ", listing_id=" + this.listing_id + ", wishlist_id=" + this.wishlist_id + ", user_id=" + this.user_id + ", page=" + this.page + ", section=" + this.section + ", checkin_date=" + this.checkin_date + ", checkout_date=" + this.checkout_date + ", guests=" + this.guests + ", parent_req_uuid=" + this.parent_req_uuid + ", backend_req_uuid=" + this.backend_req_uuid + ", context=" + this.context + ", event_name=" + this.event_name + ", request=" + this.request + ", search_ranking_id=" + this.search_ranking_id + ", p3_impression_id=" + this.p3_impression_id + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
