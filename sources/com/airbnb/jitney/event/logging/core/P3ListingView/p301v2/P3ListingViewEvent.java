package com.airbnb.jitney.event.logging.core.P3ListingView.p301v2;

import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.facebook.places.model.PlaceFields;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.core.P3ListingView.v2.P3ListingViewEvent */
public final class P3ListingViewEvent implements Struct {
    public static final Adapter<P3ListingViewEvent, Object> ADAPTER = new P3ListingViewEventAdapter();
    public final Double accuracy_rating;
    public final List<Long> amenities;
    public final C2837CancelPolicy cancel_policy;
    public final String checkin_date;
    public final Double checkin_rating;
    public final String checkout_date;
    public final Double cleanliness_rating;
    public final Double communication_rating;
    public final Context context;
    public final String description_language;
    public final String event_name;
    public final Long friend_count;
    public final Double guest_satisfaction_overall;
    public final Long guests;
    public final Boolean instant_book_possible;
    public final Boolean is_superhost;
    public final Long listing_id;
    public final Double listing_lat;
    public final Double listing_lng;
    public final Double location_rating;
    public final String operation;
    public final String p3_impression_id;
    public final Long person_capacity;
    public final Long picture_count;
    public final Double response_rate_shown;
    public final String response_time_shown;
    public final String room_type;
    public final String s_tag;
    public final Long saved_to_wishlist_count;
    public final String schema;
    public final String search_ranking_id;
    public final Long utc_offset;
    public final Double value_rating;
    public final Long visible_review_count;

    /* renamed from: com.airbnb.jitney.event.logging.core.P3ListingView.v2.P3ListingViewEvent$P3ListingViewEventAdapter */
    private static final class P3ListingViewEventAdapter implements Adapter<P3ListingViewEvent, Object> {
        private P3ListingViewEventAdapter() {
        }

        public void write(Protocol protocol, P3ListingViewEvent struct) throws IOException {
            protocol.writeStructBegin("P3ListingViewEvent");
            if (struct.schema != null) {
                protocol.writeFieldBegin("schema", 31337, PassportService.SF_DG11);
                protocol.writeString(struct.schema);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(BaseAnalytics.OPERATION, 1, PassportService.SF_DG11);
            protocol.writeString(struct.operation);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("p3_impression_id", 2, PassportService.SF_DG11);
            protocol.writeString(struct.p3_impression_id);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("listing_id", 3, 10);
            protocol.writeI64(struct.listing_id.longValue());
            protocol.writeFieldEnd();
            if (struct.visible_review_count != null) {
                protocol.writeFieldBegin("visible_review_count", 4, 10);
                protocol.writeI64(struct.visible_review_count.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.friend_count != null) {
                protocol.writeFieldBegin("friend_count", 5, 10);
                protocol.writeI64(struct.friend_count.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.person_capacity != null) {
                protocol.writeFieldBegin(ListingRequestConstants.JSON_PERSON_CAPACITY_KEY, 6, 10);
                protocol.writeI64(struct.person_capacity.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.saved_to_wishlist_count != null) {
                protocol.writeFieldBegin("saved_to_wishlist_count", 8, 10);
                protocol.writeI64(struct.saved_to_wishlist_count.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.response_rate_shown != null) {
                protocol.writeFieldBegin("response_rate_shown", 9, 4);
                protocol.writeDouble(struct.response_rate_shown.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.response_time_shown != null) {
                protocol.writeFieldBegin("response_time_shown", 10, PassportService.SF_DG11);
                protocol.writeString(struct.response_time_shown);
                protocol.writeFieldEnd();
            }
            if (struct.guest_satisfaction_overall != null) {
                protocol.writeFieldBegin("guest_satisfaction_overall", 11, 4);
                protocol.writeDouble(struct.guest_satisfaction_overall.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.accuracy_rating != null) {
                protocol.writeFieldBegin("accuracy_rating", 12, 4);
                protocol.writeDouble(struct.accuracy_rating.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.cleanliness_rating != null) {
                protocol.writeFieldBegin("cleanliness_rating", 13, 4);
                protocol.writeDouble(struct.cleanliness_rating.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.checkin_rating != null) {
                protocol.writeFieldBegin("checkin_rating", 14, 4);
                protocol.writeDouble(struct.checkin_rating.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.communication_rating != null) {
                protocol.writeFieldBegin("communication_rating", 15, 4);
                protocol.writeDouble(struct.communication_rating.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.location_rating != null) {
                protocol.writeFieldBegin("location_rating", 16, 4);
                protocol.writeDouble(struct.location_rating.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.value_rating != null) {
                protocol.writeFieldBegin("value_rating", 17, 4);
                protocol.writeDouble(struct.value_rating.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.picture_count != null) {
                protocol.writeFieldBegin("picture_count", 18, 10);
                protocol.writeI64(struct.picture_count.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.cancel_policy != null) {
                protocol.writeFieldBegin("cancel_policy", 19, 8);
                protocol.writeI32(struct.cancel_policy.value);
                protocol.writeFieldEnd();
            }
            if (struct.amenities != null) {
                protocol.writeFieldBegin("amenities", 20, 15);
                protocol.writeListBegin(10, struct.amenities.size());
                for (Long item0 : struct.amenities) {
                    protocol.writeI64(item0.longValue());
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.instant_book_possible != null) {
                protocol.writeFieldBegin("instant_book_possible", 21, 2);
                protocol.writeBool(struct.instant_book_possible.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.utc_offset != null) {
                protocol.writeFieldBegin("utc_offset", 22, 10);
                protocol.writeI64(struct.utc_offset.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("room_type", 23, PassportService.SF_DG11);
            protocol.writeString(struct.room_type);
            protocol.writeFieldEnd();
            if (struct.checkin_date != null) {
                protocol.writeFieldBegin("checkin_date", 24, PassportService.SF_DG11);
                protocol.writeString(struct.checkin_date);
                protocol.writeFieldEnd();
            }
            if (struct.checkout_date != null) {
                protocol.writeFieldBegin("checkout_date", 25, PassportService.SF_DG11);
                protocol.writeString(struct.checkout_date);
                protocol.writeFieldEnd();
            }
            if (struct.guests != null) {
                protocol.writeFieldBegin(FindTweenAnalytics.GUESTS, 26, 10);
                protocol.writeI64(struct.guests.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.s_tag != null) {
                protocol.writeFieldBegin("s_tag", 27, PassportService.SF_DG11);
                protocol.writeString(struct.s_tag);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin(PlaceFields.CONTEXT, 28, PassportService.SF_DG12);
            Context.ADAPTER.write(protocol, struct.context);
            protocol.writeFieldEnd();
            protocol.writeFieldBegin("event_name", 29, PassportService.SF_DG11);
            protocol.writeString(struct.event_name);
            protocol.writeFieldEnd();
            if (struct.listing_lat != null) {
                protocol.writeFieldBegin("listing_lat", 30, 4);
                protocol.writeDouble(struct.listing_lat.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.listing_lng != null) {
                protocol.writeFieldBegin("listing_lng", 31, 4);
                protocol.writeDouble(struct.listing_lng.doubleValue());
                protocol.writeFieldEnd();
            }
            if (struct.is_superhost != null) {
                protocol.writeFieldBegin("is_superhost", 32, 2);
                protocol.writeBool(struct.is_superhost.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.search_ranking_id != null) {
                protocol.writeFieldBegin("search_ranking_id", 33, PassportService.SF_DG11);
                protocol.writeString(struct.search_ranking_id);
                protocol.writeFieldEnd();
            }
            protocol.writeFieldBegin("description_language", 34, PassportService.SF_DG11);
            protocol.writeString(struct.description_language);
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
        if (!(other instanceof P3ListingViewEvent)) {
            return false;
        }
        P3ListingViewEvent that = (P3ListingViewEvent) other;
        if ((this.schema == that.schema || (this.schema != null && this.schema.equals(that.schema))) && ((this.operation == that.operation || this.operation.equals(that.operation)) && ((this.p3_impression_id == that.p3_impression_id || this.p3_impression_id.equals(that.p3_impression_id)) && ((this.listing_id == that.listing_id || this.listing_id.equals(that.listing_id)) && ((this.visible_review_count == that.visible_review_count || (this.visible_review_count != null && this.visible_review_count.equals(that.visible_review_count))) && ((this.friend_count == that.friend_count || (this.friend_count != null && this.friend_count.equals(that.friend_count))) && ((this.person_capacity == that.person_capacity || (this.person_capacity != null && this.person_capacity.equals(that.person_capacity))) && ((this.saved_to_wishlist_count == that.saved_to_wishlist_count || (this.saved_to_wishlist_count != null && this.saved_to_wishlist_count.equals(that.saved_to_wishlist_count))) && ((this.response_rate_shown == that.response_rate_shown || (this.response_rate_shown != null && this.response_rate_shown.equals(that.response_rate_shown))) && ((this.response_time_shown == that.response_time_shown || (this.response_time_shown != null && this.response_time_shown.equals(that.response_time_shown))) && ((this.guest_satisfaction_overall == that.guest_satisfaction_overall || (this.guest_satisfaction_overall != null && this.guest_satisfaction_overall.equals(that.guest_satisfaction_overall))) && ((this.accuracy_rating == that.accuracy_rating || (this.accuracy_rating != null && this.accuracy_rating.equals(that.accuracy_rating))) && ((this.cleanliness_rating == that.cleanliness_rating || (this.cleanliness_rating != null && this.cleanliness_rating.equals(that.cleanliness_rating))) && ((this.checkin_rating == that.checkin_rating || (this.checkin_rating != null && this.checkin_rating.equals(that.checkin_rating))) && ((this.communication_rating == that.communication_rating || (this.communication_rating != null && this.communication_rating.equals(that.communication_rating))) && ((this.location_rating == that.location_rating || (this.location_rating != null && this.location_rating.equals(that.location_rating))) && ((this.value_rating == that.value_rating || (this.value_rating != null && this.value_rating.equals(that.value_rating))) && ((this.picture_count == that.picture_count || (this.picture_count != null && this.picture_count.equals(that.picture_count))) && ((this.cancel_policy == that.cancel_policy || (this.cancel_policy != null && this.cancel_policy.equals(that.cancel_policy))) && ((this.amenities == that.amenities || (this.amenities != null && this.amenities.equals(that.amenities))) && ((this.instant_book_possible == that.instant_book_possible || (this.instant_book_possible != null && this.instant_book_possible.equals(that.instant_book_possible))) && ((this.utc_offset == that.utc_offset || (this.utc_offset != null && this.utc_offset.equals(that.utc_offset))) && ((this.room_type == that.room_type || this.room_type.equals(that.room_type)) && ((this.checkin_date == that.checkin_date || (this.checkin_date != null && this.checkin_date.equals(that.checkin_date))) && ((this.checkout_date == that.checkout_date || (this.checkout_date != null && this.checkout_date.equals(that.checkout_date))) && ((this.guests == that.guests || (this.guests != null && this.guests.equals(that.guests))) && ((this.s_tag == that.s_tag || (this.s_tag != null && this.s_tag.equals(that.s_tag))) && ((this.context == that.context || this.context.equals(that.context)) && ((this.event_name == that.event_name || this.event_name.equals(that.event_name)) && ((this.listing_lat == that.listing_lat || (this.listing_lat != null && this.listing_lat.equals(that.listing_lat))) && ((this.listing_lng == that.listing_lng || (this.listing_lng != null && this.listing_lng.equals(that.listing_lng))) && ((this.is_superhost == that.is_superhost || (this.is_superhost != null && this.is_superhost.equals(that.is_superhost))) && ((this.search_ranking_id == that.search_ranking_id || (this.search_ranking_id != null && this.search_ranking_id.equals(that.search_ranking_id))) && (this.description_language == that.description_language || this.description_language.equals(that.description_language))))))))))))))))))))))))))))))))))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((16777619 ^ (this.schema == null ? 0 : this.schema.hashCode())) * -2128831035) ^ this.operation.hashCode()) * -2128831035) ^ this.p3_impression_id.hashCode()) * -2128831035) ^ this.listing_id.hashCode()) * -2128831035) ^ (this.visible_review_count == null ? 0 : this.visible_review_count.hashCode())) * -2128831035) ^ (this.friend_count == null ? 0 : this.friend_count.hashCode())) * -2128831035) ^ (this.person_capacity == null ? 0 : this.person_capacity.hashCode())) * -2128831035) ^ (this.saved_to_wishlist_count == null ? 0 : this.saved_to_wishlist_count.hashCode())) * -2128831035) ^ (this.response_rate_shown == null ? 0 : this.response_rate_shown.hashCode())) * -2128831035) ^ (this.response_time_shown == null ? 0 : this.response_time_shown.hashCode())) * -2128831035) ^ (this.guest_satisfaction_overall == null ? 0 : this.guest_satisfaction_overall.hashCode())) * -2128831035) ^ (this.accuracy_rating == null ? 0 : this.accuracy_rating.hashCode())) * -2128831035) ^ (this.cleanliness_rating == null ? 0 : this.cleanliness_rating.hashCode())) * -2128831035) ^ (this.checkin_rating == null ? 0 : this.checkin_rating.hashCode())) * -2128831035) ^ (this.communication_rating == null ? 0 : this.communication_rating.hashCode())) * -2128831035) ^ (this.location_rating == null ? 0 : this.location_rating.hashCode())) * -2128831035) ^ (this.value_rating == null ? 0 : this.value_rating.hashCode())) * -2128831035) ^ (this.picture_count == null ? 0 : this.picture_count.hashCode())) * -2128831035) ^ (this.cancel_policy == null ? 0 : this.cancel_policy.hashCode())) * -2128831035) ^ (this.amenities == null ? 0 : this.amenities.hashCode())) * -2128831035) ^ (this.instant_book_possible == null ? 0 : this.instant_book_possible.hashCode())) * -2128831035) ^ (this.utc_offset == null ? 0 : this.utc_offset.hashCode())) * -2128831035) ^ this.room_type.hashCode()) * -2128831035) ^ (this.checkin_date == null ? 0 : this.checkin_date.hashCode())) * -2128831035) ^ (this.checkout_date == null ? 0 : this.checkout_date.hashCode())) * -2128831035) ^ (this.guests == null ? 0 : this.guests.hashCode())) * -2128831035) ^ (this.s_tag == null ? 0 : this.s_tag.hashCode())) * -2128831035) ^ this.context.hashCode()) * -2128831035) ^ this.event_name.hashCode()) * -2128831035) ^ (this.listing_lat == null ? 0 : this.listing_lat.hashCode())) * -2128831035) ^ (this.listing_lng == null ? 0 : this.listing_lng.hashCode())) * -2128831035) ^ (this.is_superhost == null ? 0 : this.is_superhost.hashCode())) * -2128831035;
        if (this.search_ranking_id != null) {
            i = this.search_ranking_id.hashCode();
        }
        return (((code ^ i) * -2128831035) ^ this.description_language.hashCode()) * -2128831035;
    }

    public String toString() {
        return "P3ListingViewEvent{schema=" + this.schema + ", operation=" + this.operation + ", p3_impression_id=" + this.p3_impression_id + ", listing_id=" + this.listing_id + ", visible_review_count=" + this.visible_review_count + ", friend_count=" + this.friend_count + ", person_capacity=" + this.person_capacity + ", saved_to_wishlist_count=" + this.saved_to_wishlist_count + ", response_rate_shown=" + this.response_rate_shown + ", response_time_shown=" + this.response_time_shown + ", guest_satisfaction_overall=" + this.guest_satisfaction_overall + ", accuracy_rating=" + this.accuracy_rating + ", cleanliness_rating=" + this.cleanliness_rating + ", checkin_rating=" + this.checkin_rating + ", communication_rating=" + this.communication_rating + ", location_rating=" + this.location_rating + ", value_rating=" + this.value_rating + ", picture_count=" + this.picture_count + ", cancel_policy=" + this.cancel_policy + ", amenities=" + this.amenities + ", instant_book_possible=" + this.instant_book_possible + ", utc_offset=" + this.utc_offset + ", room_type=" + this.room_type + ", checkin_date=" + this.checkin_date + ", checkout_date=" + this.checkout_date + ", guests=" + this.guests + ", s_tag=" + this.s_tag + ", context=" + this.context + ", event_name=" + this.event_name + ", listing_lat=" + this.listing_lat + ", listing_lng=" + this.listing_lng + ", is_superhost=" + this.is_superhost + ", search_ranking_id=" + this.search_ranking_id + ", description_language=" + this.description_language + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
